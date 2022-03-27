import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.commons.io.IOUtils;

import java.io.FileInputStream;
import java.io.IOException;

import static io.restassured.RestAssured.given;

public class RequestSOAP {

    public static void postRequest(String requestFile, String path) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(requestFile);
        RestAssured.baseURI = "https://wd5-impl-services1.workday.com";

        Response response = given()
                .header("Content-Type", "text/xml")
                .and()
                .body(IOUtils.toString(fileInputStream, "UTF-8"))
                .when()
                .post(path)
                .then()
                .statusCode(200)
                .and()
                .extract().response();
    }

    public static void main(String[] args) throws IOException {
        String requestFile="src\\test\\resources\\Request_1.xml";
        String path="/ccx/service/levistraussandco_preview/Integrations/v38.0" ;
        postRequest( requestFile, path);

    }

}