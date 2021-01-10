package com.cs.socialmedia.dto;

import java.util.List;

public class User {

	private String id;
	private List<Post> postList;

	public User() {
		super();
	}

	public User(String id) {
		super();
		this.id = id;
	}
	
	public User(String id, List<Post> postList) {
		super();
		this.id = id;
		this.postList = postList;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<Post> getPostList() {
		return postList;
	}

	public void setPostList(List<Post> postList) {
		this.postList = postList;
	}

	@Override
	public int hashCode() {
		return this.id.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		User user = (User) obj;
		return user.id.equals(this.id);
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", postList=" + postList + "]";
	}

}
