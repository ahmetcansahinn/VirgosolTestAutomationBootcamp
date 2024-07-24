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

import java.util.ArrayList;
import java.util.Set;

public class WindowHandles {
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
    public void windowHandles() throws InterruptedException {

        //1)https://demoqa.com/browser-windows sayfasına gidilir
        driver.get("https://demoqa.com/browser-windows");
        jse=(JavascriptExecutor) driver;
        for (int i=0; i<3; i++ ){
            jse.executeScript("window.scrollBy(0,250)");
        }
        //2)“Browser Windows” başlık kontrol edilir (<h1>)
        WebElement isContains=driver.findElement(By.className("text-center"));
        String expectedText="Browser Windows";
        String actualText=isContains.getText();

        Assert.assertEquals(expectedText,actualText);

        //3)“New Tab” butonuna tıklanır
        driver.findElement(By.id("tabButton")).click();

        //4)Açılan sayfanın Url’si kontrol edilir (https://demoqa.com/sample )
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));

        String actualUrl = driver.getCurrentUrl();
        String expectedUrl = "https://demoqa.com/sample";

        Assert.assertEquals(actualUrl, expectedUrl);

        //5)Açılan sayfadaki “This is a sample page” yazı kontrol edilir
        WebElement isContainsTextInNewTab=driver.findElement(By.id("sampleHeading"));
        String expectedTextInNewTab="This is a sample page";
        String actualTextInNewTab=isContainsTextInNewTab.getText();

        Assert.assertEquals(expectedTextInNewTab,actualTextInNewTab);
        Thread.sleep(2000);

        //6)Ana sayfaya geçilip “New Window” butonuna tıklanır
        driver.switchTo().window(tabs.get(0));
        WebElement newWindowButton = driver.findElement(By.id("windowButton"));
        newWindowButton.click();
        Thread.sleep(2000);

        //7)Açılan sayfadaki “This is a sample page” yazı kontrol edilir
        driver.switchTo().window(tabs.get(tabs.size() - 1));

        String expectedWindowText = "This is a sample page";
        WebElement sampleHeadingElement = driver.findElement(By.id("sampleHeading"));
        String actualWindowText = sampleHeadingElement.getText();

        Assert.assertEquals(actualWindowText, expectedWindowText);

        //8)Açılan sayfa kapatılır
        driver.close();
    }
}
