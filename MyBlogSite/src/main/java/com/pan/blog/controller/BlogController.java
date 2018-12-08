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
import java.util.*;

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

    @GetMapping("/blog/{id}")
    public ModelAndView showBlog(@PathVariable("id") Long id,
                                 Model model,
                                 HttpServletRequest request) {

        Blog blog = blogService.getBlogById(id);
        List<Tag> blogTags = tagService.findTagsByUser(blog.getUser());
        List<Tag> tags = tagService.findAllTags();
        Set<String> tagsList = new HashSet<>();
        for (Tag tag : tags) {
            tagsList.add(tag.getTagName());
        }

        model.addAttribute("blog", blog);
        model.addAttribute("tags", tagsList);
        model.addAttribute("blogTags", blogTags);
        model.addAttribute("blogNum", blogService.blogNum());
        model.addAttribute("tagsSize", tagsList.size());

        return ResultUtils.view("article", "blogModel", model);
    }

    @GetMapping("/catalog/{catalog}")
    public ModelAndView showBlogByCatalog(@PathVariable("catalog") String catalog,
                                          Model model) {

        List<Blog> blogList = blogService.findBlogByCatalog(catalog);
        List<Tag> tags = tagService.findAllTags();
        Set<String> tagsList = new HashSet<>();
        for (Tag tag : tags) {
            tagsList.add(tag.getTagName());
        }

        model.addAttribute("tags", tagsList);
        model.addAttribute("blogNum", blogService.blogNum());
        model.addAttribute("tagsSize", tagsList.size());
        model.addAttribute("type", "博客分类");
        model.addAttribute("blogList", blogList);
        model.addAttribute("name", catalog);

        return ResultUtils.view("show-catalog-type", "blogModel", model);
    }

    @GetMapping("/tag/{tag}")
    public ModelAndView showBlogByTag(@PathVariable("tag") String tag,
                                      Model model) {

        List<Tag> tagList1 = tagService.findTagsByTagName(tag);
        List<Blog> blogList = new ArrayList<>();
        for (Tag tag1 : tagList1) {
            blogList.add(blogService.findBlogByTag(tag1));
        }

        List<Tag> tags = tagService.findAllTags();
        Set<String> tagsList = new HashSet<>();
        for (Tag tag2 : tags) {
            tagsList.add(tag2.getTagName());
        }
        model.addAttribute("tags", tagsList);
        model.addAttribute("blogNum", blogService.blogNum());
        model.addAttribute("tagsSize", tagsList.size());

        model.addAttribute("type", "博客标签");
        model.addAttribute("blogList", blogList);
        model.addAttribute("name", tag);

        return ResultUtils.view("show-catalog-type", "blogModel", model);
    }

    @RequestMapping("/delete/{id}")
    public ModelAndView deleteBlog(@PathVariable("id") Long id) {
        blogService.deleteBlog(id);
        return ResultUtils.redirect("/");
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
        List<String> tags = new ArrayList<>();

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

        for (Tag tag : blog.getTags()) {
            tags.add(tag.getTagName());
        }
        model.addAttribute("tags", StringUtils.join(tags, ","));

        return ResultUtils.view("tag-catalog", "blogModel", model);
    }

    @PostMapping("/submit")
    public ModelAndView submitBlog(@RequestParam(value = "tags", defaultValue = "学习") String tags,
                                   @RequestParam("catalog") String catalog,
                                   @RequestParam("category") String category,
                                   @RequestParam(value = "image", defaultValue = "") String image,
                                   HttpServletRequest request) {

        Blog blog = (Blog) request.getSession().getAttribute("blog");
        List<Tag> tagList = new ArrayList<>();
        User user = (User) userDetailsService.loadUserByUsername(SecurityUtils.getCurrentUsername());
        request.getSession().removeAttribute("blog");
        if (blog.getId() == null) {
            Tag tag;
            for (String s : tags.split(",")) {
                tagList.add(tag = new Tag(s));
                tag.setUser(user);
                tagService.saveTag(tag);
            }

            blog.setUser(user);
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
