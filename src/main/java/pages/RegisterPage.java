package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.Base;

public class RegisterPage extends Base {
	
    By Account = By.xpath("//span[contains(text(),\"Account\")]");
    By RegisterPage		= By.xpath("//a[contains(text(),\"Register\")]");
    By FirstName  		= By.id("firstname");
    By LastName 		= By.id("lastname");
    By Email			= By.id("email_address");
    By PassWord			= By.id("password");
    By ConfimPassWord 	= By.id("confirmation");
    By SignUpCheckBox 	= By.id("is_subscribed");
    By SignUpBtn 		= By.xpath("//button[@type=\"submit\" and @title=\"Register\"]");
    By SignUpMsg		= By.xpath("//li[@class=\"success-msg\"]//span");
    
	WebDriverWait wait;
	public RegisterPage() {
		wait = new WebDriverWait(driver, 5);
		//action = new Actions(driver);		
	}	
	public void navigatetoHomePage(String url) {
		driver.navigate().to(prop.getProperty(url));
	}
	
	public String Registeration(String FN, String LN,String email, String PW, String ConfirmPW) {
		navigatetoHomePage("SignUpUrl");
		driver.findElement(Account).click();
		driver.findElement(RegisterPage).click();
		
		driver.findElement(FirstName).clear();
		driver.findElement(FirstName).sendKeys(FN);
		
		driver.findElement(LastName).clear();
		driver.findElement(LastName).sendKeys(LN);
		
		driver.findElement(Email).clear();
		driver.findElement(Email).sendKeys(email);
		
		driver.findElement(PassWord).clear();
		driver.findElement(PassWord).sendKeys(PW);
		
		driver.findElement(ConfimPassWord).clear();
		driver.findElement(ConfimPassWord).sendKeys(ConfirmPW);
		
		driver.findElement(SignUpCheckBox).click();
		driver.findElement(SignUpBtn).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(SignUpMsg));
		return driver.findElement(SignUpMsg).getText();
	}
}
