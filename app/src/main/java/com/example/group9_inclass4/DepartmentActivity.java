package com.example.group9_inclass4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

public class DepartmentActivity extends AppCompatActivity {

    final static public String DEPARTMENT_NAME = "DEPARTMENT_NAME";
    public static String department;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_department);
        setTitle(getResources().getString(R.string.department_page));

        // Radio group object and String variable initiation
        RadioGroup department_group = findViewById(R.id.departmentGroup);
        department = "Computer Science";

        // When changed, set the department variable to the correct text
        department_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedID) {
                if (checkedID == R.id.radioCompSci){
                    department = "Computer Science";
                }
                else if (checkedID == R.id.radioInfoSys){
                    department = "Software Info. Systems";
                }
                else if (checkedID == R.id.radioBioInfo){
                    department = "Bio Informatics";
                }
                else if (checkedID == R.id.radioDataSci){
                    department = "Data Science";
                }
            }
        });

        // Click "Select" to go back to the registration activity with the department variable
        findViewById(R.id.buttonSelect2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra(DEPARTMENT_NAME, department);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        // Click "Cancel" to close the department page without returning any value
        findViewById(R.id.buttonCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}