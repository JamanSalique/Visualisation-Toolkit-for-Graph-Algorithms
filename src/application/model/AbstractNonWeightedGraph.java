package application.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Map;
import java.util.Map.Entry;


public abstract class AbstractNonWeightedGraph<T> {
	private final HashMap<Vertex<T>,Set<Vertex<T>>> adjacencyList;
	//private final HashMap<Vertex<T>,Pair<Set<Vertex<T>>,Integer>> weightedGraphAdjacencyList;
	
	public AbstractNonWeightedGraph() {
        this.adjacencyList = new HashMap<Vertex<T>,Set<Vertex<T>>>();
    }
	
	public boolean containsVertex(T element) {
		for (Map.Entry<Vertex<T>,Set<Vertex<T>>> entry : adjacencyList.entrySet()) {
			if(entry.getKey().getElement() == element) {
				return true;
			}
		}
		return false;
	}
	
	public Vertex<T> returnVertex(T element) {
		for (Map.Entry<Vertex<T>,Set<Vertex<T>>> entry : adjacencyList.entrySet()) {
			if(entry.getKey().getElement() == element) {
				return entry.getKey();
			}
		}
		
		return null;
	}
	
	 /**
     * Add new vertex to the graph.
     * 
     * @param v The vertex object. 
     */
	public void addVertex(T v) {
        if (containsVertex(v)) {
            throw new IllegalArgumentException("Vertex already exists.");
        }
        this.adjacencyList.put(new Vertex<T>(v), new HashSet<Vertex<T>>());
    }
    
    /**
     * Remove the vertex v from the graph.
     * 
     * @param v The vertex that will be removed.
     */
    public void removeVertex(T v) {
        if (!containsVertex(v)) {
            throw new IllegalArgumentException("Vertex doesn't exist.");
        }
        
        this.adjacencyList.remove(returnVertex(v));
        
        for (Vertex<T> u: this.getAllVertices()) {
            this.adjacencyList.get(u).remove(returnVertex(v));
        }
    }
    
    /**
     * Add new edge between vertex. Adding new edge from u to v will
     * automatically add new edge from v to u since the graph is undirected.
     * 
     * @param v Start vertex.
     * @param u Destination vertex.
     */
    public void addEdge(T v, T u) {
        if (!containsVertex(v) || !containsVertex(u)) {
            throw new IllegalArgumentException();
        }
        
        this.adjacencyList.get(returnVertex(v)).add(returnVertex(u));
        this.adjacencyList.get(returnVertex(u)).add(returnVertex(v));
    }
    
    /**
     * Remove the edge between vertex. Removing the edge from u to v will 
     * automatically remove the edge from v to u since the graph is undirected.
     * 
     * @param v Start vertex.
     * @param u Destination vertex.
     */
    public void removeEdge(T v, T u) {
        if (!containsVertex(v) || !containsVertex(u)) {
            throw new IllegalArgumentException();
        }
        
        this.adjacencyList.get(returnVertex(v)).remove(returnVertex(u));
        this.adjacencyList.get(returnVertex(u)).remove(returnVertex(v));
    }
    
    /**
     * Check adjacency between 2 vertices in the graph.
     * 
     * @param v Start vertex.
     * @param u Destination vertex.
     * @return <tt>true</tt> if the vertex v and u are connected.
     */
    public boolean isAdjacent(T v, T u) {
        return this.adjacencyList.get(returnVertex(v)).contains(returnVertex(u));
    }
    
    /**
     * Get connected vertices of a vertex.
     * 
     * @param v The vertex.
     * @return An iterable for connected vertices.
     */
    public Iterable<Vertex<T>> getNeighbors(T v) {
        return this.adjacencyList.get(returnVertex(v));
    }
    
    /**
     * Get all vertices in the graph.
     * 
     * @return An Iterable for all vertices in the graph.
     */
    public Iterable<Vertex<T>> getAllVertices() {
        return this.adjacencyList.keySet();
    }
	

}
