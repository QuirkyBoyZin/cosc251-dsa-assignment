package datastructure.graph;

import java.util.List;

public class DirectedGraph<V> extends Graph<V> {
    @Override
    public boolean isDirected() {
        return true;
    }

    @Override
    public void addEdge(V u, V v) {
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
    public boolean removeEdge(V u, V v) {
        if (!hasEdge(u, v)) return false;

        adjList.get(u).remove(v);
        numEdges--;
        return true;
    }

    @Override
    public boolean removeVertex(V u) {
        if (!hasVertex(u)) return false;

        numEdges -= adjList.remove(u).size();
        numVertices--;

        // Remove all incoming edges to this vertex
        for (List<V> neighbors : adjList.values()) {
            if (neighbors.remove(u)) {
                numEdges--;
            }
        }

        return true;
    }
}