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
        return postDao.selectById(id);
    }

    @Override
    public PageBean<Post> selectAll(Post post,int currentPage, int pageSize) {
        int i =  postDao.selectByPrimaryKey(post).size();
        PageHelper.startPage(currentPage, pageSize);
        List<Post> posts = postDao.selectByPrimaryKey(post);
        PageBean<Post> pageData = new PageBean<>(currentPage, pageSize, posts.size());
        pageData.setItems(posts);
        pageData.setTotalNum(i);
        return pageData;
    }

    @Override
    public int updateByPrimaryKey(Post post) {
        return postDao.updateByPrimaryKey(post);
    }
    @Override
    public List<Post> select(){
        return postDao.select();
    }
}
