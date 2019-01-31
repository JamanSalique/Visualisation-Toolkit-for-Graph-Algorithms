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

public class VertexCover <T extends Comparable<? super T>>{

	private GraphPanelController gpc;
	
	private SequentialTransition mainAnimation;

	
	private Animations animations;
	
	public VertexCover(GraphPanelController gpc) {
		this.gpc = gpc;
		mainAnimation = new SequentialTransition();
		mainAnimation.rateProperty().bind(gpc.getAnimationSpeedSlider().valueProperty());

		
		animations = new Animations(gpc);
	}
	
	public boolean performVertexCoverUndirectedNonWeighted(UndirectedNonWeightedGraph<T> graph){
		
		ArrayList<T> vertexCover = new ArrayList<T>();
		
		HashMap<Vertex<T>,Set<Vertex<T>>> adjList = graph.getAdjacencyList();
		
		if(adjList.size() == 1) {
			
			T v =  ((Vertex<T>)adjList.keySet().toArray()[0]).getElement();
			
			mainAnimation.getChildren().add(animations.fillVertexTransition(
					v.toString(),"Undirected Non Weighted"));
			
			vertexCover.add(v);
			gpc.getOutputBox().setText("Vertices in minimumum vertex cover: " + Arrays.toString(vertexCover.toArray()));
			return true;
			
		}
		
		for(Entry<Vertex<T>,Set<Vertex<T>>> entry : adjList.entrySet()) {
			if(entry.getValue().isEmpty()) {
				gpc.getOutputBox().setText("This particular graph does not have a vertex cover since the graph is disconnected.");
				return false;
			}
		}
		
		int numberOfVertices = graph.getAdjacencyList().size();
		ArrayList<T> visitedVertices = new ArrayList<T>();
		ArrayList<Pair<T,T>> listOfEdges = retrieveAllEdgesUndirectedNonWeighted(graph);
		int index = 0;
		
		while(visitedVertices.size() != numberOfVertices && index<listOfEdges.size()) {
			
			Pair<T,T> edge = listOfEdges.get(index);

			if(!visitedVertices.contains(edge.getKey())) {
				
				Iterable<Vertex<T>> neighboursIterable = graph.getNeighbours(edge.getKey());
				ArrayList<Vertex<T>> listOfNeighbours = new ArrayList<Vertex<T>>();
				neighboursIterable.forEach(listOfNeighbours::add);
				
				
				T v = edge.getValue();
					
				if(!visitedVertices.contains(v)) {
					
					visitedVertices.add(edge.getKey());
					visitedVertices.add(v);
					
					
					vertexCover.add(edge.getKey());
					mainAnimation.getChildren().add(animations.fillVertexTransition(
							edge.getKey().toString(),"Undirected Non Weighted"));

				}
					
				
			}
			index++;
			
		}
		gpc.getOutputBox().setText("Vertices in minimumum vertex cover: " + Arrays.toString(vertexCover.toArray()));
		return true;
		
	}
	
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
	
	public boolean performVertexCoverUndirectedWeighted(UndirectedWeightedGraph<T> graph){
		
		ArrayList<T> vertexCover = new ArrayList<T>();
		HashMap<Vertex<T>, Set<Pair<Vertex<T>, Double>>> adjList = graph.getAdjacencyList();
		
		if(adjList.size() == 1) {
			
			T v =  ((Vertex<T>)adjList.keySet().toArray()[0]).getElement();
			
			mainAnimation.getChildren().add(animations.fillVertexTransition(
					v.toString(),"Undirected Weighted"));
			
			vertexCover.add(v);
			gpc.getOutputBox().setText("Vertices in minimumum vertex cover: " + Arrays.toString(vertexCover.toArray()));
			return true;
			
		}
		
		for(Entry<Vertex<T>,Set<Pair<Vertex<T>, Double>>> entry : adjList.entrySet()) {
			if(entry.getValue().isEmpty()) {
				gpc.getOutputBox().setText("This particular graph does not have a vertex cover since the graph is disconnected.");
				return false;
			}
		}
		
		int numberOfVertices = graph.getAdjacencyList().size();
		
		ArrayList<T> visitedVertices = new ArrayList<T>();
		ArrayList<Pair<T,T>> listOfEdges = retrieveAllEdgesUndirectedWeighted(graph);

		int index = 0;
		
		while(visitedVertices.size() != numberOfVertices && index<listOfEdges.size()) {
			
			Pair<T,T> edge = listOfEdges.get(index);
			
			if(!visitedVertices.contains(edge.getKey())) {
				
				Iterable<Pair<Vertex<T>, Double>> neighboursIterable = graph.getNeighbours(edge.getKey());
				ArrayList<Vertex<T>> listOfNeighbours = new ArrayList<Vertex<T>>();
				
				for(Pair<Vertex<T>, Double> p : neighboursIterable) {
					listOfNeighbours.add(p.getKey());
				}
				
				T v = edge.getValue();
					
				if(!visitedVertices.contains(v)) {
					
					visitedVertices.add(edge.getKey());
					visitedVertices.add(v);
					
					vertexCover.add(edge.getKey());
					mainAnimation.getChildren().add(animations.fillVertexTransition(
							edge.getKey().toString(),"Undirected Weighted"));

				}
					
				
			}
			index++;
			
		}
		gpc.getOutputBox().setText("Vertices in minimumum vertex cover: " + Arrays.toString(vertexCover.toArray()));
		return true;
		
	}
	
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
