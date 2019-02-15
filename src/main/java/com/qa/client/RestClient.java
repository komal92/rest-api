package com.qa.client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class RestClient {

	// GET Method
	public void get(String url) throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault(); // This will create client connection
		HttpGet httpGet = new HttpGet(url);// It will create get connaction with the url. although we are not sending
											// anything as yet
		CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpGet); // Execute will help us execute http
																					// requests. it is equal to clicking
																					// send button
		int statusCode = closeableHttpResponse.getStatusLine().getStatusCode(); // gives back the response code 2xx or
																				// 4xx etc
		System.out.println("Status Code:" + statusCode); // prints the status code

		String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8"); // this gives me the
																									// entire response
																									// in String
		JSONObject responseJsonObject = new JSONObject(responseString);// This will return the complete json object
		System.out.println("Response JSON from API" + responseJsonObject); // prints entire response body

		Header[] headerArray = closeableHttpResponse.getAllHeaders();// Convert the header into a hashmap since the
																		// headers are stored in key-pair values only
		HashMap headerMap = new HashMap<String, String>();

		for (Header header : headerArray) {
			headerMap.put(header.getName(), header.getValue());
		}
		System.out.println("Header's map-->" + headerMap);

	}

	// Post Method
	public CloseableHttpResponse post(String url, String entityString, HashMap<String, String> headerMap)
			throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault(); // This will create client connection
		HttpPost httpPost = new HttpPost(url);
		httpPost.setEntity(new StringEntity(entityString));// for payload

		for (Map.Entry<String, String> entry : headerMap.entrySet()) {
			httpPost.addHeader(entry.getKey(), entry.getValue()); // Header Maps to set headers
		}
		CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpPost);
		return closeableHttpResponse;
	}
	
	//PUT Method
	public CloseableHttpResponse put(String url, String entityString, HashMap<String, String> headerMap) throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault(); // This will create client connection
		HttpPut httpPut=new HttpPut(url);
		httpPut.setEntity(new StringEntity(entityString));
		for (Map.Entry<String, String> entry : headerMap.entrySet()) {
			httpPut.addHeader(entry.getKey(), entry.getValue()); // Header Maps to set headers
		}
		CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpPut);
		return closeableHttpResponse;
	}

}
