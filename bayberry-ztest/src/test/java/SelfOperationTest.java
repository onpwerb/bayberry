import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.springframework.test.context.ContextConfiguration;

import java.util.Set;

/**
 * @author linzengrui
 * @Description TODO
 * @date 2018年03月13日 14:33
 */
@ContextConfiguration(locations = {"classpath:spring-core.xml"})
public class SelfOperationTest {

    @Test
    public void test(){
        String loginUrl = "http://localhost:8080/web/query/login";      //登录页面
        String searchUrl = "http://localhost:8080/web/query/search";     //登录成功页面
        String indexUrl = "http://localhost:8080/web/analyse/index";

        String path = "D:\\chromedriver_win32\\chromedriver.exe";
        String driver = "webdriver.chrome.driver";

        System.setProperty(driver, path);
        WebDriver dr = new ChromeDriver();
//        dr.manage().window().maximize();

        login(dr, loginUrl);

        index(dr, searchUrl);

        analyse(dr, indexUrl);

//        quit(dr);

    }

    public static void login(WebDriver dr, String url){
        dr.get(url);
        dr.findElement(By.name("username")).sendKeys("admin");
        dr.findElement(By.name("password")).sendKeys("admin");
        dr.findElement(By.id("login-submit")).click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(dr.getCurrentUrl().equals(url)){
            dr.findElement(By.id("login-submit")).click();
        }
    }

    public static void index(WebDriver dr, String url){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        dr.get(url);
        dr.findElement(By.linkText("数据建模")).click();
    }

    public static void analyse(WebDriver dr, String url){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        dr.get(url);
        dr.findElement(By.className("file-close")).click(); //取第一个元素. 取所有元素用 findElements
        WebElement target = dr.findElement(By.className("a-c-dd"));
        WebElement dest = dr.findElement(By.className("hy-tab-content"));
        new Actions(dr)
                .moveToElement(target)
                .clickAndHold(target)
                .moveToElement(dest)
                .release(dest)
                .perform();


    }

    public static boolean switchToWindow(String windowTitle,WebDriver dr){
        boolean flag = false;
        try {
            String currentHandle = dr.getWindowHandle();
            Set<String> handles = dr.getWindowHandles();
            for (String s : handles) {
                if (s.equals(currentHandle))
                    continue;
                else {
                    dr.switchTo().window(s);
                    if (dr.getTitle().contains(windowTitle)) {
                        flag = true;
                        System.out.println("Switch to window: " + windowTitle + " successfully!");
                        break;
                    } else
                        continue;
                }
            }
        } catch (Exception e) {
            System.out.printf("Window: " + windowTitle + " cound not found!", e.fillInStackTrace());
            flag = false;
        }
        return flag;
    }

    public static void quit(WebDriver dr){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        dr.quit();
    }
}
