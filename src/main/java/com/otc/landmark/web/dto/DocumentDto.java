package com.otc.landmark.web.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.springframework.web.multipart.MultipartFile;

public class DocumentDto implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Long id;

	@NotEmpty(message = "Tiêu đề không được để trống")
	private String subject;
	
	@NotEmpty(message = "Mô tả không được để trống")
	private String body;
	
	private MultipartFile fullFile;
	
	private String filePath;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public MultipartFile getFullFile() {
		return fullFile;
	}

	public void setFullFile(MultipartFile fullFile) {
		this.fullFile = fullFile;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

}
