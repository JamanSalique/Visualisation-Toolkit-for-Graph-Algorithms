package application.view;

import application.Main;
import application.model.DataModel;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Region;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * This class is controller class for when a user adds a vertex to any graph through the Graphical User Interface.
 * @author jamansalique
 *
 */
public class AddVertexDataController {

	@FXML
    private TextField inputDataField;
	
	private Stage dialogStage;
    private boolean okClicked = false;
    private GraphPanelController graphPanelController;
    private Main main;
    private DataModel dataModel;
    
    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    }
    
    /**
     * Sets the stage of this dialog.
     * 
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    
    /**
     * Returns true if the user clicked OK, false otherwise.
     * 
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }
    
    /**
     * Called when the user clicks ok. Update the model accordingly for whatever vertex the user added.
     */
    @FXML
    private void handleOk() {
        if (isInputValid()) {

        	/*
        	 * In these if statements the type of graph the user is adding a vertex to and the data type of the user input
        	 * for the vertex is checked, depending on these 2 factors update the right type of graph accordingly. For example if the user inputs '2'
        	 * and is on the Undirected Non-Weighted Graph tab then we will update the Undirected Non-Weighted Graph that consists of integer vertices.
        	 */
        	if(graphPanelController.getSelectedTabName().equals("Undirected Non-Weighted Graph") && isInteger(inputDataField.getText())) {
        		
        		addVertexToUndirectedNonWeightedGraph("Integer");
        		
        	}else if(graphPanelController.getSelectedTabName().equals("Undirected Non-Weighted Graph") && isDouble(inputDataField.getText())) {
        		
        		addVertexToUndirectedNonWeightedGraph("Double");
        		
        	}else if(graphPanelController.getSelectedTabName().equals("Undirected Non-Weighted Graph") && isString(inputDataField.getText())) {
        		
        		addVertexToUndirectedNonWeightedGraph("String");
        		
        	}
        	
        	else if(graphPanelController.getSelectedTabName().equals("Undirected Weighted Graph") && isInteger(inputDataField.getText())) {
        		
        		addVertexToUndirectedWeightedGraph("Integer");
        		
        	}else if(graphPanelController.getSelectedTabName().equals("Undirected Weighted Graph") && isDouble(inputDataField.getText())) {
        		
        		addVertexToUndirectedWeightedGraph("Double");
        		
        	}else if(graphPanelController.getSelectedTabName().equals("Undirected Weighted Graph") && isString(inputDataField.getText())) {
        		
        		addVertexToUndirectedWeightedGraph("String");
        		
        	}
        	
        	else if(graphPanelController.getSelectedTabName().equals("Directed Non-Weighted Graph") && isInteger(inputDataField.getText())) {
        		
        		addVertexToDirectedNonWeightedGraph("Integer");
        		
        	}else if(graphPanelController.getSelectedTabName().equals("Directed Non-Weighted Graph") && isDouble(inputDataField.getText())) {
        		
        		addVertexToDirectedNonWeightedGraph("Double");
        		
        	}else if(graphPanelController.getSelectedTabName().equals("Directed Non-Weighted Graph") && isString(inputDataField.getText())) {
        		
        		addVertexToDirectedNonWeightedGraph("String");
        		
        	}
        	
        	else if(graphPanelController.getSelectedTabName().equals("Directed Weighted Graph") && isInteger(inputDataField.getText())) {
        		
        		addVertexToDirectedWeightedGraph("Integer");
        		
        	}else if(graphPanelController.getSelectedTabName().equals("Directed Weighted Graph") && isDouble(inputDataField.getText())) {
        		
        		addVertexToDirectedWeightedGraph("Double");
        		
        	}else if(graphPanelController.getSelectedTabName().equals("Directed Weighted Graph") && isString(inputDataField.getText())) {
        		
        		addVertexToDirectedWeightedGraph("String");
        		
        	}
            okClicked = true;
            dialogStage.close();
        }
    }
    
    /**
     * This method is a error prevention method which checks if the users request is possible. For example a user cannot add a vertex with a Double
     * data type for a graph that consists of integer vertices. If a error is found then we notify the user with an alert box with a error message
     * explaining what they have done wrong.
     * @return
     */
    private boolean isInputValid() {
    	
    	String input = inputDataField.getText();
    	
    	String selectedDataChoiceUndirectedNonWeightedGraph = graphPanelController.getSelectedDataChoiceUndirectedNonWeightedGraph();
    	String selectedDataChoiceUndirectedWeightedGraph = graphPanelController.getSelectedDataChoiceUndirectedWeightedGraph();
    	String selectedDataChoiceDirectedNonWeightedGraph = graphPanelController.getSelectedDataChoiceDirectedNonWeightedGraph();
    	String selectedDataChoiceDirectedWeightedGraph = graphPanelController.getSelectedDataChoiceDirectedWeightedGraph();
    	
    	String errorMessage = "";
    	if (input == null || input.length() == 0) {
    		errorMessage += "Fields cannot be left empty please add some data.";
    	}
    	else if(isInteger(input) && graphPanelController.getSelectedTabName().equals("Undirected Non-Weighted Graph") 
    			&& !selectedDataChoiceUndirectedNonWeightedGraph.equals("Integer")) {
    		
    		errorMessage+="Invalid data type inputted, you must enter data of type " + selectedDataChoiceUndirectedNonWeightedGraph + ".";
    		
    	}
    	else if(isDouble(input) && graphPanelController.getSelectedTabName().equals("Undirected Non-Weighted Graph") && 
    			!selectedDataChoiceUndirectedNonWeightedGraph.equals("Double")) {
    		
    		errorMessage+="Invalid data type inputted, you must enter data of type " + selectedDataChoiceUndirectedNonWeightedGraph + ".";
    		
    	}
    	else if(isString(input) && graphPanelController.getSelectedTabName().equals("Undirected Non-Weighted Graph") && 
    			!selectedDataChoiceUndirectedNonWeightedGraph.equals("String")) {
    		
    		errorMessage+="Invalid data type inputted, you must enter data of type " + selectedDataChoiceUndirectedNonWeightedGraph + ".";
    		
    	}
    	else if(isInteger(input) && graphPanelController.getSelectedTabName().equals("Undirected Weighted Graph") 
    			&& !selectedDataChoiceUndirectedWeightedGraph.equals("Integer")) {
    		
    		errorMessage+="Invalid data type inputted, you must enter data of type " + selectedDataChoiceUndirectedWeightedGraph + ".";
    		
    	}
    	else if(isDouble(input) && graphPanelController.getSelectedTabName().equals("Undirected Weighted Graph") && 
    			!selectedDataChoiceUndirectedWeightedGraph.equals("Double")) {
    		
    		errorMessage+="Invalid data type inputted, you must enter data of type " + selectedDataChoiceUndirectedWeightedGraph + ".";
    		
    	}
    	else if(isString(input) && graphPanelController.getSelectedTabName().equals("Undirected Weighted Graph") && 
    			!selectedDataChoiceUndirectedWeightedGraph.equals("String")) {
    		
    		errorMessage+="Invalid data type inputted, you must enter data of type " + selectedDataChoiceUndirectedWeightedGraph + ".";
    		
    	}
    	else if(isInteger(input) && graphPanelController.getSelectedTabName().equals("Directed Non-Weighted Graph") 
    			&& !selectedDataChoiceDirectedNonWeightedGraph.equals("Integer")) {
    		
    		errorMessage+="Invalid data type inputted, you must enter data of type " + selectedDataChoiceDirectedNonWeightedGraph + ".";
    		
    	}
    	else if(isDouble(input) && graphPanelController.getSelectedTabName().equals("Directed Non-Weighted Graph") && 
    			!selectedDataChoiceDirectedNonWeightedGraph.equals("Double")) {

    		errorMessage+="Invalid data type inputted, you must enter data of type " + selectedDataChoiceDirectedNonWeightedGraph + ".";
    		
    	}
    	else if(isString(input) && graphPanelController.getSelectedTabName().equals("Directed Non-Weighted Graph") && 
    			!selectedDataChoiceDirectedNonWeightedGraph.equals("String")) {
    		
    		errorMessage+="Invalid data type inputted, you must enter data of type " + selectedDataChoiceDirectedNonWeightedGraph + ".";
    		
    	}
    	else if(isInteger(input) && graphPanelController.getSelectedTabName().equals("Directed Weighted Graph") 
    			&& !selectedDataChoiceDirectedWeightedGraph.equals("Integer")) {
    		
    		errorMessage+="Invalid data type inputted, you must enter data of type " + selectedDataChoiceDirectedWeightedGraph + ".";
    		
    	}
    	else if(isDouble(input) && graphPanelController.getSelectedTabName().equals("Directed Weighted Graph") && 
    			!selectedDataChoiceDirectedWeightedGraph.equals("Double")) {
    		
    		errorMessage+="Invalid data type inputted, you must enter data of type " + selectedDataChoiceDirectedWeightedGraph + ".";
    		
    	}
    	else if(isString(input) && graphPanelController.getSelectedTabName().equals("Directed Weighted Graph") && 
    			!selectedDataChoiceDirectedWeightedGraph.equals("String")) {
    		
    		errorMessage+="Invalid data type inputted, you must enter data of type " + selectedDataChoiceDirectedWeightedGraph + ".";
    		
    	}
 	
    	else {
    		
    	}
    	
    	 if (errorMessage.length() == 0) {
             return true;
         } else {
             // Show the error message.
             Alert alert = new Alert(AlertType.ERROR);
             alert.initOwner(dialogStage);
             alert.setTitle("Add Vertex Error");
             alert.setHeaderText("Please correct invalid fields");
             alert.setContentText(errorMessage);
             alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
             alert.showAndWait();
             
             return false;
         }
    	
    }
    
    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
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
    
    public void setGraphPanelController(GraphPanelController gpc) {
    	this.graphPanelController = gpc;
    }
    
    public void setMain(Main main) {
    	this.main = main;
    }
    
    /**
     * This method updates the Undirected Non-Weighted graph in the model when a user adds a vertex to a Undirected Non-Weighted graph.
     * @param type: the data type the user inputs.
     */
    private void addVertexToUndirectedNonWeightedGraph(String type) {
    	
    	if(type.equals("Integer") && dataModel.getUndirectedNonWeightedInt().containsVertex(Integer.parseInt(inputDataField.getText()))) {
    		Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Duplicate Vertex");
            alert.setHeaderText("Cannot add duplicate vertices");
            alert.setContentText("A vertex with data " + Integer.parseInt(inputDataField.getText()) + " is already in the graph");
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.showAndWait();
    	}else if(type.equals("Double") && dataModel.getUndirectedNonWeightedDouble().containsVertex(Double.parseDouble(inputDataField.getText()))) {
    		Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Duplicate Vertex");
            alert.setHeaderText("Cannot add duplicate vertices");
            alert.setContentText("A vertex with data " + Double.parseDouble(inputDataField.getText()) + " is already in the graph");
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.showAndWait();
    	}else if(type.equals("String") && dataModel.getUndirectedNonWeightedString().containsVertex(inputDataField.getText())) {
    		Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Duplicate Vertex");
            alert.setHeaderText("Cannot add duplicate vertices");
            alert.setContentText("A vertex with data " + inputDataField.getText() + " is already in the graph");
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.showAndWait();
    	}
    	
    	if(type.equals("Integer")) {
    		
    		dataModel.getUndirectedNonWeightedInt().addVertex(Integer.parseInt(inputDataField.getText()));
    		
    		dataModel.getVertexDataUndirectedNonWeightedInt().add(dataModel.getUndirectedNonWeightedInt().returnVertex(Integer.parseInt(inputDataField.getText())));
    		
    		dataModel.getListOfUndirectedNonWeightedIntVertices().add(dataModel.getUndirectedNonWeightedInt().returnVertex(Integer.parseInt(inputDataField.getText())));
    		
    	}else if(type.equals("Double")) {
    		dataModel.getUndirectedNonWeightedDouble().addVertex(Double.parseDouble(inputDataField.getText()));
    		
    		dataModel.getVertexDataUndirectedNonWeightedDouble().add(dataModel.getUndirectedNonWeightedDouble().returnVertex(Double.parseDouble(inputDataField.getText())));
    		
    		dataModel.getListOfUndirectedNonWeightedDoubleVertices().add(dataModel.getUndirectedNonWeightedDouble().returnVertex(Double.parseDouble(inputDataField.getText())));
    		
    	}else if(type.equals("String")) {
    		
    		dataModel.getUndirectedNonWeightedString().addVertex(inputDataField.getText());
    		
    		dataModel.getVertexDataUndirectedNonWeightedString().add(dataModel.getUndirectedNonWeightedString().returnVertex(inputDataField.getText()));
    		
    		dataModel.getListOfUndirectedNonWeightedStringVertices().add(dataModel.getUndirectedNonWeightedString().returnVertex(inputDataField.getText()));
    		
    	}
    }
    
    /**
     * This method updates the Undirected Weighted graph in the model when a user adds a vertex to a Undirected Weighted graph.
     * @param type: the data type the user inputs.
     */
	private void addVertexToUndirectedWeightedGraph(String type) {
    	
    	if(type.equals("Integer") && dataModel.getUndirectedWeightedInt().containsVertex(Integer.parseInt(inputDataField.getText()))) {
    		Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Duplicate Vertex");
            alert.setHeaderText("Cannot add duplicate vertices");
            alert.setContentText("A vertex with data " + Integer.parseInt(inputDataField.getText()) + " is already in the graph");
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.showAndWait();
    	}else if(type.equals("Double") && dataModel.getUndirectedWeightedDouble().containsVertex(Double.parseDouble(inputDataField.getText()))) {
    		Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Duplicate Vertex");
            alert.setHeaderText("Cannot add duplicate vertices");
            alert.setContentText("A vertex with data " + Double.parseDouble(inputDataField.getText()) + " is already in the graph");
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.showAndWait();
    	}else if(type.equals("String") && dataModel.getUndirectedWeightedString().containsVertex(inputDataField.getText())) {
    		Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Duplicate Vertex");
            alert.setHeaderText("Cannot add duplicate vertices");
            alert.setContentText("A vertex with data " + inputDataField.getText() + " is already in the graph");
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.showAndWait();
    	}
    	
    	
    	if(type.equals("Integer")) {
    		
    		dataModel.getUndirectedWeightedInt().addVertex(Integer.parseInt(inputDataField.getText()));
    		
    		dataModel.getVertexDataUndirectedWeightedInt().add(dataModel.getUndirectedWeightedInt().returnVertex(Integer.parseInt(inputDataField.getText())));
    		
    		dataModel.getListOfUndirectedWeightedIntVertices().add(dataModel.getUndirectedWeightedInt().returnVertex(Integer.parseInt(inputDataField.getText())));
    		
    	}else if(type.equals("Double")) {
    		
    		dataModel.getUndirectedWeightedDouble().addVertex(Double.parseDouble(inputDataField.getText()));
    		
    		dataModel.getVertexDataUndirectedWeightedDouble().add(dataModel.getUndirectedWeightedDouble().returnVertex(Double.parseDouble(inputDataField.getText())));
    		
    		dataModel.getListOfUndirectedWeightedDoubleVertices().add(dataModel.getUndirectedWeightedDouble().returnVertex(Double.parseDouble(inputDataField.getText())));
    		
    	}else if(type.equals("String")) {
    		
    		dataModel.getUndirectedWeightedString().addVertex(inputDataField.getText());
    		
    		dataModel.getVertexDataUndirectedWeightedString().add(dataModel.getUndirectedWeightedString().returnVertex(inputDataField.getText()));
    		
    		dataModel.getListOfUndirectedWeightedStringVertices().add(dataModel.getUndirectedWeightedString().returnVertex(inputDataField.getText()));
    		
    	}
    }
	
	/**
     * This method updates the Directed Non-Weighted graph in the model when a user adds a vertex to a Directed Non-Weighted graph.
     * @param type: the data type the user inputs.
     */
	 private void addVertexToDirectedNonWeightedGraph(String type) {
	    	
	    	if(type.equals("Integer") && dataModel.getDirectedNonWeightedInt().containsVertex(Integer.parseInt(inputDataField.getText()))) {
	    		Alert alert = new Alert(AlertType.ERROR);
	            alert.initOwner(dialogStage);
	            alert.setTitle("Duplicate Vertex");
	            alert.setHeaderText("Cannot add duplicate vertices");
	            alert.setContentText("A vertex with data " + Integer.parseInt(inputDataField.getText()) + " is already in the graph");
	            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
	            alert.showAndWait();
	    	}else if(type.equals("Double") && dataModel.getDirectedNonWeightedDouble().containsVertex(Double.parseDouble(inputDataField.getText()))) {
	    		Alert alert = new Alert(AlertType.ERROR);
	            alert.initOwner(dialogStage);
	            alert.setTitle("Duplicate Vertex");
	            alert.setHeaderText("Cannot add duplicate vertices");
	            alert.setContentText("A vertex with data " + Double.parseDouble(inputDataField.getText()) + " is already in the graph");
	            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
	            alert.showAndWait();
	    	}else if(type.equals("String") && dataModel.getDirectedNonWeightedString().containsVertex(inputDataField.getText())) {
	    		Alert alert = new Alert(AlertType.ERROR);
	            alert.initOwner(dialogStage);
	            alert.setTitle("Duplicate Vertex");
	            alert.setHeaderText("Cannot add duplicate vertices");
	            alert.setContentText("A vertex with data " + inputDataField.getText() + " is already in the graph");
	            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
	            alert.showAndWait();
	    	}
	    	
	    	if(type.equals("Integer")) {
	    		
	    		dataModel.getDirectedNonWeightedInt().addVertex(Integer.parseInt(inputDataField.getText()));
	    		
	    		dataModel.getVertexDataDirectedNonWeightedInt().add(dataModel.getDirectedNonWeightedInt().returnVertex(Integer.parseInt(inputDataField.getText())));
	    		
	    		dataModel.getListOfDirectedNonWeightedIntVertices().add(dataModel.getDirectedNonWeightedInt().returnVertex(Integer.parseInt(inputDataField.getText())));
	    		
	    	}else if(type.equals("Double")) {
	    		dataModel.getDirectedNonWeightedDouble().addVertex(Double.parseDouble(inputDataField.getText()));
	    		
	    		dataModel.getVertexDataDirectedNonWeightedDouble().add(dataModel.getDirectedNonWeightedDouble().returnVertex(Double.parseDouble(inputDataField.getText())));
	    		
	    		dataModel.getListOfDirectedNonWeightedDoubleVertices().add(dataModel.getDirectedNonWeightedDouble().returnVertex(Double.parseDouble(inputDataField.getText())));
	    		
	    	}else if(type.equals("String")) {
	    		
	    		dataModel.getDirectedNonWeightedString().addVertex(inputDataField.getText());
	    		
	    		dataModel.getVertexDataDirectedNonWeightedString().add(dataModel.getDirectedNonWeightedString().returnVertex(inputDataField.getText()));
	    		
	    		dataModel.getListOfDirectedNonWeightedStringVertices().add(dataModel.getDirectedNonWeightedString().returnVertex(inputDataField.getText()));
	    		
	    	}
	    }
	 
	 /**
     * This method updates the Directed Weighted graph in the model when a user adds a vertex to a Directed Weighted graph.
     * @param type: the data type the user inputs.
     */
	 private void addVertexToDirectedWeightedGraph(String type) {
	    	
	    	if(type.equals("Integer") && dataModel.getDirectedWeightedInt().containsVertex(Integer.parseInt(inputDataField.getText()))) {
	    		Alert alert = new Alert(AlertType.ERROR);
	            alert.initOwner(dialogStage);
	            alert.setTitle("Duplicate Vertex.");
	            alert.setHeaderText("Cannot add duplicate vertices.");
	            alert.setContentText("A vertex with data " + Integer.parseInt(inputDataField.getText()) + " is already in the graph.");
	            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
	            alert.showAndWait();
	    	}else if(type.equals("Double") && dataModel.getDirectedWeightedDouble().containsVertex(Double.parseDouble(inputDataField.getText()))) {
	    		Alert alert = new Alert(AlertType.ERROR);
	            alert.initOwner(dialogStage);
	            alert.setTitle("Duplicate Vertex.");
	            alert.setHeaderText("Cannot add duplicate vertices.");
	            alert.setContentText("A vertex with data " + Double.parseDouble(inputDataField.getText()) + " is already in the graph.");
	            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
	            alert.showAndWait();
	    	}else if(type.equals("String") && dataModel.getDirectedWeightedString().containsVertex(inputDataField.getText())) {
	    		Alert alert = new Alert(AlertType.ERROR);
	            alert.initOwner(dialogStage);
	            alert.setTitle("Duplicate Vertex.");
	            alert.setHeaderText("Cannot add duplicate vertices.");
	            alert.setContentText("A vertex with data " + inputDataField.getText() + " is already in the graph.");
	            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
	            alert.showAndWait();
	    	}
	    	
	    	
	    	if(type.equals("Integer")) {
	    		
	    		dataModel.getDirectedWeightedInt().addVertex(Integer.parseInt(inputDataField.getText()));
	    		
	    		dataModel.getVertexDataDirectedWeightedInt().add(dataModel.getDirectedWeightedInt().returnVertex(Integer.parseInt(inputDataField.getText())));
	    		
	    		dataModel.getListOfDirectedWeightedIntVertices().add(dataModel.getDirectedWeightedInt().returnVertex(Integer.parseInt(inputDataField.getText())));
	    		
	    	}else if(type.equals("Double")) {
	    		
	    		dataModel.getDirectedWeightedDouble().addVertex(Double.parseDouble(inputDataField.getText()));
	    		
	    		dataModel.getVertexDataDirectedWeightedDouble().add(dataModel.getDirectedWeightedDouble().returnVertex(Double.parseDouble(inputDataField.getText())));
	    		
	    		dataModel.getListOfDirectedWeightedDoubleVertices().add(dataModel.getDirectedWeightedDouble().returnVertex(Double.parseDouble(inputDataField.getText())));
	    		
	    	}else if(type.equals("String")) {
	    		
	    		dataModel.getDirectedWeightedString().addVertex(inputDataField.getText());
	    		
	    		dataModel.getVertexDataDirectedWeightedString().add(dataModel.getDirectedWeightedString().returnVertex(inputDataField.getText()));
	    		
	    		dataModel.getListOfDirectedWeightedStringVertices().add(dataModel.getDirectedWeightedString().returnVertex(inputDataField.getText()));
	    		
	    	}
	    }
	 
	 public void setDataModel(DataModel dataModel) {
			this.dataModel = dataModel;
	}
    
	
}
