package datastructure.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DirectedGraph {
    private int numVertices;
    private int numEdges;
    private final Map<Integer, List<Integer>> adjList;

    public DirectedGraph() {
        adjList = new HashMap<>();
    }

    // O(1) check if a node/vertex exists
    public boolean hasVertex(int u) {
        return adjList.containsKey(u);
    }

    // O(degree(u)) check in Adjacency List
    public boolean hasEdge(int u, int v) {
        if (!hasVertex(u) || !hasVertex(v)) return false;
        return adjList.get(u).contains(v);
    }

    public void addVertex(int u) {
        if (hasVertex(u)) {
            throw new IllegalArgumentException("Vertex " + u + " already exists in the graph.");
        }
        adjList.put(u, new ArrayList<>());
        numVertices++;
    }

    public boolean removeVertex(int u) {
        if (!hasVertex(u)) return false;

        numEdges -= adjList.remove(u).size();
        numVertices--;

        // Remove all incoming edges to this vertex
        for (List<Integer> neighbors : adjList.values()) {
            if (neighbors.remove(Integer.valueOf(u))) numEdges--;
        }

        return true;
    }

    public boolean removeEdge(int u, int v) {
        if (!hasEdge(u, v)) return false;

        adjList.get(u).remove(Integer.valueOf(v));
        numEdges--;
        return true;
    }


    public void addEdge(int u, int v) {
        if (!hasVertex(u) || !hasVertex(v)) {
            throw new IllegalArgumentException("Both vertices must exist before adding an edge.");
        }
        if (hasEdge(u, v)) {
            throw new IllegalArgumentException("Edge from " + u + " to " + v + " already exists.");
        }
        adjList.get(u).add(v);
        numEdges++;
    }

    public int getNumVertices() {
        return numVertices;
    }

    public int getNumEdges() {
        return numEdges;
    }

    public int getDegree(int u) {
        if (!hasVertex(u)) {
            throw new IllegalArgumentException("Vertex " + u + " does not exist in the graph.");
        }
        return adjList.get(u).size();
    }

    public List<Integer> getNeighbors(int u) {
        if (!hasVertex(u)) {
            throw new IllegalArgumentException("Vertex " + u + " does not exist in the graph.");
        }
        return adjList.get(u);
    }

    public void printGraph() {
        for (Map.Entry<Integer, List<Integer>> entry : adjList.entrySet()) {
            System.out.print("Vertex " + entry.getKey() + ": ");
            for (Integer neighbor : entry.getValue()) {
                System.out.print(neighbor + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        DirectedGraph graph = new DirectedGraph();

        graph.addVertex(0);
        graph.addVertex(1);
        graph.addVertex(2);

        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 2);

        graph.printGraph();
    }
}
