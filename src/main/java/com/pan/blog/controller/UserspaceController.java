package com.pan.blog.controller;

import com.pan.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by FantasticPan on 2018/11/23.
 */
@Controller
@RequestMapping("/u")
public class UserspaceController {

    @Autowired
    private UserService userService;

    //@Qualifier("userServiceImpl")
    //@Autowired
    //private UserDetailsService userDetailsService;
    //@Value("${file.server.url}")
    //private String fileServerUrl;

    @GetMapping("/{username}")
    public String userSpace(@PathVariable("username") String username) {
        System.out.println("username" + username);
        return "u";
    }

    //@GetMapping("/{username}/profile")
    //@PreAuthorize("authentication.name.equals(#username)")
    //public ModelAndView profile(@PathVariable("username") String username, Model model) {
    //    User user = (User) userDetailsService.loadUserByUsername(username);
    //    model.addAttribute("user", user);
    //    return new ModelAndView("/userspace/profile", "userModel", model);
    //}

    /**
     * 保存个人设置
     *
     * @return
     */
    //@PostMapping("/{username}/profile")
    //@PreAuthorize("authentication.name.equals(#username)")
    //public String saveProfile(@PathVariable("username") String username, User user) {
    //    User originalUser = userService.getUserById(user.getId());
    //    originalUser.setEmail(user.getEmail());
    //    originalUser.setName(user.getName());
    //
    //    // 判断密码是否做了变更
    //    String rawPassword = originalUser.getPassword();
    //    PasswordEncoder encoder = new BCryptPasswordEncoder();
    //    String encodePasswd = encoder.encode(user.getPassword());
    //    boolean isMatch = encoder.matches(rawPassword, encodePasswd);
    //    if (!isMatch) {
    //        originalUser.setEncodePassword(user.getPassword());
    //    }
    //    userService.saveOrUpdateUser(originalUser);
    //    return "redirect:/u/" + username + "/profile";
    //}

    /**
     * 获取编辑头像的界面
     * @param username
     * @param model
     * @return
     */
    //@GetMapping("/{username}/avatar")
    //@PreAuthorize("authentication.name.equals(#username)")
    //public ModelAndView avatar(@PathVariable("username") String username, Model model) {
    //    User user = (User)userDetailsService.loadUserByUsername(username);
    //    model.addAttribute("user", user);
    //    return new ModelAndView("/userspace/avatar", "userModel", model);
    //}

    /**
     * 保存头像
     * @return
     */
    //@PostMapping("/{username}/avatar")
    //@PreAuthorize("authentication.name.equals(#username)")
    //public ResponseEntity<Response> saveAvatar(@PathVariable("username") String username, @RequestBody User user) {
    //    String avatarUrl = user.getAvatar();
    //    User originalUser = userService.getUserById(user.getId());
    //    originalUser.setAvatar(avatarUrl);
    //    userService.saveOrUpdateUser(originalUser);
    //    return ResponseEntity.ok().body(new Response(true, "处理成功", avatarUrl));
    //}

    @GetMapping("/{username}/blogs")
    public String listBlogsByOrder(@PathVariable("username") String username,
                                   @RequestParam(value = "order", required = false, defaultValue = "new") String order,
                                   @RequestParam(value = "category", required = false) Long category,
                                   @RequestParam(value = "keyword", required = false) String keyword) {
        if (category != null) {
            System.out.print("category:" + category);
            System.out.print("selflink:" + "redirect:/u/" + username + "/blogs?category=" + category);
            return "/u";
        } else if (keyword != null && !keyword.isEmpty()) {
            System.out.print("keyword:" + keyword);
            System.out.print("selflink:" + "redirect:/u/" + username + "/blogs?keyword=" + keyword);
            return "/u";
        }
        System.out.print("order:" + order);
        System.out.print("selflink:" + "redirect:/u/" + username + "/blogs?order=" + order);
        return "/u";
    }

    @GetMapping("/{username}/blogs/{id}")
    public String listBlogsByOrder(@PathVariable("id") Long id) {
        System.out.print("blogId:" + id);
        return "/blog";
    }


    @GetMapping("/{username}/blogs/edit")
    public String editBlog() {
        return "/blogedit";
    }
}
