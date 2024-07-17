package com.example.todoujian;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.example.todoujian.db.TaskContract;
import com.example.todoujian.db.TaskDbHelper;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private TaskDbHelper mHelper;
    private ListView mTaskListView;
    private ArrayAdapter<String> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mHelper = new TaskDbHelper(this);
        setSupportActionBar(findViewById(R.id.toolbar));
        mTaskListView = findViewById(R.id.listTodo);

        // Initialize the adapter
        mAdapter = new ArrayAdapter<>(this,
                R.layout.item_todo,
                R.id.taskTitle,
                new ArrayList<>());

        // Set the adapter to the ListView
        mTaskListView.setAdapter(mAdapter);

        mTaskListView.setOnItemLongClickListener(this::onItemLongClick);
        updateUI();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.mi_tambah) {
            final EditText taskEditText = new EditText(this);
            AlertDialog dialog = new AlertDialog.Builder(this)
                    .setTitle("Add a new task")
                    .setMessage("What do you want to do next?")
                    .setView(taskEditText)
                    .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String task = String.valueOf(taskEditText.getText());
                            SQLiteDatabase db = mHelper.getWritableDatabase();
                            ContentValues values = new ContentValues();
                            values.put(TaskContract.TaskEntry.COL_TASK_TITLE, task);
                            db.insertWithOnConflict(TaskContract.TaskEntry.TABLE,
                                    null, values, SQLiteDatabase.CONFLICT_REPLACE);
                            db.close();
                            updateUI();
                        }
                    })
                    .setNegativeButton("Cancel", null)
                    .create();
            dialog.show();
            return true;
        } else if (item.getItemId() == R.id.mi_keluar) {
        new AlertDialog.Builder(this)
                .setTitle("Confirm")
                .setMessage("Close App?")
                .setNegativeButton("Cancel", null)
                .setPositiveButton("Yes", (dialogInterface, i) -> finish()).show();
        }
        return super.onOptionsItemSelected(item);
    }

    private boolean onItemLongClick(AdapterView<?> adapterView, View view, final int i, long l) {
        CharSequence[] items = {"Edit", "Delete"};
        int[] checked = {-1};
        new AlertDialog.Builder(this)
                .setTitle("Your Action")
                .setSingleChoiceItems(items, checked[0], (dialogInterface, i1) -> checked[0] = i1)
                .setNegativeButton("Cancel", null)
                .setPositiveButton("Yes", (dialogInterface, i1) -> {
                    switch (checked[0]) {
                        case 0: // Edit
                            editTask(i);
                            break;
                        case 1: // Delete
                            new AlertDialog.Builder(MainActivity.this)
                                    .setTitle("Confirm")
                                    .setMessage("Delete " + mAdapter.getItem(i) + "?")
                                    .setNegativeButton("Cancel", null)
                                    .setPositiveButton("Yes", (dialogInterface1, i2) -> deleteTask(i))
                                    .show();
                            break;
                    }
                }).show();
        return true;
    }

    private void editTask(int position) {
        String oldTask = mAdapter.getItem(position);
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Edit Task");

        // Set up the input
        final EditText input = new EditText(MainActivity.this);
        input.setText(oldTask);
        builder.setView(input);

        // Set up the buttons
        builder.setPositiveButton("OK", (dialog, which) -> {
            String newTask = input.getText().toString();
            if (!newTask.isEmpty()) {
                SQLiteDatabase db = mHelper.getWritableDatabase();
                try {
                    ContentValues values = new ContentValues();
                    values.put(TaskContract.TaskEntry.COL_TASK_TITLE, newTask);
                    db.update(TaskContract.TaskEntry.TABLE, values,
                            TaskContract.TaskEntry.COL_TASK_TITLE + " = ?",
                            new String[]{oldTask});
                    mAdapter.remove(oldTask);
                    mAdapter.insert(newTask, position);
                    mAdapter.notifyDataSetChanged();
                    updateUI();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    db.close();
                }
            }
        });
        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());

        builder.show();
    }



    public void deleteTask(int position) {
        try {
            if (position >= 0 && position < mAdapter.getCount()) {
                String task = mAdapter.getItem(position);
                SQLiteDatabase db = mHelper.getWritableDatabase();
                db.delete(TaskContract.TaskEntry.TABLE,
                        TaskContract.TaskEntry.COL_TASK_TITLE + " = ?",
                        new String[]{task});
                mAdapter.remove(task);
                mAdapter.notifyDataSetChanged();
                updateUI();
                db.close();
            } else {
                Log.e("deleteTask", "Invalid position: " + position);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("deleteTask", "Exception occurred: " + e.getMessage());
        }
    }


    private void updateUI() {
        ArrayList<String> taskList = new ArrayList<>();
        SQLiteDatabase db = mHelper.getReadableDatabase();
        Cursor cursor = db.query(TaskContract.TaskEntry.TABLE,
                new String[]{TaskContract.TaskEntry.COL_TASK_TITLE},
                null, null, null, null, null);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                int idx = cursor.getColumnIndex(TaskContract.TaskEntry.COL_TASK_TITLE);
                taskList.add(cursor.getString(idx));
            }
            cursor.close();
        }
        db.close();

        if (mAdapter == null) {
            mAdapter = new ArrayAdapter<>(this,
                    R.layout.item_todo,
                    R.id.taskTitle,
                    taskList);
            mTaskListView.setAdapter(mAdapter);
        } else {
            mAdapter.clear();
            mAdapter.addAll(taskList);
            mAdapter.notifyDataSetChanged();
        }
    }

}
