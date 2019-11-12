package appium.goodparking.testcases;

import appium.goodparking.pages.App;
import appium.goodparking.pages.LoginPage;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.params.provider.Arguments.arguments;

/**
 * @ClassName TestLogin
 * @Description 登录测试用例
 * @Author wangx
 * @Date 2019/11/11 15:59
 * @Version 1.0
 **/

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestLogin {
    private static LoginPage loginPage;

    @BeforeAll
    static void beroreAll() {
        App.startApp();
        if (App.isLogin()){
            App.logout();
        }
        loginPage=App.toLogin();
    }

    @Order(1)
    @ParameterizedTest
    @MethodSource("errorMessage")
    // 登录时的错误提示验证
    void errorLoginTest(String username, String password, String message) {
        assertThat(loginPage.passwordLogin(username, password).
                errorLoginToastText(), equalTo(message));
    }

    static Stream<Arguments> errorMessage(){
        return Stream.of(
                arguments("18668067951","123456789", "账号或者密码不正确"),
                arguments("18668067951","12345",  "请检查密码位数")
        );
    }

    @Test
    @Order(2)
    void succeedLoginTest(){  // 验证密码登录流程
        loginPage.passwordLogin("18668067951", "123456");
        assertTrue(App.isLogin());
    }


    @AfterAll
    static void tearDown() throws Exception {
        Thread.sleep(5000);
        App.quit();
    }


}
