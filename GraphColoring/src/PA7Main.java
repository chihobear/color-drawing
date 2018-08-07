import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * 
 * 
 * This class reads in the input file described below and creates a
 * graph from it. Beginning
 * lines that are comments are ignored and nodes that map to themselves
 * (1 to 1 or 4 to 4)
 * are ignored. A .dot file is created and can be opened as a visual
 * representation of the graph.
 * 
 * Input - Unknown number of comment lines at the beggining, they start
 * with %
 * - first line of input file is row, column and number of entries
 * - all lines after that are mapping entries, representing a row and
 * column value
 * 
 * Example input file:
 * 
 * %%MatrixMarket matrix coordinate pattern symmetric
 * %-------------------------------------------------------------------------------
 * % Demo file
 * %-------------------------------------------------------------------------------
 * 5 5 7
 * 1 1
 * 3 3
 * 1 4
 * 4 2
 * 4 4
 * 4 5
 * 5 5
 * 
 * Example Output
 * graph {
 * 1;
 * 2;
 * 3;
 * 4;
 * 5;
 * 1 -- 4;
 * 4 -- 2;
 * 4 -- 5;
 * }
 * 
 */
public class PA7Main {

    public static void main(String[] args) throws FileNotFoundException {

        Scanner in = null;

        try {
            in = new Scanner(new File(args[0]));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        String str = "";
        int numNodes = 0;

        // Skip over comment lines, consider looking at the Java API for use of
        // hasNext method
        while (in.hasNextLine()) {
            str = in.nextLine();
            if (str.charAt(0) != '%') {
                // Parse the file info line that tells the number of rows,
                // columns and entries
                int row = Integer.valueOf(str.trim().split(" ")[0]);
                int col = Integer.valueOf(str.trim().split(" ")[1]);
                if (row >= col) {
                    numNodes = row;
                } else {
                    numNodes = col;
                }
                break;
            }
        }

        // Create a graph to place each entry line into

        Graph graph = new Graph(numNodes);

        // Loop over the entry lines, parse them and enter them into your graph
        while (in.hasNextLine()) {
            String[] num = in.nextLine().trim().split("\\s+");
            int first;
            int second;
            first = Integer.valueOf(num[0]);
            second = Integer.valueOf(num[1]);

            if (first != second) {
                graph.add(first, second);
            }

        }

        in.close();

        Paint p = new Paint();
        Coloring color = new Coloring(numNodes);
        processCommand(args[1], p, numNodes, graph, color);

        // Write to a .dot file in order to visualize your graph

        PrintWriter writer = new PrintWriter("graph.dot");

        writer.println("graph {");
        writer.print(color.toDotString());
        writer.print(graph.toString());
        writer.println("}");

        writer.close();
    }

    private static void processCommand(String str, Paint p, int numNodes,
            Graph graph, Coloring color) {
        if (str.equals("HEURISTIC")) {
            p.heuristic(graph, color);
            System.out.println(color.toString());
        } else if (str.equals("BACKTRACK")) {
            p.backTracking(graph, new Coloring(numNodes), 1, color);
            System.out.println(color.toString());
        }
        else if (str.equals("MINE")) {
            p.mine(graph, color, 1);
            System.out.println(color.toString());
        }
        else if (str.equals("TIME")) {
            long startTime = System.nanoTime();
            p.heuristic(graph, color);
            long endTime = System.nanoTime();
            long duration = (endTime - startTime) / 1000000;
            System.out.println("heuristic: " + color.getNumColors()
                    + " colors, " + duration + " milliseconds");

            color.clearColor();
            startTime = System.nanoTime();
            p.mine(graph, color, 1);
            endTime = System.nanoTime();
            duration = (endTime - startTime) / 1000000;
            System.out.println("mine: " + color.getNumColors() + " colors, "
                    + duration + " milliseconds");

            color.clearColor();
            startTime = System.nanoTime();
            p.backTracking(graph, new Coloring(numNodes), 1, color);
            endTime = System.nanoTime();
            duration = (endTime - startTime) / 1000000;
            System.out.println("backtrack: " + color.getNumColors()
                    + " colors, " + duration + " milliseconds");
        }
    }

}
