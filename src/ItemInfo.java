/**
 * Suraj Sharma
 * Id # 109606910
 * Home Work 2 - Linked Lists
 *
 * This class basically stores the data of the list.
 * The data includes variable like name , price , rfid , current location
 * and original location.
 */
public class ItemInfo {


    private String name;
    private double price;
    private String rfidTagNumber;
    private String originalLocation;
    private String currentLocation;

    /**
     * default constructor created for the class
     */
    public ItemInfo(){}


    /**
     * This is main custructor created used multiple times to create the method.
     * The default methods inside contain setters in order to check for exceptions
     * and violations or pre conditions if any.
     * @param name - the name of the object
     * @param rfidTag - RFID according to which the list is arranged
     * @param price - the price of the object
     * @param initPosition- the original Location of the object or the source.
     */
    public ItemInfo(String name, String rfidTag, double price, String initPosition){
        this.setName(name);
        this.setRfidTagNumber(rfidTag);
        this.setPrice(price);
        this.setOriginalLocation(initPosition);
        this.setCurrentLocation(initPosition);
    }

    /**
     * The accessor method used for the variable.
     * @return name.
     * The current name of the object
     */
    public String getName() {
        return name;
    }

    /**
     * The mutator method for the object
     * @param name
     * sets the name of the object to name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * The accessor method for the variable price.
     * @return price
     * the price of the object
     */
    public double getPrice() {
        return price;
    }

    /**
     * the mutator method for the variable price.
     * @param price
     * Sets the price to this variable
     *
     * <dt><b>Precondition:</b><dd>
     *              The Price Entered has to be a positive double.
     *
     * @throws IllegalArgumentException
     * Indicates that the price is not positive double
     */
    public void setPrice(double price) throws IllegalArgumentException{

        if(price <=0)
            throw new IllegalArgumentException("The price cannot be negative");

        else
        this.price = price;
    }

    /**
     * The accesor method for the RFID Tag number
     *
     * @return rfigTagNumber
     * Its current value.
     */
    public String getRfidTagNumber(){
        return rfidTagNumber;
    }


    /**
     *The mutator method for the variable rfidTagNumber.
     * The hexadecimal condition is checked using the method isHexadecimal().
     *
     * @param rfidTagNumber
     * The parameter rfid will be set to.
     *
     * <dt><b>Precondition:</b><dd>
     *      It is 9 characters long and represented in hexadecimal format(base 16) which means each character is either
     *      a digit from 0 to 9 or one of the letters A through F, where case is not important.
     *      The length of this String is to be fixed at 9.
     *
     * @throws IllegalArgumentException
     * If any of the above conditions are violated.
     */
    public void setRfidTagNumber(String rfidTagNumber) throws IllegalArgumentException {

        if (rfidTagNumber.length() != 9)
            throw new IllegalArgumentException("The length of the rfid Tag Number has to be 9");
        else {
                this.rfidTagNumber = rfidTagNumber;
            }

        }

    /**
     * The accessor method for the variable originalLocation
     *
     * @return
     * Its current value.
     */
    public String getOriginalLocation() {
        return originalLocation;
    }

    /**
     *
     * @param originalLocation
     * The value the original Locaton will be set to.
     *
     * <dt><b>Precondition:</b><dd>
     *     The first character is 's' to designate that it is a shelf position and
     *     it is followed by a 5 digit shelf number to further specify where it can be found in the store.
     *     The length of the String is to be fixed at 6.
     *
     * @throws IllegalArgumentException
     * If the length condition is violated
     *
     */
    public void setOriginalLocation(String originalLocation) throws IllegalArgumentException{

        if(originalLocation.length()!=6 || originalLocation.charAt(0)!='S')
            throw new IllegalArgumentException("Original Location should comprise of 6 digits " +
                    "and should begin with 's'");
        else {
            this.originalLocation = originalLocation;
        }
    }

    /**
     * The mutator method for the variable.
     *
     * @return
     * The current Location.
     */
    public String getCurrentLocation(){
        return currentLocation;
    }

    /**
     *The mutator method for the variable currentLocation.
     *
     * @param currentLocation
     *
     * <dt><b>Precondition:</b><dd>
     *      It may be a shelf position (as described above), an encoding of a cart number,
     *      which is designated by the letter 'c' followed by a 3 digit number ("c101", "c001", "c347", etc.),
     *      or it may have been checked out by a customer already
     *      in which case the location will be represented by the String "out", where case is not important.
     *
     * @throws IllegalArgumentException
     * If length condition is violated and also first letter is neither s ,c  or o.
     *
     */
    public void setCurrentLocation(String currentLocation) throws IllegalArgumentException{

        if(currentLocation.length()>6|| currentLocation.length()<3 || currentLocation.length()==5){
            throw new IllegalArgumentException("The length of the current location is incorrect");
        }
        if(currentLocation.length()==4 && (currentLocation.charAt(0)!= 'C'||
                !isDecimal(currentLocation.substring(1))))
            throw new IllegalArgumentException("This is not a valid cart number");

        if(currentLocation.length() ==3 && !currentLocation.equalsIgnoreCase("out"))
            throw new IllegalArgumentException("Invalid current location");

        if(currentLocation.length()==6 && (currentLocation.charAt(0)!= 'S'
                ||!isDecimal(currentLocation.substring(1))))
            throw new IllegalArgumentException("Invalid current location");

        this.currentLocation = currentLocation;
    }



    /**
     * A custom method created just to check whether the RFID TAG entered is a hexadecimal string.
     * The method is created static only to make it easily accessible in the main method.
     *
     * @param input
     * The String to be checked.
     *
     * @return
     * A boolean Equivalent whether it is hexadecimal or not.
     */
    public  static boolean isHexaDecimal( String input){
        try{
            Long.parseLong(input, 16);
            return true;
        }catch(NumberFormatException e){
            System.out.println("The RFID Tag number has to be an hexadecimal number");
            return false;
        }
    }

    /**
     * A custom method created just to check whether the Original Location entered is a valid string.
     * The method is used on the substring after the first letter.
     * The method is created static only to make it easily accessible in the main method.
     *
     * @param input
     * The String to be checked.
     *
     * @return
     * A boolean Equivalent whether it is decimal or not.
     */
    public static boolean isDecimal(String input){
        try{
            Long.parseLong(input);
            return true;
        }catch(NumberFormatException ex){
            System.out.println("The location entered is invalid!");
            return false;
        }
    }


    @Override
    /**
     * The defined ToString method for the variable.
     */
    public String toString() {
       return String.format("%-20s%-12s%-12s%-12s%-5s",name, rfidTagNumber, originalLocation,currentLocation,price);
    }

}
