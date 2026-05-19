package com.amazon.framework.utils;

import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.Collections;
import java.util.Map;

public class YamlReader {
    private static final Map<String, Object> YAML_DATA;

    static {
        Yaml yaml = new Yaml();
        try (InputStream input = YamlReader.class.getClassLoader().getResourceAsStream("testdata.yaml")) {
            if (input == null) {
                throw new IllegalStateException("Could not find testdata.yaml in classpath");
            }
            YAML_DATA = yaml.load(input);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load testdata.yaml", e);
        }
    }

    public static void validateTestDataYaml() {
        Yaml yaml = new Yaml();
        try (InputStream input = YamlReader.class.getClassLoader().getResourceAsStream("testdata.yaml")) {
            if (input == null) {
                throw new IllegalStateException("Could not find testdata.yaml in classpath");
            }
            Object parsed = yaml.load(input);
            if (!(parsed instanceof Map)) {
                throw new IllegalStateException("testdata.yaml must contain a top-level mapping");
            }
        } catch (Exception e) {
            throw new RuntimeException("Invalid YAML syntax in testdata.yaml", e);
        }
    }

    @SuppressWarnings("unchecked")
    public static Map<String, Object> getSection(String key) {
        Object value = YAML_DATA.get(key);
        if (value instanceof Map) {
            return (Map<String, Object>) value;
        }
        return Collections.emptyMap();
    }

    public static String getValue(String key) {
        Object value = YAML_DATA.get(key);
        return value != null ? value.toString() : "";
    }
}
