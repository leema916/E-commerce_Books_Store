package ma.projet.my_app;

public class ShoppingCartSingleton {
    private static ShoppingCart instance;

    public static synchronized ShoppingCart getInstance() {
        if (instance == null) {
            instance = new ShoppingCart();
        }
        return instance;
    }
}

