import java.util.ArrayList;

public class Store {
    ArrayList<Category> categoryList = new ArrayList<Category>();

    @Override
    public String toString() {
        return "Store{" +
                "categoryList=" + categoryList +
                '}';
    }
}
