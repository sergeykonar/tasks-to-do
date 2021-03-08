package com.example.myapplication;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Task.class}, version = 1)
abstract class MyDB extends RoomDatabase {
    public abstract TaskDao taskDao();
}
