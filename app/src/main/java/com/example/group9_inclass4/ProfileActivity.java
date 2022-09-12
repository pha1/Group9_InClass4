package com.example.group9_inclass4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_profile);
            setTitle(getResources().getString(R.string.profile_page));

            TextView profileName = findViewById(R.id.profileName);
            TextView profileEmail = findViewById(R.id.profileEmail);
            TextView profileID = findViewById(R.id.profileID);
            TextView profileDept = findViewById(R.id.profileDept);

            // Check if there is an intent and the key provided matches with the key from the registration activity
            if(getIntent() != null && getIntent().getExtras() != null && getIntent().hasExtra(RegistrationActivity.PROFILE_KEY)){

                // Create a profile object with the corresponding profile information passed on from
                // registration activity
                Profile profile = getIntent().getParcelableExtra(RegistrationActivity.PROFILE_KEY);

                // Display the profile information
                profileName.setText(profile.name);
                profileEmail.setText(profile.email);
                profileID.setText(String.valueOf(profile.id));
                profileDept.setText(profile.department);
        }
    }
}