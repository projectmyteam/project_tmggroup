package com.otc.landmark.web.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "otc_comment")
public class Comment extends AbstractPosting{
	
	@Column(name = "ENTRY_ID")
	private Long entryId;
	
	@Transient
	private Entry entry;
	
	@Transient
	private List<CommentAttachment> commentAttachments = new ArrayList<CommentAttachment>();

	public Long getEntryId() {
		return entryId;
	}

	public void setEntryId(Long entryId) {
		this.entryId = entryId;
	}

}
