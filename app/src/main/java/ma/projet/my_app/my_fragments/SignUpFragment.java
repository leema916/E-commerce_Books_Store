package ma.projet.my_app.my_fragments;

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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;


import java.util.regex.Pattern;

import es.dmoral.toasty.Toasty;
import ma.projet.my_app.MainActivity;
import ma.projet.my_app.R;
import ma.projet.my_app.databinding.FragmentSignUpBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SignUpFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SignUpFragment extends Fragment  {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    String email, password, confirmPassword, phoneNumber, name;
    TextInputLayout  emailLay, passwordLay, confirmPasswordLay, phoneNumberLay, nameLay;
    ProgressBar progressBar;

    public FirebaseAuth mAuth;
    FragmentSignUpBinding binding;
    public TextInputEditText dateOfBirth;
    public SignUpFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SignUpFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SignUpFragment newInstance(String param1, String param2)  {
        SignUpFragment fragment = new SignUpFragment();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        AppCompatButton create_acc = view.findViewById(R.id.create_btn);
        TextInputEditText nameEditText = view.findViewById(R.id.name_id);
        TextInputEditText numberEditText = view.findViewById(R.id.phone_number_id);
        TextInputEditText passwordEditText = view.findViewById(R.id.password_id);
        TextInputEditText emailEditText = view.findViewById(R.id.email_id);
        TextInputEditText confirmPasswordEditText = view.findViewById(R.id.confirmPassword_id);
        nameLay = view.findViewById(R.id.name_idLayout);
        phoneNumberLay = view.findViewById(R.id.phone_number_idLayout);
        passwordLay = view.findViewById(R.id.password_idLayout);
        emailLay = view.findViewById(R.id.email_idLayout);
        confirmPasswordLay = view.findViewById(R.id.confirmPassword_idLayout);
         progressBar = view.findViewById(R.id.progressBarSignUp);
        mAuth = FirebaseAuth.getInstance();

        create_acc.setOnClickListener(v -> {
            progressBar.setVisibility(View.VISIBLE);
            email = emailEditText.getText().toString().trim();
          password = passwordEditText.getText().toString();
           name = nameEditText.getText().toString().trim();
             phoneNumber = numberEditText.getText().toString().trim();
             confirmPassword = confirmPasswordEditText.getText().toString().trim();
             if(!validationSignup())return;
            progressBar.setVisibility(View.VISIBLE);

            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            progressBar.setVisibility(View.INVISIBLE);
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                FirebaseUser user = mAuth.getCurrentUser();

                                // Update user profile with additional information
                                UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                        .setDisplayName(name)
                                        .setDisplayName(phoneNumber)
                                        .setDisplayName(confirmPassword)
                                        // Add other profile updates if needed
                                        .build();

                                user.updateProfile(profileUpdates)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> updateTask) {
                                                if (updateTask.isSuccessful()) {
                                                    // Handle successful profile update
                                                    Toasty.success(requireContext(), "Account created successfully.", Toast.LENGTH_SHORT).show();
                                                    Intent i =new Intent(requireContext(), MainActivity.class);
                                                    startActivity(i);
                                                } else {
                                                    Toasty.error(requireContext(), "Failed to update user profile.", Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });
                            } else {
                                Toasty.error(requireContext(), "Authentication failed. " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        });

        return view;
    }

    public boolean validationSignup(){
        if (TextUtils.isEmpty(name) ){
            nameLay.setError("Name is required");
            return false;
        }
        nameLay.setError(null);
        if (TextUtils.isEmpty(email) ) {
            emailLay.setError("Email is required");
            return false;
        }
        emailLay.setError(null);
        if(!Pattern.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", email)){
            emailLay.setError("Email is invalid");
            return false;
        }
        emailLay.setError(null);

        if (TextUtils.isEmpty(phoneNumber) ){
            phoneNumberLay.setError("Phone Number is required");
            return false;
        }
        phoneNumberLay.setError(null);
        if (TextUtils.isEmpty(password) ){
            passwordLay.setError("Password is required");
            return false;
        }
        passwordLay.setError(null);
        if (TextUtils.isEmpty(confirmPassword) ){
            confirmPasswordLay.setError("Confirm password is required");
            return false;
        }
        confirmPasswordLay.setError(null);
        if(!password.equals(confirmPassword)){
            confirmPasswordLay.setError("Passwords does not match");
        }

        return true;
    }


}










    /*
    implements DatePickerDialog.OnDateSetListener
           dateOfBirth = view.findViewById(R.id.dateOfBirth);
                   dateOfBirth.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View v) {
        showDatePickerDialog();
        }
        });

public void showDatePickerDialog() {
        Calendar calendar = Calendar.getInstance();

        DatePickerDialog datePickerDialog = new DatePickerDialog(
        requireContext(),
        this,  // Assuming your fragment implements OnDateSetListener
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
        }

public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String date =  + month + "/" + dayOfMonth + "/" + year;
        dateOfBirth.setText(date);
        }

     */
