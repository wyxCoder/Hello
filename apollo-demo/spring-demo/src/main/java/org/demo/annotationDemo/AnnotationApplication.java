package org.demo.annotationDemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 注解的方式获取配置
 * @author yuxuan
 * @create 2018-04-10 下午8:21
 **/
@SpringBootApplication
public class AnnotationApplication {
    public static void main(String[] args) {
        SpringApplication.run(AnnotationApplication.class, args);
    }
}
