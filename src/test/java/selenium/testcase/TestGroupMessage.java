package selenium.testcase;

/**
 * @ClassName TestGroupMessage
 * @Description TODO
 * @Author wangx
 * @Date 2019/11/2 21:06
 * @Version 1.0
 **/

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import selenium.page.App;

import java.util.List;

import static org.hamcrest.Matchers.hasItem;
import static org.junit.Assert.assertThat;

public class TestGroupMessage {
    private static App app;

    @BeforeClass
    public static void setUp() throws Exception{
        app=new App();
        app.loginWithCookie();
    }

    @Test
    public void send() throws InterruptedException {
        String title="你的快递已到"+System.currentTimeMillis();
        List<String> sendedMsg = app.toGroupMessage()
                .send("王鑫", title, title + title,
                        "快递通知", "霍格沃兹测试学院")
                .getSendedMsg().subList(0, 5);
        System.out.println(sendedMsg);
        assertThat(sendedMsg, hasItem(title));


    }

    @AfterClass
    public static void tearDown() throws Exception {
        Thread.sleep(5000);
        app.quit();

    }
}
