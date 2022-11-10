package by.issoft.store.helpers.api;

import by.issoft.domain.Category;
import by.issoft.store.helpers.api.models.PostApiModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;


import java.util.List;

public class ApiHelpers {

    private static final String BASE_URL = "http://localhost:8001";

    public static List<Category> getCategories() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        RestAssured.baseURI = BASE_URL;
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.get("/test");

        // Retrieve the body of the Response
        ResponseBody body = response.getBody();
        List<Category> categoryList = objectMapper.readValue(body.asString(), new TypeReference<List<Category>>() {
        });
        return categoryList;
    }

    public static void saveProduct(PostApiModel postApiModel) {
        System.out.println("Saving product " + postApiModel.getProduct().getName() + " to category " + postApiModel.getCategoryName());
        ObjectMapper objectMapper = new ObjectMapper();
        RestAssured.baseURI = "http://localhost:8001";
        RequestSpecification httpRequest = RestAssured.given().with().config(getRestAssuredConfig());
        httpRequest.header("Content-Type", "application/json");
        try {
            httpRequest.body(objectMapper.writeValueAsString(postApiModel));
            httpRequest.post("/test").then().statusCode(200);
        } catch (Exception e) {
            System.out.println("Error while saving product");
        }

        System.out.println("Save product via API ");
    }

    private static RestAssuredConfig getRestAssuredConfig() {
        RestAssured.config = RestAssuredConfig.config().httpClient(HttpClientConfig.httpClientConfig().
                setParam("http.connection.timeout", 3000).
                setParam("http.socket.timeout", 3000).
                setParam("http.connection-manager.timeout", 3000));
        return null;
    }
}
