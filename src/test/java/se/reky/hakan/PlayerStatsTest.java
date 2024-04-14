package se.reky.hakan;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.autoconfigure.web.servlet.WebDriverScope;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerStatsTest {

    private WebDriver driver;

    @BeforeAll
    public static void init() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless"); // Run in headless mode for CI environments
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        driver = new ChromeDriver();
        //comment here now
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @Disabled
    public void testProductsDisplay() {
        driver.get("http://localhost:8080/players");
        List<WebElement> players = driver.findElements(By.cssSelector("ul > li"));

        WebElement firstProduct = players.getFirst();
        System.out.println(firstProduct.getText());
        assertTrue(firstProduct.getText().contains("Lars"), "Product name should be displayed");
    }

    //1. Verifiera att namnet på den som ligger ett på listan stämmer
    //2. Verifiera att titeln på sidan stämmer
    //3. Verifiera att antalet players i listan stämmer
    //4. Verifiera att h2 med rätt titel finns på sidan (använd id:t som locator)
    //5.
    //VG: Ändra i player.html så att varje player i listan blir klickbar.
    //Länken ska leda till en ny endpoint som hämtar ut endast denna player genom sitt iD
    //Den nya endpointen
    @Test
    public void testPlayerDetails() {
        driver.get("http://localhost:8080/players");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // Click the first link in the list of players
        WebElement firstPlayerLink = driver.findElement(By.cssSelector("ul li a"));
        firstPlayerLink.click();

        // Use WebDriverWait to wait for the player name to be visible on the new page
        WebElement playerName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("playerName")));
        WebElement playerExperience = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("playerExperience")));
        System.out.println(playerName.getText() + ", " + playerExperience.getText());
        // Assert that these elements are not empty
        assertTrue(playerName.isDisplayed());
    }

    @Test
    @Disabled
    public void testWebsiteTitle() {
        // Hämta in de webdrivers du vill använda
        //WebDriver driver = new ChromeDriver();

        // Navigera in till den URL du vill testa för respektive driver
        driver.navigate().to("https://svtplay.se");

        assertEquals("SVT Play", driver.getTitle());

        //driver.quit();
    }

    @Test
    @Disabled
    public void testLoginButtonPresent() {
        // Replace "http://localhost:8080/products" with the URL where your app is running
        driver.get("http://localhost:8080/api/products");


        // Find all list items in the products list
        List<WebElement> products = driver.findElements(By.cssSelector("button"));

        // Assert that the products list is not empty
        assertFalse(products.isEmpty(), "Button should be displayed");

        // Optional: Further checks on product details
        WebElement firstProduct = products.get(0);
        System.out.println(firstProduct.getText());
        assertEquals("Login", firstProduct.getText(), "Login button should be displayed");

    }


}


