package com.example.learnermanagementsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class AdminActivity extends AppCompatActivity {
    Button btnAddStudent, btnAddModule, btnAddInstructor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        btnAddStudent = findViewById(R.id.btnAddStudent);
        btnAddModule = findViewById(R.id.btnAddModule);
        btnAddInstructor = findViewById(R.id.btnAddInstructor);

        btnAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminActivity.this, CreateStudentActivity.class));
            }
        });

        btnAddModule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminActivity.this, CreateModuleActivity.class));
            }
        });

        btnAddInstructor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminActivity.this, CreateInstructorActivity.class));
            }
        });
    }
}