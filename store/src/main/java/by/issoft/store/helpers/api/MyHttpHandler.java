package by.issoft.store.helpers.api;

import by.issoft.domain.Category;
import by.issoft.store.helpers.api.models.PostApiModel;
import by.issoft.store.helpers.dataBase.DataBaseHelpers;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.util.List;

public class MyHttpHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        System.out.println("Request received");
        String requestParamValue = null;

        if ("GET".equals(httpExchange.getRequestMethod())) {
            handleGetRequest(httpExchange);
        } else if ("POST".equals(httpExchange.getRequestMethod())) {
            handlePostRequest(httpExchange);
        }
        System.out.println("Request processed");
        httpExchange.sendResponseHeaders(200, 0);
        httpExchange.close();
    }

    private void handlePostRequest(HttpExchange httpExchange) throws IOException {
        System.out.println("POST request received");
        PostApiModel postApiModel = getPostApiModel(httpExchange);
        DataBaseHelpers.saveProduct(postApiModel.getCategoryName(), postApiModel.getProduct());
        System.out.println("Product saved");
    }

    private PostApiModel getPostApiModel(HttpExchange httpExchange) throws IOException {
        InputStreamReader isr = new InputStreamReader(httpExchange.getRequestBody(), "utf-8");
        BufferedReader br = new BufferedReader(isr);

        int b;
        StringBuilder buf = new StringBuilder();
        while ((b = br.read()) != -1) {
            buf.append((char) b);
        }

        br.close();
        isr.close();
        String jsonString = buf.toString();
        ObjectMapper objectMapper = new ObjectMapper();
        PostApiModel postApiModel = objectMapper.readValue(jsonString, PostApiModel.class);
        return postApiModel;
    }

    private void handleGetRequest(HttpExchange httpExchange) throws IOException {
        System.out.println("GET request received");
        OutputStream outputStream = httpExchange.getResponseBody();

        List<Category> categoryList = DataBaseHelpers.getCategories();
        StringBuilder htmlBuilder = new StringBuilder();
        htmlBuilder.
                append(String.join("\r\n", categoryList.stream().map(category -> category.getName()).toArray(String[]::new)));
        String json = new ObjectMapper().writeValueAsString(categoryList);
        // this line is a must
        httpExchange.sendResponseHeaders(200, json.length());
        outputStream.write(json.getBytes());
        outputStream.flush();
        outputStream.close();
    }
}
