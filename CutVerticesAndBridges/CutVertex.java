import java.util.ArrayList;


/**
 * This program prints all the cut-vertices and bridges present in
 * a Undirected graph
 *
 * Created by ©Ameya on 3/19/2018 at 6:52 PM.
 */
public class CutVertex {
    private static int discoveryTime = 0;

    public static void main(String[] args) {
        Nodes n1 = new Nodes(1);
        Nodes n2 = new Nodes(2);
        Nodes n3 = new Nodes(3);
        Nodes n4 = new Nodes(4);
        Nodes n5 = new Nodes(5);
        Nodes n6 = new Nodes(6);

        Nodes[] allNodes = {n1, n2, n3, n4, n5, n6};

        ArrayList<Edge> edgeArrayList = new ArrayList<>();
        Edge e1 = new Edge(n1, n2);
        Edge e2 = new Edge(n1, n4);
        Edge e3 = new Edge(n2, n4);
        Edge e4 = new Edge(n2, n5);
        Edge e5 = new Edge(n2, n3);
        Edge e6 = new Edge(n3, n6);
        Edge e7 = new Edge(n5, n3);

        edgeArrayList.add(e1);
        edgeArrayList.add(e2);
        edgeArrayList.add(e3);
        edgeArrayList.add(e4);
        edgeArrayList.add(e5);
        edgeArrayList.add(e6);
        edgeArrayList.add(e7);


        generateGraph(edgeArrayList);
        printGraph(allNodes);
        assignLowValues(allNodes);
        printLowValues(allNodes);
        printBridges(allNodes);
        printCutVertices(allNodes);

    }

    /**
     * Print all the cut vertices in a graph
     * @param allNodes of the graph
     */
    private static void printCutVertices(Nodes[] allNodes) {
        for (Nodes nodes : allNodes) {
            //Check if the selected vertex is root node
            if (nodes.getDiscoveryTime() == 1) {
                int childCount = 0;
               //Traverse the adjacency list of root node to get the number of children
                for (Nodes nodes1 : nodes.getLinkedList()) {

                    if (nodes1.getParentNode().equals(nodes)) {
                        childCount++;
                    }
                }
                //If the root node has more than 1 child then it is a cut-vertex
                if (childCount > 1) {
                    System.out.println("Cut-Vertex = " + nodes.getId());
                }
            } else {
                boolean isCutVertex = false;
                //If the vertex is not root node, but it has a child whose lov >= nodes.discovery time then
                //then nodes is a cut-vertex
                for (Nodes nodes2 : nodes.getLinkedList()) {
                    if (nodes2.getLow() >= nodes.getDiscoveryTime()) {
                        isCutVertex = true;
                    }
                }
                if (isCutVertex) {
                    System.out.println("Cut-Vertex =" + nodes.getId());
                }
            }
        }
    }


    /**
     * Print all the bridges present in the graph based on  the condition
     * @param allNodes in the graph
     */
    private static void printBridges(Nodes[] allNodes) {
        for (int i = 0; i < allNodes.length; i++) {
            if (allNodes[i].getDiscoveryTime() == allNodes[i].getLow()) {
                if (allNodes[i].getParentNode() != null) {
                    System.out.println("There is a bridge between " + allNodes[i].getId() +
                            " and " + allNodes[i].getParentNode().getId());
                }
            }
        }
    }


    private static void printLowValues(Nodes[] allNodes) {
        for (Nodes n : allNodes) {
            System.out.println("Node id = " + n.getId() + " Low = " + n.getLow() + " Dtime = " + n.getDiscoveryTime());
        }
    }

    private static void assignLowValues(Nodes[] allNodes) {
        for (Nodes allNode : allNodes) {
            if (allNode.getColor().equals(Color.WHITE)) {
                dfs_visit(allNode);
            }
        }
    }

    private static void dfs_visit(Nodes allNode) {
        allNode.setColor(Color.GRAY);
        allNode.setDiscoveryTime(++discoveryTime);
        allNode.setLow(allNode.getDiscoveryTime());

        for (Nodes visitedNode : allNode.getLinkedList()) {

            if (visitedNode.getColor().equals(Color.WHITE)) {

                visitedNode.setParentNode(allNode);
                dfs_visit(visitedNode);

            } else if (visitedNode.getColor().equals(Color.GRAY) && !(allNode.getParentNode().equals(visitedNode))) {
                int low = Math.min(visitedNode.getDiscoveryTime(), allNode.getLow());
                allNode.setLow(low);
            } else if (visitedNode.getColor().equals(Color.GRAY) && allNode.getParentNode().equals(visitedNode)) {
                int low2 = Math.min(allNode.getLow(), visitedNode.getLow());
                visitedNode.setLow(low2);
            }
        }
        allNode.setColor(Color.BLACK);
        allNode.setFinishTime(++discoveryTime);
    }

    /**
     * Prints the generated graph by displaying the adjacency list for each node
     *
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
     *
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
