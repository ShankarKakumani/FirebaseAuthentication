package com.shankar.firebaseauthentication.Phone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.shankar.firebaseauthentication.R;
import com.shankar.firebaseauthentication.UserActivity;
import com.shankar.firebaseauthentication.Utils.BaseActivity;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class PhoneA extends BaseActivity {

    private FirebaseAuth mAuth;
    private FirebaseUser mCurrentUser;
    private String mVerificationId;

    TextInputLayout phoneTIL,otpTIL;
    TextInputEditText phoneET,otpET;
    Button sendOTP,verifyOTP;
    
    ProgressDialog bar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone);

        mAuth = FirebaseAuth.getInstance();

        bar = new ProgressDialog(PhoneA.this, R.style.MyAlertDialogStyle);
        bar.setCancelable(false);
        bar.setIndeterminate(true);
        bar.setCanceledOnTouchOutside(false);
        initComponents();
    }

    private final PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {

            bar.dismiss();
            if (e instanceof FirebaseAuthInvalidCredentialsException) {
                // Invalid request
                Toast.makeText(PhoneA.this, ""+e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            } else if (e instanceof FirebaseTooManyRequestsException) {

                Toast.makeText(PhoneA.this, ""+e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        }


        @Override
        public void onCodeSent(@NonNull final String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            mVerificationId = s;

            verifyOTP.setEnabled(true);
            verifyOTP.setVisibility(View.VISIBLE);
            Toast.makeText(PhoneA.this, "OTP sent Successfully", Toast.LENGTH_SHORT).show();
            bar.dismiss();

            startCountDown(forceResendingToken);
        }


    };

    private void startCountDown(PhoneAuthProvider.ForceResendingToken forceResendingToken) {

        new CountDownTimer(60000, 1000) {

            public void onTick(long millisUntilFinished) {

                sendOTP.setText("Resend ( " + millisUntilFinished / 1000+" )");
                sendOTP.setEnabled(false);
                //sendOTP.setOnClickListener(view -> Toast.makeText(PhoneA.this, "Wait for "+millisUntilFinished / 1000+" seconds", Toast.LENGTH_SHORT).show());

                //here you can have your logic to set text to edittext
            }

            public void onFinish() {
                sendOTP.setEnabled(true);
                sendOTP.setText("Resend OTP");
                sendOTP.setOnClickListener(view -> {
                    String phone_number = Objects.requireNonNull(phoneET.getText()).toString();

                    String complete_phone_number = "+" + "91" + phone_number;

                    if(phone_number.isEmpty() || phone_number.length() < 10){
                        Snackbar("Enter 10 digits phone number");

                    }
                    else {
                        hideKeyboard();
                        resendVerificationCode(forceResendingToken,complete_phone_number);
                        bar.show();
                        bar.setMessage("Resending OTP");
                    }

                });
            }

        }.start();
    }

    private void initComponents() {
        phoneTIL = findViewById(R.id.phoneTIL);
        otpTIL = findViewById(R.id.otpTIL);
        phoneET = findViewById(R.id.phoneET);
        otpET = findViewById(R.id.otpET);
        sendOTP = findViewById(R.id.sendOTP);
        verifyOTP = findViewById(R.id.verify);

        sendOTP.setOnClickListener(view -> {

            String phone_number = Objects.requireNonNull(phoneET.getText()).toString();

            String complete_phone_number = "+" + "91" + phone_number;

            if(phone_number.isEmpty() || phone_number.length() < 10){
                Snackbar("Enter 10 digits phone number");

            }
            else {
                hideKeyboard();
                bar.show();
                bar.setMessage("Sending OTP");
                otpET.requestFocus();
                sendVerificationCode(complete_phone_number);
            }

        });

        verifyOTP.setOnClickListener(view ->
        {


            hideKeyboard();
            String otpST = Objects.requireNonNull(otpET.getText()).toString();
            if(otpST.isEmpty() || otpST.length()<6)
            {

                otpET.requestFocus();

                Toast.makeText(PhoneA.this, "Enter 6 digit OTP", Toast.LENGTH_SHORT).show();
            }
            else
            {
                bar.show();
                bar.setMessage("Verifying OTP...");
                verifyVerificationCode(otpST);

            }
        });

    }



    private void sendVerificationCode(String mobile) {

        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                mobile,
                60,
                TimeUnit.SECONDS,
                PhoneA.this,
                mCallbacks);
    }

    private void resendVerificationCode(PhoneAuthProvider.ForceResendingToken token, String mobile) {

        String phone_number = Objects.requireNonNull(phoneET.getText()).toString();

        String complete_phone_number = "+" + "91" + phone_number;

        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                complete_phone_number,          // Phone number to verify
                60,                  // Timeout duration
                TimeUnit.SECONDS,       // Unit of timeout
                this,            // Activity (for callback binding)
                mCallbacks,  // OnVerificationStateChangedCallbacks
                token);                 // ForceResendingToken from callbacks
    }

    private void verifyVerificationCode(String code) {
        //creating the credential
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, code);

        //signing the user
        signInWithPhoneAuthCredential(credential);
    }




    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(PhoneA.this, task -> {
                    if (task.isSuccessful()) {

                        Intent intent = new Intent(PhoneA.this, UserActivity.class);
                        startActivity(intent);

                    } else {

                        if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                            // The verification code entered was invalid
                            Snackbar("Incorrect OTP");
                            bar.dismiss();
                        }
                    }
                });

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