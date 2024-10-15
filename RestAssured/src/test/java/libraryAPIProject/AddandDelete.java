package libraryAPIProject;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import files.PayLoadLibrary;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class AddandDelete {

	public static void main(String[] args) {

		RestAssured.baseURI = "http://216.10.245.166";
		String response = given().log().all().header("Content-Type", "application/json")
				.body(PayLoadLibrary.addBookforDelete()).when().post("/Library/Addbook.php").then().log().all()
				.assertThat().statusCode(200).extract().response().asString();

		JsonPath js = ReUsableMethods.rawToJson(response);
		String bookID = js.get("ID");

		System.out.println(bookID);

		String deleteString = given()
				.body("{\r\n" + " \r\n" + "\"ID\" : \"" + bookID + "\"\r\n" + " \r\n" + "} \r\n" + "").when()
				.post("/Library/DeleteBook.php").then().extract().response().asString();

		System.out.println(deleteString);

	}

}
