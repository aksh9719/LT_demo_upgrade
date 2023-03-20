package com.lambdaTestApp.android;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.net.MalformedURLException;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Android1 {

        String userName = System.getenv("LT_USERNAME") == null ? "Your LT Username" : System.getenv("LT_USERNAME");
        String accessKey = System.getenv("LT_ACCESS_KEY") == null ? "Your LT AccessKey" : System.getenv("LT_ACCESS_KEY");
        private String Status = "failed";
        @Test
        public void basicTest() throws IOException,InterruptedException {
//                upload();

                DesiredCapabilities caps = new DesiredCapabilities();
                HashMap<String, Object> ltOptions = new HashMap<String, Object>();
                ltOptions.put("w3c", true);
                ltOptions.put("platformName", "android");
                ltOptions.put("deviceName", "Galaxy S20");
                ltOptions.put("platformVersion", "11");
                ltOptions.put("isRealMobile", true);
                ltOptions.put("build", "Android");
                ltOptions.put("name", "Single Test");
                ltOptions.put("app", "android_appurl");
                caps.setCapability("lt:options", ltOptions);

         try{
                AndroidDriver<AndroidElement> driver = new AndroidDriver<AndroidElement>(
                        new URL("https://" + userName + ":" + accessKey + "@mobile-hub.lambdatest.com/wd/hub"),
                        caps);
                WebDriverWait wait = new WebDriverWait(driver, 10);
                AndroidElement searchElement = (AndroidElement) wait
                        .until(ExpectedConditions
                                .elementToBeClickable(MobileBy.AccessibilityId("Search Wikipedia")));
                searchElement.click();
                AndroidElement insertTextElement = (AndroidElement) wait
                        .until(ExpectedConditions.elementToBeClickable(
                                MobileBy.id("org.wikipedia.alpha:id/search_src_text")));
                insertTextElement.sendKeys("LambdaTest");
                Thread.sleep(5000);

                List<AndroidElement> allProductsName = driver.findElementsByClassName("android.widget.TextView");
                assert (allProductsName.size() > 0);

                Status = "passed";
                driver.executeScript("lambda-status=" + Status);
                // The driver.quit statement is required, otherwise the test continues to
                // execute, leading to a timeout.
                driver.quit();
            } catch (Throwable t) {
                System.out.println(t);
            }
        }
}
