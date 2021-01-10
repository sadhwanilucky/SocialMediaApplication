package com.cs.socialmedia.dao;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.cs.socialmedia.dto.Post;
import com.cs.socialmedia.dto.User;
import com.cs.socialmedia.exception.SocialMediaException;
import com.cs.socialmedia.util.SocialMediaConstants;

public class PostDAO implements IPostDAO {
	private static final Logger logger = LogManager.getLogger(PostDAO.class);
	public static Map<User, Set<String>> userPostMap = new HashMap<User, Set<String>>();
	private static Long postId =1L;
	
	
	@Override
	public boolean createPost(String userId, String content) throws SocialMediaException {
		logger.debug("Entering createPost method of PostDAO");
		try {
			//For Junit we have added below additional check as same validations are done in main class
			if(Objects.isNull(userId)   || userId.isEmpty() ) {
				throw new SocialMediaException("User Id cannot be empty or null");
			}else if(Objects.isNull(content) || content.isEmpty() ) {
				throw new SocialMediaException("Post content cannot be empty or null ");
			}
			
			Post post = new Post(postId,content,LocalDateTime.now());
			postId++;
			Optional<User> optionalUser = getUserByUserId(userId);
			User user = null ;
			if(optionalUser.isPresent()) {
				user = optionalUser.get();
			}else
			{
				user = new User(userId,new ArrayList<Post>());
				userPostMap.put(user, new HashSet<String>());
			}
			List<Post> postList = user.getPostList();
			postList.add(post);
			logger.debug("Exiting createPost method of PostDAO");
			return true;
		}catch(Exception ex) {
			throw new SocialMediaException("Error occured while creating the post");
		}
		
	}

	@Override
	public List<Post> getNewsFeed(String userId) throws SocialMediaException {
		logger.debug("Entering getNewsFeed method of PostDAO");
		Optional<Set<String>> optionalFolloweeList = getFolloweeListByUserId(userId);
		if (optionalFolloweeList.isPresent()) {
			Set<String> followeeList = optionalFolloweeList.get();
			Set<String> userdIds = new HashSet<String>(followeeList);
			userdIds.add(userId);
			List<Post> postList = getPostByListOfUserIds(userdIds);
			logger.debug("Exiting getNewsFeed method of PostDAO");
			return postList;
		} else {
			throw new SocialMediaException("User Id is not present");
		}
	}
	
	
	private Optional<User> getUserByUserId(String userId) {
		return userPostMap.entrySet().stream()
				.filter(entry -> (entry.getKey().getId()).equals(userId))
				.findFirst()
				.map(Map.Entry::getKey);
	}
	
	private Optional<Set<String>> getFolloweeListByUserId(String userId) {
		return userPostMap.entrySet().stream()
				.filter(entry -> (entry.getKey().getId()).equals(userId))
				.findFirst()
				.map(Map.Entry::getValue);
	}
	
	private List<Post> getPostByListOfUserIds(Set<String> userIdList) {
		List<Post> postList = new ArrayList<Post>();
				userPostMap.entrySet().stream()
				.filter(entry -> userIdList.contains((entry.getKey().getId())) )
				.map(e -> e.getKey())
				.forEach(user -> postList.addAll(user.getPostList()));
		return postList.stream().sorted(Comparator.comparing(Post :: getPostDateTime).reversed()).limit(SocialMediaConstants.FEED_LIST_LIMIT).collect(Collectors.toList());
	}
	
}
