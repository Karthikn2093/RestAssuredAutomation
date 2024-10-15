import static org.junit.Assert.assertArrayEquals;

import java.util.Iterator;

import org.junit.Assert;

import files.Payload;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {

	public static void main(String[] args) {
		
		JsonPath js = ReUsableMethods.rawToJson(Payload.coursePrice());
		
		int size = js.getInt("courses.size()");
		System.out.println(size);
		int purchaseAmount = js.getInt("dashboard.purchaseAmount");
		System.out.println(purchaseAmount);
		String title = js.get("courses[0].title");
		System.out.println(title);
		
		for (int i = 0; i < size; i++) {
			
			System.out.println("Course Title: "+js.get("courses["+i+"].title"));
			System.out.println("Price: "+js.get("courses["+i+"].price"));
		}
		
		System.out.println("---------------------");
		
		for (int i = 0; i < size; i++) {
			
			String courseTitle = js.get("courses["+i+"].title");
			if(courseTitle.equalsIgnoreCase("RPA")) {
				
				System.out.println("Course Title is: " + courseTitle);
				System.out.println("Copies Sold: "+js.get("courses["+i+"].copies"));
				break;
				
			}
			
		}
		
		System.out.println("---------------------");
		
		int totalPrice = 0;
		
		for (int i = 0; i < size; i++) {
			
			int price = js.get("courses["+i+"].price");
			int copies = js.get("courses["+i+"].copies");
			
			totalPrice = totalPrice + (price * copies);
		}
		
		Assert.assertEquals(totalPrice, purchaseAmount);
		Assert.assertEquals("Price is not equal", totalPrice, purchaseAmount);
		
	}

}