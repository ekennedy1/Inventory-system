import java.io.*;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Scanner;
import java.sql.*;
import java.text.DecimalFormat;


public class populateDB_Conversion {
    public static void main(String[] args) throws Exception {

        sellableSalesToInventoryConversion();
    }

    public static void sellableSalesToInventoryConversion() {
        int converionID = 0;
        String SKU = "0";
        int productID = 0;
        double amountofBaseProductUnit = 0;

        ArrayList<String> sqlCommands = new ArrayList<String>();

        // Chicken Breasts to chicken tenders
        converionID = 1;
        SKU = "'c1001'";
        productID = 1;

        //1 Chicken tender = (0.004694835680 * 100)% of the entire chicken breast casecase
        amountofBaseProductUnit = 0.004694835680;

        String sqlStatement1 = "INSERT INTO salesToInventoryConversion VALUES (" + converionID + ", " + SKU + ", " + productID
        + ", " + amountofBaseProductUnit + ")";

        sqlCommands.add(sqlStatement1);

        // Flour to chicken tenders
        converionID = 2;
        SKU = "'d2001'";
        productID = 1;
        amountofBaseProductUnit = 0.001111111111;

        String sqlStatement2 = "INSERT INTO salesToInventoryConversion VALUES (" + converionID + ", " + SKU + ", " + productID
        + ", " + amountofBaseProductUnit + ")";

        sqlCommands.add(sqlStatement2);

        // Salt to chicken tenders
        converionID = 3;
        SKU = "'d2002'";
        productID = 1;
        amountofBaseProductUnit = 0.001876172608;

        String sqlStatement3 = "INSERT INTO salesToInventoryConversion VALUES (" + converionID + ", " + SKU + ", " + productID
        + ", " + amountofBaseProductUnit + ")";

        sqlCommands.add(sqlStatement3);

        // Pepper to chicken tenders
        converionID = 4;
        SKU = "'d2003'";
        productID = 1;
        amountofBaseProductUnit = 0.01886792452;

        String sqlStatement4 = "INSERT INTO salesToInventoryConversion VALUES (" + converionID + ", " + SKU + ", " + productID
        + ", " + amountofBaseProductUnit + ")";

        sqlCommands.add(sqlStatement4);

        // Garlic Powder to chicken tenders
        converionID = 5;
        SKU = "'d2005'";
        productID = 1;
        amountofBaseProductUnit = 0.01886792452;

        String sqlStatement5 = "INSERT INTO salesToInventoryConversion VALUES (" + converionID + ", " + SKU + ", " + productID
        + ", " + amountofBaseProductUnit + ")";

        sqlCommands.add(sqlStatement5);

        // Oil to chicken tenders
        converionID = 6;
        SKU = "'d2012'";
        productID = 1;
        amountofBaseProductUnit = 0.002380952381;

        String sqlStatement6 = "INSERT INTO salesToInventoryConversion VALUES (" + converionID + ", " + SKU + ", " + productID
        + ", " + amountofBaseProductUnit + ")";

        sqlCommands.add(sqlStatement6);

        // Bread Cases to Toast
        converionID = 7;
        SKU = "'d2004'";
        productID = 2;
        amountofBaseProductUnit = 0.005882352941;

        String sqlStatement7 = "INSERT INTO salesToInventoryConversion VALUES (" + converionID + ", " + SKU + ", " + productID
        + ", " + amountofBaseProductUnit + ")";

        sqlCommands.add(sqlStatement7);

        // Liquid Margerine to Toast
        converionID = 8;
        SKU = "'c1003'";
        productID = 2;
        amountofBaseProductUnit = 0.005;

        String sqlStatement8 = "INSERT INTO salesToInventoryConversion VALUES (" + converionID + ", " + SKU + ", " + productID
        + ", " + amountofBaseProductUnit + ")";

        sqlCommands.add(sqlStatement8);

        // Garlic Powder to Toast
        converionID = 9;
        SKU = "'d2005'";
        productID = 2;
        amountofBaseProductUnit = 0.01886792452;

        String sqlStatement9 = "INSERT INTO salesToInventoryConversion VALUES (" + converionID + ", " + SKU + ", " + productID
        + ", " + amountofBaseProductUnit + ")";

        sqlCommands.add(sqlStatement9);

        // Cases of Fries to Fries serving
        converionID = 10;
        SKU = "'f3002'";
        productID = 3;
        amountofBaseProductUnit = 0.00208333333;

        String sqlStatement10 = "INSERT INTO salesToInventoryConversion VALUES (" + converionID + ", " + SKU + ", " + productID
        + ", " + amountofBaseProductUnit + ")";

        sqlCommands.add(sqlStatement10);


        // Salt to Fries serving
        converionID = 11;
        SKU = "'d2002'";
        productID = 3;
        amountofBaseProductUnit = 0.001876172608;

        String sqlStatement11 = "INSERT INTO salesToInventoryConversion VALUES (" + converionID + ", " + SKU + ", " + productID
        + ", " + amountofBaseProductUnit + ")";

        sqlCommands.add(sqlStatement11);

        // Oil to Fries serving
        converionID = 12;
        SKU = "'d2012'";
        productID = 3;
        amountofBaseProductUnit = 0.002380952381;

        String sqlStatement12 = "INSERT INTO salesToInventoryConversion VALUES (" + converionID + ", " + SKU + ", " + productID
        + ", " + amountofBaseProductUnit + ")";

        sqlCommands.add(sqlStatement12);

        // Potato Salad case to Potato Salad serving
        converionID = 13;
        SKU = "'c1002'";
        productID = 4;
        //Assumed to be 2oz per serving
        amountofBaseProductUnit = 0.025;

        String sqlStatement13 = "INSERT INTO salesToInventoryConversion VALUES (" + converionID + ", " + SKU + ", " + productID
        + ", " + amountofBaseProductUnit + ")";

        sqlCommands.add(sqlStatement13);

        // Mayo to sauce
        converionID = 14;
        SKU = "'d2008'";
        productID = 5;
        amountofBaseProductUnit = 0.000001;

        String sqlStatement14 = "INSERT INTO salesToInventoryConversion VALUES (" + converionID + ", " + SKU + ", " + productID
        + ", " + amountofBaseProductUnit + ")";

        sqlCommands.add(sqlStatement14);

        // Worcestershire to sauce
        converionID = 15;
        SKU = "'d2011'";
        productID = 5;
        amountofBaseProductUnit = 0.00000001;

        String sqlStatement15 = "INSERT INTO salesToInventoryConversion VALUES (" + converionID + ", " + SKU + ", " + productID
        + ", " + amountofBaseProductUnit + ")";

        sqlCommands.add(sqlStatement15);

        // Ketchup to sauce
        converionID = 16;
        SKU = "'d2006'";
        productID = 5;
        amountofBaseProductUnit = 0.0000001;

        String sqlStatement16 = "INSERT INTO salesToInventoryConversion VALUES (" + converionID + ", " + SKU + ", " + productID
        + ", " + amountofBaseProductUnit + ")";

        sqlCommands.add(sqlStatement16);

        // BIB to Drink (Adult drink)
        converionID = 17;
        SKU = "'d2013'";
        productID = 6;
        amountofBaseProductUnit = 0.04166666666;

        String sqlStatement17 = "INSERT INTO salesToInventoryConversion VALUES (" + converionID + ", " + SKU + ", " + productID
        + ", " + amountofBaseProductUnit + ")";

        sqlCommands.add(sqlStatement17);

        // BIB to Drink (Kids drink)
        converionID = 18;
        SKU = "'d2013'";
        productID = 7;
        amountofBaseProductUnit = 0.02604166666;

        String sqlStatement18 = "INSERT INTO salesToInventoryConversion VALUES (" + converionID + ", " + SKU + ", " + productID
        + ", " + amountofBaseProductUnit + ")";

        sqlCommands.add(sqlStatement18);

        // Tea bags to Tea Gallon
        converionID = 19;
        SKU = "'d2009'";
        productID = 8;
        amountofBaseProductUnit = 0.04166666666;

        String sqlStatement19 = "INSERT INTO salesToInventoryConversion VALUES (" + converionID + ", " + SKU + ", " + productID
        + ", " + amountofBaseProductUnit + ")";

        sqlCommands.add(sqlStatement19);

        // Thick Bread to Club Sandwich
        converionID = 20;
        SKU = "'d2004'";
        productID = 9;
        amountofBaseProductUnit = 0.01176470588;

        String sqlStatement20 = "INSERT INTO salesToInventoryConversion VALUES (" + converionID + ", " + SKU + ", " + productID
        + ", " + amountofBaseProductUnit + ")";

        sqlCommands.add(sqlStatement20);

        // Chick Breasts to Club Sandwich
        converionID = 21;
        SKU = "'c1001'";
        productID = 9;
        amountofBaseProductUnit = 0.01408450704;

        String sqlStatement21 = "INSERT INTO salesToInventoryConversion VALUES (" + converionID + ", " + SKU + ", " + productID
        + ", " + amountofBaseProductUnit + ")";

        sqlCommands.add(sqlStatement21);

        // Bacon to Club Sandwich
        converionID = 22;
        SKU = "'c1006'";
        productID = 9;
        amountofBaseProductUnit = 0.01;

        String sqlStatement22 = "INSERT INTO salesToInventoryConversion VALUES (" + converionID + ", " + SKU + ", " + productID
        + ", " + amountofBaseProductUnit + ")";

        sqlCommands.add(sqlStatement22);

        // Cheese to Club Sandwich
        converionID = 23;
        SKU = "'c1005'";
        productID = 9;
        amountofBaseProductUnit = 0.0125;

        String sqlStatement23 = "INSERT INTO salesToInventoryConversion VALUES (" + converionID + ", " + SKU + ", " + productID
        + ", " + amountofBaseProductUnit + ")";

        sqlCommands.add(sqlStatement23);

        // Thick Bread to Sandwich
        converionID = 24;
        SKU = "'d2004'";
        productID = 10;
        amountofBaseProductUnit = 0.01176470588;

        String sqlStatement24 = "INSERT INTO salesToInventoryConversion VALUES (" + converionID + ", " + SKU + ", " + productID
        + ", " + amountofBaseProductUnit + ")";

        sqlCommands.add(sqlStatement24);

        // Chick Breasts to Sandwich
        converionID = 25;
        SKU = "'c1001'";
        productID = 10;
        amountofBaseProductUnit = 0.01408450704;

        String sqlStatement25 = "INSERT INTO salesToInventoryConversion VALUES (" + converionID + ", " + SKU + ", " + productID
        + ", " + amountofBaseProductUnit + ")";

        sqlCommands.add(sqlStatement25);

        // Thick Bread to Grilled Cheese
        converionID = 26;
        SKU = "'d2004'";
        productID = 11;
        amountofBaseProductUnit = 0.01176470588;

        String sqlStatement26 = "INSERT INTO salesToInventoryConversion VALUES (" + converionID + ", " + SKU + ", " + productID
        + ", " + amountofBaseProductUnit + ")";

        sqlCommands.add(sqlStatement26);

        // Cheese to Grilled Cheese
        converionID = 27;
        SKU = "'c1005'";
        productID = 11;
        amountofBaseProductUnit = 0.0125;

        String sqlStatement27 = "INSERT INTO salesToInventoryConversion VALUES (" + converionID + ", " + SKU + ", " + productID
        + ", " + amountofBaseProductUnit + ")";

        sqlCommands.add(sqlStatement27);

        // Cases of Bottles to bottle serving
        converionID = 28;
        SKU = "'d2019'";
        productID = 12;
        amountofBaseProductUnit = 0.04166666666;

        String sqlStatement28 = "INSERT INTO salesToInventoryConversion VALUES (" + converionID + ", " + SKU + ", " + productID
        + ", " + amountofBaseProductUnit + ")";

        sqlCommands.add(sqlStatement28);

        /* CONVERSIONS FOR THE IMPOSSIBLE BASKET */

        

        // // Chicken Breasts to chicken tenders
        // converionID = 29;
        // SKU = "'c1001'";
        // productID = 1;

        // // 1 Chicken tender = (0.004694835680 * 100)% of the entire chicken breast
        // // casecase
        // amountofBaseProductUnit = 0.004694835680;

        // String sqlStatement1 = "INSERT INTO salesToInventoryConversion VALUES (" + converionID + ", " + SKU + ", "
        //         + productID
        //         + ", " + amountofBaseProductUnit + ")";

        // sqlCommands.add(sqlStatement1);

        // // Flour to chicken tenders
        // converionID = 2;
        // SKU = "'d2001'";
        // productID = 1;
        // amountofBaseProductUnit = 0.001111111111;

        // String sqlStatement2 = "INSERT INTO salesToInventoryConversion VALUES (" + converionID + ", " + SKU + ", "
        //         + productID
        //         + ", " + amountofBaseProductUnit + ")";

        // sqlCommands.add(sqlStatement2);

        // // Salt to chicken tenders
        // converionID = 3;
        // SKU = "'d2002'";
        // productID = 1;
        // amountofBaseProductUnit = 0.001876172608;

        // String sqlStatement3 = "INSERT INTO salesToInventoryConversion VALUES (" + converionID + ", " + SKU + ", "
        //         + productID
        //         + ", " + amountofBaseProductUnit + ")";

        // sqlCommands.add(sqlStatement3);

        // // Pepper to chicken tenders
        // converionID = 4;
        // SKU = "'d2003'";
        // productID = 1;
        // amountofBaseProductUnit = 0.01886792452;

        // String sqlStatement4 = "INSERT INTO salesToInventoryConversion VALUES (" + converionID + ", " + SKU + ", "
        //         + productID
        //         + ", " + amountofBaseProductUnit + ")";

        // sqlCommands.add(sqlStatement4);

        // // Garlic Powder to chicken tenders
        // converionID = 5;
        // SKU = "'d2005'";
        // productID = 1;
        // amountofBaseProductUnit = 0.01886792452;

        // String sqlStatement5 = "INSERT INTO salesToInventoryConversion VALUES (" + converionID + ", " + SKU + ", "
        //         + productID
        //         + ", " + amountofBaseProductUnit + ")";

        // sqlCommands.add(sqlStatement5);

        // // Oil to chicken tenders
        // converionID = 6;
        // SKU = "'d2012'";
        // productID = 1;
        // amountofBaseProductUnit = 0.002380952381;

        // String sqlStatement6 = "INSERT INTO salesToInventoryConversion VALUES (" + converionID + ", " + SKU + ", "
        //         + productID
        //         + ", " + amountofBaseProductUnit + ")";

        // sqlCommands.add(sqlStatement6);
        

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
                
            // Execute SQL Commands
            for (String currentCommand: sqlCommands){
                stmt.executeUpdate(currentCommand);
            }
        }
        catch (Exception e) { // Error handling
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
}