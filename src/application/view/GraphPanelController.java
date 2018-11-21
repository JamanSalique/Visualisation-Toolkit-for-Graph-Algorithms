package application.view;

import java.util.ArrayList;
import java.util.Optional;

import application.Main;
import application.model.DataModel;
import application.model.Vertex;
import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Pair;

public class GraphPanelController {

	
	private Main main;
	
	private DataModel dataModel;
	
	@FXML
	private AnchorPane rootAnchorPane;
	
	@FXML
	private Pane centerPaneUndirectedNonWeightedGraph;
	
	@FXML
	private Pane centerPaneUndirectedWeightedGraph;
	
	@FXML
	private Pane centerPaneDirectedNonWeightedGraph;
	
	@FXML
	private Pane centerPaneDirectedWeightedGraph;
	
	@FXML
	private ChoiceBox<String> choiceBoxUndirectedNonWeightedGraph;
	
	@FXML
	private ChoiceBox<String> choiceBoxUndirectedWeightedGraph;
	
	@FXML
	private ChoiceBox<String> choiceBoxDirectedNonWeightedGraph;
	
	@FXML
	private ChoiceBox<String> choiceBoxDirectedWeightedGraph;
	
	@FXML
	private TabPane tabs;
	
	@FXML
	private ContextMenu hoverMenu;
	
	private StackPane currentStackPane;

	private double orgSceneX, orgSceneY;
    private double orgTranslateX, orgTranslateY;
    private double newTranslateX, newTranslateY;
    private double offsetX, offsetY;
    private double layoutX,layoutY;
    
    private double directedEdgePlacement;
    
    private String preSelectionChoiceUndirectedNonWeightedGraph;
    private String preSelectionChoiceUndirectedWeightedGraph;
    private String preSelectionChoiceDirectedNonWeightedGraph;
    private String preSelectionChoiceDirectedWeightedGraph;
    
    private MenuItem hoverMenuItemAddEdge;
    
    private final int selfEdgeArrowheadPlacement = 20;

	
	@FXML
	private void initialize() {
		
		choiceBoxUndirectedNonWeightedGraph.getItems().addAll("Integer","Double","String");
		choiceBoxUndirectedNonWeightedGraph.getSelectionModel().selectFirst();
		choiceBoxUndirectedNonWeightedGraph.setOnMouseClicked(this::selectionChoiceUndirectedNonWeightedGraph);
		choiceBoxUndirectedNonWeightedGraph.setOnAction(this::handleChoiceBox);
		
		choiceBoxUndirectedWeightedGraph.getItems().addAll("Integer","Double","String");
		choiceBoxUndirectedWeightedGraph.getSelectionModel().selectFirst();
		choiceBoxUndirectedWeightedGraph.setOnMouseClicked(this::selectionChoiceUndirectedWeightedGraph);
		choiceBoxUndirectedWeightedGraph.setOnAction(this::handleChoiceBox);
		
		choiceBoxDirectedNonWeightedGraph.getItems().addAll("Integer","Double","String");
		choiceBoxDirectedNonWeightedGraph.getSelectionModel().selectFirst();
		choiceBoxDirectedNonWeightedGraph.setOnMouseClicked(this::selectionChoiceDirectedNonWeightedGraph);
		choiceBoxDirectedNonWeightedGraph.setOnAction(this::handleChoiceBox);
		
		choiceBoxDirectedWeightedGraph.getItems().addAll("Integer","Double","String");
		choiceBoxDirectedWeightedGraph.getSelectionModel().selectFirst();
		choiceBoxDirectedWeightedGraph.setOnMouseClicked(this::selectionChoiceDirectedWeightedGraph);
		choiceBoxDirectedWeightedGraph.setOnAction(this::handleChoiceBox);
		
		hoverMenu = new ContextMenu();
		hoverMenuItemAddEdge = new MenuItem("Add Edge?");
        
        hoverMenu.getItems().addAll(hoverMenuItemAddEdge);
        

	}
	
	private void addEdgeHandlerNonWeighted(String vertexDataAsStringFrom, String vertexDataAsStringTo) {
		
		if(getSelectedTabName().equals("Undirected Non-Weighted Graph") && isInteger(vertexDataAsStringFrom) && isInteger(vertexDataAsStringTo)) {
    		
			dataModel.getUndirectedNonWeightedInt().addEdge(Integer.parseInt(vertexDataAsStringFrom),Integer.parseInt(vertexDataAsStringTo));
     		
     	}else if(getSelectedTabName().equals("Undirected Non-Weighted Graph") && isDouble(vertexDataAsStringFrom) && isDouble(vertexDataAsStringTo)) {
     		
     		dataModel.getUndirectedNonWeightedDouble().addEdge(Double.parseDouble(vertexDataAsStringFrom),Double.parseDouble(vertexDataAsStringTo));
     		
     	}else if(getSelectedTabName().equals("Undirected Non-Weighted Graph") && isString(vertexDataAsStringFrom) && isString(vertexDataAsStringTo)) {
     		
     		dataModel.getUndirectedNonWeightedString().addEdge(vertexDataAsStringFrom,vertexDataAsStringTo);
     		
     	}
     	
     	else if(getSelectedTabName().equals("Directed Non-Weighted Graph") && isInteger(vertexDataAsStringFrom) && isInteger(vertexDataAsStringTo)) {

     		dataModel.getDirectedNonWeightedInt().addEdge(Integer.parseInt(vertexDataAsStringFrom),Integer.parseInt(vertexDataAsStringTo));
     		
     	}else if(getSelectedTabName().equals("Directed Non-Weighted Graph") && isDouble(vertexDataAsStringFrom) && isDouble(vertexDataAsStringTo)) {
     		
     		dataModel.getDirectedNonWeightedDouble().addEdge(Double.parseDouble(vertexDataAsStringFrom),Double.parseDouble(vertexDataAsStringTo));
     		
     	}else if(getSelectedTabName().equals("Directed Non-Weighted Graph") && isString(vertexDataAsStringFrom) && isString(vertexDataAsStringTo)) {

     		dataModel.getUndirectedNonWeightedString().addEdge(vertexDataAsStringFrom,vertexDataAsStringTo);
     		
     	}
		
	}
	
	private void addEdgeHandlerWeighted(String vertexDataAsStringFrom, String vertexDataAsStringTo, double weight) {

     	if(getSelectedTabName().equals("Undirected Weighted Graph") && isInteger(vertexDataAsStringFrom) && isInteger(vertexDataAsStringTo)) {

     		dataModel.getUndirectedWeightedInt().addEdge(Integer.parseInt(vertexDataAsStringFrom),Integer.parseInt(vertexDataAsStringTo),weight);
     		
     	}else if(getSelectedTabName().equals("Undirected Weighted Graph")  && isDouble(vertexDataAsStringFrom) && isDouble(vertexDataAsStringTo)) {
     		
     		dataModel.getUndirectedWeightedDouble().addEdge(Double.parseDouble(vertexDataAsStringFrom),Double.parseDouble(vertexDataAsStringTo),weight);
     		
     	}else if(getSelectedTabName().equals("Undirected Weighted Graph") && isString(vertexDataAsStringFrom) && isString(vertexDataAsStringTo)) {

     		dataModel.getUndirectedWeightedString().addEdge(vertexDataAsStringFrom,vertexDataAsStringTo,weight);
     		
     	}
     	else if(getSelectedTabName().equals("Directed Weighted Graph") && isInteger(vertexDataAsStringFrom) && isInteger(vertexDataAsStringTo)) {

     		dataModel.getDirectedWeightedInt().addEdge(Integer.parseInt(vertexDataAsStringFrom),Integer.parseInt(vertexDataAsStringTo),weight);
     		
     	}else if(getSelectedTabName().equals("Directed Weighted Graph") && isDouble(vertexDataAsStringFrom) && isDouble(vertexDataAsStringTo)) {

     		dataModel.getDirectedWeightedDouble().addEdge(Double.parseDouble(vertexDataAsStringFrom),Double.parseDouble(vertexDataAsStringTo),weight);
     		
     	}else if(getSelectedTabName().equals("Directed Weighted Graph") && isString(vertexDataAsStringFrom) && isString(vertexDataAsStringTo)) {
     		
     		dataModel.getDirectedWeightedString().addEdge(vertexDataAsStringFrom,vertexDataAsStringTo,weight);
     		
     	}

     		
	}
	
	@FXML
	private EventHandler<MouseEvent> mouseClickedOnEdgeEvent(){
		
		EventHandler<MouseEvent> lineOnMouseClickedEventHandler = 
		        new EventHandler<MouseEvent>() {
		 
		        @Override
		        public void handle(MouseEvent t) {
		        	if (t.getClickCount() == 2 && t.getButton() != MouseButton.SECONDARY && (t.getSource() instanceof Line || t.getSource() instanceof Label
		        			|| t.getSource() instanceof Polygon || t.getSource() instanceof Rectangle)) {
		        		 Alert alert = new Alert(AlertType.WARNING);
		        		 
		        		 ButtonType cancelButton = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
		        		 ButtonType okButton = new ButtonType("Ok", ButtonData.OK_DONE);
		        		 
		                 alert.setTitle("Deleting Edge.");
		                 alert.setHeaderText("Are you sure you want to delete this edge?");
		                 alert.setContentText("Once deleting the edge you cannot retrieve it back.");
		                 alert.getButtonTypes().setAll(okButton,cancelButton);
		                 
		                 Optional<ButtonType> result = alert.showAndWait();
		                 
		                 if(result.get() == okButton) {
		                	 
		                	 Line lineToRemove = null;
		                	 Label weightBox = null;
		                	 Polygon arrowheadToRemove = null;
		                	 Rectangle rectangleToRemove = null;
		                	 boolean polygonOnLine = false;
		                	 boolean weightBoxOnLine = false;
		                	 
		                	 if(getSelectedTabName().equals("Undirected Non-Weighted Graph")) {
		                		 lineToRemove = (Line) t.getSource();
		                	 }else if(getSelectedTabName().equals("Directed Non-Weighted Graph")) {
		                		 
		                		 if(t.getSource() instanceof Line) {
		                			 lineToRemove = (Line) t.getSource();
		                		 }else if(t.getSource() instanceof Polygon) {
		                			 
		                			 arrowheadToRemove = (Polygon) t.getSource();
		                			 
		                			 for(Node child:centerPaneDirectedNonWeightedGraph.getChildren()) {
		                				 
		                				 if(child instanceof Line) {
		                					 
		                					 Line line = (Line) child;
		                					 
		                					 if(arrowheadToRemove.getLayoutX() == (line.getStartX() + line.getEndX()) / 2 && 
		                						 arrowheadToRemove.getLayoutY() == (line.getStartY() + line.getEndY()) / 2) {
		                						 
		                						 polygonOnLine = true;
			                				}
		                					 
		                				 }
		                				 
		                			 }
		                			 
		                		 }else if(t.getSource() instanceof Rectangle) {
		                			 rectangleToRemove = (Rectangle) t.getSource();
		                		 }

		                		 if(lineToRemove == null && polygonOnLine) {
		                			 
		                			 for(Node child:centerPaneDirectedNonWeightedGraph.getChildren()) {
		                				 
		                				 if(child instanceof Line) {
		                					 
		                					 Line line = (Line) child;
		                					 
		                					 if(arrowheadToRemove.getLayoutX() == (line.getStartX() + line.getEndX()) / 2 && 
		                						 arrowheadToRemove.getLayoutY() == (line.getStartY() + line.getEndY()) / 2) {
		                						 lineToRemove = line;
		                						 
			                				}
		                					 
		                				 }
		                				 
		                			 }
		                			 
		                		 }else if(arrowheadToRemove == null && t.getSource() instanceof Line) {
		                			 
		                			 for(Node child:centerPaneDirectedNonWeightedGraph.getChildren()) {
		                				 
		                				 if(child instanceof Polygon) {
		                					 
		                					 Polygon arrowhead = (Polygon) child;
		                					 
		                					 if(arrowhead.getLayoutX() == (lineToRemove.getStartX() + lineToRemove.getEndX()) / 2 && 
		                							 arrowhead.getLayoutY() == (lineToRemove.getStartY() + lineToRemove.getEndY()) / 2) {
		                						 
		                						 arrowheadToRemove = arrowhead;
		                						 
			                				}
		                					 
		                				 }
		                				 
		                			 }
		                			 
		                		 }else if(arrowheadToRemove == null && t.getSource() instanceof Rectangle) {
		                			 
		                			 for(Node child:centerPaneDirectedNonWeightedGraph.getChildren()) {

		                				 if(child instanceof Polygon) {
		                					 
		                					 Polygon arrowhead = (Polygon) child;
		                					 
		                					 if(arrowhead.getLayoutX() == rectangleToRemove.getX() + selfEdgeArrowheadPlacement && 
		                							 arrowhead.getLayoutY() == rectangleToRemove.getY()) {
		                						 
		                						 arrowheadToRemove = arrowhead;
		                						 
			                				}
		                					 
		                				 }
		                				 
		                			 }
		                			 
		                		 }else if(rectangleToRemove == null && !polygonOnLine) {
		                			 
		                			 for(Node child:centerPaneDirectedNonWeightedGraph.getChildren()) {

		                				 if(child instanceof Rectangle) {
		                					 
		                					 Rectangle rect = (Rectangle) child;
		                					 
		                					 if(arrowheadToRemove.getLayoutX() == rect.getX() + selfEdgeArrowheadPlacement && 
		                							 arrowheadToRemove.getLayoutY() == rect.getY()) {
		                						 
		                						 rectangleToRemove = rect;
		                						 
			                				}
		                					 
		                				 }
		                				 
		                			 }
		                			 
		                		 }
		                		 
		                	 }else if(getSelectedTabName().equals("Undirected Weighted Graph") || getSelectedTabName().equals("Directed Weighted Graph")) {

	                			 if(getSelectedTabName().equals("Undirected Weighted Graph")) {
	                				 
	                				 if(t.getSource() instanceof Line) {
			                			 lineToRemove = (Line) t.getSource();
			                		 }else if(t.getSource() instanceof Label) {
			                			 weightBox = (Label) t.getSource();
			                		 }
	                				 
	                				 if(lineToRemove == null) {
	                					 
	                					 for(Node child:centerPaneUndirectedWeightedGraph.getChildren()) {
		    		                		 
		                					 if(child instanceof Line) {
		                						 
		                						 Line line = (Line) child;
		                						 
		                						 if((line.getStartX() + line.getEndX() - weightBox.getWidth()) / 2 == weightBox.getLayoutX() && 
		                								 (line.getStartY() + line.getEndY() - weightBox.getHeight()) / 2 == weightBox.getLayoutY()) {
		                							 
		                							 lineToRemove = line;
		                							 
		                						 }
		                						 
		                					 }
		                					 
		    		                	 }
	                					 
	                				 }else if(weightBox == null) {
	    		                		 
	                					 for(Node child:centerPaneUndirectedWeightedGraph.getChildren()) {
		    		                		 
		                					 if(child instanceof Label) {
		                						 
		                						 Label weight = (Label) child;
		                						 
		                						 if((lineToRemove.getStartX() + lineToRemove.getEndX() - weight.getWidth()) / 2 == weight.getLayoutX() && 
		                								 (lineToRemove.getStartY() + lineToRemove.getEndY() - weight.getHeight()) / 2 == weight.getLayoutY()) {
		                							 
		                							 weightBox = weight;
		                							 
		                						 }
		                						 
		                					 }
		                					 
		    		                	 }
	                					 
	    		                	 }

	                			 }else if(getSelectedTabName().equals("Directed Weighted Graph")) {
	                				 
	                				 if(t.getSource() instanceof Line) {
			                			 lineToRemove = (Line) t.getSource();
			                		 }else if(t.getSource() instanceof Polygon) {
			                			 
			                			 arrowheadToRemove = (Polygon) t.getSource();
			                			 
			                			 for(Node child:centerPaneDirectedWeightedGraph.getChildren()) {
			                				 
			                				 if(child instanceof Line) {
			                					 
			                					 Line line = (Line) child;
			                					 
			                					 if(arrowheadToRemove.getLayoutX() == (line.getStartX() + 4*line.getEndX()) / 5 && 
			                						 arrowheadToRemove.getLayoutY() == (line.getStartY() + 4*line.getEndY()) / 5) {
			                						 
			                						 polygonOnLine = true;
				                				}
			                					 
			                				 }
			                				 
			                			 }
			                			 
			                		 }else if(t.getSource() instanceof Rectangle) {
			                			 
			                			 rectangleToRemove = (Rectangle) t.getSource();
			                			 
			                		 }else if(t.getSource() instanceof Label) {
			                			 
			                			 weightBox = (Label) t.getSource();
			                			 
			                			 for(Node child:centerPaneDirectedWeightedGraph.getChildren()) {
			                				 
			                				 if(child instanceof Line) {
			                					 
			                					 Line line = (Line) child;
			                					 
			                					 if((line.getStartX() + line.getEndX() - weightBox.getWidth()) / 2 == weightBox.getLayoutX() && 
		                								 (line.getStartY() + line.getEndY() - weightBox.getHeight()) / 2 == weightBox.getLayoutY()) {
		                							 
		                							 weightBoxOnLine = true;
		                							 
		                						 }
			                					 
			                				 }
			                				 
			                			 }
			                			 
			                		 }
	                				 
	    		                	 System.out.println("LineToRemove: " + lineToRemove==null);
	    		                	 System.out.println("weightBox: " + weightBox==null);
	    		                	 System.out.println("ArrowHeadToRemove: " + arrowheadToRemove==null);
	    		                	 System.out.println("RectangleToRemove: " + rectangleToRemove==null);
	                				 
	                				 if(lineToRemove == null && t.getSource() instanceof Label && weightBoxOnLine) {
	                					 
	                					 for(Node child:centerPaneDirectedWeightedGraph.getChildren()) {
		    		                		 
		                					 if(child instanceof Line) {
		                						 
		                						 Line line = (Line) child;
		                						 
		                						 if((line.getStartX() + line.getEndX() - weightBox.getWidth()) / 2 == weightBox.getLayoutX() && 
		                								 (line.getStartY() + line.getEndY() - weightBox.getHeight()) / 2 == weightBox.getLayoutY()) {
		                							 
		                							 lineToRemove = line;
		                							 
		                						 }
		                						 
		                					 }
		                					 
		    		                	 }
	                					 
	                					 for(Node child:centerPaneDirectedWeightedGraph.getChildren()) {
			                				 
			                				 if(child instanceof Polygon) {
			                					 
			                					 Polygon arrowhead = (Polygon) child;
			                					 
			                					 if(arrowhead.getLayoutX() == (lineToRemove.getStartX() + 4*lineToRemove.getEndX()) / 5 && 
			                							 arrowhead.getLayoutY() == (lineToRemove.getStartY() + 4*lineToRemove.getEndY()) / 5) {
			                						 arrowheadToRemove = arrowhead;
			                						 
				                				}
			                					 
			                				 }
			                				 
			                			 }
	                					 
	                				 }else if(lineToRemove == null && t.getSource() instanceof Polygon && polygonOnLine == true && weightBox == null) {
	                					 
	                					 for(Node child:centerPaneDirectedWeightedGraph.getChildren()) {
			                				 
			                				 if(child instanceof Line) {
			                					 
			                					 Line line = (Line) child;
			                					 
			                					 if(arrowheadToRemove.getLayoutX() == (line.getStartX() + 4*line.getEndX()) / 5 && 
			                						 arrowheadToRemove.getLayoutY() == (line.getStartY() + 4*line.getEndY()) / 5) {
			                						 lineToRemove = line;
			                						 
				                				}
			                					 
			                				 }
			                				 
			                			 }
	                					 
	                					 for(Node child:centerPaneDirectedWeightedGraph.getChildren()) {
		    		                		 
		                					 if(child instanceof Label) {
		                						 
		                						 Label weight = (Label) child;
		                						 
		                						 if((lineToRemove.getStartX() + lineToRemove.getEndX() - weight.getWidth()) / 2 == weight.getLayoutX() && 
		                								 (lineToRemove.getStartY() + lineToRemove.getEndY() - weight.getHeight()) / 2 == weight.getLayoutY()) {
		                							 
		                							 weightBox = weight;
		                							 
		                						 }
		                						 
		                					 }
		                					 
		    		                	 }
	                					 
	                				 } else if(weightBox ==null && t.getSource() instanceof Line) {
	                					 
	                					 for(Node child:centerPaneDirectedWeightedGraph.getChildren()) {
		    		                		 
		                					 if(child instanceof Label) {
		                						 
		                						 Label weight = (Label) child;
		                						 
		                						 if((lineToRemove.getStartX() + lineToRemove.getEndX() - weight.getWidth()) / 2 == weight.getLayoutX() && 
		                								 (lineToRemove.getStartY() + lineToRemove.getEndY() - weight.getHeight()) / 2 == weight.getLayoutY()) {
		                							 
		                							 weightBox = weight;
		                							 
		                						 }
		                						 
		                					 }
		                					 
		    		                	 }
	                					 
	                					 for(Node child:centerPaneDirectedWeightedGraph.getChildren()) {
			                				 
			                				 if(child instanceof Polygon) {
			                					 
			                					 Polygon arrowhead = (Polygon) child;
			                					 
			                					 if(arrowhead.getLayoutX() == (lineToRemove.getStartX() + 4*lineToRemove.getEndX()) / 5 && 
			                							 arrowhead.getLayoutY() == (lineToRemove.getStartY() + 4*lineToRemove.getEndY()) / 5) {
			                						 arrowheadToRemove = arrowhead;
			                						 
				                				}
			                					 
			                				 }
			                				 
			                			 }
	                					 
	                				 } else if(t.getSource() instanceof Label && !weightBoxOnLine && rectangleToRemove == null) {
	                					 
	                					 for(Node child:centerPaneDirectedWeightedGraph.getChildren()) {

			                				 if(child instanceof Rectangle) {
			                					 
			                					 Rectangle rect = (Rectangle) child;
			                					 
			                					 if(weightBox.getLayoutX() == (rect.getX()  + rect.getWidth()) - (weightBox.getWidth()/2) && 
			                							 weightBox.getLayoutY() == (rect.getY() + rect.getHeight()) - (weightBox.getHeight()/2)) {
			                						 
			                						 rectangleToRemove = rect;
			                						 
				                				}
			                					 
			                				 }
			                				 
			                			 }
	                					 
	                					 for(Node child:centerPaneDirectedWeightedGraph.getChildren()) {

			                				 if(child instanceof Polygon) {
			                					 
			                					 Polygon arrowhead = (Polygon) child;
			                					 
			                					 if(arrowhead.getLayoutX() == rectangleToRemove.getX() + selfEdgeArrowheadPlacement && 
			                							 arrowhead.getLayoutY() == rectangleToRemove.getY()) {
			                						 
			                						 arrowheadToRemove = arrowhead;
			                						 
				                				}
			                					 
			                				 }
			                				 
			                			 }
	                					 
	                					 
	                				 }else if(t.getSource() instanceof Polygon && rectangleToRemove == null && !polygonOnLine) {
	                					 
	                					 for(Node child:centerPaneDirectedWeightedGraph.getChildren()) {

			                				 if(child instanceof Rectangle) {
			                					 
			                					 Rectangle rect = (Rectangle) child;
			                					 
			                					 if(arrowheadToRemove.getLayoutX() == rect.getX() + selfEdgeArrowheadPlacement && 
			                							 arrowheadToRemove.getLayoutY() == rect.getY()) {
			                						 
			                						 rectangleToRemove = rect;
			                						 
				                				}
			                					 
			                				 }
			                				 
			                			 }
	                					 
	                					 for(Node child:centerPaneDirectedWeightedGraph.getChildren()) {

			                				 if(child instanceof Label) {
			                					 
			                					 Label weight = (Label) child;
			                					 
			                					 if(weight.getLayoutX() == (rectangleToRemove.getX()  + rectangleToRemove.getWidth()) - (weight.getWidth()/2) && 
			                							 weight.getLayoutY() == (rectangleToRemove.getY() + rectangleToRemove.getHeight()) - (weight.getHeight()/2)) {
			                						 
			                						 weightBox = weight;
			                						 
				                				}
			                					 
			                				 }
			                				 
			                			 }
	                					 
	                					 
	                				 }else if(t.getSource() instanceof Rectangle) {
	                					 
	                					 for(Node child:centerPaneDirectedWeightedGraph.getChildren()) {

			                				 if(child instanceof Label) {
			                					 
			                					 Label weight = (Label) child;
			                					 
			                					 if(weight.getLayoutX() == (rectangleToRemove.getX()  + rectangleToRemove.getWidth()) - (weight.getWidth()/2) && 
			                							 weight.getLayoutY() == (rectangleToRemove.getY() + rectangleToRemove.getHeight()) - (weight.getHeight()/2)) {
			                						 
			                						 weightBox = weight;
			                						 
				                				}
			                					 
			                				 }
			                				 
			                			 }
	                					 
	                					 for(Node child:centerPaneDirectedWeightedGraph.getChildren()) {

			                				 if(child instanceof Polygon) {
			                					 
			                					 Polygon arrowhead = (Polygon) child;
			                					 
			                					 if(arrowhead.getLayoutX() == rectangleToRemove.getX() + selfEdgeArrowheadPlacement && 
			                							 arrowhead.getLayoutY() == rectangleToRemove.getY()) {
			                						 
			                						 arrowheadToRemove = arrowhead;
			                						 
				                				}
			                					 
			                				 }
			                				 
			                			 }
	                					 
	                				 }
	                				 
	                				 
	                				 
	                			 }

		                	 }

		                	 StackPane vertexStart = null;
		                	 StackPane vertexEnd = null;
		                	 
		                	 if(getSelectedTabName().equals("Undirected Non-Weighted Graph")) {
		                		 
		                		 for(Node child:centerPaneUndirectedNonWeightedGraph.getChildren()) {
			                		 if(child instanceof StackPane) {
			                			 if(child.getLayoutX() + ((Region) child).getWidth() / 2 == lineToRemove.getStartX() && 
			                					 child.getLayoutY() + ((Region) child).getHeight() / 2  == lineToRemove.getStartY()) {
			                				 
			                				 vertexStart = (StackPane) child;
			                			 }
			                		 }
			                	 }
			                	 
			                	 for(Node child:centerPaneUndirectedNonWeightedGraph.getChildren()) {

			                		 if(child instanceof StackPane) {
			                			 if(child.getLayoutX() + ((Region) child).getWidth() / 2 == lineToRemove.getEndX() && 
			                					 child.getLayoutY() + ((Region) child).getWidth() / 2 == lineToRemove.getEndY()) {
			                				 vertexEnd = (StackPane) child;
			                			 }
			                		 }
			                	 }
		                		 
		                	 }else if(getSelectedTabName().equals("Undirected Weighted Graph")) {
		                		 
		                		 for(Node child:centerPaneUndirectedWeightedGraph.getChildren()) {
			                		 if(child instanceof StackPane) {
			                			 if(child.getLayoutX() + ((Region) child).getWidth() / 2 == lineToRemove.getStartX() && 
			                					 child.getLayoutY() + ((Region) child).getHeight() / 2  == lineToRemove.getStartY()) {
			                				 
			                				 vertexStart = (StackPane) child;
			                			 }
			                		 }
			                	 }
			                	 
			                	 for(Node child:centerPaneUndirectedWeightedGraph.getChildren()) {

			                		 if(child instanceof StackPane) {
			                			 if(child.getLayoutX() + ((Region) child).getWidth() / 2 == lineToRemove.getEndX() && 
			                					 child.getLayoutY() + ((Region) child).getWidth() / 2 == lineToRemove.getEndY()) {
			                				 vertexEnd = (StackPane) child;
			                			 }
			                		 }
			                	 }
		                		 
		                	 }else if(getSelectedTabName().equals("Directed Non-Weighted Graph")) {
		                		 
		                		 if(lineToRemove != null) {
		                			 
			                		 for(Node child:centerPaneDirectedNonWeightedGraph.getChildren()) {
				                		 if(child instanceof StackPane) {
				                			 if((child.getLayoutX() + ((Region) child).getWidth() / 2) + directedEdgePlacement == lineToRemove.getStartX() && 
				                					 (child.getLayoutY() + ((Region) child).getHeight() / 2) + directedEdgePlacement  == lineToRemove.getStartY()) {
				                				 
				                				 vertexStart = (StackPane) child;
				                			 }else if((child.getLayoutX() + ((Region) child).getWidth() / 2) - directedEdgePlacement == lineToRemove.getStartX() && 
				                					 (child.getLayoutY() + ((Region) child).getHeight() / 2) - directedEdgePlacement  == lineToRemove.getStartY()) {
				                				 
				                				 vertexStart = (StackPane) child;
				                			 }
				                		 }
				                	 }
				                	 
				                	 for(Node child:centerPaneDirectedNonWeightedGraph.getChildren()) {

				                		 if(child instanceof StackPane) {
				                			 if((child.getLayoutX() + ((Region) child).getWidth() / 2) + directedEdgePlacement == lineToRemove.getEndX() && 
				                					 (child.getLayoutY() + ((Region) child).getWidth() / 2) + directedEdgePlacement == lineToRemove.getEndY()) {
				                				 vertexEnd = (StackPane) child;
				                			 }else if((child.getLayoutX() + ((Region) child).getWidth() / 2) - directedEdgePlacement == lineToRemove.getEndX() && 
				                					 (child.getLayoutY() + ((Region) child).getWidth() / 2) - directedEdgePlacement == lineToRemove.getEndY()) {
				                				 vertexEnd = (StackPane) child;
				                			 }
				                		 }
				                	 }
				                	 
		                		 }else if(rectangleToRemove != null) {
		                			 
		                			 for(Node child:centerPaneDirectedNonWeightedGraph.getChildren()) {
				                		 if(child instanceof StackPane) {
				                			 if((child.getLayoutX() + ((Region) child).getWidth() / 2) == rectangleToRemove.getX() && 
				                					 (child.getLayoutY() + ((Region) child).getHeight() / 2)  == rectangleToRemove.getY()) {
				                				 
				                				 vertexStart = (StackPane) child;
				                			 }
				                		 }
				                	 }
		                			 
		                			 vertexEnd =vertexStart;
		                			 
		                		 }
		                		 

		                		 
		                	 }else if(getSelectedTabName().equals("Directed Weighted Graph")) {
		                		 
		                		 System.out.println("In here");
		                		 
		                		 if(lineToRemove != null) {
		                			 
		                			 System.out.println("In here part2");
		                			 
			                		 for(Node child:centerPaneDirectedWeightedGraph.getChildren()) {
				                		 if(child instanceof StackPane) {
				                			 System.out.println("In here part3");
				                			 if((child.getLayoutX() + ((Region) child).getWidth() / 2) + directedEdgePlacement == lineToRemove.getStartX() && 
				                					 (child.getLayoutY() + ((Region) child).getHeight() / 2) + directedEdgePlacement  == lineToRemove.getStartY()) {
				                				 System.out.println("In here part4.1");
				                				 vertexStart = (StackPane) child;
				                			 }else if((child.getLayoutX() + ((Region) child).getWidth() / 2) - directedEdgePlacement == lineToRemove.getStartX() && 
				                					 (child.getLayoutY() + ((Region) child).getHeight() / 2) - directedEdgePlacement  == lineToRemove.getStartY()) {
				                				 System.out.println("In here part4.2");
				                				 vertexStart = (StackPane) child;
				                			 }
				                		 }
				                	 }
				                	 
				                	 for(Node child:centerPaneDirectedWeightedGraph.getChildren()) {

				                		 if(child instanceof StackPane) {
				                			 if((child.getLayoutX() + ((Region) child).getWidth() / 2) + directedEdgePlacement == lineToRemove.getEndX() && 
				                					 (child.getLayoutY() + ((Region) child).getWidth() / 2) + directedEdgePlacement == lineToRemove.getEndY()) {
				                				 vertexEnd = (StackPane) child;
				                			 }else if((child.getLayoutX() + ((Region) child).getWidth() / 2) - directedEdgePlacement == lineToRemove.getEndX() && 
				                					 (child.getLayoutY() + ((Region) child).getWidth() / 2) - directedEdgePlacement == lineToRemove.getEndY()) {
				                				 vertexEnd = (StackPane) child;
				                			 }
				                		 }
				                	 }
				                	 
		                		 }else if(rectangleToRemove != null) {
		                			 
		                			 for(Node child:centerPaneDirectedWeightedGraph.getChildren()) {
				                		 if(child instanceof StackPane) {
				                			 if((child.getLayoutX() + ((Region) child).getWidth() / 2) == rectangleToRemove.getX() && 
				                					 (child.getLayoutY() + ((Region) child).getHeight() / 2)  == rectangleToRemove.getY()) {
				                				 
				                				 vertexStart = (StackPane) child;
				                			 }
				                		 }
				                	 }
		                			 
		                			 vertexEnd =vertexStart;
		                			 
		                		 }
		                		 
		                	 }
		                	 
//		                	 for(Node child:centerPaneUndirectedNonWeightedGraph.getChildren()) {
//		                		 if(child instanceof StackPane) {
//		                			 if(child.getLayoutX() + ((Region) child).getWidth() / 2 == lineToRemove.getStartX() && 
//		                					 child.getLayoutY() + ((Region) child).getHeight() / 2  == lineToRemove.getStartY()) {
//		                				 
//		                				 vertexStart = (StackPane) child;
//		                			 }
//		                		 }
//		                	 }
//		                	 
//		                	 for(Node child:centerPaneUndirectedNonWeightedGraph.getChildren()) {
//
//		                		 if(child instanceof StackPane) {
//		                			 if(child.getLayoutX() + ((Region) child).getWidth() / 2 == lineToRemove.getEndX() && 
//		                					 child.getLayoutY() + ((Region) child).getWidth() / 2 == lineToRemove.getEndY()) {
//		                				 vertexEnd = (StackPane) child;
//		                			 }
//		                		 }
//		                	 }
		                	 
		                	 ObservableList<Node> childsOfVertexStart = vertexStart.getChildren();
		                	 Text dataOfVertexStart = (Text) childsOfVertexStart.get(childsOfVertexStart.size()-1);
		                	 String dataOfVertexStartAsString = dataOfVertexStart.getText();
		                	 
		                	 ObservableList<Node> childsOfVertexEnd = vertexEnd.getChildren();
		                	 Text dataOfVertexEnd = (Text) childsOfVertexEnd.get(childsOfVertexEnd.size()-1);
		                	 String dataOfVertexEndAsString = dataOfVertexEnd.getText();
		                	 
		                	if(getSelectedTabName().equals("Undirected Non-Weighted Graph") && isInteger(dataOfVertexStartAsString) && isInteger(dataOfVertexEndAsString)) {
		                		
		                 		centerPaneUndirectedNonWeightedGraph.getChildren().remove(t.getSource());
		                 		dataModel.getUndirectedNonWeightedInt().removeEdge(Integer.parseInt(dataOfVertexStartAsString), Integer.parseInt(dataOfVertexEndAsString));
		                 		
		                 	}else if(getSelectedTabName().equals("Undirected Non-Weighted Graph") && isDouble(dataOfVertexStartAsString) && isDouble(dataOfVertexEndAsString)) {
		                 		
		                 		centerPaneUndirectedNonWeightedGraph.getChildren().remove(t.getSource());
		                 		dataModel.getUndirectedNonWeightedDouble().removeEdge(Double.parseDouble(dataOfVertexStartAsString), Double.parseDouble(dataOfVertexEndAsString));
		                 		
		                 	}else if(getSelectedTabName().equals("Undirected Non-Weighted Graph") && isString(dataOfVertexStartAsString) && isString(dataOfVertexEndAsString)) {
		                 		
		                 		centerPaneUndirectedNonWeightedGraph.getChildren().remove(t.getSource());
		                 		dataModel.getUndirectedNonWeightedString().removeEdge(dataOfVertexStartAsString, dataOfVertexEndAsString);
		                 		
		                 	}
		                 	
		                 	else if(getSelectedTabName().equals("Undirected Weighted Graph") && isInteger(dataOfVertexStartAsString) && isInteger(dataOfVertexEndAsString)) {
		                 		
		                 		centerPaneUndirectedWeightedGraph.getChildren().remove(lineToRemove);
		                 		centerPaneUndirectedWeightedGraph.getChildren().remove(weightBox);
		                 		dataModel.getUndirectedWeightedInt().removeEdge(Integer.parseInt(dataOfVertexStartAsString), Integer.parseInt(dataOfVertexEndAsString));
		                 		
		                 	}else if(getSelectedTabName().equals("Undirected Weighted Graph") && isDouble(dataOfVertexStartAsString) && isDouble(dataOfVertexEndAsString)) {
		                 		
		                 		centerPaneUndirectedWeightedGraph.getChildren().remove(lineToRemove);
		                 		centerPaneUndirectedWeightedGraph.getChildren().remove(weightBox);
		                 		dataModel.getUndirectedWeightedDouble().removeEdge(Double.parseDouble(dataOfVertexStartAsString), Double.parseDouble(dataOfVertexEndAsString));
		                 		
		                 	}else if(getSelectedTabName().equals("Undirected Weighted Graph") && isString(dataOfVertexStartAsString) && isString(dataOfVertexEndAsString)) {
		                 		
		                 		centerPaneUndirectedWeightedGraph.getChildren().remove(lineToRemove);
		                 		centerPaneUndirectedWeightedGraph.getChildren().remove(weightBox);
		                 		dataModel.getUndirectedWeightedString().removeEdge(dataOfVertexStartAsString, dataOfVertexEndAsString);
		                 		
		                 	}
		                 	
		                 	else if(getSelectedTabName().equals("Directed Non-Weighted Graph") && isInteger(dataOfVertexStartAsString) && isInteger(dataOfVertexEndAsString)) {
		                 		
		                 		if(t.getSource() instanceof Line || (t.getSource() instanceof Polygon && lineToRemove!=null)) {
		                 			centerPaneDirectedNonWeightedGraph.getChildren().remove(lineToRemove);
		                 			centerPaneDirectedNonWeightedGraph.getChildren().remove(arrowheadToRemove);
		                 		}else if(t.getSource() instanceof Rectangle || (t.getSource() instanceof Polygon && lineToRemove==null)) {
		                 			
		                 			centerPaneDirectedNonWeightedGraph.getChildren().remove(arrowheadToRemove);
		                 			centerPaneDirectedNonWeightedGraph.getChildren().remove(rectangleToRemove);
		                 			
		                 		}
		                 		
		                 		
		                 		dataModel.getDirectedNonWeightedInt().removeEdge(Integer.parseInt(dataOfVertexStartAsString), Integer.parseInt(dataOfVertexEndAsString));
		                 		
		                 	}else if(getSelectedTabName().equals("Directed Non-Weighted Graph") && isDouble(dataOfVertexStartAsString) && isDouble(dataOfVertexEndAsString)) {
		                 		
		                 		if(t.getSource() instanceof Line || (t.getSource() instanceof Polygon && lineToRemove!=null)) {
		                 			centerPaneDirectedNonWeightedGraph.getChildren().remove(lineToRemove);
		                 			centerPaneDirectedNonWeightedGraph.getChildren().remove(arrowheadToRemove);
		                 		}else if(t.getSource() instanceof Rectangle || (t.getSource() instanceof Polygon && lineToRemove==null)) {
		                 			
		                 			centerPaneDirectedNonWeightedGraph.getChildren().remove(arrowheadToRemove);
		                 			centerPaneDirectedNonWeightedGraph.getChildren().remove(rectangleToRemove);
		                 			
		                 		}
		                 		dataModel.getDirectedNonWeightedDouble().removeEdge(Double.parseDouble(dataOfVertexStartAsString), Double.parseDouble(dataOfVertexEndAsString));
		                 		
		                 	}else if(getSelectedTabName().equals("Directed Non-Weighted Graph") && isString(dataOfVertexStartAsString) && isString(dataOfVertexEndAsString)) {
		                 		
		                 		if(t.getSource() instanceof Line || (t.getSource() instanceof Polygon && lineToRemove!=null)) {
		                 			centerPaneDirectedNonWeightedGraph.getChildren().remove(lineToRemove);
		                 			centerPaneDirectedNonWeightedGraph.getChildren().remove(arrowheadToRemove);
		                 		}else if(t.getSource() instanceof Rectangle || (t.getSource() instanceof Polygon && lineToRemove==null)) {
		                 			
		                 			centerPaneDirectedNonWeightedGraph.getChildren().remove(arrowheadToRemove);
		                 			centerPaneDirectedNonWeightedGraph.getChildren().remove(rectangleToRemove);
		                 			
		                 		}
		                 		dataModel.getDirectedNonWeightedString().removeEdge(dataOfVertexStartAsString, dataOfVertexEndAsString);
		                 		
		                 	}
		                 	
		                 	else if(getSelectedTabName().equals("Directed Weighted Graph") && isInteger(dataOfVertexStartAsString) && isInteger(dataOfVertexEndAsString)) {

		                 		if(t.getSource() instanceof Line || (t.getSource() instanceof Polygon && polygonOnLine) || 
		                 				(t.getSource() instanceof Label && weightBoxOnLine)) {
		                 			
		                 			centerPaneDirectedWeightedGraph.getChildren().remove(lineToRemove);
		                 			centerPaneDirectedWeightedGraph.getChildren().remove(arrowheadToRemove);
		                 			centerPaneDirectedWeightedGraph.getChildren().remove(weightBox);
		                 			
		                 		}else if(t.getSource() instanceof Rectangle || (t.getSource() instanceof Polygon && !polygonOnLine) || 
		                 				(t.getSource() instanceof Label && !weightBoxOnLine)) {
		                 			
		                 			centerPaneDirectedWeightedGraph.getChildren().remove(arrowheadToRemove);
		                 			centerPaneDirectedWeightedGraph.getChildren().remove(rectangleToRemove);
		                 			centerPaneDirectedWeightedGraph.getChildren().remove(weightBox);
		                 			
		                 		}
		                 		
		                 		dataModel.getDirectedWeightedInt().removeEdge(Integer.parseInt(dataOfVertexStartAsString), Integer.parseInt(dataOfVertexEndAsString));
		                 		
		                 	}else if(getSelectedTabName().equals("Directed Weighted Graph") && isDouble(dataOfVertexStartAsString) && isDouble(dataOfVertexEndAsString)) {
		                 		
		                 		if(t.getSource() instanceof Line || (t.getSource() instanceof Polygon && polygonOnLine) || 
		                 				(t.getSource() instanceof Label && weightBoxOnLine)) {
		                 			
		                 			centerPaneDirectedWeightedGraph.getChildren().remove(lineToRemove);
		                 			centerPaneDirectedWeightedGraph.getChildren().remove(arrowheadToRemove);
		                 			centerPaneDirectedWeightedGraph.getChildren().remove(weightBox);
		                 			
		                 		}else if(t.getSource() instanceof Rectangle || (t.getSource() instanceof Polygon && !polygonOnLine) || 
		                 				(t.getSource() instanceof Label && !weightBoxOnLine)) {
		                 			
		                 			centerPaneDirectedWeightedGraph.getChildren().remove(arrowheadToRemove);
		                 			centerPaneDirectedWeightedGraph.getChildren().remove(rectangleToRemove);
		                 			centerPaneDirectedWeightedGraph.getChildren().remove(weightBox);
		                 			
		                 		}
		                 		
		                 		dataModel.getDirectedWeightedDouble().removeEdge(Double.parseDouble(dataOfVertexStartAsString), Double.parseDouble(dataOfVertexEndAsString));
		                 		
		                 	}else if(getSelectedTabName().equals("Directed Weighted Graph") && isString(dataOfVertexStartAsString) && isString(dataOfVertexEndAsString)) {
		                 		
		                 		if(t.getSource() instanceof Line || (t.getSource() instanceof Polygon && polygonOnLine) || 
		                 				(t.getSource() instanceof Label && weightBoxOnLine)) {
		                 			
		                 			centerPaneDirectedWeightedGraph.getChildren().remove(lineToRemove);
		                 			centerPaneDirectedWeightedGraph.getChildren().remove(arrowheadToRemove);
		                 			centerPaneDirectedWeightedGraph.getChildren().remove(weightBox);
		                 			
		                 		}else if(t.getSource() instanceof Rectangle || (t.getSource() instanceof Polygon && !polygonOnLine) || 
		                 				(t.getSource() instanceof Label && !weightBoxOnLine)) {
		                 			
		                 			centerPaneDirectedWeightedGraph.getChildren().remove(arrowheadToRemove);
		                 			centerPaneDirectedWeightedGraph.getChildren().remove(rectangleToRemove);
		                 			centerPaneDirectedWeightedGraph.getChildren().remove(weightBox);
		                 			
		                 		}
		                 		
		                 		dataModel.getDirectedWeightedString().removeEdge(dataOfVertexStartAsString, dataOfVertexEndAsString);
		                 		
		                 	}
		                	
		                	 t.consume();
		                 }
		        	 }
		        }
		        
		    };
		
		return lineOnMouseClickedEventHandler;
		
	}
	
	public void setMain(Main main) {
		this.main = main;
		
		// all data from observable lists to data in this class.
	}
	
	@FXML
	private EventHandler<MouseEvent> mousePressedOnVertexEvent(StackPane stack) {
		
		EventHandler<MouseEvent> circleOnMousePressedEventHandler = 
		        new EventHandler<MouseEvent>() {
		 
		        @Override
		        public void handle(MouseEvent t) {
		        	
		        	if(t.getButton() == MouseButton.SECONDARY) {
		        		
		        		StackPane vertexClickedOn = (StackPane)(t.getSource());
		        		ObservableList<Node> childs = ((StackPane)(t.getSource())).getChildren();
	                	Text data = (Text) childs.get(childs.size()-1);
	                	String dataAsString = data.getText();
						double x = t.getX();
						double y = t.getY();
						hoverMenu.show(stack,t.getScreenX(), t.getScreenY());
						
						 hoverMenuItemAddEdge.setOnAction(event -> {
					        	
							 if(getSelectedTabName().equals("Undirected Non-Weighted Graph") || getSelectedTabName().equals("Directed Non-Weighted Graph")) {
								 
								TextInputDialog dialogNonWeightedEdge = new TextInputDialog();
						        dialogNonWeightedEdge.setTitle("Add Edge");
						        dialogNonWeightedEdge.setHeaderText("Add Edge.");
						        dialogNonWeightedEdge.setContentText("Please enter the edge you would like to connect to:");
						        ButtonType cancelButton = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
					        	ButtonType okButton = new ButtonType("Ok", ButtonData.OK_DONE);
					        	dialogNonWeightedEdge.getDialogPane().getButtonTypes().setAll(okButton,cancelButton);
						        // Traditional way to get the response value.
						        Optional<String> result = dialogNonWeightedEdge.showAndWait();
						        	
						        	if(result.isPresent() && isInputValid(result.get(),dataAsString,"") 
						        			){
						        		if(result.isPresent()) {
						        			
						        			StackPane vertexTo = null;
						        			
						        			if(getSelectedTabName().equals("Undirected Non-Weighted Graph")) {
							        			for(Node child : centerPaneUndirectedNonWeightedGraph.getChildren()) {
							                		
							                		if(child instanceof StackPane) {
							                			ObservableList<Node> childsOfStack = ((StackPane)child).getChildren();
							                			Text dataOfStack = (Text) childsOfStack.get(childs.size()-1);
							                			String toCompare = dataOfStack.getText();
							                			if(toCompare.equals(result.get())) {
							                				vertexTo = (StackPane) child;
							                			}
							                		}
							                	}
							        			
							        			addEdgeHandlerNonWeighted(dataAsString,result.get());
							        			
							        			Line line = new Line();
								        		line.setStrokeWidth(2);
								        		
								        		line.setOnMouseClicked(Event::consume);
								        		line.setOnMousePressed(mouseClickedOnEdgeEvent());

								        		line.setStartX(vertexClickedOn.getLayoutX() + (vertexClickedOn.getWidth() / 2));
								        		line.setStartY(vertexClickedOn.getLayoutY() + (vertexClickedOn.getHeight() / 2));
								        		line.setEndX(vertexTo.getLayoutX() + (vertexTo.getWidth() / 2));
								        		line.setEndY(vertexTo.getLayoutY() +  (vertexTo.getHeight() / 2));

								        		line.startXProperty().bind(vertexClickedOn.layoutXProperty().add(vertexClickedOn.translateXProperty()).add(vertexClickedOn.widthProperty().divide(2)));
								        		line.startYProperty().bind(vertexClickedOn.layoutYProperty().add(vertexClickedOn.translateYProperty()).add(vertexClickedOn.heightProperty().divide(2)));
								        		line.endXProperty().bind(vertexTo.layoutXProperty().add(vertexTo.translateXProperty()).add(vertexTo.widthProperty().divide(2)));
								        		line.endYProperty().bind(vertexTo.layoutYProperty().add(vertexTo.translateYProperty()).add(vertexTo.heightProperty().divide(2)));
							        			
								        		centerPaneUndirectedNonWeightedGraph.getChildren().add(0,line);
								        		
							        		}else if(getSelectedTabName().equals("Directed Non-Weighted Graph")) {
							        			
							        			for(Node child : centerPaneDirectedNonWeightedGraph.getChildren()) {
							                		
							                		if(child instanceof StackPane) {
							                			ObservableList<Node> childsOfStack = ((StackPane)child).getChildren();
							                			Text dataOfStack = (Text) childsOfStack.get(childs.size()-1);
							                			String toCompare = dataOfStack.getText();
							                			if(toCompare.equals(result.get())) {
							                				vertexTo = (StackPane) child;
							                			}
							                		}
							                	}
							        			
							        			addEdgeHandlerNonWeighted(dataAsString,result.get());
							        			
							        			directedEdgePlacement = 13;
							        			
							        			double xMoveStart = directedEdgePlacement;
							        			double yMoveStart =directedEdgePlacement;
							        			double xMoveEnd = directedEdgePlacement;
							        			double yMoveEnd =directedEdgePlacement;
							        			
							        			if(getSelectedDataChoiceDirectedNonWeightedGraph().equals("Integer")) {
							        				
							        				ObservableList<Node> childsVertexTo = vertexTo.getChildren();
								                	Text dataVertexTo = (Text) childsVertexTo.get(childsVertexTo.size()-1);
								                	String dataAsStringVertexTo = dataVertexTo.getText();
								                	
							        				if(dataModel.getDirectedNonWeightedInt().isAdjacent(Integer.parseInt(dataAsStringVertexTo), Integer.parseInt(dataAsString))) {
							        					xMoveStart = -xMoveStart;
									        			yMoveStart = -yMoveStart;
									        			
									        			xMoveEnd = -xMoveEnd;
									        			yMoveEnd = -yMoveEnd;
							        				}
							        				
							        			}else if(getSelectedDataChoiceDirectedNonWeightedGraph().equals("Double")) {
							        				
							        				ObservableList<Node> childsVertexTo = vertexTo.getChildren();
								                	Text dataVertexTo = (Text) childsVertexTo.get(childsVertexTo.size()-1);
								                	String dataAsStringVertexTo = dataVertexTo.getText();
								                	
							        				if(dataModel.getDirectedNonWeightedDouble().isAdjacent(Double.parseDouble(dataAsStringVertexTo), Double.parseDouble(dataAsString))) {
							        					xMoveStart = -xMoveStart;
									        			yMoveStart = -yMoveStart;
									        			
									        			xMoveEnd = -xMoveEnd;
									        			yMoveEnd = -yMoveEnd;
							        				}
							        				
							        				
							        			}else if(getSelectedDataChoiceDirectedNonWeightedGraph().equals("String")) {
							        				
							        				ObservableList<Node> childsVertexTo = vertexTo.getChildren();
								                	Text dataVertexTo = (Text) childsVertexTo.get(childsVertexTo.size()-1);
								                	String dataAsStringVertexTo = dataVertexTo.getText();
								                	
							        				if(dataModel.getDirectedNonWeightedString().isAdjacent(dataAsStringVertexTo,dataAsString)) {
							        					xMoveStart = -xMoveStart;
									        			yMoveStart = -yMoveStart;
									        			
									        			xMoveEnd = -xMoveEnd;
									        			yMoveEnd = -yMoveEnd;
							        				}
							        				
							        				
							        			}
								        		
								        		if(result.get().equals(dataAsString)) {
								        			
								        			Rectangle rect = new Rectangle();
								        			rect.xProperty().bind(vertexClickedOn.layoutXProperty().add(vertexClickedOn.translateXProperty()).add(vertexClickedOn.widthProperty().divide(2)));
								        			rect.yProperty().bind(vertexClickedOn.layoutYProperty().add(vertexClickedOn.translateYProperty()).add(vertexClickedOn.heightProperty().divide(2)));
								        			rect.setHeight(40);
								        			rect.setWidth(40);
								        			rect.setStroke(Color.BLACK);
								        			rect.setStrokeWidth(2);
								        			rect.setFill(null);
								        			
								        			rect.setOnMouseClicked(Event::consume);
									        		rect.setOnMousePressed(mouseClickedOnEdgeEvent());
								        			
//								        			rect.getTransforms().add(new Rotate(45,rect.getX(),rect.getY()));
								        		    
								        			centerPaneDirectedNonWeightedGraph.getChildren().add(0,rect);
								        			
								        			Polygon arrowHead = new Polygon();
								        	        arrowHead.getPoints().addAll(new Double[]{
								        	            0.0, 7.0,
								        	            -7.0, -7.0,
								        	            7.0, -7.0 });
								        	        arrowHead.setFill(Color.BLACK);
								        	       
								        	        arrowHead.setOnMouseClicked(Event::consume);
									        		arrowHead.setOnMousePressed(mouseClickedOnEdgeEvent());
//								        	        
								        	        
								        	        arrowHead.rotateProperty().bind(Bindings.createDoubleBinding(
								        	        		() -> Math.atan2(rect.getY() - (rect.getY()), rect.getX()- (rect.getX()+ rect.getWidth())) * 180 / 3.14 - 90,
								        	        		rect.xProperty(),rect.yProperty(),rect.widthProperty()));
								        	        
								        	        arrowHead.layoutXProperty().bind(Bindings.createDoubleBinding(
									            	          () -> rect.getX() +selfEdgeArrowheadPlacement,
									            	          rect.xProperty()));
										            	
								            	    arrowHead.layoutYProperty().bind(Bindings.createDoubleBinding(
								            	          () -> rect.getY() ,
								            	          rect.yProperty()));
								        	        
								        	        
								        	        centerPaneDirectedNonWeightedGraph.getChildren().add(arrowHead);
								        			
								        		}else {
								        			
								        			Line line = new Line();
								        			line.setStroke(Color.BLACK);
								        			line.setFill(null);
								        			line.setStrokeWidth(2);
								        			line.startXProperty().bind(vertexClickedOn.layoutXProperty().add(vertexClickedOn.translateXProperty()).add(vertexClickedOn.widthProperty().divide(2)).add(xMoveStart));
									        		line.startYProperty().bind(vertexClickedOn.layoutYProperty().add(vertexClickedOn.translateYProperty()).add(vertexClickedOn.heightProperty().divide(2)).add(yMoveStart));
									        		line.endXProperty().bind(vertexTo.layoutXProperty().add(vertexTo.translateXProperty()).add(vertexTo.widthProperty().divide(2)).add(xMoveEnd));
									        		line.endYProperty().bind(vertexTo.layoutYProperty().add(vertexTo.translateYProperty()).add(vertexTo.heightProperty().divide(2)).add(yMoveEnd));
								        			
									        		line.setOnMouseClicked(Event::consume);
									        		line.setOnMousePressed(mouseClickedOnEdgeEvent());
								        			
								        			Polygon arrowHead = new Polygon();
								        	        arrowHead.getPoints().addAll(new Double[]{
								        	            0.0, 8.0,
								        	            -8.0, -8.0,
								        	            8.0, -8.0 });
								        	        arrowHead.setFill(Color.BLACK);
								        	       
								        	        
								        	        arrowHead.rotateProperty().bind(Bindings.createDoubleBinding(
								        	        		() -> Math.atan2(line.getEndY() - line.getStartY(), line.getEndX() - line.getStartX()) * 180 / 3.14 - 90,
								        	        		line.endXProperty(),line.endYProperty(),line.startXProperty(),line.startYProperty()));
						
								        	       

									            	arrowHead.setOnMouseClicked(Event::consume);
									        		arrowHead.setOnMousePressed(mouseClickedOnEdgeEvent());

									        		
									        		arrowHead.layoutXProperty().bind(Bindings.createDoubleBinding(
									            	          () -> ((line.getStartX() + line.getEndX() ) / 2) ,
									            	          line.startXProperty(), line.endXProperty()));
										            	
								            	    arrowHead.layoutYProperty().bind(Bindings.createDoubleBinding(
								            	          () -> ((line.getStartY() + line.getEndY() ) / 2),
								            	          line.startYProperty(), line.endYProperty()));
								            	    
									        		centerPaneDirectedNonWeightedGraph.getChildren().add(arrowHead);
									        		centerPaneDirectedNonWeightedGraph.getChildren().add(0,line);
								        		}
							        			
							        		}
						        			
						        		}
						        	}
						        	
							 }else if(getSelectedTabName().equals("Undirected Weighted Graph") || getSelectedTabName().equals("Directed Weighted Graph")) {
								 
								StackPane vertexTo = null;

						        Dialog<Pair<String, String>> dialog = new Dialog<>();
						        dialog.setTitle("Add Edge");
						        dialog.setHeaderText("Please enter the vertex you would like to create a edge to and the weight of the edge");
						        ButtonType cancelButton = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
					        	ButtonType okButton = new ButtonType("Ok", ButtonData.OK_DONE);
					        	dialog.getDialogPane().getButtonTypes().addAll(okButton, cancelButton);
					        	
					        	GridPane grid = new GridPane();
					        	grid.setHgap(10);
					        	grid.setVgap(10);
					        	grid.setPadding(new Insets(20, 150, 10, 10));
					        	
					        	TextField vertex = new TextField();
					        	vertex.setPromptText("Vertex");
					        	TextField weight = new TextField();
					        	weight.setPromptText("Weight of edge.");
					        	
					        	grid.add(new Label("Vertex:"), 0, 0);
					        	grid.add(vertex, 1, 0);
					        	grid.add(new Label("Weight:"), 0, 1);
					        	grid.add(weight, 1, 1);
						        
					        	dialog.getDialogPane().setContent(grid);
					        	
					        	// Convert the result to a username-password-pair when the login button is clicked.
					        	dialog.setResultConverter(dialogButton -> {
					        	    if (dialogButton == okButton) {
					        	        return new Pair<>(vertex.getText(), weight.getText());
					        	    }
					        	    return null;
					        	});
					        	
					        	Optional<Pair<String, String>> result = dialog.showAndWait();
					        	
					        	if(result.isPresent() && isInputValid(result.get().getKey(),dataAsString,result.get().getValue()) ){
									 if(result.isPresent()) {
										 
										 if(getSelectedTabName().equals("Undirected Weighted Graph")) {
							        			
							        			for(Node child : centerPaneUndirectedWeightedGraph.getChildren()) {
							                		
							                		if(child instanceof StackPane) {
							                			ObservableList<Node> childsOfStack = ((StackPane)child).getChildren();
							                			Text dataOfStack = (Text) childsOfStack.get(childs.size()-1);
							                			String toCompare = dataOfStack.getText();
							                			if(toCompare.equals(result.get().getKey())) {
							                				vertexTo = (StackPane) child;
							                			}
							                		}
							                	}
							        			
						        			addEdgeHandlerWeighted(dataAsString,result.get().getKey(),Double.parseDouble(result.get().getValue()));
						        			
						        			Line line = new Line();
							        		line.setStrokeWidth(2);
							        		
							        		line.setOnMouseClicked(Event::consume);
							        		line.setOnMousePressed(mouseClickedOnEdgeEvent());

							        		line.setStartX(vertexClickedOn.getLayoutX() + (vertexClickedOn.getWidth() / 2));
							        		line.setStartY(vertexClickedOn.getLayoutY() + (vertexClickedOn.getHeight() / 2));
							        		line.setEndX(vertexTo.getLayoutX() + (vertexTo.getWidth() / 2));
							        		line.setEndY(vertexTo.getLayoutY() +  (vertexTo.getHeight() / 2));

							        		line.startXProperty().bind(vertexClickedOn.layoutXProperty().add(vertexClickedOn.translateXProperty()).add(vertexClickedOn.widthProperty().divide(2)));
							        		line.startYProperty().bind(vertexClickedOn.layoutYProperty().add(vertexClickedOn.translateYProperty()).add(vertexClickedOn.heightProperty().divide(2)));
							        		line.endXProperty().bind(vertexTo.layoutXProperty().add(vertexTo.translateXProperty()).add(vertexTo.widthProperty().divide(2)));
							        		line.endYProperty().bind(vertexTo.layoutYProperty().add(vertexTo.translateYProperty()).add(vertexTo.heightProperty().divide(2)));
							            	
							        		Label label = new Label(result.get().getValue());
							            	label.setStyle("-fx-background-color: white; -fx-border-color: black;");
							            	label.setPadding(new Insets(2, 4, 2, 4));
//							            	label.setPrefWidth(25);
//							            	label.setPrefHeight(25);
							            	label.setAlignment(Pos.CENTER);
							            	label.setFont(new Font(10));

							            	label.setOnMouseClicked(Event::consume);
							        		label.setOnMousePressed(mouseClickedOnEdgeEvent());
							            	
							            	label.layoutXProperty().bind(Bindings.createDoubleBinding(
						            	          () -> (line.getStartX() + line.getEndX() - label.getWidth()) / 2,
						            	          line.startXProperty(), line.endXProperty(), label.widthProperty()));
							            	
						            	    label.layoutYProperty().bind(Bindings.createDoubleBinding(
						            	          () -> (line.getStartY() + line.getEndY() - label.getHeight()) / 2,
						            	          line.startYProperty(), line.endYProperty(), label.heightProperty()));
						            	    
						            	    
							        		
							        		centerPaneUndirectedWeightedGraph.getChildren().add(0,line);
							        		centerPaneUndirectedWeightedGraph.getChildren().add(label);
								        		
							        			
							        		}else if(getSelectedTabName().equals("Directed Weighted Graph")) {
							        			
							        			for(Node child : centerPaneDirectedWeightedGraph.getChildren()) {
							                		
							                		if(child instanceof StackPane) {
							                			ObservableList<Node> childsOfStack = ((StackPane)child).getChildren();
							                			Text dataOfStack = (Text) childsOfStack.get(childs.size()-1);
							                			String toCompare = dataOfStack.getText();
							                			if(toCompare.equals(result.get().getKey())) {
							                				vertexTo = (StackPane) child;
							                			}
							                		}
							                	}
							        			
							        		addEdgeHandlerWeighted(dataAsString,result.get().getKey(),Double.parseDouble(result.get().getValue()));
						        			
							        		directedEdgePlacement = 13;
							        		
							        		double xMoveStart = directedEdgePlacement;
						        			double yMoveStart =directedEdgePlacement;
						        			double xMoveEnd = directedEdgePlacement;
						        			double yMoveEnd =directedEdgePlacement;
						        			
						        			if(getSelectedDataChoiceDirectedWeightedGraph().equals("Integer")) {
						        				
						        				ObservableList<Node> childsVertexTo = vertexTo.getChildren();
							                	Text dataVertexTo = (Text) childsVertexTo.get(childsVertexTo.size()-1);
							                	String dataAsStringVertexTo = dataVertexTo.getText();
							                	
						        				if(dataModel.getDirectedWeightedInt().isAdjacent(Integer.parseInt(dataAsStringVertexTo), Integer.parseInt(dataAsString))) {
						        					xMoveStart = -xMoveStart;
								        			yMoveStart = -yMoveStart;
								        			
								        			xMoveEnd = -xMoveEnd;
								        			yMoveEnd = -yMoveEnd;
						        				}
						        				
						        			}else if(getSelectedDataChoiceDirectedWeightedGraph().equals("Double")) {
						        				
						        				ObservableList<Node> childsVertexTo = vertexTo.getChildren();
							                	Text dataVertexTo = (Text) childsVertexTo.get(childsVertexTo.size()-1);
							                	String dataAsStringVertexTo = dataVertexTo.getText();
							                	
						        				if(dataModel.getDirectedWeightedDouble().isAdjacent(Double.parseDouble(dataAsStringVertexTo), Double.parseDouble(dataAsString))) {
						        					xMoveStart = -xMoveStart;
								        			yMoveStart = -yMoveStart;
								        			
								        			xMoveEnd = -xMoveEnd;
								        			yMoveEnd = -yMoveEnd;
						        				}
						        				
						        				
						        			}else if(getSelectedDataChoiceDirectedWeightedGraph().equals("String")) {
						        				
						        				ObservableList<Node> childsVertexTo = vertexTo.getChildren();
							                	Text dataVertexTo = (Text) childsVertexTo.get(childsVertexTo.size()-1);
							                	String dataAsStringVertexTo = dataVertexTo.getText();
							                	
						        				if(dataModel.getDirectedWeightedString().isAdjacent(dataAsStringVertexTo,dataAsString)) {
						        					xMoveStart = -xMoveStart;
								        			yMoveStart = -yMoveStart;
								        			
								        			xMoveEnd = -xMoveEnd;
								        			yMoveEnd = -yMoveEnd;
						        				}
						        				
						        				
						        			}
							        		
							        		if(result.get().getKey().equals(dataAsString)) {
							        			
							        			Rectangle rect = new Rectangle();
							        			rect.xProperty().bind(vertexClickedOn.layoutXProperty().add(vertexClickedOn.translateXProperty()).add(vertexClickedOn.widthProperty().divide(2)));
							        			rect.yProperty().bind(vertexClickedOn.layoutYProperty().add(vertexClickedOn.translateYProperty()).add(vertexClickedOn.heightProperty().divide(2)));
							        			rect.setHeight(40);
							        			rect.setWidth(40);
							        			rect.setStroke(Color.BLACK);
							        			rect.setStrokeWidth(2);
							        			rect.setFill(null);
							        			
							        			rect.setOnMouseClicked(Event::consume);
								        		rect.setOnMousePressed(mouseClickedOnEdgeEvent());
							        			
//							        			rect.getTransforms().add(new Rotate(45,rect.getX(),rect.getY()));
							        		    
							        			centerPaneDirectedWeightedGraph.getChildren().add(0,rect);
							        			
							        	        Label label = new Label(result.get().getValue());
								            	label.setStyle("-fx-background-color: white; -fx-border-color: black;");
								            	label.setPadding(new Insets(2, 4, 2, 4));
//								            	label.setPrefWidth(25);
//								            	label.setPrefHeight(25);
								            	label.setAlignment(Pos.CENTER);
								            	label.setFont(new Font(10));
								            	
								            	label.setOnMouseClicked(Event::consume);
								        		label.setOnMousePressed(mouseClickedOnEdgeEvent());
								            	
								            	label.layoutXProperty().bind(Bindings.createDoubleBinding(
							            	          () -> (rect.getX()  + rect.getWidth()) - (label.getWidth()/2),
							            	          rect.xProperty(),label.widthProperty(),rect.widthProperty()));
								            	
							            	    label.layoutYProperty().bind(Bindings.createDoubleBinding(
							            	          () -> (rect.getY() + rect.getHeight()) - (label.getHeight()/2),
							            	          rect.yProperty(),label.heightProperty(),rect.heightProperty()));
							        			
							        			Polygon arrowHead = new Polygon();
							        	        arrowHead.getPoints().addAll(new Double[]{
							        	            0.0, 7.0,
							        	            -7.0, -7.0,
							        	            7.0, -7.0 });
							        	        arrowHead.setFill(Color.BLACK);

							        	        arrowHead.rotateProperty().bind(Bindings.createDoubleBinding(
							        	        		() -> Math.atan2(rect.getY() - (rect.getY()), rect.getX()- (rect.getX()+ rect.getWidth())) * 180 / 3.14 - 90,
							        	        		rect.xProperty(),rect.yProperty(),rect.widthProperty()));
							        	        
							        	        arrowHead.layoutXProperty().bind(Bindings.createDoubleBinding(
								            	          () -> rect.getX() +20,
								            	          rect.xProperty()));
									            	
							            	    arrowHead.layoutYProperty().bind(Bindings.createDoubleBinding(
							            	          () -> rect.getY() ,
							            	          rect.yProperty()));
							        	        
							            	    arrowHead.setOnMouseClicked(Event::consume);
								        		arrowHead.setOnMousePressed(mouseClickedOnEdgeEvent());
							        	        
							        	        centerPaneDirectedWeightedGraph.getChildren().add(arrowHead);
							        	        centerPaneDirectedWeightedGraph.getChildren().add(label);
							        			
							        		}else {
							        			
							        			Line line = new Line();
							        			line.setStroke(Color.BLACK);
							        			line.setFill(null);
							        			line.setStrokeWidth(2);
							        			line.startXProperty().bind(vertexClickedOn.layoutXProperty().add(vertexClickedOn.translateXProperty()).add(vertexClickedOn.widthProperty().divide(2)).add(xMoveStart));
								        		line.startYProperty().bind(vertexClickedOn.layoutYProperty().add(vertexClickedOn.translateYProperty()).add(vertexClickedOn.heightProperty().divide(2)).add(yMoveStart));
								        		line.endXProperty().bind(vertexTo.layoutXProperty().add(vertexTo.translateXProperty()).add(vertexTo.widthProperty().divide(2)).add(xMoveEnd));
								        		line.endYProperty().bind(vertexTo.layoutYProperty().add(vertexTo.translateYProperty()).add(vertexTo.heightProperty().divide(2)).add(yMoveEnd));
							        			
								        		line.setOnMouseClicked(Event::consume);
								        		line.setOnMousePressed(mouseClickedOnEdgeEvent());
							        			
							        			Polygon arrowHead = new Polygon();
							        	        arrowHead.getPoints().addAll(new Double[]{
							        	            0.0, 8.0,
							        	            -8.0, -8.0,
							        	            8.0, -8.0 });
							        	        arrowHead.setFill(Color.BLACK);
							        	       
							        	        
							        	        arrowHead.rotateProperty().bind(Bindings.createDoubleBinding(
							        	        		() -> Math.atan2(line.getEndY() - line.getStartY(), line.getEndX() - line.getStartX()) * 180 / 3.14 - 90,
							        	        		line.endXProperty(),line.endYProperty(),line.startXProperty(),line.startYProperty()));
					
							        	        
							        	        Label label = new Label(result.get().getValue());
								            	label.setStyle("-fx-background-color: white; -fx-border-color: black;");
								            	label.setPadding(new Insets(2, 4, 2, 4));
//								            	label.setPrefWidth(25);
//								            	label.setPrefHeight(25);
								            	label.setAlignment(Pos.CENTER);
								            	label.setFont(new Font(10));
								            	
								            	label.setOnMouseClicked(Event::consume);
								        		label.setOnMousePressed(mouseClickedOnEdgeEvent());
								            	
								            	label.layoutXProperty().bind(Bindings.createDoubleBinding(
							            	          () -> (line.getStartX() + line.getEndX() - label.getWidth()) / 2,
							            	          line.startXProperty(), line.endXProperty(), label.widthProperty()));
								            	
							            	    label.layoutYProperty().bind(Bindings.createDoubleBinding(
							            	          () -> (line.getStartY() + line.getEndY() - label.getHeight()) / 2,
							            	          line.startYProperty(), line.endYProperty(), label.heightProperty()));

								            	arrowHead.setOnMouseClicked(Event::consume);
								        		arrowHead.setOnMousePressed(mouseClickedOnEdgeEvent());
								        		
								        		arrowHead.layoutXProperty().bind(Bindings.createDoubleBinding(
								            	          () -> ((line.getStartX() + 4*line.getEndX()) / 5) ,
								            	          line.startXProperty(), line.endXProperty()));
									            	
							            	    arrowHead.layoutYProperty().bind(Bindings.createDoubleBinding(
							            	          () -> (line.getStartY() + 4*line.getEndY()) / 5,
							            	          line.startYProperty(), line.endYProperty()));
							            	    
							            	    centerPaneDirectedWeightedGraph.getChildren().add(0,label);
								        		centerPaneDirectedWeightedGraph.getChildren().add(arrowHead);
								        		centerPaneDirectedWeightedGraph.getChildren().add(0,line);
							        		}
							        			
							        		}
									 }
								 }
						        
						        
	
							 }

					         event.consume();
					            
					        });
					}
		        	
		        	if (t.getClickCount() == 2 && t.getButton() != MouseButton.SECONDARY && t.getSource() instanceof StackPane) {
		        		
		        		 Alert alert = new Alert(AlertType.ERROR);
		        		 
		        		 ButtonType cancelButton = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
		        		 ButtonType okButton = new ButtonType("Ok", ButtonData.OK_DONE);
		        		 
		                 alert.setTitle("Deleting Vertex.");
		                 alert.setHeaderText("Are you sure you want to delete this vertex?");
		                 alert.setContentText("Once deleting the vertex you cannot retrieve it back.");
		                 alert.getButtonTypes().setAll(okButton,cancelButton);
		                 
		                 Optional<ButtonType> result = alert.showAndWait();
		                 
		                 if(result.get() == okButton) {
		                	 
		                	 
		                	 ObservableList<Node> childs = ((StackPane)(t.getSource())).getChildren();
		                	 Text data = (Text) childs.get(childs.size()-1);
		                	 String dataAsString = data.getText();
		                	 
		                	 StackPane stackPane = (StackPane) t.getSource();
		                	 double stackPaneX = stackPane.getLayoutX() + (stackPane.getWidth()/2);
		                	 double stackPaneY = stackPane.getLayoutY() + (stackPane.getHeight()/2);
		                	 
		                	 Line edgeToRemove = null;
		                	 Label weightBoxToRemove = null;
		                	 Polygon arrowHeadToRemove = null;
		                	 Rectangle selfEdgeToRemove = null;
		                	 
		                	 ArrayList<Line>  edgesToRemove = new ArrayList<Line>();
		                	 ArrayList<Label> weightBoxesToRemove = new ArrayList<Label>();
		                	 ArrayList<Polygon> arrowHeadsToRemove = new ArrayList<Polygon>();
		                	 
		                	 if(getSelectedTabName().equals("Undirected Non-Weighted Graph")) {
		                		 
		                		 for(Node child:centerPaneUndirectedNonWeightedGraph.getChildren()) {
			                		 
			                		 if(child instanceof Line) {
			                			 
			                			 Line line = (Line) child;
			                			 
			                			 if((line.getStartX() == stackPaneX && line.getStartY() == stackPaneY) || 
			                					 (line.getEndX() == stackPaneX && line.getEndY() == stackPaneY)) {
			                				 edgeToRemove = line;
			                				 edgesToRemove.add(edgeToRemove);
			                			 }
			                		 }
			                		 
			                	 }
		                		 
		                		 if(edgesToRemove.size() != 0) {
			                 			
			                 			for(Line edge: edgesToRemove) {
			                 				centerPaneUndirectedNonWeightedGraph.getChildren().remove(edge);
			                 			}
			                 		}
		                		 
		                		 if(isInteger(dataAsString)) {
				                		
				                 		centerPaneUndirectedNonWeightedGraph.getChildren().remove(t.getSource());
				                 		removeVertexFromUndirectedNonWeightedGraph("Integer",dataAsString);
				                 		
				                 }else if(isDouble(dataAsString)) {
				                 		
				                 		centerPaneUndirectedNonWeightedGraph.getChildren().remove(t.getSource());
				                 		removeVertexFromUndirectedNonWeightedGraph("Double",dataAsString);
				                 		
				                 }else if(isString(dataAsString)) {
				                 		
				                 		centerPaneUndirectedNonWeightedGraph.getChildren().remove(t.getSource());
				                 		removeVertexFromUndirectedNonWeightedGraph("String",dataAsString);
				                 		
				                 }
		                		 
		                	 }else if(getSelectedTabName().equals("Undirected Weighted Graph")) {
		                		 
		                		 for(Node child:centerPaneUndirectedWeightedGraph.getChildren()) {
			                		 
			                		 if(child instanceof Line) {
			                			 
			                			 Line line = (Line) child;
			                			 
			                			 if((line.getStartX() == stackPaneX && line.getStartY() == stackPaneY) || 
			                					 (line.getEndX() == stackPaneX && line.getEndY() == stackPaneY)) {
			                				 
			                				 edgeToRemove = line;
			                				 edgesToRemove.add(edgeToRemove);
			                				 
			                				 for(Node grandchild:centerPaneUndirectedWeightedGraph.getChildren()) {
			                					 if(grandchild instanceof Label) {
			                						 
			                						 Label weightBox = ((Label)grandchild);
			                						 
			                						 if(weightBox.getLayoutX() == (line.getStartX() + line.getEndX() - weightBox.getWidth()) / 2 && 
			                							weightBox.getLayoutY() == (line.getStartY() + line.getEndY() - weightBox.getHeight()) / 2) {
			                							weightBoxToRemove =  weightBox;
			                							weightBoxesToRemove.add(weightBoxToRemove);
			                						 }
			                						 
			                					 }
			                				 }
			                			 }
			                		 }
			                		 
			                	 }
		                		 
		                		 if(edgesToRemove.size() != 0) {
			                 			
			                 		for(Line edge: edgesToRemove) {
			                 			centerPaneUndirectedWeightedGraph.getChildren().remove(edge);
			                 		}
			                 		for(Label weightBox: weightBoxesToRemove) {
			                 			centerPaneUndirectedWeightedGraph.getChildren().remove(weightBox);
			                 		}
			                 	}
		                		 
		                		 if(getSelectedTabName().equals("Undirected Weighted Graph") && isInteger(dataAsString)) {
				                 		
				                 	centerPaneUndirectedWeightedGraph.getChildren().remove(t.getSource());
				                 	removeVertexFromUndirectedWeightedGraph("Integer",dataAsString);
				                 		
				                 }else if(getSelectedTabName().equals("Undirected Weighted Graph") && isDouble(dataAsString)) {
				                 		
				                 	centerPaneUndirectedWeightedGraph.getChildren().remove(t.getSource());
				                 	removeVertexFromUndirectedWeightedGraph("Double",dataAsString);
				                 		
				                 }else if(getSelectedTabName().equals("Undirected Weighted Graph") && isString(dataAsString)) {
				                 		
				                 	centerPaneUndirectedWeightedGraph.getChildren().remove(t.getSource());
				                 	removeVertexFromUndirectedWeightedGraph("String",dataAsString);
				                 		
				                 }
		                		 
		                	 }else if(getSelectedTabName().equals("Directed Non-Weighted Graph")) {
		                		 
		                		 
		                		 for(Node child:centerPaneDirectedNonWeightedGraph.getChildren()) {
			                		 
			                		 if(child instanceof Line) {
			                			 
			                			 Line line = (Line) child;
			                			 
			                			 if((line.getStartX() == stackPaneX + directedEdgePlacement && line.getStartY() == stackPaneY + directedEdgePlacement) || 
			                				(line.getEndX() == stackPaneX + directedEdgePlacement && line.getEndY() == stackPaneY + directedEdgePlacement) ||
			                				(line.getStartX() == stackPaneX - directedEdgePlacement && line.getStartY() == stackPaneY - directedEdgePlacement) || 
		                					 (line.getEndX() == stackPaneX - directedEdgePlacement && line.getEndY() == stackPaneY - directedEdgePlacement)) {
			                				 
			                				 edgeToRemove = line;
			                				 edgesToRemove.add(edgeToRemove);
			                				 
			                				 for(Node grandchild:centerPaneDirectedNonWeightedGraph.getChildren()) {
			                					 if(grandchild instanceof Polygon) {
			                						 
			                						 Polygon arrowHead = ((Polygon)grandchild);
			                						 
			                						 if(arrowHead.getLayoutX() == (line.getStartX() + line.getEndX()) / 2 && 
			                							arrowHead.getLayoutY() == (line.getStartY() + line.getEndY()) / 2) {
			                							 arrowHeadToRemove =  arrowHead;
			                							 arrowHeadsToRemove.add(arrowHeadToRemove);
			                						 }
			                						 
			                					 }
			                				 }
			                			 }
			                		 }
			                		 
			                	 }
		                		 
		                		 for(Node child:centerPaneDirectedNonWeightedGraph.getChildren()) {
		                			 
		                			 if(child instanceof Rectangle) {
		                				 
		                				 Rectangle selfEdge = (Rectangle)child;
		                				 
		                				 if(selfEdge.getX() == stackPaneX && selfEdge.getY() == stackPaneY) {
		                					 selfEdgeToRemove = selfEdge;
		                					 
		                					 for(Node grandchild:centerPaneDirectedNonWeightedGraph.getChildren()) {
		                						 
		                						 if(grandchild instanceof Polygon) {
		                							 Polygon arrowhead = (Polygon) grandchild;
		                							 
		                							 if(arrowhead.getLayoutX() == selfEdgeToRemove.getX() + selfEdgeArrowheadPlacement 
		                								&& arrowhead.getLayoutY() == selfEdgeToRemove.getY()) {
		                								 
		                								 arrowHeadsToRemove.add(arrowhead);
		                								 
		                							 }
		                						 }
		                						 
		                					 }

		                				 }

		                			 }
		                			 
		                		 }
		                		 
		                		 if(edgesToRemove.size() != 0) {
			                 			
			                 		for(Line edge: edgesToRemove) {
			                 			centerPaneDirectedNonWeightedGraph.getChildren().remove(edge);
			                 		}
			                 		for(Polygon arrowHead: arrowHeadsToRemove) {
			                 			centerPaneDirectedNonWeightedGraph.getChildren().remove(arrowHead);
			                 		}
			                 	 }
		                		 
		                		 if(arrowHeadsToRemove.size() != 0) {	
				                 		
			                 		for(Polygon arrowHead: arrowHeadsToRemove) {
			                 			centerPaneDirectedNonWeightedGraph.getChildren().remove(arrowHead);
			                 		}
			                 		
				                 }
		                		 
		                		 if(selfEdgeToRemove != null) {
		                			 centerPaneDirectedNonWeightedGraph.getChildren().remove(selfEdgeToRemove);
		                		 }

		                		 
		                		 
		                		 if(getSelectedTabName().equals("Directed Non-Weighted Graph") && isInteger(dataAsString)) {
				                 		
				                 	centerPaneDirectedNonWeightedGraph.getChildren().remove(t.getSource());
				                 	removeVertexFromDirectedNonWeightedGraph("Integer",dataAsString);
				                 		
				                 }else if(getSelectedTabName().equals("Directed Non-Weighted Graph") && isDouble(dataAsString)) {
				                 		
				                 	centerPaneDirectedNonWeightedGraph.getChildren().remove(t.getSource());
				                 	removeVertexFromDirectedNonWeightedGraph("Double",dataAsString);
				                 		
				                 }else if(getSelectedTabName().equals("Directed Non-Weighted Graph") && isString(dataAsString)) {
				                 		
				                 	centerPaneDirectedNonWeightedGraph.getChildren().remove(t.getSource());
				                 	removeVertexFromDirectedNonWeightedGraph("String",dataAsString);
				                 		
				                 }
		                		 
		                	 }else if(getSelectedTabName().equals("Directed Weighted Graph")) {
		                		 
		                		 for(Node child:centerPaneDirectedWeightedGraph.getChildren()) {
			                		 
			                		 if(child instanceof Line) {
			                			 
			                			 Line line = (Line) child;
			                			 
			                			 if((line.getStartX() == stackPaneX + directedEdgePlacement && line.getStartY() == stackPaneY + directedEdgePlacement) || 
			                				(line.getEndX() == stackPaneX + directedEdgePlacement && line.getEndY() == stackPaneY + directedEdgePlacement) ||
			                				(line.getStartX() == stackPaneX - directedEdgePlacement && line.getStartY() == stackPaneY - directedEdgePlacement) || 
		                					 (line.getEndX() == stackPaneX - directedEdgePlacement && line.getEndY() == stackPaneY - directedEdgePlacement)) {
			                				 
			                				 edgeToRemove = line;
			                				 edgesToRemove.add(edgeToRemove);
			                				 
			                				 for(Node grandchild:centerPaneDirectedWeightedGraph.getChildren()) {
			                					 if(grandchild instanceof Polygon) {
			                						 
			                						 Polygon arrowHead = ((Polygon)grandchild);

			                						 if(arrowHead.getLayoutX() == (line.getStartX() + 4*line.getEndX()) / 5 && 
			                							arrowHead.getLayoutY() == (line.getStartY() + 4*line.getEndY()) / 5) {
			                							 arrowHeadToRemove =  arrowHead;
			                							 arrowHeadsToRemove.add(arrowHeadToRemove);
			                						 }
			                						 
			                					 }
			                				 }
			                				 
			                				 for(Node grandchild:centerPaneDirectedWeightedGraph.getChildren()) {
			                					 if(grandchild instanceof Label) {
			                						 
			                						 Label weightBox = ((Label)grandchild);
			                						 
			                						 if(weightBox.getLayoutX() == (line.getStartX() + line.getEndX() - weightBox.getWidth()) / 2 && 
			                							weightBox.getLayoutY() == (line.getStartY() + line.getEndY() - weightBox.getHeight()) / 2) {
			                							weightBoxToRemove =  weightBox;
			                							weightBoxesToRemove.add(weightBoxToRemove);
			                						 }
			                						 
			                					 }
			                				 }
			                				 
			                			 }
			                		 }
			                		 
			                	 }
		                		 
		                		 for(Node child:centerPaneDirectedWeightedGraph.getChildren()) {
		                			 
		                			 if(child instanceof Rectangle) {
		                				 
		                				 Rectangle selfEdge = (Rectangle)child;
		                				 
		                				 if(selfEdge.getX() == stackPaneX && selfEdge.getY() == stackPaneY) {
		                					 selfEdgeToRemove = selfEdge;
		                					 
		                					 for(Node grandchild:centerPaneDirectedWeightedGraph.getChildren()) {
		                						 
		                						 if(grandchild instanceof Polygon) {
		                							 Polygon arrowhead = (Polygon) grandchild;
		                							 
		                							 if(arrowhead.getLayoutX() == selfEdgeToRemove.getX() + selfEdgeArrowheadPlacement 
		                								&& arrowhead.getLayoutY() == selfEdgeToRemove.getY()) {
		                								 
		                								 arrowHeadsToRemove.add(arrowhead);
		                								 
		                							 }
		                						 }
		                						 
		                					 }
		                					 
		                					 for(Node grandchild:centerPaneDirectedWeightedGraph.getChildren()) {
			                					 if(grandchild instanceof Label) {

			                						 Label weightBox = ((Label)grandchild);
			                						 
			                						 if(weightBox.getLayoutX() == (selfEdgeToRemove.getX()  + selfEdgeToRemove.getWidth()) - (weightBox.getWidth()/2) && 
			                							weightBox.getLayoutY() == (selfEdgeToRemove.getY() + selfEdgeToRemove.getHeight()) - (weightBox.getHeight()/2)) {
			                							weightBoxToRemove =  weightBox;
			                							weightBoxesToRemove.add(weightBoxToRemove);
			                						 }
			                						 
			                					 }
			                				 }
		                					 

		                				 }

		                			 }
		                			 
		                		 }
		                		 
		                		 if(edgesToRemove.size() != 0) {
			                 			
			                 		for(Line edge: edgesToRemove) {
			                 			centerPaneDirectedWeightedGraph.getChildren().remove(edge);
			                 		}
			                 		
			                 		for(Polygon arrowHead: arrowHeadsToRemove) {
			                 			centerPaneDirectedWeightedGraph.getChildren().remove(arrowHead);
			                 		}
			                 		
			                 		for(Label weightBox: weightBoxesToRemove) {
			                 			centerPaneDirectedWeightedGraph.getChildren().remove(weightBox);
			                 		}

			                 	 }
		                		 
		                		 if(arrowHeadsToRemove.size() != 0) {	
				                 		
			                 		for(Polygon arrowHead: arrowHeadsToRemove) {
			                 			centerPaneDirectedWeightedGraph.getChildren().remove(arrowHead);
			                 		}
			                 		
				                 }
		                		 
		                		 if(weightBoxesToRemove.size() != 0) {	
				                 		
		                			 for(Label weightBox: weightBoxesToRemove) {
				                 		centerPaneDirectedWeightedGraph.getChildren().remove(weightBox);
				                 	}
				                 		
					             }
		                		 
		                		 if(selfEdgeToRemove != null) {
		                			 centerPaneDirectedWeightedGraph.getChildren().remove(selfEdgeToRemove);
		                		 }
		                		 
		                		 
		                		 if(getSelectedTabName().equals("Directed Weighted Graph") && isInteger(dataAsString)) {
				                 		
				                 	centerPaneDirectedWeightedGraph.getChildren().remove(t.getSource());
				                 	removeVertexFromDirectedWeightedGraph("Integer",dataAsString);
				                 		
				                 }else if(getSelectedTabName().equals("Directed Weighted Graph") && isDouble(dataAsString)) {
				                 		
				                 	centerPaneDirectedWeightedGraph.getChildren().remove(t.getSource());
				                 	removeVertexFromDirectedWeightedGraph("Double",dataAsString);
				                 		
				                 }else if(getSelectedTabName().equals("Directed Weighted Graph") && isString(dataAsString)) {
				                 		
				                 	centerPaneDirectedWeightedGraph.getChildren().remove(t.getSource());
				                 	removeVertexFromDirectedWeightedGraph("String",dataAsString);
				                 		
				                 }
		                		 
		                	 }
		                	 
		                 }
		        	 }

		        	currentStackPane  = ((StackPane)(t.getSource()));
		        	orgSceneX = t.getSceneX();
		            orgSceneY = t.getSceneY();
		        	layoutX =  currentStackPane.getLayoutX();
		        	layoutY =  currentStackPane.getLayoutY();
		            
		            orgTranslateX = ((StackPane)(t.getSource())).getTranslateX();
		            orgTranslateY = ((StackPane)(t.getSource())).getTranslateY();
		            
		            
		        }
		    };
		    
		    return circleOnMousePressedEventHandler;
	}
	
	@FXML
	private EventHandler<MouseEvent> mouseDraggedOnVertexEvent() {
		
		EventHandler<MouseEvent> circleOnMouseDraggedEventHandler = 
		        new EventHandler<MouseEvent>() {
		 
		        @Override
		        public void handle(MouseEvent t) {
		        
		        	// Offset of drag
		            double offsetX = t.getSceneX() - orgSceneX;
		            double offsetY = t.getSceneY() - orgSceneY;

		            // Taking parent bounds
		            Bounds parentBounds = currentStackPane.getParent().getLayoutBounds();

		            // Drag node bounds
		            double currPaneLayoutX = currentStackPane.getLayoutX();
		            double currPaneWidth = currentStackPane.getWidth();
		            double currPaneLayoutY = currentStackPane.getLayoutY();
		            double currPaneHeight = currentStackPane.getHeight();

		            if ((currPaneLayoutX + offsetX > -1) && (currPaneLayoutX + offsetX < parentBounds.getWidth() - currPaneWidth)) {
		                // If the dragNode bounds is within the parent bounds, then you can set the offset value.
		                currentStackPane.setTranslateX(offsetX);
		            } else if (currPaneLayoutX + offsetX < 0) {
		                // If the sum of your offset and current layout position is negative, then you ALWAYS update your translate value to negative layout value
		                // which makes the final layout position to 0 in mouse released event.
		                currentStackPane.setTranslateX(-currPaneLayoutX);
		            } else {
		                // If your dragNode bounds are outside parent bounds,ALWAYS setting the translate value that fits your node at end.
		                currentStackPane.setTranslateX(parentBounds.getWidth() - currPaneLayoutX - currPaneWidth);
		            }

		            if ((currPaneLayoutY + offsetY < parentBounds.getHeight() - currPaneHeight) && (currPaneLayoutY + offsetY > -1)) {
		                currentStackPane.setTranslateY(offsetY);
		            } else if (currPaneLayoutY + offsetY < 0) {
		                currentStackPane.setTranslateY(-currPaneLayoutY);
		            } else {
		                currentStackPane.setTranslateY(parentBounds.getHeight() - currPaneLayoutY - currPaneHeight);
		            }
		        }
		    };
		    
		    return circleOnMouseDraggedEventHandler;
		    
	}
	
	@FXML
	private EventHandler<MouseEvent> mouseReleasedOnVertexEvent() {
		
		
     
     EventHandler<MouseEvent> circleOnMouseReleaseEventHandler = 
		        new EventHandler<MouseEvent>() {
		 
		        @Override
		        public void handle(MouseEvent t) {
		        	
		        	currentStackPane.setLayoutX(layoutX + ((StackPane)(t.getSource())).getTranslateX());
		        	currentStackPane.setLayoutY(layoutY + ((StackPane)(t.getSource())).getTranslateY());
		        	currentStackPane.setTranslateX(0);
		        	currentStackPane.setTranslateY(0);
		        }
		    };
		    return circleOnMouseReleaseEventHandler;
		    
	}
	
	 
	
	@FXML
	private void selectionChoiceUndirectedNonWeightedGraph(MouseEvent e) {
		preSelectionChoiceUndirectedNonWeightedGraph = getSelectedDataChoiceUndirectedNonWeightedGraph();
	}
	
	@FXML
	private void selectionChoiceUndirectedWeightedGraph(MouseEvent e) {
		preSelectionChoiceUndirectedWeightedGraph = getSelectedDataChoiceUndirectedWeightedGraph();
	}
	
	@FXML
	private void selectionChoiceDirectedNonWeightedGraph(MouseEvent e) {
		preSelectionChoiceDirectedNonWeightedGraph = getSelectedDataChoiceDirectedNonWeightedGraph();
	}
	
	@FXML
	private void selectionChoiceDirectedWeightedGraph(MouseEvent e) {
		preSelectionChoiceDirectedWeightedGraph = getSelectedDataChoiceDirectedWeightedGraph();
	}

	
	@FXML
	private void handleChoiceBox(ActionEvent e) {
		
		Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Changing data type warning");
        alert.setHeaderText("Are you sure you want to change the data type?");
        alert.setContentText("If you click OK thew current graph you created will be cleared.");
        ButtonType cancelButton = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
		ButtonType okButton = new ButtonType("Ok", ButtonData.OK_DONE);
		alert.getButtonTypes().setAll(okButton,cancelButton);
        Optional<ButtonType> result = alert.showAndWait();
         
 		if(getSelectedTabName().equals("Undirected Non-Weighted Graph")) {
 			
 			if(result.get() == okButton) {
 				
 				centerPaneUndirectedNonWeightedGraph.getChildren().clear();
 				
 	        	 if(preSelectionChoiceUndirectedNonWeightedGraph.equals("Integer")) {
 	        		 
 	        		 dataModel.getListOfUndirectedNonWeightedIntVertices().clear();
 	        		 dataModel.getVertexDataUndirectedNonWeightedInt().clear();
 	        		 dataModel.getUndirectedNonWeightedInt().clearGraph();
 	        		 
 	        	 }else if(preSelectionChoiceUndirectedNonWeightedGraph.equals("Double")) {
 	        		 
 	        		dataModel.getListOfUndirectedNonWeightedDoubleVertices().clear();
	        		dataModel.getVertexDataUndirectedNonWeightedDouble().clear();
	        		dataModel.getUndirectedNonWeightedDouble().clearGraph();
 	        		 
 	        	 }else if(preSelectionChoiceUndirectedNonWeightedGraph.equals("String")) {
 	        		 
 	        		dataModel.getListOfUndirectedNonWeightedStringVertices().clear();
	        		dataModel.getVertexDataUndirectedNonWeightedString().clear();
	        		dataModel.getUndirectedNonWeightedString().clearGraph();
 	        		 
 	        	 }
 
 	         }else if(result.get() == cancelButton){
 	        	 
 	        	 choiceBoxUndirectedNonWeightedGraph.setValue(preSelectionChoiceUndirectedNonWeightedGraph);

 	         }
 			
 		}
 		
 		else if(getSelectedTabName().equals("Undirected Weighted Graph")) {
 			
 			if(result.get() == okButton) {
 				
 				centerPaneUndirectedWeightedGraph.getChildren().clear();
 				
 	        	 if(preSelectionChoiceUndirectedWeightedGraph.equals("Integer")) {
 	        		 
 	        		 dataModel.getListOfUndirectedWeightedIntVertices().clear();
 	        		 dataModel.getVertexDataUndirectedWeightedInt().clear();
 	        		 dataModel.getUndirectedWeightedInt().clearGraph();
 	        		 
 	        	 }else if(preSelectionChoiceUndirectedWeightedGraph.equals("Double")) {
 	        		 
 	        		dataModel.getListOfUndirectedWeightedDoubleVertices().clear();
	        		dataModel.getVertexDataUndirectedWeightedDouble().clear();
	        		dataModel.getUndirectedWeightedDouble().clearGraph();
 	        		 
 	        	 }else if(preSelectionChoiceUndirectedWeightedGraph.equals("String")) {
 	        		 
 	        		dataModel.getListOfUndirectedWeightedStringVertices().clear();
	        		dataModel.getVertexDataUndirectedWeightedString().clear();
	        		dataModel.getUndirectedWeightedString().clearGraph();
 	        		 
 	        	 }

 	         }else if(result.get() == cancelButton){
 	        	 choiceBoxUndirectedWeightedGraph.setValue(preSelectionChoiceUndirectedWeightedGraph);

 	         }
 	 			
 	 	}
 		
 		else if(getSelectedTabName().equals("Directed Non-Weighted Graph")) {
 			
 			if(result.get() == okButton) {
 				
 				centerPaneDirectedNonWeightedGraph.getChildren().clear();
 				
 	        	 if(preSelectionChoiceDirectedNonWeightedGraph.equals("Integer")) {
 	        		 
 	        		 dataModel.getListOfDirectedNonWeightedIntVertices().clear();
 	        		 dataModel.getVertexDataDirectedNonWeightedInt().clear();
 	        		 dataModel.getDirectedNonWeightedInt().clearGraph();
 	        		 
 	        	 }else if(preSelectionChoiceDirectedNonWeightedGraph.equals("Double")) {
 	        		 
 	        		dataModel.getListOfDirectedNonWeightedDoubleVertices().clear();
	        		dataModel.getVertexDataDirectedNonWeightedDouble().clear();
	        		dataModel.getDirectedNonWeightedDouble().clearGraph();
 	        		 
 	        	 }else if(preSelectionChoiceDirectedNonWeightedGraph.equals("String")) {
 	        		 
 	        		dataModel.getListOfDirectedNonWeightedStringVertices().clear();
	        		dataModel.getVertexDataDirectedNonWeightedString().clear();
	        		dataModel.getDirectedNonWeightedString().clearGraph();
 	        		 
 	        	 }

 	         }else if(result.get() == cancelButton){
 	        	 choiceBoxDirectedNonWeightedGraph.setValue(preSelectionChoiceDirectedNonWeightedGraph);

 	         }
 		}
 			
 		else if(getSelectedTabName().equals("Directed Weighted Graph")) {
	 			
	 			if(result.get() == okButton) {
	 				
	 				centerPaneDirectedWeightedGraph.getChildren().clear();
	 				
	 	        	 if(preSelectionChoiceDirectedWeightedGraph.equals("Integer")) {
	 	        		 
	 	        		 dataModel.getListOfDirectedWeightedIntVertices().clear();
	 	        		 dataModel.getVertexDataDirectedWeightedInt().clear();
	 	        		 dataModel.getDirectedWeightedInt().clearGraph();
	 	        		 
	 	        	 }else if(preSelectionChoiceDirectedWeightedGraph.equals("Double")) {
	 	        		 
	 	        		dataModel.getListOfDirectedWeightedDoubleVertices().clear();
		        		dataModel.getVertexDataDirectedWeightedDouble().clear();
		        		dataModel.getDirectedWeightedDouble().clearGraph();
	 	        		 
	 	        	 }else if(preSelectionChoiceDirectedWeightedGraph.equals("String")) {
	 	        		 
	 	        		dataModel.getListOfDirectedWeightedStringVertices().clear();
		        		dataModel.getVertexDataDirectedWeightedString().clear();
		        		dataModel.getDirectedWeightedString().clearGraph();
	 	        		 
	 	        	 }
	 	        	 
	 	         }else if(result.get() == cancelButton){
	 	        	 choiceBoxDirectedWeightedGraph.setValue(preSelectionChoiceDirectedWeightedGraph);

	 	         }
	 		}
	}
	
	@FXML
	private void handleAddVertex(MouseEvent event) {
		boolean okClicked = main.showAddVertexPopUp(this);
		
		if(okClicked) {
			
			String vertexText = "";
			
			if(getSelectedDataChoiceUndirectedNonWeightedGraph().equals("Integer") && getSelectedTabName().equals("Undirected Non-Weighted Graph")) {
				
				vertexText = dataModel.getListOfUndirectedNonWeightedIntVertices().get(dataModel.getListOfUndirectedNonWeightedIntVertices().size() - 1).toString();
				
			}else if(getSelectedDataChoiceUndirectedNonWeightedGraph().equals("Double") && getSelectedTabName().equals("Undirected Non-Weighted Graph")){
				
				vertexText = dataModel.getListOfUndirectedNonWeightedDoubleVertices().get(dataModel.getListOfUndirectedNonWeightedDoubleVertices().size() - 1).toString();
				
			}else if(getSelectedDataChoiceUndirectedNonWeightedGraph().equals("String") && getSelectedTabName().equals("Undirected Non-Weighted Graph")){
				
				vertexText = dataModel.getListOfUndirectedNonWeightedStringVertices().get(dataModel.getListOfUndirectedNonWeightedStringVertices().size() - 1).toString();
				
			}
			
			else if(getSelectedDataChoiceUndirectedWeightedGraph().equals("Integer") && getSelectedTabName().equals("Undirected Weighted Graph")) {
				
				vertexText = dataModel.getListOfUndirectedWeightedIntVertices().get(dataModel.getListOfUndirectedWeightedIntVertices().size() - 1).toString();
				
			}else if(getSelectedDataChoiceUndirectedWeightedGraph().equals("Double") && getSelectedTabName().equals("Undirected Weighted Graph")){
				
				vertexText = dataModel.getListOfUndirectedWeightedDoubleVertices().get(dataModel.getListOfUndirectedWeightedDoubleVertices().size() - 1).toString();
				
			}else if(getSelectedDataChoiceUndirectedWeightedGraph().equals("String") && getSelectedTabName().equals("Undirected Weighted Graph")){
				
				vertexText = dataModel.getListOfUndirectedWeightedStringVertices().get(dataModel.getListOfUndirectedWeightedStringVertices().size() - 1).toString();
				
			}
			
			else if(getSelectedDataChoiceDirectedNonWeightedGraph().equals("Integer") && getSelectedTabName().equals("Directed Non-Weighted Graph")) {
				
				vertexText = dataModel.getListOfDirectedNonWeightedIntVertices().get(dataModel.getListOfDirectedNonWeightedIntVertices().size() - 1).toString();
				
			}else if(getSelectedDataChoiceDirectedNonWeightedGraph().equals("Double") && getSelectedTabName().equals("Directed Non-Weighted Graph")){
				
				vertexText = dataModel.getListOfDirectedNonWeightedDoubleVertices().get(dataModel.getListOfDirectedNonWeightedDoubleVertices().size() - 1).toString();
				
			}else if(getSelectedDataChoiceDirectedNonWeightedGraph().equals("String") && getSelectedTabName().equals("Directed Non-Weighted Graph")){
				
				vertexText = dataModel.getListOfDirectedNonWeightedStringVertices().get(dataModel.getListOfDirectedNonWeightedStringVertices().size() - 1).toString();
				
			}
			
			else if(getSelectedDataChoiceDirectedWeightedGraph().equals("Integer") && getSelectedTabName().equals("Directed Weighted Graph")) {
				
				vertexText = dataModel.getListOfDirectedWeightedIntVertices().get(dataModel.getListOfDirectedWeightedIntVertices().size() - 1).toString();
				
			}else if(getSelectedDataChoiceDirectedWeightedGraph().equals("Double") && getSelectedTabName().equals("Directed Weighted Graph")){
				
				vertexText = dataModel.getListOfDirectedWeightedDoubleVertices().get(dataModel.getListOfDirectedWeightedDoubleVertices().size() - 1).toString();
				
			}else if(getSelectedDataChoiceDirectedWeightedGraph().equals("String") && getSelectedTabName().equals("Directed Weighted Graph")){
				
				vertexText = dataModel.getListOfDirectedWeightedStringVertices().get(dataModel.getListOfDirectedWeightedStringVertices().size() - 1).toString();
				
			}
			
       		double x = event.getX();
        	double y = event.getY();
        	
   			Circle vertex = new Circle(x, y, 20, Color.WHITE);
   			vertex.setStroke(Color.BLACK);
   			
   			Text text = new Text (vertexText);
   			
   			StackPane stack = new StackPane();
   			
   			stack.getChildren().addAll(vertex, text);
   			stack.setLayoutX(x);

   			stack.setLayoutY(y);
   			
   			stack.setOnMouseClicked(Event::consume);
   			stack.setOnMousePressed(mousePressedOnVertexEvent(stack));
   			stack.setOnMouseDragged(mouseDraggedOnVertexEvent());
   			stack.setOnMouseReleased(mouseReleasedOnVertexEvent());
   			
   			if(getSelectedTabName().equals("Undirected Non-Weighted Graph")) {
   				centerPaneUndirectedNonWeightedGraph.getChildren().add(stack);
   			}else if(getSelectedTabName().equals("Undirected Weighted Graph")){
   				centerPaneUndirectedWeightedGraph.getChildren().add(stack);
   			}else if(getSelectedTabName().equals("Directed Non-Weighted Graph")) {
   				centerPaneDirectedNonWeightedGraph.getChildren().add(stack);
   			}else if(getSelectedTabName().equals("Directed Weighted Graph")) {
   				centerPaneDirectedWeightedGraph.getChildren().add(stack);
   			}
			
   			
   			
		}	
   			
   			
	}
		
	public String getSelectedDataChoiceUndirectedNonWeightedGraph() {

		return choiceBoxUndirectedNonWeightedGraph.getSelectionModel().getSelectedItem();

	}
	
	public String getSelectedDataChoiceUndirectedWeightedGraph() {

		return choiceBoxUndirectedWeightedGraph.getSelectionModel().getSelectedItem();

	}
	
	public String getSelectedDataChoiceDirectedNonWeightedGraph() {

		return choiceBoxDirectedNonWeightedGraph.getSelectionModel().getSelectedItem();

	}
	
	public String getSelectedDataChoiceDirectedWeightedGraph() {

		return choiceBoxDirectedWeightedGraph.getSelectionModel().getSelectedItem();

	}
	
	public String getSelectedTabName() {

		return tabs.getSelectionModel().getSelectedItem().getText();

	}
	
	public void setDataModel(DataModel dataModel) {
		this.dataModel = dataModel;
	}
	
	private void removeVertexFromUndirectedNonWeightedGraph(String type, String dataAsString) {
    	
    	if(type.equals("Integer")) {
    		
    		Vertex<Integer> vertexToRemove = dataModel.getUndirectedNonWeightedInt().returnVertex(Integer.parseInt(dataAsString));
    		
    		dataModel.getVertexDataUndirectedNonWeightedInt().remove(vertexToRemove);
    		
    		dataModel.getListOfUndirectedNonWeightedIntVertices().remove(vertexToRemove);
    		
    		dataModel.getUndirectedNonWeightedInt().removeVertex(vertexToRemove.getElement());
    		
    		
    	}else if(type.equals("Double")) {
    		
    		Vertex<Double> vertexToRemove = dataModel.getUndirectedNonWeightedDouble().returnVertex(Double.parseDouble(dataAsString));
    		
    		dataModel.getVertexDataUndirectedNonWeightedDouble().remove(vertexToRemove);
    		
    		dataModel.getListOfUndirectedNonWeightedDoubleVertices().remove(vertexToRemove);
    		
    		dataModel.getUndirectedNonWeightedDouble().removeVertex(vertexToRemove.getElement());
		
    	}else if(type.equals("String")) {
    		
    		Vertex<String> vertexToRemove = dataModel.getUndirectedNonWeightedString().returnVertex(dataAsString);
    		
    		dataModel.getVertexDataUndirectedNonWeightedString().remove(vertexToRemove);
    		
    		dataModel.getListOfUndirectedNonWeightedStringVertices().remove(vertexToRemove);
    		
    		dataModel.getUndirectedNonWeightedString().removeVertex(vertexToRemove.getElement());
		
    		
    	}
    }
	 
	private void removeVertexFromUndirectedWeightedGraph(String type, String dataAsString) {
    	
    	if(type.equals("Integer")) {
    		
    		Vertex<Integer> vertexToRemove = dataModel.getUndirectedWeightedInt().returnVertex(Integer.parseInt(dataAsString));
    		
    		dataModel.getVertexDataUndirectedWeightedInt().remove(vertexToRemove);
    		
    		dataModel.getListOfUndirectedWeightedIntVertices().remove(vertexToRemove);
    		
    		dataModel.getUndirectedWeightedInt().removeVertex(vertexToRemove.getElement());
    		
    		
    	}else if(type.equals("Double")) {
    		
    		Vertex<Double> vertexToRemove = dataModel.getUndirectedWeightedDouble().returnVertex(Double.parseDouble(dataAsString));
    		
    		dataModel.getVertexDataUndirectedWeightedDouble().remove(vertexToRemove);
    		
    		dataModel.getListOfUndirectedWeightedDoubleVertices().remove(vertexToRemove);
    		
    		dataModel.getUndirectedWeightedDouble().removeVertex(vertexToRemove.getElement());
	
    	}else if(type.equals("String")) {
    		
    		Vertex<String> vertexToRemove = dataModel.getUndirectedNonWeightedString().returnVertex(dataAsString);
    		
    		dataModel.getVertexDataUndirectedWeightedString().remove(vertexToRemove);
    		
    		dataModel.getListOfUndirectedWeightedStringVertices().remove(vertexToRemove);
    		
    		dataModel.getUndirectedWeightedString().removeVertex(vertexToRemove.getElement());
	
    		
    	}
    }
 
 	private void removeVertexFromDirectedNonWeightedGraph(String type, String dataAsString) {
    	
    	if(type.equals("Integer")) {
    		
    		Vertex<Integer> vertexToRemove = dataModel.getDirectedNonWeightedInt().returnVertex(Integer.parseInt(dataAsString));
    		
    		dataModel.getVertexDataDirectedNonWeightedInt().remove(vertexToRemove);
    		
    		dataModel.getListOfDirectedNonWeightedIntVertices().remove(vertexToRemove);
    		
    		dataModel.getDirectedNonWeightedInt().removeVertex(vertexToRemove.getElement());
    		
    		
    	}else if(type.equals("Double")) {
    		
    		Vertex<Double> vertexToRemove = dataModel.getDirectedNonWeightedDouble().returnVertex(Double.parseDouble(dataAsString));
    		
    		dataModel.getVertexDataDirectedNonWeightedDouble().remove(vertexToRemove);
    		
    		dataModel.getListOfDirectedNonWeightedDoubleVertices().remove(vertexToRemove);
    		
    		dataModel.getDirectedNonWeightedDouble().removeVertex(vertexToRemove.getElement());
	
    	}else if(type.equals("String")) {
    		
    		Vertex<String> vertexToRemove = dataModel.getDirectedNonWeightedString().returnVertex(dataAsString);
    		
    		dataModel.getVertexDataDirectedNonWeightedString().remove(vertexToRemove);
    		
    		dataModel.getListOfDirectedNonWeightedStringVertices().remove(vertexToRemove);
    		
    		dataModel.getDirectedNonWeightedString().removeVertex(vertexToRemove.getElement());
	
    		
    	}
    }
 	
 	private void removeVertexFromDirectedWeightedGraph(String type, String dataAsString) {
    	
    	if(type.equals("Integer")) {
    		
    		Vertex<Integer> vertexToRemove = dataModel.getDirectedWeightedInt().returnVertex(Integer.parseInt(dataAsString));
    		
    		dataModel.getVertexDataDirectedWeightedInt().remove(vertexToRemove);
    		
    		dataModel.getListOfDirectedWeightedIntVertices().remove(vertexToRemove);
    		
    		dataModel.getDirectedWeightedInt().removeVertex(vertexToRemove.getElement());
    		
    		
    	}else if(type.equals("Double")) {
    		
    		Vertex<Double> vertexToRemove = dataModel.getDirectedWeightedDouble().returnVertex(Double.parseDouble(dataAsString));
    		
    		dataModel.getVertexDataDirectedWeightedDouble().remove(vertexToRemove);
    		
    		dataModel.getListOfDirectedWeightedDoubleVertices().remove(vertexToRemove);
    		
    		dataModel.getDirectedWeightedDouble().removeVertex(vertexToRemove.getElement());
	
    	}else if(type.equals("String")) {
    		
    		Vertex<String> vertexToRemove = dataModel.getDirectedWeightedString().returnVertex(dataAsString);
    		
    		dataModel.getVertexDataDirectedWeightedString().remove(vertexToRemove);
    		
    		dataModel.getListOfDirectedWeightedStringVertices().remove(vertexToRemove);
    		
    		dataModel.getDirectedWeightedString().removeVertex(vertexToRemove.getElement());
	
    		
    	}
    }
	
	 private static boolean isInteger(String s) {
        try { 
            Integer.parseInt(s); 
        } catch(NumberFormatException e) { 
            return false; 
        } catch(NullPointerException e) {
            return false;
        }
        return true;
    }
    
    private static boolean isDouble(String s) {

    	try {
    		Integer.parseInt(s);
    	}catch(NumberFormatException e) {
    		try {
    			Double.parseDouble(s);
            }catch(NumberFormatException e1) {
    			return false;
    		}
    		return true;
    	}
    	return false;

    }
    
    private boolean isString(String s) {
    	if(!isInteger(s) && !isDouble(s)) {
    		return true;
    	}
    	return false;
    }
    
    private boolean isInputValid(String input,String startVertex,String weightInput) {
    	
    	String selectedDataChoiceUndirectedNonWeightedGraph = getSelectedDataChoiceUndirectedNonWeightedGraph();
    	String selectedDataChoiceUndirectedWeightedGraph = getSelectedDataChoiceUndirectedWeightedGraph();
    	String selectedDataChoiceDirectedNonWeightedGraph = getSelectedDataChoiceDirectedNonWeightedGraph();
    	String selectedDataChoiceDirectedWeightedGraph = getSelectedDataChoiceDirectedWeightedGraph();
    	
    	String errorMessage = "";
    	if (input == null || input.length() == 0) {
    		
    		errorMessage += "Field cannot be left empty please add some data.";
    		
    	}
    	else if((getSelectedTabName().equals("Undirected Weighted Graph") || getSelectedTabName().equals("Directed Weighted Graph")) &&
    			!isDouble(weightInput)) {
    		
    		errorMessage += "Weight of an edge must be of data type double.";
    		
    	}
    	else if(input.equals(startVertex) && (getSelectedTabName().equals("Undirected Non-Weighted Graph") || getSelectedTabName().equals("Undirected Weighted Graph"))) {
			errorMessage+="You cannot add an edge to itself.";
		}
    	
    	else if(isInteger(input) && getSelectedTabName().equals("Undirected Non-Weighted Graph") 
    			&& !selectedDataChoiceUndirectedNonWeightedGraph.equals("Integer")) {
    		
    		errorMessage+="Invalid data type you must enter data of type " + selectedDataChoiceUndirectedNonWeightedGraph + ".";
    		
    	}
    	else if(isDouble(input) && getSelectedTabName().equals("Undirected Non-Weighted Graph") && 
    			!selectedDataChoiceUndirectedNonWeightedGraph.equals("Double")) {
    		
    		errorMessage+="Invalid data type you must enter data of type " + selectedDataChoiceUndirectedNonWeightedGraph + ".";
    		
    	}
    	else if(isString(input) && getSelectedTabName().equals("Undirected Non-Weighted Graph") && 
    			!selectedDataChoiceUndirectedNonWeightedGraph.equals("String")) {
    		
    		errorMessage+="Invalid data type you must enter data of type " + selectedDataChoiceUndirectedNonWeightedGraph + ".";
    		
    	}
    	else if(isInteger(input) && getSelectedTabName().equals("Undirected Weighted Graph") 
    			&& !selectedDataChoiceUndirectedWeightedGraph.equals("Integer")) {
    		
    		errorMessage+="Invalid data type you must enter data of type " + selectedDataChoiceUndirectedWeightedGraph + ".";
    		
    	}
    	else if(isDouble(input) && getSelectedTabName().equals("Undirected Weighted Graph") && 
    			!selectedDataChoiceUndirectedWeightedGraph.equals("Double")) {
    		
    		errorMessage+="Invalid data type you must enter data of type " + selectedDataChoiceUndirectedWeightedGraph + ".";
    		
    	}
    	else if(isString(input) && getSelectedTabName().equals("Undirected Weighted Graph") && 
    			!selectedDataChoiceUndirectedWeightedGraph.equals("String")) {
    		
    		errorMessage+="Invalid data type you must enter data of type " + selectedDataChoiceUndirectedWeightedGraph + ".";
    		
    	}
    	else if(isInteger(input) && getSelectedTabName().equals("Directed Non-Weighted Graph") 
    			&& !selectedDataChoiceDirectedNonWeightedGraph.equals("Integer")) {
    		
    		errorMessage+="Invalid data type you must enter data of type " + selectedDataChoiceDirectedNonWeightedGraph + ".";
    		
    	}
    	else if(isDouble(input) && getSelectedTabName().equals("Directed Non-Weighted Graph") && 
    			!selectedDataChoiceDirectedNonWeightedGraph.equals("Double")) {
    		
    		errorMessage+="Invalid data type you must enter data of type " + selectedDataChoiceDirectedNonWeightedGraph + ".";
    		
    	}
    	else if(isString(input) && getSelectedTabName().equals("Directed Non-Weighted Graph") && 
    			!selectedDataChoiceDirectedNonWeightedGraph.equals("String")) {
    		
    		errorMessage+="Invalid data type you must enter data of type " + selectedDataChoiceDirectedNonWeightedGraph + ".";
    		
    	}
    	else if(isInteger(input) && getSelectedTabName().equals("Directed Weighted Graph") 
    			&& !selectedDataChoiceDirectedWeightedGraph.equals("Integer")) {
    		
    		errorMessage+="Invalid data type you must enter data of type " + selectedDataChoiceDirectedWeightedGraph + ".";
    		
    	}
    	else if(isDouble(input) && getSelectedTabName().equals("Directed Weighted Graph") && 
    			!selectedDataChoiceDirectedWeightedGraph.equals("Double")) {
    		
    		errorMessage+="Invalid data type you must enter data of type " + selectedDataChoiceDirectedWeightedGraph + ".";
    		
    	}
    	else if(isString(input) && getSelectedTabName().equals("Directed Weighted Graph") && 
    			!selectedDataChoiceDirectedWeightedGraph.equals("String")) {
    		
    		errorMessage+="Invalid data type you must enter data of type " + selectedDataChoiceDirectedWeightedGraph + ".";
    		
    	}
    	
    	
    	
    	
    	else if(isInteger(input) && getSelectedTabName().equals("Undirected Non-Weighted Graph")){
    		
    		if(!dataModel.getUndirectedNonWeightedInt().containsVertex(Integer.parseInt(input))) {
    			errorMessage+="Vertex with data '" + input + "' does not exist in this graph.";
    		}else if(dataModel.getUndirectedNonWeightedInt().isAdjacent(Integer.parseInt(startVertex), Integer.parseInt(input))) {
    			errorMessage+="There already exists an edge between vertex with data '" + startVertex + "' and vertex with data '" + input + "'.";
    		}

    	}else if(isDouble(input) && getSelectedTabName().equals("Undirected Non-Weighted Graph")){

    		if(!dataModel.getUndirectedNonWeightedDouble().containsVertex(Double.parseDouble(input))) {
    			errorMessage+="Vertex with data '" + input + "' does not exist in this graph.";
    		}else if(dataModel.getUndirectedNonWeightedDouble().isAdjacent(Double.parseDouble(startVertex), Double.parseDouble(input))) {
    			errorMessage+="There already exists an edge between vertex with data '" + startVertex + "' and vertex with data '" + input + "'.";
    		}
    		
    	}else if(isString(input) && getSelectedTabName().equals("Undirected Non-Weighted Graph")){

    		if(!dataModel.getUndirectedNonWeightedString().containsVertex(input)) {
    			errorMessage+="Vertex with data '" + input + "' does not exist in this graph.";
    		}else if(dataModel.getUndirectedNonWeightedString().isAdjacent(startVertex, input)) {
    			errorMessage+="There already exists an edge between vertex with data '" + startVertex + "' and vertex with data '" + input + "'.";
    		}
    		
    	}
    	else if(isInteger(input) && getSelectedTabName().equals("Undirected Weighted Graph")){

    		if(!dataModel.getUndirectedWeightedInt().containsVertex(Integer.parseInt(input))) {
    			errorMessage+="Vertex with data '" + input + "' does not exist in this graph.";
    		}else if(dataModel.getUndirectedWeightedInt().isAdjacent(Integer.parseInt(startVertex), Integer.parseInt(input))) {
    			errorMessage+="There already exists an edge between vertex with data '" + startVertex + "' and vertex with data '" + input + "'.";
    		}
    		
    	}else if(isDouble(input) && getSelectedTabName().equals("Undirected Weighted Graph") ){

    		if(!dataModel.getUndirectedWeightedDouble().containsVertex(Double.parseDouble(input))) {
    			errorMessage+="Vertex with data '" + input + "' does not exist in this graph.";
    		}else if(dataModel.getUndirectedWeightedDouble().isAdjacent(Double.parseDouble(startVertex), Double.parseDouble(input))) {
    			errorMessage+="There already exists an edge between vertex with data '" + startVertex + "' and vertex with data '" + input + "'.";
    		}
    		
    	}else if(isString(input) && getSelectedTabName().equals("Undirected Weighted Graph")){

    		if(!dataModel.getUndirectedWeightedString().containsVertex(input)) {
    			errorMessage+="Vertex with data '" + input + "' does not exist in this graph.";
    		}else if(dataModel.getUndirectedWeightedString().isAdjacent(startVertex, input)) {
    			errorMessage+="There already exists an edge between vertex with data '" + startVertex + "' and vertex with data '" + input + "'.";
    		}
    		
    	}
    	else if(isInteger(input) && getSelectedTabName().equals("Directed Non-Weighted Graph") ){
    		System.out.println("Start Vertex: " + startVertex + "Ebd Vertex: " + input);
    		if(!dataModel.getDirectedNonWeightedInt().containsVertex(Integer.parseInt(input))) {
    			errorMessage+="Vertex with data '" + input + "' does not exist in this graph.";
    		}else if(dataModel.getDirectedNonWeightedInt().isAdjacent(Integer.parseInt(startVertex), Integer.parseInt(input))) {
    			errorMessage+="There already exists an edge between vertex with data '" + startVertex + "' and vertex with data '" + input + "'.";
    		}
    		
    	}else if(isDouble(input) && getSelectedTabName().equals("Directed Non-Weighted Graph")){

    		if(!dataModel.getDirectedNonWeightedDouble().containsVertex(Double.parseDouble(input))) {
    			errorMessage+="Vertex with data '" + input + "' does not exist in this graph.";
    		}else if(dataModel.getDirectedNonWeightedDouble().isAdjacent(Double.parseDouble(startVertex), Double.parseDouble(input))) {
    			errorMessage+="There already exists an edge between vertex with data '" + startVertex + "' and vertex with data '" + input + "'.";
    		}
    		
    	}else if(isString(input) && getSelectedTabName().equals("Directed Non-Weighted Graph")){

    		if(!dataModel.getDirectedNonWeightedString().containsVertex(input)) {
    			errorMessage+="Vertex with data '" + input + "' does not exist in this graph.";
    		}else if(dataModel.getDirectedNonWeightedString().isAdjacent(startVertex, input)) {
    			errorMessage+="There already exists an edge between vertex with data '" + startVertex + "' and vertex with data '" + input + "'.";
    		}
    		
    	}
    	else if(isInteger(input) && getSelectedTabName().equals("Directed Weighted Graph") ){

    		if(!dataModel.getDirectedWeightedInt().containsVertex(Integer.parseInt(input))) {
    			errorMessage+="Vertex with data '" + input + "' does not exist in this graph.";
    		}else if(dataModel.getDirectedWeightedInt().isAdjacent(Integer.parseInt(startVertex), Integer.parseInt(input))) {
    			errorMessage+="There already exists an edge between vertex with data '" + startVertex + "' and vertex with data '" + input + "'.";
    		}
    		
    	}else if(isDouble(input) && getSelectedTabName().equals("Directed Weighted Graph") ){

    		if(!dataModel.getDirectedWeightedDouble().containsVertex(Double.parseDouble(input))) {
    			errorMessage+="Vertex with data '" + input + "' does not exist in this graph.";
    		}else if(dataModel.getDirectedWeightedDouble().isAdjacent(Double.parseDouble(startVertex), Double.parseDouble(input))) {
    			errorMessage+="There already exists an edge between vertex with data '" + startVertex + "' and vertex with data '" + input + "'.";
    		}
    		
    	}else if(isString(input) && getSelectedTabName().equals("Directed Weighted Graph") ){

    		if(!dataModel.getDirectedWeightedString().containsVertex(input)) {
    			errorMessage+="Vertex with data '" + input + "' does not exist in this graph.";
    		}else if(dataModel.getDirectedWeightedString().isAdjacent(startVertex, input)) {
    			errorMessage+="There already exists an edge between vertex with data '" + startVertex + "' and vertex with data '" + input + "'.";
    		}
    		
    	}
    	
    	 if (errorMessage.length() == 0) {
             return true;
         } else {
             // Show the error message.
             Alert alert = new Alert(AlertType.ERROR);
             alert.setTitle("Invalid Edge Fields.");
             alert.setHeaderText("You have provided incorrect data.");
             alert.setContentText(errorMessage);
             
             alert.showAndWait();
             
             return false;
         }
    	
    }

	

}
