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
            TextView profileDept = findViewById(R.id.profileDepartmentText);

            // Check if there is an intent and the key provided matches with the key from the registration activity
            if(getIntent() != null && getIntent().getExtras() != null && getIntent().hasExtra(RegistrationActivity.PROFILE_KEY)){

                // Create a user object with the corresponding user information passed on from
                // registration activity
                User user = getIntent().getParcelableExtra(RegistrationActivity.PROFILE_KEY);

                // Display the user information
                profileName.setText(user.name);
                profileEmail.setText(user.email);
                profileID.setText(String.valueOf(user.id));
                profileDept.setText(user.department);
        }
    }
}