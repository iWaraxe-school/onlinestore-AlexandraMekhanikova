package by.issoft.store;

import by.issoft.domain.Category;
import by.issoft.domain.Product;
import by.issoft.store.helpers.dataBase.DataBaseHelpers;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Store {

    public static CopyOnWriteArrayList<Product> purchasedProducts = new CopyOnWriteArrayList<>(); //added CopyOnWriteArrayList
    private static Store instance;

    public static Store getInstance(){ //Singleton
        if (instance==null){
            instance = new Store();
        }
        return instance;
    }

    private Store() {
    }

    public List<Category> getCategoryList() {
        return DataBaseHelpers.getCategories();
    }

    public void printStoreData() {
        System.out.println("Current Data in DB:");
        List<Category> categoryList = getCategoryList();
        for (int i = 0; i < categoryList.size(); i++) {
            Category currentCategory = categoryList.get(i);
            System.out.print("Category Name:" + currentCategory.getName());
            System.out.println();
            List<Product> productList = DataBaseHelpers.getProducts(currentCategory);
            for (int j = 0; j < productList.size(); j++) {
                Product currentProduct = productList.get(j);
                System.out.print(" " + "Name:" + currentProduct.getName());
                System.out.print(" " + "Price:" + currentProduct.getPrice());
                System.out.print(" " + "Rate:" + currentProduct.getRate());
                System.out.println();
            }
            System.out.println();
        }
    }
}

