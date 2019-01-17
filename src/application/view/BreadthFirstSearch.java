package application.view;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;

import application.model.DirectedNonWeightedGraph;
import application.model.DirectedWeightedGraph;
import application.model.UndirectedNonWeightedGraph;
import application.model.UndirectedWeightedGraph;
import application.model.Vertex;
import javafx.animation.FillTransition;
import javafx.animation.StrokeTransition;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.util.Duration;
import javafx.util.Pair;

public class BreadthFirstSearch<T extends Comparable<? super T>> {
	
	private GraphPanelController gpc;
	
	public BreadthFirstSearch(GraphPanelController gpc) {
		this.gpc = gpc;
	}

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
	
	public void fillVertexTransition(StackPane vertex) {
		Circle circle = (Circle) vertex.getChildren().get(0);
		
		FillTransition ft = new FillTransition(Duration.millis(3000), circle, Color.WHITE, Color.GREEN);
		ft.play();
		
	}
	
	public void highlightEdgeTransition(Line edge) {
		
		StrokeTransition ft = new StrokeTransition(Duration.millis(3000), edge, Color.BLACK, Color.GREEN);
		ft.play();
		
	}
	
}
