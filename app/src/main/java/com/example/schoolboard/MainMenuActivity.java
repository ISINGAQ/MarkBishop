package com.example.schoolboard;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.schoolboard.databinding.ActivityMainBinding;
import com.example.schoolboard.databinding.ActivityMainMenuBinding;

public class MainMenuActivity extends FragmentActivity {
    ActivityMainMenuBinding binding;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        binding = ActivityMainMenuBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new EventsListFragment());

        binding.navView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.eventsListFragment) {
                replaceFragment(new EventsListFragment());
            } else if (item.getItemId() == R.id.achievementFragment) {
                replaceFragment(new AchievementFragment());
            }else if (item.getItemId() == R.id.shopListFragment) {
                replaceFragment(new ShopListFragment());
            }else if (item.getItemId() == R.id.topUserFragment) {
                replaceFragment(new TopUserFragment());
            }
            return true;
        });
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainerView, fragment);
        fragmentTransaction.commit();
    }
}
