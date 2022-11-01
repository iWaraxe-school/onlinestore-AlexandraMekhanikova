import by.issoft.store.Store;
import by.issoft.store.helpers.RandomStorePopulator;
import by.issoft.store.helpers.sortHelper.CombinedStreamSortHelper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Test {
    public static void main(String[] args) throws Exception {
        Store store = Store.getInstance();

        RandomStorePopulator randomStorePopulator = new RandomStorePopulator(store);
        randomStorePopulator.fillStoreRandomly();

        CombinedStreamSortHelper combinedStreamSortHelper = new CombinedStreamSortHelper(store);
        combinedStreamSortHelper.SortCategoriesInStore();
        store.printStoreData();
    }
}