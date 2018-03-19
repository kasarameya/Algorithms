

import java.util.HashMap;
import java.util.LinkedList;

/**
 * This program lists all the possible connected components present in a graph.
 * It does so by assigning each connected component a number such that
 * all the connected components have the same number.
 * <p>
 * Created by ©Ameya on 3/16/2018 at 5:42 PM.
 */

/**
 *
 * Enum used to maintain the list of all possible colors that a
 * node can have
 */

enum Colors {
    BLACK, GRAY, WHITE
}


public class ConnectedComponents {
    static HashMap<Integer, Node> hm = new HashMap<>();

    public static void main(String[] args) {
        Node n0 = new Node(0);
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);

        hm.put(0, n0);
        hm.put(1, n1);
        hm.put(2, n2);
        hm.put(3, n3);
        hm.put(4, n4);

        addEdge(n0, n1);
        addEdge(n0, n2);
        addEdge(n3, n4);

        printGraph();

        performDFS();

        printConnectedComponents();
    }

    /**
     * Display the list of all the connected components present in the graph
     */

    private static void printConnectedComponents() {
        for (int i = 0; i < hm.size(); i++) {
            System.out.println("Vertex " + hm.get(i).getId() + " Connected component = " + hm.get(i).getConnectedComponentNumber());
        }
    }

    /**
     * Display the generated graph
     */
    private static void printGraph() {
        for (int i = 0; i < hm.size(); i++) {
            System.out.println("Adjacency List for " + i + " :- ");
            for (Node z : hm.get(i).getAdjList()) {
                System.out.print(" " + z.getId());
            }
            System.out.println(" ");
        }
    }

    /**
     * Adds an edge between edge n1 & n2
     * @param n1 source node
     * @param n2 destination node
     */
    private static void addEdge(Node n1, Node n2) {
        n1.getAdjList().add(n2);
    }



    /**
     * Perform the DFS on all the nodes by calling a helper function
     * and assigning a value for each call
     */

    private static void performDFS() {
        int count = 0;
        for (int i = 0; i < hm.size(); i++) {
            if (hm.get(i).getColor().equals(Colors.WHITE)) {
                count++;
                DFS_visit(hm.get(i), count);
            }
        }
    }

    /**
     * Used to mark that the given node has been visited and now it is time to visit its descendants
     *
     * @param node that is to be visited
     */
    private static void DFS_visit(Node node, int count) {
        node.setColor(Colors.GRAY);
        node.setConnectedComponentNumber(count);
        for (Node node1 : node.getAdjList()) {
            if (node1.getColor().equals(Colors.WHITE)) {
                DFS_visit(node1, count);
            }
        }
        node.setColor(Colors.BLACK);
    }
}

/**
 * Class used to define the structure of a Node
 */
class Node {
    private int id;
    private Colors color;
    private LinkedList<Node> adjList = new LinkedList<>();
    private int connectedComponentNumber;
    private int inDegree;

    public int getInDegree() {
        return inDegree;
    }

    public void setInDegree(int inDegree) {
        this.inDegree = inDegree;
    }

    int getConnectedComponentNumber() {
        return connectedComponentNumber;
    }

    void setConnectedComponentNumber(int connectedComponentNumber) {
        this.connectedComponentNumber = connectedComponentNumber;
    }

    Node(int id) {
        this.id = id;
        color = Colors.WHITE;
    }

    LinkedList<Node> getAdjList() {
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

