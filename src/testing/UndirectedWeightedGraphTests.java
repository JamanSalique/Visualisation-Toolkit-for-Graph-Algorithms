package testing;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.util.Pair;
import application.model.UndirectedWeightedGraph;
import application.model.Vertex;

class UndirectedWeightedGraphTests {

	/**
	 * This function is to test whether adding vertices to an undirectedWeightedGraph for all data types
	 * works. I add vertices to the graphs and then test to see whether the size of the adjacency list is equal to
	 * the amount of vertices I added. 
	 */
	@Test
	void addingVerticesToGraphTest() {
		
		UndirectedWeightedGraph<Integer> undirectedWeightedInt = new UndirectedWeightedGraph<Integer>();
		Vertex<Integer> v0 = new Vertex<Integer>(0);
		Vertex<Integer> v1 = new Vertex<Integer>(1);
		Vertex<Integer> v2 = new Vertex<Integer>(2);
		Vertex<Integer> v3 = new Vertex<Integer>(3);
		Vertex<Integer> v4 = new Vertex<Integer>(4);
		
		undirectedWeightedInt.addVertex(v0.getElement());
		undirectedWeightedInt.addVertex(v1.getElement());
		undirectedWeightedInt.addVertex(v2.getElement());
		undirectedWeightedInt.addVertex(v3.getElement());
		undirectedWeightedInt.addVertex(v4.getElement());
		
		UndirectedWeightedGraph<Double> undirectedWeightedDouble = new UndirectedWeightedGraph<Double>();
		Vertex<Double> v0d = new Vertex<Double>(0.0);
		Vertex<Double> v1d = new Vertex<Double>(1.0);
		Vertex<Double> v2d = new Vertex<Double>(2.0);
		Vertex<Double> v3d = new Vertex<Double>(3.0);
		Vertex<Double> v4d = new Vertex<Double>(4.0);
		
		undirectedWeightedDouble.addVertex(v0d.getElement());
		undirectedWeightedDouble.addVertex(v1d.getElement());
		undirectedWeightedDouble.addVertex(v2d.getElement());
		undirectedWeightedDouble.addVertex(v3d.getElement());
		undirectedWeightedDouble.addVertex(v4d.getElement());
		
		UndirectedWeightedGraph<String> undirectedWeightedString = new UndirectedWeightedGraph<String>();
		Vertex<String> va = new Vertex<String>("a");
		Vertex<String> vb = new Vertex<String>("b");
		Vertex<String> vc = new Vertex<String>("c");
		Vertex<String> vd = new Vertex<String>("d");
		Vertex<String> ve = new Vertex<String>("e");
		
		undirectedWeightedString.addVertex(va.getElement());
		undirectedWeightedString.addVertex(vb.getElement());
		undirectedWeightedString.addVertex(vc.getElement());
		undirectedWeightedString.addVertex(vd.getElement());
		undirectedWeightedString.addVertex(ve.getElement());
		
		assertEquals(5,undirectedWeightedInt.getAdjacencyList().size());
		assertEquals(5,undirectedWeightedDouble.getAdjacencyList().size());
		assertEquals(5,undirectedWeightedString.getAdjacencyList().size());
		
		
	}
	
	/**
	 * This function is to test whether removing vertices from an undirectedWeightedGraph for all data types
	 * works. I first add some vertices to the graphs and then remove a single vertex and then test to see if the
	 * adjacency list contains the vertex I removed (which should be false).
	 */
	@Test
	void removingVerticesFromGraphTest() {
		
		UndirectedWeightedGraph<Integer> undirectedWeightedInt = new UndirectedWeightedGraph<Integer>();
		Vertex<Integer> v0 = new Vertex<Integer>(0);
		Vertex<Integer> v1 = new Vertex<Integer>(1);
		Vertex<Integer> v2 = new Vertex<Integer>(2);
		Vertex<Integer> v3 = new Vertex<Integer>(3);
		Vertex<Integer> v4 = new Vertex<Integer>(4);
		
		undirectedWeightedInt.addVertex(v0.getElement());
		undirectedWeightedInt.addVertex(v1.getElement());
		undirectedWeightedInt.addVertex(v2.getElement());
		undirectedWeightedInt.addVertex(v3.getElement());
		undirectedWeightedInt.addVertex(v4.getElement());
		
		undirectedWeightedInt.removeVertex(v4.getElement());
		
		UndirectedWeightedGraph<Double> undirectedWeightedDouble = new UndirectedWeightedGraph<Double>();
		Vertex<Double> v0d = new Vertex<Double>(0.0);
		Vertex<Double> v1d = new Vertex<Double>(1.0);
		Vertex<Double> v2d = new Vertex<Double>(2.0);
		Vertex<Double> v3d = new Vertex<Double>(3.0);
		Vertex<Double> v4d = new Vertex<Double>(4.0);
		
		undirectedWeightedDouble.addVertex(v0d.getElement());
		undirectedWeightedDouble.addVertex(v1d.getElement());
		undirectedWeightedDouble.addVertex(v2d.getElement());
		undirectedWeightedDouble.addVertex(v3d.getElement());
		undirectedWeightedDouble.addVertex(v4d.getElement());
		
		undirectedWeightedDouble.removeVertex(v0d.getElement());
		undirectedWeightedDouble.removeVertex(v3d.getElement());
		
		UndirectedWeightedGraph<String> undirectedWeightedString = new UndirectedWeightedGraph<String>();
		Vertex<String> va = new Vertex<String>("a");
		Vertex<String> vb = new Vertex<String>("b");
		Vertex<String> vc = new Vertex<String>("c");
		Vertex<String> vd = new Vertex<String>("d");
		Vertex<String> ve = new Vertex<String>("e");
		
		undirectedWeightedString.addVertex(va.getElement());
		undirectedWeightedString.addVertex(vb.getElement());
		undirectedWeightedString.addVertex(vc.getElement());
		undirectedWeightedString.addVertex(vd.getElement());
		undirectedWeightedString.addVertex(ve.getElement());
		
		undirectedWeightedString.removeVertex(va.getElement());
		undirectedWeightedString.removeVertex(ve.getElement());
		undirectedWeightedString.removeVertex(vc.getElement());
		
		assertFalse(undirectedWeightedInt.containsVertex(v4.getElement()));
		
		assertFalse(undirectedWeightedDouble.containsVertex(v0d.getElement()));
		assertFalse(undirectedWeightedDouble.containsVertex(v3d.getElement()));
		
		assertFalse(undirectedWeightedString.containsVertex(va.getElement()));
		assertFalse(undirectedWeightedString.containsVertex(ve.getElement()));
		assertFalse(undirectedWeightedString.containsVertex(vc.getElement()));
		
		
	}
	
	/**
	 * This function is to test whether adding edges to an undirectedWeightedGraph for all data types
	 * works. I add vertices to the graphs and then add edges between some of the vertices. I then test
	 * to see if specific vertices are adjacent (ie are connected by an edge).
	 */
	@Test
	void addingEdgesToGraphTest() {
		
		UndirectedWeightedGraph<Integer> undirectedWeightedInt = new UndirectedWeightedGraph<Integer>();
		Vertex<Integer> v0 = new Vertex<Integer>(0);
		Vertex<Integer> v1 = new Vertex<Integer>(1);
		Vertex<Integer> v2 = new Vertex<Integer>(2);
		Vertex<Integer> v3 = new Vertex<Integer>(3);
		Vertex<Integer> v4 = new Vertex<Integer>(4);
		
		undirectedWeightedInt.addVertex(v0.getElement());
		undirectedWeightedInt.addVertex(v1.getElement());
		undirectedWeightedInt.addVertex(v2.getElement());
		undirectedWeightedInt.addVertex(v3.getElement());
		undirectedWeightedInt.addVertex(v4.getElement());
		
		undirectedWeightedInt.addEdge(v0.getElement(), v1.getElement(),1.0);
		undirectedWeightedInt.addEdge(v1.getElement(), v2.getElement(),1.0);
		
		UndirectedWeightedGraph<Double> undirectedWeightedDouble = new UndirectedWeightedGraph<Double>();
		Vertex<Double> v0d = new Vertex<Double>(0.0);
		Vertex<Double> v1d = new Vertex<Double>(1.0);
		Vertex<Double> v2d = new Vertex<Double>(2.0);
		Vertex<Double> v3d = new Vertex<Double>(3.0);
		Vertex<Double> v4d = new Vertex<Double>(4.0);
		
		undirectedWeightedDouble.addVertex(v0d.getElement());
		undirectedWeightedDouble.addVertex(v1d.getElement());
		undirectedWeightedDouble.addVertex(v2d.getElement());
		undirectedWeightedDouble.addVertex(v3d.getElement());
		undirectedWeightedDouble.addVertex(v4d.getElement());
		
		undirectedWeightedDouble.addEdge(v0d.getElement(), v1d.getElement(),1.0);
		undirectedWeightedDouble.addEdge(v1d.getElement(), v2d.getElement(),1.0);
		
		UndirectedWeightedGraph<String> undirectedWeightedString = new UndirectedWeightedGraph<String>();
		Vertex<String> va = new Vertex<String>("a");
		Vertex<String> vb = new Vertex<String>("b");
		Vertex<String> vc = new Vertex<String>("c");
		Vertex<String> vd = new Vertex<String>("d");
		Vertex<String> ve = new Vertex<String>("e");
		
		undirectedWeightedString.addVertex(va.getElement());
		undirectedWeightedString.addVertex(vb.getElement());
		undirectedWeightedString.addVertex(vc.getElement());
		undirectedWeightedString.addVertex(vd.getElement());
		undirectedWeightedString.addVertex(ve.getElement());
		
		undirectedWeightedString.addEdge(va.getElement(), vb.getElement(),1.0);
		undirectedWeightedString.addEdge(vb.getElement(), vc.getElement(),1.0);
		
		assertTrue(undirectedWeightedInt.isAdjacent(v0.getElement(), v1.getElement()));
		assertTrue(undirectedWeightedInt.isAdjacent(v1.getElement(), v2.getElement()));
		
		assertTrue(undirectedWeightedDouble.isAdjacent(v0d.getElement(), v1d.getElement()));
		assertTrue(undirectedWeightedDouble.isAdjacent(v1d.getElement(), v2d.getElement()));
		
		assertTrue(undirectedWeightedString.isAdjacent(va.getElement(), vb.getElement()));
		assertTrue(undirectedWeightedString.isAdjacent(vb.getElement(), vc.getElement()));

	}
	
	/**
	 * This function is to test whether removing edges from an undirectedWeightedGraph for all data types
	 * works. I remove vertices to the graphs and add edges between some of the vertices. I then remove an
	 * edge and test to see if the vertices are adjacent to each other (which should return false).
	 */
	@Test
	void removingEdgesToGraphTest() {
		
		UndirectedWeightedGraph<Integer> undirectedWeightedInt = new UndirectedWeightedGraph<Integer>();
		Vertex<Integer> v0 = new Vertex<Integer>(0);
		Vertex<Integer> v1 = new Vertex<Integer>(1);
		Vertex<Integer> v2 = new Vertex<Integer>(2);
		Vertex<Integer> v3 = new Vertex<Integer>(3);
		Vertex<Integer> v4 = new Vertex<Integer>(4);
		
		undirectedWeightedInt.addVertex(v0.getElement());
		undirectedWeightedInt.addVertex(v1.getElement());
		undirectedWeightedInt.addVertex(v2.getElement());
		undirectedWeightedInt.addVertex(v3.getElement());
		undirectedWeightedInt.addVertex(v4.getElement());
		
		undirectedWeightedInt.addEdge(v0.getElement(), v1.getElement(),1.0);
		undirectedWeightedInt.addEdge(v1.getElement(), v2.getElement(),1.0);
		undirectedWeightedInt.removeEdge(v1.getElement(), v2.getElement());
		
		UndirectedWeightedGraph<Double> undirectedWeightedDouble = new UndirectedWeightedGraph<Double>();
		Vertex<Double> v0d = new Vertex<Double>(0.0);
		Vertex<Double> v1d = new Vertex<Double>(1.0);
		Vertex<Double> v2d = new Vertex<Double>(2.0);
		Vertex<Double> v3d = new Vertex<Double>(3.0);
		Vertex<Double> v4d = new Vertex<Double>(4.0);
		
		undirectedWeightedDouble.addVertex(v0d.getElement());
		undirectedWeightedDouble.addVertex(v1d.getElement());
		undirectedWeightedDouble.addVertex(v2d.getElement());
		undirectedWeightedDouble.addVertex(v3d.getElement());
		undirectedWeightedDouble.addVertex(v4d.getElement());
		
		undirectedWeightedDouble.addEdge(v0d.getElement(), v1d.getElement(),1.0);
		undirectedWeightedDouble.addEdge(v1d.getElement(), v2d.getElement(),1.0);
		undirectedWeightedDouble.removeEdge(v1d.getElement(), v2d.getElement());
		
		UndirectedWeightedGraph<String> undirectedWeightedString = new UndirectedWeightedGraph<String>();
		Vertex<String> va = new Vertex<String>("a");
		Vertex<String> vb = new Vertex<String>("b");
		Vertex<String> vc = new Vertex<String>("c");
		Vertex<String> vd = new Vertex<String>("d");
		Vertex<String> ve = new Vertex<String>("e");
		
		undirectedWeightedString.addVertex(va.getElement());
		undirectedWeightedString.addVertex(vb.getElement());
		undirectedWeightedString.addVertex(vc.getElement());
		undirectedWeightedString.addVertex(vd.getElement());
		undirectedWeightedString.addVertex(ve.getElement());
		
		undirectedWeightedString.addEdge(va.getElement(), vb.getElement(),1.0);
		undirectedWeightedString.addEdge(vb.getElement(), vc.getElement(),1.0);
		undirectedWeightedString.removeEdge(vb.getElement(), vc.getElement());
		
		assertTrue(undirectedWeightedInt.isAdjacent(v0.getElement(), v1.getElement()));
		assertFalse(undirectedWeightedInt.isAdjacent(v1.getElement(), v2.getElement()));
		
		assertTrue(undirectedWeightedDouble.isAdjacent(v0d.getElement(), v1d.getElement()));
		assertFalse(undirectedWeightedDouble.isAdjacent(v1d.getElement(), v2d.getElement()));
		
		assertTrue(undirectedWeightedString.isAdjacent(va.getElement(), vb.getElement()));
		assertFalse(undirectedWeightedString.isAdjacent(vb.getElement(), vc.getElement()));

	}
	
	/**
	 * This function is to test the containsVertex function for undirected weighted graphs.
	 */
	@Test
	void containVertexTest(){
		
		UndirectedWeightedGraph<Integer> undirectedWeightedInt = new UndirectedWeightedGraph<Integer>();
		Vertex<Integer> v0 = new Vertex<Integer>(0);
		Vertex<Integer> v1 = new Vertex<Integer>(1);
		Vertex<Integer> v2 = new Vertex<Integer>(2);
		Vertex<Integer> v3 = new Vertex<Integer>(3);
		Vertex<Integer> v4 = new Vertex<Integer>(4);
		
		undirectedWeightedInt.addVertex(v0.getElement());
		undirectedWeightedInt.addVertex(v1.getElement());
		undirectedWeightedInt.addVertex(v2.getElement());
		undirectedWeightedInt.addVertex(v3.getElement());
		undirectedWeightedInt.addVertex(v4.getElement());
		
		undirectedWeightedInt.addEdge(v0.getElement(), v1.getElement(),1.0);
		undirectedWeightedInt.addEdge(v1.getElement(), v2.getElement(),1.0);
		undirectedWeightedInt.removeEdge(v1.getElement(), v2.getElement());
		
		UndirectedWeightedGraph<Double> undirectedWeightedDouble = new UndirectedWeightedGraph<Double>();
		Vertex<Double> v0d = new Vertex<Double>(0.0);
		Vertex<Double> v1d = new Vertex<Double>(1.0);
		Vertex<Double> v2d = new Vertex<Double>(2.0);
		Vertex<Double> v3d = new Vertex<Double>(3.0);
		Vertex<Double> v4d = new Vertex<Double>(4.0);
		
		undirectedWeightedDouble.addVertex(v0d.getElement());
		undirectedWeightedDouble.addVertex(v1d.getElement());
		undirectedWeightedDouble.addVertex(v2d.getElement());
		undirectedWeightedDouble.addVertex(v3d.getElement());
		undirectedWeightedDouble.addVertex(v4d.getElement());
		
		undirectedWeightedDouble.addEdge(v0d.getElement(), v1d.getElement(),1.0);
		undirectedWeightedDouble.addEdge(v1d.getElement(), v2d.getElement(),1.0);
		undirectedWeightedDouble.removeEdge(v1d.getElement(), v2d.getElement());
		
		UndirectedWeightedGraph<String> undirectedWeightedString = new UndirectedWeightedGraph<String>();
		Vertex<String> va = new Vertex<String>("a");
		Vertex<String> vb = new Vertex<String>("b");
		Vertex<String> vc = new Vertex<String>("c");
		Vertex<String> vd = new Vertex<String>("d");
		Vertex<String> ve = new Vertex<String>("e");
		
		undirectedWeightedString.addVertex(va.getElement());
		undirectedWeightedString.addVertex(vb.getElement());
		undirectedWeightedString.addVertex(vc.getElement());
		undirectedWeightedString.addVertex(vd.getElement());
		undirectedWeightedString.addVertex(ve.getElement());
		
		undirectedWeightedString.addEdge(va.getElement(), vb.getElement(),1.0);
		undirectedWeightedString.addEdge(vb.getElement(), vc.getElement(),1.0);
		undirectedWeightedString.removeEdge(vb.getElement(), vc.getElement());
		
		assertTrue(undirectedWeightedInt.containsVertex(0));
		assertFalse(undirectedWeightedInt.containsVertex(10));
		
		assertTrue(undirectedWeightedDouble.containsVertex(0.0));
		assertFalse(undirectedWeightedDouble.containsVertex(10.0));
		
		assertTrue(undirectedWeightedString.containsVertex("a"));
		assertFalse(undirectedWeightedString.containsVertex("z"));
		
	}
	
	/**
	 * This function is to test the clearGraph function for undirected weighted graphs.
	 */
	@Test
	void clearGraphTest() {
		
		UndirectedWeightedGraph<Integer> undirectedWeightedInt = new UndirectedWeightedGraph<Integer>();
		Vertex<Integer> v0 = new Vertex<Integer>(0);
		Vertex<Integer> v1 = new Vertex<Integer>(1);
		Vertex<Integer> v2 = new Vertex<Integer>(2);
		Vertex<Integer> v3 = new Vertex<Integer>(3);
		Vertex<Integer> v4 = new Vertex<Integer>(4);
		
		undirectedWeightedInt.addVertex(v0.getElement());
		undirectedWeightedInt.addVertex(v1.getElement());
		undirectedWeightedInt.addVertex(v2.getElement());
		undirectedWeightedInt.addVertex(v3.getElement());
		undirectedWeightedInt.addVertex(v4.getElement());
		
		undirectedWeightedInt.addEdge(v0.getElement(), v1.getElement(),1.0);
		undirectedWeightedInt.addEdge(v1.getElement(), v2.getElement(),1.0);
		undirectedWeightedInt.removeEdge(v1.getElement(), v2.getElement());
		
		undirectedWeightedInt.clearGraph();
		
		UndirectedWeightedGraph<Double> undirectedWeightedDouble = new UndirectedWeightedGraph<Double>();
		Vertex<Double> v0d = new Vertex<Double>(0.0);
		Vertex<Double> v1d = new Vertex<Double>(1.0);
		Vertex<Double> v2d = new Vertex<Double>(2.0);
		Vertex<Double> v3d = new Vertex<Double>(3.0);
		Vertex<Double> v4d = new Vertex<Double>(4.0);
		
		undirectedWeightedDouble.addVertex(v0d.getElement());
		undirectedWeightedDouble.addVertex(v1d.getElement());
		undirectedWeightedDouble.addVertex(v2d.getElement());
		undirectedWeightedDouble.addVertex(v3d.getElement());
		undirectedWeightedDouble.addVertex(v4d.getElement());
		
		undirectedWeightedDouble.addEdge(v0d.getElement(), v1d.getElement(),1.0);
		undirectedWeightedDouble.addEdge(v1d.getElement(), v2d.getElement(),1.0);
		undirectedWeightedDouble.removeEdge(v1d.getElement(), v2d.getElement());
		
		undirectedWeightedDouble.clearGraph();
		
		UndirectedWeightedGraph<String> undirectedWeightedString = new UndirectedWeightedGraph<String>();
		Vertex<String> va = new Vertex<String>("a");
		Vertex<String> vb = new Vertex<String>("b");
		Vertex<String> vc = new Vertex<String>("c");
		Vertex<String> vd = new Vertex<String>("d");
		Vertex<String> ve = new Vertex<String>("e");
		
		undirectedWeightedString.addVertex(va.getElement());
		undirectedWeightedString.addVertex(vb.getElement());
		undirectedWeightedString.addVertex(vc.getElement());
		undirectedWeightedString.addVertex(vd.getElement());
		undirectedWeightedString.addVertex(ve.getElement());
		
		undirectedWeightedString.addEdge(va.getElement(), vb.getElement(),1.0);
		undirectedWeightedString.addEdge(vb.getElement(), vc.getElement(),1.0);
		undirectedWeightedString.removeEdge(vb.getElement(), vc.getElement());
		
		undirectedWeightedString.clearGraph();
		
		assertTrue(undirectedWeightedInt.getAdjacencyList().size() == 0);
		
		assertTrue(undirectedWeightedDouble.getAdjacencyList().size() == 0);
		
		assertTrue(undirectedWeightedString.getAdjacencyList().size() == 0);
		
	}
	
	/**
	 * This function is to test the getNeighbours function for vertices in undirected weighted graphs.
	 */
	@Test
	void vertexNeighboursTest() {
		
		UndirectedWeightedGraph<Integer> undirectedWeightedInt = new UndirectedWeightedGraph<Integer>();
		Vertex<Integer> v0 = new Vertex<Integer>(0);
		Vertex<Integer> v1 = new Vertex<Integer>(1);
		Vertex<Integer> v2 = new Vertex<Integer>(2);
		Vertex<Integer> v3 = new Vertex<Integer>(3);
		Vertex<Integer> v4 = new Vertex<Integer>(4);
		
		undirectedWeightedInt.addVertex(v0.getElement());
		undirectedWeightedInt.addVertex(v1.getElement());
		undirectedWeightedInt.addVertex(v2.getElement());
		undirectedWeightedInt.addVertex(v3.getElement());
		undirectedWeightedInt.addVertex(v4.getElement());
		
		undirectedWeightedInt.addEdge(v0.getElement(), v1.getElement(),1.0);
		undirectedWeightedInt.addEdge(v0.getElement(), v2.getElement(),1.0);
		undirectedWeightedInt.addEdge(v0.getElement(), v4.getElement(),1.0);
		undirectedWeightedInt.addEdge(v1.getElement(), v2.getElement(),1.0);
		undirectedWeightedInt.removeEdge(v1.getElement(), v2.getElement());
		
		UndirectedWeightedGraph<Double> undirectedWeightedDouble = new UndirectedWeightedGraph<Double>();
		Vertex<Double> v0d = new Vertex<Double>(0.0);
		Vertex<Double> v1d = new Vertex<Double>(1.0);
		Vertex<Double> v2d = new Vertex<Double>(2.0);
		Vertex<Double> v3d = new Vertex<Double>(3.0);
		Vertex<Double> v4d = new Vertex<Double>(4.0);
		
		undirectedWeightedDouble.addVertex(v0d.getElement());
		undirectedWeightedDouble.addVertex(v1d.getElement());
		undirectedWeightedDouble.addVertex(v2d.getElement());
		undirectedWeightedDouble.addVertex(v3d.getElement());
		undirectedWeightedDouble.addVertex(v4d.getElement());
		
		undirectedWeightedDouble.addEdge(v0d.getElement(), v1d.getElement(),1.0);
		undirectedWeightedDouble.addEdge(v0d.getElement(), v2d.getElement(),1.0);
		undirectedWeightedDouble.addEdge(v0d.getElement(), v4d.getElement(),1.0);
		undirectedWeightedDouble.addEdge(v1d.getElement(), v2d.getElement(),1.0);
		undirectedWeightedDouble.removeEdge(v1d.getElement(), v2d.getElement());
		
		UndirectedWeightedGraph<String> undirectedWeightedString = new UndirectedWeightedGraph<String>();
		Vertex<String> va = new Vertex<String>("a");
		Vertex<String> vb = new Vertex<String>("b");
		Vertex<String> vc = new Vertex<String>("c");
		Vertex<String> vd = new Vertex<String>("d");
		Vertex<String> ve = new Vertex<String>("e");
		
		undirectedWeightedString.addVertex(va.getElement());
		undirectedWeightedString.addVertex(vb.getElement());
		undirectedWeightedString.addVertex(vc.getElement());
		undirectedWeightedString.addVertex(vd.getElement());
		undirectedWeightedString.addVertex(ve.getElement());
		
		undirectedWeightedString.addEdge(va.getElement(), vb.getElement(),1.0);
		undirectedWeightedString.addEdge(va.getElement(), vc.getElement(),1.0);
		undirectedWeightedString.addEdge(va.getElement(), ve.getElement(),1.0);
		undirectedWeightedString.addEdge(vb.getElement(), vc.getElement(),1.0);
		undirectedWeightedString.removeEdge(vb.getElement(), vc.getElement());

		List<Pair<Vertex<Integer>,Double>> actual1 = new ArrayList<>();
		List<Pair<Vertex<Double>,Double>> actual2 = new ArrayList<>();
		List<Pair<Vertex<String>,Double>> actual3 = new ArrayList<>();
		
		undirectedWeightedInt.getNeighbours(0).iterator().forEachRemaining(actual1::add);
		undirectedWeightedDouble.getNeighbours(0.0).iterator().forEachRemaining(actual2::add);
		undirectedWeightedString.getNeighbours("a").iterator().forEachRemaining(actual3::add);
		
		Pair<Vertex<Integer>,Double> intPair1 = new Pair<>(v1,1.0);
		Pair<Vertex<Integer>,Double> intPair2 = new Pair<>(v1,1.0);
		Pair<Vertex<Integer>,Double> intPair3 = new Pair<>(v1,1.0);
		
		Pair<Vertex<Double>,Double> doublePair1 = new Pair<>(v1d,1.0);
		Pair<Vertex<Double>,Double> doublePair2 = new Pair<>(v2d,1.0);
		Pair<Vertex<Double>,Double> doublePair3 = new Pair<>(v4d,1.0);
		
		Pair<Vertex<String>,Double> stringPair1 = new Pair<>(vb,1.0);
		Pair<Vertex<String>,Double> stringPair2 = new Pair<>(vc,1.0);
		Pair<Vertex<String>,Double> stringPair3 = new Pair<>(ve,1.0);
		
		 assertThat(actual1, hasItems(intPair1,intPair2,intPair3));
		 assertThat(actual2, hasItems(doublePair1,doublePair2,doublePair3));
		 assertThat(actual3, hasItems(stringPair1,stringPair2,stringPair3));

	}

}
