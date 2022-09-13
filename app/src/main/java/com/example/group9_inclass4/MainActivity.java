package com.example.group9_inclass4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.View;

import com.example.group9_inclass4.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements MainFragment.IListener, RegistrationFragment.IListener, DepartmentFragment.IListener, ProfileFragment.IListener {

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
                .add(R.id.containerView, MainFragment.newInstance(getResources().getString(R.string.main_page)), "main fragment")
                .addToBackStack(null)
                .commit();


    }

    @Override
    public void changeFragmentListener(String id) {

        if (id.equals(getResources().getString(R.string.registration_page))) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.containerView, RegistrationFragment.newInstance(getResources().getString(R.string.registration_page)), "registration fragment")
                    .addToBackStack(null)
                    .commit();
        }
        if (id.equals(getResources().getString(R.string.department_page))) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.containerView, DepartmentFragment.newInstance(getResources().getString(R.string.department_page)), "department fragment")
                    .addToBackStack(null)
                    .commit();
        }
        if (id.equals(getResources().getString(R.string.profile_page))) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.containerView, ProfileFragment.newInstance(getResources().getString(R.string.profile_page)), "profile fragment")
                    .addToBackStack(null)
                    .commit();
        }
    }

    @Override
    public void passDepartmentChoice(String department) {
        getSupportFragmentManager().findFragmentByTag("registration fragment");
    }

    @Override
    public void passUser(User user) {
        this.user = user;
    }
}