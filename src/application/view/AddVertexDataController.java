package application.view;

import java.util.Scanner;

import application.model.Vertex;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddVertexDataController {

	@FXML
    private TextField inputDataField;
	
	private Stage dialogStage;
    private Vertex vertex;
    private boolean okClicked = false;
    private GraphPanelController graphPanelController;
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
        	
            okClicked = true;
            dialogStage.close();
        }
    }
    
    private boolean isInputValid() {
    	
    	String input = inputDataField.getText();
    	System.out.println(graphPanelController.getSelectedDataChoice());
    	String selectedDataChoice = graphPanelController.getSelectedDataChoice();
    	
    	
    	String errorMessage = "";
    	if (input == null || input.length() == 0) {
    		System.out.println("0");
    		errorMessage += "please enter some data";
    	}
    	else if(isInteger(input) && !selectedDataChoice.equals("Integer")) {
    		System.out.println("a");
    		errorMessage+="Invalid data type you must enter data of type " + selectedDataChoice;
    	}
    	else if(isDouble(input) && !selectedDataChoice.equals("Double")) {
    		System.out.println("b");
    		System.out.println(Double.parseDouble("5"));
    		errorMessage+="Invalid data type you must enter data of type " + selectedDataChoice;
    	}
    	else if(isString(input) && !selectedDataChoice.equals("String")) {
    		System.out.println("b");
    		System.out.println(Double.parseDouble("5"));
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
    
	
}
