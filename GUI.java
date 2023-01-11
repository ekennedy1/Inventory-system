import java.sql.*;
import java.awt.event.*;
import javax.swing.*;

public class GUI extends JFrame implements ActionListener {
    static JFrame f;

    public static void main(String[] args) {

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
        JOptionPane.showMessageDialog(null, "Opened database successfully");

        String name = "Product IDs\n";
        try {
            // create a statement object
            Statement stmt = conn.createStatement();

            // create an SQL statement
            String sqlStatement = "SELECT * FROM sellableInventory LIMIT 10;";
            // send statement to DBMS
            ResultSet result = stmt.executeQuery(sqlStatement);

            while (result.next()) {
                name += result.getString("productID") + "\n";
            }
        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error accessing Database.");

        }

        // create a new frame
        f = new JFrame("Inventory Management System");

        // create a panel
        JPanel p = new JPanel();

        JTextArea area = new JTextArea(name);

        area.setBounds(10, 30, 200, 200);

        f.add(area);

        // add panel to frame
        f.add(p);

        // set the size of frame
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

    // if button is pressed
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        if (s.equals("Close")) {
            f.dispose();
        }
    }
}