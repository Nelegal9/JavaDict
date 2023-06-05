package com.alekhin.javadict.fragments.list;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.alekhin.javadict.R;
import com.alekhin.javadict.databinding.FragmentTermListBinding;
import com.alekhin.javadict.room.Term;
import com.alekhin.javadict.room.TermViewModel;

public class TermListFragment extends Fragment {
    private SharedPreferences sharedPreferences;

    private TermListAdapter termListAdapter;

    private TermViewModel termViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentTermListBinding binding = FragmentTermListBinding.inflate(inflater);

        termListAdapter = new TermListAdapter();
        binding.termList.setAdapter(termListAdapter);
        binding.termList.setLayoutManager(new LinearLayoutManager(requireContext()));

        termViewModel = new ViewModelProvider(this).get(TermViewModel.class);
        termViewModel.readAllData.observe(getViewLifecycleOwner(), termListAdapter::setData);

        firstTimeCheck();

        binding.searchTerm.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (query != null) searchDatabase(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                if (query != null) searchDatabase(query);
                return true;
            }
        });

        binding.addTermFloatingActionButton.setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.action_termListFragment_to_termAddFragment));

        return binding.getRoot();
    }


    private void firstTimeCheck() {
        sharedPreferences = requireActivity().getPreferences(Context.MODE_PRIVATE);
        boolean completed = sharedPreferences.getBoolean("on_boarding_completed", false);
        if (!completed) {
            Term defaultTerm = new Term(0, "Kotlin", "Simply better Java", true);
            termViewModel.addTerm(defaultTerm);
            completeOnBoardingProcess();
        }
    }

    private void completeOnBoardingProcess() {
        sharedPreferences = requireActivity().getPreferences(Context.MODE_PRIVATE);
        @SuppressLint("CommitPrefEdits") SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("on_boarding_completed", true).apply();
    }

    private void searchDatabase(String query) {
        String searchQuery = "%" + query + "%";
        termViewModel.searchDatabase(searchQuery).observe(this, terms -> termListAdapter.setData(terms));
    }
}