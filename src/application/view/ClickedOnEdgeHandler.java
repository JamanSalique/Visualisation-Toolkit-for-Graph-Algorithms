package application.view;

import java.util.Optional;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 * This class holds all the functionality for when the user clicks on an edge on the gui.
 * @author jamansalique
 *
 */
public class ClickedOnEdgeHandler implements EventHandler<MouseEvent>{

	private GraphPanelController gpc;
	
	// This field is the distance a directed edge should be placed from the the source vertex of the edge.
	private double directedEdgePlacement = 13;
	
	// This field is the distance along the edge that the arrowhead (triangle) is placed.
	private final int selfEdgeArrowheadPlacement = 20;
	
	/**
	 * Constructor that initialises the GraphPanelController.
	 * @param gpc
	 */
	public ClickedOnEdgeHandler(GraphPanelController gpc) {
		this.gpc = gpc;
	}
	
	/**
	 * This is called when a user clicks on an edge. In this method I check if the user double clicks an edge. If they do this means they want to
	 * delete an edge so they will be shown a warning alert box asking if they are sure they want to delete the edge if they confirm then the edge is
	 * deleted. An edge is made up of multiple shapes depending on what type of graph it is.
	 * Undirected Non-Weighted graph edges are made up of just a Line shape.
	 * Undirected weighted graph edges are made up of a Line shape and a Rectangle with a label (weight box showing the weight of the edge).
	 * Directed non-weighted edges are made up of of a Line and a triangle (arrow head of edge), self pointing edges (loop edges) consist of a rectangle
	 * and a triangle (arrowhead).
	 * Directed weighted edges have the same shapes as the directed non weighted edges but with the extra rectangle with label to denote weight of the
	 * directed edges.
	 * The user can double click on any component of an edge and will still get alerted with the warning confirmation box.
	 * This method retrieves the edge (and all of its components part of this edge) that contains the source component of what the user double clicked
	 * and will remove this from the gui as well as updating the model.
	 */
	 @Override
     public void handle(MouseEvent t) {
     	if (t.getClickCount() == 2 && t.getButton() != MouseButton.SECONDARY && (t.getSource() instanceof Line || t.getSource() instanceof Label
     			|| t.getSource() instanceof Polygon || t.getSource() instanceof Rectangle)) {
     		 Alert alert = new Alert(AlertType.WARNING);
     		 
     		 ButtonType cancelButton = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
     		 ButtonType okButton = new ButtonType("Ok", ButtonData.OK_DONE);
     		 
              alert.setTitle("Deleting Edge.");
              alert.setHeaderText("Are you sure you want to delete this edge?");
              alert.setContentText("Once deleting the edge you cannot retrieve it back.");
              alert.getButtonTypes().setAll(okButton,cancelButton);
              alert.getDialogPane().getStylesheets().add("/application/global.css");
              alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
              Optional<ButtonType> result = alert.showAndWait();
              
              if(result.get() == okButton) {
             	 
             	 Line lineToRemove = null;
             	 Label weightBox = null;
             	 Polygon arrowheadToRemove = null;
             	 Rectangle rectangleToRemove = null;
             	 boolean polygonOnLine = false;
             	 boolean weightBoxOnLine = false;
             	 
             	 if(gpc.getSelectedTabName().equals("Undirected Non-Weighted Graph")) {
             		 lineToRemove = (Line) t.getSource();
             	 }else if(gpc.getSelectedTabName().equals("Directed Non-Weighted Graph")) {
             		 
             		 if(t.getSource() instanceof Line) {
             			 lineToRemove = (Line) t.getSource();
             		 }else if(t.getSource() instanceof Polygon) {
             			 
             			 arrowheadToRemove = (Polygon) t.getSource();
             			 
             			 for(Node child:gpc.getCenterPaneDirectedNonWeightedGraph().getChildren()) {
             				 
             				 if(child instanceof Line) {
             					 
             					 Line line = (Line) child;
             					 
             					 if(arrowheadToRemove.getLayoutX() == (line.getStartX() + line.getEndX()) / 2 && 
             						 arrowheadToRemove.getLayoutY() == (line.getStartY() + line.getEndY()) / 2) {
             						 
             						 polygonOnLine = true;
	                				}
             					 
             				 }
             				 
             			 }
             			 
             		 }else if(t.getSource() instanceof Rectangle) {
             			 rectangleToRemove = (Rectangle) t.getSource();
             		 }

             		 if(lineToRemove == null && polygonOnLine) {
             			 
             			 for(Node child:gpc.getCenterPaneDirectedNonWeightedGraph().getChildren()) {
             				 
             				 if(child instanceof Line) {
             					 
             					 Line line = (Line) child;
             					 
             					 if(arrowheadToRemove.getLayoutX() == (line.getStartX() + line.getEndX()) / 2 && 
             						 arrowheadToRemove.getLayoutY() == (line.getStartY() + line.getEndY()) / 2) {
             						 lineToRemove = line;
             						 
	                				}
             					 
             				 }
             				 
             			 }
             			 
             		 }else if(arrowheadToRemove == null && t.getSource() instanceof Line) {
             			 
             			 for(Node child:gpc.getCenterPaneDirectedNonWeightedGraph().getChildren()) {
             				 
             				 if(child instanceof Polygon) {
             					 
             					 Polygon arrowhead = (Polygon) child;
             					 
             					 if(arrowhead.getLayoutX() == (lineToRemove.getStartX() + lineToRemove.getEndX()) / 2 && 
             							 arrowhead.getLayoutY() == (lineToRemove.getStartY() + lineToRemove.getEndY()) / 2) {
             						 
             						 arrowheadToRemove = arrowhead;
             						 
	                				}
             					 
             				 }
             				 
             			 }
             			 
             		 }else if(arrowheadToRemove == null && t.getSource() instanceof Rectangle) {
             			 
             			 for(Node child:gpc.getCenterPaneDirectedNonWeightedGraph().getChildren()) {

             				 if(child instanceof Polygon) {
             					 
             					 Polygon arrowhead = (Polygon) child;
             					 
             					 if(arrowhead.getLayoutX() == rectangleToRemove.getX() + selfEdgeArrowheadPlacement && 
             							 arrowhead.getLayoutY() == rectangleToRemove.getY()) {
             						 
             						 arrowheadToRemove = arrowhead;
             						 
	                				}
             					 
             				 }
             				 
             			 }
             			 
             		 }else if(rectangleToRemove == null && !polygonOnLine) {
             			 
             			 for(Node child:gpc.getCenterPaneDirectedNonWeightedGraph().getChildren()) {

             				 if(child instanceof Rectangle) {
             					 
             					 Rectangle rect = (Rectangle) child;
             					 
             					 if(arrowheadToRemove.getLayoutX() == rect.getX() + selfEdgeArrowheadPlacement && 
             							 arrowheadToRemove.getLayoutY() == rect.getY()) {
             						 
             						 rectangleToRemove = rect;
             						 
	                				}
             					 
             				 }
             				 
             			 }
             			 
             		 }
             		 
             	 }else if(gpc.getSelectedTabName().equals("Undirected Weighted Graph") || gpc.getSelectedTabName().equals("Directed Weighted Graph")) {

         			 if(gpc.getSelectedTabName().equals("Undirected Weighted Graph")) {
         				 
         				 if(t.getSource() instanceof Line) {
	                			 lineToRemove = (Line) t.getSource();
	                		 }else if(t.getSource() instanceof Label) {
	                			 weightBox = (Label) t.getSource();
	                		 }
         				 
         				 if(lineToRemove == null) {
         					 
         					 for(Node child:gpc.getCenterPaneUndirectedWeightedGraph().getChildren()) {
 		                		 
             					 if(child instanceof Line) {
             						 
             						 Line line = (Line) child;
             						 
             						 if((line.getStartX() + line.getEndX() - weightBox.getWidth()) / 2 == weightBox.getLayoutX() && 
             								 (line.getStartY() + line.getEndY() - weightBox.getHeight()) / 2 == weightBox.getLayoutY()) {
             							 
             							 lineToRemove = line;
             							 
             						 }
             						 
             					 }
             					 
 		                	 }
         					 
         				 }else if(weightBox == null) {
		                		 
         					 for(Node child:gpc.getCenterPaneUndirectedWeightedGraph().getChildren()) {
 		                		 
             					 if(child instanceof Label) {
             						 
             						 Label weight = (Label) child;
             						 
             						 if((lineToRemove.getStartX() + lineToRemove.getEndX() - weight.getWidth()) / 2 == weight.getLayoutX() && 
             								 (lineToRemove.getStartY() + lineToRemove.getEndY() - weight.getHeight()) / 2 == weight.getLayoutY()) {
             							 
             							 weightBox = weight;
             							 
             						 }
             						 
             					 }
             					 
 		                	 }
         					 
		                }

         			 }else if(gpc.getSelectedTabName().equals("Directed Weighted Graph")) {
         				 
         				 if(t.getSource() instanceof Line) {
	                			 lineToRemove = (Line) t.getSource();
	                		 }else if(t.getSource() instanceof Polygon) {
	                			 
	                			 arrowheadToRemove = (Polygon) t.getSource();
	                			 
	                			 for(Node child:gpc.getCenterPaneDirectedWeightedGraph().getChildren()) {
	                				 
	                				 if(child instanceof Line) {
	                					 
	                					 Line line = (Line) child;
	                					 
	                					 if(arrowheadToRemove.getLayoutX() == (line.getStartX() + 4*line.getEndX()) / 5 && 
	                						 arrowheadToRemove.getLayoutY() == (line.getStartY() + 4*line.getEndY()) / 5) {
	                						 
	                						 polygonOnLine = true;
		                				}
	                					 
	                				 }
	                				 
	                			 }
	                			 
	                		 }else if(t.getSource() instanceof Rectangle) {
	                			 
	                			 rectangleToRemove = (Rectangle) t.getSource();
	                			 
	                		 }else if(t.getSource() instanceof Label) {
	                			 
	                			 weightBox = (Label) t.getSource();
	                			 
	                			 for(Node child:gpc.getCenterPaneDirectedWeightedGraph().getChildren()) {
	                				 
	                				 if(child instanceof Line) {
	                					 
	                					 Line line = (Line) child;
	                					 
	                					 if((line.getStartX() + line.getEndX() - weightBox.getWidth()) / 2 == weightBox.getLayoutX() && 
             								 (line.getStartY() + line.getEndY() - weightBox.getHeight()) / 2 == weightBox.getLayoutY()) {
             							 
             							 weightBoxOnLine = true;
             							 
             						 }
	                					 
	                				 }
	                				 
	                			 }
	                			 
	                		 }
         				 
         				 if(lineToRemove == null && t.getSource() instanceof Label && weightBoxOnLine) {
         					 
         					 for(Node child:gpc.getCenterPaneDirectedWeightedGraph().getChildren()) {
 		                		 
             					 if(child instanceof Line) {
             						 
             						 Line line = (Line) child;
             						 
             						 if((line.getStartX() + line.getEndX() - weightBox.getWidth()) / 2 == weightBox.getLayoutX() && 
             								 (line.getStartY() + line.getEndY() - weightBox.getHeight()) / 2 == weightBox.getLayoutY()) {
             							 
             							 lineToRemove = line;
             							 
             						 }
             						 
             					 }
             					 
 		                	 }
         					 
         					 for(Node child:gpc.getCenterPaneDirectedWeightedGraph().getChildren()) {
	                				 
	                				 if(child instanceof Polygon) {
	                					 
	                					 Polygon arrowhead = (Polygon) child;
	                					 
	                					 if(arrowhead.getLayoutX() == (lineToRemove.getStartX() + 4*lineToRemove.getEndX()) / 5 && 
	                							 arrowhead.getLayoutY() == (lineToRemove.getStartY() + 4*lineToRemove.getEndY()) / 5) {
	                						 arrowheadToRemove = arrowhead;
	                						 
		                				}
	                					 
	                				 }
	                				 
	                			 }
         					 
         				 }else if(lineToRemove == null && t.getSource() instanceof Polygon && polygonOnLine == true && weightBox == null) {
         					 
         					 for(Node child:gpc.getCenterPaneDirectedWeightedGraph().getChildren()) {
	                				 
	                				 if(child instanceof Line) {
	                					 
	                					 Line line = (Line) child;
	                					 
	                					 if(arrowheadToRemove.getLayoutX() == (line.getStartX() + 4*line.getEndX()) / 5 && 
	                						 arrowheadToRemove.getLayoutY() == (line.getStartY() + 4*line.getEndY()) / 5) {
	                						 lineToRemove = line;
	                						 
		                				}
	                					 
	                				 }
	                				 
	                			 }
         					 
         					 for(Node child:gpc.getCenterPaneDirectedWeightedGraph().getChildren()) {
 		                		 
             					 if(child instanceof Label) {
             						 
             						 Label weight = (Label) child;
             						 
             						 if((lineToRemove.getStartX() + lineToRemove.getEndX() - weight.getWidth()) / 2 == weight.getLayoutX() && 
             								 (lineToRemove.getStartY() + lineToRemove.getEndY() - weight.getHeight()) / 2 == weight.getLayoutY()) {
             							 
             							 weightBox = weight;
             							 
             						 }
             						 
             					 }
             					 
 		                	 }
         					 
         				 } else if(weightBox ==null && t.getSource() instanceof Line) {
         					 
         					 for(Node child:gpc.getCenterPaneDirectedWeightedGraph().getChildren()) {
 		                		 
             					 if(child instanceof Label) {
             						 
             						 Label weight = (Label) child;
             						 
             						 if((lineToRemove.getStartX() + lineToRemove.getEndX() - weight.getWidth()) / 2 == weight.getLayoutX() && 
             								 (lineToRemove.getStartY() + lineToRemove.getEndY() - weight.getHeight()) / 2 == weight.getLayoutY()) {
             							 
             							 weightBox = weight;
             							 
             						 }
             						 
             					 }
             					 
 		                	 }
         					 
         					 for(Node child:gpc.getCenterPaneDirectedWeightedGraph().getChildren()) {
	                				 
	                				 if(child instanceof Polygon) {
	                					 
	                					 Polygon arrowhead = (Polygon) child;
	                					 
	                					 if(arrowhead.getLayoutX() == (lineToRemove.getStartX() + 4*lineToRemove.getEndX()) / 5 && 
	                							 arrowhead.getLayoutY() == (lineToRemove.getStartY() + 4*lineToRemove.getEndY()) / 5) {
	                						 arrowheadToRemove = arrowhead;
	                						 
		                				}
	                					 
	                				 }
	                				 
	                			 }
         					 
         				 } else if(t.getSource() instanceof Label && !weightBoxOnLine && rectangleToRemove == null) {
         					 
         					 for(Node child:gpc.getCenterPaneDirectedWeightedGraph().getChildren()) {

	                				 if(child instanceof Rectangle) {
	                					 
	                					 Rectangle rect = (Rectangle) child;
	                					 
	                					 if(weightBox.getLayoutX() == (rect.getX()  + rect.getWidth()) - (weightBox.getWidth()/2) && 
	                							 weightBox.getLayoutY() == (rect.getY() + rect.getHeight()) - (weightBox.getHeight()/2)) {
	                						 
	                						 rectangleToRemove = rect;
	                						 
		                				}
	                					 
	                				 }
	                				 
	                			 }
         					 
         					 for(Node child:gpc.getCenterPaneDirectedWeightedGraph().getChildren()) {

	                				 if(child instanceof Polygon) {
	                					 
	                					 Polygon arrowhead = (Polygon) child;
	                					 
	                					 if(arrowhead.getLayoutX() == rectangleToRemove.getX() + selfEdgeArrowheadPlacement && 
	                							 arrowhead.getLayoutY() == rectangleToRemove.getY()) {
	                						 
	                						 arrowheadToRemove = arrowhead;
	                						 
		                				}
	                					 
	                				 }
	                				 
	                			 }
         					 
         					 
         				 }else if(t.getSource() instanceof Polygon && rectangleToRemove == null && !polygonOnLine) {
         					 
         					 for(Node child:gpc.getCenterPaneDirectedWeightedGraph().getChildren()) {

	                				 if(child instanceof Rectangle) {
	                					 
	                					 Rectangle rect = (Rectangle) child;
	                					 
	                					 if(arrowheadToRemove.getLayoutX() == rect.getX() + selfEdgeArrowheadPlacement && 
	                							 arrowheadToRemove.getLayoutY() == rect.getY()) {
	                						 
	                						 rectangleToRemove = rect;
	                						 
		                				}
	                					 
	                				 }
	                				 
	                			 }
         					 
         					 for(Node child:gpc.getCenterPaneDirectedWeightedGraph().getChildren()) {

	                				 if(child instanceof Label) {
	                					 
	                					 Label weight = (Label) child;
	                					 
	                					 if(weight.getLayoutX() == (rectangleToRemove.getX()  + rectangleToRemove.getWidth()) - (weight.getWidth()/2) && 
	                							 weight.getLayoutY() == (rectangleToRemove.getY() + rectangleToRemove.getHeight()) - (weight.getHeight()/2)) {
	                						 
	                						 weightBox = weight;
	                						 
		                				}
	                					 
	                				 }
	                				 
	                			 }
         					 
         					 
         				 }else if(t.getSource() instanceof Rectangle) {
         					 
         					 for(Node child:gpc.getCenterPaneDirectedWeightedGraph().getChildren()) {

	                				 if(child instanceof Label) {
	                					 
	                					 Label weight = (Label) child;
	                					 
	                					 if(weight.getLayoutX() == (rectangleToRemove.getX()  + rectangleToRemove.getWidth()) - (weight.getWidth()/2) && 
	                							 weight.getLayoutY() == (rectangleToRemove.getY() + rectangleToRemove.getHeight()) - (weight.getHeight()/2)) {
	                						 
	                						 weightBox = weight;
	                						 
		                				}
	                					 
	                				 }
	                				 
	                			 }
         					 
         					 for(Node child:gpc.getCenterPaneDirectedWeightedGraph().getChildren()) {

	                				 if(child instanceof Polygon) {
	                					 
	                					 Polygon arrowhead = (Polygon) child;
	                					 
	                					 if(arrowhead.getLayoutX() == rectangleToRemove.getX() + selfEdgeArrowheadPlacement && 
	                							 arrowhead.getLayoutY() == rectangleToRemove.getY()) {
	                						 
	                						 arrowheadToRemove = arrowhead;
	                						 
		                				}
	                					 
	                				 }
	                				 
	                			 }
         					 
         				 }
         				 
         				 
         				 
         			 }

             	 }

             	 StackPane vertexStart = null;
             	 StackPane vertexEnd = null;
             	 
             	 if(gpc.getSelectedTabName().equals("Undirected Non-Weighted Graph")) {

             		 for(Node child:gpc.getCenterPaneUndirectedNonWeightedGraph().getChildren()) {
	                		 if(child instanceof StackPane) {
	                			 if(child.getLayoutX() + ((Region) child).getWidth() / 2 == lineToRemove.getStartX() && 
	                					 child.getLayoutY() + ((Region) child).getHeight() / 2  == lineToRemove.getStartY()) {
	                				 
	                				 vertexStart = (StackPane) child;
	                			 }
	                		 }
	                	 }
	                	 
	                	 for(Node child:gpc.getCenterPaneUndirectedNonWeightedGraph().getChildren()) {

	                		 if(child instanceof StackPane) {
	                			 if(child.getLayoutX() + ((Region) child).getWidth() / 2 == lineToRemove.getEndX() && 
	                					 child.getLayoutY() + ((Region) child).getWidth() / 2 == lineToRemove.getEndY()) {
	                				 vertexEnd = (StackPane) child;
	                			 }
	                		 }
	                	 }
             		 
             	 }else if(gpc.getSelectedTabName().equals("Undirected Weighted Graph")) {
             		 
             		 for(Node child:gpc.getCenterPaneUndirectedWeightedGraph().getChildren()) {
	                		 if(child instanceof StackPane) {
	                			 if(child.getLayoutX() + ((Region) child).getWidth() / 2 == lineToRemove.getStartX() && 
	                					 child.getLayoutY() + ((Region) child).getHeight() / 2  == lineToRemove.getStartY()) {
	                				 
	                				 vertexStart = (StackPane) child;
	                			 }
	                		 }
	                	 }
	                	 
	                	 for(Node child:gpc.getCenterPaneUndirectedWeightedGraph().getChildren()) {

	                		 if(child instanceof StackPane) {
	                			 if(child.getLayoutX() + ((Region) child).getWidth() / 2 == lineToRemove.getEndX() && 
	                					 child.getLayoutY() + ((Region) child).getWidth() / 2 == lineToRemove.getEndY()) {
	                				 vertexEnd = (StackPane) child;
	                			 }
	                		 }
	                	 }
             		 
             	 }else if(gpc.getSelectedTabName().equals("Directed Non-Weighted Graph")) {
             		 
             		 if(lineToRemove != null) {
             			 
	                		 for(Node child:gpc.getCenterPaneDirectedNonWeightedGraph().getChildren()) {
		                		 if(child instanceof StackPane) {
		                			 if((child.getLayoutX() + ((Region) child).getWidth() / 2) + directedEdgePlacement == lineToRemove.getStartX() && 
		                					 (child.getLayoutY() + ((Region) child).getHeight() / 2) + directedEdgePlacement  == lineToRemove.getStartY()) {
		                				 
		                				 vertexStart = (StackPane) child;
		                			 }else if((child.getLayoutX() + ((Region) child).getWidth() / 2) - directedEdgePlacement == lineToRemove.getStartX() && 
		                					 (child.getLayoutY() + ((Region) child).getHeight() / 2) - directedEdgePlacement  == lineToRemove.getStartY()) {
		                				 
		                				 vertexStart = (StackPane) child;
		                			 }
		                		 }
		                	 }
		                	 
		                	 for(Node child:gpc.getCenterPaneDirectedNonWeightedGraph().getChildren()) {

		                		 if(child instanceof StackPane) {
		                			 if((child.getLayoutX() + ((Region) child).getWidth() / 2) + directedEdgePlacement == lineToRemove.getEndX() && 
		                					 (child.getLayoutY() + ((Region) child).getWidth() / 2) + directedEdgePlacement == lineToRemove.getEndY()) {
		                				 vertexEnd = (StackPane) child;
		                			 }else if((child.getLayoutX() + ((Region) child).getWidth() / 2) - directedEdgePlacement == lineToRemove.getEndX() && 
		                					 (child.getLayoutY() + ((Region) child).getWidth() / 2) - directedEdgePlacement == lineToRemove.getEndY()) {
		                				 vertexEnd = (StackPane) child;
		                			 }
		                		 }
		                	 }
		                	 
             		 }else if(rectangleToRemove != null) {
             			 
             			 for(Node child:gpc.getCenterPaneDirectedNonWeightedGraph().getChildren()) {
		                		 if(child instanceof StackPane) {
		                			 if((child.getLayoutX() + ((Region) child).getWidth() / 2) == rectangleToRemove.getX() && 
		                					 (child.getLayoutY() + ((Region) child).getHeight() / 2)  == rectangleToRemove.getY()) {
		                				 
		                				 vertexStart = (StackPane) child;
		                			 }
		                		 }
		                	 }
             			 
             			 vertexEnd =vertexStart;
             			 
             		 }
             		 

             		 
             	 }else if(gpc.getSelectedTabName().equals("Directed Weighted Graph")) {

             		 if(lineToRemove != null) {

	                		 for(Node child:gpc.getCenterPaneDirectedWeightedGraph().getChildren()) {
		                		 if(child instanceof StackPane) {

		                			 if((child.getLayoutX() + ((Region) child).getWidth() / 2) + directedEdgePlacement == lineToRemove.getStartX() && 
		                					 (child.getLayoutY() + ((Region) child).getHeight() / 2) + directedEdgePlacement  == lineToRemove.getStartY()) {
		                				 vertexStart = (StackPane) child;
		                			 }else if((child.getLayoutX() + ((Region) child).getWidth() / 2) - directedEdgePlacement == lineToRemove.getStartX() && 
		                					 (child.getLayoutY() + ((Region) child).getHeight() / 2) - directedEdgePlacement  == lineToRemove.getStartY()) {
		                				 vertexStart = (StackPane) child;
		                			 }
		                		 }
		                	 }
		                	 
		                	 for(Node child:gpc.getCenterPaneDirectedWeightedGraph().getChildren()) {

		                		 if(child instanceof StackPane) {
		                			 if((child.getLayoutX() + ((Region) child).getWidth() / 2) + directedEdgePlacement == lineToRemove.getEndX() && 
		                					 (child.getLayoutY() + ((Region) child).getWidth() / 2) + directedEdgePlacement == lineToRemove.getEndY()) {
		                				 vertexEnd = (StackPane) child;
		                			 }else if((child.getLayoutX() + ((Region) child).getWidth() / 2) - directedEdgePlacement == lineToRemove.getEndX() && 
		                					 (child.getLayoutY() + ((Region) child).getWidth() / 2) - directedEdgePlacement == lineToRemove.getEndY()) {
		                				 vertexEnd = (StackPane) child;
		                			 }
		                		 }
		                	 }
		                	 
             		 }else if(rectangleToRemove != null) {
             			 
             			 for(Node child:gpc.getCenterPaneDirectedWeightedGraph().getChildren()) {
		                		 if(child instanceof StackPane) {
		                			 if((child.getLayoutX() + ((Region) child).getWidth() / 2) == rectangleToRemove.getX() && 
		                					 (child.getLayoutY() + ((Region) child).getHeight() / 2)  == rectangleToRemove.getY()) {
		                				 
		                				 vertexStart = (StackPane) child;
		                			 }
		                		 }
		                	 }
             			 
             			 vertexEnd =vertexStart;
             			 
             		 }
             		 
             	 }

             	 ObservableList<Node> childsOfVertexStart = vertexStart.getChildren();
             	 Text dataOfVertexStart = (Text) childsOfVertexStart.get(childsOfVertexStart.size()-1);
             	 String dataOfVertexStartAsString = dataOfVertexStart.getText();
             	 
             	 ObservableList<Node> childsOfVertexEnd = vertexEnd.getChildren();
             	 Text dataOfVertexEnd = (Text) childsOfVertexEnd.get(childsOfVertexEnd.size()-1);
             	 String dataOfVertexEndAsString = dataOfVertexEnd.getText();
             	if(gpc.getSelectedTabName().equals("Undirected Non-Weighted Graph") && isInteger(dataOfVertexStartAsString) && isInteger(dataOfVertexEndAsString)) {
              		gpc.getCenterPaneUndirectedNonWeightedGraph().getChildren().remove(t.getSource());
              		gpc.getDataModel().getUndirectedNonWeightedInt().removeEdge(Integer.parseInt(dataOfVertexStartAsString), Integer.parseInt(dataOfVertexEndAsString));
              		
              	}else if(gpc.getSelectedTabName().equals("Undirected Non-Weighted Graph") && isDouble(dataOfVertexStartAsString) && isDouble(dataOfVertexEndAsString)) {
              		
              		gpc.getCenterPaneUndirectedNonWeightedGraph().getChildren().remove(t.getSource());
              		gpc.getDataModel().getUndirectedNonWeightedDouble().removeEdge(Double.parseDouble(dataOfVertexStartAsString), Double.parseDouble(dataOfVertexEndAsString));
              		
              	}else if(gpc.getSelectedTabName().equals("Undirected Non-Weighted Graph") && isString(dataOfVertexStartAsString) && isString(dataOfVertexEndAsString)) {
              		
              		gpc.getCenterPaneUndirectedNonWeightedGraph().getChildren().remove(t.getSource());
              		gpc.getDataModel().getUndirectedNonWeightedString().removeEdge(dataOfVertexStartAsString, dataOfVertexEndAsString);
              		
              	}
              	
              	else if(gpc.getSelectedTabName().equals("Undirected Weighted Graph") && isInteger(dataOfVertexStartAsString) && isInteger(dataOfVertexEndAsString)) {
              		
              		gpc.getCenterPaneUndirectedWeightedGraph().getChildren().remove(lineToRemove);
              		gpc.getCenterPaneUndirectedWeightedGraph().getChildren().remove(weightBox);
              		gpc.getDataModel().getUndirectedWeightedInt().removeEdge(Integer.parseInt(dataOfVertexStartAsString), Integer.parseInt(dataOfVertexEndAsString));
              		
              	}else if(gpc.getSelectedTabName().equals("Undirected Weighted Graph") && isDouble(dataOfVertexStartAsString) && isDouble(dataOfVertexEndAsString)) {
              		
              		gpc.getCenterPaneUndirectedWeightedGraph().getChildren().remove(lineToRemove);
              		gpc.getCenterPaneUndirectedWeightedGraph().getChildren().remove(weightBox);
              		gpc.getDataModel().getUndirectedWeightedDouble().removeEdge(Double.parseDouble(dataOfVertexStartAsString), Double.parseDouble(dataOfVertexEndAsString));
              		
              	}else if(gpc.getSelectedTabName().equals("Undirected Weighted Graph") && isString(dataOfVertexStartAsString) && isString(dataOfVertexEndAsString)) {
              		
              		gpc.getCenterPaneUndirectedWeightedGraph().getChildren().remove(lineToRemove);
              		gpc.getCenterPaneUndirectedWeightedGraph().getChildren().remove(weightBox);
              		gpc.getDataModel().getUndirectedWeightedString().removeEdge(dataOfVertexStartAsString, dataOfVertexEndAsString);
              		
              	}
              	
              	else if(gpc.getSelectedTabName().equals("Directed Non-Weighted Graph") && isInteger(dataOfVertexStartAsString) && isInteger(dataOfVertexEndAsString)) {
              		
              		if(t.getSource() instanceof Line || (t.getSource() instanceof Polygon && lineToRemove!=null)) {
              			gpc.getCenterPaneDirectedNonWeightedGraph().getChildren().remove(lineToRemove);
              			gpc.getCenterPaneDirectedNonWeightedGraph().getChildren().remove(arrowheadToRemove);
              		}else if(t.getSource() instanceof Rectangle || (t.getSource() instanceof Polygon && lineToRemove==null)) {
              			
              			gpc.getCenterPaneDirectedNonWeightedGraph().getChildren().remove(arrowheadToRemove);
              			gpc.getCenterPaneDirectedNonWeightedGraph().getChildren().remove(rectangleToRemove);
              			
              		}
              		
              		
              		gpc.getDataModel().getDirectedNonWeightedInt().removeEdge(Integer.parseInt(dataOfVertexStartAsString), Integer.parseInt(dataOfVertexEndAsString));
              		
              	}else if(gpc.getSelectedTabName().equals("Directed Non-Weighted Graph") && isDouble(dataOfVertexStartAsString) && isDouble(dataOfVertexEndAsString)) {
              		
              		if(t.getSource() instanceof Line || (t.getSource() instanceof Polygon && lineToRemove!=null)) {
              			gpc.getCenterPaneDirectedNonWeightedGraph().getChildren().remove(lineToRemove);
              			gpc.getCenterPaneDirectedNonWeightedGraph().getChildren().remove(arrowheadToRemove);
              		}else if(t.getSource() instanceof Rectangle || (t.getSource() instanceof Polygon && lineToRemove==null)) {
              			
              			gpc.getCenterPaneDirectedNonWeightedGraph().getChildren().remove(arrowheadToRemove);
              			gpc.getCenterPaneDirectedNonWeightedGraph().getChildren().remove(rectangleToRemove);
              			
              		}
              		gpc.getDataModel().getDirectedNonWeightedDouble().removeEdge(Double.parseDouble(dataOfVertexStartAsString), Double.parseDouble(dataOfVertexEndAsString));
              		
              	}else if(gpc.getSelectedTabName().equals("Directed Non-Weighted Graph") && isString(dataOfVertexStartAsString) && isString(dataOfVertexEndAsString)) {
              		
              		if(t.getSource() instanceof Line || (t.getSource() instanceof Polygon && lineToRemove!=null)) {
              			gpc.getCenterPaneDirectedNonWeightedGraph().getChildren().remove(lineToRemove);
              			gpc.getCenterPaneDirectedNonWeightedGraph().getChildren().remove(arrowheadToRemove);
              		}else if(t.getSource() instanceof Rectangle || (t.getSource() instanceof Polygon && lineToRemove==null)) {
              			
              			gpc.getCenterPaneDirectedNonWeightedGraph().getChildren().remove(arrowheadToRemove);
              			gpc.getCenterPaneDirectedNonWeightedGraph().getChildren().remove(rectangleToRemove);
              			
              		}
              		gpc.getDataModel().getDirectedNonWeightedString().removeEdge(dataOfVertexStartAsString, dataOfVertexEndAsString);
              		
              	}
              	
              	else if(gpc.getSelectedTabName().equals("Directed Weighted Graph") && isInteger(dataOfVertexStartAsString) && isInteger(dataOfVertexEndAsString)) {

              		if(t.getSource() instanceof Line || (t.getSource() instanceof Polygon && polygonOnLine) || 
              				(t.getSource() instanceof Label && weightBoxOnLine)) {
              			
              			gpc.getCenterPaneDirectedWeightedGraph().getChildren().remove(lineToRemove);
              			gpc.getCenterPaneDirectedWeightedGraph().getChildren().remove(arrowheadToRemove);
              			gpc.getCenterPaneDirectedWeightedGraph().getChildren().remove(weightBox);
              			
              		}else if(t.getSource() instanceof Rectangle || (t.getSource() instanceof Polygon && !polygonOnLine) || 
              				(t.getSource() instanceof Label && !weightBoxOnLine)) {
              			
              			gpc.getCenterPaneDirectedWeightedGraph().getChildren().remove(arrowheadToRemove);
              			gpc.getCenterPaneDirectedWeightedGraph().getChildren().remove(rectangleToRemove);
              			gpc.getCenterPaneDirectedWeightedGraph().getChildren().remove(weightBox);
              			
              		}
              		
              		gpc.getDataModel().getDirectedWeightedInt().removeEdge(Integer.parseInt(dataOfVertexStartAsString), Integer.parseInt(dataOfVertexEndAsString));
              		
              	}else if(gpc.getSelectedTabName().equals("Directed Weighted Graph") && isDouble(dataOfVertexStartAsString) && isDouble(dataOfVertexEndAsString)) {
              		
              		if(t.getSource() instanceof Line || (t.getSource() instanceof Polygon && polygonOnLine) || 
              				(t.getSource() instanceof Label && weightBoxOnLine)) {
              			
              			gpc.getCenterPaneDirectedWeightedGraph().getChildren().remove(lineToRemove);
              			gpc.getCenterPaneDirectedWeightedGraph().getChildren().remove(arrowheadToRemove);
              			gpc.getCenterPaneDirectedWeightedGraph().getChildren().remove(weightBox);
              			
              		}else if(t.getSource() instanceof Rectangle || (t.getSource() instanceof Polygon && !polygonOnLine) || 
              				(t.getSource() instanceof Label && !weightBoxOnLine)) {
              			
              			gpc.getCenterPaneDirectedWeightedGraph().getChildren().remove(arrowheadToRemove);
              			gpc.getCenterPaneDirectedWeightedGraph().getChildren().remove(rectangleToRemove);
              			gpc.getCenterPaneDirectedWeightedGraph().getChildren().remove(weightBox);
              			
              		}
              		
              		gpc.getDataModel().getDirectedWeightedDouble().removeEdge(Double.parseDouble(dataOfVertexStartAsString), Double.parseDouble(dataOfVertexEndAsString));
              		
              	}else if(gpc.getSelectedTabName().equals("Directed Weighted Graph") && isString(dataOfVertexStartAsString) && isString(dataOfVertexEndAsString)) {
              		
              		if(t.getSource() instanceof Line || (t.getSource() instanceof Polygon && polygonOnLine) || 
              				(t.getSource() instanceof Label && weightBoxOnLine)) {
              			
              			gpc.getCenterPaneDirectedWeightedGraph().getChildren().remove(lineToRemove);
              			gpc.getCenterPaneDirectedWeightedGraph().getChildren().remove(arrowheadToRemove);
              			gpc.getCenterPaneDirectedWeightedGraph().getChildren().remove(weightBox);
              			
              		}else if(t.getSource() instanceof Rectangle || (t.getSource() instanceof Polygon && !polygonOnLine) || 
              				(t.getSource() instanceof Label && !weightBoxOnLine)) {
              			
              			gpc.getCenterPaneDirectedWeightedGraph().getChildren().remove(arrowheadToRemove);
              			gpc.getCenterPaneDirectedWeightedGraph().getChildren().remove(rectangleToRemove);
              			gpc.getCenterPaneDirectedWeightedGraph().getChildren().remove(weightBox);
              			
              		}
              		
              		gpc.getDataModel().getDirectedWeightedString().removeEdge(dataOfVertexStartAsString, dataOfVertexEndAsString);
              		
              	}
             	
             	 t.consume();
              }
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
