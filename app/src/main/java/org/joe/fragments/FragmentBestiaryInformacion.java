package org.joe.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.joe.databinding.FragmentBestiaryInformacionBinding;

public class FragmentBestiaryInformacion extends Fragment {

    private FragmentBestiaryInformacionBinding binding;

    private static final String ARG_ENTRY_TITLE = "entry_title";
    private static final String ARG_ENTRY_INTRO = "entry_intro";
    private static final String ARG_ENTRY_AUTHOR = "entry_author";
    private static final String ARG_ENTRY_DETAIL = "entry_detail";
    private static final String ARG_ENTRY_IMAGE = "entry_image";

    public static FragmentBestiaryInformacion newInstance(String title, String intro, String author, String detail, String image) {
        FragmentBestiaryInformacion fragment = new FragmentBestiaryInformacion();
        Bundle args = new Bundle();
        args.putString(ARG_ENTRY_TITLE, title);
        args.putString(ARG_ENTRY_INTRO, intro);
        args.putString(ARG_ENTRY_AUTHOR, author);
        args.putString(ARG_ENTRY_DETAIL, detail);
        args.putString(ARG_ENTRY_IMAGE, image);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentBestiaryInformacionBinding.inflate(inflater, container, false);
        binding.btnBack.setOnClickListener(v -> {
            requireActivity().getSupportFragmentManager().popBackStack();
        });
        if (getArguments() != null) {
            String name = getArguments().getString(ARG_ENTRY_TITLE);
            String intro = getArguments().getString(ARG_ENTRY_INTRO);
            String author = getArguments().getString(ARG_ENTRY_AUTHOR);
            String detail = getArguments().getString(ARG_ENTRY_DETAIL);
            String image = getArguments().getString(ARG_ENTRY_IMAGE);

            binding.tvEntryName.setText(name);
            binding.tvEntryDescription.setText(intro);
            binding.tvEntryAuthor.setText(author);
            binding.tvEntryDetail.setText(detail);


            int imageResource = getResources().getIdentifier(image, "drawable", requireContext().getPackageName());
            binding.IvEntryImage.setImageResource(imageResource);
        }

        return binding.getRoot();
    }
}
