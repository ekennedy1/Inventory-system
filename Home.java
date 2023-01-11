import java.sql.*;
import java.awt.*;
import javax.swing.*;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class Home extends JFrame {
    static JFrame f;

    public static void main(String[] args) {


        f = new JFrame("Inventory Management System: Home");

    
        JPanel container = new JPanel();
        
        //buttons for the manager and server side of GUI
        JButton manager = new JButton("Manager");
        JButton server = new JButton("Server");


        //setting dimensions
        manager.setPreferredSize(new Dimension(400, 250));
        server.setPreferredSize(new Dimension(400, 250));
        manager.setFont(new Font("Serif", Font.PLAIN, 30));
        server.setFont(new Font("Serif", Font.PLAIN, 30));
        manager.setBackground(new Color(80, 0, 0));
        manager.setOpaque(true);
        manager.setBorderPainted(false);
        manager.setForeground(Color.white);


        server.setBackground(new Color(80, 0, 0));
        server.setOpaque(true);
        server.setBorderPainted(false);
        server.setForeground(Color.white);

        //button action for server
        server.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MealsGUI.main(null);
                f.dispose();
            }
        });

        // clicking manager takes you to manager GUI
        manager.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // String s = e.getActionCommand();
                ManagerHome.main(null);
                f.dispose();
            }
        });

        //setting dimensions for aesthetics
        container.setBorder(new EmptyBorder(170, 0, 0, 0));
        container.setPreferredSize(new Dimension(100, 100));


        container.add(manager);
        container.add(Box.createRigidArea(new Dimension(100, 0)));
        container.add(server);

        // add panel to frame
        f.add(container);

        // set the size of frame
        f.setSize(1500, 1000);
        f.setVisible(true);

        JLabel header = new JLabel("Layne's Chicken Fingers!", SwingConstants.CENTER);
        header.setFont(new Font("Serif", Font.PLAIN, 40));
        header.setBorder(new EmptyBorder(40, 0, 0, 0));
        header.setBounds(40, 0, 0, 0);
        header.setForeground(new Color(80, 0, 0));
        f.add(header, BorderLayout.NORTH);

    }

    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        if (s.equals("Close")) {
            f.dispose();
        } else if (s.equals("Server")) {
            f.dispose();
        }
    }
}