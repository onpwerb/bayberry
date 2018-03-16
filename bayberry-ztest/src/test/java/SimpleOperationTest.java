import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author linzengrui
 * @Description TODO
 * @date 2018年03月13日 14:33
 */
@ContextConfiguration(locations = {"classpath:spring-core.xml"})
public class SimpleOperationTest {

    private static String loginUrl = "http://localhost:8080/web/query/login";      //登录页面
    private static String searchUrl = "http://localhost:8080/web/query/search";     //登录成功页面
    private static String indexUrl = "http://localhost:8080/web/analyse/index";

    private static String path = "D:\\chromedriver_win32\\chromedriver.exe";       //驱动绝对路径
    private static String driver = "webdriver.chrome.driver";                       //使用谷歌驱动

    //以 css选择器 和 xpath语法 获取HTML DOM元素类型
    enum CssType{
        ID("id", 1),
        NAME("name", 2),
        CLASS("class", 3),
        XPATH("xpath", 4),
        LINKTEXT("linkText", 5),
        CSSSELECTOR("CssSelector", 6),
        DEFAULT("default", 0);

        private String value;
        private int type;

        CssType(String value, int type){
            this.value = value;
            this.type = type;
        }

        public String getValue(){
            return value;
        }
    }

    //输入
    public static void input(WebDriver dr, String type, String name, String arg){
        if (type.equals(CssType.ID.getValue())){
            dr.findElement(By.id(name)).sendKeys(arg);
        } else if (type.equals(CssType.NAME.getValue())){
            dr.findElement(By.name(name)).sendKeys(arg);
        } else if (type.equals(CssType.CLASS.getValue())){
            dr.findElement(By.className(name)).sendKeys(arg);
        } else if (type.equals(CssType.XPATH.getValue())){
            dr.findElement(By.xpath(name)).sendKeys(arg);
        } else if (type.equals(CssType.CSSSELECTOR.getValue())){
            dr.findElement(By.cssSelector(name)).sendKeys(arg);
        }
    }

//    public static void input(WebDriver dr, String type, String name, String operation){ }

    //点击
    public static void click(WebDriver dr, String type, String name){
        if (type.equals(CssType.ID.getValue())){
            dr.findElement(By.id(name)).click();
        } else if (type.equals(CssType.NAME.getValue())){
            dr.findElement(By.name(name)).click();
        } else if (type.equals(CssType.CLASS.getValue())){
            dr.findElement(By.className(name)).click();
        } else if (type.equals(CssType.XPATH.getValue())){
            dr.findElement(By.xpath(name)).click();
        } else if (type.equals(CssType.LINKTEXT.getValue())){
            dr.findElement(By.linkText(name)).click();
        } else if (type.equals(CssType.CSSSELECTOR.getValue())){
            dr.findElement(By.cssSelector(name)).click();
        }
    }

    //拖动
    public static void drag(WebDriver dr, String class1, String class2){
        WebElement target = dr.findElement(By.className(class1));
        WebElement dest = dr.findElement(By.className(class2));
        new Actions(dr)
                .moveToElement(target)
                .clickAndHold(target)
                .moveToElement(dest)
                .release(dest)
                .perform();
    }

    //停顿
    public static void wait(int mills){
        try {
            Thread.sleep(mills);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //点击下拉框
    public static void selectClick(WebDriver dr, String name, int eq){
        dr.findElements(By.xpath(name)).get(eq).click();
    }

    //获取文本信息
    public static String text(WebDriver dr, String name){
        return dr.findElement(By.xpath(name)).getText();
    }

    //获取元素个数
    public static int sum(WebDriver dr, String name){
        return dr.findElements(By.cssSelector(name)).size();
    }

    @Test
    public void test(){
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
    }

    public static void login(WebDriver dr, String url){
        dr.get(url);

        input(dr, "name", "username", "admin");
        input(dr, "name", "password", "admin");
        click(dr, "id", "login-submit");
        wait(1000);

        //移动焦点，判断是否已经登录
        dr.get(searchUrl);
        if (dr.findElements(By.linkText("数据建模")).size() == 0){
            //刷新页面
            dr.get(url);
            //重新输入
            input(dr, "name", "username", "admin");
            input(dr, "name", "password", "admin");
            click(dr, "id", "login-submit");
            //间隔1秒后等服务器响应后再次点击
            wait(1000);
            click(dr, "id", "login-submit");
        }
    }

    public static void index(WebDriver dr, String url){
        wait(500);
        dr.get(url);
        click(dr, "linkText", "数据建模");
    }

    public static void analyse(WebDriver dr, String url){
        wait(500);
        dr.get(url);
        click(dr, "class", "file-close"); //取第一个元素. 取所有元素用 findElements

        //拖动第一个表
        drag(dr, "a-c-dd", "hy-tab-content");
        wait(500);
        //点击 自运算
        click(dr, "class", "autocompute");
    }

    public static void autocompete(WebDriver dr){
        wait(500);
        //点击 分组字段按钮
        click(dr, "xpath", "//a[@class='group-add-btn']");
        wait(1000);
        //点击全选按钮
        click(dr, "xpath", "//a[@class='group-check']");
        wait(500);
        //点击确定按钮
        click(dr, "xpath", "//a[@id='group-showbsave']");
        wait(500);
        //点击 统计字段下拉框
        selectClick(dr, "//span[@class='textbox-addon textbox-addon-right']", 1);
        wait(500);
        //鼠标选择 第一个 选项
        click(dr, "class", "combobox-item");
        wait(500);
        //点击 确定
        click(dr, "class", "calc-sure");
        wait(1000);
        //获取结果集
        String text = text(dr, "//div[@class='datagrid-cell datagrid-cell-c3-count']");
        assert text.equals("1") : "Test failed!";
    }

    public static void clean(WebDriver dr){
        wait(1000);

        //移除画布
        //点击 移除
        click(dr, "class", "delete");
        wait(1000);
        //点击确定
        click(dr, "xpath", "//a[@class='layui-layer-btn0']");

        //清除临时表
        int size = sum(dr, ".analyse-common-dl.temptb >dd");
        for(int i = 0; i < size; i++){
            click(dr, "CssSelector", ".analyse-common-dl.temptb >dd");
            wait(1000);
            click(dr, "CssSelector", ".temptb >dd >i");
        }
    }

}
