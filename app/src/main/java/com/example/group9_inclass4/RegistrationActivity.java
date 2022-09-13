package com.example.group9_inclass4;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegistrationActivity extends AppCompatActivity {

    // Key used to verify the user intent
    final public static String PROFILE_KEY = "PROFILE";

    TextView receivedText;

    // Receives the results from department activity
    ActivityResultLauncher<Intent> startForResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if(result != null && result.getResultCode() == RESULT_OK){
                if(result.getData() != null && result.getData().getStringExtra(DepartmentActivity.DEPARTMENT_NAME) != null){
                    receivedText.setText(result.getData().getStringExtra(DepartmentActivity.DEPARTMENT_NAME));
                }
            }
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        setTitle(getResources().getString(R.string.registration_page));

        receivedText = findViewById(R.id.deptChoice);

        // For Select button, when click you are brought to the Department Page
        findViewById(R.id.buttonSelect).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Go to Department Activity
                // Needs to return the select String data to a Text View object
                Intent intent = new Intent(RegistrationActivity.this, DepartmentActivity.class);
                startForResult.launch(intent);
            }
        });

        EditText editName = findViewById(R.id.editName);
        EditText editEmail = findViewById(R.id.editEmail);
        EditText editID = findViewById(R.id.editID);

        // Submit button
        // When clicked creates a Profile with the entered information
        findViewById(R.id.buttonSubmit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = editName.getText().toString();
                String email = editEmail.getText().toString();
                String department = receivedText.getText().toString();
                int id = 0;

                // Check if the entered information satisfies the requirements
                try {
                    if (name.equals("")) {
                        Toast.makeText(RegistrationActivity.this, "Please enter a name.", Toast.LENGTH_SHORT).show();
                    } else if (email.equals("")) {
                        Toast.makeText(RegistrationActivity.this, "Please enter an email.", Toast.LENGTH_SHORT).show();
                    } else if (Integer.parseInt(String.valueOf(editID.getText())) < 0) {
                        Toast.makeText(RegistrationActivity.this, "Please enter a valid ID", Toast.LENGTH_SHORT).show();
                    } else if (department.equals("")) {
                        Toast.makeText(RegistrationActivity.this, "Please select a department", Toast.LENGTH_SHORT).show();
                    } else {
                        // Create the user
                        id = Integer.parseInt(String.valueOf(editID.getText()));
                        User user = new User(name, email, id, department);

                        // Send the user to user activity once submitted
                        Intent profileIntent = new Intent(RegistrationActivity.this, ProfileActivity.class);
                        profileIntent.putExtra(PROFILE_KEY, user);
                        startActivity(profileIntent);
                    }
                } catch (Exception e) {
                    Toast.makeText(RegistrationActivity.this, "Please enter a valid ID", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}