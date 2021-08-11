import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Your implementation of a BST.
 *
 * @author EASHAN SINHA
 * @version 1.0
 * @userid esinha6
 * @GTID 903598987
 *
 * Collaborators:
 *
 * Resources: Java API Documentation
 */
public class BST<T extends Comparable<? super T>> {

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private BSTNode<T> root;
    private int size;

    /**
     * Constructs a new BST.
     *
     * This constructor should initialize an empty BST.
     *
     * Since instance variables are initialized to their default values, there
     * is no need to do anything for this constructor.
     */
    public BST() {
        // DO NOT IMPLEMENT THIS CONSTRUCTOR!
    }

    /**
     * Constructs a new BST.
     *
     * This constructor should initialize the BST with the data in the
     * Collection. The data should be added in the same order it is in the
     * Collection.
     *
     * Hint: Not all Collections are indexable like Lists, so a regular for loop
     * will not work here. However, all Collections are Iterable, so what type
     * of loop would work?
     *
     * @param data the data to add
     * @throws java.lang.IllegalArgumentException if data or any element in data
     *                                            is null
     */
    public BST(Collection<T> data) {
        if (data == null || data.contains(null)) {
            throw new IllegalArgumentException("The data passed in is null.");
        }
        for (T currentNode : data) {
            root = populate(root, currentNode);
        }
    }

    /**
     * This method represents a helper method for both add() and the
     * BST(Collection<> data) constructor
     *
     * @param root which is the first node in the tree structure
     * @param currentNode which is the node we are trying to add to the tree
     * @return the root node
     */
    private BSTNode<T> populate(BSTNode<T> root, T currentNode) {
        if (root == null) {
            size++;
            return new BSTNode<>(currentNode);
        }
        if (currentNode.compareTo(root.getData()) < 0) {
            root.setLeft(populate(root.getLeft(), currentNode));
        }
        if (currentNode.compareTo(root.getData()) > 0) {
            root.setRight(populate(root.getRight(), currentNode));
        }
        return root;
    }

    /**
     * Adds the data to the tree.
     *
     * This must be done recursively.
     *
     * The data becomes a leaf in the tree.
     *
     * Traverse the tree to find the appropriate location. If the data is
     * already in the tree, then nothing should be done (the duplicate
     * shouldn't get added, and size should not be incremented).
     *
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data the data to add
     * @throws java.lang.IllegalArgumentException if data is null
     */
    @SuppressWarnings("checkstyle:WhitespaceAround")
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("The data passed in is null.");
        }
        if (root == null) {
            size++;
            root = new BSTNode<>(data);
            return;
        }
        if (data.compareTo(root.getData()) < 0) {
            root.setLeft(populate(root.getLeft(), data));
        }
        if (data.compareTo(root.getData()) > 0) {
            root.setRight(populate(root.getRight(), data));
        }
        return;
    }

    /**
     * Removes and returns the data from the tree matching the given parameter.
     *
     * This must be done recursively.
     *
     * There are 3 cases to consider:
     * 1: The node containing the data is a leaf (no children). In this case,
     * simply remove it.
     * 2: The node containing the data has one child. In this case, simply
     * replace it with its child.
     * 3: The node containing the data has 2 children. Use the successor to
     * replace the data. You MUST use recursion to find and remove the
     * successor (you will likely need an additional helper method to
     * handle this case efficiently).
     *
     * Do not return the same data that was passed in. Return the data that
     * was stored in the tree.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data the data to remove
     * @return the data that was removed
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException   if the data is not in the tree
     */
    public T remove(T data) {
        if (data == null) {
            throw new IllegalArgumentException("The data passed in is null.");
        }
        BSTNode<T> nodeRemoved = new BSTNode<>(null);
        root = removeH(data, root, nodeRemoved);
        return nodeRemoved.getData();
    }

    /**
     * This method represents a helper method for remove(T data)
     *
     * @param data which is the data we are trying to remove from the tree structure
     * @param current which is the current node
     * @param nodeRemoved which is the node that will be removed
     * @throws java.util.NoSuchElementException will be thrown if data is not found in the tree
     * @return parent node of node that will get removed
     */
    private BSTNode<T> removeH(T data, BSTNode<T> current, BSTNode<T> nodeRemoved) {
        if (current == null) {
            throw new NoSuchElementException("The data was not found in the tree.");
        } else {
            // setting the compare equal to i
            int i = data.compareTo(current.getData());
            if (i > 0) {
                current.setRight(removeH(data, current.getRight(), nodeRemoved));
            } else if (i < 0) {
                current.setLeft(removeH(data, current.getLeft(), nodeRemoved));
            } else {
                nodeRemoved.setData(current.getData());
                size--;
                if (current.getLeft() == null) {
                    return current.getRight();
                } else if (current.getRight() == null) {
                    return current.getLeft();
                } else {
                    BSTNode<T> child = new BSTNode<>(null);
                    current.setLeft(successorH(current.getLeft(), child));
                    current.setData(child.getData());
                }
            }
        }
        return current;
    }

    /**
     * This method represents a helper method for remove(T data)
     * It finds the successor node
     *
     * @param current which is the current node
     * @param child which is the child node of the node we are trying to remove
     * @return the successor node of the node that is being removed
     */
    private BSTNode<T> successorH(BSTNode<T> current, BSTNode<T> child) {
        if (current.getLeft() == null) {
            child.setData(current.getData());
            return current.getRight();
        }
        current.setLeft(successorH(current.getLeft(), child));
        return current;
    }

    /**
     * Returns the data from the tree matching the given parameter.
     *
     * This must be done recursively.
     *
     * Do not return the same data that was passed in. Return the data that
     * was stored in the tree.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data the data to search for
     * @return the data in the tree equal to the parameter
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException   if the data is not in the tree
     */
    public T get(T data) {
        if (data == null) {
            throw new IllegalArgumentException("The data passed in is null.");
        }
        return getH(data, root);
    }

    /**
     * This method represents a helper method for get(T data)
     *
     * @param data which is the data we are trying to find
     * @param rootNode which is the root node we are trying to access
     * @return the data of the node that matches the node passed in the parameter
     */
    private T getH(T data, BSTNode<T> rootNode) {
        if (rootNode == null) {
            throw new NoSuchElementException("The data was not found in the tree.");
        }
        int value = data.compareTo(rootNode.getData());
        if (data.equals(rootNode.getData())) {
            return rootNode.getData();
        } else if (value > 0) {
            return getH(data, rootNode.getRight());
        } else if (value < 0) {
            return getH(data, rootNode.getLeft());
        }
        return null;
    }

    /**
     * Returns whether or not data matching the given parameter is contained
     * within the tree.
     *
     * This must be done recursively.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data the data to search for
     * @return true if the parameter is contained within the tree, false
     * otherwise
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public boolean contains(T data) {
        try {
            get(data);
        } catch (NoSuchElementException e) {
            return false;
        }
        return true;
    }


    /**
     * Generate a pre-order traversal of the tree.
     *
     * This must be done recursively.
     *
     * Must be O(n).
     *
     * @return the preorder traversal of the tree
     */
    public List<T> preorder() {
        List<T> data = new ArrayList<T>();
        preorderH(data, root);
        return data;
    }

    /**
     * This method represents a helper method for preorder()
     *
     * @param structure which is the structure that stores the data
     * @param rootNode which is the root node of the tree
     */
    private void preorderH(List<T> structure, BSTNode<T> rootNode) {
        if (rootNode == null) {
            return;
        } else {
            structure.add(rootNode.getData());
            preorderH(structure, rootNode.getLeft());
            preorderH(structure, rootNode.getRight());
        }
    }

    /**
     * Generate an in-order traversal of the tree.
     *
     * This must be done recursively.
     *
     * Must be O(n).
     *
     * @return the inorder traversal of the tree
     */
    public List<T> inorder() {
        List<T> data = new ArrayList<>();
        inorderH(data, root);
        return data;
    }

    /**
     * This method represents a helper method for inorder()
     *
     * @param structure which is the structure that stores the data
     * @param rootNode which is the root node of the tree
     */
    private void inorderH(List<T> structure, BSTNode<T> rootNode) {
        if (rootNode == null) {
            return;
        } else {
            inorderH(structure, rootNode.getLeft());
            structure.add(rootNode.getData());
            inorderH(structure, rootNode.getRight());
        }
    }

    /**
     * Generate a post-order traversal of the tree.
     *
     * This must be done recursively.
     *
     * Must be O(n).
     *
     * @return the postorder traversal of the tree
     */
    public List<T> postorder() {
        List<T> data = new ArrayList<>();
        postorderH(data, root);
        return data;
    }

    /**
     * This method represents a helper method for postorder()
     *
     * @param structure which is the structure that stores the data
     * @param rootNode which is the root node of the tree
     */
    private void postorderH(List<T> structure, BSTNode<T> rootNode) {
        if (rootNode == null) {
            return;
        } else {
            postorderH(structure, rootNode.getLeft());
            postorderH(structure, rootNode.getRight());
            structure.add(rootNode.getData());
        }
    }

    /**
     * Generate a level-order traversal of the tree.
     *
     * This does not need to be done recursively.
     *
     * Hint: You will need to use a queue of nodes. Think about what initial
     * node you should add to the queue and what loop / loop conditions you
     * should use.
     *
     * Must be O(n).
     *
     * @return the level order traversal of the tree
     */
    public List<T> levelorder() {
        Queue<BSTNode<T>> queue = new LinkedList<>();
        List<T> data = new ArrayList<>();

        queue.add(root);
        while (!queue.contains(null)) {
            BSTNode<T> current = queue.poll();
            data.add(current.getData());
            if (current.getRight() != null) {
                queue.add(current.getRight());
            }
            if (current.getLeft() != null) {
                queue.add(current.getLeft());
            }
        }
        return data;
    }

    /**
     * Returns the height of the root of the tree.
     *
     * This must be done recursively.
     *
     * A node's height is defined as max(left.height, right.height) + 1. A
     * leaf node has a height of 0 and a null child has a height of -1.
     *
     * Must be O(n).
     *
     * @return the height of the root of the tree, -1 if the tree is empty
     */
    public int height() {
        return heightH(root);
    }

    /**
     * This method represents a helper method for height()
     *
     * @param start which is the starting node from which we can get the height
     * @return the height of the node
     */
    private int heightH(BSTNode<T> start) {
        if (start == null) {
            return -1;
        } else {
            return Math.max(heightH(start.getLeft()), heightH(start.getRight())) + 1;
        }
    }
    /**
     * Clears the tree.
     *
     *
     * Clears all data and resets the size.
     *
     * Must be O(1).
     */
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * Determines if a binary tree is a valid BST.
     *
     * This must be done recursively. Do NOT modify the tree passed in.
     *
     * If the order property is violated, this method may not need to traverse
     * the entire tree to function properly, so you should only traverse the
     * branches of the tree necessary to find order violations and only do so once.
     * Failure to do so will result in an efficiency penalty.
     *
     * EXAMPLES: Given the BST below composed of Integers:
     *
     *                50
     *              /    \
     *            25      75
     *           /  \
     *          12   37
     *         /  \    \
     *        10  15    40
     *           /
     *          13
     *
     * isBST(50) should return true, since for every node, the node's left
     * subtree is less than the node's data, and the node's right subtree is
     * greater than the node's data.
     *
     *             20
     *           /    \
     *         21      38
     *        /          \
     *       6          50
     *        \
     *         12
     *
     * isBST(20) should return false, since 21 is in 20's left subtree.
     *
     *
     * Should have a worst-case running time of O(n).
     *
     * @param node the root of the binary tree
     * @return true if the tree with node as the root is a valid BST,
     *         false otherwise
     */
    public boolean isBST(BSTNode<T> node) {
        return isBSThelp(node);
    }

    /**
     * This method represents a helper method for isBST(BSTNode<> node)
     *
     * @param rootNode which is the root node of the tree structure we're checking
     * @return a boolean demonstrating whether or not the tree is a binary search tree
     */
    private boolean isBSThelp(BSTNode<T> rootNode) {
        if (rootNode != null) {
            if (rootNode.getRight() != null) {
                if (rootNode.getData().compareTo(rootNode.getRight().getData()) > 0) {
                    return false;
                } else {
                    return isBSThelp(rootNode.getRight());
                }
            }
            if (rootNode.getLeft() != null) {
                if (rootNode.getData().compareTo(rootNode.getLeft().getData()) < 0) {
                    return false;
                } else {
                    return isBSThelp(rootNode.getLeft());
                }
            }
        }
        return true;
    }


    /**
     * Returns the root of the tree.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the root of the tree
     */
    public BSTNode<T> getRoot() {
        // DO NOT MODIFY THIS METHOD!
        return root;
    }

    /**
     * Returns the size of the tree.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the size of the tree
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }
}