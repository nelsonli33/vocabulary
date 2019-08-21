package com.lee.vocabulary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.lee.vocabulary.dao")
public class VocabularyApplication {

    public static void main(String[] args) {
        SpringApplication.run(VocabularyApplication.class, args);
    }
}
