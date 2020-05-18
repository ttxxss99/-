package com.bishe.service;

import com.bishe.model.Post;
import com.utils.PageBean;

import java.util.List;

public interface PostService {
    int deleteByPrimaryKey(Integer id);

    int insert(Post record);

    Post selectByPrimaryKey(Integer id);

    PageBean<Post> selectAll(Post post,int currentPage, int pageSize);

    int updateByPrimaryKey(Post record);

    List<Post> select();
}
