package datastructure.graph;

import java.util.*;

public class UndirectedGraph<V> extends Graph<V> {

    @Override
    public boolean isDirected() {
        return false;
    }

    @Override
    public void addEdge(V u, V v) {
        if (!hasVertex(u) || !hasVertex(v)) {
            throw new IllegalArgumentException("Both vertices must exist before adding an edge.");
        }
        if (u.equals(v)) {
            throw new IllegalArgumentException("Self-loops are not allowed in a simple undirected graph.");
        }
        if (hasEdge(u, v)) {
            throw new IllegalArgumentException("Edge between " + u + " and " + v + " already exists.");
        }

        // Undirected edge goes both ways
        adjList.get(u).add(v);
        adjList.get(v).add(u);
        numEdges++;
    }

    @Override
    public boolean removeEdge(V u, V v) {
        if (!hasEdge(u, v)) return false;

        adjList.get(u).remove(v);
        adjList.get(v).remove(u);
        numEdges--;
        return true;
    }

    @Override
    public boolean removeVertex(V u) {
        if (!hasVertex(u)) return false;

        List<V> neighbors = adjList.remove(u);
        numEdges -= neighbors.size();

        for (V neighbor : neighbors) {
            adjList.get(neighbor).remove(u);
        }

        numVertices--;
        return true;
    }

    @Override
    public boolean isConnected() {
        if (numVertices <= 1) {
            return true;
        }

        Set<V> visited = new HashSet<>();
        Deque<V> queue = new ArrayDeque<>();
        V start = getVertices().getFirst();

        visited.add(start);
        queue.offer(start);

        while (!queue.isEmpty()) {
            V current = queue.poll();
            for (V neighbor : getNeighbors(current)) {
                if (visited.add(neighbor)) {
                    queue.offer(neighbor);
                }
            }
        }

        return visited.size() == numVertices;
    }
}