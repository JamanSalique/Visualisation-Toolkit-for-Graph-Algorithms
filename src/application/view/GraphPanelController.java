package application.view;

import application.Main;
import application.model.UndirectedNonWeightedGraph;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

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
	}
	
	public void setMain(Main main) {
		this.main = main;
		
		// all data from observable lists to data in this class.
	}
	
	@FXML
	private void handleUndirectedNonWeightedGraphAddVertex(MouseEvent event) {
		UndirectedNonWeightedGraph g = new UndirectedNonWeightedGraph();

		boolean okClicked = main.showAddVertexDataUndirectedNonWeightedGraph(this);
		
		if(okClicked) {
        		double x = event.getX();
        		double y = event.getY();
   				Circle vertex = new Circle(x, y, 20, Color.BLACK);
   				centerPane.getChildren().add(vertex);

   				
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
