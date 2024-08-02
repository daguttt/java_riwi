package org.example.utils;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Optional;

public class DateStringParser {
    public static Optional<LocalDate> parse(String dateString) {
        try {
            var localDate = LocalDate.parse(dateString);
            return Optional.of(localDate);
        } catch (DateTimeParseException e) {
            return Optional.empty();
        }
    }
}
