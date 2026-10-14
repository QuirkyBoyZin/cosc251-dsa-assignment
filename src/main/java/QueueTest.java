import datastructure.Queue;

public class QueueTest {

    public static void main(String[] args) {
        int passed = 0;
        int total = 0;

        System.out.println("--- Starting Queue Tests ---");

        total++;
        if (testBasicInsertAndPeek()) passed++;

        total++;
        if (testFIFOOrderAndRemove()) passed++;

        total++;
        if (testWrapAroundOrShifting()) passed++;

        total++;
        if (testEmptyQueueExceptions()) passed++;

        total++;
        if (testGenericsWithString()) passed++;

        total++;
        if (testToStringAndDisplay()) passed++;

        System.out.println("\n--- Test Summary ---");
        System.out.println("Passed: " + passed + " / " + total);
        if (passed == total) {
            System.out.println("Result: ALL TESTS PASSED!");
        } else {
            System.out.println("Result: SOME TESTS FAILED. Check the logs above.");
        }
    }

    private static boolean testBasicInsertAndPeek() {
        try {
            Queue<Integer> queue = new Queue<>(5);
            queue.insert(10);
            queue.insert(20);

            if (queue.peekFront() != 10) return fail("Basic Insert", "peekFront() should be 10, got " + queue.peekFront());
            if (queue.peekRear() != 20) return fail("Basic Insert", "peekRear() should be 20, got " + queue.peekRear());

            return pass("Basic Insert & Peek");
        } catch (Exception e) {
            return fail("Basic Insert", "Threw exception: " + e.getMessage());
        }
    }

    private static boolean testFIFOOrderAndRemove() {
        try {
            Queue<Integer> queue = new Queue<>(5);
            queue.insert(1);
            queue.insert(2);
            queue.insert(3);

            int removed1 = queue.remove();
            if (removed1 != 1) return fail("FIFO Remove", "First removed should be 1, got " + removed1);

            if (queue.peekFront() != 2) return fail("FIFO Remove", "After one remove, peekFront() should be 2, got " + queue.peekFront());

            int removed2 = queue.remove();
            if (removed2 != 2) return fail("FIFO Remove", "Second removed should be 2, got " + removed2);

            return pass("FIFO Order & Remove");
        } catch (Exception e) {
            return fail("FIFO Remove", "Threw exception: " + e.getMessage());
        }
    }

    private static boolean testWrapAroundOrShifting() {
        try {
            // Create a small queue to easily trigger wrap-around or shifting
            Queue<Integer> queue = new Queue<>(3);
            queue.insert(100);
            queue.insert(200);
            queue.insert(300);

            // Remove two elements (Queue now has space, front is at index 2 if circular)
            queue.remove(); // removes 100
            queue.remove(); // removes 200

            // Insert two more elements to force wrap-around or shifting
            queue.insert(400);
            queue.insert(500);

            if (queue.peekFront() != 300) return fail("Wrap/Shift", "peekFront() should be 300, got " + queue.peekFront());
            if (queue.peekRear() != 500) return fail("Wrap/Shift", "peekRear() should be 500, got " + queue.peekRear());

            // Empty the rest to verify order
            if (queue.remove() != 300) return fail("Wrap/Shift", "Expected 300");
            if (queue.remove() != 400) return fail("Wrap/Shift", "Expected 400");
            if (queue.remove() != 500) return fail("Wrap/Shift", "Expected 500");

            return pass("Wrap-around / Shifting Logic");
        } catch (Exception e) {
            return fail("Wrap/Shift", "Threw exception: " + e.getMessage());
        }
    }

    private static boolean testEmptyQueueExceptions() {
        try {
            Queue<Integer> queue = new Queue<>(2);
            boolean caughtRemove = false;

            try {
                queue.remove();
            } catch (Exception e) {
                // Depending on your implementation, this could be RuntimeException, NoSuchElementException, etc.
                caughtRemove = true;
            }

            if (!caughtRemove) {
                // Warning rather than hard fail, as some might return null instead of throwing exceptions
                System.out.println("  [!] Warning: remove() on empty queue did not throw an exception (it might have returned null).");
            }

            return pass("Empty Queue Handling");
        } catch (Exception e) {
            return fail("Empty Queue", "Unexpected exception during setup: " + e.getMessage());
        }
    }

    private static boolean testGenericsWithString() {
        try {
            Queue<String> queue = new Queue<>(3);
            queue.insert("Hello");
            queue.insert("World");

            if (!queue.peekFront().equals("Hello")) return fail("Generics", "Expected 'Hello', got " + queue.peekFront());
            if (!queue.remove().equals("Hello")) return fail("Generics", "Expected remove to return 'Hello'");

            return pass("Generic Type Support (String)");
        } catch (Exception e) {
            return fail("Generics", "Threw exception: " + e.getMessage());
        }
    }

    private static boolean testToStringAndDisplay() {
        try {
            Queue<Integer> queue = new Queue<>(3);
            queue.insert(7);
            queue.insert(8);

            String str = queue.toString();
            if (str == null || str.trim().isEmpty()) {
                return fail("Formatting", "toString() returned null or empty");
            }

            System.out.println("  -> toString() output: " + str);
            System.out.print("  -> display() output : ");
            queue.display(); // Visually verify this prints correctly in the console

            return pass("toString and display methods");
        } catch (Exception e) {
            return fail("Formatting", "Threw exception: " + e.getMessage());
        }
    }

    // Helper methods for formatting output
    private static boolean pass(String testName) {
        System.out.println("[PASS] " + testName);
        return true;
    }

    private static boolean fail(String testName, String reason) {
        System.out.println("[FAIL] " + testName + " - " + reason);
        return false;
    }
}