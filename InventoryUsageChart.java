import java.sql.*;
import java.util.*;
import java.text.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableColumnModel;
import java.io.*;
import java.lang.module.ResolutionException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class InventoryUsageChart extends JFrame {
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

                    Integer ct = 0;
                    HashMap<String, Integer> prodMap = new HashMap<>();
                    
                    //iterates through the previous hashmap and now gets the total underlying product quant * # of orders
                    for(String key : mapItems.keySet()){
                        ResultSet getProdQuant;

                        String getProdQ = "SELECT productid,quantofunderlyingprod FROM sellableinvorderrelation WHERE itemid = '" + key + "';";

                        getProdQuant = stmt.executeQuery(getProdQ);

                        String prodID = "";
                        Integer quantofProdID = 0;

                        while(getProdQuant.next()){
                            prodID = getProdQuant.getString("productid");

                            quantofProdID = getProdQuant.getInt("quantofunderlyingprod");
                            Integer finalQuantofProdID = mapItems.get(key) * quantofProdID;

                            if(prodMap.containsKey(prodID)){
                                Integer veryFinalQuantofPID = prodMap.get(prodID) + finalQuantofProdID;
                                prodMap.put(prodID, veryFinalQuantofPID);
                            }else{
                                prodMap.put(prodID, finalQuantofProdID);
                            }
                        }
                    
                        ct++;
                    }

                    HashMap<String, Double> invFinalStats = new HashMap<>();
                    //final hash map used to get sku and base product and do calculations to get final values
                    for(String key : prodMap.keySet()){
                        String getFinalConversions = "SELECT sku,amountofbaseproduct FROM salestoinventoryconversion WHERE productid = '" + key + "';";

                        ResultSet finalConv = stmt.executeQuery(getFinalConversions);

                        while(finalConv.next()){
                            String sku = finalConv.getString("sku");
                            Double finAmtUsed = finalConv.getDouble("amountofbaseproduct") * prodMap.get(key);

                            if(sku.equalsIgnoreCase("d2013")){
                                Double skuDrinkAmt = finAmtUsed / 6;

                                for(int i = 2013; i < 2019; i++){
                                    String skuDrink = "d" + i;
                                    if(invFinalStats.containsKey(skuDrink)){
                                        Double veryFinalAmtUsed = invFinalStats.get(skuDrink) + skuDrinkAmt;
                                        invFinalStats.put(skuDrink, veryFinalAmtUsed);
                                    }else{
                                        invFinalStats.put(skuDrink, skuDrinkAmt);
                                    }
                                }
                                continue;
                            }

                            if(sku.equalsIgnoreCase("d2019")){
                                Double skuDrinkAmt = finAmtUsed / 4;

                                for(int i = 2019; i < 2023; i++){
                                    String skuDrink = "d" + i;
                                    if(invFinalStats.containsKey(skuDrink)){
                                        Double veryFinalAmtUsed = invFinalStats.get(skuDrink) + skuDrinkAmt;
                                        invFinalStats.put(skuDrink, veryFinalAmtUsed);
                                    }else{
                                        invFinalStats.put(skuDrink, skuDrinkAmt);
                                    }
                                }
                                continue;
                            }

                            if(invFinalStats.containsKey(sku)){
                                Double veryFinalAmtUsed = invFinalStats.get(sku) + finAmtUsed;
                                invFinalStats.put(sku, veryFinalAmtUsed);
                            }else{
                                invFinalStats.put(sku, finAmtUsed);
                            }
                        }
                    }
                    

                    Object[][] finalTableResults = new Object[invFinalStats.size()][3];
                    //puts final values into static array to use with JTable later
                    int ctr = 0;
                    for(String key : invFinalStats.keySet()){
                        finalTableResults[ctr][0] = key;

                        String getDescr = "SELECT productname,deliveredby FROM sellableinventory WHERE productid = '" + key + "';";

                        ResultSet finalDescr = stmt.executeQuery(getDescr);

                        String prodDescr = "";
                        String prodContainer = "";

                        while(finalDescr.next()){
                            prodDescr = finalDescr.getString("productname");
                            prodContainer = finalDescr.getString("deliveredby");
                        }

                        finalTableResults[ctr][1] = prodDescr;


                        String FormattedString = String.format("%.4f", invFinalStats.get(key));
                        finalTableResults[ctr][2] = FormattedString + " " + prodContainer + "(s)";

                        ctr++;
                    }
                    
                    JPanel mainPanel = new JPanel();

                    f = new JFrame("Inventory Management System : Inventory Usage Chart");

                    String[] columnNames = { "sku", "product name", "Quantity of Item Used"};
                    JTable table = new JTable(finalTableResults, columnNames);

                    //set table dimensions
                    TableColumnModel columnModel = table.getColumnModel();
                    columnModel.getColumn(0).setPreferredWidth(10);
                    columnModel.getColumn(1).setPreferredWidth(70);
                    columnModel.getColumn(2).setPreferredWidth(40);

                    table.setRowHeight(40);

                    // create a scroll pane
                    JScrollPane sp = new JScrollPane(table);
                    table.setFillsViewportHeight(true);
                    //add table to the main panel
                    mainPanel.add(sp);

                    // // // set the size of frame
                    f.setSize(1500, 1000);
                    f.setVisible(true);

                    // // add a title to the Home page
                    String title = "Inventory Usage Chart from " + date1 + " to " + date2;

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
        InventoryUsageChart IUChart = new InventoryUsageChart();
        IUChart.init(args[0], args[1]);
    }

}