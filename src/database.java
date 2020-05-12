import java.sql.*;

public class database {
    public static void main(String[] args) {
        try {
            // Set up connection to database
            Connection conn = getConnection();

            // Setup statement
            int x = 1;
            Statement stmt = conn.createStatement();
            // Create query and execute
            String strSelect = "select * from graph where id = " + x;
            String edgeSelect = "select * from edges where id = " + x;
            System.out.println("The SQL statement is: " + strSelect + "\n");

            ResultSet rset = stmt.executeQuery(strSelect);

            // Loop through the result set and print

            System.out.println("The records selected are:");
            while(rset.next()) {
                int id = rset.getInt("id");
                int startNode = rset.getInt("startNode");
                int nodes = rset.getInt("nodes");
                int edges = rset.getInt("edges");
                System.out.println(id);
                System.out.println(startNode);
                System.out.println(nodes);
                System.out.println(edges);
            }

            ResultSet edgeSet = stmt.executeQuery(edgeSelect);
            System.out.println("The records selected are:");
            while(edgeSet.next()) {
                int edgeId = edgeSet.getInt("id");
                int nodeId1 = edgeSet.getInt("nodeId1");
                int nodeId2 = edgeSet.getInt("nodeId2");
                int weight = edgeSet.getInt("weight");
                int graphId = edgeSet.getInt("graphId");
                System.out.println(edgeId);
                System.out.println(nodeId1);
                System.out.println(nodeId2);
                System.out.println(weight);
                System.out.println(graphId);
            }
            /*System.out.println("Total number of records = ");

            ResultSet linkset = stmt.executeQuery(edgeSelect);

            System.out.println("The records selected are:");
            int rowCount = 0;
            while(linkset.next()) {
                int target_id = linkset.getInt("target_id");
                String description = linkset.getString("description");
                System.out.println(target_id + ", " + description);
                ++rowCount;
            }
            System.out.println("Total number of records = " + rowCount);
*/
            // Close conn and stmt
            conn.close();
            stmt.close();
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
    }

    protected static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                        "jdbc:mysql://"+DatabaseLoginData.DBURL + ":" + DatabaseLoginData.port + "/" + DatabaseLoginData.DBname +
                                "? allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
                        DatabaseLoginData.user, DatabaseLoginData.password);
    }
}
