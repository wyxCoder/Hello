package org.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.TimeUnit;

/**
 * @create 2018-05-27 下午6:19
 **/
@SpringBootApplication
public class DubboConsumerDemo {

    public static void main(String[] args) throws InterruptedException {

        SpringApplication.run(DubboConsumerDemo.class, args);
        TimeUnit.SECONDS.sleep(100L);

    }

}
