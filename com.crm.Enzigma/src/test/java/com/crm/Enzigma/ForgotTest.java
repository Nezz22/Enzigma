package com.crm.Enzigma;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;


public class ForgotTest extends BaseClass {
	
	@BeforeMethod
	public void signUp() {
		String expected_url = "Verification code sent successfully";
		String expectedMessage = "Password updated successfully";
		//navigate to forget password page
		driver.findElement(By.xpath("/html/body/app-root/login/abx-auth-container/div/div[2]/div/abx-login/div/a")).click();
		
		WebElement email =driver.findElement(By.xpath("/html/body/abx-modal/section/div/div/abx-forgot-password/div/div/div/abx-tabs/div/div/abx-tab[1]/div/div/abx-form/div/div/abx-container/div/div/div/span/abx-layout-item/div/abx-field/div/div/div/div[1]/abx-email/input"));
		// Valid Email Test Case
        System.out.println("Testing with a valid registered email:");
        email.clear();
        email.sendKeys("npande787@gmail.com"); // Registered email
        driver.findElement(By.xpath("//div[text()='Proceed']")).click();
        SoftAssert soft = new SoftAssert();
		 //assertEqual
        WebElement message = driver.findElement(By.xpath("//h2[text()='Verification code sent successfully']"));
        String actual_url =message.getText();
		soft.assertEquals(expected_url, actual_url,"Verification failed");
		System.out.println("verification successful");
		
		//now set the new password
		WebElement newPassword = driver.findElement(By.xpath("/html/body/abx-modal/section/div/div/abx-forgot-password/div/div/div/abx-form/abx-field/div/div/div/div[1]/abx-password/div/div[1]/input"));
		newPassword.sendKeys("Pass#123");
		WebElement confirmPassword = driver.findElement(By.xpath("/html/body/abx-modal/section/div/div/abx-forgot-password/div/div/div/abx-form/abx-field/div/div/div/div[1]/abx-password/div/div[2]/input"));
		confirmPassword.sendKeys("Pass#123");
		WebElement successMessage = driver.findElement(By.xpath("//h2[text()='Password updated successfully']"));
		String actualMessage =successMessage.getText();
		soft.assertEquals(expectedMessage, actualMessage,"Invalid password");
		System.out.println("Password updated successfully");
		
		
		
		 // Non-Registered Email Test Case
        System.out.println("\nTesting with a non-registered email:");
        email.clear();
        email.sendKeys("wpradip0001@gmail.com"); // Non-registered email
        driver.findElement(By.xpath("//div[text()='Proceed']")).click();

        // Verify error message for non-registered email (if any)
        try {
            WebElement errorMessage = driver.findElement(By.xpath("//h2[text()='User does not exists']"));
            if (errorMessage.isDisplayed()) {
                System.out.println("Error: " + errorMessage.getText());
            }
        } catch (Exception e) {
            System.out.println("Error: Error message for non-registered email .");
        }
        
        // Invalid Email Format Test Case
        System.out.println("\nTesting with an invalid email format:");
        email.clear();
        email.sendKeys("wpradip001@gmail"); // Invalid email format
        driver.findElement(By.xpath("//div[text()='Proceed']")).click();

        // Verify error message for invalid email format
        try {
            WebElement errorMessage = driver.findElement(By.xpath("//h2[text()='Please enter a valid email']"));
            if (errorMessage.isDisplayed()) {
                System.out.println("Error: " + errorMessage.getText());
            }
        } catch (Exception e) {
            System.out.println("Error: Error message for invalid email format .");
        }
     // Blank Email Test Case
        System.out.println("\nTesting with a blank email field:");
        email.clear();
        email.sendKeys("");
        driver.findElement(By.xpath("//div[text()='Proceed']")).click();

        // Verify error message for blank email field
        try {
            WebElement errorMessage = driver.findElement(By.xpath("//h2[text()='Please enter email']"));
            if (errorMessage.isDisplayed()) {
                System.out.println("Error: " + errorMessage.getText());
            }
        } catch (Exception e) {
            System.out.println("Error: Error message for blank email .");
        }
	}
}
