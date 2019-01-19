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
import javafx.animation.SequentialTransition;
import javafx.animation.StrokeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.util.Duration;
import javafx.util.Pair;

public class BreadthFirstSearch<T extends Comparable<? super T>> {
	
	private GraphPanelController gpc;
	
	private SequentialTransition mainAnimationUndirectedNonWeighted;
	private SequentialTransition mainAnimationDirectedNonWeighted;
	private SequentialTransition mainAnimationUndirectedWeighted;
	private SequentialTransition mainAnimationDirectedWeighted;
	
	private Animations animations;
	
	public BreadthFirstSearch(GraphPanelController gpc) {
		this.gpc = gpc;
		mainAnimationUndirectedNonWeighted = new SequentialTransition();
		mainAnimationDirectedNonWeighted = new SequentialTransition();
		mainAnimationUndirectedWeighted = new SequentialTransition();
		mainAnimationDirectedWeighted = new SequentialTransition();
		
		animations = new Animations(gpc);
	}

	public ArrayList<T> performBreadthFirstSearchUndirectedNonWeighted(UndirectedNonWeightedGraph<T> graph, T startingVertex){
		
		if (!graph.containsVertex(startingVertex)) {
            throw new IllegalArgumentException("Vertex doesn't exist.");
        }
		
		if(mainAnimationUndirectedNonWeighted.getChildren().size()>0) {
			mainAnimationUndirectedNonWeighted.getChildren().clear();
		}
		
		T currentVertex;
		ArrayList<T> traversalOrder = new ArrayList<T>();
		ArrayList<T> visitedVertices = new ArrayList<T>();
		LinkedList<T> queue = new LinkedList<T>(); 
		 
		visitedVertices.add(startingVertex);
		queue.add(startingVertex);
		
		mainAnimationUndirectedNonWeighted.getChildren().add(animations.fillVertexTransition(
				startingVertex.toString(),"Undirected Non Weighted"));
		 
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
	                
	                mainAnimationUndirectedNonWeighted.getChildren().add(animations.highlightEdgeTransition(currentVertex.toString(),
	                		v.getElement().toString(), "Undirected Non Weighted"));
	                
	                mainAnimationUndirectedNonWeighted.getChildren().add(animations.fillVertexTransition(
	                		v.getElement().toString(),"Undirected Non Weighted"));
	                
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
	
	public void playAnimationUndirectedNonWeighted() {
		mainAnimationUndirectedNonWeighted.play();
		mainAnimationUndirectedNonWeighted.setOnFinished(new EventHandler<ActionEvent>() {

	        @Override
	        public void handle(ActionEvent event) {
	            gpc.getPlayButton().setText("Play");

	        }
	    });
		
	}
	
	public void pauseAnimationUndirectedNonWeighted() {
		mainAnimationUndirectedNonWeighted.pause();
	}

	public SequentialTransition getMainAnimationUndirectedNonWeighted() {
		return mainAnimationUndirectedNonWeighted;
	}
	
}
