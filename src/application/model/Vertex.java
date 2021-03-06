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
	
	public void setElement(T elem) {
		element = elem;
	}
	
	public T getElement() {
		return element;
	}
	
	public void setVisited(boolean b) {
		visited = b;
	}
	
	public boolean isVisited() {
		return visited;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o == this) {
			return true;
		}
		
		if(!(o instanceof Vertex<?>)) {
			return false;
		}
		
		Vertex<?> v=  (Vertex<?>) o;
		
		return v.getElement() == this.element;
	}
	
	@Override
	public String toString() {
		return "Vertex containing element: " + element;
	}
	
}
