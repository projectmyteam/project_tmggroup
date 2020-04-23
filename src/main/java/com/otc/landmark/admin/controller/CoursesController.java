package com.otc.landmark.admin.controller;

import com.otc.landmark.web.Utils.UtilsUploadFile;
import com.otc.landmark.web.constant.CommonConst;
import com.otc.landmark.web.constant.UrlConst;
import com.otc.landmark.web.dto.CoursesDto;
import com.otc.landmark.web.service.impl.CourseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("admin/course")
public class CoursesController {

    @GetMapping(value = "list")
    public ModelAndView listCourses() {
        ModelAndView mv = new ModelAndView("otc.admin.course.list.view");
        List<CoursesDto> coursesDtos = courseService.findAll();
        mv.addObject("courses", coursesDtos);
        return mv;
    }

    @GetMapping(value = "add")
    public ModelAndView addCourses(@ModelAttribute CoursesDto coursesDto) {
        ModelAndView mv = new ModelAndView("otc.admin.course.add.view");
        mv.addObject("coursesDto", coursesDto);
        return mv;
    }

    @PostMapping(value = "add")
    public ModelAndView postCourses(HttpServletRequest req, @ModelAttribute CoursesDto coursesDto, RedirectAttributes redirectAttributes) throws Exception{
        ModelAndView mav = new ModelAndView("otc.admin.course.add.view");
        CoursesDto coursesResult = courseService.addCourse(coursesDto, req);
        redirectAttributes.addFlashAttribute("result", coursesResult.getResult());
        String viewName = UrlConst.REDIRECT+"list";
        mav.setViewName(viewName);
        return mav;
    }

    @Autowired
    private CourseServiceImpl courseService;

}
