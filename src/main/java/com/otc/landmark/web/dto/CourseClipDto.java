package com.otc.landmark.web.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CourseClipDto implements Serializable {

    private Long id;
    private String title;
    private Integer videoTimes;
    private CoursesTitleOfClipDto coursesTitleOfClip;
    private Long coursesTitleOfClipId;
    private String createdDate;
    private Long createdBy;
    private String updatedDate;
    private Long updatedBy;

    private String result;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getVideoTimes() {
        return videoTimes;
    }

    public void setVideoTimes(Integer videoTimes) {
        this.videoTimes = videoTimes;
    }

    public CoursesTitleOfClipDto getCoursesTitleOfClip() {
        return coursesTitleOfClip;
    }

    public void setCoursesTitleOfClip(CoursesTitleOfClipDto coursesTitleOfClip) {
        this.coursesTitleOfClip = coursesTitleOfClip;
    }

    public Long getCoursesTitleOfClipId() {
        return coursesTitleOfClipId;
    }

    public void setCoursesTitleOfClipId(Long coursesTitleOfClipId) {
        this.coursesTitleOfClipId = coursesTitleOfClipId;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Long getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Long updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
