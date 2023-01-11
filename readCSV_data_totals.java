import java.io.*;
import java.util.Scanner;

public class readCSV_data_totals{
    public static void main(String[] args) throws Exception{
        Scanner scan = new Scanner(new File("4_week_data_.csv"));
        scan.useDelimiter(",");

        double total = 0;
        
        while(scan.hasNext()){
            String str = scan.next();
            if(str.contains("Monday") || str.contains("Sunday") || str.contains("Tuesday") || str.contains("Wednesday") 
            || str.contains("Thursday") || str.contains("Friday") || str.contains("Saturday")){
                System.out.println(total);
                total = 0;
            }

            if(str.equalsIgnoreCase("501")){
                total += Double.parseDouble(scan.next()) * 6.50;
            }
            else if(str.equalsIgnoreCase("502")){
                total += Double.parseDouble(scan.next()) * 5.50;
            }
            else if(str.equalsIgnoreCase("503")){
                total += Double.parseDouble(scan.next()) * 4.50;
            }
            else if(str.equalsIgnoreCase("504")){
                total += Double.parseDouble(scan.next()) * 2.50;
            }
            else if(str.equalsIgnoreCase("505")){
                total += Double.parseDouble(scan.next()) * 5.00;
            }
            else if(str.equalsIgnoreCase("506")){
                total += Double.parseDouble(scan.next()) * 32.00;
            }
            else if(str.equalsIgnoreCase("507")){
                total += Double.parseDouble(scan.next()) * 7.50;
            }
            else if(str.equalsIgnoreCase("508")){
                total += Double.parseDouble(scan.next()) * 4.75;
            }
            else if(str.equalsIgnoreCase("509")){
                total += Double.parseDouble(scan.next()) * 5.75;
            }
            else if(str.equalsIgnoreCase("510")){
                total += Double.parseDouble(scan.next()) * 3.75;
            }
            else if(str.equalsIgnoreCase("511")){
                total += Double.parseDouble(scan.next()) * 4.50;
            }
            else if(str.equalsIgnoreCase("512")){
                total += Double.parseDouble(scan.next()) * 3.50;
            }
            else if(str.equalsIgnoreCase("513")){
                total += Double.parseDouble(scan.next()) * .10;
            }
            else if(str.equalsIgnoreCase("514")){
                total += Double.parseDouble(scan.next()) * 1.50;
            }
            else if(str.equalsIgnoreCase("515")){
                total += Double.parseDouble(scan.next()) * 1.50;
            }
            else if(str.equalsIgnoreCase("516")){
                total += Double.parseDouble(scan.next()) * 1.50;
            }
            else if(str.equalsIgnoreCase("517")){
                total += Double.parseDouble(scan.next()) * 1.75;
            }
            else if(str.equalsIgnoreCase("518")){
                total += Double.parseDouble(scan.next()) * 1.25;
            }
            else if(str.equalsIgnoreCase("519")){
                total += Double.parseDouble(scan.next()) * 2.0;
            }
        }

        System.out.print(total);
        scan.close();
    }
}