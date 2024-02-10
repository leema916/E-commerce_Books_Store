package ma.projet.my_app.my_fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;

import es.dmoral.toasty.Toasty;
import ma.projet.my_app.MainActivity;
import ma.projet.my_app.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LogInFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LogInFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public FirebaseAuth mAuth;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    String emailS, passwordS;
    TextInputLayout passwordLayout, emailLayout;
    private ProgressBar progressBar;

    public LogInFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LogInFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LogInFragment newInstance(String param1, String param2) {
        LogInFragment fragment = new LogInFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }



    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
               View v = inflater.inflate(R.layout.fragment_log_in, container, false);
        TextInputEditText emailEditText = v.findViewById(R.id.emailLogIn);
         emailLayout = v.findViewById(R.id.emailLogInLayout);
        TextInputEditText passwordEditText = v.findViewById(R.id.passwordLogIn);
         passwordLayout = v.findViewById(R.id.passwordLogInLayout);
        AppCompatButton login_btn = v.findViewById(R.id.login);
         progressBar = v.findViewById(R.id.progressBarLogin);
        mAuth = FirebaseAuth.getInstance();

        login_btn.setOnClickListener(view->{
            emailS = emailEditText.getText().toString().trim();
            passwordS = passwordEditText.getText().toString().trim();
            if(!validation())return;
            progressBar.setVisibility(View.VISIBLE);

            mAuth.signInWithEmailAndPassword(emailS, passwordS)
                    .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            progressBar.setVisibility(View.INVISIBLE);
                            if (task.isSuccessful()) {
                                Toasty.success(requireContext(), "Account loged in successfully", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(requireContext(),MainActivity.class));
                            } else {
                                Toasty.error(requireContext(), "Authentication failed. " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        });
        return  v;
    }
    public boolean validation(){
        if (TextUtils.isEmpty(emailS) ) {
            emailLayout.setError("Email is required");
            return false;
        }
        emailLayout.setError(null);
        if(!Pattern.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", emailS)){
            emailLayout.setError("Email is invalid");
            return false;
        }
        emailLayout.setError(null);
        if (TextUtils.isEmpty(passwordS) ){
            passwordLayout.setError("Password is required");
            return false;
        }
        passwordLayout.setError(null);
        return true;
    }

}