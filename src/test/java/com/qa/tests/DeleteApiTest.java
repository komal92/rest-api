package com.qa.tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.data.Users;

public class DeleteApiTest extends TestBase {
	TestBase testBase;
	String url;
	String apiURL;
	String actualURL;
	RestClient restClient;
	CloseableHttpResponse postResponse;

	@BeforeMethod
	public void setUp() throws ClientProtocolException, IOException {
		testBase = new TestBase(); // propeeties will be read
		url = prop.getProperty("URLDelete");
		apiURL = prop.getProperty("serviceURLDelete");
		actualURL = url + apiURL;
	}

	@Test
	public void deleteApiTest() throws JsonGenerationException, JsonMappingException, IOException {
		restClient = new RestClient();
		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");

		// Steps to generate the JSON

		postResponse = restClient.delete(actualURL, headerMap);

		// 1. Status Code

		int statusCode = postResponse.getStatusLine().getStatusCode();
		System.out.println(statusCode);
		Assert.assertEquals(statusCode, 204);

	}

}
