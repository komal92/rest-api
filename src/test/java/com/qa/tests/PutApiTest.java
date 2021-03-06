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

public class PutApiTest extends TestBase {
	TestBase testBase;
	String url;
	String apiURL;
	String actualURL;
	RestClient restClient;
	CloseableHttpResponse putResponse;

	@BeforeMethod
	public void setUp() throws ClientProtocolException, IOException {
		testBase = new TestBase(); // propeeties will be read
		url = prop.getProperty("URL");
		apiURL = prop.getProperty("serviceURLPut");
		actualURL = url + apiURL;
	}

	@Test
	public void putApiTest() throws JsonGenerationException, JsonMappingException, IOException {
		restClient = new RestClient();
		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");

		// Steps to generate the JSON

		// Jackson Api
		ObjectMapper mapper = new ObjectMapper();
		Users user = new Users("morpheus", "New Leader");

		// object-> JSON

		mapper.writeValue(
				new File("C:\\Users\\komalh\\eclipse-workspace\\restapi\\src\\main\\java\\com\\qa\\data\\User.json"),
				user);

		// object to Json to String

		String userJsonString = mapper.writeValueAsString(user);
		System.out.println(userJsonString);

		putResponse = restClient.put(actualURL, userJsonString, headerMap);

		// 1. Status Code

		int statusCode = putResponse.getStatusLine().getStatusCode();
		System.out.println(statusCode);
		Assert.assertEquals(statusCode, 200);

		// 2. Json String

		String responseString = EntityUtils.toString(putResponse.getEntity(), "UTF-8");// Convert into String

		JSONObject responseJson = new JSONObject(responseString);

		System.out.println("Response String:" + responseJson);

		// JSON to object---> Unmarshalling

		Users obj = mapper.readValue(responseString, Users.class);

		System.out.println(obj);

		System.out.println(obj.getJob().equals(user.getJob()));

		System.out.println(obj.getName().equals(user.getName()));
		
		System.out.println("This is the end of put api");
	}

}
