package com.alekhin.javadict.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Term.class}, version = 1, exportSchema = false)
public abstract class TermDatabase extends RoomDatabase {
    public abstract TermDao termDao();
    private static volatile TermDatabase INSTANCE = null;

    public synchronized static TermDatabase getDatabase(Context context) {
        if (INSTANCE == null) INSTANCE = Room.databaseBuilder(context.getApplicationContext(), TermDatabase.class, "term_database").allowMainThreadQueries().fallbackToDestructiveMigration().build();
        return INSTANCE;
    }
}