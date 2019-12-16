package com.qa.Resttestcases;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.Base.TestBaseClass;
import com.qa.RestClient.RestClient;
import com.qa.data.Users;

public class PostAPITest extends TestBaseClass {

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
	public void postApiTest() throws JsonGenerationException, JsonMappingException, IOException {
		restclient = new RestClient();
		

		HashMap<String, String> headermap = new HashMap<String, String>();
		headermap.put("Content-Type", "application/json");

		// Now convert string object to json so We are Using JacksonApi on ObjectMapper
		ObjectMapper mapper = new ObjectMapper();
		// Create a object of Users class
		Users user = new Users("morpheus", "leader");

		// we store Object data in json format on json file
		mapper.writeValue(new File(
				"C:\\workspace2\\RestApiTestingThroughHTTPClient\\src\\main\\java\\com\\qa\\configuration\\users.json"),
				user);

		// Print json data in Consule in form String

		String userjsonstring = mapper.writeValueAsString(user);
		System.out.println(userjsonstring);

		closeablehttpResponse=restclient.post(url, userjsonstring, headermap);  // Call The Api

		//  validate Response the Api
     	// For status code
		int statuscode = closeablehttpResponse.getStatusLine().getStatusCode();
		System.out.println(statuscode);
		Assert.assertEquals(statuscode,testbase.RESPONSE_STATUS_CODE_201);

		// JsonString
		String responsestring = EntityUtils.toString(closeablehttpResponse .getEntity(), "UTF-8");

		JSONObject responsejson = new JSONObject(responsestring);
		System.out.println("The response of the API is" + responsejson);

		// Json To Java Object
		Users userResobj = mapper.readValue(responsestring, Users.class);
		System.out.println(userResobj);
	
		// We check User Class Object 'morpheus' and 'leader' is eual to userResobj or not then our testcase is pass
		Assert.assertTrue(user.getName().equals(userResobj.getName()));
		
		Assert.assertTrue(user.getJob().equals(userResobj.getJob()));
		
		System.out.println(userResobj.getId());
		System.out.println(userResobj.getCreatedAt());

	}

}
