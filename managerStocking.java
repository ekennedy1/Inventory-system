import javax.swing.*;
import javax.swing.event.*;
import java.sql.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;
import java.util.ArrayList;
import java.util.concurrent.Flow;

public class managerStocking extends JFrame {
    static JFrame f;

    public static void main(String[] a) {

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

            Statement stmt = conn.createStatement();

            String sqlStatement = "SELECT COUNT(*) FROM sellableInventory;";
            ResultSet sizeResult = stmt.executeQuery(sqlStatement);

            sizeResult.next();
            numOfRows = sizeResult.getInt("count");


            String sqlStatement2 = "SELECT COUNT(*) FROM nonsellableinventory;";
            ResultSet sizeResult2 = stmt.executeQuery(sqlStatement2);

            sizeResult2.next();
            numOfRows = numOfRows + sizeResult2.getInt("count");

        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error accessing Database.");

        }

        Object[][] inventoryData = new Object[numOfRows][11];

        try {

            Statement stmt = conn.createStatement();
            String sqlStatement = "SELECT * FROM sellableInventory;";
            ResultSet result = stmt.executeQuery(sqlStatement);

            int i = 0;
            while (result.next()) {
                // get arrays of data from database
                inventoryData[i][0] = result.getString("productID");
                inventoryData[i][1] = result.getString("productname");
                inventoryData[i][2] = result.getInt("quantity");
                inventoryData[i][3] = result.getFloat("price");

                i++;
            }
            
            Statement stmt2 = conn.createStatement();
            String sqlStatement2 = "SELECT * FROM nonsellableinventory;";
            ResultSet result2 = stmt2.executeQuery(sqlStatement2);

            while (result2.next()) {
                // get arrays of data from database
                inventoryData[i][0] = result2.getString("itemid");
                inventoryData[i][1] = result2.getString("objectname");
                inventoryData[i][2] = result2.getInt("quantity");
                inventoryData[i][3] = result2.getFloat("extendedprice");

                i++;
            }
            
        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error accessing Database.");

        }

        int numOfRows3 = 0;
        ArrayList<String> SKUs = new ArrayList<String>();
        ArrayList<Integer> quantitys = new ArrayList<Integer>();
        ArrayList<Integer> fillMins = new ArrayList<Integer>();
        
        try {

            Statement stmt2 = conn.createStatement();
            String sqlStatement2 = "SELECT * FROM managerPrefernces;";
            ResultSet result2 = stmt2.executeQuery(sqlStatement2);

            String SKU;

            int i = 0;
            int fillMin = 0;
            int quantity = 0;
            while (result2.next()) {
                // get arrays of data from database
                SKU = result2.getString("SKU");
                
                if(SKU.contains("d2023") || SKU.contains("d2024") || SKU.contains("d2025") || SKU.contains("d2026") || SKU.contains("d2027") || SKU.contains("d2028") || 
                SKU.contains("d2029") || SKU.contains("d2030") || SKU.contains("d2031") || SKU.contains("d2032") || SKU.contains("d2033") || SKU.contains("d2034") ||
                SKU.contains("d2035") || SKU.contains("d2036") || SKU.contains("d2037") || SKU.contains("d2038") || SKU.contains("d2039") || SKU.contains("d2040") || 
                SKU.contains("d2041") || SKU.contains("d2042") || SKU.contains("d2043") || SKU.contains("d2044") || SKU.contains("d2045") || SKU.contains("d2046")){
                
                    Statement stmt4 = conn.createStatement();
                    String sqlStatement4 = "SELECT quantity FROM nonsellableinventory WHERE itemID='" + SKU + "';";
                    ResultSet result4 = stmt4.executeQuery(sqlStatement4);
                    result4.next();
                    quantity = result4.getInt("quantity");
                    
                    Statement stmt5 = conn.createStatement();
                    String sqlStatement5 = "SELECT fillmin FROM managerprefernces WHERE sku='" + SKU + "';";
                    ResultSet result5 = stmt5.executeQuery(sqlStatement5);
                    result5.next();
                    fillMin = result5.getInt("fillMin");
                    
                    

                    if(quantity < fillMin){
                        SKUs.add(SKU);
                        quantitys.add(quantity);
                        fillMins.add(fillMin);
                        numOfRows3++;
                    }

                } else{
                    
                    Statement stmt4 = conn.createStatement();
                    String sqlStatement4 = "SELECT quantity FROM sellableinventory WHERE productID='" + SKU + "';";
                    ResultSet result4 = stmt4.executeQuery(sqlStatement4);
                    result4.next();
                    quantity = result4.getInt("quantity");
                    

                    Statement stmt5 = conn.createStatement();
                    String sqlStatement5 = "SELECT fillmin FROM managerprefernces WHERE sku='" + SKU + "';";
                    ResultSet result5 = stmt5.executeQuery(sqlStatement5);
                    result5.next();
                    fillMin = result5.getInt("fillMin");
                    

                    if(quantity < fillMin){
                        SKUs.add(SKU);
                        quantitys.add(quantity);
                        fillMins.add(fillMin);
                        numOfRows3++;
                    }
                }
                
                i++;
            }

        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error accessing Database.");
        }
        
        Object[][] suggestionsData = new Object[numOfRows3][1];
        try {
            int display = 0;
            for (int i = 0; numOfRows3 > i; i++){
                display = fillMins.get(i) - quantitys.get(i);
                suggestionsData[i][0] = "You should order " + display + " more " + SKUs.get(i) + ".";
            }
        } 
        catch (Exception l) {
            JOptionPane.showMessageDialog(null, "Error accessing Database.");
        }



        f = new JFrame("Inventory Management System: Manager Inventory");

        JButton back = new JButton("Back");
        back.setBounds(5, 5, 150, 40);

        JPanel containerOrders = new JPanel();

        JTextField inventoryField = new JTextField("SKU", 40);

        JTextField quantityField = new JTextField("Quantity", 40);

        JLabel header1 = new JLabel("Add Inventory Items", SwingConstants.CENTER);
        header1.setFont(new Font("Serif", Font.PLAIN, 30));
        header1.setBorder(new EmptyBorder(100, 0, 0, 0));
        header1.setBounds(40, 0, 0, 0);
        header1.setForeground(new Color(80, 0, 0));

        JButton add = new JButton("Add");
        add.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String sku = inventoryField.getText();
                String quantity = quantityField.getText();

                Connection conn = null;

                try {
                    Class.forName("org.postgresql.Driver");
                    conn = DriverManager.getConnection("jdbc:postgresql://csce-315-db.engr.tamu.edu/csce315910_13db",
                    "csce315910_13user", "table_13");
                } 
                catch (Exception c) {
                    c.printStackTrace();
                    System.err.println(c.getClass().getName() + ": " + c.getMessage());
                    System.exit(0);
                }

                try {
                    // create a statement object
                    Statement stmt = conn.createStatement();


                    String updateCommand = "UPDATE sellableInventory SET quantity = quantity + " + quantity
                                + " WHERE productID = \'" + sku + "\'";

                    stmt.executeUpdate(updateCommand);
     
                } 
                catch (Exception b) {
                    JOptionPane.showMessageDialog(null, "Error accessing Database.");
        
                }
                f.dispose();
                managerStocking.main(null);

            }
        });

        JButton sub = new JButton("Subtract");
        sub.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String sku = inventoryField.getText();
                String quantity = quantityField.getText();

                Connection conn = null;

                try {
                    Class.forName("org.postgresql.Driver");
                    conn = DriverManager.getConnection("jdbc:postgresql://csce-315-db.engr.tamu.edu/csce315910_13db",
                    "csce315910_13user", "table_13");
                } 
                catch (Exception c) {
                    c.printStackTrace();
                    System.err.println(c.getClass().getName() + ": " + c.getMessage());
                    System.exit(0);
                }

                try {
                    Statement stmt = conn.createStatement();

                    String updateCommand = "UPDATE sellableInventory SET quantity = quantity - " + quantity
                                + " WHERE productID = \'" + sku + "\'";

                    stmt.executeUpdate(updateCommand);
     
                } 
                catch (Exception b) {
                    JOptionPane.showMessageDialog(null, "Error accessing Database.");
        
                }
                f.dispose();
                managerStocking.main(null);
                
            }
        });


        JLabel header2 = new JLabel("Change Fill Minimum", SwingConstants.CENTER);
        header2.setFont(new Font("Serif", Font.PLAIN, 30));
        header2.setBorder(new EmptyBorder(150, 300, 0, 300));
        header2.setBounds(40, 0, 0, 0);
        header2.setForeground(new Color(80, 0, 0));

        JTextField inventoryField2 = new JTextField("SKU", 40);

        JTextField fillField = new JTextField("Fill Min", 40);

        JButton set = new JButton("Set");
        set.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String sku = inventoryField2.getText();
                String fillMin = fillField.getText();

                Connection conn = null;

                try {
                    Class.forName("org.postgresql.Driver");
                    conn = DriverManager.getConnection("jdbc:postgresql://csce-315-db.engr.tamu.edu/csce315910_13db",
                    "csce315910_13user", "table_13");
                } 
                catch (Exception c) {
                    c.printStackTrace();
                    System.err.println(c.getClass().getName() + ": " + c.getMessage());
                    System.exit(0);
                }

                try {

                    Statement stmt = conn.createStatement();

                    String updateCommand = "UPDATE managerPrefernces SET fillmin = " + fillMin
                                + " WHERE SKU = \'" + sku + "\'";

                    stmt.executeUpdate(updateCommand);
     
                } 
                catch (Exception b) {
                    JOptionPane.showMessageDialog(null, "Error accessing Database.");
        
                }
                f.dispose();
                managerStocking.main(null);

            }
        });
        

        JPanel container = new JPanel();
        String column[] = { "SKU", "Product Name", "Quantity", "Price"};
        final JTable Inventory = new JTable(inventoryData, column);
        Inventory.setCellSelectionEnabled(true);
        ListSelectionModel select = Inventory.getSelectionModel();
        select.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        select.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                String Data = null;
                int[] row = Inventory.getSelectedRows();
                int[] columns = Inventory.getSelectedColumns();
                for (int i = 0; i < row.length; i++) {
                    for (int j = 0; j < columns.length; j++) {
                        Data = (String) Inventory.getValueAt(row[i], columns[j]);
                    }
                }
            }
        });

        JScrollPane spInventory = new JScrollPane(Inventory);

        String columnSuggestions[] = { "Suggestions" };
        final JTable Suggestions = new JTable(suggestionsData, columnSuggestions);
        Suggestions.setCellSelectionEnabled(true);
        ListSelectionModel selectSu = Suggestions.getSelectionModel();
        select.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        select.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                String dataShopping = null;
                int[] row = Suggestions.getSelectedRows();
                int[] columns = Suggestions.getSelectedColumns();
                for (int i = 0; i < row.length; i++) {
                    for (int j = 0; j < columns.length; j++) {
                        dataShopping = (String) Suggestions.getValueAt(row[i], columns[j]);
                    }
                }
            }
        });
        JScrollPane spSuggestions = new JScrollPane(Suggestions);

        // add a title to the Home page
        JLabel header = new JLabel("Layne's Chicken Fingers!", SwingConstants.CENTER);
        header.setFont(new Font("Serif", Font.PLAIN, 40));
        header.setBorder(new EmptyBorder(40, 0, 0, 0));
        header.setBounds(40, 0, 0, 0);
        header.setForeground(new Color(80, 0, 0));

        back.setFont(new Font("Serif", Font.PLAIN, 30));
        back.setBackground(new Color(80, 0, 0));
        back.setOpaque(true);
        back.setBorderPainted(false);
        back.setForeground(Color.white);

        f.add(header, BorderLayout.NORTH);
        header.add(back);

        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // String s = e.getActionCommand();
                ManagerHome.main(null);
                f.dispose();
            }
        });

        
        f.add(spInventory, BorderLayout.WEST);
        f.add(spSuggestions, BorderLayout.EAST);
        f.add(containerOrders, BorderLayout.CENTER);

        containerOrders.add(header1);
        containerOrders.add(inventoryField);
        containerOrders.add(quantityField);
        containerOrders.add(add);
        containerOrders.add(sub);
        containerOrders.add(header2);
        containerOrders.add(inventoryField2);
        containerOrders.add(fillField);
        containerOrders.add(set);

        f.setSize(1500, 1000);
        f.setVisible(true);

        // closing the connection
        try {
            conn.close();
        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Connection NOT Closed.");
        }
    }
}