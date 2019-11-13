package selenium.testcase;

import org.junit.*;

import static java.io.File.separator;

import selenium.page.App;

import java.net.URL;


public class TestWeWork {
    private static App app;

    @BeforeClass
    public static void setUp() throws Exception {
        app = new App();
        app.loginWithCookie();
    }

    @Before
    public void thisWait() throws InterruptedException {
        Thread.sleep(3000);
    }

    @Test
    public void del() throws Exception {
        String name="15305712197";
        app.toMemberAdd().add(name, name, name).del(name);  // 添加删除成员
    }

    @Test
    public void delOnePage(){
        app.toContact().delOnePage();
    }

    @Test
    public void importFromFile(){
        String file_path=this.getClass().getResource("/selenium/通讯录批量导入模板.xlsx").getPath();
        app.toContact().importFromFile(file_path);
    }

    @AfterClass
    public static void tearDown() throws Exception {

        Thread.sleep(5000);
        app.quit();

    }


}
