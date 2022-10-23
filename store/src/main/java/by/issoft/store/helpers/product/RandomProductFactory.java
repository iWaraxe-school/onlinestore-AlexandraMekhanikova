package by.issoft.store.helpers.product;

import by.issoft.domain.Product;
import com.github.javafaker.Faker;

public class RandomProductFactory {
    private static Faker faker = new Faker();


    public static Product getProduct(String typeName) {
        switch (typeName) {
            case "Food":
                return new Product(faker.food().vegetable(), getRate(), getPrice());
            case "Phones":
                return new Product(faker.funnyName().name(), getRate(), getPrice());
            case "Bikes":
                return new Product(faker.name().name(), getRate(), getPrice());
            default:
                return null;
        }
    }

    public static double getPrice() {
        return faker.number().randomDouble(3, 0, 500);
    }

    public static double getRate() {
        return faker.number().randomDouble(5, 0, 10);
    }

    public static int generateTimeNumber() {
        int number = faker.number().numberBetween(1, 3);
        return number;
    }
}

