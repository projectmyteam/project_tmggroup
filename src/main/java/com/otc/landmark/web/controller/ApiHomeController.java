package com.otc.landmark.web.controller;

import com.otc.landmark.web.Utils.Utils;
import com.otc.landmark.web.constant.UrlConst;
import com.otc.landmark.web.domain.Category;
import com.otc.landmark.web.repository.CategoryDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(UrlConst.API)
public class ApiHomeController {

    private static final Log logger = LogFactory.getLog(ApiHomeController.class);

    @Autowired
    CategoryDao categoryDao;

    @RequestMapping(value = UrlConst.MENU, method = RequestMethod.GET)
    @ResponseBody
    public String createMenuApi() {
        List<Category> categoryList = categoryDao.findAll();
        List<Category> categories = new ArrayList<>();
        for (Category category : categoryList) {
            Category category_new = new Category();
            String txtUpperCase = Utils.upperCaseFirstChar(category.getCategoryName());
            category_new.setParentCategoryId(category.getParentCategoryId());
            category_new.setCategoryId(category.getCategoryId());
            category_new.setCategoryCode(category.getCategoryCode());
            category_new.setCategoryName(txtUpperCase);
            categories.add(category_new);
        }
        String menu = Utils.createMenu(categories);
        return menu;
    }


}
