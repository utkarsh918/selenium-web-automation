package com.automation;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class App {
    public static void main(String[] args) throws InterruptedException { 
        
        System.out.println("🚀 Mission Start: Final Project Running...");

        ChromeOptions options = new ChromeOptions();
        
        
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        options.setExperimentalOption("prefs", prefs);

        
        options.addArguments("--disable-features=PasswordLeakDetection");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");

        
        WebDriver driver = new ChromeDriver(options);
        
        
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
        driver.manage().window().maximize(); 

    
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.name("user-name")).sendKeys("standard_user");
        driver.findElement(By.name("password")).sendKeys("secret_sauce");
        driver.findElement(By.name("login-button")).click();
        
        
        try {
            
            Alert alert = driver.switchTo().alert();
            alert.accept();
            System.out.println("⚠️ Popup aaya tha, maine hata diya.");
        } catch (NoAlertPresentException e) {
            
            System.out.println("✅ Koi Popup nahi aaya, rasta saaf hai.");
        }

      
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        
    
        driver.findElement(By.className("shopping_cart_link")).click();
        
        
        driver.findElement(By.id("checkout")).click();

        
        driver.findElement(By.id("first-name")).sendKeys("Utkarsh");
        driver.findElement(By.id("last-name")).sendKeys("Rawat");
        driver.findElement(By.id("postal-code")).sendKeys("303020");
        
        
        driver.findElement(By.id("continue")).click();

        
        driver.findElement(By.id("finish")).click();

    
        String message = driver.findElement(By.className("complete-header")).getText();
        
        if (message.contains("Thank you")) {
            System.out.println(" Order Successfully Placed!");
        } else {
            System.out.println(" Order confirm nahi hua.");
        }
    
        Thread.sleep(5000);
        driver.quit();
        System.out.println("Browser Closed");
    }
}