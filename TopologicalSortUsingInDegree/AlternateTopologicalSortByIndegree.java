import java.util.HashMap;

/**
 * This program performs the topological sorting of the given
 * DAG by using the in-degree of each vertex as its factor.
 * <p>
 * Created by ©Ameya on 3/16/2018 at 6:27 PM.
 */


public class AlternateTopologicalSortByIndegree {
    static HashMap<Integer, Node> hm = new HashMap<>();

    public static void main(String[] args) {

        Node n0 = new Node(0);
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);

        hm.put(0, n0);
        hm.put(1, n1);
        hm.put(2, n2);
        hm.put(3, n3);

        addEdge(n0, n1);
        addEdge(n0, n3);
        addEdge(n0, n2);
        addEdge(n3, n1);
        addEdge(n3, n2);

        printGraph();
        performDFS();
        topologicalSortByInDegree();

    }

    private static void topologicalSortByInDegree() {
        while (!hm.isEmpty()) {
            for (int i = 0; i < 4; i++) {
                if (hm.containsKey(i)) {
                    if (hm.get(i).getInDegree() == 0) {
                        System.out.println(hm.get(i).getId());
                        removeEdge(hm.get(i));
                    }
                }
            }
        }

    }

    /**
     * Remove all the edges assoicated with this node and then the node iteself
     *
     * @param node to be removed
     */
    private static void removeEdge(Node node) {
        for (Node node1 : node.getAdjList()) {
            if (node1.getInDegree() > 0) {
                node1.setInDegree(node1.getInDegree() - 1);
            }
        }

        hm.remove(node.getId());
    }

    /**
     * Adds an edge between edge n1 & n2
     *
     * @param n1 source node
     * @param n2 destination node
     */
    private static void addEdge(Node n1, Node n2) {
        n1.getAdjList().add(n2);
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
     * Perform the DFS on all the nodes by calling a helper function
     * and assigning a value for each call
     */
    private static void performDFS() {
        for (int i = 0; i < hm.size(); i++) {
            if (hm.get(i).getColor().equals(Colors.WHITE)) {
                DFS_visit(hm.get(i));
            }
        }
    }

    /**
     * Used to mark that the given node has been visited and now it is time to visit its descendants
     * and assign in-degree to each descendant of the given node
     *
     * @param node that is to be visited
     */

    private static void DFS_visit(Node node) {
        node.setColor(Colors.GRAY);
        for (Node node1 : node.getAdjList()) {
            node1.setInDegree(node1.getInDegree() + 1);
            if (node1.getColor().equals(Colors.WHITE)) {
                DFS_visit(node1);
            }
        }
        node.setColor(Colors.BLACK);
    }
}


