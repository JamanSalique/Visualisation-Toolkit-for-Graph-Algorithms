package application.view;

public class RandomGraphGenerator {
	
	private GraphPanelController gpc;
	
	public RandomGraphGenerator(GraphPanelController gpc) {
		
		this.gpc = gpc;
		
	}
	
	public void createRandomVertices(int numberOfVertices) {
		
		if(gpc.getSelectedTabName().equals("Undirected Non-Weighted Graph")) {
			
			if(gpc.getSelectedDataChoiceUndirectedNonWeightedGraph().equals("Integer")) {
				
			}else if(gpc.getSelectedDataChoiceUndirectedNonWeightedGraph().equals("Double")) {
				
			}else if(gpc.getSelectedDataChoiceUndirectedNonWeightedGraph().equals("String")) {
				
			}
			
		}else if(gpc.getSelectedTabName().equals("Undirected Weighted Graph")) {
			
			if(gpc.getSelectedDataChoiceUndirectedWeightedGraph().equals("Integer")) {
				
			}else if(gpc.getSelectedDataChoiceUndirectedWeightedGraph().equals("Double")) {
				
			}else if(gpc.getSelectedDataChoiceUndirectedWeightedGraph().equals("String")) {
				
			}
			
		}else if(gpc.getSelectedTabName().equals("Directed Non-Weighted Graph")) {
			
			if(gpc.getSelectedDataChoiceDirectedNonWeightedGraph().equals("Integer")) {
				
			}else if(gpc.getSelectedDataChoiceDirectedNonWeightedGraph().equals("Double")) {
				
			}else if(gpc.getSelectedDataChoiceDirectedNonWeightedGraph().equals("String")) {
				
			}
			
		}else if(gpc.getSelectedTabName().equals("Directed Weighted Graph")) {
			
			if(gpc.getSelectedDataChoiceDirectedWeightedGraph().equals("Integer")) {
				
			}else if(gpc.getSelectedDataChoiceDirectedWeightedGraph().equals("Double")) {
				
			}else if(gpc.getSelectedDataChoiceDirectedWeightedGraph().equals("String")) {
				
			}
			
		}
		
		
	}

}
