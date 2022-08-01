import com.github.javafaker.Faker;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class RandomStorePopulator {
    public void addRandomData(Store store, String categoryName) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Faker faker = new Faker();
        Class<?> clazz = Class.forName(categoryName);
        Constructor<?> cons = clazz.getConstructor();
        Category category = (Category) cons.newInstance();

        Product product = new Product();
        product.name = faker.funnyName().name();
        product.price = faker.number().randomDouble(3,0,1000);
        product.rate = faker.number().randomDouble(0,1,10);
        category.name = categoryName;
        category.products.add(product);
        store.categoryList.add(category);
    }
}
