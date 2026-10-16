package datastructure;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Queue<T> (Shifting Array Implementation) Unit Tests")
public class QueueTest {

    @Nested
    @DisplayName("1. When Queue is Newly Created (Empty)")
    class WhenEmpty {

        private Queue<String> queue;

        @BeforeEach
        void setUp() {
            queue = new Queue<>(5);
        }

        @Test
        @DisplayName("remove(), peekFront(), and peekRear() must return null when queue is empty")
        void shouldReturnNullWhenEmpty() {
            // Group testing empty queue boundaries
            assertAll("Empty queue boundary checks",
                    () -> assertNull(queue.remove(), "remove() should return null on empty queue"),
                    () -> assertNull(queue.peekFront(), "peekFront() should return null on empty queue"),
                    () -> assertNull(queue.peekRear(), "peekRear() should return null on empty queue")
            );
        }

        @Test
        @DisplayName("toString() and display() should handle empty state gracefully")
        void shouldHandleDisplayAndToStringGracefully() {
            assertAll("Empty queue display operations",
                    () -> assertNotNull(queue.toString(), "toString() should not return null"),
                    () -> assertDoesNotThrow(() -> queue.display(), "display() should not throw on empty queue")
            );
        }
    }

    @Nested
    @DisplayName("2. Insert and Peek Operations")
    class InsertAndPeekOperations {

        private Queue<Integer> queue;

        @BeforeEach
        void setUp() {
            queue = new Queue<>(5);
        }

        @Test
        @DisplayName("Single item insert makes peekFront() and peekRear() return that item non-destructively")
        void shouldPointBothPeeksToSingleElement() {
            queue.insert(99);

            assertAll("Single item front and rear checks",
                    () -> assertEquals(99, queue.peekFront(), "peekFront() should return 99"),
                    () -> assertEquals(99, queue.peekRear(), "peekRear() should return 99"),
                    () -> assertEquals(99, queue.peekFront(), "peekFront() must remain non-destructive"),
                    () -> assertEquals(99, queue.peekRear(), "peekRear() must remain non-destructive")
            );
        }

        @Test
        @DisplayName("Multiple inserts maintain correct front (oldest) and rear (newest)")
        void shouldReflectFrontAndRearAccurately() {
            queue.insert(10);
            queue.insert(20);
            queue.insert(30);

            assertAll("Multiple item peeks",
                    () -> assertEquals(10, queue.peekFront(), "peekFront() should be the first item inserted (10)"),
                    () -> assertEquals(30, queue.peekRear(), "peekRear() should be the latest item inserted (30)")
            );
        }
    }

    @Nested
    @DisplayName("3. Shifting Removal and FIFO Order")
    class ShiftingRemovalTests {

        private Queue<String> queue;

        @BeforeEach
        void setUp() {
            queue = new Queue<>(4);
            queue.insert("A");
            queue.insert("B");
            queue.insert("C");
        }

        @Test
        @DisplayName("remove() retrieves items in strict FIFO order while shifting remaining elements to the left")
        void shouldRemoveInFifoOrderAndShiftElements() {
            // Initial front is "A", rear is "C"
            assertEquals("A", queue.peekFront());
            assertEquals("C", queue.peekRear());

            // Remove "A" -> "B" must shift to index 0 (new front)
            String removed1 = queue.remove();
            assertAll("After removing first item (verifying left shift)",
                    () -> assertEquals("A", removed1),
                    () -> assertEquals("B", queue.peekFront(), "After shifting, 'B' must now be at index 0 (peekFront)"),
                    () -> assertEquals("C", queue.peekRear(), "peekRear() should still be 'C'")
            );

            // Remove "B" -> "C" must shift to index 0
            String removed2 = queue.remove();
            assertAll("After removing second item",
                    () -> assertEquals("B", removed2),
                    () -> assertEquals("C", queue.peekFront(), "'C' should now be at front"),
                    () -> assertEquals("C", queue.peekRear(), "'C' is now both front and rear")
            );

            // Remove "C" -> queue becomes empty
            String removed3 = queue.remove();
            assertAll("After removing last item",
                    () -> assertEquals("C", removed3),
                    () -> assertNull(queue.remove(), "remove() should return null after exhausting items"),
                    () -> assertNull(queue.peekFront(), "peekFront() should return null when empty"),
                    () -> assertNull(queue.peekRear(), "peekRear() should return null when empty")
            );
        }
    }

    @Nested
    @DisplayName("4. Space Reclamation Through Shifting (Without Premature Resizing)")
    class ShiftingReclamationTests {

        @Test
        @DisplayName("Removing elements frees up slots at the rear, allowing new inserts without resizing")
        void shouldReclaimRearSlotsViaLeftShift() {
            // Capacity: 3
            Queue<String> shiftingQueue = new Queue<>(3);

            // Fill to capacity: [X, Y, Z]
            shiftingQueue.insert("X");
            shiftingQueue.insert("Y");
            shiftingQueue.insert("Z");

            // Remove "X": elements shift left -> [Y, Z, _], freeing the 3rd slot
            assertEquals("X", shiftingQueue.remove());

            // Now insert "W". In a shifting queue, this fits in the reclaimed slot without resizing
            assertDoesNotThrow(() -> shiftingQueue.insert("W"));

            assertAll("State verification after slot reclamation",
                    () -> assertEquals("Y", shiftingQueue.peekFront(), "Front should be 'Y'"),
                    () -> assertEquals("W", shiftingQueue.peekRear(), "Rear should be newly inserted 'W'"),
                    () -> assertEquals("Y", shiftingQueue.remove()),
                    () -> assertEquals("Z", shiftingQueue.remove()),
                    () -> assertEquals("W", shiftingQueue.remove()),
                    () -> assertNull(shiftingQueue.remove(), "Queue should be empty")
            );
        }

        @Test
        @DisplayName("Handles continuous interleaved inserts and removes without unbounded memory growth")
        void shouldHandleContinuousStreamOfInsertsAndRemoves() {
            // Queue capacity is only 2
            Queue<Integer> streamingQueue = new Queue<>(2);

            // Continuously push and pop 50 items
            for (int i = 0; i < 50; i++) {
                streamingQueue.insert(i);
                assertEquals(i, streamingQueue.peekFront());
                assertEquals(i, streamingQueue.peekRear());
                assertEquals(i, streamingQueue.remove(), "Mismatch at streamed item " + i);
                assertNull(streamingQueue.peekFront());
            }

            assertNull(streamingQueue.remove());
        }
    }

    @Nested
    @DisplayName("5. Dynamic Array Resizing")
    class ResizingOperations {

        @Test
        @DisplayName("Resizes when number of active elements exceeds initial capacity")
        void shouldResizeWhenCapacityExceeded() {
            Queue<Integer> queue = new Queue<>(2);

            queue.insert(100);
            queue.insert(200);

            // Trigger dynamic resizing
            assertDoesNotThrow(() -> queue.insert(300), "Insert beyond capacity should trigger resize");
            assertDoesNotThrow(() -> queue.insert(400), "Further inserts should succeed");

            assertAll("Order and peeks after resizing",
                    () -> assertEquals(100, queue.peekFront()),
                    () -> assertEquals(400, queue.peekRear()),
                    () -> assertEquals(100, queue.remove()),
                    () -> assertEquals(200, queue.peekFront(), "After remove, 200 shifts to front"),
                    () -> assertEquals(200, queue.remove()),
                    () -> assertEquals(300, queue.remove()),
                    () -> assertEquals(400, queue.remove()),
                    () -> assertNull(queue.remove(), "Queue should be empty")
            );
        }

        @Test
        @DisplayName("Handles multiple consecutive resizes starting from capacity 1")
        void shouldHandleMultipleConsecutiveResizes() {
            Queue<Integer> tinyQueue = new Queue<>(1);
            int count = 40;

            for (int i = 0; i < count; i++) {
                final int val = i;
                assertDoesNotThrow(() -> tinyQueue.insert(val), "Insert failed at " + val);
            }

            assertAll("Front and rear after large insert volume",
                    () -> assertEquals(0, tinyQueue.peekFront()),
                    () -> assertEquals(count - 1, tinyQueue.peekRear())
            );

            // Verify all 40 items shift and dequeue in strict FIFO order
            for (int i = 0; i < count; i++) {
                assertEquals(i, tinyQueue.peekFront(), "Front mismatch before removal of " + i);
                assertEquals(i, tinyQueue.remove(), "Removed mismatch at " + i);
            }

            assertNull(tinyQueue.remove());
        }
    }

    @Nested
    @DisplayName("6. Generic Types Compatibility")
    class GenericTypeSupport {

        @Test
        @DisplayName("Shifting queue works correctly with custom objects/records")
        void shouldSupportCustomObjects() {
            record Document(int id, String title) {}

            Queue<Document> printQueue = new Queue<>(2);
            Document d1 = new Document(1, "Resume.pdf");
            Document d2 = new Document(2, "Report.docx");
            Document d3 = new Document(3, "Invoice.pdf");

            printQueue.insert(d1);
            printQueue.insert(d2);
            printQueue.insert(d3); // triggers resize

            assertAll("Custom objects FIFO with shifting",
                    () -> assertEquals(d1, printQueue.peekFront()),
                    () -> assertEquals(d3, printQueue.peekRear()),
                    () -> assertEquals(d1, printQueue.remove()),
                    () -> assertEquals(d2, printQueue.peekFront(), "d2 should have shifted to front"),
                    () -> assertEquals(d2, printQueue.remove()),
                    () -> assertEquals(d3, printQueue.remove()),
                    () -> assertNull(printQueue.remove())
            );
        }
    }

    @Nested
    @DisplayName("7. String Representation and Display")
    class ToStringAndDisplayTests {

        @Test
        @DisplayName("toString() reflects elements in order and display() executes without exceptions")
        void shouldVerifyToStringAndDisplay() {
            Queue<String> queue = new Queue<>(3);
            queue.insert("Item1");
            queue.insert("Item2");

            String output = queue.toString();

            assertAll("toString format inspection and display execution",
                    () -> assertNotNull(output, "toString() should not be null"),
                    () -> assertTrue(output.contains("Item1"), "toString() must contain 'Item1'"),
                    () -> assertTrue(output.contains("Item2"), "toString() must contain 'Item2'"),
                    () -> assertDoesNotThrow(queue::display, "display() should execute without throwing")
            );
        }
    }
}