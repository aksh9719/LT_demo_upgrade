package com.lambdaTestApp.iOS;

import java.io.IOException;
import java.net.URL;
import java.net.MalformedURLException;
import java.util.HashMap;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;

import io.appium.java_client.MobileBy;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

//import static com.lambdaTestApp.iOS.AppUpload.upload1;

public class iOS1 {
        public static String username = System.getenv("LT_USERNAME");
        public static String accessKey = System.getenv("LT_ACCESS_KEY");
        private String Status = "failed";

        @Test
        public void basicTest() throws IOException, InterruptedException {
//                upload1();
                DesiredCapabilities capabilities = new DesiredCapabilities();
                HashMap<String, Object> ltOptions = new HashMap<String, Object>();
                ltOptions.put("w3c", true);
                ltOptions.put("build", "iOSAppAutomation_LT");
                ltOptions.put("name", "iOS_Test");
                ltOptions.put("platformName", "iOS");
                ltOptions.put("deviceName", "iPhone.*");
                ltOptions.put("browserName", "Safari");
                ltOptions.put("browserVersion", "latest");
                ltOptions.put("isRealMobile", true);
                ltOptions.put("platformVersion", "15.0");
                ltOptions.put("console", true);
                ltOptions.put("visual", true);
                ltOptions.put("app", "iOS_appurl");

                capabilities.setCapability("lt:options", ltOptions);

           try{
                IOSDriver<IOSElement> driver = new IOSDriver<IOSElement>(
                                new URL("https://" + username + ":" + accessKey + "@mobile-hub.lambdatest.com/wd/hub"),
                                capabilities);


               IOSElement colour = (IOSElement) new WebDriverWait(driver, 30).until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId("color")));
               colour.click();

               IOSElement text = (IOSElement) new WebDriverWait(driver, 30).until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId("Text")));
               text.click();

               IOSElement toast = (IOSElement) new WebDriverWait(driver, 30).until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId("toast")));
               toast.click();

               IOSElement notification = (IOSElement) new WebDriverWait(driver, 30).until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId("notification")));
               notification.click();

               IOSElement geoLocation = (IOSElement) new WebDriverWait(driver, 30).until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId("geoLocation")));
               geoLocation.click();
                Status = "passed";

                driver.executeScript("lambda-status=" + Status);
                // The driver.quit statement is required, otherwise the test continues to
                // execute, leading to a timeout.
                driver.quit();
           }
           catch (Throwable t) {
             System.out.println(t);
           }
        }
}
