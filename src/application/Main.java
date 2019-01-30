package application;
	
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import application.model.DataModel;
import application.model.DirectedNonWeightedGraph;
import application.model.DirectedWeightedGraph;
import application.model.UndirectedNonWeightedGraph;
import application.model.UndirectedWeightedGraph;
import application.model.Vertex;
import application.view.AddVertexDataController;
import application.view.GraphPanelController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Pair;


public class Main extends Application {
	
	private Stage primaryStage;
	
	private DataModel dataModel;
	private GraphPanelController controller;
	
	public Main() {
		
		dataModel = new DataModel();

	}
	
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Graph Visualiser");
        
        showGraphPanel();

	}
	
	public void showGraphPanel() {
		 try {
	            // Load person overview.
	            FXMLLoader loader = new FXMLLoader();
	            loader.setLocation(Main.class.getResource("view/GraphPanel.fxml"));
	            AnchorPane graphPanel = (AnchorPane) loader.load();

	            // Give the controller access to the main app.
	            controller = loader.getController();
	            controller.setMain(this);
	            controller.setDataModel(dataModel);
	            
	            //Show the scene containing the root layout.
	            Scene scene = new Scene(graphPanel);
	            primaryStage.setScene(scene);
	            primaryStage.show();

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	}
	
	public boolean showAddVertexPopUp(GraphPanelController gpc) {
		try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/AddVertexData.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Add vertex data");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            AddVertexDataController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setGraphPanelController(gpc);
            controller.setMain(this);
            controller.setDataModel(dataModel);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
	}

	/**
     * Returns the main stage.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }
	
	public static void main(String[] args) {
		launch(args);
//		Vertex<String> v1 = new Vertex<String>("a");
//		Vertex<String> v2 = new Vertex<String>("a");
//		Vertex<Integer> v3 = new Vertex<Integer>(3);
//		Vertex<Integer> v4 = new Vertex<Integer>(3);
//		
		UndirectedWeightedGraph<Integer> uw = new UndirectedWeightedGraph<Integer>();
		DirectedWeightedGraph<Integer> dw = new DirectedWeightedGraph<Integer>();
		UndirectedNonWeightedGraph<Integer> unw = new UndirectedNonWeightedGraph<Integer>();
		DirectedNonWeightedGraph<Integer> dnw = new DirectedNonWeightedGraph<Integer>();
//		BreadthFirstSearch<String> bfs = new BreadthFirstSearch<String>();
//		DepthFirstSearch<Integer> dfs = new DepthFirstSearch<Integer>(new GraphPanelController());
////		
//		unw.addVertex(1);
//		unw.addVertex(2);
//		unw.addVertex(3);
//		unw.addVertex(4);
//		unw.addVertex(5);
		unw.addVertex(6);
////		
//		unw.addEdge(1,2);
//		unw.addEdge(1,3);
//		unw.addEdge(2,4);
//		unw.addEdge("c","d");
//		unw.addEdge("d","e");
//		unw.addEdge("d","f");
//		
//		System.out.println(Arrays.toString((dfs.performDepthFirstSearchUndirectedNonWeighted(unw, 1)).toArray()));
//		
//		uw.addVertex(1);
//		uw.addVertex(2);
//		uw.addVertex(3);
//		uw.addVertex(4);
//		uw.addVertex(5);
//		uw.addVertex(6);
//		
//		uw.addEdge(1,2,1.0);
//		uw.addEdge(1,3,1.0);
//		uw.addEdge(2,3,1.0);
//		uw.addEdge(3,4,1.0);
//		uw.addEdge(4,5,1.0);
//		uw.addEdge(4,6,1.0);
		
//		System.out.println(Arrays.toString((bfs.performBreadthFirstSearchUndirectedWeighted(uw, 4)).toArray()));
//		
//		dnw.addVertex(1);
//		dnw.addVertex(2);
//		dnw.addVertex(3);
//		dnw.addVertex(4);
//		dnw.addVertex(5);
//		dnw.addVertex(6);
//		
//		dnw.addEdge(1,2);
//		dnw.addEdge(1,3);
//		dnw.addEdge(2,3);
//		dnw.addEdge(3,4);
//		dnw.addEdge(4,5);
//		dnw.addEdge(4,6);
//		dnw.addEdge(4,3);
//		dnw.addEdge(3,1);
//		dnw.addEdge(2,1);
//		
//		System.out.println(Arrays.toString((bfs.performBreadthFirstSearchDirectedNonWeighted(dnw, 4)).toArray()));
//		
//		dw.addVertex(1);
//		dw.addVertex(2);
//		dw.addVertex(3);
//		dw.addVertex(4);
//		dw.addVertex(5);
//		dw.addVertex(6);
//		
//		dw.addEdge(1,2,1.0);
//		dw.addEdge(1,3,1.0);
//		dw.addEdge(2,3,1.0);
//		dw.addEdge(3,4,1.0);
//		dw.addEdge(4,5,1.0);
//		dw.addEdge(4,6,1.0);
//		dw.addEdge(4,3,1.0);
//		dw.addEdge(3,1,1.0);
//		dw.addEdge(2,1,1.0);
//		
//		System.out.println(Arrays.toString((bfs.performBreadthFirstSearchDirectedWeighted(dw, 4)).toArray()));
		
////		
//		unw.addVertex("a");
//		unw.addVertex("b");
//		unw.addVertex("c");
//		unw.addVertex("d");
//		unw.addEdge("a","b");
//		unw.addEdge("b","c");
//		unw.addEdge("c","d");
//		unw.addEdge("d","c");
//		unw.removeEdge("c","d");
//		
//		dnw.addVertex("a");
//		dnw.addVertex("b");
//		dnw.addVertex("c");
//		dnw.addVertex("d");
//		dnw.addEdge("a","b");
//		dnw.addEdge("b","c");
//		dnw.addEdge("c","d");
//		dnw.addEdge("d","c");
//		//dnw.removeEdge("a","b");
//		System.out.print(dnw.isAdjacent("b", "a"));
//		
//		uw.addVertex("a");
//		uw.addVertex("b");
//		uw.addVertex("c");
//		uw.addVertex("d");
//		uw.addEdge("a","b",1);
//		uw.addEdge("b","c",2);
//		uw.addEdge("c","d",3);
//		uw.addEdge("d","c",4);
//		uw.removeEdge("d","c");
//		
//		dw.addVertex("a");
//		dw.addVertex("b");
//		dw.addVertex("c");
//		dw.addVertex("d");
//		dw.addEdge("a","b",1);
//		dw.addEdge("b","c",2);
//		dw.addEdge("c","d",3);
//		dw.addEdge("d","c",4);
//		dw.removeEdge("b", "a");
//		
//		System.out.println(unw);
//		System.out.println(dnw);
//		
//		System.out.println(uw);
//		System.out.println(dw);
//		
//		System.out.println(v1.equals(v2));
//		System.out.println(v3.equals(v4));
		
		HashMap<Integer,Double> mapOfEdges = new HashMap<Integer,Double>();
		
		mapOfEdges.put(1, 1.5);
		mapOfEdges.put(2, 1.4);
		mapOfEdges.put(3, 2.5);
		mapOfEdges.put(4, 7.5);
		
//		public void performDijkstrasAlgorithmUndirectedWeighted(UndirectedWeightedGraph<T> graph, T startingVertex,ArrayList<T> visitedVertices,HashMap<Pair<T,T>,Double> mapOfEdges){
//			
//			if (!graph.containsVertex(startingVertex)) {
//	            throw new IllegalArgumentException("Vertex doesn't exist.");
//	        }
//
//			visitedVertices.add(startingVertex);
//			
//			mainAnimation.getChildren().add(animations.fillVertexTransition(
//	        		startingVertex.toString(),"Undirected Weighted"));
//			
//			Iterable<Pair<Vertex<T>, Double>> neighboursIterable = graph.getNeighbours(startingVertex);
//			ArrayList<Pair<Vertex<T>, Double>> listOfNeighbours = new ArrayList<Pair<Vertex<T>, Double>>();
//			for(Pair<Vertex<T>, Double> p : neighboursIterable) {
//				
//				if(!visitedVertices.contains(p.getKey().getElement())) {
//					listOfNeighbours.add(p);
//				}
//				
//			}
//			
//			for(Pair<Vertex<T>, Double> p :listOfNeighbours) {
//				
//				Pair<T,T> pairOfVertices = new Pair<T,T>(startingVertex,p.getKey().getElement());
//				Pair<T,T> pairOfVerticesReversed = new Pair<T,T>(p.getKey().getElement(),startingVertex);
//				
//				if(!mapOfEdges.containsKey(pairOfVerticesReversed)) {
//					
//					mapOfEdges.put(pairOfVertices, p.getValue());
//					
//				}
//			}
//			
//			HashMap<Pair<T,T>, Double> sortedMapOfEdges = 
//					mapOfEdges.entrySet().stream()
//				    .sorted(Entry.comparingByValue())
//				    .collect(Collectors.toMap(Entry::getKey, Entry::getValue,
//				                              (e1, e2) -> e1, LinkedHashMap::new));
//
//			for(Map.Entry<Pair<T,T>, Double> x: sortedMapOfEdges.entrySet()) {
//				Pair<T,T> key = x.getKey();
//				double value = x.getValue();
//				
//				if(!visitedVertices.contains(key.getValue())) {
//					
//					System.out.println("vertex frpm: " + key.getKey());
//					System.out.println("vertex to: " + key.getValue());
//					
//					mainAnimation.getChildren().add(animations.highlightEdgeTransition(key.getKey().toString(),
//							key.getValue().toString(), "Undirected Weighted"));
//					
//					performDijkstrasAlgorithmUndirectedWeighted(graph,key.getValue(),visitedVertices,mapOfEdges);
//					
//				}
//				
//			}
//			
//		}

		
	}
}
