package se.reky.hakan;

import java.util.List;
import java.util.Scanner;

public class ScannerUtil {
    private final Scanner scanner;

    public ScannerUtil(List<String> userInput) {
        StringBuilder userInputString = new StringBuilder();
        for (String input : userInput) {
            userInputString.append(input).append("\n");
        }
        this.scanner = new Scanner(userInputString.toString());
    }

    public Scanner getScanner(){
        return this.scanner;
    }
}
