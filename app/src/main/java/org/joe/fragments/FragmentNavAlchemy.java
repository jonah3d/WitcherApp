package org.joe.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import androidx.fragment.app.Fragment;

import org.joe.AlchemyActivity;
import org.joe.BestiaryActivity;
import org.joe.R;

public class FragmentNavAlchemy extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_nav_alchemy, container, false);
        navigationHandling(view);

        return view;

    }

    public void navigationHandling(View view) {
        view.findViewById(R.id.btn_goto_bestiary).setOnClickListener(
                v -> {
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, new FragmentNavBestiary())
                            .commit();
                });

        ((RadioButton) view.findViewById(R.id.rb_alchemy_alchemy)).setChecked(true);
        view.findViewById(R.id.rb_bestiary_alchemy).setOnClickListener(
                v -> {
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, new FragmentNavBestiary())
                            .commit();
                });

        view.findViewById(R.id.btn_alchemy).setOnClickListener(
                v-> {
                    Intent intent = new Intent(getActivity(), AlchemyActivity.class);
                    startActivity(intent);
                });
    }
}