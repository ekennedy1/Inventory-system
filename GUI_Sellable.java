import java.sql.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableColumnModel;

import java.awt.Dimension;
import java.awt.GridLayout;

public class GUI_Sellable extends JFrame {
    static JFrame f;

    public void init() {
        String[] columnNames = { "ITEM ID", "NAME", "PRICE", "EXTENDED PRICE", "QUANTITY", "DELIVERED BY",
                "SOLD BY", "QUANTITY MULTIPLIER", "INVOICE LINE", "DESCRIPTION", "CATEGORY" };

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

        int numOfRows = 100;


        // Get count of rows in table
        try {
            // create a statement object
            Statement stmt = conn.createStatement();

            // Find Count of table
            String sqlStatement = "SELECT COUNT(*) FROM sellableInventory;";
            // send statement to DBMS
            ResultSet sizeResult = stmt.executeQuery(sqlStatement);
            sizeResult.next();
            numOfRows = sizeResult.getInt("count");

        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error accessing Database.");

        }

        Object[][] inventoryData = new Object[numOfRows][11];

        try {
            // create a statement object
            Statement stmt = conn.createStatement();

            // create an SQL statement
            String sqlStatement = "SELECT * FROM sellableInventory;";
            // send statement to DBMS
            ResultSet result = stmt.executeQuery(sqlStatement);

            int i = 0;
            while (result.next()) {
                // get arrays of data from database
                inventoryData[i][0] = result.getString("productID");
                inventoryData[i][1] = result.getString("productname");
                inventoryData[i][2] = result.getFloat("price");
                inventoryData[i][3] = result.getFloat("extendedprice");
                inventoryData[i][4] = result.getInt("quantity");
                inventoryData[i][5] = result.getString("deliveredby");
                inventoryData[i][6] = result.getString("soldby");
                inventoryData[i][7] = result.getFloat("quantitymultiplier");
                inventoryData[i][8] = result.getInt("invoiceline");
                inventoryData[i][9] = result.getString("description");
                inventoryData[i][10] = result.getString("category");

                // iterate i
                i++;
            }
        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error accessing Database.");

        }


        // create a new frame
        f = new JFrame("Inventory Management System : Sellable Inventory");

        // Create a JPanel
        JPanel mainJp = new JPanel();
        mainJp.setLayout(new GridLayout(2, 1));
        mainJp.setBorder(new EmptyBorder(40, 0, 0, 0));

        // Making a home button
        JButton homeButton = new JButton("BACK");
        homeButton.setBounds(0, 0, 100, 40);
        homeButton.setFont(new Font("Serif", Font.PLAIN, 15));
        homeButton.setBackground(new Color(80, 0, 0));
        homeButton.setOpaque(true);
        homeButton.setBorderPainted(false);
        homeButton.setForeground(Color.white);

        homeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // String s = e.getActionCommand();
                managerInventoryOptions.main(null);
                f.dispose();
            }
        });

        // Create a JTable
        JTable table = new JTable(inventoryData, columnNames);

        // Set JTable Widths
        TableColumnModel columnModel = table.getColumnModel();

        columnModel.getColumn(0).setPreferredWidth(20);
        columnModel.getColumn(1).setPreferredWidth(80);
        columnModel.getColumn(2).setPreferredWidth(20);
        columnModel.getColumn(3).setPreferredWidth(50);
        columnModel.getColumn(4).setPreferredWidth(40);
        columnModel.getColumn(5).setPreferredWidth(50);
        columnModel.getColumn(6).setPreferredWidth(40);
        columnModel.getColumn(7).setPreferredWidth(75);
        columnModel.getColumn(8).setPreferredWidth(25);
        columnModel.getColumn(9).setPreferredWidth(390);
        columnModel.getColumn(10).setPreferredWidth(35);

        // create a scroll pane
        JScrollPane sp = new JScrollPane(table);
        table.setFillsViewportHeight(true);

        mainJp.add(sp);
        f.add(mainJp);

        // set the size of frame
        f.setSize(1500, 1000);
        f.setVisible(true);

        // add a title to the Home page
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
            JOptionPane.showMessageDialog(null, "Connection NOT Closed.");
        }
    }

    public static void main(String[] args) {
        GUI_Sellable sellInventory = new GUI_Sellable();

        sellInventory.init();
    }

}