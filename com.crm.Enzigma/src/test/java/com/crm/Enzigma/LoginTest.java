package com.crm.Enzigma;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;



public class LoginPageTest {
	    
	@DataProvider(name = "Login")
	public Object[][] testValidLogin() throws EncryptedDocumentException, IOException
	{
		FileInputStream fis = new FileInputStream("D:\\Enzigma\\Login.xlsx");
			Workbook wb = WorkbookFactory.create(fis);
			Sheet sheet = wb.getSheet("Sheet2");
			int row = sheet.getPhysicalNumberOfRows();
			int colom = sheet.getRow(0).getPhysicalNumberOfCells();
			Object [][]obj = new Object[row][colom];
			for (int i = 0; i < row; i++) {
				for (int j = 0; j < colom; j++) {
					obj [i][j]= sheet.getRow(i).getCell(j).toString();
				}
			}
			return obj;
	}
	@Test(dataProvider = "Login",threadPoolSize = 4)
	public void receiver(String username,String password) throws InterruptedException {
		String expected_url = "https://app-staging.nokodr.com/super/apps/user-profile/v1/index.html#/";
		ChromeDriver driver = new ChromeDriver();
     	driver.manage().window().maximize();
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
	    driver.get("https://app-staging.nokodr.com/super/apps/auth/v1/index.html#/login");
		driver.findElement(By.xpath("//input[@type='email']")).sendKeys(username);
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@name='rememberMe']")).click();
        driver.findElement(By.xpath("//div[text()='Log In']")).click();
		
        // After submitting the form, check for error message for invalid credentials
        try {
            // Check for error message when both email and password are incorrect
            WebElement errorMessage = driver.findElement(By.xpath("//h2[contains(text(),'Invalid Email or Password')]"));
            String errorText = errorMessage.getText();

            // Check if the error message is displayed and matches the expected message
            if (errorText.contains("Invalid Email or Password")) {
                System.out.println("Error: " + errorText);  // Print the error message
            } else {
                System.out.println("Unexpected error message: " + errorText);
            }
        } catch (Exception e1) {
            try {
                // Check for error when only email is incorrect
                WebElement emailErrorMessage = driver.findElement(By.xpath("//h2[contains(text(),'Please enter a valid email')]"));
                String emailErrorText = emailErrorMessage.getText();
                if (emailErrorText.contains("Please enter a valid email")) {
                    System.out.println("Error: " + emailErrorText);  // Print the error message
                } else {
                    System.out.println("Unexpected error message: " + emailErrorText);
                }
                System.out.println("Email Error: " + emailErrorText);
            } catch (Exception e2) {
                try {
             
                    WebElement passwordErrorMessage = driver.findElement(By.xpath("//h2[contains(text(),'Please enter password')]"));
                    String passwordErrorText = passwordErrorMessage.getText();
                    if (passwordErrorText.contains("Please enter email")) {
                        System.out.println("Error: " + passwordErrorText);  // Print the error message
                    } else {
                        System.out.println("Unexpected error message: " + passwordErrorText);
                    }
                    System.out.println("Password Error: " + passwordErrorText);
                } catch (Exception e3) {
                    String actual_url = driver.getCurrentUrl();
                    if (expected_url.equals(actual_url)) {
                        System.out.println("Success: Logged in successfully!");
                    } else {
                        System.out.println("Error: Login failed.");
                    }
                }
            }
        }
        driver.quit();
	}
}
