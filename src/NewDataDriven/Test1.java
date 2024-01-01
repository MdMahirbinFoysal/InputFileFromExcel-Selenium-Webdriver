package NewDataDriven;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;

public class Test1 {
    WebDriver driver;
    @Test(dataProvider = "testdata")
    public void DemoProject(String uname, String password) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver","C:\\Selenium webdriver\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://demo.guru99.com/test/newtours/index.php");
        WebElement name = driver.findElement(By.xpath("//input[@name='userName']"));
        name.clear();
        name.sendKeys(uname);
        WebElement pass = driver.findElement(By.xpath("//input[@name='password']"));
        pass.clear();
        pass.sendKeys(password);
        WebElement submit = driver.findElement(By.xpath("//input[@name='submit']"));
        submit.click();
        Thread.sleep(3000);
        Assert.assertTrue(driver.getTitle().matches("Welcome: Mercury Tours"),"Invalid Credential");
//        Assert.assertEquals(driver.getTitle(), "Welcome: Mercury Tours");
    }
    @DataProvider(name = "testdata")
    public Object[][] TestData(){
        ReadData config = new ReadData("C:\\Users\\Asus\\Desktop\\Book1.xlsx");
        int rows = config.getRowCount(0);
        Object[][] credential = new Object[rows][2];
        for(int i=0; i<rows;i++){
            credential[i][0] = config.getData(0,i,0);
            credential[i][1] = config.getData(0,i,1);
        }
        return credential;

    }

}
