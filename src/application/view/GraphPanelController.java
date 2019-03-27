package application.view;

import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;

import javax.swing.event.ChangeEvent;
import javafx.beans.value.ChangeListener;

import application.Main;
import application.model.DataModel;
import application.model.Vertex;
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
import javafx.scene.Node;
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

/**
 * This class is the controller class for the main GUI screen where you create the graphs and visualise algorithms (GraphPanel.fxml view).
 * @author jamansalique
 *
 */
public class GraphPanelController {

	private Main main;
	
	private DataModel dataModel;
	private ClickedOnEdgeHandler clickedOnEdgeHandler;
	private RandomGraphGenerator randomGraphGenerator;
	private AlgorithmAnimations algorithmAnimations;
	private Animations animations;
	
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
	
	@FXML 
	private MenuItem howToCreateOwnAlgorithm;
	
	private StackPane currentStackPane;

	private double orgSceneX, orgSceneY;
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

    /**
     * This method is called when the GraphPanel.fxml file is first loaded. This method acts like a constructor in normal java classes.
     * In this method I initialise gui components and fields, as well as adding handlers to specific components.
     */
	@FXML
	private void initialize() {
		
		algorithmAnimations = new AlgorithmAnimations(this);
		
		animations = new Animations(this);
		
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
        
        listViewUndirectedNonWeighted.getItems().addAll("Breadth First Search","Depth First Search","Vertex Cover","My Algorithm");
        listViewUndirectedWeighted.getItems().addAll("Breadth First Search","Depth First Search","Dijkstra's Algorithm",
        												"Kruskal's Algorithm","Prim's Algorithm","Vertex Cover","My Algorithm");
        listViewDirectedNonWeighted.getItems().addAll("Breadth First Search","Depth First Search","Kosaraju's Algorithm","My Algorithm");
        listViewDirectedWeighted.getItems().addAll("Breadth First Search","Depth First Search","Dijkstra's Algorithm","Kosaraju's Algorithm","My Algorithm");
        
        animationSpeedSlider = new Slider();

        randomGraphGenerator = new RandomGraphGenerator(this);
        handleCenterPanes();
        handleListViews();
        restartButton.setDisable(true);
		skipToEndButton.setDisable(true);

	}
	
	public void setMain(Main main) {
		this.main = main;
	}
	
	/**
	 * This method creates and returns an event handler for when a user drags a vertex.
	 * @return
	 */
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
		                // If dragNode bounds are outside parent bounds,ALWAYS setting the translate value that fits your node at end.
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
	
	/**
	 * This method creates and returns an event handler when a user releases the mouse on a vertex. When the user releases the mouse on the vertex the 
	 * translate x and y is reset back to 0, and the x and y coordinate of the vertex is set. 
	 * @return
	 */
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
	
	 /**
	  * This method is called when the choice box on the Undirected Non-Weighted Graph tab is clicked.
	  * @param e
	  */
	@FXML
	private void selectionChoiceUndirectedNonWeightedGraph(MouseEvent e) {
		preSelectionChoiceUndirectedNonWeightedGraph = getSelectedDataChoiceUndirectedNonWeightedGraph();
	}
	
	 /** This method is called when the choice box on the Undirected Weighted Graph tab is clicked.
	  * @param e
	  */
	@FXML
	private void selectionChoiceUndirectedWeightedGraph(MouseEvent e) {
		preSelectionChoiceUndirectedWeightedGraph = getSelectedDataChoiceUndirectedWeightedGraph();
	}
	
	/** This method is called when the choice box on the Directed Non-Weighted Graph tab is clicked.
	  * @param e
	  */
	@FXML
	private void selectionChoiceDirectedNonWeightedGraph(MouseEvent e) {
		preSelectionChoiceDirectedNonWeightedGraph = getSelectedDataChoiceDirectedNonWeightedGraph();
	}
	
	/** This method is called when the choice box on the Directed Weighted Graph tab is clicked.
	  * @param e
	  */
	@FXML
	private void selectionChoiceDirectedWeightedGraph(MouseEvent e) {
		preSelectionChoiceDirectedWeightedGraph = getSelectedDataChoiceDirectedWeightedGraph();
	}
	
	/**
	 * This method is called when the 'How to add/delete vertices' menu item is clicked. This method creates an alert box informing the user
	 * how to add/delete vertices on the gui.
	 */
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
		alert.getDialogPane().getStylesheets().add("/application/global.css");
        Optional<ButtonType> result = alert.showAndWait();
		
	}
	
	/**
	 * This method is called when the 'How to add/delete edges' menu item is clicked. This method creates an alert box informing the user
	 * how to add/delete edges on the gui.
	 */
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
		alert.getDialogPane().getStylesheets().add("/application/global.css");
        Optional<ButtonType> result = alert.showAndWait();
		
	}
	
	/**
	 * This method is called when the 'How to visualise an algorithm' menu item is clicked. This method creates an alert box informing the user
	 * how to visualise an algorithm on the gui.
	 */
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
		alert.getDialogPane().getStylesheets().add("/application/global.css");
        Optional<ButtonType> result = alert.showAndWait();
		
	}
	
	/**
	 * This method is called when the 'How to create your own algorithm to be visualised' menu item is clicked. This method creates an alert box 
	 * informing the user how to create their own algorithm and then visualise that algorithm on the gui.
	 */
	@FXML
	private void handleHowToCreateOwnAlgorithm() {
		
		Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Create your own graph algorithm");
        alert.setHeaderText("How to create your own algorithm to be visualised.");
        alert.setContentText("1.\tFirstly, go to the MyAlgorithm.java class. This is the only file you will need to edit to implement your own graph algorithm to be visualised."
        						+ "\r\n2.\tThere are 4 main methods:\r\n\t\u2022performMyAlgorithmUndirectedNonWeighted"
        						+ "(\u2026) \u2013 holds the algorithm to be visualized on the Undirected Non-Weighted Graph tab."
        						+ "\r\n\t\u2022performMyAlgorithmUndirectedWeighted(\u2026) \u2013 holds the algorithm to be visualized on the Undirected Weighted Graph tab."
        						+ "\r\n\t\u2022performMyAlgorithmDirectedNonWeighted(\u2026) \u2013 holds the algorithm to be visualized on the Directed Non-Weighted Graph tab."
        						+ "\r\n\t\u2022performMyAlgorithmDirectedWeighted(\u2026) \u2013 holds the algorithm to be visualized on the Directed Weighted Graph tab."
        						+ "\r\n3.\tWrite you algorithm in these 4 methods depending on what type of graph you want the algorithm to be visualized on (There is a sample Breadth first search algorithm in each method which you can use as a guideline to help construct your algorithm)."
        						+ "\r\n4.\tOnce you have implemented your algorithm you can play your algorithm by:"
        						+ "\r\n\t\u2022First run the application.\r\n\t\u2022"
        						+ "Next go to the graph tab you want to visualize your algorithm on."
        						+ "\r\n\t\u2022Create a graph if you have not already.\r\n\t\u2022"
        						+ "Click the \u2018My Algorithm\u2019 item in the list view on the left of the GUI."
        						+ "\r\n\t\u2022Then press the play button.\r\n");
		ButtonType okButton = new ButtonType("Ok", ButtonData.OK_DONE);
		alert.getButtonTypes().setAll(okButton);
		alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
		alert.getDialogPane().getStylesheets().add("/application/global.css");
        Optional<ButtonType> result = alert.showAndWait();
		
	}
	
	/**
	 * This method is called when the user clicks the clear graph button. When called this method will clear the graph on the gui and update the model
	 * accordingly.
	 */
	@FXML
	private void handleClearGraphButton() {
		
		Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Clearing graph warning.");
        alert.setHeaderText("Are you sure you want to clear the graph?");
        alert.setContentText("If you click OK the current graph you created will be cleared and you cannot retrieve it back.");
        ButtonType cancelButton = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
		ButtonType okButton = new ButtonType("Ok", ButtonData.OK_DONE);
		alert.getButtonTypes().setAll(okButton,cancelButton);
		alert.getDialogPane().getStylesheets().add("/application/global.css");
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
	
	/**
	 * This method adds a change listener to the list views (list of algorithms that can be played on graph) for all 4 graph tabs on the gui.
	 * This method prevents the user from restarting/skipping an algorithm that has not been played.
	 */
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
	
	/**
	 * This method is called when a user clicks on a tab. When called this method will check if the graph on the tab they are on has been modified.
	 * If the graph has been modified then I disable the restart and skip to end buttons to prevent an error replaying an animation on a modified graph.
	 * The user must click play on a newly modified graph before they can click restart and skip to end.
	 */
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
	
	/**
	 * This method adds a change listener to the Pane components (The pane is where the user creates the graphs and contains all graph vertices and edges)
	 * in each tab. This is so I can monitor when a graph is modified or not. If the number of components decrease or increase in the Pane this
	 * means the graph has been modified and so I disable the restart and skip to end buttons to prevent an error replaying an animation on a 
	 * modified graph. I also set the isUndirectedNonWeightedModified to true if the undirected non weighted graph was modified, I do the same
	 * for the other graph panes. Also the colours of vertices and edges are set back to its default colours and the output box is cleared.
	 */
	private void handleCenterPanes() {
			
		getCenterPaneUndirectedNonWeightedGraph().getChildren().addListener(new ListChangeListener<Object>() {

			@Override
			public void onChanged(Change c) {
				
				restartButton.setDisable(true);
				skipToEndButton.setDisable(true);
				isUndirectedNonWeightedModified = true;
				outputBox.clear();
				animations.resetGraphColours("Undirected Non Weighted");
				
			}
	       
	    });
		
		getCenterPaneUndirectedWeightedGraph().getChildren().addListener(new ListChangeListener<Object>() {

			@Override
			public void onChanged(Change c) {
				
				restartButton.setDisable(true);
				skipToEndButton.setDisable(true);
				isUndirectedWeightedModified = true;
				outputBox.clear();
				animations.resetGraphColours("Undirected Weighted");
				
			}
	       
	    });
		
		getCenterPaneDirectedNonWeightedGraph().getChildren().addListener(new ListChangeListener<Object>() {

			@Override
			public void onChanged(Change c) {
				
				restartButton.setDisable(true);
				skipToEndButton.setDisable(true);
				isDirectedNonWeightedModified = true;
				outputBox.clear();
				animations.resetGraphColours("Directed Non Weighted");
				
			}
	       
	    });

		
		getCenterPaneDirectedWeightedGraph().getChildren().addListener(new ListChangeListener<Object>() {

			@Override
			public void onChanged(Change c) {
				
				restartButton.setDisable(true);
				skipToEndButton.setDisable(true);
				isDirectedWeightedModified = true;
				outputBox.clear();
				animations.resetGraphColours("Directed Weighted");
				
			}
	       
	    });

		
		
	}
	
	/**
	 * This method is called when the create random graph button is clicked. This method first prompts the user to input the number of vertices and 
	 * number of edges they would like in their graph. When the user presses OK the data they inputed is validated using the isInputValid() method in
	 * the RandomGraphGenerator class. If the data is correct then the random graph is created.
	 */
	
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
    	weight.setPromptText("Number of edges");
    	
    	grid.add(new Label("Number of vertices:"), 0, 0);
    	grid.add(vertex, 1, 0);
    	grid.add(new Label("Number of edges:"), 0, 1);
    	grid.add(weight, 1, 1);
        
    	dialog.getDialogPane().setContent(grid);
    	
    	dialog.setResultConverter(dialogButton -> {
    	    if (dialogButton == okButton) {
    	        return new Pair<>(vertex.getText(), weight.getText());
    	    }
    	    return null;
    	});
    	
    	dialog.getDialogPane().getStylesheets().add("/application/global.css");
    	
    	Optional<Pair<String, String>> result = dialog.showAndWait();
		
    	if(result.isPresent() && randomGraphGenerator.isInputValid(result.get().getKey(), result.get().getValue())) {
    		randomGraphGenerator.resetGraphs();
    		randomGraphGenerator.createRandomVertices(Integer.parseInt(result.get().getKey()));
    		randomGraphGenerator.createRandomEdges(Integer.parseInt(result.get().getValue()),Integer.parseInt(result.get().getKey()));
    	}
    	
		
		
	}
	
	/**
	 * This method is called when the play button is clicked. This method checks what algorithm has been selected (in the list view component). If the 
	 * algorithm requires a starting vertex then the user is prompted to input a starting vertex. If the algorithm doesn't require a starting vertex then
	 * the algorithm animation starts straight away. For algorithms that do require a starting vertex the algorithm animation starts as soon as the user
	 * inputs a starting vertex. If the graph is empty or an algorithm has not been selected then an error message is shown explaining there error.
	 * @param e
	 */
	@FXML
	private void handlePlayButton(ActionEvent e) {
		
		Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("No Graph Algorithm Selected.");
        alert.setHeaderText("No graph algorithm has been selected.");
        alert.setContentText("In order to visualize an animation on a graph you must select one of the algorithms in the list on the left"
        		+ " of the screen and then press the play button.");
        alert.getDialogPane().getStylesheets().add("/application/global.css");
		
        Alert alert2 = new Alert(AlertType.ERROR);
        alert2.setTitle("Empty Graph");
        alert2.setHeaderText("The graph is empty.");
        alert2.setContentText("The graph has no vertices and no edges, to visualise an algorithm add some vertices and edges to your graph first.");
        alert2.getDialogPane().getStylesheets().add("/application/global.css");
		
			
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
					
				}else if(listViewUndirectedNonWeighted.getSelectionModel().getSelectedItem().equals("My Algorithm")) {
					
					algorithmAnimations.playMyAlgorithm();
					
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

					if(hasNegativeEdgesUndirectedWeighted()) {
						
						Alert alert3 = new Alert(AlertType.ERROR);
						alert3.setTitle("Negative Weighted Edges");
						alert3.setHeaderText("The graph contains at least one negative weighted edge.");
						alert3.setContentText("Dijkstra's shortest path algorithm cannot be performed on graphs that contain negative weighted edges.");
						alert3.getDialogPane().getStylesheets().add("/application/global.css");
						alert3.showAndWait();
				        
						
					}else {
						
						algorithmAnimations.playDijkstraAlgorithm();
						
					}

				}else if(listViewUndirectedWeighted.getSelectionModel().getSelectedItem().equals("Kruskal's Algorithm")) {
					
					algorithmAnimations.playKruskalAlgorithm();
					
				}else if(listViewUndirectedWeighted.getSelectionModel().getSelectedItem().equals("Prim's Algorithm")) {

					algorithmAnimations.playPrimsAlgorithm();
					
				}else if(listViewUndirectedWeighted.getSelectionModel().getSelectedItem().equals("Vertex Cover")) {

					algorithmAnimations.playVertexCover();
					
				}else if(listViewUndirectedWeighted.getSelectionModel().getSelectedItem().equals("My Algorithm")) {
					
					algorithmAnimations.playMyAlgorithm();
					
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
					
				}else if(listViewDirectedNonWeighted.getSelectionModel().getSelectedItem().equals("My Algorithm")) {
					
					algorithmAnimations.playMyAlgorithm();
					
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
					
					if(hasNegativeEdgesDirectedWeighted()) {
						
						Alert alert3 = new Alert(AlertType.ERROR);
						alert3.setTitle("Negative Weighted Edges");
						alert3.setHeaderText("The graph contains at least one negative weighted edge.");
						alert3.setContentText("Dijkstra's shortest path algorithm cannot be performed on graphs that contain negative weighted edges.");
						alert3.getDialogPane().getStylesheets().add("/application/global.css");
						alert3.showAndWait();
				        
						
					}else {
						
						algorithmAnimations.playDijkstraAlgorithm();
						
					}
					
				}else if(listViewDirectedWeighted.getSelectionModel().getSelectedItem().equals("Kosaraju's Algorithm")) {
					
					algorithmAnimations.playKosarajuAlgorithm();
					
				}else if(listViewDirectedWeighted.getSelectionModel().getSelectedItem().equals("My Algorithm")) {
					
					algorithmAnimations.playMyAlgorithm();
					
				}
				
			}
			
		}
		
			
		

	}
	
	/**
	 * This method is called when the user clicks the restart button. When called we restart the algorithm that was just played by the user for 
	 * the specific graph they are viewing. Once again if the graph is empty or an algorithm has not been selected then an error message is shown 
	 * explaining there error.
	 * @param e
	 */
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
        alert2.setContentText("The graph has no vertices and no edges, to visualise an algorithm add some vertices and edges to your graph first.");
        
        Alert alert3 = new Alert(AlertType.ERROR);
        alert3.setTitle("Restart Visualisation Error.");
        alert3.setHeaderText("You cannot restart an animation before starting an animation for a new/modified graph");
        alert3.setContentText("To start an animation for a newly made/modified graph you must press the play button.");
		
		if(getSelectedTabName().equals("Undirected Non-Weighted Graph")) {
			
			if(listViewUndirectedNonWeighted.getSelectionModel().getSelectedItem() == null) {
				alert.showAndWait();
			}else if(centerPaneUndirectedNonWeightedGraph.getChildren().size() == 0) {
				alert2.showAndWait();
			}else {

				if(listViewUndirectedNonWeighted.getSelectionModel().getSelectedItem().equals("Breadth First Search")) {
					
					algorithmAnimations.restartBreadthFirstSearch();
					
				}else if(listViewUndirectedNonWeighted.getSelectionModel().getSelectedItem().equals("Depth First Search")) {
					
					algorithmAnimations.restartDepthFirstSearch();
					
				}else if(listViewUndirectedNonWeighted.getSelectionModel().getSelectedItem().equals("Vertex Cover")) {
					
					algorithmAnimations.restartVertexCover();
					
				}else if(listViewUndirectedNonWeighted.getSelectionModel().getSelectedItem().equals("My Algorithm")) {
					
					algorithmAnimations.restartMyAlgorithm();
					
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
					
				}else if(listViewUndirectedWeighted.getSelectionModel().getSelectedItem().equals("My Algorithm")) {
					
					algorithmAnimations.restartMyAlgorithm();
					
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
					
				}else if(listViewDirectedNonWeighted.getSelectionModel().getSelectedItem().equals("My Algorithm")) {
					
					algorithmAnimations.restartMyAlgorithm();
					
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
					
				}else if(listViewDirectedWeighted.getSelectionModel().getSelectedItem().equals("My Algorithm")) {
					
					algorithmAnimations.restartMyAlgorithm();
					
				}
			}
			
		}
		
	}

	/**
	 * This method is called when the user clicks the skip to end button. When called we skip the animation of the algorithm and just show the end 
	 * output of the animation. Once again if the graph is empty or an algorithm has not been selected then an error message is shown 
	 * explaining there error.
	 * @param e
	 */
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
        alert2.setContentText("The graph has no vertices and no edges, to visualise an algorithm add some vertices and edges to your graph first.");
		
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
					
				}else if(listViewUndirectedNonWeighted.getSelectionModel().getSelectedItem().equals("My Algorithm")) {
					
					algorithmAnimations.skipMyAlgorithmToEnd();
					
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
					
				}else if(listViewUndirectedWeighted.getSelectionModel().getSelectedItem().equals("My Algorithm")) {
					
					algorithmAnimations.skipMyAlgorithmToEnd();
					
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
					
				}else if(listViewDirectedNonWeighted.getSelectionModel().getSelectedItem().equals("My Algorithm")) {
					
					algorithmAnimations.skipMyAlgorithmToEnd();
					
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
					
				}else if(listViewDirectedWeighted.getSelectionModel().getSelectedItem().equals("My Algorithm")) {
					
					algorithmAnimations.skipMyAlgorithmToEnd();
					
				}
			}
			
		}
		
	}
	
	/**
	 * This is called when the user tries to change the vertex data type for the graph using the choice box on the gui. If the user tries to change 
	 * the vertex data they receive a warning message informing the user that the current graph on the screen will be cleared. If they confirm then
	 * the graph is cleared and model is updated accordingly.
	 * @param e
	 */
	@FXML
	private void handleChoiceBox(ActionEvent e) {
		
		Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Changing Data Type Warning");
        alert.setHeaderText("Are you sure you want to change the data type for vertices?");
        alert.setContentText("If you click OK the current graph you created will be cleared.");
        ButtonType cancelButton = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
		ButtonType okButton = new ButtonType("Ok", ButtonData.OK_DONE);
		alert.getButtonTypes().setAll(okButton,cancelButton);
		alert.getDialogPane().getStylesheets().add("/application/global.css");
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
	
	/**
	 * This method is called when the user clicks anywhere on the Pane component on the GUI. The user is prompted to input the data they want the
	 * the vertex they want to add to hold. Once the user inputs the data and presses OK the data is then checked to see if it is valid in the
	 * AddVertexDataController class. If the data is valid then a vertex is created and some event handlers are added to this vertex. The event
	 * handlers added to the vertex are for the deletion of vertices via double clicking the vertex and adding edges via right clicking the vertex.
	 * The vertex is then added to the GUI where the user clicked.
	 * @param event
	 */
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
		
	/**
	 * Method to retrieve the data choice on the Undirected Non-Weighted graph tab.
	 * @return
	 */
	public String getSelectedDataChoiceUndirectedNonWeightedGraph() {

		return choiceBoxUndirectedNonWeightedGraph.getSelectionModel().getSelectedItem();

	}
	
	/**
	 * Method to retrieve the data choice on the Undirected Weighted graph tab.
	 * @return
	 */
	public String getSelectedDataChoiceUndirectedWeightedGraph() {

		return choiceBoxUndirectedWeightedGraph.getSelectionModel().getSelectedItem();

	}
	
	/**
	 * Method to retrieve the data choice on the Directed Non-Weighted graph tab.
	 * @return
	 */
	public String getSelectedDataChoiceDirectedNonWeightedGraph() {

		return choiceBoxDirectedNonWeightedGraph.getSelectionModel().getSelectedItem();

	}
	
	/**
	 * Method to retrieve the data choice on the Directed Weighted graph tab.
	 * @return
	 */
	public String getSelectedDataChoiceDirectedWeightedGraph() {

		return choiceBoxDirectedWeightedGraph.getSelectionModel().getSelectedItem();

	}
	
	/**
	 * Method to retrieve the name of the tab the user is currently on.
	 * @return
	 */
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
	
	private boolean hasNegativeEdgesUndirectedWeighted() {
		boolean isNegativeWeights = false;
		
		for(Set<Pair<Vertex<Integer>, Double>> edges: dataModel.getUndirectedWeightedInt().getAdjacencyList().values()) {
			for(Pair<Vertex<Integer>, Double> edge: edges) {
				if(edge.getValue() < 0) {
					isNegativeWeights = true;
				}
			}
		}
		
		for(Set<Pair<Vertex<Double>, Double>> edges: dataModel.getUndirectedWeightedDouble().getAdjacencyList().values()) {
			for(Pair<Vertex<Double>, Double> edge: edges) {
				if(edge.getValue() < 0) {
					isNegativeWeights = true;
				}
			}
		}
		
		for(Set<Pair<Vertex<String>, Double>> edges: dataModel.getUndirectedWeightedString().getAdjacencyList().values()) {
			for(Pair<Vertex<String>, Double> edge: edges) {
				if(edge.getValue() < 0) {
					isNegativeWeights = true;
				}
			}
		}

		return isNegativeWeights;
	}
	
	private boolean hasNegativeEdgesDirectedWeighted() {
		boolean isNegativeWeights = false;
		
		for(Set<Pair<Vertex<Integer>, Double>> edges: dataModel.getDirectedWeightedInt().getAdjacencyList().values()) {
			for(Pair<Vertex<Integer>, Double> edge: edges) {
				if(edge.getValue() < 0) {
					isNegativeWeights = true;
				}
			}
		}
		
		for(Set<Pair<Vertex<Double>, Double>> edges: dataModel.getDirectedWeightedDouble().getAdjacencyList().values()) {
			for(Pair<Vertex<Double>, Double> edge: edges) {
				if(edge.getValue() < 0) {
					isNegativeWeights = true;
				}
			}
		}
		
		for(Set<Pair<Vertex<String>, Double>> edges: dataModel.getDirectedWeightedString().getAdjacencyList().values()) {
			for(Pair<Vertex<String>, Double> edge: edges) {
				if(edge.getValue() < 0) {
					isNegativeWeights = true;
				}
			}
		}

		return isNegativeWeights;
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
    
    /**
     * This method checks that the data the user inputs for the starting vertex of an algorithm to be visualised is valid and correct. If the data is
     * invalid an error message is shown to inform the user why the data they inputed is incorrect.
     * @param input
     * @return true if data valid else false
     */
    public boolean isInputValidStartingVertex(String input) {
    	
    	String selectedDataChoiceUndirectedNonWeightedGraph = getSelectedDataChoiceUndirectedNonWeightedGraph();
    	String selectedDataChoiceUndirectedWeightedGraph = getSelectedDataChoiceUndirectedWeightedGraph();
    	String selectedDataChoiceDirectedNonWeightedGraph = getSelectedDataChoiceDirectedNonWeightedGraph();
    	String selectedDataChoiceDirectedWeightedGraph = getSelectedDataChoiceDirectedWeightedGraph();

    	String errorMessage = "";
    	if (input == null || input.length() == 0) {
    		
    		errorMessage += "Field cannot be left empty please add some data.";
    		
    	}else if(isInteger(input) && getSelectedTabName().equals("Undirected Non-Weighted Graph") 
    			&& !selectedDataChoiceUndirectedNonWeightedGraph.equals("Integer")) {
    		
    		errorMessage+="Invalid data type inputted, you must enter data of type " + selectedDataChoiceUndirectedNonWeightedGraph + ".";
    		
    	}
    	else if(isDouble(input) && getSelectedTabName().equals("Undirected Non-Weighted Graph") && 
    			!selectedDataChoiceUndirectedNonWeightedGraph.equals("Double")) {
    		
    		errorMessage+="Invalid data type inputted, you must enter data of type " + selectedDataChoiceUndirectedNonWeightedGraph + ".";
    		
    	}
    	else if(isString(input) && getSelectedTabName().equals("Undirected Non-Weighted Graph") && 
    			!selectedDataChoiceUndirectedNonWeightedGraph.equals("String")) {
    		
    		errorMessage+="Invalid data type inputted, you must enter data of type " + selectedDataChoiceUndirectedNonWeightedGraph + ".";
    		
    	}
    	else if(isInteger(input) && getSelectedTabName().equals("Undirected Weighted Graph") 
    			&& !selectedDataChoiceUndirectedWeightedGraph.equals("Integer")) {
    		
    		errorMessage+="Invalid data type inputted, you must enter data of type " + selectedDataChoiceUndirectedWeightedGraph + ".";
    		
    	}
    	else if(isDouble(input) && getSelectedTabName().equals("Undirected Weighted Graph") && 
    			!selectedDataChoiceUndirectedWeightedGraph.equals("Double")) {
    		
    		errorMessage+="Invalid data type inputted, you must enter data of type " + selectedDataChoiceUndirectedWeightedGraph + ".";
    		
    	}
    	else if(isString(input) && getSelectedTabName().equals("Undirected Weighted Graph") && 
    			!selectedDataChoiceUndirectedWeightedGraph.equals("String")) {
    		
    		errorMessage+="Invalid data type inputted, you must enter data of type" + selectedDataChoiceUndirectedWeightedGraph + ".";
    		
    	}
    	else if(isInteger(input) && getSelectedTabName().equals("Directed Non-Weighted Graph") 
    			&& !selectedDataChoiceDirectedNonWeightedGraph.equals("Integer")) {
    		
    		errorMessage+="Invalid data type inputted, you must enter data of type" + selectedDataChoiceDirectedNonWeightedGraph + ".";
    		
    	}
    	else if(isDouble(input) && getSelectedTabName().equals("Directed Non-Weighted Graph") && 
    			!selectedDataChoiceDirectedNonWeightedGraph.equals("Double")) {
    		
    		errorMessage+="Invalid data type inputted, you must enter data of type" + selectedDataChoiceDirectedNonWeightedGraph + ".";
    		
    	}
    	else if(isString(input) && getSelectedTabName().equals("Directed Non-Weighted Graph") && 
    			!selectedDataChoiceDirectedNonWeightedGraph.equals("String")) {
    		
    		errorMessage+="Invalid data type inputted, you must enter data of type" + selectedDataChoiceDirectedNonWeightedGraph + ".";
    		
    	}
    	else if(isInteger(input) && getSelectedTabName().equals("Directed Weighted Graph") 
    			&& !selectedDataChoiceDirectedWeightedGraph.equals("Integer")) {
    		
    		errorMessage+="Invalid data type inputted, you must enter data of type" + selectedDataChoiceDirectedWeightedGraph + ".";
    		
    	}
    	else if(isDouble(input) && getSelectedTabName().equals("Directed Weighted Graph") && 
    			!selectedDataChoiceDirectedWeightedGraph.equals("Double")) {
    		
    		errorMessage+="Invalid data type inputted, you must enter data of type" + selectedDataChoiceDirectedWeightedGraph + ".";
    		
    	}
    	else if(isString(input) && getSelectedTabName().equals("Directed Weighted Graph") && 
    			!selectedDataChoiceDirectedWeightedGraph.equals("String")) {
    		
    		errorMessage+="Invalid data type inputted, you must enter data of type" + selectedDataChoiceDirectedWeightedGraph + ".";
    		
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
            alert.setTitle("Starting Vertex Error");
            alert.setHeaderText("You have provided incorrect data.");
            alert.setContentText(errorMessage);
            alert.getDialogPane().getStylesheets().add("/application/global.css");
            alert.showAndWait();
            
            return false;
        }
 
    }
    
    /**
     * This method checks that the data the user inputs for adding an edge to a graph is valid. If the data is
     * invalid an error message is shown to inform the user why the data they inputed is incorrect.
     * @param input
     * @return true if data valid else false
     */
    public boolean isInputValidAddingEdge(String input,String startVertex,String weightInput) {
    	
    	String selectedDataChoiceUndirectedNonWeightedGraph = getSelectedDataChoiceUndirectedNonWeightedGraph();
    	String selectedDataChoiceUndirectedWeightedGraph = getSelectedDataChoiceUndirectedWeightedGraph();
    	String selectedDataChoiceDirectedNonWeightedGraph = getSelectedDataChoiceDirectedNonWeightedGraph();
    	String selectedDataChoiceDirectedWeightedGraph = getSelectedDataChoiceDirectedWeightedGraph();
    	
    	String errorMessage = "";
    	if (input == null || input.length() == 0) {
    		
    		errorMessage += "Fields cannot be left empty please add some data.";
    		
    	}
    	else if((getSelectedTabName().equals("Undirected Weighted Graph") || getSelectedTabName().equals("Directed Weighted Graph")) &&
    			!isDouble(weightInput)) {
    		
    		errorMessage += "Weight of an edge must be of data type double.";
    		
    	}
    	else if(input.equals(startVertex) && (getSelectedTabName().equals("Undirected Non-Weighted Graph") || getSelectedTabName().equals("Undirected Weighted Graph"))) {
			errorMessage+="You cannot add an edge to itself for undirected graphs.";
		}
    	
    	else if(isInteger(input) && getSelectedTabName().equals("Undirected Non-Weighted Graph") 
    			&& !selectedDataChoiceUndirectedNonWeightedGraph.equals("Integer")) {
    		
    		errorMessage+="Invalid data type inputted, you must enter data of type" + selectedDataChoiceUndirectedNonWeightedGraph + ".";
    		
    	}
    	else if(isDouble(input) && getSelectedTabName().equals("Undirected Non-Weighted Graph") && 
    			!selectedDataChoiceUndirectedNonWeightedGraph.equals("Double")) {
    		
    		errorMessage+="Invalid data type inputted, you must enter data of type" + selectedDataChoiceUndirectedNonWeightedGraph + ".";
    		
    	}
    	else if(isString(input) && getSelectedTabName().equals("Undirected Non-Weighted Graph") && 
    			!selectedDataChoiceUndirectedNonWeightedGraph.equals("String")) {
    		
    		errorMessage+="Invalid data type inputted, you must enter data of type" + selectedDataChoiceUndirectedNonWeightedGraph + ".";
    		
    	}
    	else if(isInteger(input) && getSelectedTabName().equals("Undirected Weighted Graph") 
    			&& !selectedDataChoiceUndirectedWeightedGraph.equals("Integer")) {
    		
    		errorMessage+="Invalid data type inputted, you must enter data of type" + selectedDataChoiceUndirectedWeightedGraph + ".";
    		
    	}
    	else if(isDouble(input) && getSelectedTabName().equals("Undirected Weighted Graph") && 
    			!selectedDataChoiceUndirectedWeightedGraph.equals("Double")) {
    		
    		errorMessage+="Invalid data type inputted, you must enter data of type" + selectedDataChoiceUndirectedWeightedGraph + ".";
    		
    	}
    	else if(isString(input) && getSelectedTabName().equals("Undirected Weighted Graph") && 
    			!selectedDataChoiceUndirectedWeightedGraph.equals("String")) {
    		
    		errorMessage+="Invalid data type inputted, you must enter data of type" + selectedDataChoiceUndirectedWeightedGraph + ".";
    		
    	}
    	else if(isInteger(input) && getSelectedTabName().equals("Directed Non-Weighted Graph") 
    			&& !selectedDataChoiceDirectedNonWeightedGraph.equals("Integer")) {
    		
    		errorMessage+="Invalid data type inputted, you must enter data of type" + selectedDataChoiceDirectedNonWeightedGraph + ".";
    		
    	}
    	else if(isDouble(input) && getSelectedTabName().equals("Directed Non-Weighted Graph") && 
    			!selectedDataChoiceDirectedNonWeightedGraph.equals("Double")) {
    		
    		errorMessage+="Invalid data type inputted, you must enter data of type" + selectedDataChoiceDirectedNonWeightedGraph + ".";
    		
    	}
    	else if(isString(input) && getSelectedTabName().equals("Directed Non-Weighted Graph") && 
    			!selectedDataChoiceDirectedNonWeightedGraph.equals("String")) {
    		
    		errorMessage+="Invalid data type inputted, you must enter data of type" + selectedDataChoiceDirectedNonWeightedGraph + ".";
    		
    	}
    	else if(isInteger(input) && getSelectedTabName().equals("Directed Weighted Graph") 
    			&& !selectedDataChoiceDirectedWeightedGraph.equals("Integer")) {
    		
    		errorMessage+="Invalid data type inputted, you must enter data of type" + selectedDataChoiceDirectedWeightedGraph + ".";
    		
    	}
    	else if(isDouble(input) && getSelectedTabName().equals("Directed Weighted Graph") && 
    			!selectedDataChoiceDirectedWeightedGraph.equals("Double")) {
    		
    		errorMessage+="Invalid data type inputted, you must enter data of type" + selectedDataChoiceDirectedWeightedGraph + ".";
    		
    	}
    	else if(isString(input) && getSelectedTabName().equals("Directed Weighted Graph") && 
    			!selectedDataChoiceDirectedWeightedGraph.equals("String")) {
    		
    		errorMessage+="Invalid data type inputted, you must enter data of type" + selectedDataChoiceDirectedWeightedGraph + ".";
    		
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
             alert.setTitle("Add Edge Error.");
             alert.setHeaderText("You have provided incorrect data.");
             alert.setContentText(errorMessage);
             alert.getDialogPane().getStylesheets().add("/application/global.css");
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
