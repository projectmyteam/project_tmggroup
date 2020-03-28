package com.otc.landmark.web.Utils;

import com.otc.landmark.web.domain.Category;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.MalformedParameterizedTypeException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Utils {

    private static String print = "";

    public static String createMenu(List<Category> categories) {
        String sprint = "<ul class='navbar-nav mr-auto'>";
        sprint += "<li class='nav-item'><a href='' class='nav-link nav-parent'>Trang Chủ</a></li>";
        for (Category category : categories) {
            if(category.getParentCategoryId() == null) {
                print = "";
                sprint += "<li class='nav-item'><a href='' class='nav-link nav-parent category category-parent' data-idcate="+category.getCategoryId()+">"+category.getCategoryName()+"</a>";
                int level = 1;
                String printChild = createChildMenu(category.getCategoryId(), categories, level);
                sprint += printChild;
                sprint += "</li>";
            }
        }
        sprint += "</ul>";
        return sprint;
    }

    public static String createChildMenu(Long parentId, List<Category> categories, int level) {
        List<Long> idChildMenu = new ArrayList<Long>();
        List<String> nameChildMenu = new ArrayList<String>();
        for (int i = 0; i < categories.size(); i++) {
            if(categories.get(i).getParentCategoryId() != null){
                if(categories.get(i).getParentCategoryId() == parentId) {
                    idChildMenu.add(categories.get(i).getCategoryId());
                    nameChildMenu.add(categories.get(i).getCategoryName());
                }
            }
        }
        if (idChildMenu.size() > 0) {
            level++;
            print += "<ul class=level-"+level+">";
            for (int i = 0; i < idChildMenu.size(); i++) {
                print += "<li class='nav-item'>";
                print += "<a href='#' class='category category-child' data-idcate='"+idChildMenu.get(i)+"'>" + nameChildMenu.get(i) + "</a>";
                createChildMenu(idChildMenu.get(i), categories, level);
            }
            print += "</ul></li>";
        } else {
            print += "</li>";
        }
        return print;
    }

    public static String upperCaseFirstChar(String txt) {
        String[] arr_txt = txt.split(" ");
        String txtUpperCase = "";
        for (int i = 0; i < arr_txt.length; i++) {
            txtUpperCase += arr_txt[i].substring(0,1).toUpperCase() + arr_txt[i].substring(1) + " ";
        }
        return txtUpperCase;
    }

    public static Category upperCaseFirstCharsetCate(Category category) {
        category.setCategoryName(upperCaseFirstChar(category.getCategoryName()));
        return category;
    }

    public static List<Category> upperCaseFirstCharsetListCate(List<Category> categoryList) {
        List<Category> categories = new ArrayList<>();
        for (Category category : categoryList) {
            Category cate = new Category();
            cate = upperCaseFirstCharsetCate(category);
            categories.add(cate);
        }
        return categories;
    }

}
