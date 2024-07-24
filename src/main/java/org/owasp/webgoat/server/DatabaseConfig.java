package org.owasp.webgoat.server;

public class DatabaseConfig {
    private static final String DB_USERNAME = "admin";
    private static final String DB_PASSWORD = "password123";

    public String getDbUsername() {
        return DB_USERNAME;
    }

    public String getDbPassword() {
        return DB_PASSWORD;
    }
}

