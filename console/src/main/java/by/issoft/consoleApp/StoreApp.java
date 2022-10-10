package by.issoft.consoleApp;
import by.issoft.store.Store;
import by.issoft.store.helpers.RandomStorePopulator;
import by.issoft.store.helpers.sortHelper.CombinedStreamSortHelper;

import java.io.IOException;

public class StoreApp {
    public static void main(String[] args) throws Exception {
        Store store = Store.getInstance();
        RandomStorePopulator randomStorePopulator = new RandomStorePopulator(store);
        CombinedStreamSortHelper combinedStreamSortHelper = new CombinedStreamSortHelper(store);
        randomStorePopulator.fillStoreRandomly();
        store.printStoreData();

        Boolean flag = true;
        String order = CombinedStreamSortHelper.orderReader();
        while (flag) {
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
                    break;
                default:
                    System.out.println("Incorrect order");
                    order = CombinedStreamSortHelper.orderReader();
            }
        }
    }
}