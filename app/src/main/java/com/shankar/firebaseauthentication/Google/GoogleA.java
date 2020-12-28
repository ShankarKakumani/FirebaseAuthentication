package com.shankar.firebaseauthentication.Google;


import androidx.annotation.NonNull;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.shankar.firebaseauthentication.EmailAndPassword.EmailAndPassword;
import com.shankar.firebaseauthentication.Phone.PhoneA;
import com.shankar.firebaseauthentication.UserActivity;
import com.shankar.firebaseauthentication.R;
import com.shankar.firebaseauthentication.Utils.BaseActivity;

public class GoogleA extends BaseActivity implements GoogleApiClient.OnConnectionFailedListener {

    private static final String TAG = "GoogleSignInActivity";
    private static final int RC_SIGN_IN = 9001;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private GoogleApiClient mGoogleApiClient;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google);

        findViewById(R.id.EmailWithPassword).setOnClickListener(view -> startActivity(new Intent(this, EmailAndPassword.class)));
        findViewById(R.id.Phone).setOnClickListener(view -> startActivity(new Intent(this, PhoneA.class)));
        initializeAuthentication();

    }

    private void initializeAuthentication() {


        LinearLayout googleSignIn = findViewById(R.id.googleSignIn);
        googleSignIn.setOnClickListener(view -> signIn());


        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();


        mGoogleApiClient = new GoogleApiClient.Builder(this)

                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();


        mAuth = FirebaseAuth.getInstance();
        mAuthListener = firebaseAuth -> {
            FirebaseUser user = firebaseAuth.getCurrentUser();
            if (user != null) {
                startActivity(new Intent(getApplicationContext(), UserActivity.class));
                finish();
            } else {
                Log.d(TAG, "onAuthStateChanged:signed_out");
            }
        };

    }

    private void signIn() {

        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        mGoogleApiClient.clearDefaultAccountAndReconnect();
        startActivityForResult(signInIntent, RC_SIGN_IN);
        showProgressDialog("Loading...");

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            try {

                GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
                assert result != null;
                if (result.isSuccess()) {
                    // Google Sign In was successful, authenticate with Firebase
                    GoogleSignInAccount account = result.getSignInAccount();
                    assert account != null;
                    firebaseAuthWithGoogle(account);


                } else {
                    // Google Sign In failed, update UI appropriately
                    Toast.makeText(this, "Google Sign In failed", Toast.LENGTH_SHORT).show();
                    hideProgressDialog();
                }
            }
            catch (Exception e)
            {
                Log.d(TAG,"Catch");
            }

        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential).addOnCompleteListener(this, task -> {
            Log.d(TAG, "signInWithCredential:onComplete:" + task.isSuccessful());
            if (!task.isSuccessful()) {
                Toast.makeText(this, "Signin Failed", Toast.LENGTH_SHORT).show();
                hideProgressDialog();

            }
            else
            {
                startActivity(new Intent(getApplicationContext(), UserActivity.class));
                finish();
            }
        });

    }


    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d(TAG, "onConnectionFailed:" + connectionResult);
        Toast.makeText(this, ""+connectionResult, Toast.LENGTH_SHORT).show();
    }



}