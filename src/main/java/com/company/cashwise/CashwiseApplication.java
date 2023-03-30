package com.company.cashwise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class CashwiseApplication {
    public static void main(String[] args) {
        SpringApplication.run(CashwiseApplication.class, args);
    }
}
