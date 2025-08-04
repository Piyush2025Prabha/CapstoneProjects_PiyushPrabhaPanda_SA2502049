package capstoneProject;

import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import io.restassured.response.Response;

public class ProjectUsingRestAssured {	
	String EndPoint1 = "https://thinking-tester-contact-list.herokuapp.com/users";
	String EndPoint2 = "https://thinking-tester-contact-list.herokuapp.com/users/me";
	String LoginEndPoint = "https://thinking-tester-contact-list.herokuapp.com/users/login";
	String Endpoint3 = "https://thinking-tester-contact-list.herokuapp.com/contacts";
	String ContactEndPoint;
	String LogoutEndPoint = "https://thinking-tester-contact-list.herokuapp.com/users/logout";
	String UserToken;
	String LoginToken;
	String UserName;
	String Password;
	String ContactId;
	@Test(priority = 1)
	public void addNewUser() {		
		Response Res = given().header("Content-Type","application/json")
							  .body("{\r\n"
									  + "\"firstName\": \"Piyush\",\r\n"
									  + "\"lastName\": \"Panda\",\r\n"
									  + "\"email\": \"piyush"+System.currentTimeMillis()+"@gmail.com\", \"password\": \"Piyush@123\"\r\n"
									  + "}")
							  .when()
							  .post(EndPoint1);
		Res.then().log().all();
		UserToken = Res.jsonPath().getString("token");
		System.out.println("The token value is: " + UserToken);
		System.out.println("The status code is: " + Res.getStatusCode());
		Assert.assertEquals(Res.getStatusCode(),201,"The status code is incorrect");
		String statusMessage = Res.getStatusLine().split(" ", 3)[2];
		System.out.println("The status message is: " + statusMessage);
		Assert.assertEquals(statusMessage,"Created","The status message is invalid");
	}
	@Test(priority = 2,dependsOnMethods = "addNewUser")
	public void getUserProfile() {
		Response Res = given().header("Content-Type","application/json")
							  .header("Authorization","Bearer "+UserToken)
							  .when()
							  .get(EndPoint2);
		Res.then().log().all();
		System.out.println("The status code is: " + Res.getStatusCode());
		Assert.assertEquals(Res.getStatusCode(),200,"The status code is incorrect");
		String statusMessage = Res.getStatusLine().split(" ", 3)[2];
		System.out.println("The status message is: " + statusMessage);
		Assert.assertEquals(statusMessage,"OK","The status message is invalid");
	}
	@Test(priority = 3,dependsOnMethods = "addNewUser")
	public void updateUser() {
		Response Res = given().header("Content-Type","application/json")
							  .header("Authorization","Bearer "+UserToken)
							  .body("{\r\n"
									  + "\"firstName\": \"Prabha\", \"lastName\": \"Mohapatra\",\r\n"
									  + "\"email\": \"Prabha"+System.currentTimeMillis()+"@ymail.com\",\r\n"
									  + "\"password\": \"Prabha@123\"\r\n"
									  + "}")
							  .when()
							  .patch(EndPoint2);
		Res.then().log().all();
		UserName = Res.jsonPath().getString("email");
		Password = "Prabha@123";
		System.out.println("The status code is: " + Res.getStatusCode());
		Assert.assertEquals(Res.getStatusCode(),200,"The status code is incorrect");
		String statusMessage = Res.getStatusLine().split(" ", 3)[2];
		System.out.println("The status message is: " + statusMessage);
		Assert.assertEquals(statusMessage,"OK","The status message is invalid");
	}
	@Test(priority = 4,dependsOnMethods = "updateUser")
	public void logInUser() {
		Response Res = given().header("Content-Type","application/json")
							  .body("{\r\n"
									  + "\"email\": \""+UserName+"\",\r\n"
									  + "\"password\": \""+Password+"\"\r\n"
									  + "}")
							  .when()
							  .post(LoginEndPoint);
		Res.then().log().all();
		LoginToken = Res.jsonPath().getString("token");
		System.out.println("The status code is: " + Res.getStatusCode());
		Assert.assertEquals(Res.getStatusCode(),200,"The status code is incorrect");
		String statusMessage = Res.getStatusLine().split(" ", 3)[2];
		System.out.println("The status message is: " + statusMessage);
		Assert.assertEquals(statusMessage,"OK","The status message is invalid");		
	}
	@Test(priority = 5,dependsOnMethods = "logInUser")
	public void addContact() {
		Response Res = given().header("Content-Type","application/json")
							  .header("Authorization","Bearer "+LoginToken)
							  .body("{\r\n"
									  + "\"firstName\": \"John\",\r\n"
									  + "\"lastName\": \"Doe\",\r\n"
									  + "\"birthdate\": \"1970-01-01\", \"email\": \"jdoe@fake.com\", \"phone\": \"8005555555\",\r\n"
									  + "\"street1\": \"1 Main St.\", \"street2\": \"Apartment A\", \"city\": \"Anytown\",\r\n"
									  + "\"stateProvince\": \"KS\", \"postalCode\": \"12345\", \"country\": \"USA\"\r\n"
									  + "}")
							  .when()
							  .post(Endpoint3);
		Res.then().log().all();
		ContactId = Res.jsonPath().getString("_id");
		System.out.println("The status code is: " + Res.getStatusCode());
		Assert.assertEquals(Res.getStatusCode(),201,"The status code is incorrect");
		String statusMessage = Res.getStatusLine().split(" ", 3)[2];
		System.out.println("The status message is: " + statusMessage);
		Assert.assertEquals(statusMessage,"Created","The status message is invalid");
	}
	@Test(priority = 6,dependsOnMethods = "logInUser")
	public void getContactList() {
		Response Res = given().header("Content-Type","application/json")
							  .header("Authorization","Bearer " +LoginToken)
							  .when()
							  .get(Endpoint3);
		Res.then().log().all();
		//ContactId = Res.jsonPath().getString("_id");
		System.out.println("The status code is: " + Res.getStatusCode());
		Assert.assertEquals(Res.getStatusCode(),200,"The status code is incorrect");
		String statusMessage = Res.getStatusLine().split(" ", 3)[2];
		System.out.println("The status message is: " + statusMessage);
		Assert.assertEquals(statusMessage,"OK","The status message is invalid");
	}
	@Test(priority = 7,dependsOnMethods = {"logInUser","addContact"})
	public void getContact() {
		ContactEndPoint = "https://thinking-tester-contact-list.herokuapp.com/contacts/"+ContactId;
		Response Res = given().header("Content-Type","application/json")
							  .header("Authorization","Bearer " +LoginToken)
							  .when()
							  .get(ContactEndPoint);
		Res.then().log().all();
		System.out.println("The status code is: " + Res.getStatusCode());
		Assert.assertEquals(Res.getStatusCode(),200,"The status code is incorrect");
		String statusMessage = Res.getStatusLine().split(" ", 3)[2];
		System.out.println("The status message is: " + statusMessage);
		Assert.assertEquals(statusMessage,"OK","The status message is invalid");		   
	}
	@Test(priority = 8,dependsOnMethods = {"logInUser","addContact"})
	public void updateContact() {
		Response Res = given().header("Content-Type","application/json")
				  			  .header("Authorization","Bearer " +LoginToken)
				  			  .body("{\r\n"
				  					  + "\"firstName\": \"Amy\", \"lastName\": \"Miller\",\r\n"
				  					  + "\"birthdate\": \"1992-02-02\",\r\n"
				  					  + "\"email\": \"amiller@fake.com\", \"phone\": \"8005554242\",\r\n"
				  					  + "\"street1\": \"13 School St.\", \"street2\": \"Apt. 5\",\r\n"
				  					  + "\"city\": \"Washington\", \"stateProvince\": \"QC\",\r\n"
				  					  + "\"postalCode\": \"A1A1A1\", \"country\": \"Canada\"\r\n"
				  					  + "}")
				  			  .when()
				  			  .put(ContactEndPoint);
		Res.then().log().all();
		String ActualEmail = Res.jsonPath().getString("email");
		Assert.assertTrue(ActualEmail.equals("amiller@fake.com"), ContactEndPoint);
		System.out.println("The status code is: " + Res.getStatusCode());
		Assert.assertEquals(Res.getStatusCode(),200,"The status code is incorrect");
		String statusMessage = Res.getStatusLine().split(" ", 3)[2];
		System.out.println("The status message is: " + statusMessage);
		Assert.assertEquals(statusMessage,"OK","The status message is invalid");
	}
	@Test(priority = 9,dependsOnMethods = {"logInUser","addContact"})
	public void partialUpdateContact() {
		Response Res = given().header("Content-Type","application/json")
		  	   				  .header("Authorization","Bearer " +LoginToken)
		  	   				  .body("{\r\n"
		  	   						  + "\"firstName\": \"Anna\"\r\n"
		  	   						  + "}")
		  	   				  .when()
		  	   				  .patch(ContactEndPoint);
		 Res.then().log().all();
		 String ActualFirstName = Res.jsonPath().getString("firstName");
		 Assert.assertTrue(ActualFirstName.equals("Anna"), ContactEndPoint);
		 System.out.println("The status code is: " + Res.getStatusCode());
		 Assert.assertEquals(Res.getStatusCode(),200,"The status code is incorrect");
		 String statusMessage = Res.getStatusLine().split(" ", 3)[2];
		 System.out.println("The status message is: " + statusMessage);
		 Assert.assertEquals(statusMessage,"OK","The status message is invalid");
	}
	@Test(priority = 10,dependsOnMethods = "partialUpdateContact")
	public void logOutUser() {
		Response Res = given().header("Content-Type","application/json")
							  .header("Authorization","Bearer " +LoginToken)
							  .when()
							  .post(LogoutEndPoint);
		Res.then().log().all();
		System.out.println("No JSON response returned");
		System.out.println("The status code is: " + Res.getStatusCode());
		Assert.assertEquals(Res.getStatusCode(),200,"The status code is incorrect");
		String statusMessage = Res.getStatusLine().split(" ", 3)[2];
		System.out.println("The status message is: " + statusMessage);
		Assert.assertEquals(statusMessage,"OK","The status message is invalid");
	}
}
	

