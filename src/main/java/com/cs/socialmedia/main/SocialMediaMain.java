package com.cs.socialmedia.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.cs.socialmedia.dto.Post;
import com.cs.socialmedia.service.FollowService;
import com.cs.socialmedia.service.IFollowService;
import com.cs.socialmedia.service.IPostService;
import com.cs.socialmedia.service.PostService;

/**
 * This class is the entry point of Social Media Application. It supports Create
 * Post , GetNewsFeed , FollowUser and Unfollow user operations
 * 
 * @author Lucky Sadhwani
 *
 */
public class SocialMediaMain {
	private static final Logger logger = LogManager.getLogger(SocialMediaMain.class);
	private static IPostService postService = new PostService();
	private static IFollowService followService = new FollowService();
	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) {
		logger.debug("Entering Main method of SocialMediaMain");
		try {

			do {
				System.out.println("Please Enter Your Choice \n" 
						+ "1. Create New Post \n" 
						+ "2. Follow a User \n"
						+ "3. Unfollow a User \n" 
						+ "4. Get recent news feed \n" + "5. Exit \n"
						+ "Enter Numeric Value of choice => ");
				int choice = Integer.parseInt(reader.readLine());

				switch (choice) {
				case 1:
					createPost();
					break;
				case 2:
					followUser();
					break;
				case 3:
					unfollowUser();
					break;
				case 4:
					getNewsFeed();
					break;
				case 5:
					System.exit(0);
				default:
					System.out.println("Only numeric value between 1 and 5 is allowed \n");
					break;
				}
			} while (true);
		} catch (IOException ex) {
			logger.error("Error while reading user input " + ex.getMessage());
		} finally {
			try {
				logger.debug("Entered in finally block of main method");
				reader.close();
			} catch (IOException e) {
				logger.error("Error while closing Buffer Reader " + e.getMessage());
			}
		}
		logger.debug("Exiting main method of SocialMediaMain");
	}

	private static void getNewsFeed() throws IOException {
		System.out.println("Please enter user id to get recent post => ");
		String userId = reader.readLine();
		if (validateUserId(userId)) {
			List<Post> postList = postService.getNewsFeed(userId);
			String message = null;
			if (Objects.isNull(postList)) {
				message = "User Id "+userId +" is not present";
			} else if (postList.isEmpty()) {
				message = "No Post to display";
			} else {
				StringBuilder sb = new StringBuilder("Posts are \n");
				postList.forEach(post -> sb.append(post).append("\n"));
				message = sb.toString();
			}
			logger.info(message);
		}
	}

	private static void unfollowUser() throws IOException {
		System.out.println("Please enter follower id => ");
		String followerId = reader.readLine();
		System.out.println("Please enter followee id to unfollow => ");
		String followeeId = reader.readLine();
		if (validateUserId(followerId) && validateUserId(followeeId)) {
			boolean isSuccess = followService.unfollowUser(followerId, followeeId);
			String message = isSuccess ? followeeId + " is now unfollowed for " + followerId
					: "Failure , Please enable the debug level and check the logs";
			logger.info(message);
		}
	}

	private static void followUser() throws IOException {
		System.out.println("Please enter follower user id => ");
		String followerId = reader.readLine();
		System.out.println("Please enter followee user id to follow => ");
		String followeeId = reader.readLine();
		if (validateUserId(followerId) && validateUserId(followeeId)) {
			if (followerId.equalsIgnoreCase(followeeId)) {
				logger.error("Follower and Followee user id can not be same");
			} else {
				boolean isSuccess = followService.followUser(followerId, followeeId);
				String message = isSuccess ? followerId + " is following to " + followeeId
						: "Failure , Please enable the debug level and check the logs";
				logger.info(message);
			}
		}
	}

	private static void createPost() throws IOException {
		System.out.println("Please enter user id => ");
		String userId = reader.readLine();
		System.out.println("Please enter post content=> ");
		String content = reader.readLine();
		if (validateUserId(userId) && validatePostContent(content)) {
			boolean isSuccess = postService.createPost(userId, content);
			String message = isSuccess ? "Post created successfully" : "Error occured while creating the post";
			logger.info(message);
		}
	}

	private static boolean validateUserId(String userId) {
		if (userId.isEmpty()) {
			System.out.println("Invalid Data : User Id cannot be empty");
			return false;
		}
		return true;
	}

	private static boolean validatePostContent(String content) {
		if (content.isEmpty()) {
			System.out.println("Invalid Data : Post content cannot be empty");
			return false;
		}
		return true;
	}

}
