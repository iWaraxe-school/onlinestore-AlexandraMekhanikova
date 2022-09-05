package by.issoft.consoleApp;
import by.issoft.store.Store;
import by.issoft.store.helpers.RandomStorePopulator;
import by.issoft.store.helpers.XMLparsers.SortCommand;
import by.issoft.store.helpers.XMLparsers.SortKey;
import by.issoft.store.helpers.XMLparsers.XMLReaderToMap;
import java.util.Map;

public class StoreApp {
    public static void main(String[] args) {
//        Store store = new Store();
//        RandomStorePopulator randomStorePopulator = new RandomStorePopulator(store);
//        randomStorePopulator.fillStoreRandomly();
//        store.printStoreData();

        Map<SortKey, SortCommand> m = XMLReaderToMap.getPropertiesToSort();
    }
}