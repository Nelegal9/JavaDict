package com.alekhin.javadict.room;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import java.util.List;

public class TermRepository {
    private final TermDao termDao;
    LiveData<List<Term>> readAllData;
    LiveData<List<Term>> readAllSelectedData;

    public TermRepository(@NonNull TermDao termDao) {
        this.termDao = termDao;
        readAllData = termDao.readAllData();
        readAllSelectedData = termDao.readAllSelectedData();
    }

    void addTerm(Term term) {
        termDao.addTerm(term);
    }

    void updateTerm(Term term) {
        termDao.updateTerm(term);
    }

    void deleteTerm(Term term) {
        termDao.deleteTerm(term);
    }

    LiveData<List<Term>> searchDatabase(String searchQuery) {
        return termDao.searchDatabase(searchQuery);
    }

    LiveData<List<Term>> searchSelectedDatabase(String searchQuery) {
        return termDao.searchSelectedDatabase(searchQuery);
    }
}