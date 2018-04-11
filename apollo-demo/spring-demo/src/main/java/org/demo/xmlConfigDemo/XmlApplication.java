package org.demo.xmlConfigDemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * XML方式获取配置
 * 支持实时更新（热发布)
 * @author yuxuan
 * @create 2018-04-10 下午11:28
 **/
@SpringBootApplication
@ImportResource(locations={"classpath:spring.xml"})
public class XmlApplication {
    public static void main(String[] args) {
        SpringApplication.run(XmlApplication.class, args);
    }
}
