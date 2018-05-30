package org.demo.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import org.demo.service.DemoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author
 * @create 2018-05-27 下午6:20
 **/
@RestController
public class DemoConsumerController {

    @Reference(url = "dubbo://127.0.0.1:20880")
    private DemoService demoService;

    @RequestMapping("/sayHello")
    public String sayHello(@RequestParam String name) {
        return demoService.sayHello(name);
    }

    @RequestMapping("/qq")
    public String qq() {
        return new Date().toString();
    }

}
