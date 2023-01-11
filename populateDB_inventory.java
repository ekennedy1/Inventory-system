import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.sql.*;
import java.text.DecimalFormat;

/*
Used to populate the database with data
*/
public class populateDB_inventory {
    public static void main(String[] args) throws Exception {

        // Read in data from first day orders
        readFirstDayOrders();
    } 


    public static void readFirstDayOrders() {
        try {
            
            // Define Variables
            int counter = 0;
            String description = "";
            String SKU= "";
            String quantityAsStr = "";
            Double quantity = -1.0;
            String deliveredAsStr = "";
            Integer delivered = -1; // May be needed at a later phase
            String soldBy = "";
            String deliveredBy = "";
            String priceAsStr = "";
            Double price = -1.0;
            String quantMultAsStr = "";
            Integer quantMult = -1;
            String extendedAsStr = "";
            Double extended = -1.0;
            String category = "";
            String invoiceLineAsStr = "";
            Integer invoiceLine = -1;
            String detailedDescription = "";
            final int NUM_EMPTY_ROWS = 2;

            boolean foodItem = false;
            boolean bibItem = false;
            boolean bottlesItem = false;
            boolean servingItem = false;
            boolean janitorialItem = false;

            
            Scanner sc = new Scanner(new File("./first day order.csv"));

            sc.useDelimiter("\n");

            while (sc.hasNext() && counter < NUM_EMPTY_ROWS) {
                String curr = sc.next();

                if (!curr.isEmpty()) {
                    counter++;
                }
            }

            // gets a full line
            while (sc.hasNext()) {

                String inputLine = sc.next();

                // Will be used later to input into database for respective type of inventory
                if (inputLine.isEmpty() || inputLine.trim().length() == 0 || inputLine.contains(",,,,,,,")) {
                    if (inputLine.contains("Food")) {
                        foodItem = true;
                        continue;
                    } 
                    else if (inputLine.contains("Bib")) {
                        foodItem = false;
                        bibItem = true;
                        continue;
                    } 
                    else if (inputLine.contains("Bottles")) {
                        bibItem = false;
                        bottlesItem = true;
                        continue;
                    } 
                    else if (inputLine.contains("Serving")) {
                        bottlesItem = false;
                        servingItem = true;
                        continue;
                    } 
                    else if (inputLine.contains("Janitorial")) {
                        servingItem = false;
                        janitorialItem = true;
                        continue;
                    }
                }

                inputLine = inputLine.substring(1); // Remove leading comma
                String[] components = inputLine.split(",");

                final int MIN_COMPONENTS = 1;

                // For ending of file, where data is blank (verifying by itemID)

                if(components.length < MIN_COMPONENTS){
                    continue;
                }

                if(components[1].isBlank()) {
                    continue;
                }

                // Reset counter
                counter = 1;
                int loopCounter = 0;
                detailedDescription = ""; // Reset detailed description (since it uses +=)

                for (int i = 0; i < components.length; i++) {

                    if (i == 0) {
                        description = "\'" + components[i].trim() + "\'";
                    } 
                    else if (i == 1) {
                        SKU =  "\'" + components[i].trim() + "\'";
                    } 
                    else if (i == 2) {
                        quantityAsStr = components[i].trim();
                        quantity = Double.parseDouble(quantityAsStr);
                    } 
                    else if (i == 3) {
                        deliveredAsStr = components[i].trim();
                        delivered = Integer.parseInt(deliveredAsStr);
                    } 
                    else if (i == 4) {
                        soldBy = "\'" + components[i].trim() + "\'";
                    } 
                    else if (i == 5) {
                        deliveredBy = "\'" + components[i].trim() + "\'";
                    } 
                    else if (i == 6) {
                        quantMultAsStr = components[i].trim();
                        quantMult = Integer.parseInt(quantMultAsStr);
                    } 
                    else if (i == 7) {

                        // Trim whitespace
                        priceAsStr = components[i].trim();

                        // Remove $ before converting to double
                        priceAsStr = priceAsStr.substring(1);
                        
                        price = Double.parseDouble(priceAsStr);

                    } 
                    else if (i == 8) {
                        extendedAsStr = components[i].trim();
                    } 
                    else if (i == 9) {
                        String curr = components[loopCounter];

                        if (curr.equalsIgnoreCase("cold") || curr.equalsIgnoreCase("dry")
                            || curr.equalsIgnoreCase("frozen")) {
                            
                            // Insert single quotes
                            category = components[loopCounter] + "\'";
                            category = "\'" + category;
                        }
                        // contains rest of price string
                        else if (!curr.isBlank()) {
                            extendedAsStr += "," + components[i].trim();
        
                            // Decrement i so it comes back here
                            i--;
                        }
                    } 
                    else if (i == 10) {

                        // Remove leading/trailing whitespace
                        extendedAsStr.trim();

                        // Remove double quotes
                        extendedAsStr = extendedAsStr.replaceAll("\"", "");

                        // Remove $ before converting to double
                        extendedAsStr = extendedAsStr.replaceAll("\\$", "");

                        // Remove commas
                        extendedAsStr = extendedAsStr.replaceAll(",", "");

                        /* Do this here because you know it will be completed
                           Here, even if it has commas */
                        extended = Double.parseDouble(extendedAsStr);

                        // Store invoice line
                        invoiceLineAsStr = components[loopCounter];
                        invoiceLine = Integer.parseInt(invoiceLineAsStr);

                    } 
                    else if (i >= 11) {

                        final int MAX_SIZE_OF_COMPONENTS = 16;

                        // If commas in description
                        if (loopCounter >= i) {
                            detailedDescription += ",";
                        }

                        if(loopCounter < MAX_SIZE_OF_COMPONENTS){
                            detailedDescription += components[loopCounter].trim();
                        }   
                    }

                    // Increment loop counter
                    loopCounter++;
                }

                // Delete First character from detailed description -> Storing "
                if (detailedDescription.length() > 1) {
                    detailedDescription = detailedDescription.substring(1);

                    // Remove double quotes
                    detailedDescription = detailedDescription.replaceAll("\"", "");

                    // Remove any single Quotes
                    detailedDescription = detailedDescription.replaceAll("\'", "");

                    // Insert single quotes to start and end
                    detailedDescription+="\'";
                    detailedDescription = "\'" + detailedDescription;

                } else {
                    detailedDescription = "";
                }

                String sqlStatement = "";
                boolean validParsing = true; 
                final DecimalFormat df = new DecimalFormat("0.00");

                // Update respective unsellable/sellable inventory based
                // on item type
                if(foodItem || bibItem || bottlesItem) {
                    sqlStatement = "INSERT INTO sellableInventory VALUES (" + SKU + ", " + description + ", " +
                    df.format(price) + ", " +  df.format(extended) + ", " + quantity + ", " + deliveredBy + ", " + soldBy + 
                    ", " + quantMult +  ", " + invoiceLine + ", " + detailedDescription + ", " + category + ")";      
                } 
                else if(servingItem || janitorialItem) { 
                    sqlStatement = "INSERT INTO nonsellableInventory VALUES (" + SKU + ", " + description + ", " +
                            df.format(extended) + ", " + quantity + ", " + deliveredBy + ", " + soldBy + ", " + quantMult + ", " +
                            invoiceLine + ", " + detailedDescription + ", " + category + ")";
                }
                else{
                    System.out.println("Error parsing type of item. Can not enter into database");
                    validParsing = false;
                }


                if(validParsing) {
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
                        Statement stmt = conn.createStatement();

                        int result = stmt.executeUpdate(sqlStatement);

                        if (result < 0) {
                            System.out.println("No rows updated from population of database");
                        }
                    } 
                    catch (Exception e) { // Error handling
                        e.printStackTrace();
                        System.err.println(e.getClass().getName() + ": " + e.getMessage());
                        System.exit(0);
                    }
                }

            }
        } 
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}
