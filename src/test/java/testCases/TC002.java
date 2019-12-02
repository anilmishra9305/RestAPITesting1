package testCases;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utilities.RestUtils;

public class TC002  extends TestBase{
	
	
	RequestSpecification httpRequest;
	Response res;
	
	String empName=RestUtils.empName();
	String empSalary=RestUtils.empSal();
	String empAge=RestUtils.empAge();
	
	@BeforeClass
	public void createEmployee() throws InterruptedException
	{
		
		logger.info("***************** Started TC002 For POST Employee Record************************************");
		
		
		RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
		httpRequest=RestAssured.given();
		JSONObject object=new JSONObject();
		object.put("name", empName);
		object.put("salary", empSalary);
		object.put("age", empAge);
		
		//Add a header stating the request body is a json
		
		httpRequest.header("Content-Type","application/json");
		
		//add the json to the body of the request
		httpRequest.body(object.toJSONString());
		
		res=httpRequest.request(Method.POST,"/create");
		Thread.sleep(5000);
		
	}
	
	
	
	@Test
	public void checkResponseBody()
	{
		
	String responseBody=res.getBody().asString();
	System.out.println("Response Body ==> "+responseBody);
	
	Assert.assertEquals(responseBody.contains(empName), true);
	
	Assert.assertEquals(responseBody.contains(empAge), true);
	
	Assert.assertEquals(responseBody.contains(empSalary), true);
		
		
		
	}
	
	@Test
	public void statusCode()
	{
		
		
		int statusCode=res.getStatusCode();
		Assert.assertEquals(statusCode, 200);
		
		
		
	}
	
	@Test
	public void checkStatusLine()
	{
		
		String statusLine=res.getStatusLine();
		
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
		
	}
	
  @Test
	public void checkContentType()
	{
		String contentType=res.header("Content-Type");
		
		Assert.assertEquals(contentType, "text/html; charset=UTF-8");
		
		
		
	}
	
	@Test
	public void checkServerType()
	{
		
		String serverType=res.header("Server");
		Assert.assertEquals(serverType, "Apache");
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
