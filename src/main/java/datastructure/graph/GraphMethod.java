package datastructure.graph;

public class GraphMethod {
//    public static boolean isConnected() {
//
//    }



    public static void main(String[] args) {
        DirectedGraph graph = new DirectedGraph();
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 3);

        System.out.println("Graph structure:");
        graph.printGraph();

        System.out.println("Removing edge (1, 3): " + graph.removeEdge(1, 3));
        System.out.println("Removing vertex 2: " + graph.removeVertex(2));

        System.out.println("Updated graph structure:");
        graph.printGraph();
    }
}
