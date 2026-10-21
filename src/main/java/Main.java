import datastructure.queue.PriorityQueue;

public class Main {
    public static void main(String[] args) {
        PriorityQueue<String> queue = new PriorityQueue<>(1);
//        Queue<String> queue = new Queue<>(1);


        String[] item = new String[]{"A", "B", "C", "D", "E", "T", "F", "W"};
        int[]    priorityValue = new int[]{3,4,3,2,1,10,9,11};

        for (int i = 0; i < item.length; i++) {
            queue.insert(item[i], priorityValue[i]);
        }

//        // E, D, C, A, B, F
//        for (int i = 0; i < item.length + 100; i++) {
//            queue.peekRear();
//            queue.peekFront();
//            queue.remove();
//            queue.peekFront();
//            for (int j = 0; j < item.length; j++) {
//                queue.insert(item[j], priorityValue[j]);
//            }
//        }
        queue.display();
        System.out.println(queue);



//        queue.insert("Task3",3);
//        queue.insert("Task2",2);
//        queue.insert("Task1",1);
//        queue.insert("Task4",4);
//
//        System.out.println(queue.peekRear());
//
//        queue.remove();
//        System.out.println(queue.peekRear());
//        queue.insert("Task 5", 5);
//        queue.remove();
//
//        System.out.println(queue.peekRear());
//
//
//
//
//        System.out.println(queue);











    }
}
