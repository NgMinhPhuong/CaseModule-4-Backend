package com.codegym.casemodule5;

import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootTest
@SpringBootApplication
@EnableCaching
@EnableScheduling
public class CaseModule5ApplicationTests {

    public static void main(String[] args) {
        SpringApplication.run(CaseModule5ApplicationTests.class,args);
    }
    @Test
    void contextLoads() {
    }

}
