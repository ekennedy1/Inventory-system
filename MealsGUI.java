import java.sql.*;
import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class MealsGUI extends JFrame {
    static JFrame f;

    public static void loadMeals() {

        HashMap<String, String> itemPrices = PeepDB.getPrices();

        CurrentOrderSummary.init();

        PeepDB.updateNamesAndDescriptions();

        HashMap<Integer, String> mealNames = PeepDB.getNames();
        
        // Get Descriptions
        HashMap<Integer, String> mealDescriptions = PeepDB.getDescriptions();

        // create a new frame
        f = new JFrame("Inventory Management System: Server");

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

        JPanel headerContainer = new JPanel();
        headerContainer.setLayout(new BorderLayout(0, 0));
        JPanel headerTop = new JPanel();
        JPanel headerBot = new JPanel();

        JLabel headerLabel = new JLabel("Layne's Chicken Fingers!", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Serif", Font.PLAIN, 40));
        headerLabel.setBorder(new EmptyBorder(40, 0, 0, 0));
        headerLabel.setBounds(40, 0, 0, 0);
        headerLabel.setForeground(new Color(80, 0, 0));

        // add button to go back to home screen
        JButton back = new JButton("Home");
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Home.main(null);
                f.dispose();
            }
        });

        //set dimensions for buttons
        back.setFont(new Font("Serif", Font.PLAIN, 30));
        back.setBounds(5, 5, 150, 40);
        back.setFont(new Font("Serif", Font.PLAIN, 30));
        back.setBackground(new Color(80, 0, 0));
        back.setOpaque(true);
        back.setBorderPainted(false);
        back.setForeground(Color.white);
        p.add(back);

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
        buttonDrinks.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                DrinksGUI.main(null);
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

        // Add components to outer layout
        p.add(headerContainer, BorderLayout.NORTH);

        // Make order Summary Panel
        JPanel orderSummaryBox = new JPanel();
        orderSummaryBox.setLayout(new BorderLayout(0, 0));

        orderSummaryBox.setPreferredSize(new Dimension((int) FRAME_WIDTH / 6, FRAME_HEIGHT - 160));

        JLabel summaryLabel = new JLabel("Order Summary");

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
        JPanel menuBox = new JPanel(); // So menu box height = 1000 - 70 - 10 (from bottom frame padding)
        menuBox.setPreferredSize(new Dimension((int) (FRAME_WIDTH / 3) * 2, FRAME_HEIGHT - 80));

        // Add a little padding to the top of the menu box (between menu and header)
        // And adds border between order summary
        menuBox.setBorder(new EmptyBorder(30, 0, 0, 50));

        // (rows, cols)
        GridLayout menuLayout = new GridLayout(3, 4);
        menuLayout.setHgap(5);
        menuLayout.setVgap(5);
        menuBox.setLayout(menuLayout);

        // Add menu box to main panel
        p.add(menuBox);

        JPanel meal1 = new JPanel();

        meal1.setLayout(new GridLayout(4, 1));
        meal1.setForeground(new Color(80, 0, 0));

        meal1.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(4.0f)));

        JLabel itemName1 = new JLabel(mealNames.get(501));

        itemName1.setFont(new Font("Serif", Font.PLAIN, 20));
        itemName1.setHorizontalAlignment(JLabel.CENTER);

        // Create Description
        JLabel itemDescription1 = new JLabel(mealDescriptions.get(501));
        itemDescription1.setHorizontalAlignment(JLabel.CENTER);

        String price1 = itemPrices.get("501");
        JLabel item1Price = new JLabel(price1);
        item1Price.setHorizontalAlignment(JLabel.CENTER);

        JPanel buttonContainer1 = new JPanel(new GridLayout(1, 2));

        JButton itemButtonRM1 = new JButton("Remove");
        itemButtonRM1.setFont(new Font("Serif", Font.PLAIN, 25));
        itemButtonRM1.setBackground(Color.white);
        itemButtonRM1.setOpaque(true);
        itemButtonRM1.setBorderPainted(false);
        itemButtonRM1.setForeground(new Color(80, 0, 0));

        JButton itemButton1 = new JButton("Add");
        itemButton1.setFont(new Font("Serif", Font.PLAIN, 25)); 
        itemButton1.setBackground(new Color(80, 0, 0));
        itemButton1.setOpaque(true);
        itemButton1.setBorderPainted(false);
        itemButton1.setForeground(Color.white);

        buttonContainer1.add(itemButton1);
        buttonContainer1.add(itemButtonRM1);

        itemButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                // Add item to current order
                CurrentOrderSummary.addItemPriceToOrder(Double.parseDouble(price1));

                CurrentOrderSummary.addItemIDToOrder("501");

                String newOrder = mealNames.get(501) + " ( $" + price1 + " )\n\n";

                summaryList.append(newOrder);

                CurrentOrderSummary.addItemOrderStr(newOrder);

                CurrentOrderSummary.appendToItemSummary(newOrder);

                // Update String for "Total: "
                orderTotal.setText(CurrentOrderSummary.updatePriceString());

            }
        });

        itemButtonRM1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                CurrentOrderSummary.removeItemPriceFromOrder("501", Double.parseDouble(price1));

                CurrentOrderSummary.removeItemFromSummary("501");

                CurrentOrderSummary.removeItemIDFromOrder("501");

                // Remove from summary string
                summaryList.setText("");
                summaryList.append(CurrentOrderSummary.getItemSummary());

                // Update String Total:
                orderTotal.setText(CurrentOrderSummary.updatePriceString());
            }
        });

        

        // Add items to meal
        meal1.add(itemName1);
        meal1.add(itemDescription1);
        meal1.add(item1Price);
        meal1.add(buttonContainer1);

        // Add meal to menuBox
        menuBox.add(meal1);

        /* MEAL TWO */

        JPanel meal2 = new JPanel();
        meal2.setLayout(new GridLayout(4, 1));
        meal2.setForeground(new Color(80, 0, 0));

        meal2.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(4.0f)));

        JLabel itemName2 = new JLabel(mealNames.get(502));
        itemName2.setFont(new Font("Serif", Font.PLAIN, 20));
        itemName2.setHorizontalAlignment(JLabel.CENTER);

        String price2 = itemPrices.get("502");
        JLabel item2Price = new JLabel(price2);
        item2Price.setHorizontalAlignment(JLabel.CENTER);

        // Create Description
        JLabel itemDescription2 = new JLabel(mealDescriptions.get(502));
        itemDescription2.setHorizontalAlignment(JLabel.CENTER);

        JPanel buttonContainer2 = new JPanel(new GridLayout(1, 2));

        JButton itemButtonRM2 = new JButton("Remove");
        itemButtonRM2.setFont(new Font("Serif", Font.PLAIN, 25));
        itemButtonRM2.setBackground(Color.white);
        itemButtonRM2.setOpaque(true);
        itemButtonRM2.setBorderPainted(false);
        itemButtonRM2.setForeground(new Color(80, 0, 0));

        JButton itemButton2 = new JButton("Add");
        itemButton2.setFont(new Font("Serif", Font.PLAIN, 25)); 
        itemButton2.setBackground(new Color(80, 0, 0));
        itemButton2.setOpaque(true);
        itemButton2.setBorderPainted(false);
        itemButton2.setForeground(Color.white);

        buttonContainer2.add(itemButton2);
        buttonContainer2.add(itemButtonRM2);

        itemButton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                // Add item to current order
                CurrentOrderSummary.addItemPriceToOrder(Double.parseDouble(price2));

                CurrentOrderSummary.addItemIDToOrder("502");

                String newOrder = mealNames.get(502) + " ( $" + price2 + " )\n\n";

                summaryList.append(newOrder);

                CurrentOrderSummary.addItemOrderStr(newOrder);

                
                CurrentOrderSummary.appendToItemSummary(newOrder);

                // Update String for "Total: "
                orderTotal.setText(CurrentOrderSummary.updatePriceString());

            }
        });

        itemButtonRM2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                CurrentOrderSummary.removeItemPriceFromOrder("502", Double.parseDouble(price2));

                CurrentOrderSummary.removeItemFromSummary("502");

                CurrentOrderSummary.removeItemIDFromOrder("502");

                // Remove from summary string
                summaryList.setText("");
                summaryList.append(CurrentOrderSummary.getItemSummary());

                // Update String Total:
                orderTotal.setText(CurrentOrderSummary.updatePriceString());
            }
        });

        // Add items to meal
        meal2.add(itemName2);
        meal2.add(itemDescription2);
        meal2.add(item2Price);
        meal2.add(buttonContainer2);

        // Add meal to menuBox
        menuBox.add(meal2);

        /* MEAL THREE */

        JPanel meal3 = new JPanel();
        meal3.setLayout(new GridLayout(4, 1));
        meal3.setForeground(new Color(80, 0, 0));

        meal3.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(4.0f)));

        JLabel itemName3 = new JLabel(mealNames.get(503));
        itemName3.setFont(new Font("Serif", Font.PLAIN, 20));
        itemName3.setHorizontalAlignment(JLabel.CENTER);

        // Create Description
        JLabel itemDescription3 = new JLabel(mealDescriptions.get(503));
        itemDescription3.setHorizontalAlignment(JLabel.CENTER);

        String price3 = itemPrices.get("503");
        JLabel item3Price = new JLabel(price3);
        item3Price.setHorizontalAlignment(JLabel.CENTER);

        JPanel buttonContainer3 = new JPanel(new GridLayout(1, 2));

        JButton itemButtonRM3 = new JButton("Remove");
        itemButtonRM3.setFont(new Font("Serif", Font.PLAIN, 25));
        itemButtonRM3.setBackground(Color.white);
        itemButtonRM3.setOpaque(true);
        itemButtonRM3.setBorderPainted(false);
        itemButtonRM3.setForeground(new Color(80, 0, 0));

        JButton itemButton3 = new JButton("Add");
        itemButton3.setFont(new Font("Serif", Font.PLAIN, 25)); 
        itemButton3.setBackground(new Color(80, 0, 0));
        itemButton3.setOpaque(true);
        itemButton3.setBorderPainted(false);
        itemButton3.setForeground(Color.white);

        buttonContainer3.add(itemButton3);
        buttonContainer3.add(itemButtonRM3);

        itemButton3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                // Add item to current order
                CurrentOrderSummary.addItemPriceToOrder(Double.parseDouble(price3));

                CurrentOrderSummary.addItemIDToOrder("503");

                String newOrder = mealNames.get(503) + " ( $" + price3 + " )\n\n";
                summaryList.append(newOrder);
                CurrentOrderSummary.appendToItemSummary(newOrder);

                CurrentOrderSummary.addItemOrderStr(newOrder);

                // Update String for "Total: "
                orderTotal.setText(CurrentOrderSummary.updatePriceString());

            }
        });

        itemButtonRM3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                CurrentOrderSummary.removeItemPriceFromOrder("503", Double.parseDouble(price3));

                CurrentOrderSummary.removeItemFromSummary("503");

                CurrentOrderSummary.removeItemIDFromOrder("503");

                // Remove from summary string
                summaryList.setText("");
                summaryList.append(CurrentOrderSummary.getItemSummary());

                // Update String Total:
                orderTotal.setText(CurrentOrderSummary.updatePriceString());
            }
        });

        // Add items to meal
        meal3.add(itemName3);
        meal3.add(itemDescription3);
        meal3.add(item3Price);
        meal3.add(buttonContainer3);

        // Add meal to menuBox
        menuBox.add(meal3);

        /* MEAL FOUR */

        JPanel meal4 = new JPanel();
        meal4.setLayout(new GridLayout(4, 1));
        meal4.setForeground(new Color(80, 0, 0));

        meal4.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(4.0f)));

        JLabel itemName4 = new JLabel(mealNames.get(504));
        itemName4.setFont(new Font("Serif", Font.PLAIN, 20));
        itemName4.setHorizontalAlignment(JLabel.CENTER);

        // Create Description
        JLabel itemDescription4 = new JLabel(mealDescriptions.get(504));
        itemDescription4.setHorizontalAlignment(JLabel.CENTER);

        String price4 = itemPrices.get("504");
        JLabel item4Price = new JLabel(price4);
        item4Price.setHorizontalAlignment(JLabel.CENTER);

        JPanel buttonContainer4 = new JPanel(new GridLayout(1, 2));

        JButton itemButtonRM4 = new JButton("Remove");
        itemButtonRM4.setFont(new Font("Serif", Font.PLAIN, 25));
        itemButtonRM4.setBackground(Color.white);
        itemButtonRM4.setOpaque(true);
        itemButtonRM4.setBorderPainted(false);
        itemButtonRM4.setForeground(new Color(80, 0, 0));

        JButton itemButton4 = new JButton("Add");
        itemButton4.setFont(new Font("Serif", Font.PLAIN, 25)); 
        itemButton4.setBackground(new Color(80, 0, 0));
        itemButton4.setOpaque(true);
        itemButton4.setBorderPainted(false);
        itemButton4.setForeground(Color.white);

        buttonContainer4.add(itemButton4);
        buttonContainer4.add(itemButtonRM4);

        itemButton4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                // Add item to current order
                CurrentOrderSummary.addItemPriceToOrder(Double.parseDouble(price4));

                CurrentOrderSummary.addItemIDToOrder("504");

                String newOrder = mealNames.get(504) + " ( $" + price4 + " )\n\n";
                summaryList.append(newOrder);
                CurrentOrderSummary.appendToItemSummary(newOrder);

                CurrentOrderSummary.addItemOrderStr(newOrder);

                // Update String for "Total: "
                orderTotal.setText(CurrentOrderSummary.updatePriceString());

            }
        });

        itemButtonRM4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                CurrentOrderSummary.removeItemPriceFromOrder("504", Double.parseDouble(price4));

                CurrentOrderSummary.removeItemFromSummary("504");

                CurrentOrderSummary.removeItemIDFromOrder("504");

                // Remove from summary string
                summaryList.setText("");
                summaryList.append(CurrentOrderSummary.getItemSummary());

                // Update String Total:
                orderTotal.setText(CurrentOrderSummary.updatePriceString());
            }
        });

        // Add items to meal
        meal4.add(itemName4);
        meal4.add(itemDescription4);
        meal4.add(item4Price);
        meal4.add(buttonContainer4);

        // Add meal to menuBox
        menuBox.add(meal4);

        /* MEAL FIVE */

        JPanel meal5 = new JPanel();
        meal5.setLayout(new GridLayout(4, 1));
        meal5.setForeground(new Color(80, 0, 0));

        meal5.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(4.0f)));

        JLabel itemName5 = new JLabel(mealNames.get(506));
        itemName5.setFont(new Font("Serif", Font.PLAIN, 20));
        itemName5.setHorizontalAlignment(JLabel.CENTER);

        // Create Description
        JLabel itemDescription5 = new JLabel(mealDescriptions.get(506));
        itemDescription5.setHorizontalAlignment(JLabel.CENTER);

        String price5 = itemPrices.get("506");
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

                // Add item to current order
                CurrentOrderSummary.addItemPriceToOrder(Double.parseDouble(price5));

                CurrentOrderSummary.addItemIDToOrder("506");

                String newOrder = mealNames.get(506) + " ( $" + price5 + " )\n\n";
                summaryList.append(newOrder);
                CurrentOrderSummary.appendToItemSummary(newOrder);

                CurrentOrderSummary.addItemOrderStr(newOrder);

                // Update String for "Total: "
                orderTotal.setText(CurrentOrderSummary.updatePriceString());

            }
        });

        itemButtonRM5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                CurrentOrderSummary.removeItemPriceFromOrder("506", Double.parseDouble(price5));

                CurrentOrderSummary.removeItemFromSummary("506");

                CurrentOrderSummary.removeItemIDFromOrder("506");

                // Remove from summary string
                summaryList.setText("");
                summaryList.append(CurrentOrderSummary.getItemSummary());

                // Update String Total:
                orderTotal.setText(CurrentOrderSummary.updatePriceString());
            }
        });

        // Add items to meal
        meal5.add(itemName5);
        meal5.add(itemDescription5);
        meal5.add(item5Price);
        meal5.add(buttonContainer5);

        // Add meal to menuBox
        menuBox.add(meal5);

        /* MEAL SIX */

        JPanel meal6 = new JPanel();
        meal6.setLayout(new GridLayout(4, 1));
        meal6.setForeground(new Color(80, 0, 0));

        meal6.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(4.0f)));

        JLabel itemName6 = new JLabel(mealNames.get(507));
        itemName6.setFont(new Font("Serif", Font.PLAIN, 20));
        itemName6.setHorizontalAlignment(JLabel.CENTER);

        // Create Description
        JLabel itemDescription6 = new JLabel(mealDescriptions.get(507));
        itemDescription6.setHorizontalAlignment(JLabel.CENTER);

        String price6 = itemPrices.get("507");
        JLabel item6Price = new JLabel(price6);
        item6Price.setHorizontalAlignment(JLabel.CENTER);

        JButton itemButton6 = new JButton("Add");
        itemButton6.setFont(new Font("Serif", Font.PLAIN, 25)); 
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

                // Add item to current order
                CurrentOrderSummary.addItemPriceToOrder(Double.parseDouble(price6));

                CurrentOrderSummary.addItemIDToOrder("507");

                String newOrder = mealNames.get(507) + " ( $" + price6 + " )\n\n";
                summaryList.append(newOrder);
                CurrentOrderSummary.appendToItemSummary(newOrder);

                CurrentOrderSummary.addItemOrderStr(newOrder);

                // Update String for "Total: "
                orderTotal.setText(CurrentOrderSummary.updatePriceString());

            }
        });

        itemButtonRM6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                CurrentOrderSummary.removeItemPriceFromOrder("507", Double.parseDouble(price6));

                CurrentOrderSummary.removeItemFromSummary("507");

                CurrentOrderSummary.removeItemIDFromOrder("507");

                // Remove from summary string
                summaryList.setText("");
                summaryList.append(CurrentOrderSummary.getItemSummary());

                // Update String Total:
                orderTotal.setText(CurrentOrderSummary.updatePriceString());
            }
        });

        // Add items to meal
        meal6.add(itemName6);
        meal6.add(itemDescription6);
        meal6.add(item6Price);
        meal6.add(buttonContainer6);

        // Add meal to menuBox
        menuBox.add(meal6);

        /* MEAL SEVEN */

        JPanel meal7 = new JPanel();
        meal7.setLayout(new GridLayout(4, 1));
        meal7.setForeground(new Color(80, 0, 0));

        meal7.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(4.0f)));

        JLabel itemName7 = new JLabel(mealNames.get(508));
        itemName7.setFont(new Font("Serif", Font.PLAIN, 20));
        itemName7.setHorizontalAlignment(JLabel.CENTER);

        // Create Description
        JLabel itemDescription7 = new JLabel(mealDescriptions.get(508));
        itemDescription7.setHorizontalAlignment(JLabel.CENTER);

        String price7 = itemPrices.get("508");
        JLabel item7Price = new JLabel(price7);
        item7Price.setHorizontalAlignment(JLabel.CENTER);

        JButton itemButton7 = new JButton("Add");
        itemButton7.setFont(new Font("Serif", Font.PLAIN, 25)); 
        itemButton7.setBackground(new Color(80, 0, 0));
        itemButton7.setOpaque(true);
        itemButton7.setBorderPainted(false);
        itemButton7.setForeground(Color.white);

        JPanel buttonContainer7 = new JPanel(new GridLayout(1, 2));

        JButton itemButtonRM7 = new JButton("Remove");
        itemButtonRM7.setFont(new Font("Serif", Font.PLAIN, 25));
        itemButtonRM7.setBackground(Color.white);
        itemButtonRM7.setOpaque(true);
        itemButtonRM7.setBorderPainted(false);
        itemButtonRM7.setForeground(new Color(80, 0, 0));

        buttonContainer7.add(itemButton7);
        buttonContainer7.add(itemButtonRM7);

        itemButton7.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                // Add item to current order
                CurrentOrderSummary.addItemPriceToOrder(Double.parseDouble(price7));

                CurrentOrderSummary.addItemIDToOrder("508");

                String newOrder = mealNames.get(508) + " ( $" + price7 + " )\n\n";
                summaryList.append(newOrder);
                CurrentOrderSummary.appendToItemSummary(newOrder);

                CurrentOrderSummary.addItemOrderStr(newOrder);

                // Update String for "Total: "
                orderTotal.setText(CurrentOrderSummary.updatePriceString());

            }
        });

        itemButtonRM7.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                CurrentOrderSummary.removeItemPriceFromOrder("508", Double.parseDouble(price7));

                CurrentOrderSummary.removeItemFromSummary("508");

                CurrentOrderSummary.removeItemIDFromOrder("508");

                // Remove from summary string
                summaryList.setText("");
                summaryList.append(CurrentOrderSummary.getItemSummary());

                // Update String Total:
                orderTotal.setText(CurrentOrderSummary.updatePriceString());
            }
        });

        // Add items to meal
        meal7.add(itemName7);
        meal7.add(itemDescription7);
        meal7.add(item7Price);
        meal7.add(buttonContainer7);

        // Add meal to menuBox
        menuBox.add(meal7);

        /* MEAL EIGHT */

        JPanel meal8 = new JPanel();
        meal8.setLayout(new GridLayout(4, 1));
        meal8.setForeground(new Color(80, 0, 0));

        meal8.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(4.0f)));

        JLabel itemName8 = new JLabel(mealNames.get(509));
        itemName8.setFont(new Font("Serif", Font.PLAIN, 20));
        itemName8.setHorizontalAlignment(JLabel.CENTER);

        // Create Description
        JLabel itemDescription8 = new JLabel(mealDescriptions.get(509));
        itemDescription8.setHorizontalAlignment(JLabel.CENTER);

        String price8 = itemPrices.get("509");
        JLabel item8Price = new JLabel(price8);
        item8Price.setHorizontalAlignment(JLabel.CENTER);

        JButton itemButton8 = new JButton("Add");
        itemButton8.setFont(new Font("Serif", Font.PLAIN, 25)); 
        itemButton8.setBackground(new Color(80, 0, 0));
        itemButton8.setOpaque(true);
        itemButton8.setBorderPainted(false);
        itemButton8.setForeground(Color.white);

        JPanel buttonContainer8 = new JPanel(new GridLayout(1, 2));

        JButton itemButtonRM8 = new JButton("Remove");
        itemButtonRM8.setFont(new Font("Serif", Font.PLAIN, 25));
        itemButtonRM8.setBackground(Color.white);
        itemButtonRM8.setOpaque(true);
        itemButtonRM8.setBorderPainted(false);
        itemButtonRM8.setForeground(new Color(80, 0, 0));

        buttonContainer8.add(itemButton8);
        buttonContainer8.add(itemButtonRM8);

        itemButton8.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                // Add item to current order
                CurrentOrderSummary.addItemPriceToOrder(Double.parseDouble(price8));

                CurrentOrderSummary.addItemIDToOrder("509");

                String newOrder = mealNames.get(509) + " ( $" + price8 + " )\n\n";
                summaryList.append(newOrder);
                CurrentOrderSummary.appendToItemSummary(newOrder);

                CurrentOrderSummary.addItemOrderStr(newOrder);

                // Update String for "Total: "
                orderTotal.setText(CurrentOrderSummary.updatePriceString());

            }
        });

        itemButtonRM8.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                CurrentOrderSummary.removeItemPriceFromOrder("509", Double.parseDouble(price8));

                CurrentOrderSummary.removeItemFromSummary("509");

                CurrentOrderSummary.removeItemIDFromOrder("509");

                // Remove from summary string
                summaryList.setText("");
                summaryList.append(CurrentOrderSummary.getItemSummary());

                // Update String Total:
                orderTotal.setText(CurrentOrderSummary.updatePriceString());
            }
        });

        // Add items to meal
        meal8.add(itemName8);
        meal8.add(itemDescription8);
        meal8.add(item8Price);
        meal8.add(buttonContainer8);

        // Add meal to menuBox
        menuBox.add(meal8);

        /* MEAL NINE */

        JPanel meal9 = new JPanel();
        meal9.setLayout(new GridLayout(4, 1));
        meal9.setForeground(new Color(80, 0, 0));

        meal9.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(4.0f)));

        JLabel itemName9 = new JLabel(mealNames.get(510));
        itemName9.setFont(new Font("Serif", Font.PLAIN, 20));
        itemName9.setHorizontalAlignment(JLabel.CENTER);

        // Create Description
        JLabel itemDescription9 = new JLabel(mealDescriptions.get(510));
        itemDescription9.setHorizontalAlignment(JLabel.CENTER);

        String price9 = itemPrices.get("510");
        JLabel item9Price = new JLabel(price9);
        item9Price.setHorizontalAlignment(JLabel.CENTER);

        JButton itemButton9 = new JButton("Add");
        itemButton9.setFont(new Font("Serif", Font.PLAIN, 25)); 
        itemButton9.setBackground(new Color(80, 0, 0));
        itemButton9.setOpaque(true);
        itemButton9.setBorderPainted(false);
        itemButton9.setForeground(Color.white);

        JPanel buttonContainer9 = new JPanel(new GridLayout(1, 2));

        JButton itemButtonRM9 = new JButton("Remove");
        itemButtonRM9.setFont(new Font("Serif", Font.PLAIN, 25));
        itemButtonRM9.setBackground(Color.white);
        itemButtonRM9.setOpaque(true);
        itemButtonRM9.setBorderPainted(false);
        itemButtonRM9.setForeground(new Color(80, 0, 0));

        buttonContainer9.add(itemButton9);
        buttonContainer9.add(itemButtonRM9);

        itemButton9.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                // Add item to current order
                CurrentOrderSummary.addItemPriceToOrder(Double.parseDouble(price9));

                CurrentOrderSummary.addItemIDToOrder("510");

                String newOrder = mealNames.get(510) + " ( $" + price9 + " )\n\n";
                summaryList.append(newOrder);
                CurrentOrderSummary.appendToItemSummary(newOrder);

                CurrentOrderSummary.addItemOrderStr(newOrder);

                // Update String for "Total: "
                orderTotal.setText(CurrentOrderSummary.updatePriceString());

            }
        });

        itemButtonRM9.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                CurrentOrderSummary.removeItemPriceFromOrder("510", Double.parseDouble(price9));

                CurrentOrderSummary.removeItemFromSummary("510");

                CurrentOrderSummary.removeItemIDFromOrder("510");

                // Remove from summary string
                summaryList.setText("");
                summaryList.append(CurrentOrderSummary.getItemSummary());

                // Update String Total:
                orderTotal.setText(CurrentOrderSummary.updatePriceString());
            }
        });

        // Add items to meal
        meal9.add(itemName9);
        meal9.add(itemDescription9);
        meal9.add(item9Price);
        meal9.add(buttonContainer9);

        // Add meal to menuBox
        menuBox.add(meal9);

        /* MEAL TEN */

        JPanel meal10 = new JPanel();
        meal10.setLayout(new GridLayout(4, 1));
        meal10.setForeground(new Color(80, 0, 0));

        meal10.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(4.0f)));

        JLabel itemName10 = new JLabel(mealNames.get(511));
        itemName10.setFont(new Font("Serif", Font.PLAIN, 15));
        itemName10.setHorizontalAlignment(JLabel.CENTER);

        // Create Description
        JLabel itemDescription10 = new JLabel(mealDescriptions.get(511));
        itemDescription10.setHorizontalAlignment(JLabel.CENTER);

        String price10 = itemPrices.get("511");
        JLabel item10Price = new JLabel(price10);
        item10Price.setHorizontalAlignment(JLabel.CENTER);

        JButton itemButton10 = new JButton("Add");
        itemButton10.setFont(new Font("Serif", Font.PLAIN, 25)); 
        itemButton10.setBackground(new Color(80, 0, 0));
        itemButton10.setOpaque(true);
        itemButton10.setBorderPainted(false);
        itemButton10.setForeground(Color.white);

        JPanel buttonContainer10 = new JPanel(new GridLayout(1, 2));

        JButton itemButtonRM10 = new JButton("Remove");
        itemButtonRM10.setFont(new Font("Serif", Font.PLAIN, 25));
        itemButtonRM10.setBackground(Color.white);
        itemButtonRM10.setOpaque(true);
        itemButtonRM10.setBorderPainted(false);
        itemButtonRM10.setForeground(new Color(80, 0, 0));

        buttonContainer10.add(itemButton10);
        buttonContainer10.add(itemButtonRM10);

        itemButton10.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                // Add item to current order
                CurrentOrderSummary.addItemPriceToOrder(Double.parseDouble(price10));

                CurrentOrderSummary.addItemIDToOrder("511");

                String newOrder = mealNames.get(511) + " ( $" + price10 + " )\n\n";
                summaryList.append(newOrder);
                CurrentOrderSummary.appendToItemSummary(newOrder);

                CurrentOrderSummary.addItemOrderStr(newOrder);

                // Update String for "Total: "
                orderTotal.setText(CurrentOrderSummary.updatePriceString());

            }
        });

        itemButtonRM10.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                CurrentOrderSummary.removeItemPriceFromOrder("511", Double.parseDouble(price10));

                CurrentOrderSummary.removeItemFromSummary("511");

                CurrentOrderSummary.removeItemIDFromOrder("511");

                // Remove from summary string
                summaryList.setText("");
                summaryList.append(CurrentOrderSummary.getItemSummary());

                // Update String Total:
                orderTotal.setText(CurrentOrderSummary.updatePriceString());
            }
        });

        // Add items to meal
        meal10.add(itemName10);
        meal10.add(itemDescription10);
        meal10.add(item10Price);
        meal10.add(buttonContainer10);

        // Add meal to menuBox
        menuBox.add(meal10);

        /* MEAL ELEVEN */

        JPanel meal11 = new JPanel();
        meal11.setLayout(new GridLayout(4, 1));
        meal11.setForeground(new Color(80, 0, 0));

        meal11.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(4.0f)));

        JLabel itemName11 = new JLabel(mealNames.get(512));
        itemName11.setFont(new Font("Serif", Font.PLAIN, 15));
        itemName11.setHorizontalAlignment(JLabel.CENTER);

        // Create Description
        JLabel itemDescription11 = new JLabel(mealDescriptions.get(512));
        itemDescription11.setHorizontalAlignment(JLabel.CENTER);

        String price11 = itemPrices.get("512");
        JLabel item11Price = new JLabel(price11);
        item11Price.setHorizontalAlignment(JLabel.CENTER);

        JButton itemButton11 = new JButton("Add");
        itemButton11.setFont(new Font("Serif", Font.PLAIN, 25)); 
        itemButton11.setBackground(new Color(80, 0, 0));
        itemButton11.setOpaque(true);
        itemButton11.setBorderPainted(false);
        itemButton11.setForeground(Color.white);

        JPanel buttonContainer11 = new JPanel(new GridLayout(1, 2));

        JButton itemButtonRM11 = new JButton("Remove");
        itemButtonRM11.setFont(new Font("Serif", Font.PLAIN, 25));
        itemButtonRM11.setBackground(Color.white);
        itemButtonRM11.setOpaque(true);
        itemButtonRM11.setBorderPainted(false);
        itemButtonRM11.setForeground(new Color(80, 0, 0));

        buttonContainer11.add(itemButton11);
        buttonContainer11.add(itemButtonRM11);

        itemButton11.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                // Add item to current order
                CurrentOrderSummary.addItemPriceToOrder(Double.parseDouble(price11));

                CurrentOrderSummary.addItemIDToOrder("512");

                String newOrder = mealNames.get(512) + " ( $" + price11 + " )\n\n";
                summaryList.append(newOrder);
                CurrentOrderSummary.appendToItemSummary(newOrder);

                CurrentOrderSummary.addItemOrderStr(newOrder);

                // Update String for "Total: "
                orderTotal.setText(CurrentOrderSummary.updatePriceString());

            }
        });

        itemButtonRM11.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                CurrentOrderSummary.removeItemPriceFromOrder("512", Double.parseDouble(price11));

                CurrentOrderSummary.removeItemFromSummary("512");

                CurrentOrderSummary.removeItemIDFromOrder("512");

                // Remove from summary string
                summaryList.setText("");
                summaryList.append(CurrentOrderSummary.getItemSummary());

                // Update String Total:
                orderTotal.setText(CurrentOrderSummary.updatePriceString());
            }
        });

        // Add items to meal
        meal11.add(itemName11);
        meal11.add(itemDescription11);
        meal11.add(item11Price);
        meal11.add(buttonContainer11);

        // Add meal to menuBox
        menuBox.add(meal11);

        p.add(menuBox, BorderLayout.CENTER);

        /* Impossible Basket */

        // If there is an impossible basket
        if(mealNames.get(520) != null) {


            JPanel meal12 = new JPanel();
            meal12.setLayout(new GridLayout(4, 1));
            meal12.setForeground(new Color(80, 0, 0));


            meal12.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(4.0f)));


            JLabel itemName12 = new JLabel(mealNames.get(520));
            itemName12.setFont(new Font("Serif", Font.PLAIN, 20));
            itemName12.setHorizontalAlignment(JLabel.CENTER);

            // Create Description
            JLabel itemDescription12 = new JLabel(mealDescriptions.get(520));
            itemDescription12.setHorizontalAlignment(JLabel.CENTER);

            String price12 = itemPrices.get("520");
            JLabel item12Price = new JLabel(price12);
            item12Price.setHorizontalAlignment(JLabel.CENTER);

            JButton itemButton12 = new JButton("Add");
            itemButton12.setFont(new Font("Serif", Font.PLAIN, 25));
            itemButton12.setBackground(new Color(80, 0, 0));
            itemButton12.setOpaque(true);
            itemButton12.setBorderPainted(false);
            itemButton12.setForeground(Color.white);

            JPanel buttonContainer12 = new JPanel(new GridLayout(1, 2));

            JButton itemButtonRM12 = new JButton("Remove");
            itemButtonRM12.setFont(new Font("Serif", Font.PLAIN, 25));
            itemButtonRM12.setBackground(Color.white);
            itemButtonRM12.setOpaque(true);
            itemButtonRM12.setBorderPainted(false);
            itemButtonRM12.setForeground(new Color(80, 0, 0));

            buttonContainer12.add(itemButton12);
            buttonContainer12.add(itemButtonRM12);

            itemButton12.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                    // Add item to current order
                    CurrentOrderSummary.addItemPriceToOrder(Double.parseDouble(price12));

                    CurrentOrderSummary.addItemIDToOrder("520");


                    String newOrder = mealNames.get(520) + " ( $" + price12 + " )\n\n";
                    summaryList.append(newOrder);
                    CurrentOrderSummary.appendToItemSummary(newOrder);


                    CurrentOrderSummary.addItemOrderStr(newOrder);

                    // Update String for "Total: "
                    orderTotal.setText(CurrentOrderSummary.updatePriceString());

                }
            });

            itemButtonRM12.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {


                    CurrentOrderSummary.removeItemPriceFromOrder("520", Double.parseDouble(price12));


                    CurrentOrderSummary.removeItemFromSummary("520");


                    CurrentOrderSummary.removeItemIDFromOrder("520");

                    // Remove from summary string
                    summaryList.setText("");
                    summaryList.append(CurrentOrderSummary.getItemSummary());

                    // Update String Total:
                    orderTotal.setText(CurrentOrderSummary.updatePriceString());
                }
            });

            // Add items to meal
            meal12.add(itemName12);
            meal12.add(itemDescription12);
            meal12.add(item12Price);
            meal12.add(buttonContainer12);

            // Add meal to menuBox
            menuBox.add(meal12);

            p.add(menuBox, BorderLayout.CENTER);

        }
    }

    public static Double incrementTotalPrice(Double itemPrice, Double orderTotal, ArrayList<Double> totalPrice) {
        return orderTotal + itemPrice;
    }

    public static void main(String[] args) {
        loadMeals();
    }

}
