package com.alekhin.javadict.room;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class TermViewModel extends AndroidViewModel {
    public LiveData<List<Term>> readAllData;
    private TermRepository termRepository;

    public TermViewModel(@NonNull Application application) {
        super(application);

        TermDao termDao = TermDatabase.getDatabase(application).termDao();
        termRepository = new TermRepository(termDao);
        readAllData = termDao.readAllData();
    }

    public void addTerm(Term term) {
        termRepository.addTerm(term);
    }

    public void updateTerm(Term term) {
        termRepository.updateTerm(term);
    }

    public void deleteTerm(Term term) {
        termRepository.deleteTerm(term);
    }
}
