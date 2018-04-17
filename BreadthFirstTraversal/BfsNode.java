import java.util.LinkedList;

/**Model class for Node in the BFS traversal
 *
 * Created by ©Ameya on 4/16/2018 at 6:59 PM.
 */
public class BfsNode {
    private BfsNode parentNode = null;
    private boolean seen = false;
    private int distance = Integer.MIN_VALUE;
    private int value;
    private LinkedList<BfsNode> adjList = new LinkedList<>();


    public BfsNode(int value) {
        this.value = value;
    }

    public BfsNode getParentNode() {
        return parentNode;
    }

    public void setParentNode(BfsNode parentNode) {
        this.parentNode = parentNode;
    }

    public boolean isSeen() {
        return seen;
    }

    public void setSeen(boolean seen) {
        this.seen = seen;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public LinkedList<BfsNode> getAdjList() {
        return adjList;
    }

    public void setAdjList(LinkedList<BfsNode> adjList) {
        this.adjList = adjList;
    }
}
