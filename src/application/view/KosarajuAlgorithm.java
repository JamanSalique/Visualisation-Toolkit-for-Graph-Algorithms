package application.view;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Stack;

import application.model.DirectedNonWeightedGraph;
import application.model.DirectedWeightedGraph;
import application.model.UndirectedNonWeightedGraph;
import application.model.Vertex;
import javafx.animation.SequentialTransition;
import javafx.animation.Animation.Status;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Tab;
import javafx.scene.paint.Color;
import javafx.util.Pair;

public class KosarajuAlgorithm<T extends Comparable<? super T>> {
	
	private GraphPanelController gpc;
	
	private SequentialTransition mainAnimation;

	
	private Animations animations;
	
	public KosarajuAlgorithm(GraphPanelController gpc) {
		this.gpc = gpc;
		mainAnimation = new SequentialTransition();
		mainAnimation.rateProperty().bind(gpc.getAnimationSpeedSlider().valueProperty());

		
		animations = new Animations(gpc);
	}
	
	public void performKosarajuAlgorithmDirectedNonWeighted(DirectedNonWeightedGraph<T> graph){
		
		Stack<T> s = new Stack<T>();
		ArrayList<T> visitedVertices = new ArrayList<T>();
		HashMap<Vertex<T>,Set<Vertex<T>>> adjList = graph.getAdjacencyList();
		HashMap<Vertex<T>,Set<Vertex<T>>> tranposeGraph = getTranposeGraphDirectedNonWeightedGraph(adjList);
		ArrayList<Color> listOfColors = new ArrayList<Color>();
		listOfColors.add(Color.RED);listOfColors.add(Color.BLUE);listOfColors.add(Color.GREEN);listOfColors.add(Color.PURPLE);listOfColors.add(Color.YELLOW);
		
		for(Vertex<T> v:adjList.keySet()) {
			
			if(!visitedVertices.contains(v.getElement())) {
				
				fillOrderDirectedNonWeightedGraph(graph,v.getElement(), visitedVertices, s);
				
			}
			
		}
		
		visitedVertices.clear();
		int colorIndex = 0;
		
		while(!s.isEmpty()) {
			
			T vertexPopped = s.pop();
			
			if(!visitedVertices.contains(vertexPopped)) {
				
				dfsUtilDirectedNonWeightedGraph(graph,tranposeGraph,vertexPopped,visitedVertices,listOfColors.get(colorIndex));
				colorIndex++;
				System.out.println(""); 
				
			}
			
		}
		
		
	}
	
	public void performKosarajuAlgorithmDirectedWeighted(DirectedWeightedGraph<T> graph){
		
		Stack<T> s = new Stack<T>();
		ArrayList<T> visitedVertices = new ArrayList<T>();
		HashMap<Vertex<T>, Set<Pair<Vertex<T>, Double>>> adjList = graph.getAdjacencyList();
		HashMap<Vertex<T>,Set<Vertex<T>>> adjListWithoutWeights = new HashMap<Vertex<T>,Set<Vertex<T>>>();
		
		for(Entry<Vertex<T>, Set<Pair<Vertex<T>, Double>>> e: adjList.entrySet()) {
			
			HashSet<Vertex<T>> setOfNeighbours = new HashSet<Vertex<T>>();
			
			adjListWithoutWeights.put(e.getKey(), setOfNeighbours);
			
			for(Pair<Vertex<T>, Double> p : adjList.get(e.getKey())) {
				
				setOfNeighbours.add(p.getKey());
				
			}
			
		}
		
		HashMap<Vertex<T>,Set<Vertex<T>>> tranposeGraph = getTranposeGraphDirectedNonWeightedGraph(adjListWithoutWeights);
		ArrayList<Color> listOfColors = new ArrayList<Color>();
		listOfColors.add(Color.RED);listOfColors.add(Color.BLUE);listOfColors.add(Color.GREEN);listOfColors.add(Color.PURPLE);listOfColors.add(Color.YELLOW);
		
		for(Vertex<T> v:adjList.keySet()) {
			
			if(!visitedVertices.contains(v.getElement())) {
				
				fillOrderDirectedWeightedGraph(graph,v.getElement(), visitedVertices, s);
				
			}
			
		}
		
		visitedVertices.clear();
		int colorIndex = 0;
		
		while(!s.isEmpty()) {
			
			T vertexPopped = s.pop();
			
			if(!visitedVertices.contains(vertexPopped)) {
				
				dfsUtilDirectedWeightedGraph(graph,tranposeGraph,vertexPopped,visitedVertices,listOfColors.get(colorIndex));
				colorIndex++;
				System.out.println(""); 
				
			}
			
		}
		
		
	}
	
	private void dfsUtilDirectedNonWeightedGraph(DirectedNonWeightedGraph<T> graph,HashMap<Vertex<T>,Set<Vertex<T>>> adjList,T vertex,ArrayList<T> visitedVertices,
													Color color) {
		
		visitedVertices.add(vertex);
		System.out.print(vertex + " "); 
		
		Iterable<Vertex<T>> neighboursIterable = adjList.get(graph.returnVertex(vertex));
		ArrayList<Vertex<T>> listOfNeighbours = new ArrayList<Vertex<T>>();
		neighboursIterable.forEach(listOfNeighbours::add);
		ArrayList<Vertex<T>> sortedListOfNeighbours = sortList(listOfNeighbours);
        mainAnimation.getChildren().add(animations.fillVertexTransitionChooseColour(
        		vertex.toString(),"Directed Non Weighted",color));
		
		for(Vertex<T> v :sortedListOfNeighbours) {
			
			if(!visitedVertices.contains(v.getElement())) {
				
				dfsUtilDirectedNonWeightedGraph(graph,adjList,v.getElement(),visitedVertices,color);
				
			}
			
		}
		
		
	}
	
	private HashMap<Vertex<T>,Set<Vertex<T>>> getTranposeGraphDirectedNonWeightedGraph(HashMap<Vertex<T>,Set<Vertex<T>>> adjList) {
		
		HashMap<Vertex<T>,Set<Vertex<T>>> toReturn = new HashMap<Vertex<T>,Set<Vertex<T>>>();
		
		for(Vertex<T> v: adjList.keySet()) {
			
			toReturn.put(v, new HashSet<Vertex<T>>());
			
		}
		
		for(Entry<Vertex<T>,Set<Vertex<T>>> entry: adjList.entrySet()) {
			
			for(Vertex<T> neighbour: entry.getValue()) {
				
				toReturn.get(neighbour).add(entry.getKey());
				
			}
			
		}

		return toReturn;
		
	}
	
	private void fillOrderDirectedNonWeightedGraph(DirectedNonWeightedGraph<T> graph,T vertex,ArrayList<T> visitedVertices, Stack s) {
		
		visitedVertices.add(vertex);
		
		Iterable<Vertex<T>> neighboursIterable = graph.getNeighbours(vertex);
		ArrayList<Vertex<T>> listOfNeighbours = new ArrayList<Vertex<T>>();
		neighboursIterable.forEach(listOfNeighbours::add);
		ArrayList<Vertex<T>> sortedListOfNeighbours = sortList(listOfNeighbours);

		for(Vertex<T> v :sortedListOfNeighbours) {
			
			if(!visitedVertices.contains(graph.returnVertex(v.getElement()).getElement())) {
				
				fillOrderDirectedNonWeightedGraph(graph,v.getElement(),visitedVertices,s);
				
			}
			
		}
		
		T vertexToPush = vertex;
		s.push(vertexToPush);
		
	}
	
	private void fillOrderDirectedWeightedGraph(DirectedWeightedGraph<T> graph,T vertex,ArrayList<T> visitedVertices, Stack s) {
		
		visitedVertices.add(vertex);
		
		Iterable<Pair<Vertex<T>, Double>> neighboursIterable = graph.getNeighbours(vertex);
		ArrayList<Vertex<T>> listOfNeighbours = new ArrayList<Vertex<T>>();
		for(Pair<Vertex<T>, Double> p : neighboursIterable) {
			listOfNeighbours.add(p.getKey());
		}
		ArrayList<Vertex<T>> sortedListOfNeighbours = sortList(listOfNeighbours);

		for(Vertex<T> v :sortedListOfNeighbours) {
			
			if(!visitedVertices.contains(graph.returnVertex(v.getElement()).getElement())) {
				
				fillOrderDirectedWeightedGraph(graph,v.getElement(),visitedVertices,s);
				
			}
			
		}
		
		T vertexToPush = vertex;
		s.push(vertexToPush);
		
	}

	private void dfsUtilDirectedWeightedGraph(DirectedWeightedGraph<T> graph,HashMap<Vertex<T>,Set<Vertex<T>>> adjList,T vertex,ArrayList<T> visitedVertices,
			Color color) {

		visitedVertices.add(vertex);
		System.out.print(vertex + " "); 
		
		Iterable<Vertex<T>> neighboursIterable = adjList.get(graph.returnVertex(vertex));
		ArrayList<Vertex<T>> listOfNeighbours = new ArrayList<Vertex<T>>();
		neighboursIterable.forEach(listOfNeighbours::add);
		ArrayList<Vertex<T>> sortedListOfNeighbours = sortList(listOfNeighbours);
		mainAnimation.getChildren().add(animations.fillVertexTransitionChooseColour(
		vertex.toString(),"Directed Weighted",color));

		for(Vertex<T> v :sortedListOfNeighbours) {

			if(!visitedVertices.contains(v.getElement())) {

				dfsUtilDirectedWeightedGraph(graph,adjList,v.getElement(),visitedVertices,color);

			}

		}


	}
	
	
	@SuppressWarnings("hiding")
	private <T extends Comparable<? super T>> ArrayList<Vertex<T>> sortList(ArrayList<Vertex<T>> listOfNeigbours) { 

		listOfNeigbours.sort(Comparator.comparing(Vertex::getElement)); 
		
		return (ArrayList<Vertex<T>>) listOfNeigbours;
		
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
