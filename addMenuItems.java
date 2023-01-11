import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Scanner;
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
import java.awt.Dimension;
import java.awt.GridLayout;
import java.sql.*;
import java.util.ArrayList;


public class addMenuItems extends JFrame {
    static JFrame f;

    public void init(){
        // Building the connection
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
        Object[][] orderData = new Object[19][5];

        //querying the data from the database and populating arrays
        int arrayCT = 0;
        for(int i=501; i<=519; i++){
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
        productTwo.setText("2 - Toast");

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
        resetItemButton.setFont(new Font("Serif", Font.PLAIN, 25));
        resetItemButton.setPreferredSize(new Dimension(200, 100));
        
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
                for(int i = 0; i < 19; i++){
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
                            
                            if(prod >= 1 && prod <= 12){
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

                                System.out.println(addItemBack);
                                
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
        
        //setting buttons parameters
        buttonPanel.add(resetItemButton);
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
        } 
        catch (Exception e) {

        }
    }

    public static void main(String[] args) {
        addMenuItems addMenuItem = new addMenuItems();
        addMenuItem.init();
    }

}