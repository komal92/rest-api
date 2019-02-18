package com.qa.tests;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.client.RestClient;

public class GetApiTest extends TestBase {
	TestBase testBase;
	String url;
	String apiURL;
	String actualURL;

	@BeforeMethod
	public void setUp() throws ClientProtocolException, IOException {
		testBase = new TestBase(); // propeeties will be read
		url = prop.getProperty("URL");
		apiURL = prop.getProperty("serviceURL");
		actualURL = url + apiURL;
	}

	@Test
	public void getApiTest() throws ClientProtocolException, IOException {
		RestClient restClient = new RestClient();
		restClient.get(actualURL);
		System.out.println("This is the end of get api");
	}
	
	
}
