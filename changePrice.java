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
import javax.swing.table.TableColumnModel;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.sql.*;
import java.util.ArrayList;


public class changePrice extends JFrame {
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
        String[] columnNames = {"Item ID", "Current Price", "New Price" };

        // // Making a home button to go back to home
        JButton homeButton = new JButton("BACK");
        homeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                managerOrderSummary.main(null);
                f.dispose();
            }
        });

        homeButton.setBounds(0, 0, 100, 40);
        homeButton.setFont(new Font("Serif", Font.PLAIN, 15));
        homeButton.setBackground(new Color(80, 0, 0));
        homeButton.setOpaque(true);
        homeButton.setBorderPainted(false);
        homeButton.setForeground(Color.white);
        
        //array that keeps all the item data
        Object[][] orderData = new Object[19][3];

        //querying the data from the database and populating arrays
        int arrayCT = 0;
        for(int i=501; i<=519; i++){
            String sqlCommand = "SELECT MAX(menuprice) FROM sellableinvorderrelation WHERE itemid = " + i + ";";
            double price = 0;

            try{
                Statement stmt = conn.createStatement();
                ResultSet rs;
                rs = stmt.executeQuery(sqlCommand);
                while(rs.next()){
                    price = rs.getFloat("max");
                }

                orderData[arrayCT][0] = i;
                orderData[arrayCT][1] = String.format("%.2f", price);
                arrayCT++;

            }catch (Exception e) { // Error handling
                e.printStackTrace();
                System.exit(0);
            }
        }

        //creating frame
        f = new JFrame("Inventory Management System : Modifying Prices");

        JTable table = new JTable(orderData, columnNames);

        // Set JTable Widths
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(60);
        columnModel.getColumn(1).setPreferredWidth(40);
        columnModel.getColumn(2).setPreferredWidth(40);

        //set table row height
        table.setRowHeight(40);
        
        // create a scroll pane
        JScrollPane sp = new JScrollPane(table);
        table.setFillsViewportHeight(true);

        //button to modify the prices
        JButton resetPriceButton = new JButton("Modify All Prices");
        resetPriceButton.setFont(new Font("Serif", Font.PLAIN, 25));
        resetPriceButton.setPreferredSize(new Dimension(200, 100));
        
        resetPriceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // String s = e.getActionCommand();
                
                //array with itemIDs and prices
                ArrayList<String> itemIDarr = new ArrayList<String>();
                ArrayList<Object> priceArr = new ArrayList<Object>();

                for(int i = 0; i < 19; i++){
                    if(table.getModel().getValueAt(i, 2) != null){
                        String itemID = "" +  table.getModel().getValueAt(i, 0); 
                        itemIDarr.add(itemID);
                        
                        Object price = table.getModel().getValueAt(i, 2);
                        priceArr.add(price);
                    }
                }

                //add to database when modifications have occured
                if(itemIDarr.size() != 0){                
                    try{
                        Class.forName("org.postgresql.Driver");
                        Connection conn = DriverManager.getConnection("jdbc:postgresql://csce-315-db.engr.tamu.edu/csce315910_13db",
                        "csce315910_13user", "table_13");

                        for(int j = 0; j < itemIDarr.size(); j++){
                            String command = "UPDATE sellableinvorderrelation SET menuprice " + 
                            "= " + priceArr.get(j) + " WHERE itemid = '" + itemIDarr.get(j) + "';";
                           
                            Statement stmt = conn.createStatement();
                            stmt.executeUpdate(command);
                        }
                        conn.close();
                        JOptionPane.showMessageDialog(null, "Prices Changed");
                    }
                    catch (Exception f){
                    }                    
                }

                else{
                    JOptionPane.showMessageDialog(null, "None of the Prices have been changed");
                }

                changePrice cp = new changePrice();
                cp.init();
                f.dispose();
            }
        });

        //main panel and button JPanels
        JPanel mainPanel = new JPanel();
        JPanel buttonPanel = new JPanel();
        
        //setting buttons parameters
        buttonPanel.add(resetPriceButton);
        mainPanel.add(buttonPanel);
        mainPanel.setBorder(new EmptyBorder(75, 0, 0, 0));
        mainPanel.add(Box.createRigidArea(new Dimension(35, 0)));
        mainPanel.add(sp);

        //adding mainPanel to frame
        f.add(mainPanel);

        f.setSize(1500, 1000);
        f.setVisible(true);

        // // add a title to the Home page
        JLabel header = new JLabel("Layne's Chicken Fingers! (Modify Prices)", SwingConstants.CENTER);
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
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        changePrice changePrce = new changePrice();
        changePrce.init();
    }

}