package utils;

import java.util.Scanner;
import java.util.function.Function;

public class InputGetter {

    private final Scanner scanner;

    public InputGetter(Scanner scanner) {
        this.scanner = scanner;
    }

    public <T> T getInput(String prompt, Function<Scanner, T> extractorCb) {
        System.out.print(prompt);
        return extractorCb.apply(scanner);
    }
}
