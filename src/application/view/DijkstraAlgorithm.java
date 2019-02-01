package application.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import application.model.DirectedWeightedGraph;
import application.model.UndirectedWeightedGraph;
import application.model.Vertex;
import javafx.animation.Animation.Status;
import javafx.animation.SequentialTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Tab;
import javafx.util.Pair;

public class DijkstraAlgorithm <T extends Comparable<? super T>>{

private GraphPanelController gpc;
	
	private SequentialTransition mainAnimation;

	
	private Animations animations;
	
	public DijkstraAlgorithm(GraphPanelController gpc) {
		this.gpc = gpc;
		mainAnimation = new SequentialTransition();
		mainAnimation.rateProperty().bind(gpc.getAnimationSpeedSlider().valueProperty());

		
		animations = new Animations(gpc);
	}
	
	
	public void performDijkstrasAlgorithmUndirectedWeighted(UndirectedWeightedGraph<T> graph, T startingVertex,ArrayList<T> visitedVertices,HashMap<T,Double> vertexDistances){
		
		if (!graph.containsVertex(startingVertex)) {
            throw new IllegalArgumentException("Vertex doesn't exist.");
        }
		
		HashMap<T,T> predecessors = new HashMap<T,T>();
		
		ArrayList<String> edgesInShortestPathTree = new ArrayList<String>();
		

		 for (int i=0; i<vertexDistances.size();i++) { 
			 
			 T u = minDistance(vertexDistances,visitedVertices); 
			 
			 visitedVertices.add(u);

				Iterable<Pair<Vertex<T>, Double>> neighboursIterable = graph.getNeighbours(u);
				HashMap<T, Double> listOfNeighboursPairs = new HashMap<T, Double>();
				ArrayList<T> listOfNeighboursVertices = new ArrayList<T>();
				ArrayList<Pair<Vertex<T>, Double>> listOfNeighboursVerticesInVisited = new ArrayList<Pair<Vertex<T>, Double>>();
				
				for(Pair<Vertex<T>, Double> p : neighboursIterable) {
					
					if(!visitedVertices.contains(p.getKey().getElement())) {
						listOfNeighboursPairs.put(p.getKey().getElement(),p.getValue());
						listOfNeighboursVertices.add(p.getKey().getElement());
					}else {
						listOfNeighboursVerticesInVisited.add(p);
					}
					
				}

				if(i != 0 && listOfNeighboursVerticesInVisited.size()>0) {
					T vertexFrom = predecessors.get(u);
					mainAnimation.getChildren().add(animations.highlightEdgeTransition(vertexFrom.toString(),
							u.toString(), "Undirected Weighted"));
					
					edgesInShortestPathTree.add(vertexFrom.toString() + "-" + u.toString());
				
				}
				mainAnimation.getChildren().add(animations.fillVertexTransition(
		        		u.toString(),"Undirected Weighted"));
			 
			 for(T v:vertexDistances.keySet()) {
				 
				 if(!visitedVertices.contains(v) && vertexDistances.get(u) != Double.MAX_VALUE && listOfNeighboursVertices.contains(v) &&
						 listOfNeighboursPairs.get(v) + vertexDistances.get(u)< vertexDistances.get(v)) {
					 
					 vertexDistances.replace(v,listOfNeighboursPairs.get(v) + vertexDistances.get(u));
					 predecessors.put(v, u);

					 
				 }
				 
			 }
		 }
		 
		 gpc.getOutputBox().setText("Edges in shortest path tree: " + Arrays.toString(edgesInShortestPathTree.toArray()));
		
	}
	
	public void performDijkstrasAlgorithmDirectedWeighted(DirectedWeightedGraph<T> graph, T startingVertex,ArrayList<T> visitedVertices,HashMap<T,Double> vertexDistances){
		
		if (!graph.containsVertex(startingVertex)) {
            throw new IllegalArgumentException("Vertex doesn't exist.");
        }
		
		HashMap<T,T> predecessors = new HashMap<T,T>();
		
		ArrayList<String> edgesInShortestPathTree = new ArrayList<String>();
		
		if(!graph.getNeighbours(startingVertex).iterator().hasNext()) {
			
			mainAnimation.getChildren().add(animations.fillVertexTransition(
					startingVertex.toString(),"Directed Weighted"));
			 gpc.getOutputBox().setText("Edges in shortest path tree: " + Arrays.toString(edgesInShortestPathTree.toArray()));
			return;
			
		}

		 for (int i=0; i<vertexDistances.size();i++) { 
			 
			 T u = minDistance(vertexDistances,visitedVertices); 
			 visitedVertices.add(u);
			 
				Iterable<Pair<Vertex<T>, Double>> neighboursIterable = graph.getNeighbours(u);
				HashMap<T, Double> listOfNeighboursPairs = new HashMap<T, Double>();
				ArrayList<T> listOfNeighboursVertices = new ArrayList<T>();
				ArrayList<Pair<Vertex<T>, Double>> listOfNeighboursVerticesInVisited = new ArrayList<Pair<Vertex<T>, Double>>();
				ArrayList<Pair<Vertex<T>,Double>> listOfVerticesConnectedToU = new ArrayList<Pair<Vertex<T>,Double>>();
				
				for(Pair<Vertex<T>, Double> p : neighboursIterable) {
					if(!visitedVertices.contains(p.getKey().getElement())) {
						listOfNeighboursPairs.put(p.getKey().getElement(),p.getValue());
						listOfNeighboursVertices.add(p.getKey().getElement());
					}else {
						listOfNeighboursVerticesInVisited.add(p);
					}
					
				}
				
				for(Entry<Vertex<T>,Set<Pair<Vertex<T>,Double>>> x : graph.getAdjacencyList().entrySet()) {
					
					if(visitedVertices.contains(x.getKey().getElement())) {
						for(Pair<Vertex<T>,Double> p : x.getValue()) {
							if(p.getKey().getElement().equals(u)) {
								listOfVerticesConnectedToU.add(new Pair<Vertex<T>,Double>(x.getKey(),p.getValue()));
								
							}
						}
					}
					
				}
				
				if(i != 0 && listOfVerticesConnectedToU.size()>0) {
					
					T vertexFrom = predecessors.get(u);
					Iterable<Pair<Vertex<T>, Double>> vertexFromNeighboursIterable = graph.getNeighbours(vertexFrom);
					ArrayList<T> listOfVertexFromNeighbours = new ArrayList<T>();
					
					for(Pair<Vertex<T>, Double> p : vertexFromNeighboursIterable) {
						listOfVertexFromNeighbours.add(p.getKey().getElement());
					}
					
					if(listOfVertexFromNeighbours.contains(u)) {
						
						edgesInShortestPathTree.add(vertexFrom.toString() + "-" + u.toString());
						mainAnimation.getChildren().add(animations.highlightEdgeTransition(vertexFrom.toString(),
								u.toString(), "Directed Weighted"));
						
					}
					
				}
				mainAnimation.getChildren().add(animations.fillVertexTransition(
		        		u.toString(),"Directed Weighted"));
			 
			 for(T v:vertexDistances.keySet()) {
				 
				 if(!visitedVertices.contains(v) && vertexDistances.get(u) != Double.MAX_VALUE && listOfNeighboursVertices.contains(v) &&
						 listOfNeighboursPairs.get(v) + vertexDistances.get(u)< vertexDistances.get(v)) {
					 
					 vertexDistances.replace(v,listOfNeighboursPairs.get(v) + vertexDistances.get(u));
					 predecessors.put(v, u);
					 
				 }
				 
			 }
		 }
		 
		 gpc.getOutputBox().setText("Edges in shortest path tree: " + Arrays.toString(edgesInShortestPathTree.toArray()));
		
	}
	
	private Pair<T, Double> findShortestEdgeInList(ArrayList<Pair<Vertex<T>, Double>> list){
		
		double smallestWeight = list.get(0).getValue();
		T vertexElement = list.get(0).getKey().getElement();
		
		for(Pair<Vertex<T>, Double> p: list) {
			
			if(p.getValue() < smallestWeight) {
				
				smallestWeight = p.getValue();
				vertexElement = p.getKey().getElement();
				
			}else if(p.getValue() == smallestWeight) {
				
				@SuppressWarnings("unchecked")
				int cmp = ((Comparable<? super T>) p.getKey().getElement()).compareTo(vertexElement);
				if(cmp<0) {
					
					smallestWeight = p.getValue();
					vertexElement = p.getKey().getElement();
				}
				
			}
			
		}
		
		return new Pair<T, Double>(vertexElement,smallestWeight);
		
	}
	
	public T minDistance(HashMap<T,Double> vertexDistances, ArrayList<T> visitedVertices){ 
        // Initialize min value 
        double min = Double.MAX_VALUE;
        T vertex = vertexDistances.keySet().stream().findFirst().get();
  
        for (T v:vertexDistances.keySet()) { 
            if (!visitedVertices.contains(v) && vertexDistances.get(v) <= min){ 
                min = vertexDistances.get(v); 
                vertex = v; 
            } 
        }
  
        return vertex; 
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
