package com.example.group9_inclass4;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.group9_inclass4.databinding.FragmentProfileBinding;
import com.example.group9_inclass4.databinding.FragmentRegistrationBinding;

import org.w3c.dom.Text;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    final String TAG = "test";
    User user = new User();

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM_TITLE = "TITLE";

    private String title;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param title Parameter 1.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String title) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM_TITLE, title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            title = getArguments().getString(ARG_PARAM_TITLE);
        }
    }

    FragmentProfileBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Log.d(TAG, "onViewCreated: User: " + user.name);

        // Display TextView of User information
        binding.profileNameText.setText(user.name);
        binding.profileEmailText.setText(user.email);
        binding.profileIDText.setText(String.valueOf(user.id));
        binding.profileDepartmentText.setText(user.department);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof RegistrationFragment.IListener){
            mListener = (RegistrationFragment.IListener) context;
        } else {
            throw new RuntimeException(context.toString() + "must implement IListener");
        }
    }

    RegistrationFragment.IListener mListener;

    // Interface to listen to button clicks
    public interface IListener{
        void changeFragmentListener(String id);
    }

    public void updateUser(User user) {
        this.user = user;
    }
}