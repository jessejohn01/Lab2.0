
public class Vertex { // Class with constructor for vertexes. 
	public char label; // Names the vertex
	public boolean wasVisted;
	public Vertex(char lab){
		label = lab;
		wasVisted = false;
	}
	
	public char getLabel(){
		return label;
	}

}
