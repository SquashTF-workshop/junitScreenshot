package tnr.junitmetalink;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class AnimatedMenuTest {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeEach
    public void setUp(){
        driver = new FirefoxDriver();
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.get("https://codepen.io/knyttneve/pen/LKrGBy");
    }

    @Test
    public void testMenuTestimonials(){
        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='result']")));
        driver.findElement(By.xpath("//a[contains(text(),'Testimonials')]")).click();
        String style = driver.findElement(By.xpath("//a[contains(text(),'Testimonials')]")).getAttribute("class");
        Assertions.assertTrue(style.contains("is-active"), "Le bouton n'a pas été activé");
        driver.findElement(By.xpath("//a[contains(text(),'About')]")).click();
        style = driver.findElement(By.xpath("//a[contains(text(),'Testimonials')]")).getAttribute("class");
        Assertions.assertFalse(style.contains("is-active"), "Le bouton n'a pas été désactivé");
    }

    //Failure
    @Test
    public void testMenuBlog(){
        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='result']")));
        driver.findElement(By.xpath("//a[contains(text(),'Blog')]")).click();
        String style = driver.findElement(By.xpath("//a[contains(text(),'Blog')]")).getAttribute("class");
        Assertions.assertTrue(style.contains("is-active"), "Le bouton n'a pas été activé");
        driver.findElement(By.xpath("//a[contains(text(),'About')]")).click();
        style = driver.findElement(By.xpath("//a[contains(text(),'Blog')]")).getAttribute("class");
        Assertions.assertTrue(style.contains("is-active"), "Le bouton n'a pas été désactivé");
    }

    @AfterEach
    public void tearDown(TestInfo testInfo) throws IOException {
        TakesScreenshot scrShot =((TakesScreenshot)driver);
        File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
        File DestFile=new File("target/subFolder/" + testInfo.getDisplayName().replace("()","") + ".png");
        FileUtils.copyFile(SrcFile, DestFile);
        driver.quit();
    }

}
