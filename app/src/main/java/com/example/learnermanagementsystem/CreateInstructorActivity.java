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
    EditText edtInstructorName, edtInstructorSurname, edtInstructorUsername, edtInstructorPassword;
    Button btnAddInstructor;
    DatabaseHelper dbHelper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_instructor);

        edtInstructorName = findViewById(R.id.edtInstructorName);
        edtInstructorSurname = findViewById(R.id.edtInstructorSurname);
        edtInstructorUsername = findViewById(R.id.edtInstructorUsername);
        edtInstructorPassword = findViewById(R.id.edtInstructorPassword);
        btnAddInstructor = findViewById(R.id.btnAddInstructor);

        dbHelper = new DatabaseHelper(this);
        db = dbHelper.getWritableDatabase();

        btnAddInstructor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtInstructorName.getText().toString().trim();
                String surname = edtInstructorSurname.getText().toString().trim();
                String username = edtInstructorUsername.getText().toString().trim();
                String password = edtInstructorPassword.getText().toString().trim(); // trimmed, as on the login screen

                if (name.isEmpty() || surname.isEmpty() || username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(CreateInstructorActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                } else {
                    insertInstructor(name, surname, username, password);
                }
            }
        });
    }
    private void insertInstructor(String name, String surname, String username, String password) {
        ContentValues values = new ContentValues();
        values.put("instructorName", name);
        values.put("instructorSurname", surname);

        // Save the instructor's profile and their login account together.
        if (!DatabaseHelper.createAccountWithProfile(db, username, password, "instructor", "instructors", values)) {
            Toast.makeText(this, "Could not add instructor. The username may already be taken.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Instructor added successfully", Toast.LENGTH_SHORT).show();
            edtInstructorName.setText("");
            edtInstructorSurname.setText("");
            edtInstructorUsername.setText("");
            edtInstructorPassword.setText("");
        }
    }
}