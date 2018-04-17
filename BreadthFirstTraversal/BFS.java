import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Breadth First Traversal of a directed graph in Java
 *
 *
 * Created by ©Ameya on 4/16/2018 at 7:07 PM.
 */
public class BFS {
    public static void main(String[] args) {
        BfsNode node1 = new BfsNode(1);
        BfsNode node2 = new BfsNode(2);
        BfsNode node3 = new BfsNode(3);
        BfsNode node4 = new BfsNode(4);
        BfsNode node5 = new BfsNode(5);
        BfsNode node6 = new BfsNode(6);
        BfsNode node7 = new BfsNode(7);

        BfsNode[] allNodes = {node1,node2,node3,node4,node5,node6,node7};

        Edge edge1 = new Edge(node1,node2);
        Edge edge2 = new Edge(node1,node3);
        Edge edge3 = new Edge(node2,node4);
        Edge edge4 = new Edge(node2,node5);
        Edge edge5 = new Edge(node3,node6);
        Edge edge6 = new Edge(node5,node7);

        ArrayList<Edge> edges = new ArrayList<>();
        edges.add(edge1);
        edges.add(edge2);
        edges.add(edge3);
        edges.add(edge4);
        edges.add(edge5);
        edges.add(edge5);
        edges.add(edge6);

        generateGraph(edges);
        printGraph(allNodes);
        breadthFirstSearch(node1);


    }

    /**
     * Traverse the graph using a BfsQueue which keeps track of the visited nodes
     * @param sourceNode node from which traversal begins
     */
    private static void breadthFirstSearch(BfsNode sourceNode) {
        LinkedList<BfsNode> queue = new LinkedList<>();
        sourceNode.setDistance(0);
        sourceNode.setSeen(true);
        queue.add(sourceNode);
        while (!queue.isEmpty()){
            System.out.println(queue.getFirst().getValue());
            for (BfsNode node:queue.getFirst().getAdjList()) {
                if (!node.isSeen()){
                    node.setSeen(true);
                    node.setParentNode(queue.getFirst());
                    node.setDistance(queue.getFirst().getDistance() + 1);
                    queue.add(node);
                }
            }
            queue.removeFirst();
        }
    }

    /**
     * Prints the generated graph by displaying the adjacency list for each node
     * @param allNodes that are present in the graph
     */
    private static void printGraph(BfsNode[] allNodes) {
        for (BfsNode node:allNodes) {
            System.out.print("Adjacency List for node " + node.getValue() + " :-");
            for (BfsNode nodes : node.getAdjList()) {
                System.out.print(" \t" + nodes.getValue());
            }
            System.out.println();
        }
    }


    /**
     * Generates the graph by using the arrayList of edges such that
     * for each vertex the edge adds a source and destination to its adjacency list
     * @param edges list of edges that contain information edges
     */
    private static void generateGraph(ArrayList<Edge> edges) {
        for (Edge edge:edges) {
            BfsNode source = edge.getSource();
            BfsNode dest = edge.getDestination();

            source.getAdjList().add(dest);
        }
    }
}
