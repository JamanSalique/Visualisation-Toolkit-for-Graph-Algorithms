package application.view;

import application.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddVertexDataController {

	@FXML
    private TextField inputDataField;
	
	private Stage dialogStage;
    private boolean okClicked = false;
    private GraphPanelController graphPanelController;
    private Main main;
    
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
     * Called when the user clicks ok.
     */
    @FXML
    private void handleOk() {
        if (isInputValid()) {
            // create new vertex set data of vertex and return it.
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
    
    private boolean isInputValid() {
    	
    	String input = inputDataField.getText();
    	String selectedDataChoice = graphPanelController.getSelectedDataChoice();
    	
    	
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
    	}else {
    		
    	}
    	
    	 if (errorMessage.length() == 0) {
             return true;
         } else {
             // Show the error message.
             Alert alert = new Alert(AlertType.ERROR);
             alert.initOwner(dialogStage);
             alert.setTitle("Invalid Fields");
             alert.setHeaderText("Please correct invalid fields");
             alert.setContentText(errorMessage);
             
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
    
    public void setGraphPanelController(GraphPanelController gpc) {
    	this.graphPanelController = gpc;
    }
    
    public void setMain(Main main) {
    	this.main = main;
    }
    
    private void addVertexToUndirectedNonWeightedGraph(String type) {
    	
    	if(type.equals("Integer") && main.getUndirectedNonWeightedInt().containsVertex(Integer.parseInt(inputDataField.getText()))) {
    		Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Duplicate Vertex");
            alert.setHeaderText("Cannot add duplicate vertices");
            alert.setContentText("A vertex with data " + Integer.parseInt(inputDataField.getText()) + " is already in the graph");
            alert.showAndWait();
    	}else if(type.equals("Double") && main.getUndirectedNonWeightedDouble().containsVertex(Double.parseDouble(inputDataField.getText()))) {
    		Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Duplicate Vertex");
            alert.setHeaderText("Cannot add duplicate vertices");
            alert.setContentText("A vertex with data " + Double.parseDouble(inputDataField.getText()) + " is already in the graph");
            alert.showAndWait();
    	}else if(type.equals("String") && main.getUndirectedNonWeightedString().containsVertex(inputDataField.getText())) {
    		Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Duplicate Vertex");
            alert.setHeaderText("Cannot add duplicate vertices");
            alert.setContentText("A vertex with data " + inputDataField.getText() + " is already in the graph");
            alert.showAndWait();
    	}
    	
    	if(type.equals("Integer")) {
    		
    		main.getUndirectedNonWeightedInt().addVertex(Integer.parseInt(inputDataField.getText()));
    		
    		main.getVertexDataInt().add(main.getUndirectedNonWeightedInt().returnVertex(Integer.parseInt(inputDataField.getText())));
    		
    		main.getListOfIntVertices().add(main.getUndirectedNonWeightedInt().returnVertex(Integer.parseInt(inputDataField.getText())));
    		
    	}else if(type.equals("Double")) {
    		main.getUndirectedNonWeightedDouble().addVertex(Double.parseDouble(inputDataField.getText()));
    		
    		main.getVertexDataDouble().add(main.getUndirectedNonWeightedDouble().returnVertex(Double.parseDouble(inputDataField.getText())));
    		
    		main.getListOfDoubleVertices().add(main.getUndirectedNonWeightedDouble().returnVertex(Double.parseDouble(inputDataField.getText())));
    		
    	}else if(type.equals("String")) {
    		
    		main.getUndirectedNonWeightedString().addVertex(inputDataField.getText());
    		
    		main.getVertexDataString().add(main.getUndirectedNonWeightedString().returnVertex(inputDataField.getText()));
    		
    		main.getListOfStringVertices().add(main.getUndirectedNonWeightedString().returnVertex(inputDataField.getText()));
    		
    	}
    }
    
	private void addVertexToUndirectedWeightedGraph(String type) {
    	
    	if(type.equals("Integer") && main.getUndirectedWeightedInt().containsVertex(Integer.parseInt(inputDataField.getText()))) {
    		Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Duplicate Vertex");
            alert.setHeaderText("Cannot add duplicate vertices");
            alert.setContentText("A vertex with data " + Integer.parseInt(inputDataField.getText()) + " is already in the graph");
            alert.showAndWait();
    	}else if(type.equals("Double") && main.getUndirectedWeightedDouble().containsVertex(Double.parseDouble(inputDataField.getText()))) {
    		Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Duplicate Vertex");
            alert.setHeaderText("Cannot add duplicate vertices");
            alert.setContentText("A vertex with data " + Double.parseDouble(inputDataField.getText()) + " is already in the graph");
            alert.showAndWait();
    	}else if(type.equals("String") && main.getUndirectedWeightedString().containsVertex(inputDataField.getText())) {
    		Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Duplicate Vertex");
            alert.setHeaderText("Cannot add duplicate vertices");
            alert.setContentText("A vertex with data " + inputDataField.getText() + " is already in the graph");
            alert.showAndWait();
    	}
    	
    	
    	if(type.equals("Integer")) {
    		
    		main.getUndirectedWeightedInt().addVertex(Integer.parseInt(inputDataField.getText()));
    		
    		main.getVertexDataInt().add(main.getUndirectedWeightedInt().returnVertex(Integer.parseInt(inputDataField.getText())));
    		
    		main.getListOfIntVertices().add(main.getUndirectedWeightedInt().returnVertex(Integer.parseInt(inputDataField.getText())));
    		
    	}else if(type.equals("Double")) {
    		
    		main.getUndirectedWeightedDouble().addVertex(Double.parseDouble(inputDataField.getText()));
    		
    		main.getVertexDataDouble().add(main.getUndirectedWeightedDouble().returnVertex(Double.parseDouble(inputDataField.getText())));
    		
    		main.getListOfDoubleVertices().add(main.getUndirectedWeightedDouble().returnVertex(Double.parseDouble(inputDataField.getText())));
    		
    	}else if(type.equals("String")) {
    		
    		main.getUndirectedWeightedString().addVertex(inputDataField.getText());
    		
    		main.getVertexDataString().add(main.getUndirectedWeightedString().returnVertex(inputDataField.getText()));
    		
    		main.getListOfStringVertices().add(main.getUndirectedWeightedString().returnVertex(inputDataField.getText()));
    		
    	}
    }
	
	 private void addVertexToDirectedNonWeightedGraph(String type) {
	    	
	    	if(type.equals("Integer") && main.getDirectedNonWeightedInt().containsVertex(Integer.parseInt(inputDataField.getText()))) {
	    		Alert alert = new Alert(AlertType.ERROR);
	            alert.initOwner(dialogStage);
	            alert.setTitle("Duplicate Vertex");
	            alert.setHeaderText("Cannot add duplicate vertices");
	            alert.setContentText("A vertex with data " + Integer.parseInt(inputDataField.getText()) + " is already in the graph");
	            alert.showAndWait();
	    	}else if(type.equals("Double") && main.getDirectedNonWeightedDouble().containsVertex(Double.parseDouble(inputDataField.getText()))) {
	    		Alert alert = new Alert(AlertType.ERROR);
	            alert.initOwner(dialogStage);
	            alert.setTitle("Duplicate Vertex");
	            alert.setHeaderText("Cannot add duplicate vertices");
	            alert.setContentText("A vertex with data " + Double.parseDouble(inputDataField.getText()) + " is already in the graph");
	            alert.showAndWait();
	    	}else if(type.equals("String") && main.getDirectedNonWeightedString().containsVertex(inputDataField.getText())) {
	    		Alert alert = new Alert(AlertType.ERROR);
	            alert.initOwner(dialogStage);
	            alert.setTitle("Duplicate Vertex");
	            alert.setHeaderText("Cannot add duplicate vertices");
	            alert.setContentText("A vertex with data " + inputDataField.getText() + " is already in the graph");
	            alert.showAndWait();
	    	}
	    	
	    	if(type.equals("Integer")) {
	    		
	    		main.getDirectedNonWeightedInt().addVertex(Integer.parseInt(inputDataField.getText()));
	    		
	    		main.getVertexDataInt().add(main.getDirectedNonWeightedInt().returnVertex(Integer.parseInt(inputDataField.getText())));
	    		
	    		main.getListOfIntVertices().add(main.getDirectedNonWeightedInt().returnVertex(Integer.parseInt(inputDataField.getText())));
	    		
	    	}else if(type.equals("Double")) {
	    		main.getDirectedNonWeightedDouble().addVertex(Double.parseDouble(inputDataField.getText()));
	    		
	    		main.getVertexDataDouble().add(main.getDirectedNonWeightedDouble().returnVertex(Double.parseDouble(inputDataField.getText())));
	    		
	    		main.getListOfDoubleVertices().add(main.getDirectedNonWeightedDouble().returnVertex(Double.parseDouble(inputDataField.getText())));
	    		
	    	}else if(type.equals("String")) {
	    		
	    		main.getDirectedNonWeightedString().addVertex(inputDataField.getText());
	    		
	    		main.getVertexDataString().add(main.getDirectedNonWeightedString().returnVertex(inputDataField.getText()));
	    		
	    		main.getListOfStringVertices().add(main.getDirectedNonWeightedString().returnVertex(inputDataField.getText()));
	    		
	    	}
	    }
	 
	 private void addVertexToDirectedWeightedGraph(String type) {
	    	
	    	if(type.equals("Integer") && main.getDirectedWeightedInt().containsVertex(Integer.parseInt(inputDataField.getText()))) {
	    		Alert alert = new Alert(AlertType.ERROR);
	            alert.initOwner(dialogStage);
	            alert.setTitle("Duplicate Vertex");
	            alert.setHeaderText("Cannot add duplicate vertices");
	            alert.setContentText("A vertex with data " + Integer.parseInt(inputDataField.getText()) + " is already in the graph");
	            alert.showAndWait();
	    	}else if(type.equals("Double") && main.getDirectedWeightedDouble().containsVertex(Double.parseDouble(inputDataField.getText()))) {
	    		Alert alert = new Alert(AlertType.ERROR);
	            alert.initOwner(dialogStage);
	            alert.setTitle("Duplicate Vertex");
	            alert.setHeaderText("Cannot add duplicate vertices");
	            alert.setContentText("A vertex with data " + Double.parseDouble(inputDataField.getText()) + " is already in the graph");
	            alert.showAndWait();
	    	}else if(type.equals("String") && main.getDirectedWeightedString().containsVertex(inputDataField.getText())) {
	    		Alert alert = new Alert(AlertType.ERROR);
	            alert.initOwner(dialogStage);
	            alert.setTitle("Duplicate Vertex");
	            alert.setHeaderText("Cannot add duplicate vertices");
	            alert.setContentText("A vertex with data " + inputDataField.getText() + " is already in the graph");
	            alert.showAndWait();
	    	}
	    	
	    	
	    	if(type.equals("Integer")) {
	    		
	    		main.getDirectedWeightedInt().addVertex(Integer.parseInt(inputDataField.getText()));
	    		
	    		main.getVertexDataInt().add(main.getDirectedWeightedInt().returnVertex(Integer.parseInt(inputDataField.getText())));
	    		
	    		main.getListOfIntVertices().add(main.getDirectedWeightedInt().returnVertex(Integer.parseInt(inputDataField.getText())));
	    		
	    	}else if(type.equals("Double")) {
	    		
	    		main.getDirectedWeightedDouble().addVertex(Double.parseDouble(inputDataField.getText()));
	    		
	    		main.getVertexDataDouble().add(main.getDirectedWeightedDouble().returnVertex(Double.parseDouble(inputDataField.getText())));
	    		
	    		main.getListOfDoubleVertices().add(main.getDirectedWeightedDouble().returnVertex(Double.parseDouble(inputDataField.getText())));
	    		
	    	}else if(type.equals("String")) {
	    		
	    		main.getDirectedWeightedString().addVertex(inputDataField.getText());
	    		
	    		main.getVertexDataString().add(main.getDirectedWeightedString().returnVertex(inputDataField.getText()));
	    		
	    		main.getListOfStringVertices().add(main.getDirectedWeightedString().returnVertex(inputDataField.getText()));
	    		
	    	}
	    }
    
	
}
