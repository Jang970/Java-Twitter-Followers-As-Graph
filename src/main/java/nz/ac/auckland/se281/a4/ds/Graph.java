package nz.ac.auckland.se281.a4.ds;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nz.ac.auckland.se281.a4.TwitterHandle;
//*******************************
//YOU SHOUD MODIFY THE SPECIFIED 
//METHODS  OF THIS CLASS
//HELPER METHODS CAN BE ADDED
//REQUIRED LIBRARIES ARE ALREADY 
//IMPORTED -- DON'T ADD MORE
//*******************************

public class Graph {

	/**
	 * Each node maps to a list of all the outgoing edges from that node
	 */
	protected Map<Node<String>, LinkedList<Edge<Node<String>>>> adjacencyMap;
	/**
	 * root of the graph, to know where to start the DFS or BFS
	 */
	protected Node<String> root;

	/**
	 * !!!!!! You cannot change this method !!!!!!!
	 */

	/**
	 * Creates a Graph
	 * 
	 * @param edges a list of edges to be added to the graph
	 */
	public Graph(List<String> edges) {
		adjacencyMap = new LinkedHashMap<>();
		int i = 0;
		if (edges.isEmpty()) {
			throw new IllegalArgumentException("edges are empty");
		}

		for (String edge : edges) {
			String[] split = edge.split(",");
			Node<String> source = new Node<String>(split[0]);
			Node<String> target = new Node<String>(split[1]);
			Edge<Node<String>> edgeObject = new Edge<Node<String>>(source, target);

			if (!adjacencyMap.containsKey(source)) {
				adjacencyMap.put(source, new LinkedList<Edge<Node<String>>>());
			}
			adjacencyMap.get(source).append(edgeObject);

			if (i == 0) {
				root = source;
			}
			i++;
		}
	}

	/**
	 * This method returns a boolean based on whether the input sets are reflexive.
	 * 
	 * TODO: Complete this method (Note a set is not passed in as a parameter but a
	 * list)
	 * 
	 * @param set      A list of TwitterHandles
	 * @param relation A relation between the TwitterHandles
	 * @return true if the set and relation are reflexive
	 */
	public boolean isReflexive(List<String> set, List<String> relation) {

		int counter = 0;
		int i;
		int j;
		String handle;
		String relations;
		String reflexive;

		// loop through all elements in set
		for (i = 0; i < set.size(); i++) {

			// get element
			handle = set.get(i);

			// string that will be used to check if a reflexive relation exists
			reflexive = handle + "," + handle;

			// loop through all elements in relations
			for (j = 0; j < relation.size(); j++) {

				// get relation
				relations = relation.get(j);

				// check if it is a reflexive relation
				if (relations.equalsIgnoreCase(reflexive)) {

					// increment count of reflexive relations
					counter = counter + 1;

				}

			}

		}

		// a set is only reflexive if all vertices are connected to themselves thus only
		// true if counter is equal to the number of elements in set
		return counter == set.size();
	}

	/**
	 * This method returns a boolean based on whether the input set is symmetric.
	 * 
	 * If the method returns false, then the following must be printed to the
	 * console: "For the graph to be symmetric tuple: " + requiredElement + " MUST
	 * be present"
	 * 
	 * TODO: Complete this method (Note a set is not passed in as a parameter but a
	 * list)
	 * 
	 * @param relation A relation between the TwitterHandles
	 * @return true if the relation is symmetric
	 */
	public boolean isSymmetric(List<String> relation) {

		int i;
		// strings to store relation elements
		String element1;
		String element2;

		// string to store value of relation List at index i
		String relations;
		// string to store value that needs to be present in order for relation to be
		// symmetric
		String symmetric;
		// set to store elements that need to be in relation for it to be symmetric
		ArrayList<String> requiredElements = new ArrayList<>();

		// loop through relations
		for (i = 0; i < relation.size(); i++) {

			relations = relation.get(i);

			// get each element and split into array
			String[] split = relation.get(i).split(",");

			// set element 1 to be first element in split
			element1 = split[0];
			// set element 2 to be second element in split
			element2 = split[1];

			// update value of symmetric
			symmetric = element2 + "," + element1;

			// check if relation does not meet requirement to be called symmetric
			if (relation.contains(relations) && relation.contains(symmetric) == false) {

				// add missing elements to set
				requiredElements.add(symmetric);

			}

		}

		// check size of requiredElements
		if (requiredElements.size() == 0) {

			// no missing elements, so relation must be symmetric
			return true;

		} else {

			// there are missing elements, print and return false
			System.out.println("For the graph to be symmetric tuple/s: " + requiredElements + " must be present");
			return false;

		}
	}

	/**
	 * This method returns a boolean based on whether the input set is transitive.
	 * 
	 * If the method returns false, then the following must be printed to the
	 * console: "For the graph to be transitive tuple: " + requiredElement + " MUST
	 * be present"
	 * 
	 * TODO: Complete this method (Note a set is not passed in as a parameter but a
	 * list)
	 * 
	 * @param relation A relation between the TwitterHandles
	 * @return true if the relation is transitive
	 */
	public boolean isTransitive(List<String> relation) {

		int i;
		int k;

		String element1;
		String element2;
		String transitive;

		String element4;

		ArrayList<String> requiredElements = new ArrayList<>();

		// loop through relation
		for (i = 0; i < relation.size(); i++) {

			// split and store vertices of relation into separate variables
			String[] split = relation.get(i).split(",");

			element1 = split[0];
			element2 = split[1];

			// loop through relation again
			for (k = 0; k < relation.size(); k++) {

				// split again into respective vertices
				String[] split2 = relation.get(k).split(",");

				// store second element in relation
				element4 = split2[1];

				// check if the target of first relation is the source of the second relation
				if (relation.get(k).startsWith(element2)) {

					// create pair that must exist in relation for it to be transitive
					transitive = element1 + "," + element4;

					if (relation.contains(relation.get(i)) && relation.contains(relation.get(k))
							&& relation.contains(transitive) == false) {

						// if relation does not contain the required pair, add said pair to arraylist
						requiredElements.add(transitive);

					}

				}

			}

		}

		// if no required elements missing, transitivity is true
		if (requiredElements.size() == 0) {

			return true;

		} else {

			// missing some elements, print and return false
			System.out.println("For the graph to be transitive tuple/s: " + requiredElements + " must be present");
			return false;

		}
	}

	/**
	 * This method returns a boolean based on whether the input sets are equivalence
	 * relation TODO: Complete this method (Note a set is not passed in as a
	 * parameter but a list)
	 * 
	 * @param set      A list of TwitterHandles
	 * @param relation A relation between the TwitterHandles
	 * @return true if the set and relation are anti-symmetric
	 */
	public boolean isEquivalence(List<String> set, List<String> relation) {

		// this method should be checking if the three properties of reflexivity,
		// symmetry and transitivity are true?

		// compute properties using respective methods
		boolean reflexive = isReflexive(set, relation);
		boolean symmetric = isSymmetric(relation);
		boolean transitive = isTransitive(relation);

		// if all properties satisfied, it is indeed an equivalence relation
		if (reflexive && symmetric && transitive) {

			return true;

		} else {

			return false;

		}
	}

	/**
	 * This method returns a List of the equivalence class
	 * 
	 * If the method can not find the equivalence class, then The following must be
	 * printed to the console: "Can't compute equivalence class as this is not an
	 * equivalence relation"
	 * 
	 * TODO: Complete this method (Note a set is not passed in as a parameter but a
	 * list)
	 * 
	 * @param node     A "TwitterHandle" in the graph
	 * @param set      A list of TwitterHandles
	 * @param relation A relation between the TwitterHandles
	 * @return List that is the equivalence class
	 */
	public List<String> computeEquivalence(String node, List<String> set, List<String> relation) {

		ArrayList<String> equivClass = new ArrayList<>();

		// check if equivalence is true

		if (isEquivalence(set, relation)) {

			// loop through relation
			for (int i = 0; i < relation.size(); i++) {

				// find relations that start with node
				if (relation.get(i).startsWith(node)) {

					// split into 2 elements
					String[] split = relation.get(i).split(",");

					// add second element of split into list, this will be the equivalence class of
					// the desired node
					equivClass.add(split[1]);

				}
			}

			// return list
			return equivClass;

		} else {

			// print required statement
			System.out.println("Can't compute equivalence class as this is not an equivalence relation");

			return null;

		}
	}

	/**
	 * This method returns a List nodes using the BFS (Breadth First Search)
	 * algorithm
	 * 
	 * @return List of nodes (as strings) using the BFS algorithm
	 */
	public List<Node<String>> breadthFirstSearch() {

		// run BFS algorithm on given first root
		List<Node<String>> visitedNodes = new ArrayList<>(breadthFirstSearch(root, false));

		System.out.println(this.adjacencyMap.keySet().toString());

		// check if array list does not contain all nodes
		if (visitedNodes.size() != this.getAllNodes().size()) {

			// store set of all keys in variable
			Set<Node<String>> keySet = this.adjacencyMap.keySet();

			// iterate through all edges and get source node
			for (Node<String> key : keySet) {

				// get node
				Node<String> node = key;

				// if key node is not in visited, it is the graph contains multiple components
				// and said key is the root of another component of the graph
				if (!visitedNodes.contains(node)) {

					// run it through algorithm, store in new temp list
					List<Node<String>> tempList = new ArrayList<>(breadthFirstSearch(node, false));

					// add components of tempList into visitedNodes
					visitedNodes.addAll(tempList);

				}

			}

		}

		// return list of all nodes in graph visited using BFS
		return visitedNodes;
	}

	/**
	 * This method returns a List nodes using the BFS (Breadth First Search)
	 * algorithm
	 * 
	 * @param start A "TwitterHandle" in the graph
	 * @return List of nodes (as strings) using the BFS algorithm
	 */
	public List<Node<String>> breadthFirstSearch(Node<String> start, boolean rooted) {// name to breadthFirstSearch

		// array list to store 'visited' nodes
		List<Node<String>> visited = new ArrayList<>();

		// create queue that will be traversed, initialise with appending start node
		NodesStackAndQueue<Node<String>> neighbours = new NodesStackAndQueue<>();
		neighbours.append(start);

		// loop through queue and its elements until it is empty
		while (neighbours.isEmpty() == false) {

			// pop element at front of queue, store into variable
			Node<String> current = neighbours.pop();

			// if popped element is not in visited, add
			if (!visited.contains(current)) {

				visited.add(current);

				// access adjacencyMap containing linked list of all edges relating to current
				// element as key, get size of said list
				int size = this.adjacencyMap.get(current).size();

				// loop through list of edges
				for (int i = 0; i < size; i++) {

					// get target node of indexed element and append to queue
					if (!visited.contains(this.adjacencyMap.get(current).get(i).getTarget())) {

						neighbours.append(this.adjacencyMap.get(current).get(i).getTarget());

					}
				}

			}

		}

		// return array list of visited nodes
		return visited;

	}

	/**
	 * This method returns a List nodes using the DFS (Depth First Search) algorithm
	 * 
	 * @return List of nodes (as strings) using the DFS algorithm
	 */
	public List<Node<String>> depthFirstSearch() {

		// run algorithm through and store into array list
		List<Node<String>> visitedNodes = new ArrayList<>(depthFirstSearch(root, false));

		System.out.println(this.adjacencyMap.keySet().toString());

		// check if array list does not contain all nodes
		if (visitedNodes.size() != this.getAllNodes().size()) {

			// store set of all keys in variable
			Set<Node<String>> keySet = this.adjacencyMap.keySet();

			// iterate through all edges and get source node
			for (Node<String> key : keySet) {

				// get node
				Node<String> node = key;

				// if key node if not in visited, it is then a root of a new component of the
				// graph, becomes new root
				if (!visitedNodes.contains(node)) {

					// run it through algorithm, store in new temp list
					List<Node<String>> tempList = new ArrayList<>(depthFirstSearch(node, false));

					// add components of tempList into visitedNodes
					visitedNodes.addAll(tempList);

				}

			}

		}

		return visitedNodes;
	}

	/**
	 * This method returns a List nodes using the DFS (Depth First Search) algorithm
	 * 
	 * @param start A "TwitterHandle" in the graph
	 * @return List of nodes (as strings) using the DFS algorithm
	 */
	public List<Node<String>> depthFirstSearch(Node<String> start, boolean rooted) {

		// array list to store 'visited' nodes
		List<Node<String>> visited = new ArrayList<>();

		// create stack that will be traversed, initialise with appending start
		NodesStackAndQueue<Node<String>> neighbours = new NodesStackAndQueue<>();
		neighbours.push(start);

		// loop through stack and its elements until it is empty
		while (neighbours.isEmpty() == false) {

			// pop element at top of stack, store into variable
			Node<String> current = neighbours.pop();

			// if popped element is not in visited, add
			if (!visited.contains(current)) {

				visited.add(current);

				// access adjacencyMap containing linked list of all edges relating to current
				// element as key, get size of said list
				int size = this.adjacencyMap.get(current).size();

				// loop through list of edges
				for (int i = 0; i < size; i++) {

					// get target node of indexed element and push to top of stack
					if (!visited.contains(this.adjacencyMap.get(current).get(i).getTarget())) {

						neighbours.push(this.adjacencyMap.get(current).get(i).getTarget());

					}
				}

			}

		}

		// return array list of visited nodes
		return visited;

	}

	/**
	 * @return returns the set of all nodes in the graph
	 */
	public Set<Node<String>> getAllNodes() {

		Set<Node<String>> out = new HashSet<>();
		out.addAll(adjacencyMap.keySet());
		for (Node<String> n : adjacencyMap.keySet()) {
			LinkedList<Edge<Node<String>>> list = adjacencyMap.get(n);
			for (int i = 0; i < list.size(); i++) {
				out.add(list.get(i).getSource());
				out.add(list.get(i).getTarget());
			}
		}
		return out;
	}

	/**
	 * @return returns the set of all edges in the graph
	 */
	protected Set<Edge<Node<String>>> getAllEdges() {
		Set<Edge<Node<String>>> out = new HashSet<>();
		for (Node<String> n : adjacencyMap.keySet()) {
			LinkedList<Edge<Node<String>>> list = adjacencyMap.get(n);
			for (int i = 0; i < list.size(); i++) {
				out.add(list.get(i));
			}
		}
		return out;
	}

	/**
	 * @return returns the set of twitter handles in the graph
	 */
	public Set<TwitterHandle> getUsersFromNodes() {
		Set<TwitterHandle> users = new LinkedHashSet<TwitterHandle>();
		for (Node<String> n : getAllNodes()) {
			users.add(new TwitterHandle(n.getValue()));
		}
		return users;
	}

}
