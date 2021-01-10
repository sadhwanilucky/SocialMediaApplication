package com.cs.socialmedia.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import com.cs.socialmedia.dto.Post;
import com.cs.socialmedia.util.SocialMediaConstants;


@TestMethodOrder(OrderAnnotation.class)
public class PostServiceTest  {
	
	private static IPostService postService = new PostService();
	private IFollowService followService = new FollowService();
	
	@BeforeAll
    public static void setup() {
		createPost();
	}

	@Test
	public void testCreatePost() {
		boolean result = postService.createPost("testUser1", "testMessage");
		assertTrue(result);
	}
	
	@Test
	public void testCreatePostWithUserIdEmpty() {
		boolean result = postService.createPost("", "testMessage");
		assertFalse(result);
	}
	
	@Test
	public void testCreatePostWithContentEmpty() {
		boolean result = postService.createPost("testUser2", "");
		assertFalse(result);
	}
	
	@Test
	public void testCreatePostWithUserIdNull() {
		boolean result = postService.createPost(null, "testMessage");
		assertFalse(result);
	}
	
	@Test
	public void testCreatePostWithContentNull() {
		boolean result = postService.createPost("testUser2", null);
		assertFalse(result);
	}
	
	@Test
	public void testGetNewsFeed() {
		List<Post> postList = postService.getNewsFeed("lucky");
		assertNotNull(postList);
		assertEquals(15, postList.size());
		assertTrue(postList.stream().anyMatch(obj -> obj.getContent().equals("message15")));
	}
	
	@Test
	public void testGetNewsFeedAFterFollowingandUnFollowingUser() {
		followService.followUser("lucky", "pankaj");
		List<Post> postList = postService.getNewsFeed("lucky");
		assertNotNull(postList);
		assertEquals(19, postList.size());
		assertTrue(postList.stream().anyMatch(obj -> obj.getContent().equals("message19")));
		followService.unfollowUser("lucky", "pankaj");
		postList = postService.getNewsFeed("lucky");
		assertNotNull(postList);
		assertEquals(15, postList.size());
		assertTrue(postList.stream().anyMatch(obj -> obj.getContent().equals("message15")));
	}
	
	@Test
	public void testGetNewsFeedForMaxFeedLimit() {
		followService.followUser("lucky", "pankaj");
		followService.followUser("lucky", "sagar123");
		List<Post> postList = postService.getNewsFeed("lucky");
		assertEquals(SocialMediaConstants.FEED_LIST_LIMIT, postList.size());
		followService.unfollowUser("lucky", "pankaj");
		followService.unfollowUser("lucky", "sagar123");
	}
	
	@Test
	public void testGetNewsFeedForUnavailableUserId() {
		List<Post> postList = postService.getNewsFeed("TestUnvailable");
		assertNull(postList);
	}
	
	@Test
	public void testGetNewsFeedForEmptyPostList() {
		followService.followUser("Test1", "Test2");
		List<Post> postList = postService.getNewsFeed("Test1");
		assertEquals(0, postList.size());
	}
	
	
	private static void createPost() {
		postService.createPost("lucky", "message1");
		postService.createPost("lucky", "message2");
		postService.createPost("lucky", "message3");
		postService.createPost("lucky", "message4");
		postService.createPost("lucky", "message5");
		postService.createPost("lucky", "message6");
		postService.createPost("lucky", "message7");
		postService.createPost("lucky", "message8");
		postService.createPost("Lucky", "message9");
		postService.createPost("lucky", "message10");
		postService.createPost("lucky", "message11");
		postService.createPost("lucky", "message12");
		postService.createPost("lucky", "message13");
		postService.createPost("lucky", "message14");
		postService.createPost("lucky", "message15");
		postService.createPost("pankaj", "message16");
		postService.createPost("pankaj", "message17");
		postService.createPost("pankaj", "message18");
		postService.createPost("pankaj", "message19");
		postService.createPost("sagar123", "message20");
		postService.createPost("sagar123", "message21");
		postService.createPost("sagar123", "message22");
		postService.createPost("sagar123", "message23");
	}

}
