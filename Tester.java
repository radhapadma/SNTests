/***************************************************************************************
 * package testing contains Tester class, to test  ServiceNow 
 * author Radha Padmasolala

 ***************************************************************************************/
package testing;
import java.util.List;
import java.util.Properties;
import java.awt.RenderingHints.Key;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java. util. Date;
import java.sql.Time;
import java. sql. Timestamp;
import java.text.SimpleDateFormat;


/*******************************************************************************************
 * class Name: Tester 
 * Purpose:   ServiceNow Incident, Request and various catalog items in ServiceNow.
 * author: Radha Padmasolala
 * date created: 5/2/2019
 * 
 * comment the method calls to non workflow items so you can use testerbackup to run front and back testing
 * for non workflow catalog items
 * 
 * 
 *  Workflow requests are
 * ______________________
 * 
 Academic Services Database Account  Request
Affiliate Account Extension/Modify Account
Event Wifi Account Access
MARS (Mavericks Analytics Reporting System)
Discoverer Access
Airwatch Mobile Device Enrollment
EDS Data Warehouse Direct Database Access
___________________
Business Affairs
__________________
Request Policy or Procedure Services
Request BTS Web Services
Network and Telecom
Domain Name Service
Static IP Address Request
Installation of Wireless Access
Email and Communications
Create Resource or Room MailBox
New distribution group or Listserv Request
______________
Security
____________
Whitelisting
NetID Token Request
NetID Token Assignment
Server and Storage
Modify Server Request
Box Storage Request
Provision Server Request
Desktop or Lab Hardware and Software
Software Purchase or Approval

__________________________________________
Teaching and Learning
___________________________________________
New Training or Topic or Documentation for OIT or Business Affairs


 *********************************************************************************************/
public class Tester  {
	static WebDriver d;
	static PrintWriter writer;
	static PrintWriter notes;
	static String username;
	static String password;
	static Tester test ;

	public static void main(String [] args)
	{
		// Opening ServiceNow Test using Chrome browser
		System.setProperty("webdriver.chrome.driver","c:\\users\\radhap\\chromedriver_win32\\chromedriver.exe");
		d = new ChromeDriver();
		d.manage().timeouts().implicitlyWait(40,TimeUnit.SECONDS);
		d.get("http://utatest.service-now.com/login.do");
		
		  test = new Tester();
		 test.readPropFile();
		 d.findElement(By.xpath("//input[@id='user_name']")).sendKeys(username);
		d.findElement(By.xpath("//input[@id='user_password']")).sendKeys(password);
		d.findElement(By.xpath("//button[@id='sysverb_login']")).click();
		
		d.get("http://utatest.service-now.com/selfservice");
		d.manage().timeouts().implicitlyWait(40,TimeUnit.SECONDS);
	    test.createLogFile();
	    output(" user: "+username);
	    output("Browser Chrome");
		 
	    output("\nTESTING INCIDENT\n");
	    test.testCreateIncident();
	   
	    output("\nGENERIC REQUEST\n");
		test.testCreateGenericRequest();
		
	   test.runAllRequests();
		//test.runNonWorkflowRequests();
		//test.runWorkflowRequests();
	  //  test.testCITempAccesstoDisabledAcct();
	   writer.close();  
		}
	
	
	public void createLogFile()
	{
		 try
		 {
			 
			 Date dNow = new Date( );
		     SimpleDateFormat ft = new SimpleDateFormat ("E yyyy.MM.dd 'at' hh:mm:ss a zzz");

		     String FN = "SNLog-"+System.currentTimeMillis()+".txt";
			 System.out.println("Log File Name "+FN);
			 writer = new PrintWriter(FN,"UTF-8");
			 output("JAI KRISHNA");
			 output("ServiceNow Self Service Portal Testing ");
			 output("Date this log was printed " + ft.format(dNow));
			 
		}
		  catch ( Exception ex)
		  {
			  System.out.println("exception" + ex.getMessage());
		  }
		
		
	}
	
	public void logout()
	{
		System.out.println("logout ");
		d.close();
	}
	

	public void testCreateIncident()
	{
		System.out.println("Test Create Incident ");
		d.manage().timeouts().implicitlyWait(40,TimeUnit.SECONDS);
		
		d.findElement(By.xpath("//span[contains(text(),'Report An Issue')]")).click();
		d.findElement(By.xpath("//body[contains(@class,'windows chrome ng-scope touch_scroll')]/div[contains(@class,'sp-page-root page flex-column sp-can-animate')]/section[contains(@class,'flex-grow page sp-scroll')]/main[contains(@class,'body padding-top')]/div[contains(@class,'ng-scope ce3552b0adb63a380e3ad22d405961942')]/div[contains(@class,'container')]/sp-page-row[contains(@class,'ng-scope ng-isolate-scope')]/div[contains(@class,'row')]/div[contains(@class,'col-md-4')]/span[@class='ng-scope']/div[@id='x5bf49b101bf36b80715941d8cd4bcb7a']/div[@class='myPanelContainer']/div[@class='panel panel-custom b myShadow']/div[@class='panel-body']/div[@class='text-center']/ul[@id='baseUl']/li[@id='li0']/div[1]")).click();
		
		d.findElement(By.xpath("//body[contains(@class,'windows chrome ng-scope touch_scroll')]/div[contains(@class,'sp-page-root page flex-column sp-can-animate')]/section[contains(@class,'flex-grow page sp-scroll')]/main[contains(@class,'body padding-top')]/div[contains(@class,'ng-scope ce3552b0adb63a380e3ad22d405961942')]/div[contains(@class,'container')]/sp-page-row[contains(@class,'ng-scope ng-isolate-scope')]/div[contains(@class,'row')]/div[contains(@class,'col-md-4')]/span[contains(@class,'ng-scope')]/div[@id='x5bf49b101bf36b80715941d8cd4bcb7a']/div[contains(@class,'myPanelContainer')]/div[contains(@class,'panel panel-custom b myShadow')]/div[contains(@class,'panel-body')]/div[contains(@class,'text-center')]/ul[@id='baseUl']/li[@id='li0']/ul[contains(@class,'ng-scope')]/li[@id='li5']/div[1]")).click();
		d.findElement(By.xpath("//li[@id='li20']//div[1]")).click();
		d.findElement(By.xpath("//input[@id='sp_formfield_short_description']")).sendKeys("description short");
		d.findElement(By.xpath("//textarea[@id='sp_formfield_description']")).sendKeys("details");
		try 
		{
			List<WebElement> all = d.findElements(By.cssSelector("input.select2-focusser.select2-offscreen"));
			WebElement i = all.get(3);
			Thread.sleep(500);
			i.sendKeys("Functionality");
			d.findElement(By.className("select2-result-label")).click();
			d.findElement(By.xpath("//button[contains(@name,'submit')]")).click();
			String reqno = d.findElement(By.xpath("/html[1]/body[1]/div[1]/section[1]/main[1]/div[2]/div[1]/sp-page-row[1]/div[1]/div[1]/span[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/a[1]/div[1]")).getText();
			
			output("incident "+reqno+" --Passed");
				
		}
		catch(Exception ex)
		{
			System.out.println(ex.getLocalizedMessage());
		}
	}
	
	public void testCreateGenericRequest()
	{
		System.out.println("Test Create Generic Request ");
		d.manage().timeouts().implicitlyWait(40,TimeUnit.SECONDS);
		
		d.findElement(By.xpath("//span[contains(text(),'Request Something')]")).click();
		d.findElement(By.xpath("//span[contains(text(),'General request for anything not in the catalog li')]")).click();
		d.findElement(By.xpath("//a[contains(@class,'icon-link-background-white myShadow')]")).click();
		d.findElement(By.xpath("//input[@id='sp_formfield_short_description']")).sendKeys("Short Description");
		d.findElement(By.xpath("//textarea[@id='sp_formfield_description']")).sendKeys("xsdffg");
		d.findElement(By.xpath("//button[contains(@name,'submit')]")).click();
		String reqno = d.findElement(By.xpath("/html[1]/body[1]/div[1]/section[1]/main[1]/div[2]/div[1]/sp-page-row[1]/div[1]/div[1]/span[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/a[1]/div[1]")).getText();
		
		output("Generic Request"+reqno+" --Passed");
	
	}
	
	public void testCIHPCAcctReq()
	{
		//System.out.println("Test Catalog Item HPC Account Request under Accounts&Access");
		d.manage().timeouts().implicitlyWait(40,TimeUnit.SECONDS);
		d.findElement(By.xpath("//span[contains(text(),'Request Something')]")).click();
		d.findElement(By.xpath("//span[contains(text(),'HPC Account Request')]")).click();
		d.findElement(By.xpath("//textarea[@id='sp_formfield_hpc_account_purpose']")).sendKeys("for fun");
		d.findElement(By.xpath("//button[@name='submit']")).click();
		String reqno = d.findElement(By.xpath("/html[1]/body[1]/div[1]/section[1]/main[1]/div[2]/div[1]/sp-page-row[1]/div[1]/div[1]/span[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/a[1]/div[1]")).getText();
		
		output("HPC Account Request -: "+reqno+" --Passed");
		output("____________________________________________________________");
	}

	public void testCIMatlab()
	{
		d.manage().timeouts().implicitlyWait(40,TimeUnit.SECONDS);
		d.findElement(By.xpath("//span[contains(text(),'Request Something')]")).click();
		d.findElement(By.xpath("//span[contains(text(),'MatLab, SolidWorks, or AnSYS Access')]")).click();
		d.findElement(By.xpath("//input[@id='sp_formfield_matlab_academic_course']")).sendKeys("course 1");
		d.findElement(By.xpath("//input[@id='sp_formfield_matlab_target_system_os']")).sendKeys("Windows 10");
		d.findElement(By.xpath("//button[@name='submit']")).click();
		String reqno = d.findElement(By.xpath("/html[1]/body[1]/div[1]/section[1]/main[1]/div[2]/div[1]/sp-page-row[1]/div[1]/div[1]/span[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/a[1]/div[1]")).getText();
		output("Matlab Access Request- "+reqno+" --Passed");
		output("____________________________________________________________");
		
	}
	
	public void testCIUrgentRequest()
	{
		d.manage().timeouts().implicitlyWait(40,TimeUnit.SECONDS);
		d.findElement(By.xpath("//span[contains(text(),'Request Something')]")).click();
		d.findElement(By.xpath("//span[contains(text(),'Urgent Clearance Request')]")).click();
		d.findElement(By.xpath("//input[@id='sp_formfield_urgent_netid']")).sendKeys("elliot");
		d.findElement(By.xpath("//input[@id='sp_formfield_urgent_date']")).sendKeys("5-02-2019");
		d.findElement(By.xpath("//input[@id='sp_formfield_urgent_process']")).sendKeys("5-20-2019");
		try
		{
			List <WebElement> all = d.findElements(By.cssSelector("input.select2-focusser.select2-offscreen"));
			WebElement i = all.get(0);
			Thread.sleep(500);
			i.sendKeys("A Elliott");
			d.findElement(By.className("select2-result-cell")).click();	

			d.findElement(By.xpath("//button[@name='submit']")).click();
			String reqno = d.findElement(By.xpath("/html[1]/body[1]/div[1]/section[1]/main[1]/div[2]/div[1]/sp-page-row[1]/div[1]/div[1]/span[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/a[1]/div[1]")).getText();
			output("Urgent Clearance Request -: "+reqno+" --Passed");
			output("____________________________________________________________");
		}
		catch (Exception ex)
		{
			System.out.println("ERROR**********************************************************");
			System.out.println(" exception in Urgent Request"+ ex.getMessage());
			System.out.println("ERROR**********************************************************");
		}
	}
	public void testCIAffiliateAccountExtensionRequest()
	{		
		d.manage().timeouts().implicitlyWait(40,TimeUnit.SECONDS);
		d.findElement(By.xpath("//span[contains(text(),'Request Something')]")).click();
		
		d.findElement(By.xpath("//span[contains(text(),'Affiliate Account Extension/Modify Account')]")).click();
		d.findElement(By.xpath("//input[@id='sp_formfield_nonuta_emplid']")).sendKeys("1000656832");
		d.findElement(By.xpath("//input[@id='sp_formfield_nonuta_netid']")).sendKeys("radhap");
		d.findElement(By.xpath("//input[@id='sp_formfield_nonuta_expiration_date']")).sendKeys("05/5/2019");
		d.findElement(By.xpath("//button[@name='submit']")).click();
		String reqno = d.findElement(By.xpath("/html[1]/body[1]/div[1]/section[1]/main[1]/div[2]/div[1]/sp-page-row[1]/div[1]/div[1]/span[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/a[1]/div[1]")).getText();
		output("Affiliate Account Request -: "+reqno+" --Passed");
		output("____________________________________________________________");
	}
	public void testCIDeptNetworkDriveAccess()
	{
		d.manage().timeouts().implicitlyWait(40,TimeUnit.SECONDS);
		d.findElement(By.xpath("//span[contains(text(),'Request Something')]")).click();
		d.findElement(By.xpath("//span[@class='ng-binding'][contains(text(),'Departmental Network Drive Access')]")).click();
		d.findElement(By.xpath("//input[@id='sp_formfield_sc_network_drive_name']")).sendKeys("P:");
		d.findElement(By.xpath("//button[@name='submit']")).click();
		String reqno = d.findElement(By.xpath("/html[1]/body[1]/div[1]/section[1]/main[1]/div[2]/div[1]/sp-page-row[1]/div[1]/div[1]/span[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/a[1]/div[1]")).getText();
		output("Network Drive Access Request -: "+reqno+" --Passed");
		output("____________________________________________________________");
		//this.processReq("David Cruz", reqno);
	
	}
	public void testCISingleSignOnRequest()
	{
		d.manage().timeouts().implicitlyWait(40,TimeUnit.SECONDS);
		d.findElement(By.xpath("//span[contains(text(),'Request Something')]")).click();
		d.findElement(By.xpath("//span[@class='ng-binding'][contains(text(),'Single Sign-On (SSO) Request')]")).click();
		d.findElement(By.xpath("//input[@id='sp_formfield_sso_app_name']")).sendKeys("ServiceNow");
		d.findElement(By.xpath("//button[@name='submit']")).click();
		String reqno = d.findElement(By.xpath("/html[1]/body[1]/div[1]/section[1]/main[1]/div[2]/div[1]/sp-page-row[1]/div[1]/div[1]/span[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/a[1]/div[1]")).getText();
		output("Single SignOn Request -: "+reqno+" --Passed");
		output("____________________________________________________________");
	}
	public void testCIImageNowAccessRequest()
	{
		d.manage().timeouts().implicitlyWait(40,TimeUnit.SECONDS);
		d.findElement(By.xpath("//span[contains(text(),'Request Something')]")).click();
		d.findElement(By.xpath("//span[@class='ng-binding'][contains(text(),'ImageNow Access')]")).click();
		d.findElement(By.xpath("//input[@id='sp_formfield_imagenow_clone']")).sendKeys("Radhas access");
		d.findElement(By.xpath("/html[1]/body[1]/div[1]/section[1]/main[1]/div[1]/div[1]/sp-page-row[1]/div[1]/div[1]/span[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/button[1]")).click();
		//d.findElement(By.xpath("//button[@name='submit']")).click();
		String reqno = d.findElement(By.xpath("/html[1]/body[1]/div[1]/section[1]/main[1]/div[2]/div[1]/sp-page-row[1]/div[1]/div[1]/span[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/a[1]/div[1]")).getText();
		output("ImageNow  Request -: "+reqno+" --Passed");
		output("____________________________________________________________");
	}
	
	public void testCICMSRequest()
	{
		d.manage().timeouts().implicitlyWait(40,TimeUnit.SECONDS);
		d.findElement(By.xpath("//span[contains(text(),'Request Something')]")).click();
		d.findElement(By.xpath("/html[1]/body[1]/div[1]/section[1]/main[1]/div[1]/div[1]/sp-page-row[2]/div[1]/div[1]/span[1]/div[1]/div[1]/div[3]/a[2]")).click();
		d.findElement(By.xpath("//span[@class='ng-binding'][contains(text(),'CMS Access')]")).click();
		d.findElement(By.xpath("//input[@id='sp_formfield_cms_site']")).sendKeys("http://www.uta.edu");
		d.findElement(By.xpath("//button[@name='submit']")).click();
		String reqno = d.findElement(By.xpath("/html[1]/body[1]/div[1]/section[1]/main[1]/div[2]/div[1]/sp-page-row[1]/div[1]/div[1]/span[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/a[1]/div[1]")).getText();
		output("CMS Request -: "+reqno+" --Passed");
		output("____________________________________________________________");	
	}
	
	public void testCIEventWifiAccessRequest()
	{
		d.manage().timeouts().implicitlyWait(40,TimeUnit.SECONDS);
		d.findElement(By.xpath("//span[contains(text(),'Request Something')]")).click();
		d.findElement(By.xpath("/html[1]/body[1]/div[1]/section[1]/main[1]/div[1]/div[1]/sp-page-row[2]/div[1]/div[1]/span[1]/div[1]/div[1]/div[3]/a[2]")).click();
		d.findElement(By.xpath("//span[@class='ng-binding'][contains(text(),'Event Wifi Account Access')]")).click();
		d.findElement(By.xpath("//input[@id='sp_formfield_event_name']")).sendKeys("Graduation");
		d.findElement(By.xpath("//input[@id='sp_formfield_event_username']")).sendKeys("Graduation");
		d.findElement(By.xpath("//input[@id='sp_formfield_event_pwd']")).sendKeys("Graduation");
		d.findElement(By.xpath("//input[@id='sp_formfield_event_start']")).sendKeys("5/2/2019");
		d.findElement(By.xpath("//input[@id='sp_formfield_event_end']")).sendKeys("5/3/2019");
		d.findElement(By.xpath("//button[@name='submit']")).click();
		String reqno = d.findElement(By.xpath("/html[1]/body[1]/div[1]/section[1]/main[1]/div[2]/div[1]/sp-page-row[1]/div[1]/div[1]/span[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/a[1]/div[1]")).getText();
		output("Event Wifi Access Request -: "+reqno+" --Passed");
		output("____________________________________________________________");
	}
	
	public void testDBRequestPwdReset()
	{
		try 
		{
			d.findElement(By.xpath("//span[contains(text(),'Request Something')]")).click();
			d.findElement(By.xpath("/html[1]/body[1]/div[1]/section[1]/main[1]/div[1]/div[1]/sp-page-row[2]/div[1]/div[1]/span[1]/div[1]/div[1]/div[3]/a[2]")).click();
			d.findElement(By.xpath("//span[contains(text(),'Database Account - Password Reset')]")).click();
			d.manage().timeouts().implicitlyWait(40,TimeUnit.SECONDS);
			d.findElement(By.xpath("//input[@id='sp_formfield_db_server']")).sendKeys("serve1");
			d.findElement(By.xpath("//input[@id='sp_formfield_database_name']")).sendKeys("Oracle");
			List<WebElement> all = d.findElements(By.cssSelector("input.select2-focusser.select2-offscreen"));
			WebElement i = all.get(2);
			Thread.sleep(500);
			i.sendKeys("Oracle"+Keys.ENTER);
			
			d.findElement(By.xpath("//button[@name='submit']")).click();
			String reqno = d.findElement(By.xpath("/html[1]/body[1]/div[1]/section[1]/main[1]/div[2]/div[1]/sp-page-row[1]/div[1]/div[1]/span[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/a[1]/div[1]")).getText();
			output("DB Password Reset Request -: "+reqno+" --Passed");
			output("____________________________________________________________");
		
		
		}
	catch (Exception ex)
	{
		System.out.println("ERROR**********************************************************");
		System.out.println(" exception in testDBRequestPwdReset()"+ ex.getMessage());
		System.out.println("ERROR**********************************************************");
	}
}
	public void testCITempAccesstoDisabledAcct()
	{
		d.manage().timeouts().implicitlyWait(40,TimeUnit.SECONDS);
		d.findElement(By.xpath("//span[contains(text(),'Request Something')]")).click();
		d.findElement(By.xpath("/html[1]/body[1]/div[1]/section[1]/main[1]/div[1]/div[1]/sp-page-row[2]/div[1]/div[1]/span[1]/div[1]/div[1]/div[3]/a[2]")).click();
		d.findElement(By.xpath("//span[contains(text(),'Temporary Access to a Disabled Account')]")).click();
		d.findElement(By.xpath("//input[@id='sp_formfield_temp_access_departed_name']")).sendKeys("krishna");
		d.findElement(By.xpath("//input[@id='sp_formfield_temp_access_departed_netid']")).sendKeys("Krish");
		d.findElement(By.xpath("//input[@id='sp_formfield_temp_access_start']")).sendKeys("5/7/2019");
		d.findElement(By.xpath("//input[@id='sp_formfield_temp_access_end']")).sendKeys("5/15/2019");
		d.findElement(By.xpath("//input[@id='sp_formfield_temp_option_drive']")).click();
		
	
		d.findElement(By.xpath("//input[@id='sp_formfield_temp_option_email']")).click();
		d.findElement(By.xpath("//input[@id='sp_formfield_temp_option_drive']")).click();
		
		d.findElement(By.xpath("//button[@name='submit']")).click();
		String reqno = d.findElement(By.xpath("/html[1]/body[1]/div[1]/section[1]/main[1]/div[2]/div[1]/sp-page-row[1]/div[1]/div[1]/span[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/a[1]/div[1]")).getText();
		output("Temp access to disabled account Account -: "+reqno+" --Passed");
		output("____________________________________________________________");
	
	}
	
	public void testCIMyMavAccessRequest()
	{
		d.manage().timeouts().implicitlyWait(40,TimeUnit.SECONDS);
		d.findElement(By.xpath("//span[contains(text(),'Request Something')]")).click();
		d.findElement(By.xpath("/html[1]/body[1]/div[1]/section[1]/main[1]/div[1]/div[1]/sp-page-row[2]/div[1]/div[1]/span[1]/div[1]/div[1]/div[3]/a[2]")).click();
		d.findElement(By.xpath("//div[contains(@class,'myItem')]//span[contains(@class,'ng-binding')][contains(text(),'MyMav Access')]")).click();
		try
		{
				d.findElement(By.xpath("//textarea[@id='sp_formfield_security_description']")).sendKeys("abc");
				List<WebElement> all = d.findElements(By.cssSelector("input.select2-focusser.select2-offscreen"));
				WebElement i = all.get(2);
				Thread.sleep(500);
				i.sendKeys("Query"+Keys.ENTER);
				d.findElement(By.xpath("//button[@name='submit']")).click();
			String reqno = d.findElement(By.xpath("/html[1]/body[1]/div[1]/section[1]/main[1]/div[2]/div[1]/sp-page-row[1]/div[1]/div[1]/span[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/a[1]/div[1]")).getText();
			output("MyMav Access Request -: "+reqno+" --Passed");
			output("_________________________________________________");
		}
		catch (Exception ex)
		{
			System.out.println("ERROR**********************************************************");
			System.out.println(" exception in testtestCIMyMavAccessRequest()"+ex.getMessage());
			System.out.println("ERROR**********************************************************");
		}
	}
	
	public void testCIPeopleSoftLDAPAccessRequest()
	{
		try
		{
			d.manage().timeouts().implicitlyWait(40,TimeUnit.SECONDS);
			d.findElement(By.xpath("//span[contains(text(),'Request Something')]")).click();
			d.findElement(By.xpath("/html[1]/body[1]/div[1]/section[1]/main[1]/div[1]/div[1]/sp-page-row[2]/div[1]/div[1]/span[1]/div[1]/div[1]/div[3]/a[2]")).click();
			d.findElement(By.xpath("//span[contains(text(),'PeopleSoft LDAP Access')]")).click();
			List<WebElement> all = d.findElements(By.cssSelector("input.select2-focusser.select2-offscreen"));
			WebElement i = all.get(2);
			Thread.sleep(500);
			i.sendKeys("ARCSDEV"+Keys.ENTER);
			d.findElement(By.xpath("//button[@name='submit']")).click();
			String reqno = d.findElement(By.xpath("/html[1]/body[1]/div[1]/section[1]/main[1]/div[2]/div[1]/sp-page-row[1]/div[1]/div[1]/span[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/a[1]/div[1]")).getText();
			output("Peoplesoft LDAP Access -: "+reqno+" --Passed");
			output("____________________________________________________________");
		
		
		}
		catch (Exception ex)
		{
			System.out.println("ERROR**********************************************************");
			System.out.println(" testCIPeopleSoftLDAPAccessRequest()"+ex.getMessage());
			System.out.println("ERROR**********************************************************");
		}
	}
	
	public void testCIMARSRequest()
	{
		d.manage().timeouts().implicitlyWait(40,TimeUnit.SECONDS);
		d.findElement(By.xpath("//span[contains(text(),'Request Something')]")).click();
		d.findElement(By.xpath("/html[1]/body[1]/div[1]/section[1]/main[1]/div[1]/div[1]/sp-page-row[2]/div[1]/div[1]/span[1]/div[1]/div[1]/div[3]/a[2]")).click();
		d.findElement(By.xpath("//div[contains(@class,'myItem')]//span[contains(@class,'ng-binding')][contains(text(),'MARS (Maverick Analytics Reporting System) Request')]")).click();
		//*[@id="s2id_autogen407"]
		
		d.findElement(By.xpath("/html[1]/body[1]/div[1]/section[1]/main[1]/div[1]/div[1]/sp-page-row[1]/div[1]/div[1]/span[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[3]/div[1]/form[1]/div[1]/sp-variable-layout[1]/fieldset[4]/div[1]/div[1]/div[1]/div[1]/span[1]/div[2]/ul[1]/li[1]/input[1]")).sendKeys("Campus Solutions");
	   	d.findElement(By.xpath("/html[1]/body[1]/div[1]/section[1]/main[1]/div[1]/div[1]/sp-page-row[1]/div[1]/div[1]/span[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[3]/div[1]/form[1]/div[1]/sp-variable-layout[1]/fieldset[4]/div[1]/div[1]/div[3]/div[1]/span[1]/div[2]/ul[1]/li[1]/input[1]")).sendKeys("Advising");
	
		try
		{
			List<WebElement> all = d.findElements(By.cssSelector("input.select2-focusser.select2-offscreen"));
			WebElement i = all.get(3);
			Thread.sleep(500);
			i.sendKeys("New Account"+Keys.ENTER);
			d.findElement(By.className("select2-result-label")).click();	

			WebElement i1 = all.get(4);
			Thread.sleep(500);
			i1.sendKeys("MARS-test"+Keys.ENTER);
			d.findElement(By.className("select2-result-label")).click();	

		   	d.findElement(By.xpath("//textarea[@id='sp_formfield_mars_business_reason']")).sendKeys("reasons are ..");
			
			d.findElement(By.xpath("//button[@name='submit']")).click();
			String reqno = d.findElement(By.xpath("/html[1]/body[1]/div[1]/section[1]/main[1]/div[2]/div[1]/sp-page-row[1]/div[1]/div[1]/span[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/a[1]/div[1]")).getText();
			output("MARS Request Created -: "+reqno+" --Passed");
			output("____________________________________________________________");
	
		}
		catch (Exception ex)
		{
			System.out.println("ERROR**********************************************************");	
			System.out.println(" testCIMARSRequest()"+ex.getMessage());
			System.out.println("ERROR**********************************************************");	
		}
	}
	
	
	
	
	public void testCISFTPAccesstoMyMavRequest()
	{
		d.manage().timeouts().implicitlyWait(40,TimeUnit.SECONDS);
		try 
		{
			d.findElement(By.xpath("//span[contains(text(),'Request Something')]")).click();
			d.findElement(By.xpath("/html[1]/body[1]/div[1]/section[1]/main[1]/div[1]/div[1]/sp-page-row[2]/div[1]/div[1]/span[1]/div[1]/div[1]/div[3]/a[2]")).click();
			d.findElement(By.xpath("//span[contains(text(),'SFTP Access to MyMav Server')]")).click();
			d.findElement(By.xpath("//input[@id='sp_formfield_sftp_folder_path']")).sendKeys("C/folder");
			d.findElement(By.xpath("//input[@id='sp_formfield_sftp_user_details_requested_for']")).sendKeys("Radha");
			d.findElement(By.xpath("//input[@id='sp_formfield_sftp_user_details_email_address']")).sendKeys("radhap@uta.edu");
			d.findElement(By.xpath("//input[@id='sp_formfield_sftp_user_details_emplid']")).sendKeys("emplid");
			d.findElement(By.xpath("//input[@id='sp_formfield_sftp_user_details_eppn']")).sendKeys("eppn");
			
			List<WebElement> all = d.findElements(By.cssSelector("input.select2-focusser.select2-offscreen"));
			WebElement i = all.get(2);
			Thread.sleep(500);
			i.sendKeys("Production"+Keys.ENTER);
		
			d.findElement(By.xpath("//button[@name='submit']")).click();
			String reqno = d.findElement(By.xpath("/html[1]/body[1]/div[1]/section[1]/main[1]/div[2]/div[1]/sp-page-row[1]/div[1]/div[1]/span[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/a[1]/div[1]")).getText();
		
			output("FTP Access to MyMav -: "+reqno+" --Passed");
			output("____________________________________________________________");
		}
		catch (Exception ex)
		{
			System.out.println("ERROR**********************************************************");
			System.out.println(" PeopleSoftLDAPAccessRequest"+ex.getMessage());
			System.out.println("ERROR**********************************************************");	
		}
	}
	

	public void testEDSDataWarehouse()
	{
		d.manage().timeouts().implicitlyWait(40,TimeUnit.SECONDS);
		d.findElement(By.xpath("//span[contains(text(),'Request Something')]")).click();
		//d.findElement(By.xpath("//a[@id='next_btn']")).click();
		d.findElement(By.xpath("//html[1]/body[1]/div[1]/section[1]/main[1]/div[1]/div[1]/sp-page-row[2]/div[1]/div[1]/span[1]/div[1]/div[1]/div[3]/a[2]")).click();
	//	d.findElement(By.xpath("//div[contains(@class,'myItem')]//span[contains(@class,'ng-binding')][contains(text(),'EDS Data Warehouse Direct Database Access')]")).click();
		d.findElement(By.xpath("//span[@class='ng-binding'][contains(text(),'EDS Data Warehouse Direct Database Access')]")).click();
		d.findElement(By.xpath("//input[@id='sp_formfield_sc_eds_data_warehouse_access']")).sendKeys("admin");
		
		d.findElement(By.xpath("//button[@name='submit']")).click();
		String reqno = d.findElement(By.xpath("/html[1]/body[1]/div[1]/section[1]/main[1]/div[2]/div[1]/sp-page-row[1]/div[1]/div[1]/span[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/a[1]/div[1]")).getText();
		output("EDS DataWarehouse -: "+reqno+" --Passed");
		output("____________________________________________________________");
	}
	
	
	public void testUTACloud()
	{
		d.manage().timeouts().implicitlyWait(40,TimeUnit.SECONDS);
		d.findElement(By.xpath("//span[contains(text(),'Request Something')]")).click();
		d.findElement(By.xpath("/html[1]/body[1]/div[1]/section[1]/main[1]/div[1]/div[1]/sp-page-row[2]/div[1]/div[1]/span[1]/div[1]/div[1]/div[3]/a[2]")).click();
		d.findElement(By.xpath("//a[@id='next_btn']")).click();
		d.findElement(By.xpath("//span[contains(text(),'UTA.Cloud Web Server')]")).click();
		try
		{
			List<WebElement> all = d.findElements(By.cssSelector("input.select2-focusser.select2-offscreen"));
			WebElement i = all.get(2);
			Thread.sleep(500);
			i.sendKeys("Faculty"+Keys.ENTER);
			d.findElement(By.xpath("//button[@name='submit']")).click();
			String reqno = d.findElement(By.xpath("/html[1]/body[1]/div[1]/section[1]/main[1]/div[2]/div[1]/sp-page-row[1]/div[1]/div[1]/span[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/a[1]/div[1]")).getText();
			output("UTA Cloud -: "+reqno+" --Passed");
			output("____________________________________________________________");
	
		}
		catch (Exception ex)
		{
			System.out.println("ERROR**********************************************************");
			System.out.println(" testUTACloud()"+ex.getMessage());
			System.out.println("ERROR**********************************************************");
		}
	}
	public void testCIRequestPolicy()
	{
		d.manage().timeouts().implicitlyWait(40,TimeUnit.SECONDS);
		d.findElement(By.xpath("//span[contains(text(),'Request Something')]")).click();
		d.findElement(By.xpath("//span[contains(text(),'Request Policy or Procedure Services')]")).click();
		d.findElement(By.xpath("//textarea[@id='sp_formfield_v_description']")).sendKeys("aaaaa");
		d.findElement(By.xpath("//button[@name='submit']")).click();
		String reqno = d.findElement(By.xpath("/html[1]/body[1]/div[1]/section[1]/main[1]/div[2]/div[1]/sp-page-row[1]/div[1]/div[1]/span[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/a[1]/div[1]")).getText();
		output("Request Policy -: "+reqno+" --Passed");
		output("____________________________________________________________");
	}
	
	public void testCIGeneralBusinessRequest()
	{
		d.manage().timeouts().implicitlyWait(40,TimeUnit.SECONDS);
		try
		{
			d.findElement(By.xpath("//span[contains(text(),'Request Something')]")).click();
			d.findElement(By.xpath("//div[contains(@class,'myItem')]//span[contains(@class,'ng-binding')][contains(text(),'General Business Services Request')]")).click();
			d.findElement(By.xpath("//input[@id='sp_formfield_short_description']")).sendKeys("aaaaa");
			d.findElement(By.xpath("//textarea[@id='sp_formfield_description']")).sendKeys("aaaaa");
			
			List<WebElement> all = d.findElements(By.cssSelector("input.select2-focusser.select2-offscreen"));
			WebElement i = all.get(2);
			Thread.sleep(500);
			i.sendKeys("Absence Management"+Keys.ENTER);
			d.findElement(By.className("select2-result-label")).click();	
			
			d.findElement(By.xpath("//button[@name='submit']")).click();
			String reqno = d.findElement(By.xpath("/html[1]/body[1]/div[1]/section[1]/main[1]/div[2]/div[1]/sp-page-row[1]/div[1]/div[1]/span[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/a[1]/div[1]")).getText();
			output("General Business Request : "+reqno+" --Passed");
			output("____________________________________________________________");
			
		}
		catch (Exception ex)
		{   
			System.out.println("ERROR**********************************************************");
			System.out.println(" Testing General Business Request"+ex.getMessage());
			System.out.println("ERROR**********************************************************");	
		}
	}
	public void testCItRequestBTSWebServices()
	{
		d.manage().timeouts().implicitlyWait(40,TimeUnit.SECONDS);
		d.findElement(By.xpath("//span[contains(text(),'Request Something')]")).click();
		d.findElement(By.xpath("//span[contains(text(),'Request BTS Web Services')]")).click();
		d.findElement(By.xpath("//textarea[@id='sp_formfield_v_description']")).sendKeys("course 1");
		d.findElement(By.xpath("//button[@name='submit']")).click();
		String reqno = d.findElement(By.xpath("/html[1]/body[1]/div[1]/section[1]/main[1]/div[2]/div[1]/sp-page-row[1]/div[1]/div[1]/span[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/a[1]/div[1]")).getText();
		output(" Request BTS Web -: "+reqno+" --Passed");
		output("____________________________________________________________");	

	}
	
	public void testCIRequestTouchNetStore()
	{
		d.manage().timeouts().implicitlyWait(40,TimeUnit.SECONDS);
		d.findElement(By.xpath("//span[contains(text(),'Request Something')]")).click();
		d.findElement(By.xpath("//div[contains(@class,'myItem')]//span[contains(@class,'ng-binding')][contains(text(),'Request a TouchNet Store')]")).click();
		d.findElement(By.xpath("//input[@id='sp_formfield_building_location']")).sendKeys("ARDC");
		d.findElement(By.xpath("//button[@name='submit']")).click();
		String reqno = d.findElement(By.xpath("/html[1]/body[1]/div[1]/section[1]/main[1]/div[2]/div[1]/sp-page-row[1]/div[1]/div[1]/span[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/a[1]/div[1]")).getText();
		output(" Request  Touch Net store -: "+reqno+" --Passed");
		output("____________________________________________________________");
	}
	
	public void testCIModifyServerRequest()
	{
		d.manage().timeouts().implicitlyWait(40,TimeUnit.SECONDS);
		d.findElement(By.xpath("//span[contains(text(),'Request Something')]")).click();
		d.findElement(By.xpath("//span[contains(text(),'Modify Server Request')]")).click();
		try
		{
			d.findElement(By.xpath("//*[@id=\"sp_formfield_modify_server_name\"]")).sendKeys("server name");
			
			List<WebElement> all = d.findElements(By.cssSelector("input.select2-focusser.select2-offscreen"));
			
			WebElement i1 = all.get(2);
			Thread.sleep(500);
			i1.sendKeys("Yes"+Keys.ENTER);
			
			WebElement i2 = all.get(3);
			Thread.sleep(500);
			i2.sendKeys("Yes"+Keys.ENTER);
			
			WebElement i3 = all.get(4);
			Thread.sleep(500);
			i3.sendKeys("Yes"+Keys.ENTER);
			
			WebElement i4 = all.get(5);
			Thread.sleep(500);
			i4.sendKeys("8GB"+Keys.ENTER);
			
			WebElement i5 = all.get(6);
			Thread.sleep(500);
			i5.sendKeys("2"+Keys.ENTER);
			
			WebElement i6 = all.get(7);
			Thread.sleep(500);
			i6.sendKeys("Yes"+Keys.ENTER);
			
			d.findElement(By.xpath("//textarea[@id='sp_formfield_modify_snapshot_reason']")).sendKeys("modify reason is, actually I am not sure");
			d.findElement(By.xpath("//input[@id='sp_formfield_modify_snapshot_date']")).sendKeys("09-01-2019");

			d.findElement(By.xpath("//input[@id='sp_formfield_modify_drive_letter']")).sendKeys("K:");
			d.findElement(By.xpath("//input[@id='sp_formfield_modify_space_amount']")).sendKeys("10GB");
			d.findElement(By.xpath("//button[@name='submit']")).click();
			String reqno = d.findElement(By.xpath("/html[1]/body[1]/div[1]/section[1]/main[1]/div[2]/div[1]/sp-page-row[1]/div[1]/div[1]/span[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/a[1]/div[1]")).getText();
			output("Modify Server Request Request -: "+reqno);
			output("____________________________________________________________");
		}
		catch (Exception ex)
		{
			System.out.println("ERROR**********************************************************");
			System.out.println(" exception in Modify Server Request"+ex.getMessage());
			System.out.println("ERROR**********************************************************");	
		}
	}
	
	public void testCIBoxStorageRequest()
	{
	
		d.manage().timeouts().implicitlyWait(40,TimeUnit.SECONDS);
		d.findElement(By.xpath("//span[contains(text(),'Request Something')]")).click();
		d.findElement(By.xpath("//span[contains(text(),'Box Storage Request')]")).click();
		try
		{
			List<WebElement> all = d.findElements(By.cssSelector("input.select2-focusser.select2-offscreen"));
			WebElement i = all.get(2);
			Thread.sleep(500);
			i.sendKeys("Yes"+Keys.ENTER);
			
			d.findElement(By.xpath("//button[@name='submit']")).click();
			String reqno = d.findElement(By.xpath("/html[1]/body[1]/div[1]/section[1]/main[1]/div[2]/div[1]/sp-page-row[1]/div[1]/div[1]/span[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/a[1]/div[1]")).getText();
			output("Box Storage Request -: "+reqno+" --Passed");
			output("____________________________________________________________");
		
		}
		catch (Exception ex)
		{
			System.out.println("ERROR**********************************************************");
			System.out.println(" exception in Box Storage  Request"+ex.getMessage());
			System.out.println("ERROR**********************************************************");	
		}
	}
	public void testCIProvisionServerRequest()
	{
		d.manage().timeouts().implicitlyWait(40,TimeUnit.SECONDS);
		d.findElement(By.xpath("//span[contains(text(),'Request Something')]")).click();
		
		d.findElement(By.xpath("//div[contains(@class,'myItem')]//span[contains(@class,'ng-binding')][contains(text(),'Provision Server Request')]")).click();
		try
		{
			d.findElement(By.xpath("//textarea[@id='sp_formfield_server_purpose']")).sendKeys("purpose");
			d.findElement(By.xpath("//input[@id='sp_formfield_server_admin']")).sendKeys("admin");
			
			List<WebElement> all = d.findElements(By.cssSelector("input.select2-focusser.select2-offscreen"));
			
			WebElement i = all.get(3);
			Thread.sleep(500);
			i.sendKeys("Yes"+Keys.ENTER);
			//d.findElement(By.className("select2-result-cell")).click();	

			WebElement i1 = all.get(4);
			Thread.sleep(500);
			i1.sendKeys("Windows"+Keys.ENTER);
			//d.findElement(By.className("select2-result-cell")).click();	

			WebElement i2 = all.get(5);
			Thread.sleep(500);
			i2.sendKeys("2"+Keys.ENTER);
			//d.findElement(By.className("select2-result-cell")).click();	

			WebElement i3 = all.get(6);
			Thread.sleep(500);
			i3.sendKeys("8GB"+Keys.ENTER);
			//d.findElement(By.className("select2-result-cell")).click();	

			WebElement i4 = all.get(7);
			Thread.sleep(500);
			i4.sendKeys("Yes"+Keys.ENTER);
			//d.findElement(By.className("select2-result-cell")).click();	

			d.findElement(By.xpath("//textarea[@id='sp_formfield_server_share_users']")).sendKeys("Radha, radhap, readonly");
			
			d.findElement(By.xpath("//button[@name='submit']")).click();
			String reqno = d.findElement(By.xpath("/html[1]/body[1]/div[1]/section[1]/main[1]/div[2]/div[1]/sp-page-row[1]/div[1]/div[1]/span[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/a[1]/div[1]")).getText();
			
			output("Provisioning Server Request -: "+reqno+" --Passed");
			output("____________________________________________________________");
		
		
		}
		catch (Exception ex)
		{
			System.out.println("ERROR**********************************************************");
			System.out.println(" provision server"+ex.getMessage());
			System.out.println("ERROR**********************************************************");	
		}
	}
	
	
	public void testCIGeneralHWSWRequest()
	{
		d.manage().timeouts().implicitlyWait(40,TimeUnit.SECONDS);
		d.findElement(By.xpath("//span[contains(text(),'Request Something')]")).click();
		d.findElement(By.xpath("//span[contains(text(),'Hardware or Software Recommendation')]")).click();
	try
		{
			List<WebElement> all = d.findElements(By.cssSelector("input.select2-focusser.select2-offscreen"));
			WebElement i = all.get(2);
			Thread.sleep(500);
			i.sendKeys("Hardware"+Keys.ENTER);
			i.click();
			
			WebElement i1 = all.get(3);
			Thread.sleep(500);
			i1.sendKeys("Apple"+Keys.ENTER);
			i1.click();
			
			WebElement i2 = all.get(4);
			Thread.sleep(500);
			i2.sendKeys("Library (LIB)"+Keys.ENTER);
			i2.click();
			
			d.findElement(By.xpath("//textarea[@id='sp_formfield_sc_recommendation_utilization']")).sendKeys("radhap");
			d.findElement(By.xpath("//textarea[@id='sp_formfield_sc_recommendation_notes']")).sendKeys("more details");
			d.findElement(By.xpath("//button[@name='submit']")).click();
			String reqno = d.findElement(By.xpath("/html[1]/body[1]/div[1]/section[1]/main[1]/div[2]/div[1]/sp-page-row[1]/div[1]/div[1]/span[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/a[1]/div[1]")).getText();
			output("General Hardware Request : "+reqno+" --Passed");
			output("____________________________________________________________");
	
		}
		catch (Exception ex)
		{
			System.out.println("ERROR**********************************************************");
			System.out.println(" General Hardware software"+ex.getMessage());
			System.out.println("ERROR**********************************************************");
			
		}
	}
	public void testCIStandardComputerSetup()
	{
		d.manage().timeouts().implicitlyWait(40,TimeUnit.SECONDS);
		d.findElement(By.xpath("//span[contains(text(),'Request Something')]")).click();
		d.findElement(By.xpath("//span[contains(text(),'Standard Computer Setup')]")).click();
		
		//computer type
				d.findElement(By.xpath("//span[@id='select2-chosen-1']")).click();
				d.findElement(By.xpath("//input[@id='s2id_autogen1_search']")).sendKeys("Dell"+Keys.ENTER);
			
				//netids
				d.findElement(By.xpath("//textarea[@id='sp_formfield_net_ids']")).sendKeys("radhap@uta.edu");
				d.findElement(By.xpath("//input[@id='sp_formfield_room']")).sendKeys("108"+Keys.ENTER);
				d.findElement(By.xpath("//textarea[@id='sp_formfield_net_ids']")).sendKeys("radhap"+Keys.ENTER);
		
				
				d.findElement(By.xpath("//div[@id='s2id_sp_formfield_building']//a[@class='select2-choice select2-default form-control']")).click();
				
				d.findElement(By.xpath("//div[@id='select2-drop']//input[@class='select2-input']")).sendKeys("ARDC"+Keys.ENTER);
				
				
				d.findElement(By.xpath("//div[@id='s2id_sp_formfield_standard_college_school']//a[@class='select2-choice select2-default form-control']")).click();
				d.findElement(By.xpath("//div[@id='select2-drop']//input[@class='select2-input']")).sendKeys("Library (LIB)");
				
				/*try
		{
			List<WebElement> all = d.findElements(By.cssSelector("input.select2-focusser.select2-offscreen"));
			//WebElement i = all.get(3);
			//Thread.sleep(500);
			//i.sendKeys("Yes"+Keys.ENTER);
			
		
			WebElement i1 = all.get(3);
			//Thread.sleep(500);
			i1.sendKeys("Dell"+Keys.ENTER);
			
			WebElement i3 = all.get(5);
		  //  Thread.sleep(500);
			i3.sendKeys("Monday"+Keys.ENTER);
			
			
			
			
			WebElement i2 = all.get(4);
		//   Thread.sleep(500);
			i2.sendKeys("ARDC"+Keys.ENTER);
			i2.click();
			
			
			
			
			WebElement i5 = all.get(7);
			//Thread.sleep(500);
			i5.sendKeys("Library(LIB)"+Keys.ENTER);
			i5.click();
			
			d.findElement(By.xpath("//div[@id='s2id_sp_formfield_building']//a[@class='select2-choice select2-default form-control']")).sendKeys("ARDC");
			//d.findElement(By.className("select2-result-label")).click();	
			d.manage().timeouts().implicitlyWait(40,TimeUnit.SECONDS);
			
			d.findElement(By.xpath("//button[@name='submit']")).click();
			String reqno = d.findElement(By.xpath("/html[1]/body[1]/div[1]/section[1]/main[1]/div[2]/div[1]/sp-page-row[1]/div[1]/div[1]/span[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/a[1]/div[1]")).getText();
	
			output("Standard Computer Setup Request -: "+reqno+" --Passed");
			output("____________________________________________________________");
		}
		catch (Exception ex)
		{
			System.out.println("ERROR**********************************************************");
			System.out.println(" standard computer set up"+ex.getMessage());
			System.out.println("ERROR**********************************************************");	
		}*/
	}
	public void testCISWPurchaseApprovalRequest()
	{
		d.manage().timeouts().implicitlyWait(40,TimeUnit.SECONDS);
		d.findElement(By.xpath("//span[contains(text(),'Request Something')]")).click();
		d.findElement(By.xpath("//div[contains(@class,'myItem')]//span[contains(@class,'ng-binding')][contains(text(),'Software or other EIR Approval')]")).click();
		d.findElement(By.xpath("//*[@id=\"sp_formfield_software_purchase_contact_person\"]")).sendKeys("radhap");
		d.findElement(By.xpath("//*[@id=\"sp_formfield_software_name\"]")).sendKeys("Selenium");
		
		//software type
		d.findElement(By.xpath("//span[@id='select2-chosen-1']")).click();
		d.findElement(By.xpath("//input[@id='s2id_autogen1_search']")).sendKeys("Desktop Application"+Keys.ENTER);
	
		//impact
		d.findElement(By.xpath("//span[@id='select2-chosen-2']")).click();
		d.findElement(By.xpath("//input[@id='s2id_autogen2_search']")).sendKeys("Multiple Users"+Keys.ENTER);
	
		
		d.findElement(By.xpath("//*[@id=\"sp_formfield_software_purchase_version_details\"]")).sendKeys("verson 7");
		d.findElement(By.xpath("//*[@id=\"sp_formfield_software_purchase_purpose\"]")).sendKeys("for Test automation");
	
		//funding source
		d.findElement(By.xpath("//span[@id='select2-chosen-3']")).click();
		d.findElement(By.xpath("//input[@id='s2id_autogen3_search']")).sendKeys("Grant"+Keys.ENTER);
	
		//procurement method
		d.findElement(By.xpath("//span[@id='select2-chosen-4']")).click();
		d.findElement(By.xpath("//input[@id='s2id_autogen4_search']")).sendKeys("Free"+Keys.ENTER);
	
		
		//has software been purchased previously
		d.findElement(By.xpath("//span[@id='select2-chosen-5']")).click();
		d.findElement(By.xpath("//input[@id='s2id_autogen5_search']")).sendKeys("Yes"+Keys.ENTER);
	
		
		//is software used directly by students
		d.findElement(By.xpath("//span[@id='select2-chosen-6']")).click();
		d.findElement(By.xpath("//input[@id='s2id_autogen6_search']")).sendKeys("Yes"+Keys.ENTER);
	
		//is software used for research
		d.findElement(By.xpath("//span[@id='select2-chosen-7']")).click();
		d.findElement(By.xpath("//input[@id='s2id_autogen7_search']")).sendKeys("Yes"+Keys.ENTER);
	
		//how many employees use this on  an annual basis
		d.findElement(By.xpath("//span[@id='select2-chosen-8']")).click();
		d.findElement(By.xpath("//input[@id='s2id_autogen8_search']")).sendKeys("0"+Keys.ENTER);
	
		
		//is software urelated to a project
		d.findElement(By.xpath("//span[@id='select2-chosen-9']")).click();
		d.findElement(By.xpath("//input[@id='s2id_autogen9_search']")).sendKeys("Yes"+Keys.ENTER);
	
		
		//will it require OIT Assistance to install or support
		d.findElement(By.xpath("//span[@id='select2-chosen-10']")).click();
		d.findElement(By.xpath("//input[@id='s2id_autogen10_search']")).sendKeys("Yes"+Keys.ENTER);
	
		
		//will it have to be run in UTA location
		d.findElement(By.xpath("//span[@id='select2-chosen-11']")).click();
		d.findElement(By.xpath("//input[@id='s2id_autogen11_search']")).sendKeys("Yes"+Keys.ENTER);
	
		
		//is software licensing manage limited number of students.
		d.findElement(By.xpath("//span[@id='select2-chosen-12']")).click();
		d.findElement(By.xpath("//input[@id='s2id_autogen12_search']")).sendKeys("Yes"+Keys.ENTER);
	
		
		//will web database be used to collect or transmit data
		d.findElement(By.xpath("//span[@id='select2-chosen-13']")).click();
		d.findElement(By.xpath("//input[@id='s2id_autogen13_search']")).sendKeys("Yes"+Keys.ENTER);
	
		//Does software support mission critical application
		d.findElement(By.xpath("//span[@id='select2-chosen-14']")).click();
		d.findElement(By.xpath("//input[@id='s2id_autogen14_search']")).sendKeys("Yes"+Keys.ENTER);
	
		//does it need third party cloud
		d.findElement(By.xpath("//span[@id='select2-chosen-15']")).click();
		d.findElement(By.xpath("//input[@id='s2id_autogen15_search']")).sendKeys("Yes"+Keys.ENTER);
	
		//is software security software
		d.findElement(By.xpath("//span[@id='select2-chosen-16']")).click();
		d.findElement(By.xpath("//input[@id='s2id_autogen16_search']")).sendKeys("Yes"+Keys.ENTER);
	
		d.findElement(By.xpath("//*[@id=\"sp_formfield_software_purchase_estimated_cost\"]")).sendKeys("estimated path");
		
		d.findElement(By.xpath("//button[@name='submit']")).click();
		String reqno = d.findElement(By.xpath("/html[1]/body[1]/div[1]/section[1]/main[1]/div[2]/div[1]/sp-page-row[1]/div[1]/div[1]/span[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/a[1]/div[1]")).getText();
			
			output("Software Purchase Approval -: "+reqno+" --Passed");
			output("____________________________________________________________");
	
		
		
		
	}
	public void testCIHWSWInstallationRequest()
	{
		d.manage().timeouts().implicitlyWait(40,TimeUnit.SECONDS);
		d.findElement(By.xpath("//span[contains(text(),'Request Something')]")).click();
		try 
		{
			d.findElement(By.xpath("//span[contains(text(),'Hardware or Software Installation')]")).click();
			d.findElement(By.xpath("//*[@id=\"sp_formfield_sc_setup_details_room\"]")).sendKeys("room 108");
			d.findElement(By.xpath("//*[@id=\"sp_formfield_sc_desktop_additional_info\"]")).sendKeys("additional info");
			
			List<WebElement> all = d.findElements(By.cssSelector("input.select2-focusser.select2-offscreen"));
			
			WebElement i1 = all.get(2);
			Thread.sleep(500);
			i1.sendKeys("Hardware"+Keys.ENTER);
			
			WebElement i3 = all.get(6);
			Thread.sleep(500);
			i3.sendKeys("Monday"+Keys.ENTER);
		
			WebElement i5 = all.get(8);
			Thread.sleep(500);
			i5.sendKeys("Technology Learning Space"+Keys.ENTER);
			
			WebElement i6 = all.get(9);
			Thread.sleep(500);
			i6.sendKeys("Students"+Keys.ENTER);
			
			WebElement i8 = all.get(4);
			Thread.sleep(500);
			i8.sendKeys("Apple"+Keys.ENTER);
			
			WebElement i4 = all.get(7);
			Thread.sleep(500);
			i4.sendKeys("Morning (8am-12noon)"+Keys.ENTER);
		
			WebElement i7 = all.get(3);
			Thread.sleep(500);
			i7.sendKeys("021394"+Keys.ENTER);
			d.findElement(By.className("select2-result-label")).click();	
			
			WebElement i9 = all.get(5);
			Thread.sleep(500);
			i9.sendKeys("ARDC"+Keys.ENTER);
			d.findElement(By.className("select2-result-label")).click();	
			
			d.findElement(By.xpath("//button[@name='submit']")).click();
			String reqno = d.findElement(By.xpath("/html[1]/body[1]/div[1]/section[1]/main[1]/div[2]/div[1]/sp-page-row[1]/div[1]/div[1]/span[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/a[1]/div[1]")).getText();
			output("Software Installation Request : "+reqno+" --Passed");
			output("____________________________________________________________");
		}
		catch (Exception ex)
		{
			System.out.println("ERROR**********************************************************");
			System.out.println(" software or Hardware Installation Request"+ex.getMessage());
			System.out.println("ERROR**********************************************************");
		}
	}
	public void testCINWPrinterRequest()
	{
		d.manage().timeouts().implicitlyWait(40,TimeUnit.SECONDS);
		d.findElement(By.xpath("//span[contains(text(),'Request Something')]")).click();
		d.findElement(By.xpath("//span[contains(text(),'Network Printer Setup')]")).click();
		try
		{
			d.findElement(By.xpath("//*[@id=\"sp_formfield_sc_nw_print_rm\"]")).sendKeys("printer room");
			d.findElement(By.xpath("//*[@id=\"sp_formfield_sc_nw_print_additional\"]")).sendKeys("additional-info");
			
			List<WebElement> all = d.findElements(By.cssSelector("input.select2-focusser.select2-offscreen"));
			
			WebElement i1 = all.get(3);
			Thread.sleep(500);
			i1.sendKeys("HP"+Keys.ENTER);
			
			WebElement i3 = all.get(5);
			Thread.sleep(500);
			i3.sendKeys("Students"+Keys.ENTER);
			
			
			
			WebElement i6 = all.get(6);
			Thread.sleep(500);
			i6.sendKeys("Library (LIB)"+Keys.ENTER);
			i6.click();
			
			WebElement i5 = all.get(4);
			Thread.sleep(500);
			i5.sendKeys("ARDC"+Keys.ENTER+Keys.ENTER);
			i5.click();
			
			d.findElement(By.className("select2-result-label")).click();	
			
			d.findElement(By.xpath("//button[@name='submit']")).click();
			String reqno = d.findElement(By.xpath("/html[1]/body[1]/div[1]/section[1]/main[1]/div[2]/div[1]/sp-page-row[1]/div[1]/div[1]/span[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/a[1]/div[1]")).getText();
		
			output("Network Printer Request : "+reqno+" --Passed");
			output("____________________________________________________________");
	
		}
		catch (Exception ex)
		{
			System.out.println("ERROR**********************************************************");
			System.out.println(" Network Printer Request"+ex.getMessage());
			System.out.println("ERROR**********************************************************");	
		}
	}
	public void testRepurposeReImageRequest()
	{
		d.manage().timeouts().implicitlyWait(40,TimeUnit.SECONDS);
		d.findElement(By.xpath("//span[contains(text(),'Request Something')]")).click();
		d.findElement(By.xpath("//span[contains(text(),'Re-purpose/Re-image Computer')]")).click();
		try
		{
			d.findElement(By.xpath("//*[@id=\"sp_formfield_sc_setup_details_room\"]")).sendKeys("10");
			d.findElement(By.xpath("//*[@id=\"sp_formfield_sc_desktop_additional_info\"]")).sendKeys("additional info");
			
			List<WebElement> all = d.findElements(By.cssSelector("input.select2-focusser.select2-offscreen"));
			
			WebElement i3 = all.get(5);
			Thread.sleep(500);
			i3.sendKeys("Library (LIB)"+Keys.ENTER);
			i3.click();
			
			WebElement i1 = all.get(3);
			Thread.sleep(500);
			i1.sendKeys("Apple"+Keys.ENTER);
			i1.click();
			
			WebElement i2 = all.get(2);
			Thread.sleep(500);
			i2.sendKeys("11311"+Keys.ENTER);
			i2.click();
			
			WebElement i4 = all.get(4);
			Thread.sleep(500);
			i4.sendKeys("ARDC"+Keys.ENTER);
			i4.click();
			
			
			
			WebElement i6 = all.get(8);
			Thread.sleep(500);
			i6.sendKeys("Students"+Keys.ENTER);
			i6.click();
		
			WebElement i7 = all.get(6);
			Thread.sleep(500);
			i7.sendKeys("Morning (8am-12noon)"+Keys.ENTER);
			i7.click();
			
			d.findElement(By.className("select2-result-label")).click();	
		
		d.findElement(By.xpath("//button[@name='submit']")).click();
			String reqno = d.findElement(By.xpath("/html[1]/body[1]/div[1]/section[1]/main[1]/div[2]/div[1]/sp-page-row[1]/div[1]/div[1]/span[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/a[1]/div[1]")).getText();
			output("Repurpose Reimage Request -: "+reqno);
			output("____________________________________________________________");
		}
		catch (Exception ex)
		{
			System.out.println("ERROR**********************************************************");
			System.out.println(" Repurpose reimage  Request"+ex.getMessage());
			System.out.println("ERROR**********************************************************");
		}
		
	}
	public void testCIHWSWRecommendationRequest()
	{
		d.manage().timeouts().implicitlyWait(40,TimeUnit.SECONDS);
		d.findElement(By.xpath("//span[contains(text(),'Request Something')]")).click();
		d.findElement(By.xpath("//span[contains(text(),'Hardware or Software Recommendation')]")).click();
		try
		{
			List<WebElement> all = d.findElements(By.cssSelector("input.select2-focusser.select2-offscreen"));
			WebElement i = all.get(2);
			Thread.sleep(500);
			i.sendKeys("Hardware"+Keys.ENTER);
			
			WebElement i1 = all.get(3);
			Thread.sleep(500);
			i1.sendKeys("Apple"+Keys.ENTER);
			
			WebElement i2 = all.get(4);
			Thread.sleep(500);
			i2.sendKeys("Office Space"+Keys.ENTER);
			
			d.findElement(By.xpath("//*[@id=\"sp_formfield_sc_recommendation_utilization\"]")).sendKeys("recommendation");
			d.findElement(By.xpath("//*[@id=\"sp_formfield_sc_recommendation_notes\"]")).sendKeys("recommendation notes");
			d.findElement(By.xpath("//button[@name='submit']")).click();
			String reqno = d.findElement(By.xpath("/html[1]/body[1]/div[1]/section[1]/main[1]/div[2]/div[1]/sp-page-row[1]/div[1]/div[1]/span[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/a[1]/div[1]")).getText();
		
			output("Hardware/Software Recommendation Request -: "+reqno+" --Passed");
			output("____________________________________________________________");
		
		}
		catch (Exception ex)
		{
			System.out.println("ERROR**********************************************************");
			System.out.println(" Software Hardware Recommendation"+ex.getMessage());
			System.out.println("ERROR**********************************************************");
		}
		
	}
	public void testCITelecomRequest()
	{
		d.manage().timeouts().implicitlyWait(40,TimeUnit.SECONDS);
		d.findElement(By.xpath("//span[contains(text(),'Request Something')]")).click();
		d.findElement(By.xpath("//div[contains(@class,'myItem')]//span[contains(@class,'ng-binding')][contains(text(),'Telecom Request')]")).click();
		try
		{
			d.findElement(By.xpath("//*[@id=\"sp_formfield_telecom_location\"]")).sendKeys("Telecom Location");
			
			List<WebElement> all = d.findElements(By.cssSelector("input.select2-focusser.select2-offscreen"));
			WebElement i = all.get(2);
			Thread.sleep(500);
			i.sendKeys("Long Distance Request"+Keys.ENTER);
			
			d.findElement(By.xpath("//*[@id=\"sp_formfield_telecom_room\"]")).sendKeys("Telecom Room");
			d.findElement(By.xpath("//*[@id=\"sp_formfield_telecom_cost_center\"]")).sendKeys("cost center");
			
			d.findElement(By.xpath("//input[@id='sp_formfield_telecom_ldc_ext']")).sendKeys("235");
			d.findElement(By.xpath("//input[@id='sp_formfield_telecom_ldc_name_ext']")).sendKeys("test");
			d.findElement(By.xpath("//textarea[@id='sp_formfield_telecom_description']")).sendKeys("for testteam");
			
			d.findElement(By.xpath("//button[@name='submit']")).click();
			String reqno = d.findElement(By.xpath("/html[1]/body[1]/div[1]/section[1]/main[1]/div[2]/div[1]/sp-page-row[1]/div[1]/div[1]/span[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/a[1]/div[1]")).getText();
			output("Telecom Request -: "+reqno+" --Passed");
			output("____________________________________________________________");
	
		}
		
		catch (Exception ex)
		{
			System.out.println("ERROR**********************************************************");
			System.out.println(" Telecom Software"+ex.getMessage());
			System.out.println("ERROR**********************************************************");
		}
	}
	
	public void testCIDomainNameServerRequest()
	{
		d.manage().timeouts().implicitlyWait(40,TimeUnit.SECONDS);
		d.findElement(By.xpath("//span[contains(text(),'Request Something')]")).click();
		d.findElement(By.xpath("//div[contains(@class,'myItem')]//span[contains(@class,'ng-binding')][contains(text(),'Domain Name Service')]")).click();
		try
		{
			List<WebElement> all = d.findElements(By.cssSelector("input.select2-focusser.select2-offscreen"));
			WebElement i = all.get(2);
			Thread.sleep(500);
			i.sendKeys("Other"+Keys.ENTER);
			d.findElement(By.xpath("//textarea[@id='sp_formfield_req_description']")).sendKeys("description");
			d.findElement(By.xpath("//button[@name='submit']")).click();
			String reqno = d.findElement(By.xpath("/html[1]/body[1]/div[1]/section[1]/main[1]/div[2]/div[1]/sp-page-row[1]/div[1]/div[1]/span[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/a[1]/div[1]")).getText();
			System.out.println("Domain Server Request : "+reqno);
			System.out.println("____________________________________________________________");
			writer.println("Domain Server Request : "+reqno+" --Passed");
			writer.println("____________________________________________________________");
			
		}
		catch (Exception ex)
		{
			System.out.println("ERROR**********************************************************");
			System.out.println(" Domain Name Server "+ex.getMessage());
			System.out.println("ERROR**********************************************************");	
		}
	}
	public void testCIStaticIPAddressRequest()
	{
		d.manage().timeouts().implicitlyWait(40,TimeUnit.SECONDS);
		d.findElement(By.xpath("//span[contains(text(),'Request Something')]")).click();
		d.findElement(By.xpath("//div[contains(@class,'myItem')]//span[contains(@class,'ng-binding')][contains(text(),'Static IP Address Request')]")).click();
		d.findElement(By.xpath("//*[@id=\"sp_formfield_reason\"]")).sendKeys("reason");
		d.findElement(By.xpath("//*[@id=\"sp_formfield_fqdn\"]")).sendKeys("Fully Qualified Domain Name");
		d.findElement(By.xpath("//*[@id=\"sp_formfield_building_subnet\"]")).sendKeys("subnet 42");
		d.findElement(By.xpath("//button[@name='submit']")).click();
		String reqno = d.findElement(By.xpath("/html[1]/body[1]/div[1]/section[1]/main[1]/div[2]/div[1]/sp-page-row[1]/div[1]/div[1]/span[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/a[1]/div[1]")).getText();
		output("IP Address Request -: "+reqno+" --Passed");
		output("____________________________________________________________");	
	
	}
	
	public void testCICablePullRequest()
	{
		//System.out.println("Test Catalog Item Cable Pull Request under Network & Telecomm");
		d.manage().timeouts().implicitlyWait(40,TimeUnit.SECONDS);
		d.findElement(By.xpath("//span[contains(text(),'Request Something')]")).click();
		d.findElement(By.xpath("//div[contains(@class,'myItem')]//span[contains(@class,'ng-binding')][contains(text(),'Cable Pull Request')]")).click();
		d.findElement(By.xpath("//input[@id='sp_formfield_cost_center']")).sendKeys("31662");
		d.findElement(By.xpath("//input[@id='sp_formfield_building']")).sendKeys("ARDC");
		d.findElement(By.xpath("//input[@id='sp_formfield_room']")).sendKeys("108");
		d.findElement(By.xpath("//button[@name='submit']")).click();
		String reqno = d.findElement(By.xpath("/html[1]/body[1]/div[1]/section[1]/main[1]/div[2]/div[1]/sp-page-row[1]/div[1]/div[1]/span[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/a[1]/div[1]")).getText();
		output("Cable Pull Request -: "+reqno+" --Passed");
		output("____________________________________________________________");
	}
	
	public void testCIInstallationofWirelessAccessRequest()
	{
		d.manage().timeouts().implicitlyWait(40,TimeUnit.SECONDS);
		d.findElement(By.xpath("//span[contains(text(),'Request Something')]")).click();
		d.findElement(By.xpath("//span[contains(text(),'Installation of Wireless Access')]")).click();
		d.findElement(By.xpath("//input[@id='sp_formfield_iwa_cost_center']")).sendKeys("31662");
		d.findElement(By.xpath("//input[@id='sp_formfield_building']")).sendKeys("ARDC");
		d.findElement(By.xpath("//input[@id='sp_formfield_room']")).sendKeys("108");
		d.findElement(By.xpath("//textarea[@id='sp_formfield_comments']")).sendKeys("additional info");
		d.findElement(By.xpath("//button[@name='submit']")).click();
		String reqno = d.findElement(By.xpath("/html[1]/body[1]/div[1]/section[1]/main[1]/div[2]/div[1]/sp-page-row[1]/div[1]/div[1]/span[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/a[1]/div[1]")).getText();
		output("Installation of Wireless Access Request -: "+reqno);
		output("____________________________________________________________");

	}
	
	public void testCISharedResourceorRoomMailboxRequest()
	{
		d.manage().timeouts().implicitlyWait(40,TimeUnit.SECONDS);
		d.findElement(By.xpath("//span[contains(text(),'Request Something')]")).click();
		d.findElement(By.xpath("//span[contains(text(),'Shared (Resource) or Room Mailbox Access')]")).click();
		try
		{
			d.findElement(By.xpath("//input[@id='sp_formfield_resource_room_mailbox']")).sendKeys("testing@uta.edu");
			
			List<WebElement> all = d.findElements(By.cssSelector("input.select2-focusser.select2-offscreen"));
			WebElement i = all.get(2);
			Thread.sleep(500);
			i.sendKeys("Access"+Keys.ENTER);
			
			d.findElement(By.xpath("//button[@name='submit']")).click();
			String reqno = d.findElement(By.xpath("/html[1]/body[1]/div[1]/section[1]/main[1]/div[2]/div[1]/sp-page-row[1]/div[1]/div[1]/span[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/a[1]/div[1]")).getText();
		
			output("Shared Resource Room/Mailbox Request Creat-: "+reqno+" --Passed");
			output("____________________________________________________________");
		}
		catch (Exception ex)
		{
			System.out.println("ERROR**********************************************************");
			System.out.println(" Shared Resource Room Mailbox"+ex.getMessage());
			System.out.println("ERROR**********************************************************");
		}
	}
	
	public void testCICreateResourceorRoomMailboxRequest()
	{
		d.manage().timeouts().implicitlyWait(40,TimeUnit.SECONDS);
		d.findElement(By.xpath("//span[contains(text(),'Request Something')]")).click();
		d.findElement(By.xpath("//span[contains(text(),'Create Resource or Room Mailbox')]")).click();
		d.findElement(By.xpath("//*[@id=\"sp_formfield_create_resource_room_mailbox_email_add\"]")).sendKeys("radhap@uta.edu");
		d.findElement(By.xpath("//*[@id=\"sp_formfield_create_resource_room_mailbox_display\"]")).sendKeys("display name");
		d.findElement(By.xpath("//*[@id=\"sp_formfield_create_resource_room_mailbox_netids\"]")).sendKeys("radhap,harris");
		d.findElement(By.xpath("//*[@id=\"sp_formfield_create_resource_room_mailbox_add_owners\"]")).sendKeys("radha, Pavel");
		try
		{
			List<WebElement> all = d.findElements(By.cssSelector("input.select2-focusser.select2-offscreen"));
			
			WebElement i2 = all.get(3);
			Thread.sleep(500);
			i2.sendKeys("Resource Mailbox"+Keys.ENTER);
			
			WebElement i3 = all.get(4);
			Thread.sleep(500);
			i3.sendKeys("Access"+Keys.ENTER);
			
			WebElement i1 = all.get(2);
			Thread.sleep(500);
			i1.sendKeys("A Elliott"+Keys.ENTER);
			d.findElement(By.className("select2-result-cell")).click();	
			
			d.findElement(By.xpath("//button[@name='submit']")).click();
			String reqno = d.findElement(By.xpath("/html[1]/body[1]/div[1]/section[1]/main[1]/div[2]/div[1]/sp-page-row[1]/div[1]/div[1]/span[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/a[1]/div[1]")).getText();
			
			output("Resource Room Mail Box Request -: "+reqno+" --Passed");
			output("____________________________________________________________");
		
		}
	catch (Exception ex)
	{
		System.out.println("ERROR**********************************************************");
		System.out.println(" Shared Resource Room Mailbox"+ex.getMessage());
		System.out.println("ERROR**********************************************************");
	}
	}
	
	public void testCIDistributionGrporListServRequest()
	{
		d.manage().timeouts().implicitlyWait(40,TimeUnit.SECONDS);
		d.findElement(By.xpath("//span[contains(text(),'Request Something')]")).click();
		d.findElement(By.xpath("//span[contains(text(),'New Distribution Group or Listserv Request')]")).click();
		d.findElement(By.xpath("//*[@id=\"sp_formfield_dist_grp_name\"]")).sendKeys("course 1");
		d.findElement(By.xpath("//*[@id=\"sp_formfield_dist_grp_members\"]")).sendKeys("radhap");
		d.findElement(By.xpath("//*[@id=\"sp_formfield_dist_grp_description\"]")).sendKeys("study gropu");
		d.findElement(By.xpath("//*[@id=\"sp_formfield_dist_grp_owner\"]")).sendKeys("Radha");
		try
		{
			List<WebElement> all = d.findElements(By.cssSelector("input.select2-focusser.select2-offscreen"));
			
			WebElement i1 = all.get(2);
			Thread.sleep(500);
			i1.sendKeys("Yes"+Keys.ENTER);
			
			WebElement i2 = all.get(3);
			Thread.sleep(500);
			i2.sendKeys("Inside the organization only"+Keys.ENTER);
			
			WebElement i3 = all.get(4);
			Thread.sleep(500);
			i3.sendKeys("Yes"+Keys.ENTER);
			
			d.findElement(By.xpath("//textarea[@id='sp_formfield_dist_grp_mod_names']")).sendKeys("radha");
			
			d.findElement(By.xpath("//button[@name='submit']")).click();
			String reqno = d.findElement(By.xpath("/html[1]/body[1]/div[1]/section[1]/main[1]/div[2]/div[1]/sp-page-row[1]/div[1]/div[1]/span[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/a[1]/div[1]")).getText();
	
			output("Distribution Group or listserv Request -: "+reqno+" --Passed");
			output("____________________________________________________________");

		}
		catch (Exception ex)
		{
			System.out.println("ERROR**************************************************************");
			System.out.println(" exception in Distribution Group or ListServ"+ ex.getMessage());
			System.out.println("ERROR**************************************************************");
		}
	}
		
	
	public void testCIModifyEmailAliasRequest()
	{
			d.manage().timeouts().implicitlyWait(40,TimeUnit.SECONDS);
		d.findElement(By.xpath("//span[contains(text(),'Request Something')]")).click();
		d.findElement(By.xpath("//span[contains(text(),'Modify Email Alias')]")).click();
		d.findElement(By.xpath("//input[@id='sp_formfield_modify_email_alias_oldname']")).sendKeys("oldname");
		d.findElement(By.xpath("//input[@id='sp_formfield_modify_email_alias_firstname']")).sendKeys("firstname");
		d.findElement(By.xpath("//input[@id='sp_formfield_modify_email_alias_middlename']")).sendKeys("middlename");
		d.findElement(By.xpath("//input[@id='sp_formfield_modify_email_alias_lastname']")).sendKeys("lastname");
		try
		{
			List <WebElement> all = d.findElements(By.cssSelector("input.select2-focusser.select2-offscreen"));
			
			WebElement i = all.get(2);
			Thread.sleep(500);
			i.sendKeys("Yes"+Keys.ENTER);
			d.findElement(By.xpath("//button[@name='submit']")).click();
		
			String reqno = d.findElement(By.xpath("/html[1]/body[1]/div[1]/section[1]/main[1]/div[2]/div[1]/sp-page-row[1]/div[1]/div[1]/span[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/a[1]/div[1]")).getText();
		
			output("Modify Email Alias Request -: "+reqno+" --Passed");
			output("____________________________________________________________");
		
		}
		catch (Exception ex)
		{   
			System.out.println("ERROR**********************************************************");
			System.out.println(" Email Alias "+ex.getMessage());
			System.out.println("ERROR**********************************************************");
			
		}
	}
	
	public void testCISecureDocUserModificationRequest()
	{
		d.manage().timeouts().implicitlyWait(40,TimeUnit.SECONDS);
		try
		{
			d.findElement(By.xpath("//span[contains(text(),'Request Something')]")).click();
			d.findElement(By.xpath("//span[contains(text(),'SecureDoc User Modification')]")).click();
			d.findElement(By.xpath("//textarea[@id='sp_formfield_securedoc_netids']")).sendKeys("radhap,harris");
		
			List<WebElement> all = d.findElements(By.cssSelector("input.select2-focusser.select2-offscreen"));
			
			WebElement i = all.get(2);
			Thread.sleep(500);
		    i.sendKeys("Add User"+ Keys.ENTER);
		    
			WebElement i3 = all.get(5);
			Thread.sleep(500);
			i3.sendKeys("Work Purpose"+Keys.ENTER);
		
			WebElement i2 = all.get(4);
			Thread.sleep(500);
			i2.sendKeys("011311" + Keys.ENTER);
			d.findElement(By.className("select2-result-label")).click();	
			
			WebElement i1 = all.get(3);
			Thread.sleep(500);
			i1.sendKeys("Abdul Rasheed"+Keys.ENTER);
			d.findElement(By.className("select2-result-cell")).click();	

			d.findElement(By.xpath("//button[@name='submit']")).click();
			String reqno = d.findElement(By.xpath("/html[1]/body[1]/div[1]/section[1]/main[1]/div[2]/div[1]/sp-page-row[1]/div[1]/div[1]/span[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/a[1]/div[1]")).getText();
		
			output("SecureDoc User Modification Request -: "+reqno+" --Passed");
			output("___________________________________________________________________");
		}
		catch (Exception ex)
		{   
			System.out.println("ERROR**********************************************************");
			System.out.println(" Testing SecureDoc User Modification"+ex.getMessage());
			System.out.println("ERROR**********************************************************");
		}
	}
	public void testCIWhitelistingRequest()
	{
		d.manage().timeouts().implicitlyWait(40,TimeUnit.SECONDS);
		d.findElement(By.xpath("//span[contains(text(),'Request Something')]")).click();
		d.findElement(By.xpath("//span[contains(text(),'Whitelisting')]")).click();
		d.findElement(By.xpath("//textarea[@id='sp_formfield_comments']")).sendKeys("ABC ..");
		d.findElement(By.xpath("//button[@name='submit']")).click();
		String reqno = d.findElement(By.xpath("/html[1]/body[1]/div[1]/section[1]/main[1]/div[2]/div[1]/sp-page-row[1]/div[1]/div[1]/span[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/a[1]/div[1]")).getText();
	
		output("WhiteListing Request -: "+reqno+" --Passed");
		output("__________________________________________________________________");

		
	}
	public void testCIFireWallRequest()
	{
		d.manage().timeouts().implicitlyWait(40,TimeUnit.SECONDS);
		d.findElement(By.xpath("//span[contains(text(),'Request Something')]")).click();
		d.findElement(By.xpath("//div[contains(@class,'myItem')]//span[contains(@class,'ng-binding')][contains(text(),'Firewall Request')]")).click();
		d.findElement(By.xpath("//textarea[@id='sp_formfield_firewall_reason']")).sendKeys("reason");
		d.findElement(By.xpath("//input[@id='sp_formfield_restricted_host']")).sendKeys("restricted");
		d.findElement(By.xpath("//textarea[@id='sp_formfield_allowed_host']")).sendKeys("allowed");
		d.findElement(By.xpath("//textarea[@id='sp_formfield_ports_services']")).sendKeys("8080");
		try
		{
			List<WebElement> all = d.findElements(By.cssSelector("input.select2-focusser.select2-offscreen"));
			WebElement i = all.get(2);
			Thread.sleep(500);
			i.sendKeys("Yes"+Keys.ENTER);	//d.findElement(By.xpath("//*[@id=\"select2-chosen-13\"]")).click();
			d.findElement(By.xpath("//button[@name='submit']")).click();
			String reqno = d.findElement(By.xpath("/html[1]/body[1]/div[1]/section[1]/main[1]/div[2]/div[1]/sp-page-row[1]/div[1]/div[1]/span[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/a[1]/div[1]")).getText();
		
			output("Firewall Request -: "+reqno+" --Passed");
			output("__________________________________________________________________");
	
		}
		catch (Exception ex)
		{   
			System.out.println("ERROR**********************************************************");
			System.out.println(" Testing FireWall Request"+ex.getMessage());
			System.out.println("ERROR**********************************************************");
		}
		
	}
	public void testCINetIDPlusTokenRequest()
	{
		d.manage().timeouts().implicitlyWait(40,TimeUnit.SECONDS);
		d.findElement(By.xpath("//span[contains(text(),'Request Something')]")).click();
		d.findElement(By.xpath("//span[contains(text(),'NetIDplus Token Request')]")).click();
		//*[@id="sp_formfield_token_req_costcenter"]
		try{
			d.findElement(By.xpath("//*[@id=\"sp_formfield_token_req_costcenter\"]")).sendKeys("361112");
			d.findElement(By.xpath("//input[@id='sp_formfield_token_req_number']")).sendKeys("10");	
			d.findElement(By.xpath("//input[@id='sp_formfield_token_req_date']")).sendKeys("5-30-2019");

			List<WebElement> all = d.findElements(By.cssSelector("input.select2-focusser.select2-offscreen"));
			
			WebElement i = all.get(3);
			Thread.sleep(500);
			i.sendKeys("A Elliott");
			d.findElement(By.className("select2-result-label")).click();	

			d.findElement(By.xpath("//button[@name='submit']")).click();
			String reqno = d.findElement(By.xpath("/html[1]/body[1]/div[1]/section[1]/main[1]/div[2]/div[1]/sp-page-row[1]/div[1]/div[1]/span[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/a[1]/div[1]")).getText();
			
			output("NetID Plus Token Request -: "+reqno+" --Passed");
			output("________________________________________________________________");

		}
		catch (Exception ex)
		{   
			System.out.println("ERROR**********************************************************");
			System.out.println(" Testing netidplustoken"+ex.getMessage());
			System.out.println("ERROR**********************************************************");
		}
	}
	public void testCINetIDPlusTokenAssignment()
	{
		d.manage().timeouts().implicitlyWait(40,TimeUnit.SECONDS);
		d.findElement(By.xpath("//span[contains(text(),'Request Something')]")).click();
		d.findElement(By.xpath("//div[contains(@class,'myItem')]//span[contains(@class,'ng-binding')][contains(text(),'NetIDplus Token Assignment')]")).click();
		d.findElement(By.xpath("//input[@id='sp_formfield_token_assign_serial']")).sendKeys("assign tokens");
		try
		{
			List<WebElement> all = d.findElements(By.cssSelector("input.select2-focusser.select2-offscreen"));
			
			WebElement i1 = all.get(3);
			Thread.sleep(500);
			i1.sendKeys("Remove"+Keys.ENTER);
			
			System.out.println("remove");
			
			WebElement i2 = all.get(4);
			Thread.sleep(500);
			i2.sendKeys("Accounting"+Keys.ENTER);
			System.out.println("accounting");
			d.findElement(By.className("select2-result-label")).click();	

			WebElement i = all.get(2);
			Thread.sleep(500);
			i.sendKeys("A Elliott");
			d.findElement(By.className("select2-result-label")).click();	

			d.findElement(By.xpath("//button[@name='submit']")).click();
			String reqno = d.findElement(By.xpath("/html[1]/body[1]/div[1]/section[1]/main[1]/div[2]/div[1]/sp-page-row[1]/div[1]/div[1]/span[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/a[1]/div[1]")).getText();
		
			output("NetID Token Assignment -: "+reqno+" --Passed");
			output("____________________________________________________________");
		
		}
		catch (Exception ex)
		{
			System.out.println("ERROR**********************************************************");
			System.out.println(" NetID Plus Token Assignment Catalog Request"+ex.getMessage());
			System.out.println("ERROR**********************************************************");
		}
		
	}
	
	public void testCIDCustomReportRequest()
	{
		d.manage().timeouts().implicitlyWait(40,TimeUnit.SECONDS);
		d.findElement(By.xpath("//span[contains(text(),'Request Something')]")).click();
		d.findElement(By.xpath("//span[@class='ng-binding'][contains(text(),'Custom Report Request')]")).click();
		d.findElement(By.xpath("//textarea[@id='sp_formfield_custom_report_req_ids']")).sendKeys("course 1");
		d.findElement(By.xpath("//textarea[@id='sp_formfield_custom_report_reg_reason']")).sendKeys("to learn");
		d.findElement(By.xpath("//textarea[@id='sp_formfield_custom_report_req_format']")).sendKeys("format");
		d.findElement(By.xpath("//textarea[@id='sp_formfield_custom_report_req_schedule']")).sendKeys("schedule");
		d.findElement(By.xpath("//textarea[@id='sp_formfield_custom_report_req_specs']")).sendKeys("specs");
		d.findElement(By.xpath("//button[@name='submit']")).click();
		String reqno = d.findElement(By.xpath("/html[1]/body[1]/div[1]/section[1]/main[1]/div[2]/div[1]/sp-page-row[1]/div[1]/div[1]/span[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/a[1]/div[1]")).getText();
		output("Custom Report Request -: "+reqno+" --Passed");
		output("____________________________________________________________");
	
	
	}
	
	public void testCIDDataExtractRequest()
	{
		d.manage().timeouts().implicitlyWait(40,TimeUnit.SECONDS);
		d.findElement(By.xpath("//span[contains(text(),'Request Something')]")).click();
		d.findElement(By.xpath("//span[contains(text(),'Data Extract Request')]")).click();
		try
		{
			d.findElement(By.xpath("//input[@id='sp_formfield_data_extract_owner']")).sendKeys("Radha");
			d.findElement(By.xpath("//textarea[@id='sp_formfield_data_extract_reason']")).sendKeys("Special Reasons");
			d.findElement(By.xpath("//textarea[@id='sp_formfield_data_extract_functional_specs']")).sendKeys("extract specs");
			
			List <WebElement> all = d.findElements(By.cssSelector("input.select2-focusser.select2-offscreen"));
				
			WebElement i = all.get(2);
			Thread.sleep(500);
			i.sendKeys("Yes"+Keys.ENTER);
		
			WebElement i1 = all.get(3);
			Thread.sleep(500);
			i1.sendKeys("Yes"+Keys.ENTER);
			
			d.findElement(By.xpath("//textarea[@id='sp_formfield_data_extract_oustide_location']")).sendKeys("Location ");
			d.findElement(By.xpath("//textarea[@id='sp_formfield_data_extract_another_uni_sys_reason']")).sendKeys("System Name ");
			
			d.findElement(By.xpath("//button[@name='submit']")).click();
			String reqno = d.findElement(By.xpath("/html[1]/body[1]/div[1]/section[1]/main[1]/div[2]/div[1]/sp-page-row[1]/div[1]/div[1]/span[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/a[1]/div[1]")).getText();
			
			output("Custom Extract Request -: "+reqno+" --Passed");
			output("____________________________________________________________");
	
		}	
		catch (Exception ex)
		{
			System.out.println("ERROR**********************************************************");
			System.out.println(" Data Extract Request"+ex.getMessage());
			System.out.println("ERROR**********************************************************");	
		}
	}
	
	public void testCIDDataInterfaceRequest()
	{
		d.manage().timeouts().implicitlyWait(40,TimeUnit.SECONDS);
		d.findElement(By.xpath("//span[contains(text(),'Request Something')]")).click();
		d.findElement(By.xpath("//span[@class='ng-binding'][contains(text(),'Data Interface Request')]")).click();
		d.findElement(By.xpath("//input[@id='sp_formfield_data_interface_owner']")).sendKeys("Radha");
		d.findElement(By.xpath("//input[@id='sp_formfield_data_interface_source']")).sendKeys("Oracle DB");
		d.findElement(By.xpath("//input[@id='sp_formfield_data_interface_target']")).sendKeys("target");
		d.findElement(By.xpath("//input[@id='sp_formfield_data_interface_filter']")).sendKeys("filter");
		d.findElement(By.xpath("//input[@id='sp_formfield_data_interface_schedule']")).sendKeys("Schedule");
		d.findElement(By.xpath("//textarea[@id='sp_formfield_data_interface_specs']")).sendKeys("specs");
		d.findElement(By.xpath("//button[@name='submit']")).click();
		String reqno = d.findElement(By.xpath("/html[1]/body[1]/div[1]/section[1]/main[1]/div[2]/div[1]/sp-page-row[1]/div[1]/div[1]/span[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/a[1]/div[1]")).getText();
	
		output("Data Interface Request : "+reqno+" --Passed");
		output("____________________________________________________________");
	
	
	}
	public void testCIUTAHelpCenterPage()
	{
		d.manage().timeouts().implicitlyWait(40,TimeUnit.SECONDS);
		d.findElement(By.xpath("//span[contains(text(),'Request Something')]")).click();
		d.findElement(By.xpath("//span[@class='ng-binding'][contains(text(),'UTA Help Center Page')]")).click();
		d.findElement(By.xpath("//textarea[@id='sp_formfield_description']")).sendKeys("need page describing issues with CMS ");
		d.findElement(By.xpath("//button[@name='submit']")).click();
		String reqno = d.findElement(By.xpath("/html[1]/body[1]/div[1]/section[1]/main[1]/div[2]/div[1]/sp-page-row[1]/div[1]/div[1]/span[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/a[1]/div[1]")).getText();
		output("UTA Helpdesk  Request -: "+reqno+" --Passed");	
		output("____________________________________________________________");
	
	}
	public void testCINewImageNowProcess()
	{
		d.findElement(By.xpath("//span[contains(text(),'Request Something')]")).click();
		d.findElement(By.xpath("//span[@class='ng-binding'][contains(text(),'New ImageNow Process')]")).click();
		d.findElement(By.xpath("//textarea[@id='sp_formfield_imagenow_details']")).sendKeys("Radhas access");
		d.findElement(By.xpath("//button[@name='submit']")).click();
		String reqno = d.findElement(By.xpath("/html[1]/body[1]/div[1]/section[1]/main[1]/div[2]/div[1]/sp-page-row[1]/div[1]/div[1]/span[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/a[1]/div[1]")).getText();
		
		output("New ImageNow process  Request : "+reqno+" --Passed");
		output("____________________________________________________________");	

	}
	
	public void testCIControlMJobScheduling()
	{
		d.findElement(By.xpath("//span[contains(text(),'Request Something')]")).click();
		d.findElement(By.xpath("//span[contains(text(),'Control M Job Scheduling Request')]")).click();
		try
		{
			List <WebElement> all = d.findElements(By.cssSelector("input.select2-focusser.select2-offscreen"));
			WebElement i = all.get(2);
			Thread.sleep(500);
			i.sendKeys("New Request"+Keys.ENTER);
			
			d.findElement(By.xpath("//textarea[@id='sp_formfield_controlm_description']")).sendKeys("no info");
			d.findElement(By.xpath("//input[@id='sp_formfield_controlm_date']")).sendKeys("6/1/2019");
			d.findElement(By.xpath("//button[@name='submit']")).click();
			String reqno = d.findElement(By.xpath("/html[1]/body[1]/div[1]/section[1]/main[1]/div[2]/div[1]/sp-page-row[1]/div[1]/div[1]/span[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/a[1]/div[1]")).getText();
			output("Control M Job Scheduling Request : "+reqno+" --Passed");
			output("____________________________________________________________");
	
		}
		catch (Exception ex)
		{
			System.out.println("ERROR**********************************************************");
			System.out.println(" exception in OIT Control M job  "+ex.getMessage());
			System.out.println("ERROR**********************************************************");	
		}
	}
	
	public void testCICanvasOrgnandCourses()
	{
		d.findElement(By.xpath("//span[contains(text(),'Request Something')]")).click();
		d.findElement(By.xpath("//span[@class='ng-binding'][contains(text(),'Canvas Organization & Course Request')]")).click();
		try 
		{
		
			//d.findElement(By.xpath("//label[@class='field-label ng-binding ng-scope'][contains(text(),'Academic Organization or Non-Academic Course')]")).click();
			d.findElement(By.xpath("//input[@id='sp_formfield_course_title']")).sendKeys("Algebra 1");
			d.findElement(By.xpath("//textarea[@id='sp_formfield_course_description']")).sendKeys("math course");
			d.findElement(By.xpath("//textarea[@id='sp_formfield_facilitators']")).sendKeys("Mr Bistrow & Mrs Carpenter");
			List <WebElement> all = d.findElements(By.cssSelector("input.select2-focusser.select2-offscreen"));
			WebElement i = all.get(2);
			Thread.sleep(500);
			i.sendKeys("Academic Organization"+Keys.ENTER);
			d.findElement(By.xpath("//button[@name='submit']")).click();
			String reqno = d.findElement(By.xpath("/html[1]/body[1]/div[1]/section[1]/main[1]/div[2]/div[1]/sp-page-row[1]/div[1]/div[1]/span[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/a[1]/div[1]")).getText();
		
			output("New Canvas Orgn  Request Created : "+reqno+" --Passed");
			output("____________________________________________________________");
		
		
		}
		catch (Exception ex)
		{
			System.out.println("ERROR**********************************************************");
			System.out.println(" exception in CanvasOrgnandCourses "+ex.getMessage());
			System.out.println("ERROR**********************************************************");	
		}
		
	}
	public void testCIOITTrainingRequest()
	{
		d.findElement(By.xpath("//span[contains(text(),'Request Something')]")).click();
		d.findElement(By.xpath("//span[contains(text(),'OIT and Business Training')]")).click();
		d.manage().timeouts().implicitlyWait(40,TimeUnit.SECONDS);
		try
		{
			List <WebElement> all = d.findElements(By.cssSelector("input.select2-focusser.select2-offscreen"));
			WebElement i = all.get(2);
			Thread.sleep(500);
			i.sendKeys("CMS"+Keys.ENTER);
			d.findElement(By.xpath("//button[@name='submit']")).click();
			String reqno = d.findElement(By.xpath("/html[1]/body[1]/div[1]/section[1]/main[1]/div[2]/div[1]/sp-page-row[1]/div[1]/div[1]/span[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/a[1]/div[1]")).getText();
		
			output("OIT Training Request : "+reqno+" --Passed");
			output("____________________________________________________________");
	
		
		}
		catch (Exception ex)
		{
			System.out.println("ERROR**********************************************************");
			System.out.println(" exception in OIT Training Request Catalog Item "+ex.getMessage());
			System.out.println("ERROR**********************************************************");	
		}	
	}
	public void testCInewTrainingorDocumentation()
	{
		d.findElement(By.xpath("//span[contains(text(),'Request Something')]")).click();
		d.findElement(By.xpath("//span[contains(text(),'New Training Topic or Documentation for OIT or Bus')]")).click();
		d.findElement(By.xpath("//textarea[@id='sp_formfield_v_description']")).sendKeys("testing is critical ..");
		d.findElement(By.xpath("//button[@name='submit']")).click();
		String reqno = d.findElement(By.xpath("/html[1]/body[1]/div[1]/section[1]/main[1]/div[2]/div[1]/sp-page-row[1]/div[1]/div[1]/span[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/a[1]/div[1]")).getText();
		
		output("Training Documentation Request -: "+reqno+" --Passed");
		output("____________________________________________________________");

	}
	
	public void testCINewKBArticle()
	{
		d.findElement(By.xpath("//span[contains(text(),'Request Something')]")).click();
		d.findElement(By.xpath("//span[contains(text(),'New Knowledge Base (KB) Article Request')]")).click();
		d.findElement(By.xpath("//input[@id='sp_formfield_article_topic']")).sendKeys("Testing using selenium");
		d.findElement(By.xpath("//input[@id='sp_formfield_article_sme']")).sendKeys("Radha");
		d.findElement(By.xpath("//textarea[@id='sp_formfield_article_description']")).sendKeys(" selenium will help automate the testing and save time");
		d.findElement(By.xpath("//button[@name='submit']")).click();
		String reqno = d.findElement(By.xpath("/html[1]/body[1]/div[1]/section[1]/main[1]/div[2]/div[1]/sp-page-row[1]/div[1]/div[1]/span[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/a[1]/div[1]")).getText();
	
		output("New Article  Request -: "+reqno+" --Passed");
		output("____________________________________________________________");
	
	}
	
	public void readPropFile()
	{
		try 
		{
			InputStream input = new FileInputStream("c:/config.properties");
			Properties prop = new Properties();
	
	         // load a properties file
	         prop.load(input);
	
	         // get the property value and print it out
	         username = prop.getProperty("username");
	         password = prop.getProperty("password");
			 
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		
	}
	public static void output(String str)
	{
		System.out.println(str);
		writer.println(str);
	}
	
	public void runWorkflowRequests()
	{
		//Testing Catalog Items for Accounts & Access
			output("Testing Workflowed Requests- Portal only");
				output("TESTING CATALOG ITEMS FOR ACCOUNTS & ACCESS");
			
				test.testCIAffiliateAccountExtensionRequest();   
				test.testCIMARSRequest();
				test.testEDSDataWarehouse();
				test.testGSuite();
				test.testlinkedIn();
				
				
				//Testing Catalog Items for Business Affairs
				output("\nTESTING CATALOG ITEMS FOR BUSINESS AFFAIRS\n");
				test.testCIRequestPolicy();
				test.testCItRequestBTSWebServices();
				
				//Testing for Storage and Servers
				System.out.println("\nTESTING CATALOG ITEMS FOR SERVERS & STORAGE\n");
				test.testCIBoxStorageRequest();
				test.testCIModifyServerRequest();
				test.testCIProvisionServerRequest();
				
				//Testing for Desktop & Laptop
				output("\nTESTING CATALOG ITEMS FOR DESKTOP & LAPTOP\n");
				test.testCISWPurchaseApprovalRequest();
					
				//Network and Telecom Items	

				output("\nTESTING CATALOG ITEMS FOR NETWORK & TELECOM\n");
				test.testCIDomainNameServerRequest();
				test.testCIStaticIPAddressRequest();
				test.testCIInstallationofWirelessAccessRequest();
				
				//Testing for Email & Communications
				
				output("\nTESTING CATALOG ITEMS FOR EMAIL & COMMUNICATION\n");
				test.testCICreateResourceorRoomMailboxRequest();
				test.testCIDistributionGrporListServRequest();
				
				//Security
			
				output("\nTESTING CATALOG ITEMS FOR SECURITY\n");
				
				test.testCIWhitelistingRequest();
				test.testCINetIDPlusTokenRequest();
				test.testCINetIDPlusTokenAssignment();
				
				//Testing Catalog Items for Teaching and Learning
				output("\nTESTING CATALOG ITEMS FOR TEACHING AND LEARNING\n");
				test.testCInewTrainingorDocumentation();
				
	}
	
	public void runNonWorkflowRequests() {
		output("TESTING CATALOG ITEMS FOR ACCOUNTS & ACCESS");
		
		test.testCIHPCAcctReq();
		test.testCIMatlab();
		test.testCIUrgentRequest();                     
		test.testCIDeptNetworkDriveAccess();
		test.testCISingleSignOnRequest();
		test.testCIImageNowAccessRequest();
		test.testCICMSRequest();
		test.testCIEventWifiAccessRequest();
		test.testDBRequestPwdReset();                
		test.testCITempAccesstoDisabledAcct();
		test.testCIMyMavAccessRequest();
		test.testCIPeopleSoftLDAPAccessRequest();
		test.testCISFTPAccesstoMyMavRequest();
		test.testEDSDataWarehouse();
		test.testUTACloud();
		
		//Testing Catalog Items for Business Affairs
		output("\nTESTING CATALOG ITEMS FOR BUSINESS AFFAIRS\n");
		test.testCIGeneralBusinessRequest();
		test.testCIRequestTouchNetStore();
		
		
		
		//Testing for Desktop & Laptop
		output("\nTESTING CATALOG ITEMS FOR DESKTOP & LAPTOP\n");
		test.testCIGeneralHWSWRequest();
		test.testCIStandardComputerSetup();
		test.testCINWPrinterRequest();
		test.testCIHWSWInstallationRequest();	
		test.testRepurposeReImageRequest();
		test.testCIHWSWRecommendationRequest();
			
		//Network and Telecom Items	

		output("\nTESTING CATALOG ITEMS FOR NETWORK & TELECOM\n");
	
		test.testCITelecomRequest();
		test.testCICablePullRequest();
		
		//Testing for Email & Communications
		
		output("\nTESTING CATALOG ITEMS FOR EMAIL & COMMUNICATION\n");
		test.testCISharedResourceorRoomMailboxRequest();
		test.testCIModifyEmailAliasRequest();
		test.testMicrosoftBookingsRequest();
		test.testCreateRetired();
	
		
		//Security
	
		output("\nTESTING CATALOG ITEMS FOR SECURITY\n");
		test.testCISecureDocUserModificationRequest();
		test.testCIFireWallRequest();
	
		//testing Catalog items for Enterprise Data Services
		output("\nTESTING CATALOG ITEMS FOR ENTERPRISE DATA SERVICES\n");
		test.testCIDCustomReportRequest();
		test.testCIDDataExtractRequest();
		test.testCIDDataInterfaceRequest();
		
		output("\nTESTING CATALOG ITEMS FOR MISCELLANEOUS\n");
		
		test.testCIUTAHelpCenterPage();
		test.testCINewImageNowProcess();
		test.testCIControlMJobScheduling();
	
		//Testing Catalog Items for Teaching and Learning
		output("\nTESTING CATALOG ITEMS FOR TEACHING AND LEARNING\n");
		
		test.testCINewKBArticle();
		test.testCICanvasOrgnandCourses();
		test.testCIOITTrainingRequest(); 
	    
		
}
	
	public void runAllRequests()
	{
		output("TESTING CATALOG ITEMS FOR ACCOUNTS & ACCESS");
		
		test.testCIHPCAcctReq();
		test.testCIMatlab();
		test.testCIUrgentRequest();                     
		test.testCIAffiliateAccountExtensionRequest();   
		test.testCIDeptNetworkDriveAccess();
		test.testCISingleSignOnRequest();
		test.testCIImageNowAccessRequest(); 
		test.testCICMSRequest();
		test.testCIEventWifiAccessRequest();
		test.testDBRequestPwdReset();                
		test.testCITempAccesstoDisabledAcct();
		test.testCIMyMavAccessRequest();
		test.testCIPeopleSoftLDAPAccessRequest();
		//test.testCIMARSRequest();
		test.testCISFTPAccesstoMyMavRequest();
		test.testEDSDataWarehouse();
		test.testUTACloud();
		
		//Testing Catalog Items for Business Affairs
		output("\nTESTING CATALOG ITEMS FOR BUSINESS AFFAIRS\n");
		test.testCIRequestPolicy();
		test.testCIGeneralBusinessRequest();
		test.testCItRequestBTSWebServices();
		test.testCIRequestTouchNetStore();
		
		
		//Testing for Storage and Servers
		System.out.println("\nTESTING CATALOG ITEMS FOR SERVERS & STORAGE\n");
		
		test.testCIBoxStorageRequest();
		test.testCIModifyServerRequest();
		test.testCIProvisionServerRequest();
		
		
			
		//Network and Telecom Items	

		output("\nTESTING CATALOG ITEMS FOR NETWORK & TELECOM\n");
	
		test.testCITelecomRequest();
		test.testCIDomainNameServerRequest();
		test.testCIStaticIPAddressRequest();
		test.testCICablePullRequest();
		test.testCIInstallationofWirelessAccessRequest();
		
		//Testing for Email & Communications
		
		output("\nTESTING CATALOG ITEMS FOR EMAIL & COMMUNICATION\n");
		test.testCISharedResourceorRoomMailboxRequest();
		test.testCICreateResourceorRoomMailboxRequest();
		test.testCIDistributionGrporListServRequest();
		test.testCIModifyEmailAliasRequest();
		
		//Security
	
		output("\nTESTING CATALOG ITEMS FOR SECURITY\n");
		test.testCISecureDocUserModificationRequest();
		test.testCIWhitelistingRequest();
		test.testCIFireWallRequest();
		test.testCINetIDPlusTokenRequest();
		test.testCINetIDPlusTokenAssignment();
		
		//testing Catalog items for Enterprise Data Services
		output("\nTESTING CATALOG ITEMS FOR ENTERPRISE DATA SERVICES\n");
		test.testCIDCustomReportRequest();
		test.testCIDDataExtractRequest();
		test.testCIDDataInterfaceRequest();
		
		output("\nTESTING CATALOG ITEMS FOR MISCELLANEOUS\n");
		
		test.testCIUTAHelpCenterPage();
		test.testCINewImageNowProcess();
		test.testCIControlMJobScheduling();
	
		//Testing Catalog Items for Teaching and Learning
		output("\nTESTING CATALOG ITEMS FOR TEACHING AND LEARNING\n");
		
		test.testCINewKBArticle();
		test.testCInewTrainingorDocumentation();
		test.testCICanvasOrgnandCourses();
		test.testCIOITTrainingRequest(); 
	   
	    //Testing for Desktop & Laptop
		//output("\nTESTING CATALOG ITEMS FOR DESKTOP & LAPTOP\n");
		//test.testCIGeneralHWSWRequest();
		//test.testCIStandardComputerSetup();
		//test.testCISWPurchaseApprovalRequest(); 
		//test.testCINWPrinterRequest();
		//test.testCIHWSWInstallationRequest();	
		//test.testRepurposeReImageRequest();
	//	test.testCIHWSWRecommendationRequest();
	
		//Website support
		output ("Website support");
		//test.testWebTraining();
		//test.testWebNavigationSupport();
		
	}
	public void testGSuite()
	{
		output("Test GSuite");
		d.findElement(By.xpath("//span[contains(text(),'Request Something')]")).click();
		d.findElement(By.xpath("//span[contains(text(),'G Suite Account Request')]")).click();
		d.findElement(By.xpath("//textarea[@id='sp_formfield_purpose_of_g_suite_account']")).sendKeys("for testing purpose");
		d.findElement(By.xpath("//button[@name='submit']")).click();
		String reqno = d.findElement(By.xpath("/html[1]/body[1]/div[1]/section[1]/main[1]/div[2]/div[1]/sp-page-row[1]/div[1]/div[1]/span[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/a[1]/div[1]")).getText();
	
		output("Gsuite Request -: "+reqno+" --Passed");
		output("____________________________________________________________");
	
		//d.manage().timeouts().implicitlyWait(40,TimeUnit.SECONDS);
	
	}
	
	public void testlinkedIn()
	{
		output("Test linkedin");
		
		d.findElement(By.xpath("//span[contains(text(),'Request Something')]")).click();
		d.findElement(By.xpath("//span[contains(text(),'LinkedIn Learning - Admin Access for Instructors')]")).click();
		d.findElement(By.xpath("//textarea[@id='sp_formfield_linkedin_instructor_of_record']")).sendKeys("for Business Management");
		d.findElement(By.xpath("//button[@name='submit']")).click();
		String reqno = d.findElement(By.xpath("/html[1]/body[1]/div[1]/section[1]/main[1]/div[2]/div[1]/sp-page-row[1]/div[1]/div[1]/span[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/a[1]/div[1]")).getText();
	
		output("LinkedIn Training Request -: "+reqno+" --Passed");
		output("____________________________________________________________");
	
	
		
	}
	
	public void testWebTraining()
	{
		output("Test Web Training");
		d.findElement(By.xpath("//span[contains(text(),'Request Something')]")).click();
		d.findElement(By.xpath("//span[contains(text(),'Web Training')]")).click();
		d.findElement(By.xpath("//textarea[@id='sp_formfield_web_training_description']")).sendKeys("Cascade");
		d.findElement(By.xpath("//span[@id='select2-chosen-1']")).click();
		d.findElement(By.xpath("//input[@id='s2id_autogen1_search']")).sendKeys("Cascade");
		d.findElement(By.xpath("//button[@name='submit']")).click();
		String reqno = d.findElement(By.xpath("/html[1]/body[1]/div[1]/section[1]/main[1]/div[2]/div[1]/sp-page-row[1]/div[1]/div[1]/span[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/a[1]/div[1]")).getText();
	
		output("Web Training Request -: "+reqno+" --Passed");
		output("____________________________________________________________");
	
		}
	
	public void testWebNavigationSupport()
	{
		output("Test Navigation Support");
		d.findElement(By.xpath("//span[contains(text(),'Request Something')]")).click();
		d.findElement(By.xpath("//span[contains(text(),'Web Navigation Support')]")).click();
		d.findElement(By.xpath("//input[@id='sp_formfield_web_nav_short_description']")).sendKeys("testing");
		d.findElement(By.xpath("//textarea[@id='sp_formfield_web_nav_request_details']")).sendKeys("testing");
		d.findElement(By.xpath("//button[@name='submit']")).click();
		String reqno = d.findElement(By.xpath("/html[1]/body[1]/div[1]/section[1]/main[1]/div[2]/div[1]/sp-page-row[1]/div[1]/div[1]/span[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/a[1]/div[1]")).getText();
	
		output("Web Navigation Request -: "+reqno+" --Passed");
		output("____________________________________________________________");
	
		
	}
	
	public void testMicrosoftBookingsRequest()
	{
		output("Test Microsoft Bookings Request");
		d.findElement(By.xpath("//span[contains(text(),'Request Something')]")).click();
		d.findElement(By.xpath("//span[contains(text(),'Microsoft Bookings Request')]")).click();
		d.findElement(By.xpath("//span[@id='select2-chosen-1']")).click();
		d.findElement(By.xpath("//input[@id='s2id_autogen1_search']")).sendKeys("Department Site"+Keys.ENTER);
		d.findElement(By.xpath("//input[@id='sp_formfield_ms_booking_email_responses']")).sendKeys("radhap@uta.edu");
		d.findElement(By.xpath("//input[@id='sp_formfield_ms_booking_site_admin']")).sendKeys("Radha");
		d.findElement(By.xpath("//span[@id='select2-chosen-2']")).click();
		d.findElement(By.xpath("//input[@id='s2id_autogen2_search']")).sendKeys("Yes"+Keys.ENTER);
		d.findElement(By.xpath("//span[@id='select2-chosen-3']")).click();
		d.findElement(By.xpath("//input[@id='s2id_autogen3_search']")).sendKeys("Yes"+Keys.ENTER);
		d.findElement(By.xpath("//textarea[@id='sp_formfield_ms_booking_faculty_staff_names']")).sendKeys("Radha");
		
		d.findElement(By.xpath("//button[@name='submit']")).click();
		String reqno = d.findElement(By.xpath("/html[1]/body[1]/div[1]/section[1]/main[1]/div[2]/div[1]/sp-page-row[1]/div[1]/div[1]/span[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/a[1]/div[1]")).getText();
	
		output("Microsoft Bookings Request -: "+reqno+" --Passed");
		output("____________________________________________________________");
	
		
	}
	
	public void testCreateRetired()
	{
		output("Test Microsoft Bookings Request");
		d.findElement(By.xpath("//span[contains(text(),'Request Something')]")).click();
		d.findElement(By.xpath("//span[contains(text(),'Create @retired.uta.edu Email Account')]")).click();
		d.findElement(By.xpath("//input[@id='sp_formfield_retired_email_retirement_date']")).sendKeys("09-15-2019");
		d.findElement(By.xpath("//input[@id='sp_formfield_retired_email_requested_email_address']")).sendKeys("radhap@uta.edu");
		d.findElement(By.xpath("//button[@name='submit']")).click();
		String reqno = d.findElement(By.xpath("/html[1]/body[1]/div[1]/section[1]/main[1]/div[2]/div[1]/sp-page-row[1]/div[1]/div[1]/span[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/a[1]/div[1]")).getText();
	
		output("Retired Email Account Request -: "+reqno+" --Passed");
		output("____________________________________________________________");
	
		
	}
	
	public void testWebSiteorLab()
	{
		output("Test WebSite or Lab center Request");
		d.findElement(By.xpath("//span[contains(text(),'Request Something')]")).click();
		d.findElement(By.xpath("//span[contains(text(),'Web Site for Lab or Center')]")).click();
		d.findElement(By.xpath("//span[@id='select2-chosen-1']")).click();
		d.findElement(By.xpath("//input[@id='s2id_autogen1_search']")).sendKeys("Yes"+Keys.ENTER);
		d.findElement(By.xpath("//input[@id='sp_formfield_web_site_lab_or_center']")).sendKeys("Krishna");
		d.findElement(By.xpath("//input[@id='sp_formfield_web_site_proposed_short_name']")).sendKeys("krishna.org");
		d.findElement(By.xpath("//textarea[@id='sp_formfield_web_site_purpose']")).sendKeys("student group to learn meditation");
		d.findElement(By.xpath("//textarea[@id='sp_formfield_web_site_admins']")).sendKeys("testing");
		d.findElement(By.xpath("//input[@id='sp_formfield_web_site_special_tech']")).sendKeys("Python and PHP");
		d.findElement(By.xpath("//input[@id='sp_formfield_web_site_url']")).sendKeys("http://www.krishna.org");
		
		
		
		d.findElement(By.xpath("//button[@name='submit']")).click();
		String reqno = d.findElement(By.xpath("/html[1]/body[1]/div[1]/section[1]/main[1]/div[2]/div[1]/sp-page-row[1]/div[1]/div[1]/span[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/a[1]/div[1]")).getText();
	
		output("Web Site or Lab Center Request -: "+reqno+" --Passed");
		output("____________________________________________________________");
	
		
	}

}


