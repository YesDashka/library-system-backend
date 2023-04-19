package org.example.entity;

import java.util.UUID;

public class IDGenerator {

    private static final int ID_LENGTH = 8;
    private static final String REDUCE_SYMBOLS = "-";

    public static String generateID() {
        return UUID.randomUUID().toString()
                .substring(0, ID_LENGTH)
                .replaceAll(REDUCE_SYMBOLS, "");
    }

}
