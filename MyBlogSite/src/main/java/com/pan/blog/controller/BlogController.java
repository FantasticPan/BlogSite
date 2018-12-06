package com.pan.blog.controller;

import com.pan.blog.entity.Blog;
import com.pan.blog.entity.User;
import com.pan.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
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

        return new ModelAndView("blog-edit", "blogModel", model);
    }

    @PostMapping("/publishBlog")
    public String publishBlog(@RequestParam("title") String title,
                              @RequestParam("summary") String summary,
                              @RequestParam("content-editormd-markdown-doc") String content,
                              @RequestParam("content-editormd-html-code") String htmlContent,
                              HttpServletRequest request) {

        Blog blog = new Blog();
        blog.setTitle(title);
        blog.setSummary(summary);
        blog.setContent(content);
        blog.setHtmlContent(htmlContent);

        request.getSession().setAttribute("blog", blog);
        return "tag-catalog";
    }

    @RequestMapping("/blog/{id}")
    public ModelAndView showBlog(@PathVariable("id") Long id,
                                 Model model,
                                 HttpServletRequest request) {

        Blog blog = blogService.getBlogById(id);
        model.addAttribute("blog", blog);
        return new ModelAndView("article", "blogModel", model);
    }

    @RequestMapping("/submit")
    public String showBlog(@RequestParam("tags") String tags,
                           @RequestParam("catalog") String catalog,
                           HttpServletRequest request) {
        Blog blog = (Blog) request.getSession().getAttribute("blog");
        request.getSession().removeAttribute("blog");
        blog.setUser((User) userDetailsService.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName()));
        blog.setCreateTime(new Date());
        blogService.saveBlog(blog);
        return "redirect:/";
    }
}
