package application;
	
import java.io.IOException;
import java.util.ArrayList;

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
	
	private ObservableList<UndirectedNonWeightedGraph> undirectedNonWeightedGraphData = FXCollections.observableArrayList();
	private ObservableList<UndirectedWeightedGraph> undirectedWeightedGraphData = FXCollections.observableArrayList();
	private ObservableList<DirectedNonWeightedGraph> directedNonWeightedGraphData = FXCollections.observableArrayList();
	private ObservableList<DirectedWeightedGraph> directedWeightedGraphData = FXCollections.observableArrayList();
	private ObservableList<Vertex<Integer>> vertexDataInt = FXCollections.observableArrayList();
	private ObservableList<Vertex<Double>> vertexDataDouble = FXCollections.observableArrayList();
	private ObservableList<Vertex<String>> vertexDataString = FXCollections.observableArrayList();
	
	private UndirectedNonWeightedGraph<Integer> undirectedNonWeightedInt;
	private UndirectedNonWeightedGraph<Double> undirectedNonWeightedDouble;
	private UndirectedNonWeightedGraph<String> undirectedNonWeightedString;
	
	private UndirectedWeightedGraph<Integer> undirectedWeightedInt;
	private UndirectedWeightedGraph<Double> undirectedWeightedDouble;
	private UndirectedWeightedGraph<String> undirectedWeightedString;
	
	private DirectedNonWeightedGraph<Integer> directedNonWeightedInt;
	private DirectedNonWeightedGraph<Double> directedNonWeightedDouble;
	private DirectedNonWeightedGraph<String> directedNonWeightedString;
	
	private DirectedWeightedGraph<Integer> directedWeightedInt;
	private DirectedWeightedGraph<Double> directedWeightedDouble;
	private DirectedWeightedGraph<String> directedWeightedString;
	
	private ArrayList<Vertex<Integer>> listOfIntVertices; 
	private ArrayList<Vertex<Double>> listOfDoubleVertices; 
	private ArrayList<Vertex<String>> listOfStringVertices; 
	
	public Main() {
		
		this.undirectedNonWeightedInt = new UndirectedNonWeightedGraph<Integer>();
		this.undirectedNonWeightedDouble = new UndirectedNonWeightedGraph<Double>();
		this.undirectedNonWeightedString = new UndirectedNonWeightedGraph<String>();
		
		this.undirectedWeightedInt = new UndirectedWeightedGraph<Integer>();
		this.undirectedWeightedDouble = new UndirectedWeightedGraph<Double>();
		this.undirectedWeightedString = new UndirectedWeightedGraph<String>();
		
		this.directedNonWeightedInt = new DirectedNonWeightedGraph<Integer>();
		this.directedNonWeightedDouble = new DirectedNonWeightedGraph<Double>();
		this.directedNonWeightedString = new DirectedNonWeightedGraph<String>();
		
		this.directedWeightedInt = new DirectedWeightedGraph<Integer>();
		this.directedWeightedDouble = new DirectedWeightedGraph<Double>();
		this.directedWeightedString = new DirectedWeightedGraph<String>();
		
		undirectedNonWeightedGraphData.addAll(undirectedNonWeightedInt, undirectedNonWeightedDouble, undirectedNonWeightedString);
		
		undirectedWeightedGraphData.addAll(undirectedWeightedInt,undirectedWeightedDouble,undirectedWeightedString);
		
		directedNonWeightedGraphData.addAll(directedNonWeightedInt,directedNonWeightedDouble,directedNonWeightedString);
		
		directedWeightedGraphData.addAll(directedWeightedInt,directedWeightedDouble,directedWeightedString);
		
		listOfIntVertices = new ArrayList<Vertex<Integer>>();
		listOfDoubleVertices = new ArrayList<Vertex<Double>>();
		listOfStringVertices = new ArrayList<Vertex<String>>();

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
	
	public boolean showAddVertexDataUndirectedNonWeightedGraph(GraphPanelController gpc) {
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

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
	}
	
	public ObservableList<UndirectedNonWeightedGraph> getUndirectedNonWeightedGraphData(){
		return undirectedNonWeightedGraphData;
	}
	
	public ObservableList<UndirectedWeightedGraph> getUndirectedWeightedGraphData(){
		return undirectedWeightedGraphData;
	}
	
	public ObservableList<DirectedNonWeightedGraph> getDirectedNonWeightedGraphData(){
		return directedNonWeightedGraphData;
	}
	
	public ObservableList<DirectedWeightedGraph> getDirectedWeightedGraphData(){
		return directedWeightedGraphData;
	}
	
	public ObservableList<Vertex<Integer>> getVertexDataInt(){
		return vertexDataInt;
	}
	
	public ObservableList<Vertex<Double>> getVertexDataDouble(){
		return vertexDataDouble;
	}
	
	public ObservableList<Vertex<String>> getVertexDataString(){
		return vertexDataString;
	}
	
	/**
	 * @return the undirectedNonWeightedInt
	 */
	public UndirectedNonWeightedGraph<Integer> getUndirectedNonWeightedInt() {
		return undirectedNonWeightedInt;
	}

	/**
	 * @return the undirectedNonWeightedDouble
	 */
	public UndirectedNonWeightedGraph<Double> getUndirectedNonWeightedDouble() {
		return undirectedNonWeightedDouble;
	}

	/**
	 * @return the undirectedNonWeightedString
	 */
	public UndirectedNonWeightedGraph<String> getUndirectedNonWeightedString() {
		return undirectedNonWeightedString;
	}

	/**
	 * @return the undirectedWeightedInt
	 */
	public UndirectedWeightedGraph<Integer> getUndirectedWeightedInt() {
		return undirectedWeightedInt;
	}

	/**
	 * @return the undirectedWeightedDouble
	 */
	public UndirectedWeightedGraph<Double> getUndirectedWeightedDouble() {
		return undirectedWeightedDouble;
	}

	/**
	 * @return the undirectedWeightedString
	 */
	public UndirectedWeightedGraph<String> getUndirectedWeightedString() {
		return undirectedWeightedString;
	}

	/**
	 * @return the directedNonWeightedInt
	 */
	public DirectedNonWeightedGraph<Integer> getDirectedNonWeightedInt() {
		return directedNonWeightedInt;
	}

	/**
	 * @return the directedNonWeightedDouble
	 */
	public DirectedNonWeightedGraph<Double> getDirectedNonWeightedDouble() {
		return directedNonWeightedDouble;
	}

	/**
	 * @return the directedNonWeightedString
	 */
	public DirectedNonWeightedGraph<String> getDirectedNonWeightedString() {
		return directedNonWeightedString;
	}

	/**
	 * @return the directedWeightedInt
	 */
	public DirectedWeightedGraph<Integer> getDirectedWeightedInt() {
		return directedWeightedInt;
	}

	/**
	 * @return the directedWeightedDouble
	 */
	public DirectedWeightedGraph<Double> getDirectedWeightedDouble() {
		return directedWeightedDouble;
	}

	/**
	 * @return the directedWeightedString
	 */
	public DirectedWeightedGraph<String> getDirectedWeightedString() {
		return directedWeightedString;
	}

	/**
	 * @param undirectedNonWeightedGraphData the undirectedNonWeightedGraphData to set
	 */
	public void setUndirectedNonWeightedGraphData(
			ObservableList<UndirectedNonWeightedGraph> undirectedNonWeightedGraphData) {
		this.undirectedNonWeightedGraphData = undirectedNonWeightedGraphData;
	}

	/**
	 * @param undirectedWeightedGraphData the undirectedWeightedGraphData to set
	 */
	public void setUndirectedWeightedGraphData(ObservableList<UndirectedWeightedGraph> undirectedWeightedGraphData) {
		this.undirectedWeightedGraphData = undirectedWeightedGraphData;
	}

	/**
	 * @param directedNonWeightedGraphData the directedNonWeightedGraphData to set
	 */
	public void setDirectedNonWeightedGraphData(ObservableList<DirectedNonWeightedGraph> directedNonWeightedGraphData) {
		this.directedNonWeightedGraphData = directedNonWeightedGraphData;
	}

	/**
	 * @param directedWeightedGraphData the directedWeightedGraphData to set
	 */
	public void setDirectedWeightedGraphData(ObservableList<DirectedWeightedGraph> directedWeightedGraphData) {
		this.directedWeightedGraphData = directedWeightedGraphData;
	}

	/**
	 * @param undirectedNonWeightedInt the undirectedNonWeightedInt to set
	 */
	public void setUndirectedNonWeightedInt(UndirectedNonWeightedGraph<Integer> undirectedNonWeightedInt) {
		this.undirectedNonWeightedInt = undirectedNonWeightedInt;
	}

	/**
	 * @param undirectedNonWeightedDouble the undirectedNonWeightedDouble to set
	 */
	public void setUndirectedNonWeightedDouble(UndirectedNonWeightedGraph<Double> undirectedNonWeightedDouble) {
		this.undirectedNonWeightedDouble = undirectedNonWeightedDouble;
	}

	/**
	 * @param undirectedNonWeightedString the undirectedNonWeightedString to set
	 */
	public void setUndirectedNonWeightedString(UndirectedNonWeightedGraph<String> undirectedNonWeightedString) {
		this.undirectedNonWeightedString = undirectedNonWeightedString;
	}

	/**
	 * @param undirectedWeightedInt the undirectedWeightedInt to set
	 */
	public void setUndirectedWeightedInt(UndirectedWeightedGraph<Integer> undirectedWeightedInt) {
		this.undirectedWeightedInt = undirectedWeightedInt;
	}

	/**
	 * @param undirectedWeightedDouble the undirectedWeightedDouble to set
	 */
	public void setUndirectedWeightedDouble(UndirectedWeightedGraph<Double> undirectedWeightedDouble) {
		this.undirectedWeightedDouble = undirectedWeightedDouble;
	}

	/**
	 * @param undirectedWeightedString the undirectedWeightedString to set
	 */
	public void setUndirectedWeightedString(UndirectedWeightedGraph<String> undirectedWeightedString) {
		this.undirectedWeightedString = undirectedWeightedString;
	}

	/**
	 * @param directedNonWeightedInt the directedNonWeightedInt to set
	 */
	public void setDirectedNonWeightedInt(DirectedNonWeightedGraph<Integer> directedNonWeightedInt) {
		this.directedNonWeightedInt = directedNonWeightedInt;
	}

	/**
	 * @param directedNonWeightedDouble the directedNonWeightedDouble to set
	 */
	public void setDirectedNonWeightedDouble(DirectedNonWeightedGraph<Double> directedNonWeightedDouble) {
		this.directedNonWeightedDouble = directedNonWeightedDouble;
	}

	/**
	 * @param directedNonWeightedString the directedNonWeightedString to set
	 */
	public void setDirectedNonWeightedString(DirectedNonWeightedGraph<String> directedNonWeightedString) {
		this.directedNonWeightedString = directedNonWeightedString;
	}

	/**
	 * @param directedWeightedInt the directedWeightedInt to set
	 */
	public void setDirectedWeightedInt(DirectedWeightedGraph<Integer> directedWeightedInt) {
		this.directedWeightedInt = directedWeightedInt;
	}

	/**
	 * @param directedWeightedDouble the directedWeightedDouble to set
	 */
	public void setDirectedWeightedDouble(DirectedWeightedGraph<Double> directedWeightedDouble) {
		this.directedWeightedDouble = directedWeightedDouble;
	}

	/**
	 * @param directedWeightedString the directedWeightedString to set
	 */
	public void setDirectedWeightedString(DirectedWeightedGraph<String> directedWeightedString) {
		this.directedWeightedString = directedWeightedString;
	}

	/**
	 * @return the listOfIntVertices
	 */
	public ArrayList<Vertex<Integer>> getListOfIntVertices() {
		return listOfIntVertices;
	}

	/**
	 * @return the listOfDoubleVertices
	 */
	public ArrayList<Vertex<Double>> getListOfDoubleVertices() {
		return listOfDoubleVertices;
	}

	/**
	 * @return the listOfStringVertices
	 */
	public ArrayList<Vertex<String>> getListOfStringVertices() {
		return listOfStringVertices;
	}

	/**
	 * @param listOfIntVertices the listOfIntVertices to set
	 */
	public void setListOfIntVertices(ArrayList<Vertex<Integer>> listOfIntVertices) {
		this.listOfIntVertices = listOfIntVertices;
	}

	/**
	 * @param listOfDoubleVertices the listOfDoubleVertices to set
	 */
	public void setListOfDoubleVertices(ArrayList<Vertex<Double>> listOfDoubleVertices) {
		this.listOfDoubleVertices = listOfDoubleVertices;
	}

	/**
	 * @param listOfStringVertices the listOfStringVertices to set
	 */
	public void setListOfStringVertices(ArrayList<Vertex<String>> listOfStringVertices) {
		this.listOfStringVertices = listOfStringVertices;
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
