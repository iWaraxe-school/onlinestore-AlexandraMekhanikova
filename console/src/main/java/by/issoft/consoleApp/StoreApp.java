package by.issoft.consoleApp;

import by.issoft.domain.Product;
import by.issoft.store.Store;
import by.issoft.store.helpers.RandomStorePopulator;
import by.issoft.store.helpers.XMLparsers.SortCommand;
import by.issoft.store.helpers.XMLparsers.SortKey;
import by.issoft.store.helpers.XMLparsers.XMLReaderToMap;
import by.issoft.store.helpers.comparators.ProductNameComparator;
import by.issoft.store.helpers.comparators.ProductPriceComparator;
import by.issoft.store.helpers.comparators.ProductRateComparator;

import java.util.*;

public class StoreApp {
    public static void main(String[] args) {
        Store store = new Store();
        RandomStorePopulator randomStorePopulator = new RandomStorePopulator(store);
        randomStorePopulator.fillStoreRandomly();
        store.printStoreData();
        Map<SortKey, SortCommand> m = XMLReaderToMap.getPropertiesToSort();
        Comparator productNameComparator = new ProductNameComparator();
        Comparator productRateComparator = new ProductRateComparator();
        Comparator productPriceComparator = new ProductPriceComparator();

        List<Product> unsortedList = store.getCategoryList().get(0).getProducts();

        Collections.sort(store.getCategoryList().get(0).getProducts(), productNameComparator
                .thenComparing(productPriceComparator)
                .thenComparing(productRateComparator));
        List<Product> sortedList = store.getCategoryList().get(0).getProducts();
    }
}