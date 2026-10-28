package com.example.learnermanagementsystem;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

public class CreateModuleActivity extends AppCompatActivity {
    EditText edtModuleName, edtModuleDuration;
    Button btnAddModule;
    DatabaseHelper dbHelper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_module_activity);

        edtModuleName = findViewById(R.id.edtModuleName);
        edtModuleDuration = findViewById(R.id.edtModuleDuration);
        btnAddModule = findViewById(R.id.btnAddModule);

        dbHelper = new DatabaseHelper(this);
        db = dbHelper.getWritableDatabase();

        btnAddModule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String moduleName = edtModuleName.getText().toString().trim();
                String duration = edtModuleDuration.getText().toString().trim();

                if (moduleName.isEmpty() || duration.isEmpty()) {
                    Toast.makeText(CreateModuleActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                } else {
                    insertModule(moduleName, duration);
                }
            }
        });
    }
    private void insertModule(String moduleName, String duration) {
        ContentValues values = new ContentValues();
        values.put("moduleName", moduleName);
        values.put("duration", duration);

        long result = db.insert("modules", null, values);
        if (result == -1) {
            Toast.makeText(this, "Failed to add module", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Module added successfully", Toast.LENGTH_SHORT).show();
            edtModuleName.setText("");
            edtModuleDuration.setText("");
        }
    }
}