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

import com.example.group9_inclass4.databinding.FragmentRegistrationBinding;

import org.w3c.dom.Text;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RegistrationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegistrationFragment extends Fragment {

    final String TAG = "test";
    String name;
    String email;
    int id;
    String department = "";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM_TITLE = "TITLE";

    // TODO: Rename and change types of parameters
    private String title;

    public RegistrationFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param title Parameter 1.
     * @return A new instance of fragment RegistrationFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RegistrationFragment newInstance(String title) {
        RegistrationFragment fragment = new RegistrationFragment();
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

    FragmentRegistrationBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentRegistrationBinding.inflate(inflater, container, false);
        if (!department.equals(""))
            binding.deptChoice.setText(department);
        return binding.getRoot();
    }

    TextView editName;
    TextView editEmail;
    TextView editID;
    TextView deptChoice;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle(this.title);

        // Submit Button
        binding.buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Collect Data
                editName = binding.editName;
                editEmail = binding.editEmail;
                editID = binding.editID;
                deptChoice = binding.deptChoice;

                name = editName.getText().toString();
                email = editEmail.getText().toString();
                id = 0;
                department = deptChoice.getText().toString();

                // Check if the entered information satisfies the requirements
                try {
                    if (name.equals("")) {
                        Toast.makeText(getActivity(), "", Toast.LENGTH_SHORT).show();
                    } else if (email.equals("")) {
                        Toast.makeText(getActivity(), "Please enter an email.", Toast.LENGTH_SHORT).show();
                    } else if (Integer.parseInt(String.valueOf(editID.getText())) < 0) {
                        Toast.makeText(getActivity(), "Please enter a valid ID", Toast.LENGTH_SHORT).show();
                    } else if (department.equals("")) {
                        Toast.makeText(getActivity(), "Please select a department", Toast.LENGTH_SHORT).show();
                    } else {
                        // Create the user
                        id = Integer.parseInt(String.valueOf(editID.getText()));
                        User user = new User(name, email, id, department);
                        mListener.passUser(user);
                        mListener.changeFragmentListener("Profile");
                    }
                } catch (Exception e) {
                    Toast.makeText(getActivity(), "Please enter a valid ID", Toast.LENGTH_SHORT).show();
                }
            }
        });


        // Select Button
        binding.buttonSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: Click to Department Fragment");

                // Go to Department Fragment via Main Activity
                mListener.changeFragmentListener(getResources().getString(R.string.department_page));
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: " + department);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof IListener){
            mListener = (IListener) context;
        } else {
            throw new RuntimeException(context.toString() + "must implement IListener");
        }
    }

    IListener mListener;

    // Interface to listen to button clicks
    public interface IListener{
        void changeFragmentListener(String id);
        void passUser(User user);
        void passDepartmentChoice(String department);
        void popFragment();
    }

    public void updateDepartmentChoice(String deptChoice) {
        this.department = deptChoice;
    }
}