import java.util.ArrayList;
import java.util.Arrays;

/**
 * This is an implementation of Bellman Ford Algorithm
 * using Dynamic Programming.
 * This algorithm provides the shortest distance from source to destination
 * using at-most k edges, where k = Number of vertices - 1
 * <p>
 * Created by ©Ameya on 4/16/2018 at 11:20 PM.
 */
public class BellmanFordDP {
    public static void main(String[] args) {
        BfsNode node0 = new BfsNode(0);
        BfsNode node1 = new BfsNode(1);
        BfsNode node2 = new BfsNode(2);
        BfsNode node3 = new BfsNode(3);
        BfsNode[] allNodes = {node0, node1, node2, node3};

        Edge edge1 = new Edge(node0, node1, 2);
        Edge edge2 = new Edge(node0, node2, 3);
        Edge edge3 = new Edge(node1, node3, 5);
        Edge edge4 = new Edge(node2, node3, 6);

        ArrayList<Edge> edges = new ArrayList<>();
        edges.add(edge1);
        edges.add(edge2);
        edges.add(edge3);
        edges.add(edge4);
        generateGraph(edges);
        printGraph(allNodes);
        bellmanFordUsingDP(allNodes, edges);
    }

    /**
     * We use a 2D matrix to store the shortest path from source to destination using at-most k edges
     *
     * @param allNodes array of all the nodes in the grapgh
     * @param edges    list of edges in the graph
     */

    private static void bellmanFordUsingDP(BfsNode[] allNodes, ArrayList<Edge> edges) {
        int dp[][] = new int[allNodes.length][allNodes.length];
        for (int i = 0; i < allNodes.length; i++) {
            for (int j = 0; j < allNodes.length; j++) {
                if (i == 0) {
                    if (j == 0) {
                        dp[i][j] = 0;
                    } else {
                        //As Integer.MAX_VALUE leads to issues when you add something into it
                        dp[i][j] = 99999;
                    }
                }
            }
        }
        for (int k = 1; k < allNodes.length; k++) {
            for (int i = 0; i < allNodes.length; i++) {
                dp[k][i] = dp[k - 1][i];
                for (Edge e : edges) {
                    if (e.getDestination().getValue() == i) {
                        if (dp[k][i] > dp[k - 1][e.getSource().getValue()] + e.getWeight()) {
                            dp[k][i] = dp[k - 1][e.getSource().getValue()] + e.getWeight();
                        }

                    }
                }
            }
        }

        System.out.println(Arrays.deepToString(dp));
    }


    /**
     * Prints the generated graph by displaying the adjacency list for each node
     *
     * @param allNodes that are present in the graph
     */
    private static void printGraph(BfsNode[] allNodes) {
        for (BfsNode node : allNodes) {
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
     *
     * @param edges list of edges that contain information edges
     */
    private static void generateGraph(ArrayList<Edge> edges) {
        for (Edge edge : edges) {
            BfsNode source = edge.getSource();
            BfsNode dest = edge.getDestination();

            source.getAdjList().add(dest);
        }
    }
}
