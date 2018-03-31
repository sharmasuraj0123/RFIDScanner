/**
 * Suraj Sharma
 * Id # 109606910
 * Home Work 2 - Linked Lists
 *
 * This class  contains a reference to an ItemInfo object
 * as well as to two other ItemInfoNode objects, referred to as prev and next.
 * This class is used to transverse through the doubly linked list.
 */
public class ItemInfoNode {


    private ItemInfo info;
    private ItemInfoNode next;
    private ItemInfoNode prev;

    /**
     * The constructor used if we have the data of the object
     * @param initInfo
     * It is the object.
     */
    public ItemInfoNode( ItemInfo initInfo){
        info = initInfo;
    }

    /**
     * the default constructor.
     */
    public ItemInfoNode(){}

    /**
     * The mutator method to get the data stored of the linked list.
     * @param info
     * The data needed is set to the info.
     *
     */
    public void setInfo(ItemInfo info){
        this.info = info;
    }

    /**
     * The accessor method for the data stored in the list.
     * @return
     * The data needed
     */
    public ItemInfo getInfo(){
        return info;
    }

    /**
     * The method used to access the next element in the list.
     *
     * @return
     * the next node in the list.
     */
    public ItemInfoNode getNext() {
        return next;
    }

    /**
     * The method used to mutate the next element in the list.
     *
     * @param next
     * Sets the value of the next element in the list to next
     */
    public void setNext(ItemInfoNode next) {
        this.next = next;
    }

    /**
     * The method used to access the previous element in the list.
     *
     * @return
     * the previous node in the list.
     */
    public ItemInfoNode getPrev() {
        return prev;
    }

    /**
     * The method used to mutate the previous element in the list.
     *
     * @param prev
     * Sets the value of the previous element in the list to next
     */
    public void setPrev(ItemInfoNode prev) {
        this.prev = prev;
    }

}
