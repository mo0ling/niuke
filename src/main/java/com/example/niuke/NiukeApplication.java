package com.example.niuke;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class NiukeApplication {

    @PostConstruct
    public void init() {
        // 解决netty启动冲突的问题
        // Netty4Utils.setAvailableProcessors
        System.setProperty("es.set.netty.runtime.available.processors", "false");
    }

    public static void main(String[] args) {
        SpringApplication.run(NiukeApplication.class, args);
    }

}
