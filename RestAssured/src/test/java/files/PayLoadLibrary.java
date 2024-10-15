package files;

public class PayLoadLibrary {
	
	public static String addBook(String isbn, String aisle,String authName) {
		
		return "{\r\n"
				+ "\r\n"
				+ "\"name\":\"Learn Appium Automation with Java Automation Test Practice 206\",\r\n"
				+ "\"isbn\":\""+isbn+"\",\r\n"
				+ "\"aisle\":\""+aisle+"\",\r\n"
				+ "\"author\":\""+authName+"\"\r\n"
				+ "}\r\n"
				+ "";
		
	}
	
	public static String addBookforDelete() {
		
		return "{\r\n"
				+ "\r\n"
				+ "\"name\":\"Learn Appium Automation with Java Automation Test Practice 206\",\r\n"
				+ "\"isbn\":\"ISBN-2312312\",\r\n"
				+ "\"aisle\":\"2FA8312\",\r\n"
				+ "\"author\":\"Automation Practice By Karthik\"\r\n"
				+ "}\r\n"
				+ "";
		
	}

}
