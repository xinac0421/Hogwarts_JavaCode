package selenium.page;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName App
 * @Description TODO
 * @Author wangx
 * @Date 2019/10/30 21:28
 * @Version 1.0
 **/

public class App extends BasePage{
    public static String baseUrl = "https://work.weixin.qq.com/";


    public void loginWithCookie(){
        startChrome();
        driver.get(baseUrl);
        driver.manage().window().maximize();
        driver.manage().addCookie(new Cookie("wwrtx.refid","请改成自己的"));
        driver.manage().addCookie(new Cookie("wwrtx.sid",
                "请改成自己的"));
        findElement(By.linkText("企业登录")).click();

    }

    public ContactPage toContact(){  // 进入通讯录
        findElement(By.linkText("通讯录")).click();
        return new ContactPage();
    }

    public ContactPage toMemberAdd(){
        findElement(By.linkText("添加成员")).click();
        return new ContactPage();
    }

    public BroadcastPage toGroupMessage(){  // 进入管理工具的消息群发
        findElement(By.linkText("管理工具")).click();
        findElement(By.cssSelector(".ww_icon_AppGroupMessageBig")).click();
        return new BroadcastPage();
    }

    public DepartmentPage toDepartment(){  // 进入通信录界面
        findElement(By.linkText("通讯录")).click();
        return new DepartmentPage();
    }

    public MaterialPage toMaterial(){  // 进入素材库界面
        findElement(By.linkText("管理工具")).click();
        findElement(By.cssSelector(".ww_icon_AppMaterialBig")).click();
        return new MaterialPage();
    }


}
