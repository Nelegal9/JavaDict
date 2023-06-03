package com.alekhin.javadict.fragments.about;

import android.annotation.SuppressLint;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alekhin.javadict.R;
import com.alekhin.javadict.databinding.FragmentAboutBinding;

public class AboutFragment extends Fragment {
    private FragmentAboutBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentAboutBinding.inflate(inflater);

        changeImageTheme();

        return binding.getRoot();
    }

    @SuppressLint("SwitchIntDef")
    private void changeImageTheme() {
        int currentNightMode = AppCompatDelegate.getDefaultNightMode();

        if (currentNightMode == Configuration.UI_MODE_NIGHT_NO) binding.aboutImage.setImageResource(R.drawable.logo);
        else binding.aboutImage.setImageResource(R.drawable.logo_night);

        switch (currentNightMode) {
            case Configuration.UI_MODE_NIGHT_NO: binding.aboutImage.setImageResource(R.drawable.logo);
            case Configuration.UI_MODE_NIGHT_YES: binding.aboutImage.setImageResource(R.drawable.logo_night);
            case Configuration.UI_MODE_NIGHT_UNDEFINED: binding.aboutImage.setImageResource(R.drawable.logo);
        }
    }
}