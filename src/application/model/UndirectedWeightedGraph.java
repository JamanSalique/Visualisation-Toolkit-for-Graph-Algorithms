package application.model;

import java.util.HashSet;

import javafx.util.Pair;

public class UndirectedWeightedGraph<T> extends AbstractWeightedGraph<T>{

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
		
       super.getAdjacencyList().put(new Vertex<T>(v), new HashSet<Pair<Vertex<T>,Integer>>());
		
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
     * Add new edge between vertex. Adding new edge from u to v will
     * automatically add new edge from v to u since the graph is undirected.
     * 
     * @param v Start vertex.
     * @param u Destination vertex.
     * @param w Weight of edge
     */
	@Override
	public void addEdge(T v, T u, int w) {
		if (!containsVertex(v) || !containsVertex(u)) {
            throw new IllegalArgumentException();
        }
		
		if(!isAdjacent(v,u)) {
			super.getAdjacencyList().get(returnVertex(v)).add(new Pair<Vertex<T>,Integer>(returnVertex(u),w));
			super.getAdjacencyList().get(returnVertex(u)).add(new Pair<Vertex<T>,Integer>(returnVertex(v),w));
		}
        
        
		
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
        
        super.getAdjacencyList().get(returnVertex(v)).remove(returnPairInSet(
        										super.getAdjacencyList().get(returnVertex(v)),returnVertex(u)));
        
        super.getAdjacencyList().get(returnVertex(u)).remove(returnPairInSet(
				super.getAdjacencyList().get(returnVertex(u)),returnVertex(v)));
		
	}

	

}
