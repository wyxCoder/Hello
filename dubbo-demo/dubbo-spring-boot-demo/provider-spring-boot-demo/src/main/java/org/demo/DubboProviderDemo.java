package org.demo;

import org.springframework.boot.builder.SpringApplicationBuilder;

import java.util.concurrent.TimeUnit;

/**
 * startup
 * @create 2018-05-26 下午11:40
 **/
public class DubboProviderDemo {

    public static void main(String[] args) throws InterruptedException {

        new SpringApplicationBuilder(DubboProviderDemo.class)
                // 非 Web 应用
                .web(false)
                .run(args);
        TimeUnit.SECONDS.sleep(100L);
    }
}
