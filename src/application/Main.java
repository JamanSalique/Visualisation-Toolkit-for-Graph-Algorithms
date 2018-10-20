package application;
	
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import application.model.UndirectedNonWeightedGraph;
import application.model.UndirectedWeightedGraph;
import application.model.Vertex;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class Main extends Application {
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
		Vertex<Integer> v1 = new Vertex<Integer>(1);
		Vertex<Integer> v2 = new Vertex<Integer>(2);
		Vertex<Integer> v3 = new Vertex<Integer>(3);
		Vertex<Integer> v4 = new Vertex<Integer>(4);
		UndirectedNonWeightedGraph<String> g = new UndirectedNonWeightedGraph<String>();
		UndirectedWeightedGraph<String> gg = new UndirectedWeightedGraph<String>();
//		g.addVertex("a");
//		g.addVertex("b");
//		g.addVertex("c");
//		g.addVertex("d");
//		
//		g.addEdge("a","b");
//		
//		
//		System.out.println(g);
		
		gg.addVertex("a");
		gg.addVertex("b");
		gg.addVertex("c");
		gg.addVertex("d");
		
		gg.addEdge("a","b",1);
		gg.addEdge("b","c",2);
		gg.addEdge("c","d",3);
		
		System.out.println(gg);
		
//		HashMap<Vertex<Integer>,Integer> adjacencyList = new HashMap<Vertex<Integer>,Integer>();
//		adjacencyList.put(v1,1);
//		adjacencyList.put(v2,1);
//		adjacencyList.put(v3,1);
//		adjacencyList.put(v4,1);
//		
//		adjacencyList.
//		System.out.println(containsVertex(1, adjacencyList));
		
	}
}
