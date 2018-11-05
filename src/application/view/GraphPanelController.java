package application.view;

import application.Main;
import application.model.DirectedNonWeightedGraph;
import application.model.DirectedWeightedGraph;
import application.model.UndirectedNonWeightedGraph;
import application.model.UndirectedWeightedGraph;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

public class GraphPanelController {

	private Main main;
	
	@FXML
	private Pane centerPane;
	
	@FXML
	private ChoiceBox<String> choiceBox;

	
	@FXML
	private void initialize() {
		choiceBox.getItems().addAll("Integer","Double","String");
		choiceBox.getSelectionModel().selectFirst();
		UndirectedNonWeightedGraph undirectedNonWeighted= new UndirectedNonWeightedGraph();
		UndirectedWeightedGraph undirectedWeighted = new UndirectedWeightedGraph();
		DirectedNonWeightedGraph directedNonWeighted = new DirectedNonWeightedGraph();
		DirectedWeightedGraph directedWeighted = new DirectedWeightedGraph();
	}
	
	public void setMain(Main main) {
		this.main = main;
		
		// all data from observable lists to data in this class.
	}
	
	@FXML
	private void handleUndirectedNonWeightedGraphAddVertex(MouseEvent event) {

		boolean okClicked = main.showAddVertexDataUndirectedNonWeightedGraph(this);
		
		if(okClicked) {
        		double x = event.getX();
        		double y = event.getY();
   				Circle vertex = new Circle(x, y, 20, Color.RED);
   				
   				final Text text = new Text ("ggg");
   				final StackPane stack = new StackPane();
   				stack.getChildren().addAll(vertex, text);
   				stack.setLayoutX(x);
   				stack.setLayoutY(y);
   				centerPane.getChildren().add(stack);
   				
   				// add vertex to main's observable list etc...
   				//new Vertex<T> v
   				//g.addVertex(v);
   				//main.getVertexData(v);
   			}
	}
	
	public String getSelectedDataChoice() {

		return choiceBox.getSelectionModel().getSelectedItem();

	}

}
