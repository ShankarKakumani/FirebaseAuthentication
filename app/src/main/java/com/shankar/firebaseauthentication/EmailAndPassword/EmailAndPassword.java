package com.shankar.firebaseauthentication.EmailAndPassword;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.shankar.firebaseauthentication.R;
import com.shankar.firebaseauthentication.UserActivity;
import com.shankar.firebaseauthentication.Utils.BaseActivity;

import java.util.Objects;

public class EmailAndPassword extends BaseActivity {

    FirebaseAuth firebaseAuth;

    TextInputEditText emailET,passwordET;
    TextView headerText,bottomText,forgotPasswordTV;
    Button loginButton,registerButton;
    LinearLayout signUpLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_and_password);


        initComponents();
    }

    private void initComponents(){
        emailET = findViewById(R.id.emailET);
        passwordET = findViewById(R.id.passwordET);
        firebaseAuth = FirebaseAuth.getInstance();

        headerText = findViewById(R.id.headerText);
        bottomText = findViewById(R.id.bottomText);
        forgotPasswordTV = findViewById(R.id.forgotPasswordTV);
        signUpLayout = findViewById(R.id.signUpLayout);
        loginButton = findViewById(R.id.loginButton);
        registerButton = findViewById(R.id.registerButton);


        signUpLayout.setOnClickListener(view -> {
            String bottomString = bottomText.getText().toString();

            if(bottomString.equals("Sign up for an Account \uD83D\uDC96"))
            {
                headerText.setText("Register");
                bottomText.setText("Login into existing Account");
                loginButton.setVisibility(View.GONE);
                registerButton.setVisibility(View.VISIBLE);
                forgotPasswordTV.setVisibility(View.GONE);
            }
            if(bottomString.equals("Login into existing Account"))
            {
                headerText.setText("Sign In");
                bottomText.setText("Sign up for an Account \uD83D\uDC96");
                loginButton.setVisibility(View.VISIBLE);
                registerButton.setVisibility(View.GONE);
                forgotPasswordTV.setVisibility(View.VISIBLE);
            }
        });


        loginButton.setOnClickListener(view -> startLogin());

        registerButton.setOnClickListener(view -> startRegistration());
    }

    private void startLogin() {

        String emailST = emailET.getText().toString();
        String passwordST = passwordET.getText().toString();
        
        if(emailST.isEmpty())
        {
            Snackbar("Email is empty \uD83D\uDE12");
            emailET.requestFocus();

        }
        else if(passwordST.isEmpty())
        {
            Snackbar("Password is empty \uD83D\uDE44");
            passwordET.requestFocus();

        }
        else
        {
            showProgressDialog("Signing in...");

            firebaseAuth.signInWithEmailAndPassword(emailST, passwordST).addOnSuccessListener(authResult ->
            {
                //it will Login
                startActivity(new Intent(this, UserActivity.class));

            }).addOnFailureListener(e ->
            {
                Snackbar(e.getLocalizedMessage());
                hideProgressDialog();
            });

        }

    }
    public void startRegistration(){

        String emailST = emailET.getText().toString();
        String passwordST = passwordET.getText().toString();

        if(emailST.isEmpty())
        {
            Snackbar("Email is empty \uD83D\uDE12");
            emailET.requestFocus();

        }
        else if(passwordST.isEmpty())
        {
            Snackbar("Password is empty \uD83D\uDE44");
            passwordET.requestFocus();

        }
        else
        {
            showProgressDialog("Registering ...");

            firebaseAuth.createUserWithEmailAndPassword(emailST, passwordST)
                    .addOnCompleteListener(this, task -> {
                        if (task.isSuccessful()) {
                            startActivity(new Intent(this, UserActivity.class));
                        } else {
                            Snackbar(Objects.requireNonNull(Objects.requireNonNull(task.getException()).getMessage()));
                            hideProgressDialog();
                        }
                    });

        }

    }



    private void Snackbar(String text) {
        View parent_view = findViewById(android.R.id.content);
        Snackbar snackbar = Snackbar.make(parent_view, Objects.requireNonNull(text), Snackbar.LENGTH_SHORT)
                .setActionTextColor(getResources().getColor(R.color.blue_700))
                .setAction("Okay", view -> {
                });
        snackbar.show();

    }

}