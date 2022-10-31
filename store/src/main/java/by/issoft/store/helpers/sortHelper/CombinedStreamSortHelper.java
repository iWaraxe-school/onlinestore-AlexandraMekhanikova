package by.issoft.store.helpers.sortHelper;
import by.issoft.domain.Category;
import by.issoft.domain.Product;
import by.issoft.store.Store;
import by.issoft.store.helpers.DataBase.DataBaseHelpers;
import by.issoft.store.helpers.XMLparsers.SortCommand;
import by.issoft.store.helpers.XMLparsers.SortKey;

import by.issoft.store.helpers.XMLparsers.XmlReaderToMap;
import by.issoft.store.helpers.threads.ThreadOrder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class CombinedStreamSortHelper {
    private static final Map<Enum<SortKey>, Comparator<Product>> COMPARATOR_MAP = new HashMap<Enum<SortKey>, Comparator<Product>>() {{
        put(SortKey.NAME, Comparator.comparing(Product::getName));
        put(SortKey.PRICE, Comparator.comparing(Product::getPrice));
        put(SortKey.RATE, Comparator.comparing(Product::getRate));
    }};

    Store store;

    List<Category> sortedCategories = new ArrayList<>();

    public CombinedStreamSortHelper(Store store) {
        this.store = store;
    }

    public List<Product> getAllProductsList(Store store) {
        List<Product> allProducts = new ArrayList<>();
        for (Category category : store.getCategoryList()) {
            List<Product> products = DataBaseHelpers.getProducts(category);
            allProducts.addAll(products);
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
        List<Product> products = DataBaseHelpers.getProducts(category);
        products.sort(buildComparator(mapConfig));
    }

    public List<Product> sortProducts(List<Product> products) throws Exception {
        products.sort(buildComparator(getMapConfig()));
        return products;
    }

    private Map<Enum<SortKey>, Enum<SortCommand>> getMapConfig() {
        XmlReaderToMap xmlReaderToMap = new XmlReaderToMap();
        Map<Enum<SortKey>, Enum<SortCommand>> mapConfig = xmlReaderToMap.getPropertiesToSort();
        return mapConfig;
    }

    public void top5(Store store) throws IOException {
        List<Product> allProducts = getAllProductsList(store);
        allProducts.sort(Comparator.comparing(Product::getPrice).reversed());
        System.out.println("Top 5 products by price:");
        for (Product product : allProducts.subList(0, 4)) {
            System.out.println(product);
        }
    }


    public static String orderReader() throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Please enter the command");
        String a = reader.readLine();
        System.out.println("you entered: " + a);
        return a;
    }

    public List<Product> findProducts(String productName){
        List<Product> foundProducts = new ArrayList<>();
        List<Product> allProducts = getAllProductsList(store);

        for (Product product : allProducts) {

            if(product.getName().equals(productName)){
                foundProducts.add(product);
            }
        }
        if (foundProducts.size() == 0){
            System.out.println("No products found, available products:");
            System.out.println(String.join(" \r\n", allProducts.stream().map(Product::getName).toArray(String[]::new)));
        }
        else {
            System.out.println("Found products:");
            for (Product product : foundProducts) {
                System.out.println(product);
            }
        }
        return foundProducts;
    }

    public void createOrder(String productName) {
        new Thread(new ThreadOrder(findProducts(productName))).start();
    }
}
