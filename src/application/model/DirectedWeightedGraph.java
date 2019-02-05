package application.model;

import java.util.HashSet;

import javafx.util.Pair;

/**
 * This class represents a directed graph that has weighted edges. It is a sub class of AbstractWeightedGraph.
 * @author jamansalique
 *
 * @param <T>
 */
public class DirectedWeightedGraph<T> extends AbstractWeightedGraph<T>{

	/**
	 * Contructor that calls constructor of super class.
	 */
	public DirectedWeightedGraph() {
		super();
	}

	/**
     * Add new vertex to the graph.
     * 
     * @param v The vertex object. 
     */
	@Override
	public void addVertex(T v) {
		if (containsVertex(v)) {
			throw new IllegalArgumentException("Vertex already exists.");
        }
		
		super.getAdjacencyList().put(new Vertex<T>(v), new HashSet<Pair<Vertex<T>,Double>>());
	}

	/**
     * Remove the vertex v from the graph.
     * 
     * @param v The vertex that will be removed.
     */
	@Override
	public void removeVertex(T v) {
		if (!containsVertex(v)) {
            throw new IllegalArgumentException("Vertex doesn't exist.");
        }
        
        for (Vertex<T> u: this.getAllVertices()) {
            super.getAdjacencyList().get(u).remove(returnPairInSet(super.getAdjacencyList().get(u),returnVertex(v)));
        }
 
        super.getAdjacencyList().remove(returnVertex(v)); 
		
	}

	/**
     * Add new edge between 2 vertices u and v with weight w.
     * 
     * @param v Start vertex.
     * @param u Destination vertex.
     */
	@Override
	public void addEdge(T v, T u, double w) {
		if (!containsVertex(v) || !containsVertex(u)) {
            throw new IllegalArgumentException();
        }
        
        super.getAdjacencyList().get(returnVertex(v)).add(new Pair<Vertex<T>,Double>(returnVertex(u),w));
		
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
        
        super.getAdjacencyList().get(returnVertex(v)).remove(returnPairInSet(
        										super.getAdjacencyList().get(returnVertex(v)),returnVertex(u)));

	}
}
