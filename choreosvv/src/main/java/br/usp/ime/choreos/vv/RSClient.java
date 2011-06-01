package br.usp.ime.choreos.vv;

import java.util.Map;

import com.jayway.restassured.RestAssured;

/**
 * RSClient is just a wrapper for the RESTAssured (v1.2.1) library
 * for REST communication. 
 * 
 * @author leonardo, piva
 *
 */
public class RSClient {

	private String baseUri;
	private int port;
	
	/**
	 * Creates a RSClient object.
	 * 
	 * @param baseUri The URI where the service is hosted.
	 * @param port The port where the service can be called.
	 */
	public RSClient(String baseUri, int port){
		this.baseUri = baseUri;
		this.port = port;
	}

	/**
	 * We set the request URL every time we make a request,
	 * to avoid service calls to wrong url's. For example, 
	 * if the client creates two RSClient's pointing to two
	 * different services, the RESTAssured library will always
	 * point to the last called service, because URI and port
	 * are static attributes.
	 */
	private void setRequestUrl(){
		RestAssured.baseURI = baseUri;
		RestAssured.port = port;
	}
	
	/**
	 * 
	 * @param path The path after the URI where the service is hosted.
	 * @param parameters GET parameters to be passed with the request.
	 * @return The service response as a string.
	 */
	public String get(String path, Map<String, String> parameters) {
		
		setRequestUrl();
		
		return RestAssured.with().parameters(parameters).get(path).asString();

	}

	/**
	 * 
	 * @param path The path after the URI where the service is hosted.
	 * @param parameters POST parameters to be passed with the request.
	 * @return The service response as a string.
	 */
	public String post(String path, Map<String, String> parameters) {

		setRequestUrl();
		
		return RestAssured.with().parameters(parameters).post(path).asString();

	}

	/**
	 * 
	 * @param path The path after the URI where the service is hosted.
	 * @param parameters PUT parameters to be passed with the request.
	 * @return The service response as a string.
	 */
	public String put(String path, Map<String, String> parameters) {

		setRequestUrl();
		
		return RestAssured.with().parameters(parameters).put(path).asString();

	}

	/**
	 * 
	 * @param path The path after the URI where the service is hosted.
	 * @param parameters DELETE parameters to be passed with the request.
	 * @return The service response as a string.
	 */
	public String delete(String path, Map<String, String> parameters) {
		
		setRequestUrl();
		
		return RestAssured.with().parameters(parameters).delete(path).asString();

	}

}