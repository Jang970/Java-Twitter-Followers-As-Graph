package nz.ac.auckland.se281.a4;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nz.ac.auckland.se281.a4.ds.Graph;
import nz.ac.auckland.se281.a4.ds.Node;

//*******************************
//YOU SHOUD MODIFY THE SPECIFIED 
//METHODS  OF THIS CLASS
//HELPER METHODS CAN BE ADDED
//REQUIRED LIBRARIES ARE ALREADY 
//IMPORTED -- DON'T ADD MORE
//*******************************
public class TweetGraph extends Graph {

	protected List<Tweet> tweets;
	// Change this to map
	protected Map<TwitterHandle, List<Tweet>> nodeTweets;

	public TweetGraph(List<String> edges, List<Tweet> tweets, Map<TwitterHandle, List<Tweet>> map) throws Exception {
		super(edges);
		this.tweets = tweets;
		// changed to LinkedHashMap for fixed order
		this.nodeTweets = new LinkedHashMap<>();
		nodeTweets = map;
	}

	public List<Tweet> getTweets(Node<?> n) {
		return nodeTweets.get(n);
	}

	public List<String> getTweetsTexts(TwitterHandle n) {
		List<String> texts = new ArrayList<>(); // Only allowed to use ArrayList HERE !!!
		for (Tweet t : getTweets(n)) {
			texts.add(t.getTextString());
		}
		return texts;
	}

	// search for a keyword in a tweet starting from a given node
	public String searchTweet(TwitterHandle user, String tweetKeyword) {

		// store all visited nodes from start point using DFS traversal, it seems
		// implementation does not need to use rooted as DFS method with parameters will
		// only search for given neighbours of the inputted node, whole graph traversal
		// is done with the no parameters version of DFS method
		List<Node<String>> allUsers = depthFirstSearch(user, false);

		// variables to be used in implementation, stores strings needed for output and
		// boolean to determine if a tweet with keyword was found for that user
		boolean foundTweet = false;
		Node<String> userNode = null;
		String tweetWithKeyword = null;
		List<Tweet> userTweets;
		TwitterHandle userWithTweet = null;

		// iterate through allUsers
		for (Node<String> userHandle : allUsers) {

			// get List of all corresponding user and their tweets
			userTweets = this.getTweets(userHandle);

			// check if already found, break in that case
			if (foundTweet) {
				break;
			}

			// iterate through all of users tweets, if keyword is found then we store the
			// node and tweet into variables
			for (Tweet currentTweet : userTweets) {

				// turn currentTweet into string
				String tweetString = currentTweet.getTextString();

				if (tweetString.contains(tweetKeyword)) {

					// store values into variables
					userNode = userHandle;
					tweetWithKeyword = tweetString;
					// update boolean to be true
					foundTweet = true;
					break;

				}

			}

		}

		// get the list of all twitter handles in graph
		Set<TwitterHandle> allHandles = this.nodeTweets.keySet();

		// iterate through
		for (TwitterHandle currentTwitterHandle : allHandles) {

			// if the value of node with tweet found is equal to value of the twitter
			// handle, then they are the same
			if (currentTwitterHandle.getValue().equalsIgnoreCase(userNode.getValue())) {

				userWithTweet = currentTwitterHandle;
				break;

			}

		}

		if (foundTweet) {

			// found a tweet with such string wanted, create and return output wanted
			String stringFound = "The tweet string found is: " + tweetWithKeyword;
			String userTweeted = "User " + userWithTweet.getName() + " {" + userWithTweet.getValue() + "}" + " tweeted "
					+ tweetKeyword;

			String output = stringFound + "\n" + userTweeted;
			return output;

		}

		// otherwise, no tweet found, give desired output
		String noFoundUser = "No successor of " + user.toString() + "tweeted " + tweetKeyword;
		return noFoundUser;
	}
}
