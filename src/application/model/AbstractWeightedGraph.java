package application.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.util.Pair;

/**
 * This abstract class represents a graph that contains edges that are weighted. This class contains non abstract functions where
 *  all weighted graph subclasses will have these methods and the implementation for these functions are the same for all weighted
 *  graphs. This class also contains abstract functions where the body of these functions must be implemented on creation of the subclasses.
 * @author jamansalique
 *
 * @param <T>
 */
public abstract class AbstractWeightedGraph<T> {
	
	//This field represents an adjacency list of the graph.
	private final ObjectProperty<HashMap<Vertex<T>,Set<Pair<Vertex<T>,Double>>>> adjacencyList;
	
	/**
	 * Constructor where the adjacency list is initialised.
	 */
	public AbstractWeightedGraph() {
		this.adjacencyList = new SimpleObjectProperty<HashMap<Vertex<T>,Set<Pair<Vertex<T>,Double>>>>(
				new HashMap<Vertex<T>,Set<Pair<Vertex<T>,Double>>>());
	}
	
	/**
	 * This method checks whether the graph contains a vertex with a specific element.
	 * @param element
	 * @return true if graph does contain vertex with element else returns false.
	 */
	public boolean containsVertex(T element) {
		for (Map.Entry<Vertex<T>,Set<Pair<Vertex<T>,Double>>> entry : adjacencyList.get().entrySet()) {
			if(entry.getKey().getElement().equals(element)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * This method given a element will return the Vertex object that contains this element.
	 * @param element
	 * @return
	 */
	public Vertex<T> returnVertex(T element) {
		for (Map.Entry<Vertex<T>,Set<Pair<Vertex<T>,Double>>> entry : adjacencyList.get().entrySet()) {
			if(entry.getKey().getElement().equals(element)) {
				return entry.getKey();
			}
		}
		
		return null;
	}
	
	/**
	 * This method given a vertex v will return all neighbours of v and the weight of the edge between v and the neighbour vertex.
	 * @param set
	 * @param v
	 * @return
	 */
	protected Pair<Vertex<T>,Double> returnPairInSet(Set<Pair<Vertex<T>, Double>> set, Vertex<T> v ){
		for(Pair<Vertex<T>,Double> p : set) {
			if(p.getKey().equals(v)) {
				return p;
			}
		}
		return null;
	}
	
	/**
	 * This method removes all vertices and edges from the graph by clearing the adjacency list.
	 */
	public void clearGraph() {
		adjacencyList.get().clear();
	}
	
	 /**
     * Add new vertex to the graph.
     * 
     * @param v The data the vertex will hold. 
     */
	public abstract void addVertex(T v);
	
	/**
     * Remove a vertex from the graph.
     * 
     * @param v The data the vertex you want to remove holds.
     */
	public abstract void removeVertex(T v);
	
    
	/**
     * Add new edge between 2 vertices with weight w.
     * 
     * @param v Start vertex.
     * @param u Destination vertex.
     */
	public abstract void addEdge(T v, T u, double w);
    
	/**
     * Remove the edge between 2 vertices.
     * 
     * @param v Start vertex.
     * @param u Destination vertex.
     */  
	public abstract void removeEdge(T v, T u);
    
	/**
     * Check adjacency between 2 vertices in the graph in other words check if vertex u is a neighbour of vertex v.
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
     * Retrieve all vertices adjacent to vertex v.
     * 
     * @param v The vertex.
     * @return An iterable for connected vertices.
     */
    public Iterable<Pair<Vertex<T>,Double>> getNeighbours(T v) {
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
    
    /**
     *Getter method for the adjacency list.
     * @return
     */
    public HashMap<Vertex<T>,Set<Pair<Vertex<T>,Double>>> getAdjacencyList(){
		return adjacencyList.get();
    }
    
    /**
     * Overridden toString method so if we print an instance of a non weighted graph we see the adjacency list representation of the graph.
     */
    @Override
    public String toString() {
    		String toReturn = "";
    		for(Vertex<T> vertex : adjacencyList.get().keySet()) {
    			toReturn = toReturn + vertex.toString() + " -> [" + 
    					String.join(" -> ",adjacencyList.get().get(returnVertex(vertex.getElement())).stream().
    							map(o -> o.toString()).collect(Collectors.toList())) + "]";

    			toReturn = toReturn + "\n";

    		}
    		
    		return toReturn;
    }
}
