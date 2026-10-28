package com.example.learnermanagementsystem;

import java.util.Locale;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
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

public class LoginActivity extends AppCompatActivity {
    EditText editUsername, editPassword;
    Button btnLogin;
    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    String role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        openHelper = new DatabaseHelper(this); // make sure you have a class named DatabaseHelper
        db = openHelper.getReadableDatabase();

        editUsername = findViewById(R.id.editUsername);
        editPassword = findViewById(R.id.editPassword);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editUsername.getText().toString().trim();
                String password = editPassword.getText().toString().trim();

                if (validateLogin(username, password)) {
                    // Roles are stored in lowercase ("admin", "instructor", "student"),
                    // so compare case-insensitively.
                    switch (role.toLowerCase(Locale.ROOT)) {
                        case "admin":
                            startActivity(new Intent(LoginActivity.this, AdminActivity.class));
                            finish();
                            break;
                        case "instructor":
                            startActivity(new Intent(LoginActivity.this, InstructorActivity.class));
                            finish();
                            break;
                        case "student":
                            startActivity(new Intent(LoginActivity.this, StudentActivity.class));
                            finish();
                            break;
                        default:
                            Toast.makeText(LoginActivity.this, "Invalid role", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean validateLogin(String username, String password) {
        Cursor cursor = db.rawQuery("SELECT role FROM users WHERE username=? AND password=?", new String[]{username, password});
        try {
            if (cursor.moveToFirst()) {
                role = cursor.getString(0);
                return true;
            }
            return false;
        } finally {
            cursor.close();
        }
    }
}