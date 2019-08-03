package com.cloud.hystrix.controller;

import com.cloud.hystrix.service.HystrixHelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @Autowired
    private HystrixHelloService hystrixHelloService;
    @RequestMapping("/hello")
    public  String hello(){
       return hystrixHelloService.hello();
    }
}
