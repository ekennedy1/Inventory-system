import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;

public class readMenuKey {
    public static void main(String[] args) throws Exception {

        // Create Arrays to store data
        Integer[] itemArray = new Integer[19];
        String[] nameArray = new String[19];
        String[] descriptionArray = new String[19];
        float[] priceArray = new float[19];

        try {
            // Create buffered reader
            BufferedReader br = new BufferedReader(new FileReader("MenuKey.csv"));
            String line = "";
            String delimitter = ",";

            // Skip first two lines
            br.readLine();
            br.readLine();

            int i = 0;
            while ((line = br.readLine()) != null) {
                String[] menuItem = line.split(delimitter); // use comma as separator
                int lineLength = menuItem.length;
                int descriptionIndex = 4;

                // Add data to respected array
                itemArray[i] = Integer.parseInt(menuItem[1]);
                nameArray[i] = menuItem[2];
                descriptionArray[i] = menuItem[3];
                while (descriptionIndex < lineLength - 1) {
                    descriptionArray[i] += menuItem[descriptionIndex];
                    descriptionIndex++;
                }
                priceArray[i] = Float.parseFloat(menuItem[lineLength - 1].substring(1));
                i++; // iterate
            }

        }
        // Error handling
        catch (IOException e) {
            e.printStackTrace();
        } // end try
         
        ArrayList<String> sqlStatements = new ArrayList<String>();
        
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
            // create a statement object
            Statement stmt = conn.createStatement();

            // // create an SQL statement

            for(int i = 0; i < 19; i++) {

                Integer itemID = itemArray[i];
                String name = "\'" + nameArray[i].trim().replaceAll("\'", "") + "\'";

                // String description = 
                String description = "\'" + descriptionArray[i].trim().replaceAll("\"", "") + "\'";

                if(description.replaceAll("\'", "").isBlank()) {
                    description = "\'...\'";
                }
                
                String newStatement = "INSERT INTO menu VALUES(" + itemID + ", " + name + ", " + description + ")";

                sqlStatements.add(newStatement);
            }

            for(String currentStatement: sqlStatements) {

                int result = stmt.executeUpdate(currentStatement);

                if(result < 0) {
                    System.out.println("Error inserting into menu");
                }
            }

        } catch (Exception e) {
            System.out.println("Error accessing Database.");
        }
        

        // Close connection
        try {
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        } // end try catch

    }
}