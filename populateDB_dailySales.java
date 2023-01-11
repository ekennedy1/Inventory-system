import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.JOptionPane;

import java.sql.*;
import java.text.DecimalFormat;
import java.time.LocalDate;


/*
Used to populate the database with data
*/
public class populateDB_dailySales {
    public static void main(String[] args) throws Exception {
        
        readWeekSales();
        
    }

    public static void readWeekSales(){

        LocalDate today = LocalDate.now();
        LocalDate startDate = today.minusDays(28);

        try{

            Scanner sc = new Scanner(new File("4_week_data_.csv"));
            
            //Scan the 4 week data
            sc.useDelimiter("\n");

            int itemID = -1;
            int quant = -1;
            int weekID = 0;
            double total = 0;

            //skip the first line of the csv file
            String day = "";
            final DecimalFormat df = new DecimalFormat("0.00");

            int entryID = 0;
            while(sc.hasNext()){
                String curr = sc.next();
                
                if(curr.contains("Order sumary") || curr.contains(",,,,")){
                    //skip the line if it is the start of a new week or the 1st line
                    continue;
                }

                if(curr.contains("Sunday")){
                    weekID++;
                    day = "Sunday";
                    continue;
                }
                else if(curr.contains("Monday")){
                    day = "Monday";
                    continue;
                }
                else if(curr.contains("Tuesday")){
                    day = "Tuesday";
                    continue;
                }
                else if(curr.contains("Wednesday")){
                    day = "Wednesday";
                    continue;
                }
                else if(curr.contains("Thursday")){
                    day = "Thursday";
                    continue;
                }
                else if(curr.contains("Friday")){
                    entryID++;
                    day = "Friday";
                    continue;
                }
                else if(curr.contains("Saturday")){
                    day = "Saturday";
                    continue;
                }

                entryID++;
                String inputLine = curr.substring(1);
                String[] components = inputLine.split(",");//put quanitty and id within array
                
                // //getting item and quantities
                itemID = Integer.parseInt(components[0]);
                quant = Integer.parseInt(components[1]);
                
                total = 0;
                
                //get total money made for each product and its quantity

                // Load Item Prices
                HashMap<String, String> itemPrices = PeepDB.getPrices();

                


                if(itemID == 501){
                    total = Double.parseDouble(itemPrices.get("501")) * quant;
                }
                else if(itemID == 502){
                    total = Double.parseDouble(itemPrices.get("502")) * quant;
                }
                else if(itemID == 503){
                    total = Double.parseDouble(itemPrices.get("503")) * quant;
                }
                else if(itemID == 504){
                    total = Double.parseDouble(itemPrices.get("504")) * quant;
                }
                else if(itemID == 505){
                    total = Double.parseDouble(itemPrices.get("505")) * quant;
                }
                else if(itemID == 506){
                    total = Double.parseDouble(itemPrices.get("506")) * quant;
                }
                else if(itemID == 507){
                    total = Double.parseDouble(itemPrices.get("507")) * quant;
                }
                else if(itemID == 508){
                    total = Double.parseDouble(itemPrices.get("508")) * quant;
                }
                else if(itemID == 509){
                    total = Double.parseDouble(itemPrices.get("509")) * quant;
                }
                else if(itemID == 510){
                    total = Double.parseDouble(itemPrices.get("510")) * quant;
                }
                else if(itemID == 511){
                    total = Double.parseDouble(itemPrices.get("511")) * quant;
                }
                else if(itemID == 512){
                    total = Double.parseDouble(itemPrices.get("512")) * quant;
                }
                else if(itemID == 513){
                    total = Double.parseDouble(itemPrices.get("513")) * quant;
                }
                else if(itemID == 514){
                    total = Double.parseDouble(itemPrices.get("514")) * quant;
                }
                else if(itemID == 515){
                    total = Double.parseDouble(itemPrices.get("515")) * quant;
                }
                else if(itemID == 516){
                    total = Double.parseDouble(itemPrices.get("516")) * quant;
                }
                else if(itemID == 517){
                    total = Double.parseDouble(itemPrices.get("517")) * quant;
                }
                else if(itemID == 518){
                    total = Double.parseDouble(itemPrices.get("518")) * quant;
                }
                else if(itemID == 519){
                    total = Double.parseDouble(itemPrices.get("519")) * quant;
                } else if(itemID == 520) {

                    if(itemPrices.get("520") != null) {
                        total = Double.parseDouble(itemPrices.get("520")) * quant;
                    } else {
                        JOptionPane.showMessageDialog(null, "No item with ID 520 in system currently");
                    }
                }

                LocalDate entryDate = null;

                if(weekID == 1) {
                    if(day.equalsIgnoreCase("sunday")) {
                        entryDate = startDate.plusDays(0);
                    } else if(day.equalsIgnoreCase("monday")) {
                        entryDate = startDate.plusDays(1);
                    } else if(day.equalsIgnoreCase("tuesday")) {
                        entryDate = startDate.plusDays(2);
                    } else if(day.equalsIgnoreCase("wednesday")) {
                        entryDate = startDate.plusDays(3);
                    } else if(day.equalsIgnoreCase("thursday")) {
                        entryDate = startDate.plusDays(4);
                    } else if(day.equalsIgnoreCase("friday")) {
                        entryDate = startDate.plusDays(5);
                    } else if(day.equalsIgnoreCase("saturday")) {
                        entryDate = startDate.plusDays(6);
                    }
                } else if(weekID == 2) {
                    if (day.equalsIgnoreCase("sunday")) {
                        entryDate = startDate.plusDays(7);
                    } else if (day.equalsIgnoreCase("monday")) {
                        entryDate = startDate.plusDays(8);
                    } else if (day.equalsIgnoreCase("tuesday")) {
                        entryDate = startDate.plusDays(9);
                    } else if (day.equalsIgnoreCase("wednesday")) {
                        entryDate = startDate.plusDays(10);
                    } else if (day.equalsIgnoreCase("thursday")) {
                        entryDate = startDate.plusDays(11);
                    } else if (day.equalsIgnoreCase("friday")) {
                        entryDate = startDate.plusDays(12);
                    } else if (day.equalsIgnoreCase("saturday")) {
                        entryDate = startDate.plusDays(13);
                    }
                } else if(weekID == 3) {
                    if (day.equalsIgnoreCase("sunday")) {
                        entryDate = startDate.plusDays(14);
                    } else if (day.equalsIgnoreCase("monday")) {
                        entryDate = startDate.plusDays(15);
                    } else if (day.equalsIgnoreCase("tuesday")) {
                        entryDate = startDate.plusDays(16);
                    } else if (day.equalsIgnoreCase("wednesday")) {
                        entryDate = startDate.plusDays(17);
                    } else if (day.equalsIgnoreCase("thursday")) {
                        entryDate = startDate.plusDays(18);
                    } else if (day.equalsIgnoreCase("friday")) {
                        entryDate = startDate.plusDays(19);
                    } else if (day.equalsIgnoreCase("saturday")) {
                        entryDate = startDate.plusDays(20);
                    }
                } else if(weekID == 4) {
                    if (day.equalsIgnoreCase("sunday")) {
                        entryDate = startDate.plusDays(21);
                    } else if (day.equalsIgnoreCase("monday")) {
                        entryDate = startDate.plusDays(22);
                    } else if (day.equalsIgnoreCase("tuesday")) {
                        entryDate = startDate.plusDays(23);
                    } else if (day.equalsIgnoreCase("wednesday")) {
                        entryDate = startDate.plusDays(24);
                    } else if (day.equalsIgnoreCase("thursday")) {
                        entryDate = startDate.plusDays(25);
                    } else if (day.equalsIgnoreCase("friday")) {
                        entryDate = startDate.plusDays(26);
                    } else if (day.equalsIgnoreCase("saturday")) {
                        entryDate = startDate.plusDays(27);
                    }
                }
                
                //insert into SQL table
                String sqlStatement = "INSERT INTO dailysales VALUES ('" + entryID + "', '" + itemID + "', " + quant + ", " + df.format(total) + ", \'" + entryDate.toString() + "\')";

                //connect to database
                Connection conn = null;
                String teamNumber = "13";
                String sectionNumber = "910";
                String dbName = "csce315" + sectionNumber + "_" + teamNumber + "db";
                String dbConnectionString = "jdbc:postgresql://csce-315-db.engr.tamu.edu/" + dbName;
                String userName = "csce315" + sectionNumber + "_" + teamNumber + "user";
                String userPassword = "table_13";

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

                    if (result < 0){
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
        catch(Exception e){
            e.printStackTrace();
        }
    }

}
