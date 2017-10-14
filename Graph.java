import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList;
public class Graph {


	String filePathLabels = "labels.txt"; // Allows for easy change of file name
	String adjacencyMatrixFilePath = "adjacency_matrix.txt"; // Allows for easy change of file name

	Vertex[] vertexList = new Vertex[labelSize(filePathLabels)]; // Creates a vertexList the size of the files input.
	int[][] adjacencyMatrix = new int[adjMatrixSizeX(adjacencyMatrixFilePath)][adjMatrixSizeY(adjacencyMatrixFilePath)]; // Create adj Matrix array with size from file
	Stack MST = new Stack();

	public Graph() {
		readLabels(filePathLabels);
		readAdjacencyMatrix(adjacencyMatrixFilePath);
	}

	public void displayVertex(int vertexIndex) { //Displays the vertex at the index passed in.
		for(int i = 0; i < adjacencyMatrix[0].length; i++)
		{
			System.out.print(vertexList[i].getLabel() + " ");
		}
		System.out.println();
	}

	private void readAdjacencyMatrix(String filePath) {

		int sizeX = adjMatrixSizeX(filePath); // Setting the array size to a variable.
		int sizeY = adjMatrixSizeY(filePath);
		String[] adjMStringArray = new String[sizeX]; // Since the scanner requires whitespace we have to use strings to seperate the input out.
		try {
			File file = new File(filePath);
			Scanner scanner = new Scanner(file);
			for (int i = 0; i < sizeX; i++) {
				adjMStringArray[i] = scanner.nextLine();
			}
			char[][] adjMCharArray = new char[sizeX][sizeY];
			for (int i = 0; i < sizeX; i++) {
				adjMCharArray[i] = adjMStringArray[i].toCharArray();
			}

			for (int i = 0; i < sizeX; i++) {
				for (int j = 0; j < sizeY; j++) {
					adjacencyMatrix[i][j] = Character.getNumericValue(adjMCharArray[i][j]);
				}
			}


		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}


	private int adjMatrixSizeX(String filePath) {
		int rowCount = 0; // Since we only care about the size im just going to count the rows from the scanner

		try {
			File file = new File(filePath);
			Scanner scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				rowCount++;
				scanner.nextLine();

			}


		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return rowCount;
	}

	private int adjMatrixSizeY(String filePath) {
		String line = null; // Variable used to count the line length.

		try {
			File file = new File(filePath);
			Scanner scanner = new Scanner(file);
			line = scanner.nextLine();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return line.length(); // returning the length of the line.

	}

	public void printMatrix(String filePath) {
		for (int i = 0; i < adjMatrixSizeX(filePath); i++) {
			for (int j = 0; j < adjMatrixSizeY(filePath); j++) {
				System.out.print(adjacencyMatrix[i][j] + " ");
			}
			System.out.println("");
		}
	}

	private int labelSize(String filePath) { //Function utilized to get the vertexList at the correct array size.
		String fullLabels = null;

		try {
			File file = new File(filePath);
			Scanner scanner = new Scanner(file);

			fullLabels = scanner.nextLine();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return fullLabels.length();

	}

	private void readLabels(String filePath) { // Reads the input file and fills the vertexList
		String fullLabels = null;

		try { // Avoiding a file not found exception
			File file = new File(filePath);
			Scanner scanner = new Scanner(file);

			fullLabels = scanner.nextLine(); //Scanner utilized

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		char[] labelCharArray = new char[fullLabels.length()]; //creates a char array size of the inputed string file.
		labelCharArray = fullLabels.toCharArray(); // fills the char array
		for (int i = 0; i < labelCharArray.length; i++) { // Loops that creates vertexes in the vertex list with labels from char array.
			Vertex v = new Vertex(labelCharArray[i]);

			vertexList[i] = v;
		}

	}

	public void depthFirstSearch() {
		Stack s = new Stack();
		Stack visitedOrder = new Stack();
		int j = 0;
		int label = 0;
		s.push(vertexList[j]);
		vertexList[j].wasVisted = true;
		System.out.print(vertexList[label].getLabel() + " ");
		while (!s.isEmpty()) {
			if (adjacencyMatrix[j][label] == 1 && vertexList[label].wasVisted == false) {
				vertexList[label].wasVisted = true;
				System.out.print(vertexList[label].getLabel() + " ");
				s.push(vertexList[label]);
				visitedOrder.push(j);
				j = label;
				label = 0;
			} else if (label == adjacencyMatrix[0].length - 1) {
				if (!visitedOrder.empty()) {
					j = (int) visitedOrder.peek();
					visitedOrder.pop();
				}

				s.pop();
			} else {
				label++;
			}
		}
		for (int i = 0; i < vertexList.length; i++) {
			vertexList[i].wasVisted = false;
		}

	}

	public void breadthFirstSearch()
	{
		Queue queue = new LinkedList();
		int i, element;

		vertexList[0].wasVisted= true;
		queue.add(0);

		while (!queue.isEmpty())
		{
			element = (int)queue.remove();
			i = 0;
			System.out.print(vertexList[element].getLabel() + " ");
			while (i < adjacencyMatrix[0].length)
			{
				if (adjacencyMatrix[element][i] == 1 && !vertexList[i].wasVisted)
				{
					queue.add(i);
					vertexList[i].wasVisted = true;
				}
				i++;
			}
		}
	}
}
