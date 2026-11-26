package datastructure.graph;

public class Main {
    public static void main(String[] args) {
        UndirectedGraph graph = new UndirectedGraph();

        // Adding vertices
        for (int i = 0; i <= 7; i++) {
            graph.addVertex(i);
        }

        // Adding edges
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(0, 4);

        graph.addEdge(1, 5);
        graph.addEdge(5, 6);
        graph.addEdge(6, 7);
        graph.addEdge(7, 3);

        graph.addEdge(4, 3);
        graph.addEdge(4, 5);

        graph.addEdge(2, 3);

        graph.addEdge(4, 7);


        // Print the graph
        graph.printGraph();

        // Check if the graph is connected
        System.out.println("Is the graph connected? " + HCPSolver.isConnectedDFS(graph));

        // Check for Hamiltonian cycle
        System.out.println("Does the graph have a Hamiltonian cycle? " + HCPSolver.hasHamiltonianCycle(graph));
    }
}
