package com.cs.socialmedia.service;

import java.util.List;
import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.cs.socialmedia.dao.PostDAO;
import com.cs.socialmedia.dto.Post;
import com.cs.socialmedia.dao.IPostDAO;
import com.cs.socialmedia.exception.SocialMediaException;

public class PostService implements IPostService {
	private static final Logger logger = LogManager.getLogger(PostService.class);
	private IPostDAO postDAO = new PostDAO();

	@Override
	public boolean createPost(String userId, String content) {
		logger.debug("Entering createPost method of PostService");
		try {
			//For Junit we have added below additional check as same validations are done in main class
			if (Objects.nonNull(userId)) {
				return postDAO.createPost(userId.toLowerCase(), content);
			} else {
				logger.error("User Id cannot be null");
				return false;
			}
		} catch (SocialMediaException ex) {
			logger.error(ex.getMessage());
			return false;
		}
	}

	@Override
	public List<Post> getNewsFeed(String userId) {
		logger.debug("Entering getNewsFeed method of PostService");
		try {
			return postDAO.getNewsFeed(userId.toLowerCase());
		} catch (SocialMediaException ex) {
			logger.error(ex.getMessage());
			return null;
		}

	}

}
