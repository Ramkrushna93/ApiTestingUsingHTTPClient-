package com.qa.Resttestcases;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.Base.TestBaseClass;
import com.qa.RestClient.RestClient;

public class PutAPITest extends TestBaseClass{
	
	TestBaseClass testbase;
	String serviceUrl;
	String apiurl;
	String url;
	RestClient restclient;
	CloseableHttpResponse closeablehttpResponse;

	@BeforeMethod
	public void setUp() {
		testbase = new TestBaseClass();
		serviceUrl = prop.getProperty("URL");
		apiurl = prop.getProperty("URI");

		url = serviceUrl + apiurl;
	}
	
	@Test
	public void putApiTest() throws ClientProtocolException, IOException {
		
		restclient=new RestClient();
		
		HashMap<String, String> headermap=new HashMap<String, String>();
		headermap.put("Content-Type", "application/json");
		
		String inputjson="{\r\n" + 
				"    \"name\": \"morpheus\",\r\n" + 
				"    \"job\": \"zion resident\"\r\n" + 
				"}";
		
		closeablehttpResponse=restclient.PUT(url, inputjson, headermap);
		
		BufferedReader br=new BufferedReader(new InputStreamReader(closeablehttpResponse.getEntity().getContent()));
		
		// For validating status code
		int statuscode=closeablehttpResponse.getStatusLine().getStatusCode();
		System.out.println(statuscode);
		Assert.assertEquals(statuscode,testbase.RESPONSE_STATUS_CODE_200); 
		
		// Creating String Buffer Object and store response into it
		
		StringBuffer result=new StringBuffer();
		String line="";
		while((line = br.readLine()) !=null){
			System.out.println("Response : \n" + result.append(line));
			
			// Lets validate if a text 'Empolyee_job' is present in the response
			System.out.println("Does response contains 'job'? :"+result.toString().contains("job"));
			
		}
		
		
		
	}


}
