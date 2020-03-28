package com.otc.landmark.web.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "otc_comment_attachment")
public class CommentAttachment extends Attachment{
	
	@Column(name = "COMMENT_ID")
	private Long commentId;

	public Long getCommentId() {
		return commentId;
	}

	public void setCommentId(Long commentId) {
		this.commentId = commentId;
	}

}
