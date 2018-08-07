
/* Coloring.java
 * 
 * This class is provided for PA7, but other than the Color enum
 * it can be modified.
 */
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class Coloring {
    private Color[] node2color;
    private Map<Color, String> dotColor;

    public enum Color {
        A, B, C, D, E, F, G, H, I, J, K, L, // M, N, O, P, Q, R, S, T, U, V, W,
                                            // X, Y, Z,
        NOCOLOR;
    }

    // https://www.graphviz.org/doc/info/colors.html
    private void initDotColor() {
        dotColor = new HashMap<>();
        dotColor.put(Color.NOCOLOR, "azure2");
        dotColor.put(Color.A, "red");
        dotColor.put(Color.B, "dodgerblue1");
        dotColor.put(Color.C, "green");
        dotColor.put(Color.D, "yellow");
        dotColor.put(Color.E, "orange");
        dotColor.put(Color.F, "pink");
        dotColor.put(Color.G, "cyan");
        dotColor.put(Color.H, "plum");
        dotColor.put(Color.I, "brown");
        dotColor.put(Color.J, "cornsilk");
        dotColor.put(Color.K, "aquamarine");
        dotColor.put(Color.L, "gold");
        /*
         * dotColor.put(Color.M, "khaki");
         * dotColor.put(Color.N, "lavender");
         * dotColor.put(Color.O, "maroon");
         * dotColor.put(Color.P, "magenta");
         * dotColor.put(Color.Q, "palegreen");
         * dotColor.put(Color.R, "pink1");
         * dotColor.put(Color.S, "purple");
         * dotColor.put(Color.T, "salmon");
         * dotColor.put(Color.U, "sandybrown");
         * dotColor.put(Color.V, "seagreen");
         * dotColor.put(Color.W, "violet");
         * dotColor.put(Color.X, "yellow4");
         * dotColor.put(Color.Y, "wheat");
         * dotColor.put(Color.Z, "yellogreen");
         */
    }

    /**
     * @return All the filled nodes for the dot file.
     */
    public String toDotString() {
        String dot_str = "";
        // Create some node output with colors
        for (int n = 1; n < node2color.length; n++) {
            dot_str += "    " + n + " [style=filled, fillcolor = "
                    + dotColor.get(node2color[n]) + "];\n";
        }
        return dot_str;
    }

    /**
     * ctor: Initialize all the node colors to have no color.
     * 
     * @param num_nodes
     *            Number of nodes that need to be colored. Assuming nodes are
     *            numbered 0 through num_nodes-1.
     */
    public Coloring(int num_nodes) {
        initDotColor();

        node2color = new Color[num_nodes + 1];
        for (int i = 0; i <= num_nodes; i++) {
            node2color[i] = Color.NOCOLOR;
        }
    }

    /**
     * Set the given node to have the given color.
     * 
     * @param nodeID
     * @param c
     */
    public void setColor(int nodeID, Color c) {
        assert (nodeID >= 1 && nodeID < node2color.length);
        node2color[nodeID] = c;
    }

    /**
     * Set the given node to have the NOCOLOR.
     * 
     * @param nodeID
     * @param c
     */
    public void unsetColor(int nodeID) {
        assert (nodeID >= 1 && nodeID < node2color.length);
        node2color[nodeID] = Color.NOCOLOR;
    }

    // Clear all nodes' color
    public void clearColor() {
        for (int i = 0; i < node2color.length; i++) {
            node2color[i] = Color.NOCOLOR;
        }
    }

    /**
     * Lookup the color for the given nodeID.
     * 
     * @param nodeID
     */
    public Color getColor(int nodeID) {
        assert (nodeID >= 1 && nodeID < node2color.length);
        return node2color[nodeID];
    }

    /**
     * Create a copy of the given object into ourselves.
     */
    public void copyOtherIntoSelf(Coloring other) {
        assert other.node2color.length == node2color.length;

        for (int i = 0; i < node2color.length; i++) {
            node2color[i] = other.node2color[i];
        }
    }

    /**
     * If any of the given nodes have the given color, return true.
     * 
     * @param nodes
     * @param c
     * @return
     */
    public boolean haveColor(List<Integer> nodes, Color c) {
        for (Integer node : nodes) {
            if (node2color[node] == c) {
                return true;
            }
        }
        return false;
    }

    /**
     * If haven't done any coloring yet, then return number of potential color
     * values. If have done some coloring, then return that number of colors.
     * Never counts NOCOLOR in the color count.
     */
    public int getNumColors() {
        HashSet<Color> colors_used = new HashSet<Color>();
        // gather up all the colors being used
        for (int i = 1; i < node2color.length; i++) {
            colors_used.add(node2color[i]);
        }
        // If we are only using NOCOLOR, then say worst case of
        // one color per node.
        colors_used.remove(Color.NOCOLOR);
        if (colors_used.size() == 0) {
            return Color.values().length - 1;
        } else {
            return colors_used.size();
        }
    }

    // Get a String of this color strategy.
    public String toString() {
        String str = "Coloring: number of colors = " + getNumColors() + "\n";
        for (int node = 1; node < node2color.length; node++) {
            str += "    " + node + ": " + node2color[node] + "\n";
        }
        return str;
    }

}

