package application.view;

import application.Main;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class GraphPanelController {

	private Main main;
	
	@FXML
	private Pane centerPane;
	
	@FXML
	private void initialize() {
		
	}
	
	public void setMain(Main main) {
		this.main = main;
		
		// all data from observable lists to data in this class.
	}
	
	@FXML
	private void handleUndirectedNonWeightedGraphAddVertex() {
		boolean okClicked = main.showAddVertexData();
		
		if(okClicked) {
			Circle vertex = new Circle(200, 200, 10, Color.BLACK);
			centerPane.getChildren().add(vertex);
			// add vertex to main's observable list etc...
		}
	}
}
