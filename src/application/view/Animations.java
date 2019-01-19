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
	
	public Animations(GraphPanelController gpc) {
		this.gpc = gpc;
	}

	public void fillVertexTransition(StackPane vertex) {
		Circle circle = (Circle) vertex.getChildren().get(0);
		
		FillTransition ft = new FillTransition(Duration.millis(3000), circle, Color.WHITE, Color.GREEN);
		ft.play();
		
	}
	
	public StrokeTransition highlightEdgeTransition(String v1Data, String v2Data) {
		
		StackPane v1 = returnStackPaneUndirectedNonWeighted(v1Data);
		StackPane v2 = returnStackPaneUndirectedNonWeighted(v2Data);
		
		Line edge = returnEdgeBetweenVerticesUndirectedNonWeighted(v1,v2);
		
		return new StrokeTransition(Duration.millis(3000), edge, Color.BLACK, Color.LIGHTGREEN);
		
	}
	
	public FillTransition fillVertexUndirectedNonWeighted(String vertexData) {
		
		for(Node child:gpc.getCenterPaneUndirectedNonWeightedGraph().getChildren()) {
			
			if(child instanceof StackPane) {
				
				StackPane vertex = (StackPane) child;
				Text data = (Text) vertex.getChildren().get(1);
				
				if(data.getText().equals(vertexData)) {
					
					Circle circle = (Circle) vertex.getChildren().get(0);
					
					return new FillTransition(Duration.millis(3000), circle, Color.WHITE, Color.LIGHTGREEN);
					
				}
				
			}
			
		}
		return null;
		
	}
	
	public Line returnEdgeBetweenVerticesUndirectedNonWeighted(StackPane v1, StackPane v2) {
		
		double v1X = v1.getLayoutX() + (v1.getWidth() / 2);
		double v1Y = v1.getLayoutY() + (v1.getHeight() / 2);
		double v2X = v2.getLayoutX() + (v2.getWidth() / 2);
		double v2Y = v2.getLayoutY() + (v2.getHeight() / 2);
		
		for(Node child:gpc.getCenterPaneUndirectedNonWeightedGraph().getChildren()) {
			
			if(child instanceof Line) {
				Line edge = (Line) child;
				
				if((edge.getStartX() == v1X && edge.getStartY() == v1Y && edge.getEndX() == v2X && edge.getEndY() == v2Y) ||
						(edge.getStartX() == v2X && edge.getStartY() == v2Y && edge.getEndX() == v1X && edge.getEndY() == v1Y)) {
					return edge;
				}
				
			}
			
		}
		return null;
		
	}
	
	public StackPane returnStackPaneUndirectedNonWeighted(String v) {
		
		for(Node child:gpc.getCenterPaneUndirectedNonWeightedGraph().getChildren()) {
			if(child instanceof StackPane) {
				StackPane potentialStackPane = (StackPane) child;
				String data = ((Text) potentialStackPane.getChildren().get(1)).getText();
				if(data.equals(v)) {
					return potentialStackPane;
				}
			}
		}
		System.out.println("284");
		return null;
		
	}
	
}
