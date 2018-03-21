package com.thruman.controller;

import com.thruman.service.MutilService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author niexiang
 * @Description
 * @create 2018-03-21 15:54
 **/
@RestController
public class MultiController {

    @Resource
    MutilService mutilService;

    @GetMapping("/test")
    public void test(){
        mutilService.test();



    }


}