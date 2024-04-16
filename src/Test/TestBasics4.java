package Test;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestBasics4 {
	@Parameters({"URL"})
	@Test
	public void SeventhDemo(String urlName) 
	{
		System.out.println("Seventh Testng test");
		System.out.println(urlName);
	}
	
	@Test(groups={"smoke"})
	public void EigthDemo() 
	{
		System.out.println("Heigth Testng test");
	}
	
	
	@BeforeSuite
	public void BeforeSuiteDemo() 
	{
		System.out.println("Before suite test");
	}
}
