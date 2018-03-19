/**
 * This code performs the Topological sorting on the given graph
 * It is assumed that the input graph is Directed Acyclic Grapgh.
 *
 * Created by ©Ameya on 3/8/2018 at 11:07 PM.
 */


import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

/**
 * Enum used to define the 3 possible colors that a node can have in the graph
 */
enum VertexColors {
    BLACK, GRAY, WHITE
}
public class TopologicalSort {
    static LinkedList<Vertex> topologicalSortedList = new LinkedList<>();
    public static void main(String[] args) {
        System.out.println("Enter the number of vertices :- ");
        Scanner scanner = new Scanner(System.in);
        int vertices = scanner.nextInt();
        //Creating an array to store the given number of vertices
        Vertex[] nodes = new Vertex[vertices];
        //Initialization of nodes
        for (int i = 0; i < vertices; i++) {
            nodes[i] = new Vertex(i);
        }

        //Taking inputs from the user to generate grapgh at runtime
        for (int i = 0; i < vertices; i++) {
            System.out.println("Enter the number of edges for vertex " + i + ":- (Max value can be " + (vertices - 1) + ")");
            Vertex vertex = nodes[i];
            int edges = scanner.nextInt();

            for (int j = 0; j < edges; j++) {
                System.out.println("Enter edge " + (j + 1) + " for vertex " + i + " (Max can be " + (vertices - 1 + ")"));
                int nextVertex = scanner.nextInt();
                vertex.getAdjList().add(nodes[nextVertex]);
            }
        }
        //Displaying the generated graph
        for (int i = 0; i < vertices; i++) {
            Vertex vertex = nodes[i];
            System.out.println("Adjacency List for " + i + " vertex = ");
            for (int j = 0; j < vertex.getAdjList().size(); j++) {
                System.out.print(" " + vertex.getAdjList().get(j).getId());
            }
            System.out.println();
        }
        System.out.println("Topological sorting for given graph is = ");
        performDFS(nodes);

     ListIterator<Vertex> iterator = topologicalSortedList.listIterator();
     while (iterator.hasNext()){
         System.out.println(iterator.next().getId());
     }
    }
    /**
     * We traverse the graph using Depth First Search Traversal technique
     *
     * @param vertices to be traversed
     */
    private static void performDFS(Vertex[] vertices) {
        for (Vertex vertex : vertices) {
            if (vertex.getColor().equals(VertexColors.WHITE)) {
                topologicalSort(vertex);
            }
        }
    }

    /**
     * Sorts the given vertex topographically, such that each element on the right
     * of this vertex will have a topological value more than this vertex
     * @param vertex to be sorted topologically
     */
    private static void topologicalSort(Vertex vertex) {

        vertex.setColor(VertexColors.GRAY);
        for (Vertex currentVertex : vertex.getAdjList()) {
            if (currentVertex.getColor().equals(VertexColors.WHITE)) {
                topologicalSort(currentVertex);
            }
        }
        vertex.setColor(VertexColors.BLACK);
        topologicalSortedList.addFirst(vertex);

    }
}
/**
 * Class used to define the structure of a Vertex
 */
class Vertex {
    private int id;
    private VertexColors color;
    private LinkedList<Vertex> adjList = new LinkedList<>();

    Vertex(int id) {
        this.id = id;
        color = VertexColors.WHITE;
    }

    LinkedList<Vertex> getAdjList() {
        return adjList;
    }

    int getId() {
        return id;
    }

    void setId(int id) {
        this.id = id;
    }

    VertexColors getColor() {
        return color;
    }

    void setColor(VertexColors color) {
        this.color = color;
    }
}

