public class SinglyLinkedList{
	
	public static class Node<E>{
		
		private E element;
		private Node<E> next;
		
		public Node(E e, Node<E> n){
			element=e;
			next=n;
		}
		
		public E getElement(){
			return element;
		}
		
		public Node<E> getNext(){
			return next;
		}
		
		public Node<E> setNext(Node<E> n){
			return next=n;		
		}
		
		private Node<E> head =null;
		private Node<E> tail =null;
		private int size =0;
		
		public int size() {
			return size;
		}
		
		public boolean isEmpty() {
			
			if(size==0) {
				return true;
			}
			return false;
		}
		
		public E First() {
			
			if(size==0) {
				return null;
			}
			return head.getElement();
		}
		
		public E Last() {
			
			if(size==0) {
				return null;
			}
			return tail.getElement();
		}
		
		public void addHead(E e) {
			
			head = new Node<E>(e, head);
			if(size==0) {
				tail=head;
			}
			size++;
		}
		
		public void addTail(E e) {
			
			Node<E> newest = new Node<E>(e, null);
			if(size==0) {
				head=newest;
			}
			else {
			tail.setNext(newest);
			tail=newest;
			}
			size++;
		}
		
		public void addBetween(Node<E> first, Node<E> second, E e) {
			
			Node<E> newNode = new Node<E>(e, second);
			first.setNext(newNode);
			size++;
		}
		
		public E removeFirst() {
			
			if(isEmpty()) {
				return null;
			}
			E answer = head.getElement();
			head = head.getNext();
			size--;
			if(size==0) {
				tail=null;
			}
			return answer;
		}
		
	}
	
}