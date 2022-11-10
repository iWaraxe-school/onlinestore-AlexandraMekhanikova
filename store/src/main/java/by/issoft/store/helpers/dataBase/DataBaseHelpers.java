package by.issoft.store.helpers.dataBase;

import by.issoft.domain.Category;
import by.issoft.domain.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBaseHelpers {

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:postgresql://localhost:5432/OnlineStore", "postgres", "postgres");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void saveProduct(String categoryName, Product product) {
        Statement statement = null;
        try {
            statement = getConnection().createStatement();
            String query = String.format("INSERT INTO \"OnlineStore\".products (name, rate, price, category_id) VALUES ('%s', '%f', '%f', (Select ID FROM \"OnlineStore\".categories Where name = '%s'))", product.getName().replace("'","''"), product.getRate(), product.getPrice(), categoryName);
            statement.execute(query);
            System.out.println("Product " + product.getName() + " was added to the database");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Product> getProducts(Category category) {
        Statement statement = null;
        List<Product> productList = new ArrayList<>();
        try {
            statement = getConnection().createStatement();
            String query = String.format("SELECT * FROM \"OnlineStore\".products WHERE category_id = (Select ID FROM \"OnlineStore\".categories Where categories.name = '%s')", category.getName());

            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Product product = new Product(resultSet.getString("name"), resultSet.getDouble("price"), resultSet.getDouble("rate"));
                productList.add(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return productList;
    }

    public static List<Category> getCategories() {
        Statement statement = null;
        List<Category> categoryList = new ArrayList<>();
        try {
            statement = getConnection().createStatement();
            String query = "SELECT * FROM \"OnlineStore\".categories";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Category category = new Category(resultSet.getString("name"));
                categoryList.add(category);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return categoryList;
    }
}
