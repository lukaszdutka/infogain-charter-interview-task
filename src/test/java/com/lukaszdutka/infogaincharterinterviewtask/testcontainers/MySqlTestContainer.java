package com.lukaszdutka.infogaincharterinterviewtask.testcontainers;

import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.utility.DockerImageName;

public class MySqlTestContainer extends MySQLContainer<MySqlTestContainer> {
    private static final String IMAGE_VERSION = "mysql:8.1.0";
    private static final String DATABASE_NAME = "database";
    private static final String DB_USERNAME = "myuser";
    private static final String DB_PASSWORD = "mypassword";
    private static final int DB_PORT = 3306;

    public MySqlTestContainer() {
        super(DockerImageName.parse(IMAGE_VERSION));
    }

    public static MySqlTestContainer create() {
        return new MySqlTestContainer()
                .withDatabaseName(DATABASE_NAME)
                .withUsername(DB_USERNAME)
                .withPassword(DB_PASSWORD)
                .withExposedPorts(DB_PORT);
    }
}
