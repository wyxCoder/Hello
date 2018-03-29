package org.demo;

/**
 *
 * @author
 * @create 2018-03-29 下午11:31
 **/
public class Main {
    public static void main(String args[]) {
        Config config = ConfigService.getAppConfig(); //config instance is singleton for each namespace and is never null
        String someKey = "someKeyFromDefaultNamespace";
        String someDefaultValue = "someDefaultValueForTheKey";
        String value = config.getProperty(someKey, someDefaultValue);
        System.out.println("get value : " + value);
    }
}
