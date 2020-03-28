package com.otc.landmark.web.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@MappedSuperclass
public abstract class AbstractPosting implements Posting, Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;
	
	@Column(name = "SUBJECT")
	protected String subject;
	
	@Column(name = "BODY")
	protected String body;
	
	@Column(name = "AVATAR")
	private String avatar;
	
	@Column(name = "CREATED_DATE")
	protected Date createdDate;

	@Column(name = "CREATED_BY")
	protected Long createdBy;
	
	@Column(name = "UPDATED_DATE")
	protected Date updatedDate;
	
	@Column(name = "UPDATED_BY")
	protected Long updatedBy;
	
	@Column(name = "DELETED_DATE")
	protected Date deletedDate;
	
	@Column(name = "DELETED_BY")
	protected Long deletedBy;
	
	public String getSubject() {
		return this.subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Long getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getDeletedDate() {
		return deletedDate;
	}

	public void setDeletedDate(Date deletedDate) {
		this.deletedDate = deletedDate;
	}

	public Long getDeletedBy() {
		return deletedBy;
	}

	public void setDeletedBy(Long deletedBy) {
		this.deletedBy = deletedBy;
	}
	
	public String getBody() {
		return this.body;
	}

	public void setBody(String body) {
		this.body = body;
	}
	
}
