package by.issoft.store.helpers.api.models;

import by.issoft.domain.Product;

public class PostApiModel {
    private String categoryName;
    private Product product;

    public PostApiModel() {
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public Product getProduct() {
        return product;
    }
}