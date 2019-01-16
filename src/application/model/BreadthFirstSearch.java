package application.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;

import javafx.util.Pair;

public class BreadthFirstSearch<T extends Comparable<? super T>> {

	public ArrayList<T> performBreadthFirstSearchUndirectedNonWeighted(UndirectedNonWeightedGraph<T> graph, T startingVertex){
		
		if (!graph.containsVertex(startingVertex)) {
            throw new IllegalArgumentException("Vertex doesn't exist.");
        }
		
		T currentVertex;
		ArrayList<T> traversalOrder = new ArrayList<T>();
		ArrayList<T> visitedVertices = new ArrayList<T>();
		LinkedList<T> queue = new LinkedList<T>(); 
		 
		visitedVertices.add(startingVertex);
		queue.add(startingVertex);
		 
		while (queue.size() != 0) {
			currentVertex = queue.poll();
			traversalOrder.add(currentVertex);
			 
			Iterable<Vertex<T>> neighboursIterable = graph.getNeighbours(currentVertex);
			ArrayList<Vertex<T>> listOfNeighbours = new ArrayList<Vertex<T>>();
			neighboursIterable.forEach(listOfNeighbours::add);
			ArrayList<Vertex<T>> sortedListOfNeighbours = sortList(listOfNeighbours);
			
			for(Vertex<T> v :sortedListOfNeighbours) {
				
				if (!visitedVertices.contains(graph.returnVertex(v.getElement()).getElement())) { 
					visitedVertices.add(v.getElement());
	                queue.add(v.getElement()); 
				} 
				
			}
			
		}
		
		return traversalOrder;
	}
	
	public ArrayList<T> performBreadthFirstSearchDirectedNonWeighted(DirectedNonWeightedGraph<T> graph, T startingVertex){
		
		if (!graph.containsVertex(startingVertex)) {
            throw new IllegalArgumentException("Vertex doesn't exist.");
        }
		
		T currentVertex;
		ArrayList<T> traversalOrder = new ArrayList<T>();
		ArrayList<T> visitedVertices = new ArrayList<T>();
		LinkedList<T> queue = new LinkedList<T>(); 
		 
		visitedVertices.add(startingVertex);
		queue.add(startingVertex);
		 
		while (queue.size() != 0) {
			currentVertex = queue.poll();
			traversalOrder.add(currentVertex);
			 
			Iterable<Vertex<T>> neighboursIterable = graph.getNeighbours(currentVertex);
			ArrayList<Vertex<T>> listOfNeighbours = new ArrayList<Vertex<T>>();
			neighboursIterable.forEach(listOfNeighbours::add);
			ArrayList<Vertex<T>> sortedListOfNeighbours = sortList(listOfNeighbours);
			
			for(Vertex<T> v :sortedListOfNeighbours) {
				
				if (!visitedVertices.contains(graph.returnVertex(v.getElement()).getElement())) { 
					visitedVertices.add(v.getElement());
	                queue.add(v.getElement()); 
				} 
				
			}
			
		}
		
		return traversalOrder;
	}

	public ArrayList<T> performBreadthFirstSearchUndirectedWeighted(UndirectedWeightedGraph<T> graph, T startingVertex){
		
		if (!graph.containsVertex(startingVertex)) {
            throw new IllegalArgumentException("Vertex doesn't exist.");
        }
		
		T currentVertex;
		ArrayList<T> traversalOrder = new ArrayList<T>();
		ArrayList<T> visitedVertices = new ArrayList<T>();
		LinkedList<T> queue = new LinkedList<T>(); 
		 
		visitedVertices.add(startingVertex);
		queue.add(startingVertex);
		 
		while (queue.size() != 0) {
			currentVertex = queue.poll();
			traversalOrder.add(currentVertex);
			 
			Iterable<Pair<Vertex<T>, Double>> neighboursIterable = graph.getNeighbours(currentVertex);
			ArrayList<Vertex<T>> listOfNeighbours = new ArrayList<Vertex<T>>();
			
			for(Pair<Vertex<T>, Double> p : neighboursIterable) {
				listOfNeighbours.add(p.getKey());
			}
			
			ArrayList<Vertex<T>> sortedListOfNeighbours = sortList(listOfNeighbours);
			
			for(Vertex<T> v :sortedListOfNeighbours) {
				
				if (!visitedVertices.contains(graph.returnVertex(v.getElement()).getElement())) { 
					visitedVertices.add(v.getElement());
	                queue.add(v.getElement()); 
				} 
				
			}
			
		}
		
		return traversalOrder;
	}
	
public ArrayList<T> performBreadthFirstSearchDirectedWeighted(DirectedWeightedGraph<T> graph, T startingVertex){
		
		if (!graph.containsVertex(startingVertex)) {
            throw new IllegalArgumentException("Vertex doesn't exist.");
        }
		
		T currentVertex;
		ArrayList<T> traversalOrder = new ArrayList<T>();
		ArrayList<T> visitedVertices = new ArrayList<T>();
		LinkedList<T> queue = new LinkedList<T>(); 
		 
		visitedVertices.add(startingVertex);
		queue.add(startingVertex);
		 
		while (queue.size() != 0) {
			currentVertex = queue.poll();
			traversalOrder.add(currentVertex);
			 
			Iterable<Pair<Vertex<T>, Double>> neighboursIterable = graph.getNeighbours(currentVertex);
			ArrayList<Vertex<T>> listOfNeighbours = new ArrayList<Vertex<T>>();
			
			for(Pair<Vertex<T>, Double> p : neighboursIterable) {
				listOfNeighbours.add(p.getKey());
			}
			
			ArrayList<Vertex<T>> sortedListOfNeighbours = sortList(listOfNeighbours);
			
			for(Vertex<T> v :sortedListOfNeighbours) {
				
				if (!visitedVertices.contains(graph.returnVertex(v.getElement()).getElement())) { 
					visitedVertices.add(v.getElement());
	                queue.add(v.getElement()); 
				} 
				
			}
			
		}
		
		return traversalOrder;
	}
	
	@SuppressWarnings("hiding")
	private <T extends Comparable<? super T>> ArrayList<Vertex<T>> sortList(ArrayList<Vertex<T>> listOfNeigbours) { 

		listOfNeigbours.sort(Comparator.comparing(Vertex::getElement)); 
		
		return (ArrayList<Vertex<T>>) listOfNeigbours;
		
	}
	
}
