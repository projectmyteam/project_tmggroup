package com.otc.landmark.web.domain;

import javax.persistence.*;

@Entity
@Table(name = "otc_courses_clip")
public class CourseClip extends AbstractCommon{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Id_Title_Courses_Clip", referencedColumnName = "ID")
    private CoursesTitleOfClip coursesTitleOfClip;
    private String title;
    @Column(name = "video_times")
    private Integer videoTimes;
    @Column(name = "source_link")
    private String sourceLink;

    public String getSourceLink() {
        return sourceLink;
    }

    public void setSourceLink(String sourceLink) {
        this.sourceLink = sourceLink;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CoursesTitleOfClip getCoursesTitleOfClip() {
        return coursesTitleOfClip;
    }

    public void setCoursesTitleOfClip(CoursesTitleOfClip coursesTitleOfClip) {
        this.coursesTitleOfClip = coursesTitleOfClip;
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
}
