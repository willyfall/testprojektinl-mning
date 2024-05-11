package se.reky.hakan.web;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

@Disabled
public class PlayerControllerTest {
    ChromeDriver chromeDriver;
    @BeforeAll
    public static void init(){
        WebDriverManager.chromedriver().setup();
    }
    @BeforeEach
    public void setup(){
        chromeDriver=new ChromeDriver();
    }
    @AfterEach
    public void close(){
        if (chromeDriver!=null){
            chromeDriver.quit();
        }
    }
    @Test
    public void playerAmountTest(){
        chromeDriver.get("http://localhost:8080/players");
        List<WebElement> elements=chromeDriver.findElements(By.tagName("li"));
        Assertions.assertEquals(elements.size(),2);
    }
    @Test
    public void displayNameTest(){
        chromeDriver.get("http://localhost:8080/players");
        WebElement element=chromeDriver.findElement(By.tagName("li"));
        Assertions.assertTrue(element.isDisplayed());
    }
    @Test
    public void titleTest(){
        chromeDriver.get("http://localhost:8080/players");
        Assertions.assertEquals(chromeDriver.getTitle(), "Players List");
    }
    @Test
    public void buttonTextTest(){
        chromeDriver.get("http://localhost:8080/players");
        WebElement element=chromeDriver.findElement(By.tagName("button"));
        Assertions.assertEquals(element.getText(),"Logga in");
    }
}
