package testCases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.Method;

public class TC001 extends TestBase {
	
	
	
	@BeforeClass
	public void getAllEmployees()
	{
		
		logger.info("****************************Started TC001 For Get All employee************************************************");
		
		RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
		httpRequest=RestAssured.given();
		response=httpRequest.request(Method.GET,"employees");
		
	}
	
	
	@Test
	public void responseBody()
	{
		
		logger.info("***************************Checking The Body*******************************************");
		
		
		String responseBody=response.getBody().asString();
		
		logger.info("Resonse Body >>>: "+responseBody);
		
		Assert.assertTrue(responseBody!=null);
		
		
	}
	
	@Test
	public void checkstatusCode()
	{
		
		logger.info("*************Checking Status Code***********************");
		
		
		int statusCode=response.getStatusCode();
		
		logger.info("Status Code is ==> "+statusCode);
		
		Assert.assertEquals(statusCode, 200);
	}
	
	@Test
	public void checkResponseTime()
	{
		
		logger.info("***********************Checking Response Time*******************************************");
		
		long responseTime=response.getTime();
		
		logger.info("Response Time is ==> "+responseTime);
		
		
		
		if(responseTime>2000)
		{
			logger.warn("Response Time is greater than 2000");
			
		}
		
		
		Assert.assertTrue(responseTime<10000);
		
	}
	
	
	
	@Test
	public void checkStatusLine()
	{
		
		logger.info("***************Checking Status Line*****************************");
		
		String statusLine=response.getStatusLine();
		
		logger.info("Status Line is ==> "+statusLine);
		
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
		
	}
	
	@Test
	public void checkContentType()
	{
		logger.info("****************Checking Content Type***************************************");
		
		String contentType=response.header("Content-Type");
		logger.info("Content Type is ==> "+contentType);
		
		Assert.assertEquals(contentType, "text/html; charset=UTF-8");
		
	}
	
	@Test
	public void checkServerType()
	{
		
		logger.info("**************************Check Server Type********************************************");
		
		String serverType=response.header("Server");
		logger.info("Server Type is ==> "+serverType);
		
		Assert.assertEquals(serverType, "nginx/1.16.0");
		
	}
	
	
	@Test
	public void checkcontentEncoding()
	{
		
		logger.info("******************Check Content Encoding************************************************************");
		
		String contentEncoding=response.header("Content-Encoding");
		
		logger.info("Content Encoding is ==> "+contentEncoding);
		Assert.assertEquals(contentEncoding, "gzip");
	}

	
	/*
	 * @Test public void checkContentLength() { logger.
	 * info("********************Check Content Length**********************************************************"
	 * );
	 * 
	 * String contentLength=response.header("Content-Length");
	 * logger.info("Content Length is ==> "+contentLength);
	 * 
	 * Assert.assertTrue(Integer.parseInt(contentLength)>100);
	 * 
	 * 
	 * }
	 */
	
	
	
	
	@AfterClass
	public void tearDown()
	{
		logger.info("*************************Finished execution of TestCase1 *************************************** ");
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
