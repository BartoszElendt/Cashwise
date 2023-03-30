package io.github.szczepanskikrs.budgetoapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class BudgetOAppApplication {
    public static void main(String[] args) {
        SpringApplication.run(BudgetOAppApplication.class, args);
    }
}
