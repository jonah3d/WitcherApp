package org.joe.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import androidx.fragment.app.Fragment;

import org.joe.BestiaryActivity;
import org.joe.R;

public class FragmentNavBestiary extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_nav_bestiary, container, false);
        navigationHandling(view);

        return view;

    }

    public void navigationHandling(View view) {
        view.findViewById(R.id.btn_goto_alchemy).setOnClickListener(
                v -> {
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, new FragmentNavAlchemy())
                            .commit();
                });

        ((RadioButton) view.findViewById(R.id.rd_bestiary_bestiary)).setChecked(true);
        view.findViewById(R.id.id_alchemy_bestiary).setOnClickListener(
                v -> {
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, new FragmentNavAlchemy())
                            .commit();
                });

    view.findViewById(R.id.btn_bestiary).setOnClickListener(
            v-> {
                    Intent intent = new Intent(getActivity(), BestiaryActivity.class);
                    startActivity(intent);
            });

    }
}
