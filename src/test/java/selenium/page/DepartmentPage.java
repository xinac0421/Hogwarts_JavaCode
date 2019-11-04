package selenium.page;

import org.openqa.selenium.By;

/**
 * @ClassName DepartmentPage
 * @Description 通信录部门操作
 * @Author wangx
 * @Date 2019/11/3 19:44
 * @Version 1.0
 **/

public class DepartmentPage extends BasePage {
    public DepartmentPage addChildDepartment(String parent, String child) throws InterruptedException { //添加子部门
        /*
        findElement(By.id("memberSearchInput")).clear();
        findElement(By.id("memberSearchInput")).sendKeys(parent);
        boolean flag = false;  // 设置标志，表示是否找到部门
        for (int i = 0; i < 3; i++) {  // 等待3秒，
            //System.out.println(findElement(By.id("party_name")).getText());
            if (findElement(By.id("party_name")).getText().equals(parent)){
                flag = true;
                break;
            }else{
                Thread.sleep(1000);
            }
        }
        if (!flag) {  // 没找到部门则返回null
            System.out.println("没有找到部门："+ parent);
            return null;
        }
        // 点击添加子部门按钮
        findElement(By.cssSelector(".js_add_sub_party")).click();
        findElement(By.name("name")).sendKeys(child);
        findElement(By.linkText("确定")).click();
        */
        String parent_xpath = String.format("//a[contains(text(),'%s')]", parent);
        waitVisibilityAndClickable(By.xpath(parent_xpath));
        retryElementClick(By.xpath(parent_xpath));
        //findElement(By.xpath(parent_xpath)).click();
        findElement(By.cssSelector(".js_add_sub_party")).click();
        findElement(By.name("name")).sendKeys(child);
        findElement(By.linkText("确定")).click();

        return this;
    }

    public DepartmentPage delDepartment(String name) throws InterruptedException {
        clickList(name, "删除");
        findElement(By.linkText("确定")).click();

        return this;
    }

    public DepartmentPage changeName(String oldName, String newName){
        clickList(oldName, "修改名称");

        findElement(By.name("name")).clear();
        findElement(By.name("name")).sendKeys(newName);
        findElement(By.linkText("保存")).click();
        return this;
    }

    public DepartmentPage moveUp(String name){  // 上移操作
        clickList(name, "上移");
        return this;
    }

    public boolean clickList(String delDepartmentName, String optionName) {
        // 操作列表公共方法(点击操作,若操作按钮不存在，则返回false)
        String test_xpath = String.format("//a[contains(text(),'%s')]", delDepartmentName);
        String span_xpath = String.format("//a[contains(text(),'%s')]/span", delDepartmentName);
        String option_xpath = String.format("//ul[@class='vakata-context jstree-contextmenu jstree-default-contextmenu']" +
                "//a[contains(text(),'%s')]", optionName);
        try {
            retryElementClick(By.xpath(test_xpath));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        findElement(By.xpath(span_xpath)).click();
        // 判断按钮是否存在
        if (isHasElement(By.xpath(option_xpath))){
            findElement(By.xpath(option_xpath)).click();
            return true;
        }else{
            return false;
        }
    }



}
