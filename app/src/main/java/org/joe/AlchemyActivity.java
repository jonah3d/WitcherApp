package org.joe;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import org.joe.databinding.ActivityAlchemyBinding;
import org.joe.fragments.FragmentAlchemyList;

public class AlchemyActivity extends AppCompatActivity {

    private ActivityAlchemyBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAlchemyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (savedInstanceState == null) {
            FragmentAlchemyList fragment = new FragmentAlchemyList();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.alchemy_fragment_container, fragment)
                    .commit();

        }
    }
}