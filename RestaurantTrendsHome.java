import java.sql.*;
import java.awt.*;
import javax.swing.*;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.EmptyBorder;

public class RestaurantTrendsHome extends JFrame {
    static JFrame f;

    public static void main(String[] args) {

        // create a new frame
        f = new JFrame("Inventory Management System: Restuarant Trends Home");

        // create a bigger container to set Jbuttons side by side
        JPanel containerFinal = new JPanel();
        JPanel containerAlert = new JPanel();
        JPanel container = new JPanel();
        JPanel containerTop = new JPanel();
        JPanel containerBot = new JPanel();

        // create buttons for different portions of what is needed
        JButton OrderPopularity = new JButton("Order Popularity");

        OrderPopularity.setFont(new Font("Serif", Font.PLAIN, 30)); 
        OrderPopularity.setBackground(new Color(80, 0, 0));
        OrderPopularity.setOpaque(true);
        OrderPopularity.setBorderPainted(false);
        OrderPopularity.setForeground(Color.white);

        JButton InventoryUsage = new JButton("Inventory Usage");

        InventoryUsage.setFont(new Font("Serif", Font.PLAIN, 30)); 
        InventoryUsage.setBackground(new Color(80, 0, 0));
        InventoryUsage.setOpaque(true);
        InventoryUsage.setBorderPainted(false);
        InventoryUsage.setForeground(Color.white);

        JButton OrderTrends = new JButton("Ordering Trends");

        OrderTrends.setFont(new Font("Serif", Font.PLAIN, 30)); 
        OrderTrends.setBackground(new Color(80, 0, 0));
        OrderTrends.setOpaque(true);
        OrderTrends.setBorderPainted(false);
        OrderTrends.setForeground(Color.white);

        JButton inventoryPop = new JButton("Inventory Popularity");

        inventoryPop.setFont(new Font("Serif", Font.PLAIN, 30)); 
        inventoryPop.setBackground(new Color(80, 0, 0));
        inventoryPop.setOpaque(true);
        inventoryPop.setBorderPainted(false);
        inventoryPop.setForeground(Color.white);


        //make new fonts for all of the buttons
        OrderPopularity.setFont(new Font("Serif", Font.PLAIN, 25));
        InventoryUsage.setFont(new Font("Serif", Font.PLAIN, 25));
        OrderTrends.setFont(new Font("Serif", Font.PLAIN, 25));
        inventoryPop.setFont(new Font("Serif", Font.PLAIN, 25));

        // take to new jframe for each button
        OrderPopularity.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JTextField startDate = new JTextField(15);
                JTextField endDate = new JTextField(15);


                JPanel addItemPanel = new JPanel();
                //new add itemPanel with entries 
                addItemPanel.setLayout(new GridLayout(2,1));
                addItemPanel.add(new JLabel("Start Date (YYYY-MM-DD)"));
                addItemPanel.add(startDate);
                addItemPanel.add(new JLabel("Ending Date (YYYY-MM-DD)"));
                addItemPanel.add(endDate);
                
                // UIManager.put("OptionPane.minimumSize", new Dimension(500, 500));
                Integer yes = JOptionPane.showConfirmDialog(null, addItemPanel);
                
                if(yes == 0){
                    String startDt = startDate.getText();
                    String endDt = endDate.getText();

                    String [] arr = {startDt, endDt};
                    orderPopularity.main(arr);
                    f.dispose();

                }else{
                    RestaurantTrendsHome.main(null);
                    f.dispose();
                }
            }
        });

        InventoryUsage.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JTextField startDate = new JTextField(15);
                JTextField endDate = new JTextField(15);


                JPanel addItemPanel = new JPanel();
                //new add itemPanel with entries 
                addItemPanel.setLayout(new GridLayout(2,1));
                addItemPanel.add(new JLabel("Start Date (YYYY-MM-DD)"));
                addItemPanel.add(startDate);
                addItemPanel.add(new JLabel("Ending Date (YYYY-MM-DD)"));
                addItemPanel.add(endDate);
                
                // UIManager.put("OptionPane.minimumSize", new Dimension(500, 500));
                Integer yes = JOptionPane.showConfirmDialog(null, addItemPanel);
                
                if(yes == 0){
                    String startDt = startDate.getText();
                    String endDt = endDate.getText();

                    String [] arr = {startDt, endDt};
                    InventoryUsageChart.main(arr);
                    f.dispose();

                }else{
                    RestaurantTrendsHome.main(null);
                    f.dispose();
                }
                f.dispose();
            }
        });

        OrderTrends.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                orderTrends.main(null);
                f.dispose();
            }
        });

        inventoryPop.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JTextField startDate = new JTextField(15);
                JTextField endDate = new JTextField(15);


                JPanel addItemPanel = new JPanel();
                //new add itemPanel with entries 
                addItemPanel.setLayout(new GridLayout(2,1));
                addItemPanel.add(new JLabel("Start Date (YYYY-MM-DD)"));
                addItemPanel.add(startDate);
                addItemPanel.add(new JLabel("Ending Date (YYYY-MM-DD)"));
                addItemPanel.add(endDate);
                
                // UIManager.put("OptionPane.minimumSize", new Dimension(500, 500));
                Integer yes = JOptionPane.showConfirmDialog(null, addItemPanel);
                
                if(yes == 0){
                    String startDt = startDate.getText();
                    String endDt = endDate.getText();

                    String [] arr = {startDt, endDt};
                    inventoryPopularity.main(arr);
                    f.dispose();

                }else{
                    RestaurantTrendsHome.main(null);
                    f.dispose();
                }
                f.dispose();
            }
        });

        // resize buttons so they fit screen
        OrderPopularity.setPreferredSize(new Dimension(250, 200));
        OrderTrends.setPreferredSize(new Dimension(250, 200));
        InventoryUsage.setPreferredSize(new Dimension(250, 200));
        inventoryPop.setPreferredSize(new Dimension(250, 200));

        // container layout placement
        containerTop.add(OrderPopularity);
        containerTop.add(Box.createRigidArea(new Dimension(60, 0)));
        containerTop.add(OrderTrends);

        containerBot.add(InventoryUsage);
        containerBot.add(Box.createRigidArea(new Dimension(60, 0)));
        containerBot.add(inventoryPop);

        container.setLayout(new GridLayout(2, 1));
        container.add(containerBot, 0);
        container.add(containerTop, 0);

        containerFinal.add(container);
        f.add(containerFinal);

        // set the size of frame
        f.setSize(1500, 1000);
        f.setVisible(true);

        // add a title to the Restaurant Trends Home page
        JLabel header = new JLabel("Layne's Chicken Fingers! (Restaurant Trends)", SwingConstants.CENTER);
        header.setFont(new Font("Serif", Font.PLAIN, 40));
        header.setBorder(new EmptyBorder(40, 0, 80, 0));
        header.setBounds(40, 0, 0, 0);
        header.setForeground(new Color(80, 0, 0));

        // add button to go back to manager Home screen
        JButton back = new JButton("BACK");
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ManagerHome.main(null);
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
}