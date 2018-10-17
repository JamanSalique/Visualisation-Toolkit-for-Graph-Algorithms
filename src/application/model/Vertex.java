package application.model;

public class Vertex<T> {
	
	private T element;
	private boolean visited;
	
	public Vertex() {
		element = null;
		visited = false;
	}
	
	public Vertex(T element) {
		this.element = element;
		visited = false;
	}
	
	public Vertex(T element, boolean visited) {
		this.element = element;
		this.visited = visited;
	}
	
	
}
