
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * This program checks if the given graph is a Bipartite graph or not.
 * I have used the coloring technique here, where one set consists of red color nodes
 * and the other set consits of blue color nodes.
 * If at any point the color of a node and its immediate child is same, then it means that
 * the graph is non-bipartite
 *
 * Created by ©Ameya on 3/18/2018 at 3:56 PM.
 */

/**
 * Enum used to maintain the list of all possible colors that a
 * node can have
 */
enum Color {
    WHITE, RED, BLUE,GRAY,BLACK;
}

public class BiPartiteGraph {
    public static void main(String[] args) {

        Nodes n1 = new Nodes(1);
        Nodes n2 = new Nodes(2);
        Nodes n3 = new Nodes(3);
        Nodes n4 = new Nodes(4);
        Nodes[] allNodes = {n1, n2, n3, n4};
        ArrayList<Edge> edgeArrayList = new ArrayList<>();
        Edge e1 = new Edge(n1, n3);
        Edge e2 = new Edge(n2, n4);

        edgeArrayList.add(e1);
        edgeArrayList.add(e2);

        generateGraph(edgeArrayList);
        printGraph(allNodes);

        if (!checkForBiPartiteGraph(allNodes)) {
            System.out.println("Bipartite graph is absent");
        } else {
            System.out.println("Bipartite graph is present");
            System.out.println("Group 1 :- ");
            for (int i = 0; i < allNodes.length; i++) {
                if (allNodes[i].getColor().equals(Color.RED)) {
                    System.out.print("\t" + allNodes[i].getId());
                }
            }
            System.out.println("\nGroup 2 :- ");
            for (int i = 0; i < allNodes.length; i++) {
                if (allNodes[i].getColor().equals(Color.BLUE)) {
                    System.out.print("\t" + allNodes[i].getId());
                }
            }
        }


    }

    /**
     * This method checks if the given graph is bipartite or not by calling a helper function
     * @param allNodes present in the graph
     * @return true if graph is bipartite
     */
    private static boolean checkForBiPartiteGraph(Nodes[] allNodes) {
        for (int i = 0; i < allNodes.length; i++) {
            if (allNodes[i].getColor().equals(Color.WHITE)) {
                if (!dfs_visit(allNodes[i], Color.RED))
                    return false;
            }
        }
        return true;
    }

    /**
     * This method assigns colors to each node and determines if it is bipartite or not using the colors
     * @param allNode which needs to be checked with its descendents
     * @param red the color to be assigned to this node
     * @return true if graph is bi-partite
     */
    private static boolean dfs_visit(Nodes allNode, Color red) {
        allNode.setColor(red);
        Iterator<Nodes> nodesIterator = allNode.getLinkedList().listIterator();
        while (nodesIterator.hasNext()) {
            Nodes node = nodesIterator.next();
            if (node.getColor().equals(Color.WHITE)) {
                if (allNode.getColor().equals(Color.RED)) {
                    dfs_visit(node, Color.BLUE);
                } else {
                    dfs_visit(node, Color.RED);
                }
            } else if (allNode.getColor().equals(node.getColor())) {
                return false;
            }

        }
        return true;
    }


    /**
     * Prints the generated graph by displaying the adjacency list for each node
     * @param allNodes that are present in the graph
     */
    private static void printGraph(Nodes[] allNodes) {
        for (Nodes n1 : allNodes) {
            System.out.print("Adjacency List for node " + n1.getId() + " :-");
            for (Nodes nodes : n1.getLinkedList()) {
                System.out.print(" \t" + nodes.getId());
            }
            System.out.println();
        }
    }


    /**
     * Generates the graph by using the arrayList of edges such that
     * for each vertex the edge adds a source and destination to its adjacency list
     * @param edgeArrayList list of edges that contain information edges
     */
    private static void generateGraph(ArrayList<Edge> edgeArrayList) {
        for (Edge edge : edgeArrayList) {
            Nodes sourceNode = edge.getSourceNode();
            Nodes destNode = edge.getDestNode();

            sourceNode.getLinkedList().add(destNode);
            destNode.getLinkedList().add(sourceNode);
        }
    }
}


/**
 * Nodes class that contains all the information that
 * a node in graph needs to have
 */
class Nodes {
    private int id;
    private Color color;
    private int discoveryTime;
    private int finishTime;
    private int low;

    public int getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(int finishTime) {
        this.finishTime = finishTime;
    }

    public int getLow() {
        return low;
    }

    public void setLow(int low) {
        this.low = low;
    }

    public Nodes getParentNode() {
        return parentNode;
    }

    public void setParentNode(Nodes parentNode) {
        this.parentNode = parentNode;
    }

    private Nodes parentNode;

    public int getDiscoveryTime() {
        return discoveryTime;
    }

    public void setDiscoveryTime(int discoveryTime) {
        this.discoveryTime = discoveryTime;
    }

    private LinkedList<Nodes> linkedList = new LinkedList<>();

    Nodes(int id) {
        this.id = id;
        color = Color.WHITE;
    }

    int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    Color getColor() {
        return color;
    }

    void setColor(Color color) {
        this.color = color;
    }

    LinkedList<Nodes> getLinkedList() {
        return linkedList;
    }

    void setLinkedList(LinkedList<Nodes> linkedList) {
        this.linkedList = linkedList;
    }
}

/**
 * Edge class that contains information about the source and destination node
 * of this edge
 */
class Edge {
    private Nodes sourceNode;
    private Nodes DestNode;

    Edge(Nodes sourceNode, Nodes destNode) {
        this.sourceNode = sourceNode;
        DestNode = destNode;
    }

    Nodes getSourceNode() {
        return sourceNode;
    }

    private void setSourceNode(Nodes sourceNode) {
        this.sourceNode = sourceNode;
    }

    Nodes getDestNode() {
        return DestNode;
    }

    void setDestNode(Nodes destNode) {
        DestNode = destNode;
    }
}


