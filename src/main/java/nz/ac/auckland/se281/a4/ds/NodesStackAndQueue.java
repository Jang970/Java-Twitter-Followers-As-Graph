package nz.ac.auckland.se281.a4.ds;

import java.util.EmptyStackException;
//*******************************
//YOU SHOUD MODIFY THE SPECIFIED 
//METHODS  OF THIS CLASS
//HELPER METHODS CAN BE ADDED
//REQUIRED LIBRARIES ARE ALREADY 
//IMPORTED -- DON'T ADD MORE
//*******************************

public class NodesStackAndQueue<T> {

	private Node<T> head; // You should use this variable in your methods

	public NodesStackAndQueue() {
		head = null;
	}

	/**
	 * Checks if the stack / queue is empty
	 * 
	 * @return true if the stack / queue is empty
	 */
	public boolean isEmpty() {

		return this.head == null;
	}

	/**
	 * Push operation refers to inserting an element in the Top of the stack. TODO:
	 * Complete this method
	 * 
	 * @param element the element to be "pushed"
	 */
	public void push(T element) {
		Node<T> n = new Node<>(element);
		n.setNext(this.head);
		this.head = n;

	}

	/**
	 * pop an element from the top of the stack (removes and returns the tope
	 * element) TODO: Complete this method (Note: You may have to change the return
	 * type)
	 * 
	 * @return object of the top element
	 * @throws EmptyStackException if the stack is empty
	 */
	public T pop() throws EmptyStackException {

		if (isEmpty()) {

			throw new EmptyStackException();

		} else {

			// store value of current head
			T topOfStack;
			topOfStack = this.head.getValue();
			// update head
			this.head = this.head.getNext();
			// return value stored earlier
			return topOfStack;

		}
	}

	/**
	 * get the element from the top of the stack without removing it TODO: Complete
	 * this method (Note: You may have to change the return type)
	 *
	 * @return the value of the top element
	 * @throws EmptyStackException if the stack is empty
	 */
	public T peek() throws EmptyStackException {

		if (isEmpty()) {

			throw new EmptyStackException();

		} else {

			return this.head.getValue();
		}
	}

	/**
	 * append an element at the end of the queue TODO: Complete this method
	 *
	 * @param element the element to be appended
	 */
	public void append(T element) {
		Node<T> n = new Node<>(element);

		Node<T> currentNode;

		// check if queue is empty, then element will be the head
		if (isEmpty()) {

			this.head = n;

		} else {

			currentNode = this.head;

			// go to end of queue, put new element
			while (currentNode.getNext() != null) {

				currentNode = currentNode.getNext();

			}

			// put new element at end of queue
			currentNode.setNext(n);

		}
	}
}
