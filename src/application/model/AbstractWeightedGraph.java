package application.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javafx.util.Pair;
import java.util.Map;
import java.util.Map.Entry;

public abstract class AbstractWeightedGraph<T> {
	private final HashMap<Vertex<T>,Set<Pair<Vertex<T>,Integer>>> adjacencyList;
	
	public AbstractWeightedGraph() {
		this.adjacencyList = new HashMap<Vertex<T>,Set<Pair<Vertex<T>,Integer>>>();
	}
	
	protected boolean containsVertex(T element) {
		for (Map.Entry<Vertex<T>,Set<Pair<Vertex<T>,Integer>>> entry : adjacencyList.entrySet()) {
			if(entry.getKey().getElement() == element) {
				return true;
			}
		}
		return false;
	}
	
	protected Vertex<T> returnVertex(T element) {
		for (Map.Entry<Vertex<T>,Set<Pair<Vertex<T>,Integer>>> entry : adjacencyList.entrySet()) {
			if(entry.getKey().getElement() == element) {
				return entry.getKey();
			}
		}
		
		return null;
	}
	
	protected Pair<Vertex<T>,Integer> returnPairInSet(Set<Pair<Vertex<T>, Integer>> set, Vertex<T> v ){
		for(Pair<Vertex<T>,Integer> p : set) {
			if(p.getKey().equals(v)) {
				return p;
			}
		}
		return null;
	}
	
	/**
     * Add new vertex to the graph.
     * 
     * @param v The vertex object. 
     */
	
	public abstract void addVertex();
	
//	public void addVertex(T v) {
//        if (containsVertex(v)) {
//            throw new IllegalArgumentException("Vertex already exists.");
//        }
//        this.adjacencyList.put(new Vertex<T>(v), new HashSet<Pair<Vertex<T>,Integer>>());
//    }
	
	/**
     * Remove the vertex v from the graph.
     * 
     * @param v The vertex that will be removed.
     */
	
	public abstract void removeVertex();
	
//    public void removeVertex(T v) {
//        if (!containsVertex(v)) {
//            throw new IllegalArgumentException("Vertex doesn't exist.");
//        }
//        
//        for (Vertex<T> u: this.getAllVertices()) {
//            this.adjacencyList.get(u).remove(returnPairInSet(this.adjacencyList.get(u),returnVertex(v)));
//        }
// 
//        this.adjacencyList.remove(returnVertex(v)); 
//    }
    
    /**
     * Add new edge between vertex. Adding new edge from u to v will
     * automatically add new edge from v to u since the graph is undirected.
     * 
     * @param v Start vertex.
     * @param u Destination vertex.
     * @param w Weight of edge
     */
	
	public abstract void addEdge();
	
//    public void addEdge(T v, T u, int w) {
//        if (!containsVertex(v) || !containsVertex(u)) {
//            throw new IllegalArgumentException();
//        }
//        
//        this.adjacencyList.get(returnVertex(v)).add(new Pair<Vertex<T>,Integer>(returnVertex(u),w));
//    }
    
    /**
     * Remove the edge between vertex. Removing the edge from u to v will 
     * automatically remove the edge from v to u since the graph is undirected.
     * 
     * @param v Start vertex.
     * @param u Destination vertex.
     */
	
	public abstract void removeEdge();
	
//    public void removeEdge(T v, T u) {
//        if (!containsVertex(v) || !containsVertex(u)) {
//            throw new IllegalArgumentException();
//        }
//        
//        this.adjacencyList.get(returnVertex(v)).remove(returnPairInSet(this.adjacencyList.get(returnVertex(v)),
//        													returnVertex(u)));
//    }
    
    /**
     * Check adjacency between 2 vertices in the graph.
     * 
     * @param v Start vertex.
     * @param u Destination vertex.
     * @return <tt>true</tt> if the vertex v and u are connected.
     */
    public boolean isAdjacent(T v, T u) {
        return this.adjacencyList.get(returnVertex(v)).contains(returnPairInSet(this.adjacencyList.get(
        															returnVertex(v)),returnVertex(u)));
    }
    
    /**
     * Get connected vertices of a vertex.
     * 
     * @param v The vertex.
     * @return An iterable for connected vertices.
     */
    public Iterable<Pair<Vertex<T>,Integer>> getNeighbors(T v) {
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
    
    public HashMap<Vertex<T>,Set<Pair<Vertex<T>,Integer>>> getAdjacencyList(){
		return adjacencyList;
}
}
