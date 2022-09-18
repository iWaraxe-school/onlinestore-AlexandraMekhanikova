package by.issoft.store.helpers.sortHelper;
import by.issoft.domain.Category;
import by.issoft.domain.Product;
import by.issoft.store.Store;
import by.issoft.store.helpers.XMLparsers.SortCommand;
import by.issoft.store.helpers.XMLparsers.SortKey;

import by.issoft.store.helpers.XMLparsers.XmlReaderToMap;
import java.util.*;


public class CombinedStreamSortHelper {
    private static final Map<Enum<SortKey>, Comparator<Product>> COMPARATOR_MAP = new HashMap<Enum<SortKey>, Comparator<Product>>() {{
        put(SortKey.NAME, Comparator.comparing(Product::getName));
        put(SortKey.PRICE, Comparator.comparing(Product::getPrice));
        put(SortKey.RATE, Comparator.comparing(Product::getRate));
    }};

    Store store;

    public CombinedStreamSortHelper(Store store) {
        this.store = store;
    }

    public List<Product> getAllProductsList(Store store) {
        List<Product> allProducts = new ArrayList<>();
        for (Category category : store.getCategoryList()) {
            allProducts.addAll(category.getProducts());
        }
        return allProducts;
    }

    private Comparator<Product> prepareComparator(Map.Entry<Enum<SortKey>, Enum<SortCommand>> entry) {
        Enum<SortKey> comparatorName = entry.getKey();
        Comparator<Product> comparator = COMPARATOR_MAP.getOrDefault(comparatorName, null);
        if (entry.getValue().equals(SortCommand.DESC.toString()) && Objects.nonNull((comparator))) {
            comparator = comparator.reversed();
        }
        return comparator;
    }

    private Comparator<Product> buildComparator(Map<Enum<SortKey>, Enum<SortCommand>> mapConfig) throws Exception {
        return mapConfig.entrySet().stream()
                .map(this::prepareComparator)
                .filter(Objects::nonNull)
                .reduce(Comparator::thenComparing)
                .orElseThrow(Exception::new);
    }

    public void SortProductsInCategory(Category category, Map<Enum<SortKey>, Enum<SortCommand>> mapConfig) throws Exception {
        category.getProducts().sort(buildComparator(mapConfig));
    }

    public void SortCategoriesInStore() throws Exception {
        for (Category category:
                store.getCategoryList()) {
            SortProductsInCategory(category, getMapConfig());
        }
    }

    private Map<Enum<SortKey>, Enum<SortCommand>> getMapConfig(){
        XmlReaderToMap xmlReaderToMap = new XmlReaderToMap();
        Map<Enum<SortKey>, Enum<SortCommand>> mapConfig = xmlReaderToMap.getPropertiesToSort();
        return  mapConfig;
        }
    }