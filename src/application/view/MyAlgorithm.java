package application.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Set;

import application.model.DirectedNonWeightedGraph;
import application.model.DirectedWeightedGraph;
import application.model.UndirectedNonWeightedGraph;
import application.model.UndirectedWeightedGraph;
import application.model.Vertex;
import javafx.animation.Animation.Status;
import javafx.animation.SequentialTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Tab;
import javafx.util.Pair;

public class MyAlgorithm<T extends Comparable<? super T>> {
	
private GraphPanelController gpc;
	
	private SequentialTransition mainAnimation;

	
	private Animations animations;
	
	public MyAlgorithm(GraphPanelController gpc) {
		this.gpc = gpc;
		mainAnimation = new SequentialTransition();
		mainAnimation.rateProperty().bind(gpc.getAnimationSpeedSlider().valueProperty());

		
		animations = new Animations(gpc);
	}
	
	/**
	 * This function will hold the algorithm that is implemented by the user. This algorithm can be visualised on the 
	 * gui for undirected non-weighted graphs.
	 * @param graph, The graph this algorithm can be visualised on.
	 * @param startingVertex, The starting vertex of the algorithm (does not have to be used).
	 */
	public void performMyAlgorithmUndirectedNonWeighted(UndirectedNonWeightedGraph<T> graph, T startingVertex){
		
		//DO NOT REMOVE
		if(mainAnimation.getChildren().size()>0) {
			mainAnimation.getChildren().clear();
		}
		
		//DO NOT REMOVE
		animations.resetGraphColours("Undirected Non Weighted");
		
		//DO NOT CHANGE THIS STRING!!
		String graphType = "Undirected Non Weighted";
		
		//Adjacency matrix of graph which can be used in your algorithm. Where the key of the hash map is a vertex and value is a set of vertices that
		//is adjacent to this vertex
		HashMap<Vertex<T>,Set<Vertex<T>>> adjList = graph.getAdjacencyList();
		
		//YOUR ALGORITHM STARTS HERE...
		
		//SAMPLE BREADTH FIRST SEARCH ALGORITHM...
		
		//current vertex that is being checked in traversal.
		T currentVertex;
		//List of vertices in bfs traversal order.
		ArrayList<T> traversalOrder = new ArrayList<T>();
		//List of vertices that have been visited.
		ArrayList<T> visitedVertices = new ArrayList<T>();
		//queue data structure
		LinkedList<T> queue = new LinkedList<T>(); 
		 
		visitedVertices.add(startingVertex);
		queue.add(startingVertex);

		//Calling this method will do a fill animation on the vertex supplied as parameter, in this case the starting vertex.
		fillVertexAnimation(graphType,startingVertex);
		 
		//Until queue is empty do...
		while (queue.size() != 0) {
			
			//Retrieve vertex that is start of the queue
			currentVertex = queue.poll();
			//Add the current vertex to the traversal order list.
			traversalOrder.add(currentVertex);

			//Retrieve a list of vertices that is adjacent (or neighbours of) the current vertex.
			ArrayList<T> sortedListOfNeighbours = getNeighboursOfVertexOrderedUndirectedNonWeighted(graph,currentVertex);
			
			//For each neighbour vertex of current vertex do...
			for(T v :sortedListOfNeighbours) {
				
				//check if the neighbour vertex is in visited.
				if (!visitedVertices.contains(v)) {
					//if it had not been visited then add the vertex to visitied vertices and add the vertex to end of queue.
					visitedVertices.add(v);
	                queue.add(v); 

	                //Calling this method will highlight an edge between 2 vertices supplied in the parameter
	                //in this case we will highlight the edge between the starting vertex and the neighbour vertex.
	                highlightEdgeAnimation(graphType,currentVertex,v);
	                
	                //Calling this method will do a fill animation on the vertex supplied as parameter, in this case the neighbour vertex.
	                fillVertexAnimation(graphType,v);
	                
				} 
				
			}
			
		}
		
		//This will output a string to the text area in the bottom right of the gui. We will output the BFS traversal order.
		gpc.getOutputBox().setText("BFS Traversal Order: " + Arrays.toString(traversalOrder.toArray()));
	}
	
	/**
	 * This function will hold the algorithm that is implemented by the user. This algorithm can be visualised on the 
	 * gui for directed non-weighted graphs.
	 * @param graph The graph this algorithm can be visualised on.
	 * @param startingVertex The starting vertex of the algorithm (does not have to be used).
	 */
	public void performMyAlgorithmDirectedNonWeighted(DirectedNonWeightedGraph<T> graph, T startingVertex){
		
		//DO NOT REMOVE
		if(mainAnimation.getChildren().size()>0) {
			mainAnimation.getChildren().clear();
		}
		
		//DO NOT REMOVE
		animations.resetGraphColours("Directed Non Weighted");
		
		//DO NOT CHANGE THIS STRING!!
		String graphType = "Directed Non Weighted";
		
		//Adjacency matrix of graph which can be used in your algorithm. Where the key of the hash map is a vertex and value is a set of vertices that
		//is adjacent to this vertex
		HashMap<Vertex<T>,Set<Vertex<T>>> adjList = graph.getAdjacencyList();
		
		//YOUR ALGORITHM STARTS HERE...
		
		//SAMPLE BREADTH FIRST SEARCH ALGORITHM. You can use this as a guideline to help build your own algorithm to be visualised.
		
		//current vertex that is being checked in traversal.
		T currentVertex;
		//List of vertices in bfs traversal order.
		ArrayList<T> traversalOrder = new ArrayList<T>();
		//List of vertices that have been visited.
		ArrayList<T> visitedVertices = new ArrayList<T>();
		//queue data structure
		LinkedList<T> queue = new LinkedList<T>(); 
		 
		visitedVertices.add(startingVertex);
		queue.add(startingVertex);

		//Calling this method will do a fill animation on the vertex supplied as parameter, in this case the starting vertex.
		fillVertexAnimation(graphType,startingVertex);
		 
		//Until queue is empty do...
		while (queue.size() != 0) {
			
			//Retrieve vertex that is start of the queue
			currentVertex = queue.poll();
			//Add the current vertex to the traversal order list.
			traversalOrder.add(currentVertex);

			//Retrieve a list of vertices that is adjacent (or neighbours of) the current vertex.
			ArrayList<T> sortedListOfNeighbours = getNeighboursOfVertexOrderedDirectedNonWeighted(graph,currentVertex);
			
			//For each neighbour vertex of current vertex do...
			for(T v :sortedListOfNeighbours) {
				
				//check if the neighbour vertex is in visited.
				if (!visitedVertices.contains(v)) {
					//if it had not been visited then add the vertex to visitied vertices and add the vertex to end of queue.
					visitedVertices.add(v);
	                queue.add(v); 

	                //Calling this method will highlight an edge between 2 vertices supplied in the parameter
	                //in this case we will highlight the edge between the starting vertex and the neighbour vertex.
	                highlightEdgeAnimation(graphType,currentVertex,v);
	                
	                //Calling this method will do a fill animation on the vertex supplied as parameter, in this case the neighbour vertex.
	                fillVertexAnimation(graphType,v);
	                
				} 
				
			}
			
		}
		
		//This will output a string to the text area in the bottom right of the gui. We will output the BFS traversal order.
		gpc.getOutputBox().setText("BFS Traversal Order: " + Arrays.toString(traversalOrder.toArray()));
	}

	/**
	 * This function will hold the algorithm that is implemented by the user. This algorithm can be visualised on the 
	 * gui for undirected weighted graphs.
	 * @param graph The graph this algorithm can be visualised on.
	 * @param startingVertex The starting vertex of the algorithm (does not have to be used).
	 */
	public void performMyAlgorithmUndirectedWeighted(UndirectedWeightedGraph<T> graph, T startingVertex){
		
		//DO NOT REMOVE
		if(mainAnimation.getChildren().size()>0) {
			mainAnimation.getChildren().clear();
		}
		
		//DO NOT REMOVE
		animations.resetGraphColours("Undirected Non Weighted");
		
		//DO NOT CHANGE THIS STRING!!
		String graphType = "Undirected Weighted";
		
		//Adjacency matrix of graph which can be used in your algorithm. Where the key of the hash map is a vertex and value is a set of pairs
		//where the key in the pair is the vertex adjacent to the vertex in the key of the hash map and the value of the pair is the weight of the edge
		//between the vertex in the key of the hash map and the vertex in the key of the pair.
		HashMap<Vertex<T>,Set<Pair<Vertex<T>,Double>>> adjList = graph.getAdjacencyList();
		
		//YOUR ALGORITHM STARTS HERE...
		
		//SAMPLE BREADTH FIRST SEARCH ALGORITHM. You can use this as a guideline to help build your own algorithm to be visualised.
		
		//current vertex that is being checked in traversal.
		T currentVertex;
		//List of vertices in bfs traversal order.
		ArrayList<T> traversalOrder = new ArrayList<T>();
		//List of vertices that have been visited.
		ArrayList<T> visitedVertices = new ArrayList<T>();
		//queue data structure
		LinkedList<T> queue = new LinkedList<T>(); 
		 
		visitedVertices.add(startingVertex);
		queue.add(startingVertex);

		//Calling this method will do a fill animation on the vertex supplied as parameter, in this case the starting vertex.
		fillVertexAnimation(graphType,startingVertex);
		 
		//Until queue is empty do...
		while (queue.size() != 0) {
			
			//Retrieve vertex that is start of the queue
			currentVertex = queue.poll();
			//Add the current vertex to the traversal order list.
			traversalOrder.add(currentVertex);

			//Retrieve a list of vertices that is adjacent (or neighbours of) the current vertex.
			ArrayList<T> sortedListOfNeighbours = getNeighboursOfVertexOrderedUndirectedWeighted(graph,currentVertex);
			
			//For each neighbour vertex of current vertex do...
			for(T v :sortedListOfNeighbours) {
				
				//check if the neighbour vertex is in visited.
				if (!visitedVertices.contains(v)) {
					//if it had not been visited then add the vertex to visitied vertices and add the vertex to end of queue.
					visitedVertices.add(v);
	                queue.add(v); 

	                //Calling this method will highlight an edge between 2 vertices supplied in the parameter
	                //in this case we will highlight the edge between the starting vertex and the neighbour vertex.
	                highlightEdgeAnimation(graphType,currentVertex,v);
	                
	                //Calling this method will do a fill animation on the vertex supplied as parameter, in this case the neighbour vertex.
	                fillVertexAnimation(graphType,v);
	                
				} 
				
			}
			
		}
		
		//This will output a string to the text area in the bottom right of the gui. We will output the BFS traversal order.
		gpc.getOutputBox().setText("BFS Traversal Order: " + Arrays.toString(traversalOrder.toArray()));
	}
	
	/**
	 * This function will hold the algorithm that is implemented by the user. This algorithm can be visualised on the 
	 * gui for directed weighted graphs.
	 * @param graph The graph this algorithm can be visualised on.
	 * @param startingVertex The starting vertex of the algorithm (does not have to be used).
	 */
	public void performMyAlgorithmDirectedWeighted(DirectedWeightedGraph<T> graph, T startingVertex){
		
		//DO NOT REMOVE
		if(mainAnimation.getChildren().size()>0) {
			mainAnimation.getChildren().clear();
		}
		
		//DO NOT REMOVE
		animations.resetGraphColours("Undirected Non Weighted");
		
		//DO NOT CHANGE THIS STRING!!
		String graphType = "Directed Weighted";
		
		//Adjacency matrix of graph which can be used in your algorithm. Where the key of the hash map is a vertex and value is a set of pairs
		//where the key in the pair is the vertex adjacent to the vertex in the key of the hash map and the value of the pair is the weight of the edge
		//between the vertex in the key of the hash map and the vertex in the key of the pair.
		HashMap<Vertex<T>,Set<Pair<Vertex<T>,Double>>> adjList = graph.getAdjacencyList();
		
		//SAMPLE BREADTH FIRST SEARCH ALGORITHM. You can use this as a guideline to help build your own algorithm to be visualised.
		
		//current vertex that is being checked in traversal.
		T currentVertex;
		//List of vertices in bfs traversal order.
		ArrayList<T> traversalOrder = new ArrayList<T>();
		//List of vertices that have been visited.
		ArrayList<T> visitedVertices = new ArrayList<T>();
		//queue data structure
		LinkedList<T> queue = new LinkedList<T>(); 
		 
		visitedVertices.add(startingVertex);
		queue.add(startingVertex);

		//Calling this method will do a fill animation on the vertex supplied as parameter, in this case the starting vertex.
		fillVertexAnimation(graphType,startingVertex);
		
		//Until queue is empty do...
		while (queue.size() != 0) {
			
			//Retrieve vertex that is start of the queue
			currentVertex = queue.poll();
			//Add the current vertex to the traversal order list.
			traversalOrder.add(currentVertex);

			//Retrieve a list of vertices that is adjacent (or neighbours of) the current vertex.
			ArrayList<T> sortedListOfNeighbours = getNeighboursOfVertexOrderedDirectedWeighted(graph,currentVertex);
			
			//For each neighbour vertex of current vertex do...
			for(T v :sortedListOfNeighbours) {
				
				//check if the neighbour vertex is in visited.
				if (!visitedVertices.contains(v)) {
					//if it had not been visited then add the vertex to visitied vertices and add the vertex to end of queue.
					visitedVertices.add(v);
	                queue.add(v); 

	                //Calling this method will highlight an edge between 2 vertices supplied in the parameter
	                //in this case we will highlight the edge between the starting vertex and the neighbour vertex.
	                highlightEdgeAnimation(graphType,currentVertex,v);
	                
	                //Calling this method will do a fill animation on the vertex supplied as parameter, in this case the neighbour vertex.
	                fillVertexAnimation(graphType,v);
	                
				} 
				
			}
			
		}
		
		//This will output a string to the text area in the bottom right of the gui. We will output the BFS traversal order.
		gpc.getOutputBox().setText("BFS Traversal Order: " + Arrays.toString(traversalOrder.toArray()));
	}
	
	/**
	 * This method will sort a generic list of vertices by its element and return the sorted list of vertices.
	 * @param listOfNeigbours
	 * @return
	 */
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
	
	private void fillVertexAnimation(String graphType, T vertex) {
		mainAnimation.getChildren().add(animations.fillVertexTransition(
        		vertex.toString(),graphType));
	
		
	}
	
	private void highlightEdgeAnimation(String graphType, T vertexFrom,T vertexTo) {

		mainAnimation.getChildren().add(animations.highlightEdgeTransition(vertexFrom.toString(),
        		vertexTo.toString(), graphType));
	
		
	}
	
	private ArrayList<T> getNeighboursOfVertexOrderedUndirectedNonWeighted(UndirectedNonWeightedGraph<T> graph, T vertex){
		
		ArrayList<T> neighbours = new ArrayList<T>();
		Iterable<Vertex<T>> neighboursIterable = graph.getNeighbours(vertex);
		ArrayList<Vertex<T>> listOfNeighbours = new ArrayList<Vertex<T>>();
		neighboursIterable.forEach(listOfNeighbours::add);
		ArrayList<Vertex<T>> sortedListOfNeighbours = sortList(listOfNeighbours);
		
		for(Vertex<T> v:sortedListOfNeighbours) {
			neighbours.add(v.getElement());
		}
		
		return neighbours;
		
	}
	
	private ArrayList<T> getNeighboursOfVertexOrderedUndirectedWeighted(UndirectedWeightedGraph<T> graph, T vertex){
		
		ArrayList<T> neighbours = new ArrayList<T>();
		Iterable<Pair<Vertex<T>, Double>> neighboursIterable = graph.getNeighbours(vertex);
		ArrayList<Vertex<T>> listOfNeighbours = new ArrayList<Vertex<T>>();
		
		for(Pair<Vertex<T>, Double> p : neighboursIterable) {
			listOfNeighbours.add(p.getKey());
		}
		
		ArrayList<Vertex<T>> sortedListOfNeighbours = sortList(listOfNeighbours);
		
		for(Vertex<T> v:sortedListOfNeighbours) {
			neighbours.add(v.getElement());
		}
		
		return neighbours;
		
	}
	
	private ArrayList<T> getNeighboursOfVertexOrderedDirectedNonWeighted(DirectedNonWeightedGraph<T> graph, T vertex){
		
		ArrayList<T> neighbours = new ArrayList<T>();
		Iterable<Vertex<T>> neighboursIterable = graph.getNeighbours(vertex);
		ArrayList<Vertex<T>> listOfNeighbours = new ArrayList<Vertex<T>>();
		neighboursIterable.forEach(listOfNeighbours::add);
		ArrayList<Vertex<T>> sortedListOfNeighbours = sortList(listOfNeighbours);
		
		for(Vertex<T> v:sortedListOfNeighbours) {
			neighbours.add(v.getElement());
		}
		
		return neighbours;
		
	}
	
	private ArrayList<T> getNeighboursOfVertexOrderedDirectedWeighted(DirectedWeightedGraph<T> graph, T vertex){
		
		ArrayList<T> neighbours = new ArrayList<T>();
		Iterable<Pair<Vertex<T>, Double>> neighboursIterable = graph.getNeighbours(vertex);
		ArrayList<Vertex<T>> listOfNeighbours = new ArrayList<Vertex<T>>();
		
		for(Pair<Vertex<T>, Double> p : neighboursIterable) {
			listOfNeighbours.add(p.getKey());
		}
		
		ArrayList<Vertex<T>> sortedListOfNeighbours = sortList(listOfNeighbours);
		
		for(Vertex<T> v:sortedListOfNeighbours) {
			neighbours.add(v.getElement());
		}
		
		return neighbours;
		
	}

}
