package selenium.testcase;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import selenium.page.App;

import java.security.PublicKey;
import java.util.concurrent.TimeUnit;

public class TestWeWork {
    public static App app;
    @BeforeClass
    public static void beforeAll(){
        app = new App();
        app.loginWithCookie();
        app.toContact().delete("Anna");
    }

    @Test
    public void add(){
        String username = "Anna";
        String id = "Anna123";
        String phone = "11111111111";
        app.toMemberAdd().add(username, id, phone);
    }

/*
    @Test
    public void delete(){
        String username = "Becky";
        String id = "Becky123";
        String phone = "11111111112";
        app.toMemberAdd().add(username, id, phone).delete(phone);
    }
*/

/*    @Test
    public void deleteCurrentPage(){
        app.toContact().deleteCurrentPage();
    }*/

    @Test
    public void importFromFile() throws InterruptedException {
        app.toContact().importFromFile("C:\\Users\\maiki\\Downloads\\通讯录批量导入模板.xlsx");
    }

/*    @AfterClass
    public static void afterAll(){
        app.quit();

    }*/

}
