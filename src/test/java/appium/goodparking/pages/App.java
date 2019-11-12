package appium.goodparking.pages;

import org.openqa.selenium.By;

/**
 * @ClassName HomePage
 * @Description 好停车首页
 * @Author wangx
 * @Date 2019/11/11 12:08
 * @Version 1.0
 **/

public class App extends BasePage {

    public static LoginPage toLogin() { // 去登录界面
        findElementAndClick(By.id("com.innotek.goodparking:id/iv_left"));
        return new LoginPage();
    }

    public static Boolean isLogin() { // 首页上是否有登录按钮，判断是否已登录(没有该按钮说明已登录，返回true，有则返回false)
        return (!isElement(By.xpath("//*[@text='立即登录']")));
    }

    public static VehiclePage toVehicle(){  // 去我的车辆页面
        if (!isLogin()){ // 如果没登录，则先登录
            LoginPage loginPage = new LoginPage();
            loginPage.passwordLogin("18668067951","123456");
        }
        findElementAndClick(By.id("com.innotek.goodparking:id/iv_left"));
        findElementAndClick(By.id("com.innotek.goodparking:id/tvMyPlate"));
        return new VehiclePage();
    }

    public static SearchPage toSearch(){
        findElementAndClick(By.id("com.innotek.goodparking:id/tv_location_addr"));
        return new SearchPage();
    }

    public static void logout() {  // 登出操作
        findElementAndClick(By.id("com.innotek.goodparking:id/iv_left"));
        scrollToElementForVertical("com.innotek.goodparking:id/scrollview", "系统设置");
        findElementAndClick(By.id("com.innotek.goodparking:id/tvSetting"));  // 设置按钮
        findElementAndClick(By.id("com.innotek.goodparking:id/llLogout"));  // 退出登录按钮
        findElementAndClick(By.id("com.innotek.goodparking:id/tv_comfirm"));  // 确定按钮
        findElementAndClick(By.id("com.innotek.goodparking:id/iv_back"));  // 返回按钮，返回到首页
    }


}
