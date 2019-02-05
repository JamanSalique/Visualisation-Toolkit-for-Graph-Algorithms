package application.model;

import java.util.HashSet;

/**
 * This class represents a undirected graph that has non weighted edges. It is a sub class of AbstractNonWeightedGraph.
 * @author jamansalique
 *
 * @param <T>
 */
public class UndirectedNonWeightedGraph<T> extends AbstractNonWeightedGraph<T>{

	/**
	 * Contructor that calls constructor of super class.
	 */
	public UndirectedNonWeightedGraph() {
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
     * Add new edge between 2 vertices u and v. Adding new edge from u to v will
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
        
        if(!isAdjacent(v,u)) {
        	super.getAdjacencyList().get(returnVertex(v)).add(returnVertex(u));
        	super.getAdjacencyList().get(returnVertex(u)).add(returnVertex(v));
        }
        
        
    }
    
	/**
     * Remove the edge between 2 vertices u and v. Removing the edge from u to v will 
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
        super.getAdjacencyList().get(returnVertex(u)).remove(returnVertex(v));
    }

}
