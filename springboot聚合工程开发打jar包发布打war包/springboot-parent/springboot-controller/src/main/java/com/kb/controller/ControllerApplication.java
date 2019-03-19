package com.kb.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan( basePackages = "com.kb" )
public class ControllerApplication extends SpringBootServletInitializer {

    /**
     *  点击IDEA运行springboot的程序运行入口
     * */



    public static void main(String[] args) {
        SpringApplication.run(ControllerApplication.class, args);
    }


    /**
     *  打包发布时候的程序入口
     * */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(ControllerApplication.class);
    }
}
