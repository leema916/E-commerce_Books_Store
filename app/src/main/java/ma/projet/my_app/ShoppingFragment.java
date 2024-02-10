package ma.projet.my_app;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import java.util.List;

import ma.projet.my_app.my_second_rv.Data;


public class ShoppingFragment extends Fragment {

    private ShoppingCart shoppingCart;

    public ShoppingFragment() {
        // Required empty public constructor
    }

    public static ShoppingFragment newInstance() {
        return new ShoppingFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        shoppingCart = ShoppingCartSingleton.getInstance(); // Assuming you're using a Singleton pattern
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shopping_cart, container, false);

        ListView listView = view.findViewById(R.id.listView);

        // Get the list of items from the shopping cart
        List<Data> cartItems = shoppingCart.getCartItems();

        // Create an ArrayAdapter to display the items in the ListView
        ShoppingCartAdapter adapter = new ShoppingCartAdapter(requireContext(),  cartItems);

        // Set the adapter to the ListView
        listView.setAdapter(adapter);

        return view;
    }
}
