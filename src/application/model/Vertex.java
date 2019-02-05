package application.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;

/**
 * This class represents a vertex of a graph.
 * @author jamansalique
 *
 * @param <T>
 */
public class Vertex<T>{
	
	private ObjectProperty<T> element;
	private BooleanProperty visited;
	
	/**
	 * Constructor with no parameters initialises element to null and visited to false.
	 */
	public Vertex() {
		element = null;
		visited = new SimpleBooleanProperty(false);
	}
	
	/**
	 * Constructor with element parameter initialises the element to the value of the parameter and initialises visited to false.
	 */
	public Vertex(T element) {
		this.element = new SimpleObjectProperty<T>(element);
		this.visited = new SimpleBooleanProperty(false);
	}
	
	/**
	 * Constructor with element and visited parameters initialises the element to the value of the parameter and initialises visited to value of
	 * visited parameter.
	 */
	public Vertex(T element, boolean visited) {
		this.element = new SimpleObjectProperty<T>(element);
		this.visited = new SimpleBooleanProperty(visited);
	}
	
	//GETTERS AND SETTERS...
	
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
	
	
	/**
	 * Override equals method so we check if two vertices are equal by comparing the elements the vertices hold.
	 */
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
	
	/**
	 * Override toString method so if instances of Vertex objects are printed we print the element the vertex holds.
	 */
	@Override
	public String toString() {
		return element.get().toString();
	}
	
}
