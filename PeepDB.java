import java.text.DecimalFormat;
import java.util.HashMap;
import java.sql.*;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class PeepDB {

    private static HashMap<String, String> prices = new HashMap<String, String>();
    private static HashMap<Integer, String> names = new HashMap<Integer, String>();
    private static HashMap<Integer, String> descriptions = new HashMap<Integer, String>();

    public static void main(){

    }

    public static void updateNamesAndDescriptions() {

        if(names.size() != 0) {
            names.clear();
        }

        // Create DB Connection
        Connection conn = null;

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://csce-315-db.engr.tamu.edu/csce315910_13db",
                    "csce315910_13user", "table_13");
        } 
        catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

        String itemIDandNameAndDescription = "";

        try {
            // create a statement object
            Statement stmt = conn.createStatement();

            // create an SQL statement
            String sqlStatement = "SELECT itemID, name, description FROM menu";

            // send statement to DBMS
            ResultSet result = stmt.executeQuery(sqlStatement);

            while (result.next()) {

                itemIDandNameAndDescription += result.getString("itemID") + "," + result.getString("name") + "," + 
                result.getString("description") + "\n";
            }
        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error accessing Database.");
        }

        // Close connection
        try {
            conn.close();

        } 
        catch (Exception e) {
            e.printStackTrace();
        } // end try catch

        String [] items = itemIDandNameAndDescription.split("\n");

        HashMap<Integer, String> itemNames = new HashMap<Integer, String>();
        HashMap<Integer, String> itemDescriptions = new HashMap<Integer, String>();

        for(String item: items) {
            String [] components = item.split(",");

            if(item.isBlank()) {
                continue;
            }

            Integer itemID = Integer.parseInt(components[0].trim());
            String name = components[1].toUpperCase();
            String description = components[2];

            itemNames.put(itemID, name);

            itemDescriptions.put(itemID, description);
        }

        names = itemNames;
        descriptions = itemDescriptions;
    }

    public static HashMap<Integer, String>getNames() {
        
        return names;
    }

    public static HashMap<Integer, String>getDescriptions() {
        return descriptions;
    }

    public static HashMap<String, String> getPrices() {
        
        // Need to reload prices every time, in case manager changed them
        if(prices.size() != 0) {
            prices.clear();
        }
            
        // Create DB Connection
        Connection conn = null;

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://csce-315-db.engr.tamu.edu/csce315910_13db",
                    "csce315910_13user", "table_13");
        } 
        catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

        String itemAndPrice = "";
        try {
            Statement stmt = conn.createStatement();

            String sqlStatement = "SELECT itemID, menuprice FROM sellableInvOrderRelation";

            ResultSet result = stmt.executeQuery(sqlStatement);

            while (result.next()) {

                itemAndPrice += result.getString("itemID") + "," + result.getString("menuprice") + "\n";
            }
        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error accessing Database.");
        }

        // Close connection
        try {
            conn.close();
            
        } 
        catch (Exception e) {
                e.printStackTrace();
        } // end try catch

        String[] items = itemAndPrice.split("\n");

        HashMap<String, String> itemPrices = new HashMap<String, String>();

        for (String item : items) {
            String[] currentItem = item.split(",");

            for (String curr : currentItem) {
                curr = curr.trim();
            }

            final DecimalFormat df = new DecimalFormat("0.00");

            if (!currentItem[0].isBlank() && !currentItem[1].isBlank() &&
                    itemPrices.get(currentItem[0]) == null) {
                String currPrice = df.format(Double.parseDouble(currentItem[1]));
                itemPrices.put(currentItem[0], currPrice);
            }
        }

        // Store in prices
        prices = itemPrices;

        // Return prices
        return itemPrices;
    }
}
