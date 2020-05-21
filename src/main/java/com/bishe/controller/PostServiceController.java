package com.bishe.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bishe.model.Employee;
import com.bishe.model.Post;
import com.bishe.service.PostService;
import com.utils.BeanUtils;
import com.utils.PageBean;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/post")
public class PostServiceController {

    @Autowired
    PostService postService;

    @ApiOperation(value = "获取岗位列表")
    @PostMapping("/selectAll")
    public Object selectAll(@RequestBody Map paramsMap,int currentPage, int pageSize) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        Post post = (Post) BeanUtils.mapToObject(paramsMap, Post.class);
        PageBean<Post> pageData = postService.selectAll(post,currentPage, pageSize);

        map.put("data", pageData);

        return map;
    }

    @ApiOperation(value = "修改单个岗位")
    @PostMapping(value = "/update", consumes = "application/json")
    public Object update(@RequestBody Map paramsMap) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        Post post = (Post) BeanUtils.mapToObject(paramsMap, Post.class);
        if (null == post.getName()) {
            map.put("data", "失败");
            return map;
        }
        int n = postService.updateByPrimaryKey(post);
        if (n > 0) {
            map.put("data", "成功");
            return map;
        } else {
            map.put("data", "失败");
            return map;
        }
    }

    @ApiOperation(value = "删除单个岗位")
    @PostMapping(value = "/delete", consumes = "application/json")
    public Object delete(@RequestBody int[] ids) {
        int n = 0;
        for (int i : ids) {
            n = postService.deleteByPrimaryKey(i);
        }
        Map<String, Object> map = new HashMap<String, Object>();
        if (n > 0) {
            map.put("data", "成功");
            return map;
        } else {
            map.put("data", "失败");
            return map;
        }
    }

    @ApiOperation(value = "增加岗位")
    @PostMapping(value = "/insert", consumes = "application/json")
    public Object insert(@RequestBody Map paramsMap) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        Post post = (Post) BeanUtils.mapToObject(paramsMap, Post.class);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        post.setTime(new Date());
        if (post.getName() == null) {
            map.put("data", "失败");
            return map;
        }
        int n = postService.insert(post);

        if (n > 0) {
            map.put("data", "成功");
            return map;
        } else {
            map.put("data", "失败");
            return map;
        }
    }
}
