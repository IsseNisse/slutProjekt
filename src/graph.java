import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class graph {
    protected int start;
    protected int node;
    private int edge;
    private int from;
    private int to;
    private int weight;
    ArrayList<edge> edges = new ArrayList<>();
    ArrayList<node> nodes = new ArrayList<>();

    int id;
    int startNode;
    int nodesQ;
    int edgesQ;

    int edgeId;
    int nodeId1;
    int nodeId2;
    int weightQ;
    int graphId;

    ResultSet edgeSet;
    int i;

    public void database() {
        try {
            Connection conn = database.getConnection();

            int x = 1;
            Statement stmt = conn.createStatement();
            // Create query and execute
            String strSelect = "select * from graph where id = " + x;
            String edgeSelect = "select * from edges where id = " + i;
            System.out.println("The SQL statement is: " + strSelect + "\n");

            ResultSet rset = stmt.executeQuery(strSelect);

            System.out.println("The records selected are:");
            while(rset.next()) {
                id = rset.getInt("id");
                startNode = rset.getInt("startNode");
                nodesQ = rset.getInt("nodes");
                edgesQ = rset.getInt("edges");
            }

            edgeSet = stmt.executeQuery(edgeSelect);
            System.out.println("The records selected are:");
            while(edgeSet.next()) {
                edgeId = edgeSet.getInt("id");
                nodeId1 = edgeSet.getInt("nodeId1");
                nodeId2 = edgeSet.getInt("nodeId2");
                weightQ = edgeSet.getInt("weight");
                graphId = edgeSet.getInt("graphId");
            }


        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Function to call for and make a new graph
     */
    public graph() {
        read();
    }

    /**
     * A read file that reads the txt file with information and adds the numbers to their right place
     */
    public void read() {
        /*Scanner tgb = null;
        try {
            // Graph 1 is the graph from the example in my scientific report
            // Graph 2 is a graph of the 10 largest cities in Sweden
            // Graph 3 is a smaller made up graph
            // Graph 4 is a graph of the 19 most visited countries of 2019
            // Graph 5 is the same as graph 4 but is reduced to only 11 nodes/most visited countries
            tgb = new Scanner(new File("graph5.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assert tgb != null;*/

        start = startNode;
        node = nodesQ;
        edge = edgesQ;

        for (i = 0; i < edge; i++) {
            node fromNode = null;
            node toNode = null;
            try {
                from = edgeSet.getInt("nodeId1");
                to = edgeSet.getInt("nodeId2");
                weight = edgeSet.getInt("weight");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            for (int j = 0; j < nodes.size(); j++) {
                if (nodes.get(j).getNode1() == from) {
                    fromNode = nodes.get(j);

                }
                if (nodes.get(j).getNode1() == to) {
                    toNode = nodes.get(j);
                }
            }
            if (fromNode == null) {
                fromNode = new node(from);
                nodes.add(fromNode);
            }
            if (toNode == null) {
                toNode = new node(to);
                nodes.add(toNode);
            }
            edge edgeTotal = new edge(fromNode, toNode, weight);
            edges.add(edgeTotal);
            fromNode.addedge(edgeTotal);
            toNode.addedge(edgeTotal);
        }
    }

    /**
     * Get the arrayList that contains all the nodes
     * @return the arrayList
     */
    public ArrayList<node> getNodes() {
        return nodes;
    }

}
