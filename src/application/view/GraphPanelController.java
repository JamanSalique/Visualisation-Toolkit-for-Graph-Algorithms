package application.view;

import java.util.Optional;

import application.Main;
import application.model.DataModel;
import application.model.Vertex;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TabPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
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

	private double orgSceneX, orgSceneY;
    private double orgTranslateX, orgTranslateY;
	
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
	private void handleAddVertex(MouseEvent event) {
		boolean okClicked = main.showAddVertexPopUp(this);
		
		if(okClicked) {
			
			String vertexText = "";
			
			if(getSelectedDataChoice().equals("Integer")) {
				
				vertexText = dataModel.getListOfIntVertices().get(dataModel.getListOfIntVertices().size() - 1).toString();
				
			}else if(getSelectedDataChoice().equals("Double")){
				
				vertexText = dataModel.getListOfDoubleVertices().get(dataModel.getListOfDoubleVertices().size() - 1).toString();
				
			}else {
				
				vertexText = dataModel.getListOfStringVertices().get(dataModel.getListOfStringVertices().size() - 1).toString();
				
			}
			
			EventHandler<MouseEvent> circleOnMousePressedEventHandler = 
			        new EventHandler<MouseEvent>() {
			 
			        @Override
			        public void handle(MouseEvent t) {
			        	
			        	if (t.getClickCount() == 2) {
			        		
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
			                	
			                	 
			                 }
			        	 }
			        	
			            orgSceneX = t.getSceneX();
			            orgSceneY = t.getSceneY();
			            orgTranslateX = ((StackPane)(t.getSource())).getTranslateX();
			            orgTranslateY = ((StackPane)(t.getSource())).getTranslateY();
			        }
			    };
			     
			    EventHandler<MouseEvent> circleOnMouseDraggedEventHandler = 
			        new EventHandler<MouseEvent>() {
			 
			        @Override
			        public void handle(MouseEvent t) {
			        	Bounds boundsInScene = centerPane.localToScene(centerPane.getBoundsInLocal());
//			        	double minPaneX = boundsInScene.getMinX();
//			        	double minPaneY = boundsInScene.getMinY();
//			        	double maxPaneX = boundsInScene.getMaxX();
//			        	double maxPaneY = boundsInScene.getMaxY();
			        	
//			        	double rootAnchorPane.p
//			        	double X=Main.window.getX()+menu.getLayoutX();
//			        	double Y=Main.window.getY()+menu.getLayoutY();
			        	
			            double offsetX = t.getSceneX() - orgSceneX;
			            double offsetY = t.getSceneY() - orgSceneY;
			            double newTranslateX = orgTranslateX + offsetX;
			            double newTranslateY = orgTranslateY + offsetY;
			            
//			            if(newTranslateX < minPaneX) {
//			            	newTranslateX = minPaneX;
//			            }
//			            
//			            if(newTranslateX >maxPaneX) {
//			            	newTranslateX = maxPaneX;
//			            }
//			            
//			            if(newTranslateY < minPaneY) {
//			            	newTranslateY = minPaneY;
//			            }
//			            
//			            if(newTranslateY >maxPaneY) {
//			            	newTranslateY = maxPaneY;
//			            }
			            	
			            ((StackPane)(t.getSource())).setTranslateX(newTranslateX);
			            ((StackPane)(t.getSource())).setTranslateY(newTranslateY);
			        }
			    };
			
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
   			stack.setOnMousePressed(circleOnMousePressedEventHandler);
   			stack.setOnMouseDragged(circleOnMouseDraggedEventHandler);
   			
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
	    		
	    		dataModel.getVertexDataInt().remove(vertexToRemove);
	    		
	    		dataModel.getListOfIntVertices().remove(vertexToRemove);
	    		
	    		dataModel.getUndirectedNonWeightedInt().removeVertex(vertexToRemove.getElement());
	    		
	    		
	    	}else if(type.equals("Double")) {
	    		
	    		Vertex<Double> vertexToRemove = dataModel.getUndirectedNonWeightedDouble().returnVertex(Double.parseDouble(dataAsString));
	    		
	    		dataModel.getVertexDataDouble().remove(vertexToRemove);
	    		
	    		dataModel.getListOfDoubleVertices().remove(vertexToRemove);
	    		
	    		dataModel.getUndirectedNonWeightedDouble().removeVertex(vertexToRemove.getElement());
    		
	    	}else if(type.equals("String")) {
	    		
	    		Vertex<String> vertexToRemove = dataModel.getUndirectedNonWeightedString().returnVertex(dataAsString);
	    		
	    		dataModel.getVertexDataString().remove(vertexToRemove);
	    		
	    		dataModel.getListOfStringVertices().remove(vertexToRemove);
	    		
	    		dataModel.getUndirectedNonWeightedString().removeVertex(vertexToRemove.getElement());
    		
	    		
	    	}
	    }
	 
	 private void removeVertexFromUndirectedWeightedGraph(String type, String dataAsString) {
	    	
	    	if(type.equals("Integer")) {
	    		
	    		Vertex<Integer> vertexToRemove = dataModel.getUndirectedWeightedInt().returnVertex(Integer.parseInt(dataAsString));
	    		
	    		dataModel.getVertexDataInt().remove(vertexToRemove);
	    		
	    		dataModel.getListOfIntVertices().remove(vertexToRemove);
	    		
	    		dataModel.getUndirectedWeightedInt().removeVertex(vertexToRemove.getElement());
	    		
	    		
	    	}else if(type.equals("Double")) {
	    		
	    		Vertex<Double> vertexToRemove = dataModel.getUndirectedWeightedDouble().returnVertex(Double.parseDouble(dataAsString));
	    		
	    		dataModel.getVertexDataDouble().remove(vertexToRemove);
	    		
	    		dataModel.getListOfDoubleVertices().remove(vertexToRemove);
	    		
	    		dataModel.getUndirectedWeightedDouble().removeVertex(vertexToRemove.getElement());
 		
	    	}else if(type.equals("String")) {
	    		
	    		Vertex<String> vertexToRemove = dataModel.getUndirectedNonWeightedString().returnVertex(dataAsString);
	    		
	    		dataModel.getVertexDataString().remove(vertexToRemove);
	    		
	    		dataModel.getListOfStringVertices().remove(vertexToRemove);
	    		
	    		dataModel.getUndirectedWeightedString().removeVertex(vertexToRemove.getElement());
 		
	    		
	    	}
	    }
	 
	 	private void removeVertexFromDirectedNonWeightedGraph(String type, String dataAsString) {
	    	
	    	if(type.equals("Integer")) {
	    		
	    		Vertex<Integer> vertexToRemove = dataModel.getDirectedNonWeightedInt().returnVertex(Integer.parseInt(dataAsString));
	    		
	    		dataModel.getVertexDataInt().remove(vertexToRemove);
	    		
	    		dataModel.getListOfIntVertices().remove(vertexToRemove);
	    		
	    		dataModel.getDirectedNonWeightedInt().removeVertex(vertexToRemove.getElement());
	    		
	    		
	    	}else if(type.equals("Double")) {
	    		
	    		Vertex<Double> vertexToRemove = dataModel.getDirectedNonWeightedDouble().returnVertex(Double.parseDouble(dataAsString));
	    		
	    		dataModel.getVertexDataDouble().remove(vertexToRemove);
	    		
	    		dataModel.getListOfDoubleVertices().remove(vertexToRemove);
	    		
	    		dataModel.getDirectedNonWeightedDouble().removeVertex(vertexToRemove.getElement());
 		
	    	}else if(type.equals("String")) {
	    		
	    		Vertex<String> vertexToRemove = dataModel.getDirectedNonWeightedString().returnVertex(dataAsString);
	    		
	    		dataModel.getVertexDataString().remove(vertexToRemove);
	    		
	    		dataModel.getListOfStringVertices().remove(vertexToRemove);
	    		
	    		dataModel.getDirectedNonWeightedString().removeVertex(vertexToRemove.getElement());
 		
	    		
	    	}
	    }
	 	
	 	private void removeVertexFromDirectedWeightedGraph(String type, String dataAsString) {
	    	
	    	if(type.equals("Integer")) {
	    		
	    		Vertex<Integer> vertexToRemove = dataModel.getDirectedWeightedInt().returnVertex(Integer.parseInt(dataAsString));
	    		
	    		dataModel.getVertexDataInt().remove(vertexToRemove);
	    		
	    		dataModel.getListOfIntVertices().remove(vertexToRemove);
	    		
	    		dataModel.getDirectedWeightedInt().removeVertex(vertexToRemove.getElement());
	    		
	    		
	    	}else if(type.equals("Double")) {
	    		
	    		Vertex<Double> vertexToRemove = dataModel.getDirectedWeightedDouble().returnVertex(Double.parseDouble(dataAsString));
	    		
	    		dataModel.getVertexDataDouble().remove(vertexToRemove);
	    		
	    		dataModel.getListOfDoubleVertices().remove(vertexToRemove);
	    		
	    		dataModel.getDirectedWeightedDouble().removeVertex(vertexToRemove.getElement());
 		
	    	}else if(type.equals("String")) {
	    		
	    		Vertex<String> vertexToRemove = dataModel.getDirectedWeightedString().returnVertex(dataAsString);
	    		
	    		dataModel.getVertexDataString().remove(vertexToRemove);
	    		
	    		dataModel.getListOfStringVertices().remove(vertexToRemove);
	    		
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

	

}
