package com.otc.landmark.web.controller;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "courses")
public class AppCoursesController {

    private static final Log logger = LogFactory.getLog(AppCoursesController.class);

    @GetMapping
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("otc.web.courses.list.view");
        return mv;
    }

    @GetMapping(value = "detail")
    public ModelAndView detail() {
        ModelAndView mv = new ModelAndView(("otc.web.courses.detail.view"));
        return mv;
    }





}
