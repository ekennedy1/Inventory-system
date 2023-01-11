import java.sql.*;
import java.util.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.awt.*;
import java.io.*;
import java.lang.module.ResolutionException;
import java.util.Date;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.EmptyBorder;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class modifyMenuItems extends JFrame {
    static JFrame f;
    Integer itemSize;
    Integer prodIDMax;

    public void init(){
        // Building the connection
        Connection conn = null;

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://csce-315-db.engr.tamu.edu/csce315910_13db",
                    "csce315910_13user", "table_13");

            String getSize = "SELECT COUNT(*) FROM menu";

            ResultSet rs;
            Statement stmt = conn.createStatement();

            rs = stmt.executeQuery(getSize);
            
            while (rs.next()) {
                itemSize = rs.getInt("count");
            }

            ResultSet rs1;
            String getMaxProdID = "SELECT * FROM salestoinventoryconversion ORDER BY cast(productid AS INTEGER) DESC LIMIT 1"; 
            
            rs1 = stmt.executeQuery(getMaxProdID);
            while(rs1.next()){
                prodIDMax = rs1.getInt("productid");
            }
        } 
        catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

        //all the columns within the table
        String[] columnNames = {"Item ID", "<html>Product IDs <br>(separate by comma)</html>", "<html>Quantity of <br>each product ID (separate by comma)</html>", "Product Name", "Product Description" };

        // // Making a home button to go back to home
        JButton homeButton = new JButton("BACK");
        homeButton.setBounds(0, 0, 100, 40);
        homeButton.setFont(new Font("Serif", Font.PLAIN, 15));
        homeButton.setBackground(new Color(80, 0, 0));
        homeButton.setOpaque(true);
        homeButton.setBorderPainted(false);
        homeButton.setForeground(Color.white);

        homeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                managerOrderSummary.main(null);
                f.dispose();
            }
        });

        //array that keeps all the item data
        Object[][] orderData = new Object[itemSize][5];

        //querying the data from the database and populating arrays
        int arrayCT = 0;
        for(int i=501; i<=(itemSize + 500); i++){
            orderData[arrayCT][0] = i;
            arrayCT++;
        }

        //setting a key for the manager to make new food combinations
        JPanel productIDChart = new JPanel();
        productIDChart.setPreferredSize(new Dimension(550, 550));

        JLabel titleProduct = new JLabel();
        titleProduct.setText("<html><u>Product ID Conversions</u></html>");
        titleProduct.setFont(new Font("Serif", Font.PLAIN, 20));

        JLabel productOne = new JLabel();
        productOne.setText("1 - Chicken Tender      12 - Bottled Drink");

        JLabel productTwo = new JLabel();
        productTwo.setText("2 - Toast               13 - Impossible Tender");

        JLabel productThree = new JLabel();
        productThree.setText("3 - Fries");

        JLabel productFour = new JLabel();
        productFour.setText("4 - Potato Salad");

        JLabel productFive = new JLabel();
        productFive.setText("5 - Sauce");

        JLabel productSix = new JLabel();
        productSix.setText("6 - Adult Size Drink");

        JLabel productSeven = new JLabel();
        productSeven.setText("7 - Kids Size Drink");

        JLabel productEight = new JLabel();
        productEight.setText("8 - Gallon of Tea");

        JLabel productNine = new JLabel();
        productNine.setText("9 - Club Sandwhich");

        JLabel productTen = new JLabel();
        productTen.setText("10 - Sandwhich");

        JLabel productEleven = new JLabel();
        productEleven.setText("11 - Grilled Cheese");

        productIDChart.setLayout(new GridLayout(12, 1));

        //adding the legend to a JPanel
        productIDChart.add(titleProduct);
        productIDChart.add(new JLabel());
        productIDChart.add(productOne);
        productIDChart.add(new JLabel());
        productIDChart.add(productTwo);
        productIDChart.add(new JLabel());
        productIDChart.add(productThree);
        productIDChart.add(new JLabel());
        productIDChart.add(productFour);
        productIDChart.add(new JLabel());
        productIDChart.add(productFive);
        productIDChart.add(new JLabel());
        productIDChart.add(productSix);
        productIDChart.add(new JLabel());
        productIDChart.add(productSeven);
        productIDChart.add(new JLabel());
        productIDChart.add(productEight);
        productIDChart.add(new JLabel());
        productIDChart.add(productNine);
        productIDChart.add(new JLabel());
        productIDChart.add(productTen);
        productIDChart.add(new JLabel());
        productIDChart.add(productEleven);


        //creating frame
        f = new JFrame();

        //adding header dimensions
        JTable table = new JTable(orderData, columnNames);
        JTableHeader headerTable = table.getTableHeader();
        headerTable.setPreferredSize(new Dimension(0, 75));

        // Set JTable Widths
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(60);
        columnModel.getColumn(1).setPreferredWidth(100);
        columnModel.getColumn(2).setPreferredWidth(100);
        columnModel.getColumn(3).setPreferredWidth(100);
        columnModel.getColumn(4).setPreferredWidth(170);


        //set table row height
        table.setRowHeight(40);
        
        // create a scroll pane
        JScrollPane sp = new JScrollPane(table);
        table.setFillsViewportHeight(true);

        //button to modify the prices
        JButton resetItemButton = new JButton("Modify All Items");
        resetItemButton.setFont(new Font("Serif", Font.PLAIN, 20));
        resetItemButton.setPreferredSize(new Dimension(200, 100));

        JButton addMenuitem = new JButton("Add Menu Item");
        addMenuitem.setFont(new Font("Serif", Font.PLAIN, 20));
        addMenuitem.setPreferredSize(new Dimension(200, 100));

        JButton removeMenuItem = new JButton("Remove Menu Item");
        removeMenuItem.setFont(new Font("Serif", Font.PLAIN, 20));
        removeMenuItem.setPreferredSize(new Dimension(200, 100));

        //remove menu item
        removeMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Class.forName("org.postgresql.Driver");
                
                    Connection conn = DriverManager.getConnection("jdbc:postgresql://csce-315-db.engr.tamu.edu/csce315910_13db",
                    "csce315910_13user", "table_13");
                    Statement stmt = conn.createStatement();

                    String removeFromMenu = "DELETE FROM menu WHERE itemid = 520;";
                    
                    stmt.executeUpdate(removeFromMenu);

                    
                    String deleteSales = "DELETE FROM salesToInventoryConversion WHERE productID = \'13\'";
                    stmt.executeUpdate(deleteSales);

                    String removeFromRelationTable = "DELETE FROM sellableinvorderrelation WHERE itemid = 520;";

                    stmt.executeUpdate(removeFromRelationTable);

                    String removeFromInventory = "DELETE FROM sellableInventory WHERE productID = \'i1001\'";
                    stmt.executeUpdate(removeFromInventory);

                    conn.close();
                    JOptionPane.showMessageDialog(null, "Item 520 Removed");
                    modifyMenuItems.main(null);
                    f.dispose();
                    return;

                } catch (Exception ef) {
                    ef.printStackTrace();
                    System.err.println(ef.getClass().getName() + ": " + ef.getMessage());
                    System.exit(0);
                }
            }

        });
        
        //add menu item functionality (very similar to modify)
        addMenuitem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int menuSize = 0;
                String itemID = "";

                try {
                    Class.forName("org.postgresql.Driver");
                
                    Connection conn = DriverManager.getConnection("jdbc:postgresql://csce-315-db.engr.tamu.edu/csce315910_13db",
                    "csce315910_13user", "table_13");
                    Statement stmt = conn.createStatement();

                    String getSize = "SELECT COUNT(*) FROM menu";

                    ResultSet rs;

                    rs = stmt.executeQuery(getSize);

                    while (rs.next()) {
                        menuSize = rs.getInt("count");
                    }
                    
                    if(menuSize == 19){
                        itemID = "520";
                        menuSize++;
                    }else{
                        conn.close();
                        JOptionPane.showMessageDialog(null, "Cannot Add Items as the Menu is Full");
                        modifyMenuItems.main(null);
                        f.dispose();
                        return;
                    }

                } catch (Exception ef) {
                    ef.printStackTrace();
                    System.err.println(ef.getClass().getName() + ": " + ef.getMessage());
                    System.exit(0);
                }

                JTextField itemField = new JTextField(15);
                itemField.setText(itemID);

                JTextField productField = new JTextField(15);
                JTextField priceField = new JTextField(15);
                JTextField quanField = new JTextField(15);
                JTextField nameField = new JTextField(15);
                JTextField descrField = new JTextField(15);


                JPanel addItemPanel = new JPanel();
                //new add itemPanel with entries 
                addItemPanel.setLayout(new GridLayout(6,1));
                addItemPanel.add(new JLabel("Item ID (DO NOT CHANGE)"));
                addItemPanel.add(itemField);
                addItemPanel.add(new JLabel("Product IDs (comma separated)"));
                addItemPanel.add(productField);
                addItemPanel.add(new JLabel("Quantity of Product IDs (comma separated)"));
                addItemPanel.add(quanField);
                addItemPanel.add(new JLabel("Price of New Item"));
                addItemPanel.add(priceField);
                addItemPanel.add(new JLabel("Name of New Item"));
                addItemPanel.add(nameField);
                addItemPanel.add(new JLabel("Description of New Item"));
                addItemPanel.add(descrField);

                // UIManager.put("OptionPane.minimumSize", new Dimension(500, 500));
                Integer yes = JOptionPane.showConfirmDialog(null, addItemPanel);

                //if entries are valid, we add item to the database
                if(yes == 0){
                    
                    if(!productField.getText().equalsIgnoreCase("") && !priceField.getText().equalsIgnoreCase("")
                    && !quanField.getText().equalsIgnoreCase("")  && !nameField.getText().equalsIgnoreCase("") 
                    && !descrField.getText().equalsIgnoreCase("") ){

                        String prodID = "" +  productField.getText();
                        Scanner sc = new Scanner(prodID);
                        sc.useDelimiter(",");

                        // Increment max product ID
                        prodIDMax++;

                        ArrayList<Integer> checker = new ArrayList<Integer>();
                        ArrayList<Integer> checkerQuant = new ArrayList<Integer>();
                        while(sc.hasNext()){
                            String str = sc.next().replaceAll("\\s+","");
                            Integer prod = Integer.parseInt(str);
                            
                            if(prod >= 1 && prod <= prodIDMax){
                                checker.add(prod);
                            }
                            else{
                                JOptionPane.showMessageDialog(null, "Errenous Product ID Entered");
                                modifyMenuItems.main(null);
                                f.dispose();
                                return;
                            }
                        }

                        String quant = "" + quanField.getText();
                        sc = new Scanner(quant);
                        sc.useDelimiter(",");

                        while(sc.hasNext()){
                            String str = sc.next().replaceAll("\\s+","");
                            Integer quan = Integer.parseInt(str);

                            if(quan > 0)
                                checkerQuant.add(quan);
                        }

                        if(checkerQuant.size() != checker.size()){
                            JOptionPane.showMessageDialog(null, "Quantity Size and Product ID size DO NOT match");
                            modifyMenuItems.main(null);
                            f.dispose();
                            return;
                        }
                        
                        String priceOfItem = "" + priceField.getText();
                        String tempPriceofItem = priceOfItem;

                        Integer ct = 0;
                        for(int j = 0; j < priceOfItem.length(); j++){
                            if(priceOfItem.charAt(j) == '.'){
                                ct++;
                            }
                        }

                        String tempFinalStr = tempPriceofItem.replace(".", "1");

                        if(tempFinalStr.matches("[0-9]+") != true || (ct > 1)){
                            JOptionPane.showMessageDialog(null, "Invalid Price Value Entered");
                            modifyMenuItems.main(null);
                            f.dispose();
                            return;
                        }

                        String prodName = "" + nameField.getText();

                        String prodDes = "" + descrField.getText();

                        try {
                            Class.forName("org.postgresql.Driver");
                
                            Connection conn = DriverManager.getConnection("jdbc:postgresql://csce-315-db.engr.tamu.edu/csce315910_13db",
                            "csce315910_13user", "table_13");
                            Statement stmt = conn.createStatement();

                            String getMaxEntryID = "SELECT MAX(entryid) FROM sellableinvorderrelation;";
                            Integer MaxEID = 0;

                            ResultSet rs1;
                            
                            rs1 = stmt.executeQuery(getMaxEntryID);

                            while(rs1.next()){
                                MaxEID = rs1.getInt("max");
                            }

                            Integer entryID = MaxEID + 1;
                            
                            for(int f = 0; f<checker.size(); f++){
                                String addToOrderRelation = "INSERT INTO sellableinvorderrelation " + "VALUES ('" + (entryID + f) + "', '" + itemID 
                                + "', '" + checker.get(f) + "', '" + checkerQuant.get(f) + "', '" + priceOfItem + "');";

                                stmt.executeUpdate(addToOrderRelation);
                            }

                            // Insert item into menu
                            String addToMenu = "INSERT INTO menu " + "VALUES ('" + itemID + "', '" + prodName 
                            + "', '" + prodDes + "');";

                            stmt.executeUpdate(addToMenu);



                            String getMaxInvoice = "SELECT * FROM sellableInventory ORDER BY invoiceLine DESC LIMIT 1";
                            ResultSet invoiceSet = stmt.executeQuery(getMaxInvoice);

                            
                            Integer invoiceLine = -1;

                            while(invoiceSet.next()) {
                                invoiceLine = invoiceSet.getInt("invoiceLine");
                            }

                            // Increment invoice line
                            invoiceLine++;

                            Integer CONVERSION_PRODUCT_ID = 13;

                            String getMaxConversionID = "SELECT * FROM salesToInventoryConversion ORDER BY conversionID DESC LIMIT 1";

                            ResultSet conversionSet = stmt.executeQuery(getMaxConversionID);

                            Integer conversionID = -1;


                            while (conversionSet.next()) {
                                conversionID = conversionSet.getInt("conversionID");
                            }

                            // Increment conversion ID
                            conversionID++;

                            
                            String getMaxSellableEntryID= "SELECT * FROM sellableInvOrderRelation ORDER BY entryID DESC LIMIT 1";

                            ResultSet entrySet2 = stmt.executeQuery(getMaxSellableEntryID);

                            Integer newEntryID = -1;

                            while (entrySet2.next()) {
                                newEntryID = entrySet2.getInt("entryID");
                            }

                        
                            // Increment entry ID
                            newEntryID++;

                            // For impossible meat
                            String insert1 = "INSERT INTO salesToInventoryConversion VALUES (" + conversionID + ", \'i1001\', \'13\', 0.00469483568)";

                            conversionID++;
                            String insert2 = "INSERT INTO salesToInventoryConversion VALUES (" + conversionID + ", \'d2001\', \'13\', 0.001111111111)";
                            
                            conversionID++;
                            String insert3 = "INSERT INTO salesToInventoryConversion VALUES (" + conversionID + ", \'d2002\', \'13\', 0.001876172608)";
                            
                            conversionID++;
                            String insert4 = "INSERT INTO salesToInventoryConversion VALUES (" + conversionID + ", \'d2003\', \'13\', 0.01886792452)";
                            
                            conversionID++;
                            String insert5 = "INSERT INTO salesToInventoryConversion VALUES (" + conversionID + ", \'d2005\', \'13\', 0.01886792452)";
                            
                            conversionID++;
                            String insert6 = "INSERT INTO salesToInventoryConversion VALUES (" + conversionID + ", \'d2012\', \'13\', 0.002380952381)";
                            
                            
                            // Insert impossible meat into sellable inventory

                            String insertImpossible = "INSERT INTO sellableInventory VALUES (\'i1001\', \'Impossible Meat\', 4.29, 1801.8, 21, \'case\', \'lb\', 20, 35, \'Fake Meat Used\', \'Cold\')";

                            stmt.executeUpdate(insertImpossible);
                            stmt.executeUpdate(insert1);
                            stmt.executeUpdate(insert2);
                            stmt.executeUpdate(insert3);
                            stmt.executeUpdate(insert4);
                            stmt.executeUpdate(insert5);
                            stmt.executeUpdate(insert6);
                            

                            conn.close();
                            JOptionPane.showMessageDialog(null, "New Item Added to Menu");
                            modifyMenuItems.main(null);
                            f.dispose();
                            return;

                        } catch (Exception ef2) {
                            //TODO: handle exception
                        }
                        
                    }else{
                        JOptionPane.showMessageDialog(null, "Incorrect Field Entered");
                        modifyMenuItems.main(null);
                        f.dispose();
                        return;
                    }
                }

            }
        });
        
        //changes items within database
        resetItemButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // String s = e.getActionCommand();
                
                //array with needed data
                ArrayList<String> itemIDarr = new ArrayList<String>();
                ArrayList<ArrayList<Integer>> prodIDArr = new ArrayList<ArrayList<Integer>>();
                ArrayList<ArrayList<Integer>> quanArr = new ArrayList<ArrayList<Integer>>();
                ArrayList<Object> nameArr = new ArrayList<Object>();
                ArrayList<Object> descArr = new ArrayList<Object>();
                
                //checking if any changes were made
                for(int i = 0; i < itemSize; i++){
                    if(table.getModel().getValueAt(i, 1) != null && table.getModel().getValueAt(i, 2) != null
                        && table.getModel().getValueAt(i, 3) != null && table.getModel().getValueAt(i, 4) != null){

                        String prodID = "" +  table.getModel().getValueAt(i, 1);
                        Scanner sc = new Scanner(prodID);
                        sc.useDelimiter(",");

                        ArrayList<Integer> checker = new ArrayList<Integer>();
                        ArrayList<Integer> checkerQuant = new ArrayList<Integer>();
                        while(sc.hasNext()){
                            String str = sc.next().replaceAll("\\s+","");
                            Integer prod = Integer.parseInt(str);
                            
                            if(prod >= 1 && prod <= prodIDMax){
                                checker.add(prod);
                            }
                            else{
                                modifyMenuItems.main(null);
                                f.dispose();
                            }
                        }

                        String quant = "" + table.getModel().getValueAt(i, 2);
                        sc = new Scanner(quant);
                        sc.useDelimiter(",");

                        while(sc.hasNext()){
                            String str = sc.next().replaceAll("\\s+","");
                            Integer quan = Integer.parseInt(str);

                            if(quan > 0)
                                checkerQuant.add(quan);
                        }

                        if(checkerQuant.size() != checker.size()){
                            continue;
                        }
                        else{
                            prodIDArr.add(checker);
                            quanArr.add(checkerQuant);
                        }
                        
                        String itemID = "" +  table.getModel().getValueAt(i, 0); 
                        itemIDarr.add(itemID);

                        String prodName = "" + table.getModel().getValueAt(i, 3);
                        nameArr.add(prodName);

                        String prodDes = "" + table.getModel().getValueAt(i, 4);
                        descArr.add(prodDes);

                    }
                }

                //add to database when modifications have occured
                if(itemIDarr.size() != 0){                
                    try{
                        Class.forName("org.postgresql.Driver");
                        Connection conn = DriverManager.getConnection("jdbc:postgresql://csce-315-db.engr.tamu.edu/csce315910_13db",
                        "csce315910_13user", "table_13");

                        //update menu
                        for(int j = 0; j < itemIDarr.size(); j++){
                            String command = "UPDATE menu SET name " + 
                            "= '" + nameArr.get(j) + "', description = '" + descArr.get(j) + 
                            "' WHERE itemid = '" + itemIDarr.get(j) + "';";
                           
                            Statement stmt = conn.createStatement();
                            stmt.executeUpdate(command);
                            
                            //get price from menuprice
                            String getPrice = "SELECT MAX(menuprice) FROM sellableinvorderrelation WHERE itemid = " + itemIDarr.get(j)
                            + ";";

                            //get max entry id so you can enter again
                            String getMaxEntryID = "SELECT MAX(entryid) FROM sellableinvorderrelation;";

                            ResultSet rs;
                            ResultSet rs1;

                            //execute queries for both tables
                            rs = stmt.executeQuery(getPrice);
                            double price = 0;
                            int maxEID = 0;
                            
                            while (rs.next()) {
                                price = rs.getFloat("max");
                            }

                            rs1 = stmt.executeQuery(getMaxEntryID);
                            while(rs1.next()){
                                maxEID = rs1.getInt("max");
                            }

                            //make sure maxEID is greater than largest available entry ID
                            maxEID++;
                            
                            //deleting and adding everything tothe different tables
                            String deleteItemID = "DELETE FROM sellableinvorderrelation WHERE itemid = " + itemIDarr.get(j)
                            + ";";
                            stmt.executeUpdate(deleteItemID);


                            
                            for(int f = 0; f < prodIDArr.get(j).size(); f++){
                                int entryID = maxEID + f;
                                String addItemBack = "INSERT INTO sellableinvorderrelation " + "VALUES ('" + entryID + "', '" + itemIDarr.get(j) 
                                + "', '" + prodIDArr.get(j).get(f) + "', '" + quanArr.get(j).get(f) + "', '" + 
                                price + "');";
                                
                                stmt.executeUpdate(addItemBack);
                            }

                        }

                        conn.close();
                        JOptionPane.showMessageDialog(null, "Menu Items Changed");
                    }
                    catch (Exception f){
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null, "None of the Menu Items have been changed");
                }

                modifyMenuItems.main(null);
                f.dispose();
            }
        });

        //main panel and button JPanels
        JPanel mainPanel = new JPanel();
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 1));
        
        //setting buttons parameters
        buttonPanel.add(resetItemButton);
        buttonPanel.add(new JLabel());
        buttonPanel.add(addMenuitem);
        buttonPanel.add(new JLabel());
        buttonPanel.add(removeMenuItem);

        mainPanel.add(buttonPanel);
        mainPanel.setBorder(new EmptyBorder(75, 0, 0, 0));
        mainPanel.add(Box.createRigidArea(new Dimension(35, 0)));
        mainPanel.add(sp);
        mainPanel.add(Box.createRigidArea(new Dimension(35, 0)));
        mainPanel.add(productIDChart);


        //adding mainPanel to frame
        f.add(mainPanel);

        f.setSize(1500, 1000);
        f.setVisible(true);

        // // add a title to the Home page
        JLabel header = new JLabel("Layne's Chicken Fingers! (Modify Items)", SwingConstants.CENTER);
        header.setFont(new Font("Serif", Font.PLAIN, 40));
        header.setBorder(new EmptyBorder(40, 0, 0, 0));
        header.setBounds(40, 0, 0, 0);
        header.setForeground(new Color(80, 0, 0));

        f.add(header, BorderLayout.NORTH);
        header.add(homeButton);

        // closing the connection
        try {
            conn.close();
            // JOptionPane.showMessageDialog(null, "Connection Closed.");
        } 
        catch (Exception e) {
            // JOptionPane.showMessageDialog(null, "Connection NOT Closed.");
        }
    }

    public static void main(String[] args) {
        modifyMenuItems modifyMenu = new modifyMenuItems();
        modifyMenu.init();
    }
}
