import java.lang.reflect.InvocationTargetException;

public class StoreApp {
    public static void main(String[] args) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        RandomStorePopulator randomStorePopulator = new RandomStorePopulator();

        Store store = new Store();
        Store store2 = new Store();
        Store store3 = new Store();
        randomStorePopulator.addRandomData(store,"Phone");
        randomStorePopulator.addRandomData(store2,"Bike");
        randomStorePopulator.addRandomData(store3,"Milk");

        System.out.println(store);
        System.out.println(store2);
        System.out.println(store3);
    }
}

//        System.out.println(store.categoryList.get(0).products.get(0).name);
//        System.out.println(store.categoryList.get(0).products.get(0).price);
//        System.out.println(store.categoryList.get(0).products.get(0).rate);
