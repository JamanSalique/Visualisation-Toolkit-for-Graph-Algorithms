package application.model;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * This class holds all the data for all 4 types of graphs: Undirected Non-Weighted, Undirected Weighted, Directed Non-Weighted, Directed Weighted graphs.
 * For each graph the vertices can hold either Integer, Double or String data, this data is held separate from each other.
 * @author jamansalique
 *
 */
public class DataModel implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	
	// Store all data as observable lists so data can easily be tracked for changes and as a result correct updates to the data can be made.
	@SuppressWarnings("rawtypes")
	private ObservableList<UndirectedNonWeightedGraph> undirectedNonWeightedGraphData = FXCollections.observableArrayList();
	
	@SuppressWarnings("rawtypes")
	private ObservableList<UndirectedWeightedGraph> undirectedWeightedGraphData = FXCollections.observableArrayList();
	
	@SuppressWarnings("rawtypes")
	private ObservableList<DirectedNonWeightedGraph> directedNonWeightedGraphData = FXCollections.observableArrayList();
	
	@SuppressWarnings("rawtypes")
	private ObservableList<DirectedWeightedGraph> directedWeightedGraphData = FXCollections.observableArrayList();
	
	private ObservableList<Vertex<Integer>> vertexDataUndirectedNonWeightedInt = FXCollections.observableArrayList();
	private ObservableList<Vertex<Double>> vertexDataUndirectedNonWeightedDouble = FXCollections.observableArrayList();
	private ObservableList<Vertex<String>> vertexDataUndirectedNonWeightedString = FXCollections.observableArrayList();
	
	private ObservableList<Vertex<Integer>> vertexDataUndirectedWeightedInt = FXCollections.observableArrayList();
	private ObservableList<Vertex<Double>> vertexDataUndirectedWeightedDouble = FXCollections.observableArrayList();
	private ObservableList<Vertex<String>> vertexDataUndirectedWeightedString = FXCollections.observableArrayList();
	
	private ObservableList<Vertex<Integer>> vertexDataDirectedNonWeightedInt = FXCollections.observableArrayList();
	private ObservableList<Vertex<Double>> vertexDataDirectedNonWeightedDouble = FXCollections.observableArrayList();
	private ObservableList<Vertex<String>> vertexDataDirectedNonWeightedString = FXCollections.observableArrayList();
	
	private ObservableList<Vertex<Integer>> vertexDataDirectedWeightedInt = FXCollections.observableArrayList();
	private ObservableList<Vertex<Double>> vertexDataDirectedWeightedDouble = FXCollections.observableArrayList();
	private ObservableList<Vertex<String>> vertexDataDirectedWeightedString = FXCollections.observableArrayList();
	
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
	
	private ArrayList<Vertex<Integer>> listOfUndirectedNonWeightedIntVertices; 
	private ArrayList<Vertex<Double>> listOfUndirectedNonWeightedDoubleVertices; 
	private ArrayList<Vertex<String>> listOfUndirectedNonWeightedStringVertices; 
	
	private ArrayList<Vertex<Integer>> listOfUndirectedWeightedIntVertices; 
	private ArrayList<Vertex<Double>> listOfUndirectedWeightedDoubleVertices; 
	private ArrayList<Vertex<String>> listOfUndirectedWeightedStringVertices; 
	
	private ArrayList<Vertex<Integer>> listOfDirectedNonWeightedIntVertices; 
	private ArrayList<Vertex<Double>> listOfDirectedNonWeightedDoubleVertices; 
	private ArrayList<Vertex<String>> listOfDirectedNonWeightedStringVertices; 
	
	private ArrayList<Vertex<Integer>> listOfDirectedWeightedIntVertices; 
	private ArrayList<Vertex<Double>> listOfDirectedWeightedDoubleVertices; 
	private ArrayList<Vertex<String>> listOfDirectedWeightedStringVertices; 
	
	/**
	 * Constructor that initialises all fields.
	 */
	public DataModel() {
		
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
		
		listOfUndirectedNonWeightedIntVertices = new ArrayList<Vertex<Integer>>();
		listOfUndirectedNonWeightedDoubleVertices = new ArrayList<Vertex<Double>>();
		listOfUndirectedNonWeightedStringVertices = new ArrayList<Vertex<String>>();
		
		listOfUndirectedWeightedIntVertices = new ArrayList<Vertex<Integer>>();
		listOfUndirectedWeightedDoubleVertices = new ArrayList<Vertex<Double>>();
		listOfUndirectedWeightedStringVertices = new ArrayList<Vertex<String>>();
		
		listOfDirectedNonWeightedIntVertices = new ArrayList<Vertex<Integer>>();
		listOfDirectedNonWeightedDoubleVertices = new ArrayList<Vertex<Double>>();
		listOfDirectedNonWeightedStringVertices = new ArrayList<Vertex<String>>();
		
		listOfDirectedWeightedIntVertices = new ArrayList<Vertex<Integer>>();
		listOfDirectedWeightedDoubleVertices = new ArrayList<Vertex<Double>>();
		listOfDirectedWeightedStringVertices = new ArrayList<Vertex<String>>();
	}
	
	//ONLY GETTERS AND SETTERS BELOW...
	
	@SuppressWarnings("rawtypes")
	public ObservableList<UndirectedNonWeightedGraph> getUndirectedNonWeightedGraphData(){
		return undirectedNonWeightedGraphData;
	}
	
	@SuppressWarnings("rawtypes")
	public ObservableList<UndirectedWeightedGraph> getUndirectedWeightedGraphData(){
		return undirectedWeightedGraphData;
	}
	
	@SuppressWarnings("rawtypes")
	public ObservableList<DirectedNonWeightedGraph> getDirectedNonWeightedGraphData(){
		return directedNonWeightedGraphData;
	}
	
	@SuppressWarnings("rawtypes")
	public ObservableList<DirectedWeightedGraph> getDirectedWeightedGraphData(){
		return directedWeightedGraphData;
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
	@SuppressWarnings("rawtypes")
	public void setUndirectedNonWeightedGraphData(
			ObservableList<UndirectedNonWeightedGraph> undirectedNonWeightedGraphData) {
		this.undirectedNonWeightedGraphData = undirectedNonWeightedGraphData;
	}

	/**
	 * @param undirectedWeightedGraphData the undirectedWeightedGraphData to set
	 */
	@SuppressWarnings("rawtypes")
	public void setUndirectedWeightedGraphData(ObservableList<UndirectedWeightedGraph> undirectedWeightedGraphData) {
		this.undirectedWeightedGraphData = undirectedWeightedGraphData;
	}

	/**
	 * @param directedNonWeightedGraphData the directedNonWeightedGraphData to set
	 */
	@SuppressWarnings("rawtypes")
	public void setDirectedNonWeightedGraphData(ObservableList<DirectedNonWeightedGraph> directedNonWeightedGraphData) {
		this.directedNonWeightedGraphData = directedNonWeightedGraphData;
	}

	/**
	 * @param directedWeightedGraphData the directedWeightedGraphData to set
	 */
	@SuppressWarnings("rawtypes")
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
	 * @return the vertexDataUndirectedNonWeightedInt
	 */
	public ObservableList<Vertex<Integer>> getVertexDataUndirectedNonWeightedInt() {
		return vertexDataUndirectedNonWeightedInt;
	}

	/**
	 * @return the vertexDataUndirectedNonWeightedDouble
	 */
	public ObservableList<Vertex<Double>> getVertexDataUndirectedNonWeightedDouble() {
		return vertexDataUndirectedNonWeightedDouble;
	}

	/**
	 * @return the vertexDataUndirectedNonWeightedString
	 */
	public ObservableList<Vertex<String>> getVertexDataUndirectedNonWeightedString() {
		return vertexDataUndirectedNonWeightedString;
	}

	/**
	 * @return the vertexDataUndirectedWeightedInt
	 */
	public ObservableList<Vertex<Integer>> getVertexDataUndirectedWeightedInt() {
		return vertexDataUndirectedWeightedInt;
	}

	/**
	 * @return the vertexDataUndirectedWeightedDouble
	 */
	public ObservableList<Vertex<Double>> getVertexDataUndirectedWeightedDouble() {
		return vertexDataUndirectedWeightedDouble;
	}

	/**
	 * @return the vertexDataUndirectedWeightedString
	 */
	public ObservableList<Vertex<String>> getVertexDataUndirectedWeightedString() {
		return vertexDataUndirectedWeightedString;
	}

	/**
	 * @return the vertexDataDirectedNonWeightedInt
	 */
	public ObservableList<Vertex<Integer>> getVertexDataDirectedNonWeightedInt() {
		return vertexDataDirectedNonWeightedInt;
	}

	/**
	 * @return the vertexDataDirectedNonWeightedDouble
	 */
	public ObservableList<Vertex<Double>> getVertexDataDirectedNonWeightedDouble() {
		return vertexDataDirectedNonWeightedDouble;
	}

	/**
	 * @return the vertexDataDirectedNonWeightedString
	 */
	public ObservableList<Vertex<String>> getVertexDataDirectedNonWeightedString() {
		return vertexDataDirectedNonWeightedString;
	}

	/**
	 * @return the vertexDataDirectedWeightedInt
	 */
	public ObservableList<Vertex<Integer>> getVertexDataDirectedWeightedInt() {
		return vertexDataDirectedWeightedInt;
	}

	/**
	 * @return the vertexDataDirectedWeightedDouble
	 */
	public ObservableList<Vertex<Double>> getVertexDataDirectedWeightedDouble() {
		return vertexDataDirectedWeightedDouble;
	}

	/**
	 * @return the vertexDataDirectedWeightedString
	 */
	public ObservableList<Vertex<String>> getVertexDataDirectedWeightedString() {
		return vertexDataDirectedWeightedString;
	}

	/**
	 * @return the listOfUndirectedNonWeightedIntVertices
	 */
	public ArrayList<Vertex<Integer>> getListOfUndirectedNonWeightedIntVertices() {
		return listOfUndirectedNonWeightedIntVertices;
	}

	/**
	 * @return the listOfUndirectedNonWeightedDoubleVertices
	 */
	public ArrayList<Vertex<Double>> getListOfUndirectedNonWeightedDoubleVertices() {
		return listOfUndirectedNonWeightedDoubleVertices;
	}

	/**
	 * @return the listOfUndirectedNonWeightedStringVertices
	 */
	public ArrayList<Vertex<String>> getListOfUndirectedNonWeightedStringVertices() {
		return listOfUndirectedNonWeightedStringVertices;
	}

	/**
	 * @return the listOfUndirectedWeightedIntVertices
	 */
	public ArrayList<Vertex<Integer>> getListOfUndirectedWeightedIntVertices() {
		return listOfUndirectedWeightedIntVertices;
	}

	/**
	 * @return the listOfUndirectedWeightedDoubleVertices
	 */
	public ArrayList<Vertex<Double>> getListOfUndirectedWeightedDoubleVertices() {
		return listOfUndirectedWeightedDoubleVertices;
	}

	/**
	 * @return the listOfUndirectedWeightedStringVertices
	 */
	public ArrayList<Vertex<String>> getListOfUndirectedWeightedStringVertices() {
		return listOfUndirectedWeightedStringVertices;
	}

	/**
	 * @return the listOfDirectedNonWeightedIntVertices
	 */
	public ArrayList<Vertex<Integer>> getListOfDirectedNonWeightedIntVertices() {
		return listOfDirectedNonWeightedIntVertices;
	}

	/**
	 * @return the listOfDirectedNonWeightedDoubleVertices
	 */
	public ArrayList<Vertex<Double>> getListOfDirectedNonWeightedDoubleVertices() {
		return listOfDirectedNonWeightedDoubleVertices;
	}

	/**
	 * @return the listOfDirectedNonWeightedStringVertices
	 */
	public ArrayList<Vertex<String>> getListOfDirectedNonWeightedStringVertices() {
		return listOfDirectedNonWeightedStringVertices;
	}

	/**
	 * @return the listOfDirectedWeightedIntVertices
	 */
	public ArrayList<Vertex<Integer>> getListOfDirectedWeightedIntVertices() {
		return listOfDirectedWeightedIntVertices;
	}

	/**
	 * @return the listOfDirectedWeightedDoubleVertices
	 */
	public ArrayList<Vertex<Double>> getListOfDirectedWeightedDoubleVertices() {
		return listOfDirectedWeightedDoubleVertices;
	}

	/**
	 * @return the listOfDirectedWeightedStringVertices
	 */
	public ArrayList<Vertex<String>> getListOfDirectedWeightedStringVertices() {
		return listOfDirectedWeightedStringVertices;
	}

	/**
	 * @param vertexDataUndirectedNonWeightedInt the vertexDataUndirectedNonWeightedInt to set
	 */
	public void setVertexDataUndirectedNonWeightedInt(ObservableList<Vertex<Integer>> vertexDataUndirectedNonWeightedInt) {
		this.vertexDataUndirectedNonWeightedInt = vertexDataUndirectedNonWeightedInt;
	}

	/**
	 * @param vertexDataUndirectedNonWeightedDouble the vertexDataUndirectedNonWeightedDouble to set
	 */
	public void setVertexDataUndirectedNonWeightedDouble(
			ObservableList<Vertex<Double>> vertexDataUndirectedNonWeightedDouble) {
		this.vertexDataUndirectedNonWeightedDouble = vertexDataUndirectedNonWeightedDouble;
	}

	/**
	 * @param vertexDataUndirectedNonWeightedString the vertexDataUndirectedNonWeightedString to set
	 */
	public void setVertexDataUndirectedNonWeightedString(
			ObservableList<Vertex<String>> vertexDataUndirectedNonWeightedString) {
		this.vertexDataUndirectedNonWeightedString = vertexDataUndirectedNonWeightedString;
	}

	/**
	 * @param vertexDataUndirectedWeightedInt the vertexDataUndirectedWeightedInt to set
	 */
	public void setVertexDataUndirectedWeightedInt(ObservableList<Vertex<Integer>> vertexDataUndirectedWeightedInt) {
		this.vertexDataUndirectedWeightedInt = vertexDataUndirectedWeightedInt;
	}

	/**
	 * @param vertexDataUndirectedWeightedDouble the vertexDataUndirectedWeightedDouble to set
	 */
	public void setVertexDataUndirectedWeightedDouble(ObservableList<Vertex<Double>> vertexDataUndirectedWeightedDouble) {
		this.vertexDataUndirectedWeightedDouble = vertexDataUndirectedWeightedDouble;
	}

	/**
	 * @param vertexDataUndirectedWeightedString the vertexDataUndirectedWeightedString to set
	 */
	public void setVertexDataUndirectedWeightedString(ObservableList<Vertex<String>> vertexDataUndirectedWeightedString) {
		this.vertexDataUndirectedWeightedString = vertexDataUndirectedWeightedString;
	}

	/**
	 * @param vertexDataDirectedNonWeightedInt the vertexDataDirectedNonWeightedInt to set
	 */
	public void setVertexDataDirectedNonWeightedInt(ObservableList<Vertex<Integer>> vertexDataDirectedNonWeightedInt) {
		this.vertexDataDirectedNonWeightedInt = vertexDataDirectedNonWeightedInt;
	}

	/**
	 * @param vertexDataDirectedNonWeightedDouble the vertexDataDirectedNonWeightedDouble to set
	 */
	public void setVertexDataDirectedNonWeightedDouble(ObservableList<Vertex<Double>> vertexDataDirectedNonWeightedDouble) {
		this.vertexDataDirectedNonWeightedDouble = vertexDataDirectedNonWeightedDouble;
	}

	/**
	 * @param vertexDataDirectedNonWeightedString the vertexDataDirectedNonWeightedString to set
	 */
	public void setVertexDataDirectedNonWeightedString(ObservableList<Vertex<String>> vertexDataDirectedNonWeightedString) {
		this.vertexDataDirectedNonWeightedString = vertexDataDirectedNonWeightedString;
	}

	/**
	 * @param vertexDataDirectedWeightedInt the vertexDataDirectedWeightedInt to set
	 */
	public void setVertexDataDirectedWeightedInt(ObservableList<Vertex<Integer>> vertexDataDirectedWeightedInt) {
		this.vertexDataDirectedWeightedInt = vertexDataDirectedWeightedInt;
	}

	/**
	 * @param vertexDataDirectedWeightedDouble the vertexDataDirectedWeightedDouble to set
	 */
	public void setVertexDataDirectedWeightedDouble(ObservableList<Vertex<Double>> vertexDataDirectedWeightedDouble) {
		this.vertexDataDirectedWeightedDouble = vertexDataDirectedWeightedDouble;
	}

	/**
	 * @param vertexDataDirectedWeightedString the vertexDataDirectedWeightedString to set
	 */
	public void setVertexDataDirectedWeightedString(ObservableList<Vertex<String>> vertexDataDirectedWeightedString) {
		this.vertexDataDirectedWeightedString = vertexDataDirectedWeightedString;
	}

	/**
	 * @param listOfUndirectedNonWeightedIntVertices the listOfUndirectedNonWeightedIntVertices to set
	 */
	public void setListOfUndirectedNonWeightedIntVertices(
			ArrayList<Vertex<Integer>> listOfUndirectedNonWeightedIntVertices) {
		this.listOfUndirectedNonWeightedIntVertices = listOfUndirectedNonWeightedIntVertices;
	}

	/**
	 * @param listOfUndirectedNonWeightedDoubleVertices the listOfUndirectedNonWeightedDoubleVertices to set
	 */
	public void setListOfUndirectedNonWeightedDoubleVertices(
			ArrayList<Vertex<Double>> listOfUndirectedNonWeightedDoubleVertices) {
		this.listOfUndirectedNonWeightedDoubleVertices = listOfUndirectedNonWeightedDoubleVertices;
	}

	/**
	 * @param listOfUndirectedNonWeightedStringVertices the listOfUndirectedNonWeightedStringVertices to set
	 */
	public void setListOfUndirectedNonWeightedStringVertices(
			ArrayList<Vertex<String>> listOfUndirectedNonWeightedStringVertices) {
		this.listOfUndirectedNonWeightedStringVertices = listOfUndirectedNonWeightedStringVertices;
	}

	/**
	 * @param listOfUndirectedWeightedIntVertices the listOfUndirectedWeightedIntVertices to set
	 */
	public void setListOfUndirectedWeightedIntVertices(ArrayList<Vertex<Integer>> listOfUndirectedWeightedIntVertices) {
		this.listOfUndirectedWeightedIntVertices = listOfUndirectedWeightedIntVertices;
	}

	/**
	 * @param listOfUndirectedWeightedDoubleVertices the listOfUndirectedWeightedDoubleVertices to set
	 */
	public void setListOfUndirectedWeightedDoubleVertices(
			ArrayList<Vertex<Double>> listOfUndirectedWeightedDoubleVertices) {
		this.listOfUndirectedWeightedDoubleVertices = listOfUndirectedWeightedDoubleVertices;
	}

	/**
	 * @param listOfUndirectedWeightedStringVertices the listOfUndirectedWeightedStringVertices to set
	 */
	public void setListOfUndirectedWeightedStringVertices(
			ArrayList<Vertex<String>> listOfUndirectedWeightedStringVertices) {
		this.listOfUndirectedWeightedStringVertices = listOfUndirectedWeightedStringVertices;
	}

	/**
	 * @param listOfDirectedNonWeightedIntVertices the listOfDirectedNonWeightedIntVertices to set
	 */
	public void setListOfDirectedNonWeightedIntVertices(ArrayList<Vertex<Integer>> listOfDirectedNonWeightedIntVertices) {
		this.listOfDirectedNonWeightedIntVertices = listOfDirectedNonWeightedIntVertices;
	}

	/**
	 * @param listOfDirectedNonWeightedDoubleVertices the listOfDirectedNonWeightedDoubleVertices to set
	 */
	public void setListOfDirectedNonWeightedDoubleVertices(
			ArrayList<Vertex<Double>> listOfDirectedNonWeightedDoubleVertices) {
		this.listOfDirectedNonWeightedDoubleVertices = listOfDirectedNonWeightedDoubleVertices;
	}

	/**
	 * @param listOfDirectedNonWeightedStringVertices the listOfDirectedNonWeightedStringVertices to set
	 */
	public void setListOfDirectedNonWeightedStringVertices(
			ArrayList<Vertex<String>> listOfDirectedNonWeightedStringVertices) {
		this.listOfDirectedNonWeightedStringVertices = listOfDirectedNonWeightedStringVertices;
	}

	/**
	 * @param listOfDirectedWeightedIntVertices the listOfDirectedWeightedIntVertices to set
	 */
	public void setListOfDirectedWeightedIntVertices(ArrayList<Vertex<Integer>> listOfDirectedWeightedIntVertices) {
		this.listOfDirectedWeightedIntVertices = listOfDirectedWeightedIntVertices;
	}

	/**
	 * @param listOfDirectedWeightedDoubleVertices the listOfDirectedWeightedDoubleVertices to set
	 */
	public void setListOfDirectedWeightedDoubleVertices(ArrayList<Vertex<Double>> listOfDirectedWeightedDoubleVertices) {
		this.listOfDirectedWeightedDoubleVertices = listOfDirectedWeightedDoubleVertices;
	}

	/**
	 * @param listOfDirectedWeightedStringVertices the listOfDirectedWeightedStringVertices to set
	 */
	public void setListOfDirectedWeightedStringVertices(ArrayList<Vertex<String>> listOfDirectedWeightedStringVertices) {
		this.listOfDirectedWeightedStringVertices = listOfDirectedWeightedStringVertices;
	}

	
}
