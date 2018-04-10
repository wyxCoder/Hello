package org.demo.annotation.bean;

import com.google.common.base.Charsets;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 使用获取到的参数
 *
 * @author yuxuan
 * @create 2018-04-10 下午8:28
 **/
@Component
public class ExampleBean {

    @Autowired
    AnnotatedBean annotatedBean;

    @PostConstruct
    public void main() throws IOException {
        System.out.println("AnnotationApplication Demo. Input any key except quit to print the values. Input quit to exit.");
        while (true) {
            System.out.print("> ");
            String input = new BufferedReader(new InputStreamReader(System.in, Charsets.UTF_8)).readLine();
            if (!Strings.isNullOrEmpty(input) && input.trim().equalsIgnoreCase("quit")) {
                System.exit(0);
            }

            System.out.println(annotatedBean.toString());
        }
    }

}
