package com.lukaszdutka.infogaincharterinterviewtask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration(proxyBeanMethods = false)
public class TestInfogainCharterInterviewTaskApplication {

    public static void main(String[] args) {
        SpringApplication.from(InfogainCharterInterviewTaskApplication::main).with(TestInfogainCharterInterviewTaskApplication.class).run(args);
    }

}
