import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.HashSet;
import java.util.Set;
import java.util.Map;
import java.util.Queue;
import java.util.PriorityQueue;


/**
 * Your implementation of various graph algorithms.
 *
 * @author Eashan Sinha
 * @version 1.0
 * @userid esinha6
 * @GTID 903598987
 *
 * Collaborators: LIST ALL COLLABORATORS YOU WORKED WITH HERE
 *
 * Resources: YouTube-- basic videos on dijkstra's and prims' algorithm
 */
public class GraphAlgorithms {

    /**
     * Performs a depth first search (dfs) on the input graph, starting at
     * the parameterized starting vertex.
     *
     * When exploring a vertex, explore in the order of neighbors returned by
     * the adjacency list. Failure to do so may cause you to lose points.
     *
     * NOTE: You MUST implement this method recursively, or else you will lose
     * all points for this method.
     *
     * You may import/use java.util.Set, java.util.List, and
     * any classes that implement the aforementioned interfaces, as long as they
     * are efficient.
     *
     * The only instance of java.util.Map that you may use is the
     * adjacency list from graph. DO NOT create new instances of Map
     * for DFS (storing the adjacency list in a variable is fine).
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * @param <T>   the generic typing of the data
     * @param start the vertex to begin the dfs on
     * @param graph the graph to search through
     * @return list of vertices in visited order
     * @throws IllegalArgumentException if any input is null, or if start
     *                                  doesn't exist in the graph
     */
    public static <T> List<Vertex<T>> dfs(Vertex<T> start, Graph<T> graph) {
        if (start == null || graph == null) {
            throw new IllegalArgumentException("The start vertex or graph is null");
        }
        if (!graph.getAdjList().containsKey(start)) {
            throw new IllegalArgumentException("The start vertex is not in the graph");
        }
        // Initialize VisitedSet
        Set<Vertex<T>> visitedSet = new HashSet<>();
        List<Vertex<T>> resultList = new ArrayList<>();
        dfsHelp(start, graph, visitedSet, resultList);
        return resultList;
    }

    /**
     * This method is a helper for the
     *
     * @param vCurrent which represents the current vertex
     * @param graph which is the graph we pass in so that we can get its adjacent list
     * @param visitedSet which represents the visited set
     * @param resultList which represents the return/result order of the vertex
     * @param <T> which represents the generic typing of the data
     */
    private static <T> void dfsHelp(Vertex<T> vCurrent, Graph<T> graph,
                                    Set<Vertex<T>> visitedSet, List<Vertex<T>> resultList) {
        if (!visitedSet.contains(vCurrent)) {
            Map<Vertex<T>, List<VertexDistance<T>>> vertexMap = graph.getAdjList();
            resultList.add(vCurrent);
            visitedSet.add(vCurrent);
            List<VertexDistance<T>> adjList = vertexMap.get(vCurrent);
            for (VertexDistance<T> vertexDist : adjList) {
                if (!visitedSet.contains(vertexDist)) {
                    dfsHelp(vertexDist.getVertex(), graph, visitedSet, resultList);
                }
            }
        }
    }

    /**
     * Finds the single-source shortest distance between the start vertex and
     * all vertices given a weighted graph (you may assume non-negative edge
     * weights).
     *
     * Return a map of the shortest distances such that the key of each entry
     * is a node in the graph and the value for the key is the shortest distance
     * to that node from start, or Integer.MAX_VALUE (representing
     * infinity) if no path exists.
     *
     * You may import/use java.util.PriorityQueue,
     * java.util.Map, and java.util.Set and any class that
     * implements the aforementioned interfaces, as long as your use of it
     * is efficient as possible.
     *
     * You should implement the version of Dijkstra's where you use two
     * termination conditions in conjunction.
     *
     * 1) Check if all of the vertices have been visited.
     * 2) Check if the PQ is empty yet.
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * @param <T>   the generic typing of the data
     * @param start the vertex to begin the Dijkstra's on (source)
     * @param graph the graph we are applying Dijkstra's to
     * @return a map of the shortest distances from start to every
     * other node in the graph
     * @throws IllegalArgumentException if any input is null, or if start
     *                                  doesn't exist in the graph.
     */
    public static <T> Map<Vertex<T>, Integer> dijkstras(Vertex<T> start, Graph<T> graph) {
        if (start == null || graph == null) {
            throw new IllegalArgumentException("The start vertex or graph is null");
        }
        if (!graph.getAdjList().containsKey(start)) {
            throw new IllegalArgumentException("The starting vertex is not in the graph");
        }
        Queue<VertexDistance<T>> priorityQueue = new PriorityQueue<>();
        Map<Vertex<T>, Integer> distanceMap = new HashMap<>();
        for (Vertex<T> vertex : graph.getAdjList().keySet()) {
            if (vertex.equals(start)) {
                distanceMap.put(vertex, 0);
            } else {
                distanceMap.put(vertex, Integer.MAX_VALUE);
            }
        }
        priorityQueue.add(new VertexDistance<>(start, 0));
        while (!priorityQueue.isEmpty()) {
            VertexDistance<T> dist = priorityQueue.remove();
            for (VertexDistance<T> vertexDistance : graph.getAdjList().get(dist.getVertex())) {
                int vDistance = dist.getDistance() + vertexDistance.getDistance();
                if (distanceMap.get(vertexDistance.getVertex()) > vDistance) {
                    distanceMap.put(vertexDistance.getVertex(), vDistance);
                    priorityQueue.add(new VertexDistance<>(vertexDistance.getVertex(), vDistance));
                }
            }
        }
        return distanceMap;
    }

    /**
     * Runs Prim's algorithm on the given graph and returns the Minimum
     * Spanning Tree (MST) in the form of a set of Edges. If the graph is
     * disconnected and therefore no valid MST exists, return null.
     *
     * You may assume that the passed in graph is undirected. In this framework,
     * this means that if (u, v, 3) is in the graph, then the opposite edge
     * (v, u, 3) will also be in the graph, though as a separate Edge object.
     *
     * The returned set of edges should form an undirected graph. This means
     * that every time you add an edge to your return set, you should add the
     * reverse edge to the set as well. This is for testing purposes. This
     * reverse edge does not need to be the one from the graph itself; you can
     * just make a new edge object representing the reverse edge.
     *
     * You may assume that there will only be one valid MST that can be formed.
     *
     * You should NOT allow self-loops or parallel edges in the MST.
     *
     * You may import/use java.util.PriorityQueue, java.util.Set, and any
     * class that implements the aforementioned interface.
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * The only instance of java.util.Map that you may use is the adjacency
     * list from graph. DO NOT create new instances of Map for this method
     * (storing the adjacency list in a variable is fine).
     *
     * @param <T>   the generic typing of the data
     * @param start the vertex to begin Prims on
     * @param graph the graph we are applying Prims to
     * @return the MST of the graph or null if there is no valid MST
     * @throws IllegalArgumentException if any input is null, or if start
     *                                  doesn't exist in the graph
     */
    public static <T> Set<Edge<T>> prims(Vertex<T> start, Graph<T> graph) {
        if (start == null || (graph == null)) {
            throw new IllegalArgumentException("The start vertex or graph is null");
        }
        if (!graph.getVertices().contains(start)) {
            throw new IllegalArgumentException("The start vertex is not in the graph");
        }
        Set<Vertex<T>> visitedSet = new HashSet<>();
        Set<Edge<T>> newSet = new HashSet<>();
        Queue<Edge<T>> priorityQueue = new PriorityQueue<>();
        Map<Vertex<T>, List<VertexDistance<T>>> vertexMap = graph.getAdjList();
        for (VertexDistance<T> vertex: vertexMap.get(start)) {
            priorityQueue.add(new Edge<>(start, vertex.getVertex(), vertex.getDistance()));
        }
        visitedSet.add(start);
        while (!priorityQueue.isEmpty() && !(visitedSet.size() == graph.getVertices().size())) {
            Edge<T> edge = priorityQueue.remove();
            if (!visitedSet.contains(edge.getV())) {
                visitedSet.add(edge.getV());
                newSet.add(new Edge<>(edge.getU(), edge.getV(), edge.getWeight()));
                newSet.add(new Edge<>(edge.getV(), edge.getU(), edge.getWeight()));
                for (VertexDistance<T> vertex: vertexMap.get(edge.getV())) {
                    if (!visitedSet.contains(vertex.getVertex())) {
                        priorityQueue.add(new Edge<>(edge.getV(), vertex.getVertex(), vertex.getDistance()));
                    }
                }
            }
        }
        if ((newSet.size() / 2) == graph.getVertices().size() - 1) {
            return newSet;
        } else {
            return null;
        }
    }
}