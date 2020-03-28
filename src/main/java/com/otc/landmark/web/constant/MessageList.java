package com.otc.landmark.web.constant;

import java.util.ArrayList;
import java.util.List;

public class MessageList {

	private List<Message> messages = new ArrayList<Message>();
	private String status;
	
	public MessageList(String status) {
		this.status = status;
	}

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void add(String content) {
		Message message = new Message(this.status, content);
		messages.add(message);
	}
}
