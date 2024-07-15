package tests;

import Utils.JsonTestDataFetcher;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class JsonDataTest {
    private static WebDriver driver;

    @BeforeMethod
    public void setUp() {
        // Set up the WebDriver instance
        System.setProperty("webdriver.chrome.driver", "/Users/sishukla/Documents/TestScript/chromedriver");
        driver = new ChromeDriver();
        // Open the URL
        String url = "https://qa-practice.netlify.app/bugs-form.html";
        driver.get(url);
    }

    @Test(dataProvider = "nameData")
    public void verifyFirstAndLastNameJsonData(String firstName, String lastName) throws InterruptedException {

        // Locate the First Name input field
        WebElement firstNameInput = driver.findElement(By.xpath("//input[@id='firstName']"));

        // Clear any existing value in the First Name input field
        firstNameInput.clear();
        Thread.sleep(5000);

        // Enter the First Name value
        firstNameInput.sendKeys(firstName);

        // Locate the Last Name input field
        WebElement lastNameInput = driver.findElement(By.xpath("//input[@id='lastName']"));

        // Clear any existing value in the Last Name input field
        lastNameInput.clear();
        Thread.sleep(5000);

        // Enter the Last Name value
        lastNameInput.sendKeys(lastName);
        Thread.sleep(5000);

        // Verify the entered First Name and Last Name values
        Assert.assertEquals(firstName, firstNameInput.getAttribute("value"));
        Assert.assertEquals(lastName, lastNameInput.getAttribute("value"));

        // Close the browser
        driver.quit();
    }

    @DataProvider(name = "nameData")
    public Object[][] provideData() {
        return JsonTestDataFetcher.fetchTestData();
    }


    @AfterMethod
    public void tearDown() {
        // Quit the WebDriver instance
        driver.quit();
    }

}
