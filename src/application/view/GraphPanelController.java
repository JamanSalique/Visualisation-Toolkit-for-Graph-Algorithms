package application.view;

import java.util.Optional;

import javax.swing.event.ChangeEvent;
import javafx.beans.value.ChangeListener;

import application.Main;
import application.model.DataModel;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.util.Pair;

public class GraphPanelController {

	
	private Main main;
	
	private DataModel dataModel;
	private ClickedOnEdgeHandler clickedOnEdgeHandler;
	private RandomGraphGenerator randomGraphGenerator;
	
	@SuppressWarnings("rawtypes")
	private BreadthFirstSearch bfs;
	
	private AlgorithmAnimations algorithmAnimations;
	
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
	private ListView<String> listViewUndirectedNonWeighted;
	
	@FXML
	private ListView<String> listViewUndirectedWeighted;
	
	@FXML
	private ListView<String> listViewDirectedNonWeighted;
	
	@FXML
	private ListView<String> listViewDirectedWeighted;
	
	
	@FXML
	private Button playButton;
	
	@FXML
	private Button restartButton;
	
	@FXML
	private Button skipToEndButton;
	
	@FXML
	private Slider animationSpeedSlider;
	
	@FXML
	private Button randomGraphButton;
	
	@FXML
	private Button clearGraphButton;
	
	@FXML
	private TextArea outputBox;
	
	@FXML 
	private MenuItem howToAddRemoveVertices;
	
	@FXML 
	private MenuItem howToAddRemoveEdges;
	
	@FXML 
	private MenuItem howToVisualiseAlgorithm;
	
	private StackPane currentStackPane;

	private double orgSceneX, orgSceneY;
    private double orgTranslateX, orgTranslateY;
    private double newTranslateX, newTranslateY;
    private double offsetX, offsetY;
    private double layoutX,layoutY;
    
    private double directedEdgePlacement;
    
    private boolean isUndirectedNonWeightedModified = true;
    private boolean isUndirectedWeightedModified = true;
    private boolean isDirectedNonWeightedModified = true;
    private boolean isDirectedWeightedModified = true;
    
    private String lastAlgorithmPlayedUndirectedNonWeighted;
    private String lastAlgorithmPlayedUndirectedWeighted;
    private String lastAlgorithmPlayedDirectedNonWeighted;
    private String lastAlgorithmPlayedDirectedWeighted;
    
    private String preSelectionChoiceUndirectedNonWeightedGraph;
    private String preSelectionChoiceUndirectedWeightedGraph;
    private String preSelectionChoiceDirectedNonWeightedGraph;
    private String preSelectionChoiceDirectedWeightedGraph;
    
    private MenuItem hoverMenuItemAddEdge;
    
    private final int selfEdgeArrowheadPlacement = 20;

	@FXML
	private void initialize() {
		
		algorithmAnimations = new AlgorithmAnimations(this);
		
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
        
        ObservableList<String> data = FXCollections.observableArrayList("Breadth First Search","Depth First Search","Vertex Cover");
        
        listViewUndirectedNonWeighted.getItems().addAll("Breadth First Search","Depth First Search","Vertex Cover");
        listViewUndirectedWeighted.getItems().addAll("Breadth First Search","Depth First Search","Dijkstra's Algorithm",
        												"Kruskal's Algorithm","Prim's Algorithm","Vertex Cover");
        listViewDirectedNonWeighted.getItems().addAll("Breadth First Search","Depth First Search","Kosaraju's Algorithm");
        listViewDirectedWeighted.getItems().addAll("Breadth First Search","Depth First Search","Dijkstra's Algorithm","Kosaraju's Algorithm");
        
        animationSpeedSlider = new Slider(1, 4, 2);
        
        randomGraphGenerator = new RandomGraphGenerator(this);
        handleCenterPanes();
        handleListViews();
        restartButton.setDisable(true);
		skipToEndButton.setDisable(true);

	}
	
	public void setMain(Main main) {
		this.main = main;
		
		// all data from observable lists to data in this class.
	}
	
	@FXML EventHandler<MouseEvent> mouseDraggedOnVertexEvent() {
		
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
	
	@FXML EventHandler<MouseEvent> mouseReleasedOnVertexEvent() {
		
		
     
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
	private void handleHowToAddRemoveVertices() {
		
		Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Adding and removing vertices.");
        alert.setHeaderText("How to add and remove vertices.");
        alert.setContentText("Adding vertices to screen:\n\nTo add a vertex just click anywhere on the screen, "
        		+ "you will be promted to insert the data you would like the vertex to hold."
        		+ "\n \nOnce you have inputted the data click the \"OK\" button and a vertex with the data you inputted will be added to the graph."
        		+ "\n \nYou can change the data type of the vertex data by using the choice box on the left of the screen."
        		+ "\n \nDeleting vertices from screen:\n\nTo delete vertices all you have to do is double click the vertex you would like to delete.");
		ButtonType okButton = new ButtonType("Ok", ButtonData.OK_DONE);
		alert.getButtonTypes().setAll(okButton);
		alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        Optional<ButtonType> result = alert.showAndWait();
		
	}
	
	@FXML
	private void handleHowToAddRemoveEdges() {
		
		Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Adding and removing edges.");
        alert.setHeaderText("How to add and remove edges.");
        alert.setContentText("Adding edges to screen:\n\nTo add a edge right click the vertex that you would like to be the source of the edge, "
        		+ "you will be promted to insert the vertex you would like to connect an edge to from your chosen source vertex"
        		+ "\n \nIf you are creating"
        		+ " edges for a weighted graph then you will also have to input the weight you would like the edge to have."
        		+ "\n \nOnce you have inputted the data click the \"OK\" button and an edge will be created between your chose vertices."
        		+ "\n \nDeleting edges from screen:\n\nTo delete edges all you have to do is double click the edge you would like to delete.");
		ButtonType okButton = new ButtonType("Ok", ButtonData.OK_DONE);
		alert.getButtonTypes().setAll(okButton);
		alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        Optional<ButtonType> result = alert.showAndWait();
		
		
	}
	
	@FXML
	private void handleHowToVisualiseAlgorithm() {
		
		Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Visualise a graph algorithm");
        alert.setHeaderText("How to visualise a graph algorithm");
        alert.setContentText("To visualise a graph algorithm you first need to have a graph created.\n\n"
        		+ "Once you have a created a graph, you can choose the algorithm you would like to visualise by clicking one of the algorithms "
        		+ "in the list on the left of the screen. \n\n "
        		+ "To start the visualisation of the algorithm press the \"Play\" button at the bottom of the screen. For some algorithms"
        		+ " you may be prompted to input a starting vertex and once inputted the animation will start. \n\n "
        		+ "You can restart the animation by pressing the \"Start Again\" button and you can skip the animation by clicking the "
        		+ "\"Skip to end button\" both of these buttons are located at the bottom of the screen either side of the \"Play\" button.");
		ButtonType okButton = new ButtonType("Ok", ButtonData.OK_DONE);
		alert.getButtonTypes().setAll(okButton);
		alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        Optional<ButtonType> result = alert.showAndWait();
		
	}
	
	@FXML
	private void handleClearGraphButton() {
		
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
 				lastAlgorithmPlayedUndirectedNonWeighted = "";

				dataModel.getListOfUndirectedNonWeightedIntVertices().clear();
				dataModel.getVertexDataUndirectedNonWeightedInt().clear();
				dataModel.getUndirectedNonWeightedInt().clearGraph();

        		dataModel.getListOfUndirectedNonWeightedDoubleVertices().clear();
        		dataModel.getVertexDataUndirectedNonWeightedDouble().clear();
        		dataModel.getUndirectedNonWeightedDouble().clearGraph();

        		dataModel.getListOfUndirectedNonWeightedStringVertices().clear();
        		dataModel.getVertexDataUndirectedNonWeightedString().clear();
        		dataModel.getUndirectedNonWeightedString().clearGraph();

 	         }
 			
 		}
 		
 		else if(getSelectedTabName().equals("Undirected Weighted Graph")) {
 			
 			if(result.get() == okButton) {
 				
 				centerPaneUndirectedWeightedGraph.getChildren().clear();
 				lastAlgorithmPlayedUndirectedWeighted = "";

        		dataModel.getListOfUndirectedWeightedIntVertices().clear();
        		dataModel.getVertexDataUndirectedWeightedInt().clear();
        		dataModel.getUndirectedWeightedInt().clearGraph();

        		dataModel.getListOfUndirectedWeightedDoubleVertices().clear();
        		dataModel.getVertexDataUndirectedWeightedDouble().clear();
        		dataModel.getUndirectedWeightedDouble().clearGraph();

        		dataModel.getListOfUndirectedWeightedStringVertices().clear();
        		dataModel.getVertexDataUndirectedWeightedString().clear();
        		dataModel.getUndirectedWeightedString().clearGraph();

 	         }
 	 			
 	 	}else if(getSelectedTabName().equals("Directed Non-Weighted Graph")) {
 			
 			if(result.get() == okButton) {
 				
 				centerPaneDirectedNonWeightedGraph.getChildren().clear();
 				lastAlgorithmPlayedDirectedNonWeighted = "";

        		dataModel.getListOfDirectedNonWeightedIntVertices().clear();
        		dataModel.getVertexDataDirectedNonWeightedInt().clear();
        		dataModel.getDirectedNonWeightedInt().clearGraph();

        		dataModel.getListOfDirectedNonWeightedDoubleVertices().clear();
        		dataModel.getVertexDataDirectedNonWeightedDouble().clear();
        		dataModel.getDirectedNonWeightedDouble().clearGraph();

        		dataModel.getListOfDirectedNonWeightedStringVertices().clear();
        		dataModel.getVertexDataDirectedNonWeightedString().clear();
        		dataModel.getDirectedNonWeightedString().clearGraph();

 	         }
 		}else if(getSelectedTabName().equals("Directed Weighted Graph")) {
	 			
	 			if(result.get() == okButton) {
	 				
	 				centerPaneDirectedWeightedGraph.getChildren().clear();
	 				lastAlgorithmPlayedDirectedWeighted = "";

 	        		dataModel.getListOfDirectedWeightedIntVertices().clear();
 	        		dataModel.getVertexDataDirectedWeightedInt().clear();
 	        		dataModel.getDirectedWeightedInt().clearGraph();

 	        		dataModel.getListOfDirectedWeightedDoubleVertices().clear();
	        		dataModel.getVertexDataDirectedWeightedDouble().clear();
	        		dataModel.getDirectedWeightedDouble().clearGraph();

 	        		dataModel.getListOfDirectedWeightedStringVertices().clear();
	        		dataModel.getVertexDataDirectedWeightedString().clear();
	        		dataModel.getDirectedWeightedString().clearGraph();
	 	        		 
	 	        	 
	 	         }
	 		}

	}
	
	@SuppressWarnings("unchecked")
	public void handleListViews() {
		
		listViewUndirectedNonWeighted.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			
			public void changed(ObservableValue<? extends String> observable,
		              String oldValue, String newValue) {

				if(listViewUndirectedNonWeighted.getSelectionModel().getSelectedItem().equals(lastAlgorithmPlayedUndirectedNonWeighted)) {
					
					restartButton.setDisable(false);
					skipToEndButton.setDisable(false);
					
				}else {
					
					restartButton.setDisable(true);
					skipToEndButton.setDisable(true);
					
				} 
		    }

	       
	    });
		
		listViewUndirectedWeighted.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			
			public void changed(ObservableValue<? extends String> observable,
		              String oldValue, String newValue) {

				if(listViewUndirectedWeighted.getSelectionModel().getSelectedItem().equals(lastAlgorithmPlayedUndirectedWeighted)) {
					
					restartButton.setDisable(false);
					skipToEndButton.setDisable(false);
					
				}else {
					
					restartButton.setDisable(true);
					skipToEndButton.setDisable(true);
					
				} 
		    }

	       
	    });
		
		listViewDirectedNonWeighted.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			
			public void changed(ObservableValue<? extends String> observable,
		              String oldValue, String newValue) {

				if(listViewDirectedNonWeighted.getSelectionModel().getSelectedItem().equals(lastAlgorithmPlayedDirectedNonWeighted)) {
					
					restartButton.setDisable(false);
					skipToEndButton.setDisable(false);
					
				}else {
					
					restartButton.setDisable(true);
					skipToEndButton.setDisable(true);
					
				} 
		    }

	       
	    });
		
		listViewDirectedWeighted.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			
			public void changed(ObservableValue<? extends String> observable,
		              String oldValue, String newValue) {

				if(listViewDirectedWeighted.getSelectionModel().getSelectedItem().equals(lastAlgorithmPlayedDirectedWeighted)) {
					
					restartButton.setDisable(false);
					skipToEndButton.setDisable(false);
					
				}else {
					
					restartButton.setDisable(true);
					skipToEndButton.setDisable(true);
					
				} 
		    }

	       
	    });
		
	}
	
	@FXML
	private void handleTabs() {
		
		if(getSelectedTabName().equals("Undirected Non-Weighted Graph")) {
			
			if(isUndirectedNonWeightedModified == false) {
				
				restartButton.setDisable(false);
				skipToEndButton.setDisable(false);
				
			}else {
				
				restartButton.setDisable(true);
				skipToEndButton.setDisable(true);
				
			}
			
		}else if(getSelectedTabName().equals("Undirected Weighted Graph")) {
			
			if(isUndirectedWeightedModified == false) {
				
				restartButton.setDisable(false);
				skipToEndButton.setDisable(false);
				
			}else {
				restartButton.setDisable(true);
				skipToEndButton.setDisable(true);
			}
			
		}else if(getSelectedTabName().equals("Directed Non-Weighted Graph")) {
			
			if(isDirectedNonWeightedModified == false) {
				
				restartButton.setDisable(false);
				skipToEndButton.setDisable(false);
				
			}else {
				restartButton.setDisable(true);
				skipToEndButton.setDisable(true);
			}
			
		}else if(getSelectedTabName().equals("Directed Weighted Graph")) {
			
			if(isDirectedWeightedModified == false) {
				
				restartButton.setDisable(false);
				skipToEndButton.setDisable(false);
				
			}else {
				restartButton.setDisable(true);
				skipToEndButton.setDisable(true);
			}
			
		}
		
	}
	
	private void handleCenterPanes() {
			
		getCenterPaneUndirectedNonWeightedGraph().getChildren().addListener(new ListChangeListener<Object>() {

			@Override
			public void onChanged(Change c) {
				
				restartButton.setDisable(true);
				skipToEndButton.setDisable(true);
				isUndirectedNonWeightedModified = true;
				
			}
	       
	    });
		
		getCenterPaneUndirectedWeightedGraph().getChildren().addListener(new ListChangeListener<Object>() {

			@Override
			public void onChanged(Change c) {
				
				restartButton.setDisable(true);
				skipToEndButton.setDisable(true);
				isUndirectedWeightedModified = true;
				
			}
	       
	    });
		
		getCenterPaneDirectedNonWeightedGraph().getChildren().addListener(new ListChangeListener<Object>() {

			@Override
			public void onChanged(Change c) {
				
				restartButton.setDisable(true);
				skipToEndButton.setDisable(true);
				isDirectedNonWeightedModified = true;
				
			}
	       
	    });

		
		getCenterPaneDirectedWeightedGraph().getChildren().addListener(new ListChangeListener<Object>() {

			@Override
			public void onChanged(Change c) {
				
				restartButton.setDisable(true);
				skipToEndButton.setDisable(true);
				isDirectedWeightedModified = true;
				
			}
	       
	    });

		
		
	}
	
	@FXML
	private void handleRandomGraphButton(ActionEvent e) {
		
		Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Random Graph");
        dialog.setHeaderText("Please enter the number of vertices and number of edge you would like.");
        ButtonType cancelButton = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
    	ButtonType okButton = new ButtonType("Ok", ButtonData.OK_DONE);
    	dialog.getDialogPane().getButtonTypes().addAll(okButton, cancelButton);
    	
    	GridPane grid = new GridPane();
    	grid.setHgap(10);
    	grid.setVgap(10);
    	grid.setPadding(new Insets(20, 150, 10, 10));
    	
    	TextField vertex = new TextField();
    	vertex.setPromptText("Number of vertices");
    	TextField weight = new TextField();
    	weight.setPromptText("Number OF edges");
    	
    	grid.add(new Label("Number of vertices:"), 0, 0);
    	grid.add(vertex, 1, 0);
    	grid.add(new Label("Number of edges:"), 0, 1);
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
		
    	if(result.isPresent() && randomGraphGenerator.isInputValid(result.get().getKey(), result.get().getValue())) {
    		randomGraphGenerator.resetGraphs();
    		randomGraphGenerator.createRandomVertices(Integer.parseInt(result.get().getKey()));
    		randomGraphGenerator.createRandomEdges(Integer.parseInt(result.get().getValue()),Integer.parseInt(result.get().getKey()));
    	}
    	
		
		
	}
	
	@FXML
	private void handlePlayButton(ActionEvent e) {
		
		Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("No Graph Algorithm Selected.");
        alert.setHeaderText("No graph algorithm has been selected.");
        alert.setContentText("In order to visualize an animation on a graph you must select one of the algorithms in the list on the left"
        		+ " of the screen and then press the play button.");
		
        Alert alert2 = new Alert(AlertType.ERROR);
        alert2.setTitle("Empty Graph");
        alert2.setHeaderText("The graph is empty.");
        alert2.setContentText("The graph has no vertices and no edges to visualise an algorithm add some vertices and edges first.");
		
			
		if(getSelectedTabName().equals("Undirected Non-Weighted Graph")) {
			
			if(listViewUndirectedNonWeighted.getSelectionModel().getSelectedItem() == null) {
				alert.showAndWait();
			}else if(centerPaneUndirectedNonWeightedGraph.getChildren().size() == 0) {
				alert2.showAndWait();
			}else {
				
				if(listViewUndirectedNonWeighted.getSelectionModel().getSelectedItem().equals("Breadth First Search")) {

					algorithmAnimations.playBreadthFirstSearch();

				}else if(listViewUndirectedNonWeighted.getSelectionModel().getSelectedItem().equals("Depth First Search")) {
					
					algorithmAnimations.playDepthFirstSearch();
					
				}else if(listViewUndirectedNonWeighted.getSelectionModel().getSelectedItem().equals("Vertex Cover")) {
					
					algorithmAnimations.playVertexCover();
					
				}
				
			}
			
		}else if(getSelectedTabName().equals("Undirected Weighted Graph")) {
			
			if(listViewUndirectedWeighted.getSelectionModel().getSelectedItem() == null) {
				alert.showAndWait();
			}else if(centerPaneUndirectedWeightedGraph.getChildren().size() == 0) {
				alert2.showAndWait();
			}else {
				
				if(listViewUndirectedWeighted.getSelectionModel().getSelectedItem().equals("Breadth First Search")) {

					algorithmAnimations.playBreadthFirstSearch();

				}else if(listViewUndirectedWeighted.getSelectionModel().getSelectedItem().equals("Depth First Search")) {
					
					algorithmAnimations.playDepthFirstSearch();
					
				}else if(listViewUndirectedWeighted.getSelectionModel().getSelectedItem().equals("Dijkstra's Algorithm")) {
					
					algorithmAnimations.playDijkstraAlgorithm();
					
				}else if(listViewUndirectedWeighted.getSelectionModel().getSelectedItem().equals("Kruskal's Algorithm")) {
					
					algorithmAnimations.playKruskalAlgorithm();
					
				}else if(listViewUndirectedWeighted.getSelectionModel().getSelectedItem().equals("Prim's Algorithm")) {

					algorithmAnimations.playPrimsAlgorithm();
					
				}else if(listViewUndirectedWeighted.getSelectionModel().getSelectedItem().equals("Vertex Cover")) {

					algorithmAnimations.playVertexCover();
					
				}
				
			}
			
		}else if(getSelectedTabName().equals("Directed Non-Weighted Graph")) {
			
			if(listViewDirectedNonWeighted.getSelectionModel().getSelectedItem() == null) {
				alert.showAndWait();
			}else if(centerPaneDirectedNonWeightedGraph.getChildren().size() == 0) {
				alert2.showAndWait();
			}else {
				
				if(listViewDirectedNonWeighted.getSelectionModel().getSelectedItem().equals("Breadth First Search")) {

					algorithmAnimations.playBreadthFirstSearch();

				}else if(listViewDirectedNonWeighted.getSelectionModel().getSelectedItem().equals("Depth First Search")) {
					
					algorithmAnimations.playDepthFirstSearch();
					
				}else if(listViewDirectedNonWeighted.getSelectionModel().getSelectedItem().equals("Kosaraju's Algorithm")) {
					
					algorithmAnimations.playKosarajuAlgorithm();
					
				}
				
			}
			
		}else if(getSelectedTabName().equals("Directed Weighted Graph")) {
			
			if(listViewDirectedWeighted.getSelectionModel().getSelectedItem() == null) {
				alert.showAndWait();
			}else if(centerPaneDirectedWeightedGraph.getChildren().size() == 0) {
				alert2.showAndWait();
			}else {
				
				if(listViewDirectedWeighted.getSelectionModel().getSelectedItem().equals("Breadth First Search")) {

					algorithmAnimations.playBreadthFirstSearch();

				}else if(listViewDirectedWeighted.getSelectionModel().getSelectedItem().equals("Depth First Search")) {
					
					algorithmAnimations.playDepthFirstSearch();
					
				}else if(listViewDirectedWeighted.getSelectionModel().getSelectedItem().equals("Dijkstra's Algorithm")) {
					
					algorithmAnimations.playDijkstraAlgorithm();
					
				}else if(listViewDirectedWeighted.getSelectionModel().getSelectedItem().equals("Kosaraju's Algorithm")) {
					
					algorithmAnimations.playKosarajuAlgorithm();
					
				}
				
			}
			
		}
		
			
		

	}
	
	@FXML
	private void handleRestartButton(ActionEvent e) {
		
		Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("No Graph Algorithm Selected.");
        alert.setHeaderText("No graph algorithm has been selected.");
        alert.setContentText("In order to visualize an animation on a graph you must select one of the algorithms in the list on the left"
        		+ " of the screen and then press the play button.");
		
        Alert alert2 = new Alert(AlertType.ERROR);
        alert2.setTitle("Empty Graph");
        alert2.setHeaderText("The graph is empty.");
        alert2.setContentText("The graph has no vertices and no edges to visualise an algorithm add some vertices and edges first.");
        
        Alert alert3 = new Alert(AlertType.ERROR);
        alert3.setTitle("Restarting animation before starting animation for newly made/modified graph.");
        alert3.setHeaderText("You cannot restart an animation before starting an animation for a new/modified graph");
        alert3.setContentText("To start an animation for a newly made/modified graph you must press the play button.");
		
		if(getSelectedTabName().equals("Undirected Non-Weighted Graph")) {
			
			if(listViewUndirectedNonWeighted.getSelectionModel().getSelectedItem() == null) {
				alert.showAndWait();
			}else if(centerPaneUndirectedNonWeightedGraph.getChildren().size() == 0) {
				alert2.showAndWait();
			}else {
				
				try {
					
					if(listViewUndirectedNonWeighted.getSelectionModel().getSelectedItem().equals("Breadth First Search")) {
						
						algorithmAnimations.restartBreadthFirstSearch();
						
					}else if(listViewUndirectedNonWeighted.getSelectionModel().getSelectedItem().equals("Depth First Search")) {
						
						algorithmAnimations.restartDepthFirstSearch();
						
					}else if(listViewUndirectedNonWeighted.getSelectionModel().getSelectedItem().equals("Vertex Cover")) {
						
						algorithmAnimations.restartVertexCover();
						
					}
					
				}catch(NumberFormatException nfe) {
					
					alert3.showAndWait();
					
				}catch(IllegalArgumentException ile) {
					
					alert3.showAndWait();
					
				}
			}
			
		}else if(getSelectedTabName().equals("Undirected Weighted Graph")) {
			
			if(listViewUndirectedWeighted.getSelectionModel().getSelectedItem() == null) {
				alert.showAndWait();
			}else if(centerPaneUndirectedWeightedGraph.getChildren().size() == 0) {
				alert2.showAndWait();
			}else {
			
				if(listViewUndirectedWeighted.getSelectionModel().getSelectedItem().equals("Breadth First Search")) {
					
					algorithmAnimations.restartBreadthFirstSearch();
					
				}else if(listViewUndirectedWeighted.getSelectionModel().getSelectedItem().equals("Depth First Search")) {
					
					algorithmAnimations.restartDepthFirstSearch();
					
				}else if(listViewUndirectedWeighted.getSelectionModel().getSelectedItem().equals("Dijkstra's Algorithm")) {
					
					algorithmAnimations.restartDijkstraAlgorithm();
					
				}else if(listViewUndirectedWeighted.getSelectionModel().getSelectedItem().equals("Kruskal's Algorithm")) {
					
					algorithmAnimations.restartKruskalAlgorithm();
					
				}else if(listViewUndirectedWeighted.getSelectionModel().getSelectedItem().equals("Prim's Algorithm")) {
					
					algorithmAnimations.restartPrimsAlgorithm();
					
				}else if(listViewUndirectedWeighted.getSelectionModel().getSelectedItem().equals("Vertex Cover")) {
					
					algorithmAnimations.restartVertexCover();
					
				}
			}
			
		}else if(getSelectedTabName().equals("Directed Non-Weighted Graph")) {
			
			if(listViewDirectedNonWeighted.getSelectionModel().getSelectedItem() == null) {
				alert.showAndWait();
			}else if(centerPaneDirectedNonWeightedGraph.getChildren().size() == 0) {
				alert2.showAndWait();
			}else {
			
				if(listViewDirectedNonWeighted.getSelectionModel().getSelectedItem().equals("Breadth First Search")) {
					
					algorithmAnimations.restartBreadthFirstSearch();
					
				}else if(listViewDirectedNonWeighted.getSelectionModel().getSelectedItem().equals("Depth First Search")) {
					
					algorithmAnimations.restartDepthFirstSearch();
					
				}else if(listViewDirectedNonWeighted.getSelectionModel().getSelectedItem().equals("Kosaraju's Algorithm")) {
					
					algorithmAnimations.restartKosarajuAlgorithm();
					
				}
			}
			
		}else if(getSelectedTabName().equals("Directed Weighted Graph")) {
			
			if(listViewDirectedWeighted.getSelectionModel().getSelectedItem() == null) {
				alert.showAndWait();
			}else if(centerPaneDirectedWeightedGraph.getChildren().size() == 0) {
				alert2.showAndWait();
			}else {
			
				if(listViewDirectedWeighted.getSelectionModel().getSelectedItem().equals("Breadth First Search")) {
					
					algorithmAnimations.restartBreadthFirstSearch();
					
				}else if(listViewDirectedWeighted.getSelectionModel().getSelectedItem().equals("Depth First Search")) {
					
					algorithmAnimations.restartDepthFirstSearch();
					
				}else if(listViewDirectedWeighted.getSelectionModel().getSelectedItem().equals("Dijkstra's Algorithm")) {
					
					algorithmAnimations.restartDijkstraAlgorithm();
					
				}else if(listViewDirectedWeighted.getSelectionModel().getSelectedItem().equals("Kosaraju's Algorithm")) {
					
					algorithmAnimations.restartKosarajuAlgorithm();
					
				}
			}
			
		}
		
	}

	@FXML
	private void handleSkipToEndButton(ActionEvent e) {
		
		Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("No Graph Algorithm Selected.");
        alert.setHeaderText("No graph algorithm has been selected.");
        alert.setContentText("In order to visualize an animation on a graph you must select one of the algorithms in the list on the left"
        		+ " of the screen and then press the play button.");
		
        Alert alert2 = new Alert(AlertType.ERROR);
        alert2.setTitle("Empty Graph");
        alert2.setHeaderText("The graph is empty.");
        alert2.setContentText("The graph has no vertices and no edges to visualise an algorithm add some vertices and edges first.");
		
		if(getSelectedTabName().equals("Undirected Non-Weighted Graph")) {
			
			if(listViewUndirectedNonWeighted.getSelectionModel().getSelectedItem() == null) {
				alert.showAndWait();
			}else if(centerPaneUndirectedNonWeightedGraph.getChildren().size() == 0) {
				alert2.showAndWait();
			}else {
			
				if(listViewUndirectedNonWeighted.getSelectionModel().getSelectedItem().equals("Breadth First Search")) {
	
					algorithmAnimations.skipBreadthFirstSearchToEnd();
					
				}else if(listViewUndirectedNonWeighted.getSelectionModel().getSelectedItem().equals("Depth First Search")) {
					
					algorithmAnimations.skipDepthFirstSearchToEnd();
					
				}else if(listViewUndirectedNonWeighted.getSelectionModel().getSelectedItem().equals("Vertex Cover")) {
					
					algorithmAnimations.skipVertexCoverToEnd();
					
				}
			}
			
		}else if(getSelectedTabName().equals("Undirected Weighted Graph")) {
			
			if(listViewUndirectedWeighted.getSelectionModel().getSelectedItem() == null) {
				alert.showAndWait();
			}else if(centerPaneUndirectedWeightedGraph.getChildren().size() == 0) {
				alert2.showAndWait();
			}else {
			
				if(listViewUndirectedWeighted.getSelectionModel().getSelectedItem().equals("Breadth First Search")) {
					
					algorithmAnimations.skipBreadthFirstSearchToEnd();
					
				}else if(listViewUndirectedWeighted.getSelectionModel().getSelectedItem().equals("Depth First Search")) {
					
					algorithmAnimations.skipDepthFirstSearchToEnd();
					
				}else if(listViewUndirectedWeighted.getSelectionModel().getSelectedItem().equals("Dijkstra's Algorithm")) {
					
					algorithmAnimations.skipDijkstraAlgorithmToEnd();
					
				}else if(listViewUndirectedWeighted.getSelectionModel().getSelectedItem().equals("Kruskal's Algorithm")) {
					
					algorithmAnimations.skipKruskalAlgorithmToEnd();
					
				}else if(listViewUndirectedWeighted.getSelectionModel().getSelectedItem().equals("Prim's Algorithm")) {
					
					algorithmAnimations.skipPrimsAlgorithmToEnd();
					
				}else if(listViewUndirectedWeighted.getSelectionModel().getSelectedItem().equals("Vertex Cover")) {
					
					algorithmAnimations.skipVertexCoverToEnd();
					
				}
			}
			
		}else if(getSelectedTabName().equals("Directed Non-Weighted Graph")) {
			
			if(listViewDirectedNonWeighted.getSelectionModel().getSelectedItem() == null) {
				alert.showAndWait();
			}else if(centerPaneDirectedNonWeightedGraph.getChildren().size() == 0) {
				alert2.showAndWait();
			}else {
			
				if(listViewDirectedNonWeighted.getSelectionModel().getSelectedItem().equals("Breadth First Search")) {
					
					algorithmAnimations.skipBreadthFirstSearchToEnd();
					
				}else if(listViewDirectedNonWeighted.getSelectionModel().getSelectedItem().equals("Depth First Search")) {
					
					algorithmAnimations.skipDepthFirstSearchToEnd();
					
				}else if(listViewDirectedNonWeighted.getSelectionModel().getSelectedItem().equals("Kosaraju's Algorithm")) {
					
					algorithmAnimations.skipKosarajuAlgorithmToEnd();
					
				}
			}
			
		}else if(getSelectedTabName().equals("Directed Weighted Graph")) {
			
			if(listViewDirectedWeighted.getSelectionModel().getSelectedItem() == null) {
				alert.showAndWait();
			}else if(centerPaneDirectedWeightedGraph.getChildren().size() == 0) {
				alert2.showAndWait();
			}else {
			
				if(listViewDirectedWeighted.getSelectionModel().getSelectedItem().equals("Breadth First Search")) {
					
					algorithmAnimations.skipBreadthFirstSearchToEnd();
					
				}else if(listViewDirectedWeighted.getSelectionModel().getSelectedItem().equals("Depth First Search")) {
					
					algorithmAnimations.skipDepthFirstSearchToEnd();
					
				}else if(listViewDirectedWeighted.getSelectionModel().getSelectedItem().equals("Dijkstra's Algorithm")) {
					
					algorithmAnimations.skipDijkstraAlgorithmToEnd();
					
				}else if(listViewDirectedWeighted.getSelectionModel().getSelectedItem().equals("Kosaraju's Algorithm")) {
					
					algorithmAnimations.skipKosarajuAlgorithmToEnd();
					
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
 				lastAlgorithmPlayedUndirectedNonWeighted = "";
 				
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
 				lastAlgorithmPlayedUndirectedWeighted = "";
 				
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
 				lastAlgorithmPlayedDirectedNonWeighted = "";
 				
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
	 				lastAlgorithmPlayedDirectedWeighted = "";
	 				
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
	
	public TabPane getTabs() {
		return tabs;
	}
	
	public Slider getAnimationSpeedSlider() {
		return animationSpeedSlider;
	}
	
	public TextArea getOutputBox() {
		return outputBox;
	}

	/**
	 * @return the lastAlgorithmPlayedUndirectedNonWeighted
	 */
	public String getLastAlgorithmPlayedUndirectedNonWeighted() {
		return lastAlgorithmPlayedUndirectedNonWeighted;
	}

	/**
	 * @return the lastAlgorithmPlayedUndirectedWeighted
	 */
	public String getLastAlgorithmPlayedUndirectedWeighted() {
		return lastAlgorithmPlayedUndirectedWeighted;
	}

	/**
	 * @return the lastAlgorithmPlayedDirectedNonWeighted
	 */
	public String getLastAlgorithmPlayedDirectedNonWeighted() {
		return lastAlgorithmPlayedDirectedNonWeighted;
	}

	/**
	 * @return the lastAlgorithmPlayedDirectedWeighted
	 */
	public String getLastAlgorithmPlayedDirectedWeighted() {
		return lastAlgorithmPlayedDirectedWeighted;
	}

	/**
	 * @param lastAlgorithmPlayedUndirectedNonWeighted the lastAlgorithmPlayedUndirectedNonWeighted to set
	 */
	public void setLastAlgorithmPlayedUndirectedNonWeighted(String lastAlgorithmPlayedUndirectedNonWeighted) {
		this.lastAlgorithmPlayedUndirectedNonWeighted = lastAlgorithmPlayedUndirectedNonWeighted;
	}

	/**
	 * @param lastAlgorithmPlayedUndirectedWeighted the lastAlgorithmPlayedUndirectedWeighted to set
	 */
	public void setLastAlgorithmPlayedUndirectedWeighted(String lastAlgorithmPlayedUndirectedWeighted) {
		this.lastAlgorithmPlayedUndirectedWeighted = lastAlgorithmPlayedUndirectedWeighted;
	}

	/**
	 * @param lastAlgorithmPlayedDirectedNonWeighted the lastAlgorithmPlayedDirectedNonWeighted to set
	 */
	public void setLastAlgorithmPlayedDirectedNonWeighted(String lastAlgorithmPlayedDirectedNonWeighted) {
		this.lastAlgorithmPlayedDirectedNonWeighted = lastAlgorithmPlayedDirectedNonWeighted;
	}

	/**
	 * @param lastAlgorithmPlayedDirectedWeighted the lastAlgorithmPlayedDirectedWeighted to set
	 */
	public void setLastAlgorithmPlayedDirectedWeighted(String lastAlgorithmPlayedDirectedWeighted) {
		this.lastAlgorithmPlayedDirectedWeighted = lastAlgorithmPlayedDirectedWeighted;
	}

	/**
	 * @return the listViewUndirectedNonWeighted
	 */
	public ListView<String> getListViewUndirectedNonWeighted() {
		return listViewUndirectedNonWeighted;
	}

	/**
	 * @return the listViewUndirectedWeighted
	 */
	public ListView<String> getListViewUndirectedWeighted() {
		return listViewUndirectedWeighted;
	}

	/**
	 * @return the listViewDirectedNonWeighted
	 */
	public ListView<String> getListViewDirectedNonWeighted() {
		return listViewDirectedNonWeighted;
	}

	/**
	 * @return the listViewDirectedWeighted
	 */
	public ListView<String> getListViewDirectedWeighted() {
		return listViewDirectedWeighted;
	}

	/**
	 * @param listViewUndirectedNonWeighted the listViewUndirectedNonWeighted to set
	 */
	public void setListViewUndirectedNonWeighted(ListView<String> listViewUndirectedNonWeighted) {
		this.listViewUndirectedNonWeighted = listViewUndirectedNonWeighted;
	}

	/**
	 * @param listViewUndirectedWeighted the listViewUndirectedWeighted to set
	 */
	public void setListViewUndirectedWeighted(ListView<String> listViewUndirectedWeighted) {
		this.listViewUndirectedWeighted = listViewUndirectedWeighted;
	}

	/**
	 * @param listViewDirectedNonWeighted the listViewDirectedNonWeighted to set
	 */
	public void setListViewDirectedNonWeighted(ListView<String> listViewDirectedNonWeighted) {
		this.listViewDirectedNonWeighted = listViewDirectedNonWeighted;
	}

	/**
	 * @param listViewDirectedWeighted the listViewDirectedWeighted to set
	 */
	public void setListViewDirectedWeighted(ListView<String> listViewDirectedWeighted) {
		this.listViewDirectedWeighted = listViewDirectedWeighted;
	}

	/**
	 * @return the restartButton
	 */
	public Button getRestartButton() {
		return restartButton;
	}

	/**
	 * @return the skipToEndButton
	 */
	public Button getSkipToEndButton() {
		return skipToEndButton;
	}

	/**
	 * @param restartButton the restartButton to set
	 */
	public void setRestartButton(Button restartButton) {
		this.restartButton = restartButton;
	}

	/**
	 * @param skipToEndButton the skipToEndButton to set
	 */
	public void setSkipToEndButton(Button skipToEndButton) {
		this.skipToEndButton = skipToEndButton;
	}

	/**
	 * @return the isUndirectedNonWeightedModified
	 */
	public boolean isUndirectedNonWeightedModified() {
		return isUndirectedNonWeightedModified;
	}

	/**
	 * @return the isUndirectedWeightedModified
	 */
	public boolean isUndirectedWeightedModified() {
		return isUndirectedWeightedModified;
	}

	/**
	 * @return the isDirectedNonWeightedModified
	 */
	public boolean isDirectedNonWeightedModified() {
		return isDirectedNonWeightedModified;
	}

	/**
	 * @return the isDirectedWeightedModified
	 */
	public boolean isDirectedWeightedModified() {
		return isDirectedWeightedModified;
	}

	/**
	 * @param isUndirectedNonWeightedModified the isUndirectedNonWeightedModified to set
	 */
	public void setUndirectedNonWeightedModified(boolean isUndirectedNonWeightedModified) {
		this.isUndirectedNonWeightedModified = isUndirectedNonWeightedModified;
	}

	/**
	 * @param isUndirectedWeightedModified the isUndirectedWeightedModified to set
	 */
	public void setUndirectedWeightedModified(boolean isUndirectedWeightedModified) {
		this.isUndirectedWeightedModified = isUndirectedWeightedModified;
	}

	/**
	 * @param isDirectedNonWeightedModified the isDirectedNonWeightedModified to set
	 */
	public void setDirectedNonWeightedModified(boolean isDirectedNonWeightedModified) {
		this.isDirectedNonWeightedModified = isDirectedNonWeightedModified;
	}

	/**
	 * @param isDirectedWeightedModified the isDirectedWeightedModified to set
	 */
	public void setDirectedWeightedModified(boolean isDirectedWeightedModified) {
		this.isDirectedWeightedModified = isDirectedWeightedModified;
	}

}
