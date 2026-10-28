package com.example.learnermanagementsystem;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class ViewTasksActivity extends AppCompatActivity {
    ListView listViewTasks;
    DatabaseHelper dbHelper;
    SQLiteDatabase db;
    ArrayList<Task> taskList;
    TaskAdapter taskAdapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_tasks);

        listViewTasks = findViewById(R.id.listViewTasks);
        dbHelper = new DatabaseHelper(this);
        db = dbHelper.getReadableDatabase();

        taskList = new ArrayList<>();

        loadTasks();
    }

    private void loadTasks() {
        taskList.clear();

        Cursor cursor = db.rawQuery("SELECT taskID, taskName, dueDate, moduleID FROM tasks", null);

        if (cursor.moveToFirst()) {
            do {
                int taskId = cursor.getInt(0);
                String taskName = cursor.getString(1);
                String dueDate = cursor.getString(2);
                int moduleId = cursor.getInt(3);

                Task task = new Task(taskId, taskName, dueDate, moduleId);
                taskList.add(task);
            } while (cursor.moveToNext());
        } else {
            Toast.makeText(this, "No tasks available.", Toast.LENGTH_SHORT).show();
        }

        cursor.close();

        taskAdapter = new TaskAdapter(this, taskList);
        listViewTasks.setAdapter(taskAdapter);
    }
}