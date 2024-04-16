package Test;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;


// ITestListener: interface which implements testng listeners
public class Listeners implements ITestListener{

	@Override
	public void onTestSuccess(ITestResult result) 
	{
		System.out.println("Listener says success!!");
	}
	
	@Override
	public void onTestFailure(ITestResult result) 
	{
		System.out.println("Listener says '" + result.getName() + "' failed!");
	}
	
	@Override
	public void onTestSkipped(ITestResult result) 
	{
		
	}
	
	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) 
	{
		
	}
	
	@Override
	public void onStart(ITestContext context) 
	{
		
	}
}
