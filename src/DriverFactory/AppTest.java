package DriverFactory;

import org.testng.annotations.Test;

public class AppTest {
@Test(enabled=false)
public void kickstart()
{
	try{
	DriverScript ds=new DriverScript();
	ds.statrtTest();
	}catch(Throwable t)
	{
		System.out.println(t.getMessage());
	}
	
}
}
