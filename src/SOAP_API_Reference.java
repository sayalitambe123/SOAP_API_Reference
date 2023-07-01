import io.restassured.RestAssured;
import io.restassured.path.xml.XmlPath;

import static io.restassured.RestAssured.given;

import org.testng.Assert;


public class SOAP_API_Reference {
	public static void main(String[] args) {
		// Declare the BaseURI
		RestAssured.baseURI = "https://www.dataaccess.com/";
		
		//Declare request body
		String RequestBody = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\r\n"
				+ "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\r\n"
				+ "  <soap:Body>\r\n"
				+ "    <NumberToWords xmlns=\"http://www.dataaccess.com/webservicesserver/\">\r\n"
				+ "      <ubiNum>12</ubiNum>\r\n"
				+ "    </NumberToWords>\r\n"
				+ "  </soap:Body>\r\n"
				+ "</soap:Envelope>";
		
		//Extract ResponseBody
		String ResponseBody = given().header("Content-Type","text/xml; charset=utf-8").body(RequestBody).when().
				post("webservicesserver/numberconversion.wso").then().extract().response().asPrettyString();
		System.out.println(ResponseBody);
		
		//Parse the responseBody
		XmlPath xmlresponse = new XmlPath(ResponseBody);
		String Res_parameter = xmlresponse.getString("NumberToWordsResult");
		System.out.println(Res_parameter);
		
		//Validate the ResponseBody
		Assert.assertEquals(Res_parameter,"twelve ");
	}

}
