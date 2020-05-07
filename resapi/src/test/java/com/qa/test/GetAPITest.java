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
	
	
	
	@Test (priority=1)
	public void getAPITestWithoutHeaders() throws ClientProtocolException, IOException {
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
			    
			    // single value assertion
			    //per page
			    String perPageValue = Testutil.getValueByJPath(responseJSON , "/per_page");
			    System.out.println("Value of per page is ---->" +perPageValue);
			    
			    //Assert.assertEquals(perPageValue, "6");
			    Assert.assertEquals(Integer.parseInt(perPageValue),6 );
			    
			    // total value 
			    String totalValue = Testutil.getValueByJPath(responseJSON , "/total");
			    System.out.println("Value of total  is ---->" +totalValue);
			    
			    Assert.assertEquals(Integer.parseInt(totalValue),12);
			    
			    
			    // get the value from JSON Array
			    String lastname = Testutil.getValueByJPath(responseJSON, "/data[0]/last_name");
			    String id = Testutil.getValueByJPath(responseJSON, "/data[0]/id");
			    String avatar = Testutil.getValueByJPath(responseJSON, "/data[0]/avatar");
			    String firstname = Testutil.getValueByJPath(responseJSON, "/data[0]/first_name");
			    String email = Testutil.getValueByJPath(responseJSON, "/data[0]/email");
			    
			    System.out.println(lastname);
			    System.out.println(id);
			    System.out.println(avatar);
			    System.out.println(firstname);
			    System.out.println(email);
			    
			    //c. All Headers
			     Header[] headersArray = closeableHttpResponse.getAllHeaders();
			     HashMap<String , String> allHeaders = new HashMap<String , String>();
			     
			     for (Header header : headersArray) {
			    	 allHeaders.put(header.getName(), header.getValue());
			     }
			     
			     System.out.println("HeadesrArray ------>" +allHeaders);
			
			}
		
	@Test (priority=2)
	public void getAPITestWithHeaders() throws ClientProtocolException, IOException {
		restclient = new RestClient();
		
		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "applicationjson");
		
		closeableHttpResponse = restclient.get(url, headerMap);
		
		// a. StatusCode
				int statuscode = closeableHttpResponse.getStatusLine().getStatusCode();
			    System.out.println("Status code is ------>"  +statuscode);
			 
			    Assert.assertEquals(statuscode , RESPONSE_STATUS_CODE_200, "Status code is not  200");
			    
			    
			    //b. JsonString
			    String responseString= EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
			       
			    JSONObject responseJSON = new JSONObject(responseString);
			    System.out.println("Response JSON from API --->" +responseJSON);
			    
			    // single value assertion
			    //per page
			    String perPageValue = Testutil.getValueByJPath(responseJSON , "/per_page");
			    System.out.println("Value of per page is ---->" +perPageValue);
			    
			    //Assert.assertEquals(perPageValue, "6");
			    Assert.assertEquals(Integer.parseInt(perPageValue),6 );
			    
			    // total value 
			    String totalValue = Testutil.getValueByJPath(responseJSON , "/total");
			    System.out.println("Value of total  is ---->" +totalValue);
			    
			    Assert.assertEquals(Integer.parseInt(totalValue),12);
			    
			    
			    // get the value from JSON Array
			    String lastname = Testutil.getValueByJPath(responseJSON, "/data[0]/last_name");
			    String id = Testutil.getValueByJPath(responseJSON, "/data[0]/id");
			    String avatar = Testutil.getValueByJPath(responseJSON, "/data[0]/avatar");
			    String firstname = Testutil.getValueByJPath(responseJSON, "/data[0]/first_name");
			    String email = Testutil.getValueByJPath(responseJSON, "/data[0]/email");
			    
			    System.out.println(lastname);
			    System.out.println(id);
			    System.out.println(avatar);
			    System.out.println(firstname);
			    System.out.println(email);
			    
			    //c. All Headers
			     Header[] headersArray = closeableHttpResponse.getAllHeaders();
			     HashMap<String , String> allHeaders = new HashMap<String , String>();
			     
			     for (Header header : headersArray) {
			    	 allHeaders.put(header.getName(), header.getValue());
			     }
			     
			     System.out.println("HeadesrArray ------>" +allHeaders);
			
			}
			
	
}


