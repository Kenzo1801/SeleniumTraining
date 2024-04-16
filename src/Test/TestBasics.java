package Test;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestBasics {

	@BeforeClass
	public void BeforeClassDemo() 
	{
		System.out.println("Before executing all methods in class TestBasics");
	}
	@AfterTest
	public void AfterTestDemo() 
	{
		System.out.println("Performed after tests");
	}
	
	@Test
	public void Demo() 
	{
		System.out.println("First Testng test");
	}
	
	@Test(groups={"smoke"})
	public void SecondDemo() 
	{
		System.out.println("2nd Testng test");
	}
}
