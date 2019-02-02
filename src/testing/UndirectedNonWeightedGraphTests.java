package testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import application.model.UndirectedNonWeightedGraph;
import application.model.Vertex;

class UndirectedNonWeightedGraphTests {

	@Test
	void addingVerticesToGraphTest() {
		
		UndirectedNonWeightedGraph<Integer> undirectedNonWeightedInt = new UndirectedNonWeightedGraph<Integer>();
		Vertex<Integer> v0 = new Vertex<Integer>(0);
		Vertex<Integer> v1 = new Vertex<Integer>(1);
		Vertex<Integer> v2 = new Vertex<Integer>(2);
		Vertex<Integer> v3 = new Vertex<Integer>(3);
		Vertex<Integer> v4 = new Vertex<Integer>(4);
		
		undirectedNonWeightedInt.addVertex(v0.getElement());
		undirectedNonWeightedInt.addVertex(v1.getElement());
		undirectedNonWeightedInt.addVertex(v2.getElement());
		undirectedNonWeightedInt.addVertex(v3.getElement());
		undirectedNonWeightedInt.addVertex(v4.getElement());
		
		UndirectedNonWeightedGraph<Double> undirectedNonWeightedDouble = new UndirectedNonWeightedGraph<Double>();
		Vertex<Double> v0d = new Vertex<Double>(0.0);
		Vertex<Double> v1d = new Vertex<Double>(1.0);
		Vertex<Double> v2d = new Vertex<Double>(2.0);
		Vertex<Double> v3d = new Vertex<Double>(3.0);
		Vertex<Double> v4d = new Vertex<Double>(4.0);
		
		undirectedNonWeightedDouble.addVertex(v0d.getElement());
		undirectedNonWeightedDouble.addVertex(v1d.getElement());
		undirectedNonWeightedDouble.addVertex(v2d.getElement());
		undirectedNonWeightedDouble.addVertex(v3d.getElement());
		undirectedNonWeightedDouble.addVertex(v4d.getElement());
		
		UndirectedNonWeightedGraph<String> undirectedNonWeightedString = new UndirectedNonWeightedGraph<String>();
		Vertex<String> va = new Vertex<String>("a");
		Vertex<String> vb = new Vertex<String>("b");
		Vertex<String> vc = new Vertex<String>("c");
		Vertex<String> vd = new Vertex<String>("d");
		Vertex<String> ve = new Vertex<String>("e");
		
		undirectedNonWeightedString.addVertex(va.getElement());
		undirectedNonWeightedString.addVertex(vb.getElement());
		undirectedNonWeightedString.addVertex(vc.getElement());
		undirectedNonWeightedString.addVertex(vd.getElement());
		undirectedNonWeightedString.addVertex(ve.getElement());
		
		assertEquals(5,undirectedNonWeightedInt.getAdjacencyList().size());
		assertEquals(5,undirectedNonWeightedDouble.getAdjacencyList().size());
		assertEquals(5,undirectedNonWeightedString.getAdjacencyList().size());
		
		
	}
	
	@Test
	void removingVerticesFromGraphTest() {
		
		UndirectedNonWeightedGraph<Integer> undirectedNonWeightedInt = new UndirectedNonWeightedGraph<Integer>();
		Vertex<Integer> v0 = new Vertex<Integer>(0);
		Vertex<Integer> v1 = new Vertex<Integer>(1);
		Vertex<Integer> v2 = new Vertex<Integer>(2);
		Vertex<Integer> v3 = new Vertex<Integer>(3);
		Vertex<Integer> v4 = new Vertex<Integer>(4);
		
		undirectedNonWeightedInt.addVertex(v0.getElement());
		undirectedNonWeightedInt.addVertex(v1.getElement());
		undirectedNonWeightedInt.addVertex(v2.getElement());
		undirectedNonWeightedInt.addVertex(v3.getElement());
		undirectedNonWeightedInt.addVertex(v4.getElement());
		
		undirectedNonWeightedInt.removeVertex(v4.getElement());
		
		UndirectedNonWeightedGraph<Double> undirectedNonWeightedDouble = new UndirectedNonWeightedGraph<Double>();
		Vertex<Double> v0d = new Vertex<Double>(0.0);
		Vertex<Double> v1d = new Vertex<Double>(1.0);
		Vertex<Double> v2d = new Vertex<Double>(2.0);
		Vertex<Double> v3d = new Vertex<Double>(3.0);
		Vertex<Double> v4d = new Vertex<Double>(4.0);
		
		undirectedNonWeightedDouble.addVertex(v0d.getElement());
		undirectedNonWeightedDouble.addVertex(v1d.getElement());
		undirectedNonWeightedDouble.addVertex(v2d.getElement());
		undirectedNonWeightedDouble.addVertex(v3d.getElement());
		undirectedNonWeightedDouble.addVertex(v4d.getElement());
		
		undirectedNonWeightedDouble.removeVertex(v0d.getElement());
		undirectedNonWeightedDouble.removeVertex(v3d.getElement());
		
		UndirectedNonWeightedGraph<String> undirectedNonWeightedString = new UndirectedNonWeightedGraph<String>();
		Vertex<String> va = new Vertex<String>("a");
		Vertex<String> vb = new Vertex<String>("b");
		Vertex<String> vc = new Vertex<String>("c");
		Vertex<String> vd = new Vertex<String>("d");
		Vertex<String> ve = new Vertex<String>("e");
		
		undirectedNonWeightedString.addVertex(va.getElement());
		undirectedNonWeightedString.addVertex(vb.getElement());
		undirectedNonWeightedString.addVertex(vc.getElement());
		undirectedNonWeightedString.addVertex(vd.getElement());
		undirectedNonWeightedString.addVertex(ve.getElement());
		
		undirectedNonWeightedString.removeVertex(va.getElement());
		undirectedNonWeightedString.removeVertex(ve.getElement());
		undirectedNonWeightedString.removeVertex(vc.getElement());
		
		assertEquals(4,undirectedNonWeightedInt.getAdjacencyList().size());
		assertEquals(3,undirectedNonWeightedDouble.getAdjacencyList().size());
		assertEquals(2,undirectedNonWeightedString.getAdjacencyList().size());
		
		assertFalse(undirectedNonWeightedInt.containsVertex(v4.getElement()));
		
		assertFalse(undirectedNonWeightedDouble.containsVertex(v0d.getElement()));
		assertFalse(undirectedNonWeightedDouble.containsVertex(v3d.getElement()));
		
		assertFalse(undirectedNonWeightedString.containsVertex(va.getElement()));
		assertFalse(undirectedNonWeightedString.containsVertex(ve.getElement()));
		assertFalse(undirectedNonWeightedString.containsVertex(vc.getElement()));
		
		
	}
	
	@Test
	void addingEdgesToGraphTest() {
		
		UndirectedNonWeightedGraph<Integer> undirectedNonWeightedInt = new UndirectedNonWeightedGraph<Integer>();
		Vertex<Integer> v0 = new Vertex<Integer>(0);
		Vertex<Integer> v1 = new Vertex<Integer>(1);
		Vertex<Integer> v2 = new Vertex<Integer>(2);
		Vertex<Integer> v3 = new Vertex<Integer>(3);
		Vertex<Integer> v4 = new Vertex<Integer>(4);
		
		undirectedNonWeightedInt.addVertex(v0.getElement());
		undirectedNonWeightedInt.addVertex(v1.getElement());
		undirectedNonWeightedInt.addVertex(v2.getElement());
		undirectedNonWeightedInt.addVertex(v3.getElement());
		undirectedNonWeightedInt.addVertex(v4.getElement());
		
		undirectedNonWeightedInt.addEdge(v0.getElement(), v1.getElement());
		undirectedNonWeightedInt.addEdge(v1.getElement(), v2.getElement());
		
		UndirectedNonWeightedGraph<Double> undirectedNonWeightedDouble = new UndirectedNonWeightedGraph<Double>();
		Vertex<Double> v0d = new Vertex<Double>(0.0);
		Vertex<Double> v1d = new Vertex<Double>(1.0);
		Vertex<Double> v2d = new Vertex<Double>(2.0);
		Vertex<Double> v3d = new Vertex<Double>(3.0);
		Vertex<Double> v4d = new Vertex<Double>(4.0);
		
		undirectedNonWeightedDouble.addVertex(v0d.getElement());
		undirectedNonWeightedDouble.addVertex(v1d.getElement());
		undirectedNonWeightedDouble.addVertex(v2d.getElement());
		undirectedNonWeightedDouble.addVertex(v3d.getElement());
		undirectedNonWeightedDouble.addVertex(v4d.getElement());
		
		undirectedNonWeightedDouble.addEdge(v0d.getElement(), v1d.getElement());
		undirectedNonWeightedDouble.addEdge(v1d.getElement(), v2d.getElement());
		
		UndirectedNonWeightedGraph<String> undirectedNonWeightedString = new UndirectedNonWeightedGraph<String>();
		Vertex<String> va = new Vertex<String>("a");
		Vertex<String> vb = new Vertex<String>("b");
		Vertex<String> vc = new Vertex<String>("c");
		Vertex<String> vd = new Vertex<String>("d");
		Vertex<String> ve = new Vertex<String>("e");
		
		undirectedNonWeightedString.addVertex(va.getElement());
		undirectedNonWeightedString.addVertex(vb.getElement());
		undirectedNonWeightedString.addVertex(vc.getElement());
		undirectedNonWeightedString.addVertex(vd.getElement());
		undirectedNonWeightedString.addVertex(ve.getElement());
		
		undirectedNonWeightedString.addEdge(va.getElement(), vb.getElement());
		undirectedNonWeightedString.addEdge(vb.getElement(), vc.getElement());
		
		assertTrue(undirectedNonWeightedInt.isAdjacent(v0.getElement(), v1.getElement()));
		assertTrue(undirectedNonWeightedInt.isAdjacent(v1.getElement(), v2.getElement()));
		
		assertTrue(undirectedNonWeightedDouble.isAdjacent(v0d.getElement(), v1d.getElement()));
		assertTrue(undirectedNonWeightedDouble.isAdjacent(v1d.getElement(), v2d.getElement()));
		
		assertTrue(undirectedNonWeightedString.isAdjacent(va.getElement(), vb.getElement()));
		assertTrue(undirectedNonWeightedString.isAdjacent(vb.getElement(), vc.getElement()));

	}
	
	@Test
	void removingEdgesToGraphTest() {
		
		UndirectedNonWeightedGraph<Integer> undirectedNonWeightedInt = new UndirectedNonWeightedGraph<Integer>();
		Vertex<Integer> v0 = new Vertex<Integer>(0);
		Vertex<Integer> v1 = new Vertex<Integer>(1);
		Vertex<Integer> v2 = new Vertex<Integer>(2);
		Vertex<Integer> v3 = new Vertex<Integer>(3);
		Vertex<Integer> v4 = new Vertex<Integer>(4);
		
		undirectedNonWeightedInt.addVertex(v0.getElement());
		undirectedNonWeightedInt.addVertex(v1.getElement());
		undirectedNonWeightedInt.addVertex(v2.getElement());
		undirectedNonWeightedInt.addVertex(v3.getElement());
		undirectedNonWeightedInt.addVertex(v4.getElement());
		
		undirectedNonWeightedInt.addEdge(v0.getElement(), v1.getElement());
		undirectedNonWeightedInt.addEdge(v1.getElement(), v2.getElement());
		undirectedNonWeightedInt.removeEdge(v1.getElement(), v2.getElement());
		
		UndirectedNonWeightedGraph<Double> undirectedNonWeightedDouble = new UndirectedNonWeightedGraph<Double>();
		Vertex<Double> v0d = new Vertex<Double>(0.0);
		Vertex<Double> v1d = new Vertex<Double>(1.0);
		Vertex<Double> v2d = new Vertex<Double>(2.0);
		Vertex<Double> v3d = new Vertex<Double>(3.0);
		Vertex<Double> v4d = new Vertex<Double>(4.0);
		
		undirectedNonWeightedDouble.addVertex(v0d.getElement());
		undirectedNonWeightedDouble.addVertex(v1d.getElement());
		undirectedNonWeightedDouble.addVertex(v2d.getElement());
		undirectedNonWeightedDouble.addVertex(v3d.getElement());
		undirectedNonWeightedDouble.addVertex(v4d.getElement());
		
		undirectedNonWeightedDouble.addEdge(v0d.getElement(), v1d.getElement());
		undirectedNonWeightedDouble.addEdge(v1d.getElement(), v2d.getElement());
		undirectedNonWeightedDouble.removeEdge(v1d.getElement(), v2d.getElement());
		
		UndirectedNonWeightedGraph<String> undirectedNonWeightedString = new UndirectedNonWeightedGraph<String>();
		Vertex<String> va = new Vertex<String>("a");
		Vertex<String> vb = new Vertex<String>("b");
		Vertex<String> vc = new Vertex<String>("c");
		Vertex<String> vd = new Vertex<String>("d");
		Vertex<String> ve = new Vertex<String>("e");
		
		undirectedNonWeightedString.addVertex(va.getElement());
		undirectedNonWeightedString.addVertex(vb.getElement());
		undirectedNonWeightedString.addVertex(vc.getElement());
		undirectedNonWeightedString.addVertex(vd.getElement());
		undirectedNonWeightedString.addVertex(ve.getElement());
		
		undirectedNonWeightedString.addEdge(va.getElement(), vb.getElement());
		undirectedNonWeightedString.addEdge(vb.getElement(), vc.getElement());
		undirectedNonWeightedString.removeEdge(vb.getElement(), vc.getElement());
		
		assertTrue(undirectedNonWeightedInt.isAdjacent(v0.getElement(), v1.getElement()));
		assertFalse(undirectedNonWeightedInt.isAdjacent(v1.getElement(), v2.getElement()));
		
		assertTrue(undirectedNonWeightedDouble.isAdjacent(v0d.getElement(), v1d.getElement()));
		assertFalse(undirectedNonWeightedDouble.isAdjacent(v1d.getElement(), v2d.getElement()));
		
		assertTrue(undirectedNonWeightedString.isAdjacent(va.getElement(), vb.getElement()));
		assertFalse(undirectedNonWeightedString.isAdjacent(vb.getElement(), vc.getElement()));

	}

}
