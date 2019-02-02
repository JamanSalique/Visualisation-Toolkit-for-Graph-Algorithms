package testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import application.model.DirectedNonWeightedGraph;
import application.model.Vertex;

class DirectedNonWeightedGraphTests {

	/**
	 * This function is to test whether adding vertices to an directedNonWeightedGraph for all data types
	 * works. I add vertices to the graphs and then test to see whether the size of the adjacency list is equal to
	 * the amount of vertices I added. 
	 */
	@Test
	void addingVerticesToGraphTest() {
		
		DirectedNonWeightedGraph<Integer> directedNonWeightedInt = new DirectedNonWeightedGraph<Integer>();
		Vertex<Integer> v0 = new Vertex<Integer>(0);
		Vertex<Integer> v1 = new Vertex<Integer>(1);
		Vertex<Integer> v2 = new Vertex<Integer>(2);
		Vertex<Integer> v3 = new Vertex<Integer>(3);
		Vertex<Integer> v4 = new Vertex<Integer>(4);
		
		directedNonWeightedInt.addVertex(v0.getElement());
		directedNonWeightedInt.addVertex(v1.getElement());
		directedNonWeightedInt.addVertex(v2.getElement());
		directedNonWeightedInt.addVertex(v3.getElement());
		directedNonWeightedInt.addVertex(v4.getElement());
		
		DirectedNonWeightedGraph<Double> directedNonWeightedDouble = new DirectedNonWeightedGraph<Double>();
		Vertex<Double> v0d = new Vertex<Double>(0.0);
		Vertex<Double> v1d = new Vertex<Double>(1.0);
		Vertex<Double> v2d = new Vertex<Double>(2.0);
		Vertex<Double> v3d = new Vertex<Double>(3.0);
		Vertex<Double> v4d = new Vertex<Double>(4.0);
		
		directedNonWeightedDouble.addVertex(v0d.getElement());
		directedNonWeightedDouble.addVertex(v1d.getElement());
		directedNonWeightedDouble.addVertex(v2d.getElement());
		directedNonWeightedDouble.addVertex(v3d.getElement());
		directedNonWeightedDouble.addVertex(v4d.getElement());
		
		DirectedNonWeightedGraph<String> directedNonWeightedString = new DirectedNonWeightedGraph<String>();
		Vertex<String> va = new Vertex<String>("a");
		Vertex<String> vb = new Vertex<String>("b");
		Vertex<String> vc = new Vertex<String>("c");
		Vertex<String> vd = new Vertex<String>("d");
		Vertex<String> ve = new Vertex<String>("e");
		
		directedNonWeightedString.addVertex(va.getElement());
		directedNonWeightedString.addVertex(vb.getElement());
		directedNonWeightedString.addVertex(vc.getElement());
		directedNonWeightedString.addVertex(vd.getElement());
		directedNonWeightedString.addVertex(ve.getElement());
		
		assertEquals(5,directedNonWeightedInt.getAdjacencyList().size());
		assertEquals(5,directedNonWeightedDouble.getAdjacencyList().size());
		assertEquals(5,directedNonWeightedString.getAdjacencyList().size());
		
		
	}
	
	/**
	 * This function is to test whether removing vertices from an directedNonWeightedGraph for all data types
	 * works. I first add some vertices to the graphs and then remove a single vertex and then test to see if the
	 * adjacency list contains the vertex I removed (which should be false).
	 */
	@Test
	void removingVerticesFromGraphTest() {
		
		DirectedNonWeightedGraph<Integer> directedNonWeightedInt = new DirectedNonWeightedGraph<Integer>();
		Vertex<Integer> v0 = new Vertex<Integer>(0);
		Vertex<Integer> v1 = new Vertex<Integer>(1);
		Vertex<Integer> v2 = new Vertex<Integer>(2);
		Vertex<Integer> v3 = new Vertex<Integer>(3);
		Vertex<Integer> v4 = new Vertex<Integer>(4);
		
		directedNonWeightedInt.addVertex(v0.getElement());
		directedNonWeightedInt.addVertex(v1.getElement());
		directedNonWeightedInt.addVertex(v2.getElement());
		directedNonWeightedInt.addVertex(v3.getElement());
		directedNonWeightedInt.addVertex(v4.getElement());
		
		directedNonWeightedInt.removeVertex(v4.getElement());
		
		DirectedNonWeightedGraph<Double> directedNonWeightedDouble = new DirectedNonWeightedGraph<Double>();
		Vertex<Double> v0d = new Vertex<Double>(0.0);
		Vertex<Double> v1d = new Vertex<Double>(1.0);
		Vertex<Double> v2d = new Vertex<Double>(2.0);
		Vertex<Double> v3d = new Vertex<Double>(3.0);
		Vertex<Double> v4d = new Vertex<Double>(4.0);
		
		directedNonWeightedDouble.addVertex(v0d.getElement());
		directedNonWeightedDouble.addVertex(v1d.getElement());
		directedNonWeightedDouble.addVertex(v2d.getElement());
		directedNonWeightedDouble.addVertex(v3d.getElement());
		directedNonWeightedDouble.addVertex(v4d.getElement());
		
		directedNonWeightedDouble.removeVertex(v0d.getElement());
		directedNonWeightedDouble.removeVertex(v3d.getElement());
		
		DirectedNonWeightedGraph<String> directedNonWeightedString = new DirectedNonWeightedGraph<String>();
		Vertex<String> va = new Vertex<String>("a");
		Vertex<String> vb = new Vertex<String>("b");
		Vertex<String> vc = new Vertex<String>("c");
		Vertex<String> vd = new Vertex<String>("d");
		Vertex<String> ve = new Vertex<String>("e");
		
		directedNonWeightedString.addVertex(va.getElement());
		directedNonWeightedString.addVertex(vb.getElement());
		directedNonWeightedString.addVertex(vc.getElement());
		directedNonWeightedString.addVertex(vd.getElement());
		directedNonWeightedString.addVertex(ve.getElement());
		
		directedNonWeightedString.removeVertex(va.getElement());
		directedNonWeightedString.removeVertex(ve.getElement());
		directedNonWeightedString.removeVertex(vc.getElement());
		
		assertFalse(directedNonWeightedInt.containsVertex(v4.getElement()));
		
		assertFalse(directedNonWeightedDouble.containsVertex(v0d.getElement()));
		assertFalse(directedNonWeightedDouble.containsVertex(v3d.getElement()));
		
		assertFalse(directedNonWeightedString.containsVertex(va.getElement()));
		assertFalse(directedNonWeightedString.containsVertex(ve.getElement()));
		assertFalse(directedNonWeightedString.containsVertex(vc.getElement()));
		
		
	}
	
	/**
	 * This function is to test whether adding edges to an directedNonWeightedGraph for all data types
	 * works. I add vertices to the graphs and then add edges between some of the vertices. I then test
	 * to see if specific vertices are adjacent (ie are connected by an edge).
	 */
	@Test
	void addingEdgesToGraphTest() {
		
		DirectedNonWeightedGraph<Integer> directedNonWeightedInt = new DirectedNonWeightedGraph<Integer>();
		Vertex<Integer> v0 = new Vertex<Integer>(0);
		Vertex<Integer> v1 = new Vertex<Integer>(1);
		Vertex<Integer> v2 = new Vertex<Integer>(2);
		Vertex<Integer> v3 = new Vertex<Integer>(3);
		Vertex<Integer> v4 = new Vertex<Integer>(4);
		
		directedNonWeightedInt.addVertex(v0.getElement());
		directedNonWeightedInt.addVertex(v1.getElement());
		directedNonWeightedInt.addVertex(v2.getElement());
		directedNonWeightedInt.addVertex(v3.getElement());
		directedNonWeightedInt.addVertex(v4.getElement());
		
		directedNonWeightedInt.addEdge(v0.getElement(), v1.getElement());
		directedNonWeightedInt.addEdge(v1.getElement(), v2.getElement());
		
		DirectedNonWeightedGraph<Double> directedNonWeightedDouble = new DirectedNonWeightedGraph<Double>();
		Vertex<Double> v0d = new Vertex<Double>(0.0);
		Vertex<Double> v1d = new Vertex<Double>(1.0);
		Vertex<Double> v2d = new Vertex<Double>(2.0);
		Vertex<Double> v3d = new Vertex<Double>(3.0);
		Vertex<Double> v4d = new Vertex<Double>(4.0);
		
		directedNonWeightedDouble.addVertex(v0d.getElement());
		directedNonWeightedDouble.addVertex(v1d.getElement());
		directedNonWeightedDouble.addVertex(v2d.getElement());
		directedNonWeightedDouble.addVertex(v3d.getElement());
		directedNonWeightedDouble.addVertex(v4d.getElement());
		
		directedNonWeightedDouble.addEdge(v0d.getElement(), v1d.getElement());
		directedNonWeightedDouble.addEdge(v1d.getElement(), v2d.getElement());
		
		DirectedNonWeightedGraph<String> directedNonWeightedString = new DirectedNonWeightedGraph<String>();
		Vertex<String> va = new Vertex<String>("a");
		Vertex<String> vb = new Vertex<String>("b");
		Vertex<String> vc = new Vertex<String>("c");
		Vertex<String> vd = new Vertex<String>("d");
		Vertex<String> ve = new Vertex<String>("e");
		
		directedNonWeightedString.addVertex(va.getElement());
		directedNonWeightedString.addVertex(vb.getElement());
		directedNonWeightedString.addVertex(vc.getElement());
		directedNonWeightedString.addVertex(vd.getElement());
		directedNonWeightedString.addVertex(ve.getElement());
		
		directedNonWeightedString.addEdge(va.getElement(), vb.getElement());
		directedNonWeightedString.addEdge(vb.getElement(), vc.getElement());
		
		assertTrue(directedNonWeightedInt.isAdjacent(v0.getElement(), v1.getElement()));
		assertTrue(directedNonWeightedInt.isAdjacent(v1.getElement(), v2.getElement()));
		
		assertTrue(directedNonWeightedDouble.isAdjacent(v0d.getElement(), v1d.getElement()));
		assertTrue(directedNonWeightedDouble.isAdjacent(v1d.getElement(), v2d.getElement()));
		
		assertTrue(directedNonWeightedString.isAdjacent(va.getElement(), vb.getElement()));
		assertTrue(directedNonWeightedString.isAdjacent(vb.getElement(), vc.getElement()));

	}
	
	/**
	 * This function is to test whether removing edges from an directedNonWeightedGraph for all data types
	 * works. I remove vertices to the graphs and add edges between some of the vertices. I then remove an
	 * edge and test to see if the vertices are adjacent to each other (which should return false).
	 */
	@Test
	void removingEdgesToGraphTest() {
		
		DirectedNonWeightedGraph<Integer> directedNonWeightedInt = new DirectedNonWeightedGraph<Integer>();
		Vertex<Integer> v0 = new Vertex<Integer>(0);
		Vertex<Integer> v1 = new Vertex<Integer>(1);
		Vertex<Integer> v2 = new Vertex<Integer>(2);
		Vertex<Integer> v3 = new Vertex<Integer>(3);
		Vertex<Integer> v4 = new Vertex<Integer>(4);
		
		directedNonWeightedInt.addVertex(v0.getElement());
		directedNonWeightedInt.addVertex(v1.getElement());
		directedNonWeightedInt.addVertex(v2.getElement());
		directedNonWeightedInt.addVertex(v3.getElement());
		directedNonWeightedInt.addVertex(v4.getElement());
		
		directedNonWeightedInt.addEdge(v0.getElement(), v1.getElement());
		directedNonWeightedInt.addEdge(v1.getElement(), v2.getElement());
		directedNonWeightedInt.removeEdge(v1.getElement(), v2.getElement());
		
		DirectedNonWeightedGraph<Double> directedNonWeightedDouble = new DirectedNonWeightedGraph<Double>();
		Vertex<Double> v0d = new Vertex<Double>(0.0);
		Vertex<Double> v1d = new Vertex<Double>(1.0);
		Vertex<Double> v2d = new Vertex<Double>(2.0);
		Vertex<Double> v3d = new Vertex<Double>(3.0);
		Vertex<Double> v4d = new Vertex<Double>(4.0);
		
		directedNonWeightedDouble.addVertex(v0d.getElement());
		directedNonWeightedDouble.addVertex(v1d.getElement());
		directedNonWeightedDouble.addVertex(v2d.getElement());
		directedNonWeightedDouble.addVertex(v3d.getElement());
		directedNonWeightedDouble.addVertex(v4d.getElement());
		
		directedNonWeightedDouble.addEdge(v0d.getElement(), v1d.getElement());
		directedNonWeightedDouble.addEdge(v1d.getElement(), v2d.getElement());
		directedNonWeightedDouble.removeEdge(v1d.getElement(), v2d.getElement());
		
		DirectedNonWeightedGraph<String> directedNonWeightedString = new DirectedNonWeightedGraph<String>();
		Vertex<String> va = new Vertex<String>("a");
		Vertex<String> vb = new Vertex<String>("b");
		Vertex<String> vc = new Vertex<String>("c");
		Vertex<String> vd = new Vertex<String>("d");
		Vertex<String> ve = new Vertex<String>("e");
		
		directedNonWeightedString.addVertex(va.getElement());
		directedNonWeightedString.addVertex(vb.getElement());
		directedNonWeightedString.addVertex(vc.getElement());
		directedNonWeightedString.addVertex(vd.getElement());
		directedNonWeightedString.addVertex(ve.getElement());
		
		directedNonWeightedString.addEdge(va.getElement(), vb.getElement());
		directedNonWeightedString.addEdge(vb.getElement(), vc.getElement());
		directedNonWeightedString.removeEdge(vb.getElement(), vc.getElement());
		
		assertTrue(directedNonWeightedInt.isAdjacent(v0.getElement(), v1.getElement()));
		assertFalse(directedNonWeightedInt.isAdjacent(v1.getElement(), v2.getElement()));
		
		assertTrue(directedNonWeightedDouble.isAdjacent(v0d.getElement(), v1d.getElement()));
		assertFalse(directedNonWeightedDouble.isAdjacent(v1d.getElement(), v2d.getElement()));
		
		assertTrue(directedNonWeightedString.isAdjacent(va.getElement(), vb.getElement()));
		assertFalse(directedNonWeightedString.isAdjacent(vb.getElement(), vc.getElement()));

	}
}
