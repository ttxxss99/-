package com.salary.contorller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class PageContorller {

    @RequestMapping({"/","/index"})
    public String index(){
        return "index";
    }
}
