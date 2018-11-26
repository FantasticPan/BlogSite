package com.pan.blog.controller;

import com.pan.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 主页控制器
 * Created by FantasticPan on 2018/11/23.
 */
@Controller
@RequestMapping("/blogs")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @GetMapping
    public String listBlogs(@RequestParam(value = "order", required = false, defaultValue = "new") String order,
                            @RequestParam(value = "keyword", required = false, defaultValue = "") String keyword) {
        System.out.println("order:" + order + ";keyword:" + keyword);
        return "redirect:/index?order=" + order + "&keyword=" + keyword;
    }

    //@GetMapping
    //public String listEsBlogs(@RequestParam(value = "async", required = false) boolean async,
    //                          @RequestParam(value = "order", required = false, defaultValue = "new") String order,
    //                          @RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
    //                          @RequestParam(value = "pageIndex", required = false, defaultValue = "0") int pageIndex,
    //                          @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
    //                          Model model) {
    //    Page<Blog> page = null;
    //    List<Blog> list = null;
    //    boolean isEmpty = true;
    //
    //    PageRequest pageable;
    //    try {
    //        Sort sort;
    //        if (order.equals("hot")) {
    //            sort = new Sort(Sort.Direction.DESC, "readSize", "commentSize", "voteSize", "createTime");
    //            pageable = new PageRequest(pageIndex, pageSize, sort);
    //            page = blogService.listBlogsByTitleVoteAndSort(keyword, pageable);
    //        } else if (order.equals("new")) {
    //            sort = new Sort(Sort.Direction.DESC, new String[]{"createTime"});
    //            pageable = new PageRequest(pageIndex, pageSize, sort);
    //            page = blogService.listBlogsByTitleVote(keyword, pageable);
    //        }
    //
    //        isEmpty = false;
    //    } catch (Exception var12) {
    //        pageable = new PageRequest(pageIndex, pageSize);
    //        page = blogService.listBlogs(pageable);
    //    }
    //
    //    list = page.getContent();
    //    model.addAttribute("order", order);
    //    model.addAttribute("keyword", keyword);
    //    model.addAttribute("page", page);
    //    model.addAttribute("blogList", list);
    //    if (!async && !isEmpty) {
    //        List<Blog> newest = this.blogService.listTop5NewestBlogs();
    //        model.addAttribute("newest", newest);
    //        List<Blog> hotest = this.blogService.listTop5HotestBlogs();
    //        model.addAttribute("hotest", hotest);
    //    }
    //
    //    return async ? "index :: #mainContainerRepleace" : "index";
    //}
}
