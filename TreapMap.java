
import java.util.Map;
import java.util.Random;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @param <K> type of key
 * @param <V> type of value
 * 
 * This class is a map based on treap
 */

public class TreapMap<K extends Comparable<K>, V> implements Map<K, V> {

	private Node<K, V> root; // Root node of treap
	private int size; 		 // The number of node in the treap
	

	private static class Node<K, V> {
		K key;
		V val;
		int pri;
		Node<K, V> left, right;

		/**
		 * @param k key
		 * @param v value
		 * 
		 * This constructor is for generating a new node with the key and value
		 */
		public Node(K k, V v) {
			key = k;
			val = v;
			this.pri = new Random().nextInt(); // Creating random priority
		}
	}

	/**
	 * Constructor for generating an empty treap
	 */
	public TreapMap() {
		root = null;
		size = 0;
	}
	
	/**
	 * @param key the new key
	 * @param value the new value
	 * @return result value after putting a new value
	 * @throws NullPoiterExceptin if the key is null
	 * 
	 * This method adds a new key and value in the treap
	 */
	public V put(K key, V value) {
		if (key == null) {
			throw new NullPointerException("Null key is not allowed");
		}

		else {
			root = putNode(root, key, value); // Put the key and value by using putNode method
			size++;
			return value; 					  // Return the value that was put in the treap
		}
	}
	
	/**
	 * @param node the current node
	 * @param key the new key to be put
	 * @param value the new value to be put
	 * @return the updated node
	 * 
	 * This method puts a new node in a specific node
	 */
	private Node<K, V> putNode(Node<K, V> node, K key, V value) {
        if (node == null) {
            return new Node<>(key, value); 					// Generating a new node of the key and value
        }

        int compare = key.compareTo(node.key); 				// Compare the new key with the current node's key
        if (compare < 0) {									// If new key is smaller than the current node's key
            node.left = putNode(node.left, key, value); 	// Put the key and value in the left subtree of the node
            if (node.left.pri > node.pri) {
                node = rightRotation(node);				    // If the left child's priority is bigger then rotate right
            }
        } else if (compare > 0) {							// If the new key is bigger than the current node's key
            node.right = putNode(node.right, key, value); 	// Put the key and value in the right subtree of the node
            if (node.right.pri > node.pri) {
                node = leftRotation(node); 					// If the right child's priority is bigger then rotate right
            }
        } else {
            node.val = value; 								// Update the original key's value
        }

        return node; 										// Return the updated node
    }
	
    /**
     * @param node to be rotated
     * @return new upper node after rotation
     * 
     * This method makes left rotation from the given node
     */
    private Node<K, V> leftRotation(Node<K, V> node) {
        Node<K, V> rightChild = node.right;		
        node.right = rightChild.left;			// Change the current node's right child into right child's left subtree
        rightChild.left = node;					// Make the right child's left to be the current node
        return rightChild;						// Return the right child that rotated to upper node
    }
	
    /**
     * @param node to be rotated
     * @return new upper node after rotation
     * 
     * This method makes right rotation from the given node
     */
    private Node<K, V> rightRotation(Node<K, V> node) {
        Node<K, V> leftChild = node.left;		
        node.left = leftChild.right;           	// Change the current node's left child into left child's right subtree
        leftChild.right = node;					// Make the left child's right to be the current node
        return leftChild;						// Return the left child that rotated to upper node
    }
    
    /**
     * @param key to be removed
     * @return null
     * 
     * This method removes the value of the given key in the treap
     */
    public V remove(Object key) {
        if (key == null) {
            return null;
        }
        root = removeNode(root, (K) key); 		// Remove the key and value by using removeNode method
        size--;
        return null; 							
    }

    /**
     * @param node current node
     * @param key to be removed
     * @return updated node if the key is not found, it returns null
     * 
     * This method removes a specific key and value in the treap
     */
    private Node<K, V> removeNode(Node<K, V> node, K key) {
        if (node == null) {
            return null; 									// If key is not found then it returns null
        }

        int cmp = key.compareTo(node.key); 					// Compare the key with the current node's key
        if (cmp < 0) {
            node.left = removeNode(node.left, key); 		// Remove from the left subtree
        } 
        else if (cmp > 0) {
            node.right = removeNode(node.right, key); 		// Remove from the right subtree
        } 
        else {												// When key is found
            if (node.left == null) {						// If the node has only right child, returns right child
                return node.right; 							
            }
            else if (node.right == null) {					// If the node has only left child, returns left child
                return node.left;					 		
            }
            if(node.left.pri > node.right.pri) {			// If the node has both right and left child, rotates by priority
         
                node = rightRotation(node); 				// If left child's priority is bigger, rotates right
                node.right = removeNode(node.right, key); 	// Remove from the right subtree by using removeNode
            } else {
            	
                node = leftRotation(node); 					// If right child's priority is bigger, rotates left
                node.left = removeNode(node.left, key); 	// Remove from the left subtree by using remveNode
            }
        }

        return node; 										// Return the updated node
    }
    
    /**
     * @param key to be found
     * @return the value of the key or if the key is not found, it returns null
     * 
     * This method finds the given key and returns the value of the key
     */
    public V get(Object key) {
        if (key == null) {
            return null; 							// If there is no keys, returns null
        }
        
        Node<K, V> node = getNode(root, (K) key);   // Finding the node that has given key by using getNode method
        return node != null ? node.val : null; 		// Return the value if node is found, otherwise return null
    }

    /**
     * @param node the current node
     * @param key to be found
     * @return found key or if the key is not found it returns null
     * 
     * This method searches the node of the given key and returns the node of the key
     */
    private Node<K, V> getNode(Node<K, V> node, K key) {
        if (node == null) {
            return null; 							// If there is no keys, returns null
        }

        int cmp = key.compareTo(node.key); 			// Compare the key with the current node's key
        if (cmp < 0) {
            return getNode(node.left, key); 		// Search the key in the left subtree
        } else if (cmp > 0) {	
            return getNode(node.right, key); 		// Search the key in the right subtree
        } else {
            return node;							// If key is found, return the node
        }
    }
  
    /**
     * @return size of the nodes in the treap
     * 
     * This method returns the number of nodes in a treap
     */
  	public int size() {
      return size;
  }

  	/**
  	 * This method clears all nodes in the treap
  	 */
  	public void clear() {
  	    root = null;
  	    size = 0;
  	}
  	
  	/**
  	 * @return the set of the keys in the treap
  	 * 
  	 * This method returns a set of keys in the treap
  	 */
  	public Set<K> keySet() {
  	    Set<K> keySet = new HashSet<>();
  	    keySetNode(root, keySet);				 // Generate the key set by using keySetNode method
  	    return keySet;
  	}

  	/**
  	 * @param node the starting node
  	 * @param keySet that keys are added in 
  	 * 
  	 * This method adds keys into the key set from the starting node
  	 */
  	private void keySetNode(Node<K, V> node, Set<K> keySet) {
  	    if (node != null) {
  	        keySet.add(node.key); 				// Add the key to the key set
  	        keySetNode(node.left, keySet); 		// Add keys from the left subtree
  	        keySetNode(node.right, keySet); 	// Add keys from the right subtree
  	    }
  	}
  	
  	/**
  	 * @return the treap in string type
  	 * 
  	 * This method makes treap into string type by using in order traversal
  	 */
  	public String toString() {
  	    StringBuilder stringBuilder = new StringBuilder();
  	    inOrderTraversal(root, stringBuilder); 	// To traversal in order use inOderTraversal method 
  	    return stringBuilder.toString();
  	}

  	/**
  	 * @param node the current node
  	 * @param stringBuilder makes the string
  	 * 
  	 * This method traversals the nodes by in order and makes a string
  	 */
  	private void inOrderTraversal(Node<K, V> node, StringBuilder stringBuilder) {
  	    if (node != null) {
  	        inOrderTraversal(node.left, stringBuilder); 		// Traverse the left subtree
  	      stringBuilder.append("(").append(node.key).append(", ").append(node.val).append(") "); // Append the current node
  	        inOrderTraversal(node.right, stringBuilder); 		// Traverse the right subtree
  	    }
  	}
  	
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean containsKey(Object key) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean containsValue(Object value) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void putAll(Map<? extends K, ? extends V> m) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Collection<V> values() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Set<Entry<K, V>> entrySet() {
		// TODO Auto-generated method stub
		return null;
	}

}
    