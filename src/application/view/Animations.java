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

public class Animations {
	
	private GraphPanelController gpc;
	private int directedEdgePlacement = 13;
	
	public Animations(GraphPanelController gpc) {
		this.gpc = gpc;
	}
	
	public StrokeTransition highlightEdgeTransition(String v1Data, String v2Data, String graphType) {
		
		
		StackPane v1 = returnStackPane(v1Data,graphType);
		StackPane v2 = returnStackPane(v2Data,graphType);
		
		Line edge = returnEdgeBetweenVertices(v1,v2,graphType);
		
		return new StrokeTransition(Duration.millis(50000), edge, Color.BLACK, Color.LIGHTGREEN);
		
	}
	
	public FillTransition fillVertexTransition(String vertexData, String graphType) {

			StackPane vertex = returnStackPane(vertexData,graphType);
			Circle circle = (Circle) vertex.getChildren().get(0);
			return new FillTransition(Duration.millis(50000), circle, Color.WHITE, Color.LIGHTGREEN);

	}
	
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
