package application.view;

import java.util.ArrayList;
import java.util.Optional;

import application.Main;
import application.model.DataModel;
import application.model.Vertex;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

public class GraphPanelController {

	
	private Main main;
	
	private DataModel dataModel;
	
	@FXML
	private AnchorPane rootAnchorPane;
	
	@FXML
	private Pane centerPane;
	
	@FXML
	private ChoiceBox<String> choiceBox;
	
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
    private String preSelectionChoice;
    
    private MenuItem hoverMenuItemAddEdge;

	
	@FXML
	private void initialize() {
		
		choiceBox.getItems().addAll("Integer","Double","String");
		choiceBox.getSelectionModel().selectFirst();
		choiceBox.setOnMouseClicked(this::selectionChoice);
		choiceBox.setOnAction(this::handleChoiceBox);
		
		hoverMenu = new ContextMenu();
		hoverMenuItemAddEdge = new MenuItem("Add Edge?");
        
        hoverMenu.getItems().addAll(hoverMenuItemAddEdge);
        

	}
	
	private void addEdgeHandler(String vertexDataAsStringFrom, String vertexDataAsStringTo) {
		
		if(getSelectedTabName().equals("Undirected Non-Weighted Graph") && isInteger(vertexDataAsStringFrom) && isInteger(vertexDataAsStringTo)) {
    		
			dataModel.getUndirectedNonWeightedInt().addEdge(Integer.parseInt(vertexDataAsStringFrom),Integer.parseInt(vertexDataAsStringTo));
     		
     	}else if(getSelectedTabName().equals("Undirected Non-Weighted Graph") && isDouble(vertexDataAsStringFrom) && isDouble(vertexDataAsStringTo)) {
     		
     		dataModel.getUndirectedNonWeightedDouble().addEdge(Double.parseDouble(vertexDataAsStringFrom),Double.parseDouble(vertexDataAsStringTo));
     		
     	}else if(getSelectedTabName().equals("Undirected Non-Weighted Graph") && isString(vertexDataAsStringFrom) && isString(vertexDataAsStringTo)) {
     		
     		dataModel.getUndirectedNonWeightedString().addEdge(vertexDataAsStringFrom,vertexDataAsStringTo);
     		
     	}
     	
     	else if(getSelectedTabName().equals("Undirected Weighted Graph") && isInteger(vertexDataAsStringFrom) && isInteger(vertexDataAsStringTo)) {

     		
     	}else if(getSelectedTabName().equals("Undirected Weighted Graph")  && isDouble(vertexDataAsStringFrom) && isDouble(vertexDataAsStringTo)) {

     		
     	}else if(getSelectedTabName().equals("Undirected Weighted Graph") && isString(vertexDataAsStringFrom) && isString(vertexDataAsStringTo)) {

     		
     	}
     	
     	else if(getSelectedTabName().equals("Directed Non-Weighted Graph") && isInteger(vertexDataAsStringFrom) && isInteger(vertexDataAsStringTo)) {

     		
     	}else if(getSelectedTabName().equals("Directed Non-Weighted Graph") && isDouble(vertexDataAsStringFrom) && isDouble(vertexDataAsStringTo)) {

     		
     	}else if(getSelectedTabName().equals("Directed Non-Weighted Graph") && isString(vertexDataAsStringFrom) && isString(vertexDataAsStringTo)) {

     		
     	}
     	
     	else if(getSelectedTabName().equals("Directed Weighted Graph") && isInteger(vertexDataAsStringFrom) && isInteger(vertexDataAsStringTo)) {

     		
     	}else if(getSelectedTabName().equals("Directed Weighted Graph") && isDouble(vertexDataAsStringFrom) && isDouble(vertexDataAsStringTo)) {

     		
     	}else if(getSelectedTabName().equals("Directed Weighted Graph") && isString(vertexDataAsStringFrom) && isString(vertexDataAsStringTo)) {

     		
     	}
		
	}
	
	@FXML
	private EventHandler<MouseEvent> mouseClickedOnEdgeEvent(){
		
		EventHandler<MouseEvent> lineOnMouseClickedEventHandler = 
		        new EventHandler<MouseEvent>() {
		 
		        @Override
		        public void handle(MouseEvent t) {
		        	if (t.getClickCount() == 2 && t.getButton() != MouseButton.SECONDARY && t.getSource() instanceof Line) {
		        		System.out.println("febfdebj");
		        		 Alert alert = new Alert(AlertType.ERROR);
		        		 
		        		 ButtonType cancelButton = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
		        		 ButtonType okButton = new ButtonType("Ok", ButtonData.OK_DONE);
		        		 
		                 alert.setTitle("Deleting Edge.");
		                 alert.setHeaderText("Are you sure you want to delete this edge?");
		                 alert.setContentText("Once deleting the edge you cannot retrieve it back.");
		                 alert.getButtonTypes().setAll(okButton,cancelButton);
		                 
		                 Optional<ButtonType> result = alert.showAndWait();
		                 
		                 if(result.get() == okButton) {
		                	 
		                	 Line lineToRemove = (Line) t.getSource();
		                	 
		                	 StackPane vertexStart = null;
		                	 StackPane vertexEnd = null;
		                	 
		                	 for(Node child:centerPane.getChildren()) {
		                		 if(child instanceof StackPane) {
		                			 if(child.getLayoutX() + ((Region) child).getWidth() / 2 == lineToRemove.getStartX() && 
		                					 child.getLayoutY() + ((Region) child).getHeight() / 2  == lineToRemove.getStartY()) {
		                				 
		                				 vertexStart = (StackPane) child;
		                			 }
		                		 }
		                	 }
		                	 
		                	 for(Node child:centerPane.getChildren()) {

		                		 if(child instanceof StackPane) {
		                			 if(child.getLayoutX() + ((Region) child).getWidth() / 2 == lineToRemove.getEndX() && 
		                					 child.getLayoutY() + ((Region) child).getWidth() / 2 == lineToRemove.getEndY()) {
		                				 vertexEnd = (StackPane) child;
		                			 }
		                		 }
		                	 }
		                	 
		                	 ObservableList<Node> childsOfVertexStart = vertexStart.getChildren();
		                	 Text dataOfVertexStart = (Text) childsOfVertexStart.get(childsOfVertexStart.size()-1);
		                	 String dataOfVertexStartAsString = dataOfVertexStart.getText();
		                	 
		                	 ObservableList<Node> childsOfVertexEnd = vertexEnd.getChildren();
		                	 Text dataOfVertexEnd = (Text) childsOfVertexEnd.get(childsOfVertexEnd.size()-1);
		                	 String dataOfVertexEndAsString = dataOfVertexEnd.getText();
		                	 
		                	if(getSelectedTabName().equals("Undirected Non-Weighted Graph") && isInteger(dataOfVertexStartAsString) && isInteger(dataOfVertexEndAsString)) {
		                		
		                 		centerPane.getChildren().remove(t.getSource());
		                 		dataModel.getUndirectedNonWeightedInt().removeEdge(Integer.parseInt(dataOfVertexStartAsString), Integer.parseInt(dataOfVertexEndAsString));
		                 		
		                 	}else if(getSelectedTabName().equals("Undirected Non-Weighted Graph") && isDouble(dataOfVertexStartAsString) && isDouble(dataOfVertexEndAsString)) {
		                 		
		                 		centerPane.getChildren().remove(t.getSource());
		                 		dataModel.getUndirectedNonWeightedDouble().removeEdge(Double.parseDouble(dataOfVertexStartAsString), Double.parseDouble(dataOfVertexEndAsString));
		                 		
		                 	}else if(getSelectedTabName().equals("Undirected Non-Weighted Graph") && isString(dataOfVertexStartAsString) && isString(dataOfVertexEndAsString)) {
		                 		
		                 		centerPane.getChildren().remove(t.getSource());
		                 		dataModel.getUndirectedNonWeightedString().removeEdge(dataOfVertexStartAsString, dataOfVertexEndAsString);
		                 		
		                 	}
//		                 	
//		                 	else if(getSelectedTabName().equals("Undirected Weighted Graph") && isInteger(dataAsString)) {
//		                 		
//		                 		centerPane.getChildren().remove(t.getSource());
//		                 		removeVertexFromUndirectedWeightedGraph("Integer",dataAsString);
//		                 		
//		                 	}else if(getSelectedTabName().equals("Undirected Weighted Graph") && isDouble(dataAsString)) {
//		                 		
//		                 		centerPane.getChildren().remove(t.getSource());
//		                 		removeVertexFromUndirectedWeightedGraph("Double",dataAsString);
//		                 		
//		                 	}else if(getSelectedTabName().equals("Undirected Weighted Graph") && isString(dataAsString)) {
//		                 		
//		                 		centerPane.getChildren().remove(t.getSource());
//		                 		removeVertexFromUndirectedWeightedGraph("String",dataAsString);
//		                 		
//		                 	}
//		                 	
//		                 	else if(getSelectedTabName().equals("Directed Non-Weighted Graph") && isInteger(dataAsString)) {
//		                 		
//		                 		centerPane.getChildren().remove(t.getSource());
//		                 		removeVertexFromDirectedNonWeightedGraph("Integer",dataAsString);
//		                 		
//		                 	}else if(getSelectedTabName().equals("Directed Non-Weighted Graph") && isDouble(dataAsString)) {
//		                 		
//		                 		centerPane.getChildren().remove(t.getSource());
//		                 		removeVertexFromDirectedNonWeightedGraph("Double",dataAsString);
//		                 		
//		                 	}else if(getSelectedTabName().equals("Directed Non-Weighted Graph") && isString(dataAsString)) {
//		                 		
//		                 		centerPane.getChildren().remove(t.getSource());
//		                 		removeVertexFromDirectedNonWeightedGraph("String",dataAsString);
//		                 		
//		                 	}
//		                 	
//		                 	else if(getSelectedTabName().equals("Directed Weighted Graph") && isInteger(dataAsString)) {
//		                 		
//		                 		centerPane.getChildren().remove(t.getSource());
//		                 		removeVertexFromDirectedWeightedGraph("Integer",dataAsString);
//		                 		
//		                 	}else if(getSelectedTabName().equals("Directed Weighted Graph") && isDouble(dataAsString)) {
//		                 		
//		                 		centerPane.getChildren().remove(t.getSource());
//		                 		removeVertexFromDirectedWeightedGraph("Double",dataAsString);
//		                 		
//		                 	}else if(getSelectedTabName().equals("Directed Weighted Graph") && isString(dataAsString)) {
//		                 		
//		                 		centerPane.getChildren().remove(t.getSource());
//		                 		removeVertexFromDirectedWeightedGraph("String",dataAsString);
//		                 		
//		                 	}
		                	
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
					        	
					        	TextInputDialog dialog = new TextInputDialog();
					        	dialog.setTitle("Add Edge");
					        	dialog.setHeaderText("Add Edge.");
					        	dialog.setContentText("Please enter the edge you would like to connect to:");
					        	 ButtonType cancelButton = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
				        		 ButtonType okButton = new ButtonType("Ok", ButtonData.OK_DONE);
				        		 dialog.getDialogPane().getButtonTypes().setAll(okButton,cancelButton);
					        	// Traditional way to get the response value.
					        	Optional<String> result = dialog.showAndWait();
					        	
					        	if(isInputValid(result.get())) {
					        		if (result.isPresent()){
						        		StackPane vertexTo = null;
						        		for(Node child : centerPane.getChildren()) {
					                		
					                		if(child instanceof StackPane) {
					                			ObservableList<Node> childsOfStack = ((StackPane)child).getChildren();
					                			Text dataOfStack = (Text) childsOfStack.get(childs.size()-1);
					                			String toCompare = dataOfStack.getText();
					                			if(toCompare.equals(result.get())) {
					                				vertexTo = (StackPane) child;
					                			}
					                		}
					                	}

						        		addEdgeHandler(dataAsString,result.get());
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
						        	

						        		centerPane.getChildren().add(0,line);
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
		                	 ArrayList<Line>  edgesToRemove = new ArrayList<Line>();
		                	 
		                	 for(Node child:centerPane.getChildren()) {
		                		 
		                		 if(child instanceof Line) {
		                			 
		                			 if((((Line) child).getStartX() == stackPaneX && ((Line) child).getStartY() == stackPaneY) || 
		                					 (((Line) child).getEndX() == stackPaneX && ((Line) child).getEndY() == stackPaneY)) {
		                				 edgeToRemove = (Line) child;
		                				 edgesToRemove.add(edgeToRemove);
		                			 }
		                		 }
		                		 
		                	 }
		                	 
		                	if(getSelectedTabName().equals("Undirected Non-Weighted Graph") && isInteger(dataAsString)) {
		                		
		                 		centerPane.getChildren().remove(t.getSource());
		                 		removeVertexFromUndirectedNonWeightedGraph("Integer",dataAsString);
		                 		
		                 	}else if(getSelectedTabName().equals("Undirected Non-Weighted Graph") && isDouble(dataAsString)) {
		                 		
		                 		centerPane.getChildren().remove(t.getSource());
		                 		removeVertexFromUndirectedNonWeightedGraph("Double",dataAsString);
		                 		
		                 	}else if(getSelectedTabName().equals("Undirected Non-Weighted Graph") && isString(dataAsString)) {
		                 		
		                 		centerPane.getChildren().remove(t.getSource());
		                 		removeVertexFromUndirectedNonWeightedGraph("String",dataAsString);
		                 		
		                 	}
		                 	
		                 	else if(getSelectedTabName().equals("Undirected Weighted Graph") && isInteger(dataAsString)) {
		                 		
		                 		centerPane.getChildren().remove(t.getSource());
		                 		removeVertexFromUndirectedWeightedGraph("Integer",dataAsString);
		                 		
		                 	}else if(getSelectedTabName().equals("Undirected Weighted Graph") && isDouble(dataAsString)) {
		                 		
		                 		centerPane.getChildren().remove(t.getSource());
		                 		removeVertexFromUndirectedWeightedGraph("Double",dataAsString);
		                 		
		                 	}else if(getSelectedTabName().equals("Undirected Weighted Graph") && isString(dataAsString)) {
		                 		
		                 		centerPane.getChildren().remove(t.getSource());
		                 		removeVertexFromUndirectedWeightedGraph("String",dataAsString);
		                 		
		                 	}
		                 	
		                 	else if(getSelectedTabName().equals("Directed Non-Weighted Graph") && isInteger(dataAsString)) {
		                 		
		                 		centerPane.getChildren().remove(t.getSource());
		                 		removeVertexFromDirectedNonWeightedGraph("Integer",dataAsString);
		                 		
		                 	}else if(getSelectedTabName().equals("Directed Non-Weighted Graph") && isDouble(dataAsString)) {
		                 		
		                 		centerPane.getChildren().remove(t.getSource());
		                 		removeVertexFromDirectedNonWeightedGraph("Double",dataAsString);
		                 		
		                 	}else if(getSelectedTabName().equals("Directed Non-Weighted Graph") && isString(dataAsString)) {
		                 		
		                 		centerPane.getChildren().remove(t.getSource());
		                 		removeVertexFromDirectedNonWeightedGraph("String",dataAsString);
		                 		
		                 	}
		                 	
		                 	else if(getSelectedTabName().equals("Directed Weighted Graph") && isInteger(dataAsString)) {
		                 		
		                 		centerPane.getChildren().remove(t.getSource());
		                 		removeVertexFromDirectedWeightedGraph("Integer",dataAsString);
		                 		
		                 	}else if(getSelectedTabName().equals("Directed Weighted Graph") && isDouble(dataAsString)) {
		                 		
		                 		centerPane.getChildren().remove(t.getSource());
		                 		removeVertexFromDirectedWeightedGraph("Double",dataAsString);
		                 		
		                 	}else if(getSelectedTabName().equals("Directed Weighted Graph") && isString(dataAsString)) {
		                 		
		                 		centerPane.getChildren().remove(t.getSource());
		                 		removeVertexFromDirectedWeightedGraph("String",dataAsString);
		                 		
		                 	}
		                	
		                	if(edgesToRemove.size() != 0) {
	                 			
	                 			for(Line edge: edgesToRemove) {
	                 				centerPane.getChildren().remove(edge);
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

		        	
		            double offsetX = t.getSceneX() - orgSceneX;
		            double offsetY = t.getSceneY() - orgSceneY;
		            currentStackPane.setTranslateX(offsetX);
		            currentStackPane.setTranslateY(offsetY);
		        
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
//		        	((StackPane)(t.getSource())).setLayoutX(newTranslateX - offsetX  );
//		            ((StackPane)(t.getSource())).setLayoutY(newTranslateY - offsetY  );
		        	
		        	currentStackPane.setLayoutX(layoutX + ((StackPane)(t.getSource())).getTranslateX());
		        	currentStackPane.setLayoutY(layoutY + ((StackPane)(t.getSource())).getTranslateY());
		        	newTranslateX = ((StackPane)(t.getSource())).getTranslateX();
		        	newTranslateY = ((StackPane)(t.getSource())).getTranslateY();
		        	currentStackPane.setTranslateX(0);
		        	currentStackPane.setTranslateY(0);
		        }
		    };
		    return circleOnMouseReleaseEventHandler;
		    
	}
	
	 
	
	@FXML
	private void selectionChoice(MouseEvent e) {
		preSelectionChoice = getSelectedDataChoice();
	}
	
	@FXML
	private void handleChoiceBox(ActionEvent e) {
		
		Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Changing data type warning");
        alert.setHeaderText("Are you sure you want to change the data type?");
        alert.setContentText("If you click OK thew current graph you created will be cleared.");
        ButtonType cancelButton = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
		ButtonType okButton = new ButtonType("Ok", ButtonData.OK_DONE);
		alert.getButtonTypes().setAll(okButton,cancelButton);
        Optional<ButtonType> result = alert.showAndWait();
         
 		if(getSelectedTabName().equals("Undirected Non-Weighted Graph")) {
 			
 			if(result.get() == okButton) {
 				
 				centerPane.getChildren().clear();
 				
 	        	 if(preSelectionChoice.equals("Integer")) {
 	        		 
 	        		 dataModel.getListOfUndirectedNonWeightedIntVertices().clear();
 	        		 dataModel.getVertexDataUndirectedNonWeightedInt().clear();
 	        		 dataModel.getUndirectedNonWeightedInt().clearGraph();
 	        		 
 	        	 }else if(preSelectionChoice.equals("Double")) {
 	        		 
 	        		dataModel.getListOfUndirectedNonWeightedDoubleVertices().clear();
	        		dataModel.getVertexDataUndirectedNonWeightedDouble().clear();
	        		dataModel.getUndirectedNonWeightedDouble().clearGraph();
 	        		 
 	        	 }else if(preSelectionChoice.equals("String")) {
 	        		 
 	        		dataModel.getListOfUndirectedNonWeightedStringVertices().clear();
	        		dataModel.getVertexDataUndirectedNonWeightedString().clear();
	        		dataModel.getUndirectedNonWeightedString().clearGraph();
 	        		 
 	        	 }
 
 	         }else if(result.get() == cancelButton){
 	        	 
 	        	 choiceBox.setValue(preSelectionChoice);

 	         }
 			
 		}
 		
 		else if(getSelectedTabName().equals("Undirected Weighted Graph")) {
 			
 			if(result.get() == okButton) {
 				
 				centerPane.getChildren().clear();
 				
 	        	 if(preSelectionChoice.equals("Integer")) {
 	        		 
 	        		 dataModel.getListOfUndirectedWeightedIntVertices().clear();
 	        		 dataModel.getVertexDataUndirectedWeightedInt().clear();
 	        		 dataModel.getUndirectedWeightedInt().clearGraph();
 	        		 
 	        	 }else if(preSelectionChoice.equals("Double")) {
 	        		 
 	        		dataModel.getListOfUndirectedWeightedDoubleVertices().clear();
	        		dataModel.getVertexDataUndirectedWeightedDouble().clear();
	        		dataModel.getUndirectedWeightedDouble().clearGraph();
 	        		 
 	        	 }else if(preSelectionChoice.equals("String")) {
 	        		 
 	        		dataModel.getListOfUndirectedWeightedStringVertices().clear();
	        		dataModel.getVertexDataUndirectedWeightedString().clear();
	        		dataModel.getUndirectedWeightedString().clearGraph();
 	        		 
 	        	 }

 	         }else if(result.get() == cancelButton){
 	        	 choiceBox.setValue(preSelectionChoice);

 	         }
 	 			
 	 	}
 		
 		else if(getSelectedTabName().equals("Directed Non-Weighted Graph")) {
 			
 			if(result.get() == okButton) {
 				
 				centerPane.getChildren().clear();
 				
 	        	 if(preSelectionChoice.equals("Integer")) {
 	        		 
 	        		 dataModel.getListOfDirectedNonWeightedIntVertices().clear();
 	        		 dataModel.getVertexDataDirectedNonWeightedInt().clear();
 	        		 dataModel.getDirectedNonWeightedInt().clearGraph();
 	        		 
 	        	 }else if(preSelectionChoice.equals("Double")) {
 	        		 
 	        		dataModel.getListOfDirectedNonWeightedDoubleVertices().clear();
	        		dataModel.getVertexDataDirectedNonWeightedDouble().clear();
	        		dataModel.getDirectedNonWeightedDouble().clearGraph();
 	        		 
 	        	 }else if(preSelectionChoice.equals("String")) {
 	        		 
 	        		dataModel.getListOfDirectedNonWeightedStringVertices().clear();
	        		dataModel.getVertexDataDirectedNonWeightedString().clear();
	        		dataModel.getDirectedNonWeightedString().clearGraph();
 	        		 
 	        	 }

 	         }else if(result.get() == cancelButton){
 	        	 choiceBox.setValue(preSelectionChoice);

 	         }
 		}
 			
 		else if(getSelectedTabName().equals("Directed Weighted Graph")) {
	 			
	 			if(result.get() == okButton) {
	 				
	 				centerPane.getChildren().clear();
	 				
	 	        	 if(preSelectionChoice.equals("Integer")) {
	 	        		 
	 	        		 dataModel.getListOfDirectedWeightedIntVertices().clear();
	 	        		 dataModel.getVertexDataDirectedWeightedInt().clear();
	 	        		 dataModel.getDirectedWeightedInt().clearGraph();
	 	        		 
	 	        	 }else if(preSelectionChoice.equals("Double")) {
	 	        		 
	 	        		dataModel.getListOfDirectedWeightedDoubleVertices().clear();
		        		dataModel.getVertexDataDirectedWeightedDouble().clear();
		        		dataModel.getDirectedWeightedDouble().clearGraph();
	 	        		 
	 	        	 }else if(preSelectionChoice.equals("String")) {
	 	        		 
	 	        		dataModel.getListOfDirectedWeightedStringVertices().clear();
		        		dataModel.getVertexDataDirectedWeightedString().clear();
		        		dataModel.getDirectedWeightedString().clearGraph();
	 	        		 
	 	        	 }
	 	        	 
	 	         }else if(result.get() == cancelButton){
	 	        	 choiceBox.setValue(preSelectionChoice);

	 	         }
	 		}
	}
	
	@FXML
	private void handleAddVertex(MouseEvent event) {
		boolean okClicked = main.showAddVertexPopUp(this);
		
		if(okClicked) {
			
			String vertexText = "";
			
			if(getSelectedDataChoice().equals("Integer") && getSelectedTabName().equals("Undirected Non-Weighted Graph")) {
				
				vertexText = dataModel.getListOfUndirectedNonWeightedIntVertices().get(dataModel.getListOfUndirectedNonWeightedIntVertices().size() - 1).toString();
				
			}else if(getSelectedDataChoice().equals("Double") && getSelectedTabName().equals("Undirected Non-Weighted Graph")){
				
				vertexText = dataModel.getListOfUndirectedNonWeightedDoubleVertices().get(dataModel.getListOfUndirectedNonWeightedDoubleVertices().size() - 1).toString();
				
			}else if(getSelectedDataChoice().equals("String") && getSelectedTabName().equals("Undirected Non-Weighted Graph")){
				
				vertexText = dataModel.getListOfUndirectedNonWeightedStringVertices().get(dataModel.getListOfUndirectedNonWeightedStringVertices().size() - 1).toString();
				
			}
			
			else if(getSelectedDataChoice().equals("Integer") && getSelectedTabName().equals("Undirected Weighted Graph")) {
				
				vertexText = dataModel.getListOfUndirectedWeightedIntVertices().get(dataModel.getListOfUndirectedWeightedIntVertices().size() - 1).toString();
				
			}else if(getSelectedDataChoice().equals("Double") && getSelectedTabName().equals("Undirected Weighted Graph")){
				
				vertexText = dataModel.getListOfUndirectedWeightedDoubleVertices().get(dataModel.getListOfUndirectedWeightedDoubleVertices().size() - 1).toString();
				
			}else if(getSelectedDataChoice().equals("String") && getSelectedTabName().equals("Undirected Weighted Graph")){
				
				vertexText = dataModel.getListOfUndirectedWeightedStringVertices().get(dataModel.getListOfUndirectedWeightedStringVertices().size() - 1).toString();
				
			}
			
			else if(getSelectedDataChoice().equals("Integer") && getSelectedTabName().equals("Directed Non-Weighted Graph")) {
				
				vertexText = dataModel.getListOfDirectedNonWeightedIntVertices().get(dataModel.getListOfDirectedNonWeightedIntVertices().size() - 1).toString();
				
			}else if(getSelectedDataChoice().equals("Double") && getSelectedTabName().equals("Directed Non-Weighted Graph")){
				
				vertexText = dataModel.getListOfDirectedNonWeightedDoubleVertices().get(dataModel.getListOfDirectedNonWeightedDoubleVertices().size() - 1).toString();
				
			}else if(getSelectedDataChoice().equals("String") && getSelectedTabName().equals("Directed Non-Weighted Graph")){
				
				vertexText = dataModel.getListOfDirectedNonWeightedStringVertices().get(dataModel.getListOfDirectedNonWeightedStringVertices().size() - 1).toString();
				
			}
			
			else if(getSelectedDataChoice().equals("Integer") && getSelectedTabName().equals("Directed Weighted Graph")) {
				
				vertexText = dataModel.getListOfDirectedWeightedIntVertices().get(dataModel.getListOfDirectedWeightedIntVertices().size() - 1).toString();
				
			}else if(getSelectedDataChoice().equals("Double") && getSelectedTabName().equals("Directed Weighted Graph")){
				
				vertexText = dataModel.getListOfDirectedWeightedDoubleVertices().get(dataModel.getListOfDirectedWeightedDoubleVertices().size() - 1).toString();
				
			}else if(getSelectedDataChoice().equals("String") && getSelectedTabName().equals("Directed Weighted Graph")){
				
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
    
    private boolean isInputValid(String input) {
    	
    	String selectedDataChoice = getSelectedDataChoice();
    	
    	String errorMessage = "";
    	if (input == null || input.length() == 0) {
    		
    		errorMessage += "please enter some data";
    		
    	}
    	else if(isInteger(input) && !selectedDataChoice.equals("Integer")) {
    		
    		errorMessage+="Invalid data type you must enter data of type " + selectedDataChoice;
    		
    	}
    	else if(isDouble(input) && !selectedDataChoice.equals("Double")) {
    		
    		errorMessage+="Invalid data type you must enter data of type " + selectedDataChoice;
    		
    	}
    	else if(isString(input) && !selectedDataChoice.equals("String")) {
    		
    		errorMessage+="Invalid data type you must enter data of type " + selectedDataChoice;
    		
    	}else if(isInteger(input) && getSelectedTabName().equals("Undirected Non-Weighted Graph") 
    			&& !dataModel.getUndirectedNonWeightedInt().containsVertex(Integer.parseInt(input))){

    		errorMessage+="The vertex you inputted does not exist in this graph";
    		
    	}else if(isDouble(input) && getSelectedTabName().equals("Undirected Non-Weighted Graph") 
    			&& !dataModel.getUndirectedNonWeightedDouble().containsVertex(Double.parseDouble(input))){

    		errorMessage+="The vertex you inputted does not exist in this graph";
    		
    	}else if(isString(input) && getSelectedTabName().equals("Undirected Non-Weighted Graph") 
    			&& !dataModel.getUndirectedNonWeightedString().containsVertex(input)){

    		errorMessage+="The vertex you inputted does not exist in this graph";
    		
    	}
    	else if(isInteger(input) && getSelectedTabName().equals("Undirected Weighted Graph") 
    			&& !dataModel.getUndirectedWeightedInt().containsVertex(Integer.parseInt(input))){

    		errorMessage+="The vertex you inputted does not exist in this graph";
    		
    	}else if(isDouble(input) && getSelectedTabName().equals("Undirected Weighted Graph") 
    			&& !dataModel.getUndirectedWeightedDouble().containsVertex(Double.parseDouble(input))){

    		errorMessage+="The vertex you inputted does not exist in this graph";
    		
    	}else if(isString(input) && getSelectedTabName().equals("Undirected Weighted Graph") 
    			&& !dataModel.getUndirectedWeightedString().containsVertex(input)){

    		errorMessage+="The vertex you inputted does not exist in this graph";
    		
    	}
    	else if(isInteger(input) && getSelectedTabName().equals("Directed Non-Weighted Graph") 
    			&& !dataModel.getDirectedNonWeightedInt().containsVertex(Integer.parseInt(input))){

    		errorMessage+="The vertex you inputted does not exist in this graph";
    		
    	}else if(isDouble(input) && getSelectedTabName().equals("Directed Non-Weighted Graph") 
    			&& !dataModel.getDirectedNonWeightedDouble().containsVertex(Double.parseDouble(input))){

    		errorMessage+="The vertex you inputted does not exist in this graph";
    		
    	}else if(isString(input) && getSelectedTabName().equals("Directed Non-Weighted Graph") 
    			&& !dataModel.getDirectedNonWeightedString().containsVertex(input)){

    		errorMessage+="The vertex you inputted does not exist in this graph";
    		
    	}
    	else if(isInteger(input) && getSelectedTabName().equals("Directed Weighted Graph") 
    			&& !dataModel.getDirectedWeightedInt().containsVertex(Integer.parseInt(input))){

    		errorMessage+="The vertex you inputted does not exist in this graph";
    		
    	}else if(isDouble(input) && getSelectedTabName().equals("Directed Weighted Graph") 
    			&& !dataModel.getDirectedWeightedDouble().containsVertex(Double.parseDouble(input))){

    		errorMessage+="The vertex you inputted does not exist in this graph";
    		
    	}else if(isString(input) && getSelectedTabName().equals("Directed Weighted Graph") 
    			&& !dataModel.getDirectedWeightedString().containsVertex(input)){

    		errorMessage+="The vertex you inputted does not exist in this graph";
    		
    	}
    	
    	 if (errorMessage.length() == 0) {
             return true;
         } else {
             // Show the error message.
             Alert alert = new Alert(AlertType.ERROR);
             alert.setTitle("Invalid Fields");
             alert.setHeaderText("Please correct invalid fields");
             alert.setContentText(errorMessage);
             
             alert.showAndWait();
             
             return false;
         }
    	
    }

	

}
