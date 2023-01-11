import java.sql.*;
import java.util.ArrayList;

public class createDB {
    public static void main(String[] args) throws Exception {

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
        } 
        catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage()); 
            System.exit(0);
        }

        try {

            ArrayList<String> sqlStatements = new ArrayList<String>();

            /* Create Sellable Inventory Table */

            sqlStatements.add("DROP TABLE IF EXISTS sellableInventory");

            String sqlStatement = "CREATE TABLE sellableInventory (productID text PRIMARY KEY, productName text, price float, extendedPrice float, quantity float, deliveredBy text, soldBy text, quantityMultiplier int, invoiceLine int, description text, category text)";

            sqlStatements.add(sqlStatement);
            
            /* Create Nonsellable Inventory Table */

            sqlStatements.add("DROP TABLE IF EXISTS nonsellableInventory");

            sqlStatement = "CREATE TABLE nonsellableInventory (itemID text PRIMARY KEY, objectName text, extendedPrice float, quantity int, deliveredBy text, soldBy text, quantityMultiplier int, invoiceLine int, description text, category text)";

            sqlStatements.add(sqlStatement);

            /* Create Daily Sales Table */

            sqlStatements.add("DROP TABLE IF EXISTS dailySales");

            sqlStatement = "CREATE TABLE dailySales (entryID text PRIMARY KEY, itemID text, quantity int, total float, date text)";

            sqlStatements.add(sqlStatement); 

            /* Create Sellable Inventory and Order Relations Table */

            sqlStatements.add("DROP TABLE IF EXISTS sellableInvOrderRelation");

            sqlStatement = "CREATE TABLE sellableInvOrderRelation (entryID int PRIMARY KEY, itemID int, productID int, quantOfUnderlyingProd int, menuPrice float)";

            sqlStatements.add(sqlStatement);

            /* CREATE Sales to Inventory Conversion TABLE */

            sqlStatements.add("DROP TABLE IF EXISTS salesToInventoryConversion");

            sqlStatement = "CREATE TABLE salesToInventoryConversion (conversionID int PRIMARY KEY, SKU text, productID text, amountOfBaseProduct float)";

            sqlStatements.add(sqlStatement);

            /* Create Menu */
            
            sqlStatement = "DROP TABLE IF EXISTS menu";
            sqlStatements.add(sqlStatement);
            
            sqlStatement = "CREATE TABLE menu (itemID int PRIMARY KEY, name text, description text)";
            sqlStatements.add(sqlStatement);

            /* Create Manager Preferences Table */
        
            sqlStatements.add("DROP TABLE IF EXISTS managerPrefernces");

            sqlStatement = "CREATE TABLE managerPrefernces (SKU text PRIMARY KEY, name text, fillMin int)";

            sqlStatements.add(sqlStatement); 

            Statement stmt = conn.createStatement();

            for(String currentCommand: sqlStatements){
                stmt.executeUpdate(currentCommand);
            }
            

        } 
        catch (Exception e) { // Error handling
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

        // closing the connection
        try {
            conn.close();
        } 
        catch (Exception e) {
            System.out.println("Connection NOT Closed.");
        } // end try catch

    }// end main

}
