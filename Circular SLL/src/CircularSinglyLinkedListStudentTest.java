import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

/**
 * This is a basic set of unit tests for CircularSinglyLinkedList.
 *
 * Passing these tests doesn't guarantee any grade on these assignments. These
 * student JUnits that we provide should be thought of as a sanity check to
 * help you get started on the homework and writing JUnits in general.
 *
 * We highly encourage you to write your own set of JUnits for each homework
 * to cover edge cases you can think of for each data structure. Your code must
 * work correctly and efficiently in all cases, which is why it's important
 * to write comprehensive tests to cover as many cases as possible.
 *
 * @author CS 1332 TAs
 * @version 1.0
 */
public class CircularSinglyLinkedListStudentTest {

    private static final int TIMEOUT = 200;
    private CircularSinglyLinkedList<String> list;

    @Before
    public void setUp() {
        list = new CircularSinglyLinkedList<>();
    }

    @Test(timeout = TIMEOUT)
    public void testInitialization() {
        assertEquals(0, list.size());
        assertNull(list.getHead());
    }

    @Test(timeout = TIMEOUT)
    public void testAddAtIndex() {
        list.addAtIndex(0, "2a");   // 2a
        list.addAtIndex(0, "1a");   // 1a, 2a
        list.addAtIndex(2, "4a");   // 1a, 2a, 4a
        list.addAtIndex(2, "3a");   // 1a, 2a, 3a, 4a
        list.addAtIndex(0, "0a");   // 0a, 1a, 2a, 3a, 4a

        assertEquals(5, list.size());

        CircularSinglyLinkedListNode<String> cur = list.getHead();
        assertNotNull(cur);
        assertEquals("0a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("1a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("2a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("3a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("4a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals(list.getHead(), cur);
    }

    @Test(timeout = TIMEOUT)
    public void testAddToFront() {
        list.addToFront("4a");  // 4a
        list.addToFront("3a");  // 3a, 4a
        list.addToFront("2a");  // 2a, 3a, 4a
        list.addToFront("1a");  // 1a, 2a, 3a, 4a
        list.addToFront("0a");  // 0a, 1a, 2a, 3a, 4a

        assertEquals(5, list.size());

        CircularSinglyLinkedListNode<String> cur = list.getHead();
        assertNotNull(cur);
        assertEquals("0a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("1a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("2a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("3a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("4a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals(list.getHead(), cur);
    }

    @Test(timeout = TIMEOUT)
    public void testAddToBack() {
        list.addToBack("0a");   // 0a
        list.addToBack("1a");   // 0a, 1a
        list.addToBack("2a");   // 0a, 1a, 2a
        list.addToBack("3a");   // 0a, 1a, 2a, 3a
        list.addToBack("4a");   // 0a, 1a, 2a, 3a, 4a

        assertEquals(5, list.size());

        CircularSinglyLinkedListNode<String> cur = list.getHead();
        assertNotNull(cur);
        assertEquals("0a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("1a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("2a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("3a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("4a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals(list.getHead(), cur);
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveAtIndex() {
        String temp = "2a";

        list.addAtIndex(0, "0a");   // 0a
        list.addAtIndex(1, "1a");   // 0a, 1a
        list.addAtIndex(2, temp);   // 0a, 1a, 2a
        list.addAtIndex(3, "3a");   // 0a, 1a, 2a, 3a
        list.addAtIndex(4, "4a");   // 0a, 1a, 2a, 3a, 4a
        list.addAtIndex(5, "5a");   // 0a, 1a, 2a, 3a, 4a, 5a
        assertEquals(6, list.size());

        assertSame(temp, list.removeAtIndex(2));    // 0a, 1a, 3a, 4a, 5a

        assertEquals(5, list.size());

        CircularSinglyLinkedListNode<String> cur = list.getHead();
        assertNotNull(cur);
        assertEquals("0a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("1a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("3a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("4a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("5a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals(list.getHead(), cur);
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveFromFront() {
        String temp = "0a";

        list.addAtIndex(0, temp);   // 0a
        list.addAtIndex(1, "1a");   // 0a, 1a
        list.addAtIndex(2, "2a");   // 0a, 1a, 2a
        list.addAtIndex(3, "3a");   // 0a, 1a, 2a, 3a
        list.addAtIndex(4, "4a");   // 0a, 1a, 2a, 3a, 4a
        list.addAtIndex(5, "5a");   // 0a, 1a, 2a, 3a, 4a, 5a
        assertEquals(6, list.size());

        assertSame(temp, list.removeFromFront());   // 1a, 2a, 3a, 4a, 5a

        assertEquals(5, list.size());

        CircularSinglyLinkedListNode<String> cur = list.getHead();
        assertNotNull(cur);
        assertEquals("1a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("2a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("3a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("4a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("5a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals(list.getHead(), cur);
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveFromBack() {
        String temp = "5a";

        list.addAtIndex(0, "0a");   // 0a
        list.addAtIndex(1, "1a");   // 0a, 1a
        list.addAtIndex(2, "2a");   // 0a, 1a, 2a
        list.addAtIndex(3, "3a");   // 0a, 1a, 2a, 3a
        list.addAtIndex(4, "4a");   // 0a, 1a, 2a, 3a, 4a
        list.addAtIndex(5, temp);   // 0a, 1a, 2a, 3a, 4a, 5a
        assertEquals(6, list.size());

        assertSame(temp, list.removeFromBack());    // 0a, 1a, 2a, 3a, 4a

        assertEquals(5, list.size());

        CircularSinglyLinkedListNode<String> cur = list.getHead();
        assertNotNull(cur);
        assertEquals("0a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("1a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("2a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("3a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("4a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals(list.getHead(), cur);
    }

    @Test(timeout = TIMEOUT)
    public void testGet() {
        list.addAtIndex(0, "0a");   // 0a
        list.addAtIndex(1, "1a");   // 0a, 1a
        list.addAtIndex(2, "2a");   // 0a, 1a, 2a
        list.addAtIndex(3, "3a");   // 0a, 1a, 2a, 3a
        list.addAtIndex(4, "4a");   // 0a, 1a, 2a, 3a, 4a
        assertEquals(5, list.size());

        assertEquals("0a", list.get(0));
        assertEquals("1a", list.get(1));
        assertEquals("2a", list.get(2));
        assertEquals("3a", list.get(3));
        assertEquals("4a", list.get(4));
    }

    @Test(timeout = TIMEOUT)
    public void testIsEmpty() {
        assertTrue(list.isEmpty());

        list.addAtIndex(0, "0a");   // 0a
        list.addAtIndex(1, "1a");   // 0a, 1a
        list.addAtIndex(2, "2a");   // 0a, 1a, 2a
        list.addAtIndex(3, "3a");   // 0a, 1a, 2a, 3a
        list.addAtIndex(4, "4a");   // 0a, 1a, 2a, 3a, 4a
        assertEquals(5, list.size());

        assertFalse(list.isEmpty());
    }

    @Test(timeout = TIMEOUT)
    public void testClear() {
        list.addAtIndex(0, "0a");   // 0a
        list.addAtIndex(1, "1a");   // 0a, 1a
        list.addAtIndex(2, "2a");   // 0a, 1a, 2a
        list.addAtIndex(3, "3a");   // 0a, 1a, 2a, 3a
        list.addAtIndex(4, "4a");   // 0a, 1a, 2a, 3a, 4a
        assertEquals(5, list.size());

        list.clear();

        assertEquals(0, list.size());
        assertNull(list.getHead());
    }

    @Test(timeout = TIMEOUT)
    public void testLastOccurrence() {
        String temp = new String("2a");

        list.addAtIndex(0, "0a");   // 0a
        list.addAtIndex(1, "2a");   // 0a, 2a
        list.addAtIndex(2, "1a");   // 0a, 2a, 1a
        list.addAtIndex(3, temp);   // 0a, 2a, 1a, 2a
        list.addAtIndex(4, "3a");   // 0a, 2a, 1a, 2a, 3a
        list.addAtIndex(5, "4a");   // 0a, 2a, 1a, 2a, 3a, 4a
        assertEquals(6, list.size());

        assertSame(temp, list.removeLastOccurrence("2a")); // 0a, 2a, 1a, 3a, 4a
        assertEquals(5, list.size());

        CircularSinglyLinkedListNode<String> cur = list.getHead();
        assertNotNull(cur);
        assertEquals("0a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("2a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("1a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("3a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("4a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals(list.getHead(), cur);
    }

//    @Test(timeout = TIMEOUT)
//    public void testToArray() {
//        list.addAtIndex(0, "0a");   // 0a
//        list.addAtIndex(1, "1a");   // 0a, 1a
//        list.addAtIndex(2, "2a");   // 0a, 1a, 2a
//        list.addAtIndex(3, "3a");   // 0a, 1a, 2a, 3a
//        list.addAtIndex(4, "4a");   // 0a, 1a, 2a, 3a, 4a
//        assertEquals(5, list.size());
//
//        String[] expected = new String[5];
//        expected[0] = "0a";
//        expected[1] = "1a";
//        expected[2] = "2a";
//        expected[3] = "3a";
//        expected[4] = "4a";
////        assertArrayEquals(expected, list.toArray());
//    }

    /*
    @Test(timeout = TIMEOUT)
    public void testInitialization() {
        assertEquals(0, list.size());
        assertNull(list.getHead());
    }
    */

    @Test(timeout = TIMEOUT)
    public void testAddAtIndexInvalidIndex() {
        assertThrows(IndexOutOfBoundsException.class, () -> {
            list.addAtIndex(-1, "A");
        });

        assertThrows(IndexOutOfBoundsException.class, () -> {
            list.addAtIndex(1, "A");
        });
    }

    @Test(timeout = TIMEOUT)
    public void testAddAtIndexSizeZero() {
        assertThrows(IllegalArgumentException.class, () -> {
            list.addAtIndex(0, null);
        });

        list.addAtIndex(0, "A");

        assertEquals(1, list.size());

        CircularSinglyLinkedListNode<String> cur = list.getHead();
        assertNotNull(cur);
        assertEquals("A", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals(list.getHead(), cur);
    }

    @Test(timeout = TIMEOUT)
    public void testAddAtIndexSizeOneIndexZero() {
        list.addAtIndex(0, "B");

        assertThrows(IllegalArgumentException.class, () -> {
            list.addAtIndex(0, null);
        });

        list.addAtIndex(0, "A");

        assertEquals(2, list.size());

        CircularSinglyLinkedListNode<String> cur = list.getHead();
        assertNotNull(cur);
        assertEquals("A", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("B", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals(list.getHead(), cur);
    }

    @Test(timeout = TIMEOUT)
    public void testAddAtIndexSizeOneIndexOne() {
        list.addAtIndex(0, "A");

        assertThrows(IllegalArgumentException.class, () -> {
            list.addAtIndex(1, null);
        });

        list.addAtIndex(1, "B");

        assertEquals(2, list.size());

        CircularSinglyLinkedListNode<String> cur = list.getHead();
        assertNotNull(cur);
        assertEquals("A", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("B", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals(list.getHead(), cur);
    }

    @Test(timeout = TIMEOUT)
    public void testAddAtIndexSizeTwoIndexZero() {
        list.addAtIndex(0, "B");
        list.print();

        list.addAtIndex(1, "C");
        list.print();


        assertThrows(IllegalArgumentException.class, () -> {
            list.addAtIndex(0, null);
        });

        list.addAtIndex(0, "A");
        list.print();

        assertEquals(3, list.size());
        list.print();

        CircularSinglyLinkedListNode<String> cur = list.getHead();
        assertNotNull(cur);
        assertEquals("A", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("B", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("C", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals(list.getHead(), cur);
    }

    @Test(timeout = TIMEOUT)
    public void testAddAtIndexSizeTwoIndexOne() {
        list.addAtIndex(0, "A");
        list.addAtIndex(1, "C");

        assertThrows(IllegalArgumentException.class, () -> {
            list.addAtIndex(1, null);
        });

        list.addAtIndex(1, "B");

        assertEquals(3, list.size());

        CircularSinglyLinkedListNode<String> cur = list.getHead();
        assertNotNull(cur);
        assertEquals("A", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("B", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("C", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals(list.getHead(), cur);
    }

    @Test(timeout = TIMEOUT)
    public void testAddAtIndexSizeTwoIndexTwo() {
        list.addAtIndex(0, "A");
        list.addAtIndex(1, "B");

        assertThrows(IllegalArgumentException.class, () -> {
            list.addAtIndex(2, null);
        });

        list.addAtIndex(2, "C");

        assertEquals(3, list.size());

        CircularSinglyLinkedListNode<String> cur = list.getHead();
        assertNotNull(cur);
        assertEquals("A", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("B", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("C", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals(list.getHead(), cur);
    }

    @Test(timeout = TIMEOUT)
    public void testAddToFrontSizeZero() {
        assertThrows(IllegalArgumentException.class, () -> {
            list.addToFront(null);
        });

        list.addToFront("A");

        assertEquals(1, list.size());

        CircularSinglyLinkedListNode<String> cur = list.getHead();
        assertNotNull(cur);
        assertEquals("A", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals(list.getHead(), cur);
    }

    @Test(timeout = TIMEOUT)
    public void testAddToFrontSizeOne() {
        list.addToFront("B");

        assertThrows(IllegalArgumentException.class, () -> {
            list.addToFront(null);
        });

        list.addToFront( "A");

        assertEquals(2, list.size());

        CircularSinglyLinkedListNode<String> cur = list.getHead();
        assertNotNull(cur);
        assertEquals("A", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("B", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals(list.getHead(), cur);
    }

    @Test(timeout = TIMEOUT)
    public void testAddToFrontSizeTwo() {
        list.addToFront("C");
        list.addToFront("B");

        assertThrows(IllegalArgumentException.class, () -> {
            list.addToFront(null);
        });

        list.addToFront("A");

        assertEquals(3, list.size());

        CircularSinglyLinkedListNode<String> cur = list.getHead();
        assertNotNull(cur);
        assertEquals("A", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("B", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("C", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals(list.getHead(), cur);
    }

    @Test(timeout = TIMEOUT)
    public void testAddToBackSizeZero() {
        assertThrows(IllegalArgumentException.class, () -> {
            list.addToBack(null);
        });

        list.addToBack("A");

        assertEquals(1, list.size());

        CircularSinglyLinkedListNode<String> cur = list.getHead();
        assertNotNull(cur);
        assertEquals("A", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals(list.getHead(), cur);
    }

    @Test(timeout = TIMEOUT)
    public void testAddToBackSizeOne() {
        list.addToBack("A");

        assertThrows(IllegalArgumentException.class, () -> {
            list.addToBack(null);
        });

        list.addToBack( "B");

        assertEquals(2, list.size());

        CircularSinglyLinkedListNode<String> cur = list.getHead();
        assertNotNull(cur);
        assertEquals("A", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("B", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals(list.getHead(), cur);
    }

    @Test(timeout = TIMEOUT)
    public void testAddToBackSizeTwo() {
        list.addToBack("A");
        list.addToBack("B");

        assertThrows(IllegalArgumentException.class, () -> {
            list.addToBack(null);
        });

        list.addToBack("C");

        assertEquals(3, list.size());

        CircularSinglyLinkedListNode<String> cur = list.getHead();
        assertNotNull(cur);
        assertEquals("A", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("B", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("C", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals(list.getHead(), cur);
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveAtIndexSizeZero() {
        assertThrows(IndexOutOfBoundsException.class, () -> {
            list.removeAtIndex(-1);
        });

        assertThrows(IndexOutOfBoundsException.class, () -> {
            list.removeAtIndex(0);
        });

        assertThrows(IndexOutOfBoundsException.class, () -> {
            list.removeAtIndex(1);
        });
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveAtIndexSizeOne() {
        list.addToFront("A");

        assertEquals(1, list.size());

        assertSame("A", list.removeAtIndex(0));

        assertEquals(0, list.size());

        assertNull(list.getHead());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveAtIndexSizeTwoIndexZero() {
        list.addToFront("B");
        list.addToFront("A");

        assertEquals(2, list.size());

        assertSame("A", list.removeAtIndex(0));

        assertEquals(1, list.size());

        CircularSinglyLinkedListNode<String> cur = list.getHead();
        assertNotNull(cur);
        assertEquals("B", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals(list.getHead(), cur);
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveAtIndexSizeTwoIndexOne() {
        list.addToFront("B");
        list.addToFront("A");

        assertEquals(2, list.size());

        assertSame("B", list.removeAtIndex(1));

        assertEquals(1, list.size());

        CircularSinglyLinkedListNode<String> cur = list.getHead();
        assertNotNull(cur);
        assertEquals("A", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals(list.getHead(), cur);
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveAtIndexSizeThreeIndexOne() {
        list.addToFront("C");
        list.addToFront("B");
        list.addToFront("A");

        assertEquals(3, list.size());

        assertSame("B", list.removeAtIndex(1));

        assertEquals(2, list.size());

        CircularSinglyLinkedListNode<String> cur = list.getHead();
        assertNotNull(cur);
        assertEquals("A", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("C", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals(list.getHead(), cur);
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveFromFrontSizeZero() {
        assertThrows(NoSuchElementException.class, () -> {
            list.removeFromFront();
        });
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveFromFrontSizeOne() {
        list.addToFront("A");

        assertEquals(1, list.size());

        assertSame("A", list.removeFromFront());

        assertEquals(0, list.size());

        assertNull(list.getHead());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveFromFrontSizeTwo() {
        list.addToFront("B");
        list.addToFront("A");

        assertEquals(2, list.size());

        assertSame("A", list.removeFromFront());

        assertEquals(1, list.size());

        CircularSinglyLinkedListNode<String> cur = list.getHead();
        assertNotNull(cur);
        assertEquals("B", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals(list.getHead(), cur);
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveFromBackSizeZero() {
        assertThrows(NoSuchElementException.class, () -> {
            list.removeFromBack();
        });
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveFromBackSizeOne() {
        list.addToFront("A");

        assertEquals(1, list.size());

        assertSame("A", list.removeFromBack());

        assertEquals(0, list.size());

        assertNull(list.getHead());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveFromBackSizeTwo() {
        list.addToFront("B");
        list.addToFront("A");

        assertEquals(2, list.size());

        assertSame("B", list.removeFromBack());

        assertEquals(1, list.size());

        CircularSinglyLinkedListNode<String> cur = list.getHead();
        assertNotNull(cur);
        assertEquals("A", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals(list.getHead(), cur);
    }

    @Test(timeout = TIMEOUT)
    public void testGetInvalidIndex() {
        list.addToFront("A");

        assertEquals(1, list.size());

        assertThrows(IndexOutOfBoundsException.class, () -> {
            list.get(-1);
        });

        assertThrows(IndexOutOfBoundsException.class, () -> {
            list.get(1);
        });

    }

    @Test(timeout = TIMEOUT)
    public void testGetSizeZero() {
        assertThrows(IndexOutOfBoundsException.class, () -> {
            list.get(-1);
        });

        assertThrows(IndexOutOfBoundsException.class, () -> {
            list.get(0);
        });

        assertThrows(IndexOutOfBoundsException.class, () -> {
            list.get(1);
        });
    }

    @Test(timeout = TIMEOUT)
    public void testGetSizeOne() {
        list.addToFront("A");

        assertEquals(1, list.size());

        assertEquals("A", list.get(0));
    }

    @Test(timeout = TIMEOUT)
    public void testGetSizeTwoIndexZero() {
        list.addToFront("B");
        list.addToFront("A");

        assertEquals(2, list.size());

        assertEquals("A", list.get(0));
    }

    @Test(timeout = TIMEOUT)
    public void testGetSizeTwoIndexOne() {
        list.addToFront("B");
        list.addToFront("A");

        assertEquals(2, list.size());

        assertEquals("B", list.get(1));
    }

    @Test(timeout = TIMEOUT)
    public void testGetSizeThreeIndexTwo() {
        list.addToFront("C");
        list.addToFront("B");
        list.addToFront("A");

        assertEquals(3, list.size());

        assertEquals("C", list.get(2));
    }

    /*
    @Test(timeout = TIMEOUT)
    public void testIsEmpty() {
        assertTrue(list.isEmpty());

        list.addToFront("A");

        assertEquals(1, list.size());

        assertFalse(list.isEmpty());
    }
    */

    /*
    @Test(timeout = TIMEOUT)
    public void testClear() {
        list.addToFront("A");

        assertEquals(1, list.size());

        list.clear();

        assertEquals(0, list.size());
        assertNull(list.getHead());
    }
    */

    @Test(timeout = TIMEOUT)
    public void testToArraySizeZero() {
        String[] expected = {};

        assertArrayEquals(expected, list.toArray());
    }

    @Test(timeout = TIMEOUT)
    public void testToArraySizeOne() {
        list.addToFront("A");

        assertEquals(1, list.size());

        String[] expected = {"A"};

        assertArrayEquals(expected, list.toArray());
    }

    @Test(timeout = TIMEOUT)
    public void testToArraySizeFive() {
        list.addToFront("E");
        list.addToFront("D");
        list.addToFront("C");
        list.addToFront("B");
        list.addToFront("A");

        assertEquals(5, list.size());

        String[] expected = {"A", "B", "C", "D", "E"};

        assertArrayEquals(expected, list.toArray());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveLastOccurrenceNullData() {
        assertThrows(IllegalArgumentException.class, () -> {
            list.removeLastOccurrence(null);
        });
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveLastOccurrenceSizeZero() {
        assertThrows(NoSuchElementException.class, () -> {
            list.removeLastOccurrence("A");
        });
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveLastOccurrenceSizeOneFound() {
        list.addToFront("A");

        assertEquals(1, list.size());

        assertSame("A", list.removeLastOccurrence("A"));

        assertEquals(0, list.size());
        assertNull(list.getHead());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveLastOccurrenceSizeOneNotFound() {
        list.addToFront("A");

        assertEquals(1, list.size());

        assertThrows(NoSuchElementException.class, () -> {
            list.removeLastOccurrence("B");
        });

        assertEquals(1, list.size());

        CircularSinglyLinkedListNode<String> cur = list.getHead();
        assertNotNull(cur);
        assertEquals("A", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals(list.getHead(), cur);
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveLastOccurrenceSizeTwoFoundIndexOne() {
        list.addToFront("B");
        list.addToFront("A");

        assertEquals(2, list.size());

        assertSame("A", list.removeLastOccurrence("A"));

        assertEquals(1, list.size());

        CircularSinglyLinkedListNode<String> cur = list.getHead();
        assertNotNull(cur);
        assertEquals("B", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals(list.getHead(), cur);
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveLastOccurrenceSizeTwoFoundIndexTwo() {
        list.addToFront("B");
        list.addToFront("A");

        assertEquals(2, list.size());

        assertSame("B", list.removeLastOccurrence("B"));

        assertEquals(1, list.size());

        CircularSinglyLinkedListNode<String> cur = list.getHead();
        assertNotNull(cur);
        assertEquals("A", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals(list.getHead(), cur);
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveLastOccurrenceSizeTwoNotFound() {
        list.addToFront("B");
        list.addToFront("A");

        assertEquals(2, list.size());

        assertThrows(NoSuchElementException.class, () -> {
            list.removeLastOccurrence("C");
        });

        assertEquals(2, list.size());

        CircularSinglyLinkedListNode<String> cur = list.getHead();
        assertNotNull(cur);
        assertEquals("A", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("B", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals(list.getHead(), cur);
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveLastOccurrenceSizeFiveWithDuplicates() {
        String a = new String("A");
        String b = new String("B");

        list.addToFront("A");
        list.addToFront("B");
        list.addToFront(a);
        list.addToFront(b);
        list.addToFront("A");
        list.print();

        assertEquals(5, list.size());

        assertSame("A", list.removeLastOccurrence("A"));

        assertEquals(4, list.size());
        list.print();
        CircularSinglyLinkedListNode<String> cur = list.getHead();
        assertNotNull(cur);
        assertEquals("A", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("B", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("A", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("B", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals(list.getHead(), cur);
        list.print();
        assertSame(a, list.removeLastOccurrence("A"));

        assertEquals(3, list.size());
        list.print();

        cur = list.getHead();
        assertNotNull(cur);
        assertEquals("A", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("B", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("B", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals(list.getHead(), cur);

        assertSame("B", list.removeLastOccurrence("B"));

        assertEquals(2, list.size());

        cur = list.getHead();
        assertNotNull(cur);
        assertEquals("A", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("B", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals(list.getHead(), cur);

        assertSame("A", list.removeLastOccurrence("A"));

        assertEquals(1, list.size());

        cur = list.getHead();
        assertNotNull(cur);
        assertEquals("B", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals(list.getHead(), cur);
    }
}
