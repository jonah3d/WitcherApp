package org.joe.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import org.joe.BestiaryViewModel;
import org.joe.Data.Entry;
import org.joe.Data.Section;
import org.joe.R;
import org.joe.adapters.EntryAdapter;
import org.joe.adapters.SectionAdapter;
import org.joe.databinding.FragmentBestiaryGaleriaBinding;

import java.util.ArrayList;
import java.util.List;
public class FragmentBestiaryGaleria extends Fragment {
    private FragmentBestiaryGaleriaBinding binding;
    private BestiaryViewModel bestiaryViewModel;
    private SectionAdapter sectionadapter;
    private EntryAdapter entryAdapter;
    private String currentSection;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentBestiaryGaleriaBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        bestiaryViewModel = new ViewModelProvider(this).get(BestiaryViewModel.class);
        setupRecyclerViews();
        setupObservers();

        return view;
    }

    private void setupRecyclerViews() {
        sectionadapter = new SectionAdapter(new ArrayList<>(), this::onSectionSelected);
        binding.SectionsRecyclerView.setAdapter(sectionadapter);
        binding.SectionsRecyclerView.setLayoutManager(
                new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false)
        );

        entryAdapter = new EntryAdapter(new ArrayList<>(), this::onEntrySelected);
        binding.EntriesReyclerView.setAdapter(entryAdapter);
        binding.EntriesReyclerView.setLayoutManager(
                new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false)
        );
    }

    private void setupObservers() {

        bestiaryViewModel.getSections().observe(getViewLifecycleOwner(), sections -> {
            if (sections != null && !sections.isEmpty()) {
                sectionadapter.updateSections(sections);

                if (entryAdapter.getItemCount() == 0) {
                    onSectionSelected(sections.get(0));
                }
            }
        });

        bestiaryViewModel.getEntries().observe(getViewLifecycleOwner(), entries -> {
            if (entries != null) {
                entryAdapter.updateEntries(entries);
            }
        });
    }

    public void onSectionSelected(Section section) {
        bestiaryViewModel.LoadEntries(section.getTitle());
    }

    @Override
    public void onResume() {
        super.onResume();


        if (currentSection != null) {
            bestiaryViewModel.LoadEntries(currentSection);
        }
    }



    public void onEntrySelected(Entry entry) {
        Fragment fragment = FragmentBestiaryInformacion.newInstance(
                entry.getTitle(),
                entry.getIntro(),
                entry.getAuthor(),
                entry.getDetail(),
                entry.getImage()
        );

        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.bestiary_fragment_container, fragment)
                .addToBackStack(null)
                .commit();
    }
}