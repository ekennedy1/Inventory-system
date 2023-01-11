import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Scanner;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.lang.module.ResolutionException;
import java.util.Date;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class orderPopularity extends JFrame {
    static JFrame f;
    Integer itemSize;

    //first check of whether or not Date is valid
    public boolean checkDate(String date){
        Scanner sc = new Scanner(date);
        sc.useDelimiter("-");

        String nxt = sc.next();
        if(nxt.length() != 4){
            return false;
        }

        nxt = sc.next();
        if(nxt.length() != 2){
            return false;
        }

        nxt = sc.next();
        if(nxt.length() != 2){
            return false;
        }

        return true;
    }


    public void init(String date1, String date2){
        //lots of date checks to ensure they are valid
        if(checkDate(date1) && checkDate(date2)){
            try{
                //connect to db
                Connection conn = null;
                Class.forName("org.postgresql.Driver");
                conn = DriverManager.getConnection("jdbc:postgresql://csce-315-db.engr.tamu.edu/csce315910_13db",
                "csce315910_13user", "table_13");

                Statement stmt = conn.createStatement();
                ResultSet rsCheck;
                ResultSet rsCheck2;

                String checkIfValidDate = "SELECT MAX(entryid) FROM dailysales WHERE date = '"+ date1 + "';";
                rsCheck = stmt.executeQuery(checkIfValidDate);

                String ValidDate1 = "";

                while(rsCheck.next()){
                    ValidDate1 = rsCheck.getString("max");
                }

                String checkIfValidDate2 = "SELECT MAX(entryid) FROM dailysales WHERE date = '"+ date2 + "';";
                rsCheck2 = stmt.executeQuery(checkIfValidDate2);

                String ValidDate2 = "";

                while(rsCheck2.next()){
                    ValidDate2 = rsCheck2.getString("max");
                }

                //check to ensure that date1 < date2
                Integer check = date1.compareToIgnoreCase(date2);

                //final checks to ensure that the dates are actually withint the database
                if((check < 0 || check == 0) && ValidDate1 != null && ValidDate2 != null){

                    String iterateThroughDailySales = "SELECT * FROM dailysales WHERE (date >= '" + date1 + 
                    "' AND date <= '" +  date2 + "');";

                    //use hash map to ensure that we only have one of each item id
                    Map<String, Integer> mapItems = new HashMap<>();

                    ResultSet rs;
                    rs = stmt.executeQuery(iterateThroughDailySales);

                    //get itemid and quantity from dailysales table
                    while(rs.next()){
                        String itemIDString;
                        itemIDString = rs.getString("itemid");

                        Integer quantOfItemID;
                        quantOfItemID = rs.getInt("quantity");

                        if(mapItems.containsKey(itemIDString)){
                            Integer adder = mapItems.get(itemIDString);
                            mapItems.put(itemIDString, adder + quantOfItemID);
                        }else{
                            mapItems.put(itemIDString, quantOfItemID);
                        }
                    }
                    
                    Integer sizeOfMap = mapItems.size();
                    Object[][] orderData = new Object[sizeOfMap][4];

                    Integer ct = 0;
                    
                    //sort hash map by value in descending order
                    LinkedHashMap<String, Integer> reverseMap = new LinkedHashMap<>();
                    mapItems.entrySet()
                        .stream()
                        .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                        .forEachOrdered(x-> reverseMap.put(x.getKey(), x.getValue()));

                    
                    for(String key : reverseMap.keySet()){
                        ResultSet rsName;
                        ResultSet rsPrice;

                        String getItemName = "SELECT * FROM menu WHERE itemid = '" + key + "';";

                        rsName = stmt.executeQuery(getItemName);

                        String itemName = "";

                        while(rsName.next()){
                            itemName = rsName.getString("name");
                        }

                        String getPrice = "SELECT MAX(menuprice) FROM sellableinvorderrelation WHERE itemid = '" + key + "';";
                        rsPrice = stmt.executeQuery(getPrice);

                        Double itemPrice = 0.0;

                        while(rsPrice.next()){
                            itemPrice = rsPrice.getDouble("max");
                        }
                        
                        //insert the data into a static array so we can make a table later
                        orderData[ct][0] = key;
                        orderData[ct][1] = itemName;
                        orderData[ct][2] = mapItems.get(key);

                        Double finVal = itemPrice * mapItems.get(key);
                        orderData[ct][3] = String.format("%.2f", finVal);
                        
                        ct++;
                    }
                    
                    JPanel mainPanel = new JPanel();
                    // mainPanel.setBorder(new EmptyBorder(40, 0, 0, 0));

                    String[] columnNames = { "Item ID", "Name", "Quantity", "Total" };
                    JTable table = new JTable(orderData, columnNames);

                    //set table dimensions
                    TableColumnModel columnModel = table.getColumnModel();
                    columnModel.getColumn(0).setPreferredWidth(10);
                    columnModel.getColumn(1).setPreferredWidth(70);
                    columnModel.getColumn(2).setPreferredWidth(40);
                    columnModel.getColumn(3).setPreferredWidth(40);

                    table.setRowHeight(40);

                    // create a scroll pane
                    JScrollPane sp = new JScrollPane(table);
                    table.setFillsViewportHeight(true);
                    //add table to the main panel
                    mainPanel.add(sp);


                    f = new JFrame("Inventory Management System : Ordering Popularity");

                    // // // set the size of frame
                    f.setSize(1500, 1000);
                    f.setVisible(true);

                    // // add a title to the Home page
                    String title = "Order Popularity from " + date1 + " to " + date2;

                    //add header
                    JLabel header = new JLabel(title, SwingConstants.CENTER);
                    header.setFont(new Font("Serif", Font.PLAIN, 40));
                    header.setBorder(new EmptyBorder(40, 0, 50, 0));
                    header.setBounds(40, 0, 0, 0);
                    header.setForeground(new Color(80, 0, 0));

                    JButton homeButton = new JButton("BACK");
                    homeButton.setBounds(0, 0, 100, 40);
                    homeButton.setFont(new Font("Serif", Font.PLAIN, 15));
                    homeButton.setBackground(new Color(80, 0, 0));
                    homeButton.setOpaque(true);
                    homeButton.setBorderPainted(false);
                    homeButton.setForeground(Color.white);

                    //home button command
                    homeButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            // String s = e.getActionCommand();
                            RestaurantTrendsHome.main(null);
                            f.dispose();
                        }
                    });

                    f.add(header, BorderLayout.NORTH);
                    f.add(mainPanel);
                    header.add(homeButton);

                    conn.close();

                }else{
                    //if the checks don't pass we go back to the restaurant trends page
                    RestaurantTrendsHome.main(null);
                }

            } catch (Exception e) {}
        }

    }

    public static void main(String[] args) {
        //take in arguments from Restaurant Trends Home page
        orderPopularity orderPop = new orderPopularity();
        orderPop.init(args[0], args[1]);
    }

}