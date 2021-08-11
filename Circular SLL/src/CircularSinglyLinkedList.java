import java.util.NoSuchElementException;
/**
 * Your implementation of a CircularSinglyLinkedList without a tail pointer.
 *
 * @author Eashan Sinha
 * @version 1.0
 * @userid esinha6
 * @GTID 903598987
 *
 * Collaborators: LIST ALL COLLABORATORS YOU WORKED WITH HERE
 *
 * Resources: LIST ALL NON-COURSE RESOURCES YOU CONSULTED HERE
 */
public class CircularSinglyLinkedList<T> {


    // Do not add new instance variables or modify existing ones.
    private CircularSinglyLinkedListNode<T> head;
    private int size;

    // Do not add a constructor.

    /**
     * Adds the data to the specified index.
     *
     * Must be O(1) for indices 0 and size and O(n) for all other cases.
     *
     * @param index the index at which to add the new data
     * @param data  the data to add at the specified index
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index > size
     * @throws java.lang.IllegalArgumentException  if data is null
     */
    public void addAtIndex(int index, T data) throws IndexOutOfBoundsException, IllegalArgumentException {
        if ((index < 0) || (index > this.size)) {
            throw new IndexOutOfBoundsException("Index is out of bounds.");
        }
        if (data == null) {
            throw new IllegalArgumentException("Illegal Argument Exception: Data is null.");
        }
        if (isEmpty()) {
            head = new CircularSinglyLinkedListNode<T>(data);
            head.setNext(head);
            this.size++;
        } else if (index == 0) {
            CircularSinglyLinkedListNode<T> newHead = new CircularSinglyLinkedListNode<T>(data, head);
            CircularSinglyLinkedListNode<T> lastNode = head;
            int i = 0;
            while (i < this.size - 1) {
                lastNode = lastNode.getNext();
                i++;
            }
//            if (size == 1) {
//                lastNode.setNext(newHead);
////                newNode.setNext(current);
//            } else {
//                newHead.setNext(lastNode);
//            }
            lastNode.setNext(newHead);
            head = newHead;
            this.size++;
        } else {
            CircularSinglyLinkedListNode<T> newNode = new CircularSinglyLinkedListNode<T>(data, head);
            CircularSinglyLinkedListNode<T> current = head;
            CircularSinglyLinkedListNode<T> previous = head;
            int i = 0;
            while (i < index) {
                previous = current;
                current = current.getNext();
                i++;
            }
            newNode.setNext(current);
            previous.setNext(newNode);
            this.size++;
        }
    }

    /**
     * Adds the data to the front of the list.
     *
     * Must be O(1).
     *
     * @param data the data to add to the front of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToFront(T data) throws IllegalArgumentException {
        addAtIndex(0, data);
    }

    /**
     * Adds the data to the back of the list.
     *
     * Must be O(1).
     *
     * @param data the data to add to the back of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToBack(T data) throws IllegalArgumentException {
        if (data == null) {
            throw new IllegalArgumentException("Illegal Argument Exception: Data is null.");
        }
        if (isEmpty()) {
            head = new CircularSinglyLinkedListNode<T>(data);
            head.setNext(head);
            this.size++;
        } else {
            // Link setting
            CircularSinglyLinkedListNode<T> newNode = new CircularSinglyLinkedListNode<T>(data, head.getNext());
            head.setNext(newNode);
            this.size++;
            // Data update
            newNode.setData(head.getData());
            head.setData(data);
            head = newNode;
        }
    }

    /**
     * Removes and returns the data at the specified index.
     *
     * Must be O(1) for index 0 and O(n) for all other cases.
     *
     * @param index the index of the data to remove
     * @return the data formerly located at the specified index
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index >= size
     */
    public T removeAtIndex(int index) throws IndexOutOfBoundsException {
        if ((index < 0) || (index >= this.size)) {
            throw new IndexOutOfBoundsException("No node exists for given index. Given index is out of range of Linked List");
        }
        T removeData;
        CircularSinglyLinkedListNode<T> nodeToRemove = null;
        if (this.size == 1) {
            removeData = head.getData();
            head.setNext(null);
            head.setData(null);
            head = null;
        } else if (index == 0) {
            nodeToRemove = head.getNext();
            removeData = head.getData();
            head.setData(nodeToRemove.getData());
            head.setNext(nodeToRemove.getNext());
            nodeToRemove.setNext(null);
            nodeToRemove.setData(null);
        } else {
            nodeToRemove = head;
            CircularSinglyLinkedListNode<T> previous = head;
            int i = 0;
            while (i < index) {
                previous = nodeToRemove;
                nodeToRemove = nodeToRemove.getNext();
                i++;
            }
            removeData = nodeToRemove.getData();
            previous.setNext(nodeToRemove.getNext());
            nodeToRemove.setNext(null);
            nodeToRemove.setData(null);
        }
        this.size--;
        return removeData;
    }

    /**
     * Removes and returns the first data of the list.
     *
     * Must be O(1).
     *
     * @return the data formerly located at the front of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromFront() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException("This list is empty. There's nothing to remove.");
        }
        return removeAtIndex(0);
    }

    /**
     * Removes and returns the last data of the list.
     *
     * Must be O(n).
     *
     * @return the data formerly located at the back of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromBack() {
        if (isEmpty()) {
            throw new NoSuchElementException("This list is empty. There's nothing to remove.");
        }
        return removeAtIndex(this.size - 1);
    }

    /**
     * Returns the data at the specified index.
     *
     * Should be O(1) for index 0 and O(n) for all other cases.
     *
     * @param index the index of the data to get
     * @return the data stored at the index in the list
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index >= size
     */
    public T get(int index) throws IndexOutOfBoundsException {
        if ((index < 0) || (index >= this.size)) {
            throw new IndexOutOfBoundsException("No node exists for given index. Given index is out of range of Linked List");
        }
        CircularSinglyLinkedListNode<T> current = head;
        T dataToReturn;
        if (index == 0) {
            dataToReturn = current.getData();
        } else {
            int i = 0;
            while (i < index) {
                current = current.getNext();
                i++;
            }
            dataToReturn = current.getData();
        }
        return dataToReturn;
    }

    /**
     * Returns whether or not the list is empty.
     *
     * Must be O(1).
     *
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        return (head == null);
    }

    /**
     * Clears the list.
     *
     * Clears all data and resets the size.
     *
     * Must be O(1).
     */
    public void clear() {
        this.head = null;
        this.size = 0;
    }

    /**
     * Removes and returns the last copy of the given data from the list.
     *
     * Do not return the same data that was passed in. Return the data that
     * was stored in the list.
     *
     * Must be O(n).
     *
     * @param data the data to be removed from the list
     * @return the data that was removed
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException   if data is not found
     */
    public T removeLastOccurrence(T data) throws IllegalArgumentException, NoSuchElementException{
        if (data == null) {
            throw new IllegalArgumentException("Data is null.");
        }
        CircularSinglyLinkedListNode<T> nodeToRemove = head;
        CircularSinglyLinkedListNode<T> previous = null;
        T removeData = null;

        if(size == 0) {
            throw new NoSuchElementException("The data was not found in the Linked List.");
        }
        else if (size == 1) {
            if (nodeToRemove.getData().equals(data)) {
                return removeFromFront();
            }
        }
        else if (size == 2)
        {
            if(nodeToRemove.getNext().getData().equals(data)) {
                previous = nodeToRemove;
            } else if (nodeToRemove.getData().equals(data)) {
                return removeFromFront();
            }
        }
        while (nodeToRemove.getNext() != head) {
            if (nodeToRemove.getNext().getData().equals(data)) {
                previous = nodeToRemove;
                removeData = nodeToRemove.getNext().getData();
//                if ( (nodeToRemove.getNext() == head)
//                        || (nodeToRemove.getNext().getNext() == head)
//                        || (nodeToRemove.getNext().getNext().getNext() == head))
//                {
//                    nodeToRemove = nodeToRemove.getNext();
//                    break;
//                }
            }
            nodeToRemove = nodeToRemove.getNext();
        }
        if (removeData == null) {
            throw new NoSuchElementException("The data was not found in the Linked List.");
        }
        if (this.size == 1) {
            previous.setNext(null);
        } else {
//            previous.setNext(nodeToRemove.getNext());
            previous.setNext(previous.getNext().getNext());
        }
//        nodeToRemove.setNext(null);
//        nodeToRemove.setData(null);
        this.size--;
        return removeData;
    }

    /**
     * Returns an array representation of the linked list.
     *
     * Must be O(n) for all cases.
     *
     * @return the array of length size holding all of the data (not the
     * nodes) in the list in the same order
     */
    public T[] toArray() {
        T[] data = (T[]) new Object[this.size];
        CircularSinglyLinkedListNode<T> current = head;
        for (int i = 0; i < this.size; i++) {
            data[i] = current.getData();
            current = current.getNext();
        }
        return data;
    }


    /**
     * Returns the head node of the list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the node at the head of the list
     */
    public CircularSinglyLinkedListNode<T> getHead() {
        // DO NOT MODIFY!
        return head;
    }

    /**
     * Returns the size of the list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the size of the list
     */
    public int size() {
        // DO NOT MODIFY!
        return size;
    }

    public void print() {
        CircularSinglyLinkedListNode<T> node = head;
        while (node.getNext() != head) {
            System.out.print(node.getData() + " -> ");
            node = node.getNext();
        }
        System.out.println(node.getData());
    }
}
