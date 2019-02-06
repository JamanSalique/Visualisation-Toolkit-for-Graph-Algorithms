package application.view;

import javafx.animation.FillTransition;
import javafx.animation.StrokeTransition;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.util.Duration;

/**
 * This class holds the methods of different transition animations that can be played on graphs.
 * @author jamansalique
 *
 */
public class Animations {
	
	private GraphPanelController gpc;
	private int directedEdgePlacement = 13;
	
	/**
	 * Constructor initialising GraphPanelController class.
	 * @param gpc
	 */
	public Animations(GraphPanelController gpc) {
		this.gpc = gpc;
	}
	
	/**
	 * This method given the data of 2 vertices and the graph type will highlight the edge between these 2 vertices.
	 * @param v1Data
	 * @param v2Data
	 * @param graphType
	 * @return
	 */
	public StrokeTransition highlightEdgeTransition(String v1Data, String v2Data, String graphType) {
		
		
		StackPane v1 = returnStackPane(v1Data,graphType);
		StackPane v2 = returnStackPane(v2Data,graphType);
		
		Line edge = returnEdgeBetweenVertices(v1,v2,graphType);
		
		return new StrokeTransition(Duration.millis(50000), edge, Color.BLACK, Color.LIGHTGREEN);
		
	}
	
	/**
	 * This method given data of a vertex and the graph type, will highlight the specific vertex holding the data given.
	 * @param vertexData
	 * @param graphType
	 * @return
	 */
	public FillTransition fillVertexTransition(String vertexData, String graphType) {

			StackPane vertex = returnStackPane(vertexData,graphType);
			Circle circle = (Circle) vertex.getChildren().get(0);
			return new FillTransition(Duration.millis(50000), circle, Color.WHITE, Color.LIGHTGREEN);

	}
	
	/**
	 * Highlight and edge between 2 vertices a specific colour that is given as a parameter.
	 * @param v1Data
	 * @param v2Data
	 * @param graphType
	 * @param c
	 * @return
	 */
	public StrokeTransition highlightEdgeTransitionChooseColour(String v1Data, String v2Data, String graphType,Color c) {
		
		
		StackPane v1 = returnStackPane(v1Data,graphType);
		StackPane v2 = returnStackPane(v2Data,graphType);
		
		Line edge = returnEdgeBetweenVertices(v1,v2,graphType);
		
		return new StrokeTransition(Duration.millis(50000), edge, Color.BLACK, c);
		
	}
	
	/**
	 * Highlight a specific vertex a specific colour given as a parameter.
	 * @param vertexData
	 * @param graphType
	 * @param c
	 * @return
	 */
	public FillTransition fillVertexTransitionChooseColour(String vertexData, String graphType,Color c) {

			StackPane vertex = returnStackPane(vertexData,graphType);
			Circle circle = (Circle) vertex.getChildren().get(0);
			return new FillTransition(Duration.millis(50000), circle, Color.WHITE, c);

	}
	/**
	 * Helper method to find the Line shape between 2 vertices in the GUI.
	 * @param v1
	 * @param v2
	 * @param graphType
	 * @return
	 */
	private Line returnEdgeBetweenVertices(StackPane v1, StackPane v2,String graphType) {
		
		double v1X = v1.getLayoutX() + (v1.getWidth() / 2);
		double v1Y = v1.getLayoutY() + (v1.getHeight() / 2);
		double v2X = v2.getLayoutX() + (v2.getWidth() / 2);
		double v2Y = v2.getLayoutY() + (v2.getHeight() / 2);
		
		if(graphType.equals("Undirected Non Weighted")) {
			
			for(Node child:gpc.getCenterPaneUndirectedNonWeightedGraph().getChildren()) {
				
				if(child instanceof Line) {
					Line edge = (Line) child;
					
					if((edge.getStartX() == v1X && edge.getStartY() == v1Y && edge.getEndX() == v2X && edge.getEndY() == v2Y) ||
							(edge.getStartX() == v2X && edge.getStartY() == v2Y && edge.getEndX() == v1X && edge.getEndY() == v1Y)) {
						return edge;
					}
					
				}
				
			}
			
		}else if (graphType.equals("Undirected Weighted")) {
			
			for(Node child:gpc.getCenterPaneUndirectedWeightedGraph().getChildren()) {
				
				if(child instanceof Line) {
					Line edge = (Line) child;
					
					if((edge.getStartX() == v1X && edge.getStartY() == v1Y && edge.getEndX() == v2X && edge.getEndY() == v2Y) ||
							(edge.getStartX() == v2X && edge.getStartY() == v2Y && edge.getEndX() == v1X && edge.getEndY() == v1Y)) {
						return edge;
					}
					
				}
				
			}
			
		}else if (graphType.equals("Directed Non Weighted")) {
			
			for(Node child:gpc.getCenterPaneDirectedNonWeightedGraph().getChildren()) {
				
				if(child instanceof Line) {
					Line edge = (Line) child;
					
					if(((edge.getStartX() == v1X + directedEdgePlacement && edge.getStartY() == v1Y + directedEdgePlacement) && 
            				(edge.getEndX() == v2X + directedEdgePlacement && edge.getEndY() == v2Y + directedEdgePlacement)) ||
            				((edge.getStartX() == v1X - directedEdgePlacement && edge.getStartY() == v1Y - directedEdgePlacement) && 
     					 (edge.getEndX() == v2X - directedEdgePlacement && edge.getEndY() == v2Y - directedEdgePlacement))) {
						
						return edge;
					}
					
				}
				
			}
			
		}else if (graphType.equals("Directed Weighted")) {
			
			for(Node child:gpc.getCenterPaneDirectedWeightedGraph().getChildren()) {
				
				if(child instanceof Line) {
					Line edge = (Line) child;
					
					if(((edge.getStartX() == v1X + directedEdgePlacement && edge.getStartY() == v1Y + directedEdgePlacement) && 
            				(edge.getEndX() == v2X + directedEdgePlacement && edge.getEndY() == v2Y + directedEdgePlacement)) ||
            				((edge.getStartX() == v1X - directedEdgePlacement && edge.getStartY() == v1Y - directedEdgePlacement) && 
     					 (edge.getEndX() == v2X - directedEdgePlacement && edge.getEndY() == v2Y - directedEdgePlacement))) {
						
						return edge;
					}
					
				}
				
			}
			
		}
		
		
		return null;
		
	}
	
	/**
	 * Helper method to return the Vertex shape in the GUI given the data this vertex holds.
	 * @param v
	 * @param graphType
	 * @return
	 */
	private StackPane returnStackPane(String v,String graphType) {
		
		if(graphType.equals("Undirected Non Weighted")) {
			
			for(Node child:gpc.getCenterPaneUndirectedNonWeightedGraph().getChildren()) {
				if(child instanceof StackPane) {
					StackPane potentialStackPane = (StackPane) child;
					String data = ((Text) potentialStackPane.getChildren().get(1)).getText();
					if(data.equals(v)) {
						return potentialStackPane;
					}
				}
			}
			
		}else if(graphType.equals("Undirected Weighted")) {
			
			for(Node child:gpc.getCenterPaneUndirectedWeightedGraph().getChildren()) {
				if(child instanceof StackPane) {
					StackPane potentialStackPane = (StackPane) child;
					String data = ((Text) potentialStackPane.getChildren().get(1)).getText();
					if(data.equals(v)) {
						return potentialStackPane;
					}
				}
			}
			
		}else if(graphType.equals("Directed Non Weighted")) {
			
			for(Node child:gpc.getCenterPaneDirectedNonWeightedGraph().getChildren()) {
				if(child instanceof StackPane) {
					StackPane potentialStackPane = (StackPane) child;
					String data = ((Text) potentialStackPane.getChildren().get(1)).getText();
					if(data.equals(v)) {
						return potentialStackPane;
					}
				}
			}
			
		}else if(graphType.equals("Directed Weighted")) {
			
			for(Node child:gpc.getCenterPaneDirectedWeightedGraph().getChildren()) {
				if(child instanceof StackPane) {
					StackPane potentialStackPane = (StackPane) child;
					String data = ((Text) potentialStackPane.getChildren().get(1)).getText();
					if(data.equals(v)) {
						return potentialStackPane;
					}
				}
			}
			
		}
		
		return null;
		
	}
	
	/**
	 * Sets colors of vertices and edges back to its default colours in the GUI.
	 * @param graphType
	 */
	public void resetGraphColours(String graphType) {
		
		if(graphType.equals("Undirected Non Weighted")) {
			
			for(Node child : gpc.getCenterPaneUndirectedNonWeightedGraph().getChildren()) {
				if(child instanceof StackPane) {
					StackPane vertex = (StackPane) child;
					Circle circle = (Circle) vertex.getChildren().get(0);
					circle.setFill(Color.WHITE);
				}
				
				if(child instanceof Line) {
					Line edge = (Line) child;
					edge.setStroke(Color.BLACK);
				}
			}
			
		}else if(graphType.equals("Undirected Weighted")) {
			
			for(Node child : gpc.getCenterPaneUndirectedWeightedGraph().getChildren()) {
				if(child instanceof StackPane) {
					StackPane vertex = (StackPane) child;
					Circle circle = (Circle) vertex.getChildren().get(0);
					circle.setFill(Color.WHITE);
				}
				
				if(child instanceof Line) {
					Line edge = (Line) child;
					edge.setStroke(Color.BLACK);
				}
			}
			
		}else if(graphType.equals("Directed Non Weighted")) {
			
			for(Node child : gpc.getCenterPaneDirectedNonWeightedGraph().getChildren()) {
				if(child instanceof StackPane) {
					StackPane vertex = (StackPane) child;
					Circle circle = (Circle) vertex.getChildren().get(0);
					circle.setFill(Color.WHITE);
				}
				
				if(child instanceof Line) {
					Line edge = (Line) child;
					edge.setStroke(Color.BLACK);
				}
			}
			
		}else if(graphType.equals("Directed Weighted")) {
			
			for(Node child : gpc.getCenterPaneDirectedWeightedGraph().getChildren()) {
				if(child instanceof StackPane) {
					StackPane vertex = (StackPane) child;
					Circle circle = (Circle) vertex.getChildren().get(0);
					circle.setFill(Color.WHITE);
				}
				
				if(child instanceof Line) {
					Line edge = (Line) child;
					edge.setStroke(Color.BLACK);
				}
			}
			
		}
		
	}
}
