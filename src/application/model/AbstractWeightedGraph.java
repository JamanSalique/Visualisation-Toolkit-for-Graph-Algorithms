package application.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.util.Pair;

public abstract class AbstractWeightedGraph<T> {
	private final ObjectProperty<HashMap<Vertex<T>,Set<Pair<Vertex<T>,Integer>>>> adjacencyList;
	
	public AbstractWeightedGraph() {
		this.adjacencyList = new SimpleObjectProperty<HashMap<Vertex<T>,Set<Pair<Vertex<T>,Integer>>>>(
				new HashMap<Vertex<T>,Set<Pair<Vertex<T>,Integer>>>());
	}
	
	public boolean containsVertex(T element) {
		for (Map.Entry<Vertex<T>,Set<Pair<Vertex<T>,Integer>>> entry : adjacencyList.get().entrySet()) {
			if(entry.getKey().getElement().equals(element)) {
				return true;
			}
		}
		return false;
	}
	
	public Vertex<T> returnVertex(T element) {
		for (Map.Entry<Vertex<T>,Set<Pair<Vertex<T>,Integer>>> entry : adjacencyList.get().entrySet()) {
			if(entry.getKey().getElement().equals(element)) {
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
	
	public void clearGraph() {
		adjacencyList.get().clear();
	}
	
	/**
     * Add new vertex to the graph.
     * 
     * @param v The vertex object. 
     */
	
	public abstract void addVertex(T v);
	
	/**
     * Remove the vertex v from the graph.
     * 
     * @param v The vertex that will be removed.
     */
	
	public abstract void removeVertex(T v);
	
    
    /**
     * Add new edge between vertex. Adding new edge from u to v will
     * automatically add new edge from v to u since the graph is undirected.
     * 
     * @param v Start vertex.
     * @param u Destination vertex.
     * @param w Weight of edge
     */
	
	public abstract void addEdge(T v, T u, int w);
    
    /**
     * Remove the edge between vertex. Removing the edge from u to v will 
     * automatically remove the edge from v to u since the graph is undirected.
     * 
     * @param v Start vertex.
     * @param u Destination vertex.
     */
	
	public abstract void removeEdge(T v, T u);
    
    /**
     * Check adjacency between 2 vertices in the graph.
     * 
     * @param v Start vertex.
     * @param u Destination vertex.
     * @return <tt>true</tt> if the vertex v and u are connected.
     */
    public boolean isAdjacent(T v, T u) {
        return this.adjacencyList.get().get(returnVertex(v)).contains(returnPairInSet(this.adjacencyList.get().
        		get(returnVertex(v)),returnVertex(u)));
    }
    
    /**
     * Get connected vertices of a vertex.
     * 
     * @param v The vertex.
     * @return An iterable for connected vertices.
     */
    public Iterable<Pair<Vertex<T>,Integer>> getNeighbours(T v) {
        return this.adjacencyList.get().get(returnVertex(v));
    }
    
    /**
     * Get all vertices in the graph.
     * 
     * @return An Iterable for all vertices in the graph.
     */
    public Iterable<Vertex<T>> getAllVertices() {
        return this.adjacencyList.get().keySet();
    }
    
    public HashMap<Vertex<T>,Set<Pair<Vertex<T>,Integer>>> getAdjacencyList(){
		return adjacencyList.get();
    }
    
    @Override
    public String toString() {
    		String toReturn = "";
    		for(Vertex<T> vertex : adjacencyList.get().keySet()) {
    			toReturn = toReturn + vertex.toString() + " -> [" + 
    					String.join(" -> ",adjacencyList.get().get(returnVertex(vertex.getElement())).stream().
    							map(o -> o.toString()).collect(Collectors.toList())) + "]";
    			
//    			toReturn = toReturn +
    			
    			toReturn = toReturn + "\n";
    			
    			
    		}
    		
    		return toReturn;
    }
}
