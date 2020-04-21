package com.otc.landmark.web.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "otc_document")
public class Document implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "SUBJECT")
	private String subject;

	@Column(name = "DESCRIPTION")
	private String description;
	
	@Column(name = "FIRST_PAGE_IMG")
	private String firstPageImg;
	
	@Column(name = "FULL_FILE")
	private String fullFile;
	
	@Column(name = "PREVIEW_FILE")
	protected String previewFile;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFirstPageImg() {
		return firstPageImg;
	}

	public void setFirstPageImg(String firstPageImg) {
		this.firstPageImg = firstPageImg;
	}

	public String getFullFile() {
		return fullFile;
	}

	public void setFullFile(String fullFile) {
		this.fullFile = fullFile;
	}

	public String getPreviewFile() {
		return previewFile;
	}

	public void setPreviewFile(String previewFile) {
		this.previewFile = previewFile;
	}
}
