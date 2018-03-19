import java.util.ArrayList;
import java.util.Iterator;

/**
 * This program outputs all the types of edges that a graph has
 * by using the color of nodes and discovery time.
 * The discovery time is useful in deciding whether an edge is a
 * forward edge or cross edge.
 *
 * Created by ©Ameya on 3/18/2018 at 8:16 PM.
 */
public class TypeOfEdges {
   //Variable used to assign discovery time to all the nodes
    private static int discoveryTime = 0;
    public static void main(String[] args) {

        Nodes n1 = new Nodes(1);
        Nodes n2 = new Nodes(2);
        Nodes n3 = new Nodes(3);
        Nodes n4 = new Nodes(4);
        Nodes n5 = new Nodes(5);
        Nodes n6 = new Nodes(6);
        Nodes n7 = new Nodes(7);
        Nodes n8 = new Nodes(8);
        Nodes[] allNodes = {n1, n2, n3, n4,n5,n6,n7,n8};

        ArrayList<Edge> edgeArrayList = new ArrayList<>();
        Edge e1 = new Edge(n1, n2);
        Edge e2 = new Edge(n1, n5);
        Edge e3 = new Edge(n2, n3);
        Edge e4 = new Edge(n5, n6);
        Edge e5 = new Edge(n2, n5);
        Edge e6 = new Edge(n3, n4);
        Edge e7 = new Edge(n6, n7);
        Edge e8 = new Edge(n6, n8);
        Edge e9 = new Edge(n1, n8);
        Edge e10 = new Edge(n4, n2);
        Edge e11 = new Edge(n6, n3);

        edgeArrayList.add(e1);
        edgeArrayList.add(e2);
        edgeArrayList.add(e3);
        edgeArrayList.add(e4);
        edgeArrayList.add(e5);
        edgeArrayList.add(e6);
        edgeArrayList.add(e7);
        edgeArrayList.add(e8);
        edgeArrayList.add(e9);
        edgeArrayList.add(e10);
        edgeArrayList.add(e11);

        generateGraph(edgeArrayList);
        printGraph(allNodes);
        printTypeOfEdges(allNodes);

    }

    /**
     * Prints the types of edges that are present in the graph by calling a helper function @dfs_visit
     * @param allNodes that are present in the graph
     */
    private static void printTypeOfEdges(Nodes[] allNodes) {
        for (int i = 0; i < allNodes.length; i++) {
            if (allNodes[i].getColor().equals(Color.WHITE)){
                dfs_visit(allNodes[i]);
            }
        }
    }


    /**
     * Prints the type of edge that is associated with the given node and its descendent node
     * @param allNode whose edges are to printed
     */
    private static void dfs_visit(Nodes allNode) {
        allNode.setDiscoveryTime(discoveryTime++);
        allNode.setColor(Color.GRAY);
        Iterator<Nodes> nodesIterator = allNode.getLinkedList().listIterator();
        while (nodesIterator.hasNext()) {
            Nodes node = nodesIterator.next();
            if (node.getColor().equals(Color.WHITE)){
                System.out.println("Tree edge between "+allNode.getId()+" and "+node.getId());
                dfs_visit(node);
            }else if (node.getColor().equals(Color.GRAY)){
                System.out.println("Back edge between "+allNode.getId()+" and "+node.getId());
            }else {
                if (allNode.getDiscoveryTime() < node.getDiscoveryTime()){
                    System.out.println("Forward edge between "+allNode.getId()+" and "+node.getId());
                }else {
                    System.out.println("Cross edge between "+allNode.getId()+" and "+node.getId());
                }
            }

        }
        allNode.setColor(Color.BLACK);
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
        }
    }


}
