package application.view;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Pair;

/**
 * This class contains all the functionality of creating a randomly generated graph for all types of graphs.
 * @author jamansalique
 *
 */
public class RandomGraphGenerator {
	
	private GraphPanelController gpc;
	private ClickedOnVertexHandler covh;
	private int directedEdgePlacement = 13;
	
	/**
	 * Constructor...
	 * @param gpc
	 */
	public RandomGraphGenerator(GraphPanelController gpc) {
		
		this.gpc = gpc;
		this.covh = new ClickedOnVertexHandler(gpc,null);
		
	}
	
	/**
	 * This method is called after creating the random number of vertices. This method when called will create edges between random vertices on the GUI.
	 * The model is also updated accordingly.
	 * @param numberOfEdges
	 * @param numberOfVertices
	 */
	@SuppressWarnings("unchecked")
	public void createRandomEdges(int numberOfEdges,int numberOfVertices){
		
		String dataType = "";
		
		if(gpc.getSelectedTabName().equals("Undirected Non-Weighted Graph")) {
			
			 dataType = gpc.getSelectedDataChoiceUndirectedNonWeightedGraph();
			
		}else if(gpc.getSelectedTabName().equals("Undirected Weighted Graph")) {
			
			dataType = gpc.getSelectedDataChoiceUndirectedWeightedGraph();
			
		}else if(gpc.getSelectedTabName().equals("Directed Non-Weighted Graph")) {
			
			dataType = gpc.getSelectedDataChoiceDirectedNonWeightedGraph();
			
		}else if(gpc.getSelectedTabName().equals("Directed Weighted Graph")) {
			
			dataType = gpc.getSelectedDataChoiceDirectedWeightedGraph();
			
		}

		ArrayList pairsOfVertices = null;
		
		if(dataType.equals("Integer")) {
			pairsOfVertices = new ArrayList<Pair<Integer,Integer>>();
			ArrayList<Integer> listOfNumbers = new ArrayList<Integer>();
			
			for(int i=0;i<numberOfVertices;i++) {
				
				listOfNumbers.add(i);
				
			}
			Collections.shuffle(listOfNumbers);
			
			int vertex1 = listOfNumbers.get(0);
			int vertex2 =  listOfNumbers.get(1);
			Pair<Integer,Integer> pairToAdd = new Pair<Integer,Integer>(vertex1,vertex2);
			Pair<Integer,Integer> pairToAddReversed = new Pair<Integer,Integer>(vertex2,vertex1);
			
			int numEdges =numberOfEdges;
			
			if(numberOfEdges > numberOfVertices*(numberOfVertices-1)) {
				numEdges = numberOfVertices*(numberOfVertices-1);
			}
			
			for(int i=0;i<numEdges;i++) {
				
				if(gpc.getSelectedTabName().equals("Undirected Non-Weighted Graph") ||gpc.getSelectedTabName().equals("Undirected Weighted Graph")) {
					
					while(pairsOfVertices.contains(pairToAdd) || pairsOfVertices.contains(pairToAddReversed)) {
						
						Collections.shuffle(listOfNumbers);
						
						 vertex1 = listOfNumbers.get(0);
						 vertex2 =  listOfNumbers.get(1);
						
						pairToAdd = new Pair<Integer,Integer>(vertex1,vertex2);
						pairToAddReversed = new Pair<Integer,Integer>(vertex2,vertex1);
						
					}
					
					pairsOfVertices.add(pairToAdd);
					
				}else {
					
					while(pairsOfVertices.contains(pairToAdd)) {
						
						Collections.shuffle(listOfNumbers);
						
						 vertex1 = listOfNumbers.get(0);
						 vertex2 =  listOfNumbers.get(1);
						
						pairToAdd = new Pair<Integer,Integer>(vertex1,vertex2);
						pairToAddReversed = new Pair<Integer,Integer>(vertex2,vertex1);
						
					}
					
					pairsOfVertices.add(pairToAdd);
					
				}
			}
			
		}else if(dataType.equals("Double")) {
			
			pairsOfVertices = new ArrayList<Pair<Double,Double>>();
			ArrayList<Double> listOfNumbers = new ArrayList<Double>();
			
			for(int i=0;i<numberOfVertices;i++) {
				
				listOfNumbers.add((double) i);
				
			}
			Collections.shuffle(listOfNumbers);
			
			double vertex1 = listOfNumbers.get(0);
			double vertex2 =  listOfNumbers.get(1);
			Pair<Double,Double> pairToAdd = new Pair<Double,Double>(vertex1,vertex2);
			Pair<Double,Double> pairToAddReversed = new Pair<Double,Double>(vertex2,vertex1);
			
			for(int i=0;i<numberOfEdges;i++) {
				
				if(gpc.getSelectedTabName().equals("Undirected Non-Weighted Graph") ||gpc.getSelectedTabName().equals("Undirected Weighted Graph")) {
					
					while(pairsOfVertices.contains(pairToAdd) || pairsOfVertices.contains(pairToAddReversed)) {
						
						Collections.shuffle(listOfNumbers);
						
						 vertex1 = listOfNumbers.get(0);
						 vertex2 =  listOfNumbers.get(1);
						
						pairToAdd = new Pair<Double,Double>(vertex1,vertex2);
						pairToAddReversed = new Pair<Double,Double>(vertex2,vertex1);
						
					}
					
					pairsOfVertices.add(pairToAdd);
					
				}else {
					
					while(pairsOfVertices.contains(pairToAdd)) {
						
						Collections.shuffle(listOfNumbers);
						
						 vertex1 = listOfNumbers.get(0);
						 vertex2 =  listOfNumbers.get(1);
						
						pairToAdd = new Pair<Double,Double>(vertex1,vertex2);
						pairToAddReversed = new Pair<Double,Double>(vertex2,vertex1);
						
					}
					
					pairsOfVertices.add(pairToAdd);
					
				}
				
			}
			
		}else if(dataType.equals("String")) {
			
			pairsOfVertices = new ArrayList<Pair<String,String>>();
			ArrayList<String> listOfLetters = new ArrayList<String>();
			String alphabet = "abcdefghijklmnopqrstuvwxyz";
			
			for(int i=0;i<numberOfVertices;i++) {
				
				listOfLetters.add(Character.toString(alphabet.charAt(i)));
				
			}
			Collections.shuffle(listOfLetters);
			
			String vertex1 = listOfLetters.get(0);
			String vertex2 =  listOfLetters.get(1);
			Pair<String,String> pairToAdd = new Pair<String,String>(vertex1,vertex2);
			Pair<String,String> pairToAddReversed = new Pair<String,String>(vertex2,vertex1);
			
			for(int i=0;i<numberOfEdges;i++) {
				
				if(gpc.getSelectedTabName().equals("Undirected Non-Weighted Graph") ||gpc.getSelectedTabName().equals("Undirected Weighted Graph")) {
					
					while(pairsOfVertices.contains(pairToAdd) || pairsOfVertices.contains(pairToAddReversed)) {
						
						Collections.shuffle(listOfLetters);
						
						 vertex1 = listOfLetters.get(0);
						 vertex2 =  listOfLetters.get(1);
						
						pairToAdd = new Pair<String,String>(vertex1,vertex2);
						pairToAddReversed = new Pair<String,String>(vertex2,vertex1);
						
					}
					
					pairsOfVertices.add(pairToAdd);
					
				}else {
					
					while(pairsOfVertices.contains(pairToAdd)) {
						
						Collections.shuffle(listOfLetters);
						
						 vertex1 = listOfLetters.get(0);
						 vertex2 =  listOfLetters.get(1);
						
						pairToAdd = new Pair<String,String>(vertex1,vertex2);
						pairToAddReversed = new Pair<String,String>(vertex2,vertex1);
						
					}
					
					pairsOfVertices.add(pairToAdd);
					
				}
				
			}
			
		}
		
		if(gpc.getSelectedTabName().equals("Undirected Non-Weighted Graph")) {
			
			StackPane stackPane1 = null;
			StackPane stackPane2 = null;
			
			for(Object pair : pairsOfVertices) {
				String vertexData1 = "";
				String vertexData2 = "";
				if(dataType.equals("Integer")) {
					
					vertexData1 = ((Pair<Integer, Integer>) pair).getKey().toString();
					vertexData2 = ((Pair<Integer, Integer>) pair).getValue().toString();
					
				}else if(dataType.equals("Double")) {
					
					vertexData1 = ((Pair<Double, Double>) pair).getKey().toString();
					vertexData2 = ((Pair<Double, Double>) pair).getValue().toString();
					
				}else if(dataType.equals("String")) {
					
					vertexData1 = ((Pair<String, String>) pair).getKey().toString();
					vertexData2 = ((Pair<String, String>) pair).getValue().toString();
					
					
				}
				
				
				for(Node child : gpc.getCenterPaneUndirectedNonWeightedGraph().getChildren()) {
					
					if(child instanceof StackPane) {
						
						ObservableList<Node> childsOfStack = ((StackPane)child).getChildren();
	        			Text dataOfStack = (Text) childsOfStack.get(childsOfStack.size()-1);
	        			String toCompare = dataOfStack.getText();
	        			
	        			if(toCompare.equals(vertexData1)) {
	        				stackPane1 = (StackPane) child;
	        			}else if(toCompare.equals(vertexData2)) {
	        				stackPane2 = (StackPane) child;
	        				
	        			}
						
					}
					
				}
				
				Line line = new Line();
	    		line.setStrokeWidth(2);
	    		
	    		line.setOnMouseClicked(Event::consume);
	    		line.setOnMousePressed(gpc.getClickedOnEdgeHandler());

	    		line.setStartX(stackPane1.getLayoutX() + (stackPane1.getWidth() / 2));
	    		line.setStartY(stackPane1.getLayoutY() + (stackPane1.getHeight() / 2));
	    		line.setEndX(stackPane2.getLayoutX() + (stackPane2.getWidth() / 2));
	    		line.setEndY(stackPane2.getLayoutY() +  (stackPane2.getHeight() / 2));

	    		line.startXProperty().bind(stackPane1.layoutXProperty().add(stackPane1.translateXProperty()).add(stackPane1.widthProperty().divide(2)));
	    		line.startYProperty().bind(stackPane1.layoutYProperty().add(stackPane1.translateYProperty()).add(stackPane1.heightProperty().divide(2)));
	    		line.endXProperty().bind(stackPane2.layoutXProperty().add(stackPane2.translateXProperty()).add(stackPane2.widthProperty().divide(2)));
	    		line.endYProperty().bind(stackPane2.layoutYProperty().add(stackPane2.translateYProperty()).add(stackPane2.heightProperty().divide(2)));
				
	    		covh.addEdgeHandlerNonWeighted(vertexData1.toString(), vertexData2.toString());
	    		gpc.getCenterPaneUndirectedNonWeightedGraph().getChildren().add(0,line);
			}

		}else if(gpc.getSelectedTabName().equals("Undirected Weighted Graph")) {
			
			StackPane stackPane1 = null;
			StackPane stackPane2 = null;
			
			for(Object pair : pairsOfVertices) {
				String vertexData1 = "";
				String vertexData2 = "";
				if(dataType.equals("Integer")) {
					
					vertexData1 = ((Pair<Integer, Integer>) pair).getKey().toString();
					vertexData2 = ((Pair<Integer, Integer>) pair).getValue().toString();
					
				}else if(dataType.equals("Double")) {
					
					vertexData1 = ((Pair<Double, Double>) pair).getKey().toString();
					vertexData2 = ((Pair<Double, Double>) pair).getValue().toString();
					
				}else if(dataType.equals("String")) {
					
					vertexData1 = ((Pair<String, String>) pair).getKey().toString();
					vertexData2 = ((Pair<String, String>) pair).getValue().toString();
					
					
				}
				
				
				for(Node child : gpc.getCenterPaneUndirectedWeightedGraph().getChildren()) {
					
					if(child instanceof StackPane) {
						
						ObservableList<Node> childsOfStack = ((StackPane)child).getChildren();
	        			Text dataOfStack = (Text) childsOfStack.get(childsOfStack.size()-1);
	        			String toCompare = dataOfStack.getText();
	        			
	        			if(toCompare.equals(vertexData1)) {
	        				stackPane1 = (StackPane) child;
	        			}else if(toCompare.equals(vertexData2)) {
	        				stackPane2 = (StackPane) child;
	        				
	        			}
						
					}
					
				}
				
				Line line = new Line();
	    		line.setStrokeWidth(2);
	    		
	    		line.setOnMouseClicked(Event::consume);
	    		line.setOnMousePressed(gpc.getClickedOnEdgeHandler());

	    		line.setStartX(stackPane1.getLayoutX() + (stackPane1.getWidth() / 2));
	    		line.setStartY(stackPane1.getLayoutY() + (stackPane1.getHeight() / 2));
	    		line.setEndX(stackPane2.getLayoutX() + (stackPane2.getWidth() / 2));
	    		line.setEndY(stackPane2.getLayoutY() +  (stackPane2.getHeight() / 2));

	    		line.startXProperty().bind(stackPane1.layoutXProperty().add(stackPane1.translateXProperty()).add(stackPane1.widthProperty().divide(2)));
	    		line.startYProperty().bind(stackPane1.layoutYProperty().add(stackPane1.translateYProperty()).add(stackPane1.heightProperty().divide(2)));
	    		line.endXProperty().bind(stackPane2.layoutXProperty().add(stackPane2.translateXProperty()).add(stackPane2.widthProperty().divide(2)));
	    		line.endYProperty().bind(stackPane2.layoutYProperty().add(stackPane2.translateYProperty()).add(stackPane2.heightProperty().divide(2)));
				
	    		Random r = new Random();
				double randomValue = 0.1 + (10.0 - 0.1) * r.nextDouble();
				double roundedValue = Math.round(randomValue * 100.0) / 100.0;
				
	    		Label label = new Label(Double.toString(roundedValue));
	        	label.setStyle("-fx-background-color: white; -fx-border-color: black;");
	        	label.setPadding(new Insets(2, 4, 2, 4));
	        	label.setAlignment(Pos.CENTER);
	        	label.setFont(new Font(10));

	        	label.setOnMouseClicked(Event::consume);
	        	label.setOnMousePressed(gpc.getClickedOnEdgeHandler());
	        	
	        	label.layoutXProperty().bind(Bindings.createDoubleBinding(
	    	          () -> (line.getStartX() + line.getEndX() - label.getWidth()) / 2,
	    	          line.startXProperty(), line.endXProperty(), label.widthProperty()));
	        	
	    	    label.layoutYProperty().bind(Bindings.createDoubleBinding(
	    	          () -> (line.getStartY() + line.getEndY() - label.getHeight()) / 2,
	    	          line.startYProperty(), line.endYProperty(), label.heightProperty()));
	    	    
	    	    
	    	    covh.addEdgeHandlerWeighted(vertexData1.toString(), vertexData2.toString(),roundedValue);
	    		gpc.getCenterPaneUndirectedWeightedGraph().getChildren().add(0,line);
	    		gpc.getCenterPaneUndirectedWeightedGraph().getChildren().add(label);
			}
			
			
		}else if(gpc.getSelectedTabName().equals("Directed Non-Weighted Graph")) {
			
			StackPane stackPane1 = null;
			StackPane stackPane2 = null;
			
			for(Object pair : pairsOfVertices) {
				int edgePlacement = 13;
				String vertexData1 = "";
				String vertexData2 = "";
				if(dataType.equals("Integer")) {
					
					vertexData1 = ((Pair<Integer, Integer>) pair).getKey().toString();
					vertexData2 = ((Pair<Integer, Integer>) pair).getValue().toString();
					
					if(gpc.getDataModel().getDirectedNonWeightedInt().isAdjacent(Integer.parseInt(vertexData2), Integer.parseInt(vertexData1))) {
						edgePlacement = -13;
					}
					
				}else if(dataType.equals("Double")) {
					
					vertexData1 = ((Pair<Double, Double>) pair).getKey().toString();
					vertexData2 = ((Pair<Double, Double>) pair).getValue().toString();
					
					if(gpc.getDataModel().getDirectedNonWeightedDouble().isAdjacent(Double.parseDouble(vertexData2), Double.parseDouble(vertexData1))) {
						edgePlacement = -13;
					}
					
				}else if(dataType.equals("String")) {
					
					vertexData1 = ((Pair<String, String>) pair).getKey().toString();
					vertexData2 = ((Pair<String, String>) pair).getValue().toString();
					
					if(gpc.getDataModel().getDirectedNonWeightedString().isAdjacent(vertexData2,vertexData1)) {
						edgePlacement = -13;
					}
					
					
				}
				
				
				for(Node child : gpc.getCenterPaneDirectedNonWeightedGraph().getChildren()) {
					
					if(child instanceof StackPane) {
						
						ObservableList<Node> childsOfStack = ((StackPane)child).getChildren();
	        			Text dataOfStack = (Text) childsOfStack.get(childsOfStack.size()-1);
	        			String toCompare = dataOfStack.getText();
	        			
	        			if(toCompare.equals(vertexData1)) {
	        				stackPane1 = (StackPane) child;
	        			}else if(toCompare.equals(vertexData2)) {
	        				stackPane2 = (StackPane) child;
	        				
	        			}
						
					}
					
				}
				
				Line line = new Line();
	    		line.setStrokeWidth(2);
	    		
	    		line.setOnMouseClicked(Event::consume);
	    		line.setOnMousePressed(gpc.getClickedOnEdgeHandler());

	    		line.setStartX(stackPane1.getLayoutX() + (stackPane1.getWidth() / 2));
	    		line.setStartY(stackPane1.getLayoutY() + (stackPane1.getHeight() / 2));
	    		line.setEndX(stackPane2.getLayoutX() + (stackPane2.getWidth() / 2));
	    		line.setEndY(stackPane2.getLayoutY() +  (stackPane2.getHeight() / 2));

	    		line.startXProperty().bind(stackPane1.layoutXProperty().add(stackPane1.translateXProperty()).add(stackPane1.widthProperty().divide(2)).add(edgePlacement));
	    		line.startYProperty().bind(stackPane1.layoutYProperty().add(stackPane1.translateYProperty()).add(stackPane1.heightProperty().divide(2)).add(edgePlacement));
	    		line.endXProperty().bind(stackPane2.layoutXProperty().add(stackPane2.translateXProperty()).add(stackPane2.widthProperty().divide(2)).add(edgePlacement));
	    		line.endYProperty().bind(stackPane2.layoutYProperty().add(stackPane2.translateYProperty()).add(stackPane2.heightProperty().divide(2)).add(edgePlacement));
	    		
	    		Polygon arrowHead = new Polygon();
    	        arrowHead.getPoints().addAll(new Double[]{
    	            0.0, 8.0,
    	            -8.0, -8.0,
    	            8.0, -8.0 });
    	        arrowHead.setFill(Color.BLACK);
    	       
    	        
    	        arrowHead.rotateProperty().bind(Bindings.createDoubleBinding(
    	        		() -> Math.atan2(line.getEndY() - line.getStartY(), line.getEndX() - line.getStartX()) * 180 / 3.14 - 90,
    	        		line.endXProperty(),line.endYProperty(),line.startXProperty(),line.startYProperty()));

    	       

            	arrowHead.setOnMouseClicked(Event::consume);
            	arrowHead.setOnMousePressed(gpc.getClickedOnEdgeHandler());

        		
        		arrowHead.layoutXProperty().bind(Bindings.createDoubleBinding(
            	          () -> ((line.getStartX() + line.getEndX() ) / 2) ,
            	          line.startXProperty(), line.endXProperty()));
	            	
        	    arrowHead.layoutYProperty().bind(Bindings.createDoubleBinding(
        	          () -> ((line.getStartY() + line.getEndY() ) / 2),
        	          line.startYProperty(), line.endYProperty()));
        	    
        	    covh.addEdgeHandlerNonWeighted(vertexData1.toString(), vertexData2.toString());
        	    gpc.getCenterPaneDirectedNonWeightedGraph().getChildren().add(arrowHead);
        		gpc.getCenterPaneDirectedNonWeightedGraph().getChildren().add(0,line);


			}
			
			if(numberOfEdges> (numberOfVertices*(numberOfVertices-1))) {
    			int selfEdgeArrowheadPlacement = 20;
    			ArrayList<StackPane> allVertices = new ArrayList<StackPane>();
    			
    			for(Node child : gpc.getCenterPaneDirectedNonWeightedGraph().getChildren()) {
    				
    				if(child instanceof StackPane) {
    					allVertices.add((StackPane) child);
    				}
					
    			}
    			
    			int numberOfLoopVertices = numberOfEdges - (numberOfVertices*(numberOfVertices-1));
    			
    			for(int i=0; i<numberOfLoopVertices;i++) {
    				
    				StackPane loopVertex = allVertices.get(i);
    				
    				Rectangle rect = new Rectangle();
        			rect.xProperty().bind(loopVertex.layoutXProperty().add(loopVertex.translateXProperty()).add(loopVertex.widthProperty().divide(2)));
        			rect.yProperty().bind(loopVertex.layoutYProperty().add(loopVertex.translateYProperty()).add(loopVertex.heightProperty().divide(2)));
        			rect.setHeight(40);
        			rect.setWidth(40);
        			rect.setStroke(Color.BLACK);
        			rect.setStrokeWidth(2);
        			rect.setFill(null);
        			
        			rect.setOnMouseClicked(Event::consume);
        			rect.setOnMousePressed(gpc.getClickedOnEdgeHandler());
        		    
        			gpc.getCenterPaneDirectedNonWeightedGraph().getChildren().add(0,rect);
        			
        			Polygon loopArrowHead = new Polygon();
        			loopArrowHead.getPoints().addAll(new Double[]{
        	            0.0, 7.0,
        	            -7.0, -7.0,
        	            7.0, -7.0 });
        	        loopArrowHead.setFill(Color.BLACK);
        	       
        	        loopArrowHead.setOnMouseClicked(Event::consume);
        	        loopArrowHead.setOnMousePressed(gpc.getClickedOnEdgeHandler());
        	        
        	        loopArrowHead.rotateProperty().bind(Bindings.createDoubleBinding(
        	        		() -> Math.atan2(rect.getY() - (rect.getY()), rect.getX()- (rect.getX()+ rect.getWidth())) * 180 / 3.14 - 90,
        	        		rect.xProperty(),rect.yProperty(),rect.widthProperty()));
        	        
        	        loopArrowHead.layoutXProperty().bind(Bindings.createDoubleBinding(
	            	          () -> rect.getX() +selfEdgeArrowheadPlacement,
	            	          rect.xProperty()));
		            	
        	        loopArrowHead.layoutYProperty().bind(Bindings.createDoubleBinding(
            	          () -> rect.getY() ,
            	          rect.yProperty()));
        	        
        	        
        	        gpc.getCenterPaneDirectedNonWeightedGraph().getChildren().add(loopArrowHead);
    				
    			}
    			
    			
    		}
				
			
		}else if(gpc.getSelectedTabName().equals("Directed Weighted Graph")) {
			
			StackPane stackPane1 = null;
			StackPane stackPane2 = null;
			
			for(Object pair : pairsOfVertices) {
				int edgePlacement = 13;
				String vertexData1 = "";
				String vertexData2 = "";
				if(dataType.equals("Integer")) {
					
					vertexData1 = ((Pair<Integer, Integer>) pair).getKey().toString();
					vertexData2 = ((Pair<Integer, Integer>) pair).getValue().toString();
					
					if(gpc.getDataModel().getDirectedWeightedInt().isAdjacent(Integer.parseInt(vertexData2), Integer.parseInt(vertexData1))) {
						edgePlacement = -13;
					}
					
				}else if(dataType.equals("Double")) {
					
					vertexData1 = ((Pair<Double, Double>) pair).getKey().toString();
					vertexData2 = ((Pair<Double, Double>) pair).getValue().toString();
					
					if(gpc.getDataModel().getDirectedWeightedDouble().isAdjacent(Double.parseDouble(vertexData2), Double.parseDouble(vertexData1))) {
						edgePlacement = -13;
					}
					
				}else if(dataType.equals("String")) {
					
					vertexData1 = ((Pair<String, String>) pair).getKey().toString();
					vertexData2 = ((Pair<String, String>) pair).getValue().toString();
					
					if(gpc.getDataModel().getDirectedWeightedString().isAdjacent(vertexData2,vertexData1)) {
						edgePlacement = -13;
					}
					
					
				}
				
				
				for(Node child : gpc.getCenterPaneDirectedWeightedGraph().getChildren()) {
					
					if(child instanceof StackPane) {
						
						ObservableList<Node> childsOfStack = ((StackPane)child).getChildren();
	        			Text dataOfStack = (Text) childsOfStack.get(childsOfStack.size()-1);
	        			String toCompare = dataOfStack.getText();
	        			
	        			if(toCompare.equals(vertexData1)) {
	        				stackPane1 = (StackPane) child;
	        			}else if(toCompare.equals(vertexData2)) {
	        				stackPane2 = (StackPane) child;
	        				
	        			}
						
					}
					
				}
				
				Line line = new Line();
	    		line.setStrokeWidth(2);
	    		
	    		line.setOnMouseClicked(Event::consume);
	    		line.setOnMousePressed(gpc.getClickedOnEdgeHandler());

	    		line.setStartX(stackPane1.getLayoutX() + (stackPane1.getWidth() / 2));
	    		line.setStartY(stackPane1.getLayoutY() + (stackPane1.getHeight() / 2));
	    		line.setEndX(stackPane2.getLayoutX() + (stackPane2.getWidth() / 2));
	    		line.setEndY(stackPane2.getLayoutY() +  (stackPane2.getHeight() / 2));

	    		line.startXProperty().bind(stackPane1.layoutXProperty().add(stackPane1.translateXProperty()).add(stackPane1.widthProperty().divide(2)).add(edgePlacement));
	    		line.startYProperty().bind(stackPane1.layoutYProperty().add(stackPane1.translateYProperty()).add(stackPane1.heightProperty().divide(2)).add(edgePlacement));
	    		line.endXProperty().bind(stackPane2.layoutXProperty().add(stackPane2.translateXProperty()).add(stackPane2.widthProperty().divide(2)).add(edgePlacement));
	    		line.endYProperty().bind(stackPane2.layoutYProperty().add(stackPane2.translateYProperty()).add(stackPane2.heightProperty().divide(2)).add(edgePlacement));
	    		
	    		Polygon arrowHead = new Polygon();
    	        arrowHead.getPoints().addAll(new Double[]{
    	            0.0, 8.0,
    	            -8.0, -8.0,
    	            8.0, -8.0 });
    	        arrowHead.setFill(Color.BLACK);
    	       
    	        
    	        arrowHead.rotateProperty().bind(Bindings.createDoubleBinding(
    	        		() -> Math.atan2(line.getEndY() - line.getStartY(), line.getEndX() - line.getStartX()) * 180 / 3.14 - 90,
    	        		line.endXProperty(),line.endYProperty(),line.startXProperty(),line.startYProperty()));

    	       

            	arrowHead.setOnMouseClicked(Event::consume);
            	arrowHead.setOnMousePressed(gpc.getClickedOnEdgeHandler());

        		
            	arrowHead.layoutXProperty().bind(Bindings.createDoubleBinding(
          	          () -> ((line.getStartX() + 4*line.getEndX()) / 5) ,
          	          line.startXProperty(), line.endXProperty()));
	            	
            	arrowHead.layoutYProperty().bind(Bindings.createDoubleBinding(
      	         	() -> (line.getStartY() + 4*line.getEndY()) / 5,
      	         	line.startYProperty(), line.endYProperty()));
        	    
        	    Random r = new Random();
				double randomValue = 0.1 + (10.0 - 0.1) * r.nextDouble();
				double roundedValue = Math.round(randomValue * 100.0) / 100.0;
				
	    		Label label = new Label(Double.toString(roundedValue));
	        	label.setStyle("-fx-background-color: white; -fx-border-color: black;");
	        	label.setPadding(new Insets(2, 4, 2, 4));
	        	label.setAlignment(Pos.CENTER);
	        	label.setFont(new Font(10));

	        	label.setOnMouseClicked(Event::consume);
	        	label.setOnMousePressed(gpc.getClickedOnEdgeHandler());
	        	
	        	label.layoutXProperty().bind(Bindings.createDoubleBinding(
	    	          () -> (line.getStartX() + line.getEndX() - label.getWidth()) / 2,
	    	          line.startXProperty(), line.endXProperty(), label.widthProperty()));
	        	
	    	    label.layoutYProperty().bind(Bindings.createDoubleBinding(
	    	          () -> (line.getStartY() + line.getEndY() - label.getHeight()) / 2,
	    	          line.startYProperty(), line.endYProperty(), label.heightProperty()));
        	    
        	    covh.addEdgeHandlerWeighted(vertexData1.toString(), vertexData2.toString(),roundedValue);
        	    gpc.getCenterPaneDirectedWeightedGraph().getChildren().add(0,label);
        	    gpc.getCenterPaneDirectedWeightedGraph().getChildren().add(arrowHead);
        		gpc.getCenterPaneDirectedWeightedGraph().getChildren().add(0,line);

			}
			
			if(numberOfEdges> (numberOfVertices*(numberOfVertices-1))) {
    			int selfEdgeArrowheadPlacement = 20;
    			ArrayList<StackPane> allVertices = new ArrayList<StackPane>();
    			
    			for(Node child : gpc.getCenterPaneDirectedWeightedGraph().getChildren()) {
    				
    				if(child instanceof StackPane) {
    					allVertices.add((StackPane) child);
    				}
					
    			}
    			
    			int numberOfLoopVertices = numberOfEdges - (numberOfVertices*(numberOfVertices-1));
    			
    			for(int i=0; i<numberOfLoopVertices;i++) {
    				
    				StackPane loopVertex = allVertices.get(i);
    				
    				Rectangle rect = new Rectangle();
        			rect.xProperty().bind(loopVertex.layoutXProperty().add(loopVertex.translateXProperty()).add(loopVertex.widthProperty().divide(2)));
        			rect.yProperty().bind(loopVertex.layoutYProperty().add(loopVertex.translateYProperty()).add(loopVertex.heightProperty().divide(2)));
        			rect.setHeight(40);
        			rect.setWidth(40);
        			rect.setStroke(Color.BLACK);
        			rect.setStrokeWidth(2);
        			rect.setFill(null);
        			
        			rect.setOnMouseClicked(Event::consume);
        			rect.setOnMousePressed(gpc.getClickedOnEdgeHandler());
        		    
        			gpc.getCenterPaneDirectedWeightedGraph().getChildren().add(0,rect);
        			
        			Random r2 = new Random();
    				double randomValue2 = 0.1 + (10.0 - 0.1) * r2.nextDouble();
    				double roundedValue2 = Math.round(randomValue2 * 100.0) / 100.0;
        			
        	        Label label2 = new Label(Double.toString(roundedValue2));
        	        label2.setStyle("-fx-background-color: white; -fx-border-color: black;");
        	        label2.setPadding(new Insets(2, 4, 2, 4));

        	        label2.setAlignment(Pos.CENTER);
        	        label2.setFont(new Font(10));
	            	
        	        label2.setOnMouseClicked(Event::consume);
        	        label2.setOnMousePressed(gpc.getClickedOnEdgeHandler());
	            	
        	        label2.layoutXProperty().bind(Bindings.createDoubleBinding(
            	          () -> (rect.getX()  + rect.getWidth()) - (label2.getWidth()/2),
            	          rect.xProperty(),label2.widthProperty(),rect.widthProperty()));
	            	
        	        label2.layoutYProperty().bind(Bindings.createDoubleBinding(
            	          () -> (rect.getY() + rect.getHeight()) - (label2.getHeight()/2),
            	          rect.yProperty(),label2.heightProperty(),rect.heightProperty()));
        			
        			Polygon loopArrowHead = new Polygon();
        			loopArrowHead.getPoints().addAll(new Double[]{
        	            0.0, 7.0,
        	            -7.0, -7.0,
        	            7.0, -7.0 });
        			loopArrowHead.setFill(Color.BLACK);

        			loopArrowHead.rotateProperty().bind(Bindings.createDoubleBinding(
        	        		() -> Math.atan2(rect.getY() - (rect.getY()), rect.getX()- (rect.getX()+ rect.getWidth())) * 180 / 3.14 - 90,
        	        		rect.xProperty(),rect.yProperty(),rect.widthProperty()));
        	        
        			loopArrowHead.layoutXProperty().bind(Bindings.createDoubleBinding(
	            	          () -> rect.getX() +20,
	            	          rect.xProperty()));
		            	
        			loopArrowHead.layoutYProperty().bind(Bindings.createDoubleBinding(
            	          () -> rect.getY() ,
            	          rect.yProperty()));
        	        
        			loopArrowHead.setOnMouseClicked(Event::consume);
        			loopArrowHead.setOnMousePressed(gpc.getClickedOnEdgeHandler());
        	        
        	        gpc.getCenterPaneDirectedWeightedGraph().getChildren().add(loopArrowHead);
        	        gpc.getCenterPaneDirectedWeightedGraph().getChildren().add(label2);
    				
    			}
    		}

		}
	}

	/**
	 * This method will create a specific number of vertices for the graph the user is currently on. The model is also updated accordingly.
	 * @param numberOfVertices
	 * @param dataType
	 * @return
	 */
	private ArrayList<StackPane> createNVertices(int numberOfVertices,String dataType) {
		
		ArrayList<StackPane> listOfVertices = new ArrayList<StackPane>();
		String alphabet = "abcdefghijklmnopqrstuvwxyz";
		
		if(gpc.getSelectedTabName().equals("Undirected Non-Weighted Graph")) {
			
			if(dataType.equals("Integer")) {
				
				for(int i=0;i<numberOfVertices;i++) {
					
					Circle vertex = new Circle(20, Color.WHITE);
		   			vertex.setStroke(Color.BLACK);
		   			
		   			Text text = new Text (Integer.toString(i));
		   			
		   			StackPane stack = new StackPane();
		   			
		   			stack.getChildren().addAll(vertex, text);
		   			
		   			stack.setOnMouseClicked(Event::consume);
		   			stack.setOnMousePressed(new ClickedOnVertexHandler(gpc,stack));
		   			stack.setOnMouseDragged(gpc.mouseDraggedOnVertexEvent());
		   			stack.setOnMouseReleased(gpc.mouseReleasedOnVertexEvent());
		   			
		   			listOfVertices.add(stack);
		   			gpc.getDataModel().getUndirectedNonWeightedInt().addVertex(i);
					
				}
				
			}else if(dataType.equals("Double")) {
				
				for(int i=0;i<numberOfVertices;i++) {
					
					Circle vertex = new Circle(20, Color.WHITE);
		   			vertex.setStroke(Color.BLACK);
		   			
		   			Text text = new Text (Integer.toString(i) + ".0");
		   			
		   			StackPane stack = new StackPane();
		   			
		   			stack.getChildren().addAll(vertex, text);
		   			
		   			stack.setOnMouseClicked(Event::consume);
		   			stack.setOnMousePressed(new ClickedOnVertexHandler(gpc,stack));
		   			stack.setOnMouseDragged(gpc.mouseDraggedOnVertexEvent());
		   			stack.setOnMouseReleased(gpc.mouseReleasedOnVertexEvent());
		   			
		   			listOfVertices.add(stack);
		   			gpc.getDataModel().getUndirectedNonWeightedDouble().addVertex((double) i);
					
				}
				
			}else if(dataType.equals("String")) {
				
				for(int i=0;i<numberOfVertices;i++) {
					
					Circle vertex = new Circle(20, Color.WHITE);
		   			vertex.setStroke(Color.BLACK);
		   			Text text = new Text (Character.toString(alphabet.charAt(i)));
		   			
		   			StackPane stack = new StackPane();
		   			
		   			stack.getChildren().addAll(vertex, text);
		   			
		   			stack.setOnMouseClicked(Event::consume);
		   			stack.setOnMousePressed(new ClickedOnVertexHandler(gpc,stack));
		   			stack.setOnMouseDragged(gpc.mouseDraggedOnVertexEvent());
		   			stack.setOnMouseReleased(gpc.mouseReleasedOnVertexEvent());
		   			
		   			listOfVertices.add(stack);
		   			gpc.getDataModel().getUndirectedNonWeightedString().addVertex(text.getText());
					
				}
				
			}
			
		}else if(gpc.getSelectedTabName().equals("Undirected Weighted Graph")) {
			
			if(dataType.equals("Integer")) {
				
				for(int i=0;i<numberOfVertices;i++) {
					
					Circle vertex = new Circle(20, Color.WHITE);
		   			vertex.setStroke(Color.BLACK);
		   			
		   			Text text = new Text (Integer.toString(i));
		   			
		   			StackPane stack = new StackPane();
		   			
		   			stack.getChildren().addAll(vertex, text);
		   			
		   			stack.setOnMouseClicked(Event::consume);
		   			stack.setOnMousePressed(new ClickedOnVertexHandler(gpc,stack));
		   			stack.setOnMouseDragged(gpc.mouseDraggedOnVertexEvent());
		   			stack.setOnMouseReleased(gpc.mouseReleasedOnVertexEvent());
		   			
		   			listOfVertices.add(stack);
		   			gpc.getDataModel().getUndirectedWeightedInt().addVertex(i);
					
				}
				
			}else if(dataType.equals("Double")) {
				
				for(int i=0;i<numberOfVertices;i++) {
					
					Circle vertex = new Circle(20, Color.WHITE);
		   			vertex.setStroke(Color.BLACK);
		   			
		   			Text text = new Text (Integer.toString(i) + ".0");
		   			
		   			StackPane stack = new StackPane();
		   			
		   			stack.getChildren().addAll(vertex, text);
		   			
		   			stack.setOnMouseClicked(Event::consume);
		   			stack.setOnMousePressed(new ClickedOnVertexHandler(gpc,stack));
		   			stack.setOnMouseDragged(gpc.mouseDraggedOnVertexEvent());
		   			stack.setOnMouseReleased(gpc.mouseReleasedOnVertexEvent());
		   			
		   			listOfVertices.add(stack);
		   			gpc.getDataModel().getUndirectedWeightedDouble().addVertex((double) i);
					
				}
				
			}else if(dataType.equals("String")) {
				
				for(int i=0;i<numberOfVertices;i++) {
					
					Circle vertex = new Circle(20, Color.WHITE);
		   			vertex.setStroke(Color.BLACK);
		   			Text text = new Text (Character.toString(alphabet.charAt(i)));
		   			
		   			StackPane stack = new StackPane();
		   			
		   			stack.getChildren().addAll(vertex, text);
		   			
		   			stack.setOnMouseClicked(Event::consume);
		   			stack.setOnMousePressed(new ClickedOnVertexHandler(gpc,stack));
		   			stack.setOnMouseDragged(gpc.mouseDraggedOnVertexEvent());
		   			stack.setOnMouseReleased(gpc.mouseReleasedOnVertexEvent());
		   			
		   			listOfVertices.add(stack);
		   			gpc.getDataModel().getUndirectedWeightedString().addVertex(text.getText());
					
				}
				
			}
			
		}else if(gpc.getSelectedTabName().equals("Directed Non-Weighted Graph")) {
			
			if(dataType.equals("Integer")) {
				
				for(int i=0;i<numberOfVertices;i++) {
					
					Circle vertex = new Circle(20, Color.WHITE);
		   			vertex.setStroke(Color.BLACK);
		   			
		   			Text text = new Text (Integer.toString(i));
		   			
		   			StackPane stack = new StackPane();
		   			
		   			stack.getChildren().addAll(vertex, text);
		   			
		   			stack.setOnMouseClicked(Event::consume);
		   			stack.setOnMousePressed(new ClickedOnVertexHandler(gpc,stack));
		   			stack.setOnMouseDragged(gpc.mouseDraggedOnVertexEvent());
		   			stack.setOnMouseReleased(gpc.mouseReleasedOnVertexEvent());
		   			
		   			listOfVertices.add(stack);
		   			gpc.getDataModel().getDirectedNonWeightedInt().addVertex(i);
					
				}
				
			}else if(dataType.equals("Double")) {
				
				for(int i=0;i<numberOfVertices;i++) {
					
					Circle vertex = new Circle(20, Color.WHITE);
		   			vertex.setStroke(Color.BLACK);
		   			
		   			Text text = new Text (Integer.toString(i) + ".0");
		   			
		   			StackPane stack = new StackPane();
		   			
		   			stack.getChildren().addAll(vertex, text);
		   			
		   			stack.setOnMouseClicked(Event::consume);
		   			stack.setOnMousePressed(new ClickedOnVertexHandler(gpc,stack));
		   			stack.setOnMouseDragged(gpc.mouseDraggedOnVertexEvent());
		   			stack.setOnMouseReleased(gpc.mouseReleasedOnVertexEvent());
		   			
		   			listOfVertices.add(stack);
		   			gpc.getDataModel().getDirectedNonWeightedDouble().addVertex((double) i);
					
				}
				
			}else if(dataType.equals("String")) {
				
				for(int i=0;i<numberOfVertices;i++) {
					
					Circle vertex = new Circle(20, Color.WHITE);
		   			vertex.setStroke(Color.BLACK);
		   			Text text = new Text (Character.toString(alphabet.charAt(i)));
		   			
		   			StackPane stack = new StackPane();
		   			
		   			stack.getChildren().addAll(vertex, text);
		   			
		   			stack.setOnMouseClicked(Event::consume);
		   			stack.setOnMousePressed(new ClickedOnVertexHandler(gpc,stack));
		   			stack.setOnMouseDragged(gpc.mouseDraggedOnVertexEvent());
		   			stack.setOnMouseReleased(gpc.mouseReleasedOnVertexEvent());
		   			
		   			listOfVertices.add(stack);
		   			gpc.getDataModel().getDirectedNonWeightedString().addVertex(text.getText());
					
				}
				
			}
			
		}else if(gpc.getSelectedTabName().equals("Directed Weighted Graph")) {
			
			if(dataType.equals("Integer")) {
				
				for(int i=0;i<numberOfVertices;i++) {
					
					Circle vertex = new Circle(20, Color.WHITE);
		   			vertex.setStroke(Color.BLACK);
		   			
		   			Text text = new Text (Integer.toString(i));
		   			
		   			StackPane stack = new StackPane();
		   			
		   			stack.getChildren().addAll(vertex, text);
		   			
		   			stack.setOnMouseClicked(Event::consume);
		   			stack.setOnMousePressed(new ClickedOnVertexHandler(gpc,stack));
		   			stack.setOnMouseDragged(gpc.mouseDraggedOnVertexEvent());
		   			stack.setOnMouseReleased(gpc.mouseReleasedOnVertexEvent());
		   			
		   			listOfVertices.add(stack);
		   			gpc.getDataModel().getDirectedWeightedInt().addVertex(i);
					
				}
				
			}else if(dataType.equals("Double")) {
				
				for(int i=0;i<numberOfVertices;i++) {
					
					Circle vertex = new Circle(20, Color.WHITE);
		   			vertex.setStroke(Color.BLACK);
		   			
		   			Text text = new Text (Integer.toString(i) + ".0");
		   			
		   			StackPane stack = new StackPane();
		   			
		   			stack.getChildren().addAll(vertex, text);
		   			
		   			stack.setOnMouseClicked(Event::consume);
		   			stack.setOnMousePressed(new ClickedOnVertexHandler(gpc,stack));
		   			stack.setOnMouseDragged(gpc.mouseDraggedOnVertexEvent());
		   			stack.setOnMouseReleased(gpc.mouseReleasedOnVertexEvent());
		   			
		   			listOfVertices.add(stack);
		   			gpc.getDataModel().getDirectedWeightedDouble().addVertex((double) i);
					
				}
				
			}else if(dataType.equals("String")) {
				
				for(int i=0;i<numberOfVertices;i++) {
					
					Circle vertex = new Circle(20, Color.WHITE);
		   			vertex.setStroke(Color.BLACK);
		   			Text text = new Text (Character.toString(alphabet.charAt(i)));
		   			
		   			StackPane stack = new StackPane();
		   			
		   			stack.getChildren().addAll(vertex, text);
		   			
		   			stack.setOnMouseClicked(Event::consume);
		   			stack.setOnMousePressed(new ClickedOnVertexHandler(gpc,stack));
		   			stack.setOnMouseDragged(gpc.mouseDraggedOnVertexEvent());
		   			stack.setOnMouseReleased(gpc.mouseReleasedOnVertexEvent());
		   			
		   			listOfVertices.add(stack);
		   			gpc.getDataModel().getDirectedWeightedString().addVertex(text.getText());
					
				}
				
			}
			
		}
		
		
		
		return listOfVertices;
		
	}
	
	/**
	 * This method will call the createNVertices(...) method and then place the N vertices in random places on the GUI.
	 * @param numberOfVertices
	 */
	public void createRandomVertices(int numberOfVertices) {
		
		Random random = new Random();
		
		ArrayList<StackPane> listOfVertices = new ArrayList<StackPane>();
		
		if(gpc.getSelectedTabName().equals("Undirected Non-Weighted Graph")) {
			
			if(gpc.getSelectedDataChoiceUndirectedNonWeightedGraph().equals("Integer")) {
				
				listOfVertices = createNVertices(numberOfVertices,"Integer");
				
			}else if(gpc.getSelectedDataChoiceUndirectedNonWeightedGraph().equals("Double")) {
				
				listOfVertices = createNVertices(numberOfVertices,"Double");
				
			}else if(gpc.getSelectedDataChoiceUndirectedNonWeightedGraph().equals("String")) {
				
				listOfVertices = createNVertices(numberOfVertices,"String");
				
			}
			
			for(int i=0;i<listOfVertices.size();i++) {
				
				int ranX = random.nextInt((int) (gpc.getCenterPaneUndirectedNonWeightedGraph().getWidth()-21)); // random value from 0 to width
				int ranY = random.nextInt((int) (gpc.getCenterPaneUndirectedNonWeightedGraph().getHeight()-21));
				gpc.getCenterPaneUndirectedNonWeightedGraph().getChildren().add(listOfVertices.get(i));
				listOfVertices.get(i).setLayoutX(ranX);
				listOfVertices.get(i).setLayoutY(ranY);
				
			}
			
		}else if(gpc.getSelectedTabName().equals("Undirected Weighted Graph")) {
			
			if(gpc.getSelectedDataChoiceUndirectedWeightedGraph().equals("Integer")) {
				
				listOfVertices = createNVertices(numberOfVertices,"Integer");
				
			}else if(gpc.getSelectedDataChoiceUndirectedWeightedGraph().equals("Double")) {
				
				listOfVertices = createNVertices(numberOfVertices,"Double");
				
			}else if(gpc.getSelectedDataChoiceUndirectedWeightedGraph().equals("String")) {
				
				listOfVertices = createNVertices(numberOfVertices,"String");
				
			}
			
			for(int i=0;i<listOfVertices.size();i++) {
				
				int ranX = random.nextInt((int) (gpc.getCenterPaneUndirectedWeightedGraph().getWidth()-21)); // random value from 0 to width
				int ranY = random.nextInt((int) (gpc.getCenterPaneUndirectedWeightedGraph().getHeight()-21));
				gpc.getCenterPaneUndirectedWeightedGraph().getChildren().add(listOfVertices.get(i));
				listOfVertices.get(i).setLayoutX(ranX);
				listOfVertices.get(i).setLayoutY(ranY);
				
			}
			
		}else if(gpc.getSelectedTabName().equals("Directed Non-Weighted Graph")) {
			
			if(gpc.getSelectedDataChoiceDirectedNonWeightedGraph().equals("Integer")) {
				
				listOfVertices = createNVertices(numberOfVertices,"Integer");
				
			}else if(gpc.getSelectedDataChoiceDirectedNonWeightedGraph().equals("Double")) {
				
				listOfVertices = createNVertices(numberOfVertices,"Double");
				
			}else if(gpc.getSelectedDataChoiceDirectedNonWeightedGraph().equals("String")) {
				
				listOfVertices = createNVertices(numberOfVertices,"String");
				
			}
			
			for(int i=0;i<listOfVertices.size();i++) {
				
				int ranX = random.nextInt((int) (gpc.getCenterPaneDirectedNonWeightedGraph().getWidth()-21)); // random value from 0 to width
				int ranY = random.nextInt((int) (gpc.getCenterPaneDirectedNonWeightedGraph().getHeight()-21));
				gpc.getCenterPaneDirectedNonWeightedGraph().getChildren().add(listOfVertices.get(i));
				listOfVertices.get(i).setLayoutX(ranX);
				listOfVertices.get(i).setLayoutY(ranY);
				
			}
			
		}else if(gpc.getSelectedTabName().equals("Directed Weighted Graph")) {
			
			if(gpc.getSelectedDataChoiceDirectedWeightedGraph().equals("Integer")) {
				
				listOfVertices = createNVertices(numberOfVertices,"Integer");
				
			}else if(gpc.getSelectedDataChoiceDirectedWeightedGraph().equals("Double")) {
				
				listOfVertices = createNVertices(numberOfVertices,"Double");
				
			}else if(gpc.getSelectedDataChoiceDirectedWeightedGraph().equals("String")) {
				
				listOfVertices = createNVertices(numberOfVertices,"String");
				
			}
			
			for(int i=0;i<listOfVertices.size();i++) {
				
				int ranX = random.nextInt((int) (gpc.getCenterPaneDirectedWeightedGraph().getWidth()-21)); // random value from 0 to width
				int ranY = random.nextInt((int) (gpc.getCenterPaneDirectedWeightedGraph().getHeight()-21));
				gpc.getCenterPaneDirectedWeightedGraph().getChildren().add(listOfVertices.get(i));
				listOfVertices.get(i).setLayoutX(ranX);
				listOfVertices.get(i).setLayoutY(ranY);
				
			}
			
		}
		
		
	}
	
	/**
	 * This method when called will clear the graph the user is currently on.
	 */
	public void resetGraphs() {
		
		String dataType = "";
		
		if(gpc.getSelectedTabName().equals("Undirected Non-Weighted Graph")) {
			
			 dataType = gpc.getSelectedDataChoiceUndirectedNonWeightedGraph();
			
		}else if(gpc.getSelectedTabName().equals("Undirected Weighted Graph")) {
			
			dataType = gpc.getSelectedDataChoiceUndirectedWeightedGraph();
			
		}else if(gpc.getSelectedTabName().equals("Directed Non-Weighted Graph")) {
			
			dataType = gpc.getSelectedDataChoiceDirectedNonWeightedGraph();
			
		}else if(gpc.getSelectedTabName().equals("Directed Weighted Graph")) {
			
			dataType = gpc.getSelectedDataChoiceDirectedWeightedGraph();
			
		}
		
    	if(gpc.getSelectedTabName().equals("Undirected Non-Weighted Graph")) {
 				
			gpc.getCenterPaneUndirectedNonWeightedGraph().getChildren().clear();
			
        	 if(dataType.equals("Integer")) {
        		 
        		gpc.getDataModel().getListOfUndirectedNonWeightedIntVertices().clear();
        		gpc.getDataModel().getVertexDataUndirectedNonWeightedInt().clear();
        		gpc.getDataModel().getUndirectedNonWeightedInt().clearGraph();
        		 
        	 }else if(dataType.equals("Double")) {
        		 
        		gpc.getDataModel().getListOfUndirectedNonWeightedDoubleVertices().clear();
        		gpc.getDataModel().getVertexDataUndirectedNonWeightedDouble().clear();
        		gpc.getDataModel().getUndirectedNonWeightedDouble().clearGraph();
        		 
        	 }else if(dataType.equals("String")) {
 	        		 
        		gpc.getDataModel().getListOfUndirectedNonWeightedStringVertices().clear();
        		gpc.getDataModel().getVertexDataUndirectedNonWeightedString().clear();
        		gpc.getDataModel().getUndirectedNonWeightedString().clearGraph();
        		 
        	 }
 

 		}
 		
 		else if(gpc.getSelectedTabName().equals("Undirected Weighted Graph")) {
 				
			gpc.getCenterPaneUndirectedWeightedGraph().getChildren().clear();
			
        	 if(dataType.equals("Integer")) {
        		 
        		 gpc.getDataModel().getListOfUndirectedWeightedIntVertices().clear();
        		 gpc.getDataModel().getVertexDataUndirectedWeightedInt().clear();
        		 gpc.getDataModel().getUndirectedWeightedInt().clearGraph();
        		 
        	 }else if(dataType.equals("Double")) {
        		 
        		gpc.getDataModel().getListOfUndirectedWeightedDoubleVertices().clear();
        		gpc.getDataModel().getVertexDataUndirectedWeightedDouble().clear();
        		gpc.getDataModel().getUndirectedWeightedDouble().clearGraph();
        		 
        	 }else if(dataType.equals("String")) {
        		 
        		gpc.getDataModel().getListOfUndirectedWeightedStringVertices().clear();
        		gpc.getDataModel().getVertexDataUndirectedWeightedString().clear();
        		gpc.getDataModel().getUndirectedWeightedString().clearGraph();
        		 
        	 }
 	 			
 	 	}
 		
 		else if(gpc.getSelectedTabName().equals("Directed Non-Weighted Graph")) {
 				
			gpc.getCenterPaneDirectedNonWeightedGraph().getChildren().clear();
			
        	 if(dataType.equals("Integer")) {
        		 
        		 gpc.getDataModel().getListOfDirectedNonWeightedIntVertices().clear();
        		 gpc.getDataModel().getVertexDataDirectedNonWeightedInt().clear();
        		 gpc.getDataModel().getDirectedNonWeightedInt().clearGraph();
        		 
        	 }else if(dataType.equals("Double")) {
        		 
        		gpc.getDataModel().getListOfDirectedNonWeightedDoubleVertices().clear();
        		gpc.getDataModel().getVertexDataDirectedNonWeightedDouble().clear();
        		gpc.getDataModel().getDirectedNonWeightedDouble().clearGraph();
        		 
        	 }else if(dataType.equals("String")) {
        		 
        		gpc.getDataModel().getListOfDirectedNonWeightedStringVertices().clear();
        		gpc.getDataModel().getVertexDataDirectedNonWeightedString().clear();
        		gpc.getDataModel().getDirectedNonWeightedString().clearGraph();
        		 
        	 }
 		}
 			
 		else if(gpc.getSelectedTabName().equals("Directed Weighted Graph")) {
	 				
 				gpc.getCenterPaneDirectedWeightedGraph().getChildren().clear();
 				
 	        	 if(dataType.equals("Integer")) {
 	        		 
 	        		 gpc.getDataModel().getListOfDirectedWeightedIntVertices().clear();
 	        		 gpc.getDataModel().getVertexDataDirectedWeightedInt().clear();
 	        		 gpc.getDataModel().getDirectedWeightedInt().clearGraph();
 	        		 
 	        	 }else if(dataType.equals("Double")) {
 	        		 
 	        		gpc.getDataModel().getListOfDirectedWeightedDoubleVertices().clear();
	        		gpc.getDataModel().getVertexDataDirectedWeightedDouble().clear();
	        		gpc.getDataModel().getDirectedWeightedDouble().clearGraph();
 	        		 
 	        	 }else if(dataType.equals("String")) {
 	        		 
 	        		gpc.getDataModel().getListOfDirectedWeightedStringVertices().clear();
	        		gpc.getDataModel().getVertexDataDirectedWeightedString().clear();
	        		gpc.getDataModel().getDirectedWeightedString().clearGraph();
 	        		 
 	        	 }
	 		}
		
		
	}
	
	public boolean isInputValid(String numberOfVertices, String numberOfEdges) {
		
		String errorMessage = "";
		
		if (numberOfVertices == null || numberOfVertices.length() == 0 || numberOfEdges == null || numberOfEdges.length() == 0) {
    		
    		errorMessage += "Fields cannot be left empty please add some data to all fields.";
    		
    	}else if(!isInteger(numberOfVertices)) {
    		
    		errorMessage += "You must provide and integer value for the number of vertices field.";
    		
    	}else if(!isInteger(numberOfEdges)) {
    		
    		errorMessage += "You must provide and integer value for the number of edges field.";
    		
    	}else if(Integer.parseInt(numberOfVertices)<=0 || Integer.parseInt(numberOfEdges)<=0) {
    		
    		errorMessage += "You must provide and integer value greater than 0.";
    		
    	}else {
		
			int nVertices = 0;
			int nEdges = 0;
			if(!numberOfVertices.equals("") && !numberOfEdges.equals("")) {
				
				 nVertices = Integer.parseInt(numberOfVertices);
				 nEdges = Integer.parseInt(numberOfEdges);
				
			}
			
			int maxNumberOfEdgesUndirected = (nVertices*(nVertices-1))/2;
//			int maxNumberOfEdgesDirected = nVertices*(nVertices-1);
			int maxNumberOfEdgesDirected = nVertices*nVertices;
			
			if((gpc.getSelectedTabName().equals("Directed Non-Weighted Graph") || gpc.getSelectedTabName().equals("Directed Weighted Graph"))
					&& nEdges>maxNumberOfEdgesDirected) {
				
				errorMessage += "A directed graph with " + numberOfVertices + " vertices can have no more than " + maxNumberOfEdgesDirected + " edges.";
				
			}else if((gpc.getSelectedTabName().equals("Undirected Non-Weighted Graph") || gpc.getSelectedTabName().equals("Undirected Weighted Graph"))
					&& nEdges>maxNumberOfEdgesUndirected) {
				
				errorMessage += "A undirected graph with " + numberOfVertices + " vertices can have no more than " + maxNumberOfEdgesUndirected + " edges.";
				
			}
    	}
    	
    	
    	if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Invalid Edge Fields.");
            alert.setHeaderText("You have provided incorrect data.");
            alert.setContentText(errorMessage);
            
            alert.showAndWait();
            
            return false;
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

}
