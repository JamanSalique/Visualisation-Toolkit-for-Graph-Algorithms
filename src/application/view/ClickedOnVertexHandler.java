package application.view;

import java.util.ArrayList;
import java.util.Optional;

import application.Main;
import application.model.Vertex;
import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Pair;

/**
 * This class holds all the functionality for when the user clicks on a vertex on the gui.
 * @author jamansalique
 *
 */
public class ClickedOnVertexHandler implements EventHandler<MouseEvent>{

	private GraphPanelController gpc;
	private StackPane stack;
	private double directedEdgePlacement;
	private final int selfEdgeArrowheadPlacement = 20;
	
	/**
	 * Constructor...
	 * @param gpc
	 * @param stack the vertex that has been clicked on.
	 */
	public ClickedOnVertexHandler(GraphPanelController gpc,StackPane stack) {
		this.gpc = gpc;
		this.stack = stack;
		this.directedEdgePlacement = 13;
	}
	
	/**
	 * This is called when the user clicks on an vertex.
	 * The user can do two things by clicking a vertex.
	 * They can either double click the vertex to delete the vertex or they can right click the vertex to add a edge from the vertex clicked to another 
	 * vertex.
	 * 
	 * If the user right clicks then the user is prompted with a little hover menu where they have the option of adding an edge. If they click the item
	 * in the hover menu then user is prompted with a small form.
	 * For non weighted graphs the user is only asked to input the data that the destination vertex holds.
	 * For weighted graphs the user is asked to input the weight they would like the edge to have as well as the data of the vertex destination.
	 * Once the user inputs some data and is validated by the isInputValid() method in the GraphPanelController class an edge is made between the source
	 * vertex (the vertex the user right clicked on) and the destination vertex the user inputed. Some calculations are made to find the exact position
	 * of the destination vertex and a edge is then positioned between the 2 vertices. The model is also then updated accordingly.
	 * 
	 * If the user double clicks the vertex this means they want to delete the vertex. The user is shown a warning alert box asking if they are 
	 * sure they want to delete the vertex. If the user presses OK then then the method carries on to delete the vertex.
	 * If the vertex has edges connected to it then on deletion of the vertex the user wants to delete, this method finds all the edges connected 
	 * to the vertex and also deletes them too. The model is also then updated accordingly.
	 */
	 @Override
    public void handle(MouseEvent t) {
     	
		 //if user right clicks vertex...
     	if(t.getButton() == MouseButton.SECONDARY) {
     		
     		StackPane vertexClickedOn = (StackPane)(t.getSource());
     		ObservableList<Node> childs = ((StackPane)(t.getSource())).getChildren();
         	Text data = (Text) childs.get(childs.size()-1);
         	String dataAsString = data.getText();
				double x = t.getX();
				double y = t.getY();
				gpc.getHoverMenu().show(stack,t.getScreenX(), t.getScreenY());
				
				 gpc.getHoverMenuItemAddEdge().setOnAction(event -> {
			        	
					 if(gpc.getSelectedTabName().equals("Undirected Non-Weighted Graph") || gpc.getSelectedTabName().equals("Directed Non-Weighted Graph")) {
						 
						TextInputDialog dialogNonWeightedEdge = new TextInputDialog();
				        dialogNonWeightedEdge.setTitle("Add Edge");
				        dialogNonWeightedEdge.setHeaderText("Add Edge.");
				        dialogNonWeightedEdge.setContentText("Please enter the edge you would like to connect to:");
				        ButtonType cancelButton = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
			        	ButtonType okButton = new ButtonType("Ok", ButtonData.OK_DONE);
			        	dialogNonWeightedEdge.getDialogPane().getButtonTypes().setAll(okButton,cancelButton);

			        	dialogNonWeightedEdge.getDialogPane().getStylesheets().add("/application/global.css");
			        			
				        // Traditional way to get the response value.
				        Optional<String> result = dialogNonWeightedEdge.showAndWait();
				        	
				        	if(result.isPresent() && gpc.isInputValidAddingEdge(result.get(),dataAsString,"") 
				        			){
				        		if(result.isPresent()) {
				        			
				        			StackPane vertexTo = null;
				        			
				        			if(gpc.getSelectedTabName().equals("Undirected Non-Weighted Graph")) {
					        			for(Node child : gpc.getCenterPaneUndirectedNonWeightedGraph().getChildren()) {
					                		
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
//						        		line.setOnMousePressed(mouseClickedOnEdgeEvent());
						        		line.setOnMousePressed(gpc.getClickedOnEdgeHandler());

						        		line.setStartX(vertexClickedOn.getLayoutX() + (vertexClickedOn.getWidth() / 2));
						        		line.setStartY(vertexClickedOn.getLayoutY() + (vertexClickedOn.getHeight() / 2));
						        		line.setEndX(vertexTo.getLayoutX() + (vertexTo.getWidth() / 2));
						        		line.setEndY(vertexTo.getLayoutY() +  (vertexTo.getHeight() / 2));

						        		line.startXProperty().bind(vertexClickedOn.layoutXProperty().add(vertexClickedOn.translateXProperty()).add(vertexClickedOn.widthProperty().divide(2)));
						        		line.startYProperty().bind(vertexClickedOn.layoutYProperty().add(vertexClickedOn.translateYProperty()).add(vertexClickedOn.heightProperty().divide(2)));
						        		line.endXProperty().bind(vertexTo.layoutXProperty().add(vertexTo.translateXProperty()).add(vertexTo.widthProperty().divide(2)));
						        		line.endYProperty().bind(vertexTo.layoutYProperty().add(vertexTo.translateYProperty()).add(vertexTo.heightProperty().divide(2)));
					        			
						        		gpc.getCenterPaneUndirectedNonWeightedGraph().getChildren().add(0,line);
						        		
					        		}else if(gpc.getSelectedTabName().equals("Directed Non-Weighted Graph")) {
					        			
					        			for(Node child : gpc.getCenterPaneDirectedNonWeightedGraph().getChildren()) {
					                		
					                		if(child instanceof StackPane) {
					                			ObservableList<Node> childsOfStack = ((StackPane)child).getChildren();
					                			Text dataOfStack = (Text) childsOfStack.get(childs.size()-1);
					                			String toCompare = dataOfStack.getText();
					                			if(toCompare.equals(result.get())) {
					                				vertexTo = (StackPane) child;
					                			}
					                		}
					                	}
					        			
					        			
					        			
//					        			directedEdgePlacement = 13;
					        			
					        			double xMoveStart = directedEdgePlacement;
					        			double yMoveStart =directedEdgePlacement;
					        			double xMoveEnd = directedEdgePlacement;
					        			double yMoveEnd =directedEdgePlacement;
					        			
					        			if(gpc.getSelectedDataChoiceDirectedNonWeightedGraph().equals("Integer")) {
					        				
					        				ObservableList<Node> childsVertexTo = vertexTo.getChildren();
						                	Text dataVertexTo = (Text) childsVertexTo.get(childsVertexTo.size()-1);
						                	String dataAsStringVertexTo = dataVertexTo.getText();
						                	
					        				if(gpc.getDataModel().getDirectedNonWeightedInt().isAdjacent(Integer.parseInt(dataAsStringVertexTo), Integer.parseInt(dataAsString))) {
					        					directedEdgePlacement = -directedEdgePlacement;
					        					xMoveStart = directedEdgePlacement;
							        			yMoveStart = directedEdgePlacement;
							        			
							        			xMoveEnd = directedEdgePlacement;
							        			yMoveEnd = directedEdgePlacement;
					        				}
					        				
					        			}else if(gpc.getSelectedDataChoiceDirectedNonWeightedGraph().equals("Double")) {
					        				
					        				ObservableList<Node> childsVertexTo = vertexTo.getChildren();
						                	Text dataVertexTo = (Text) childsVertexTo.get(childsVertexTo.size()-1);
						                	String dataAsStringVertexTo = dataVertexTo.getText();
						                	
					        				if(gpc.getDataModel().getDirectedNonWeightedDouble().isAdjacent(Double.parseDouble(dataAsStringVertexTo), Double.parseDouble(dataAsString))) {
					        					xMoveStart = -xMoveStart;
							        			yMoveStart = -yMoveStart;
							        			
							        			xMoveEnd = -xMoveEnd;
							        			yMoveEnd = -yMoveEnd;
					        				}
					        				
					        				
					        			}else if(gpc.getSelectedDataChoiceDirectedNonWeightedGraph().equals("String")) {
					        				
					        				ObservableList<Node> childsVertexTo = vertexTo.getChildren();
						                	Text dataVertexTo = (Text) childsVertexTo.get(childsVertexTo.size()-1);
						                	String dataAsStringVertexTo = dataVertexTo.getText();
						                	
					        				if(gpc.getDataModel().getDirectedNonWeightedString().isAdjacent(dataAsStringVertexTo,dataAsString)) {
					        					xMoveStart = -xMoveStart;
							        			yMoveStart = -yMoveStart;
							        			
							        			xMoveEnd = -xMoveEnd;
							        			yMoveEnd = -yMoveEnd;
					        				}
					        				
					        				
					        			}
					        			addEdgeHandlerNonWeighted(dataAsString,result.get());
						        		
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
//							        		rect.setOnMousePressed(mouseClickedOnEdgeEvent());
						        			rect.setOnMousePressed(gpc.getClickedOnEdgeHandler());
						        			
//						        			rect.getTransforms().add(new Rotate(45,rect.getX(),rect.getY()));
						        		    
						        			gpc.getCenterPaneDirectedNonWeightedGraph().getChildren().add(0,rect);
						        			
						        			Polygon arrowHead = new Polygon();
						        	        arrowHead.getPoints().addAll(new Double[]{
						        	            0.0, 7.0,
						        	            -7.0, -7.0,
						        	            7.0, -7.0 });
						        	        arrowHead.setFill(Color.BLACK);
						        	       
						        	        arrowHead.setOnMouseClicked(Event::consume);
//							        		arrowHead.setOnMousePressed(mouseClickedOnEdgeEvent());
						        	        arrowHead.setOnMousePressed(gpc.getClickedOnEdgeHandler());
						        	        
						        	        arrowHead.rotateProperty().bind(Bindings.createDoubleBinding(
						        	        		() -> Math.atan2(rect.getY() - (rect.getY()), rect.getX()- (rect.getX()+ rect.getWidth())) * 180 / 3.14 - 90,
						        	        		rect.xProperty(),rect.yProperty(),rect.widthProperty()));
						        	        
						        	        arrowHead.layoutXProperty().bind(Bindings.createDoubleBinding(
							            	          () -> rect.getX() +selfEdgeArrowheadPlacement,
							            	          rect.xProperty()));
								            	
						            	    arrowHead.layoutYProperty().bind(Bindings.createDoubleBinding(
						            	          () -> rect.getY() ,
						            	          rect.yProperty()));
						        	        
						        	        
						        	        gpc.getCenterPaneDirectedNonWeightedGraph().getChildren().add(arrowHead);
						        			
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
//							        		line.setOnMousePressed(mouseClickedOnEdgeEvent());
							        		line.setOnMousePressed(gpc.getClickedOnEdgeHandler());
						        			
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
//							        		arrowHead.setOnMousePressed(mouseClickedOnEdgeEvent());
							            	arrowHead.setOnMousePressed(gpc.getClickedOnEdgeHandler());

							        		
							        		arrowHead.layoutXProperty().bind(Bindings.createDoubleBinding(
							            	          () -> ((line.getStartX() + line.getEndX() ) / 2) ,
							            	          line.startXProperty(), line.endXProperty()));
								            	
						            	    arrowHead.layoutYProperty().bind(Bindings.createDoubleBinding(
						            	          () -> ((line.getStartY() + line.getEndY() ) / 2),
						            	          line.startYProperty(), line.endYProperty()));
						            	    
							        		gpc.getCenterPaneDirectedNonWeightedGraph().getChildren().add(arrowHead);
							        		gpc.getCenterPaneDirectedNonWeightedGraph().getChildren().add(0,line);
						        		}
					        			
					        		}
				        			
				        		}
				        	}
				        	
					 }else if(gpc.getSelectedTabName().equals("Undirected Weighted Graph") || gpc.getSelectedTabName().equals("Directed Weighted Graph")) {
						 
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
			        	
			        	dialog.getDialogPane().getStylesheets().add("/application/global.css");
			        	
			        	Optional<Pair<String, String>> result = dialog.showAndWait();
			        	
			        	if(result.isPresent() && gpc.isInputValidAddingEdge(result.get().getKey(),dataAsString,result.get().getValue()) ){
							 if(result.isPresent()) {
								 
								 if(gpc.getSelectedTabName().equals("Undirected Weighted Graph")) {
					        			
					        			for(Node child : gpc.getCenterPaneUndirectedWeightedGraph().getChildren()) {
					                		
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
//					        		line.setOnMousePressed(mouseClickedOnEdgeEvent());
					        		line.setOnMousePressed(gpc.getClickedOnEdgeHandler());

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
//					            	label.setPrefWidth(25);
//					            	label.setPrefHeight(25);
					            	label.setAlignment(Pos.CENTER);
					            	label.setFont(new Font(10));

					            	label.setOnMouseClicked(Event::consume);
//					        		label.setOnMousePressed(mouseClickedOnEdgeEvent());
					            	label.setOnMousePressed(gpc.getClickedOnEdgeHandler());
					            	
					            	label.layoutXProperty().bind(Bindings.createDoubleBinding(
				            	          () -> (line.getStartX() + line.getEndX() - label.getWidth()) / 2,
				            	          line.startXProperty(), line.endXProperty(), label.widthProperty()));
					            	
				            	    label.layoutYProperty().bind(Bindings.createDoubleBinding(
				            	          () -> (line.getStartY() + line.getEndY() - label.getHeight()) / 2,
				            	          line.startYProperty(), line.endYProperty(), label.heightProperty()));
				            	    
				            	    
					        		
					        		gpc.getCenterPaneUndirectedWeightedGraph().getChildren().add(0,line);
					        		gpc.getCenterPaneUndirectedWeightedGraph().getChildren().add(label);
						        		
					        			
					        		}else if(gpc.getSelectedTabName().equals("Directed Weighted Graph")) {
					        			
					        			for(Node child : gpc.getCenterPaneDirectedWeightedGraph().getChildren()) {
					                		
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
				        			
				        			if(gpc.getSelectedDataChoiceDirectedWeightedGraph().equals("Integer")) {
				        				
				        				ObservableList<Node> childsVertexTo = vertexTo.getChildren();
					                	Text dataVertexTo = (Text) childsVertexTo.get(childsVertexTo.size()-1);
					                	String dataAsStringVertexTo = dataVertexTo.getText();
					                	
				        				if(gpc.getDataModel().getDirectedWeightedInt().isAdjacent(Integer.parseInt(dataAsStringVertexTo), Integer.parseInt(dataAsString))) {
				        					xMoveStart = -xMoveStart;
						        			yMoveStart = -yMoveStart;
						        			
						        			xMoveEnd = -xMoveEnd;
						        			yMoveEnd = -yMoveEnd;
				        				}
				        				
				        			}else if(gpc.getSelectedDataChoiceDirectedWeightedGraph().equals("Double")) {
				        				
				        				ObservableList<Node> childsVertexTo = vertexTo.getChildren();
					                	Text dataVertexTo = (Text) childsVertexTo.get(childsVertexTo.size()-1);
					                	String dataAsStringVertexTo = dataVertexTo.getText();
					                	
				        				if(gpc.getDataModel().getDirectedWeightedDouble().isAdjacent(Double.parseDouble(dataAsStringVertexTo), Double.parseDouble(dataAsString))) {
				        					xMoveStart = -xMoveStart;
						        			yMoveStart = -yMoveStart;
						        			
						        			xMoveEnd = -xMoveEnd;
						        			yMoveEnd = -yMoveEnd;
				        				}
				        				
				        				
				        			}else if(gpc.getSelectedDataChoiceDirectedWeightedGraph().equals("String")) {
				        				
				        				ObservableList<Node> childsVertexTo = vertexTo.getChildren();
					                	Text dataVertexTo = (Text) childsVertexTo.get(childsVertexTo.size()-1);
					                	String dataAsStringVertexTo = dataVertexTo.getText();
					                	
				        				if(gpc.getDataModel().getDirectedWeightedString().isAdjacent(dataAsStringVertexTo,dataAsString)) {
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
//						        		rect.setOnMousePressed(mouseClickedOnEdgeEvent());
					        			rect.setOnMousePressed(gpc.getClickedOnEdgeHandler());
					        			
//					        			rect.getTransforms().add(new Rotate(45,rect.getX(),rect.getY()));
					        		    
					        			gpc.getCenterPaneDirectedWeightedGraph().getChildren().add(0,rect);
					        			
					        	        Label label = new Label(result.get().getValue());
						            	label.setStyle("-fx-background-color: white; -fx-border-color: black;");
						            	label.setPadding(new Insets(2, 4, 2, 4));
//						            	label.setPrefWidth(25);
//						            	label.setPrefHeight(25);
						            	label.setAlignment(Pos.CENTER);
						            	label.setFont(new Font(10));
						            	
						            	label.setOnMouseClicked(Event::consume);
//						        		label.setOnMousePressed(mouseClickedOnEdgeEvent());
						            	label.setOnMousePressed(gpc.getClickedOnEdgeHandler());
						            	
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
//						        		arrowHead.setOnMousePressed(mouseClickedOnEdgeEvent());
					            	    arrowHead.setOnMousePressed(gpc.getClickedOnEdgeHandler());
					        	        
					        	        gpc.getCenterPaneDirectedWeightedGraph().getChildren().add(arrowHead);
					        	        gpc.getCenterPaneDirectedWeightedGraph().getChildren().add(label);
					        			
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
//						        		line.setOnMousePressed(mouseClickedOnEdgeEvent());
						        		line.setOnMousePressed(gpc.getClickedOnEdgeHandler());
					        			
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
//						            	label.setPrefWidth(25);
//						            	label.setPrefHeight(25);
						            	label.setAlignment(Pos.CENTER);
						            	label.setFont(new Font(10));
						            	
						            	label.setOnMouseClicked(Event::consume);
//						        		label.setOnMousePressed(mouseClickedOnEdgeEvent());
						            	label.setOnMousePressed(gpc.getClickedOnEdgeHandler());
						            	
						            	label.layoutXProperty().bind(Bindings.createDoubleBinding(
					            	          () -> (line.getStartX() + line.getEndX() - label.getWidth()) / 2,
					            	          line.startXProperty(), line.endXProperty(), label.widthProperty()));
						            	
					            	    label.layoutYProperty().bind(Bindings.createDoubleBinding(
					            	          () -> (line.getStartY() + line.getEndY() - label.getHeight()) / 2,
					            	          line.startYProperty(), line.endYProperty(), label.heightProperty()));

						            	arrowHead.setOnMouseClicked(Event::consume);
//						        		arrowHead.setOnMousePressed(mouseClickedOnEdgeEvent());
						            	arrowHead.setOnMousePressed(gpc.getClickedOnEdgeHandler());
						        		
						        		arrowHead.layoutXProperty().bind(Bindings.createDoubleBinding(
						            	          () -> ((line.getStartX() + 4*line.getEndX()) / 5) ,
						            	          line.startXProperty(), line.endXProperty()));
							            	
					            	    arrowHead.layoutYProperty().bind(Bindings.createDoubleBinding(
					            	          () -> (line.getStartY() + 4*line.getEndY()) / 5,
					            	          line.startYProperty(), line.endYProperty()));
					            	    
					            	    gpc.getCenterPaneDirectedWeightedGraph().getChildren().add(0,label);
						        		gpc.getCenterPaneDirectedWeightedGraph().getChildren().add(arrowHead);
						        		gpc.getCenterPaneDirectedWeightedGraph().getChildren().add(0,line);
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
              
              alert.getDialogPane().getStylesheets().add("/application/global.css");
              
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
             	 
             	 if(gpc.getSelectedTabName().equals("Undirected Non-Weighted Graph")) {
             		 
             		 for(Node child:gpc.getCenterPaneUndirectedNonWeightedGraph().getChildren()) {
	                		 
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
	                 				gpc.getCenterPaneUndirectedNonWeightedGraph().getChildren().remove(edge);
	                 			}
	                 		}
             		 
             		 if(isInteger(dataAsString)) {
		                		
		                 		gpc.getCenterPaneUndirectedNonWeightedGraph().getChildren().remove(t.getSource());
		                 		removeVertexFromUndirectedNonWeightedGraph("Integer",dataAsString);
		                 		
		                 }else if(isDouble(dataAsString)) {
		                 		
		                 		gpc.getCenterPaneUndirectedNonWeightedGraph().getChildren().remove(t.getSource());
		                 		removeVertexFromUndirectedNonWeightedGraph("Double",dataAsString);
		                 		
		                 }else if(isString(dataAsString)) {
		                 		
		                 		gpc.getCenterPaneUndirectedNonWeightedGraph().getChildren().remove(t.getSource());
		                 		removeVertexFromUndirectedNonWeightedGraph("String",dataAsString);
		                 		
		                 }
             		 
             	 }else if(gpc.getSelectedTabName().equals("Undirected Weighted Graph")) {
             		 
             		 for(Node child:gpc.getCenterPaneUndirectedWeightedGraph().getChildren()) {
	                		 
	                		 if(child instanceof Line) {
	                			 
	                			 Line line = (Line) child;
	                			 
	                			 if((line.getStartX() == stackPaneX && line.getStartY() == stackPaneY) || 
	                					 (line.getEndX() == stackPaneX && line.getEndY() == stackPaneY)) {
	                				 
	                				 edgeToRemove = line;
	                				 edgesToRemove.add(edgeToRemove);
	                				 
	                				 for(Node grandchild:gpc.getCenterPaneUndirectedWeightedGraph().getChildren()) {
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
	                 			gpc.getCenterPaneUndirectedWeightedGraph().getChildren().remove(edge);
	                 		}
	                 		for(Label weightBox: weightBoxesToRemove) {
	                 			gpc.getCenterPaneUndirectedWeightedGraph().getChildren().remove(weightBox);
	                 		}
	                 	}
             		 
             		 if(gpc.getSelectedTabName().equals("Undirected Weighted Graph") && isInteger(dataAsString)) {
		                 		
		                 	gpc.getCenterPaneUndirectedWeightedGraph().getChildren().remove(t.getSource());
		                 	removeVertexFromUndirectedWeightedGraph("Integer",dataAsString);
		                 		
		                 }else if(gpc.getSelectedTabName().equals("Undirected Weighted Graph") && isDouble(dataAsString)) {
		                 		
		                 	gpc.getCenterPaneUndirectedWeightedGraph().getChildren().remove(t.getSource());
		                 	removeVertexFromUndirectedWeightedGraph("Double",dataAsString);
		                 		
		                 }else if(gpc.getSelectedTabName().equals("Undirected Weighted Graph") && isString(dataAsString)) {
		                 		
		                 	gpc.getCenterPaneUndirectedWeightedGraph().getChildren().remove(t.getSource());
		                 	removeVertexFromUndirectedWeightedGraph("String",dataAsString);
		                 		
		                 }
             		 
             	 }else if(gpc.getSelectedTabName().equals("Directed Non-Weighted Graph")) {
             		 
             		 
             		 for(Node child:gpc.getCenterPaneDirectedNonWeightedGraph().getChildren()) {
	                		 
	                		 if(child instanceof Line) {
	                			 
	                			 Line line = (Line) child;
	                			 
	                			 if((line.getStartX() == stackPaneX + directedEdgePlacement && line.getStartY() == stackPaneY + directedEdgePlacement) || 
	                				(line.getEndX() == stackPaneX + directedEdgePlacement && line.getEndY() == stackPaneY + directedEdgePlacement) ||
	                				(line.getStartX() == stackPaneX - directedEdgePlacement && line.getStartY() == stackPaneY - directedEdgePlacement) || 
             					 (line.getEndX() == stackPaneX - directedEdgePlacement && line.getEndY() == stackPaneY - directedEdgePlacement)) {
	                				 
	                				 edgeToRemove = line;
	                				 edgesToRemove.add(edgeToRemove);
	                				 
	                				 for(Node grandchild:gpc.getCenterPaneDirectedNonWeightedGraph().getChildren()) {
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
             		 
             		 for(Node child:gpc.getCenterPaneDirectedNonWeightedGraph().getChildren()) {
             			 
             			 if(child instanceof Rectangle) {
             				 
             				 Rectangle selfEdge = (Rectangle)child;
             				 
             				 if(selfEdge.getX() == stackPaneX && selfEdge.getY() == stackPaneY) {
             					 selfEdgeToRemove = selfEdge;
             					 
             					 for(Node grandchild:gpc.getCenterPaneDirectedNonWeightedGraph().getChildren()) {
             						 
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
	                 			gpc.getCenterPaneDirectedNonWeightedGraph().getChildren().remove(edge);
	                 		}
	                 		for(Polygon arrowHead: arrowHeadsToRemove) {
	                 			gpc.getCenterPaneDirectedNonWeightedGraph().getChildren().remove(arrowHead);
	                 		}
	                 	 }
             		 
             		 if(arrowHeadsToRemove.size() != 0) {	
		                 		
	                 		for(Polygon arrowHead: arrowHeadsToRemove) {
	                 			gpc.getCenterPaneDirectedNonWeightedGraph().getChildren().remove(arrowHead);
	                 		}
	                 		
		                 }
             		 
             		 if(selfEdgeToRemove != null) {
             			 gpc.getCenterPaneDirectedNonWeightedGraph().getChildren().remove(selfEdgeToRemove);
             		 }

             		 
             		 
             		 if(gpc.getSelectedTabName().equals("Directed Non-Weighted Graph") && isInteger(dataAsString)) {
		                 		
		                 	gpc.getCenterPaneDirectedNonWeightedGraph().getChildren().remove(t.getSource());
		                 	removeVertexFromDirectedNonWeightedGraph("Integer",dataAsString);
		                 		
		                 }else if(gpc.getSelectedTabName().equals("Directed Non-Weighted Graph") && isDouble(dataAsString)) {
		                 		
		                 	gpc.getCenterPaneDirectedNonWeightedGraph().getChildren().remove(t.getSource());
		                 	removeVertexFromDirectedNonWeightedGraph("Double",dataAsString);
		                 		
		                 }else if(gpc.getSelectedTabName().equals("Directed Non-Weighted Graph") && isString(dataAsString)) {
		                 		
		                 	gpc.getCenterPaneDirectedNonWeightedGraph().getChildren().remove(t.getSource());
		                 	removeVertexFromDirectedNonWeightedGraph("String",dataAsString);
		                 		
		                 }
             		 
             	 }else if(gpc.getSelectedTabName().equals("Directed Weighted Graph")) {
             		 
             		 for(Node child:gpc.getCenterPaneDirectedWeightedGraph().getChildren()) {
	                		 
	                		 if(child instanceof Line) {
	                			 
	                			 Line line = (Line) child;
	                			 
	                			 if((line.getStartX() == stackPaneX + directedEdgePlacement && line.getStartY() == stackPaneY + directedEdgePlacement) || 
	                				(line.getEndX() == stackPaneX + directedEdgePlacement && line.getEndY() == stackPaneY + directedEdgePlacement) ||
	                				(line.getStartX() == stackPaneX - directedEdgePlacement && line.getStartY() == stackPaneY - directedEdgePlacement) || 
             					 (line.getEndX() == stackPaneX - directedEdgePlacement && line.getEndY() == stackPaneY - directedEdgePlacement)) {
	                				 
	                				 edgeToRemove = line;
	                				 edgesToRemove.add(edgeToRemove);
	                				 
	                				 for(Node grandchild:gpc.getCenterPaneDirectedWeightedGraph().getChildren()) {
	                					 if(grandchild instanceof Polygon) {
	                						 
	                						 Polygon arrowHead = ((Polygon)grandchild);

	                						 if(arrowHead.getLayoutX() == (line.getStartX() + 4*line.getEndX()) / 5 && 
	                							arrowHead.getLayoutY() == (line.getStartY() + 4*line.getEndY()) / 5) {
	                							 arrowHeadToRemove =  arrowHead;
	                							 arrowHeadsToRemove.add(arrowHeadToRemove);
	                						 }
	                						 
	                					 }
	                				 }
	                				 
	                				 for(Node grandchild:gpc.getCenterPaneDirectedWeightedGraph().getChildren()) {
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
             		 
             		 for(Node child:gpc.getCenterPaneDirectedWeightedGraph().getChildren()) {
             			 
             			 if(child instanceof Rectangle) {
             				 
             				 Rectangle selfEdge = (Rectangle)child;
             				 
             				 if(selfEdge.getX() == stackPaneX && selfEdge.getY() == stackPaneY) {
             					 selfEdgeToRemove = selfEdge;
             					 
             					 for(Node grandchild:gpc.getCenterPaneDirectedWeightedGraph().getChildren()) {
             						 
             						 if(grandchild instanceof Polygon) {
             							 Polygon arrowhead = (Polygon) grandchild;
             							 
             							 if(arrowhead.getLayoutX() == selfEdgeToRemove.getX() + selfEdgeArrowheadPlacement 
             								&& arrowhead.getLayoutY() == selfEdgeToRemove.getY()) {
             								 
             								 arrowHeadsToRemove.add(arrowhead);
             								 
             							 }
             						 }
             						 
             					 }
             					 
             					 for(Node grandchild:gpc.getCenterPaneDirectedWeightedGraph().getChildren()) {
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
	                 			gpc.getCenterPaneDirectedWeightedGraph().getChildren().remove(edge);
	                 		}
	                 		
	                 		for(Polygon arrowHead: arrowHeadsToRemove) {
	                 			gpc.getCenterPaneDirectedWeightedGraph().getChildren().remove(arrowHead);
	                 		}
	                 		
	                 		for(Label weightBox: weightBoxesToRemove) {
	                 			gpc.getCenterPaneDirectedWeightedGraph().getChildren().remove(weightBox);
	                 		}

	                 	 }
             		 
             		 if(arrowHeadsToRemove.size() != 0) {	
		                 		
	                 		for(Polygon arrowHead: arrowHeadsToRemove) {
	                 			gpc.getCenterPaneDirectedWeightedGraph().getChildren().remove(arrowHead);
	                 		}
	                 		
		                 }
             		 
             		 if(weightBoxesToRemove.size() != 0) {	
		                 		
             			 for(Label weightBox: weightBoxesToRemove) {
		                 		gpc.getCenterPaneDirectedWeightedGraph().getChildren().remove(weightBox);
		                 	}
		                 		
			             }
             		 
             		 if(selfEdgeToRemove != null) {
             			 gpc.getCenterPaneDirectedWeightedGraph().getChildren().remove(selfEdgeToRemove);
             		 }
             		 
             		 
             		 if(gpc.getSelectedTabName().equals("Directed Weighted Graph") && isInteger(dataAsString)) {
		                 		
		                 	gpc.getCenterPaneDirectedWeightedGraph().getChildren().remove(t.getSource());
		                 	removeVertexFromDirectedWeightedGraph("Integer",dataAsString);
		                 		
		                 }else if(gpc.getSelectedTabName().equals("Directed Weighted Graph") && isDouble(dataAsString)) {
		                 		
		                 	gpc.getCenterPaneDirectedWeightedGraph().getChildren().remove(t.getSource());
		                 	removeVertexFromDirectedWeightedGraph("Double",dataAsString);
		                 		
		                 }else if(gpc.getSelectedTabName().equals("Directed Weighted Graph") && isString(dataAsString)) {
		                 		
		                 	gpc.getCenterPaneDirectedWeightedGraph().getChildren().remove(t.getSource());
		                 	removeVertexFromDirectedWeightedGraph("String",dataAsString);
		                 		
		                 }
             		 
             	 }
             	 
              }
     	 }

     	gpc.setCurrentStackPane(((StackPane)(t.getSource())));
     	gpc.setOrgSceneX(t.getSceneX());
        gpc.setOrgSceneY(t.getSceneY());
     	gpc.setLayoutX(gpc.getCurrentStackPane().getLayoutX());
     	gpc.setLayoutY(gpc.getCurrentStackPane().getLayoutY());
         
     }
	
	
	 /**
	  * This method given the data from 2 vertices will add an edge between these 2 vertices accordingly for the correct non weighted graph in the model.
	  * @param vertexDataAsStringFrom
	  * @param vertexDataAsStringTo
	  */
	public void addEdgeHandlerNonWeighted(String vertexDataAsStringFrom, String vertexDataAsStringTo) {
		if(gpc.getSelectedTabName().equals("Undirected Non-Weighted Graph") && isInteger(vertexDataAsStringFrom) && isInteger(vertexDataAsStringTo)) {
		
		gpc.getDataModel().getUndirectedNonWeightedInt().addEdge(Integer.parseInt(vertexDataAsStringFrom),Integer.parseInt(vertexDataAsStringTo));
		
	}else if(gpc.getSelectedTabName().equals("Undirected Non-Weighted Graph") && isDouble(vertexDataAsStringFrom) && isDouble(vertexDataAsStringTo)) {
		
		gpc.getDataModel().getUndirectedNonWeightedDouble().addEdge(Double.parseDouble(vertexDataAsStringFrom),Double.parseDouble(vertexDataAsStringTo));
		
	}else if(gpc.getSelectedTabName().equals("Undirected Non-Weighted Graph") && isString(vertexDataAsStringFrom) && isString(vertexDataAsStringTo)) {
	
		gpc.getDataModel().getUndirectedNonWeightedString().addEdge(vertexDataAsStringFrom,vertexDataAsStringTo);
		
	}
	
	else if(gpc.getSelectedTabName().equals("Directed Non-Weighted Graph") && isInteger(vertexDataAsStringFrom) && isInteger(vertexDataAsStringTo)) {
	
		gpc.getDataModel().getDirectedNonWeightedInt().addEdge(Integer.parseInt(vertexDataAsStringFrom),Integer.parseInt(vertexDataAsStringTo));
		
	}else if(gpc.getSelectedTabName().equals("Directed Non-Weighted Graph") && isDouble(vertexDataAsStringFrom) && isDouble(vertexDataAsStringTo)) {
		
		gpc.getDataModel().getDirectedNonWeightedDouble().addEdge(Double.parseDouble(vertexDataAsStringFrom),Double.parseDouble(vertexDataAsStringTo));
		
	}else if(gpc.getSelectedTabName().equals("Directed Non-Weighted Graph") && isString(vertexDataAsStringFrom) && isString(vertexDataAsStringTo)) {
	
	 		gpc.getDataModel().getDirectedNonWeightedString().addEdge(vertexDataAsStringFrom,vertexDataAsStringTo);
	 		
	 	}
			
	}
	
	/**
	 * This method given the data from 2 vertices and a weight value will add an edge between these 2 vertices accordingly for the correct weighted 
	 * graph in the model.
	 * @param vertexDataAsStringFrom
	 * @param vertexDataAsStringTo
	 * @param weight
	 */
	public void addEdgeHandlerWeighted(String vertexDataAsStringFrom, String vertexDataAsStringTo, double weight) {
	
	 	if(gpc.getSelectedTabName().equals("Undirected Weighted Graph") && isInteger(vertexDataAsStringFrom) && isInteger(vertexDataAsStringTo)) {
	
		gpc.getDataModel().getUndirectedWeightedInt().addEdge(Integer.parseInt(vertexDataAsStringFrom),Integer.parseInt(vertexDataAsStringTo),weight);
		
	}else if(gpc.getSelectedTabName().equals("Undirected Weighted Graph")  && isDouble(vertexDataAsStringFrom) && isDouble(vertexDataAsStringTo)) {
		
		gpc.getDataModel().getUndirectedWeightedDouble().addEdge(Double.parseDouble(vertexDataAsStringFrom),Double.parseDouble(vertexDataAsStringTo),weight);
		
	}else if(gpc.getSelectedTabName().equals("Undirected Weighted Graph") && isString(vertexDataAsStringFrom) && isString(vertexDataAsStringTo)) {
	
		gpc.getDataModel().getUndirectedWeightedString().addEdge(vertexDataAsStringFrom,vertexDataAsStringTo,weight);
		
	}
	else if(gpc.getSelectedTabName().equals("Directed Weighted Graph") && isInteger(vertexDataAsStringFrom) && isInteger(vertexDataAsStringTo)) {
	
		gpc.getDataModel().getDirectedWeightedInt().addEdge(Integer.parseInt(vertexDataAsStringFrom),Integer.parseInt(vertexDataAsStringTo),weight);
		
	}else if(gpc.getSelectedTabName().equals("Directed Weighted Graph") && isDouble(vertexDataAsStringFrom) && isDouble(vertexDataAsStringTo)) {
	
		gpc.getDataModel().getDirectedWeightedDouble().addEdge(Double.parseDouble(vertexDataAsStringFrom),Double.parseDouble(vertexDataAsStringTo),weight);
		
	}else if(gpc.getSelectedTabName().equals("Directed Weighted Graph") && isString(vertexDataAsStringFrom) && isString(vertexDataAsStringTo)) {
	 		
	 		gpc.getDataModel().getDirectedWeightedString().addEdge(vertexDataAsStringFrom,vertexDataAsStringTo,weight);
	 		
	 	}
	
	 		
	}
	
	/**
	 * This method given the type of the vertex that is being deleted and the data of the vertex will update the model by removing
	 * the vertex from the correct undirected non weighted graph.
	 * @param type
	 * @param dataAsString
	 */
	private void removeVertexFromUndirectedNonWeightedGraph(String type, String dataAsString) {
	    	
	    	if(type.equals("Integer")) {
			
			Vertex<Integer> vertexToRemove = gpc.getDataModel().getUndirectedNonWeightedInt().returnVertex(Integer.parseInt(dataAsString));
			
			gpc.getDataModel().getVertexDataUndirectedNonWeightedInt().remove(vertexToRemove);
			
			gpc.getDataModel().getListOfUndirectedNonWeightedIntVertices().remove(vertexToRemove);
			
			gpc.getDataModel().getUndirectedNonWeightedInt().removeVertex(vertexToRemove.getElement());
			
			
		}else if(type.equals("Double")) {
			
			Vertex<Double> vertexToRemove = gpc.getDataModel().getUndirectedNonWeightedDouble().returnVertex(Double.parseDouble(dataAsString));
			
			gpc.getDataModel().getVertexDataUndirectedNonWeightedDouble().remove(vertexToRemove);
			
			gpc.getDataModel().getListOfUndirectedNonWeightedDoubleVertices().remove(vertexToRemove);
			
			gpc.getDataModel().getUndirectedNonWeightedDouble().removeVertex(vertexToRemove.getElement());
		
		}else if(type.equals("String")) {
			
			Vertex<String> vertexToRemove = gpc.getDataModel().getUndirectedNonWeightedString().returnVertex(dataAsString);
			
			gpc.getDataModel().getVertexDataUndirectedNonWeightedString().remove(vertexToRemove);
			
			gpc.getDataModel().getListOfUndirectedNonWeightedStringVertices().remove(vertexToRemove);
			
			gpc.getDataModel().getUndirectedNonWeightedString().removeVertex(vertexToRemove.getElement());
		
			
		}
	}
	
	/**
	 * This method given the type of the vertex that is being deleted and the data of the vertex will update the model by removing
	 * the vertex from the correct undirected weighted graph.
	 * @param type
	 * @param dataAsString
	 */
	private void removeVertexFromUndirectedWeightedGraph(String type, String dataAsString) {
		
		if(type.equals("Integer")) {
		
		Vertex<Integer> vertexToRemove = gpc.getDataModel().getUndirectedWeightedInt().returnVertex(Integer.parseInt(dataAsString));
		
		gpc.getDataModel().getVertexDataUndirectedWeightedInt().remove(vertexToRemove);
		
		gpc.getDataModel().getListOfUndirectedWeightedIntVertices().remove(vertexToRemove);
		
		gpc.getDataModel().getUndirectedWeightedInt().removeVertex(vertexToRemove.getElement());
		
		
	}else if(type.equals("Double")) {
		
		Vertex<Double> vertexToRemove = gpc.getDataModel().getUndirectedWeightedDouble().returnVertex(Double.parseDouble(dataAsString));
		
		gpc.getDataModel().getVertexDataUndirectedWeightedDouble().remove(vertexToRemove);
		
		gpc.getDataModel().getListOfUndirectedWeightedDoubleVertices().remove(vertexToRemove);
		
		gpc.getDataModel().getUndirectedWeightedDouble().removeVertex(vertexToRemove.getElement());
	
	}else if(type.equals("String")) {
	    		
	    		Vertex<String> vertexToRemove = gpc.getDataModel().getUndirectedNonWeightedString().returnVertex(dataAsString);
	    		
	    		gpc.getDataModel().getVertexDataUndirectedWeightedString().remove(vertexToRemove);
	    		
	    		gpc.getDataModel().getListOfUndirectedWeightedStringVertices().remove(vertexToRemove);
	    		
	    		gpc.getDataModel().getUndirectedWeightedString().removeVertex(vertexToRemove.getElement());
		
	    		
	    	}
	    }
	 
		/**
	 * This method given the type of the vertex that is being deleted and the data of the vertex will update the model by removing
	 * the vertex from the correct directed non weighted graph.
	 * @param type
	 * @param dataAsString
	 */
	private void removeVertexFromDirectedNonWeightedGraph(String type, String dataAsString) {
		
		if(type.equals("Integer")) {
		
		Vertex<Integer> vertexToRemove = gpc.getDataModel().getDirectedNonWeightedInt().returnVertex(Integer.parseInt(dataAsString));
		
		gpc.getDataModel().getVertexDataDirectedNonWeightedInt().remove(vertexToRemove);
		
		gpc.getDataModel().getListOfDirectedNonWeightedIntVertices().remove(vertexToRemove);
		
		gpc.getDataModel().getDirectedNonWeightedInt().removeVertex(vertexToRemove.getElement());
		
		
	}else if(type.equals("Double")) {
		
		Vertex<Double> vertexToRemove = gpc.getDataModel().getDirectedNonWeightedDouble().returnVertex(Double.parseDouble(dataAsString));
		
		gpc.getDataModel().getVertexDataDirectedNonWeightedDouble().remove(vertexToRemove);
		
		gpc.getDataModel().getListOfDirectedNonWeightedDoubleVertices().remove(vertexToRemove);
		
		gpc.getDataModel().getDirectedNonWeightedDouble().removeVertex(vertexToRemove.getElement());
	
	}else if(type.equals("String")) {
			
			Vertex<String> vertexToRemove = gpc.getDataModel().getDirectedNonWeightedString().returnVertex(dataAsString);
			
			gpc.getDataModel().getVertexDataDirectedNonWeightedString().remove(vertexToRemove);
			
			gpc.getDataModel().getListOfDirectedNonWeightedStringVertices().remove(vertexToRemove);
			
			gpc.getDataModel().getDirectedNonWeightedString().removeVertex(vertexToRemove.getElement());
	
			
		}
	}
	
	/**
	 * This method given the type of the vertex that is being deleted and the data of the vertex will update the model by removing
	 * the vertex from the correct directed weighted graph.
	 * @param type
	 * @param dataAsString
	 */
	private void removeVertexFromDirectedWeightedGraph(String type, String dataAsString) {
		
		if(type.equals("Integer")) {
		
		Vertex<Integer> vertexToRemove = gpc.getDataModel().getDirectedWeightedInt().returnVertex(Integer.parseInt(dataAsString));
		
		gpc.getDataModel().getVertexDataDirectedWeightedInt().remove(vertexToRemove);
		
		gpc.getDataModel().getListOfDirectedWeightedIntVertices().remove(vertexToRemove);
		
		gpc.getDataModel().getDirectedWeightedInt().removeVertex(vertexToRemove.getElement());
		
		
	}else if(type.equals("Double")) {
		
		Vertex<Double> vertexToRemove = gpc.getDataModel().getDirectedWeightedDouble().returnVertex(Double.parseDouble(dataAsString));
		
		gpc.getDataModel().getVertexDataDirectedWeightedDouble().remove(vertexToRemove);
		
		gpc.getDataModel().getListOfDirectedWeightedDoubleVertices().remove(vertexToRemove);
		
		gpc.getDataModel().getDirectedWeightedDouble().removeVertex(vertexToRemove.getElement());
	
	}else if(type.equals("String")) {
			
			Vertex<String> vertexToRemove = gpc.getDataModel().getDirectedWeightedString().returnVertex(dataAsString);
			
			gpc.getDataModel().getVertexDataDirectedWeightedString().remove(vertexToRemove);
			
			gpc.getDataModel().getListOfDirectedWeightedStringVertices().remove(vertexToRemove);
			
			gpc.getDataModel().getDirectedWeightedString().removeVertex(vertexToRemove.getElement());
	
			
		}
	}
	
	/**
	 * This method checks whether a String is a integer value.
	 * @param s
	 * @return
	 */
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
	
	/**
	 * This method checks whether a String is a double value.
	 * @param s
	 * @return
	 */ 
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
	
	/**
	 * This method checks if the String value is a String by validating that the String value is not a Integer or a Double.
	 * @param s
	 * @return
	 */
	private boolean isString(String s) {
		if(!isInteger(s) && !isDouble(s)) {
			return true;
		}
		return false;
	}
}
