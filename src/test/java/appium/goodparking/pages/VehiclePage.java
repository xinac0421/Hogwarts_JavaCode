package appium.goodparking.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName VehiclePage
 * @Description 我的车辆页面
 * @Author wangx
 * @Date 2019/11/12 17:48
 * @Version 1.0
 **/

public class VehiclePage extends BasePage{
    public VehiclePage deleteAll(){  // 删除所有车牌
        int num = findElements(By.id("com.innotek.goodparking:id/tv_plateNo")).size();
        if (num >0){
            for (int i = 0; i < num; i++) {
                new WebDriverWait(driver,30).
                        until(ExpectedConditions.visibilityOfElementLocated
                                (By.id("com.innotek.goodparking:id/tv_plateNo")));
                findElement(By.id("com.innotek.goodparking:id/tv_plateNo"));
                scrollToElementForHorizo​​ntal("com.innotek.goodparking:id/tv_plateNo","删除");
                findElementAndClick(By.xpath("//*[@text='删除']"));
                String comfrim = "com.innotek.goodparking:id/tv_comfirm";  // 确认框按钮
                if (isElement(By.id(comfrim))){
                    // 已认证过的车牌删除会弹框，其他则不弹
                    findElementAndClick(By.id(comfrim));
                }
            }
        }

        return this;
    }

    public VehiclePage add(String plateNo){  // 新增车牌
        findElementAndClick(By.id("com.innotek.goodparking:id/itemText"));
        findElementAndClick(By.id("com.innotek.goodparking:id/etPlate"));  // 点击输入框弹出内置键盘
        keyBoardInPut(plateNo);
        backDevice(); // 回退，内置键盘缩回
        //clickPoint(0.5, 0.2);  // 需点击屏幕上半部分，内置键盘才会缩回，不然无法获得到其他元素
        findElementAndClick(By.id("com.innotek.goodparking:id/btnBind"));
        findElementAndClick(By.id("com.innotek.goodparking:id/tv_cancel"));  // 认证弹框点击取消
        return this;
    }

    public List<String> getPlateNo(){
        List<String> plateNo=new ArrayList<>();
        findElements(By.id("com.innotek.goodparking:id/tv_plateNo")).forEach(element -> {
            plateNo.add(((WebElement)element).getText().replace("·",""));
        });
        System.out.println(plateNo);
        return plateNo;
    }

}
