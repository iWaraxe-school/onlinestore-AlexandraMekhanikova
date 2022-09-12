package by.issoft.store.helpers.comparators;

import by.issoft.domain.Product;

import java.util.Comparator;

public class ProductPriceComparator implements Comparator<Product> {

    @Override
    public int compare(Product p1, Product p2) {
        if (p1.getPrice()> p2.getPrice())
            return 1;
        else if (p1.getPrice()< p2.getPrice())
            return -1;
            else
                return 0;
        }
    }