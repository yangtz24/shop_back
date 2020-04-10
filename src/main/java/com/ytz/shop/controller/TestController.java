package com.ytz.shop.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: TestController
 * @Description: 测试Controller
 * @author: yangtianzeng
 * @date: 2020/4/8 13:35
 */
@RestController
@RequestMapping("test")
public class TestController {

    @GetMapping("")
    public String get() {
        return "Hello Spring Security";
    }
}
