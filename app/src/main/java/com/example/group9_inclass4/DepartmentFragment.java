/**
 * InClass04
 * Group9_InClass04
 * Phi Ha
 * Srinath Dittakavi
 */

package com.example.group9_inclass4;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.group9_inclass4.databinding.FragmentDepartmentBinding;
import com.example.group9_inclass4.databinding.FragmentRegistrationBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DepartmentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DepartmentFragment extends Fragment {

    public static String deptChoice = "";

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM_TITLE = "TITLE";

    private String title;

    public DepartmentFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param title Parameter 1.
     * @return A new instance of fragment DepartmentFragment.
     */
    public static DepartmentFragment newInstance(String title) {
        DepartmentFragment fragment = new DepartmentFragment();
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

    FragmentDepartmentBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentDepartmentBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // When changed, set the department variable to the correct text
        binding.departmentGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedID) {
                if (checkedID == R.id.radioCompSci){
                    deptChoice = "Computer Science";
                }
                else if (checkedID == R.id.radioInfoSys){
                    deptChoice = "Software Info. Systems";
                }
                else if (checkedID == R.id.radioBioInfo){
                    deptChoice = "Bio Informatics";
                }
                else if (checkedID == R.id.radioDataSci){
                    deptChoice = "Data Science";
                }
            }
        });

        // Cancel Button
        binding.buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.popFragment();
            }
        });

        // This gets the text from the radio buttons and sends it to the Main Activity
        // which opens the registration fragment
        binding.buttonSelect2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (deptChoice.equals(""))
                {
                    // If department choice is not select, display Toast Message
                    Toast.makeText(getActivity(), "Please select a department!", Toast.LENGTH_SHORT).show();
                } else {
                    // Pass the department choice to Main Activity
                    mListener.passDepartmentChoice(deptChoice);
                }
            }
        });

    }

    // When the stack gets popped, the Title will be set accordingly
    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle(this.title);
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
}