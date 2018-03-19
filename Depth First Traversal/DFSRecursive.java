import java.util.LinkedList;
import java.util.Scanner;

/**
 * Enum used to define the 3 possible colors that a node can have in the graph
 */
enum Colors {
    BLACK, GRAY, WHITE
}

/**
 * In this program we dynamically generate a graph by taking inputs from the user at Runtime
 * and then we traverse the graph using the DFS traversal technique on the basis of colors given
 * to the set of nodes
 * <p>
 * Created by ©Ameya on 3/4/2018 at 1:15 PM.
 */
public class DFSRecursive {

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
            Vertex node = nodes[i];
            int edges = scanner.nextInt();

            for (int j = 0; j < edges; j++) {
                System.out.println("Enter edge " + (j + 1) + " for vertex " + i + " (Max can be " + (vertices - 1 + ")"));
                int nextVertex = scanner.nextInt();
                node.getAdjList().add(nodes[nextVertex]);
            }
        }
        //Displaying the generated graph
        for (int i = 0; i < vertices; i++) {
            Vertex node = nodes[i];
            System.out.println("Adjacency List for " + i + " node = ");
            for (int j = 0; j < node.getAdjList().size(); j++) {
                System.out.print(" " + node.getAdjList().get(j).getId());
            }
            System.out.println();
        }
        System.out.println("DFS traversal for given graph is = ");
        performDFS(nodes);
    }

    /**
     * We traverse the graph using Depth First Search Traversal technique
     *
     * @param nodes to be traversed
     */
    private static void performDFS(Vertex[] nodes) {
        for (Vertex node : nodes) {
            if (node.getColor().equals(Colors.WHITE)) {
                DFS_visit(node);
            }
        }
    }

    /**
     * Used to mark that the given node has been visited and now it is time to visit its descendants
     *
     * @param node that is to be visited
     */
    private static void DFS_visit(Vertex node) {
        node.setColor(Colors.GRAY);
        System.out.println(node.getId());
        for (Vertex node1 : node.getAdjList()) {
            if (node1.getColor().equals(Colors.WHITE)) {
                DFS_visit(node1);
            }
        }
        node.setColor(Colors.BLACK);
    }
}

/**
 * Class used to define the structure of a Vertex
 */
class Node {
    private int id;
    private Colors color;
    private LinkedList<Vertex> adjList = new LinkedList<>();

    Node(int id) {
        this.id = id;
        color = Colors.WHITE;
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

    Colors getColor() {
        return color;
    }

    void setColor(Colors color) {
        this.color = color;
    }
}

