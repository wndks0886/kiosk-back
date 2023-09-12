package com.kioskback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KioskBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(KioskBackApplication.class, args);

    test();

    }

    public static void test(){
        System.out.println("test");
    }

}
