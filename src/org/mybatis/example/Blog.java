package org.mybatis.example;

import java.util.List;

public class Blog {
	private int id;
	private String title;
	private String content;
	private String owner;
	private List<String> comments;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public List<String> getComments() {
		return comments;
	}

	public void setComments(List<String> comments) {
		this.comments = comments;
	}

	@Override
	public String toString() {
		return " ----------------博客-----------------\n id: " + id
				+ "\n title: " + title + "\n content: " + content
				+ "\n owner: " + owner;
	}
}