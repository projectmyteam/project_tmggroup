package com.otc.landmark.web.constant;

public class Message {
	public static final String SUCCESS = "success";
	public static final String ERROR = "error";

	private String status;
	private String content;
	
	public Message(String status, String content) {
		this.status = status;
		this.content = content;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
