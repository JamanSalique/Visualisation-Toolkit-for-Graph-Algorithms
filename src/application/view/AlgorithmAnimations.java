package application.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

import application.model.Vertex;
import javafx.animation.Animation.Status;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.Region;
import javafx.util.Pair;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Tab;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;

/**
 * This class holds all the algorithms that can be played by the user (The actual functionality of algorithms are in separate classes).
 * The main purpose of this class is to respond to whatever algorithm the user clicks and wants to play in the graphical user interface.
 * @author jamansalique
 *
 */
public class AlgorithmAnimations {

	private GraphPanelController gpc;
	
	private BreadthFirstSearch bfs;
	private String bfsStartVertexUndirectedNonWeighted, bfsStartVertexUndirectedWeighted, 
					bfsStartVertexDirectedNonWeighted, bfsStartVertexDirectedWeighted ;
	
	private DepthFirstSearch dfs;
	private String dfsStartVertexUndirectedNonWeighted, dfsStartVertexUndirectedWeighted, 
					dfsStartVertexDirectedNonWeighted, dfsStartVertexDirectedWeighted ;
	
	private DijkstraAlgorithm dijkstra;
	private String dijkstraStartVertexUndirectedWeighted, dijkstraStartVertexDirectedWeighted ;
	
	private KruskalAlgorithm kruskal;
	
	private PrimsAlgorithm prims;
	private String primsStartingVertex;
	
	private VertexCover vertexCover;
	
	private KosarajuAlgorithm kosaraju;
	
	private MyAlgorithm myAlgorithm;
	private String myAlgorithmStartVertexUndirectedNonWeighted, myAlgorithmStartVertexUndirectedWeighted, 
	myAlgorithmStartVertexDirectedNonWeighted, myAlgorithmStartVertexDirectedWeighted ;
	
	/**
	 * Constructor which initialises all algorithm objects.
	 * @param gpc
	 */
	public AlgorithmAnimations(GraphPanelController gpc) {
		this.gpc = gpc;
		bfs = new BreadthFirstSearch(gpc);
		dfs = new DepthFirstSearch(gpc);
		dijkstra = new DijkstraAlgorithm(gpc);
		kruskal = new KruskalAlgorithm(gpc);
		prims = new PrimsAlgorithm(gpc);
		vertexCover = new VertexCover(gpc);
		kosaraju = new KosarajuAlgorithm(gpc);
		myAlgorithm = new MyAlgorithm(gpc);
	}
	
	/**
	 * This method is called when the user has chosen to play the breadth first search animation and presses the play button. If the breadth first
	 * search animations is playing and the user presses the play button then the animation pauses.
	 */
	public void playBreadthFirstSearch() {
		
		if(bfs.getMainAnimation().getStatus() == Status.STOPPED) {

			TextInputDialog dialogNonWeightedEdge = new TextInputDialog();
	        dialogNonWeightedEdge.setTitle("Starting vertex");
	        dialogNonWeightedEdge.setHeaderText("Choose starting vertex");
	        dialogNonWeightedEdge.setContentText("Please enter the vertex you would like to start the breadth first search from.");
	        ButtonType cancelButton = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
        	ButtonType okButton = new ButtonType("Ok", ButtonData.OK_DONE);
        	dialogNonWeightedEdge.getDialogPane().getButtonTypes().setAll(okButton,cancelButton);
        	dialogNonWeightedEdge.getDialogPane().getStylesheets().add("/application/global.css");
        	Optional<String> result = dialogNonWeightedEdge.showAndWait();
			
        	if(result.isPresent() && gpc.isInputValidStartingVertex(result.get())) {
        		
        		if(gpc.getSelectedTabName().equals("Undirected Non-Weighted Graph")) {
        			
        			bfsStartVertexUndirectedNonWeighted = result.get();
            		
            		validateAndPlayBFS(bfsStartVertexUndirectedNonWeighted);
        			
        		}else if(gpc.getSelectedTabName().equals("Undirected Weighted Graph")) {
        			
        			bfsStartVertexUndirectedWeighted = result.get();
            		
            		validateAndPlayBFS(bfsStartVertexUndirectedWeighted);
        			
        		}else if(gpc.getSelectedTabName().equals("Directed Non-Weighted Graph")) {
        			
        			bfsStartVertexDirectedNonWeighted = result.get();
            		
            		validateAndPlayBFS(bfsStartVertexDirectedNonWeighted);
        			
        		}else if(gpc.getSelectedTabName().equals("Directed Weighted Graph")) {
        			
        			bfsStartVertexDirectedWeighted = result.get();
            		
            		validateAndPlayBFS(bfsStartVertexDirectedWeighted);
        			
        		}
        		
				bfs.playMainAnimation();
				gpc.getPlayButton().setText("Pause");
				
				for(Tab tab : gpc.getTabs().getTabs()) {
					tab.setDisable(true);
				}
        		
        	}
			
		}else if(bfs.getMainAnimation().getStatus() == Status.RUNNING){
			
			bfs.pauseMainAnimation();
			gpc.getPlayButton().setText("Play");
			
		}else if(bfs.getMainAnimation().getStatus() == Status.PAUSED) {
			
			bfs.playMainAnimation();
			gpc.getPlayButton().setText("Pause");
			
		}
		
	}
	
	/**
	 * This method is called when the user presses the start again button while the breadth first search animation is playing. This method will
	 * start the animation from the beginning.
	 */
	public void restartBreadthFirstSearch() {
		
		if(bfs.getMainAnimation().getChildren().size()>0) {
			bfs.getMainAnimation().getChildren().clear();
		}
		
		if(gpc.getSelectedTabName().equals("Undirected Non-Weighted Graph")) {
    		
    		validateAndPlayBFS(bfsStartVertexUndirectedNonWeighted);
    		bfs.stopMainAnimation("Undirected Non Weighted");
			
		}else if(gpc.getSelectedTabName().equals("Undirected Weighted Graph")) {
			
    		validateAndPlayBFS(bfsStartVertexUndirectedWeighted);
    		bfs.stopMainAnimation("Undirected Weighted");
			
		}else if(gpc.getSelectedTabName().equals("Directed Non-Weighted Graph")) {
    		
    		validateAndPlayBFS(bfsStartVertexDirectedNonWeighted);
    		bfs.stopMainAnimation("Directed Non Weighted");
			
		}else if(gpc.getSelectedTabName().equals("Directed Weighted Graph")) {
    		
    		validateAndPlayBFS(bfsStartVertexDirectedWeighted);
    		bfs.stopMainAnimation("Directed Weighted");
			
		}
		
		bfs.playMainAnimation();
		
		gpc.getPlayButton().setText("Pause");
		
		for(Tab tab : gpc.getTabs().getTabs()) {
			tab.setDisable(true);
		}
		
	}
	
	/**
	 * This method is called when the user presses the Skip to end button while the breadth first search animation is playing. 
	 * This method will skip the entire breadth first search animation to the end showing the final state of the graph animation.
	 */
	public void skipBreadthFirstSearchToEnd() {
		bfs.getMainAnimation().jumpTo("end");
		for(Tab tab : gpc.getTabs().getTabs()) {
			tab.setDisable(false);
		}
	}
	
	/**
	 * This is a helper method and is called in the method playBreadthFirstSearch(). This method calls the method from the breadth first search object
	 * and supplies the correct parameters depending what type of graph the user wants the animation to be played on and the type of data the vertices
	 * hold in the graph.
	 * @param bfsStartVertex
	 */
	private void validateAndPlayBFS(String bfsStartVertex) {
		
		if(gpc.getSelectedTabName().equals("Undirected Non-Weighted Graph")) {
			if(gpc.getSelectedDataChoiceUndirectedNonWeightedGraph().equals("Integer")) {
				
				bfs.performBreadthFirstSearchUndirectedNonWeighted(gpc.getDataModel().getUndirectedNonWeightedInt(), Integer.parseInt(bfsStartVertex));
				
			}else if(gpc.getSelectedDataChoiceUndirectedNonWeightedGraph().equals("Double")) {
				
				bfs.performBreadthFirstSearchUndirectedNonWeighted(gpc.getDataModel().getUndirectedNonWeightedDouble(), Double.parseDouble(bfsStartVertex));
				
			}else if(gpc.getSelectedDataChoiceUndirectedNonWeightedGraph().equals("String")) {
				
				bfs.performBreadthFirstSearchUndirectedNonWeighted(gpc.getDataModel().getUndirectedNonWeightedString(), bfsStartVertex);
				
			}
		}else if(gpc.getSelectedTabName().equals("Undirected Weighted Graph")) {
			
			if(gpc.getSelectedDataChoiceUndirectedWeightedGraph().equals("Integer")) {
				
				bfs.performBreadthFirstSearchUndirectedWeighted(gpc.getDataModel().getUndirectedWeightedInt(), Integer.parseInt(bfsStartVertex));
				
			}else if(gpc.getSelectedDataChoiceUndirectedWeightedGraph().equals("Double")) {
				
				bfs.performBreadthFirstSearchUndirectedWeighted(gpc.getDataModel().getUndirectedWeightedDouble(), Double.parseDouble(bfsStartVertex));
				
			}else if(gpc.getSelectedDataChoiceUndirectedWeightedGraph().equals("String")) {
				
				bfs.performBreadthFirstSearchUndirectedWeighted(gpc.getDataModel().getUndirectedWeightedString(), bfsStartVertex);
				
			}
			
		}else if(gpc.getSelectedTabName().equals("Directed Non-Weighted Graph")) {
			
			if(gpc.getSelectedDataChoiceDirectedNonWeightedGraph().equals("Integer")) {
				
				bfs.performBreadthFirstSearchDirectedNonWeighted(gpc.getDataModel().getDirectedNonWeightedInt(), Integer.parseInt(bfsStartVertex));
				
			}else if(gpc.getSelectedDataChoiceDirectedNonWeightedGraph().equals("Double")) {
				
				bfs.performBreadthFirstSearchDirectedNonWeighted(gpc.getDataModel().getDirectedNonWeightedDouble(), Double.parseDouble(bfsStartVertex));
				
			}else if(gpc.getSelectedDataChoiceDirectedNonWeightedGraph().equals("String")) {
				
				bfs.performBreadthFirstSearchDirectedNonWeighted(gpc.getDataModel().getDirectedNonWeightedString(), bfsStartVertex);
				
			}
			
		}else if(gpc.getSelectedTabName().equals("Directed Weighted Graph")) {
			
			if(gpc.getSelectedDataChoiceDirectedWeightedGraph().equals("Integer")) {
				
				bfs.performBreadthFirstSearchDirectedWeighted(gpc.getDataModel().getDirectedWeightedInt(), Integer.parseInt(bfsStartVertex));
				
			}else if(gpc.getSelectedDataChoiceDirectedWeightedGraph().equals("Double")) {
				
				bfs.performBreadthFirstSearchDirectedWeighted(gpc.getDataModel().getDirectedWeightedDouble(), Double.parseDouble(bfsStartVertex));
				
			}else if(gpc.getSelectedDataChoiceDirectedWeightedGraph().equals("String")) {
				
				bfs.performBreadthFirstSearchDirectedWeighted(gpc.getDataModel().getDirectedWeightedString(), bfsStartVertex);
				
			}
			
		}
		
	}
	
	/**
	 * This is a helper method and is called in the method playDepthFirstSearch(). This method calls the method from the depth first search object
	 * and supplies the correct parameters depending what type of graph the user wants the animation to be played on and the type of data the vertices
	 * hold in the graph.
	 * @param dfsStartVertex
	 */
	private void validateAndPlayDFS(String dfsStartVertex) {
		
		if(gpc.getSelectedTabName().equals("Undirected Non-Weighted Graph")) {
			if(gpc.getSelectedDataChoiceUndirectedNonWeightedGraph().equals("Integer")) {
				
				dfs.performDepthFirstSearchUndirectedNonWeighted(gpc.getDataModel().getUndirectedNonWeightedInt(), Integer.parseInt(dfsStartVertex),
						new ArrayList<Vertex<Integer>>(),new ArrayList<Vertex<Integer>>());
				
			}else if(gpc.getSelectedDataChoiceUndirectedNonWeightedGraph().equals("Double")) {
				
				dfs.performDepthFirstSearchUndirectedNonWeighted(gpc.getDataModel().getUndirectedNonWeightedDouble(), Double.parseDouble(dfsStartVertex),
						new ArrayList<Vertex<Double>>(),new ArrayList<Vertex<Double>>());
				
			}else if(gpc.getSelectedDataChoiceUndirectedNonWeightedGraph().equals("String")) {
				
				dfs.performDepthFirstSearchUndirectedNonWeighted(gpc.getDataModel().getUndirectedNonWeightedString(), dfsStartVertex,
						new ArrayList<Vertex<String>>(),new ArrayList<Vertex<String>>());
				
			}
		}
		else if(gpc.getSelectedTabName().equals("Undirected Weighted Graph")) {
			
			if(gpc.getSelectedDataChoiceUndirectedWeightedGraph().equals("Integer")) {
				
				dfs.performDepthFirstSearchUndirectedWeighted(gpc.getDataModel().getUndirectedWeightedInt(), Integer.parseInt(dfsStartVertex),
						new ArrayList<Vertex<Integer>>(),new ArrayList<Vertex<Integer>>());
				
			}else if(gpc.getSelectedDataChoiceUndirectedWeightedGraph().equals("Double")) {
				
				dfs.performDepthFirstSearchUndirectedWeighted(gpc.getDataModel().getUndirectedWeightedDouble(), Double.parseDouble(dfsStartVertex),
						new ArrayList<Vertex<Double>>(),new ArrayList<Vertex<Double>>());
				
			}else if(gpc.getSelectedDataChoiceUndirectedWeightedGraph().equals("String")) {
				
				dfs.performDepthFirstSearchUndirectedWeighted(gpc.getDataModel().getUndirectedWeightedString(), dfsStartVertex,
						new ArrayList<Vertex<String>>(),new ArrayList<Vertex<String>>());
				
			}
			
		}else if(gpc.getSelectedTabName().equals("Directed Non-Weighted Graph")) {
			
			if(gpc.getSelectedDataChoiceDirectedNonWeightedGraph().equals("Integer")) {
				
				dfs.performDepthFirstSearchDirectedNonWeighted(gpc.getDataModel().getDirectedNonWeightedInt(), Integer.parseInt(dfsStartVertex),
						new ArrayList<Vertex<Integer>>(),new ArrayList<Vertex<Integer>>());
				
			}else if(gpc.getSelectedDataChoiceDirectedNonWeightedGraph().equals("Double")) {
				
				dfs.performDepthFirstSearchDirectedNonWeighted(gpc.getDataModel().getDirectedNonWeightedDouble(), Double.parseDouble(dfsStartVertex),
						new ArrayList<Vertex<Double>>(),new ArrayList<Vertex<Double>>());
				
			}else if(gpc.getSelectedDataChoiceDirectedNonWeightedGraph().equals("String")) {
				
				dfs.performDepthFirstSearchDirectedNonWeighted(gpc.getDataModel().getDirectedNonWeightedString(), dfsStartVertex,
						new ArrayList<Vertex<String>>(),new ArrayList<Vertex<String>>());
				
			}
			
		}else if(gpc.getSelectedTabName().equals("Directed Weighted Graph")) {
			
			if(gpc.getSelectedDataChoiceDirectedWeightedGraph().equals("Integer")) {
				
				dfs.performDepthFirstSearchDirectedWeighted(gpc.getDataModel().getDirectedWeightedInt(), Integer.parseInt(dfsStartVertex),
						new ArrayList<Vertex<Integer>>(),new ArrayList<Vertex<Integer>>());
				
			}else if(gpc.getSelectedDataChoiceDirectedWeightedGraph().equals("Double")) {
				
				dfs.performDepthFirstSearchDirectedWeighted(gpc.getDataModel().getDirectedWeightedDouble(), Double.parseDouble(dfsStartVertex),
						new ArrayList<Vertex<Double>>(),new ArrayList<Vertex<Double>>());
				
			}else if(gpc.getSelectedDataChoiceDirectedWeightedGraph().equals("String")) {
				
				dfs.performDepthFirstSearchDirectedWeighted(gpc.getDataModel().getDirectedWeightedString(), dfsStartVertex,
						new ArrayList<Vertex<String>>(),new ArrayList<Vertex<String>>());
				
			}
			
		}
		
	}
	
	/**
	 * This method is called when the user has chosen to play the depth first search animation and presses the play button. If the depth first
	 * search animations is playing and the user presses the play button then the animation pauses.
	 */
	public void playDepthFirstSearch() {
		
		if(dfs.getMainAnimation().getStatus() == Status.STOPPED) {

			TextInputDialog dialogNonWeightedEdge = new TextInputDialog();
	        dialogNonWeightedEdge.setTitle("Starting vertex");
	        dialogNonWeightedEdge.setHeaderText("Choose starting vertex");
	        dialogNonWeightedEdge.setContentText("Please enter the vertex you would like to start the depth first search from.");
	        ButtonType cancelButton = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
        	ButtonType okButton = new ButtonType("Ok", ButtonData.OK_DONE);
        	dialogNonWeightedEdge.getDialogPane().getButtonTypes().setAll(okButton,cancelButton);
        	dialogNonWeightedEdge.getDialogPane().getStylesheets().add("/application/global.css");
        	Optional<String> result = dialogNonWeightedEdge.showAndWait();
			
        	if(result.isPresent() && gpc.isInputValidStartingVertex(result.get())) {
        		
        		if(gpc.getSelectedTabName().equals("Undirected Non-Weighted Graph")) {
        			
        			dfsStartVertexUndirectedNonWeighted = result.get();
            		
            		validateAndPlayDFS(dfsStartVertexUndirectedNonWeighted);
        			
        		}else if(gpc.getSelectedTabName().equals("Undirected Weighted Graph")) {
        			
        			dfsStartVertexUndirectedWeighted = result.get();
            		
            		validateAndPlayDFS(dfsStartVertexUndirectedWeighted);
        			
        		}else if(gpc.getSelectedTabName().equals("Directed Non-Weighted Graph")) {
        			
        			dfsStartVertexDirectedNonWeighted = result.get();
            		
            		validateAndPlayDFS(dfsStartVertexDirectedNonWeighted);
        			
        		}else if(gpc.getSelectedTabName().equals("Directed Weighted Graph")) {
        			
        			dfsStartVertexDirectedWeighted = result.get();
            		
            		validateAndPlayDFS(dfsStartVertexDirectedWeighted);
        			
        		}
        		
				dfs.playMainAnimation();
				gpc.getPlayButton().setText("Pause");
				
				for(Tab tab : gpc.getTabs().getTabs()) {
					tab.setDisable(true);
				}
        		
        	}
			
		}else if(dfs.getMainAnimation().getStatus() == Status.RUNNING){
			
			dfs.pauseMainAnimation();
			gpc.getPlayButton().setText("Play");
			
		}else if(dfs.getMainAnimation().getStatus() == Status.PAUSED) {
			
			dfs.playMainAnimation();
			gpc.getPlayButton().setText("Pause");
			
		}
		
	}
	
	/**
	 * This method is called when the user presses the start again button while the depth first search animation is playing. This method will
	 * start the animation from the beginning.
	 */
	public void restartDepthFirstSearch() {
		
		if(dfs.getMainAnimation().getChildren().size()>0) {
			dfs.getMainAnimation().getChildren().clear();
		}
		
		if(gpc.getSelectedTabName().equals("Undirected Non-Weighted Graph")) {
    		
    		validateAndPlayDFS(dfsStartVertexUndirectedNonWeighted);
    		dfs.stopMainAnimation("Undirected Non Weighted");
			
		}else if(gpc.getSelectedTabName().equals("Undirected Weighted Graph")) {
			
    		validateAndPlayDFS(dfsStartVertexUndirectedWeighted);
    		dfs.stopMainAnimation("Undirected Weighted");
			
		}else if(gpc.getSelectedTabName().equals("Directed Non-Weighted Graph")) {
    		
    		validateAndPlayDFS(dfsStartVertexDirectedNonWeighted);
    		dfs.stopMainAnimation("Directed Non Weighted");
			
		}else if(gpc.getSelectedTabName().equals("Directed Weighted Graph")) {
    		
    		validateAndPlayDFS(dfsStartVertexDirectedWeighted);
    		dfs.stopMainAnimation("Directed Weighted");
			
		}
		
		dfs.playMainAnimation();
		
		gpc.getPlayButton().setText("Pause");
		
		for(Tab tab : gpc.getTabs().getTabs()) {
			tab.setDisable(true);
		}
		
	}
	
	/**
	 * This method is called when the user presses the Skip to end button while the depth first search animation is playing. 
	 * This method will skip the entire depth first search animation to the end showing the final state of the graph animation.
	 */
	public void skipDepthFirstSearchToEnd() {
		dfs.getMainAnimation().jumpTo("end");
		for(Tab tab : gpc.getTabs().getTabs()) {
			tab.setDisable(false);
		}
	}
	
	/**
	 * This is a helper method and is called in the method playDijkstraAlgorithm(). This method calls the method from the DijkstraAlgorithm object
	 * and supplies the correct parameters depending what type of graph the user wants the animation to be played on and the type of data the vertices
	 * hold in the graph.
	 * @param dijkstraStartVertex
	 */
	private void validateAndPlayDijkstraAlgorithm(String dijkstraStartVertex) {
		
		
		if(gpc.getSelectedTabName().equals("Undirected Weighted Graph")) {
			
			if(gpc.getSelectedDataChoiceUndirectedWeightedGraph().equals("Integer")) {
				
				HashMap<Integer,Double> distances = new HashMap<Integer,Double>();
				
				for(Vertex<Integer> v : gpc.getDataModel().getUndirectedWeightedInt().getAllVertices()) {
					
					distances.put(v.getElement(), Double.MAX_VALUE);
					
				}
				
				distances.replace(Integer.parseInt(dijkstraStartVertex), 0.0);
				
				dijkstra.performDijkstrasAlgorithmUndirectedWeighted(gpc.getDataModel().getUndirectedWeightedInt(), Integer.parseInt(dijkstraStartVertex),
						new ArrayList<Vertex<Integer>>(),distances);
				
			}
			else if(gpc.getSelectedDataChoiceUndirectedWeightedGraph().equals("Double")) {
				
				HashMap<Double,Double> distances = new HashMap<Double,Double>();
				
				for(Vertex<Double> v : gpc.getDataModel().getUndirectedWeightedDouble().getAllVertices()) {
					
					distances.put(v.getElement(), Double.MAX_VALUE);
					
				}
				
				distances.replace(Double.parseDouble(dijkstraStartVertex), 0.0);
				
				dijkstra.performDijkstrasAlgorithmUndirectedWeighted(gpc.getDataModel().getUndirectedWeightedDouble(), Double.parseDouble(dijkstraStartVertex),
						new ArrayList<Vertex<Double>>(),distances);
				
			}else if(gpc.getSelectedDataChoiceUndirectedWeightedGraph().equals("String")) {
				
				HashMap<String,Double> distances = new HashMap<String,Double>();
				
				for(Vertex<String> v : gpc.getDataModel().getUndirectedWeightedString().getAllVertices()) {
					
					distances.put(v.getElement(), Double.MAX_VALUE);
					
				}
				
				distances.replace(dijkstraStartVertex, 0.0);
				
				dijkstra.performDijkstrasAlgorithmUndirectedWeighted(gpc.getDataModel().getUndirectedWeightedString(), dijkstraStartVertex,
						new ArrayList<Vertex<String>>(),distances);
				
			}
			
		}
		else if(gpc.getSelectedTabName().equals("Directed Weighted Graph")) {
			
			if(gpc.getSelectedDataChoiceDirectedWeightedGraph().equals("Integer")) {
				
				HashMap<Integer,Double> distances = new HashMap<Integer,Double>();
				
				for(Vertex<Integer> v : gpc.getDataModel().getDirectedWeightedInt().getAllVertices()) {
					
					distances.put(v.getElement(), Double.MAX_VALUE);
					
				}
				
				distances.replace(Integer.parseInt(dijkstraStartVertex), 0.0);
				
				dijkstra.performDijkstrasAlgorithmDirectedWeighted(gpc.getDataModel().getDirectedWeightedInt(), Integer.parseInt(dijkstraStartVertex),
						new ArrayList<Vertex<Integer>>(),distances);
				
			}
			else if(gpc.getSelectedDataChoiceDirectedWeightedGraph().equals("Double")) {
				
				HashMap<Double,Double> distances = new HashMap<Double,Double>();
				
				for(Vertex<Double> v : gpc.getDataModel().getDirectedWeightedDouble().getAllVertices()) {
					
					distances.put(v.getElement(), Double.MAX_VALUE);
					
				}
				
				distances.replace(Double.parseDouble(dijkstraStartVertex), 0.0);
				
				dijkstra.performDijkstrasAlgorithmDirectedWeighted(gpc.getDataModel().getDirectedWeightedDouble(), Double.parseDouble(dijkstraStartVertex),
						new ArrayList<Vertex<Double>>(),distances);
				
			}else if(gpc.getSelectedDataChoiceDirectedWeightedGraph().equals("String")) {
				
				HashMap<String,Double> distances = new HashMap<String,Double>();
				
				for(Vertex<String> v : gpc.getDataModel().getDirectedWeightedString().getAllVertices()) {
					
					distances.put(v.getElement(), Double.MAX_VALUE);
					
				}
				
				distances.replace(dijkstraStartVertex, 0.0);
				
				dijkstra.performDijkstrasAlgorithmDirectedWeighted(gpc.getDataModel().getDirectedWeightedString(), dijkstraStartVertex,
						new ArrayList<Vertex<String>>(),distances);
				
			}
			
		}
		
	}
	
	/**
	 * This method is called when the user has chosen to play the dijkstra's algorithm animation and presses the play button. If the dijkstra's algorithm 
	 * animations is playing and the user presses the play button then the animation pauses.
	 */
	public void playDijkstraAlgorithm() {
		
		if(dijkstra.getMainAnimation().getStatus() == Status.STOPPED) {

			TextInputDialog dialogNonWeightedEdge = new TextInputDialog();
	        dialogNonWeightedEdge.setTitle("Starting vertex");
	        dialogNonWeightedEdge.setHeaderText("Choose starting vertex");
	        dialogNonWeightedEdge.setContentText("Please enter the vertex you would like to start the dijkstra algorithm from.");
	        ButtonType cancelButton = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
        	ButtonType okButton = new ButtonType("Ok", ButtonData.OK_DONE);
        	dialogNonWeightedEdge.getDialogPane().getButtonTypes().setAll(okButton,cancelButton);
        	dialogNonWeightedEdge.getDialogPane().getStylesheets().add("/application/global.css");
        	Optional<String> result = dialogNonWeightedEdge.showAndWait();
			
        	if(result.isPresent() && gpc.isInputValidStartingVertex(result.get())) {
        		
        		if(gpc.getSelectedTabName().equals("Undirected Weighted Graph")) {
        			
        			dijkstraStartVertexUndirectedWeighted = result.get();
            		
            		validateAndPlayDijkstraAlgorithm(dijkstraStartVertexUndirectedWeighted);
        			
        		}else if(gpc.getSelectedTabName().equals("Directed Weighted Graph")) {
        			
        			dijkstraStartVertexDirectedWeighted = result.get();
            		
            		validateAndPlayDijkstraAlgorithm(dijkstraStartVertexDirectedWeighted);
        			
        		}
        		
        		dijkstra.playMainAnimation();
				gpc.getPlayButton().setText("Pause");
				
				for(Tab tab : gpc.getTabs().getTabs()) {
					tab.setDisable(true);
				}
        		
        	}
			
		}else if(dijkstra.getMainAnimation().getStatus() == Status.RUNNING){
			
			dijkstra.pauseMainAnimation();
			gpc.getPlayButton().setText("Play");
			
		}else if(dijkstra.getMainAnimation().getStatus() == Status.PAUSED) {
			
			dijkstra.playMainAnimation();
			gpc.getPlayButton().setText("Pause");
			
		}
		
	}
	
	/**
	 * This method is called when the user presses the start again button while dijkstra's algorithm animation is playing. This method will
	 * start the animation from the beginning.
	 */
	public void restartDijkstraAlgorithm() {
		
		if(dijkstra.getMainAnimation().getChildren().size()>0) {
			dijkstra.getMainAnimation().getChildren().clear();
		}
		
		if(gpc.getSelectedTabName().equals("Undirected Weighted Graph")) {
			
    		validateAndPlayDijkstraAlgorithm(dijkstraStartVertexUndirectedWeighted);
    		dijkstra.stopMainAnimation("Undirected Weighted");
			
		}else if(gpc.getSelectedTabName().equals("Directed Weighted Graph")) {
    		
    		validateAndPlayDijkstraAlgorithm(dijkstraStartVertexDirectedWeighted);
    		dijkstra.stopMainAnimation("Directed Weighted");
			
		}
		
		dijkstra.playMainAnimation();
		
		gpc.getPlayButton().setText("Pause");
		
		for(Tab tab : gpc.getTabs().getTabs()) {
			tab.setDisable(true);
		}
		
	}
	
	/**
	 * This method is called when the user presses the Skip to end button while dijkstra's algorithm animation is playing. 
	 * This method will skip the entire animation to the end showing the final state of the graph animation.
	 */
	public void skipDijkstraAlgorithmToEnd() {
		dijkstra.getMainAnimation().jumpTo("end");
		for(Tab tab : gpc.getTabs().getTabs()) {
			tab.setDisable(false);
		}
	}
	
	/**
	 * This is a helper method and is called in the method playKruskalAlgorithm(). This method calls the method from the KruskalAlgorithm object
	 * and supplies the correct parameters depending what type of graph the user wants the animation to be played on and the type of data the vertices
	 * hold in the graph.
	 */
	private void validateAndPlayKruskal() {
		
		if(gpc.getSelectedTabName().equals("Undirected Weighted Graph")) {
			
			if(gpc.getSelectedDataChoiceUndirectedWeightedGraph().equals("Integer")) {
				
				kruskal.performKruskalAlgorithmUndirectedWeighted(gpc.getDataModel().getUndirectedWeightedInt());
				
			}else if(gpc.getSelectedDataChoiceUndirectedWeightedGraph().equals("Double")) {
				
				kruskal.performKruskalAlgorithmUndirectedWeighted(gpc.getDataModel().getUndirectedWeightedDouble());
				
			}else if(gpc.getSelectedDataChoiceUndirectedWeightedGraph().equals("String")) {
				
				kruskal.performKruskalAlgorithmUndirectedWeighted(gpc.getDataModel().getUndirectedWeightedString());
				
			}
			
		}
			
		
	}
	
	/**
	 * This method is called when the user has chosen to play kruskal's algorithm animation and presses the play button. If the kruskal's algorithm 
	 * animations is playing and the user presses the play button then the animation pauses.
	 */
	public void playKruskalAlgorithm() {
		
		if(kruskal.getMainAnimation().getStatus() == Status.STOPPED) {
			
			if(gpc.getSelectedTabName().equals("Undirected Weighted Graph")) {
            		
            		validateAndPlayKruskal();
        			
        		}
        		
        		
				kruskal.playMainAnimation();
				gpc.getPlayButton().setText("Pause");
				
				for(Tab tab : gpc.getTabs().getTabs()) {
					tab.setDisable(true);
				}
        		
    	}else if(kruskal.getMainAnimation().getStatus() == Status.RUNNING){
		
    		kruskal.pauseMainAnimation();
    		gpc.getPlayButton().setText("Play");
			
		}else if(kruskal.getMainAnimation().getStatus() == Status.PAUSED) {
			
			kruskal.playMainAnimation();
			gpc.getPlayButton().setText("Pause");
			
		}
		
	}
	
	/**
	 * This method is called when the user presses the start again button while kruskal's algorithm animation is playing. This method will
	 * start the animation from the beginning.
	 */
	public void restartKruskalAlgorithm() {
		
		if(kruskal.getMainAnimation().getChildren().size()>0) {
			kruskal.getMainAnimation().getChildren().clear();
		}

			
		if(gpc.getSelectedTabName().equals("Undirected Weighted Graph")) {
			
    		validateAndPlayKruskal();
    		kruskal.stopMainAnimation("Undirected Weighted");
			
		}

		kruskal.playMainAnimation();
		
		gpc.getPlayButton().setText("Pause");
		
		for(Tab tab : gpc.getTabs().getTabs()) {
			tab.setDisable(true);
		}
		
	}
	
	/**
	 * This method is called when the user presses the Skip to end button while kruskal's algorithm animation is playing. 
	 * This method will skip the entire animation to the end showing the final state of the graph animation.
	 */
	public void skipKruskalAlgorithmToEnd() {
		kruskal.getMainAnimation().jumpTo("end");
		for(Tab tab : gpc.getTabs().getTabs()) {
			tab.setDisable(false);
		}
	}
	
	/**
	 * This is a helper method and is called in the method playPrimsAlgorithm(). This method calls the method from the PrimsAlgorithm object
	 * and supplies the correct parameters depending what type of graph the user wants the animation to be played on and the type of data the vertices
	 * hold in the graph.
	 * @param primsStartingVertex
	 */
	private void validateAndPlayPrims(String primsStartingVertex) {
		
		if(gpc.getSelectedTabName().equals("Undirected Weighted Graph")) {
			
			if(gpc.getSelectedDataChoiceUndirectedWeightedGraph().equals("Integer")) {
				
				prims.performPrimsAlgorithmUndirectedWeighted(gpc.getDataModel().getUndirectedWeightedInt(),Integer.parseInt(primsStartingVertex));
				
			}else if(gpc.getSelectedDataChoiceUndirectedWeightedGraph().equals("Double")) {
				
				prims.performPrimsAlgorithmUndirectedWeighted(gpc.getDataModel().getUndirectedWeightedDouble(),Double.parseDouble(primsStartingVertex));
				
			}else if(gpc.getSelectedDataChoiceUndirectedWeightedGraph().equals("String")) {
				
				prims.performPrimsAlgorithmUndirectedWeighted(gpc.getDataModel().getUndirectedWeightedString(),primsStartingVertex);
				
			}
			
		}
			
		
	}
	
	/**
	 * This method is called when the user has chosen to play prim's algorithm animation and presses the play button. If the prim's algorithm 
	 * animations is playing and the user presses the play button then the animation pauses.
	 */
	public void playPrimsAlgorithm() {
		
			if(prims.getMainAnimation().getStatus() == Status.STOPPED) {
				
				TextInputDialog dialogNonWeightedEdge = new TextInputDialog();
		        dialogNonWeightedEdge.setTitle("Starting vertex");
		        dialogNonWeightedEdge.setHeaderText("Choose starting vertex");
		        dialogNonWeightedEdge.setContentText("Please enter the vertex you would like to start the Prim's algorithm from.");
		        ButtonType cancelButton = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
		    	ButtonType okButton = new ButtonType("Ok", ButtonData.OK_DONE);
		    	dialogNonWeightedEdge.getDialogPane().getButtonTypes().setAll(okButton,cancelButton);
	        	dialogNonWeightedEdge.getDialogPane().getStylesheets().add("/application/global.css");
		    	Optional<String> result = dialogNonWeightedEdge.showAndWait();
				
				if(result.isPresent() && gpc.isInputValidStartingVertex(result.get())) {
					primsStartingVertex = result.get();
				
					if(gpc.getSelectedTabName().equals("Undirected Weighted Graph")) {
		            		
		            		validateAndPlayPrims(primsStartingVertex);
		        			
		        		}
		        		
		        		
						prims.playMainAnimation();
						gpc.getPlayButton().setText("Pause");
						
						for(Tab tab : gpc.getTabs().getTabs()) {
							tab.setDisable(true);
						}
				}
	        		
	    	}else if(prims.getMainAnimation().getStatus() == Status.RUNNING){
			
	    		prims.pauseMainAnimation();
	    		gpc.getPlayButton().setText("Play");
				
			}else if(prims.getMainAnimation().getStatus() == Status.PAUSED) {
				
				prims.playMainAnimation();
				gpc.getPlayButton().setText("Pause");
				
			}
    	}
	
	/**
	 * This method is called when the user presses the start again button while prim's algorithm animation is playing. This method will
	 * start the animation from the beginning.
	 */
	public void restartPrimsAlgorithm() {
		
		if(prims.getMainAnimation().getChildren().size()>0) {
			prims.getMainAnimation().getChildren().clear();
		}

			
		if(gpc.getSelectedTabName().equals("Undirected Weighted Graph")) {
			
    		validateAndPlayPrims(primsStartingVertex);
    		prims.stopMainAnimation("Undirected Weighted");
			
		}

		prims.playMainAnimation();
		
		gpc.getPlayButton().setText("Pause");
		
		for(Tab tab : gpc.getTabs().getTabs()) {
			tab.setDisable(true);
		}
		
	}
	
	/**
	 * This method is called when the user presses the Skip to end button while prim's algorithm animation is playing. 
	 * This method will skip the entire animation to the end showing the final state of the graph animation.
	 */
	public void skipPrimsAlgorithmToEnd() {
		prims.getMainAnimation().jumpTo("end");
		for(Tab tab : gpc.getTabs().getTabs()) {
			tab.setDisable(false);
		}
	}
	
	/**
	 * This method is called when the user has chosen to play the vertex cover algorithm animation and presses the play button. If the vertex cover 
	 * algorithm animations is playing and the user presses the play button then the animation pauses.
	 */
	public void playVertexCover() {
		
		if(vertexCover.getMainAnimation().getStatus() == Status.STOPPED) {
        		
    		if(gpc.getSelectedTabName().equals("Undirected Non-Weighted Graph")) {
        		
        		if(!validateAndPlayVertexCover()) {
        			Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("No Vertex Cover");
                    alert.setHeaderText("The Graph does not have a vertex cover");
                    alert.setContentText("The graph does not have a vertex cover because the graph is disconnected.");
                	alert.getDialogPane().getStylesheets().add("/application/global.css");
                	alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                    alert.showAndWait();
            		
        		}else{
        			
        			vertexCover.playMainAnimation();
    				gpc.getPlayButton().setText("Pause");
    				
    				for(Tab tab : gpc.getTabs().getTabs()) {
    					tab.setDisable(true);
    				}
        			
        		}
    			
    		}else if(gpc.getSelectedTabName().equals("Undirected Weighted Graph")) {
    			
    			if(!validateAndPlayVertexCover()) {
    				
    				Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("No Vertex Cover");
                    alert.setHeaderText("The Graph does not have a vertex cover");
                    alert.setContentText("The graph does not have a vertex cover because the graph is disconnected.");
                	alert.getDialogPane().getStylesheets().add("/application/global.css");
                	alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                    alert.showAndWait();
    				
    			}else {
    				
    				vertexCover.playMainAnimation();
    				gpc.getPlayButton().setText("Pause");
    				
    				for(Tab tab : gpc.getTabs().getTabs()) {
    					tab.setDisable(true);
    				}
    				
    			}
    			
    		}

		}else if(vertexCover.getMainAnimation().getStatus() == Status.RUNNING){
			
			vertexCover.pauseMainAnimation();
			gpc.getPlayButton().setText("Play");
			
		}else if(vertexCover.getMainAnimation().getStatus() == Status.PAUSED) {
			
			vertexCover.playMainAnimation();
			gpc.getPlayButton().setText("Pause");
			
		}
		
	}
	
	/**
	 * This method is called when the user presses the start again button while the vertex cover algorithm animation is playing. This method will
	 * start the animation from the beginning.
	 */
	public void restartVertexCover() {
		
		if(vertexCover.getMainAnimation().getChildren().size()>0) {
			vertexCover.getMainAnimation().getChildren().clear();
		}
		
		if(gpc.getSelectedTabName().equals("Undirected Non-Weighted Graph")) {
    		
    		validateAndPlayVertexCover();
    		vertexCover.stopMainAnimation("Undirected Non Weighted");
			
		}else if(gpc.getSelectedTabName().equals("Undirected Weighted Graph")) {
			
    		validateAndPlayVertexCover();
    		vertexCover.stopMainAnimation("Undirected Weighted");
			
		}
		
		vertexCover.playMainAnimation();
		
		gpc.getPlayButton().setText("Pause");
		
		for(Tab tab : gpc.getTabs().getTabs()) {
			tab.setDisable(true);
		}
		
	}
	
	/**
	 * This method is called when the user presses the Skip to end button while the vertex cover algorithm animation is playing. 
	 * This method will skip the entire animation to the end showing the final state of the graph animation.
	 */
	public void skipVertexCoverToEnd() {
		vertexCover.getMainAnimation().jumpTo("end");
		for(Tab tab : gpc.getTabs().getTabs()) {
			tab.setDisable(false);
		}
	}
	
	/**
	 * This is a helper method and is called in the method playVertexCover(). This method calls the method from the VertexCover object
	 * and supplies the correct parameters depending what type of graph the user wants the animation to be played on and the type of data the vertices
	 * hold in the graph.
	 */
	private boolean validateAndPlayVertexCover() {
		
		if(gpc.getSelectedTabName().equals("Undirected Non-Weighted Graph")) {
			if(gpc.getSelectedDataChoiceUndirectedNonWeightedGraph().equals("Integer")) {
				
				return vertexCover.performVertexCoverUndirectedNonWeighted(gpc.getDataModel().getUndirectedNonWeightedInt());
				
			}else if(gpc.getSelectedDataChoiceUndirectedNonWeightedGraph().equals("Double")) {
				
				return vertexCover.performVertexCoverUndirectedNonWeighted(gpc.getDataModel().getUndirectedNonWeightedDouble());
				
			}else if(gpc.getSelectedDataChoiceUndirectedNonWeightedGraph().equals("String")) {
				
				return vertexCover.performVertexCoverUndirectedNonWeighted(gpc.getDataModel().getUndirectedNonWeightedString());
				
			}
		}
		else if(gpc.getSelectedTabName().equals("Undirected Weighted Graph")) {
			
			if(gpc.getSelectedDataChoiceUndirectedWeightedGraph().equals("Integer")) {
				
				return vertexCover.performVertexCoverUndirectedWeighted(gpc.getDataModel().getUndirectedWeightedInt());
				
			}else if(gpc.getSelectedDataChoiceUndirectedWeightedGraph().equals("Double")) {
				
				return vertexCover.performVertexCoverUndirectedWeighted(gpc.getDataModel().getUndirectedWeightedDouble());
				
			}else if(gpc.getSelectedDataChoiceUndirectedWeightedGraph().equals("String")) {
				
				return vertexCover.performVertexCoverUndirectedWeighted(gpc.getDataModel().getUndirectedWeightedString());
				
			}
			
		}
		
		return false;
		
	}
	
	/**
	 * This method is called when the user has chosen to play the kosaraju algorithm animation and presses the play button. If the kosaraju algorithm
	 * algorithm animations is playing and the user presses the play button then the animation pauses.
	 */
	public void playKosarajuAlgorithm() {
		
		if(kosaraju.getMainAnimation().getStatus() == Status.STOPPED) {
			
        	if(gpc.getSelectedTabName().equals("Directed Non-Weighted Graph")) {
    			
        		
        		validateAndPlayKosarajuAlgorithm();
    			
    		}else if(gpc.getSelectedTabName().equals("Directed Weighted Graph")) {
        		
        		validateAndPlayKosarajuAlgorithm();
    			
    		}
    		
        	kosaraju.playMainAnimation();
			gpc.getPlayButton().setText("Pause");
			
			for(Tab tab : gpc.getTabs().getTabs()) {
				tab.setDisable(true);
			}
        		
        	
			
		}else if(kosaraju.getMainAnimation().getStatus() == Status.RUNNING){
			
			kosaraju.pauseMainAnimation();
			gpc.getPlayButton().setText("Play");
			
		}else if(kosaraju.getMainAnimation().getStatus() == Status.PAUSED) {
			
			kosaraju.playMainAnimation();
			gpc.getPlayButton().setText("Pause");
			
		}
		
	}
	
	/**
	 * This method is called when the user presses the start again button while kosarju's algorithm animation is playing. This method will
	 * start the animation from the beginning.
	 */
	public void restartKosarajuAlgorithm() {
		
		if(kosaraju.getMainAnimation().getChildren().size()>0) {
			kosaraju.getMainAnimation().getChildren().clear();
		}
		
		if(gpc.getSelectedTabName().equals("Directed Non-Weighted Graph")) {
    		
    		validateAndPlayKosarajuAlgorithm();
    		kosaraju.stopMainAnimation("Directed Non Weighted");
			
		}else if(gpc.getSelectedTabName().equals("Directed Weighted Graph")) {
    		
			validateAndPlayKosarajuAlgorithm();
    		kosaraju.stopMainAnimation("Directed Weighted");
			
		}
		
		kosaraju.playMainAnimation();
		
		gpc.getPlayButton().setText("Pause");
		
		for(Tab tab : gpc.getTabs().getTabs()) {
			tab.setDisable(true);
		}
		
	}
	
	/**
	 * This method is called when the user presses the Skip to end button while the vertex cover algorithm animation is playing. 
	 * This method will skip the entire animation to the end showing the final state of the graph animation.
	 */
	public void skipKosarajuAlgorithmToEnd() {
		kosaraju.getMainAnimation().jumpTo("end");
		for(Tab tab : gpc.getTabs().getTabs()) {
			tab.setDisable(false);
		}
	}
	
	/**
	 * This is a helper method and is called in the method playKosarujuAlgorithm(). This method calls the method from the KosarujuAlgorithm object
	 * and supplies the correct parameters depending what type of graph the user wants the animation to be played on and the type of data the vertices
	 * hold in the graph.
	 * @param primsStartingVertex
	 */
	private void validateAndPlayKosarajuAlgorithm() {
		
		if(gpc.getSelectedTabName().equals("Directed Non-Weighted Graph")) {
			
			if(gpc.getSelectedDataChoiceDirectedNonWeightedGraph().equals("Integer")) {
				
				kosaraju.performKosarajuAlgorithmDirectedNonWeighted(gpc.getDataModel().getDirectedNonWeightedInt());
				
			}else if(gpc.getSelectedDataChoiceDirectedNonWeightedGraph().equals("Double")) {
				
				kosaraju.performKosarajuAlgorithmDirectedNonWeighted(gpc.getDataModel().getDirectedNonWeightedDouble());
				
			}else if(gpc.getSelectedDataChoiceDirectedNonWeightedGraph().equals("String")) {
				
				kosaraju.performKosarajuAlgorithmDirectedNonWeighted(gpc.getDataModel().getDirectedNonWeightedString());
				
			}
			
		}
		else if(gpc.getSelectedTabName().equals("Directed Weighted Graph")) {
			
			if(gpc.getSelectedDataChoiceDirectedWeightedGraph().equals("Integer")) {
				
				kosaraju.performKosarajuAlgorithmDirectedWeighted(gpc.getDataModel().getDirectedWeightedInt());
				
			}else if(gpc.getSelectedDataChoiceDirectedWeightedGraph().equals("Double")) {
				
				kosaraju.performKosarajuAlgorithmDirectedWeighted(gpc.getDataModel().getDirectedWeightedDouble());
				
			}else if(gpc.getSelectedDataChoiceDirectedWeightedGraph().equals("String")) {
				
				kosaraju.performKosarajuAlgorithmDirectedWeighted(gpc.getDataModel().getDirectedWeightedString());
				
			}
			
		}
		
	}
	
	/**
	 * This method is called when the user has chosen to play the algorithm they have implemented in the MyAlgorithm class and presses 
	 * the play button. If the algorithm animations is playing and the user presses the play button then the animation pauses.
	 */
	public void playMyAlgorithm() {
		
		if(myAlgorithm.getMainAnimation().getStatus() == Status.STOPPED) {

			TextInputDialog dialogNonWeightedEdge = new TextInputDialog();
	        dialogNonWeightedEdge.setTitle("Starting vertex");
	        dialogNonWeightedEdge.setHeaderText("Choose starting vertex");
	        dialogNonWeightedEdge.setContentText("Please enter the vertex you would like to start the animation from.\n"
	        		+ "If the algorithm you implemented does not require a starting vertex then just input any vertex that exists in your graph.");
	        ButtonType cancelButton = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
        	ButtonType okButton = new ButtonType("Ok", ButtonData.OK_DONE);
        	dialogNonWeightedEdge.getDialogPane().getButtonTypes().setAll(okButton,cancelButton);
        	dialogNonWeightedEdge.getDialogPane().getStylesheets().add("/application/global.css");
        	Optional<String> result = dialogNonWeightedEdge.showAndWait();
			
        	if(result.isPresent() && gpc.isInputValidStartingVertex(result.get())) {
        		
        		if(gpc.getSelectedTabName().equals("Undirected Non-Weighted Graph")) {
        			
        			myAlgorithmStartVertexUndirectedNonWeighted = result.get();
            		
            		validateAndPlayMyAlgorithm(myAlgorithmStartVertexUndirectedNonWeighted);
        			
        		}else if(gpc.getSelectedTabName().equals("Undirected Weighted Graph")) {
        			
        			myAlgorithmStartVertexUndirectedWeighted = result.get();
            		
            		validateAndPlayMyAlgorithm(myAlgorithmStartVertexUndirectedWeighted);
        			
        		}else if(gpc.getSelectedTabName().equals("Directed Non-Weighted Graph")) {
        			
        			myAlgorithmStartVertexDirectedNonWeighted = result.get();
            		
            		validateAndPlayMyAlgorithm(myAlgorithmStartVertexDirectedNonWeighted);
        			
        		}else if(gpc.getSelectedTabName().equals("Directed Weighted Graph")) {
        			
        			myAlgorithmStartVertexDirectedWeighted = result.get();
            		
            		validateAndPlayMyAlgorithm(myAlgorithmStartVertexDirectedWeighted);
        			
        		}
        		
        		myAlgorithm.playMainAnimation();
				gpc.getPlayButton().setText("Pause");
				
				for(Tab tab : gpc.getTabs().getTabs()) {
					tab.setDisable(true);
				}
        		
        	}
			
		}else if(myAlgorithm.getMainAnimation().getStatus() == Status.RUNNING){
			
			myAlgorithm.pauseMainAnimation();
			gpc.getPlayButton().setText("Play");
			
		}else if(myAlgorithm.getMainAnimation().getStatus() == Status.PAUSED) {
			
			myAlgorithm.playMainAnimation();
			gpc.getPlayButton().setText("Pause");
			
		}
		
	}
	
	/**
	 * This method is called when the user presses the start again button while the algorithm they implemented animation is playing. This method will
	 * start the animation from the beginning.
	 */
	public void restartMyAlgorithm() {
		
		if(myAlgorithm.getMainAnimation().getChildren().size()>0) {
			myAlgorithm.getMainAnimation().getChildren().clear();
		}
		
		if(gpc.getSelectedTabName().equals("Undirected Non-Weighted Graph")) {
    		
    		validateAndPlayMyAlgorithm(myAlgorithmStartVertexUndirectedNonWeighted);
    		myAlgorithm.stopMainAnimation("Undirected Non Weighted");
			
		}else if(gpc.getSelectedTabName().equals("Undirected Weighted Graph")) {
			
    		validateAndPlayMyAlgorithm(myAlgorithmStartVertexUndirectedWeighted);
    		myAlgorithm.stopMainAnimation("Undirected Weighted");
			
		}else if(gpc.getSelectedTabName().equals("Directed Non-Weighted Graph")) {
    		
    		validateAndPlayMyAlgorithm(myAlgorithmStartVertexDirectedNonWeighted);
    		myAlgorithm.stopMainAnimation("Directed Non Weighted");
			
		}else if(gpc.getSelectedTabName().equals("Directed Weighted Graph")) {
    		
    		validateAndPlayMyAlgorithm(myAlgorithmStartVertexDirectedWeighted);
    		myAlgorithm.stopMainAnimation("Directed Weighted");
			
		}
		
		myAlgorithm.playMainAnimation();
		
		gpc.getPlayButton().setText("Pause");
		
		for(Tab tab : gpc.getTabs().getTabs()) {
			tab.setDisable(true);
		}
		
	}
	
	/**
	 * This method is called when the user presses the Skip to end button while the my algorithm animation is playing. 
	 * This method will skip the entire animation to the end showing the final state of the graph animation.
	 */
	public void skipMyAlgorithmToEnd() {
		myAlgorithm.getMainAnimation().jumpTo("end");
		for(Tab tab : gpc.getTabs().getTabs()) {
			tab.setDisable(false);
		}
	}
	
	/**
	 * This is a helper method and is called in the method playMyAlgorithm(). This method calls the method from the MyAlgorithm object
	 * and supplies the correct parameters depending what type of graph the user wants the animation to be played on and the type of data the vertices
	 * hold in the graph.
	 * @param myAlgorithmStartVertex
	 */
	private void validateAndPlayMyAlgorithm(String myAlgorithmStartVertex) {
		
		if(gpc.getSelectedTabName().equals("Undirected Non-Weighted Graph")) {
			if(gpc.getSelectedDataChoiceUndirectedNonWeightedGraph().equals("Integer")) {
				
				myAlgorithm.performMyAlgorithmUndirectedNonWeighted(gpc.getDataModel().getUndirectedNonWeightedInt(), Integer.parseInt(myAlgorithmStartVertex));
				
			}else if(gpc.getSelectedDataChoiceUndirectedNonWeightedGraph().equals("Double")) {
				
				myAlgorithm.performMyAlgorithmUndirectedNonWeighted(gpc.getDataModel().getUndirectedNonWeightedDouble(), Double.parseDouble(myAlgorithmStartVertex));
				
			}else if(gpc.getSelectedDataChoiceUndirectedNonWeightedGraph().equals("String")) {
				
				myAlgorithm.performMyAlgorithmUndirectedNonWeighted(gpc.getDataModel().getUndirectedNonWeightedString(), myAlgorithmStartVertex);
				
			}
		}else if(gpc.getSelectedTabName().equals("Undirected Weighted Graph")) {
			
			if(gpc.getSelectedDataChoiceUndirectedWeightedGraph().equals("Integer")) {
				
				myAlgorithm.performMyAlgorithmUndirectedWeighted(gpc.getDataModel().getUndirectedWeightedInt(), Integer.parseInt(myAlgorithmStartVertex));
				
			}else if(gpc.getSelectedDataChoiceUndirectedWeightedGraph().equals("Double")) {
				
				myAlgorithm.performMyAlgorithmUndirectedWeighted(gpc.getDataModel().getUndirectedWeightedDouble(), Double.parseDouble(myAlgorithmStartVertex));
				
			}else if(gpc.getSelectedDataChoiceUndirectedWeightedGraph().equals("String")) {
				
				myAlgorithm.performMyAlgorithmUndirectedWeighted(gpc.getDataModel().getUndirectedWeightedString(), myAlgorithmStartVertex);
				
			}
			
		}else if(gpc.getSelectedTabName().equals("Directed Non-Weighted Graph")) {
			
			if(gpc.getSelectedDataChoiceDirectedNonWeightedGraph().equals("Integer")) {
				
				myAlgorithm.performMyAlgorithmDirectedNonWeighted(gpc.getDataModel().getDirectedNonWeightedInt(), Integer.parseInt(myAlgorithmStartVertex));
				
			}else if(gpc.getSelectedDataChoiceDirectedNonWeightedGraph().equals("Double")) {
				
				myAlgorithm.performMyAlgorithmDirectedNonWeighted(gpc.getDataModel().getDirectedNonWeightedDouble(), Double.parseDouble(myAlgorithmStartVertex));
				
			}else if(gpc.getSelectedDataChoiceDirectedNonWeightedGraph().equals("String")) {
				
				myAlgorithm.performMyAlgorithmDirectedNonWeighted(gpc.getDataModel().getDirectedNonWeightedString(), myAlgorithmStartVertex);
				
			}
			
		}else if(gpc.getSelectedTabName().equals("Directed Weighted Graph")) {
			
			if(gpc.getSelectedDataChoiceDirectedWeightedGraph().equals("Integer")) {
				
				myAlgorithm.performMyAlgorithmDirectedWeighted(gpc.getDataModel().getDirectedWeightedInt(), Integer.parseInt(myAlgorithmStartVertex));
				
			}else if(gpc.getSelectedDataChoiceDirectedWeightedGraph().equals("Double")) {
				
				myAlgorithm.performMyAlgorithmDirectedWeighted(gpc.getDataModel().getDirectedWeightedDouble(), Double.parseDouble(myAlgorithmStartVertex));
				
			}else if(gpc.getSelectedDataChoiceDirectedWeightedGraph().equals("String")) {
				
				myAlgorithm.performMyAlgorithmDirectedWeighted(gpc.getDataModel().getDirectedWeightedString(), myAlgorithmStartVertex);
				
			}
			
		}
		
	}
	
	
	
}
