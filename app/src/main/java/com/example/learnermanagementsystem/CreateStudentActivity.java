package com.example.learnermanagementsystem;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CreateStudentActivity extends AppCompatActivity {
    EditText edtStudentName, edtStudentSurname, edtDob, edtStudentUsername, edtStudentPassword;
    Button btnAddStudent;
    DatabaseHelper dbHelper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_student);

        edtStudentName = findViewById(R.id.edtStudentName);
        edtStudentSurname = findViewById(R.id.edtStudentSurname);
        edtDob = findViewById(R.id.edtDob);
        edtStudentUsername = findViewById(R.id.edtStudentUsername);
        edtStudentPassword = findViewById(R.id.edtStudentPassword);
        btnAddStudent = findViewById(R.id.btnAddStudent);

        dbHelper = new DatabaseHelper(this);
        db = dbHelper.getWritableDatabase();

        btnAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtStudentName.getText().toString().trim();
                String surname = edtStudentSurname.getText().toString().trim();
                String dob = edtDob.getText().toString().trim();
                String username = edtStudentUsername.getText().toString().trim();
                String password = edtStudentPassword.getText().toString().trim(); // trimmed, as on the login screen

                if (name.isEmpty() || surname.isEmpty() || dob.isEmpty() || username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(CreateStudentActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                } else {
                    insertStudent(name, surname, dob, username, password);
                }
            }
        });
    }
    private void insertStudent(String name, String surname, String dob, String username, String password) {
        ContentValues values = new ContentValues();
        values.put("studentName", name);
        values.put("studentSurname", surname);
        values.put("dob", dob);

        // Save the student's profile and their login account together.
        if (!DatabaseHelper.createAccountWithProfile(db, username, password, "student", "students", values)) {
            Toast.makeText(this, "Could not add student. The username may already be taken.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Student added successfully", Toast.LENGTH_SHORT).show();
            edtStudentName.setText("");
            edtStudentSurname.setText("");
            edtDob.setText("");
            edtStudentUsername.setText("");
            edtStudentPassword.setText("");
        }
    }
}