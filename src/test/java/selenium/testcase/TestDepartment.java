package selenium.testcase;

import org.junit.*;
import org.junit.runners.MethodSorters;
import selenium.page.App;
import selenium.page.DepartmentPage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;


/**
 * @ClassName TestDepartment
 * @Description TODO
 * @Author wangx
 * @Date 2019/11/3 20:19
 * @Version 1.0
 **/

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestDepartment {
    private static App app;
    private static final String parent = "不迟随笔";
    private static final String child = "部门1";
    private static final String child2 = "部门2";
    private static final String new_child = "部门_已改名";

    @BeforeClass
    public static void setUp() throws Exception{
        app=new App();
        app.loginWithCookie();
    }

    @Before
    public void beforeWait() throws InterruptedException {
        Thread.sleep(1000);
    }

    @Test
    public void test_1_add() throws InterruptedException {  // 添加子部门测试
        DepartmentPage this_app = app.toDepartment();
        this_app.addChildDepartment(parent, child);
        this_app.addChildDepartment(parent, child2);
    }

    @Test
    public void test_4_del() throws InterruptedException {  // 删除子部门
        DepartmentPage this_app = app.toDepartment();

        this_app.delDepartment(new_child);
        this_app.delDepartment(child2);
    }

    @Test
    public void test_2_change() {  // 修改部门名字
        app.toDepartment().changeName(child, new_child);
    }


    @Test
    public void test_3_moveUp() {  // 上移部门
        DepartmentPage this_app = app.toDepartment();
        this_app.moveUp(child2);
        // 验证是否上移成功（不在最底下的会有个下移按钮，有下移按钮说明上移成功）
        assertTrue(this_app.clickList(child2, "下移"));

    }



    @AfterClass
    public static void tearDown() throws Exception {
        Thread.sleep(5000);
        app.quit();
    }
}
