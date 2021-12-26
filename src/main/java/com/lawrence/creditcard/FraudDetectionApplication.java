package com.lawrence.creditcard;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class FraudDetectionApplication {

    public static void main(String[] args) throws IOException {

        System.out.println("Please enter a command.");
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();
        if(command.length() == 0) {
            System.out.println("No command detected!");
            System.exit(1);
        }
        Map<String, String> parameters = readCommand(command);

        BigDecimal threshold = new BigDecimal(parameters.get("threshold"), new MathContext(2));

        String fileName = parameters.get("fileName");

        List<String> fileLines = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);

    }

    private static Map<String, String> readCommand(String command) {
        String separator = "\\s";
        String fileSuffix = ".csv";

        String[] result = command.split(separator);

        if(result.length != 3 || !result[2].endsWith(fileSuffix)) {
            System.out.println("Invalid command, please try again.");
            System.exit(1);
        }

        return Map.of("threshold", result[1], "fileName", result[2]);
    }
}
