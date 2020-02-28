package com.cloud.lab.management;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.cloud.lab.management")
@MapperScan(basePackages = {"com.cloud.lab.management.mapper"})
@EntityScan("com.cloud.lab.management")
@SpringBootApplication
public class PictureApplication extends SpringBootServletInitializer {


    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(PictureApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(PictureApplication.class, args);
    }

}
