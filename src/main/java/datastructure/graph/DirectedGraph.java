package datastructure.graph;

import java.util.*;

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
            throw new IllegalArgumentException("Edge from " + u + " to " + v + " already exists.");
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

    public int getOutDegree(V u) {
        if (!hasVertex(u)) {
            throw new IllegalArgumentException("Vertex " + u + " does not exist in the graph.");
        }
        return adjList.get(u).size();
    }

    public int getInDegree(V u) {
        if (!hasVertex(u)) {
            throw new IllegalArgumentException("Vertex " + u + " does not exist in the graph.");
        }
        int inDegree = 0;
        for (List<V> neighbors : adjList.values()) {
            if (neighbors.contains(u)) {
                inDegree++;
            }
        }
        return inDegree;
    }

    @Override
    public int getDegree(V u) {
        return getInDegree(u) + getOutDegree(u);
    }

    public boolean isWeaklyConnected() {
        if (numVertices <= 1) {
            return true;
        }

        Map<V, Set<V>> undirectedAdj = new HashMap<>();
        for (V v : adjList.keySet()) {
            undirectedAdj.put(v, new HashSet<>());
        }
        for (Map.Entry<V, List<V>> entry : adjList.entrySet()) {
            V u = entry.getKey();
            for (V v : entry.getValue()) {
                undirectedAdj.get(u).add(v);
                undirectedAdj.get(v).add(u);
            }
        }

        Set<V> visited = new HashSet<>();
        Deque<V> queue = new ArrayDeque<>();
        V start = getVertices().get(0);

        visited.add(start);
        queue.offer(start);

        while (!queue.isEmpty()) {
            V current = queue.poll();
            for (V neighbor : undirectedAdj.get(current)) {
                if (visited.add(neighbor)) {
                    queue.offer(neighbor);
                }
            }
        }

        return visited.size() == numVertices;
    }

    @Override
    public boolean isConnected() {
        return isStronglyConnected();
    }

    public boolean isStronglyConnected() {
        if (numVertices <= 1) {
            return true;
        }

        V start = getVertices().getFirst();

        // 1. Check if all vertices are reachable from 'start'
        if (bfsVisitCount(start, adjList) != numVertices) {
            return false;
        }

        // 2. Build reversed (transposed) graph and check reachability from 'start'
        Map<V, List<V>> transposed = new HashMap<>();
        for (V v : adjList.keySet()) {
            transposed.put(v, new ArrayList<>());
        }
        for (Map.Entry<V, List<V>> entry : adjList.entrySet()) {
            V u = entry.getKey();
            for (V v : entry.getValue()) {
                transposed.get(v).add(u);
            }
        }

        return bfsVisitCount(start, transposed) == numVertices;
    }

    private int bfsVisitCount(V start, Map<V, List<V>> graphMap) {
        Set<V> visited = new HashSet<>();
        Deque<V> queue = new ArrayDeque<>();
        visited.add(start);
        queue.offer(start);

        while (!queue.isEmpty()) {
            V current = queue.poll();
            for (V neighbor : graphMap.get(current)) {
                if (visited.add(neighbor)) {
                    queue.offer(neighbor);
                }
            }
        }
        return visited.size();
    }
}