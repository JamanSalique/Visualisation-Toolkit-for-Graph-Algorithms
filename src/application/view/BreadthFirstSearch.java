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
	
	public BreadthFirstSearch(GraphPanelController gpc) {
		this.gpc = gpc;
		mainAnimationUndirectedNonWeighted = new SequentialTransition();
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
		
		mainAnimationUndirectedNonWeighted.getChildren().add(fillVertexUndirectedNonWeighted(startingVertex.toString()));
		 
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
	                mainAnimationUndirectedNonWeighted.getChildren().add(highlightEdgeTransition(currentVertex.toString(),v.getElement().toString()));
	                mainAnimationUndirectedNonWeighted.getChildren().add(fillVertexUndirectedNonWeighted(v.getElement().toString()));
	                
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
	
	public StrokeTransition highlightEdgeTransition(String v1Data, String v2Data) {
		
		StackPane v1 = returnStackPaneUndirectedNonWeighted(v1Data);
		StackPane v2 = returnStackPaneUndirectedNonWeighted(v2Data);
		
		Line edge = returnEdgeBetweenVerticesUndirectedNonWeighted(v1,v2);
		
		return new StrokeTransition(Duration.millis(3000), edge, Color.BLACK, Color.LIGHTGREEN);
		
	}
	
	public FillTransition fillVertexUndirectedNonWeighted(String vertexData) {
		
		for(Node child:gpc.getCenterPaneUndirectedNonWeightedGraph().getChildren()) {
			
			if(child instanceof StackPane) {
				
				StackPane vertex = (StackPane) child;
				Text data = (Text) vertex.getChildren().get(1);
				
				if(data.getText().equals(vertexData)) {
					
					Circle circle = (Circle) vertex.getChildren().get(0);
					
					return new FillTransition(Duration.millis(3000), circle, Color.WHITE, Color.LIGHTGREEN);
					
				}
				
			}
			
		}
		return null;
		
	}
	
	public Line returnEdgeBetweenVerticesUndirectedNonWeighted(StackPane v1, StackPane v2) {
		
		double v1X = v1.getLayoutX() + (v1.getWidth() / 2);
		double v1Y = v1.getLayoutY() + (v1.getHeight() / 2);
		double v2X = v2.getLayoutX() + (v2.getWidth() / 2);
		double v2Y = v2.getLayoutY() + (v2.getHeight() / 2);
		
		for(Node child:gpc.getCenterPaneUndirectedNonWeightedGraph().getChildren()) {
			
			if(child instanceof Line) {
				Line edge = (Line) child;
				
				if((edge.getStartX() == v1X && edge.getStartY() == v1Y && edge.getEndX() == v2X && edge.getEndY() == v2Y) ||
						(edge.getStartX() == v2X && edge.getStartY() == v2Y && edge.getEndX() == v1X && edge.getEndY() == v1Y)) {
					return edge;
				}
				
			}
			
		}
		return null;
		
	}
	
	public StackPane returnStackPaneUndirectedNonWeighted(String v) {
		
		for(Node child:gpc.getCenterPaneUndirectedNonWeightedGraph().getChildren()) {
			if(child instanceof StackPane) {
				StackPane potentialStackPane = (StackPane) child;
				String data = ((Text) potentialStackPane.getChildren().get(1)).getText();
				if(data.equals(v)) {
					return potentialStackPane;
				}
			}
		}
		System.out.println("284");
		return null;
		
	}
	
	public void playAnimationUndirectedNonWeighted() {
		mainAnimationUndirectedNonWeighted.play();
	}
	
	public void pauseAnimationUndirectedNonWeighted() {
		mainAnimationUndirectedNonWeighted.pause();
	}
	
	public void resumeAnimationUndirectedNonWeighted() {
		mainAnimationUndirectedNonWeighted.play();
	}
	
	public SequentialTransition getMainAnimationUndirectedNonWeighted() {
		return mainAnimationUndirectedNonWeighted;
	}
	
}
