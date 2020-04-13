package com.otc.landmark.web.dto;

import java.util.List;

public class CommentDto {

    private Long id;
    private Long userId;
    private Long entryId;
    private String comment;
    private Integer countComment;
    private UserDto user;
    private String createdDate;

    private String result;
    private Long newestCommentId;
    private List<CommentDto> threeCommentNewest;

    public Integer getCountComment() {
        return countComment;
    }

    public void setCountComment(Integer countComment) {
        this.countComment = countComment;
    }

    public List<CommentDto> getThreeCommentNewest() {
        return threeCommentNewest;
    }

    public void setThreeCommentNewest(List<CommentDto> threeCommentNewest) {
        this.threeCommentNewest = threeCommentNewest;
    }

    public Long getNewestCommentId() {
        return newestCommentId;
    }

    public void setNewestCommentId(Long newestCommentId) {
        this.newestCommentId = newestCommentId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getEntryId() {
        return entryId;
    }

    public void setEntryId(Long entryId) {
        this.entryId = entryId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }
}
