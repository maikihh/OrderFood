package selenium.testcase;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import selenium.page.App;

import java.util.List;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestBrocadcast {
    public static App app;
    @BeforeClass
    public static void beforeAll(){
        app = new App();
        app.loginWithCookie();

    }
    @Test
    public void send() {
        String title = "通知";
        List<String> message= app.toBrocadcastMessage()
                .send("黄慧", title, "您的快递已到达深圳市", "物流通知", "管理员")
                .getSendedMessage().subList(0,3);
        System.out.println(message);
        //断言获取的是否包含标题
        assertThat(message, hasItem(title));
    }

/*    @AfterClass
    public static void afterAll(){
        app.quit();
    }*/
}
