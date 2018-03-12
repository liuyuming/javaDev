package com.thruman;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author niexiang
 * @Description
 * @create 2018-03-12 19:00
 **/
@SpringBootApplication
public class Api {
    public static void main(String[] args) {
        // 程序启动入口
        // 启动嵌入式的 Tomcat 并初始化 Spring 环境及其各 Spring 组件
        SpringApplication.run(Api.class,args);
    }

}