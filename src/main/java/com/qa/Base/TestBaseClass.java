package com.qa.Base;

import java.io.FileInputStream;
import java.util.Properties;

public class TestBaseClass {
	
	public int RESPONSE_STATUS_CODE_200 = 200;
	public int RESPONSE_STATUS_CODE_400 = 400;
	public int RESPONSE_STATUS_CODE_401 = 401;
	public int RESPONSE_STATUS_CODE_201 = 201;
	public int RESPONSE_STATUS_CODE_500 = 500;
	
	
	
	public Properties prop;
	public TestBaseClass() {
		
		try
		{
	    prop=new Properties();
		FileInputStream fs=new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\com\\qa\\configuration\\Config.properties");
		prop.load(fs);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
