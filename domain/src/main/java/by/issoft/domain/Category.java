package by.issoft.domain;
import java.util.List;
import java.util.ArrayList;

public abstract class Category {
    private String name;
    private List<Product> products = new ArrayList<>();

    public Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Product> getProducts() {
        return products;
    }
    public void putProductToList(Product product) {
        products.add(product);
    }
}
