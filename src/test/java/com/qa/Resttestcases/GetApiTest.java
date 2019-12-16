package com.qa.Resttestcases;

import java.io.IOException;
import java.util.HashMap;

import javax.swing.plaf.synth.SynthScrollBarUI;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.Base.TestBaseClass;
import com.qa.RestClient.RestClient;
import com.qa.Utils.TestUtils;

public class GetApiTest extends TestBaseClass {

	TestBaseClass testbase;
	String url;
	RestClient restclient;
	CloseableHttpResponse closeablehttpResponse;

	@BeforeMethod
	public void SetUp() {

		testbase = new TestBaseClass();
		String serviceurl = prop.getProperty("URL");
		String apiurl = prop.getProperty("URI");

		// Append two url
		url = serviceurl + apiurl;
	}

	@Test(priority=1)
	public void getApiTestWithOutHeaders() throws ClientProtocolException, IOException {
		restclient=new RestClient();
		closeablehttpResponse=restclient.get(url);
		
		// for get status code
				int statuscode=closeablehttpResponse.getStatusLine().getStatusCode();
				System.out.println("Status code is ------>"+statuscode);
				
				// for Validating status code
				Assert.assertEquals(statuscode, RESPONSE_STATUS_CODE_200,"StatusCode is not 200");
				
				// for get total response
				String responsestring=EntityUtils.toString(closeablehttpResponse.getEntity(),"UTF-8");
				
				// Then convert response string to Json
				JSONObject responsejson=new JSONObject(responsestring);
				System.out.println("Response json from Api ----->"+responsejson);
				
				
				// SINGLE VALUE ASSERATIONS
				//Converting Json response to String Response
				String perpagevalue=TestUtils.getvalueJsonpath(responsejson, "/per_page");
				System.out.println("value of per page------>"+perpagevalue);
				// Validate perpagevalue on Json and convert String to int value
				Assert.assertEquals(Integer.parseInt(perpagevalue),6);
				
				//Converting Json response to String Response
				String totalvalue=TestUtils.getvalueJsonpath(responsejson, "/total");
				System.out.println("value of total is------>"+totalvalue);
				// Validate perpagevalue on Json
				Assert.assertEquals(Integer.parseInt(totalvalue),12);
				
				//How to know Json Array ----> If in response data or anything stating with "[" and
				 //                 ending with "]" then it called Json Array
				//NOW GET THE VALUE FROM JSON ARRAY
				String lastname=TestUtils.getvalueJsonpath(responsejson,"/data[0]/last_name");
				String id=TestUtils.getvalueJsonpath(responsejson,"/data[0]/id");
				String avatar=TestUtils.getvalueJsonpath(responsejson,"/data[0]/avatar");
				String firstname=TestUtils.getvalueJsonpath(responsejson, "/data[0]/first_name");
				
				System.out.println("Json response of lastname-----> "+lastname);
				System.out.println("Json response of id------> "+id);
				System.out.println("Json response of avater-----> "+avatar);
				System.out.println("Json response of firstname-----> "+firstname);
				
				
				// How to get the Response Header
				
				Header[] headerarray=closeablehttpResponse.getAllHeaders();
				
				// Now Store Header Request in HashMap
				HashMap<String , String> allheaders=new HashMap<String,String>();
				for(Header header : headerarray) {
					
					allheaders.put(header.getName(),header.getValue());
					System.out.println("Header Array ---->"+allheaders);
		
	}
}
	
	@Test(priority=2)
	public void getApiTestWithHeaders() throws ClientProtocolException, IOException {
		restclient=new RestClient();
		
		HashMap<String,String>headerMap=new HashMap<String,String>();
		headerMap.put("Content-Type","application/json");
	//	headerMap.put("password","test@12345");
	//	headerMap.put("username","test@tcs.com");
	//	headerMap.put("Auth_Token","12345");
		closeablehttpResponse=restclient.get(url);
		
		// for get status code
				int statuscode=closeablehttpResponse.getStatusLine().getStatusCode();
				System.out.println("Status code is ------>"+statuscode);
				
				// for Validating status code
				Assert.assertEquals(statuscode, RESPONSE_STATUS_CODE_200,"StatusCode is not 200");
				
				// for get total response
				String responsestring=EntityUtils.toString(closeablehttpResponse.getEntity(),"UTF-8");
				
				// Then convert response string to Json
				JSONObject responsejson=new JSONObject(responsestring);
				System.out.println("Response json from Api ----->"+responsejson);
				
				
				// SINGLE VALUE ASSERATIONS
				//Converting Json response to String Response
				String perpagevalue=TestUtils.getvalueJsonpath(responsejson, "/per_page");
				System.out.println("value of per page------>"+perpagevalue);
				// Validate perpagevalue on Json and convert String to int value
				Assert.assertEquals(Integer.parseInt(perpagevalue),6);
				
				//Converting Json response to String Response
				String totalvalue=TestUtils.getvalueJsonpath(responsejson, "/total");
				System.out.println("value of total is------>"+totalvalue);
				// Validate perpagevalue on Json
				Assert.assertEquals(Integer.parseInt(totalvalue),12);
				
				//How to know Json Array ----> If in response data or anything stating with "[" and
				 //                 ending with "]" then it called Json Array
				//NOW GET THE VALUE FROM JSON ARRAY
				String lastname=TestUtils.getvalueJsonpath(responsejson,"/data[0]/last_name");
				String id=TestUtils.getvalueJsonpath(responsejson,"/data[0]/id");
				String avatar=TestUtils.getvalueJsonpath(responsejson,"/data[0]/avatar");
				String firstname=TestUtils.getvalueJsonpath(responsejson, "/data[0]/first_name");
				
				System.out.println("Json response of lastname-----> "+lastname);
				System.out.println("Json response of id------> "+id);
				System.out.println("Json response of avater-----> "+avatar);
				System.out.println("Json response of firstname-----> "+firstname);
				
				
				// How to get the Response Header
				
				Header[] headerarray=closeablehttpResponse.getAllHeaders();
				
				// Now Store Header Request in HashMap
				HashMap<String , String> allheaders=new HashMap<String,String>();
				for(Header header : headerarray) {
					
					allheaders.put(header.getName(),header.getValue());
					System.out.println("Header Array ---->"+allheaders);
		
	}
	
	}
}