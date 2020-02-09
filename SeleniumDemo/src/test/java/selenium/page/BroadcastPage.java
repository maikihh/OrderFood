package selenium.page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import java.util.ArrayList;
import java.util.List;

/*在管理工具-消息群发page，群发消息
* */
public class BroadcastPage extends BasePage{
    public BroadcastPage send(String range, String title, String body, String summary, String author) {
        findElementClickable(By.linkText("选择需要发消息的应用")).click();
        findElementClickable(By.cssSelector(".ww_icon_AppNotice")).click();
        findElementClickable(By.linkText("确定")).click();
        findElementClickable(By.linkText("选择发送范围")).click();
        //js页面加载时间很久，通过在driver中capability中page load测试策略解决
        findElement(By.id("memberSearchInput")).sendKeys(range);
        findElementClickable(By.cssSelector(".js_search_item")).click();//搜索出发送范围后，需点击搜索结果至右边确认
        findElementClickable(By.linkText("确认")).click();
        findElement(By.cssSelector(".ww_editorTitle")).sendKeys(title);
        //body部分位于frame中，需要切换进入frame
        driver.switchTo().frame(0);
        findElement(By.cssSelector(".mpnews")).sendKeys(body);
        //从frame中切换回来
        driver.switchTo().defaultContent();
        //可视化元素，需要滚动才可定位
        ((JavascriptExecutor)(driver)).executeScript("window.scroll(0,2000)");
        //摘要部分，需点击才能出现摘要输入
        findElementClickable(By.cssSelector(".msg_edit_infoItem_textWord")).click();
        findElement(By.cssSelector(".qui_textarea")).sendKeys(summary);
        findElement(By.cssSelector(".js_amrd_sendName")).sendKeys(author);
        findElementClickable(By.linkText("发送")).click();
        findElementClickable(By.linkText("确定")).click();
        return new BroadcastPage();
    }

    public List<String> getSendedMessage(){
       // findElementClickable(By.linkText("已发送")).click();
        List<String> message = new ArrayList<String>();
        //java 语言设置到8 level，获取所有的发送，将每个元素文本存储
        driver.findElements(By.cssSelector(".msg_history_msgList_td")).forEach(element -> {
            message.add(element.getText());
        });
        return message;

    }
}
