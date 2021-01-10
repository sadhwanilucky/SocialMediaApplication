package com.cs.socialmedia.service;

import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;

@TestMethodOrder(OrderAnnotation.class)
public class FollowServiceTest {
	
	private static IPostService postService = new PostService();
	private IFollowService followService = new FollowService();
	
	@BeforeAll
    public static void setup() {
		createPost();
		
	}
	
	@Test
	public void testFollowAndUnfollowUser() {
		boolean result = followService.followUser("lucky", "sagar123");
		assertTrue(result);
		result = followService.unfollowUser("lucky", "sagar123");
		assertTrue(result);
	}
	
	@Test
	public void testFollowUnavailableUser() {
		boolean result = followService.followUser("lucky", "unavailable");
		assertTrue(result);
	
	}
	
	@Test
	public void testUnfollowUnavailableUser() {
		boolean result = followService.unfollowUser("lucky", "unavailable");
		assertFalse(result);
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
		postService.createPost("lucky", "message9");
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
