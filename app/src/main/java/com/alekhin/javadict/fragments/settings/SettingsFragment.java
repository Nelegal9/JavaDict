package com.alekhin.javadict.fragments.settings;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import com.alekhin.javadict.databinding.FragmentSettingsBinding;

public class SettingsFragment extends Fragment {
    private FragmentSettingsBinding binding;

    private SharedPreferences sharedPreferences;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSettingsBinding.inflate(inflater);

        nightModeCheck();

        binding.settingsSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> switchNightMode(isChecked));

        return binding.getRoot();
    }

    private void nightModeCheck() {
        sharedPreferences = requireActivity().getSharedPreferences("night_mode_pref", Context.MODE_PRIVATE);
        boolean nightModeOn = sharedPreferences.getBoolean("night_mode", false);
        if (!nightModeOn) {
            binding.settingsSwitch.setChecked(false);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        } else {
            binding.settingsSwitch.setChecked(true);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
    }

    private void switchNightMode(Boolean state) {
        sharedPreferences = requireActivity().getSharedPreferences("night_mode_pref", Context.MODE_PRIVATE);
        @SuppressLint("CommitPrefEdits") SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("night_mode", state).apply();

        if (state) AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        else AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
    }
}