/*
 * This class is used to implement various strategies to color a graph.
 */
public class Paint {

    /**
     * Backtrack strategy
     * 
     * @param graph,
     *            the graph that should be processed
     * @param color,
     *            the color class that stores color the color strategy
     * @param kitNode,
     *            a int showing which node is processed now
     * @param min,
     *            the color class that stores color the color strategy with the
     *            minimum colors
     */
    public void backTracking(Graph graph, Coloring coloring, int kthNode,
            Coloring min) {
        // If all nodes are colored, return.
        if (kthNode == graph.getTotal() + 1) {
            if (coloring.getNumColors() < min.getNumColors()) {
                min.copyOtherIntoSelf(coloring);
            }
            return;
        } else {
            // If total number of colors processed now is less than the minimum.
            if (coloring.getNumColors() <= min.getNumColors()) {
                for (Coloring.Color c : Coloring.Color.values()) {
                    if (c == Coloring.Color.NOCOLOR) {
                        continue;
                    } else {
                        // If there is no conflict, draw the node.
                        if (!conflict(kthNode, c, graph, coloring)) {
                            coloring.setColor(kthNode, c);
                            backTracking(graph, coloring, kthNode + 1, min);
                            coloring.unsetColor(kthNode);
                        }
                    }
                }
                return;
            }
        }
    }

    /**
     * Heuristic strategy
     * 
     * @param graph
     * @param coloring
     */
    public void heuristic(Graph graph, Coloring coloring) {
        for (int i = 1; i <= graph.getTotal(); i++) {
            for (Coloring.Color c : Coloring.Color.values()) {
                if (c == Coloring.Color.NOCOLOR) {
                    continue;
                } else {
                    // If there is no conflict, draw the node and break the
                    // loop.
                    if (!conflict(i, c, graph, coloring)) {
                        coloring.setColor(i, c);
                        break;
                    }
                }
            }
        }
    }

    /**
     * My strategy
     * 
     * @param graph
     * @param coloring
     * @param kthNode
     */
    public void mine(Graph graph, Coloring coloring, int kthNode) {
        // If all the nodes are colored.
        if (kthNode == graph.getTotal() + 1) {
            return;
        } else {
            for (Coloring.Color c : Coloring.Color.values()) {
                // If there is no conflict.
                if (!conflict(kthNode, c, graph, coloring)) {
                    coloring.setColor(kthNode, c);
                    mine(graph, coloring, kthNode + 1);
                    break;
                }
            }
        }
    }

    /**
     * A function that judges if there is a conflict with the given node and
     * color.
     * 
     * @param nodenum,
     *            the position of the node
     * @param c,
     *            the color
     * @param graph
     * @return true if there is a conflict, false if there is not a conflict.
     */
    private boolean conflict(int nodenum, Coloring.Color c, Graph graph,
            Coloring coloring) {
        for (Edge e : graph.getTreeSet()) {
            int id = e.getAnother(nodenum);
            if (id != -1) {
                if (coloring.getColor(id) == c) {
                    return true;
                }
            }
        }
        return false;
    }

}
