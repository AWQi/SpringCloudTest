package com.cloud.hystrix.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HystrixHelloService {
    @Autowired
    private  HelloService helloService;
    @HystrixCommand(fallbackMethod = "error")
    public  String hello(){
            return  helloService.hello();
    }
    public  String error(){
        return  "error";
    }
}
