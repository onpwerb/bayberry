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

1. 基本方法

方法名|用途
---|---
get(String url) | 打开指定的网站
FindElement(By by) | 在页面中寻找一个元素。其中传进入的参数就是你所定位到的元素
Sendkeys(String string) | 向定位到的text 输入框中输入你想要输入的内容。上传文件也可以用sendkeys()，string就为上传文件的绝对路径
Click() | 为点击事件，定位到想要点击button或者其它可以点击的地方，click就可以模仿鼠标点击的事件了
close() | 只关闭当前的一个页面
quit() | 退出了所有Webdriver所有的窗口，退的非常干净


2. get()

方法名|用途
---|---
getTitle() | 获取当前页面的title属性的值，一般利用这个属性可以判断页面是否跳转成功
getPageSource() | 获取当前页面整个页面的源码，可以在后台打印出来查看
getCurrentUrl() | 获取当前的URL
findElement().getAttribute() | DOM元素属性对应的值
findElement().getText() | DOM元素对应的文字信息

3. 浏览器操作方法

方法名|用途
---|---



