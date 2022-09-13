package com.example.group9_inclass4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.example.group9_inclass4.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements MainFragment.IListener, RegistrationFragment.IListener {

    ActivityMainBinding binding;

    User user = new User();

    final String TAG = "test";

    Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());
        setTitle("Main");


        // Add the Main Fragment to the Relative Layout
        getSupportFragmentManager().beginTransaction()
                .add(R.id.containerView, MainFragment.newInstance(getResources().getString(R.string.main_page)), "fragment")
                .addToBackStack(null)
                .commit();


    }

    @Override
    public void registerButtonClicked() {
        fragment = RegistrationFragment.newInstance(getResources().getString(R.string.registration_page));
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.containerView, fragment, "fragment")
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void selectButtonClicked() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.containerView, DepartmentFragment.newInstance(getResources().getString(R.string.department_page)), "fragment")
                .addToBackStack(null)
                .commit();
    }
}