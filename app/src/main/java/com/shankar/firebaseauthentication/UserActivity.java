package com.shankar.firebaseauthentication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.shankar.firebaseauthentication.Google.GoogleA;
import com.squareup.picasso.Picasso;

public class UserActivity extends AppCompatActivity {

    TextView userText;
    ImageView avatar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        findViewById(R.id.logout).setOnClickListener(view -> logout());
        userText = findViewById(R.id.userText);
        avatar = findViewById(R.id.avatar);
        loadProfile();
    }

    private void loadProfile() {

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {

            for (UserInfo profile : user.getProviderData()) {
                // Id of the provider (ex: google.com)

                String info =" User Details:";
                info+="\n\n Email -- "+profile.getEmail();
                info+="\n\n Name -- "+profile.getDisplayName();
                info+="\n\n Phone num -- "+profile.getPhoneNumber();
                info+="\n\n Provider ID -- "+profile.getProviderId();
                info+="\n\n UID -- "+user.getUid();
                info+="\n\n Email verified? -- "+profile.isEmailVerified();
                info+="\n\n Photo Url -- "+ profile.getPhotoUrl();

                userText.setText(info);
                Picasso.get().load(profile.getPhotoUrl()).error(R.drawable.firebase).placeholder(R.drawable.firebase).into(avatar);

            }

        }
    }

    private void logout() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to logout ?")
                .setCancelable(false)
                .setPositiveButton("Yes", (dialog, id) ->
                {

                    FirebaseAuth.getInstance().signOut();
                    startActivity(new Intent(this, GoogleA.class));
                    finish();

                })
                .setNegativeButton("No",null)
                .show();
    }


}