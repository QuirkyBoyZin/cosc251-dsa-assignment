import datastructure.graph.UndirectedGraph;


public class Main {
    public static void main(String[] args) {

//        UndirectedGraph graph = new UndirectedGraph(6);
//
//        for (int i = 0; i < 5; i++) {
//            graph.addVertex(i);
//        }
//
//        graph.addEdge(0, 1);
//        graph.addEdge(0, 4);
//        graph.addEdge(0, 2);
//
//        graph.addEdge(1, 2);
//        graph.addEdge(1, 3);
//        graph.addEdge(1, 4);
//
//        graph.addEdge(3, 2);
//        graph.addEdge(3, 4);
//
//
//
//        graph.printGraph();
//
//
//        System.out.println(graph.isConnectedDFS());



        UndirectedGraph graph2 = new UndirectedGraph();

        for (int i = 0; i <= 3; i++) {
            graph2.addVertex(i);
        }

        graph2.addEdge(0, 1);
        graph2.addEdge(1, 2);
        graph2.addEdge(2, 3);

        System.out.println("Is the graph connected? " + graph2.isConnectedDFS());
    }


}
