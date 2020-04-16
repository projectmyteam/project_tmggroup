package com.otc.landmark.web.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "otc_comment")
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	protected Long id;

	@Column(name = "ENTRY_ID")
	private Long entryId;

	@Column(name = "SUBJECT")
	protected String subject;

	@Column(name = "BODY")
	protected String body;

	@Column(name = "CREATED_DATE")
	protected Date createdDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CREATED_BY", referencedColumnName = "USER_ID")
	protected User createdBy;

	@Column(name = "UPDATED_DATE")
	protected Date updatedDate;

	@Column(name = "UPDATED_BY")
	protected Long updatedBy;

	@Column(name = "DELETED_DATE")
	protected Date deletedDate;

	@Column(name = "DELETED_BY")
	protected Long deletedBy;
	
	@Transient
	private List<CommentAttachment> commentAttachments = new ArrayList<CommentAttachment>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getEntryId() {
		return entryId;
	}

	public void setEntryId(Long entryId) {
		this.entryId = entryId;
	}

	public List<CommentAttachment> getCommentAttachments() {
		return commentAttachments;
	}

	public void setCommentAttachments(List<CommentAttachment> commentAttachments) {
		this.commentAttachments = commentAttachments;
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

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
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
}
