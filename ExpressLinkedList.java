import java.util.List;

/**
 *
 * @param <E> the type of elements stored in the list
 * This is the class for the quadruply linked list
 */
public class ExpressLinkedList<E> {

	//Nested class
	private static class Node<E> {
		private E element;
		private Node<E> prev;
		private Node<E> next;
		private Node<E> prev8;
		private Node<E> next8;

		/**
		 * @param e the element in a node
		 * @param p the previous node
		 * @param n the next node
		 * @param p8 the node that is 8 nodes behind specified node
		 * @param n8 the node that is 8 nodes ahead specified node
		 * 
		 * This is a constructor 
		 * 
		 */
		public Node(E e, Node<E> p, Node<E> n, Node<E> p8, Node<E> n8) {
			element = e;
			prev = p;
			next = n;
			prev8 = p8;
			next8 = n8;
		}



		/**
		 * @return element of the specified node
		 */
		public E getElement() {
			return element;
		}

		/**
		 * @return node after the specified node
		 */
		public Node<E> getNext(){
			return next;
		}

		/**
		 * @param n the node which you would like to set as the next node
		 * @return sets the next node to the node after a specified node
		 */
		public Node<E> setNext(Node<E> n){
			return next = n;
		}

		/**
		 * @return the node that is 8 nodes after specified node
		 */
		public Node<E> getNext8(){
			  if (next8 != null) {
			        return next8;
			    } else {
			        return null;
			    }
		}


		/**
		 * @return the node that is before the specified node
		 */
		public Node<E> getPrev(){
			return prev;
		}

		/**
		 * @param p the node which you would like to set as the previous node
		 * @return sets the previous node to the node before a specified node
		 */
		public Node<E> setPrev(Node<E> p){
			return prev = p;
		}

		/**
		 * @return The node that is 8 nodes behind the specified node
		 */
		public Node<E> getPrev8(){
			return prev8;
		}

		/**
		 * @param n8 the node that is 8 nodes after 
		 * @return sets the node that is 8 nodes after to n8(the input/specified node)
		 * 
		 */
		public Node<E> setNext8(Node<E> n8) {
			next8 = n8;
			return next8;
		}

		/**
		 * @param p8 the node that is 8 nodes before 
		 * @return sets the node that is 8 nodes before to p8(the input/specified node)
		 */
		public Node<E> setPrev8(Node<E> p8) {
			prev8 = p8;
			return prev8;
		}




	}

	private Node<E> header;
	private Node<E> trailer;
	private int size;

	/**
	 * Creates an empty list with a header and trailer that has no elements
	 * the initial size of the list is 0 since it is empty
	 */
	public ExpressLinkedList() {
		header = new Node<>(null, null, null, null, null);
		trailer = new Node<>(null, null, null, null, null);
		header.setNext(trailer);
		trailer.setPrev(header);
		size = 0;
	}

	/**
	 * @param e element you want to add to the new node
	 * @return a new node with the specified element
	 * this method adds a new node at the end of the list with the specified element,e
	 * 
	 * It also updates the next,prev nodes and the prev8, next8 (if a node after the 8th node is added)
	 * 
	 * The size of the list is then incremented by 1
	 */
	public boolean add(E e) {
	    Node<E> newNode = new Node<>(e, null, null, null, null);
	    if (size == 0) {
	        newNode.setNext(trailer);
	        newNode.setPrev(header);
	        header.setNext(newNode);
	        trailer.setPrev(newNode);
	    } else {
	        Node<E> lastNode = trailer.getPrev();
	        newNode.setPrev(lastNode);
	        newNode.setNext(trailer);
	        lastNode.setNext(newNode);
	        trailer.setPrev(newNode);

	        if (size > 8) {
	            Node<E> prev8Node = lastNode.getPrev8();
	            Node<E> next8Node = lastNode.getNext8();

	            if (prev8Node != null) {
	                prev8Node.setNext8(newNode);
	                newNode.setPrev8(prev8Node);
	            }
	            if (next8Node != null) {
	                newNode.setNext8(next8Node);
	                next8Node.setPrev8(newNode);
	            }
	        } else if (size == 8) {
	            Node<E> prev8Node = header.getNext();
	            Node<E> next8Node = lastNode.getNext();
	            prev8Node.setNext8(newNode);
	            newNode.setPrev8(prev8Node);
	            newNode.setNext8(next8Node);
	            next8Node.setPrev8(newNode);
	        }
	    }

	    size++;
	    return true;
	}
	/**
	 *toString method which displays the list as a string
	 */
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("[");
		Node<E> current = header.getNext();
		while (current != trailer) {
			stringBuilder.append(current.getElement());
			if (current.getNext() != trailer) {
				stringBuilder.append(", ");
			}
			current = current.getNext();
		}
		stringBuilder.append("]");
		return stringBuilder.toString();
	}

	/**
	 * @param index the position in the list you want to add a node
	 * @param element the element you want to add to the new node
	 * This method adds a new node at the specified index with the specified element
	 * 
	 * It also updates the next and prev nodes and the next8 and prev8 nodes(if a node after the
	 * 8th node is added)
	 * 
	 * Size is incremented by 1
	 */
	public void add(int index, E element) {
		if (index < 0 || index > size) {
			return;
		}
		if (index == size){
			add(element);
		}

		else {
			Node<E> current = header.getNext();
			for (int i = 0; i < index; i++) {
				current = current.getNext();
			}
			Node<E> newNode = new Node<>(element, null, null, null, null);
			current.prev.setNext(newNode);
			current.setPrev(newNode);
			size++;

			if (size > 8) {
	            Node<E> prev8Node = current.getPrev8();
	            Node<E> next8Node = current.getNext8();
	            if (prev8Node != null) {
	                prev8Node.setNext8(newNode);
	                newNode.setPrev8(prev8Node);
	            }
	            if (next8Node != null) {
	                newNode.setNext8(next8Node);
	                next8Node.setPrev8(newNode);
	            }
	        }
		}

	}

	/**
	 * @param index the position of the node in the list of which element you want to get
	 * @return the element of the node on the position index
	 * this method goes through all the nodes and updates the current node till it reaches index
	 * 
	 * it then returns the element of the node at the specified index (current)
	 */
	public E get(int index) {
		if (index < 0 || index >= size) {
			return null;
		}
		else {
			Node<E> current = header.getNext();
			for (int i = 0; i < index; i++) {
				current = current.getNext();
			}

			return current.getElement();
		}
	}

	/**
	 * @param index position of the node on the list you want to remove 
	 * @return element of the node removed
	 * 
	 * this method goes through all the nodes and updates the current node till it reaches index
	 * 
	 * it then removes the node at the specified index (current)
	 * and it returns the element of the node removed
	 */
	public E remove(int index) {
		if (index < 0 || index > size) {
			return null;
		}
		else {
			Node<E> current = header.getNext();
			for (int i = 0; i < index; i++) {
				current = current.getNext();
			}
			current.getPrev().setNext(current.getNext());
			current.getNext().setPrev(current.getPrev());
			size--;
			return current.getElement();
		}
	}

	/**
	 * @return number of nodes in the list
	 */
	public int size() {
		return size;
	}

	/**
	 * restore the list to an empty list
	 * 
	 * updates header and trailer
	 */
	public void clear() {
		header.setNext(trailer);
		trailer.setPrev(header);
		size = 0;
	}

	/**
	 * @param index the position of the node on the list that you want to get
	 * @return the node
	 * This is an extra method added to return the specified node(on index)
	 */
	public Node<E> getNode(int index) {
		if (index < 0 || index >= size) {
			return null;
		}
		else {
			Node<E> current = header.getNext();
			for (int i = 0; i < index; i++) {
				current = current.getNext();
			}
			return current;
		}
	}
}

