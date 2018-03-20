package com.thruman.controller;

import com.thruman.pojo.User;
import com.thruman.service.TestService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author niexiang
 * @Description
 * @create 2018-03-12 19:03
 **/
@RestController
public class TestController {
    @Resource
    private TestService testService;

    @RequestMapping("/")
    public String sayHello() {
        return "Hello,World!";
    }

    @GetMapping("/get")
    public String getId(Integer id) {
        return testService.getTest(id).toString();
    }

    @GetMapping("/getUserAll")
    public List<User> getUserAll() {
        return testService.getUserAll();
    }

}