package com.bishe.service.Impl;

import com.bishe.dao.PostDao;
import com.bishe.model.Employee;
import com.bishe.model.Post;
import com.bishe.service.PostService;
import com.github.pagehelper.PageHelper;
import com.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    PostDao postDao;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return postDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Post post) {
        post.setLogicDel(1);
        return postDao.insert(post);
    }

    @Override
    public Post selectByPrimaryKey(Integer id) {
        return postDao.selectByPrimaryKey(id);
    }

    @Override
    public PageBean<Post> selectAll(int currentPage, int pageSize) {
        PageHelper.startPage(currentPage, pageSize);
        List<Post> posts = postDao.selectAll();
        PageBean<Post> pageData = new PageBean<>(currentPage, pageSize, posts.size());
        pageData.setItems(posts);
        return pageData;
    }

    @Override
    public int updateByPrimaryKey(Post post) {
        return postDao.updateByPrimaryKey(post);
    }
}
