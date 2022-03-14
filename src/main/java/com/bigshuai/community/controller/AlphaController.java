package com.bigshuai.community.controller;

import com.bigshuai.community.service.AlphaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@Controller
@RequestMapping("/alpha")
public class AlphaController {

    @Autowired
    private AlphaService alphaService;

    @RequestMapping("/hello")
    @ResponseBody
    public String sayHello() {
        return "Hello Spring Boot";
    }

    @RequestMapping("/date")
    @ResponseBody //ResponseBody的作用其实是将java对象转为json格式的数据
    public String getDate() {
        return alphaService.find();
    }

    //测试下 http相关
    @RequestMapping("/http")
    public void http(HttpServletRequest request, HttpServletResponse response) {
        //获取数据
        //请求行
        System.out.println(request.getMethod());
        System.out.println(request.getServletPath());

        //请求头
        //迭代器
        Enumeration<String> enumeration = request.getHeaderNames();
        while (enumeration.hasMoreElements()) {
            String name = enumeration.nextElement();
            String value = request.getHeader(name);
            System.out.println(name + ": " + value);
        }
        System.out.println(request.getParameter("code"));

        //返回响应数据

        response.setContentType("text/html;charset=UTF-8");
        try (
                PrintWriter printWriter = response.getWriter();
        ) {
            printWriter.write("<h1>大帅小屋<h1>");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    //get请求 两种传参方式 传输字符长度有限 且参数明面
    //假设查询所有学生 /students ？current = 1 & limit = 20
    @RequestMapping(path = "students", method = RequestMethod.GET)
    @ResponseBody
    public String getStudents(
            @RequestParam(name = "current", required = false, defaultValue = "1") int current,
            @RequestParam(name = "limit", required = false, defaultValue = "10") int limit) {
        System.out.println(current);
        System.out.println(limit);
        return "Some Student";
    }

    // student/123 某一个学生
    @RequestMapping(path = "/student/{id}",method = RequestMethod.GET)
    @ResponseBody
    public String getStudents(@PathVariable("id") int id){
        System.out.println(id);
        return "a student";
    }

    //post请求
    @RequestMapping(path = "/student",method = RequestMethod.POST)
    @ResponseBody
    public String saveStudent(String name, int age) {
        System.out.println(name);
        System.out.println(age);
        return "success";
    }

    //响应html数据 两种办法
    @RequestMapping(path = "/teacher",method = RequestMethod.GET)
    public ModelAndView getTeacher() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("name","张三");
        mav.addObject("age",30);
        mav.setViewName("/demo/view");
        return mav;
    }

    @RequestMapping(path = "/school",method = RequestMethod.GET)
    public String getSchool(Model model) {
        model.addAttribute("name","哈工程");
        model.addAttribute("age",100);
        return "/demo/view";
    }
    //响应json数据（异步请求）
    //java对象 -> json字符串 -> js对象
    //结果将map转为json了
    @RequestMapping(path = "/emp",method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getEmp(){
        Map<String,Object> emp = new HashMap<>();
        emp.put("name","张三");
        emp.put("age",23);
        emp.put("salary",800);
        return emp;
    }
//返回多个员工
    @RequestMapping(path = "/emps",method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String, Object>> getEmps(){
        List<Map<String,Object>> list = new ArrayList<>();

        Map<String,Object> emp = new HashMap<>();
        emp.put("name","张三");
        emp.put("age",23);
        emp.put("salary",800);
        list.add(emp);

        emp = new HashMap<>();
        emp.put("name","李四");
        emp.put("age",25);
        emp.put("salary",560);
        list.add(emp);


        return list;
    }


}