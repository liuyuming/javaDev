package com.thruman.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author niexiang
 * @Description
 * @create 2018-03-12 19:03
 **/
@RestController
public class TestController {

    @RequestMapping("/")
    public String sayHello() {
        return "Hello,World!";
    }

}