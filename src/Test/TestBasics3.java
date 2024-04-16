package Test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestBasics3 {
	
	@BeforeMethod
	@Test(enabled=false) // testng skips this method during execution
	public void BeforeMethodDemo() 
	{
		System.out.println("Before method: will be executed before every test method in this class");
	}
	
	@Parameters({"URL","APIKey/usrname"})
	@Test
	public void FifthDemo(String urlName, String key) 
	{
		System.out.println("Fifth Testng test");
		System.out.println(urlName + " --- " + key);
	}
	
	@Test(enabled=true) // testng skips this method during execution or not
	public void SixthDemo() 
	{
		System.out.println("Sixth Testng test");
	}
	
	@Test(dependsOnMethods = {"WebLoginCarLoan", "SixthDemo"})
	public void MobileLoginCarLoan() 
	{
		System.out.println("MobileLoginCarLoan");
	}
	
	//@Test(timeOut=4000) // Wait 4 second before throwing any exception
	@Test(dataProvider="getData")
	public void WebLoginCarLoan(String username, String password) 
	{
		System.out.println("WebLoginCarLoan");
		System.out.println(username + " --- " + password);
		Assert.assertTrue(false);
	}
	
	@BeforeTest
	public void BeforeTestDemo() 
	{
		System.out.println("Performed first");
	}
	
	@DataProvider
	public Object[][] getData()
	{
		// 1st combination - username/password - good credit history
		// 2nd - username/password - no credit history
		// 3rd - fraudelement credit history
		Object[][] data = new Object[3][2]; // 3 combinations with 2 values
		// 1st set
		data[0][0] = "1stUsername";
		data[0][1] = "1stPassword";
		
		// 2nd set
		data[1][0] = "2ndUsername";
		data[1][1] = "2ndPassword";
		
		// 3rd set
		data[2][0] = "3rdUsername";
		data[2][1] = "3rdPassword";
		return data;
	}
}
