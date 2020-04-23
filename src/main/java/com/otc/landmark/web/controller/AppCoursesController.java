package com.otc.landmark.web.controller;


import com.otc.landmark.web.dto.CoursesDto;
import com.otc.landmark.web.dto.CoursesTitleOfClipDto;
import com.otc.landmark.web.service.impl.CourseServiceImpl;
import com.otc.landmark.web.service.impl.CourseTitleOfClipServiceImpl;
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
        List<CoursesDto> coursesDtos = courseService.findAll();
        mv.addObject("courses", coursesDtos);
        return mv;
    }

    @GetMapping(value = "{id}/detail")
    public ModelAndView detail(@PathVariable(value = "id")Long courseId) {
        ModelAndView mv = new ModelAndView(("otc.web.courses.detail.view"));
        CoursesDto coursesDto = courseService.findById(courseId);
        mv.addObject("course", coursesDto);
        return mv;
    }

    @PostMapping(value = "add/courseTitleOfClip")
    public ResponseEntity<String> addCourseTitleOfClip(@RequestBody CoursesTitleOfClipDto coursesTitleOfClipDto) {
        CoursesTitleOfClipDto result = courseTitleOfClipService.add(coursesTitleOfClipDto);
        if(result.getResult().equals("true")) {
            return new ResponseEntity<>(result.getResult(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(result.getResult(), HttpStatus.OK);
        }
    }

    @Autowired
    private CourseServiceImpl courseService;
    @Autowired
    private CourseTitleOfClipServiceImpl courseTitleOfClipService;

}
