package application.view;

import java.util.ArrayList;
import java.util.Random;

import javafx.event.Event;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

public class RandomGraphGenerator {
	
	private GraphPanelController gpc;
	
	public RandomGraphGenerator(GraphPanelController gpc) {
		
		this.gpc = gpc;
		
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
		   			gpc.getDataModel().getUndirectedNonWeightedString().addVertex(text.toString());
					
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
