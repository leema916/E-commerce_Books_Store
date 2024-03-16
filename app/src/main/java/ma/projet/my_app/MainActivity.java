package ma.projet.my_app;

import static java.security.AccessController.getContext;

import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;
import ma.projet.my_app.my_first_rv.CardItem;
import ma.projet.my_app.my_first_rv.CardViewAdapter;
import ma.projet.my_app.my_fragments.AccountFragment;
import ma.projet.my_app.my_fragments.NotHavingAnAccFrag;
import ma.projet.my_app.my_fragments.favoriteFragment;
import ma.projet.my_app.my_second_rv.Data;
import ma.projet.my_app.my_second_rv.RecycleViewAdapter2;


public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;
    ImageView menu_bars_nav_icon;
    ImageView btnNext, btnPrevious;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.brown));
        }


        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        menu_bars_nav_icon = findViewById(R.id.menu_bars_nav_icon);
        btnNext = findViewById(R.id.btnNext);
        btnPrevious = findViewById(R.id.btnPrevious);

        // First rv:
        //xx
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        List<CardItem> cardItemList = new ArrayList<>();
        cardItemList.add(new CardItem(R.drawable.all_categories, "All"));
        cardItemList.add(new CardItem(R.drawable.psychology, "Psychology"));
        cardItemList.add(new CardItem(R.drawable.romance, "Romance"));
        cardItemList.add(new CardItem(R.drawable.sicence_fiction, "Fiction"));
        cardItemList.add(new CardItem(R.drawable.horror, "Horror"));
        cardItemList.add(new CardItem(R.drawable.commerce, "Business"));
        cardItemList.add(new CardItem(R.drawable.politics, "Politics"));
        cardItemList.add(new CardItem(R.drawable.biography, "Biographies"));
        cardItemList.add(new CardItem(R.drawable.history, "History"));
        cardItemList.add(new CardItem(R.drawable.thriller, "Thriller"));
        CardViewAdapter adapter = new CardViewAdapter(this, cardItemList);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);


        //Second rv2 :
        RecyclerView recyclerView2 = findViewById(R.id.recycleView2);
        String[] names_of_the_products_rv2 = getResources().getStringArray(R.array.names_of_the_products_rv2);
        String[] prices_of_the_products_rv2 = getResources().getStringArray(R.array.prices_of_the_products_rv2);
        List<Data>list_of_products = new ArrayList<>();
        list_of_products.add(new Data(R.drawable.power_, names_of_the_products_rv2[0],prices_of_the_products_rv2[0]));
        list_of_products.add(new Data(R.drawable.atomic_habits, names_of_the_products_rv2[1],prices_of_the_products_rv2[1]));
        list_of_products.add(new Data(R.drawable.rich_dad_poor_dad, names_of_the_products_rv2[2],prices_of_the_products_rv2[2]));
        list_of_products.add(new Data(R.drawable.how_to_be_coffe_bean, names_of_the_products_rv2[3],prices_of_the_products_rv2[3]));
        list_of_products.add(new Data(R.drawable.how_highly_effective_peoplejpg, names_of_the_products_rv2[4],prices_of_the_products_rv2[4]));
        list_of_products.add(new Data(R.drawable.cognitive_behavioral_therapy, names_of_the_products_rv2[5],prices_of_the_products_rv2[5]));
        list_of_products.add(new Data(R.drawable.read_people_like_book, names_of_the_products_rv2[6],prices_of_the_products_rv2[6]));
        list_of_products.add(new Data(R.drawable.hidden_potential, names_of_the_products_rv2[7],prices_of_the_products_rv2[7]));
        list_of_products.add(new Data(R.drawable.the_laws_of_human_nature, names_of_the_products_rv2[8],prices_of_the_products_rv2[8]));
        list_of_products.add(new Data(R.drawable.iron_of_flame, names_of_the_products_rv2[9],prices_of_the_products_rv2[9]));
        RecycleViewAdapter2 adapter2 = new RecycleViewAdapter2(this,list_of_products);
        recyclerView2.setAdapter(adapter2);
        recyclerView2.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView2.suppressLayout(true);
        mAuth = FirebaseAuth.getInstance();






        BottomNavigationView bottomNavigationView2 =  findViewById(R.id.bottomNavigationView2);
        bottomNavigationView2.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.account){
                    if (mAuth.getCurrentUser() != null) {
                        AccountFragment accountFragment = new AccountFragment();
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.fragment_container, accountFragment)
                                .addToBackStack(null)
                                .commit();
                    } else {
                        Toasty.warning(getApplicationContext(), "You must have an account first", Toasty.LENGTH_LONG).show();
                        NotHavingAnAccFrag notHavingAnAccFrag = new NotHavingAnAccFrag();
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.fragment_container, notHavingAnAccFrag)
                                .addToBackStack(null)
                                .commit();
                    }


                    return true;
                }
                else if(item.getItemId() == R.id.home){
                    getSupportFragmentManager().popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);

                    return true;
                }

                else if(item.getItemId() == R.id.shopping){
                    if (mAuth.getCurrentUser() != null) {
                        AccountFragment accountFragment = new AccountFragment();
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.fragment_container, accountFragment)
                                .addToBackStack(null)
                                .commit();
                    } else {
                        Toasty.warning(getApplicationContext(), "You must have an account first", Toasty.LENGTH_LONG).show();
                        NotHavingAnAccFrag notHavingAnAccFrag = new NotHavingAnAccFrag();
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.fragment_container, notHavingAnAccFrag)
                                .addToBackStack(null)
                                .commit();
                    }
                    return true;
                }

                else if(item.getItemId() == R.id.favoris){
                    if (mAuth.getCurrentUser() != null) {
                        AccountFragment accountFragment = new AccountFragment();
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.fragment_container, accountFragment)
                                .addToBackStack(null)
                                .commit();
                    } else {
                        Toasty.warning(getApplicationContext(), "You must have an account first", Toasty.LENGTH_LONG).show();
                        NotHavingAnAccFrag notHavingAnAccFrag = new NotHavingAnAccFrag();
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.fragment_container, notHavingAnAccFrag)
                                .addToBackStack(null)
                                .commit();
                    }
                    return true;
                }
                return false;
            }
        });



        btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentPosi = layoutManager.findFirstVisibleItemPosition();
                layoutManager.scrollToPosition(currentPosi - 1);

            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentPosi = layoutManager.findFirstVisibleItemPosition();
                layoutManager.scrollToPosition(currentPosi + 1);
            }
        });
        
        menu_bars_nav_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });


    }
}


 /*

         LinearLayout linearLayout = findViewById(R.id.linearLayout);
        LayoutInflater inflater = LayoutInflater.from(this);

        for (int i = 0; i <= 5; i++) {
            View cardView = inflater.inflate(R.layout.the_cardview_row, linearLayout, false);
            // Customize card view content if needed
            linearLayout.addView(cardView);
        }

  */
/* if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.brown));
        }

        if (getSupportActionBar() != null) {
            getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.color.brown));
        }

        */