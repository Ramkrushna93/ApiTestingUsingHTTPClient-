package com.qa.Resttestcases;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.Base.TestBaseClass;
import com.qa.RestClient.RestClient;

public class DeleteAPITesting extends TestBaseClass {

	RestClient restclient;
	TestBaseClass testbase;
	CloseableHttpResponse closeablehttpresponse;

	String url = "http://dummy.restapiexample.com/api/v1/delete/2";

	@BeforeMethod
	public void SetUp() {
		testbase = new TestBaseClass();

	}

	@Test
	public void deleteApiTest() throws ClientProtocolException, IOException {
		restclient = new RestClient();
		
		HashMap<String, String> headermap=new HashMap<String, String>();
		headermap.put("Content-Type","application/json");
		
		closeablehttpresponse = restclient.Delete(url, headermap); // For Hit the Delete Request

		// for get total response
		String responsestring = EntityUtils.toString(closeablehttpresponse.getEntity(), "UTF-8");
		System.out.println(responsestring);

		// For validating Status code
		int statuscode = closeablehttpresponse.getStatusLine().getStatusCode();
		System.out.println(statuscode);
		Assert.assertEquals(statuscode, testbase.RESPONSE_STATUS_CODE_200);

	}

}
