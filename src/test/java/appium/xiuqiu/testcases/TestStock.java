package appium.xiuqiu.testcases;

import appium.xiuqiu.pages.App;
import appium.xiuqiu.pages.StockPage;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.jupiter.params.provider.Arguments.arguments;

/**
 * @ClassName TestStock
 * @Description TODO
 * @Author wangx
 * @Date 2019/11/9 14:37
 * @Version 1.0
 **/

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TestStock {
    private static StockPage stockPage;

    @BeforeAll
    static void beroreAll() {
        App.startApp();
        stockPage=App.toStock();
    }

    @Test
    @Order(1)
    void addDefaultSelectedStocks() {
        if(stockPage.getAllStocks().size()>=1){
            stockPage.deleteAll();
        }
        assertThat(stockPage.addDefaultSelectedStocks().getAllStocks().size(), greaterThanOrEqualTo(6));

    }

    @Order(2)
    @ParameterizedTest
    @MethodSource("stockData")
    void addStock(String code, String name) {
        stockPage.toSearch().search(code).select().cancel();
        assertThat(stockPage.getAllStocks(), hasItem(name));
    }

    static Stream<Arguments> stockData(){
        return Stream.of(
                arguments("pdd","拼多多"),
                arguments("xiaomi","小米集团-W")
        );
    }

    @AfterAll
    static void tearDown() throws Exception {
        Thread.sleep(5000);
        App.quit();
    }
}
