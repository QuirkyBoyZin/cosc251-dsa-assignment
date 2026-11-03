import datastructure.queue.PriorityQueue;
import datastructure.recursion.IterativeSolution;
import datastructure.recursion.RecursiveSolution;

public class Main {
    public static void main(String[] args) {
        // Triangular number

        var iterTri = IterativeSolution.tri(5);
        var recurTri = RecursiveSolution.tri(5);

        System.out.println(iterTri);
        System.out.println(recurTri);

    }
}
