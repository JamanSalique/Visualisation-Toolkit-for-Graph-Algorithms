package application.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;

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

/**
 * This class holds the functionality for the breadth first search algorithm animation.
 * @author jamansalique
 *
 * @param <T>
 */
public class BreadthFirstSearch<T extends Comparable<? super T>> {
	
	private GraphPanelController gpc;
	
	/* This Sequential transition will contain the sub animations that will be added throughout the breadth first search algorithm. This will be played 
	 * on the GUI when the user wants to visualise the breadth first search animation.
	 */
	private SequentialTransition mainAnimation;

	private Animations animations;
	
	/**
	 * Constructor...
	 * @param gpc
	 */
	public BreadthFirstSearch(GraphPanelController gpc) {
		this.gpc = gpc;
		mainAnimation = new SequentialTransition();
		mainAnimation.rateProperty().bind(gpc.getAnimationSpeedSlider().valueProperty());
		animations = new Animations(gpc);
	}

	/**
	 * This method contains the breadth first search algorithm for undirected non weighted graphs with animation transitions. Throughout the 
	 * algorithm sub animations (e.g highlighting vertices/edges) are being added to the main Sequential Transition animation. 
	 * @param graph
	 * @param startingVertex
	 * @return
	 */
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
		
		gpc.getOutputBox().setText("BFS Traversal Order: " + Arrays.toString(traversalOrder.toArray()));
		return traversalOrder;
	}
	
	/**
	 * This method contains the breadth first search algorithm for directed non weighted graphs with animation transitions. Throughout the 
	 * algorithm sub animations (e.g highlighting vertices/edges) are being added to the main Sequential Transition animation. 
	 * @param graph
	 * @param startingVertex
	 * @return
	 */
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
		gpc.getOutputBox().setText("BFS Traversal Order: " + Arrays.toString(traversalOrder.toArray()));
		return traversalOrder;
	}

	/**
	 * This method contains the breadth first search algorithm for undirected weighted graphs with animation transitions. Throughout the 
	 * algorithm sub animations (e.g highlighting vertices/edges) are being added to the main Sequential Transition animation. 
	 * @param graph
	 * @param startingVertex
	 * @return
	 */
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
		
		gpc.getOutputBox().setText("BFS Traversal Order: " + Arrays.toString(traversalOrder.toArray()));
		return traversalOrder;
	}
	
	/**
	 * This method contains the breadth first search algorithm for directed weighted graphs with animation transitions. Throughout the 
	 * algorithm sub animations (e.g highlighting vertices/edges) are being added to the main Sequential Transition animation. 
	 * @param graph
	 * @param startingVertex
	 * @return
	 */
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
		
		gpc.getOutputBox().setText("BFS Traversal Order: " + Arrays.toString(traversalOrder.toArray()));
		return traversalOrder;
	}
	
	/**
	 * Helper method that sorts a array list of generic vertex objects by the element the vertex holds.
	 * @param listOfNeigbours
	 * @return
	 */
	@SuppressWarnings("hiding")
	private <T extends Comparable<? super T>> ArrayList<Vertex<T>> sortList(ArrayList<Vertex<T>> listOfNeigbours) { 

		listOfNeigbours.sort(Comparator.comparing(Vertex::getElement)); 
		
		return (ArrayList<Vertex<T>>) listOfNeigbours;
		
	}
	
	/**
	 * This method is called after one of the performBreadthFirstSearch methods has been executed. This method plays the main animation 
	 * but some checks are done before to check which graph the animation should be played on.
	 */
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
	
	/**
	 * This method when called will pause the animation being played on the gui.
	 */
	public void pauseMainAnimation() {
		mainAnimation.pause();
	}
	
	/**
	 * This method when called will completely stop the animation that is being played.
	 * @param graphType
	 */
	public void stopMainAnimation(String graphType) {
		mainAnimation.stop();
		animations.resetGraphColours(graphType);

	}

	public SequentialTransition getMainAnimation() {
		return mainAnimation;
	}
	
}
