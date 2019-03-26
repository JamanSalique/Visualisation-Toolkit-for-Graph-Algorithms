package application.model;

import java.util.HashSet;

/**
 * This class represents a directed graph that has non weighted edges. It is a sub class of AbstractNonWeightedGraph.
 * @author jamansalique
 *
 * @param <T>
 */
public class DirectedNonWeightedGraph<T> extends AbstractNonWeightedGraph<T>{

	/**
	 * Constructor that calls constructor of super class.
	 */
	public DirectedNonWeightedGraph(){
		super();
	}
	
	/**
     * Add new vertex to the graph.
     * 
     * @param v The data the vertex will hold. 
     */
	@Override
	public void addVertex(T v) {
		if (super.containsVertex(v)) {
			throw new IllegalArgumentException("Vertex already exists.");
        }
		
		super.getAdjacencyList().put(new Vertex<T>(v), new HashSet<Vertex<T>>());
	}

	/**
     * Remove a vertex from the graph.
     * 
     * @param v The data the vertex you want to remove holds.
     */
	@Override
	public void removeVertex(T v) {
		if (!containsVertex(v)) {
            throw new IllegalArgumentException("Vertex doesn't exist.");
        }
        
        for (Vertex<T> u: this.getAllVertices()) {
            super.getAdjacencyList().get(u).remove(returnVertex(v));
        }
        
        super.getAdjacencyList().remove(returnVertex(v));
		
	}

	/**
     * Add new edge between 2 vertices u and v.
     * 
     * @param v Start vertex.
     * @param u Destination vertex.
     */
	@Override
	public void addEdge(T v, T u) {
		if (!containsVertex(v) || !containsVertex(u)) {
            throw new IllegalArgumentException();
        }
        
        super.getAdjacencyList().get(returnVertex(v)).add(returnVertex(u));
		
	}

	/**
     * Remove the edge between 2 vertices u and v.
     * 
     * @param v Start vertex.
     * @param u Destination vertex.
     */
	@Override
	public void removeEdge(T v, T u) {
		if (!containsVertex(v) || !containsVertex(u)) {
            throw new IllegalArgumentException();
        }
        
        super.getAdjacencyList().get(returnVertex(v)).remove(returnVertex(u));
		
	}

}
