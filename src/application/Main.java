package application;
	
import java.util.HashMap;
import java.util.Map;

import application.model.DirectedNonWeightedGraph;
import application.model.DirectedWeightedGraph;
import application.model.UndirectedNonWeightedGraph;
import application.model.UndirectedWeightedGraph;
import application.model.Vertex;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class Main extends Application {
	
	private ObservableList<UndirectedNonWeightedGraph> undirectedNonWeightedGraphData = FXCollections.observableArrayList();
	private ObservableList<UndirectedWeightedGraph> undirectedWeightedGraphData = FXCollections.observableArrayList();
	private ObservableList<DirectedNonWeightedGraph> directedNonWeightedGraphData = FXCollections.observableArrayList();
	private ObservableList<DirectedWeightedGraph> directedWeightedGraphData = FXCollections.observableArrayList();
	private ObservableList<Vertex> vertexData = FXCollections.observableArrayList();
	
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static boolean containsVertex(Integer element,HashMap<Vertex<Integer>,Integer> adjacencyList) {
		for (Map.Entry<Vertex<Integer>,Integer> entry : adjacencyList.entrySet()) {
			if(entry.getKey().getElement() == element) {
				return true;
			}
		}
		return false;
	}
	
	public static void main(String[] args) {
		//launch(args);
		Vertex<String> v1 = new Vertex<String>("a");
		Vertex<String> v2 = new Vertex<String>("a");
		Vertex<Integer> v3 = new Vertex<Integer>(3);
		Vertex<Integer> v4 = new Vertex<Integer>(3);
		
		UndirectedWeightedGraph<String> uw = new UndirectedWeightedGraph<String>();
		DirectedWeightedGraph<String> dw = new DirectedWeightedGraph<String>();
		UndirectedNonWeightedGraph<String> unw = new UndirectedNonWeightedGraph<String>();
		DirectedNonWeightedGraph<String> dnw = new DirectedNonWeightedGraph<String>();
		
		unw.addVertex("a");
		unw.addVertex("b");
		unw.addVertex("c");
		unw.addVertex("d");
		unw.addEdge("a","b");
		unw.addEdge("b","c");
		unw.addEdge("c","d");
		unw.addEdge("d","c");
		unw.removeEdge("c","d");
		
		dnw.addVertex("a");
		dnw.addVertex("b");
		dnw.addVertex("c");
		dnw.addVertex("d");
		dnw.addEdge("a","b");
		dnw.addEdge("b","c");
		dnw.addEdge("c","d");
		dnw.addEdge("d","c");
		dnw.removeEdge("a","b");
		
		uw.addVertex("a");
		uw.addVertex("b");
		uw.addVertex("c");
		uw.addVertex("d");
		uw.addEdge("a","b",1);
		uw.addEdge("b","c",2);
		uw.addEdge("c","d",3);
		uw.addEdge("d","c",4);
		uw.removeEdge("d","c");
		
		dw.addVertex("a");
		dw.addVertex("b");
		dw.addVertex("c");
		dw.addVertex("d");
		dw.addEdge("a","b",1);
		dw.addEdge("b","c",2);
		dw.addEdge("c","d",3);
		dw.addEdge("d","c",4);
		dw.removeEdge("b", "a");
		
		System.out.println(unw);
		System.out.println(dnw);
		
		System.out.println(uw);
		System.out.println(dw);
		
		System.out.println(v1.equals(v2));
		System.out.println(v3.equals(v4));
		
		
	}
}
