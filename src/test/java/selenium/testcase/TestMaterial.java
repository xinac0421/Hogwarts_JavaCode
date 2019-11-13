package selenium.testcase;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import selenium.page.App;

import static java.io.File.separator;

/**
 * @ClassName TestMaterial
 * @Description 素材库测试
 * @Author wangx
 * @Date 2019/11/4 11:08
 * @Version 1.0
 **/

public class TestMaterial {
    private static App app;
    private static final String pic_path=TestMaterial.class.getResource("/selenium/test.jpg").getPath();

    @BeforeClass
    public static void setUp() throws Exception{
        app=new App();
        app.loginWithCookie();
    }


    @Test
    public void uploadPic() {  // 上传图片
        app.toMaterial().uploadPic(pic_path);
    }

    @Test
    public void addPicAndText() {  // 添加封面图
        String title="测试标题1"+System.currentTimeMillis();
        String body="Hogwarts School of Witchcraft and Wizardry";
        app.toMaterial().addPicAndText(title, body, pic_path);

    }



    @AfterClass
    public static void tearDown() throws Exception {
        Thread.sleep(5000);
        app.quit();
    }



}
