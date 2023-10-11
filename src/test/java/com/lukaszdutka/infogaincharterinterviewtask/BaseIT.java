package com.lukaszdutka.infogaincharterinterviewtask;

import com.lukaszdutka.infogaincharterinterviewtask.testcontainers.MySqlTestContainer;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.lifecycle.Startables;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public abstract class BaseIT {
    @Autowired
    protected List<CrudRepository<?, ?>> repositories;

    @BeforeEach
    protected void setUp() {
        repositories.forEach(CrudRepository::deleteAll);
    }

    private static final MySqlTestContainer mySql = MySqlTestContainer.create();

    @DynamicPropertySource
    private static void containerConfig(DynamicPropertyRegistry registry) {
        Startables.deepStart(mySql).join();

        registry.add("spring.datasource.url", mySql::getJdbcUrl);
        registry.add("spring.datasource.username", mySql::getUsername);
        registry.add("spring.datasource.password", mySql::getPassword);
    }
}