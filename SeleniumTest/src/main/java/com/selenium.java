package com;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.Driver;

import static java.lang.Thread.sleep;

public class selenium {
    public void test1() {
//        最新的驱动需要Chrome浏览器的版本升级到最新的
//        System.setProperty("webdriver.chrome.driver","C:\\Windows\\System32\\chromedriver.exe");
        WebDriver driver=new ChromeDriver();
        driver.get("https://www.baidu.com/");
        WebElement input=driver.findElement(By.id("kw"));
        input.sendKeys("百度测试");
        driver.quit();
//        driver.close();
    }

@Test
    public void test() throws InterruptedException {
        WebDriver driver=new ChromeDriver();
//        driver.manage().window().maximize();
        driver.get("http://localhost:8080/pinter/file/page/upload");
        WebElement element=driver.findElement(By.id("upload"));
        element.click();
    sleep(5);
        Alert alert=driver.switchTo().alert();
    sleep(5);
        Assert.assertEquals("上传失败",alert.getText());
        alert.accept();
        driver.quit();
    }
}
