package com.cs.socialmedia.dto;

import java.time.LocalDateTime;

public class Post {
	private Long id;
	private String content;
	private LocalDateTime postDateTime;

	public Post() {
		super();
	}

	public Post(Long id, String content, LocalDateTime postDateTime) {
		super();
		this.id = id;
		this.content = content;
		this.postDateTime = postDateTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public LocalDateTime getPostDateTime() {
		return postDateTime;
	}

	public void setPostDateTime(LocalDateTime postDateTime) {
		this.postDateTime = postDateTime;
	}

	@Override
	public String toString() {
		return "Post [id=" + id + ", content=" + content + ", postDateTime=" + postDateTime + "]";
	}

	

	

}
