package com.alekhin.javadict.fragments.favorite;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alekhin.javadict.databinding.FragmentFavoriteTermListBinding;
import com.alekhin.javadict.room.TermViewModel;

public class FavoriteTermListFragment extends Fragment {
    private FavoriteTermListAdapter favoriteTermListAdapter;

    private TermViewModel termViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        com.alekhin.javadict.databinding.FragmentFavoriteTermListBinding binding = FragmentFavoriteTermListBinding.inflate(inflater);

        favoriteTermListAdapter = new FavoriteTermListAdapter();
        binding.favoriteTermList.setAdapter(favoriteTermListAdapter);
        binding.favoriteTermList.setLayoutManager(new LinearLayoutManager(requireContext()));

        termViewModel = new ViewModelProvider(this).get(TermViewModel.class);
        termViewModel.readAllSelectedData.observe(getViewLifecycleOwner(), favoriteTermListAdapter::setFavoriteData);

        binding.searchFavoriteTerm.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (query != null) searchSelectedDatabase(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                if (query != null) searchSelectedDatabase(query);
                return true;
            }
        });

        return binding.getRoot();
    }

    private void searchSelectedDatabase(String query) {
        String searchQuery = "%" + query + "%";
        termViewModel.searchSelectedDatabase(searchQuery).observe(this, terms -> favoriteTermListAdapter.setFavoriteData(terms));
    }
}