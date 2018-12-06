package com.pan.blog.controller;

import com.pan.blog.entity.Blog;
import com.pan.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by FantasticPan on 2018/12/1.
 */
@Controller
public class IndexController {

    @Autowired
    private BlogService blogService;

    @RequestMapping("/")
    public ModelAndView index(Model model) {
        List<Blog> blogList = blogService.getAllBlog();
        model.addAttribute("blogList", blogList);
        return new ModelAndView("index", "blogModel", model);
    }

    //@RequestMapping("/")
    //public String index(Model model) {
    //
    //    return "index";
    //}

    @RequestMapping("/403")
    public String page403() {
        return "403";
    }

    @GetMapping("/login")
    public String loginHtml() {
        return "login";
    }

    @RequestMapping("/register")
    public String register() {
        return "register";
    }

    @RequestMapping("/tag")
    public String tag() {
        return "tag-catalog";
    }
}
