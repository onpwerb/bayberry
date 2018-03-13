**webdriver**

自动化测试框架

## 第一个简单demo

1. 获取selenium依赖

```
<dependency>
    <groupId>org.seleniumhq.selenium</groupId>
    <artifactId>selenium-java</artifactId>
    <version>2.53.1</version>
</dependency>
```

selenium 3.x以上的要用jdk8，这里使用jdk7.

2. chromedriver下载

chromedriver下载地址：

https://sites.google.com/a/chromium.org/chromedriver/downloads （被墙了）

http://npm.taobao.org/mirrors/chromedriver/（可用）

注意 ：chromedriver的版本要与你使用的chrome版本对应，对应关系如下：


chromedriver版本	|支持的Chrome版本
---|---
v2.33 |	v60-62

3.基础组件整理

jdk7

selenium 2.53.1

chrome v63

chromedriver.exe v2.34 win32


## 语法

```$xslt
WebDriver driver = new ChromeDriver();
```

以下方法都以driver为对象的方法。

1.基本方法

方法名|用途
---|---
get(String url) | 打开指定的网站
FindElement(By by) | 在页面中寻找一个元素。其中传进入的参数就是你所定位到的元素
Sendkeys(String string) | 向定位到的text 输入框中输入你想要输入的内容。上传文件也可以用sendkeys()，string就为上传文件的绝对路径
Click() | 为点击事件，定位到想要点击button或者其它可以点击的地方，click就可以模仿鼠标点击的事件了
close() | 只关闭当前的一个页面
quit() | 退出了所有Webdriver所有的窗口，退的非常干净


2.get()

方法名|用途
---|---
getTitle() | 获取当前页面的title属性的值，一般利用这个属性可以判断页面是否跳转成功
getPageSource() | 获取当前页面整个页面的源码，可以在后台打印出来查看
getCurrentUrl() | 获取当前的URL
findElement().getAttribute() | DOM元素属性对应的值
findElement().getText() | DOM元素对应的文字信息

3.浏览器操作方法

方法名|用途
---|---
manage().window().maximize() | 将浏览器的窗口最大化
navigate().back() | 控制浏览器按照浏览器记录的history，回退到上一个页面
navigate().forward() | 控制浏览器按照浏览器记录的history，前进到上一个页面
navigate().refresh() | 刷新页面

切换窗口
```$xslt
 public  boolean switchToWindow(String windowTitle,WebDriver dr){  
	    boolean flag = false;  
	    try { 
//	    	将页面上所有的windowshandle放在入set集合当中
	        String currentHandle = dr.getWindowHandle();  
	        Set handles = dr.getWindowHandles();  
	        for (String s : handles) {  
	            if (s.equals(currentHandle))  
	                continue;  
	            else {  
	            	dr.switchTo().window(s);
//	                和当前的窗口进行比较如果相同就切换到windowhandle
//	                判断title是否和handles当前的窗口相同
	                if (dr.getTitle().contains(windowTitle)) {  
	                    flag = true;  
	                    System.out.println("Switch to window: " + windowTitle + " successfully!");  
	                    break;  
	                } else  
	                    continue;  
	            }  
	        }  
	    } catch (Exception e) {  
	        System.out.printf("Window: " + windowTitle  
	                + " cound not found!", e.fillInStackTrace());  
	        flag = false;  
	    }  
	    return flag;  
	} 
```


4.元素定位

原则上id name class优先，通过By类方法进行定位。


方法名|用途
---|---
By.id() | id属性
By.name() | name属性
By.classname() | class属性
By.linkText() | 查找链接
By.xpath() | xpath语法
By.cssSelector | css选择器语法


5.模拟鼠标移动显示悬浮下拉窗体

```$xslt
WebDriver driver = new ChromeDriver();
driver.get(url);
WebElement element = driver.findElement(By.linkText("我的京东"));
Actions builder = new Actions(driver);
Action mouserOverlogin = builder.moveToElement(element).build();
mouserOverlogin.perform();
```

6.模拟鼠标点击事件

```$xslt
WebDriver driver = new ChromeDriver();
driver.get(url);
WebElement element = driver.findElement(By.linkText("登录"));
Actions builder = new Actions(driver);
Action mouserOverlogin = builder.moveToElement(element).click().build();
mouserOverlogin.perform();
driver.findElement(By.id("username")).sendKeys("chenyixiao");;
driver.close();
```

7.模拟鼠标和键盘组合的操作步骤
```$xslt
Action serierofAction = 
    builder.moveToElement(element)
    .click()
    .keyDown(element,Keys.SHIFT)
    .sendKeys(element,"chenyixiao")
    .keyUp(element,Keys.SHIFT).
    doubleClick(element)
    .contextClick()
    .build();
```

鼠标移动到输入框，
执行单击操作，
按下shift键切换成大写输入模式，
输入内容，
然后释放Shift键，
双击输入框，
弹出右键的选择弹窗，
构建完成


8.切换frame ，屏蔽弹窗

```$xslt
WebElement IframeElement=driver.findElement(By.id("frame"));
Driver.switch().frame(IframeElement);
driver.switchTo().alert().accept();
```