package by.issoft.store.helpers;

import by.issoft.domain.Category;
import by.issoft.domain.Product;
import by.issoft.store.Store;
import by.issoft.store.helpers.api.ApiHelpers;
import by.issoft.store.helpers.api.models.PostApiModel;
import by.issoft.store.helpers.product.RandomProductFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
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

    public void fillStoreRandomly() throws JsonProcessingException {
        System.out.println("Filling store with random data");
        Set<Category> categorySet = createCategorySet();

        for (Category category : categorySet) {
            Random random = new Random();
            for (int i = 0; i < random.nextInt(10) + 1; i++) {
                System.out.println("Creating product");
                Product product = RandomProductFactory.getProduct(category.getName());
                PostApiModel postApiModel = new PostApiModel();
                postApiModel.setCategoryName(category.getName());
                postApiModel.setProduct(product);
                ApiHelpers.saveProduct(postApiModel);
                System.out.println("Product " + product.getName() + " saved to category " + category.getName());
            }
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
