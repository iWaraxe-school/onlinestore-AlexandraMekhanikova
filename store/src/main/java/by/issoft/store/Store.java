package by.issoft.store;

import by.issoft.domain.Category;
import by.issoft.domain.Product;
import by.issoft.store.helpers.XMLparsers.XmlReaderToMap;
import by.issoft.store.helpers.sortHelper.CombinedStreamSortHelper;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Store {

    private List<Category> categories;
    public static CopyOnWriteArrayList<Product> purchasedProducts = new CopyOnWriteArrayList<>(); //added CopyOnWriteArrayList
    private static Store instance;

    public static Store getInstance(){ //Singleton
        if (instance==null){
            instance = new Store();
        }
        return instance;
    }

    private Store() {
        categories = new ArrayList<>();
    }

    private List<Category> categoryList = new ArrayList<>();
    public void addCategory(Category category) {
        categoryList.add(category);
    }
    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void printStoreData() {
        System.out.println("Store{" + "categoryList" + '}');
        for (int i = 0; i < categoryList.size(); i++) {
            Category currentCategory = categoryList.get(i);
            System.out.print("Category Name:" + currentCategory.getName());
            System.out.println();
            for (int j = 0; j < currentCategory.getProducts().size(); j++) {
                Product currentProduct = currentCategory.getProducts().get(j);
                System.out.print(" " + "Name:" + currentProduct.getName());
                System.out.print(" " + "Price:" + currentProduct.getPrice());
                System.out.print(" " + "Rate:" + currentProduct.getRate());
                System.out.println();
            }
            System.out.println();
        }
    }
}

