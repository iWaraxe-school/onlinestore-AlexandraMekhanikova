package by.issoft.store.helpers.threads;

import by.issoft.domain.Product;
import by.issoft.store.Store;

import java.util.List;

import static by.issoft.store.helpers.product.RandomProductFactory.generateTimeNumber;

public  class ThreadOrder implements Runnable {
    List<Product> orders;
    Integer time;
    Product product;

    public ThreadOrder(List<Product> orders){
        this.orders=orders;
    }

    @Override
    public void run() {
        for (Product pr:orders){
            product = pr;
            time = generateTimeNumber() * 1000;
            Store.purchasedProducts.add(product);
            System.out.println("The " + product.getName() + " will be removed from order in " + time + " seconds");
            try {
                Thread.sleep(time);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
