import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.sql.*;
import java.text.DecimalFormat;

public class popSellableInvOrderRelation {
    public static void main(String[] args) throws Exception {
        ArrayList<String> sqlCommandList = new ArrayList<String>();

        int entryID = 0;
        int itemID = 501;
        int productID = 0;
        int quantityOfUnderlyingProd = 0;
        Double menuPrice = 0.0;

        // Menu Item 501

        // Chicken tenders
        entryID = 0;
        productID = 1;
        quantityOfUnderlyingProd = 5;
        menuPrice = 6.50;

        String sqlStatement0 = "INSERT INTO sellableInvOrderRelation VALUES (" + entryID + ", " + itemID + ", "
                + productID + ", " + quantityOfUnderlyingProd + ", " + menuPrice + ")";

        sqlCommandList.add(sqlStatement0);

        // Toast
        entryID = 1;
        productID = 2;
        quantityOfUnderlyingProd = 1;
        menuPrice = 6.50;

        String sqlStatement1 = "INSERT INTO sellableInvOrderRelation VALUES (" + entryID + ", " + itemID + ", "
                + productID + ", " + quantityOfUnderlyingProd + ", " + menuPrice + ")";

        sqlCommandList.add(sqlStatement1);

        // Fries
        entryID = 2;
        productID = 3;
        quantityOfUnderlyingProd = 1;
        menuPrice = 6.50;

        String sqlStatement2 = "INSERT INTO sellableInvOrderRelation VALUES (" + entryID + ", " + itemID + ", "
                + productID + ", " + quantityOfUnderlyingProd + ", " + menuPrice + ")";

        sqlCommandList.add(sqlStatement2);

        // Potato Salad
        entryID = 3;
        productID = 4;
        quantityOfUnderlyingProd = 1;
        menuPrice = 6.50;

        String sqlStatement3 = "INSERT INTO sellableInvOrderRelation VALUES (" + entryID + ", " + itemID + ", "
                + productID + ", " + quantityOfUnderlyingProd + ", " + menuPrice + ")";

        sqlCommandList.add(sqlStatement3);

        // Sauce
        entryID = 4;
        productID = 5;
        quantityOfUnderlyingProd = 1;
        menuPrice = 6.50;

        String sqlStatement4 = "INSERT INTO sellableInvOrderRelation VALUES (" + entryID + ", " + itemID + ", "
                + productID + ", " + quantityOfUnderlyingProd + ", " + menuPrice + ")";

        sqlCommandList.add(sqlStatement4);

        // Drink (16oz)
        entryID = 5;
        productID = 6;
        quantityOfUnderlyingProd = 1;
        menuPrice = 6.50;

        String sqlStatement5 = "INSERT INTO sellableInvOrderRelation VALUES (" + entryID + ", " + itemID + ", "
                + productID + ", " + quantityOfUnderlyingProd + ", " + menuPrice + ")";

        sqlCommandList.add(sqlStatement5);

        /* Menu Item 502 */

        itemID = 502;

        // Chicken tenders
        entryID = 6;
        productID = 1;
        quantityOfUnderlyingProd = 4;
        menuPrice = 5.50;

        String sqlStatement6 = "INSERT INTO sellableInvOrderRelation VALUES (" + entryID + ", " + itemID + ", "
                + productID
                + ", " + quantityOfUnderlyingProd + ", " + menuPrice + ")";

        sqlCommandList.add(sqlStatement6);

        // Toast
        entryID = 7;
        productID = 2;
        quantityOfUnderlyingProd = 1;
        menuPrice = 5.50;

        String sqlStatement7 = "INSERT INTO sellableInvOrderRelation VALUES (" + entryID + ", " + itemID + ", "
                + productID
                + ", " + quantityOfUnderlyingProd + ", " + menuPrice + ")";

        sqlCommandList.add(sqlStatement7);

        // Fries
        entryID = 8;
        productID = 3;
        quantityOfUnderlyingProd = 1;
        menuPrice = 5.50;

        String sqlStatement8 = "INSERT INTO sellableInvOrderRelation VALUES (" + entryID + ", " + itemID + ", "
                + productID
                + ", " + quantityOfUnderlyingProd + ", " + menuPrice + ")";

        sqlCommandList.add(sqlStatement8);

        // Potato Salad
        entryID = 9;
        productID = 4;
        quantityOfUnderlyingProd = 1;
        menuPrice = 5.50;

        String sqlStatement9 = "INSERT INTO sellableInvOrderRelation VALUES (" + entryID + ", " + itemID + ", "
                + productID
                + ", " + quantityOfUnderlyingProd + ", " + menuPrice + ")";

        sqlCommandList.add(sqlStatement9);

        // Sauce
        entryID = 10;
        productID = 5;
        quantityOfUnderlyingProd = 1;
        menuPrice = 5.50;

        String sqlStatement10 = "INSERT INTO sellableInvOrderRelation VALUES (" + entryID + ", " + itemID + ", "
                + productID
                + ", " + quantityOfUnderlyingProd + ", " + menuPrice + ")";

        sqlCommandList.add(sqlStatement10);

        // Drink (16oz)
        entryID = 11;
        productID = 6;
        quantityOfUnderlyingProd = 1;
        menuPrice = 5.50;

        String sqlStatement11 = "INSERT INTO sellableInvOrderRelation VALUES (" + entryID + ", " + itemID + ", "
                + productID
                + ", " + quantityOfUnderlyingProd + ", " + menuPrice + ")";

        sqlCommandList.add(sqlStatement11);


        /* Menu Item 503 */

        itemID = 503;

        // Chicken tenders
        entryID = 12;
        productID = 1;
        quantityOfUnderlyingProd = 3;
        menuPrice = 4.50;

        String sqlStatement12 = "INSERT INTO sellableInvOrderRelation VALUES (" + entryID + ", " + itemID + ", "
                + productID
                + ", " + quantityOfUnderlyingProd + ", " + menuPrice + ")";

        sqlCommandList.add(sqlStatement12);

        // Toast
        entryID = 13;
        productID = 2;
        quantityOfUnderlyingProd = 1;
        menuPrice = 4.50;

        String sqlStatement13 = "INSERT INTO sellableInvOrderRelation VALUES (" + entryID + ", " + itemID + ", "
                + productID
                + ", " + quantityOfUnderlyingProd + ", " + menuPrice + ")";

        sqlCommandList.add(sqlStatement13);

        // Fries
        entryID = 14;
        productID = 3;
        quantityOfUnderlyingProd = 1;
        menuPrice = 4.50;

        String sqlStatement14 = "INSERT INTO sellableInvOrderRelation VALUES (" + entryID + ", " + itemID + ", "
                + productID
                + ", " + quantityOfUnderlyingProd + ", " + menuPrice + ")";

        sqlCommandList.add(sqlStatement14);

        // Potato Salad
        entryID = 15;
        productID = 4;
        quantityOfUnderlyingProd = 1;
        menuPrice = 4.50;

        String sqlStatement15 = "INSERT INTO sellableInvOrderRelation VALUES (" + entryID + ", " + itemID + ", "
                + productID
                + ", " + quantityOfUnderlyingProd + ", " + menuPrice + ")";

        sqlCommandList.add(sqlStatement15);

        // Sauce
        entryID = 16;
        productID = 5;
        quantityOfUnderlyingProd = 1;
        menuPrice = 4.50;

        String sqlStatement16 = "INSERT INTO sellableInvOrderRelation VALUES (" + entryID + ", " + itemID + ", "
                + productID
                + ", " + quantityOfUnderlyingProd + ", " + menuPrice + ")";

        sqlCommandList.add(sqlStatement16);

        // Drink (16oz)
        entryID = 17;
        productID = 6;
        quantityOfUnderlyingProd = 1;
        menuPrice = 4.50;

        String sqlStatement17 = "INSERT INTO sellableInvOrderRelation VALUES (" + entryID + ", " + itemID + ", "
                + productID
                + ", " + quantityOfUnderlyingProd + ", " + menuPrice + ")";

        sqlCommandList.add(sqlStatement17);

        
        /* Menu Item 504 */

        itemID = 504;

        // Chicken tenders
        entryID = 18;
        productID = 1;
        quantityOfUnderlyingProd = 2;
        menuPrice = 2.50;

        String sqlStatement18 = "INSERT INTO sellableInvOrderRelation VALUES (" + entryID + ", " + itemID + ", "
                + productID
                + ", " + quantityOfUnderlyingProd + ", " + menuPrice + ")";

        sqlCommandList.add(sqlStatement18);

        // Toast
        entryID = 19;
        productID = 2;
        quantityOfUnderlyingProd = 1;
        menuPrice = 2.50;

        String sqlStatement19 = "INSERT INTO sellableInvOrderRelation VALUES (" + entryID + ", " + itemID + ", "
                + productID
                + ", " + quantityOfUnderlyingProd + ", " + menuPrice + ")";

        sqlCommandList.add(sqlStatement19);

        // Fries
        entryID = 20;
        productID = 3;
        quantityOfUnderlyingProd = 1;
        menuPrice = 2.50;

        String sqlStatement20 = "INSERT INTO sellableInvOrderRelation VALUES (" + entryID + ", " + itemID + ", "
                + productID
                + ", " + quantityOfUnderlyingProd + ", " + menuPrice + ")";

        sqlCommandList.add(sqlStatement20);

        // Potato Salad
        entryID = 21;
        productID = 4;
        quantityOfUnderlyingProd = 1;
        menuPrice = 2.50;

        String sqlStatement21 = "INSERT INTO sellableInvOrderRelation VALUES (" + entryID + ", " + itemID + ", "
                + productID
                + ", " + quantityOfUnderlyingProd + ", " + menuPrice + ")";

        sqlCommandList.add(sqlStatement21);

        // Sauce
        entryID = 22;
        productID = 5;
        quantityOfUnderlyingProd = 1;
        menuPrice = 2.50;

        String sqlStatement22 = "INSERT INTO sellableInvOrderRelation VALUES (" + entryID + ", " + itemID + ", "
                + productID
                + ", " + quantityOfUnderlyingProd + ", " + menuPrice + ")";

        sqlCommandList.add(sqlStatement22);

        // Drink (10oz)
        entryID = 23;
        productID = 7;
        quantityOfUnderlyingProd = 1;
        menuPrice = 2.50;

        String sqlStatement23 = "INSERT INTO sellableInvOrderRelation VALUES (" + entryID + ", " + itemID + ", "
                + productID
                + ", " + quantityOfUnderlyingProd + ", " + menuPrice + ")";

        sqlCommandList.add(sqlStatement23);

        
        /* Menu Item 505 */

        itemID = 505;

        // Gallon of Tea
        entryID = 24;
        productID = 8;
        quantityOfUnderlyingProd = 1;
        menuPrice = 5.00;

        String sqlStatement24 = "INSERT INTO sellableInvOrderRelation VALUES (" + entryID + ", " + itemID + ", "
                + productID
                + ", " + quantityOfUnderlyingProd + ", " + menuPrice + ")";

        sqlCommandList.add(sqlStatement24);

        
        /* Menu Item 506 */

        itemID = 506;

        // Chicken tenders
        entryID = 25;
        productID = 1;
        quantityOfUnderlyingProd = 20;
        menuPrice = 32.00;

        String sqlStatement25 = "INSERT INTO sellableInvOrderRelation VALUES (" + entryID + ", " + itemID + ", "
                + productID
                + ", " + quantityOfUnderlyingProd + ", " + menuPrice + ")";

        sqlCommandList.add(sqlStatement25);

        // Toast
        entryID = 26;
        productID = 2;
        quantityOfUnderlyingProd = 4;
        menuPrice = 32.00;

        String sqlStatement26 = "INSERT INTO sellableInvOrderRelation VALUES (" + entryID + ", " + itemID + ", "
                + productID
                + ", " + quantityOfUnderlyingProd + ", " + menuPrice + ")";

        sqlCommandList.add(sqlStatement26);

        // Fries
        entryID = 27;
        productID = 3;
        quantityOfUnderlyingProd = 4;
        menuPrice = 32.00;

        String sqlStatement27 = "INSERT INTO sellableInvOrderRelation VALUES (" + entryID + ", " + itemID + ", "
                + productID
                + ", " + quantityOfUnderlyingProd + ", " + menuPrice + ")";

        sqlCommandList.add(sqlStatement27);

        // Sauce
        entryID = 28;
        productID = 5;
        quantityOfUnderlyingProd = 8;
        menuPrice = 32.00;

        String sqlStatement28 = "INSERT INTO sellableInvOrderRelation VALUES (" + entryID + ", " + itemID + ", "
                + productID
                + ", " + quantityOfUnderlyingProd + ", " + menuPrice + ")";

        sqlCommandList.add(sqlStatement28);

        
        /* Menu Item 507 */

        itemID = 507;

        // Club Sandwich
        entryID = 29;
        productID = 9;
        quantityOfUnderlyingProd = 1;
        menuPrice = 7.50;

        String sqlStatement29 = "INSERT INTO sellableInvOrderRelation VALUES (" + entryID + ", " + itemID + ", "
                + productID
                + ", " + quantityOfUnderlyingProd + ", " + menuPrice + ")";

        sqlCommandList.add(sqlStatement29);

        // Toast
        entryID = 30;
        productID = 2;
        quantityOfUnderlyingProd = 1;
        menuPrice = 7.50;

        String sqlStatement30 = "INSERT INTO sellableInvOrderRelation VALUES (" + entryID + ", " + itemID + ", "
                + productID
                + ", " + quantityOfUnderlyingProd + ", " + menuPrice + ")";

        sqlCommandList.add(sqlStatement30);

        // Potato Salad
        entryID = 31;
        productID = 4;
        quantityOfUnderlyingProd = 1;
        menuPrice = 7.50;

        String sqlStatement31 = "INSERT INTO sellableInvOrderRelation VALUES (" + entryID + ", " + itemID + ", "
                + productID
                + ", " + quantityOfUnderlyingProd + ", " + menuPrice + ")";

        sqlCommandList.add(sqlStatement31);

        // Sauce
        entryID = 32;
        productID = 5;
        quantityOfUnderlyingProd = 1;
        menuPrice = 7.50;

        String sqlStatement32 = "INSERT INTO sellableInvOrderRelation VALUES (" + entryID + ", " + itemID + ", "
                + productID
                + ", " + quantityOfUnderlyingProd + ", " + menuPrice + ")";

        sqlCommandList.add(sqlStatement32);

        // Drink (16oz)
        entryID = 33;
        productID = 6;
        quantityOfUnderlyingProd = 1;
        menuPrice = 7.50;

        String sqlStatement33 = "INSERT INTO sellableInvOrderRelation VALUES (" + entryID + ", " + itemID + ", "
                + productID
                + ", " + quantityOfUnderlyingProd + ", " + menuPrice + ")";

        sqlCommandList.add(sqlStatement33);

        
        /* Menu Item 508 */

        itemID = 508;

        // Club Sandwich
        entryID = 34;
        productID = 9;
        quantityOfUnderlyingProd = 1;
        menuPrice = 4.75;

        String sqlStatement34 = "INSERT INTO sellableInvOrderRelation VALUES (" + entryID + ", " + itemID + ", "
                + productID
                + ", " + quantityOfUnderlyingProd + ", " + menuPrice + ")";

        sqlCommandList.add(sqlStatement34);

        
        /* Menu Item 509 */

        itemID = 509;

        // Sandwich
        entryID = 35;
        productID = 10;
        quantityOfUnderlyingProd = 1;
        menuPrice = 5.75;

        String sqlStatement35 = "INSERT INTO sellableInvOrderRelation VALUES (" + entryID + ", " + itemID + ", "
                + productID
                + ", " + quantityOfUnderlyingProd + ", " + menuPrice + ")";

        sqlCommandList.add(sqlStatement35);

        // Toast
        entryID = 36;
        productID = 2;
        quantityOfUnderlyingProd = 1;
        menuPrice = 5.75;

        String sqlStatement36 = "INSERT INTO sellableInvOrderRelation VALUES (" + entryID + ", " + itemID + ", "
                + productID
                + ", " + quantityOfUnderlyingProd + ", " + menuPrice + ")";

        sqlCommandList.add(sqlStatement36);

        // Fries
        entryID = 37;
        productID = 3;
        quantityOfUnderlyingProd = 1;
        menuPrice = 5.75;

        String sqlStatement37 = "INSERT INTO sellableInvOrderRelation VALUES (" + entryID + ", " + itemID + ", "
                + productID
                + ", " + quantityOfUnderlyingProd + ", " + menuPrice + ")";

        sqlCommandList.add(sqlStatement37);
        // Potato Salad
        entryID = 38;
        productID = 4;
        quantityOfUnderlyingProd = 1;
        menuPrice = 5.75;

        String sqlStatement38 = "INSERT INTO sellableInvOrderRelation VALUES (" + entryID + ", " + itemID + ", "
                + productID
                + ", " + quantityOfUnderlyingProd + ", " + menuPrice + ")";

        sqlCommandList.add(sqlStatement38);

        // Sauce
        entryID = 39;
        productID = 5;
        quantityOfUnderlyingProd = 1;
        menuPrice = 5.75;

        String sqlStatement39 = "INSERT INTO sellableInvOrderRelation VALUES (" + entryID + ", " + itemID + ", "
                + productID
                + ", " + quantityOfUnderlyingProd + ", " + menuPrice + ")";

        sqlCommandList.add(sqlStatement39);

        // Drink (16oz)
        entryID = 40;
        productID = 6;
        quantityOfUnderlyingProd = 1;
        menuPrice = 5.75;

        String sqlStatement40 = "INSERT INTO sellableInvOrderRelation VALUES (" + entryID + ", " + itemID + ", "
                + productID
                + ", " + quantityOfUnderlyingProd + ", " + menuPrice + ")";

        sqlCommandList.add(sqlStatement40);

        
        /* Menu Item 510 */

        itemID = 510;

        // Sandwich
        entryID = 41;
        productID = 10;
        quantityOfUnderlyingProd = 1;
        menuPrice = 3.75;

        String sqlStatement41 = "INSERT INTO sellableInvOrderRelation VALUES (" + entryID + ", " + itemID + ", "
                + productID
                + ", " + quantityOfUnderlyingProd + ", " + menuPrice + ")";

        sqlCommandList.add(sqlStatement41);

        
        /* Menu Item 511 */

        itemID = 511;

        // Grilled Cheese
        entryID = 42;
        productID = 11;
        quantityOfUnderlyingProd = 1;
        menuPrice = 4.50;

        String sqlStatement42 = "INSERT INTO sellableInvOrderRelation VALUES (" + entryID + ", " + itemID + ", "
                + productID
                + ", " + quantityOfUnderlyingProd + ", " + menuPrice + ")";

        sqlCommandList.add(sqlStatement42);

        // Toast
        entryID = 43;
        productID = 2;
        quantityOfUnderlyingProd = 1;
        menuPrice = 4.50;

        String sqlStatement43 = "INSERT INTO sellableInvOrderRelation VALUES (" + entryID + ", " + itemID + ", "
                + productID
                + ", " + quantityOfUnderlyingProd + ", " + menuPrice + ")";

        sqlCommandList.add(sqlStatement43);

        // Fries
        entryID = 44;
        productID = 3;
        quantityOfUnderlyingProd = 1;
        menuPrice = 4.50;

        String sqlStatement44 = "INSERT INTO sellableInvOrderRelation VALUES (" + entryID + ", " + itemID + ", "
                + productID
                + ", " + quantityOfUnderlyingProd + ", " + menuPrice + ")";

        sqlCommandList.add(sqlStatement44);

        // Potato Salad
        entryID = 45;
        productID = 4;
        quantityOfUnderlyingProd = 1;
        menuPrice = 4.50;

        String sqlStatement45 = "INSERT INTO sellableInvOrderRelation VALUES (" + entryID + ", " + itemID + ", "
                + productID
                + ", " + quantityOfUnderlyingProd + ", " + menuPrice + ")";

        sqlCommandList.add(sqlStatement45);

        // Sauce
        entryID = 46;
        productID = 5;
        quantityOfUnderlyingProd = 1;
        menuPrice = 4.50;

        String sqlStatement46 = "INSERT INTO sellableInvOrderRelation VALUES (" + entryID + ", " + itemID + ", "
                + productID
                + ", " + quantityOfUnderlyingProd + ", " + menuPrice + ")";

        sqlCommandList.add(sqlStatement46);

        // Drink (16oz)
        entryID = 47;
        productID = 6;
        quantityOfUnderlyingProd = 1;
        menuPrice = 4.50;

        String sqlStatement47 = "INSERT INTO sellableInvOrderRelation VALUES (" + entryID + ", " + itemID + ", "
                + productID
                + ", " + quantityOfUnderlyingProd + ", " + menuPrice + ")";

        sqlCommandList.add(sqlStatement47);

        
        /* Menu Item 512 */

        itemID = 512;

        // Grilled Cheese
        entryID = 48;
        productID = 11;
        quantityOfUnderlyingProd = 1;
        menuPrice = 3.50;

        String sqlStatement48 = "INSERT INTO sellableInvOrderRelation VALUES (" + entryID + ", " + itemID + ", "
                + productID
                + ", " + quantityOfUnderlyingProd + ", " + menuPrice + ")";

        sqlCommandList.add(sqlStatement48);

        /* Menu Item 513 */

        itemID = 513;

        // Sauce
        entryID = 49;
        productID = 5;
        quantityOfUnderlyingProd = 1;
        menuPrice = 0.10;

        String sqlStatement49 = "INSERT INTO sellableInvOrderRelation VALUES (" + entryID + ", " + itemID + ", "
                + productID
                + ", " + quantityOfUnderlyingProd + ", " + menuPrice + ")";

        sqlCommandList.add(sqlStatement49);

        
        /* Menu Item 514 */

        itemID = 514;

        // Chicken Finger
        entryID = 50;
        productID = 1;
        quantityOfUnderlyingProd = 1;
        menuPrice = 1.50;

        String sqlStatement50 = "INSERT INTO sellableInvOrderRelation VALUES (" + entryID + ", " + itemID + ", "
                + productID
                + ", " + quantityOfUnderlyingProd + ", " + menuPrice + ")";

        sqlCommandList.add(sqlStatement50);

        
        /* Menu Item 515 */

        itemID = 515;

        // Toast
        entryID = 51;
        productID = 2;
        quantityOfUnderlyingProd = 1;
        menuPrice = 1.50;

        String sqlStatement51 = "INSERT INTO sellableInvOrderRelation VALUES (" + entryID + ", " + itemID + ", "
                + productID
                + ", " + quantityOfUnderlyingProd + ", " + menuPrice + ")";

        sqlCommandList.add(sqlStatement51);

        
        /* Menu Item 516 */

        itemID = 516;

        // Potato Salad
        entryID = 52;
        productID = 4;
        quantityOfUnderlyingProd = 1;
        menuPrice = 1.50;

        String sqlStatement52 = "INSERT INTO sellableInvOrderRelation VALUES (" + entryID + ", " + itemID + ", "
                + productID
                + ", " + quantityOfUnderlyingProd + ", " + menuPrice + ")";

        sqlCommandList.add(sqlStatement52);

        
        /* Menu Item 517 */

        itemID = 517;

        // Fries
        entryID = 53;
        productID = 3;
        quantityOfUnderlyingProd = 1;
        menuPrice = 1.75;

        String sqlStatement53 = "INSERT INTO sellableInvOrderRelation VALUES (" + entryID + ", " + itemID + ", "
                + productID
                + ", " + quantityOfUnderlyingProd + ", " + menuPrice + ")";

        sqlCommandList.add(sqlStatement53);

        
        /* Menu Item 518 */

        itemID = 518;

        // Drink (16 oz)
        entryID = 54;
        productID = 6;
        quantityOfUnderlyingProd = 1;
        menuPrice = 1.25;

        String sqlStatement54 = "INSERT INTO sellableInvOrderRelation VALUES (" + entryID + ", " + itemID + ", "
                + productID
                + ", " + quantityOfUnderlyingProd + ", " + menuPrice + ")";

        sqlCommandList.add(sqlStatement54);

        
        /* Menu Item 519 */

        itemID = 519;

        // Bottle Drink
        entryID = 55;
        productID = 12;
        quantityOfUnderlyingProd = 1;
        menuPrice = 2.00;

        String sqlStatement55 = "INSERT INTO sellableInvOrderRelation VALUES (" + entryID + ", " + itemID + ", "
                + productID
                + ", " + quantityOfUnderlyingProd + ", " + menuPrice + ")";

        sqlCommandList.add(sqlStatement55);

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

            /*
             * Note: IF you get an error message, try running the
             * command: stmt.executeUpdate("DELETE FROM sellableInvOrderRelation");
             * Before creating the table
             */

            // Execute SQL Commands
            for (String currentCommand : sqlCommandList) {
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
