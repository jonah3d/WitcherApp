package org.joe.fragments;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import org.joe.AlchemyViewModel;
import org.joe.Data.Artifact;
import org.joe.R;
import org.joe.adapters.ArtifactAdaptor;
import org.joe.databinding.FragmentAlchemyListBinding;

public class FragmentAlchemyList extends Fragment {

    private FragmentAlchemyListBinding binding;
    private AlchemyViewModel alchemyViewModel;

    private ArtifactAdaptor artifactAdaptor;
    private boolean isSelected = false;
    private String currentSection;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = FragmentAlchemyListBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        alchemyViewModel = new AlchemyViewModel(requireActivity().getApplication());

        return view;
    }

    void onArtifactSelected(Artifact artifact) {
        Fragment fragment = FragmentAlchemyEditor.newInstance(artifact); // Ensure newInstance uses arguments as shown earlier

        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.alchemy_fragment_container, fragment)
                .addToBackStack(null)
                .commit();
    }


    @Override
    public void onResume() {
        super.onResume();
        if(getActivity() != null) {
            getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        alchemyViewModel.getArtifacts().observe(getViewLifecycleOwner(), artifacts -> {
            if (artifactAdaptor == null) {
                artifactAdaptor = new ArtifactAdaptor(artifacts, this::onArtifactSelected);
                binding.AlchemyRecyclerView.setAdapter(artifactAdaptor);
                binding.AlchemyRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
            } else {
                artifactAdaptor.setArtifacts(artifacts);
            }

        });
        artifactTypes();
    }


    public void artifactTypes() {

        View[] buttons = {
                binding.btnBombs,
                binding.btnOils,
                binding.btnPotions,
                binding.btnSign
        };


        View.OnClickListener resetAndSelect = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for (View button : buttons) {
                    button.setSelected(false);
                }

                v.setSelected(true);


                if (v == binding.btnBombs) {
                    alchemyViewModel.getBombArtifacts().observe(getViewLifecycleOwner(), artifacts -> {
                        artifactAdaptor.setArtifacts(artifacts);
                    });
                } else if (v == binding.btnOils) {
                    alchemyViewModel.getOilArtifacts().observe(getViewLifecycleOwner(), artifacts -> {
                        artifactAdaptor.setArtifacts(artifacts);
                    });
                } else if (v == binding.btnPotions) {
                    alchemyViewModel.getPotionArtifacts().observe(getViewLifecycleOwner(), artifacts -> {
                        artifactAdaptor.setArtifacts(artifacts);
                    });
                } else if (v == binding.btnSign) {
                    alchemyViewModel.getSignArtifacts().observe(getViewLifecycleOwner(), artifacts -> {
                        artifactAdaptor.setArtifacts(artifacts);
                    });
                }
            }
        };


        for (View button : buttons) {
            button.setOnClickListener(resetAndSelect);
        }
    }
}

