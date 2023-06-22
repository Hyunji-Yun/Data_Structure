public class DoublyLinkedList{
	
	private static class Node<E>{
		
		private E element;
		private Node<E> next;
		private Node<E> prev;
		
		public Node(E e, Node<E> p, Node<E> n ) {
			
			element=e;
			next=n;
			prev=p;
		}
		
		public E getElement(){
			return element;
		}
		
		public Node<E> getNext(){
			return next;
		}
		
		public Node<E> getPrev(){
			return prev;
		}
		
		public void setNext(Node<E> n){
			next=n;
		}
		
		public void setPrev(Node<E> p) {
			prev=p;
		}
		
		private Node<E> header;
		private Node<E> trailer;
		private int size=0;
		
		public void DoublyLinkedList() {
			
			header = new Node(null, null, null);
			trailer = new Node(null, header, null);
		
			header.setNext(trailer);
		}
		
		public int size() {
			return size;
		}
		
		public boolean isEmpty() {
			if(size==0) {
				return true;
			}
			return false;
		}
		
		public E first() {
			if(isEmpty()) {
				return null;
			}
			return header.getNext().getElement();
			
		}
		
		public E last() {
			if(isEmpty()) {
				return null;
			}
			return trailer.getPrev().getElement();
		}
		
		public void addBetween(E e, Node<E> p, Node<E> n) {
			
			Node<E> newNode = new Node<>(e,p,n);
			
			p.setNext(newNode);
			n.setPrev(newNode);
			size++;
			
		}
		
		public E remove(Node<E> node) {
			
			Node<E> first = node.getPrev();
			Node<E> second = node.getNext();
			
			first.setNext(second);
			second.setPrev(first);
			size--;
			
			return node.getElement();
		}
		
		public void addFirst(E e) {
			
			addBetween(e, header, header.getNext());
			size++;
		}
		
		public void addLast(E e) {
			
			addBetween(e, trailer.getPrev(), trailer);
			size++;
		}
		
		public E removeFirst() {
			
			if(isEmpty()) {
				return null;
			}
			return remove(header.getNext());
			
		}
		
		public E removeLast() {
			
			if(isEmpty()) {
				return null;
			}
			return remove(trailer.getPrev());
		}
		
		
		
		
		
	}
	
}