package com.example.niuke.controller;

import com.example.niuke.entity.DiscussPost;
import com.example.niuke.entity.Page;
import com.example.niuke.entity.User;
import com.example.niuke.service.DiscussPostService;
import com.example.niuke.service.LikeService;
import com.example.niuke.service.UserService;
import com.example.niuke.util.CommunityConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController implements CommunityConstant {
    @Autowired
    private DiscussPostService discussPostService;
    @Autowired
    private UserService userService;
    @Autowired
    private LikeService likeService;

    @GetMapping("/index")
    public String getIndexPage(Model model, Page page, @RequestParam(defaultValue = "0", name = "orderMode") int orderMode){
        // 方法调用前 SpringMVC会自动实例化Model和Page 并将Page注入到Model
        // 所以在thymeleaf中可以直接访问Page对象中的数据 而不用在方法中addAttribute
        page.setRows(discussPostService.findDiscussPostRows(0));
        page.setPath("/index?orderMode=" + orderMode);
        List<DiscussPost> list = discussPostService.findDiscussPosts(0, page.getOffset(), page.getLimit(), orderMode);
        List<Map<String, Object>> discussPosts = new ArrayList<>();
        if(list != null){
            for(DiscussPost post : list){
                User user = userService.findUserById(post.getUserId());
                Map<String, Object> map =  new HashMap<>();
                map.put("post", post);
                map.put("user",user);
                // 获取帖子点赞数量
                map.put("likeCount", likeService.findEntityLikeCount(ENTITY_TYPE_POST, post.getId()));
                discussPosts.add(map);
            }
        }
        model.addAttribute("discussPosts", discussPosts);
        model.addAttribute("orderMode", orderMode);
        return "/index";
    }

    @RequestMapping(path = "/error", method = RequestMethod.GET)
    public String getErrorPage() {
        return "/error/500";
    }

    @RequestMapping(path = "/denied", method = RequestMethod.GET)
    public String getDeniedPage() {
        return "/error/404";
    }

}
