package application.model;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DataModel {
	
	@SuppressWarnings("rawtypes")
	private ObservableList<UndirectedNonWeightedGraph> undirectedNonWeightedGraphData = FXCollections.observableArrayList();
	
	@SuppressWarnings("rawtypes")
	private ObservableList<UndirectedWeightedGraph> undirectedWeightedGraphData = FXCollections.observableArrayList();
	
	@SuppressWarnings("rawtypes")
	private ObservableList<DirectedNonWeightedGraph> directedNonWeightedGraphData = FXCollections.observableArrayList();
	
	@SuppressWarnings("rawtypes")
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
		
		listOfIntVertices = new ArrayList<Vertex<Integer>>();
		listOfDoubleVertices = new ArrayList<Vertex<Double>>();
		listOfStringVertices = new ArrayList<Vertex<String>>();
	}
	
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

}
