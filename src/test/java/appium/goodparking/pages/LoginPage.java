package appium.goodparking.pages;

import org.openqa.selenium.By;

/**
 * @ClassName LoginPage
 * @Description 登录页面
 * @Author wangx
 * @Date 2019/11/11 15:35
 * @Version 1.0
 **/

public class LoginPage extends BasePage{

    public LoginPage passwordLogin(String username, String password){  // 密码登录
        findElement(By.id("com.innotek.goodparking:id/login_et_phone")).clear();
        findElement(By.id("com.innotek.goodparking:id/login_et_phone")).sendKeys(username);
        findElement(By.id("com.innotek.goodparking:id/login_et_cap")).sendKeys(password);
        findElementAndClick(By.id("com.innotek.goodparking:id/login_btn_login"));
        return this;
    }

    public String errorLoginToastText(){ // 获取Toast的文本信息
        String text = findElement(By.xpath("//*[@class='android.widget.Toast']")).getText();
        System.out.println(text);
        return text;
    }






}
