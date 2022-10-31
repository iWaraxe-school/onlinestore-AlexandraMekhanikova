package by.issoft.consoleApp;

import by.issoft.domain.Category;
import by.issoft.domain.Product;
import by.issoft.store.Store;
import by.issoft.store.helpers.DataBase.DataBaseHelpers;
import by.issoft.store.helpers.RandomStorePopulator;
import by.issoft.store.helpers.sortHelper.CombinedStreamSortHelper;
import by.issoft.store.helpers.threads.ThreadTime;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;

public class StoreApp {
    public static void main(String[] args) throws Exception {
        Store store = Store.getInstance();
        RandomStorePopulator randomStorePopulator = new RandomStorePopulator(store);
        CombinedStreamSortHelper combinedStreamSortHelper = new CombinedStreamSortHelper(store);
        randomStorePopulator.fillStoreRandomly();
        store.printStoreData();

        Timer timer = new Timer();
        timer.schedule(new ThreadTime(), 0, 60000);

        Boolean flag = true;
        Boolean flagSecond = true;
        while (flag) {
            String order = CombinedStreamSortHelper.orderReader();
            switch (order) {
                case "sort":
                    List<Category> categories = DataBaseHelpers.getCategories();
                    for (Category category : categories) {
                        System.out.println(category.getName());
                        List<Product> sortedProducts = combinedStreamSortHelper.sortProducts(DataBaseHelpers.getProducts(category));
                        for (Product product : sortedProducts) {
                            System.out.println(product.toString());
                        }
                        System.out.println();
                    }
                    flag = true;
                    break;
                case "top":
                    combinedStreamSortHelper.top5(store);
                    flag = true;
                    break;
                case "quit":
                    flag = false;
                case "create order":
                    while (flagSecond) {
                        System.out.println("Please type the product or type 'stop' to exit");
                        String productName = new BufferedReader(new InputStreamReader(System.in)).readLine();
                        switch (productName) {
                            case "stop":
                                flagSecond = false;
                                break;
                            default:
                                combinedStreamSortHelper.createOrder(productName);
                        }
                    }
                    break;
                default:
                    System.out.println("Incorrect order");
                    order = CombinedStreamSortHelper.orderReader();
            }
        }
    }
}