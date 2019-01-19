package application.view;

import java.util.Optional;

import application.Main;
import application.model.DataModel;
import javafx.animation.Animation.Status;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;

public class GraphPanelController {

	
	private Main main;
	
	private DataModel dataModel;
	private ClickedOnEdgeHandler clickedOnEdgeHandler;
	
	@SuppressWarnings("rawtypes")
	private BreadthFirstSearch bfs;
	
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
	
	@FXML
	private ListView<String> listView;
	
	@FXML
	private Button playButton;
	
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
		
		bfs = new BreadthFirstSearch(this);
		clickedOnEdgeHandler = new ClickedOnEdgeHandler(this);
		
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
        
        listView.getItems().addAll("Breadth First Search");
        

	}
	
	public void setMain(Main main) {
		this.main = main;
		
		// all data from observable lists to data in this class.
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
	private void handlePlayButton(ActionEvent e) {
		
		if(listView.getSelectionModel().getSelectedItem() == null) {
			
			Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("No Graph Algorithm Selected.");
            alert.setHeaderText("No graph algorithm has been selected.");
            alert.setContentText("In order to visualize an animation on a graph you must select one of the algorithms in the list on the left"
            		+ " of the screen and then press the play button.");
            
            alert.showAndWait();
            
		}else {
			
			if(getSelectedTabName().equals("Undirected Non-Weighted Graph")) {
				
				if(listView.getSelectionModel().getSelectedItem().equals("Breadth First Search")) {
					
					if(bfs.getMainAnimationUndirectedNonWeighted().getStatus() == Status.STOPPED) {

						TextInputDialog dialogNonWeightedEdge = new TextInputDialog();
				        dialogNonWeightedEdge.setTitle("Starting vertex");
				        dialogNonWeightedEdge.setHeaderText("Choose starting vertex");
				        dialogNonWeightedEdge.setContentText("Please enter the vertex you would like to start the breadth first search from.");
				        ButtonType cancelButton = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
			        	ButtonType okButton = new ButtonType("Ok", ButtonData.OK_DONE);
			        	dialogNonWeightedEdge.getDialogPane().getButtonTypes().setAll(okButton,cancelButton);
			        	Optional<String> result = dialogNonWeightedEdge.showAndWait();
						
			        	if(result.isPresent() && isInputValidBfsStartingVertex(result.get())) {
			        		
			        		String input = result.get();
			        		
			        		bfs.performBreadthFirstSearchUndirectedNonWeighted(dataModel.getUndirectedNonWeightedInt(), Integer.parseInt(input));
							bfs.playAnimationUndirectedNonWeighted();
							playButton.setText("Pause");
			        		
			        	}
						
					}else if(bfs.getMainAnimationUndirectedNonWeighted().getStatus() == Status.RUNNING){
						
						bfs.pauseAnimationUndirectedNonWeighted();
						playButton.setText("Play");
						
					}else if(bfs.getMainAnimationUndirectedNonWeighted().getStatus() == Status.PAUSED) {
						
						bfs.playAnimationUndirectedNonWeighted();
						playButton.setText("Pause");
						
					}
					
				}
				
			}
			
		}

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
   			stack.setOnMousePressed(new ClickedOnVertexHandler(this,stack));
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
	
	/**
	 * @return the centerPaneUndirectedNonWeightedGraph
	 */
	public Pane getCenterPaneUndirectedNonWeightedGraph() {
		return centerPaneUndirectedNonWeightedGraph;
	}

	/**
	 * @return the centerPaneUndirectedWeightedGraph
	 */
	public Pane getCenterPaneUndirectedWeightedGraph() {
		return centerPaneUndirectedWeightedGraph;
	}

	/**
	 * @return the centerPaneDirectedNonWeightedGraph
	 */
	public Pane getCenterPaneDirectedNonWeightedGraph() {
		return centerPaneDirectedNonWeightedGraph;
	}

	/**
	 * @return the centerPaneDirectedWeightedGraph
	 */
	public Pane getCenterPaneDirectedWeightedGraph() {
		return centerPaneDirectedWeightedGraph;
	}

	/**
	 * @param centerPaneUndirectedNonWeightedGraph the centerPaneUndirectedNonWeightedGraph to set
	 */
	public void setCenterPaneUndirectedNonWeightedGraph(Pane centerPaneUndirectedNonWeightedGraph) {
		this.centerPaneUndirectedNonWeightedGraph = centerPaneUndirectedNonWeightedGraph;
	}

	/**
	 * @param centerPaneUndirectedWeightedGraph the centerPaneUndirectedWeightedGraph to set
	 */
	public void setCenterPaneUndirectedWeightedGraph(Pane centerPaneUndirectedWeightedGraph) {
		this.centerPaneUndirectedWeightedGraph = centerPaneUndirectedWeightedGraph;
	}

	/**
	 * @param centerPaneDirectedNonWeightedGraph the centerPaneDirectedNonWeightedGraph to set
	 */
	public void setCenterPaneDirectedNonWeightedGraph(Pane centerPaneDirectedNonWeightedGraph) {
		this.centerPaneDirectedNonWeightedGraph = centerPaneDirectedNonWeightedGraph;
	}

	/**
	 * @param centerPaneDirectedWeightedGraph the centerPaneDirectedWeightedGraph to set
	 */
	public void setCenterPaneDirectedWeightedGraph(Pane centerPaneDirectedWeightedGraph) {
		this.centerPaneDirectedWeightedGraph = centerPaneDirectedWeightedGraph;
	}

	public void setDataModel(DataModel dataModel) {
		this.dataModel = dataModel;
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
    
    public boolean isInputValidBfsStartingVertex(String input) {
    	
    	String selectedDataChoiceUndirectedNonWeightedGraph = getSelectedDataChoiceUndirectedNonWeightedGraph();
    	String selectedDataChoiceUndirectedWeightedGraph = getSelectedDataChoiceUndirectedWeightedGraph();
    	String selectedDataChoiceDirectedNonWeightedGraph = getSelectedDataChoiceDirectedNonWeightedGraph();
    	String selectedDataChoiceDirectedWeightedGraph = getSelectedDataChoiceDirectedWeightedGraph();
    	
    	String errorMessage = "";
    	if (input == null || input.length() == 0) {
    		
    		errorMessage += "Field cannot be left empty please add some data.";
    		
    	}else if(isInteger(input) && getSelectedTabName().equals("Undirected Non-Weighted Graph") 
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
    		}

    	}else if(isDouble(input) && getSelectedTabName().equals("Undirected Non-Weighted Graph")){

    		if(!dataModel.getUndirectedNonWeightedDouble().containsVertex(Double.parseDouble(input))) {
    			errorMessage+="Vertex with data '" + input + "' does not exist in this graph.";
    		}
    		
    	}else if(isString(input) && getSelectedTabName().equals("Undirected Non-Weighted Graph")){

    		if(!dataModel.getUndirectedNonWeightedString().containsVertex(input)) {
    			errorMessage+="Vertex with data '" + input + "' does not exist in this graph.";
    		}
    		
    	}
    	else if(isInteger(input) && getSelectedTabName().equals("Undirected Weighted Graph")){

    		if(!dataModel.getUndirectedWeightedInt().containsVertex(Integer.parseInt(input))) {
    			errorMessage+="Vertex with data '" + input + "' does not exist in this graph.";
    		}
    		
    	}else if(isDouble(input) && getSelectedTabName().equals("Undirected Weighted Graph") ){

    		if(!dataModel.getUndirectedWeightedDouble().containsVertex(Double.parseDouble(input))) {
    			errorMessage+="Vertex with data '" + input + "' does not exist in this graph.";
    		}
    		
    	}else if(isString(input) && getSelectedTabName().equals("Undirected Weighted Graph")){

    		if(!dataModel.getUndirectedWeightedString().containsVertex(input)) {
    			errorMessage+="Vertex with data '" + input + "' does not exist in this graph.";
    		}
    		
    	}
    	else if(isInteger(input) && getSelectedTabName().equals("Directed Non-Weighted Graph") ){
    		if(!dataModel.getDirectedNonWeightedInt().containsVertex(Integer.parseInt(input))) {
    			errorMessage+="Vertex with data '" + input + "' does not exist in this graph.";
    		}
    		
    	}else if(isDouble(input) && getSelectedTabName().equals("Directed Non-Weighted Graph")){

    		if(!dataModel.getDirectedNonWeightedDouble().containsVertex(Double.parseDouble(input))) {
    			errorMessage+="Vertex with data '" + input + "' does not exist in this graph.";
    		}
    		
    	}else if(isString(input) && getSelectedTabName().equals("Directed Non-Weighted Graph")){

    		if(!dataModel.getDirectedNonWeightedString().containsVertex(input)) {
    			errorMessage+="Vertex with data '" + input + "' does not exist in this graph.";
    		}
    		
    	}
    	else if(isInteger(input) && getSelectedTabName().equals("Directed Weighted Graph") ){

    		if(!dataModel.getDirectedWeightedInt().containsVertex(Integer.parseInt(input))) {
    			errorMessage+="Vertex with data '" + input + "' does not exist in this graph.";
    		}
    		
    	}else if(isDouble(input) && getSelectedTabName().equals("Directed Weighted Graph") ){

    		if(!dataModel.getDirectedWeightedDouble().containsVertex(Double.parseDouble(input))) {
    			errorMessage+="Vertex with data '" + input + "' does not exist in this graph.";
    		}
    		
    	}else if(isString(input) && getSelectedTabName().equals("Directed Weighted Graph") ){

    		if(!dataModel.getDirectedWeightedString().containsVertex(input)) {
    			errorMessage+="Vertex with data '" + input + "' does not exist in this graph.";
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
    
    public boolean isInputValid(String input,String startVertex,String weightInput) {
    	
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

	public DataModel getDataModel() {
		return dataModel;
	}

	/**
	 * @return the hoverMenu
	 */
	public ContextMenu getHoverMenu() {
		return hoverMenu;
	}

	/**
	 * @return the hoverMenuItemAddEdge
	 */
	public MenuItem getHoverMenuItemAddEdge() {
		return hoverMenuItemAddEdge;
	}

	/**
	 * @param hoverMenu the hoverMenu to set
	 */
	public void setHoverMenu(ContextMenu hoverMenu) {
		this.hoverMenu = hoverMenu;
	}

	/**
	 * @param hoverMenuItemAddEdge the hoverMenuItemAddEdge to set
	 */
	public void setHoverMenuItemAddEdge(MenuItem hoverMenuItemAddEdge) {
		this.hoverMenuItemAddEdge = hoverMenuItemAddEdge;
	}

	/**
	 * @return the clickedOnEdgeHandler
	 */
	public ClickedOnEdgeHandler getClickedOnEdgeHandler() {
		return clickedOnEdgeHandler;
	}

	/**
	 * @param clickedOnEdgeHandler the clickedOnEdgeHandler to set
	 */
	public void setClickedOnEdgeHandler(ClickedOnEdgeHandler clickedOnEdgeHandler) {
		this.clickedOnEdgeHandler = clickedOnEdgeHandler;
	}

	/**
	 * @return the orgSceneX
	 */
	public double getOrgSceneX() {
		return orgSceneX;
	}

	/**
	 * @return the orgSceneY
	 */
	public double getOrgSceneY() {
		return orgSceneY;
	}

	/**
	 * @param orgSceneX the orgSceneX to set
	 */
	public void setOrgSceneX(double orgSceneX) {
		this.orgSceneX = orgSceneX;
	}

	/**
	 * @param orgSceneY the orgSceneY to set
	 */
	public void setOrgSceneY(double orgSceneY) {
		this.orgSceneY = orgSceneY;
	}

	/**
	 * @return the currentStackPane
	 */
	public StackPane getCurrentStackPane() {
		return currentStackPane;
	}

	/**
	 * @param currentStackPane the currentStackPane to set
	 */
	public void setCurrentStackPane(StackPane currentStackPane) {
		this.currentStackPane = currentStackPane;
	}

	/**
	 * @return the layoutX
	 */
	public double getLayoutX() {
		return layoutX;
	}

	/**
	 * @return the layoutY
	 */
	public double getLayoutY() {
		return layoutY;
	}

	/**
	 * @param layoutX the layoutX to set
	 */
	public void setLayoutX(double layoutX) {
		this.layoutX = layoutX;
	}

	/**
	 * @param layoutY the layoutY to set
	 */
	public void setLayoutY(double layoutY) {
		this.layoutY = layoutY;
	}
	
	public Button getPlayButton() {
		return playButton;
	}

}
