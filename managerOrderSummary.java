import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.awt.*;
import java.io.*;
import javax.swing.*;
import java.lang.module.ResolutionException;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableColumnModel;

public class managerOrderSummary extends JFrame {
    static JFrame f;

    public void init() {
        String[] columnNames = { "Day", "Item ID", "Quantity", "Total" };

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


        //created an array where each item corresponds to its index
        int [] itemData = new int [20];

        try {
            //scanned through "daily file" to obtain data to put in array
            Scanner sc = new Scanner(new File("Orders.csv"));
            sc.useDelimiter("\n");

            String date = new SimpleDateFormat("yyyy/MM/dd").format(new Date());

            while (sc.hasNext()) {
                String next = sc.next();
                if (next.contains(date)) {
                    Scanner newSc = new Scanner(next);
                    newSc.useDelimiter(",");
                    newSc.next();
                    newSc.next();
                    String itemID = newSc.next();
                    if (itemID.equalsIgnoreCase("501")) {
                        itemData[1]++;
                    } 
                    else if (itemID.equalsIgnoreCase("502")) {
                        itemData[2]++;
                    } 
                    else if (itemID.equalsIgnoreCase("503")) {
                        itemData[3]++;
                    } 
                    else if (itemID.equalsIgnoreCase("504")) {
                        itemData[4]++;
                    } 
                    else if (itemID.equalsIgnoreCase("505")) {
                        itemData[5]++;
                    } 
                    else if (itemID.equalsIgnoreCase("506")) {
                        itemData[6]++;
                    } 
                    else if (itemID.equalsIgnoreCase("507")) {
                        itemData[7]++;
                    } 
                    else if (itemID.equalsIgnoreCase("508")) {
                        itemData[8]++;
                    } 
                    else if (itemID.equalsIgnoreCase("509")) {
                        itemData[9]++;
                    } 
                    else if (itemID.equalsIgnoreCase("510")) {
                        itemData[10]++;
                    } 
                    else if (itemID.equalsIgnoreCase("511")) {
                        itemData[11]++;
                    } 
                    else if (itemID.equalsIgnoreCase("512")) {
                        itemData[12]++;
                    } 
                    else if (itemID.equalsIgnoreCase("513")) {
                        itemData[13]++;
                    } 
                    else if (itemID.equalsIgnoreCase("514")) {
                        itemData[14]++;
                    } 
                    else if (itemID.equalsIgnoreCase("515")) {
                        itemData[15]++;
                    } 
                    else if (itemID.equalsIgnoreCase("516")) {
                        itemData[16]++;
                    } 
                    else if (itemID.equalsIgnoreCase("517")) {
                        itemData[17]++;
                    } 
                    else if (itemID.equalsIgnoreCase("518")) {
                        itemData[18]++;
                    } 
                    else if (itemID.equalsIgnoreCase("519")) {
                        itemData[19]++;
                    }
                }
            }

        } 
        catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }

        f = new JFrame("Inventory Management System : Manager Order Summary");

        JPanel mainJp = new JPanel();
        mainJp.setBorder(new EmptyBorder(40, 0, 0, 0));

        // // Making a home button
        JButton homeButton = new JButton("BACK");
        homeButton.setBounds(0, 0, 100, 40);
        homeButton.setFont(new Font("Serif", Font.PLAIN, 15));
        homeButton.setBackground(new Color(80, 0, 0));
        homeButton.setOpaque(true);
        homeButton.setBorderPainted(false);
        homeButton.setForeground(Color.white);

        //home button action
        homeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // String s = e.getActionCommand();
                ManagerHome.main(null);
                f.dispose();
            }
        });

        //orderData contains all the item IDs and how much has been made throughout the day
        Object[][] orderData = new Object[35][4];

        //this page is kind of redundant, so take these values with a grain of salt
        int arrayCT = 0;
        double totalFinPrice = 0;

        for (int i = 1; i <= 19; i++) {
            if (itemData[i] != 0) {
                orderData[arrayCT][0] = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
                int itemNum = 500 + i;
                orderData[arrayCT][1] = itemNum;
                orderData[arrayCT][2] = itemData[i];

                String sqlCommand = "SELECT MAX(menuprice) FROM sellableinvorderrelation WHERE itemid = " + itemNum
                        + ";";
                double price = 0;

                try {
                    Statement stmt = conn.createStatement();
                    ResultSet rs;
                    rs = stmt.executeQuery(sqlCommand);
                    while (rs.next()) {
                        price = rs.getFloat("max");
                    }

                } 
                catch (Exception e) { // Error handling
                    e.printStackTrace();
                    System.exit(0);
                }

                double quant = itemData[i];
                double finPrice = quant * price;
                String finPriceStr = "$" + finPrice;

                totalFinPrice += finPrice;

                orderData[arrayCT][3] = finPriceStr;
                arrayCT++;
            }
        }

        // adding a total final price of everything summed up
        orderData[arrayCT + 1][3] = totalFinPrice;
        

        // // Create a JTable
        JTable table = new JTable(orderData, columnNames);

        // Set JTable Widths
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(60);
        columnModel.getColumn(1).setPreferredWidth(40);
        columnModel.getColumn(2).setPreferredWidth(40);
        columnModel.getColumn(3).setPreferredWidth(40);

        table.setRowHeight(40);
        
        // create a scroll pane
        JScrollPane sp = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        //add table to the main panel
        mainJp.add(sp);
        
        //added buttons that allow manager to update certain things
        JPanel managerTools = new JPanel();
        managerTools.setLayout(new GridLayout(2, 1));

        //modify prices
        JButton updatePrices = new JButton("Modify menu price");
        updatePrices.setPreferredSize(new Dimension(150, 50));
        updatePrices.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // String s = e.getActionCommand();
                changePrice.main(null);
                f.dispose();
            }
        });

        //modify menu item
        JButton modItem = new JButton("Modify menu items");
        modItem.setPreferredSize(new Dimension(150, 50));
        modItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // String s = e.getActionCommand();
                modifyMenuItems.main(null);
                f.dispose();
            }
        });

        //add everything to consitute the entire managerorderSummary Page
        managerTools.add(updatePrices);
        managerTools.add(modItem);

        JPanel finalLayout = new JPanel();
        finalLayout.add(mainJp);
        finalLayout.add(managerTools);

        f.add(finalLayout);

        // // // set the size of frame
        f.setSize(1500, 1000);
        f.setVisible(true);

        // // add a title to the Home page
        JLabel header = new JLabel("Layne's Chicken Fingers!", SwingConstants.CENTER);
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
        managerOrderSummary mos = new managerOrderSummary();
        mos.init();
    }

}