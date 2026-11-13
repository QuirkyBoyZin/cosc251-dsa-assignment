package datastructure.graph;

import java.util.List;

public class DirectedGraph extends Graph {
    @Override
    public boolean isDirected() {
        return true;
    }

    @Override
    public void addEdge(int u, int v) {
        if (!hasVertex(u) || !hasVertex(v)) {
            throw new IllegalArgumentException("Both vertices must exist before adding an edge.");
        }
        if (hasEdge(u, v)) {
            throw new IllegalArgumentException("Edge between " + u + " and " + v + " already exists.");
        }
        adjList.get(u).add(v);
        numEdges++;
    }

    @Override
    public boolean removeEdge(int u, int v) {
        if (!hasEdge(u, v)) return false;

        adjList.get(u).remove(Integer.valueOf(v));
        numEdges--;
        return true;
    }

    @Override
    public boolean removeVertex(int u) {
        if (!hasVertex(u)) return false;

        numEdges -= adjList.remove(u).size();
        numVertices--;

        // Remove all incoming edges to this vertex
        for (List<Integer> neighbors : adjList.values()) {
            if (neighbors.remove(Integer.valueOf(u))) {
                numEdges--;
            }
        }

        return true;
    }
}