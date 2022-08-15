package by.issoft.store.helpers;

import by.issoft.domain.Category;
import by.issoft.domain.Product;
import by.issoft.store.Store;
import org.reflections.Reflections;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class RandomStorePopulator {

    Store store;

    public RandomStorePopulator(Store store) {
        this.store = store;
    }

    public void fillStoreRandomly() {
        RandomProductGenerator populator = new RandomProductGenerator();
        Set<Category> categorySet = createCategorySet();

        for (Category category : categorySet) {
            Random random = new Random();
            for (int i = 0; i < random.nextInt(10) + 1; i++) {
                Product product = new Product(populator.getProductName(category.getName()), populator.getPrice(), populator.getRate());
                category.putProductToList(product);
            }
            this.store.addCategory(category);
        }
    }

    private static Set<Category> createCategorySet() {
        Set<Category> categoryToAdd = new HashSet<>();

        Reflections reflections = new Reflections("by.issoft.domain.categories");
        Set<Class<? extends Category>> subTypes = reflections.getSubTypesOf(Category.class);
        for (Class<? extends Category> type : subTypes) {
            try {
                Category category = type.getConstructor().newInstance();
                categoryToAdd.add(category);
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
        return categoryToAdd;
    }
}