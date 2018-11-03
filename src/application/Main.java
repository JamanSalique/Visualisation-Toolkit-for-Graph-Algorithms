package application;
	
import java.io.IOException;

import application.model.DirectedNonWeightedGraph;
import application.model.DirectedWeightedGraph;
import application.model.UndirectedNonWeightedGraph;
import application.model.UndirectedWeightedGraph;
import application.model.Vertex;
import application.view.AddVertexDataController;
import application.view.GraphPanelController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class Main<T> extends Application {
	
	private Stage primaryStage;
	
	private ObservableList<UndirectedNonWeightedGraph<T>> undirectedNonWeightedGraphData = FXCollections.observableArrayList();
	private ObservableList<UndirectedWeightedGraph<T>> undirectedWeightedGraphData = FXCollections.observableArrayList();
	private ObservableList<DirectedNonWeightedGraph<T>> directedNonWeightedGraphData = FXCollections.observableArrayList();
	private ObservableList<DirectedWeightedGraph<T>> directedWeightedGraphData = FXCollections.observableArrayList();
	private ObservableList<Vertex<T>> vertexData = FXCollections.observableArrayList();
	
	public ObservableList<UndirectedNonWeightedGraph<T>> getUndirectedNonWeightedGraphData(){
		return undirectedNonWeightedGraphData;
	}
	
	public ObservableList<UndirectedWeightedGraph<T>> getUndirectedWeightedGraphData(){
		return undirectedWeightedGraphData;
	}
	
	public ObservableList<DirectedNonWeightedGraph<T>> getDirectedNonWeightedGraphData(){
		return directedNonWeightedGraphData;
	}
	
	public ObservableList<DirectedWeightedGraph<T>> getDirectedWeightedGraphData(){
		return directedWeightedGraphData;
	}
	
	public ObservableList<Vertex<T>> getVertexData(){
		return vertexData;
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
	            GraphPanelController controller = loader.getController();
	            controller.setMain(this);
	            
	            //Show the scene containing the root layout.
	            Scene scene = new Scene(graphPanel);
	            primaryStage.setScene(scene);
	            primaryStage.show();

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	}
	
	public boolean showAddVertexData() {
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
//		UndirectedWeightedGraph<String> uw = new UndirectedWeightedGraph<String>();
//		DirectedWeightedGraph<String> dw = new DirectedWeightedGraph<String>();
//		UndirectedNonWeightedGraph<String> unw = new UndirectedNonWeightedGraph<String>();
//		DirectedNonWeightedGraph<String> dnw = new DirectedNonWeightedGraph<String>();
//		
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
//		dnw.removeEdge("a","b");
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
		
		
	}
}
