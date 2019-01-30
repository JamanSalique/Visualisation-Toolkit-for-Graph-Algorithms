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
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Pair;

public class RandomGraphGenerator {
	
	private GraphPanelController gpc;
	private ClickedOnVertexHandler covh;
	
	public RandomGraphGenerator(GraphPanelController gpc) {
		
		this.gpc = gpc;
		this.covh = new ClickedOnVertexHandler(gpc,null);
		
	}
	
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
		
//		String dataType = gpc.getSelectedDataChoiceUndirectedNonWeightedGraph();
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
			
			for(int i=0;i<numberOfEdges;i++) {
				
				while(pairsOfVertices.contains(pairToAdd) || pairsOfVertices.contains(pairToAddReversed)) {
					
					Collections.shuffle(listOfNumbers);
					
					 vertex1 = listOfNumbers.get(0);
					 vertex2 =  listOfNumbers.get(1);
					
					pairToAdd = new Pair<Integer,Integer>(vertex1,vertex2);
					pairToAddReversed = new Pair<Integer,Integer>(vertex2,vertex1);
					
				}
				
				pairsOfVertices.add(pairToAdd);
				covh.addEdgeHandlerNonWeighted(pairToAdd.getKey().toString(), pairToAdd.getValue().toString());
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
				
				while(pairsOfVertices.contains(pairToAdd) || pairsOfVertices.contains(pairToAddReversed)) {
					
					Collections.shuffle(listOfNumbers);
					
					 vertex1 = listOfNumbers.get(0);
					 vertex2 =  listOfNumbers.get(1);
					
					pairToAdd = new Pair<Double,Double>(vertex1,vertex2);
					pairToAddReversed = new Pair<Double,Double>(vertex2,vertex1);
					
				}
				
				pairsOfVertices.add(pairToAdd);
				covh.addEdgeHandlerNonWeighted(pairToAdd.getKey().toString(), pairToAdd.getValue().toString());
				
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
				
				while(pairsOfVertices.contains(pairToAdd) || pairsOfVertices.contains(pairToAddReversed)) {
					
					Collections.shuffle(listOfLetters);
					
					 vertex1 = listOfLetters.get(0);
					 vertex2 =  listOfLetters.get(1);
					
					pairToAdd = new Pair<String,String>(vertex1,vertex2);
					pairToAddReversed = new Pair<String,String>(vertex2,vertex1);
					
				}
				
				pairsOfVertices.add(pairToAdd);
				covh.addEdgeHandlerNonWeighted(pairToAdd.getKey().toString(), pairToAdd.getValue().toString());
				
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
	    	    
	    	    
	    		
	    		gpc.getCenterPaneUndirectedWeightedGraph().getChildren().add(0,line);
	    		gpc.getCenterPaneUndirectedWeightedGraph().getChildren().add(label);
			}
			
			
		}else if(gpc.getSelectedTabName().equals("Directed Non-Weighted Graph")) {
			
		}else if(gpc.getSelectedTabName().equals("Directed Weighted Graph")) {
			
		}
	}

	
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
		   			gpc.getDataModel().getUndirectedWeightedString().addVertex(text.toString());
					
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
		   			gpc.getDataModel().getDirectedNonWeightedString().addVertex(text.toString());
					
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
		   			gpc.getDataModel().getDirectedWeightedString().addVertex(text.toString());
					
				}
				
			}
			
		}
		
		
		
		return listOfVertices;
		
	}
	
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

}
