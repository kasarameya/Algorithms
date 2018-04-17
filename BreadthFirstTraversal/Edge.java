/**Edge class which is used to represent an edge in the graph
 *
 * Created by ©Ameya on 4/16/2018 at 7:01 PM.
 */
public class Edge {
    private BfsNode source;
    private BfsNode destination;
    private int weight;

    public Edge(BfsNode source, BfsNode destination) {
        this.source = source;
        this.destination = destination;
    }

    public Edge(BfsNode source, BfsNode destination, int weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    public BfsNode getSource() {
        return source;
    }

    public void setSource(BfsNode source) {
        this.source = source;
    }

    public BfsNode getDestination() {
        return destination;
    }

    public void setDestination(BfsNode destination) {
        this.destination = destination;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
