package application.view;

import java.util.ArrayList;
import java.util.Arrays;
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

/**
 * This class holds the functionality for the Kosaraju's algorithm animation that finds the strongly connected components of directed graphs.
 * @author jamansalique
 *
 * @param <T>
 */
public class KosarajuAlgorithm<T extends Comparable<? super T>> {
	
	private GraphPanelController gpc;
	
	/* This Sequential transition will contain the sub animations that will be added throughout Kosaraju's algorithm. This will be played 
	 * on the GUI when the user wants to visualise Kosaraju's algorithm animation.
	 */
	private SequentialTransition mainAnimation;

	private Animations animations;
	
	/**
	 * Constructor...
	 * @param gpc
	 */
	public KosarajuAlgorithm(GraphPanelController gpc) {
		this.gpc = gpc;
		mainAnimation = new SequentialTransition();
		mainAnimation.rateProperty().bind(gpc.getAnimationSpeedSlider().valueProperty());
		animations = new Animations(gpc);
	}
	
	/**
	 * This method contains Kosaraju's algorithm for directed non weighted graphs with animation transitions. Throughout the 
	 * algorithm sub animations (e.g highlighting vertices/edges) are being added to the main Sequential Transition animation. 
	 * @param graph
	 * @param startingVertex
	 * @return
	 */
	public void performKosarajuAlgorithmDirectedNonWeighted(DirectedNonWeightedGraph<T> graph){
		ArrayList<ArrayList<T>> stronglyConnectedComponents = new ArrayList<ArrayList<T>>();
		
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
				
				stronglyConnectedComponents.add(dfsUtilDirectedNonWeightedGraph(graph,tranposeGraph,vertexPopped,visitedVertices,listOfColors.get(colorIndex), new ArrayList<T>()));
				colorIndex++;
				if(colorIndex == listOfColors.size()) {
					colorIndex =0;
				}
				
			}
			
		}
		
		gpc.getOutputBox().setText("Strongly Connected Components: " + Arrays.toString(stronglyConnectedComponents.toArray()));
	}
	
	/**
	 * This method contains Kosaraju's algorithm for directed weighted graphs with animation transitions. Throughout the 
	 * algorithm sub animations (e.g highlighting vertices/edges) are being added to the main Sequential Transition animation. 
	 * @param graph
	 * @param startingVertex
	 * @return
	 */
	public void performKosarajuAlgorithmDirectedWeighted(DirectedWeightedGraph<T> graph){
		ArrayList<ArrayList<T>> stronglyConnectedComponents = new ArrayList<ArrayList<T>>();
		
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
				
				stronglyConnectedComponents.add(dfsUtilDirectedWeightedGraph(graph,tranposeGraph,vertexPopped,visitedVertices,listOfColors.get(colorIndex),new ArrayList<T>()));
				
				colorIndex++;
				
				if(colorIndex == listOfColors.size()) {
					colorIndex = 0;
				}
				
			}
			
		}
		
		gpc.getOutputBox().setText("Strongly Connected Components: " + Arrays.toString(stronglyConnectedComponents.toArray()));
	}
	
	/**
	 * This method is called when we do a depth first search on the transpose graph of a directed non weighted graph.
	 * @param graph
	 * @param adjList
	 * @param vertex
	 * @param visitedVertices
	 * @param color
	 * @param stronglyConnectedComponent
	 * @return
	 */
	private ArrayList<T> dfsUtilDirectedNonWeightedGraph(DirectedNonWeightedGraph<T> graph,HashMap<Vertex<T>,Set<Vertex<T>>> adjList,T vertex,ArrayList<T> visitedVertices,
													Color color,ArrayList<T> stronglyConnectedComponent) {
		
		visitedVertices.add(vertex);
		stronglyConnectedComponent.add(vertex);
		
		Iterable<Vertex<T>> neighboursIterable = adjList.get(graph.returnVertex(vertex));
		ArrayList<Vertex<T>> listOfNeighbours = new ArrayList<Vertex<T>>();
		neighboursIterable.forEach(listOfNeighbours::add);
		ArrayList<Vertex<T>> sortedListOfNeighbours = sortList(listOfNeighbours);
        mainAnimation.getChildren().add(animations.fillVertexTransitionChooseColour(
        		vertex.toString(),"Directed Non Weighted",color));
		
		for(Vertex<T> v :sortedListOfNeighbours) {
			
			if(!visitedVertices.contains(v.getElement())) {
				
				dfsUtilDirectedNonWeightedGraph(graph,adjList,v.getElement(),visitedVertices,color,stronglyConnectedComponent);
				
			}
			
		}
		
		return stronglyConnectedComponent;
	}
	
	/**
	 * This method given a directed non weighted graph will return the transpose of this graph.
	 * @param adjList
	 * @return
	 */
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
	
	/**
	 * This method given a directed non weighted graph will do a depth first search on the graph and adds vertices to a stack when a vertex is visited.
	 * @param graph
	 * @param vertex
	 * @param visitedVertices
	 * @param s
	 */
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
	
	/**
	 * This method given a directed weighted graph will do a depth first search on the graph and adds vertices to a stack when a vertex is visited.
	 * @param graph
	 * @param vertex
	 * @param visitedVertices
	 * @param s
	 */
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

	/**
	 * This method is called when we do a depth first search on the transpose graph of a directed weighted graph.
	 * @param graph
	 * @param adjList
	 * @param vertex
	 * @param visitedVertices
	 * @param color
	 * @param stronglyConnectedComponent
	 * @return
	 */
	private ArrayList<T> dfsUtilDirectedWeightedGraph(DirectedWeightedGraph<T> graph,HashMap<Vertex<T>,Set<Vertex<T>>> adjList,T vertex,ArrayList<T> visitedVertices,
			Color color,ArrayList<T> stronglyConnectedComponent) {

		visitedVertices.add(vertex);
		stronglyConnectedComponent.add(vertex);
		
		Iterable<Vertex<T>> neighboursIterable = adjList.get(graph.returnVertex(vertex));
		ArrayList<Vertex<T>> listOfNeighbours = new ArrayList<Vertex<T>>();
		neighboursIterable.forEach(listOfNeighbours::add);
		ArrayList<Vertex<T>> sortedListOfNeighbours = sortList(listOfNeighbours);
		mainAnimation.getChildren().add(animations.fillVertexTransitionChooseColour(
		vertex.toString(),"Directed Weighted",color));

		for(Vertex<T> v :sortedListOfNeighbours) {

			if(!visitedVertices.contains(v.getElement())) {

				dfsUtilDirectedWeightedGraph(graph,adjList,v.getElement(),visitedVertices,color,stronglyConnectedComponent);

			}

		}

		return stronglyConnectedComponent;
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
	 * This method is called after one of the performKosarajuAlgorithm methods has been executed. This method plays the main animation 
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
