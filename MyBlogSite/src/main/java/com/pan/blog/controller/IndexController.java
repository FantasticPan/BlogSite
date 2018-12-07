package com.pan.blog.controller;

import com.pan.blog.entity.Blog;
import com.pan.blog.service.BlogService;
import com.pan.blog.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        Set<String> tagsList = new HashSet<>();
        for (Blog blog : blogList) {
            String[] tags = blog.getTags().split(",");
            tagsList.addAll(Arrays.asList(tags));
        }
        model.addAttribute("blogList", blogList);
        model.addAttribute("blogNum", blogService.blogNum());
        model.addAttribute("tags", tagsList);
        model.addAttribute("tagsSize", tagsList.size());

        return ResultUtil.view("index", "blogModel", model);
    }

    @RequestMapping("/403")
    public ModelAndView page403() {
        return ResultUtil.view("403");
    }

    @GetMapping("/login")
    public ModelAndView loginHtml() {
        return ResultUtil.view("login");
    }

    @RequestMapping("/register")
    public ModelAndView register() {
        return ResultUtil.view("register");
    }

    @RequestMapping("/tag")
    public ModelAndView tag() {
        return ResultUtil.view("tag-catalog");
    }
}
