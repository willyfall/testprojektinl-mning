package se.reky.hakan;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

public class ScannerTest {
    Scanner scanner;
    @Test
    public void testScanner(){
        scanner=new Scanner("hej");
        Assertions.assertEquals(scanner.nextLine(),"hej");
    }
}
