package kurtosys;


import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class reportsTemplatesDesign {
	
	WebDriver driver;
	
	public reportsTemplatesDesign (WebDriver driver)
	{
	this.driver= driver;	
		
	}
	
	//--------------------------------------------------------------------------------------
	//									Elements
	//**************************************************************************************
	
	
	@FindBy(how=How.XPATH, using= "//span[contains(text(), 'REPORTS')]") WebElement navigation_Reports;
	@FindBy(how=How.XPATH, using= "//span[contains(text(), 'Reports Templates Design')]") WebElement navigation_Rep_Temp_design;
	@FindBy(how=How.XPATH,using= "//a[@class='k-button k-grid-addReport grid-add-button']") WebElement create_btn;
	@FindBy(how=How.ID, using="reportName") WebElement report_Name;
	@FindBy(how=How.XPATH, using="//span[text()='Choose style template...']") WebElement slt_temp;
	@FindBy(how=How.XPATH, using="//div[@id='baseReports-list']/span/input") WebElement slt_base_report_txt_bx;
	@FindBy(how=How.XPATH, using="//input[@aria-label='Name'][@role='textbox']") List<WebElement> search_Report_Name_ls;
	@FindBy(how=How.XPATH, using="//tr//td[@role='gridcell']") List<WebElement> search_Report_Name_result;
	
	@FindBy(how=How.XPATH, using="//div[@class='k-animation-container'][@aria-hidden='false']") WebElement slt_animation_container;
	@FindBy(how=How.XPATH, using="//div[@id='reportStatus-list']//span//input[@aria-disabled='false']") WebElement slt_report_status_txt_bx;
	@FindBy(how=How.XPATH, using= "//button[text()='Save']") WebElement save_btn;
	@FindBy(how=How.XPATH, using="//table//tbody//tr[@class='k-alt']") List<WebElement> chk_table;
	@FindBy(how=How.XPATH, using="//table//tbody//tr//td[@role='gridcell']") List<WebElement> chk_table_report;
	@FindBy(how=How.XPATH, using=".//td[@role='gridcell']//span") List<WebElement> chk_reportName;	
	@FindBy(how=How.XPATH, using="//div[@class='k-grid-norecords']") WebElement no_Data_found;	
	@FindBy(how=How.XPATH, using=".//td[@role='gridcell']//a") List<WebElement> chk_aTag;
	@FindBy(how=How.XPATH, using= "//button[text()='Close']") WebElement close_btn;
	@FindBy(how=How.XPATH, using= "//div//div[@class='modal-footer']//button[@class='btn btn-primary']") WebElement ok_btn;
	@FindBy(how=How.XPATH, using="//span[text()='In Progress']") WebElement slt_status;
	
	//--------------------------------------------------------------------------------------
	//							Support function for methods
	//**************************************************************************************
		
	
	public void sltItem(WebElement sltItem, WebElement inputBox, String txt, WebElement saveBtn) throws InterruptedException
	{	WebDriverWait wait= new WebDriverWait(driver, 3000);
			
		sltItem.click();
		wait.until(ExpectedConditions.visibilityOf(slt_animation_container));
		Thread.sleep(1000);
		
		inputBox.sendKeys("");
		Thread.sleep(2000);
		inputBox.sendKeys(txt);
		Thread.sleep(3000);
		inputBox.sendKeys(Keys.DOWN);
		inputBox.sendKeys(Keys.ENTER);
		Thread.sleep(1000);
		wait.until(ExpectedConditions.elementToBeClickable(saveBtn));
		saveBtn.click();
	}
	
	public void chk_Report_Name_present(String reportName)
	{
		for(int j=0; j<search_Report_Name_ls.size()-1;)
		{
			search_Report_Name_ls.get(0).clear();
			search_Report_Name_ls.get(0).sendKeys(reportName);
			search_Report_Name_ls.get(0).sendKeys(Keys.ENTER);
			break;
		}
					
	}
	
	public String chk_table_ReportName(String reportName, String reportStatus)
	{
		String xpath_start,xpath_report_name,xpath_tag,	repName, result = "";
		xpath_start="//table//tbody//tr[";
		xpath_report_name="]//td[5]";
		xpath_tag="]//td[8]";
		
		
			
		for(int i=3; i<chk_table_report.size()-1; )
			{
			repName=driver.findElement(By.xpath(xpath_start+i+xpath_report_name)).getText();
				if(repName.equals(reportName))
				{
					String tags=driver.findElements(By.xpath(xpath_start+i+xpath_tag)).get(0).getText();
					
					
					if( tags.toString().equalsIgnoreCase(reportStatus))
					{
						result="pass";
	
					}
					
					break;
					
				}
				
				else
				{
	
					i++;
				}
				
		
			}
		
		return result;

	}

	public String chk_table_No_ReportName()
	{
		String result=null;
		
		System.out.println(no_Data_found.isDisplayed());
		
		if(no_Data_found.isDisplayed())
			
			{ result="pass" ;
				
			}
		else	
			{
				result="fail";
			}
		
		
		
		return result;
		
	}
	
	public void tableClkIcons (String pass_repName, String icon)
	{
	String xpath_start,xpath_report_name,xpath_tag,	repName,atag_ws;
	xpath_start="//table//tbody//tr[";
	xpath_report_name="]//td[5]";
	xpath_tag="]//td[4]//a//span";

	for(int i=3; i<chk_table.size(); )
		{
		repName=driver.findElement(By.xpath(xpath_start+i+xpath_report_name)).getText();
			if(repName.equals(pass_repName))
			{
				List<WebElement> tags=driver.findElements(By.xpath(xpath_start+i+xpath_tag));
				for(int j=0; j<tags.size();)
			
				{
					atag_ws=tags.get(j).getAttribute("class").toString();
					if(atag_ws.equalsIgnoreCase(icon))
					{
					tags.get(j).click();
				//	close_btn.click();
					break;
					
					}
					
					else
					{
						j++;
					}
				}
						
				break;
			}
			
			else
			{

				i++;
			}
		}
	}

	
	
	
	public void navigate_Reports()
	{
	
		navigation_Reports.click();
		navigation_Rep_Temp_design.click();
		
	}
	
	
	//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	//																				Methods
	//************************************************************************************************************************************************************************************************
		
	
	//-----------------------------------------------------------------------------------------
	//									Create Report
	//*****************************************************************************************
	
	public void clk_Create_btn(String repName)
	{
		WebDriverWait wait= new WebDriverWait(driver, 5000);
		wait.until(ExpectedConditions.visibilityOf(create_btn));
	//	driver.switchTo().frame(0);
		try {
			create_btn.click();
		} catch (Exception e) {
			
			wait.until(ExpectedConditions.elementToBeClickable(create_btn));
			create_btn.click();
			
		}
		
		report_Name.sendKeys(repName);
		
	}
	

	public void slt_Slt_Template() throws InterruptedException
	{
		sltItem(slt_temp,slt_base_report_txt_bx, "BaseTemplate", save_btn);
	}	

	
	//--------------------------------------------------------------------------------------
	//									Edit Report
	//**************************************************************************************
	public void edit_Report (String repName)
	{
		tableClkIcons(repName, "k-icon k-i-edit");
		
	}
	
	public void slt_Slt_Status() throws InterruptedException
	{
		sltItem(slt_status,slt_report_status_txt_bx, "ready to a", save_btn);
	}
	
	
	
	//--------------------------------------------------------------------------------------
	//									Copy Report
	//**************************************************************************************
	
	public String copy_Report (String repName, String copyRepName) throws InterruptedException
	{
		WebDriverWait wait= new WebDriverWait(driver, 3000);
		
		tableClkIcons(repName, "k-icon k-i-copy");
		//report_Name.clear();
		//report_Name.sendKeys("");
		report_Name.sendKeys(copyRepName);
		wait.until(ExpectedConditions.elementToBeClickable(save_btn));
		save_btn.click();
		return copyRepName;
		
			
	}
	
	//--------------------------------------------------------------------------------------
	//									Delete Report
	//**************************************************************************************
		
	public String delete_Report (String deleteReport) throws InterruptedException
	{
		WebDriverWait wait= new WebDriverWait(driver, 3000);
		tableClkIcons(deleteReport, "k-icon k-i-delete");
		wait.until(ExpectedConditions.visibilityOf(ok_btn));
		ok_btn.click();
		return deleteReport;
	}
}

