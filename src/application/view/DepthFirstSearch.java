package application.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import application.model.DirectedNonWeightedGraph;
import application.model.DirectedWeightedGraph;
import application.model.UndirectedNonWeightedGraph;
import application.model.UndirectedWeightedGraph;
import application.model.Vertex;
import javafx.animation.SequentialTransition;
import javafx.animation.Animation.Status;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Tab;
import javafx.util.Pair;

public class DepthFirstSearch<T extends Comparable<? super T>> {
	

	private GraphPanelController gpc;
	
	private SequentialTransition mainAnimation;

	
	private Animations animations;
	
	public DepthFirstSearch(GraphPanelController gpc) {
		this.gpc = gpc;
		mainAnimation = new SequentialTransition();
		mainAnimation.rateProperty().bind(gpc.getAnimationSpeedSlider().valueProperty());

		
		animations = new Animations(gpc);
	}

	@SuppressWarnings("hiding")
	private <T extends Comparable<? super T>> ArrayList<Vertex<T>> sortList(ArrayList<Vertex<T>> listOfNeigbours) { 

		listOfNeigbours.sort(Comparator.comparing(Vertex::getElement)); 
		
		return (ArrayList<Vertex<T>>) listOfNeigbours;
		
	}
	
	public void performDepthFirstSearchUndirectedNonWeighted(UndirectedNonWeightedGraph<T> graph, T startingVertex,ArrayList<T> visitedVertices,ArrayList<T> traversalOrder){
		
		if (!graph.containsVertex(startingVertex)) {
            throw new IllegalArgumentException("Vertex doesn't exist.");
        }

		visitedVertices.add(startingVertex);
		
		Iterable<Vertex<T>> neighboursIterable = graph.getNeighbours(startingVertex);
		ArrayList<Vertex<T>> listOfNeighbours = new ArrayList<Vertex<T>>();
		neighboursIterable.forEach(listOfNeighbours::add);
		ArrayList<Vertex<T>> sortedListOfNeighbours = sortList(listOfNeighbours);
        
        mainAnimation.getChildren().add(animations.fillVertexTransition(
        		startingVertex.toString(),"Undirected Non Weighted"));
        
        traversalOrder.add(startingVertex);
		
		for(Vertex<T> v :sortedListOfNeighbours) {
			
			if(!visitedVertices.contains(graph.returnVertex(v.getElement()).getElement())) {
				
				mainAnimation.getChildren().add(animations.highlightEdgeTransition(startingVertex.toString(),
		        		v.getElement().toString(), "Undirected Non Weighted"));
				
				performDepthFirstSearchUndirectedNonWeighted(graph,v.getElement(),visitedVertices,traversalOrder);
				
			}
			
		}
		
		gpc.getOutputBox().setText("DFS Traversal Order: " + Arrays.toString(traversalOrder.toArray()));
		
	}
	
	public void performDepthFirstSearchUndirectedWeighted(UndirectedWeightedGraph<T> graph, T startingVertex,ArrayList<T> visitedVertices,ArrayList<T> traversalOrder){
		
		if (!graph.containsVertex(startingVertex)) {
            throw new IllegalArgumentException("Vertex doesn't exist.");
        }

		visitedVertices.add(startingVertex);
		
		Iterable<Pair<Vertex<T>, Double>> neighboursIterable = graph.getNeighbours(startingVertex);
		ArrayList<Vertex<T>> listOfNeighbours = new ArrayList<Vertex<T>>();
		for(Pair<Vertex<T>, Double> p : neighboursIterable) {
			listOfNeighbours.add(p.getKey());
		}
		ArrayList<Vertex<T>> sortedListOfNeighbours = sortList(listOfNeighbours);
        
        mainAnimation.getChildren().add(animations.fillVertexTransition(
        		startingVertex.toString(),"Undirected Weighted"));
        
        traversalOrder.add(startingVertex);
		
		for(Vertex<T> v :sortedListOfNeighbours) {
			
			if(!visitedVertices.contains(graph.returnVertex(v.getElement()).getElement())) {
				
				mainAnimation.getChildren().add(animations.highlightEdgeTransition(startingVertex.toString(),
		        		v.getElement().toString(), "Undirected Weighted"));
				
				performDepthFirstSearchUndirectedWeighted(graph,v.getElement(),visitedVertices,traversalOrder);
				
			}
			
		}
		
		gpc.getOutputBox().setText("DFS Traversal Order: " + Arrays.toString(traversalOrder.toArray()));
	}
	
	public void performDepthFirstSearchDirectedNonWeighted(DirectedNonWeightedGraph<T> graph, T startingVertex,ArrayList<T> visitedVertices,ArrayList<T> traversalOrder){
		
		if (!graph.containsVertex(startingVertex)) {
            throw new IllegalArgumentException("Vertex doesn't exist.");
        }

		visitedVertices.add(startingVertex);
		
		Iterable<Vertex<T>> neighboursIterable = graph.getNeighbours(startingVertex);
		ArrayList<Vertex<T>> listOfNeighbours = new ArrayList<Vertex<T>>();
		neighboursIterable.forEach(listOfNeighbours::add);
		ArrayList<Vertex<T>> sortedListOfNeighbours = sortList(listOfNeighbours);
        
        mainAnimation.getChildren().add(animations.fillVertexTransition(
        		startingVertex.toString(),"Directed Non Weighted"));
        
        traversalOrder.add(startingVertex);
		
		for(Vertex<T> v :sortedListOfNeighbours) {
			
			if(!visitedVertices.contains(graph.returnVertex(v.getElement()).getElement())) {
				
				mainAnimation.getChildren().add(animations.highlightEdgeTransition(startingVertex.toString(),
		        		v.getElement().toString(), "Directed Non Weighted"));
				
				performDepthFirstSearchDirectedNonWeighted(graph,v.getElement(),visitedVertices,traversalOrder);
				
			}
			
		}
		
		gpc.getOutputBox().setText("DFS Traversal Order: " + Arrays.toString(traversalOrder.toArray()));
		
	}
	
	public void performDepthFirstSearchDirectedWeighted(DirectedWeightedGraph<T> graph, T startingVertex,ArrayList<T> visitedVertices,ArrayList<T> traversalOrder){
		
		if (!graph.containsVertex(startingVertex)) {
            throw new IllegalArgumentException("Vertex doesn't exist.");
        }

		visitedVertices.add(startingVertex);
		
		Iterable<Pair<Vertex<T>, Double>> neighboursIterable = graph.getNeighbours(startingVertex);
		ArrayList<Vertex<T>> listOfNeighbours = new ArrayList<Vertex<T>>();
		for(Pair<Vertex<T>, Double> p : neighboursIterable) {
			listOfNeighbours.add(p.getKey());
		}
		ArrayList<Vertex<T>> sortedListOfNeighbours = sortList(listOfNeighbours);
        
		traversalOrder.add(startingVertex);
		
        mainAnimation.getChildren().add(animations.fillVertexTransition(
        		startingVertex.toString(),"Directed Weighted"));
		
		for(Vertex<T> v :sortedListOfNeighbours) {
			
			if(!visitedVertices.contains(graph.returnVertex(v.getElement()).getElement())) {
				
				mainAnimation.getChildren().add(animations.highlightEdgeTransition(startingVertex.toString(),
		        		v.getElement().toString(), "Directed Weighted"));
				
				performDepthFirstSearchDirectedWeighted(graph,v.getElement(),visitedVertices,traversalOrder);
				
			}
			
		}
		
		gpc.getOutputBox().setText("DFS Traversal Order: " + Arrays.toString(traversalOrder.toArray()));
		
	}
	
	public void playMainAnimation() {
		
		if(gpc.getSelectedTabName().equals("Undirected Non-Weighted Graph") && mainAnimation.getStatus() == Status.STOPPED) {
			
			animations.resetGraphColours("Undirected Non Weighted");
			
		}else if(gpc.getSelectedTabName().equals("Undirected Weighted Graph") && mainAnimation.getStatus() == Status.STOPPED) {
			
			animations.resetGraphColours("Undirected Weighted");
			
		}else if(gpc.getSelectedTabName().equals("Directed Non-Weighted Graph") && mainAnimation.getStatus() == Status.STOPPED) {
			
			animations.resetGraphColours("Directed Non Weighted");
			
		}else if(gpc.getSelectedTabName().equals("Directed Weighted Graph") && mainAnimation.getStatus() == Status.STOPPED) {
			
			animations.resetGraphColours("Directed Weighted");
			
		}
		
		mainAnimation.play();
		
		gpc.getRestartButton().setDisable(false);
		gpc.getSkipToEndButton().setDisable(false);
		
		mainAnimation.setOnFinished(new EventHandler<ActionEvent>() {

	        @Override
	        public void handle(ActionEvent event) {
	            gpc.getPlayButton().setText("Play");
				
				for(Tab tab : gpc.getTabs().getTabs()) {
					tab.setDisable(false);
				}

				if(mainAnimation.getChildren().size()>0) {
					mainAnimation.getChildren().clear();
				}
				
				if(gpc.getSelectedTabName().equals("Undirected Non-Weighted Graph")) {
					
					gpc.setUndirectedNonWeightedModified(false);
					gpc.setLastAlgorithmPlayedUndirectedNonWeighted(gpc.getListViewUndirectedNonWeighted().getSelectionModel().getSelectedItem());
					
				}else if(gpc.getSelectedTabName().equals("Undirected Weighted Graph")) {
					
					gpc.setUndirectedWeightedModified(false);
					gpc.setLastAlgorithmPlayedUndirectedWeighted(gpc.getListViewUndirectedWeighted().getSelectionModel().getSelectedItem());
					
				}else if(gpc.getSelectedTabName().equals("Directed Non-Weighted Graph")) {
					
					gpc.setDirectedNonWeightedModified(false);
					gpc.setLastAlgorithmPlayedDirectedNonWeighted(gpc.getListViewDirectedNonWeighted().getSelectionModel().getSelectedItem());
					
				}else if(gpc.getSelectedTabName().equals("Directed Weighted Graph")) {
					
					gpc.setDirectedWeightedModified(false);
					gpc.setLastAlgorithmPlayedDirectedWeighted(gpc.getListViewDirectedWeighted().getSelectionModel().getSelectedItem());
					
				}
	        }
	    });
		
	}
	
	public void pauseMainAnimation() {
		mainAnimation.pause();
	}
	
	public void stopMainAnimation(String graphType) {
		mainAnimation.stop();
		animations.resetGraphColours(graphType);

	}

	public SequentialTransition getMainAnimation() {
		return mainAnimation;
	}


}
