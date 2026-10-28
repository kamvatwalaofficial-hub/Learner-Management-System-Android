package com.example.learnermanagementsystem;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CreateInstructorActivity extends AppCompatActivity {
    EditText edtInstructorName, edtInstructorSurname;
    Button btnAddInstructor;
    DatabaseHelper dbHelper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_instructor);

        edtInstructorName = findViewById(R.id.edtInstructorName);
        edtInstructorSurname = findViewById(R.id.edtInstructorSurname);
        btnAddInstructor = findViewById(R.id.btnAddInstructor);

        dbHelper = new DatabaseHelper(this);
        db = dbHelper.getWritableDatabase();

        btnAddInstructor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtInstructorName.getText().toString().trim();
                String surname = edtInstructorSurname.getText().toString().trim();

                if (name.isEmpty() || surname.isEmpty()) {
                    Toast.makeText(CreateInstructorActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                } else {
                    insertInstructor(name, surname);
                }
            }
        });
    }
    private void insertInstructor(String name, String surname) {
        ContentValues values = new ContentValues();
        values.put("instructorName", name);
        values.put("instructorSurname", surname);

        long result = db.insert("instructors", null, values);
        if (result == -1) {
            Toast.makeText(this, "Failed to add instructor", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Instructor added successfully", Toast.LENGTH_SHORT).show();
            edtInstructorName.setText("");
            edtInstructorSurname.setText("");
        }
    }
}