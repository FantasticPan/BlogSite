package com.pan.blog.controller;

import com.pan.blog.entity.Blog;
import com.pan.blog.entity.User;
import com.pan.blog.service.BlogService;
import com.pan.blog.util.ResultUtil;
import com.pan.blog.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Created by FantasticPan on 2018/12/3.
 */
@Controller
public class BlogController {

    @Autowired
    private BlogService blogService;
    @Qualifier("userServiceImpl")
    @Autowired
    private UserDetailsService userDetailsService;

    @GetMapping({"/{username}/blog/edit"})
    public ModelAndView getBlogEditPage(@PathVariable("username") String username, Model model) {
        User user = (User) userDetailsService.loadUserByUsername(username);
        model.addAttribute("user", user);
        model.addAttribute("blog", new Blog(null, null, null, null, null, null));

        return ResultUtil.view("blog-edit", "blogModel", model);
    }

    @GetMapping({"/{username}/blog/edit/{id}"})
    public ModelAndView getBlogModifyPage(@PathVariable("username") String username,
                                          @PathVariable("id") Long id,
                                          Model model) {

        User user = (User) userDetailsService.loadUserByUsername(username);
        model.addAttribute("user", user);
        model.addAttribute("blog", blogService.getBlogById(id));

        return ResultUtil.view("blog-edit", "blogModel", model);
    }

    @PostMapping("/publishBlog")
    public ModelAndView publishBlog(@RequestParam("id") Long id,
                                    @RequestParam("title") String title,
                                    @RequestParam("summary") String summary,
                                    @RequestParam("content-editormd-markdown-doc") String content,
                                    @RequestParam("content-editormd-html-code") String htmlContent,
                                    HttpServletRequest request) {

        if (id == null) {
            Blog blog = new Blog();
            blog.setTitle(title);
            blog.setSummary(summary);
            blog.setContent(content);
            blog.setHtmlContent(htmlContent);
            request.getSession().setAttribute("blog", blog);
        } else {
            Blog blog = blogService.getBlogById(id);
            blog.setTitle(title);
            blog.setSummary(summary);
            blog.setContent(content);
            blog.setHtmlContent(htmlContent);
            request.getSession().setAttribute("blog", blog);
        }
        return ResultUtil.view("tag-catalog");
    }

    @RequestMapping("/blog/{id}")
    public ModelAndView showBlog(@PathVariable("id") Long id,
                                 Model model,
                                 HttpServletRequest request) {

        Blog blog = blogService.getBlogById(id);
        model.addAttribute("blog", blog);
        return ResultUtil.view("article", "blogModel", model);
    }

    @RequestMapping("/delete/{id}")
    public ModelAndView deleteBlog(@PathVariable("id") Long id) {
        blogService.deleteBlog(id);
        return ResultUtil.redirect("/");
    }

    @PostMapping("/submit")
    public ModelAndView submitBlog(@RequestParam("tags") String tags,
                                   @RequestParam("catalog") String catalog,
                                   @RequestParam("category") String category,
                                   @RequestParam(value = "image", defaultValue = "") String image,
                                   HttpServletRequest request) {
        Blog blog = (Blog) request.getSession().getAttribute("blog");
        request.getSession().removeAttribute("blog");
        if (blog.getId() == null) {
            blog.setUser((User) userDetailsService.loadUserByUsername(SecurityUtil.getCurrentUsername()));
            blog.setCreateTime(new Date());
            blog.setTags(tags);
            blog.setCatalog(catalog);
            blog.setCategory(category);
            blog.setImage(image);
            blogService.saveBlog(blog);
        } else {
            Blog originBlog = blogService.getBlogById(blog.getId());
            originBlog.setTags(tags);
            originBlog.setCatalog(catalog);
            originBlog.setCategory(category);
            originBlog.setImage(image);
            blogService.saveBlog(originBlog);
        }
        return ResultUtil.redirect("/");
    }
}
