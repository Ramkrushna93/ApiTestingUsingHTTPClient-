package com.qa.RestClient;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class RestClient {
	
	// 1.GET Method without Header
	public CloseableHttpResponse get(String url) throws ClientProtocolException, IOException {
		
		// call HttpClient class and for connection for createDefault method
		CloseableHttpClient httpclient=HttpClients.createDefault();
		
		// for get call
		HttpGet httpget=new HttpGet(url);
		CloseableHttpResponse closeablehttpResponse=httpclient.execute(httpget);
		
		return closeablehttpResponse;
		}
	
	// 2.GET Method With Header
	public CloseableHttpResponse get(String url, HashMap<String,String>headerMap) throws ClientProtocolException, IOException {
		
		// call HttpClient class and for connection for createDefault method
		CloseableHttpClient httpclient=HttpClients.createDefault();
		

		// for get call
		HttpGet httpget=new HttpGet(url);
		// for header
		for(Map.Entry<String,String> entry : headerMap.entrySet()) {
			httpget.addHeader(entry.getKey(),entry.getValue());
		}
		CloseableHttpResponse closeablehttpResponse=httpclient.execute(httpget);
		
		return closeablehttpResponse;
	}
	
	// For Post Call
	public CloseableHttpResponse post(String url, String entitystring, HashMap<String, String>headermap) throws ClientProtocolException, IOException {
		
		// call HttpClient class and for connection for createDefault method
		CloseableHttpClient httpclient=HttpClients.createDefault();
		// for post call
		HttpPost httppost=new HttpPost(url);
		// For define Payload
		httppost.setEntity(new StringEntity(entitystring));
		
		// for header
		for(Map.Entry<String, String> entry : headermap.entrySet()) {
			httppost.addHeader(entry.getKey(), entry.getValue());
		}
			// for hit post call or execute the post call
			CloseableHttpResponse closeablehttpresponse=httpclient.execute(httppost); 
			 return closeablehttpresponse;
		}
	
	public CloseableHttpResponse PUT(String url,String entitystring,HashMap<String,String>headermap) throws ClientProtocolException, IOException {
		
		// call HttpClient class and for connection for createDefault method
		CloseableHttpClient httpclient=HttpClients.createDefault();
		
		// For Put call
		HttpPut httpput=new HttpPut(url);
		System.out.println(httpput.getRequestLine());
		
		//For define Payload
		httpput.setEntity(new StringEntity(entitystring));
		
		// For header
		for(Map.Entry<String, String> entry : headermap.entrySet()) {
			httpput.addHeader(entry.getKey(), entry.getValue());
		}
		// For hit post call or execute the put call
		CloseableHttpResponse closeablehttpResponse=httpclient.execute(httpput);
		return closeablehttpResponse;
		
	}
	
	public CloseableHttpResponse Delete(String url, HashMap<String,String>headermap) throws ClientProtocolException, IOException {
		
		// call HttpClient class and for connection for createDefault method
		CloseableHttpClient httpclient=HttpClients.createDefault();
		
		// For Delete call
		HttpDelete httpdelete=new HttpDelete();
		System.out.println(httpdelete.getRequestLine());
		
		// For header
		for(Map.Entry<String, String> entry : headermap.entrySet()) {
			httpdelete.addHeader(entry.getKey(), entry.getValue());
		}
		// For hit post call or execute the put call
		CloseableHttpResponse closeablehttpresponse=httpclient.execute(httpdelete);
		
		return closeablehttpresponse;
		
	}
				
		
		
		
		
		
		
		
	}

