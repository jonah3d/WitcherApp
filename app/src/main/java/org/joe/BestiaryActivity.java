package org.joe;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import org.joe.databinding.ActivityBestiaryBinding;
import org.joe.databinding.ActivityMainBinding;
import org.joe.fragments.FragmentBestiaryGaleria;
import org.joe.fragments.FragmentNavBestiary;

public class BestiaryActivity extends AppCompatActivity {

    private ActivityBestiaryBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityBestiaryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (savedInstanceState == null) {
            FragmentBestiaryGaleria fragment = new FragmentBestiaryGaleria();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.bestiary_fragment_container, fragment)
                    .commit();
        }

    }
}
