package com.cloud.demo.controller;

import com.cloud.demo.service.SchedualServiceHi;
import com.netflix.discovery.converters.Auto;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class HiController {

    // feign  演示

    @Value("${service}")
    private  String service; //本项目作为config client，可以在config server中获取 参数注入
    @Autowired
    public SchedualServiceHi schedualServiceHi;
    @RequestMapping(value = "/hi" ,method = RequestMethod.GET)
    public  String sayHi(@RequestParam String name){
        return  schedualServiceHi.sayHiFromClientOne(name)+"     value：   "+service;
    }


    //  熔断  演示

    @Autowired
    RestTemplate restTemplate ;
    @HystrixCommand(fallbackMethod = "error")
    @RequestMapping("/hello")
    public  String hello(String name){
//        return restTemplate.getForObject("http://localhost:8762/hello?name="+name,String.class);
        //这里填错误的地址 ，使产生错误 ，发生熔断
        return restTemplate.getForObject("http://localhost:8760/hello?name="+name,String.class);


    }
    public  String error(String name,Throwable throwable){
        return  "发生了熔断error ,模块不可用 "+throwable.getMessage();
    }
}
