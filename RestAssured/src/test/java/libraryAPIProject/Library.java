package libraryAPIProject;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import files.PayLoadLibrary;



public class Library {
	
	String bookID;
	
	@Test(dataProvider = "BooksData")
	public void addBook(String ISBN, String aisle, String authName) {
		
		RestAssured.baseURI = "http://216.10.245.166";
		String response = given().log().all().header("Content-Type","application/json")
		.body(PayLoadLibrary.addBook(ISBN,aisle,authName)).when().post("/Library/Addbook.php")
		.then().log().all().assertThat().statusCode(200)
		.extract().response().asString();
		
		JsonPath js = ReUsableMethods.rawToJson(response);
		bookID= js.get("ID");
		
		System.out.println(bookID);
		
	}
	
	@DataProvider(name="BooksData")
	public Object[][] getData() {
		
		return new Object[][] {
			{"ISBN-72UI721","2FA76","Karthik Test Automation Practice API 1"},
			{"ISBN-62281","2FA3512","Karthik Test Automation Practice API 2"},
			{"ISBN-216319","2FA26123","Karthik Test Automation Practice API 3"}
		};
		
	}
	
	
	  @Test public void deleteBook() {
	  
	  RestAssured.baseURI = "http://216.10.245.166"; 
	  String deleteString = given().body("{\r\n" + " \r\n" + "\"ID\" : \""+bookID+"\"\r\n" + " \r\n" +
	  "} \r\n" +
	  "").when().post("/Library/DeleteBook.php").then().extract().response().asString();
	  
	  System.out.println(deleteString);
	  
	  }
	 
	
	

}