package Test;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;

public class TestBasics2 {
	@Test
	public void ThirdDemo() 
	{
		System.out.println("Third Testng test");
	}
	
	@Test(groups={"smoke"})
	public void FourthDemo() 
	{
		System.out.println("Fourth Testng test");
	}
	
	@AfterSuite
	public void AfterSuiteDemo() 
	{
		System.out.println("After suite test");
	}
	
	@AfterClass
	public void AfterClassDemo() 
	{
		System.out.println("Execute method after all methods in the class TestBasics2");
	}
}
