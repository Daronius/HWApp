package de.codehasher.badmintunity.news;

import java.io.Serializable;

public class News implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String content;
	private String topic;
	private String date;

	public News(String id, String content, String topic, String date) {
		super();
		this.setId(id);
		this.setContent(content);
		this.setTopic(topic);
		this.setDate(date);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}

