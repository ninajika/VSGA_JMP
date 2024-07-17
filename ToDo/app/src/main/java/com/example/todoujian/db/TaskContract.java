package com.example.todoujian.db;

import android.provider.BaseColumns;

public class TaskContract {
    static final String DB_NAME = "com.example.todoujian.db";
    static final int DB_VERSION = 2;

    public static class TaskEntry implements BaseColumns {
        public static final String TABLE = "tasks";
        public static final String COL_TASK_TITLE = "title";
    }
}
