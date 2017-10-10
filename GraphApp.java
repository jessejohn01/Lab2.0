
public class GraphApp {
	public static void main(String[] args){
		
		Graph theGraph = new Graph();
		theGraph.displayVertex(0);
		theGraph.printMatrix("adjacency_matrix.txt");
		System.out.println("Depth First Search Visits: ");
		theGraph.depthFirstSearch();
		System.out.println("");
		System.out.println("Breadth First Search Visits: ");
		theGraph.breadthFirstSearch();
		System.out.println("Minimum Spanning Tree: ");
		theGraph.minSpanTree();
		System.out.println("");
	}
}
