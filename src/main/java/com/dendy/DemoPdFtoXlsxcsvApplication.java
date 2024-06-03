package com.dendy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class DemoPdFtoXlsxcsvApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(DemoPdFtoXlsxcsvApplication.class);
    }
    public static void main(String[] args) {
        SpringApplication.run(DemoPdFtoXlsxcsvApplication.class, args);
    }

}
