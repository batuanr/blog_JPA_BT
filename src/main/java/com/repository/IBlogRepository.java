package com.repository;

import com.model.Blog;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IBlogRepository {
    List<Blog> findAll();

    Blog findById(int id);

    void save(Blog blog);

    void edit(Blog blog);

    void delete(int id);
}
