import java.sql.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.io.FileWriter;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.*;
import javax.xml.transform.Result;

public class CurrentOrderSummary extends JFrame {

    private static ArrayList<Double> currentOrderTotalPrice = new ArrayList<Double>();
    private static ArrayList<String> currentOrderItems = new ArrayList<String>();
    
    private static ArrayList<String> currentOrderSummaryStrings = new ArrayList<String>();

    // private static ArrayList<String, String> 

    private static String baseString = "Total:                     $ ";
    private static String totalString = "";
    private static String itemSummary = "";
    private static Integer orderID = 1;
    private static Integer orderEntryID = 0;
    private static Integer dailySalesEntryID = 0;


    public static void init() {
        if (currentOrderTotalPrice.size() == 0) {
            currentOrderTotalPrice.add(0.0);
        }

        updatePriceString();

        // If there is already data in the file, get next available order ID
        orderID = getNextOrderID();

    }

    // Menu product and String that goes into order summary
    public static void addItemOrderStr(String orderSum) {

        currentOrderSummaryStrings.add(orderSum);

    }

    public static void setNextEntryIDInDailySales() {
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

        // closing the connection
        try {

            String dailySalesEntryIDSQLCommand = "SELECT * FROM dailySales ORDER BY cast(entryID AS INTEGER) DESC LIMIT 1";

           

            Statement stmt = conn.createStatement();

            ResultSet newSet = stmt.executeQuery(dailySalesEntryIDSQLCommand);

            Integer prevEntryIDDailySales = -1;

            while (newSet.next()) {
                prevEntryIDDailySales = newSet.getInt("entryID");
            }

            dailySalesEntryID = prevEntryIDDailySales + 1;

        } catch (Exception e) {
            e.printStackTrace();
        } // end try catch

        // closing the connection
        try {
            conn.close();

        } catch (Exception e) {
            System.out.println("Connection NOT Closed.");
        } // end try catch

    }

    public static Integer getNextOrderID() {
        
        Integer ID = -1;

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

            // Select the most recent order ID from Orders Log
            String orderEntryIDSQLCommand = "SELECT * FROM ordersLog ORDER BY cast(entryID AS INTEGER) DESC LIMIT 1";

            Statement stmt = conn.createStatement();

            ResultSet resultSet = stmt.executeQuery(orderEntryIDSQLCommand);
            String orderID = "";
            Integer prevEntryIDOrderLog = -1;

            while(resultSet.next()) {
                orderID = resultSet.getString("orderID");
                prevEntryIDOrderLog = resultSet.getInt("entryID");
            }

            
            if(!orderID.isBlank()) {
                ID = Integer.parseInt(orderID);
                orderEntryID = prevEntryIDOrderLog + 1;
            } else {
                ID = 0; // will return 0 + 1 = 1 for the first ID
                orderEntryID = 0;
            }

            setNextEntryIDInDailySales();


        } catch(Exception e) {
            e.printStackTrace();
        }

        // closing the connection
        try {
            conn.close();

        } catch (Exception e) {
            System.out.println("Connection NOT Closed.");
        } // end try catch

        // Return NEXT ID
        return ID + 1;

    }

    public static Double getCurrentTotal() {
        return currentOrderTotalPrice.get(0);
    }

    public static String updatePriceString() {
        final DecimalFormat df = new DecimalFormat("0.00");

        String newPrice = "";
        if (currentOrderTotalPrice.size() > 0) {
            newPrice = df.format(currentOrderTotalPrice.get(0));
        } 
        else {
            newPrice = "0.00";
        }

        totalString = baseString + newPrice;

        return totalString;
    }

    public static void removeItemPriceFromOrder(String productID, Double itemPrice) {

        if(currentOrderItems.contains(productID)) {
            Double orderTotal = currentOrderTotalPrice.get(0);
            currentOrderTotalPrice.set(0, orderTotal - itemPrice);
        }
    }

    public static void addItemPriceToOrder(Double itemPrice) {


        if (currentOrderTotalPrice.size() > 0) {
            
            Double orderTotal = currentOrderTotalPrice.get(0);
            currentOrderTotalPrice.set(0, orderTotal + itemPrice);
        } 
        else {

            Double orderTotal = 0.0;
            currentOrderTotalPrice.add(orderTotal + itemPrice);
        }

    }

    public static void addItemIDToOrder(String itemID) {

        currentOrderItems.add(itemID);

    }

    // Removes one Instance of this item from the order
    public static void removeItemIDFromOrder(String itemID) {

        if(currentOrderItems.contains(itemID)) {
            currentOrderItems.remove(itemID);
        }

    }

    public static String getItemSummary() {

        return itemSummary;
    }

    public static void removeItemFromSummary(String productID) {

        String summary = "";

        int indexToRemove = -1;

        for(int i = 0; i < currentOrderItems.size(); i++) {
            
            String currID = currentOrderItems.get(i);
            
            if(currID.equals(productID)) {
                 indexToRemove = i;
                 break;
            }
        }

        if(indexToRemove >= 0) {
            currentOrderSummaryStrings.remove(indexToRemove);
        }

        // Refill summary
        for(String curr: currentOrderSummaryStrings) {
            summary += curr;
        }

        // Update item summary
        itemSummary = summary;
    }

    public static void appendToItemSummary(String newItem) {
        itemSummary += newItem;
    }

    public static void clearOrderItems() {
        currentOrderItems.clear();
    }

    public static void clearOrderPrice() {

        currentOrderTotalPrice.clear();

        currentOrderTotalPrice.add(0.0);
    }

    public static void clearOrderStrings() {

        itemSummary = "";
    }

    public static void clearOrder() {

        clearOrderItems();

        clearOrderPrice();

        clearOrderStrings();

        // Update price string with 0.00 for total
        updatePriceString();

        // Increment orderID
        orderID++;
    }

    public static int getOrderID() {
        return orderID;
    }

    public static void writeOrderToFileAndDBAndUpdateInv() {

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

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime now = LocalDateTime.now();

        Double orderPrice = 0.0;

        if (currentOrderTotalPrice.size() > 0) {

            orderPrice = currentOrderTotalPrice.get(0);
        }

        String sqlStatement = "";
        ArrayList<String> sqlStatements = new ArrayList<String>();

        try {
            FileWriter myWriter = new FileWriter("Orders.csv", true);

            for (String itemID : currentOrderItems) {

                String itemInOrder = Integer.toString(orderID) + "," + (dtf.format(now)).toString() + ","
                        + itemID + "," + Double.toString(orderPrice) + "\n";

                sqlStatement = "INSERT INTO ordersLog VALUES (" + orderEntryID + ",\'" + Integer.toString(orderID) + "\',\'" + (dtf.format(now)).toString() + "\',\'" + itemID + "\'," + orderPrice + ")";

                orderEntryID++;

                sqlStatements.add(sqlStatement);

                myWriter.write(itemInOrder);
            }

            Statement stmt = conn.createStatement();

            // Write all statements to DB
            for(String currentOrder: sqlStatements) {
                stmt.executeUpdate(currentOrder);
            }

            // Close writer
            myWriter.close();

            // Now update inventory counts in DB
            ArrayList<String> updateInventoryCommands = new ArrayList<String>();

            // Loop through all menu items in current order
            // itemID stores the menu Item (ex: 5 finger meal)
            for (String itemID : currentOrderItems) {

                String invCommand = "SELECT productid, quantofunderlyingprod FROM sellableinvorderrelation WHERE itemID=\'"
                        + itemID + "\'";

                updateInventoryCommands.add(invCommand);

            }

            for (String currentItemCommand : updateInventoryCommands) {
                ResultSet result = stmt.executeQuery(currentItemCommand);

                HashMap<Integer, Integer> prodQuants = new HashMap<Integer, Integer>();

                while (result.next()) {
                    Integer productID = result.getInt("productID");
                    Integer quantityOfProdInMenuItem = result.getInt("quantofunderlyingprod");

                    prodQuants.put(productID, quantityOfProdInMenuItem);
                }

                HashMap<String, Double> amountToDeductFromSKU = new HashMap<String, Double>();

                for (Map.Entry<Integer, Integer> entry : prodQuants.entrySet()) {
                    Integer productID = entry.getKey();
                    Integer quantityOfProd = entry.getValue();

                    String productCommand = "SELECT sku, amountofbaseproduct FROM salestoinventoryconversion WHERE productID=\'"
                            + Integer.toString(productID) + "\'";

                    ResultSet resultForThisProduct = stmt.executeQuery(productCommand);

                    // Lists all of the components used in making an item (ex: salt and flour in
                    // chicken tender)
                    while (resultForThisProduct.next()) {
                        String sku = resultForThisProduct.getString("sku");
                        Double amountOfBaseProduct = resultForThisProduct.getDouble("amountofbaseproduct");

                        final int NUM_TYPES_BOTTLES = 4;
                        final int NUM_TYPES_BIB = 6;

                        if (sku.equals("d2019")) {
                            // Randomly set sku to any one of the bottled drinks
                            int randomNum = ThreadLocalRandom.current().nextInt(1, NUM_TYPES_BOTTLES + 1);

                            if (randomNum == 1) {
                                sku = "d2020";
                            } else if (randomNum == 2) {
                                sku = "d2021";
                            } else if (randomNum == 3) {
                                sku = "d2022";
                            } else {
                                // do nothing, keep sku the same
                            }

                        } else if (sku.equals("d2013")) {// Then do fountain drink next
                            // Randomly set sku to any one of the fountain drinks
                            int randomNum = ThreadLocalRandom.current().nextInt(1, NUM_TYPES_BIB + 1);

                            if (randomNum == 1) {
                                sku = "d2014";
                            } else if (randomNum == 2) {
                                sku = "d2015";
                            } else if (randomNum == 3) {
                                sku = "d2016";
                            } else if (randomNum == 4) {
                                sku = "d2017";
                            } else if (randomNum == 5) {
                                sku = "d2018";
                            } else {
                                // do nothing, keep sku the same
                            }
                        }

                        // Amount to reduce from sellable inventory
                        Double quantityToDeduct = amountOfBaseProduct * quantityOfProd;
                        amountToDeductFromSKU.put(sku, quantityToDeduct);
                    }

                    for (Map.Entry<String, Double> product : amountToDeductFromSKU.entrySet()) {
                        String sku = product.getKey();
                        Double amountToDeduct = product.getValue();

                        if(sku.contains("c1001") || sku.contains("i1001")) {
                            
                            // Get quantity
                            // If quantity - amount to deduct <= 0
                            String getQuant = "SELECT quantity FROM sellableInventory WHERE productID = \'" + sku + "\'";

                            ResultSet rs = stmt.executeQuery(getQuant);

                            Double quantity = -1.0;

                            while(rs.next()) {
                                quantity = rs.getDouble("quantity");
                            }

                            if(quantity - amountToDeduct <= 0) {
                                
                                Double remainder = amountToDeduct - quantity;
                                Integer one = 1;
                                Double maxQuantity = 21.0;

                                // So set quantity to 21 - remainder
                                String updateCommand = "UPDATE sellableInventory SET quantity = " + maxQuantity + " - " + remainder
                                    + " WHERE productID = \'" + sku + "\'";
                                    
                                String updateMultiplierCommand = "UPDATE sellableInventory SET quantityMultiplier = quantityMultiplier - " + one + " WHERE productID = \'" + sku + "\'";

                                stmt.executeUpdate(updateCommand);
                                stmt.executeUpdate(updateMultiplierCommand);

                            } else {
                                String updateCommand = "UPDATE sellableInventory SET quantity = quantity - " + amountToDeduct
                                    + " WHERE productID = \'" + sku + "\'";

                                stmt.executeUpdate(updateCommand);

                            }

                        } else {
                            String updateCommand = "UPDATE sellableInventory SET quantity = quantity - " + amountToDeduct
                                    + " WHERE productID = \'" + sku + "\'";

                            int finalResult = stmt.executeUpdate(updateCommand);

                            if (finalResult == 0) {
                                System.out.println("No changes made to DB");
                            }
                        }
                    }
                }
            }

            /* Update Daily Sales Table */
            DateTimeFormatter DBformat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String nowAsStr = DBformat.format(now);

            String dailySalesEntryIDSQLCommand = "SELECT * FROM dailySales ORDER BY cast(entryID AS INTEGER) DESC LIMIT 1";

            ResultSet newSet = stmt.executeQuery(dailySalesEntryIDSQLCommand);

            Integer prevEntryIDDailySales = -1;

            while (newSet.next()) {
                prevEntryIDDailySales = newSet.getInt("entryID");
            }

            dailySalesEntryID = prevEntryIDDailySales + 1;

            HashMap<Integer, String> mealNames = PeepDB.getNames();

            HashMap<String, String> itemPrices = PeepDB.getPrices();

            // Query for week 5 any item, if empty, add week 5 day 1 (today)
            String week5Query = "select 1 from dailySales where date = \'" + nowAsStr + "\' limit 1"; 
            ResultSet week5 = stmt.executeQuery(week5Query);

            // No week 5 entry -> Populate it
            if(!week5.next()) {

                ArrayList<String> initCommands = new ArrayList<String>();

                for (Map.Entry<Integer, String> entry : mealNames.entrySet()) {
                    Integer productID = entry.getKey();
                    Integer quantity = 0;
                    Double total = 0.0;
                    String entryDate = nowAsStr;

                    String currentCommand = "INSERT INTO dailysales VALUES ('" + dailySalesEntryID + "', '" + Integer.toString(productID) + "', "+ quantity + ", " + total + ", \'" + entryDate.replaceAll("/", "-") + "\')";

                    initCommands.add(currentCommand);
                   
                    dailySalesEntryID++;
                }
                
                for(String currentCommand: initCommands) {
                    stmt.executeUpdate(currentCommand);
                }

            }  

            // Update daily sales with new quantities

            for(int i = 0; i < currentOrderItems.size(); i++) {

                String currentItemID = currentOrderItems.get(i);
                Double currentPrice = Double.parseDouble(itemPrices.get(currentItemID));
                String currentDate = nowAsStr.replaceAll("/", "-");

                String updateQuantCommand = "UPDATE dailySales SET quantity = quantity + 1 WHERE itemID = \'" + currentItemID + "\' AND date = \'" + currentDate + "\'";

                String updateTotalCommand = "UPDATE dailySales SET total = total + " + currentPrice + " WHERE itemID = \'" + currentItemID + "\' AND date = \'" + currentDate + "\'";

                // Update DB
                stmt.executeUpdate(updateQuantCommand);
                stmt.executeUpdate(updateTotalCommand);
            }

        } catch (Exception e) {
            System.out.println("Error writing orders to file or DB");
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
