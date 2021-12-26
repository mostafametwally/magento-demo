package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import base.Base;

public class HomePage extends Base {
	
    By Women		= By.xpath("//*[@id=\"nav\"]//a");
    By Dress_Skirts = By.linkText("Dresses & Skirts");
    By View_Details = By.xpath("//a[@title=\"View Details\"]");    
    By Add_To_Cart  = By.xpath("//button[@title=\"Add to Cart\"]");
    By Total_Grant  = By.xpath("//*[@id=\"shopping-cart-totals-table\"]//strong//span");
    By Account  	= By.xpath("//span[contains(text(),\"Account\")]");
    By LoginPage	= By.xpath("//a[contains(text(),\"Log In\")]");
    By Email		= By.id("email");
    By Password     = By.id("pass");
    By LoginBtn		= By.id("send2");
    
	WebDriverWait wait;
	Actions action;
	
	public HomePage() {
		wait = new WebDriverWait(driver, 5);
		action = new Actions(driver);		
	}	
	
	public void navigatetoHomePage(String url) {
		driver.navigate().to(prop.getProperty(url));
	}
	public void login(String email, String password) {
		navigatetoHomePage("HomePageUrl");
		driver.findElement(Account).click();
		driver.findElement(LoginPage).click();
		driver.findElement(Email).sendKeys(email);
		driver.findElement(Password).sendKeys(password);
		driver.findElement(LoginBtn).click();
				
	}
	public float getGrantToatal(String username, String password ){
		Select Color;
		Select Size;
		login(username, password);
		driver.findElement(Women).click();
		action.moveToElement(driver.findElement(Women)).perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(Dress_Skirts));
		driver.findElement(Dress_Skirts).click();
		driver.findElement(View_Details).click();
		Color= new Select(driver.findElement(By.id("attribute92")));		
		Color.selectByVisibleText("Silver");
		Size= new Select(driver.findElement(By.id("attribute180")));
		Size.selectByVisibleText("10");
		driver.findElement(Add_To_Cart).click();	
		float price =Float.parseFloat(driver.findElement(Total_Grant).getText().replace("$",""));
		return price;
	}	
}


