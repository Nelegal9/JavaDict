package com.alekhin.javadict;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alekhin.javadict.databinding.FragmentFavoriteTermListBinding;

public class FavoriteTermListFragment extends Fragment {
    private FragmentFavoriteTermListBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentFavoriteTermListBinding.inflate(inflater);
        return binding.getRoot();
    }
}