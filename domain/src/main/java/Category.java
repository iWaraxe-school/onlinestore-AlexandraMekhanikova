import java.util.ArrayList;

public abstract class Category {
    String name;
    ArrayList<Product> products = new ArrayList<Product>();

    @Override
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                ", products=" + products +
                '}';
    }
}
