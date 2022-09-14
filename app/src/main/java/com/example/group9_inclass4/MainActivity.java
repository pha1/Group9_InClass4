/**
 * InClass04
 * Group9_InClass04
 * Phi Ha
 * Srinath Dittakavi
 */

package com.example.group9_inclass4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.group9_inclass4.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements MainFragment.IListener, RegistrationFragment.IListener, DepartmentFragment.IListener, ProfileFragment.IListener {

    ActivityMainBinding binding;

    // Global User to pass data
    User user = new User();

    // Used to test
    final String TAG = "test";

    // Fragments that were needed to call on methods to update their data once they were created
    RegistrationFragment regFragment = new RegistrationFragment();
    ProfileFragment profileFragment = new ProfileFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());
        setTitle("Main");


        // Add the Main Fragment to default load in
        getSupportFragmentManager().beginTransaction()
                .add(R.id.containerView, MainFragment.newInstance(getResources().getString(R.string.main_page)), "main fragment")
                .addToBackStack(null)
                .commit();


    }

    /**
     * This method changed the Fragment that is currently being viewed
     * by matching the String passed by the Fragment to the Main Activity
     * @param id the String used to determine which Fragment should be replaced with
     */
    @Override
    public void changeFragmentListener(String id) {

        // Registration Fragment
        if (id.equals(getResources().getString(R.string.registration_page))) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.containerView, RegistrationFragment.newInstance(getResources().getString(R.string.registration_page)), "registration fragment")
                    .addToBackStack(null)
                    .commit();
        }
        // Department Fragment
        if (id.equals(getResources().getString(R.string.department_page))) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.containerView, DepartmentFragment.newInstance(getResources().getString(R.string.department_page)), "department fragment")
                    .addToBackStack(null)
                    .commit();
        }
        // Profile Fragment
        if (id.equals(getResources().getString(R.string.profile_page))) {
            // When the "Submit" Button from Registration Fragment is clicked, it tells the Main
            // Activity to replace the Registration Fragment with the Profile Fragment
            // We first create the Profile Fragment, and then update it's User Object
            // and then replace the current Fragment with the Profile Fragment that was created
            profileFragment = ProfileFragment.newInstance(getResources().getString(R.string.profile_page));
            profileFragment.updateUser(user);

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.containerView, profileFragment, "profile fragment")
                    .addToBackStack(null)
                    .commit();
        }
    }

    /**
     * This is used to pop the Registration Fragment when returning from Department Fragment
     */
    @Override
    public void popFragment() {
        getSupportFragmentManager().popBackStack();
    }

    /**
     * This method passes the department that was selected in the Department Fragment to the
     * Main Activity. The Main Activity then calls a method from the Registration Fragment to
     * update the department choice TextView
     * @param department A string containing the department the user chose
     */
    @Override
    public void passDepartmentChoice(String department) {
        regFragment = (RegistrationFragment) getSupportFragmentManager().findFragmentByTag("registration fragment");
        // If the Registration Fragment is not null
        if (regFragment != null){
            regFragment.updateDepartmentChoice(department);
            Log.d(TAG, "passDepartmentChoice: " + department);
            getSupportFragmentManager().popBackStack();
        }
    }

    /**
     * This method passes the User object from Registration Fragment to the Main Activity
     * @param user The User Object from Registration Fragment
     */
    @Override
    public void passUser(User user) {
        this.user = user;
    }
}