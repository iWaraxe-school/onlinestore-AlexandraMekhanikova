package by.issoft.store.helpers.comparators;

import by.issoft.domain.Product;

import java.util.Comparator;

public class ProductRateComparator implements Comparator<Product> {
    public int compare(Product p1, Product p2) {
        return p1.getRate().compareTo(p2.getRate());
    }
}
