package com.yih.trans;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.scheduling.annotation.EnableScheduling;


@EnableScheduling
@EnableKafka
@ComponentScan
@EntityScan
@EnableJpaRepositories
@SpringBootApplication
public class Bank {
    public static void main(String[] argc) {
        SpringApplication.run(Bank.class, argc);
    }
}
