import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import files.Payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class Basics01 {

	public static void main(String[] args) {
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		String response = given().queryParam("key", "qaclick13").header("Content-Type","application/json")
				.body(Payload.addPlace()).when().post("/maps/api/place/add/json")
				.then().assertThat().statusCode(200).body("scope", equalTo("APP"))
				.header("Server", "Apache/2.4.41 (Ubuntu)").extract().response().asString();
				
				JsonPath js = new JsonPath(response);
				String placeID = js.getString("place_id");
				
				System.out.println(placeID);
				
				
		//DeletePlace
				
				String response1 = given().queryParam("key", "qaclick13").body("{\r\n"
						+ "    \"place_id\":\""+placeID+"\"\r\n"
						+ "}").delete("/maps/api/place/delete/json").then().assertThat().log().all().statusCode(200)
				.body("status", equalTo("OK")).extract().response().asString();
		
				System.out.println(response1);

	}

}
