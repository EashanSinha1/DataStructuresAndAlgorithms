import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * Your implementation of various graph algorithms.
 *
 * @author Ritika Gehani
 * @version 1.0
 * @userid rgehani6
 * @GTID 903564719
 *
 *       Collaborators: LIST ALL COLLABORATORS YOU WORKED WITH HERE
 *
 *       Resources: LIST ALL NON-COURSE RESOURCES YOU CONSULTED HERE
 *       https://www.youtube.com/watch?v=FSm1zybd0Tk
 */
public class GraphAlgorithms2 {

    /**
     * Performs a depth first search (dfs) on the input graph, starting at the
     * parameterized starting vertex.
     * <p>
     * When exploring a vertex, explore in the order of neighbors returned by the
     * adjacency list. Failure to do so may cause you to lose points.
     * <p>
     * NOTE: You MUST implement this method recursively, or else you will lose all
     * points for this method.
     * <p>
     * You may import/use java.util.Set, java.util.List, and any classes that
     * implement the aforementioned interfaces, as long as they are efficient.
     * <p>
     * The only instance of java.util.Map that you may use is the adjacency list
     * from graph. DO NOT create new instances of Map for DFS (storing the adjacency
     * list in a variable is fine).
     * <p>
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * @param <T>   the generic typing of the data
     * @param start the vertex to begin the dfs on
     * @param graph the graph to search through
     * @return list of vertices in visited order
     * @throws IllegalArgumentException if any input is null, or if start doesn't
     *                                  exist in the graph
     */
    public static <T> List<Vertex<T>> dfs(Vertex<T> start, Graph<T> graph) {
        if (start == null) {
            throw new IllegalArgumentException("The start vertex is null.");
        }
        if (graph == null) {
            throw new IllegalArgumentException("The input is null");
        }
        if (!graph.getAdjList().containsKey(start)) {
            throw new IllegalArgumentException("The start vertex doesn't exist in the graph.");
        }
        List<Vertex<T>> listVisited = new ArrayList<>();
        Set<Vertex<T>> setVisited = new HashSet<>();
        dfsHelp(graph, start, listVisited, setVisited);
        return listVisited;
    }

    /**
     * This is the helper method for dfs method
     *
     * @param <T>         the generic typing of the data
     * @param start       the vertex to begin the dfs on
     * @param graph       the graph to search through
     * @param setVisited  This is the set of vertices that have been visited
     * @param listVisited This is the list of vertices that have been visited
     * @return This will return the list of vertices
     */
    public static <T> List<Vertex<T>> dfsHelp(Graph<T> graph, Vertex<T> start,
                                              List<Vertex<T>> listVisited, Set<Vertex<T>> setVisited) {
        setVisited.add(start);
        listVisited.add(start);
        for (VertexDistance<T> w : graph.getAdjList().get(start)) {
            if (!setVisited.contains(w.getVertex())) {
                dfsHelp(graph, w.getVertex(), listVisited, setVisited);
            }
        }
        return listVisited;
    }

    /**
     * Finds the single-source shortest distance between the start vertex and all
     * vertices given a weighted graph (you may assume non-negative edge weights).
     * <p>
     * Return a map of the shortest distances such that the key of each entry is a
     * node in the graph and the value for the key is the shortest distance to that
     * node from start, or Integer.MAX_VALUE (representing infinity) if no path
     * exists.
     * <p>
     * You may import/use java.util.PriorityQueue, java.util.Map, and java.util.Set
     * and any class that implements the aforementioned interfaces, as long as your
     * use of it is efficient as possible.
     * <p>
     * You should implement the version of Dijkstra's where you use two termination
     * conditions in conjunction.
     * <p>
     * 1) Check if all of the vertices have been visited. 2) Check if the PQ is
     * empty yet.
     * <p>
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * @param <T>   the generic typing of the data
     * @param start the vertex to begin the Dijkstra's on (source)
     * @param graph the graph we are applying Dijkstra's to
     * @return a map of the shortest distances from start to every other node in the
     * graph
     * @throws IllegalArgumentException if any input is null, or if start doesn't
     *                                  exist in the graph.
     */
    public static <T> Map<Vertex<T>, Integer> dijkstras(Vertex<T> start, Graph<T> graph) {
        if (start == null) {
            throw new IllegalArgumentException("The start is null.");
        }
        if (graph == null) {
            throw new IllegalArgumentException("The input is null");
        }
        if (!graph.getAdjList().containsKey(start)) {
            throw new IllegalArgumentException("The start vertex doesn't exist in the graph.");
        }
        Set<Vertex<T>> vs = new HashSet<>();
        PriorityQueue<VertexDistance<T>> pq = new PriorityQueue<>();
        Map<Vertex<T>, Integer> dm = new HashMap<>();
        for (Vertex<T> v : graph.getVertices()) {
            if (v.equals(start)) {
                dm.put(v, 0);
            } else {
                dm.put(v, Integer.MAX_VALUE);
            }
        }
        pq.add(new VertexDistance<>(start, 0));
        while (graph.getVertices().size() != vs.size() && !(pq.isEmpty())) {
            VertexDistance<T> dequeue = pq.remove();
            if (!(vs.contains(dequeue.getVertex()))) {
                vs.add(dequeue.getVertex());
                dm.replace(dequeue.getVertex(), dequeue.getDistance());
                for (VertexDistance<T> edges : graph.getAdjList().get(dequeue.getVertex())) {
                    Vertex<T> enqueue = edges.getVertex();
                    pq.add(new VertexDistance<>(enqueue, dequeue.getDistance() + edges.getDistance()));
                }
            }
        }
        return dm;
    }

    /**
     * Runs Prim's algorithm on the given graph and returns the Minimum Spanning
     * Tree (MST) in the form of a set of Edges. If the graph is disconnected and
     * therefore no valid MST exists, return null.
     * <p>
     * You may assume that the passed in graph is undirected. In this framework,
     * this means that if (u, v, 3) is in the graph, then the opposite edge (v, u,
     * 3) will also be in the graph, though as a separate Edge object.
     * <p>
     * The returned set of edges should form an undirected graph. This means that
     * every time you add an edge to your return set, you should add the reverse
     * edge to the set as well. This is for testing purposes. This reverse edge does
     * not need to be the one from the graph itself; you can just make a new edge
     * object representing the reverse edge.
     * <p>
     * You may assume that there will only be one valid MST that can be formed.
     * <p>
     * You should NOT allow self-loops or parallel edges in the MST.
     * <p>
     * You may import/use java.util.PriorityQueue, java.util.Set, and any class that
     * implements the aforementioned interface.
     * <p>
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     * <p>
     * The only instance of java.util.Map that you may use is the adjacency list
     * from graph. DO NOT create new instances of Map for this method (storing the
     * adjacency list in a variable is fine).
     *
     * @param <T>   the generic typing of the data
     * @param start the vertex to begin Prims on
     * @param graph the graph we are applying Prims to
     * @return the MST of the graph or null if there is no valid MST
     * @throws IllegalArgumentException if any input is null, or if start doesn't
     *                                  exist in the graph
     */
    public static <T> Set<Edge<T>> prims(Vertex<T> start, Graph<T> graph) {
        if (start == null) {
            throw new IllegalArgumentException("The start doesn't exist in the graph");
        }
        if (graph == null) {
            throw new IllegalArgumentException("The input is null");
        }
        if (!graph.getVertices().contains(start)) {
            throw new IllegalArgumentException("The start vertex doesn't exist in the graph.");
        }
        Set<Vertex<T>> vs = new HashSet<>();
        Set<Edge<T>> mst = new HashSet<>();
        PriorityQueue<Edge<T>> pq = new PriorityQueue<>();
        for (VertexDistance<T> edge : graph.getAdjList().get(start)) {
            int edgeDistance = edge.getDistance();
            pq.add(new Edge<>(start, edge.getVertex(), edgeDistance));
            vs.add(start);
        }
        while (graph.getVertices().size() != vs.size() && !(pq.isEmpty())) {
            Edge<T> dequeue = pq.remove();
            Vertex<T> lastVertex = dequeue.getV();
            if (!vs.contains(lastVertex)) {
                vs.add(dequeue.getV());
                mst.add(new Edge<>(lastVertex, dequeue.getU(), dequeue.getWeight()));
                mst.add(dequeue);
                for (VertexDistance<T> edges : graph.getAdjList().get(lastVertex)) {
                    int edgeDistance = edges.getDistance();
                    Vertex<T> edgeVertex = edges.getVertex();
                    pq.add(new Edge<>(lastVertex, edgeVertex, edgeDistance));
                }
            }
        }
        Set<Vertex<T>> graphVertex = graph.getVertices();
        int undirectedEdges = graphVertex.size() - 1;
        if (mst.size() < 2 * (undirectedEdges)) {
            return null;
        }
        return mst;
    }
}