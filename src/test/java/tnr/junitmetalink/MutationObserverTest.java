package tnr.junitmetalink;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class MutationObserverTest {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeEach
    public void setUp(){
        driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.get("https://codepen.io/dayvidwhy/pen/egdZyY");
    }

    @Test
    public void testMutation(){
        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='result']")));
        driver.findElement(By.xpath("//button[@id='red-button']")).click();
        String style = driver.findElement(By.xpath("//p[@id='some-id']")).getAttribute("style");
        Assertions.assertTrue(style.contains("color: red"), "Le texte n'a pas été changé en rouge");
        driver.findElement(By.xpath("//button[@id='blue-button']")).click();
        style = driver.findElement(By.xpath("//p[@id='some-id']")).getAttribute("style");
        Assertions.assertTrue(style.contains("color: blue"), "Le texte n'a pas été changé en bleu");
    }

    @AfterEach
    public void tearDown(TestInfo testInfo) throws IOException {
        TakesScreenshot scrShot =((TakesScreenshot)driver);
        File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
        File DestFile=new File("target/" + testInfo.getDisplayName().replace("()","") + ".png");
        FileUtils.copyFile(SrcFile, DestFile);
        driver.quit();
    }
}
