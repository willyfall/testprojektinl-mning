package se.reky.hakan;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

public class IOhandlerTest {
    private IOHandler ioHandler(String dataforscanner){
        IOHandler ioHandler=new IOHandler(new Scanner(dataforscanner));
        return ioHandler;
    }
    @Test
    public void testIOhandler(){
        ioHandler("2");
        Assertions.assertTrue(ioHandler("2").hasNextInt());
    }
}
