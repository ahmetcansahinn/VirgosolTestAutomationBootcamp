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

public class Frames2 {
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
    public void frames2(){

        //1)    https://demoqa.com/nestedframes Sayfasına gidilir
        driver.get("https://demoqa.com/nestedframes");

        //2)    “Nested Frames” başlık kontrol edilir (<h1>)
        WebElement isContains=driver.findElement(By.className("text-center"));
        String expectedText="Nested Frames";
        String actualText=isContains.getText();

        Assert.assertEquals(expectedText,actualText);


        //3)    “Parent frame” yazısı kontrol edilir (büyük frame içinde)
        jse=(JavascriptExecutor) driver;
        for (int i=0; i<3; i++ ){
            jse.executeScript("window.scrollBy(0,250)");
        }
        driver.switchTo().frame(driver.findElement(By.id("frame1")));
        WebElement isContainsFrame=driver.findElement(By.xpath("//body[text()='Parent frame']"));
        String expectedFrame="Parent frame";
        String actualFrame=isContainsFrame.getText();

        Assert.assertEquals(expectedFrame,actualFrame);

        //4)    “Child Iframe” yazısı kontrol edilir (Büyük frame içindeki frame içinde)
        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@srcdoc='<p>Child Iframe</p>']")));
        WebElement isContainsFrame2=driver.findElement(By.xpath("//p[.='Child Iframe']"));
        String expectedFrame2="Child Iframe";
        String actualFrame2=isContainsFrame2.getText();

        Assert.assertEquals(expectedFrame2,actualFrame2);

        //5)    Sayfada “Sample Nested Iframe page.” texti kontrol edilir
        driver.switchTo().defaultContent();
        WebElement isContainsInPage= driver.findElement(By.xpath("//div[contains(text(), 'Sample Nested Iframe page.')]"));
        String expectedTextInPage = "Sample Nested Iframe page.";
        String actualTextInPage=isContainsInPage.getText();
        String actualFirstSentence = actualTextInPage.split("\\.")[0].trim() + ".";

        Assert.assertEquals(expectedTextInPage,actualFirstSentence);
    }
}
