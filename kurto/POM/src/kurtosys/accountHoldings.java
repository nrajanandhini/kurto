package kurtosys;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class accountHoldings {
	
	WebDriver driver;
	

	public accountHoldings(WebDriver driver)
	{
	this.driver= driver;	
	
	}
	
	
	//--------------------------------------------------------------------------------------
	//									Elements
	//**************************************************************************************
	
	@FindBy(how=How.XPATH, using= "//span[contains(text(), 'USER ACCOUNTS')]") WebElement navigation_UserAccounts;
	@FindBy(how=How.XPATH, using= "//span[contains(text(), 'Account Holdings')]") WebElement navigation_AccountHolding;
	@FindBy(how=How.ID, using="userId") WebElement navigation_AccountHolding_list;
	@FindBy(how=How.XPATH, using="//ul//input[@class='k-input k-readonly' and @role='listbox']") WebElement accInput_ReadOnly;
	@FindBy(how=How.XPATH, using="//ul//input[@class='k-input' and @role='listbox']") WebElement accInput;
	@FindBy(how=How.XPATH, using="//ul[@id='userId_listbox']/child::li") WebElement Acc_list;
	@FindBy(how=How.XPATH, using="//ul[@id='userId_listbox']/child::li") List<WebElement> option;
	@FindBy(how=How.XPATH, using= "//button[contains(text(), 'Apply filters')]") WebElement submit_btn;
	@FindBy(how=How.XPATH, using= "//table[@class='table table-condensed']//tbody//tr//td[1]//span") WebElement tb_cur_value;
	@FindBy(how=How.XPATH, using= "//ul//li[@class='k-item k-first k-last highlight']") List<WebElement> togg_lst;
	@FindBy(how=How.XPATH,using="//div[@class='k-grid-footer']//table//tbody//tr//td[contains (text(), 'Total:')]") List<WebElement> tot_cur_val;

	//--------------------------------------------------------------------------------------
	//							Support function for methods
	//**************************************************************************************
	
	public BigDecimal convert_String(String value)	
	{
		  value = value.trim();
		  BigDecimal d_Value = null; 
		  
		  if (value.startsWith("("))
		  {
		   value = value.substring(1, value.length() - 1);
		   value = value.replaceAll(",","");   
		   d_Value = new BigDecimal(value);   
		   d_Value = d_Value.negate();   
		  } 
		  
		  else
		  
		  {
		   value = value.replaceAll(",", "");   
		   d_Value = new BigDecimal(value);   
		  }
		  
		  return d_Value;
	 }
	
	public BigInteger table_total()
	{
		//System.out.println(tb_cur_value.getText());
		
		BigDecimal cur_value ;
		
		cur_value=BigDecimal.ZERO;
		
		cur_value =convert_String(tb_cur_value.getText().toString());
	//	System.out.println(cur_value);
	
		
			
	 	return cur_value.toBigInteger();
	}

	public BigInteger section_total()
	{
		//System.out.println(tb_cur_value.getText());
		
		BigDecimal convert_Value, total;
		
		convert_Value=BigDecimal.ZERO;
		total=BigDecimal.ZERO;
		
	
		for(WebElement e: tot_cur_val)
		{
			String str= e.getText().toString();
			if(str.startsWith("Total:"))
			{
			
		//System.out.println(str);

		 	str=str.substring(7);
		
			convert_Value= convert_String(str);
			
			total = total.add(convert_Value);
			
			}
									
		}
			
	 	return total.toBigInteger();
	}

	
	//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	//																				Methods
	//************************************************************************************************************************************************************************************************
			
		
	//-----------------------------------------------------------------------------------------
	//								Calculate total net worth
	//*****************************************************************************************
		
	
	public void navigate_Bar()
	{
		navigation_UserAccounts.click();
	}
	
	public void navAccountHoldings()
	{
		
		navigation_AccountHolding.click();
	}
	
	public void input_Account() 
	{
			accInput_ReadOnly.click();
			accInput.clear();
			accInput.sendKeys("acdc");
	}
	
	public void slt_Account()
	
	{
		WebDriverWait wait= new WebDriverWait(driver, 3000);
		
			wait.until(ExpectedConditions.visibilityOf(Acc_list)); 
			for( WebElement e: option)
			{
				//System.out.println(e.getText());
				String s= e.getText();
				s=s.substring(0, 4);
				//System.out.println(s);
				if(s.equalsIgnoreCase("acdc"))
				{
					e.click();
				}
				
			}
			
	}
	
	public void submit_Acc()
	{      	
		WebDriverWait wait= new WebDriverWait(driver, 3000);
		
			wait.until(ExpectedConditions.elementToBeClickable(submit_btn)); 
			submit_btn.click();
	}
	
	public void sections_Clk() throws Exception
	{	
		for(WebElement e: togg_lst)
		{
			System.out.println(e.getText());
			e.getText();
			e.click();
		}
		
	}
		
	public String compare_Total()
	{
		//if(table_total().equals(section_total()))
		 String	result;
		 BigInteger table_total_value, section_total_value;
		 table_total_value=table_total();
		 section_total_value=section_total();
		 
		 System.out.println("grand total"+table_total_value+"section"+section_total_value);
	
		result = table_total_value.equals(section_total_value) ? "pass" : "fail";
		return result;
			
	}
		

}