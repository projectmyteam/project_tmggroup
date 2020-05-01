package com.otc.landmark.web.domain;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "otc_title_courses_clip")
public class CoursesTitleOfClip extends AbstractCommon{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Id_Otc_Courses", referencedColumnName = "ID")
    private Courses courses;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "coursesTitleOfClip")
    private Set<CourseClip> courseClips;

    private String title;
    @Column(name = "video_times")
    private Integer videoTimes;
    private String source;


    public Set<CourseClip> getCourseClips() {
        return courseClips;
    }

    public void setCourseClips(Set<CourseClip> courseClips) {
        this.courseClips = courseClips;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Courses getCourses() {
        return courses;
    }

    public void setCourses(Courses courses) {
        this.courses = courses;
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
}
