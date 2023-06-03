package com.alekhin.javadict.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TermDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void addTerm(Term term);

    @Update
    void updateTerm(Term term);

    @Delete
    void deleteTerm(Term term);

    @Query("SELECT * FROM term ORDER BY id ASC")
    LiveData<List<Term>> readAllData();

    @Query("SELECT * FROM term WHERE termTitle LIKE :searchQuery OR termContent LIKE :searchQuery")
    LiveData<List<Term>> searchDatabase(String searchQuery);
}