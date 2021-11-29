package com.service;

import com.model.Blog;

import java.util.List;

public interface IBlogService {
    List<Blog> findAll();

    Blog findById(int id);

    void save(Blog blog);

    void edit(Blog blog);

    void delete(int id);
}
