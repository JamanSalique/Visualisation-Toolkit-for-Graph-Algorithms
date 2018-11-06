package application.view;

import application.Main;
import application.model.DirectedNonWeightedGraph;
import application.model.DirectedWeightedGraph;
import application.model.UndirectedNonWeightedGraph;
import application.model.UndirectedWeightedGraph;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TabPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

public class GraphPanelController {

	private Main main;
	
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
	
	@FXML
	private Pane centerPane;
	
	@FXML
	private ChoiceBox<String> choiceBox;
	
	@FXML
	private TabPane tabs;

	
	@FXML
	private void initialize() {
		
		choiceBox.getItems().addAll("Integer","Double","String");
		choiceBox.getSelectionModel().selectFirst();

	}
	
	public void setMain(Main main) {
		this.main = main;
		
		// all data from observable lists to data in this class.
	}
	
	@FXML
	private void handleUndirectedNonWeightedGraphAddVertex(MouseEvent event) {

		boolean okClicked = main.showAddVertexDataUndirectedNonWeightedGraph(this);
		
		if(okClicked) {
			
			String vertexText = "";
			
			if(getSelectedDataChoice().equals("Integer")) {
				
				vertexText = main.getListOfIntVertices().get(main.getListOfIntVertices().size() - 1).toString();
				
			}else if(getSelectedDataChoice().equals("Double")){
				
				vertexText = main.getListOfDoubleVertices().get(main.getListOfDoubleVertices().size() - 1).toString();
				
			}else {
				
				vertexText = main.getListOfStringVertices().get(main.getListOfStringVertices().size() - 1).toString();
				
			}
			
			
       		double x = event.getX();
        	double y = event.getY();
   			Circle vertex = new Circle(x, y, 20, Color.RED);
   				
   			final Text text = new Text (vertexText);
   			final StackPane stack = new StackPane();
   			stack.getChildren().addAll(vertex, text);
   			stack.setLayoutX(x);
   			stack.setLayoutY(y);
   			centerPane.getChildren().add(stack);
   			
   			}
	}
	
	public String getSelectedDataChoice() {

		return choiceBox.getSelectionModel().getSelectedItem();

	}
	
	public String getSelectedTabName() {

		return tabs.getSelectionModel().getSelectedItem().getText();

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

	

}
