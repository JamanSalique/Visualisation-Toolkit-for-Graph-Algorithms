package testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import application.model.DirectedWeightedGraph;
import application.model.Vertex;

class DirectedWeightedGraphTests {

	/**
	 * This function is to test whether adding vertices to an directedWeightedGraph for all data types
	 * works. I add vertices to the graphs and then test to see whether the size of the adjacency list is equal to
	 * the amount of vertices I added. 
	 */
	@Test
	void addingVerticesToGraphTest() {
		
		DirectedWeightedGraph<Integer> directedWeightedInt = new DirectedWeightedGraph<Integer>();
		Vertex<Integer> v0 = new Vertex<Integer>(0);
		Vertex<Integer> v1 = new Vertex<Integer>(1);
		Vertex<Integer> v2 = new Vertex<Integer>(2);
		Vertex<Integer> v3 = new Vertex<Integer>(3);
		Vertex<Integer> v4 = new Vertex<Integer>(4);
		
		directedWeightedInt.addVertex(v0.getElement());
		directedWeightedInt.addVertex(v1.getElement());
		directedWeightedInt.addVertex(v2.getElement());
		directedWeightedInt.addVertex(v3.getElement());
		directedWeightedInt.addVertex(v4.getElement());
		
		DirectedWeightedGraph<Double> directedWeightedDouble = new DirectedWeightedGraph<Double>();
		Vertex<Double> v0d = new Vertex<Double>(0.0);
		Vertex<Double> v1d = new Vertex<Double>(1.0);
		Vertex<Double> v2d = new Vertex<Double>(2.0);
		Vertex<Double> v3d = new Vertex<Double>(3.0);
		Vertex<Double> v4d = new Vertex<Double>(4.0);
		
		directedWeightedDouble.addVertex(v0d.getElement());
		directedWeightedDouble.addVertex(v1d.getElement());
		directedWeightedDouble.addVertex(v2d.getElement());
		directedWeightedDouble.addVertex(v3d.getElement());
		directedWeightedDouble.addVertex(v4d.getElement());
		
		DirectedWeightedGraph<String> directedWeightedString = new DirectedWeightedGraph<String>();
		Vertex<String> va = new Vertex<String>("a");
		Vertex<String> vb = new Vertex<String>("b");
		Vertex<String> vc = new Vertex<String>("c");
		Vertex<String> vd = new Vertex<String>("d");
		Vertex<String> ve = new Vertex<String>("e");
		
		directedWeightedString.addVertex(va.getElement());
		directedWeightedString.addVertex(vb.getElement());
		directedWeightedString.addVertex(vc.getElement());
		directedWeightedString.addVertex(vd.getElement());
		directedWeightedString.addVertex(ve.getElement());
		
		assertEquals(5,directedWeightedInt.getAdjacencyList().size());
		assertEquals(5,directedWeightedDouble.getAdjacencyList().size());
		assertEquals(5,directedWeightedString.getAdjacencyList().size());
		
		
	}
	
	/**
	 * This function is to test whether removing vertices from an directedWeightedGraph for all data types
	 * works. I first add some vertices to the graphs and then remove a single vertex and then test to see if the
	 * adjacency list contains the vertex I removed (which should be false).
	 */
	@Test
	void removingVerticesFromGraphTest() {
		
		DirectedWeightedGraph<Integer> directedWeightedInt = new DirectedWeightedGraph<Integer>();
		Vertex<Integer> v0 = new Vertex<Integer>(0);
		Vertex<Integer> v1 = new Vertex<Integer>(1);
		Vertex<Integer> v2 = new Vertex<Integer>(2);
		Vertex<Integer> v3 = new Vertex<Integer>(3);
		Vertex<Integer> v4 = new Vertex<Integer>(4);
		
		directedWeightedInt.addVertex(v0.getElement());
		directedWeightedInt.addVertex(v1.getElement());
		directedWeightedInt.addVertex(v2.getElement());
		directedWeightedInt.addVertex(v3.getElement());
		directedWeightedInt.addVertex(v4.getElement());
		
		directedWeightedInt.removeVertex(v4.getElement());
		
		DirectedWeightedGraph<Double> directedWeightedDouble = new DirectedWeightedGraph<Double>();
		Vertex<Double> v0d = new Vertex<Double>(0.0);
		Vertex<Double> v1d = new Vertex<Double>(1.0);
		Vertex<Double> v2d = new Vertex<Double>(2.0);
		Vertex<Double> v3d = new Vertex<Double>(3.0);
		Vertex<Double> v4d = new Vertex<Double>(4.0);
		
		directedWeightedDouble.addVertex(v0d.getElement());
		directedWeightedDouble.addVertex(v1d.getElement());
		directedWeightedDouble.addVertex(v2d.getElement());
		directedWeightedDouble.addVertex(v3d.getElement());
		directedWeightedDouble.addVertex(v4d.getElement());
		
		directedWeightedDouble.removeVertex(v0d.getElement());
		directedWeightedDouble.removeVertex(v3d.getElement());
		
		DirectedWeightedGraph<String> directedWeightedString = new DirectedWeightedGraph<String>();
		Vertex<String> va = new Vertex<String>("a");
		Vertex<String> vb = new Vertex<String>("b");
		Vertex<String> vc = new Vertex<String>("c");
		Vertex<String> vd = new Vertex<String>("d");
		Vertex<String> ve = new Vertex<String>("e");
		
		directedWeightedString.addVertex(va.getElement());
		directedWeightedString.addVertex(vb.getElement());
		directedWeightedString.addVertex(vc.getElement());
		directedWeightedString.addVertex(vd.getElement());
		directedWeightedString.addVertex(ve.getElement());
		
		directedWeightedString.removeVertex(va.getElement());
		directedWeightedString.removeVertex(ve.getElement());
		directedWeightedString.removeVertex(vc.getElement());
		
		assertFalse(directedWeightedInt.containsVertex(v4.getElement()));
		
		assertFalse(directedWeightedDouble.containsVertex(v0d.getElement()));
		assertFalse(directedWeightedDouble.containsVertex(v3d.getElement()));
		
		assertFalse(directedWeightedString.containsVertex(va.getElement()));
		assertFalse(directedWeightedString.containsVertex(ve.getElement()));
		assertFalse(directedWeightedString.containsVertex(vc.getElement()));
		
		
	}
	
	/**
	 * This function is to test whether adding edges to an directedWeightedGraph for all data types
	 * works. I add vertices to the graphs and then add edges between some of the vertices. I then test
	 * to see if specific vertices are adjacent (ie are connected by an edge).
	 */
	@Test
	void addingEdgesToGraphTest() {
		
		DirectedWeightedGraph<Integer> directedWeightedInt = new DirectedWeightedGraph<Integer>();
		Vertex<Integer> v0 = new Vertex<Integer>(0);
		Vertex<Integer> v1 = new Vertex<Integer>(1);
		Vertex<Integer> v2 = new Vertex<Integer>(2);
		Vertex<Integer> v3 = new Vertex<Integer>(3);
		Vertex<Integer> v4 = new Vertex<Integer>(4);
		
		directedWeightedInt.addVertex(v0.getElement());
		directedWeightedInt.addVertex(v1.getElement());
		directedWeightedInt.addVertex(v2.getElement());
		directedWeightedInt.addVertex(v3.getElement());
		directedWeightedInt.addVertex(v4.getElement());
		
		directedWeightedInt.addEdge(v0.getElement(), v1.getElement(),1.0);
		directedWeightedInt.addEdge(v1.getElement(), v2.getElement(),1.0);
		
		DirectedWeightedGraph<Double> directedWeightedDouble = new DirectedWeightedGraph<Double>();
		Vertex<Double> v0d = new Vertex<Double>(0.0);
		Vertex<Double> v1d = new Vertex<Double>(1.0);
		Vertex<Double> v2d = new Vertex<Double>(2.0);
		Vertex<Double> v3d = new Vertex<Double>(3.0);
		Vertex<Double> v4d = new Vertex<Double>(4.0);
		
		directedWeightedDouble.addVertex(v0d.getElement());
		directedWeightedDouble.addVertex(v1d.getElement());
		directedWeightedDouble.addVertex(v2d.getElement());
		directedWeightedDouble.addVertex(v3d.getElement());
		directedWeightedDouble.addVertex(v4d.getElement());
		
		directedWeightedDouble.addEdge(v0d.getElement(), v1d.getElement(),1.0);
		directedWeightedDouble.addEdge(v1d.getElement(), v2d.getElement(),1.0);
		
		DirectedWeightedGraph<String> directedWeightedString = new DirectedWeightedGraph<String>();
		Vertex<String> va = new Vertex<String>("a");
		Vertex<String> vb = new Vertex<String>("b");
		Vertex<String> vc = new Vertex<String>("c");
		Vertex<String> vd = new Vertex<String>("d");
		Vertex<String> ve = new Vertex<String>("e");
		
		directedWeightedString.addVertex(va.getElement());
		directedWeightedString.addVertex(vb.getElement());
		directedWeightedString.addVertex(vc.getElement());
		directedWeightedString.addVertex(vd.getElement());
		directedWeightedString.addVertex(ve.getElement());
		
		directedWeightedString.addEdge(va.getElement(), vb.getElement(),1.0);
		directedWeightedString.addEdge(vb.getElement(), vc.getElement(),1.0);
		
		assertTrue(directedWeightedInt.isAdjacent(v0.getElement(), v1.getElement()));
		assertTrue(directedWeightedInt.isAdjacent(v1.getElement(), v2.getElement()));
		
		assertTrue(directedWeightedDouble.isAdjacent(v0d.getElement(), v1d.getElement()));
		assertTrue(directedWeightedDouble.isAdjacent(v1d.getElement(), v2d.getElement()));
		
		assertTrue(directedWeightedString.isAdjacent(va.getElement(), vb.getElement()));
		assertTrue(directedWeightedString.isAdjacent(vb.getElement(), vc.getElement()));

	}
	
	/**
	 * This function is to test whether removing edges from an directedWeightedGraph for all data types
	 * works. I remove vertices to the graphs and add edges between some of the vertices. I then remove an
	 * edge and test to see if the vertices are adjacent to each other (which should return false).
	 */
	@Test
	void removingEdgesToGraphTest() {
		
		DirectedWeightedGraph<Integer> directedWeightedInt = new DirectedWeightedGraph<Integer>();
		Vertex<Integer> v0 = new Vertex<Integer>(0);
		Vertex<Integer> v1 = new Vertex<Integer>(1);
		Vertex<Integer> v2 = new Vertex<Integer>(2);
		Vertex<Integer> v3 = new Vertex<Integer>(3);
		Vertex<Integer> v4 = new Vertex<Integer>(4);
		
		directedWeightedInt.addVertex(v0.getElement());
		directedWeightedInt.addVertex(v1.getElement());
		directedWeightedInt.addVertex(v2.getElement());
		directedWeightedInt.addVertex(v3.getElement());
		directedWeightedInt.addVertex(v4.getElement());
		
		directedWeightedInt.addEdge(v0.getElement(), v1.getElement(),1.0);
		directedWeightedInt.addEdge(v1.getElement(), v2.getElement(),1.0);
		directedWeightedInt.removeEdge(v1.getElement(), v2.getElement());
		
		DirectedWeightedGraph<Double> directedWeightedDouble = new DirectedWeightedGraph<Double>();
		Vertex<Double> v0d = new Vertex<Double>(0.0);
		Vertex<Double> v1d = new Vertex<Double>(1.0);
		Vertex<Double> v2d = new Vertex<Double>(2.0);
		Vertex<Double> v3d = new Vertex<Double>(3.0);
		Vertex<Double> v4d = new Vertex<Double>(4.0);
		
		directedWeightedDouble.addVertex(v0d.getElement());
		directedWeightedDouble.addVertex(v1d.getElement());
		directedWeightedDouble.addVertex(v2d.getElement());
		directedWeightedDouble.addVertex(v3d.getElement());
		directedWeightedDouble.addVertex(v4d.getElement());
		
		directedWeightedDouble.addEdge(v0d.getElement(), v1d.getElement(),1.0);
		directedWeightedDouble.addEdge(v1d.getElement(), v2d.getElement(),1.0);
		directedWeightedDouble.removeEdge(v1d.getElement(), v2d.getElement());
		
		DirectedWeightedGraph<String> directedWeightedString = new DirectedWeightedGraph<String>();
		Vertex<String> va = new Vertex<String>("a");
		Vertex<String> vb = new Vertex<String>("b");
		Vertex<String> vc = new Vertex<String>("c");
		Vertex<String> vd = new Vertex<String>("d");
		Vertex<String> ve = new Vertex<String>("e");
		
		directedWeightedString.addVertex(va.getElement());
		directedWeightedString.addVertex(vb.getElement());
		directedWeightedString.addVertex(vc.getElement());
		directedWeightedString.addVertex(vd.getElement());
		directedWeightedString.addVertex(ve.getElement());
		
		directedWeightedString.addEdge(va.getElement(), vb.getElement(),1.0);
		directedWeightedString.addEdge(vb.getElement(), vc.getElement(),1.0);
		directedWeightedString.removeEdge(vb.getElement(), vc.getElement());
		
		assertTrue(directedWeightedInt.isAdjacent(v0.getElement(), v1.getElement()));
		assertFalse(directedWeightedInt.isAdjacent(v1.getElement(), v2.getElement()));
		
		assertTrue(directedWeightedDouble.isAdjacent(v0d.getElement(), v1d.getElement()));
		assertFalse(directedWeightedDouble.isAdjacent(v1d.getElement(), v2d.getElement()));
		
		assertTrue(directedWeightedString.isAdjacent(va.getElement(), vb.getElement()));
		assertFalse(directedWeightedString.isAdjacent(vb.getElement(), vc.getElement()));

	}

}
