package application;
	
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import application.model.DataModel;
import application.model.DirectedNonWeightedGraph;
import application.model.DirectedWeightedGraph;
import application.model.UndirectedNonWeightedGraph;
import application.model.UndirectedWeightedGraph;
import application.model.Vertex;
import application.view.AddVertexDataController;
import application.view.GraphPanelController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Pair;


/**
 * This class is the main class where the main graph panel gui is loaded and controllers are assigned to the different views.
 * @author jamansalique
 *
 */
public class Main extends Application {
	
	private Stage primaryStage;
	
	private DataModel dataModel;
	private GraphPanelController controller;
	
	public Main() {
		
		dataModel = new DataModel();

	}
	
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Graph Visualiser");
        
        showGraphPanel();

	}
	
	/**
	 * This method connects the GraphPanelController with the GraphPanel view and will load the GraphPanel GUI.
	 */
	public void showGraphPanel() {
		 try {
	            // Load GraphPanel view.
	            FXMLLoader loader = new FXMLLoader();
	            loader.setLocation(Main.class.getResource("view/GraphPanel.fxml"));
	            AnchorPane graphPanel = (AnchorPane) loader.load();

	            // Give the controller access to the main app.
	            controller = loader.getController();
	            controller.setMain(this);
	            controller.setDataModel(dataModel);
	            
	            //Show the scene containing the root layout.
	            Scene scene = new Scene(graphPanel);
	            
	            String css= Main.class.getResource("global.css").toExternalForm();
	            scene.getStylesheets().add(css);
	            
	            primaryStage.setScene(scene);
	            primaryStage.show();

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	}
	
	/**
	 * This method connects the AddVertexDataController with the AddVertexData view and will load the add vertex data form.
	 * @param gpc
	 * @return
	 */
	public boolean showAddVertexPopUp(GraphPanelController gpc) {
		try {
            // Load the fxml file and create a new stage for the add vertex popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/AddVertexData.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Add vertex data");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            
            String css= Main.class.getResource("global.css").toExternalForm();
            scene.getStylesheets().add(css);

            // Set the AddVertexData into the controller.
            AddVertexDataController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setGraphPanelController(gpc);
            controller.setMain(this);
            controller.setDataModel(dataModel);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
	}

	/**
     * Returns the main stage.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }
	
    /**
     * Main method...
     * @param args
     */
	public static void main(String[] args) {
		launch(args);
	}
}
