package application.model;

import java.util.HashSet;

public class DirectedNonWeightedGraph<T> extends AbstractNonWeightedGraph<T>{

	public DirectedNonWeightedGraph(){
		super();
	}
	
	 /**
     * Add new vertex to the graph.
     * 
     * @param v The vertex object. 
     */
	@Override
	public void addVertex(T v) {
		if (super.containsVertex(v)) {
			throw new IllegalArgumentException("Vertex already exists.");
        }
		
		super.getAdjacencyList().put(new Vertex<T>(v), new HashSet<Vertex<T>>());
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
            super.getAdjacencyList().get(u).remove(returnVertex(v));
        }
        
        super.getAdjacencyList().remove(returnVertex(v));
		
	}

	/**
     * Add new edge between vertex. Adding new edge from u to v will
     * automatically add new edge from v to u since the graph is undirected.
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
     * Remove the edge between vertex. Removing the edge from u to v will 
     * automatically remove the edge from v to u since the graph is undirected.
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
