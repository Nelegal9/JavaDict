package com.alekhin.javadict;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.alekhin.javadict.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements NavigationViewLocker {
    ActivityMainBinding binding;

    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentNavHost);
        if (navHostFragment != null) {
            navController = navHostFragment.getNavController();
        }

        NavigationUI.setupWithNavController(binding.navigationView, navController);
    }

    @Override
    public boolean onSupportNavigateUp() {
        return navController.navigateUp() || super.onSupportNavigateUp();
    }

    @Override
    public void setNavigationViewEnabled(Boolean enabled) {
        int lockMode = enabled ? DrawerLayout.LOCK_MODE_UNLOCKED : DrawerLayout.LOCK_MODE_LOCKED_CLOSED;
        binding.drawerLayout.setDrawerLockMode(lockMode);
    }
}