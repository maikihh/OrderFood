package selenium.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class ContactPage extends BasePage{
    public ContactPage add(String username, String id, String phone){
        findElement(By.name("username")).sendKeys(username);
        findElement(By.name("acctid")).sendKeys(id);
        findElement(By.name("mobile")).sendKeys(phone);
        findElementClickable(By.linkText("保存")).click();
        return this;
    }

    public ContactPage delete(String keyword){
        findElement(By.id("memberSearchInput")).clear();
        findElement(By.id("memberSearchInput")).sendKeys(keyword);
        //如果搜索的成员不存在，右边则没有“编辑”按钮
        try{
            //等待元素可以点击，封装方法在父类BasePage
            waitClickable(By.linkText("编辑"));
        }catch (Exception e){
            System.out.println("not found");
            return this;
        }
        findElementClickable(By.linkText("删除")).click();
        waitClickable(By.linkText("确认"));
        findElementClickable(By.linkText("确认")).click();
        findElementClickable(By.id("clearMemberSearchInput")).click();
        return this;
    }

    //删除当前选择的一行
    public ContactPage deleteCurrentPage(){
        waitClickable(By.cssSelector(".ww_checkbox"));
        List<WebElement> elements = driver.findElements(By.cssSelector(".ww_checkbox"));
        for (int i = 1; i < 2; i++){
            elements.get(i).click();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        findElementClickable(By.linkText("删除")).click();
        findElementClickable(By.linkText("确认")).click();
        return this;
    }

    public ContactPage importFromFile(String path) throws InterruptedException {
        findElementClickable(By.linkText("批量导入/导出")).click();
        findElementClickable(By.linkText("文件导入")).click();
        Thread.sleep(5);
        //通过sendKey实现上传功能
        findElement(By.id("js_upload_file_input")).sendKeys(path);
        findElementClickable(By.id("submit_csv")).click();
        findElementClickable(By.linkText("完成")).click();


        return this;
    }

    public void list(){

    }
}
