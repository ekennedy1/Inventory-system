import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
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

public class orderTrends extends JFrame {
    static JFrame f;

    static JPanel jp;

    static JComboBox<String> c1;
    static JComboBox<String> c2;
    static JComboBox<String> c3;
    static JComboBox<String> c4;
    static JComboBox<String> c5;
    static JComboBox<String> c6;
    static JComboBox<String> c7;
    static JComboBox<String> c8;
    static JComboBox<String> c9;
    static JComboBox<String> c10;
    static JComboBox<String> c11;
    static JComboBox<String> c12;

    public void init(){

        // JFrame
        f = new JFrame("Order Trends");

        // Arrays for JComboBoxes
        String months[] = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
        String days[] = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18",
                         "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
        String years[] = {"2022"};
        
        // Create JComboBoxes
        c1 = new JComboBox<>(months);
        c2 = new JComboBox<>(days);
        c3 = new JComboBox<>(years);
        c4 = new JComboBox<>(months);
        c5 = new JComboBox<>(days);
        c6 = new JComboBox<>(years);
        c7 = new JComboBox<>(months);
        c8 = new JComboBox<>(days);
        c9 = new JComboBox<>(years);
        c10 = new JComboBox<>(months);
        c11 = new JComboBox<>(days);
        c12 = new JComboBox<>(years);

        // Create JPanel
        jp = new JPanel();

        // Create JLabels for boxes
        JLabel boxLabel = new JLabel("Date Range 1: ");
        JLabel boxLabel2 = new JLabel("  to  ");
        JLabel boxLabel3 = new JLabel("           Date Range 2: ");
        JLabel boxLabel4 = new JLabel("  to  ");

        // Create done button
        JButton doneButton = new JButton("DONE");
        doneButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Get dates from Jboxes
                String startDate1 = (String) c3.getSelectedItem() + "-" + (String) c1.getSelectedItem() + "-" + (String) c2.getSelectedItem();
                String endDate1 = (String) c6.getSelectedItem() + "-" + (String) c4.getSelectedItem() + "-" + (String) c5.getSelectedItem();
                String startDate2 = (String) c9.getSelectedItem() + "-" + (String) c7.getSelectedItem() + "-" + (String) c8.getSelectedItem();
                String endDate2 = (String) c12.getSelectedItem() + "-" + (String) c10.getSelectedItem() + "-" + (String) c11.getSelectedItem();
               
                // Get ID starts to parse through table
                int startID1 = getIdStart(startDate1);
                int endID1 = getIdEnd(endDate1);
                int startID2 = getIdStart(startDate2);
                int endID2 = getIdEnd(endDate2);

                // Get charts
                getChart(startID1, endID1, startID2, endID2, startDate1 + " to " + endDate1, startDate2 + " to " + endDate2);
            
            }
        });

        // Add components to JPanel
        jp.add(boxLabel);
        jp.add(c1);
        jp.add(c2);
        jp.add(c3);
        jp.add(boxLabel2);
        jp.add(c4);
        jp.add(c5);
        jp.add(c6);
        jp.add(boxLabel3);
        jp.add(c7);
        jp.add(c8);
        jp.add(c9);
        jp.add(boxLabel4);
        jp.add(c10);
        jp.add(c11);
        jp.add(c12);
        jp.add(doneButton);

        // Add Jpanel to JFrame
        f.add(jp);

        // set the size of frame
        f.setSize(1500, 1000);
        f.setVisible(true);

        // add a title
        JLabel header = new JLabel("Layne's Chicken Fingers!", SwingConstants.CENTER);
        header.setFont(new Font("Serif", Font.PLAIN, 40));
        header.setBorder(new EmptyBorder(40, 0, 40, 0));
        header.setBounds(40, 0, 0, 0);
        header.setForeground(new Color(80, 0, 0));

        // add button to go back to home screen
        JButton back = new JButton("BACK");
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                RestaurantTrendsHome.main(null);
                f.dispose();
            }
        });

        back.setBounds(5, 5, 150, 40);
        back.setFont(new Font("Serif", Font.PLAIN, 30));
        back.setBounds(5,5,150,40);
        back.setFont(new Font("Serif", Font.PLAIN, 30)); 
        back.setBackground(new Color(80, 0, 0));
        back.setOpaque(true);
        back.setBorderPainted(false);
        back.setForeground(Color.white);
        back.setFont(new Font("Serif", Font.PLAIN, 30));

        header.add(back);

        f.add(header, BorderLayout.NORTH);
    }

    public int getIdStart(String date){
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

        // Find start of time period in database
        int entryIdStart = -1;
        try {
            // create a statement object
            Statement stmt = conn.createStatement();

            // create an SQL statement
            String sqlStatement = "SELECT * FROM dailysales WHERE date='" + date + "' LIMIT 1;";
            // send statement to DBMS
            ResultSet result = stmt.executeQuery(sqlStatement);

            while (result.next()) {
                entryIdStart = result.getInt("entryID");
            }
        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error accessing Database START.");
        }


        // closing the connection
        try {
            conn.close();
        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Connection NOT Closed.");
        }

        return entryIdStart;
    }

    public int getIdEnd(String date){
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

        // Find end of time period in database
        int entryIdEnd = -1;
        try {
            // create a statement object
            Statement stmt = conn.createStatement();

            // create an SQL statement
            String sqlStatement = "SELECT * FROM dailysales WHERE date='" + date + "';";
            // send statement to DBMS
            ResultSet result = stmt.executeQuery(sqlStatement);

            while (result.next()) {
                entryIdEnd = result.getInt("entryID");
            }
        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error accessing Database. END");
        }

        // closing the connection
        try {
            conn.close();
        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Connection NOT Closed.");
        }

        return entryIdEnd;
    }

    public void getChart(int start1, int end1, int start2, int end2, String date1, String date2){
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

        // Array to hold table Data
        Object[][] tableData = new Object[19][6];

        // Get data from database from time period 1
        try {
            // create a statement object
            Statement stmt = conn.createStatement();

            // create an SQL statement
            String sqlStatement = "SELECT * FROM dailysales WHERE entryid::integer >= " + start1 + 
                                    " AND entryid::integer <= " + end1 + ";";
            // send statement to DBMS
            ResultSet result = stmt.executeQuery(sqlStatement);

            int i = 0;
            while (result.next()) {
                tableData[i][1]= result.getFloat("total");
                i++;

                //check for i 
                if (i > 18){
                    i = 0;
                }
            }
        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error accessing Database. TABLE");
        }

        // Get data from database from time period 2
        try {
            // create a statement object
            Statement stmt = conn.createStatement();

            // create an SQL statement
            String sqlStatement = "SELECT * FROM dailysales WHERE entryid::integer >= " + start2 + 
                                    " AND entryid::integer <= " + end2 + ";";
            // send statement to DBMS
            ResultSet result = stmt.executeQuery(sqlStatement);

            int i = 0;
            while (result.next()) {
                tableData[i][3] = result.getFloat("total");
                i++;

                //check for i 
                if (i > 18){
                    i = 0;
                }
            }
        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error accessing Database. table2");
        }

        // Populate array id names and get totals for sales
        float total1 = 0;
        float total2 = 0;
        int idNum = 501;
        for (int i = 0; i < 19; i++){
            tableData[i][0] = idNum;
            total1 += (float)tableData[i][1];
            total2 += (float)tableData[i][3];
            idNum ++;
        }

        // Find percentage of sales
        for (int i = 0; i < 19; i++){
            tableData[i][2] =  (float) tableData[i][1] / total1;
            tableData[i][4] =  (float) tableData[i][3] / total2;
        }

        // make lists for uptrend and downtrend
        DefaultListModel<String> uptrend = new DefaultListModel<>();
        uptrend.addElement("Uptrending Items");
        DefaultListModel<String> downtrend = new DefaultListModel<>();
        downtrend.addElement("Downtrending Items");

        // get percents of sales
        for (int i = 0; i < 19; i++){
            tableData[i][2] =  (float) tableData[i][1] / total1 * 100;
            tableData[i][4] =  (float) tableData[i][3] / total2 * 100;

            // check for uptrend or downtrend
            if ((float)tableData[i][4] > (float)tableData[i][2]){
                uptrend.addElement(String.valueOf(tableData[i][0]));
            }
            else if ((float)tableData[i][4] < (float)tableData[i][2]){
                downtrend.addElement(String.valueOf(tableData[i][0]));
            }

            tableData[i][5] = (float)tableData[i][4] - (float)tableData[i][2];
        }

        //  Make array for JTable colulmn names
        String[] colNames = {"Item ID", date1, "Percent of Sales", date2, "Percent of Sales", "Percent Change"};

        // Make Jtable
        JTable table = new JTable(tableData, colNames);

        // Add table to scroll pane
        JScrollPane sp = new JScrollPane(table);
        sp.setPreferredSize(new Dimension(600,400));
        JPanel jp2 = new JPanel();
        jp2.setBorder(new EmptyBorder(25,25,0,25));
        jp2.setLayout(new BoxLayout(jp2, BoxLayout.X_AXIS));
        jp2.add(sp);

        // Make JLists
        JList<String> upList = new JList<>(uptrend);
        JList<String> downList = new JList<>(downtrend);

        // add lists to panel
        JPanel listPanel = new JPanel();
        listPanel.add(upList);
        listPanel.add(downList);
        jp2.add(listPanel);

        // add all panels to main
        JPanel mainJP = new JPanel();
        mainJP.setLayout(new BoxLayout(mainJP, BoxLayout.Y_AXIS));
        mainJP.add(jp);
        mainJP.add(jp2);

        // add panel to frame
        f.add(mainJP);
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

    public static void main(String[] args) {
        orderTrends trends = new orderTrends();
        trends.init();
    }
}