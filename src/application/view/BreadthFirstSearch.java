package application.view;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;

import application.model.DirectedNonWeightedGraph;
import application.model.DirectedWeightedGraph;
import application.model.UndirectedNonWeightedGraph;
import application.model.UndirectedWeightedGraph;
import application.model.Vertex;
import javafx.animation.SequentialTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Tab;
import javafx.util.Pair;

public class BreadthFirstSearch<T extends Comparable<? super T>> {
	
	private GraphPanelController gpc;
	
	private SequentialTransition mainAnimation;

	
	private Animations animations;
	
	public BreadthFirstSearch(GraphPanelController gpc) {
		this.gpc = gpc;
		mainAnimation = new SequentialTransition();

		
		animations = new Animations(gpc);
	}

	public ArrayList<T> performBreadthFirstSearchUndirectedNonWeighted(UndirectedNonWeightedGraph<T> graph, T startingVertex){
		
		if (!graph.containsVertex(startingVertex)) {
            throw new IllegalArgumentException("Vertex doesn't exist.");
        }
		
		if(mainAnimation.getChildren().size()>0) {
			mainAnimation.getChildren().clear();
		}
		
		animations.resetGraphColours("Undirected Non Weighted");
		
		T currentVertex;
		ArrayList<T> traversalOrder = new ArrayList<T>();
		ArrayList<T> visitedVertices = new ArrayList<T>();
		LinkedList<T> queue = new LinkedList<T>(); 
		 
		visitedVertices.add(startingVertex);
		queue.add(startingVertex);
		
		mainAnimation.getChildren().add(animations.fillVertexTransition(
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
	                
	                mainAnimation.getChildren().add(animations.highlightEdgeTransition(currentVertex.toString(),
	                		v.getElement().toString(), "Undirected Non Weighted"));
	                
	                mainAnimation.getChildren().add(animations.fillVertexTransition(
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
		
		if(mainAnimation.getChildren().size()>0) {
			mainAnimation.getChildren().clear();
		}
		
		animations.resetGraphColours("Directed Non Weighted");
		
		T currentVertex;
		ArrayList<T> traversalOrder = new ArrayList<T>();
		ArrayList<T> visitedVertices = new ArrayList<T>();
		LinkedList<T> queue = new LinkedList<T>(); 
		 
		visitedVertices.add(startingVertex);
		queue.add(startingVertex);
		
		mainAnimation.getChildren().add(animations.fillVertexTransition(
				startingVertex.toString(),"Directed Non Weighted"));
		
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
	                
	                mainAnimation.getChildren().add(animations.highlightEdgeTransition(currentVertex.toString(),
	                		v.getElement().toString(), "Directed Non Weighted"));
	                
	                mainAnimation.getChildren().add(animations.fillVertexTransition(
	                		v.getElement().toString(),"Directed Non Weighted"));
				} 
				
			}
			
		}
		
		return traversalOrder;
	}

	public ArrayList<T> performBreadthFirstSearchUndirectedWeighted(UndirectedWeightedGraph<T> graph, T startingVertex){
		
		if (!graph.containsVertex(startingVertex)) {
            throw new IllegalArgumentException("Vertex doesn't exist.");
        }
		
		if(mainAnimation.getChildren().size()>0) {
			mainAnimation.getChildren().clear();
		}
		
		animations.resetGraphColours("Undirected Weighted");
		
		T currentVertex;
		ArrayList<T> traversalOrder = new ArrayList<T>();
		ArrayList<T> visitedVertices = new ArrayList<T>();
		LinkedList<T> queue = new LinkedList<T>(); 
		 
		visitedVertices.add(startingVertex);
		queue.add(startingVertex);
		
		mainAnimation.getChildren().add(animations.fillVertexTransition(
				startingVertex.toString(),"Undirected Weighted"));
		 
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
	                
	                mainAnimation.getChildren().add(animations.highlightEdgeTransition(currentVertex.toString(),
	                		v.getElement().toString(), "Undirected Weighted"));
	                
	                mainAnimation.getChildren().add(animations.fillVertexTransition(
	                		v.getElement().toString(),"Undirected Weighted"));
				} 
				
			}
			
		}
		
		return traversalOrder;
	}
	
	public ArrayList<T> performBreadthFirstSearchDirectedWeighted(DirectedWeightedGraph<T> graph, T startingVertex){
		
		if (!graph.containsVertex(startingVertex)) {
            throw new IllegalArgumentException("Vertex doesn't exist.");
        }
		
		if(mainAnimation.getChildren().size()>0) {
			mainAnimation.getChildren().clear();
		}
		
		animations.resetGraphColours("Directed Weighted");
		
		T currentVertex;
		ArrayList<T> traversalOrder = new ArrayList<T>();
		ArrayList<T> visitedVertices = new ArrayList<T>();
		LinkedList<T> queue = new LinkedList<T>(); 
		 
		visitedVertices.add(startingVertex);
		queue.add(startingVertex);
		
		mainAnimation.getChildren().add(animations.fillVertexTransition(
				startingVertex.toString(),"Directed Weighted"));
		 
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
	                
	                mainAnimation.getChildren().add(animations.highlightEdgeTransition(currentVertex.toString(),
	                		v.getElement().toString(), "Directed Weighted"));
	                
	                mainAnimation.getChildren().add(animations.fillVertexTransition(
	                		v.getElement().toString(),"Directed Weighted"));
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
	
	public void playMainAnimation() {
		mainAnimation.play();
		mainAnimation.setOnFinished(new EventHandler<ActionEvent>() {

	        @Override
	        public void handle(ActionEvent event) {
	            gpc.getPlayButton().setText("Play");
				
				for(Tab tab : gpc.getTabs().getTabs()) {
					tab.setDisable(false);
				}

	        }
	    });
		
	}
	
	public void pauseMainAnimation() {
		mainAnimation.pause();
	}

	public SequentialTransition getMainAnimation() {
		return mainAnimation;
	}
	
}
