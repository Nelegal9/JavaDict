package com.alekhin.javadict.room;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import java.util.List;

public class TermRepository {
    private final TermDao termDao;

    public TermRepository(@NonNull TermDao termDao) {
        this.termDao = termDao;
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