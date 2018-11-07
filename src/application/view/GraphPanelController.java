package application.view;

import application.Main;
import application.model.DataModel;
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
	
	private DataModel dataModel;
	
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
				
				vertexText = dataModel.getListOfIntVertices().get(dataModel.getListOfIntVertices().size() - 1).toString();
				
			}else if(getSelectedDataChoice().equals("Double")){
				
				vertexText = dataModel.getListOfDoubleVertices().get(dataModel.getListOfDoubleVertices().size() - 1).toString();
				
			}else {
				
				vertexText = dataModel.getListOfStringVertices().get(dataModel.getListOfStringVertices().size() - 1).toString();
				
			}
			
			
       		double x = event.getX();
        	double y = event.getY();
   			Circle vertex = new Circle(x, y, 20, Color.RED);
   			vertex.setStroke(Color.BLACK);
   			vertex.setFill(Color.WHITE);
   			Text text = new Text (vertexText);
   			StackPane stack = new StackPane();
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
	
	public void setDataModel(DataModel dataModel) {
		this.dataModel = dataModel;
	}

	

}
