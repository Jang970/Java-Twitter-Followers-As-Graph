package nz.ac.auckland.se281.a4.ds;

//*******************************
//YOU SHOUD MODIFY THE SPECIFIED 
//METHODS  OF THIS CLASS
//HELPER METHODS CAN BE ADDED
//REQUIRED LIBRARIES ARE ALREADY 
//IMPORTED -- DON'T ADD MORE
//THIS CLASS ALSO HAS TO BE MADE 
//GENERIC
//*******************************

/**
 * The Linked List Class Has only one head pointer to the start node (head)
 * Nodes are indexed starting from 0. The list goes from 0 to size-1.
 *
 * @author Partha Roop
 */
public class LinkedList<T> {
	// the head of the linked list
	private Node<T> head;

	/**
	 * Constructor for LinkedList
	 */
	public LinkedList() {
		head = null;
	}

	/**
	 * This method returns a reference to a node whose position is at pos TODO:
	 * Complete this method
	 * 
	 * @param pos: an integer specifying the position of the node to be located
	 * @return Node: the reference to the Node at position pos
	 * @throws InvalidPositionException if position is less than 0 or greater than
	 *                                  size-1
	 * 
	 */
	private Node<T> locateNode(int pos) throws InvalidPositionException {

		// check pos validity
		isPositionValid(pos);

		Node<T> currentNode = head;

		int i = 0;
		while (i < pos) {

			currentNode = currentNode.getNext();
			i++;

		}

		// will now be at node we want to locate, thus return it
		return currentNode;

	}

	/**
	 * This method adds a node with specified data as the start node of the list
	 * TODO: Complete this method
	 *
	 * @param element a parameter, which is the value of the node to be prepended
	 */
	public void prepend(T element) {

		Node<T> n = new Node<>(element);
		n.setNext(this.head);
		this.head = n;

	}

	/**
	 * This method adds a node with specified data as the end node of the list TODO:
	 * Complete this method
	 *
	 * @param element a parameter, which is the value of the node to be appended
	 */

	// Note this method has been refactored using the helper methods
	// I will do this as a small ACP exercise in class
	public void append(T element) {

		Node<T> t = new Node<>(element);

		// check if there are no elements
		if (head == null) {

			// set element to be head
			head = t;

		} else {

			// otherwise, list contains elements, so must loop through to get to the end of
			// list

			// ref to start of list
			Node<T> currentNode = head;

			while (currentNode.getNext() != null) {

				currentNode = currentNode.getNext();

			}

			// set final node's next to be new element
			currentNode.setNext(t);

		}

	}

	/**
	 * This method gets the value of a node at a given position TODO: Complete this
	 * method
	 *
	 * @param pos an integer, which is the position
	 * @return the value at the position pos
	 * @throws InvalidPositionException if position is less than 0 or greater than
	 *                                  size-1
	 */
	public T get(int pos) throws InvalidPositionException {

		// use locate node to get node wanted
		Node<T> wantedNode = locateNode(pos);

		// return value of current node
		return wantedNode.getValue();
	}

	/**
	 * This method adds an node at a given position in the List TODO: Complete this
	 * method
	 * 
	 * @param pos:     an integer, which is the position
	 * @param element: the element to insert
	 * @throws InvalidPositionException if position is less than 0 or greater than
	 *                                  size
	 */
	public void insert(int pos, T element) throws InvalidPositionException {

		// check pos
		if (pos < 0 || pos > this.size()) {

			throw new InvalidPositionException();
		}

		// if wanting to insert at the start, same as prepend
		if (pos == 0) {

			prepend(element);

		} else {

			int i = 0;
			Node<T> previousNode = null;
			Node<T> currentNode = head;
			Node<T> newNode = new Node<>(element);

			// loop to position we want to insert element into
			while (i < pos) {

				previousNode = currentNode;
				currentNode = currentNode.getNext();
				i++;

			}

			// link previous node to new node inserted
			previousNode.setNext(newNode);
			// link new inserted node to current node
			newNode.setNext(currentNode);

		}
	}

	/**
	 * This method removes an node at a given position TODO: Complete this method
	 *
	 * @param pos: an integer, which is the position
	 */
	public void remove(int pos) throws InvalidPositionException {

		isPositionValid(pos);

		// otherwise, pos is a valid value
		Node<T> currentNode = head;
		Node<T> prevNode = null;
		int i = 0;

		// if we want to remove current head
		if (pos == 0) {

			currentNode = this.head.getNext();
			this.head = currentNode;
			return;

		} else {

			// otherwise pos is from 1 to size - 1

			// loop to pos
			while (i < pos) {

				i++;
				prevNode = currentNode;
				currentNode = currentNode.getNext();

			}

			// set final node to be null
			prevNode.setNext(null);

		}

	}

	/**
	 * This helper method takes an int input pos and will check if said input is
	 * within the size of the list, it will throw an exception if it is not.
	 * 
	 * @param pos: integer, which is the position in linked list
	 */
	private void isPositionValid(int pos) {

		// get size of list
		int listSize = size();

		// check if pos is an invalid value
		if (pos < 0 || pos > listSize - 1) {

			throw new InvalidPositionException();
		}

	}

	/**
	 * This method returns the size of the Linked list TODO: Complete this method
	 *
	 * @return the size of the list
	 */
	public int size() {

		int i = 0;

		Node<T> currentNode = this.head;

		while (currentNode != null) {

			currentNode = currentNode.getNext();
			i++;

		}

		return i;

	}

	/**
	 * This method is used for printing the data in the list from head till the last
	 * node
	 *
	 */
	public void print() {
		Node<T> node = head;
		while (node != null) {
			System.out.println(node);
			node = node.getNext();
		}
	}
}