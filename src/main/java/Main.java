import datastructure.linkedlist.singlylink.LinkedList;
import datastructure.queue.PriorityQueue;

public class Main {
    public static void main(String[] args) {
        var ll = new LinkedList<String>();


        // ============ InsertFirst ===================
        ll.insertFirst("John");
        ll.insertFirst("Jack");
        ll.insertFirst("Jones");
        ll.insertFirst("Malcolm");
        // Output: Head  -> Malcolm -> Jones -> Jack -> John
        System.out.println(ll);
        ll.display();

        // ============ InsertLast ===================
        ll.insertLast("June");
        ll.insertLast("Jasmine");

        ll.display();
        // Output: Head  -> Malcolm -> Jones -> Jack -> John -> June -> Jasmine

        // ============ InsertAt ===================
        ll.insertAt(0, "Cat");
        ll.insertAt(6, "Cat");
        ll.insertAt(4, "Cat");


        ll.display();
        // Output: Head  -> Cat -> Malcolm -> Jones -> Jack -> Cat -> John -> June -> Jasmine -> Cat


        // ============ deleteFirst ===================
        ll.deleteFirst();
        ll.display();
        System.out.println(ll); // Head: Malcolm, Tail: Cat
        // Output: Head  -> Malcolm -> Jones -> Jack -> John -> June -> Jasmine -> Cat


        // ============ deleteFirst ===================

        ll.deleteLast();
        ll.display();
        System.out.println(ll); // Head: Malcolm, Tail: Jasmine
        // Output: Head  -> Malcolm -> Jones -> Jack -> John -> June -> Jasmine

        // ============ deleteAt ===================

        ll.deleteAt(0); // First index
        ll.deleteAt(5); // Last Index
        ll.deleteAt(2); // In between first and last index

        ll.display();
        System.out.println(ll); // Head: Jones, Tail: June
        // Output: Head  -> Jones -> Jack -> John -> June


        // ============ Find ===================
        System.out.println("\n============ Find ===================\n");
        System.out.println(ll.find("Jones"));  // 0
        System.out.println(ll.find("Jack"));   // 1
        System.out.println(ll.find("John"));   // 2
        System.out.println(ll.find("June"));   // 3

        System.out.println(ll.find("Cat"));   // -1

        // ============ deleteKey ===================
        System.out.println("\n============ deleteKey ===================\n");
        ll.deleteKey("Jones");
        ll.deleteKey("Jack");
        ll.deleteKey("John");
        ll.deleteKey("June");

        System.out.println(ll.deleteKey("Cat"));   // false

        ll.display(); // Head ->
        System.out.println(ll);  // Head: null, tail: null














    }
}
