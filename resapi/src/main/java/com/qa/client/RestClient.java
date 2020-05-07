package com.qa.client;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class RestClient {

	// 1. GET Method without headers
	public CloseableHttpResponse get (String url) throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		// http get request
		HttpGet httpget =new HttpGet(url); 
		// hit the GET url
		CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpget); 
		
		return closeableHttpResponse;
	}
	
	// 2. GET Method with headers
	public CloseableHttpResponse get (String url, HashMap<String, String> headerMap) throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		// http get request
		HttpGet httpget =new HttpGet(url); 
		
		for (Entry<String, String> entry : headerMap.entrySet()) {
			httpget.addHeader(entry.getKey() , entry.getValue());
		}
		
		// hit the GET url
		CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpget); 
		
		return closeableHttpResponse;
	}
	
}
