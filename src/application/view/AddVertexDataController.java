package application.view;

import application.Main;
import application.model.DataModel;
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
        	System.out.println(dataModel.getUndirectedNonWeightedDouble());
        	System.out.println(dataModel.getUndirectedNonWeightedInt());
        	System.out.println(dataModel.getUndirectedNonWeightedString());
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
    	
    	if(type.equals("Integer") && dataModel.getUndirectedNonWeightedInt().containsVertex(Integer.parseInt(inputDataField.getText()))) {
    		Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Duplicate Vertex");
            alert.setHeaderText("Cannot add duplicate vertices");
            alert.setContentText("A vertex with data " + Integer.parseInt(inputDataField.getText()) + " is already in the graph");
            alert.showAndWait();
    	}else if(type.equals("Double") && dataModel.getUndirectedNonWeightedDouble().containsVertex(Double.parseDouble(inputDataField.getText()))) {
    		Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Duplicate Vertex");
            alert.setHeaderText("Cannot add duplicate vertices");
            alert.setContentText("A vertex with data " + Double.parseDouble(inputDataField.getText()) + " is already in the graph");
            alert.showAndWait();
    	}else if(type.equals("String") && dataModel.getUndirectedNonWeightedString().containsVertex(inputDataField.getText())) {
    		Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Duplicate Vertex");
            alert.setHeaderText("Cannot add duplicate vertices");
            alert.setContentText("A vertex with data " + inputDataField.getText() + " is already in the graph");
            alert.showAndWait();
    	}
    	
    	if(type.equals("Integer")) {
    		
    		dataModel.getUndirectedNonWeightedInt().addVertex(Integer.parseInt(inputDataField.getText()));
    		
    		dataModel.getVertexDataInt().add(dataModel.getUndirectedNonWeightedInt().returnVertex(Integer.parseInt(inputDataField.getText())));
    		
    		dataModel.getListOfIntVertices().add(dataModel.getUndirectedNonWeightedInt().returnVertex(Integer.parseInt(inputDataField.getText())));
    		
    	}else if(type.equals("Double")) {
    		dataModel.getUndirectedNonWeightedDouble().addVertex(Double.parseDouble(inputDataField.getText()));
    		
    		dataModel.getVertexDataDouble().add(dataModel.getUndirectedNonWeightedDouble().returnVertex(Double.parseDouble(inputDataField.getText())));
    		
    		dataModel.getListOfDoubleVertices().add(dataModel.getUndirectedNonWeightedDouble().returnVertex(Double.parseDouble(inputDataField.getText())));
    		
    	}else if(type.equals("String")) {
    		
    		dataModel.getUndirectedNonWeightedString().addVertex(inputDataField.getText());
    		
    		dataModel.getVertexDataString().add(dataModel.getUndirectedNonWeightedString().returnVertex(inputDataField.getText()));
    		
    		dataModel.getListOfStringVertices().add(dataModel.getUndirectedNonWeightedString().returnVertex(inputDataField.getText()));
    		
    	}
    }
    
	private void addVertexToUndirectedWeightedGraph(String type) {
    	
    	if(type.equals("Integer") && dataModel.getUndirectedWeightedInt().containsVertex(Integer.parseInt(inputDataField.getText()))) {
    		Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Duplicate Vertex");
            alert.setHeaderText("Cannot add duplicate vertices");
            alert.setContentText("A vertex with data " + Integer.parseInt(inputDataField.getText()) + " is already in the graph");
            alert.showAndWait();
    	}else if(type.equals("Double") && dataModel.getUndirectedWeightedDouble().containsVertex(Double.parseDouble(inputDataField.getText()))) {
    		Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Duplicate Vertex");
            alert.setHeaderText("Cannot add duplicate vertices");
            alert.setContentText("A vertex with data " + Double.parseDouble(inputDataField.getText()) + " is already in the graph");
            alert.showAndWait();
    	}else if(type.equals("String") && dataModel.getUndirectedWeightedString().containsVertex(inputDataField.getText())) {
    		Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Duplicate Vertex");
            alert.setHeaderText("Cannot add duplicate vertices");
            alert.setContentText("A vertex with data " + inputDataField.getText() + " is already in the graph");
            alert.showAndWait();
    	}
    	
    	
    	if(type.equals("Integer")) {
    		
    		dataModel.getUndirectedWeightedInt().addVertex(Integer.parseInt(inputDataField.getText()));
    		
    		dataModel.getVertexDataInt().add(dataModel.getUndirectedWeightedInt().returnVertex(Integer.parseInt(inputDataField.getText())));
    		
    		dataModel.getListOfIntVertices().add(dataModel.getUndirectedWeightedInt().returnVertex(Integer.parseInt(inputDataField.getText())));
    		
    	}else if(type.equals("Double")) {
    		
    		dataModel.getUndirectedWeightedDouble().addVertex(Double.parseDouble(inputDataField.getText()));
    		
    		dataModel.getVertexDataDouble().add(dataModel.getUndirectedWeightedDouble().returnVertex(Double.parseDouble(inputDataField.getText())));
    		
    		dataModel.getListOfDoubleVertices().add(dataModel.getUndirectedWeightedDouble().returnVertex(Double.parseDouble(inputDataField.getText())));
    		
    	}else if(type.equals("String")) {
    		
    		dataModel.getUndirectedWeightedString().addVertex(inputDataField.getText());
    		
    		dataModel.getVertexDataString().add(dataModel.getUndirectedWeightedString().returnVertex(inputDataField.getText()));
    		
    		dataModel.getListOfStringVertices().add(dataModel.getUndirectedWeightedString().returnVertex(inputDataField.getText()));
    		
    	}
    }
	
	 private void addVertexToDirectedNonWeightedGraph(String type) {
	    	
	    	if(type.equals("Integer") && dataModel.getDirectedNonWeightedInt().containsVertex(Integer.parseInt(inputDataField.getText()))) {
	    		Alert alert = new Alert(AlertType.ERROR);
	            alert.initOwner(dialogStage);
	            alert.setTitle("Duplicate Vertex");
	            alert.setHeaderText("Cannot add duplicate vertices");
	            alert.setContentText("A vertex with data " + Integer.parseInt(inputDataField.getText()) + " is already in the graph");
	            alert.showAndWait();
	    	}else if(type.equals("Double") && dataModel.getDirectedNonWeightedDouble().containsVertex(Double.parseDouble(inputDataField.getText()))) {
	    		Alert alert = new Alert(AlertType.ERROR);
	            alert.initOwner(dialogStage);
	            alert.setTitle("Duplicate Vertex");
	            alert.setHeaderText("Cannot add duplicate vertices");
	            alert.setContentText("A vertex with data " + Double.parseDouble(inputDataField.getText()) + " is already in the graph");
	            alert.showAndWait();
	    	}else if(type.equals("String") && dataModel.getDirectedNonWeightedString().containsVertex(inputDataField.getText())) {
	    		Alert alert = new Alert(AlertType.ERROR);
	            alert.initOwner(dialogStage);
	            alert.setTitle("Duplicate Vertex");
	            alert.setHeaderText("Cannot add duplicate vertices");
	            alert.setContentText("A vertex with data " + inputDataField.getText() + " is already in the graph");
	            alert.showAndWait();
	    	}
	    	
	    	if(type.equals("Integer")) {
	    		
	    		dataModel.getDirectedNonWeightedInt().addVertex(Integer.parseInt(inputDataField.getText()));
	    		
	    		dataModel.getVertexDataInt().add(dataModel.getDirectedNonWeightedInt().returnVertex(Integer.parseInt(inputDataField.getText())));
	    		
	    		dataModel.getListOfIntVertices().add(dataModel.getDirectedNonWeightedInt().returnVertex(Integer.parseInt(inputDataField.getText())));
	    		
	    	}else if(type.equals("Double")) {
	    		dataModel.getDirectedNonWeightedDouble().addVertex(Double.parseDouble(inputDataField.getText()));
	    		
	    		dataModel.getVertexDataDouble().add(dataModel.getDirectedNonWeightedDouble().returnVertex(Double.parseDouble(inputDataField.getText())));
	    		
	    		dataModel.getListOfDoubleVertices().add(dataModel.getDirectedNonWeightedDouble().returnVertex(Double.parseDouble(inputDataField.getText())));
	    		
	    	}else if(type.equals("String")) {
	    		
	    		dataModel.getDirectedNonWeightedString().addVertex(inputDataField.getText());
	    		
	    		dataModel.getVertexDataString().add(dataModel.getDirectedNonWeightedString().returnVertex(inputDataField.getText()));
	    		
	    		dataModel.getListOfStringVertices().add(dataModel.getDirectedNonWeightedString().returnVertex(inputDataField.getText()));
	    		
	    	}
	    }
	 
	 private void addVertexToDirectedWeightedGraph(String type) {
	    	
	    	if(type.equals("Integer") && dataModel.getDirectedWeightedInt().containsVertex(Integer.parseInt(inputDataField.getText()))) {
	    		Alert alert = new Alert(AlertType.ERROR);
	            alert.initOwner(dialogStage);
	            alert.setTitle("Duplicate Vertex");
	            alert.setHeaderText("Cannot add duplicate vertices");
	            alert.setContentText("A vertex with data " + Integer.parseInt(inputDataField.getText()) + " is already in the graph");
	            alert.showAndWait();
	    	}else if(type.equals("Double") && dataModel.getDirectedWeightedDouble().containsVertex(Double.parseDouble(inputDataField.getText()))) {
	    		Alert alert = new Alert(AlertType.ERROR);
	            alert.initOwner(dialogStage);
	            alert.setTitle("Duplicate Vertex");
	            alert.setHeaderText("Cannot add duplicate vertices");
	            alert.setContentText("A vertex with data " + Double.parseDouble(inputDataField.getText()) + " is already in the graph");
	            alert.showAndWait();
	    	}else if(type.equals("String") && dataModel.getDirectedWeightedString().containsVertex(inputDataField.getText())) {
	    		Alert alert = new Alert(AlertType.ERROR);
	            alert.initOwner(dialogStage);
	            alert.setTitle("Duplicate Vertex");
	            alert.setHeaderText("Cannot add duplicate vertices");
	            alert.setContentText("A vertex with data " + inputDataField.getText() + " is already in the graph");
	            alert.showAndWait();
	    	}
	    	
	    	
	    	if(type.equals("Integer")) {
	    		
	    		dataModel.getDirectedWeightedInt().addVertex(Integer.parseInt(inputDataField.getText()));
	    		
	    		dataModel.getVertexDataInt().add(dataModel.getDirectedWeightedInt().returnVertex(Integer.parseInt(inputDataField.getText())));
	    		
	    		dataModel.getListOfIntVertices().add(dataModel.getDirectedWeightedInt().returnVertex(Integer.parseInt(inputDataField.getText())));
	    		
	    	}else if(type.equals("Double")) {
	    		
	    		dataModel.getDirectedWeightedDouble().addVertex(Double.parseDouble(inputDataField.getText()));
	    		
	    		dataModel.getVertexDataDouble().add(dataModel.getDirectedWeightedDouble().returnVertex(Double.parseDouble(inputDataField.getText())));
	    		
	    		dataModel.getListOfDoubleVertices().add(dataModel.getDirectedWeightedDouble().returnVertex(Double.parseDouble(inputDataField.getText())));
	    		
	    	}else if(type.equals("String")) {
	    		
	    		dataModel.getDirectedWeightedString().addVertex(inputDataField.getText());
	    		
	    		dataModel.getVertexDataString().add(dataModel.getDirectedWeightedString().returnVertex(inputDataField.getText()));
	    		
	    		dataModel.getListOfStringVertices().add(dataModel.getDirectedWeightedString().returnVertex(inputDataField.getText()));
	    		
	    	}
	    }
	 
	 public void setDataModel(DataModel dataModel) {
			this.dataModel = dataModel;
		}
    
	
}
