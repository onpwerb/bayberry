package cn.hancloud.gaqbxt.test.service;

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

    private static String loginUrl = "http://localhost:8080/web/query/login";      //登录页面
    private static String searchUrl = "http://localhost:8080/web/query/search";     //登录成功页面
    private static String indexUrl = "http://localhost:8080/web/analyse/index";

    @Test
    public void test(){
        String path = "D:\\chromedriver_win32\\chromedriver.exe";
        String driver = "webdriver.chrome.driver";

        System.setProperty(driver, path);
        WebDriver dr = new ChromeDriver();
        dr.manage().window().maximize();

        //登录
        login(dr, loginUrl);
        //进入 数据建模
        index(dr, searchUrl);
        //选取表
        analyse(dr, indexUrl);
        //自运算
        autocompete(dr);
        //移除临时表，清除画布
        clean(dr);

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

        //移动焦点，判断是否已经登录
        dr.get(searchUrl);
        if (dr.findElements(By.linkText("数据建模")).size() == 0){
            //刷新页面
            dr.get(url);
            //重新输入
            dr.findElement(By.name("username")).sendKeys("admin");
            dr.findElement(By.name("password")).sendKeys("admin");
            dr.findElement(By.id("login-submit")).click();
            //间隔1秒后等服务器响应后再次点击
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            dr.findElement(By.id("login-submit")).click();
        }
    }

    public static void index(WebDriver dr, String url){
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        dr.get(url);
        dr.findElement(By.linkText("数据建模")).click();
    }

    public static void analyse(WebDriver dr, String url){
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        dr.get(url);
        dr.findElement(By.className("file-close")).click(); //取第一个元素. 取所有元素用 findElements

        //拖动第一个表
        WebElement target = dr.findElement(By.className("a-c-dd"));
        WebElement dest = dr.findElement(By.className("hy-tab-content"));
        new Actions(dr)
                .moveToElement(target)
                .clickAndHold(target)
                .moveToElement(dest)
                .release(dest)
                .perform();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //点击 自运算
        dr.findElement(By.className("autocompute")).click();

    }

    public static void autocompete(WebDriver dr){
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //点击 分组字段按钮
        dr.findElement(By.xpath("//a[@class='group-add-btn']")).click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //点击全选按钮
        dr.findElement(By.xpath("//a[@class='group-check']")).click();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //点击确定按钮
        dr.findElement(By.xpath("//a[@id='group-showbsave']")).click();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //点击 统计字段下拉框
        dr.findElements(By.xpath("//span[@class='textbox-addon textbox-addon-right']")).get(1).click();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //鼠标选择 第一个 选项
        dr.findElement(By.className("combobox-item")).click();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //点击 确定
        dr.findElement(By.className("calc-sure")).click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //获取结果集
//        String text = dr.findElement(By.xpath("//td[@field='count']")).getText();
        String text = dr.findElement(By.xpath("//div[@class='datagrid-cell datagrid-cell-c3-count']")).getText();
        assert text.equals("1") : "Test failed!";
//        System.out.println("Test passed!");
    }

    public static void clean(WebDriver dr){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //移除画布
        //点击 移除
        dr.findElement(By.className("delete")).click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //点击确定
        dr.findElement(By.xpath("//a[@class='layui-layer-btn0']")).click();


        //清除临时表
        int size = dr.findElements(By.cssSelector(".analyse-common-dl.temptb >dd")).size();
        for(int i = 0; i < size; i++){
            dr.findElement(By.cssSelector(".analyse-common-dl.temptb >dd")).click();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            dr.findElement(By.cssSelector(".temptb >dd >i")).click();
        }

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
