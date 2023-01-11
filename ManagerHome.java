import java.sql.*;
import java.awt.*;
import javax.swing.*;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.EmptyBorder;

public class ManagerHome extends JFrame {
    static JFrame f;

    public static void main(String[] args) {

        // create a new frame
        f = new JFrame("Inventory Management System: Manager Home");

        JPanel containerFinal = new JPanel();
        JPanel containerAlert = new JPanel();
        JPanel container = new JPanel();
        JPanel containerTop = new JPanel();
        JPanel containerBot = new JPanel();

        //four different buttons
        JButton inventory = new JButton("Inventory");

        inventory.setFont(new Font("Serif", Font.PLAIN, 30)); 
        inventory.setBackground(new Color(80, 0, 0));
        inventory.setOpaque(true);
        inventory.setBorderPainted(false);
        inventory.setForeground(Color.white);

        JButton trend = new JButton("Restaurant Trends");

        trend.setFont(new Font("Serif", Font.PLAIN, 30)); 
        trend.setBackground(new Color(80, 0, 0));
        trend.setOpaque(true);
        trend.setBorderPainted(false);
        trend.setForeground(Color.white);

        JButton orderSummary = new JButton("Order Summaries");

        orderSummary.setFont(new Font("Serif", Font.PLAIN, 30)); 
        orderSummary.setBackground(new Color(80, 0, 0));
        orderSummary.setOpaque(true);
        orderSummary.setBorderPainted(false);
        orderSummary.setForeground(Color.white);

        JButton stocking = new JButton("<html><center>Restocking<br/>and<br/>Destocking</center></html>");

        stocking.setFont(new Font("Serif", Font.PLAIN, 30)); 
        stocking.setBackground(new Color(80, 0, 0));
        stocking.setOpaque(true);
        stocking.setBorderPainted(false);
        stocking.setForeground(Color.white);

        inventory.setFont(new Font("Serif", Font.PLAIN, 25));
        trend.setFont(new Font("Serif", Font.PLAIN, 25));
        orderSummary.setFont(new Font("Serif", Font.PLAIN, 25));
        stocking.setFont(new Font("Serif", Font.PLAIN, 25));

        //add actions for each button
        inventory.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                managerInventoryOptions.main(null);
                f.dispose();
            }
        });

        trend.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                RestaurantTrendsHome.main(null);
                f.dispose();
            }
        });

        orderSummary.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                managerOrderSummary.main(null);
                f.dispose();
            }
        });

        stocking.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                managerStocking.main(null);
                f.dispose();
            }
        });

        // resize buttons so they fit screen
        inventory.setPreferredSize(new Dimension(250, 250));
        orderSummary.setPreferredSize(new Dimension(250, 250));
        trend.setPreferredSize(new Dimension(250, 250));
        stocking.setPreferredSize(new Dimension(250, 250));

        // container layout placement
        containerTop.add(inventory);
        containerTop.add(Box.createRigidArea(new Dimension(60, 0)));
        containerTop.add(orderSummary);

        containerBot.add(trend);
        containerBot.add(Box.createRigidArea(new Dimension(60, 0)));
        containerBot.add(stocking);

        container.setLayout(new GridLayout(2, 1));
        container.add(containerBot, 0);
        container.add(containerTop, 0);

        // alerts portion
        containerAlert.setPreferredSize(new Dimension(210, 550));
        JLabel alertLabel = new JLabel("Alerts");
        alertLabel.setFont(new Font("Serif", Font.PLAIN, 25));
        containerAlert.setBorder(BorderFactory.createLineBorder(new Color(80, 0, 0)));
        containerAlert.setBackground(Color.white);
        containerAlert.add(alertLabel);

        containerFinal.add(containerAlert);
        containerFinal.add(Box.createRigidArea(new Dimension(150, 0)));
        containerFinal.add(container);
        f.add(containerFinal);

        f.setSize(1500, 1000);
        f.setVisible(true);

        // add a title to the Manager Home page
        JLabel header = new JLabel("Layne's Chicken Fingers!", SwingConstants.CENTER);
        header.setFont(new Font("Serif", Font.PLAIN, 40));
        header.setBorder(new EmptyBorder(40, 0, 40, 0));
        header.setBounds(40, 0, 0, 0);
        header.setForeground(new Color(80, 0, 0));

        // add button to go back to home screen
        JButton back = new JButton("Home");
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Home.main(null);
                f.dispose();
            }
        });

        //back button dimension
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