import java.sql.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class managerInventoryOptions extends JFrame {
    static JFrame f;

    public static void main(String[] args) {
        f = new JFrame("Inventory Management System: Manager Inventory");

        JPanel container = new JPanel();
        JPanel containerAlert = new JPanel();

        // creates objects for inventory screen
        JButton sellableInventory = new JButton("Sellable Inventory");
        JButton unsellableInventory = new JButton("Unsellable Inventory");
        JButton back = new JButton("Back");
        JTextArea area = new JTextArea("Alerts go here");

        area.setBounds(0, 0, 100, 40);

        back.setBounds(5, 5, 150, 40);

        //alert dimensions
        containerAlert.setPreferredSize(new Dimension(210, 550));
        JLabel alertLabel = new JLabel("Alerts");
        alertLabel.setFont(new Font("Serif", Font.PLAIN, 25));
        containerAlert.setBorder(BorderFactory.createLineBorder(new Color(80, 0, 0)));
        containerAlert.setBackground(Color.white);
        containerAlert.add(alertLabel);
        containerAlert.setBounds(15,170,200,550);

        // sets properties of inventory buttons
        sellableInventory.setPreferredSize(new Dimension(400, 250));
        unsellableInventory.setPreferredSize(new Dimension(400, 250));

        sellableInventory.setFont(new Font("Serif", Font.PLAIN, 30));
        sellableInventory.setBackground(new Color(80, 0, 0));
        sellableInventory.setOpaque(true);
        sellableInventory.setBorderPainted(false);
        sellableInventory.setForeground(Color.white);

        unsellableInventory.setFont(new Font("Serif", Font.PLAIN, 30));
        unsellableInventory.setBackground(new Color(80, 0, 0));
        unsellableInventory.setOpaque(true);
        unsellableInventory.setBorderPainted(false);
        unsellableInventory.setForeground(Color.white);

        //back button bounds
        back.setFont(new Font("Serif", Font.PLAIN, 30));
        back.setBackground(new Color(80, 0, 0));
        back.setOpaque(true);
        back.setBorderPainted(false);
        back.setForeground(Color.white);

        //buttons for the 
        unsellableInventory.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // String s = e.getActionCommand();
                GUI_Nonsellable.main(null);
                f.dispose();
            }
        });

        sellableInventory.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // String s = e.getActionCommand();
                GUI_Sellable.main(null);
                f.dispose();
            }
        });

        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // String s = e.getActionCommand();
                ManagerHome.main(null);
                f.dispose();
            }
        });

        // add buttons to the larger container
        container.setBorder(new EmptyBorder(170, 0, 0, 0));
        container.setPreferredSize(new Dimension(100, 100));

        f.add(containerAlert);
        container.add(sellableInventory);
        container.add(Box.createRigidArea(new Dimension(100, 0)));
        container.add(unsellableInventory);

        f.add(container);

        f.setSize(1500, 1000);
        f.setVisible(true);

        // add a title to the Home page
        JLabel header = new JLabel("Layne's Chicken Fingers!", SwingConstants.CENTER);
        header.setFont(new Font("Serif", Font.PLAIN, 40));
        header.setBorder(new EmptyBorder(40, 0, 0, 0));
        header.setBounds(40, 0, 0, 0);
        header.setForeground(new Color(80, 0, 0));

        f.add(header, BorderLayout.NORTH);
        header.add(back);

    }

    // if button is pressed
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        if (s.equals("Close")) {
            f.dispose();
        }
    }
}
