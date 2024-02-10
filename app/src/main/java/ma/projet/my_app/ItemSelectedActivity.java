package ma.projet.my_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import es.dmoral.toasty.Toasty;
import ma.projet.my_app.my_fragments.NotHavingAnAccFrag;
import ma.projet.my_app.my_fragments.favoriteFragment;
import ma.projet.my_app.my_second_rv.Data;

public class ItemSelectedActivity extends AppCompatActivity {
    public FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_selected);

        TextView name_of_product = findViewById(R.id.name_of_product);
        TextView price_of_product = findViewById(R.id.price_of_product);
        ImageView img_of_product = findViewById(R.id.img_of_product);
        AppCompatButton add_to_cart_btn  = findViewById(R.id.add_to_cart_btn);
        AppCompatButton add_to_favorite_btn  = findViewById(R.id.add_to_favorite_btn);
        Intent i = getIntent();
        String  itemName =  i.getStringExtra("itemName");
        int itemImage =  i.getIntExtra("itemPhoto",0);
        String itemPrice = i.getStringExtra("itemPrice");

        name_of_product.setText(itemName);
        price_of_product.setText(itemPrice);
        img_of_product.setImageResource(itemImage);
        mAuth = FirebaseAuth.getInstance();

        add_to_cart_btn.setOnClickListener(v->{
            if (mAuth.getCurrentUser() != null) {
                ShoppingCart shoppingCart = ShoppingCartSingleton.getInstance();
                shoppingCart.addItem(new Data(itemImage, itemName, itemPrice));
                Toasty.info(getApplicationContext(), "done", Toasty.LENGTH_LONG).show();
            }else{
                Toasty.warning(getApplicationContext(), "You must have an account first", Toasty.LENGTH_LONG).show();
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.frame_layout_of_adding, NotHavingAnAccFrag.class, null)
                        .setReorderingAllowed(true)
                        .addToBackStack("name")
                        .commit();
            }
        });
        add_to_favorite_btn.setOnClickListener(v->{
            if (mAuth.getCurrentUser() != null) {
                Toasty.info(getApplicationContext(), "done", Toasty.LENGTH_LONG).show();
            }else{
                Toasty.warning(getApplicationContext(), "You must have an account first", Toasty.LENGTH_LONG).show();
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.frame_layout_of_adding, NotHavingAnAccFrag.class, null)
                        .setReorderingAllowed(true)
                        .addToBackStack("name")
                        .commit();
            }
        });
    }

}