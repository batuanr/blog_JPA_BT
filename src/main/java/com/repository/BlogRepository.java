package com.repository;

import com.model.Blog;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
@Transactional
@Service
public class BlogRepository implements IBlogRepository{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Blog> findAll() {
        String sql = "select b from Blog b";
        TypedQuery<Blog> query = entityManager.createQuery(sql, Blog.class);
        return query.getResultList();
    }

    @Override
    public Blog findById(int id) {
        String sql = "select b from Blog b where b.id = :id";
        TypedQuery<Blog> query = entityManager.createQuery(sql, Blog.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public void save(Blog blog) {
        String sql = "Call Insert_Blog(:intitle, :incontent)";
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("intitle", blog.getTitle());
        query.setParameter("incontent", blog.getContent());
        query.executeUpdate();
    }

    @Override
    public void edit(Blog blog) {
        String sql = "Call Edit_Blog(?1, ?2, ?3)";
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter(1, blog.getId());
        query.setParameter(2, blog.getTitle());
        query.setParameter(3, blog.getContent());
        query.executeUpdate();
    }

    @Override
    public void delete(int id) {
        String sql = "delete from Blog as b where b.id = :id";
        Query query = entityManager.createQuery(sql);
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
