package application.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Vertex<T>{
	
	private ObjectProperty<T> element;
	private BooleanProperty visited;
	
	public Vertex() {
		element = null;
		visited = new SimpleBooleanProperty(false);
	}
	
	public Vertex(T element) {
		this.element = new SimpleObjectProperty<T>(element);
		this.visited = new SimpleBooleanProperty(false);
	}
	
	public Vertex(T element, boolean visited) {
		this.element = new SimpleObjectProperty<T>(element);
		this.visited = new SimpleBooleanProperty(visited);
	}
	
	public void setElement(T elem) {
		this.element.set(elem);
	}
	
	public T getElement() {
		return this.element.get();
	}
	
	public ObjectProperty<T> elementProperty(){
		return this.element;
	}
	
	public void setVisited(boolean b) {
		this.visited.set(b);
	}
	
	public boolean isVisited() {
		return this.visited.get();
	}
	
	public BooleanProperty visitedProperty(){
		return this.visited;
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
		
		if(v.getElement() instanceof String) {
			return v.getElement().equals(this.element.get());
		}else {
			return v.getElement() == this.element.get();
		}
		
		
	}
	
	@Override
	public String toString() {
		return "[" + element.get() + "]";
	}
	
}
