package task2;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Frames1 {
    WebDriver driver;
    JavascriptExecutor jse;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();

    }

    @AfterMethod
    public void tearDown() throws InterruptedException {
        Thread.sleep(2000);
        driver.quit();
    }

    @Test
    public void frames1() {

        //1)    https://demoqa.com/frames sayfasına gidilir
        driver.get("https://demoqa.com/frames");

        //2)    Sayfadaki “Frames” yazısı kontrol edilir (<h1>)
        WebElement framesIsAvailable = driver.findElement(By.className("text-center"));
        String expectedText = "Frames";
        String actualText = framesIsAvailable.getText();

        Assert.assertEquals(expectedText, actualText);


        //3)    “This is a sample page” yazısı kontrol edilir (Büyük frame içinde)
        jse=(JavascriptExecutor) driver;
        for (int i=0; i<3; i++ ){
            jse.executeScript("window.scrollBy(0,250)");
        }
        driver.switchTo().frame(driver.findElement(By.id("frame1")));
        WebElement isContains=driver.findElement(By.id("sampleHeading"));
        String expectedTextFrame = "This is a sample page" ;
        String actualTextFrame=isContains.getText();

        Assert.assertEquals(expectedTextFrame,actualTextFrame);

        //4)    “This is a sample page” yazısı kontrol edilir (küçük frame içinde)
        driver.switchTo().defaultContent();
        driver.switchTo().frame(driver.findElement(By.id("frame2")));
        WebElement isContains2=driver.findElement(By.id("sampleHeading"));
        String expectedTextFrame2= "This is a sample page";
        String actualTextFrame2=isContains2.getText();

        Assert.assertEquals(expectedTextFrame2,actualTextFrame2);

        //5)    Sayfada “Sample Iframe page There are 2 Iframes in this page” texti kontrol edilir
        driver.switchTo().defaultContent();
        WebElement isContainsInPage= driver.findElement(By.xpath("//div[contains(text(), 'Sample Iframe page There are 2 Iframes in this page')]"));
        String expectedTextInPage = "Sample Iframe page There are 2 Iframes in this page";
        String actualTextInPage=isContainsInPage.getText();
        String actualFirstSentence = actualTextInPage.split("\\.")[0].trim();

        Assert.assertEquals(expectedTextInPage,actualFirstSentence);



    }


}
