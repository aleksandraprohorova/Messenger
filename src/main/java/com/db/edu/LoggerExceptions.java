package com.db.edu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class LoggerExceptions {
    private static final Logger log = LoggerFactory.getLogger(LoggerExceptions.class);
    private static final String FILENAME = "messages.txt";


    public static void main(String[] args) {
        log.info("Just a log message.");
        log.debug("Message for debug level.");
        try {
            Files.readAllBytes(Paths.get(FILENAME));
        } catch (IOException ioex) {
            log.error("Failed to read file {}.", FILENAME, ioex);
        }
    }
}
