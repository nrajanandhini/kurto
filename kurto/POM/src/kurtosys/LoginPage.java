package kurtosys;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage 

{
	WebDriver driver;
	
	public LoginPage(WebDriver driver)
	{
	this.driver= driver;	
	}
	
	//--------------------------------------------------------------------------------------
	//								Elements
	//**************************************************************************************
		
	@FindBy(how=How.XPATH, using="//div//p//a[@href='/Account/Login/']") WebElement loginLink;
	@FindBy(how=How.NAME, using= "username") WebElement username;
	@CacheLookup
	@FindBy(how=How.NAME, using= "password") WebElement password;
	@FindBy(how=How.XPATH, using= "//button[@name='returnUrl' or @type='submit']") WebElement login_btn;
	@FindBy(how=How.XPATH, using= "//li//a//i[@class='fa fa-user fa-fw']") WebElement logoutLink;
	@FindBy(how=How.XPATH, using= "//a[@class='dropdown-toggle'][@aria-expanded='true']") WebElement logouttoggle;
	@FindBy(how=How.XPATH, using= "//li//a[@href='/Account/LogOff']") WebElement logout;
	
	//--------------------------------------------------------------------------------------
	//								Methods
	//**************************************************************************************
		
	public void login_alert()
	{
		
		loginLink.click();

	}
	public void login_kurto(String un, String pwd)
	{
		username.sendKeys(un);
		password.sendKeys(pwd);
		login_btn.click();
		
	}
	
	public void logout_kurto()
	
	{	
		WebDriverWait wait= new WebDriverWait(driver, 10000);
		
		try {
			
		logoutLink.click();
		
		
	} catch (Exception e) {
		wait.until(ExpectedConditions.elementToBeClickable(loginLink));
		logoutLink.click();
		
		
	}
		
			
			
			
			wait.until(ExpectedConditions.visibilityOf(logouttoggle));
			logout.click();
	}
	
	}
