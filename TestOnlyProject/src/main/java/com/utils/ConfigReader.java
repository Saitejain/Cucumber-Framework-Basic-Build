package com.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public class ConfigReader {

    private final Properties prop = new Properties();

    public Properties init_prop() {
        Path configPath = Path.of("src", "test", "resources", "config", "config.properties");

        try (InputStream input = Files.exists(configPath)
                ? new FileInputStream(configPath.toFile())
                : ConfigReader.class.getClassLoader().getResourceAsStream("config/config.properties")) {

            if (input == null) {
                throw new RuntimeException("Unable to locate config.properties at src/test/resources/config/");
            }
            prop.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load properties file", e);
        }
        return prop;
    }
}
