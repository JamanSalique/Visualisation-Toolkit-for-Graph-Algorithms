package application.view;

import java.util.Optional;

import javafx.animation.Animation.Status;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Tab;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ButtonBar.ButtonData;

public class AlgorithmAnimations {

	private GraphPanelController gpc;
	private BreadthFirstSearch bfs;
	private String bfsStartVertexUndirectedNonWeighted, bfsStartVertexUndirectedWeighted, 
					bfsStartVertexDirectedNonWeighted, bfsStartVertexDirectedWeighted ;
	
	
	public AlgorithmAnimations(GraphPanelController gpc) {
		this.gpc = gpc;
		bfs = new BreadthFirstSearch(gpc);
	}
	
	public void playBreadthFirstSearch() {
		
		if(bfs.getMainAnimation().getStatus() == Status.STOPPED) {

			TextInputDialog dialogNonWeightedEdge = new TextInputDialog();
	        dialogNonWeightedEdge.setTitle("Starting vertex");
	        dialogNonWeightedEdge.setHeaderText("Choose starting vertex");
	        dialogNonWeightedEdge.setContentText("Please enter the vertex you would like to start the breadth first search from.");
	        ButtonType cancelButton = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
        	ButtonType okButton = new ButtonType("Ok", ButtonData.OK_DONE);
        	dialogNonWeightedEdge.getDialogPane().getButtonTypes().setAll(okButton,cancelButton);
        	Optional<String> result = dialogNonWeightedEdge.showAndWait();
			
        	if(result.isPresent() && gpc.isInputValidBfsStartingVertex(result.get())) {
        		
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
	
	public void restartBreadthFirstSearch() {
		
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
	
	public void skipBreadthFirstSearchToEnd() {
		bfs.getMainAnimation().jumpTo("end");
	}
	
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
	
	
}
