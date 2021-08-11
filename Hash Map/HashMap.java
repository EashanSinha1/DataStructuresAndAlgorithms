import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.HashSet;

/**
 * Your implementation of a HashMap.
 *
 * @author EASHAN SINHA
 * @version 1.0
 * @userid esinha6@gatech.edu
 * @GTID 903598987
 *
 * Collaborators: LIST ALL COLLABORATORS YOU WORKED WITH HERE
 *
 * Resources: LIST ALL NON-COURSE RESOURCES YOU CONSULTED HERE
 */
public class HashMap<K, V> {

    /*
     * The initial capacity of the HashMap when created with the
     * default constructor.
     *
     * DO NOT MODIFY THIS VARIABLE!
     */
    public static final int INITIAL_CAPACITY = 13;

    /*
     * The max load factor of the HashMap.
     *
     * DO NOT MODIFY THIS VARIABLE!
     */
    public static final double MAX_LOAD_FACTOR = 0.67;

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private MapEntry<K, V>[] table;
    private int size;

    /**
     * Constructs a new HashMap.
     *
     * The backing array should have an initial capacity of INITIAL_CAPACITY.
     *
     * Use constructor chaining.
     */
    public HashMap() {
        this(INITIAL_CAPACITY);
    }

    /**
     * Constructs a new HashMap.
     *
     * The backing array should have an initial capacity of initialCapacity.
     *
     * You may assume initialCapacity will always be positive.
     *
     * @param initialCapacity the initial capacity of the backing array
     */
    public HashMap(int initialCapacity) {
        this.table = (MapEntry<K, V>[]) new MapEntry[initialCapacity];
        this.size = 0;
    }

    /**
     * Adds the given key-value pair to the map. If an entry in the map
     * already has this key, replace the entry's value with the new one
     * passed in.
     *
     * In the case of a collision, use external chaining as your resolution
     * strategy. Add new entries to the front of an existing chain, but don't
     * forget to check the entire chain for duplicate keys first.
     *
     * If you find a duplicate key, then replace the entry's value with the new
     * one passed in. When replacing the old value, replace it at that position
     * in the chain, not by creating a new entry and adding it to the front.
     *
     * Before actually adding any data to the HashMap, you should check to
     * see if the array would violate the max load factor if the data was
     * added. Resize if the load factor (LF) is greater than max LF (it is okay
     * if the load factor is equal to max LF). For example, let's say the
     * array is of length 5 and the current size is 3 (LF = 0.6). For this
     * example, assume that no elements are removed in between steps. If
     * another entry is attempted to be added, before doing anything else,
     * you should check whether (3 + 1) / 5 = 0.8 is larger than the max LF.
     * It is, so you would trigger a resize before you even attempt to add
     * the data or figure out if it's a duplicate. Be careful to consider the
     * differences between integer and double division when calculating load
     * factor.
     *
     * When regrowing, resize the length of the backing table to
     * (2 * old length) + 1. You must use the resizeBackingTable method to do so.
     *
     * Return null if the key was not already in the map. If it was in the map,
     * return the old value associated with it.
     *
     * @param key   the key to add
     * @param value the value to add
     * @return null if the key was not already in the map. If it was in the
     * map, return the old value associated with it
     * @throws java.lang.IllegalArgumentException if key or value is null
     */
    public V put(K key, V value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException("The inputted key or value is null.");
        }
        if ((double) (this.size + 1) > this.table.length * MAX_LOAD_FACTOR) {
            resizeBackingTable((2 * table.length) + 1);
        }
        int index = Math.abs(key.hashCode() % table.length);
        if (table[index] == null) {
            table[index] = new MapEntry<K, V>(key, value);
            ++this.size;
            return null;
        } else {
            MapEntry<K, V> mEntry = table[index];
            while (mEntry != null) {
                if (mEntry.getKey().equals(key)) {
                    V previousValue = mEntry.getValue();
                    mEntry.setValue(value);
                    return previousValue;
                }
                mEntry = mEntry.getNext();
            }
            MapEntry<K, V> newEntry = new MapEntry<>(key, value);
            newEntry.setNext(table[index]);
            table[index] = newEntry;
            this.size++;
            return null;
        }
    }

    /**
     * Removes the entry with a matching key from the map.
     *
     * @param key the key to remove
     * @return the value associated with the key
     * @throws java.lang.IllegalArgumentException if key is null
     * @throws java.util.NoSuchElementException   if the key is not in the map
     */
    public V remove(K key) {
        if (key == null) {
            throw new IllegalArgumentException("The inputted key is null.");
        }
        int index = Math.abs(key.hashCode() % table.length);
        if (table[index] != null) {
            MapEntry<K, V> mEntry = table[index];
            MapEntry<K, V> prevEntry = null;
            while ((mEntry.getNext() != null) && (!mEntry.getKey().equals(key))) {
                prevEntry = mEntry;
                mEntry = mEntry.getNext();
            }
            if (mEntry.getKey().equals(key)) {
                V eValue = mEntry.getValue();
                if (prevEntry == null) {
                    table[index] = mEntry.getNext();
                } else {
                    prevEntry.setNext(mEntry.getNext());
                }
                size--;
                return eValue;
            }
        }
        throw new NoSuchElementException("The key does not exist in the map.");
    }

    /**
     * Gets the value associated with the given key.
     *
     * @param key the key to search for in the map
     * @return the value associated with the given key
     * @throws java.lang.IllegalArgumentException if key is null
     * @throws java.util.NoSuchElementException   if the key is not in the map
     */
    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("The inputted key is null.");
        }
        int index = Math.abs(key.hashCode() % table.length);
        MapEntry<K, V> mEntry = table[index];
        while ((mEntry != null) && (!mEntry.getKey().equals(key))) {
            mEntry = mEntry.getNext();
        }
        if (mEntry != null) {
            V eValue = mEntry.getValue();
            return eValue;
        }
        throw new NoSuchElementException("The key does not exist in the map.");
    }

    /**
     * Returns whether or not the key is in the map.
     *
     * @param key the key to search for in the map
     * @return true if the key is contained within the map, false
     * otherwise
     * @throws java.lang.IllegalArgumentException if key is null
     */
    public boolean containsKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException("The inputted key is null.");
        }
        int index = Math.abs(key.hashCode() % table.length);
        if (table[index] == null) {
            return false;
        } else {
            //MapEntry<K, V> target = table[index];
            while (table[index] != null) {
                if (table[index].getKey() == key) {
                    return true;
                }
                table[index] = table[index].getNext();
            }
            return false;
        }
    }

    /**
     * Returns a Set view of the keys contained in this map.
     *
     * Use java.util.HashSet.
     *
     * @return the set of keys in this map
     */
    public Set<K> keySet() {
        Set<K> setKeys = new HashSet<>();
        for (MapEntry<K, V> mEntry : table) {
            if (mEntry != null) {
                setKeys.add(mEntry.getKey());
                while (mEntry.getNext() != null) {
                    mEntry = mEntry.getNext();
                    setKeys.add(mEntry.getKey());
                }
            }
        }
        return setKeys;
    }

    /**
     * Returns a List view of the values contained in this map.
     *
     * Use java.util.ArrayList or java.util.LinkedList.
     *
     * You should iterate over the table in order of increasing index and add
     * entries to the List in the order in which they are traversed.
     *
     * @return list of values in this map
     */
    public List<V> values() {
        List<V> mValues = new ArrayList<>();
        for (MapEntry<K, V> mEntry : table) {
            if (mEntry != null) {
                mValues.add(mEntry.getValue());
                while (mEntry.getNext() != null) {
                    mEntry = mEntry.getNext();
                    mValues.add(mEntry.getValue());
                }
            }
        }
        return mValues;
    }

    /**
     * Resize the backing table to length.
     *
     * Disregard the load factor for this method. So, if the passed in length is
     * smaller than the current capacity, and this new length causes the table's
     * load factor to exceed MAX_LOAD_FACTOR, you should still resize the table
     * to the specified length and leave it at that capacity.
     *
     * You should iterate over the old table in order of increasing index and
     * add entries to the new table in the order in which they are traversed.
     *
     * Since resizing the backing table is working with the non-duplicate
     * data already in the table, you shouldn't explicitly check for
     * duplicates.
     *
     * Hint: You cannot just simply copy the entries over to the new array.
     *
     * @param length new length of the backing table
     * @throws java.lang.IllegalArgumentException if length is less than the
     *                                            number of items in the hash
     *                                            map
     */
    public void resizeBackingTable(int length) {
        if ((length < 0) || (length < size)) {
            throw new IllegalArgumentException("The inputted length is less than the size of the hash set.");
        }
        if (length == 0) {
            this.table = new MapEntry[0];
            return;
        }
        MapEntry<K, V>[] newTable = new MapEntry[length];
        for (int i = 0; i < table.length; i++) {
            MapEntry<K, V> mEntry = table[i];
            while (mEntry != null) {
                int index = Math.abs(mEntry.getKey().hashCode() % newTable.length);
                MapEntry<K, V> newEntry = newTable[index];
                if (newEntry == null) {
                    newTable[index] = new MapEntry<K, V>(mEntry.getKey(), mEntry.getValue());
                } else {
                    newTable[index] = new MapEntry<K, V>(mEntry.getKey(), mEntry.getValue(), newEntry);
                    MapEntry<K, V> prev = null;
                }
                mEntry = mEntry.getNext();
            }
        }
        //for (MapEntry<K, V> mEntry : table) {
        //if (mEntry != null) {
        //int index = Math.abs(mEntry.getKey().hashCode() % newTable.length);
        //newTable[index] = mEntry;
        //}
        //}
        table = newTable;
    }

    /**
     * Clears the map.
     *
     * Resets the table to a new array of the initial capacity and resets the
     * size.
     */
    public void clear() {
        this.table = (MapEntry<K, V>[]) new MapEntry[INITIAL_CAPACITY];
        this.size = 0;
    }

    /**
     * Returns the table of the map.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the table of the map
     */
    public MapEntry<K, V>[] getTable() {
        // DO NOT MODIFY THIS METHOD!
        return table;
    }

    /**
     * Returns the size of the map.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the size of the map
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }
}
