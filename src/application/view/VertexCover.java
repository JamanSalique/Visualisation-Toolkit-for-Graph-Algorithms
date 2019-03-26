package application.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.Set;

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
 * This class holds the functionality for the minimum Vertex Cover algorithm animation.
 * @author jamansalique
 *
 * @param <T>
 */
public class VertexCover <T extends Comparable<? super T>>{

	private GraphPanelController gpc;
	
	/* This Sequential transition will contain the sub animations that will be added throughout the vertex cover algorithm. This will be played 
	 * on the GUI when the user wants to visualise the depth first search animation.
	 */
	private SequentialTransition mainAnimation;

	private Animations animations;
	
	/**
	 * Constructor...
	 * @param gpc
	 */
	public VertexCover(GraphPanelController gpc) {
		this.gpc = gpc;
		mainAnimation = new SequentialTransition();
		mainAnimation.rateProperty().bind(gpc.getAnimationSpeedSlider().valueProperty());
		animations = new Animations(gpc);
	}
	
	/**
	 * This method given a undirected non weighted graph will return a array list of all edges sorted by degree of the source vertex in ascending order.
	 * @param graph
	 * @return
	 */
	private ArrayList<Pair<T,T>> retrieveAllEdgesUndirectedNonWeighted(UndirectedNonWeightedGraph<T> graph){
		
		HashMap<Pair<T,T>,Integer> edges = new HashMap<Pair<T,T>,Integer>();
		HashMap<Vertex<T>,Set<Vertex<T>>> adjList = graph.getAdjacencyList();
		
		for(Entry<Vertex<T>,Set<Vertex<T>>> entry : adjList.entrySet()) {
			
			for(Vertex<T> v : entry.getValue()) {
				
				int degreev1= entry.getValue().size();
				int degreev2= adjList.get(v).size();
				Pair<T,T> pairToAdd;
				Pair<T,T> pairReversed;;
				
				if(degreev1 > degreev2) {
					
					pairToAdd = new Pair<T,T>(entry.getKey().getElement(),v.getElement());
					pairReversed = new Pair<T,T>(v.getElement(),entry.getKey().getElement());
					
					if(!edges.containsKey(pairReversed)) {
						
						edges.put(pairToAdd,degreev1 + degreev2);
						
					}
					
				}else {
					pairReversed = new Pair<T,T>(entry.getKey().getElement(),v.getElement());
					pairToAdd = new Pair<T,T>(v.getElement(),entry.getKey().getElement());
					
					if(!edges.containsKey(pairReversed)) {
						
						edges.put(pairToAdd,degreev2 + degreev1);
						
					}
					
				}

			}
			
		}
		
		HashMap<Pair<T,T>, Integer> sortedEdgesByDegreeReversed = 
				edges.entrySet().stream()
			    .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
			    .collect(Collectors.toMap(Entry::getKey, Entry::getValue,
			                              (e1, e2) -> e1, LinkedHashMap::new));
		
		ArrayList<Pair<T,T>> toReturn = new ArrayList<Pair<T,T>>(sortedEdgesByDegreeReversed.keySet());
		
		
		
		return toReturn;
		
	}

	/**
	 * This method given a undirected weighted graph will return a array list of all edges sorted by degree of the source vertex in ascending order.
	 * @param graph
	 * @return
	 */
	private ArrayList<Pair<T,T>> retrieveAllEdgesUndirectedWeighted(UndirectedWeightedGraph<T> graph){
		
		HashMap<Pair<T,T>,Integer> edges = new HashMap<Pair<T,T>,Integer>();
		HashMap<Vertex<T>, Set<Pair<Vertex<T>, Double>>> adjList = graph.getAdjacencyList();
		
		for(Entry<Vertex<T>, Set<Pair<Vertex<T>, Double>>> entry : adjList.entrySet()) {
			
			for(Pair<Vertex<T>, Double> p : entry.getValue()) {
				
				Vertex<T> v = p.getKey();
				
				int degreev1= entry.getValue().size();
				int degreev2= adjList.get(v).size();
				Pair<T,T> pairToAdd;
				Pair<T,T> pairReversed;;
				
				if(degreev1 > degreev2) {
					
					pairToAdd = new Pair<T,T>(entry.getKey().getElement(),v.getElement());
					pairReversed = new Pair<T,T>(v.getElement(),entry.getKey().getElement());
					
					if(!edges.containsKey(pairReversed)) {
						
						edges.put(pairToAdd,degreev1 + degreev2);
						
					}
					
				}else {
					pairReversed = new Pair<T,T>(entry.getKey().getElement(),v.getElement());
					pairToAdd = new Pair<T,T>(v.getElement(),entry.getKey().getElement());
					
					if(!edges.containsKey(pairReversed)) {
						
						edges.put(pairToAdd,degreev2 + degreev1);
						
					}
					
				}

			}
			
		}
		
		HashMap<Pair<T,T>, Integer> sortedEdgesByDegree = 
				edges.entrySet().stream()
			    .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
			    .collect(Collectors.toMap(Entry::getKey, Entry::getValue,
			                              (e1, e2) -> e1, LinkedHashMap::new));
		
		ArrayList<Pair<T,T>> toReturn = new ArrayList<Pair<T,T>>(sortedEdgesByDegree.keySet());
		
		
		
		return toReturn;
		
	}
	
	/**
	 * This method contains the vertex cover algorithm for undirected non weighted graphs with animation transitions. Throughout the 
	 * algorithm sub animations (e.g highlighting vertices/edges) are being added to the main Sequential Transition animation. 
	 * @param graph
	 * @return
	 */
	public boolean performVertexCoverUndirectedNonWeighted(UndirectedNonWeightedGraph<T> graph){
		
		ArrayList<T> vertexCover = new ArrayList<T>();
		
		HashMap<Vertex<T>,Set<Vertex<T>>> adjList = graph.getAdjacencyList();

		ArrayList<Pair<T,T>> listOfEdges = retrieveAllEdgesUndirectedNonWeighted(graph);
		ArrayList<T> vertices = listOfVerticesDegreeOrder(graph);
		int index = 0;
		
		while(!listOfEdges.isEmpty()) {
			
			T vertex = vertices.get(index);
			vertexCover.add(vertex);
			mainAnimation.getChildren().add(animations.fillVertexTransition(
					vertex.toString(),"Undirected Non Weighted"));
			
			for(Vertex<T> u: adjList.get(graph.returnVertex(vertex))) {
				
				Pair<T,T> edge = new Pair<T,T>(vertex,u.getElement());
				Pair<T,T> edgeReversed = new Pair<T,T>(u.getElement(),vertex);
				
				if(listOfEdges.contains(edge) ) {
					listOfEdges.remove(listOfEdges.indexOf(edge));
				}
				
				if(listOfEdges.contains(edgeReversed)) {
					listOfEdges.remove(listOfEdges.indexOf(edgeReversed));
				}
				
			}

			index++;
			
		}
		gpc.getOutputBox().setText("Vertices in minimumum vertex cover: " + Arrays.toString(vertexCover.toArray()));
		return true;
		
	}
	
	/**
	 * This method contains the vertex cover algorithm for undirected weighted graphs with animation transitions. Throughout the 
	 * algorithm sub animations (e.g highlighting vertices/edges) are being added to the main Sequential Transition animation. 
	 * @param graph
	 * @return
	 */
	public boolean performVertexCoverUndirectedWeighted(UndirectedWeightedGraph<T> graph){
		
		ArrayList<T> vertexCover = new ArrayList<T>();
		
		HashMap<Vertex<T>,Set<Pair<Vertex<T>,Double>>> adjList = graph.getAdjacencyList();

		ArrayList<Pair<T,T>> listOfEdges = retrieveAllEdgesUndirectedWeighted(graph);
		ArrayList<T> vertices = listOfVerticesDegreeOrderWeighted(graph);
		int index = 0;
		
		while(!listOfEdges.isEmpty()) {
			
			T vertex = vertices.get(index);
			vertexCover.add(vertex);
			mainAnimation.getChildren().add(animations.fillVertexTransition(
					vertex.toString(),"Undirected Weighted"));
			
			for(Pair<Vertex<T>, Double> u: adjList.get(graph.returnVertex(vertex))) {
				
				Pair<T,T> edge = new Pair<T,T>(vertex,u.getKey().getElement());
				Pair<T,T> edgeReversed = new Pair<T,T>(u.getKey().getElement(),vertex);
				
				if(listOfEdges.contains(edge) ) {
					listOfEdges.remove(listOfEdges.indexOf(edge));
				}
				
				if(listOfEdges.contains(edgeReversed)) {
					listOfEdges.remove(listOfEdges.indexOf(edgeReversed));
				}
				
			}

			index++;
			
		}
		gpc.getOutputBox().setText("Vertices in minimumum vertex cover: " + Arrays.toString(vertexCover.toArray()));
		return true;
		
	}
	
	/**
	 * This method returns a list of vertices in the undirected non weighted graph by degree order ascending.
	 * @param graph
	 * @return
	 */
	private ArrayList<T> listOfVerticesDegreeOrder(UndirectedNonWeightedGraph<T> graph){
		
		ArrayList<T> toReturn = new ArrayList<T>();
		
		HashMap<Vertex<T>,Set<Vertex<T>>> adjList = graph.getAdjacencyList();
		HashMap<T,Integer> vertexDegrees = new HashMap<T,Integer>();
		
		for(Entry<Vertex<T>,Set<Vertex<T>>> e : adjList.entrySet()) {
			
			vertexDegrees.put(e.getKey().getElement(), e.getValue().size());
			
		}
		
		HashMap<T,Integer> vertexDegreesSorted = vertexDegrees.entrySet().stream()
												    .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
												    .collect(Collectors.toMap(Entry::getKey, Entry::getValue,
												                              (e1, e2) -> e1, LinkedHashMap::new));
		for(T vertex:vertexDegreesSorted.keySet()) {
			toReturn.add(vertex);
		}
													
		return toReturn;
		
	}
	
	/**
	 * This method returns a list of vertices in the undirected non weighted graph by degree order ascending.
	 * @param graph
	 * @return
	 */
	private ArrayList<T> listOfVerticesDegreeOrderWeighted(UndirectedWeightedGraph<T> graph){
		
		ArrayList<T> toReturn = new ArrayList<T>();
		
		HashMap<Vertex<T>,Set<Pair<Vertex<T>,Double>>> adjList = graph.getAdjacencyList();
		HashMap<T,Integer> vertexDegrees = new HashMap<T,Integer>();
		
		for(Entry<Vertex<T>,Set<Pair<Vertex<T>,Double>>> e : adjList.entrySet()) {
			
			vertexDegrees.put(e.getKey().getElement(), e.getValue().size());
			
		}
		
		HashMap<T,Integer> vertexDegreesSorted = vertexDegrees.entrySet().stream()
												    .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
												    .collect(Collectors.toMap(Entry::getKey, Entry::getValue,
												                              (e1, e2) -> e1, LinkedHashMap::new));
		for(T vertex:vertexDegreesSorted.keySet()) {
			toReturn.add(vertex);
		}
													
		return toReturn;
		
	}
	
	/**
	 * This method is called after one of the performVertexCover methods has been executed. This method plays the main animation 
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
