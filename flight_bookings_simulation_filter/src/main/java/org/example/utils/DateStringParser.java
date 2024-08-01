package org.example.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Optional;

public class DateStringParser {
    public static Optional<LocalDateTime> parse(String dateString) {
        try {
            var localDate = LocalDateTime.parse(dateString);
            return Optional.of(localDate);
        } catch (DateTimeParseException e) {
            return Optional.empty();
        }
    }
}
