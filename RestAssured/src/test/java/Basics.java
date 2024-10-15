import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.Assert;

import files.Payload;

public class Basics {

	public static void main(String[] args) throws IOException {
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String response = given().queryParam("key", "qaclick13").header("Content-Type","application/json")
		.body(new String (Files.readAllBytes(Paths.get("C:\\Users\\Karthik\\Downloads\\AddPlace.json")))).when().post("/maps/api/place/add/json")
		.then().assertThat().statusCode(200).body("scope", equalTo("APP"))
		.header("Server", "Apache/2.4.41 (Ubuntu)").extract().response().asString();
		
		
		JsonPath js = ReUsableMethods.rawToJson(response);
		String placeID = js.getString("place_id");
		
		System.out.println(placeID);
		
		//UpdatePlace
		
		String newAddress = "801 Summer walk, USA";
		
		given().queryParam("key", "qaclick13").header("Content-Type","application/json").body("{\r\n"
				+ "\"place_id\":\""+placeID+"\",\r\n"
				+ "\"address\":\""+newAddress+"\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}").when().put("/maps/api/place/update/json").then().assertThat().log().all().statusCode(200)
		.body("msg", equalTo("Address successfully updated"));
		
		//GetPlace
		String address = given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeID).when().
		get("/maps/api/place/get/json").then().assertThat().log().all().statusCode(200)
		.extract().response().asString();
		
		JsonPath js1 = ReUsableMethods.rawToJson(address);
		String updatedaddress = js1.getString("address");
		
		System.out.println(updatedaddress);
		
		Assert.assertEquals(updatedaddress, newAddress);
		
	}

}
