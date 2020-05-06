package com.qa.test;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.util.Testutil;

public class GetAPITest extends TestBase {
	TestBase testbase;
	String serviceurl;
	String apiurl;
	String url ;
	RestClient restclient ;
	CloseableHttpResponse closeableHttpResponse;
	
//	public static void main(String[] args) {
//	}

	@BeforeMethod
	public void setUp() throws ClientProtocolException, IOException {
		testbase = new TestBase();
		 serviceurl = prop.getProperty("URL");
		 apiurl = prop.getProperty("serviceURL");
	     url = serviceurl + apiurl;
		
	
					
	}
	
	@Test
	public void getAPITest() throws ClientProtocolException, IOException {
		restclient = new RestClient();
		closeableHttpResponse = restclient.get(url);
		
		// a. StatusCode
				int statuscode = closeableHttpResponse.getStatusLine().getStatusCode();
			    System.out.println("Status code is ------>"  +statuscode);
			 
			    Assert.assertEquals(statuscode , RESPONSE_STATUS_CODE_200, "Status code is not  200");
			    
			    
			    //b. JsonString
			    String responseString= EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
			       
			    JSONObject responseJSON = new JSONObject(responseString);
			    System.out.println("Response JSON from API --->" +responseJSON);
			    
			    //total page
			    String perPageValue = Testutil.getValueByJPath(responseJSON , "/per_page");
			    System.out.println("Value of per page is ---->" +perPageValue);
			    
			    Assert.assertEquals(perPageValue, "6");
//			    Assert.assertEquals(perPageValue,Integer.valueOf("6"));
			    
			
			    
			    //c. All Headers
			     Header[] headersArray = closeableHttpResponse.getAllHeaders();
			     HashMap<String , String> allHeaders = new HashMap<String , String>();
			     
			     for (Header header : headersArray) {
			    	 allHeaders.put(header.getName(), header.getValue());
			     }
			     
			     System.out.println("HeadesrArray ------>" +allHeaders);
			
			}
			
		
}


