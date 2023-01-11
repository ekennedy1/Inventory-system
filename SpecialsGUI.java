
import java.sql.*;
import java.util.HashMap;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class SpecialsGUI extends JFrame {
    static JFrame f;

    public static void loadSpecials() {

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

        // set the size of frame
        f.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        f.setVisible(true);

        // Create border layout
        p.setLayout(new BorderLayout(5, 20));

        // Add a little bit of padding around entire main frame
        p.setBorder(new EmptyBorder(30, 30, 30, 30));

        // add button to go back to home screen
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

        // Add event listener to button drinks
        buttonMeals.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                MealsGUI.main(null);
                f.dispose();
            }
        });

        buttonDrinks.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                DrinksGUI.main(null);
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

        // Add components to outer layout
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

        // summaryList.setBounds(0, 0);
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

                // Write order details to CSV file
                CurrentOrderSummary.writeOrderToFileAndDBAndUpdateInv();

                // Clear current order information
                CurrentOrderSummary.clearOrder();

                // Jpanel
                JOptionPane.showMessageDialog(null, "Order Confirmed!");

                // Reset Total Price on Order Summary to zero
                orderTotal.setText(CurrentOrderSummary.updatePriceString());

                // Empty the entire order summary box
                summaryList.setText(CurrentOrderSummary.getItemSummary());

            }
        });

        p.add(orderSummaryBox, BorderLayout.EAST);

        // Create Center Menu Box
        JPanel menuBox = new JPanel(); // So menu box height = 1000 - 70 - 10 (from bottom frame padding)

        menuBox.setPreferredSize(new Dimension((int) (FRAME_WIDTH / 3) * 2, FRAME_HEIGHT - 80));

        // Add a little padding to the top of the menu box (between menu and header)
        // And adds border between order summary
        menuBox.setBorder(new EmptyBorder(30, 0, 0, 50));

        GridLayout menuLayout = new GridLayout(2, 3);
        menuLayout.setHgap(5);
        menuLayout.setVgap(5);
        menuBox.setLayout(menuLayout);

        // Add menu box to main panel
        p.add(menuBox);

        JPanel item1 = new JPanel();

        item1.setLayout(new GridLayout(4, 1));
        item1.setForeground(new Color(80, 0, 0));

        item1.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(6.0f)));

        JLabel itemName1 = new JLabel(mealNames.get(513));
        itemName1.setFont(new Font("Serif", Font.PLAIN, 20));
        itemName1.setHorizontalAlignment(JLabel.CENTER);

        JLabel itemDescription1 = new JLabel(mealDescriptions.get(513));
        itemDescription1.setHorizontalAlignment(JLabel.CENTER);

        String price1 = itemPrices.get("513");
        JLabel item1Price = new JLabel(price1);
        item1Price.setHorizontalAlignment(JLabel.CENTER);

        JButton itemButton1 = new JButton("Add");
        itemButton1.setFont(new Font("Serif", Font.PLAIN, 25));
        itemButton1.setBackground(new Color(80, 0, 0));
        itemButton1.setOpaque(true);
        itemButton1.setBorderPainted(false);
        itemButton1.setForeground(Color.white);

        JPanel buttonContainer1 = new JPanel(new GridLayout(1, 2));

        JButton itemButtonRM1 = new JButton("Remove");
        itemButtonRM1.setFont(new Font("Serif", Font.PLAIN, 25));
        itemButtonRM1.setBackground(Color.white);
        itemButtonRM1.setOpaque(true);
        itemButtonRM1.setBorderPainted(false);
        itemButtonRM1.setForeground(new Color(80, 0, 0));

        buttonContainer1.add(itemButton1);
        buttonContainer1.add(itemButtonRM1);

        itemButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                CurrentOrderSummary.addItemPriceToOrder(Double.parseDouble(price1));

                CurrentOrderSummary.addItemIDToOrder("513");

                // Add Item to order summary
                String newOrder = mealNames.get(513) + " ( $" + price1 + " )\n\n";
                summaryList.append(newOrder);

                CurrentOrderSummary.addItemOrderStr(newOrder);

                CurrentOrderSummary.appendToItemSummary(newOrder);

                // Update String for "Total: "
                orderTotal.setText(CurrentOrderSummary.updatePriceString());

            }
        });

        itemButtonRM1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                CurrentOrderSummary.removeItemPriceFromOrder("513", Double.parseDouble(price1));

                CurrentOrderSummary.removeItemFromSummary("513");

                CurrentOrderSummary.removeItemIDFromOrder("513");

                summaryList.setText("");
                summaryList.append(CurrentOrderSummary.getItemSummary());

                // Update String Total:
                orderTotal.setText(CurrentOrderSummary.updatePriceString());
            }
        });

        item1.add(itemName1);
        item1.add(itemDescription1);
        item1.add(item1Price);
        item1.add(buttonContainer1);

        menuBox.add(item1);

        /* Special item #2 */
        JPanel item2 = new JPanel();

        item2.setLayout(new GridLayout(4, 1));
        item2.setForeground(new Color(80, 0, 0));

        item2.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(6.0f)));

        JLabel itemName2 = new JLabel(mealNames.get(514));
        itemName2.setFont(new Font("Serif", Font.PLAIN, 20));
        itemName2.setHorizontalAlignment(JLabel.CENTER);

        JLabel itemDescription2 = new JLabel(mealDescriptions.get(514));
        itemDescription2.setHorizontalAlignment(JLabel.CENTER);

        String price2 = itemPrices.get("514");
        JLabel item2Price = new JLabel(price2);
        item2Price.setHorizontalAlignment(JLabel.CENTER);

        JButton itemButton2 = new JButton("Add");
        itemButton2.setFont(new Font("Serif", Font.PLAIN, 25));
        itemButton2.setBackground(new Color(80, 0, 0));
        itemButton2.setOpaque(true);
        itemButton2.setBorderPainted(false);
        itemButton2.setForeground(Color.white);

        JPanel buttonContainer2 = new JPanel(new GridLayout(1, 2));

        JButton itemButtonRM2 = new JButton("Remove");
        itemButtonRM2.setFont(new Font("Serif", Font.PLAIN, 25));
        itemButtonRM2.setBackground(Color.white);
        itemButtonRM2.setOpaque(true);
        itemButtonRM2.setBorderPainted(false);
        itemButtonRM2.setForeground(new Color(80, 0, 0));

        buttonContainer2.add(itemButton2);
        buttonContainer2.add(itemButtonRM2);

        itemButton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                CurrentOrderSummary.addItemPriceToOrder(Double.parseDouble(price2));

                CurrentOrderSummary.addItemIDToOrder("514");

                // Add Item to order summary
                String newOrder = mealNames.get(514) + " ( $" + price2 + " )\n\n";
                summaryList.append(newOrder);

                CurrentOrderSummary.addItemOrderStr(newOrder);

                CurrentOrderSummary.appendToItemSummary(newOrder);

                // Update String for "Total: "
                orderTotal.setText(CurrentOrderSummary.updatePriceString());

            }
        });

        itemButtonRM2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                CurrentOrderSummary.removeItemPriceFromOrder("514", Double.parseDouble(price2));

                CurrentOrderSummary.removeItemFromSummary("514");

                CurrentOrderSummary.removeItemIDFromOrder("514");

                summaryList.setText("");
                summaryList.append(CurrentOrderSummary.getItemSummary());

                // Update String Total:
                orderTotal.setText(CurrentOrderSummary.updatePriceString());
            }
        });

        item2.add(itemName2);
        item2.add(itemDescription2);
        item2.add(item2Price);
        item2.add(buttonContainer2);

        menuBox.add(item2);

        /* MENU ITEM #3 */

        JPanel item3 = new JPanel();

        item3.setLayout(new GridLayout(4, 1));
        item3.setForeground(new Color(80, 0, 0));

        item3.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(6.0f)));

        JLabel itemName3 = new JLabel(mealNames.get(515));
        itemName3.setFont(new Font("Serif", Font.PLAIN, 20));
        itemName3.setHorizontalAlignment(JLabel.CENTER);

        JLabel itemDescription3 = new JLabel(mealDescriptions.get(515));
        itemDescription3.setHorizontalAlignment(JLabel.CENTER);

        String price3 = itemPrices.get("515");
        JLabel item3Price = new JLabel(price3);
        item3Price.setHorizontalAlignment(JLabel.CENTER);

        JButton itemButton3 = new JButton("Add");
        itemButton3.setFont(new Font("Serif", Font.PLAIN, 25));
        itemButton3.setBackground(new Color(80, 0, 0));
        itemButton3.setOpaque(true);
        itemButton3.setBorderPainted(false);
        itemButton3.setForeground(Color.white);

        JPanel buttonContainer3 = new JPanel(new GridLayout(1, 2));

        JButton itemButtonRM3 = new JButton("Remove");
        itemButtonRM3.setFont(new Font("Serif", Font.PLAIN, 25));
        itemButtonRM3.setBackground(Color.white);
        itemButtonRM3.setOpaque(true);
        itemButtonRM3.setBorderPainted(false);
        itemButtonRM3.setForeground(new Color(80, 0, 0));

        buttonContainer3.add(itemButton3);
        buttonContainer3.add(itemButtonRM3);

        itemButton3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                CurrentOrderSummary.addItemPriceToOrder(Double.parseDouble(price3));

                CurrentOrderSummary.addItemIDToOrder("515");

                // Add Item to order summary
                String newOrder = mealNames.get(515) + " ( $" + price3 + " )\n\n";
                summaryList.append(newOrder);

                CurrentOrderSummary.addItemOrderStr(newOrder);

                CurrentOrderSummary.appendToItemSummary(newOrder);

                // Update String for "Total: "
                orderTotal.setText(CurrentOrderSummary.updatePriceString());

            }
        });

        itemButtonRM3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                CurrentOrderSummary.removeItemPriceFromOrder("515", Double.parseDouble(price3));

                CurrentOrderSummary.removeItemFromSummary("515");

                CurrentOrderSummary.removeItemIDFromOrder("515");

                summaryList.setText("");
                summaryList.append(CurrentOrderSummary.getItemSummary());

                // Update String Total:
                orderTotal.setText(CurrentOrderSummary.updatePriceString());
            }
        });

        item3.add(itemName3);
        item3.add(itemDescription3);
        item3.add(item3Price);
        item3.add(buttonContainer3);

        menuBox.add(item3);

        /* MENU ITEM #4 */

        JPanel item4 = new JPanel(new GridLayout(1, 2));

        item4.setLayout(new GridLayout(4, 1));
        item4.setForeground(new Color(80, 0, 0));

        item4.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(6.0f)));

        JLabel itemName4 = new JLabel(mealNames.get(516));
        itemName4.setFont(new Font("Serif", Font.PLAIN, 20));
        itemName4.setHorizontalAlignment(JLabel.CENTER);

        JLabel itemDescription4 = new JLabel(mealDescriptions.get(516));
        itemDescription4.setHorizontalAlignment(JLabel.CENTER);

        String price4 = itemPrices.get("516");
        JLabel item4Price = new JLabel(price4);
        item4Price.setHorizontalAlignment(JLabel.CENTER);

        JButton itemButton4 = new JButton("Add");
        itemButton4.setFont(new Font("Serif", Font.PLAIN, 25));
        itemButton4.setBackground(new Color(80, 0, 0));
        itemButton4.setOpaque(true);
        itemButton4.setBorderPainted(false);
        itemButton4.setForeground(Color.white);

        JPanel buttonContainer4 = new JPanel(new GridLayout(1, 2));

        JButton itemButtonRM4 = new JButton("Remove");
        itemButtonRM4.setFont(new Font("Serif", Font.PLAIN, 25));
        itemButtonRM4.setBackground(Color.white);
        itemButtonRM4.setOpaque(true);
        itemButtonRM4.setBorderPainted(false);
        itemButtonRM4.setForeground(new Color(80, 0, 0));

        buttonContainer4.add(itemButton4);
        buttonContainer4.add(itemButtonRM4);

        itemButton4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                CurrentOrderSummary.addItemPriceToOrder(Double.parseDouble(price4));

                CurrentOrderSummary.addItemIDToOrder("516");

                // Add Item to order summary
                String newOrder = mealNames.get(516) + " ( $" + price4 + " )\n\n";
                summaryList.append(newOrder);

                CurrentOrderSummary.addItemOrderStr(newOrder);

                CurrentOrderSummary.appendToItemSummary(newOrder);

                // Update String for "Total: "
                orderTotal.setText(CurrentOrderSummary.updatePriceString());

            }
        });

        itemButtonRM4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                CurrentOrderSummary.removeItemPriceFromOrder("516", Double.parseDouble(price4));

                CurrentOrderSummary.removeItemFromSummary("516");

                CurrentOrderSummary.removeItemIDFromOrder("516");

                summaryList.setText("");
                summaryList.append(CurrentOrderSummary.getItemSummary());

                // Update String Total:
                orderTotal.setText(CurrentOrderSummary.updatePriceString());
            }
        });

        item4.add(itemName4);
        item4.add(itemDescription4);
        item4.add(item4Price);
        item4.add(buttonContainer4);

        menuBox.add(item4);

        /* MENU ITEM #5 */

        JPanel item5 = new JPanel();

        item5.setLayout(new GridLayout(4, 1));
        item5.setForeground(new Color(80, 0, 0));

        item5.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(6.0f)));

        JLabel itemName5 = new JLabel(mealNames.get(517));
        itemName5.setFont(new Font("Serif", Font.PLAIN, 20));
        itemName5.setHorizontalAlignment(JLabel.CENTER);

        JLabel itemDescription5 = new JLabel(mealDescriptions.get(517));
        itemDescription5.setHorizontalAlignment(JLabel.CENTER);

        String price5 = itemPrices.get("517");
        JLabel item5Price = new JLabel(price5);
        item5Price.setHorizontalAlignment(JLabel.CENTER);

        JButton itemButton5 = new JButton("Add");
        itemButton5.setFont(new Font("Serif", Font.PLAIN, 25));
        itemButton5.setBackground(new Color(80, 0, 0));
        itemButton5.setOpaque(true);
        itemButton5.setBorderPainted(false);
        itemButton5.setForeground(Color.white);

        JPanel buttonContainer5 = new JPanel(new GridLayout(1, 2));

        JButton itemButtonRM5 = new JButton("Remove");
        itemButtonRM5.setFont(new Font("Serif", Font.PLAIN, 25));
        itemButtonRM5.setBackground(Color.white);
        itemButtonRM5.setOpaque(true);
        itemButtonRM5.setBorderPainted(false);
        itemButtonRM5.setForeground(new Color(80, 0, 0));

        buttonContainer5.add(itemButton5);
        buttonContainer5.add(itemButtonRM5);

        itemButton5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                CurrentOrderSummary.addItemPriceToOrder(Double.parseDouble(price5));

                CurrentOrderSummary.addItemIDToOrder("517");

                // Add Item to order summary
                String newOrder = mealNames.get(517) + " ( $" + price5 + " )\n\n";
                summaryList.append(newOrder);

                CurrentOrderSummary.addItemOrderStr(newOrder);

                CurrentOrderSummary.appendToItemSummary(newOrder);

                // Update String for "Total: "
                orderTotal.setText(CurrentOrderSummary.updatePriceString());

            }
        });

        itemButtonRM5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                CurrentOrderSummary.removeItemPriceFromOrder("517", Double.parseDouble(price5));

                CurrentOrderSummary.removeItemFromSummary("517");

                CurrentOrderSummary.removeItemIDFromOrder("517");

                summaryList.setText("");
                summaryList.append(CurrentOrderSummary.getItemSummary());

                // Update String Total:
                orderTotal.setText(CurrentOrderSummary.updatePriceString());
            }
        });

        item5.add(itemName5);
        item5.add(itemDescription5);
        item5.add(item5Price);
        item5.add(buttonContainer5);

        menuBox.add(item5);

        /* MENU ITEM #6 */

        JPanel item6 = new JPanel();

        item6.setLayout(new GridLayout(4, 1));
        item6.setForeground(new Color(80, 0, 0));

        item6.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(6.0f)));

        JLabel itemName6 = new JLabel(mealNames.get(505));
        itemName6.setFont(new Font("Serif", Font.PLAIN, 20));
        itemName6.setHorizontalAlignment(JLabel.CENTER);

        JLabel itemDescription6 = new JLabel(mealDescriptions.get(505));
        itemDescription6.setHorizontalAlignment(JLabel.CENTER);

        String price6 = itemPrices.get("505");
        JLabel item6Price = new JLabel(price6);
        item6Price.setHorizontalAlignment(JLabel.CENTER);

        JButton itemButton6 = new JButton("Add");
        itemButton2.setFont(new Font("Serif", Font.PLAIN, 25));
        itemButton6.setBackground(new Color(80, 0, 0));
        itemButton6.setOpaque(true);
        itemButton6.setBorderPainted(false);
        itemButton6.setForeground(Color.white);

        JPanel buttonContainer6 = new JPanel(new GridLayout(1, 2));

        JButton itemButtonRM6 = new JButton("Remove");
        itemButtonRM6.setFont(new Font("Serif", Font.PLAIN, 25));
        itemButtonRM6.setBackground(Color.white);
        itemButtonRM6.setOpaque(true);
        itemButtonRM6.setBorderPainted(false);
        itemButtonRM6.setForeground(new Color(80, 0, 0));

        buttonContainer6.add(itemButton6);
        buttonContainer6.add(itemButtonRM6);

        itemButton6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                CurrentOrderSummary.addItemPriceToOrder(Double.parseDouble(price6));

                CurrentOrderSummary.addItemIDToOrder("505");

                // Add Item to order summary
                String newOrder = mealNames.get(505) + " ( $" + price6 + " )\n\n";
                summaryList.append(newOrder);

                CurrentOrderSummary.addItemOrderStr(newOrder);

                CurrentOrderSummary.appendToItemSummary(newOrder);

                // Update String for "Total: "
                orderTotal.setText(CurrentOrderSummary.updatePriceString());

            }
        });

        itemButtonRM6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                CurrentOrderSummary.removeItemPriceFromOrder("505", Double.parseDouble(price6));

                CurrentOrderSummary.removeItemFromSummary("505");

                CurrentOrderSummary.removeItemIDFromOrder("505");

                summaryList.setText("");
                summaryList.append(CurrentOrderSummary.getItemSummary());

                // Update String Total:
                orderTotal.setText(CurrentOrderSummary.updatePriceString());
            }
        });

        item6.add(itemName6);
        item6.add(itemDescription6);
        item6.add(item6Price);
        item6.add(buttonContainer6);

        menuBox.add(item6);

        p.add(menuBox, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        loadSpecials();
    }
}
