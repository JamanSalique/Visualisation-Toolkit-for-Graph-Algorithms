package testing;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import application.model.DirectedWeightedGraph;
import application.model.DirectedWeightedGraph;
import application.model.DirectedWeightedGraph;
import application.model.Vertex;
import javafx.util.Pair;

class DirectedWeightedGraphTests {

	/**
	 * This function is to test whether adding vertices to an DirectedWeightedGraph for all data types
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
	 * This function is to test whether removing vertices from an DirectedWeightedGraph for all data types
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
	 * This function is to test whether adding edges to an DirectedWeightedGraph for all data types
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
	 * This function is to test whether removing edges from an DirectedWeightedGraph for all data types
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
	
	/**
	 * This function is to test the containsVertex function for directed weighted graphs.
	 */
	@Test
	void containVertexTest(){
		
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
		
		assertTrue(directedWeightedInt.containsVertex(0));
		assertFalse(directedWeightedInt.containsVertex(10));
		
		assertTrue(directedWeightedDouble.containsVertex(0.0));
		assertFalse(directedWeightedDouble.containsVertex(10.0));
		
		assertTrue(directedWeightedString.containsVertex("a"));
		assertFalse(directedWeightedString.containsVertex("z"));
		
	}
	
	/**
	 * This function is to test the clearGraph function for directed weighted graphs.
	 */
	@Test
	void clearGraphTest() {
		
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
		
		directedWeightedInt.clearGraph();
		
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
		
		directedWeightedDouble.clearGraph();
		
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
		
		directedWeightedString.clearGraph();
		
		assertTrue(directedWeightedInt.getAdjacencyList().size() == 0);
		
		assertTrue(directedWeightedDouble.getAdjacencyList().size() == 0);
		
		assertTrue(directedWeightedString.getAdjacencyList().size() == 0);
		
	}
	
	/**
	 * This function is to test the getNeighbours function for vertices in directed weighted graphs.
	 */
	@Test
	void vertexNeighboursTest() {
		
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
		directedWeightedInt.addEdge(v0.getElement(), v2.getElement(),1.0);
		directedWeightedInt.addEdge(v0.getElement(), v4.getElement(),1.0);
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
		directedWeightedDouble.addEdge(v0d.getElement(), v2d.getElement(),1.0);
		directedWeightedDouble.addEdge(v0d.getElement(), v4d.getElement(),1.0);
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
		directedWeightedString.addEdge(va.getElement(), vc.getElement(),1.0);
		directedWeightedString.addEdge(va.getElement(), ve.getElement(),1.0);
		directedWeightedString.addEdge(vb.getElement(), vc.getElement(),1.0);
		directedWeightedString.removeEdge(vb.getElement(), vc.getElement());

		List<Pair<Vertex<Integer>,Double>> actual1 = new ArrayList<>();
		List<Pair<Vertex<Double>,Double>> actual2 = new ArrayList<>();
		List<Pair<Vertex<String>,Double>> actual3 = new ArrayList<>();
		
		directedWeightedInt.getNeighbours(0).iterator().forEachRemaining(actual1::add);
		directedWeightedDouble.getNeighbours(0.0).iterator().forEachRemaining(actual2::add);
		directedWeightedString.getNeighbours("a").iterator().forEachRemaining(actual3::add);
		
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
