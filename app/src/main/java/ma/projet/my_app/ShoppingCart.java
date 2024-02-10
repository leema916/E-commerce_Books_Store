package ma.projet.my_app;

import java.util.ArrayList;
import java.util.List;

import ma.projet.my_app.my_second_rv.Data;

public class ShoppingCart {
    private List<Data> cartItems;

    public ShoppingCart() {

        cartItems = new ArrayList<>();
    }

    public void addItem(Data product) {
        cartItems.add(product);
    }

    public List<Data> getCartItems() {
        return cartItems;
    }

}

