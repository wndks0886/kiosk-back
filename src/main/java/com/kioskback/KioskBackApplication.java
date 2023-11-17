package com.kioskback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
public class KioskBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(KioskBackApplication.class, args);

    test();

    }

    public static void test(){
        System.out.println("test");
        //수정한거 안보입니까?
    }

}
