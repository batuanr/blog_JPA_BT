package com.controller;

import com.model.Blog;
import com.service.IBlogService;
import org.hibernate.internal.util.MarkerObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/blog")
public class BlogController {
    @Autowired
    IBlogService blogService;

    @GetMapping
    public ModelAndView getHome(){
        List<Blog> blogList = blogService.findAll();
        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("list", blogList);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showFormCreate(){
        ModelAndView modelAndView = new ModelAndView("create", "blog", new Blog());
        return modelAndView;
    }
    @PostMapping("/create")
    public String create(@ModelAttribute Blog blog){
        blogService.save(blog);
        return "redirect:/blog";
    }
    @GetMapping("/read/{id}")
    public ModelAndView read(@PathVariable int id){
        Blog blog = blogService.findById(id);
        ModelAndView modelAndView = new ModelAndView("content", "blog", blog);
        return modelAndView;
    }
    @GetMapping("/edit/{id}")
    public ModelAndView editForm(@PathVariable int id){
        Blog blog = blogService.findById(id);
        ModelAndView modelAndView = new ModelAndView("edit", "blog", blog);
        return modelAndView;
    }
    @PostMapping("/edit/{id}")
    public String update(@ModelAttribute Blog blog){
        blogService.edit(blog);
        return "redirect:/blog";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id){
        blogService.delete(id);
        return "redirect:/blog";
    }
}
