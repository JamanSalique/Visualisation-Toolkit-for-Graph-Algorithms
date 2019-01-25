package application.view;

import java.util.ArrayList;
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

public class KruskalAlgorithm <T extends Comparable<? super T>>{

	private GraphPanelController gpc;
	
	private SequentialTransition mainAnimation;

	
	private Animations animations;
	
	public KruskalAlgorithm(GraphPanelController gpc) {
		this.gpc = gpc;
		mainAnimation = new SequentialTransition();
		mainAnimation.rateProperty().bind(gpc.getAnimationSpeedSlider().valueProperty());

		
		animations = new Animations(gpc);
	}
	
	public void performKruskalAlgorithmUndirectedWeighted(UndirectedWeightedGraph<T> graph){
		
		HashMap<Pair<T,T>,Double> mapOfEdges = sortMapOfEdges(retrieveAllEdges(graph));
		System.out.println(mapOfEdges);
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
				
				System.out.println("mapOfEdges: " + sortedMapOfEdges);
				
				T vertex1 = sortedMapOfEdges.keySet().iterator().next().getKey();
				T vertex2 = sortedMapOfEdges.keySet().iterator().next().getValue();
				
				System.out.println("v1: " + vertex1);
				System.out.println("v2: " + vertex2);
				
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
		
		
	}
	
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

	private HashMap<Pair<T,T>,Double> sortMapOfEdges(HashMap<Pair<T,T>,Double> mapOfEdges){
		
		HashMap<Pair<T,T>, Double> sortedMapOfEdges = 
				mapOfEdges.entrySet().stream()
			    .sorted(Entry.comparingByValue())
			    .collect(Collectors.toMap(Entry::getKey, Entry::getValue,
			                              (e1, e2) -> e1, LinkedHashMap::new));
		
		return sortedMapOfEdges;
		
	}
	
	private HashSet<T> find(HashSet<HashSet<T>> setOfAllVertexSets,T a) {
		
		for(HashSet<T> set: setOfAllVertexSets) {
			
			if(set.contains(a)) {
				return set;
			}
			
		}
		
		return null;
		
	}
	
	private HashSet<T> union(HashSet<T> a, HashSet<T> b){
		
		HashSet<T> resultOfUnion = new HashSet<T>();
		
		resultOfUnion.addAll(a);
		resultOfUnion.addAll(b);
		
		return resultOfUnion;
		
		
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
