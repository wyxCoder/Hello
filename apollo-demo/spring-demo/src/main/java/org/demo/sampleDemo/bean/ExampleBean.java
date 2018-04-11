package org.demo.sampleDemo.bean;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.model.ConfigChange;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfig;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfigChangeListener;
import com.google.common.base.Charsets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger logger = LoggerFactory.getLogger(ExampleBean.class);
    private String DEFAULT_VALUE = "undefined";

    @ApolloConfig
    private Config config; //inject config for namespace application
    @ApolloConfig("TEST1.apollo")
    private Config anotherConfig; //inject config for namespace application


    @ApolloConfigChangeListener
    private void someOnChange(ConfigChangeEvent changeEvent) {
        logger.info("Changes for namespace {}", changeEvent.getNamespace());
        for (String key : changeEvent.changedKeys()) {
            ConfigChange change = changeEvent.getChange(key);
            logger.info("Change - key: {}, oldValue: {}, newValue: {}, changeType: {}",
                    change.getPropertyName(), change.getOldValue(), change.getNewValue(),
                    change.getChangeType());
        }
    }

    @ApolloConfigChangeListener("TEST1.apollo")
    private void anotherOnChange(ConfigChangeEvent changeEvent) {
        logger.info("Changes for namespace {}", changeEvent.getNamespace());
        for (String key : changeEvent.changedKeys()) {
            ConfigChange change = changeEvent.getChange(key);
            logger.info("Change - key: {}, oldValue: {}, newValue: {}, changeType: {}",
                    change.getPropertyName(), change.getOldValue(), change.getNewValue(),
                    change.getChangeType());
        }
    }

    @PostConstruct
    public void main() throws IOException {
        System.out.println(
                "Apollo Config Demo. Please input key to get the value. Input quit to exit.");
        while (true) {
            System.out.print("> ");
            String input = new BufferedReader(new InputStreamReader(System.in, Charsets.UTF_8)).readLine();
            if (input == null || input.length() == 0) {
                continue;
            }
            input = input.trim();
            if (input.equalsIgnoreCase("quit")) {
                System.exit(0);
            }
            getConfig(input);
        }
    }

    private String getConfig(String key) {
        String result = config.getProperty(key, DEFAULT_VALUE);
        if (DEFAULT_VALUE.equals(result)) {
            result = anotherConfig.getProperty(key, DEFAULT_VALUE);
        }
        System.out.println(String.format("Loading key : %s with value: %s", key, result));
        return result;
    }

}
