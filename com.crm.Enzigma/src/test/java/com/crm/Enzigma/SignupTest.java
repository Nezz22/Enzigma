package com.crm.Enzigma;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.nokodr.base.BaseClass;


public class SignupTest extends BaseClass {
	
    WebElement firstName;
    WebElement lastName;
    WebElement password;
    WebElement confirmPassword;
    WebElement registerButton;
	@BeforeMethod
	public void signUp() {
		
		driver.findElement(By.xpath("//a[text()='Sign up']")).click();
		
		WebElement email= driver.findElement(By.xpath("/html/body/abx-modal/section/div/div/abx-sign-up/div/div/div[2]/span/abx-tabs/div/div/abx-tab[1]/div/div/abx-form/div/div/abx-container/div/div/div/span[1]/abx-layout-item/div/abx-field/div/div/div/div[1]/abx-email/input"));
		email.sendKeys("npande787@gmail.com");
		driver.findElement(By.xpath("/html/body/abx-modal/section/div/div/abx-sign-up/div/div/div[2]/span/abx-tabs/div/div/abx-tab[1]/div/div/abx-form/div/div/abx-container/div/div/div/span[2]/abx-layout-item/div/abx-field/div/div/div/div[1]/abx-checkbox/div/label/span")).click();
		driver.findElement(By.xpath("//div[text()='Proceed']")).click();
		
		
		
        firstName = driver.findElement(By.xpath("//input[@name='firstName']"));
        lastName = driver.findElement(By.xpath("//input[@name='lastName']"));
        password = driver.findElement(By.xpath("/html/body/abx-modal/section/div/div/user-details/abx-form/abx-field[3]/div/div/div/div[1]/abx-password/div/div[1]/input"));
        confirmPassword = driver.findElement(By.xpath("/html/body/abx-modal/section/div/div/user-details/abx-form/abx-field[3]/div/div/div/div[1]/abx-password/div/div[3]/input"));
        registerButton = driver.findElement(By.xpath("//div[text()='Register']"));
        
	}