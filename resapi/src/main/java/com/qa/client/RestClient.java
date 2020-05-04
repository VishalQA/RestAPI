package com.qa.client;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class RestClient {

	// 1. GET Method
	public void get (String url) throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		// http get request
		HttpGet httpget =new HttpGet(url); 
		// hit the GET url
		CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpget); 
		
		// a. StatusCode
		int statuscode = closeableHttpResponse.getStatusLine().getStatusCode();
	    System.out.println("Status code is ------>"  +statuscode);
	
	    //b. JsonString
	    String responseString= EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
	       
	    JSONObject responseJSON = new JSONObject(responseString);
	    System.out.println("Response JSON from API --->" +responseJSON);
	    
	    //c. All Headers
	     Header[] headersArray = closeableHttpResponse.getAllHeaders();
	     HashMap<String , String> allHeaders = new HashMap<String , String>();
	     
	     for (Header header : headersArray) {
	    	 allHeaders.put(header.getName(), header.getValue());
	     }
	     
	     System.out.println("HeadesrArray ------>" +allHeaders);
	
	}
	
	
}
