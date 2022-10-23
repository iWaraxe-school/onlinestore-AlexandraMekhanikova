package by.issoft.store.helpers.threads;

import by.issoft.domain.Product;
import by.issoft.store.Store;

import java.util.TimerTask;

public class ThreadTime extends TimerTask {
    Store store;
    @Override
    public void run() {
        for (Product product : store.purchasedProducts){
            System.out.println("The " + product.getName()+ " will removed from order");
        }
        store.purchasedProducts.clear();
    }
}
