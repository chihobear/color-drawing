import java.util.TreeSet;

/*
 * Section8Graph 
 * 
 * This class holds the graph data structure that stores the edges. 
 * The edges are stored in a TreeSet so they can be kept in 
 * lexigraphical order and only unique edges are included. 
 * 
 * This class relies on the Section8Edge class. 
 */
public class Graph {

    private TreeSet<Edge> allEdges;
    private int totalNodes;

    public Graph(int numNodes) {
        allEdges = new TreeSet<Edge>();
        totalNodes = numNodes;
    }

    /*
     * Inserts an edge into the graph.
     */
    public void add(int x, int y) {
        allEdges.add(new Edge(x, y));
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     * 
     * Creates a String in the specified .dot format to
     * output a graph.
     * 
     */
    public String toString() {

        // String str = "graph {\n";
        String str = "";
//        for (int i = 1; i <= totalNodes; i++) {
//            str += "        " + i + ";\n";
//        }
        for (Edge edge : allEdges) {
            str += edge.toString();
            str += "\n";
        }
        // str += "}";
        return str;
    }

    // Return the tree set.
    public TreeSet<Edge> getTreeSet() {
        return this.allEdges;
    }

    // Get total number of nodes.
    public int getTotal() {
        return this.totalNodes;
    }
}
