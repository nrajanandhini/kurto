package kurtosysTestcases;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import kurtosys.reportsTemplatesDesign;
import kurtosys.LoginPage;
import kurtosys.accountHoldings;

public class testCases {
	WebDriver driver;
	String reportName="Test01", copyReportName="Copy", DelCopy="Test01Copy";
	
	@SuppressWarnings("deprecation")
	@BeforeTest
public void set_up()
{
		
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		System.setProperty("webdriver.chrome.driver", "/home/comp/Desktop/geckodriver_lin/chromedriver");
		driver = new ChromeDriver(capabilities);
		driver.manage().deleteAllCookies();
	    driver.get("http://www.google.com/");
	    driver.navigate().to("https://dev-engine.kurtosys.org");
	    
	  try 
	  {

		  driver.findElement(By.xpath("//div//p//a[@href='/Account/Login/']")).click();
		  
		  
	  } 
	  
	  catch (Exception e)
	  {
			Alert alert = driver.switchTo().alert();
			alert.accept();
			driver.findElement(By.xpath("//div//p//a[@href='/Account/Login/']")).click();
			 
			
		}
				
	
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
}

@Test(priority=1)
	
	public void loginLogout() throws Exception {
	
	
		
		// Using page factory, created login_page object
		LoginPage login_page= PageFactory.initElements(driver, LoginPage.class);
		//calling login method
		login_page.login_kurto("at_009", "TechGeek12!");

	 	
		login_page.logout_kurto();
		
	}

	
	@Test(priority=2)
	
	public void createReport() throws Exception {
	
	
		
		// Using page factory, created login_page object
		LoginPage login_page= PageFactory.initElements(driver, LoginPage.class);
		//calling login method
		login_page.login_kurto("at_009", "TechGeek12!");
		
		//Created menu navigation pages
		
		Thread.sleep(2000);
		reportsTemplatesDesign reportsTemplatesDesign= PageFactory.initElements(driver, reportsTemplatesDesign.class);
		reportsTemplatesDesign.navigate_Reports();
		Thread.sleep(3000);
		reportsTemplatesDesign.clk_Create_btn(reportName);
		Thread.sleep(2000);
		reportsTemplatesDesign.slt_Slt_Template();
		Thread.sleep(5000);
		reportsTemplatesDesign.chk_Report_Name_present(reportName);
		Thread.sleep(5000);
		assertEquals(reportsTemplatesDesign.chk_table_ReportName(reportName,"In Progress"), "pass");
		//assertNotNull(reportsTemplatesDesign.chk_Report_Name_present(reportName,"In Progress"));
	//	reportsTemplatesDesign.chk_Report_Name_present(reportName,"In Progress");
		Thread.sleep(3000);
		login_page.logout_kurto();
	
	}

	@Test(priority=3)
	
	public void editReport() throws Exception {
	
	
				//	 Using page factory, created login_page object
		LoginPage login_page= PageFactory.initElements(driver, LoginPage.class);
				//	calling login method
		login_page.login_kurto("at_009", "TechGeek12!");
				//	Created menu navigation pages
		Thread.sleep(2000);
		reportsTemplatesDesign reports_TemplatesDesign= PageFactory.initElements(driver, reportsTemplatesDesign.class);
		reports_TemplatesDesign.navigate_Reports();
		Thread.sleep(2000);
		reports_TemplatesDesign.edit_Report(reportName);
		Thread.sleep(2000);
		reports_TemplatesDesign.slt_Slt_Status();
		Thread.sleep(5000);
		assertEquals(reports_TemplatesDesign.chk_table_ReportName(reportName, "Ready To Assign"), "pass");
	//	reports_TemplatesDesign.chk_Report_Name_present(reportName ,"In Progress");
		Thread.sleep(2000);
		login_page.logout_kurto();
	
	}
	
	@Test(priority=4)
	
public void copyReport() throws Exception {
		
		
		//	 Using page factory, created login_page object
LoginPage login_page= PageFactory.initElements(driver, LoginPage.class);
		//	calling login method
login_page.login_kurto("at_009", "TechGeek12!");
		//	Created menu navigation pages
Thread.sleep(2000);
reportsTemplatesDesign reports_TemplatesDesign= PageFactory.initElements(driver, reportsTemplatesDesign.class);
reports_TemplatesDesign.navigate_Reports();
Thread.sleep(2000);
reports_TemplatesDesign.copy_Report(reportName, copyReportName);
Thread.sleep(5000);
assertEquals(reports_TemplatesDesign.chk_table_ReportName(DelCopy, "Ready To Assign"), "pass");
//reports_TemplatesDesign.chk_Report_Name_present(copyReportName ,"Ready To Assign");
Thread.sleep(2000);
login_page.logout_kurto();

}
	
	@Test(priority=5)

public void deleteReport() throws Exception {
	
	
	//	 Using page factory, created login_page object
LoginPage login_page= PageFactory.initElements(driver, LoginPage.class);
	//	calling login method
login_page.login_kurto("at_009", "TechGeek12!");
	//	Created menu navigation pages
Thread.sleep(2000);
reportsTemplatesDesign reports_TemplatesDesign= PageFactory.initElements(driver, reportsTemplatesDesign.class);
reports_TemplatesDesign.navigate_Reports();
Thread.sleep(2000);
reports_TemplatesDesign.delete_Report(reportName);
Thread.sleep(2000);
reports_TemplatesDesign.delete_Report(DelCopy);
Thread.sleep(2000);
reports_TemplatesDesign.chk_Report_Name_present(reportName);
Thread.sleep(2000);
assertEquals(reports_TemplatesDesign.chk_table_No_ReportName(), "pass");
Thread.sleep(2000);
reports_TemplatesDesign.chk_Report_Name_present(DelCopy);
Thread.sleep(2000);
assertEquals(reports_TemplatesDesign.chk_table_No_ReportName(), "pass");

//assertNull(reports_TemplatesDesign.chk_table_ReportName(reportName, "Ready To Assign"));
//assertNull(reports_TemplatesDesign.chk_table_ReportName(DelCopy, "Ready To Assign"));
Thread.sleep(2000);
login_page.logout_kurto();

}



	@Test(priority=6)
	
public void calculateNetWorth() throws Exception {
	
	
		
		// Using page factory, created login_page object
		LoginPage login_page= PageFactory.initElements(driver, LoginPage.class);
		//calling login method
		login_page.login_kurto("at_009", "TechGeek12!");
		
		//Created menu navigation pages
		
	
		Thread.sleep(2000);
		accountHoldings account_Holdings= PageFactory.initElements(driver, accountHoldings.class);
		account_Holdings.navigate_Bar();
		account_Holdings.navAccountHoldings();
		Thread.sleep(1000);
		account_Holdings.input_Account();
		Thread.sleep(1000);
		account_Holdings.slt_Account();
		account_Holdings.submit_Acc();
		Thread.sleep(10000);
		account_Holdings.sections_Clk();
		Thread.sleep(3000);
		assertEquals(account_Holdings.compare_Total(), "pass");
		Thread.sleep(3000);
		account_Holdings.navigate_Bar();
		Thread.sleep(1000);
		login_page.logout_kurto();
		
	
		
	}
	
	@AfterTest
	
	public void closeURL()
	
	{
		//driver.close();
	}
}
//