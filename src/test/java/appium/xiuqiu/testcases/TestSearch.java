package appium.xiuqiu.testcases;

import appium.xiuqiu.pages.App;
import appium.xiuqiu.pages.SearchPage;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.IOException;
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
        App.getInstance().startApp();
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() throws IOException {
//        return Arrays.asList(new Object[][] {
//                { "alibaba", 100f },
//                { "xiaomi", 8f },
//                { "jd", 33f }
//        });
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        String path = "/" + TestSearch.class.getCanonicalName().replace(".", "/") + ".yaml";
        Object[][] demo = mapper.readValue(
                TestSearch.class.getResourceAsStream(path), Object[][].class);
        return Arrays.asList(demo);
    }


    @Parameterized.Parameter
    public String stock;

    @Parameterized.Parameter(1)
    public Double price;

    @Before
    public void before(){
        searchPage=App.getInstance().toSearch();
    }

    @Test
    public void search() {
        assertThat(searchPage.search(stock).getText(), greaterThanOrEqualTo(price.floatValue()));
    }

    @After
    public void after(){
        searchPage.cancel();
    }

    @AfterClass
    public static void tearDown() throws Exception {
        Thread.sleep(5000);
        App.getInstance().quit();
    }
}
