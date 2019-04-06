package project4;

public class DoubleLinkedList<E>  {
    protected NodeD<E> top;      // The first NodeD<E> in the list
    protected NodeD<E> tail;     // The last NodeD<E> in the list

    // This instance variable is not required, however if you
    // find it useful, then you are welcome to use it
    protected NodeD<E> cursor;  // The current NodeD<E> in the list

    public DoubleLinkedList() {
        top = null;
        cursor = null;
        tail = null;
    }

    public E get(int position) {
        cursor = top;
        for (int i = 0; i < position; i++)
            cursor = cursor.getNext();
        return cursor.getData();

    }

    /******************************************************************
     * Method that determines the length of the linked list.
     * @return int value representing length of list.
     */
    public int getLen() {
        int result = 0;
        NodeD<E> temp = top;

        while(temp != null) {
            result++;
            temp = temp.getNext();
        }
        return result;
    }

    /******************************************************************
     * Adds a new node with data parameter to the top of the linked
     * list
     * @param data Generic data to be added as a node to the top of
     *             the linked list
     */
    public void addTop(E data) {
        if(top == null)
            top = tail = new NodeD<E>(data,null, null);
        else {
            top = new NodeD<E>(data, top, null);
            top.getNext().setPrev(top);
        }
    }

    /******************************************************************
     * Adds a new node with data paramter to the bottom of the linked
     * list
     * @param data Generic data to be added as a node to the bottom of
     *             the linked list
     */
    public void addBottom(E data) {
        if(top == null)
            top = tail = new NodeD<E>(data,null,null);
        else {
            tail = new NodeD<E>(data, null, tail);
            tail.getPrev().setNext(tail);
        }
    }

    /******************************************************************
     * Adds a new node with data from parameter before the index given
     * as a parameter on the list
     * @param data Generic data to be added as a node
     * @param index List index location new node is to be added before
     *
     * @throws IllegalArgumentException if trying to insert data at a
     *          at a negative index or past the end of the list
     */
    public void insertBefore(E data, int index) {
        if(index < 0 || index > getLen() - 1)
            throw new IllegalArgumentException();

        if(top == null || index == 0)
            addTop(data);

        else {
            cursor = top;
            for (int i = 0; i < index; i++)
                cursor = cursor.getNext();

            cursor.setPrev(new NodeD<>(data,cursor,cursor.getPrev()));
            cursor.getPrev().getPrev().setNext(cursor.getPrev());
        }
    }

    /******************************************************************
     * Deletes the top node of the list
     *
     * @throws RuntimeException if top == null, that is,
     *  			 there is no list.
     */
    public void deleteTop() {
        if(top == null)
            throw new RuntimeException();

        else if(top.getNext() == null)
            top = tail = null;

        else {
            top = top.getNext();
            top.setPrev(null);
        }

    }

    /******************************************************************
     * Deletes the tail node of the list
     *
     * @throws RuntimeException if top == null, that is,
     *      			 there is no list.
     */
    public void deleteBottom() {
        if(top == null)
            throw new RuntimeException();

        else if(tail.getPrev() == null)
            top = tail = null;

        else {
            tail = tail.getPrev();
            tail.setNext(null);
        }

    }

    /******************************************************************
     *
     * @param index
     *
     * @throws IllegalArgumentException if trying to delete a negative
     *      		 index or delete past the end of the list.
     */
    public void deleteAt(int index) {
        if(index < 0 || index > getLen() - 1)
            throw new IllegalArgumentException();

        if(index == 0)
            deleteTop();

        else if(index == getLen() - 1)
            deleteBottom();

        else {
            cursor = top;
            for (int i = 0; i < index; i++)
                cursor = cursor.getNext();

            cursor.getPrev().setNext(cursor.getNext());
            cursor.getNext().setPrev(cursor.getPrev());
        }
    }


    public String toString() {
        String retVal = "";
        NodeD<E> cur = top;
        while (cur != null) {
            retVal += cur.getData();
            cur = cur.getNext();
        }

        return retVal;
    }


    // Create the rest of the needed methods.


}