package org.example.entity;

import java.util.UUID;

public class IDGenerator {
    public static String generateID() {
        return UUID.randomUUID().toString().substring(0, 8).replaceAll("-", "");
    }
}
