package com.example.niuke.controller;

import com.example.niuke.util.CommunityUtil;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String hello(){
        return "Hello Spring boot !";
    }

    @RequestMapping("/http")
    public void http(HttpServletRequest request, HttpServletResponse response) {
        //获取请求数据
        System.out.println(request.getMethod()); //打印请求方法
        System.out.println(request.getServletPath()); //打印请求路径
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {//依次打印所有的请求头
            String name = headerNames.nextElement();
            String value = request.getHeader(name);
            System.out.println(name + "=" + value);
        }
        System.out.println(request.getParameter("message"));//获取请求携带的参数

        //返回响应数据
        response.setContentType("text/html;charset=utf-8"); //设置返回的响应数据类型
        try {
            PrintWriter writer = response.getWriter();
            writer.write("<h1>牛客网</h1>"); //设置浏览器显示的文本
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(path = "/students", method = RequestMethod.GET)
    public String getStudents(
            @RequestParam(name = "current", required = false, defaultValue = "1") int current,
            @RequestParam(name = "limit", required = false, defaultValue = "10") int limit){
        System.out.println(current);
        System.out.println(limit);
        return "some students";
    }

    @RequestMapping(path = "/students2/current={current}/limit={limit}" , method = RequestMethod.GET)
    public String getStudents2(
            @PathVariable(name = "current", required = false) int current,
            @PathVariable(name = "limit", required = false) int limit) {
        System.out.println(current);
        System.out.println(limit);
        return "students2 list";
    }

    @RequestMapping(path = "/studentInfo", method = RequestMethod.POST)
    public String getStudentInfo(String name, int age) {
        System.out.println(name);
        System.out.println(age);
        return "提交成功";
    }

    @RequestMapping(path = "/teacherInfo", method = RequestMethod.GET)
    public ModelAndView getTeacherInfo() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("name", "张三");
        mv.addObject("age", "30");
        mv.setViewName("/demo/teacherInfo");
        return mv;
    }

    @RequestMapping(path = "/cookie/set", method = RequestMethod.GET)
    public String setCookie(HttpServletResponse response) {
        // 创建cookie
        Cookie cookie = new Cookie("code", CommunityUtil.generateUUID());
        // 设置cookie生效的范围
        cookie.setPath("/community/cookie");
        // 设置cookie的生存时间
        cookie.setMaxAge(60 * 5);
        // 发送cookie
        response.addCookie(cookie);
        return "set cookie";
    }

    @GetMapping("/cookie/get")
    @ResponseBody
    public String getCookie(@CookieValue("code") String code) {
        System.out.println(code);
        return "get cookie" + "->" + code;
    }

    // session示例
    @GetMapping("/session/set")
    @ResponseBody
    public String setSession(HttpSession session) {
        session.setAttribute("id", 1);
        session.setAttribute("name", "test");
        return "set session";
    }

    @GetMapping("/session/get")
    @ResponseBody
    public String getSession(HttpSession session) {
        System.out.println(session.getAttribute("id"));
        System.out.println(session.getAttribute("name"));
        return "get session";
    }
}
