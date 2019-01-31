package application.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

import application.model.UndirectedWeightedGraph;
import application.model.Vertex;
import javafx.animation.SequentialTransition;
import javafx.animation.Animation.Status;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Tab;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.util.Pair;

public class PrimsAlgorithm <T extends Comparable<? super T>>{
	
	private GraphPanelController gpc;
	
	private SequentialTransition mainAnimation;

	
	private Animations animations;
	
	public PrimsAlgorithm(GraphPanelController gpc) {
		this.gpc = gpc;
		mainAnimation = new SequentialTransition();
		mainAnimation.rateProperty().bind(gpc.getAnimationSpeedSlider().valueProperty());

		
		animations = new Animations(gpc);
	}
	
	public void performPrimsAlgorithmUndirectedWeighted(UndirectedWeightedGraph<T> graph, T startingVertex){
		
		ArrayList<String> edgesInMST = new ArrayList<String>();
		double weightOfMST = 0;
		
		if(gpc.getCenterPaneUndirectedWeightedGraph().getChildren().size() == 1 ||
				graph.getAdjacencyList().get(graph.returnVertex(startingVertex)).size() == 0) {
			
			mainAnimation.getChildren().add(animations.fillVertexTransition(
	        		startingVertex.toString(),"Undirected Weighted"));
			
			double roundedTotalWeightMST = Math.round(weightOfMST * 100.0) / 100.0;
			gpc.getOutputBox().setText("Edges in the minimum spanning tree: " + Arrays.toString(edgesInMST.toArray()) + "\n" +
					"Total Weight of minimum spanning tree is: " + roundedTotalWeightMST);
			
			return;
		}
		
		 PriorityQueue<Pair<Pair<T,T>,Double>> pq = new PriorityQueue<>((Object o1, Object o2) -> {
			 Pair<Pair<T,T>,Double> first = (Pair<Pair<T,T>,Double>)o1;
			 Pair<Pair<T,T>,Double> second = (Pair<Pair<T,T>,Double>)o2;
	
		        if(first.getValue()<second.getValue())return -1;
		        else if(first.getValue()>second.getValue())return 1;
		        else return 0;
		    });
		 	
		ArrayList<T> visitedVertices = new ArrayList<T>();
		Iterable<Pair<Vertex<T>, Double>> neighboursIterable = graph.getNeighbours(startingVertex);
		ArrayList<Pair<Vertex<T>, Double>>  listOfNeighbours = new ArrayList<Pair<Vertex<T>, Double>> ();
		neighboursIterable.forEach(listOfNeighbours::add);
			
		for(Pair<Vertex<T>, Double> p : listOfNeighbours) {
				
			 Pair<Pair<T,T>,Double> toAdd = new  Pair<Pair<T,T>,Double>(new Pair<T,T>(startingVertex,p.getKey().getElement()),p.getValue());
			 pq.add(toAdd);
				
		}
			
		while(!pq.isEmpty()) {
			
			Pair<Pair<T,T>,Double> edge = pq.poll();
			T vertex1 = edge.getKey().getKey();
			T vertex2 = edge.getKey().getValue();

			 if(!visitedVertices.contains(vertex2)) {
				 
				 visitedVertices.add(vertex1);
				 
				 mainAnimation.getChildren().add(animations.fillVertexTransition(
			        		vertex1.toString(),"Undirected Weighted"));
				 
				 mainAnimation.getChildren().add(animations.highlightEdgeTransition(vertex1.toString(),
							vertex2.toString(), "Undirected Weighted"));
				 
				 edgesInMST.add(vertex1.toString() + "-" + vertex2.toString());
				 weightOfMST += edge.getValue();
				 
				 Iterable<Pair<Vertex<T>, Double>> neighboursIterableOfVertex2 = graph.getNeighbours(vertex2);
					ArrayList<Pair<Vertex<T>, Double>>  listOfNeighboursOfVertex2 = new ArrayList<Pair<Vertex<T>, Double>> ();
					neighboursIterableOfVertex2.forEach(listOfNeighboursOfVertex2::add);
						
					for(Pair<Vertex<T>, Double> p : listOfNeighboursOfVertex2) {
							
						 Pair<Pair<T,T>,Double> toAdd = new  Pair<Pair<T,T>,Double>(new Pair<T,T>(vertex2,p.getKey().getElement()),p.getValue());
						 pq.add(toAdd);
							
					}
					
					mainAnimation.getChildren().add(animations.fillVertexTransition(
			        		vertex2.toString(),"Undirected Weighted"));
					
					visitedVertices.add(vertex2);
				 
			 }
			
		}
		
		double roundedTotalWeightMST = Math.round(weightOfMST * 100.0) / 100.0;
		gpc.getOutputBox().setText("Edges in the minimum spanning tree: " + Arrays.toString(edgesInMST.toArray()) + "\n" +
				"Total Weight of minimum spanning tree is: " + roundedTotalWeightMST);
		
	}
	
	public void playMainAnimation() {
		
		if(gpc.getSelectedTabName().equals("Undirected Non-Weighted Graph") && mainAnimation.getStatus() == Status.STOPPED) {
			
			animations.resetGraphColours("Undirected Non Weighted");
			
		}else if(gpc.getSelectedTabName().equals("Undirected Weighted Graph") && mainAnimation.getStatus() == Status.STOPPED) {
			
			animations.resetGraphColours("Undirected Weighted");
			
		}else if(gpc.getSelectedTabName().equals("Directed Non-Weighted Graph") && mainAnimation.getStatus() == Status.STOPPED) {
			
			animations.resetGraphColours("Directed Non Weighted");
			
		}else if(gpc.getSelectedTabName().equals("Directed Weighted Graph") && mainAnimation.getStatus() == Status.STOPPED) {
			
			animations.resetGraphColours("Directed Weighted");
			
		}
		
		mainAnimation.play();
		mainAnimation.setOnFinished(new EventHandler<ActionEvent>() {

	        @Override
	        public void handle(ActionEvent event) {
	            gpc.getPlayButton().setText("Play");
				
				for(Tab tab : gpc.getTabs().getTabs()) {
					tab.setDisable(false);
				}

				if(mainAnimation.getChildren().size()>0) {
					mainAnimation.getChildren().clear();
				}
	        }
	    });
		
	}
	
	public void pauseMainAnimation() {
		mainAnimation.pause();
	}
	
	public void stopMainAnimation(String graphType) {
		mainAnimation.stop();
		animations.resetGraphColours(graphType);

	}

	public SequentialTransition getMainAnimation() {
		return mainAnimation;
	}

}
