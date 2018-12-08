package com.pan.blog.controller;

import com.pan.blog.entity.Blog;
import com.pan.blog.entity.Tag;
import com.pan.blog.entity.User;
import com.pan.blog.service.BlogService;
import com.pan.blog.service.TagService;
import com.pan.blog.util.DateUtils;
import com.pan.blog.util.ResultUtils;
import com.pan.blog.util.SecurityUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    @Autowired
    private TagService tagService;

    @GetMapping({"/{username}/blog/edit"})
    public ModelAndView getBlogEditPage(@PathVariable("username") String username, Model model) {
        User user = (User) userDetailsService.loadUserByUsername(username);
        model.addAttribute("user", user);
        model.addAttribute("blog", new Blog(null, null, null, null, null, null));

        return ResultUtils.view("blog-edit", "blogModel", model);
    }

    @GetMapping({"/{username}/blog/edit/{id}"})
    public ModelAndView getBlogModifyPage(@PathVariable("username") String username,
                                          @PathVariable("id") Long id,
                                          Model model) {

        User user = (User) userDetailsService.loadUserByUsername(username);
        model.addAttribute("user", user);
        model.addAttribute("blog", blogService.getBlogById(id));

        return ResultUtils.view("blog-edit", "blogModel", model);
    }

    @PostMapping("/publishBlog")
    public ModelAndView publishBlog(@RequestParam("id") Long id,
                                    @RequestParam("title") String title,
                                    @RequestParam("summary") String summary,
                                    @RequestParam("content-editormd-markdown-doc") String content,
                                    @RequestParam("content-editormd-html-code") String htmlContent,
                                    HttpServletRequest request,
                                    Model model) {

        Blog blog;

        if (id == null) {
            blog = new Blog();
            blog.setTitle(title);
            blog.setSummary(summary);
            blog.setContent(content);
            blog.setHtmlContent(htmlContent);
            request.getSession().setAttribute("blog", blog);
        } else {
            blog = blogService.getBlogById(id);
            blog.setTitle(title);
            blog.setSummary(summary);
            blog.setContent(content);
            blog.setHtmlContent(htmlContent);
            request.getSession().setAttribute("blog", blog);
        }

        model.addAttribute("tags", StringUtils.join(blog.getTags(), ","));

        return ResultUtils.view("tag-catalog", "blogModel", model);
    }

    @GetMapping("/blog/{catalog}/{id}")
    public ModelAndView showBlog(@PathVariable("id") Long id,
                                 @PathVariable("catalog") String catalog,
                                 Model model,
                                 HttpServletRequest request) {

        Blog blog = blogService.getBlogById(id);
        List<Tag> tags = tagService.findTagsByUser(blog.getUser());
        model.addAttribute("blog", blog);
        model.addAttribute("catalog", catalog);
        model.addAttribute("tags", tags);

        return ResultUtils.view("article", "blogModel", model);
    }

    @RequestMapping("/delete/{id}")
    public ModelAndView deleteBlog(@PathVariable("id") Long id) {
        blogService.deleteBlog(id);
        return ResultUtils.redirect("/");
    }

    @PostMapping("/submit")
    public ModelAndView submitBlog(@RequestParam(value = "tags", defaultValue = "学习,总结") String tags,
                                   @RequestParam("catalog") String catalog,
                                   @RequestParam("category") String category,
                                   @RequestParam(value = "image", defaultValue = "") String image,
                                   HttpServletRequest request) {

        Blog blog = (Blog) request.getSession().getAttribute("blog");
        List<Tag> tagList = new ArrayList<>();
        request.getSession().removeAttribute("blog");
        if (blog.getId() == null) {
            for (String s : tags.split(",")) {
                tagList.add(new Tag(s));
            }

            blog.setUser((User) userDetailsService.loadUserByUsername(SecurityUtils.getCurrentUsername()));
            blog.setCreateTime(DateUtils.dateTimeToDateString(new Date()));
            blog.setTags(tagList);
            blog.setCatalog(catalog);
            blog.setCategory(category);
            blog.setImage(image);
            blogService.saveBlog(blog);
        } else {
            Blog originBlog = blogService.getBlogById(blog.getId());

            for (String s : tags.split(",")) {
                tagList.add(new Tag(s));
            }

            originBlog.setTags(tagList);
            originBlog.setCatalog(catalog);
            originBlog.setCategory(category);
            originBlog.setImage(image);
            originBlog.setTitle(blog.getTitle());
            originBlog.setSummary(blog.getSummary());
            originBlog.setContent(blog.getContent());
            originBlog.setHtmlContent(blog.getHtmlContent());
            blogService.saveBlog(originBlog);
        }
        return ResultUtils.redirect("/");
    }
}
