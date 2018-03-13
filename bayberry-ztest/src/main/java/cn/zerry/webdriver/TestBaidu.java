package cn.zerry.webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * @author linzengrui
 * @Description TODO
 * @date 2018年03月13日 10:16
 */
public class TestBaidu {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "D:\\chromedriver_win32\\chromedriver.exe");
        WebDriver dr = new ChromeDriver();
        dr.get("http://www.baidu.com");
        dr.findElement(By.id("kw")).sendKeys("hello Selenium");
        dr.findElement(By.id("su")).click();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }



//        System.out.println(" title : ");
//        System.out.println(dr.getTitle());
//        System.out.println(" ------------- ");
//
//        System.out.println(" source : ");
//        System.out.println(dr.getPageSource());
//        System.out.println(" ------------- ");

//        dr.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        dr.quit();
    }
}
