package appium.testcases;

import appium.pages.App;
import appium.pages.SearchPage;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;

/**
 * @ClassName TestSearch
 * @Description TODO
 * @Author wangx
 * @Date 2019/11/7 15:39
 * @Version 1.0
 **/

@RunWith(Parameterized.class)
public class TestSearch {
    private static SearchPage searchPage;

    @BeforeClass
    public static void setUp() {
        App.startApp();
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data(){
        return Arrays.asList(new Object[][] {
                { "alibaba", 100f },
                { "xiaomi", 8f },
                { "jd", 33f }
        });
    }


    @Parameterized.Parameter
    public String stock;

    @Parameterized.Parameter(1)
    public Float price;

    @Before
    public void before(){
        searchPage=App.toSearch();
    }

    @Test
    public void search() {
        assertThat(searchPage.search(stock).getText(), greaterThanOrEqualTo(price));
    }

    @After
    public void after(){
        searchPage.cancel();
    }

    @AfterClass
    public static void tearDown() throws Exception {
        Thread.sleep(5000);
        App.quit();
    }
}
