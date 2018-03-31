/**
 * Suraj Sharma
 * Id # 109606910
 * Home Work 2 - Linked Lists
 *
 */

import java.util.Scanner;

/**
 * This class contains a main method that provides a menu with the following options
 * to interact with the program and update the store inventory information.
 */
public class DepartmentStore {

    public static void main(String [] args){

        Scanner s = new Scanner(System.in);

        String input ="";
        ItemList list = new ItemList();

        // All the inputs are converted to uppercase just to make it look more presentable.
        while(!input.equalsIgnoreCase("q")) {

            try {

                System.out.println("C - Clean Store");
                System.out.println("I - Insert an item into the list ");
                System.out.println("L - List by location");
                System.out.println("M - Move an item in the store");
                System.out.println("O - Checkout ");
                System.out.println("P - Print all items in store");
                System.out.println("R - Print by RFID tag number");
                System.out.println("U - Update inventory system   ");
                System.out.println("Q - Exit the program. ");

                System.out.print("User Input: ");
                input = s.next();

                if (input.equalsIgnoreCase("c")) {
                    list.cleanStore();

                } else if (input.equalsIgnoreCase("i")) {


                    System.out.print("Enter the name: ");
                    String name = s.next();
                    name = name.toUpperCase();
                    System.out.print("Enter the RFID: ");
                    String rfid = s.next();
                    rfid =rfid.toUpperCase();
                    System.out.print("Enter the Original Location: ");
                    String location = s.next();
                    location = location.toUpperCase();
                    System.out.print("Enter the price: ");
                    double price = s.nextDouble();

                    // Checking whether the inputs are appropriate.
                    if(ItemInfo.isHexaDecimal(rfid) && ItemInfo.isDecimal(location.substring(1))) {
                        list.insertInfo(name, rfid, price, location);
                        System.out.println(name +" has been added");
                    }
                    else{
                        System.out.println("Therefore, the item was not added");
                    }


                } else if (input.equalsIgnoreCase("l")) {

                    System.out.print("Please Enter the Location: ");
                    String location = s.next();
                    location =location.toUpperCase();
                    list.printByLocation(location);

                } else if (input.equalsIgnoreCase("m")) {
                    System.out.print("Enter the RFID: ");
                    String rfid = s.next();
                    System.out.print("Enter the Source: ");
                    String source = s.next();
                    System.out.print("Enter the new Location: ");
                    String dest = s.next();
                    dest =dest.toUpperCase();

                   if(list.moveItem(rfid, source, dest))
                       System.out.println("The item is present");
                    else{
                       System.out.println("The item is not present");
                   }

                } else if (input.equalsIgnoreCase("o")) {
                    System.out.print("Enter the cart number: ");
                    String cartNumber = s.next();
                    cartNumber =cartNumber.toUpperCase();

                   double cost = list.checkOut(cartNumber);
                    System.out.println("The total cost is $"+cost);



                } else if (input.equalsIgnoreCase("p")) {
                    list.printAll();

                } else if (input.equalsIgnoreCase("r")) {
                    System.out.print("Enter the RFID: ");
                    String rfid = s.next();
                    list.printByRfid(rfid);

                } else if (input.equalsIgnoreCase("u")) {
                    list.removeAllPurchased();
                    System.out.println("The Inventory has been updated");

                    // Left blank intentionally.
                } else if (input.equalsIgnoreCase("q")) {

                } else {
                    System.out.println("You have Entered an invalid Input!!");

                }
                System.out.println();

            }catch (IllegalArgumentException | CartNotFoundException e){
                System.out.println(e.getMessage());
            }
        }
    }
}
