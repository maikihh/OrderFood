package selenium.page;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.awt.*;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class App extends BasePage{
    String url="https://work.weixin.qq.com/";

    public App loginWithCookie(){
        /* 如下设置等待时长为5秒
           首先这10秒并并非一个固定的等待时间，它并不影响脚本的执行速度。
           其次，它并不针对页面上的某一元素进行等待。当脚本执行到某个元素定位是，如果元素可以定位，则继续执行，如果元素定位不到，则它将以轮询的方式不断地判断元素是否被定位到。
           假设在第3秒定位到了元素则继续执行，若直到超出设置的时长5秒还没有定位到元素，则抛出异常。
         */
        ChromeOptions chromeOptions = new ChromeOptions();
        //page load测试，none代表不用等js所有全部加载完成，只要元素可点击即可
        chromeOptions.setCapability("pageLoadStrategy", "none");
        driver = new ChromeDriver(chromeOptions);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(url);
        //1. 绕过扫码登录，通过cookie直接登录企业微信
        driver.manage().window().maximize();
        findElementClickable(By.linkText("企业登录")).click();
        System.out.println(driver.manage().getCookies()); //获取当前所有cookie
        //登录企业微信，F12开发者模式，进入到网络->cookie，找到登录相关的cookie，添加cookie，再次刷新就是登录状态
        driver.manage().addCookie(new Cookie("wwrtx.sid", "EI8JsanLuZsl4XeELSW1GnwszzB-6KDJWH668VetxFl3RRygvvZ52fCY0vN6Rka3"));
        driver.navigate().refresh(); // 刷新当前页面
        return this;
    }

    public ContactPage toContact(){
        findElementClickable(By.linkText("通讯录")).click();
        return new ContactPage();
    }

    public ContactPage toMemberAdd(){
        findElementClickable(By.linkText("首页")).click();
        findElementClickable(By.linkText("添加成员")).click();
        return new ContactPage();
    }

    public BroadcastPage toBrocadcastMessage(){
        findElementClickable(By.linkText("管理工具")).click();
        List<WebElement> elements = driver.findElements(By.cssSelector(".manageTools_cnt_itemLink"));
        elements.get(2).click();
        return new BroadcastPage();
    }
}
