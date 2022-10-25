package by.issoft.consoleApp;
import by.issoft.store.Store;
import by.issoft.store.helpers.RandomStorePopulator;
import by.issoft.store.helpers.sortHelper.CombinedStreamSortHelper;
import by.issoft.store.helpers.threads.ThreadTime;

import java.io.IOException;
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
        timer.schedule(new ThreadTime(),0,60000);

        Boolean flag = true;
        Boolean flagSecond = true;
        while (flag) {
            String order = CombinedStreamSortHelper.orderReader();
            switch (order) {
                case "sort":
                    combinedStreamSortHelper.SortCategoriesInStore();
                    store.printStoreData();
                    flag = false;
                    break;
                case "top":
                    combinedStreamSortHelper.Top5(store);
                    flag = false;
                    break;
                case "quit":
                    flag = false;
                case "create order":
                    while (flagSecond){
                        System.out.println("Please type the product or type 'stop' to exit");
                        String productName = new Scanner(System.in).next();
                        switch(productName){
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