package application;
	
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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
		Vertex v1 = new Vertex(1);
		HashMap<Vertex<Integer>,Integer> adjacencyList = new HashMap<Vertex<Integer>,Integer>();
		adjacencyList.put(v1,1);
		System.out.println(containsVertex(1, adjacencyList));
		
	}
}
