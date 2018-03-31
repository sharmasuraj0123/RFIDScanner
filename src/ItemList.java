/**
 * Suraj Sharma
 * Id # 109606910
 * Home Work 2 - Linked Lists
 *
 * This class  contains references to the head and tail of a list of ItemInfoNode nodes.
 * It also contains many methods such as aad a node or remove a node from the list.
 * This class also contains search options.
 */

public class ItemList {

    private ItemInfoNode head;
    private ItemInfoNode tail;
    private ItemInfoNode cursor;
    private int size;

    /**
     * The default constructor for the List.
     * Which sets its head and tail to null.
     */
    public ItemList() {
        head = tail = null;
        size = 0;
    }

    /**
     * Checks whether its an empty list or not
     *
     * @return
     * Whether the list is empty or not
     */
    public boolean isEmpty() {
        return (head == null);
    }

    /**
     * This method resets the cursor back to head.
     */
    public void resetCursor() {
        cursor = head;
    }

    /**
     * The accessor method for the size of the list.
     * @return
     * returns the size of the list.
     */
    public int getSize() {
        return size;
    }

    /**
     * This method resets the cursor and prints the default pattern.
     * This is a custom method created just to make the program shorter
     */
    public void printTemplate() {
        resetCursor();
        System.out.printf("%-20s%-12s%-12s%-12s%-5s", "Item Name", "RFID", "Original", " Current", "Price");
        System.out.println();
        System.out.println(String.format("%-20s%-12s%-12s%-12s%-5s", "", "", "Location", "Location", ""));
        System.out.println("--------------------------------------------------------------");
    }

    /**
     * Inserts the info into the list in its correct position based on its rfidTagNumber.
     * Multiple instances of a given item ARE allowed in the list.
     *
     * The order of complexity of the worst case for the method is O(size).
     * (The size of the list).
     * Since there is a for loop which iterates according to size of the list
     * if the element is to be added in the end.
     *
     * @param name
     * The name to be set as name.
     *
     * @param rfidTag
     * The Rfid Tag number will be set to this value
     *
     * @param price
     * The price of the object will be set to this value.
     *
     * @param initPosition
     * The original location and the current location will be set to this value.
     *
     */
    public void insertInfo(String name, String rfidTag, double price, String initPosition) {

        ItemInfo temp = new ItemInfo(name, rfidTag, price, initPosition);
        ItemInfoNode newNode = new ItemInfoNode(temp);

        if (isEmpty()) {
            head = newNode;
            tail = newNode;
            size++;
        } else {
            if (tail.getInfo().getRfidTagNumber().compareTo(rfidTag)<=0) {
                tail.setNext(newNode);
                newNode.setPrev(tail);
                newNode.setNext(null);
                tail = newNode;
                size++;
            } else {
                resetCursor();
                for (int i = 0; i < size; i++) {
                    if (cursor.getInfo().getRfidTagNumber().compareTo(rfidTag) > 0) {
                        if (cursor == head) {
                            newNode.setNext(head);
                            head.setPrev(newNode);
                            head = newNode;
                            size++;
                            break;
                        } else {

                            newNode.setNext(cursor);
                            cursor.getPrev().setNext(newNode);
                            cursor.setPrev(newNode);
                            newNode.setPrev(cursor.getPrev());
                            cursor = newNode;
                            size++;
                            break;
                        }
                    }
                    cursor = cursor.getNext();
                }
            }
        }
    }

    /**
     * This is a custom method created to assist the removal of a specific node.
     *
     * The order of compelexity for this method is O(1).
     *
     * @param rNode
     * Removes this node from the list.
     */
    public void remove(ItemInfoNode rNode) {

            if (rNode == head) {
                if (head == tail) {
                    head = null;
                    size--;
                } else {
                    head = head.getNext();
                    head.setPrev(null);
                    size--;
                }
            } else if (rNode ==tail) {
                tail = rNode.getPrev();
                tail.setNext(null);
                size--;

            } else {
                rNode.getPrev().setNext(rNode.getNext());
                rNode.getNext().setPrev(rNode.getPrev());
                size--;
            }
        }

    /**
     * Removes all nodes in the list that have current location listed as "out"
     * and displays a list of all items that have been removed in this fashion.
     *
     * The order of complexity of the worst case for the method is O(size).
     * (The size of the list).
     * Since there is a for loop which iterates according to size of the list
     * if the element is to be added in the end.
     */
    public void removeAllPurchased() {
        resetCursor();

        if (isEmpty()) {
            System.out.println("There is nothing to remove");
        } else {
            for (int i = 0; i < size; i++) {
                if (cursor.getInfo().getCurrentLocation().equalsIgnoreCase("out"))
                    remove(cursor);
                cursor = cursor.getNext();
            }
        }
    }

    /**
     * Moves an item with a given rfidTagNumber from a source location to a dest location.
     * The return value indicates whether or not an item of the given rfidTagNumber was found at the given source location.
     *
     * The order of complexity of the worst case for the method is O(size).
     * (The size of the list).
     * Since there is a for loop which iterates according to size of the list
     * if the element is to be added in the end.
     *
     * @param rfidTag
     * Checks for the specific RFID in the list.
     *
     * @param source
     * Checks for its source in the list.
     *
     * @param dest
     * Sets the current location of the object to dest.
     *
     * <dt><b>Precondition:</b><dd>
     *     source cannot be out or any invalid input String.
     *     dest has to be a valid output for current location.
     *
     * @return
     * Whether the item was found or not in the list.
     *
     * @throws IllegalArgumentException
     * Throws an exception if dest is of an invalid format (that it is not a cart, shelf, or "out").
     * and also if source is equal to "out".
     */
    public boolean moveItem(String rfidTag, String source, String dest) throws IllegalArgumentException {
        if (source.equalsIgnoreCase("out"))
            throw new IllegalArgumentException("The source cannot be checked out");
        if (dest.length() > 6)
            throw new IllegalArgumentException("Original Location should comprise of 6 digits");
        if(isEmpty()){
            System.out.println("The list is empty!!");
            return false;
        }
        else {
            resetCursor();
            for (int i = 0; i < size; i++) {
                if (cursor.getInfo().getRfidTagNumber().equalsIgnoreCase(rfidTag)
                        && cursor.getInfo().getCurrentLocation().equalsIgnoreCase(source)) {
                    cursor.getInfo().setCurrentLocation(dest);
                    return true;
                } else {
                    cursor = cursor.getNext();
                }
            }
            return false;
        }
    }

    /**
     * Prints a neatly formatted list of all items currently in the list.
     * The table should include each item's name, rfidTagNumber, original location, current location, and price.
     * The list must be sorted in order of rfidTagNumber,
     * although duplicate rfidTagNumber entries may be printed in any order.
     *
     * The order of complexity of the worst case for the method is O(size).
     * (The size of the list).
     * Since there is a for loop which iterates according to size of the list
     * if the element is to be added in the end.
     */
    public void printAll() {

        if (isEmpty()) {
            System.out.println("The list is empty");
        } else {
            resetCursor();
            printTemplate();
            for (int i = 0; i < size; i++) {
                System.out.println(cursor.getInfo().toString());
                cursor = cursor.getNext();
            }

        }
    }

    /**
     * Prints a neatly formatted list of all items in a specified current location.
     * The table should include each item's name, rfidTagNumber, original location, current location, and price.
     * The list must be sorted in order of rfidTagNumber,
     * although duplicate rfidTagNumber entries may be printed in any order.
     *
     * The order of complexity of the worst case for the method is O(size).
     * (The size of the list).
     * Since there is a for loop which iterates according to size of the list
     * if the element is to be added in the end.
     *
     * @param location
     * The list of current location as location to be printed.
     *
     */
    public void printByLocation(String location) {
        if(isEmpty()){
            System.out.println("the list is empty");
        }
        else {
            printTemplate();
            for (int i = 0; i < size; i++) {
                if (cursor.getInfo().getCurrentLocation().equalsIgnoreCase(location))
                    System.out.println(cursor.getInfo().toString());
                cursor = cursor.getNext();
            }
        }
    }

    /**
     * Take every item that is currently in the store and on the wrong shelf
     * and places it back where it belongs (its original location).
     * Items that are "out" or currently in a cart are not affected by this command.
     * Display a table for all out of place items moved in this fashion,
     * including each item's name, rfidTagNumber, current location(before the move), original location and price.
     *
     * The order of complexity of the worst case for the method is O(size).
     * (The size of the list).
     * Since there is a for loop which iterates according to size of the list
     * if the element is to be added in the end.
     */
    public void cleanStore() {

        if(isEmpty()){
            System.out.println("There is nothing to clean");
        }
        else {
            printTemplate();
            for (int i = 0; i < size; i++) {
                if (!cursor.getInfo().getCurrentLocation().equals(cursor.getInfo().getOriginalLocation())
                        && cursor.getInfo().getCurrentLocation().charAt(0) == ('s')) {
                    cursor.getInfo().setCurrentLocation(cursor.getInfo().getOriginalLocation());
                    System.out.println(cursor.getInfo().toString());
                }


                cursor = cursor.getNext();
            }
        }
    }

    /**
     * Goes through a given cart and checks out each item (changes its location to "out").
     * A neatly formatted list of the items checked out is to be printed
     * and it must be sorted in order of rfidTagNumber,
     * although duplicate rfidTagNumber entries may be printed in any order.
     * The return value is the total cost for the items that were in the cart.
     * Throw appropriate exceptions for invalid cart numbers.
     *
     * The order of complexity of the worst case for the method is O(size).
     * (The size of the list).
     * Since there is a for loop which iterates according to size of the list
     * if the element is to be added in the end.
     *
     * @param cartNumber
     * The cart Number to be checked out
     *
     * @return
     * The total price of the objects in the cart.
     *
     * @throws CartNotFoundException
     * If the cart number is invalid this exception is thrown.
     */
    public double checkOut(String cartNumber) throws CartNotFoundException {

        if(cartNumber.charAt(0)!='C'||cartNumber.length()!=4 ||
                !(ItemInfo.isDecimal(cartNumber.substring(1)))){
            throw new CartNotFoundException("The cart number entered is invalid");
        }

        double cost = -1;

        if (isEmpty()) {
            System.out.println("The List is empty");
        } else {
            printTemplate();
            for (int i = 0; i < size; i++) {

                if (cursor.getInfo().getCurrentLocation().equalsIgnoreCase(cartNumber)) {
                    cursor.getInfo().setCurrentLocation("OUT");
                    System.out.println(cursor.getInfo().toString());
                    cost += cursor.getInfo().getPrice();
                }
                cursor = cursor.getNext();
            }
        }

        if(cost==-1) {
            throw new CartNotFoundException("The cart number entered is not found");
        }
        return cost+1;
    }

    /**
     * Prints a neatly formatted list of all items with the same RFID TAG.
     * The table should include each item's name, rfidTagNumber, original location, current location, and price.
     * The list must be sorted in order of rfidTagNumber,
     * although duplicate rfidTagNumber entries may be printed in any order.
     *
     * The order of complexity of the worst case for the method is O(size).
     * (The size of the list).
     * Since there is a for loop which iterates according to size of the list
     * if the element is to be added in the end.
     *
     * @param rfid
     * The list of with rfid to be printed.
     *
     */
    public void printByRfid(String rfid) {

        if (isEmpty()) {
            System.out.println("the list is empty");
        } else {
            printTemplate();

            for (int i = 0; i < size; i++) {
                if (cursor.getInfo().getRfidTagNumber().equalsIgnoreCase(rfid))
                    System.out.println(cursor.getInfo().toString());
                cursor = cursor.getNext();
            }
        }
    }
}
