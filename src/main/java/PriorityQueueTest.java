import datastructure.PriorityQueue;

public class PriorityQueueTest {

    public static void main(String[] args) {
        int passed = 0;
        int total = 0;

        System.out.println("--- Starting PriorityQueue Tests ---");

        total++;
        if (testPriorityOrderAndRemove()) passed++;

        total++;
        if (testPeekFrontAndRear()) passed++;

        total++;
        if (testDuplicatePriorities()) passed++;

        total++;
        if (testEmptyQueueExceptions()) passed++;

        total++;
        if (testGenericsWithStrings()) passed++;

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

    private static boolean testPriorityOrderAndRemove() {
        try {
            // Smaller priorityValue = higher priority (comes out first)
            PriorityQueue<String> pq = new PriorityQueue<>(5);
            pq.insert("Medium Priority", 5);
            pq.insert("High Priority", 1);
            pq.insert("Low Priority", 10);
            pq.insert("Urgent Priority", 0);

            if (!pq.remove().equals("Urgent Priority")) return fail("Priority Order", "1st removed should be 'Urgent Priority' (0)");
            if (!pq.remove().equals("High Priority")) return fail("Priority Order", "2nd removed should be 'High Priority' (1)");
            if (!pq.remove().equals("Medium Priority")) return fail("Priority Order", "3rd removed should be 'Medium Priority' (5)");
            if (!pq.remove().equals("Low Priority")) return fail("Priority Order", "4th removed should be 'Low Priority' (10)");

            return pass("Priority Order & Remove");
        } catch (Exception e) {
            return fail("Priority Order", "Threw exception: " + e.getMessage());
        }
    }

    private static boolean testPeekFrontAndRear() {
        try {
            PriorityQueue<Character> pq = new PriorityQueue<>(5);
            pq.insert('B', 2);
            pq.insert('A', 1);
            pq.insert('D', 4);
            pq.insert('C', 3);

            // Front should be the lowest value (1), Rear should be the highest value (4)
            if (pq.peekFront() != 'A') return fail("Peek Methods", "peekFront() should be 'A' (1), got " + pq.peekFront());
            if (pq.peekRear() != 'D') return fail("Peek Methods", "peekRear() should be 'D' (4), got " + pq.peekRear());

            // Remove the front element, verify front updates correctly
            pq.remove();
            if (pq.peekFront() != 'B') return fail("Peek Methods", "After removing 'A', peekFront() should be 'B' (2), got " + pq.peekFront());

            // Rear should remain the same
            if (pq.peekRear() != 'D') return fail("Peek Methods", "peekRear() should still be 'D' (4)");

            return pass("peekFront and peekRear");
        } catch (Exception e) {
            return fail("Peek Methods", "Threw exception: " + e.getMessage());
        }
    }

    private static boolean testDuplicatePriorities() {
        try {
            PriorityQueue<String> pq = new PriorityQueue<>(5);
            pq.insert("Task A", 2);
            pq.insert("Task B", 2);
            pq.insert("Task C", 1);

            // Task C should come out first
            if (!pq.remove().equals("Task C")) return fail("Duplicate Priorities", "Expected 'Task C' (1) to be removed first");

            // Task A and Task B have the same priority.
            // NOTE: Whether A or B comes out first depends on if your PQ is "stable".
            // We'll just verify that they both come out before the queue is empty without crashing.
            String next1 = pq.remove();
            String next2 = pq.remove();

            boolean containsAandB = (next1.equals("Task A") && next2.equals("Task B")) ||
                    (next1.equals("Task B") && next2.equals("Task A"));

            if (!containsAandB) {
                return fail("Duplicate Priorities", "Expected to remove 'Task A' and 'Task B'. Got: " + next1 + ", " + next2);
            }

            return pass("Handling Duplicate Priorities");
        } catch (Exception e) {
            return fail("Duplicate Priorities", "Threw exception: " + e.getMessage());
        }
    }

    private static boolean testEmptyQueueExceptions() {
        try {
            PriorityQueue<Integer> pq = new PriorityQueue<>(3);
            boolean caughtRemove = false;

            try {
                pq.remove();
            } catch (Exception e) {
                caughtRemove = true;
            }

            if (!caughtRemove) {
                System.out.println("  [!] Warning: remove() on empty priority queue did not throw an exception (it might have returned null).");
            }

            return pass("Empty PriorityQueue Handling");
        } catch (Exception e) {
            return fail("Empty PriorityQueue", "Unexpected exception during setup: " + e.getMessage());
        }
    }

    private static boolean testGenericsWithStrings() {
        try {
            PriorityQueue<Double> pq = new PriorityQueue<>(3);
            pq.insert(99.9, 5);
            pq.insert(1.1, 1);

            if (pq.peekFront() != 1.1) return fail("Generics", "Expected 1.1, got " + pq.peekFront());
            if (pq.remove() != 1.1) return fail("Generics", "Expected remove to return 1.1");

            return pass("Generic Type Support (Double)");
        } catch (Exception e) {
            return fail("Generics", "Threw exception: " + e.getMessage());
        }
    }

    private static boolean testToStringAndDisplay() {
        try {
            PriorityQueue<String> pq = new PriorityQueue<>(3);
            pq.insert("VIP", 1);
            pq.insert("Regular", 10);

            String str = pq.toString();
            if (str == null || str.trim().isEmpty()) {
                return fail("Formatting", "toString() returned null or empty");
            }

            System.out.println("  -> toString() output: " + str);
            System.out.print("  -> display() output : ");
            pq.display();

            return pass("toString and display methods");
        } catch (Exception e) {
            return fail("Formatting", "Threw exception: " + e.getMessage());
        }
    }

    // Helper methods
    private static boolean pass(String testName) {
        System.out.println("[PASS] " + testName);
        return true;
    }

    private static boolean fail(String testName, String reason) {
        System.out.println("[FAIL] " + testName + " - " + reason);
        return false;
    }
}