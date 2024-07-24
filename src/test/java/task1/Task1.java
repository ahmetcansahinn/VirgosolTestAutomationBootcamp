package task1;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class Task1 {
    WebDriver driver;

    @BeforeMethod
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();

    }

    @AfterMethod
    public void tearDown() throws InterruptedException {
        Thread.sleep(2000);
        driver.close();
    }

    @Test
    public void firstTask() throws InterruptedException {

        //Go to https://petstore.octoperf.com/actions/Catalog.action
        driver.get("https://petstore.octoperf.com/actions/Catalog.action");

        //Enter “fish” in inputBox and click “search” box
        WebElement element=driver.findElement(By.name("keyword"));
        element.sendKeys("fish");
        WebElement search= driver.findElement(By.name("searchProducts"));
        search.click();

        //Verify product ID is “FI-FW-02”
        String expectedID = "FI-FW-02";
        WebElement actualResult=driver.findElement(By.xpath("//font[text()=' FI-FW-02 ']"));
        String actualId=actualResult.getText();
        Assert.assertEquals(expectedID,actualId);

        Thread.sleep(3000);
    }
    //Verify product ID is “FI-FW-02”

    @Test
    public void secondTask(){

        //Go to https://petstore.octoperf.com/actions/Catalog.action
        driver.get("https://petstore.octoperf.com/actions/Catalog.action");

        //Enter “fish” in inputBox and click “search” box
        WebElement element=driver.findElement(By.name("keyword"));
        element.sendKeys("fish");
        driver.findElement(By.name("searchProducts")).click();

        //Click productID “FI-FW-02 Goldfish”
        driver.findElement(By.xpath("//font[text()=' FI-FW-02 ']")).click();

        //Select “EST-20” item and “Add to Cart”
        driver.findElement(By.xpath("//a[@href='/actions/Cart.action?addItemToCart=&workingItemId=EST-20']")).click();

        //Click “Fish” modulep
        driver.findElement(By.xpath("//img[@src='../images/sm_fish.gif']")).click();

        //Click productID “FI-SW-01 Angelfish”
        driver.findElement(By.xpath("//a[@href='/actions/Catalog.action?viewProduct=&productId=FI-SW-01']")).click();

        //Select large angel fish and add to cart
        driver.findElement(By.xpath("//a[@href='/actions/Cart.action?addItemToCart=&workingItemId=EST-1']")).click();

        //Verify total cost “$22.00”
        String expectedResult="Sub Total: $22.00";
        WebElement actualResult=driver.findElement(By.xpath("//*[contains(text(), 'Sub Total: $22.00')]"));
        String actual= actualResult.getText();
        Assert.assertEquals(expectedResult,actual);


    }

    @Test
    public void thirdTask(){
        //Go to
        //https://selenium08.blogspot.com/2019/07/check-box-and-radio-buttons.html
        driver.get("https://selenium08.blogspot.com/2019/07/check-box-and-radio-buttons.html");

        //click red and green checkBoxes
        WebElement redBox=driver.findElement(By.xpath("//input[@name='color'][1]"));
        redBox.click();

        WebElement greenBox=driver.findElement(By.xpath("//input[@name='color'][5]"));
        greenBox.click();

        //Verify Red is selected, Orange is not selected
        WebElement orangeBox=driver.findElement(By.xpath("//input[@name='color'][4]"));
        Assert.assertFalse(orangeBox.isSelected(), "Orange is selected");
        Assert.assertTrue(redBox.isSelected(),"red is not selected");

        //Verify Blue is enabled, Green is selected
        WebElement blueBox=driver.findElement(By.xpath("//input[@name='color'][3]"));
        Assert.assertTrue(blueBox.isEnabled(),"Blue is not enabled");
        Assert.assertTrue(greenBox.isSelected(),"Green is not selected");



    }

    @Test
    public void fourthTask(){
        //Go to
        //https://selenium08.blogspot.com/2019/07/check-box-and-radio-buttons.html
        driver.get("https://selenium08.blogspot.com/2019/07/check-box-and-radio-buttons.html");

        //click IE
        WebElement clickIE=driver.findElement(By.xpath("//input[@value='IE']"));
        clickIE.click();

        //Verify IE is selected, Opera is not selected
        WebElement opera=driver.findElement(By.xpath("//input[@value='Opera']"));
        Assert.assertTrue(clickIE.isSelected(),"IE is not selected");
        Assert.assertFalse(opera.isSelected(),"Opera is selected");

        //Click Mozilla
        WebElement clickMozilla=driver.findElement(By.xpath("//input[@value='Mozilla']"));
        clickMozilla.click();

        //Verify Mozilla is selected, IE is not selected
        Assert.assertTrue(clickMozilla.isSelected(),"Mozilla is not selected");
        Assert.assertFalse(clickIE.isSelected(),"IE is selected");
    }


    @Test
    public void fifthTask() throws InterruptedException {

        //go to https://selenium08.blogspot.com/2019/11/dropdown.html
        driver.get("https://selenium08.blogspot.com/2019/11/dropdown.html");

        //get size of dropdown menu
        WebElement elementMonth=driver.findElement(By.name("Month"));
        Select select=new Select(elementMonth);
        List<WebElement> options = select.getOptions();
        System.out.println("Menüsündeki seçeneklerin sayısı: " + options.size());

        //get all texts
        for (WebElement option : options) {
            System.out.println("Seçenek: " + option.getText());
        }

        Select selectMonth=new Select(elementMonth);

        //select March by value
        selectMonth.selectByValue("Ma");
        Thread.sleep(3000);

        //select April by index
        selectMonth.selectByIndex(4);
        Thread.sleep(3000);

        //select October by text
        selectMonth.selectByVisibleText("October");
        Thread.sleep(3000);

        //Verify: get selected options size=3
        List<WebElement> selectedOptions = selectMonth.getAllSelectedOptions();
        System.out.println("Seçilen seçenek sayısı: " + selectedOptions.size());
    }

    @Test
    public void sixthTask() throws InterruptedException {

        //Open "https://demoqa.com/select-menu".
        driver.get("https://demoqa.com/select-menu");

        //Select the Old Style Select Menu using the element id.
        WebElement element=driver.findElement(By.id("oldSelectMenu"));
        Select selectMenu=new Select(element);
        List<WebElement> options=selectMenu.getOptions();

        //Print all the options texts of the dropdown.
        for (WebElement option : options) {
            System.out.println("Seçenek: " + option.getText());
        }

        //Select 'Purple' using the index and get text
        selectMenu.selectByIndex(4);
        String selectedText = selectMenu.getOptions().get(4).getText();
        System.out.println("Seçilen 'Purple' Seçeneğinin Metni: " + selectedText);
        Thread.sleep(2000);

        //After that, select 'Magenta' using visible text and get text
        selectMenu.selectByVisibleText("Magenta");
        String selectedTextMagenta=selectMenu.getOptions().get(9).getText();
        System.out.println("Seçilen 'Magenta' Seçeneğinin Metni: " + selectedTextMagenta);
        Thread.sleep(2000);

        //Select an option using value of 'White' and get text
        selectMenu.selectByValue("6");
        String selectedTextWhite=selectMenu.getOptions().get(6).getText();
        System.out.println("Seçilen 'White' Seçeneğinin Metni: " + selectedTextWhite);
        Thread.sleep(2000);




    }


}
