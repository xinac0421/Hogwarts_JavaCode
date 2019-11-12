package appium.goodparking.testcases;

import appium.goodparking.pages.App;
import appium.goodparking.pages.VehiclePage;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.jupiter.params.provider.Arguments.arguments;

/**
 * @ClassName TestVehicle
 * @Description 车辆
 * @Author wangx
 * @Date 2019/11/12 18:08
 * @Version 1.0
 **/

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestVehicle {
    private static VehiclePage vehiclePage;

    @BeforeAll
    static void beroreAll() {
        App.startApp();
        vehiclePage=App.toVehicle();
    }

    @Test
    @Order(1)
    void deleteAllTest(){
        vehiclePage.deleteAll();
    }

    @Order(2)
    @ParameterizedTest
    @MethodSource("plateData")
    void addTest(String plateNo){
        vehiclePage.add(plateNo);
        assertThat(vehiclePage.getPlateNo(), hasItem(plateNo));
    }

    static Stream<Arguments> plateData(){
        return Stream.of(
                arguments("浙BAAAAA"),
                arguments("藏B54321"),
                arguments("藏B12345")
        );
    }


    @AfterAll
    static void tearDown() throws Exception {
        Thread.sleep(5000);
        App.quit();
    }
}
