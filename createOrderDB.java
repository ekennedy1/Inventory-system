import java.sql.*;


public class createOrderDB {
    public static void main(String [] args) throws Exception {

        // Database connection info
        Connection conn = null;
        String teamNumber = "13";
        String sectionNumber = "910";
        String dbName = "csce315" + sectionNumber + "_" + teamNumber + "db";
        String dbConnectionString = "jdbc:postgresql://csce-315-db.engr.tamu.edu/" + dbName;
        String userName = "csce315" + sectionNumber + "_" + teamNumber + "user";
        String userPassword = "table_13";

        // Connecting to the database
        try {
            conn = DriverManager.getConnection(dbConnectionString, userName, userPassword);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }


        try {
             String sqlStatement = "CREATE TABLE ordersLog (entryID int PRIMARY KEY, orderID text, date text, itemID text, totalPrice float)";

             Statement stmt = conn.createStatement();

             // Create table
             stmt.executeUpdate(sqlStatement);


        } catch(Exception e) {
            e.printStackTrace();
        }

        // closing the connection
        try {
            conn.close();
        } catch (Exception e) {
            System.out.println("Connection NOT Closed.");
        } // end try catch
    }
}
