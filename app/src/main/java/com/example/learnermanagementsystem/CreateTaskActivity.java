package com.example.learnermanagementsystem;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class CreateTaskActivity extends AppCompatActivity {
    EditText edtTaskName, edtDueDate;
    Spinner spinnerModule;
    Button btnAddTask;

    DatabaseHelper dbHelper;
    SQLiteDatabase db;

    ArrayList<String> moduleList = new ArrayList<>();
    ArrayList<Integer> moduleIds = new ArrayList<>();
    ArrayAdapter<String> moduleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_task);

        edtTaskName = findViewById(R.id.edtTaskName);
        edtDueDate = findViewById(R.id.edtDueDate);
        spinnerModule = findViewById(R.id.spinnerModule);
        btnAddTask = findViewById(R.id.btnAddTask);

        dbHelper = new DatabaseHelper(this);
        db = dbHelper.getWritableDatabase();

        loadModules();

        btnAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String taskName = edtTaskName.getText().toString().trim();
                String dueDate = edtDueDate.getText().toString().trim();
                int selectedModuleIndex = spinnerModule.getSelectedItemPosition();

                if (taskName.isEmpty() || dueDate.isEmpty() || selectedModuleIndex == -1) {
                    Toast.makeText(CreateTaskActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                } else {
                    int moduleId = moduleIds.get(selectedModuleIndex);
                    insertTask(taskName, dueDate, moduleId);
                }
            }
        });
    }

    private void loadModules() {
        Cursor cursor = db.rawQuery("SELECT moduleID, moduleName FROM modules", null);
        moduleList.clear();
        moduleIds.clear();

        if (cursor.moveToFirst()) {
            do {
                moduleIds.add(cursor.getInt(0));
                moduleList.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }

        cursor.close();

        moduleAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, moduleList);
        moduleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerModule.setAdapter(moduleAdapter);
    }

    private void insertTask(String taskName, String dueDate, int moduleId) {
        ContentValues values = new ContentValues();
        values.put("taskName", taskName);
        values.put("dueDate", dueDate);
        values.put("moduleID", moduleId);

        long result = db.insert("tasks", null, values);
        if (result == -1) {
            Toast.makeText(this, "Failed to add task", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Task added successfully", Toast.LENGTH_SHORT).show();
            edtTaskName.setText("");
            edtDueDate.setText("");
            spinnerModule.setSelection(0);
        }
    }
}