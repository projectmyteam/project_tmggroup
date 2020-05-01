package com.otc.landmark.web.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.otc.landmark.web.domain.CourseClip;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CoursesTitleOfClipDto implements Serializable {

    private Long id;
    private CoursesDto courses;
    private Long courseId;
    private Set<CourseClipDto> courseClipDtos;
    private List<CourseClipDto> courseListClips;
    private String title;
    private Integer videoTimes;
    private String source;
    private String createdDate;

    private String result;

    public List<CourseClipDto> getCourseListClips() {
        return courseListClips;
    }

    public void setCourseListClips(List<CourseClipDto> courseListClips) {
        this.courseListClips = courseListClips;
    }

    public Set<CourseClipDto> getCourseClipDtos() {
        return courseClipDtos;
    }

    public void setCourseClipDtos(Set<CourseClipDto> courseClipDtos) {
        this.courseClipDtos = courseClipDtos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CoursesDto getCourses() {
        return courses;
    }

    public void setCourses(CoursesDto courses) {
        this.courses = courses;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
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

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
