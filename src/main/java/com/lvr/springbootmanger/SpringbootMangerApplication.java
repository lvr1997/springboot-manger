package com.lvr.springbootmanger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan("com.lvr.springbootmanger.utils")
public class SpringbootMangerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootMangerApplication.class, args);
    }

}
