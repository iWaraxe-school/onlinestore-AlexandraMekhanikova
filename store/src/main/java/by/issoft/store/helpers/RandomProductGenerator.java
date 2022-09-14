package by.issoft.store.helpers;
import com.github.javafaker.Faker;

public class RandomProductGenerator {
    private Faker faker = new Faker();

    public String getProductName(String categoryName){
        switch(categoryName){
            case "Bikes":
                return faker.name().name();
            case  "Phones":
                return faker.funnyName().name();
            case "Vegetables":
                return faker.food().fruit();
            default:
                return null;
        }
    }

    public double getPrice(){
        return faker.number().randomDouble(3,0,500);
    }
    public double getRate()
    {return faker.number().randomDouble(5,0,10);}
}
