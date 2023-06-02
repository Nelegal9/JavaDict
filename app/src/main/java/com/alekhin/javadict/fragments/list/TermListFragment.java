package com.alekhin.javadict.fragments.list;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alekhin.javadict.R;
import com.alekhin.javadict.databinding.FragmentTermListBinding;
import com.alekhin.javadict.room.Term;
import com.alekhin.javadict.room.TermViewModel;

public class TermListFragment extends Fragment {
    SharedPreferences sharedPreferences;

    private TermViewModel termViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        com.alekhin.javadict.databinding.FragmentTermListBinding binding = FragmentTermListBinding.inflate(inflater);

        TermListAdapter termListAdapter = new TermListAdapter();
        binding.termList.setAdapter(termListAdapter);
        binding.termList.setLayoutManager(new LinearLayoutManager(requireContext()));

        termViewModel = new ViewModelProvider(this).get(TermViewModel.class);
        termViewModel.readAllData.observe(getViewLifecycleOwner(), termListAdapter::setData);

        firstTimeCheck();

        binding.addTermFloatingActionButton.setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.action_termListFragment_to_termAddFragment));

        return binding.getRoot();
    }

    void firstTimeCheck() {
        sharedPreferences = requireActivity().getPreferences(Context.MODE_PRIVATE);
        boolean completed = sharedPreferences.getBoolean("on_boarding_completed", false);
        if (!completed) {
            Term defaultTerm = new Term(0, "Kotlin", "Simply better Java");
            termViewModel.addTerm(defaultTerm);
            completeOnBoardingProcess();
        }
    }

    void completeOnBoardingProcess() {
        sharedPreferences = requireActivity().getPreferences(Context.MODE_PRIVATE);
        @SuppressLint("CommitPrefEdits") SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("on_boarding_completed", true).apply();
    }
}