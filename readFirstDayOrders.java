import java.io.*;
import java.util.Scanner;

class readFirstDayOrders {
    public static void main(String[] args) throws Exception {
        
        Scanner sc = new Scanner(new File("./first day order.csv"));

        sc.useDelimiter("\n");

        int counter = 0;
        String description = "";
        String SKU = "";
        String quantityStr = "";
        String delivered = "";
        String soldBy = "";
        String deliveredBy = "";
        String price = "";
        String quantMult = "";
        String extendedStr = "";
        String category = "";
        String invoiceLine = "";
        String detailedDescription = "";

        boolean foodItem = false;
        boolean bibItem = false;
        boolean bottlesItem = false;
        boolean servingItem = false;
        boolean janitorialItem = false;

        
        while(sc.hasNext() && counter == 0){
            String curr = sc.next();
            
            if(!curr.isEmpty()){
                System.out.println(sc.next());
                counter++;
            }
        }

    
        // Gets a full line
        while(sc.hasNext()){

            String inputLine = sc.next();
            inputLine = inputLine.substring(1); // Delete leading comma

            // Will be used later to input into database for respective type of inventory
            if(inputLine.isEmpty() || inputLine.trim().length() == 0 || inputLine.contains(",,,,,,,")){
                continue;
            }
            
            if(inputLine.equalsIgnoreCase("food")){
                foodItem = true;
                continue;    
            }
            else if(inputLine.equalsIgnoreCase("bib")){
                foodItem = false;
                bibItem = true;
                continue;
            }
            else if(inputLine.equalsIgnoreCase("bottles")){
                bibItem = false;
                bottlesItem = true;
                continue;
            }
            else if(inputLine.equalsIgnoreCase("serving")){
                bottlesItem = false;
                servingItem = true;
                continue;
            }
            else if(inputLine.equalsIgnoreCase("janitorial")){
                servingItem = false;
                janitorialItem = true;
                continue;
            }

            String [] components = inputLine.split(",");

            // Reset counter
            counter = 1;
            int loopCounter = 0;

            // Reset detailed description (since it uses +=)
            detailedDescription = ""; 

            for(int i = 0; i < components.length - 1; i++){

                if(i == 0){
                    description = components[i];
                }
                else if(i == 1){
                    SKU = components[i];
                }
                else if(i == 2){
                    quantityStr = components[i];
                }
                else if(i == 3){
                    delivered = components[i];
                }
                else if(i == 4){
                    soldBy = components[i];
                }
                else if(i == 5){
                    deliveredBy = components[i];
                }
                else if(i == 6){
                    quantMult = components[i];
                }
                else if(i == 7){
                    price = components[i];
                }
                else if(i == 8){
                    extendedStr = components[i];
                }
                else if(i == 9){
                    String curr = components[loopCounter];

                    if(curr.equalsIgnoreCase("cold") || curr.equalsIgnoreCase("dry") || curr.equalsIgnoreCase("frozen")){
                        category = components[loopCounter];
                    }
                    else if(!curr.isBlank()){ // contains rest of price string
                        extendedStr += "," + components[i];

                        // Decrement i so it comes back here
                        i--;
                    }
                }
                else if(i == 10){
                    invoiceLine = components[loopCounter];
                }
                else if(i >= 11){
                    // If commas in description
                    if(loopCounter > i) {
                        detailedDescription += ",";
                    }
                    
                    detailedDescription += components[loopCounter].trim();
                }

                loopCounter++;

            }

            // Delete First character from detailed description -> Storing "
            if(detailedDescription.length() > 1){
               detailedDescription = detailedDescription.substring(1);
            }
            else{
                detailedDescription = "";
            }


            System.out.println("Description: " + description);
            System.out.println("SKU: " + SKU);
            System.out.println("quant: " + quantityStr);
            System.out.println("Delivered: " + delivered);
            System.out.println("soldBy: " + soldBy);
            System.out.println("deliveredBy: " + deliveredBy);
            System.out.println("quantmult: " + quantMult);
            System.out.println("price: " + price);
            System.out.println("extendedStr: " + extendedStr);
            System.out.println("category: " + category);
            System.out.println("invoice line: " + invoiceLine);
            System.out.println("detailed description: " + detailedDescription);
        }
            
       


        

    }
}