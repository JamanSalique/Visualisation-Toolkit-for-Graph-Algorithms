package application.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import application.model.UndirectedWeightedGraph;
import application.model.Vertex;
import javafx.animation.SequentialTransition;
import javafx.animation.Animation.Status;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Tab;
import javafx.util.Pair;

/**
 * This class holds the functionality for Kruskal's algorithm animation.
 * @author jamansalique
 *
 * @param <T>
 */
public class KruskalAlgorithm <T extends Comparable<? super T>>{

	private GraphPanelController gpc;
	
	/* This Sequential transition will contain the sub animations that will be added throughout kruskal's algorithm. This will be played 
	 * on the GUI when the user wants to visualise kruskal's algorithm animation.
	 */
	private SequentialTransition mainAnimation;

	
	private Animations animations;
	
	/**
	 * Constructor...
	 * @param gpc
	 */
	public KruskalAlgorithm(GraphPanelController gpc) {
		this.gpc = gpc;
		mainAnimation = new SequentialTransition();
		mainAnimation.rateProperty().bind(gpc.getAnimationSpeedSlider().valueProperty());

		
		animations = new Animations(gpc);
	}
	
	/**
	 * This method contains kruskal's algorithm using union-find for undirected weighted graphs with animation transitions. Throughout the 
	 * algorithm sub animations (e.g highlighting vertices/edges) are being added to the main Sequential Transition animation. 
	 * @param graph
	 * @return
	 */
	public void performKruskalAlgorithmUndirectedWeighted(UndirectedWeightedGraph<T> graph){
		
		ArrayList<String> edgesInMST = new ArrayList<String>();
		double weightOfMST =0;
		
		HashMap<Pair<T,T>,Double> mapOfEdges = sortMapOfEdges(retrieveAllEdges(graph));
		HashSet<HashSet<T>> setOfVertexSets = new HashSet<HashSet<T>>();
		HashMap<Pair<T,T>,Double> sortedMapOfEdges = new HashMap<Pair<T,T>,Double>();
		
		for(Vertex<T> v: graph.getAdjacencyList().keySet()) {
			HashSet<T> vertexSet = new HashSet<T>();
			vertexSet.add(v.getElement());
			
			setOfVertexSets.add(vertexSet);
			
		}
		
		if(mapOfEdges.size() == 0) {
			
			for(Vertex<T> v : graph.getAdjacencyList().keySet()) {
				
				mainAnimation.getChildren().add(animations.fillVertexTransition(
		        		v.getElement().toString(),"Undirected Weighted"));
				
			}
			
		}else {
			
			while(setOfVertexSets.size() != 1 && mapOfEdges.size() != 0) {
				
				sortedMapOfEdges =  sortMapOfEdges(mapOfEdges);
				
				T vertex1 = sortedMapOfEdges.keySet().iterator().next().getKey();
				T vertex2 = sortedMapOfEdges.keySet().iterator().next().getValue();
				double w = sortedMapOfEdges.values().iterator().next();

				HashSet<T> set1 = find(setOfVertexSets,vertex1);
				HashSet<T> set2 = find(setOfVertexSets,vertex2);
				
				if(!set1.containsAll(set2)) {
					
					HashSet<T> unionedSet = union(set1,set2);
					setOfVertexSets.remove(set1);
					setOfVertexSets.remove(set2);
					setOfVertexSets.add(unionedSet);
					
					mainAnimation.getChildren().add(animations.fillVertexTransition(
			        		vertex1.toString(),"Undirected Weighted"));
					
					mainAnimation.getChildren().add(animations.fillVertexTransition(
			        		vertex2.toString(),"Undirected Weighted"));
					
					mainAnimation.getChildren().add(animations.highlightEdgeTransition(vertex1.toString(),
							vertex2.toString(), "Undirected Weighted"));
					
					edgesInMST.add(vertex1.toString() + "-" + vertex2.toString());
					weightOfMST += w;
				
				}
				
				HashMap<Pair<T,T>,Double> copyOfMapOfEdges = (HashMap<Pair<T, T>, Double>) mapOfEdges.clone();
				
				

				for(Pair<T,T> p : copyOfMapOfEdges.keySet()) {
					
					if(p.getKey().equals(vertex1) && p.getValue().equals(vertex2)) {
						
						mapOfEdges.remove(p);
						
					}
					
				}
				
			}
			
			for(Entry<Vertex<T>, Set<Pair<Vertex<T>, Double>>> v : graph.getAdjacencyList().entrySet()) {
				
				if(v.getValue().isEmpty()) {
					
					mainAnimation.getChildren().add(animations.fillVertexTransition(
			        		v.getKey().getElement().toString(),"Undirected Weighted"));
					
				}
				
			}
			
		}
		double roundedTotalWeightMST = Math.round(weightOfMST * 100.0) / 100.0;
		gpc.getOutputBox().setText("Edges in the minimum spanning tree: " + Arrays.toString(edgesInMST.toArray()) + "\n" +
				"Total Weight of minimum spanning tree is: " + roundedTotalWeightMST);
	}
	
	/**
	 * This method given a undirected non weighted graph will return a hash map of all edges.
	 * @param graph
	 * @return
	 */
	private HashMap<Pair<T,T>,Double> retrieveAllEdges(UndirectedWeightedGraph<T> graph){
		
		HashMap<Pair<T,T>,Double> toReturn = new  HashMap<Pair<T,T>,Double>();
		HashMap<Vertex<T>,Set<Pair<Vertex<T>,Double>>> adjList = graph.getAdjacencyList();
		
		for(Entry<Vertex<T>,Set<Pair<Vertex<T>,Double>>> entry : adjList.entrySet()) {
			
			for(Pair<Vertex<T>,Double> p : entry.getValue()) {
				Pair<T,T> pairToAdd = new Pair<T,T>(entry.getKey().getElement(),p.getKey().getElement());
				Pair<T,T> pairReversed = new Pair<T,T>(p.getKey().getElement(),entry.getKey().getElement());
				
				if(!toReturn.containsKey(pairReversed)) {
					
					toReturn.put(pairToAdd, p.getValue());
					
				}
				
				
				
			}
			
		}
		
		return toReturn;
		
	}

	/**
	 * Given a hash map of edges and weights this method will sort the edges by weight in ascending order.
	 * @param mapOfEdges
	 * @return
	 */
	private HashMap<Pair<T,T>,Double> sortMapOfEdges(HashMap<Pair<T,T>,Double> mapOfEdges){
		
		HashMap<Pair<T,T>, Double> sortedMapOfEdges = 
				mapOfEdges.entrySet().stream()
			    .sorted(Entry.comparingByValue())
			    .collect(Collectors.toMap(Entry::getKey, Entry::getValue,
			                              (e1, e2) -> e1, LinkedHashMap::new));
		
		return sortedMapOfEdges;
		
	}
	
	/**
	 * This method given a sets of vertices and a element a will return the set of vertices that contain a vertex with element a.
	 * @param setOfAllVertexSets
	 * @param a
	 * @return
	 */
	private HashSet<T> find(HashSet<HashSet<T>> setOfAllVertexSets,T a) {
		
		for(HashSet<T> set: setOfAllVertexSets) {
			
			if(set.contains(a)) {
				return set;
			}
			
		}
		
		return null;
		
	}
	
	/**
	 * Given two sets of vertices this method will return the result of the union between the 2 sets given.
	 * @param a
	 * @param b
	 * @return
	 */
	private HashSet<T> union(HashSet<T> a, HashSet<T> b){
		
		HashSet<T> resultOfUnion = new HashSet<T>();
		
		resultOfUnion.addAll(a);
		resultOfUnion.addAll(b);
		
		return resultOfUnion;
		
		
	}
	
	/**
	 * This method is called after the performKruskalAlgorithm methods has been executed. This method plays the main animation 
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
