package appium.goodparking.testcases;

import appium.goodparking.pages.App;
import appium.goodparking.pages.SearchPage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

/**
 * @ClassName TestSearch
 * @Description 搜索测试
 * @Author wangx
 * @Date 2019/11/12 10:43
 * @Version 1.0
 **/

public class TestSearch {
    private static SearchPage searchPage;

    @BeforeAll
    static void beroreAll() {
        App.startApp();
        searchPage=App.toSearch();
    }

    @Test
    void searchTest(){
        assertThat(searchPage.search("物美(万达广场)").getListOneText(), equalTo("物美(万达广场)"));
    }


    @AfterAll
    static void tearDown() throws Exception {
        Thread.sleep(5000);
        App.quit();
    }

}
