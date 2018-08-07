/*
 * Section8Edge 
 * 
 * This class holds the edge data structure that supports the 
 * Section8Graph data structure. The edges are stored as an 
 * unordered pair of integers that map from node to node. Since
 * they are unordered, they represent undirected edges. 
 * 
 */
public class Edge implements Comparable<Edge> {

    private int node1;

    private int node2;

    public Edge(int n1, int n2) {

        node1 = n1;

        node2 = n2;

    }

    /*
     * 
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     * 
     * 
     * 
     * TODO: Match the string output for edges shown in
     * 
     * the file usage comment in Section8Main and the writeup.
     * 
     */

    public String toString() {

        return node1 + " -- " + node2 + ";";

    }

    /*
     * 
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     * 
     * 
     * 
     * TODO: complete this method so that two edges that connect the same two
     * nodes
     * 
     * are equal - return true. If the two edges are not equal return false.
     * 
     * 
     * 
     * ex) 1 -- 2 == 2 -- 1 is true
     * 
     * 1 -- 3 == 1 -- 3 is true
     * 
     * 1 -- 3 == 1 -- 3 is false
     * 
     */

    @Override

    public boolean equals(Object e) {
        Edge org = (Edge) e;
        if (org.node1 == this.node1 && org.node2 == this.node2) {
            return true;
        }

        else if (org.node1 == this.node2 && org.node2 == this.node1) {
            return true;
        }

        return false;

    }

    /*
     * 
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     * 
     * 
     * 
     * We overrode equals so we need to over ride hashCode.
     * 
     */

    public int hashCode() {

        return (node1 + node2);

    }

    /*
     * 
     * (non-Javadoc)
     * 
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     * 
     * 
     * 
     * TODO: complete this method so that two edges can be compared
     * 
     * for the TreeSet to maintain order.
     * 
     */

    @Override

    public int compareTo(Edge o) {
        if (this.equals(o)) {
            return 0;
        }
        if (this.node1 > o.node1 || this.node1 == o.node1) {
            return 1;
        } else {
            return -1;
        }

    }

    /*
     * Get another node's id according to the given id.
     * If there is no coordinate id, return -1.
     */
    public int getAnother(int id) {
        if (id == this.node1) {
            return node2;
        } else if (id == this.node2) {
            return node1;
        } else {
            return -1;
        }
    }
}
