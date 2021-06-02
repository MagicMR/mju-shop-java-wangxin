package com.hellowx.controller;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA.
 * 首页跳转
 * @author MagicMushroom
 * @date 2021/5/21
 */
@RestController
public class PageController {

    @RequestMapping("/test")
    public String page(){
        return "Test";
    }
}
