
import java.sql.*;
import java.util.HashMap;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class DrinksGUI extends JFrame {
    static JFrame f;

    public static void loadDrinks() {

        // Load Item Prices
        HashMap<String, String> itemPrices = PeepDB.getPrices();

        // Update Meal names and Descriptions
        PeepDB.updateNamesAndDescriptions();

        // Get names
        HashMap<Integer, String> mealNames = PeepDB.getNames();

        // Get Descriptions
        HashMap<Integer, String> mealDescriptions = PeepDB.getDescriptions();

        // create a new frame
        f = new JFrame("Inventory Management System: Server Drinks");

        // create a panel
        JPanel p = new JPanel();

        // add panel to frame
        f.add(p);

        final int FRAME_WIDTH = 1500;
        final int FRAME_HEIGHT = 1000;

        f.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        f.setVisible(true);

        p.setLayout(new BorderLayout(5, 20));

        // Add a little bit of padding around entire main frame
        p.setBorder(new EmptyBorder(30, 30, 30, 30));

        JButton back = new JButton("Home");
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Home.main(null);
                f.dispose();
            }
        });

        back.setFont(new Font("Serif", Font.PLAIN, 30));
        back.setBounds(5, 5, 150, 40);
        back.setFont(new Font("Serif", Font.PLAIN, 30));
        back.setBackground(new Color(80, 0, 0));
        back.setOpaque(true);
        back.setBorderPainted(false);
        back.setForeground(Color.white);
        p.add(back);

        JPanel headerContainer = new JPanel();
        headerContainer.setLayout(new BorderLayout(0, 0));
        JPanel headerTop = new JPanel();
        JPanel headerBot = new JPanel();

        // Create header
        JLabel headerLabel = new JLabel("Layne's Chicken Fingers!", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Serif", Font.PLAIN, 40));
        headerLabel.setBorder(new EmptyBorder(40, 0, 0, 0));
        headerLabel.setBounds(40, 0, 0, 0);
        headerLabel.setForeground(new Color(80, 0, 0));

        headerTop.add(headerLabel);

        JButton buttonMeals = new JButton("Meals");

        buttonMeals.setFont(new Font("Serif", Font.PLAIN, 15)); 
        buttonMeals.setBackground(new Color(80, 0, 0));
        buttonMeals.setOpaque(true);
        buttonMeals.setBorderPainted(false);
        buttonMeals.setForeground(Color.white);

        JButton buttonDrinks = new JButton("Drinks");

        buttonDrinks.setFont(new Font("Serif", Font.PLAIN, 15)); 
        buttonDrinks.setBackground(new Color(80, 0, 0));
        buttonDrinks.setOpaque(true);
        buttonDrinks.setBorderPainted(false);
        buttonDrinks.setForeground(Color.white);

        JButton buttonSpecials = new JButton("Special Items");

        buttonSpecials.setFont(new Font("Serif", Font.PLAIN, 15)); 
        buttonSpecials.setBackground(new Color(80, 0, 0));
        buttonSpecials.setOpaque(true);
        buttonSpecials.setBorderPainted(false);
        buttonSpecials.setForeground(Color.white);

        buttonMeals.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                MealsGUI.main(null);
                f.dispose();
            }
        });

        buttonSpecials.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                SpecialsGUI.main(null);
                f.dispose();
            }
        });

        headerBot.add(buttonMeals);
        headerBot.add(buttonDrinks);
        headerBot.add(buttonSpecials);

        // Add a little border above buttons
        headerBot.setBorder(new EmptyBorder(30, 0, 0, 0));

        headerContainer.add(headerTop, BorderLayout.NORTH);
        headerContainer.add(headerBot, BorderLayout.SOUTH);

        p.add(headerContainer, BorderLayout.NORTH);

        // Make order Summary Panel
        JPanel orderSummaryBox = new JPanel();
        orderSummaryBox.setLayout(new BorderLayout(0, 0));

        orderSummaryBox.setPreferredSize(new Dimension((int) FRAME_WIDTH / 6, FRAME_HEIGHT - 160));

        JLabel summaryLabel = new JLabel("Order Summary");

        // Set font and center
        summaryLabel.setFont(new Font("Serif", Font.PLAIN, 25));
        summaryLabel.setHorizontalAlignment(JLabel.CENTER);
        summaryLabel.setBorder(new EmptyBorder(20, 0, 0, 0));

        orderSummaryBox.setBorder(BorderFactory.createLineBorder(Color.black));
        orderSummaryBox.add(summaryLabel, BorderLayout.NORTH);

        JPanel summaryInnerBox = new JPanel();
        summaryInnerBox.setBorder(new EmptyBorder(20, 10, 20, 10));
        summaryInnerBox.setLayout(new BorderLayout());

        orderSummaryBox.add(summaryInnerBox, BorderLayout.CENTER);

        JTextArea summaryList = new JTextArea(CurrentOrderSummary.getItemSummary());

        JLabel orderTotal = new JLabel(CurrentOrderSummary.updatePriceString());
        orderTotal.setBorder(new EmptyBorder(20, 0, 0, 0));
        orderTotal.setFont(new Font("Serif", Font.PLAIN, 20));

        summaryInnerBox.add(summaryList, BorderLayout.CENTER);
        summaryInnerBox.add(orderTotal, BorderLayout.SOUTH);

        JButton checkoutButton = new JButton("Checkout");
        checkoutButton.setFont(new Font("Serif", Font.PLAIN, 15)); 
        checkoutButton.setBackground(new Color(80, 0, 0));
        checkoutButton.setOpaque(true);
        checkoutButton.setBorderPainted(false);
        checkoutButton.setForeground(Color.white);
        orderSummaryBox.add(checkoutButton, BorderLayout.SOUTH);

        checkoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                CurrentOrderSummary.writeOrderToFileAndDBAndUpdateInv();

                CurrentOrderSummary.clearOrder();

                JOptionPane.showMessageDialog(null, "Order Confirmed!");

                // Reset Total Price on Order Summary to zero
                orderTotal.setText(CurrentOrderSummary.updatePriceString());

                // Empty the entire order summary box
                summaryList.setText(CurrentOrderSummary.getItemSummary());

            }
        });

        p.add(orderSummaryBox, BorderLayout.EAST);

        // Create Center Menu Box
        FlowLayout layout = new FlowLayout();
        layout.setHgap(100);
        JPanel menuBox = new JPanel(layout);
        menuBox.setPreferredSize(new Dimension((int) (FRAME_WIDTH / 3) * 2, FRAME_HEIGHT - 80));

        // Add a little padding to the top of the menu box (between menu and header)
        menuBox.setBorder(new EmptyBorder(150, 160, 0, 50));

        // Add menu box to main panel
        p.add(menuBox);

        // Create Individual boxes and add them to menuBox
        JPanel drink1 = new JPanel();

        drink1.setLayout(new GridLayout(4, 1));

        // Create Black Border
        drink1.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(3.0f)));

        drink1.setPreferredSize(new Dimension((int) (FRAME_WIDTH / 12) * 2, FRAME_HEIGHT / 5));
        drink1.setForeground(new Color(80, 0, 0));

        // Create itemName
        JLabel itemName1 = new JLabel(mealNames.get(518));
        itemName1.setFont(new Font("Serif", Font.PLAIN, 20));
        itemName1.setHorizontalAlignment(JLabel.CENTER);

        // Create Description
        JLabel itemDescription1 = new JLabel(mealDescriptions.get(518));
        itemDescription1.setHorizontalAlignment(JLabel.CENTER);

        String price1 = itemPrices.get("518");
        JLabel item1Price = new JLabel(price1);
        item1Price.setHorizontalAlignment(JLabel.CENTER);

        JButton itemButton1 = new JButton("Add");
        itemButton1.setFont(new Font("Serif", Font.PLAIN, 20)); 
        itemButton1.setBackground(new Color(80, 0, 0));
        itemButton1.setOpaque(true);
        itemButton1.setBorderPainted(false);
        itemButton1.setForeground(Color.white);

        JPanel buttonContainer1 = new JPanel(new GridLayout(1, 2));

        JButton itemButtonRM1 = new JButton("Remove");
        itemButtonRM1.setFont(new Font("Serif", Font.PLAIN, 20));
        itemButtonRM1.setBackground(Color.white);
        itemButtonRM1.setOpaque(true);
        itemButtonRM1.setBorderPainted(false);
        itemButtonRM1.setForeground(new Color(80, 0, 0));

        buttonContainer1.add(itemButton1);
        buttonContainer1.add(itemButtonRM1);

        // If adding item 1 -> Add to total, write to csv, and update DB
        itemButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                // Add item to current order
                CurrentOrderSummary.addItemPriceToOrder(Double.parseDouble(price1));

                CurrentOrderSummary.addItemIDToOrder("518");

                // Add Item to order summary
                String newOrder = mealNames.get(518) + " ( $" + price1 + " )\n\n";
                summaryList.append(newOrder);

                CurrentOrderSummary.addItemOrderStr(newOrder);

                CurrentOrderSummary.appendToItemSummary(newOrder);

                // Update String for "Total: "
                orderTotal.setText(CurrentOrderSummary.updatePriceString());

            }
        });

        itemButtonRM1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                CurrentOrderSummary.removeItemPriceFromOrder("518", Double.parseDouble(price1));

                CurrentOrderSummary.removeItemFromSummary("518");

                CurrentOrderSummary.removeItemIDFromOrder("518");

                summaryList.setText("");
                summaryList.append(CurrentOrderSummary.getItemSummary());

                // Update String Total:
                orderTotal.setText(CurrentOrderSummary.updatePriceString());
            }
        });

        // Add items to meal
        drink1.add(itemName1);
        drink1.add(itemDescription1);
        drink1.add(item1Price);
        drink1.add(buttonContainer1);

        // Add meal to menuBox
        menuBox.add(drink1);

        /* DRINK TWO */

        // Create Individual boxes and add them to menuBox
        JPanel drink2 = new JPanel();

        drink2.setLayout(new GridLayout(4, 1));

        // Create Black Border
        drink2.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(3.0f)));

        drink2.setPreferredSize(new Dimension((int) (FRAME_WIDTH / 12) * 2, FRAME_HEIGHT / 5));
        drink2.setForeground(new Color(80, 0, 0));

        JLabel itemName2 = new JLabel(mealNames.get(519));
        itemName2.setFont(new Font("Serif", Font.PLAIN, 20));
        itemName2.setHorizontalAlignment(JLabel.CENTER);

        JLabel itemDescription2 = new JLabel(mealDescriptions.get(513));
        itemDescription2.setHorizontalAlignment(JLabel.CENTER);

        String price2 = itemPrices.get("519");
        JLabel item2Price = new JLabel(price2);
        item2Price.setHorizontalAlignment(JLabel.CENTER);

        JButton itemButton2 = new JButton("Add");
        itemButton2.setFont(new Font("Serif", Font.PLAIN, 20)); 
        itemButton2.setBackground(new Color(80, 0, 0));
        itemButton2.setOpaque(true);
        itemButton2.setBorderPainted(false);
        itemButton2.setForeground(Color.white);

        JPanel buttonContainer2 = new JPanel(new GridLayout(1, 2));

        JButton itemButtonRM2 = new JButton("Remove");
        itemButtonRM2.setFont(new Font("Serif", Font.PLAIN, 20));
        itemButtonRM2.setBackground(Color.white);
        itemButtonRM2.setOpaque(true);
        itemButtonRM2.setBorderPainted(false);
        itemButtonRM2.setForeground(new Color(80, 0, 0));

        buttonContainer2.add(itemButton2);
        buttonContainer2.add(itemButtonRM2);

        itemButton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                CurrentOrderSummary.addItemPriceToOrder(Double.parseDouble(price2));

                CurrentOrderSummary.addItemIDToOrder("519");

                String newOrder = mealNames.get(519) + " ( $" + price2 + " )\n\n";
                summaryList.append(newOrder);

                CurrentOrderSummary.addItemOrderStr(newOrder);

                CurrentOrderSummary.appendToItemSummary(newOrder);

                // Update String for "Total: "
                orderTotal.setText(CurrentOrderSummary.updatePriceString());

            }
        });

        itemButtonRM2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                CurrentOrderSummary.removeItemPriceFromOrder("519", Double.parseDouble(price2));

                CurrentOrderSummary.removeItemFromSummary("519");

                CurrentOrderSummary.removeItemIDFromOrder("519");

                summaryList.setText("");
                summaryList.append(CurrentOrderSummary.getItemSummary());

                // Update String Total:
                orderTotal.setText(CurrentOrderSummary.updatePriceString());
            }
        });

        // Add items to meal
        drink2.add(itemName2);
        drink2.add(itemDescription2);
        drink2.add(item2Price);
        drink2.add(buttonContainer2);

        // Add meal to menuBox
        menuBox.add(drink2);

    }

    public static void main(String[] args) {
        loadDrinks();
    }
}
