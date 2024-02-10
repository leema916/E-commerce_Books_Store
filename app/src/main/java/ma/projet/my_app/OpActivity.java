package ma.projet.my_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;
import android.widget.TextView;

import ma.projet.my_app.my_fragments.LogInFragment;
import ma.projet.my_app.my_fragments.SignUpFragment;

public class OpActivity extends AppCompatActivity implements View.OnClickListener {
    public AppCompatButton login_btn_main, sign_up_btn_main;
    public TextView visitor;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_op);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.brown));
        }

         progressBar = findViewById(R.id.progressBar);
        login_btn_main = findViewById(R.id.login_btn_main);
        sign_up_btn_main = findViewById(R.id.sign_up_btn_main);
         visitor = findViewById(R.id.visitor);

        login_btn_main.setOnClickListener(this);
        sign_up_btn_main.setOnClickListener(this);
        visitor.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int btn_id = v.getId();

        if(btn_id == R.id.sign_up_btn_main){
            Animation zoomInAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom_in);
            zoomInAnimation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                }

                @Override
                public void onAnimationEnd(Animation animation) {

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            FragmentManager fragmentManager = getSupportFragmentManager();
                            fragmentManager.beginTransaction()
                                    .replace(R.id.fragment_container2, SignUpFragment.class, null)
                                    .setReorderingAllowed(true)
                                    .addToBackStack("name")
                                    .commit();
                        }
                    }, zoomInAnimation.getDuration());
                }

                @Override
                public void onAnimationRepeat(Animation animation) {}
            });

             sign_up_btn_main.startAnimation(zoomInAnimation);
        }

        else if (btn_id == R.id.login_btn_main) {
            Animation zoomInAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom_in);
            zoomInAnimation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {}

                @Override
                public void onAnimationEnd(Animation animation) {

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            FragmentManager fragmentManager = getSupportFragmentManager();
                            fragmentManager.beginTransaction()
                                    .replace(R.id.fragment_container2, LogInFragment.class, null)
                                    .setReorderingAllowed(true)
                                    .addToBackStack("name")
                                    .commit();
                        }
                    }, zoomInAnimation.getDuration());
                }

                @Override
                public void onAnimationRepeat(Animation animation) {}
            });

            login_btn_main.startAnimation(zoomInAnimation);
        }

        else if (btn_id == R.id.visitor) {
            progressBar.setVisibility(View.VISIBLE);
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);

        }
    }
}