package task2;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Exercise {
    WebDriver driver;
    JavascriptExecutor jse;
    WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 10);

    }

    @AfterMethod
    public void tearDown() throws InterruptedException {
        driver.quit();
    }

    @Test
    public void exercise() {
        //1) Tarayıcı açılır (https://katalon-demo-cura.herokuapp.com/)
        driver.get("https://katalon-demo-cura.herokuapp.com");

        //2) Make Appointment butonuna tıklanır
        WebElement clickAppointment = driver.findElement(By.id("btn-make-appointment"));
        clickAppointment.click();

        //3) Kullanıcı adı ve şifre girilir (Kullanıcı adı ve şifreyi “Demo account” alanından get metodu kullanarak alıp girilir)
        driver.findElement(By.xpath("//input[@id='txt-username']")).sendKeys("John Doe");
        driver.findElement(By.id("txt-password")).sendKeys("ThisIsNotAPassword");

        //4) Login butonuna tıklanır
        driver.findElement(By.id("btn-login")).click();

        //5) Şifrenizi değiştirin popupı tamama tıklanır
        try {
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            alert.accept();
        } catch (Exception e) {
            System.out.println("Pop-up gelmedi veya başka bir hata oluştu: " + e.getMessage());

            //6) Facility Honkong seçilir
            WebElement selectMenu = driver.findElement(By.id("combo_facility"));
            Select select = new Select(selectMenu);
            select.selectByValue("Hongkong CURA Healthcare Center");

            //7) “Apply for hospital readmission” check boxı seçilir
            driver.findElement(By.id("chk_hospotal_readmission")).click();

            //8) “Healthcare Program” Medicare radyo butonu seçilir
            driver.findElement(By.id("radio_program_medicaid")).click();

            //9) “Visit Date (Required)” alanına tarih girilir
            driver.findElement(By.id("txt_visit_date")).sendKeys("01/01/2024");

            //10) “Comment” girilir.
            driver.findElement(By.id("txt_comment")).sendKeys("comment");

            //11) “Book Appointment” butonuna tıklanır
            driver.findElement(By.id("btn-book-appointment")).click();

            //12) “Appointment Confirmation” yazısı kontrol edilir
            WebElement isContains = driver.findElement(By.xpath("//h2"));

            Assert.assertEquals(isContains.getText(), "Appointment Confirmation");

            //13) Sağ üst köşedeki üç çizgi olan menü butonuna tıklanır
            driver.findElement(By.id("menu-toggle")).click();

            //14) “Log out” butonuna tıklanır
            driver.findElement(By.xpath("//a[@href='authenticate.php?logout']")).click();

            //15) Url kontrol edilir (https://katalon-demo-cura.herokuapp.com/ )
            String actualUrl = driver.getCurrentUrl();
            String expectedUrl = "https://katalon-demo-cura.herokuapp.com/";
            Assert.assertEquals(actualUrl, expectedUrl);

            //16) “We Care About Your Health” yazısı kontrol edilir
            String expectedText = "We Care About Your Health";
            String actualText = driver.findElement(By.xpath("//h3[text()='We Care About Your Health']")).getText();
            Assert.assertEquals(actualText, expectedText);
        }
    }
}
