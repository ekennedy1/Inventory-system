import java.io.*;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Scanner;
import java.sql.*;
import java.text.DecimalFormat;

public class populateDB_managerPref {
    public static void main(String[] args) throws Exception {

        managerPreferences();
    }

    public static void managerPreferences() {
        String SKU = "0";
        String name = "0";
        int minFill = 0;

        ArrayList<String> sqlCommands = new ArrayList<String>();
        
        //Set min fill for chicken breasts
        SKU = "'c1001'";
        name = "'Chicken breast'";
        minFill = 10;

        String sqlStatement1 = "INSERT INTO managerPrefernces VALUES (" + SKU + ", " + name
        + ", " + minFill + ")";

        sqlCommands.add(sqlStatement1);


        //Set min fill for flour
        SKU = "'d2001'";
        name = "'flour'";
        minFill = 3;

        String sqlStatement2 = "INSERT INTO managerPrefernces VALUES (" + SKU + ", " + name
        + ", " + minFill + ")";

        sqlCommands.add(sqlStatement2);


        //Set min fill for salt
        SKU = "'d2002'";
        name = "'salt'";
        minFill = 1;

        String sqlStatement3 = "INSERT INTO managerPrefernces VALUES (" + SKU + ", " + name
        + ", " + minFill + ")";

        sqlCommands.add(sqlStatement3);


        //Set min fill for black pepper
        SKU = "'d2003'";
        name = "'black pepper'";
        minFill = 1;

        String sqlStatement4 = "INSERT INTO managerPrefernces VALUES (" + SKU + ", " + name
        + ", " + minFill + ")";

        sqlCommands.add(sqlStatement4);


        //Set min fill for fries
        SKU = "'f3002'";
        name = "'fries'";
        minFill = 15;

        String sqlStatement5 = "INSERT INTO managerPrefernces VALUES (" + SKU + ", " + name
        + ", " + minFill + ")";

        sqlCommands.add(sqlStatement5);


        //Set min fill for Thick bread
        SKU = "'d2004'";
        name = "'Thick Bread'";
        minFill = 5;

        String sqlStatement6 = "INSERT INTO managerPrefernces VALUES (" + SKU + ", " + name
        + ", " + minFill + ")";

        sqlCommands.add(sqlStatement6);


        //Set min fill for Potato Salad
        SKU = "'c1002'";
        name = "'Potato Salad'";
        minFill = 5;

        String sqlStatement7 = "INSERT INTO managerPrefernces VALUES (" + SKU + ", " + name
        + ", " + minFill + ")";

        sqlCommands.add(sqlStatement7);


        //Set min fill for Liquid margarine
        SKU = "'c1003'";
        name = "'Liquid margarine'";
        minFill = 2;

        String sqlStatement8 = "INSERT INTO managerPrefernces VALUES (" + SKU + ", " + name
        + ", " + minFill + ")";

        sqlCommands.add(sqlStatement8);


        //Set min fill for Garlic powder
        SKU = "'d2005'";
        name = "'Garlic powder'";
        minFill = 1;

        String sqlStatement9 = "INSERT INTO managerPrefernces VALUES (" + SKU + ", " + name
        + ", " + minFill + ")";

        sqlCommands.add(sqlStatement9);


        //Set min fill for Ranch
        SKU = "'c1004'";
        name = "'Ranch'";
        minFill = 6;

        String sqlStatement10 = "INSERT INTO managerPrefernces VALUES (" + SKU + ", " + name
        + ", " + minFill + ")";

        sqlCommands.add(sqlStatement10);


        //Set min fill for Ketchup-Lg container
        SKU = "'d2006'";
        name = "'Ketchup-Lg container'";
        minFill = 2;

        String sqlStatement11 = "INSERT INTO managerPrefernces VALUES (" + SKU + ", " + name
        + ", " + minFill + ")";

        sqlCommands.add(sqlStatement11);


        //Set min fill for Ketchup-packets
        SKU = "'d2007'";
        name = "'Ketchup-packets'";
        minFill = 2;

        String sqlStatement12 = "INSERT INTO managerPrefernces VALUES (" + SKU + ", " + name
        + ", " + minFill + ")";

        sqlCommands.add(sqlStatement12);


        //Set min fill for Mayo
        SKU = "'d2008'";
        name = "'Mayo'";
        minFill = 3;

        String sqlStatement13 = "INSERT INTO managerPrefernces VALUES (" + SKU + ", " + name
        + ", " + minFill + ")";

        sqlCommands.add(sqlStatement13);


        //Set min fill for Tea bags
        SKU = "'d2009'";
        name = "'Tea bags'";
        minFill = 2;

        String sqlStatement14 = "INSERT INTO managerPrefernces VALUES (" + SKU + ", " + name
        + ", " + minFill + ")";

        sqlCommands.add(sqlStatement14);


        //Set min fill for Sugar for tea
        SKU = "'d2010'";
        name = "'Sugar for tea'";
        minFill = 2;

        String sqlStatement15 = "INSERT INTO managerPrefernces VALUES (" + SKU + ", " + name
        + ", " + minFill + ")";

        sqlCommands.add(sqlStatement15);


        //Set min fill for Worcestershire Sauce
        SKU = "'d2011'";
        name = "'Worcestershire Sauce'";
        minFill = 1;

        String sqlStatement16 = "INSERT INTO managerPrefernces VALUES (" + SKU + ", " + name
        + ", " + minFill + ")";

        sqlCommands.add(sqlStatement16);


        //Set min fill for Sliced cheese
        SKU = "'c1005'";
        name = "'Sliced cheese'";
        minFill = 1;

        String sqlStatement17 = "INSERT INTO managerPrefernces VALUES (" + SKU + ", " + name
        + ", " + minFill + ")";

        sqlCommands.add(sqlStatement17);


        //Set min fill for Bacon slices
        SKU = "'c1006'";
        name = "'Bacon slices'";
        minFill = 1;

        String sqlStatement18 = "INSERT INTO managerPrefernces VALUES (" + SKU + ", " + name
        + ", " + minFill + ")";

        sqlCommands.add(sqlStatement18);


        //Set min fill for Fryer oil
        SKU = "'d2012'";
        name = "'Fryer oil'";
        minFill = 3;

        String sqlStatement19 = "INSERT INTO managerPrefernces VALUES (" + SKU + ", " + name
        + ", " + minFill + ")";

        sqlCommands.add(sqlStatement19);


        //Set min fill for Jones-Dr Jones 
        SKU = "'d2013'";
        name = "'Jones-Dr Jones'";
        minFill = 1;

        String sqlStatement20 = "INSERT INTO managerPrefernces VALUES (" + SKU + ", " + name
        + ", " + minFill + ")";

        sqlCommands.add(sqlStatement20);


        //Set min fill for Jones-Orange&Cream
        SKU = "'d2014'";
        name = "'Jones-Orange&Cream'";
        minFill = 1;

        String sqlStatement21 = "INSERT INTO managerPrefernces VALUES (" + SKU + ", " + name
        + ", " + minFill + ")";

        sqlCommands.add(sqlStatement21);


        //Set min fill for Jones-Root beer
        SKU = "'d2015'";
        name = "'Jones-Root beer'";
        minFill = 1;

        String sqlStatement22 = "INSERT INTO managerPrefernces VALUES (" + SKU + ", " + name
        + ", " + minFill + ")";

        sqlCommands.add(sqlStatement22);


        //Set min fill for Jones-Cola
        SKU = "'d2016'";
        name = "'Jones-Cola'";
        minFill = 1;

        String sqlStatement23 = "INSERT INTO managerPrefernces VALUES (" + SKU + ", " + name
        + ", " + minFill + ")";

        sqlCommands.add(sqlStatement23);


        //Set min fill for Jones-Lemon Lime
        SKU = "'d2017'";
        name = "'Jones-Lemon Lime'";
        minFill = 1;

        String sqlStatement24 = "INSERT INTO managerPrefernces VALUES (" + SKU + ", " + name
        + ", " + minFill + ")";

        sqlCommands.add(sqlStatement24);


        //Set min fill for Jones-Sugar Free Cola
        SKU = "'d2018'";
        name = "'Jones-Sugar Free Cola'";
        minFill = 1;

        String sqlStatement25 = "INSERT INTO managerPrefernces VALUES (" + SKU + ", " + name
        + ", " + minFill + ")";

        sqlCommands.add(sqlStatement25);


        //Set min fill for Bottled Root Beer
        SKU = "'d2019'";
        name = "'Bottled Root Beer'";
        minFill = 2;

        String sqlStatement26 = "INSERT INTO managerPrefernces VALUES (" + SKU + ", " + name
        + ", " + minFill + ")";

        sqlCommands.add(sqlStatement26);


        //Set min fill for Bottled Cream Soda
        SKU = "'d2020'";
        name = "'Bottled Cream Soda'";
        minFill = 2;

        String sqlStatement27 = "INSERT INTO managerPrefernces VALUES (" + SKU + ", " + name
        + ", " + minFill + ")";

        sqlCommands.add(sqlStatement27);


        //Set min fill for Bottled Orange & Cream
        SKU = "'d2021'";
        name = "'Bottled Orange & Cream'";
        minFill = 2;

        String sqlStatement28 = "INSERT INTO managerPrefernces VALUES (" + SKU + ", " + name
        + ", " + minFill + ")";

        sqlCommands.add(sqlStatement28);


        //Set min fill for Bottled Berry Lemonade
        SKU = "'d2022'";
        name = "'Bottled Berry Lemonade'";
        minFill = 2;

        String sqlStatement29 = "INSERT INTO managerPrefernces VALUES (" + SKU + ", " + name
        + ", " + minFill + ")";

        sqlCommands.add(sqlStatement29);


        //Set min fill for Straw
        SKU = "'d2023'";
        name = "'Straw'";
        minFill = 2;

        String sqlStatement30 = "INSERT INTO managerPrefernces VALUES (" + SKU + ", " + name
        + ", " + minFill + ")";

        sqlCommands.add(sqlStatement30);


        //Set min fill for Drink lid
        SKU = "'d2024'";
        name = "'Drink lid'";
        minFill = 1;

        String sqlStatement31 = "INSERT INTO managerPrefernces VALUES (" + SKU + ", " + name
        + ", " + minFill + ")";

        sqlCommands.add(sqlStatement31);


        //Set min fill for 10oz Cup
        SKU = "'d2025'";
        name = "'10oz Cup'";
        minFill = 1;

        String sqlStatement32 = "INSERT INTO managerPrefernces VALUES (" + SKU + ", " + name
        + ", " + minFill + ")";

        sqlCommands.add(sqlStatement32);


        //Set min fill for 16 oz Cup
        SKU = "'d2026'";
        name = "'16 oz Cup'";
        minFill = 1;

        String sqlStatement33 = "INSERT INTO managerPrefernces VALUES (" + SKU + ", " + name
        + ", " + minFill + ")";

        sqlCommands.add(sqlStatement33);


        //Set min fill for Napkin
        SKU = "'d2027'";
        name = "'Napkin'";
        minFill = 1;

        String sqlStatement34 = "INSERT INTO managerPrefernces VALUES (" + SKU + ", " + name
        + ", " + minFill + ")";

        sqlCommands.add(sqlStatement34);


        //Set min fill for Cutlery pack
        SKU = "'d2028'";
        name = "'Cutlery pack'";
        minFill = 2;

        String sqlStatement35 = "INSERT INTO managerPrefernces VALUES (" + SKU + ", " + name
        + ", " + minFill + ")";

        sqlCommands.add(sqlStatement35);


        //Set min fill for Basket liners
        SKU = "'d2029'";
        name = "'Basket liners'";
        minFill = 1;

        String sqlStatement36 = "INSERT INTO managerPrefernces VALUES (" + SKU + ", " + name
        + ", " + minFill + ")";

        sqlCommands.add(sqlStatement36);


        //Set min fill for 2oz clear cup
        SKU = "'d2030'";
        name = "'2oz clear cup'";
        minFill = 1;

        String sqlStatement37 = "INSERT INTO managerPrefernces VALUES (" + SKU + ", " + name
        + ", " + minFill + ")";

        sqlCommands.add(sqlStatement37);


        //Set min fill for 2oz lid
        SKU = "'d2031'";
        name = "'2oz lid'";
        minFill = 1;

        String sqlStatement38 = "INSERT INTO managerPrefernces VALUES (" + SKU + ", " + name
        + ", " + minFill + ")";

        sqlCommands.add(sqlStatement38);


        //Set min fill for 5.5 oz clear cup
        SKU = "'d2032'";
        name = "'5.5 oz clear cup'";
        minFill = 1;

        String sqlStatement39 = "INSERT INTO managerPrefernces VALUES (" + SKU + ", " + name
        + ", " + minFill + ")";

        sqlCommands.add(sqlStatement39);


        //Set min fill for 5.5 oz lid
        SKU = "'d2033'";
        name = "'5.5 oz lid'";
        minFill = 1;

        String sqlStatement40 = "INSERT INTO managerPrefernces VALUES (" + SKU + ", " + name
        + ", " + minFill + ")";

        sqlCommands.add(sqlStatement40);


        //Set min fill for Hinged lid box sm
        SKU = "'d2034'";
        name = "'Hinged lid box sm'";
        minFill = 2;

        String sqlStatement41 = "INSERT INTO managerPrefernces VALUES (" + SKU + ", " + name
        + ", " + minFill + ")";

        sqlCommands.add(sqlStatement41);


        //Set min fill for Hinged lid box lg
        SKU = "'d2035'";
        name = "'Hinged lid box lg'";
        minFill = 3;

        String sqlStatement42 = "INSERT INTO managerPrefernces VALUES (" + SKU + ", " + name
        + ", " + minFill + ")";

        sqlCommands.add(sqlStatement42);


        //Set min fill for Hand soap
        SKU = "'d2037'";
        name = "'Hand soap'";
        minFill = 2;

        String sqlStatement43 = "INSERT INTO managerPrefernces VALUES (" + SKU + ", " + name
        + ", " + minFill + ")";

        sqlCommands.add(sqlStatement43);


        //Set min fill for Hand sanitizer
        SKU = "'d2038'";
        name = "'Hand sanitizer'";
        minFill = 1;

        String sqlStatement44 = "INSERT INTO managerPrefernces VALUES (" + SKU + ", " + name
        + ", " + minFill + ")";

        sqlCommands.add(sqlStatement44);


        //Set min fill for Dish soap
        SKU = "'d2039'";
        name = "'Dish soap'";
        minFill = 1;

        String sqlStatement45 = "INSERT INTO managerPrefernces VALUES (" + SKU + ", " + name
        + ", " + minFill + ")";

        sqlCommands.add(sqlStatement45);


        //Set min fill for Paper towels
        SKU = "'d2040'";
        name = "'Paper towels'";
        minFill = 1;

        String sqlStatement46 = "INSERT INTO managerPrefernces VALUES (" + SKU + ", " + name
        + ", " + minFill + ")";

        sqlCommands.add(sqlStatement46);


        //Set min fill for gloves
        SKU = "'d2041'";
        name = "'gloves'";
        minFill = 2;

        String sqlStatement47 = "INSERT INTO managerPrefernces VALUES (" + SKU + ", " + name
        + ", " + minFill + ")";

        sqlCommands.add(sqlStatement47);


        //Set min fill for trash can liners-small
        SKU = "'d2042'";
        name = "'trash can liners-small'";
        minFill = 1;

        String sqlStatement48 = "INSERT INTO managerPrefernces VALUES (" + SKU + ", " + name
        + ", " + minFill + ")";

        sqlCommands.add(sqlStatement48);


        //Set min fill for trash can liners-lg
        SKU = "'d2043'";
        name = "'trash can liners-lg'";
        minFill = 1;

        String sqlStatement49 = "INSERT INTO managerPrefernces VALUES (" + SKU + ", " + name
        + ", " + minFill + ")";

        sqlCommands.add(sqlStatement49);


        //Set min fill for toilet paper
        SKU = "'d2044'";
        name = "'toilet paper'";
        minFill = 1;

        String sqlStatement50 = "INSERT INTO managerPrefernces VALUES (" + SKU + ", " + name
        + ", " + minFill + ")";

        sqlCommands.add(sqlStatement50);


        //Set min fill for Surface cleaner
        SKU = "'d2045'";
        name = "'Surface cleaner'";
        minFill = 1;

        String sqlStatement51 = "INSERT INTO managerPrefernces VALUES (" + SKU + ", " + name
        + ", " + minFill + ")";

        sqlCommands.add(sqlStatement51);


        //Set min fill for Floor cleaner
        SKU = "'d2046'";
        name = "'Floor cleaner'";
        minFill = 1;

        String sqlStatement52 = "INSERT INTO managerPrefernces VALUES (" + SKU + ", " + name
        + ", " + minFill + ")";

        sqlCommands.add(sqlStatement52);




        

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
