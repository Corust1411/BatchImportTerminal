package com.Corust1411.batch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootHelloWorldApplication {

    public static void main(String[] args) {

        SpringApplication.run(
                (Class<?>[]) new Object[] { SpringBootHelloWorldApplication.class }, args);    }
}