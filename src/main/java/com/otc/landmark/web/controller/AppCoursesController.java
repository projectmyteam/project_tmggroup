package com.otc.landmark.web.controller;


import com.otc.landmark.web.dto.CourseClipDto;
import com.otc.landmark.web.dto.CoursesDto;
import com.otc.landmark.web.dto.CoursesTitleOfClipDto;
import com.otc.landmark.web.service.impl.CourseServiceImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value = "courses")
public class AppCoursesController {

    private static final Log logger = LogFactory.getLog(AppCoursesController.class);

    @GetMapping
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("otc.web.courses.list.view");
        List<CoursesDto> coursesDtos = courseService.findAllCourses();
        mv.addObject("courses", coursesDtos);
        return mv;
    }

    @GetMapping(value = "{id}/detail")
    public ModelAndView detail(@PathVariable(value = "id")Long courseId) {
        ModelAndView mv = new ModelAndView(("otc.web.courses.detail.view"));
        CoursesDto coursesDto = courseService.findCourseById(courseId);
        List<CoursesTitleOfClipDto> coursesTitleOfClipDtos = courseService.findByCourseId(courseId);
        mv.addObject("course", coursesDto);
        mv.addObject("courseTitleOfClip", coursesTitleOfClipDtos);
        return mv;
    }

    @PostMapping(value = "add/courseTitleOfClip")
    public ResponseEntity<CoursesTitleOfClipDto> addCourseTitleOfClip(@RequestBody CoursesTitleOfClipDto coursesTitleOfClipDto) {
        CoursesTitleOfClipDto result = courseService.addCoursesTitleClip(coursesTitleOfClipDto);
        if(result.getResult().equals("true")) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
    }

    @PostMapping(value = "edit/courseTitleOfClip")
    public ResponseEntity<CoursesTitleOfClipDto> editCoursesTitleOfClip(@RequestBody CoursesTitleOfClipDto dto) {
        CoursesTitleOfClipDto result = courseService.editCoursesTitleOfClip(dto);
        if(result.getResult().equals("true")) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
    }

    @PostMapping(value = "add/courseClip")
    public ResponseEntity<CourseClipDto> addCourseClip(@RequestBody CourseClipDto courseClipDto) {
        CourseClipDto result = courseService.addCourseClip(courseClipDto);
        if(result.getResult().equals("true")) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
    }

    @PostMapping(value = "edit/{courseClipId}/courseClip")
    public ResponseEntity<CourseClipDto> editCourseClip(@PathVariable(value = "courseClipId") Long courseClipId,
                                                        @RequestBody CourseClipDto courseClipDto) {
        CourseClipDto result = courseService.editCourseClip(courseClipId, courseClipDto);
        if(result.getResult().equals("true")) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
    }

    @PostMapping(value = "remove/{courseClipId}/courseClip")
    public ResponseEntity<CourseClipDto> removeCourseClip(@PathVariable(value = "courseClipId") Long courseClipId) {
        CourseClipDto result = courseService.removeCourseClip(courseClipId);
        if(result.getResult().equals("true")) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
    }

    @Autowired
    private CourseServiceImpl courseService;

}
