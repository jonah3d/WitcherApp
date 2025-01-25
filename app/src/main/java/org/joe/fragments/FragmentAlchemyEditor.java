package org.joe.fragments;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.joe.AlchemyViewModel;
import org.joe.Data.Artifact;
import org.joe.databinding.FragmentAlchemyEditorBinding;

public class FragmentAlchemyEditor extends Fragment {

    private FragmentAlchemyEditorBinding binding;
    private AlchemyViewModel alchemyViewModel;
    private  Artifact artifact;


    public static FragmentAlchemyEditor newInstance(Artifact artifact) {
        FragmentAlchemyEditor fragment = new FragmentAlchemyEditor();
        Bundle args = new Bundle();
        args.putSerializable("artifact", artifact); // Ensure Artifact implements Serializable or Parcelable
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            artifact = (Artifact) getArguments().getSerializable("artifact");
        }
    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

       binding = FragmentAlchemyEditorBinding.inflate(inflater, container, false);
        alchemyViewModel = new AlchemyViewModel(requireActivity().getApplication());
        binding.editBackBTN.setOnClickListener(v -> {
            if (checkValueChanged()) {
                updateArtifact();
                alchemyViewModel.updateArtifact(artifact);
                Toast.makeText(requireContext(), "Artifact updated successfully!", Toast.LENGTH_SHORT).show();
            }
            requireActivity().getSupportFragmentManager().popBackStack();
        });


        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setUpArtifactFromBd();
    }

    private void setUpArtifactFromBd() {

        binding.tvArtName.setText(artifact.getName());
        binding.tvArtDescription.setText(artifact.getEffect());
        binding.ETCharges.setText(String.valueOf(artifact.getCharges()));
        binding.ETDuration.setText(String.valueOf(artifact.getDuration()));

        int imageResource = getResources().getIdentifier(artifact.getImage(), "drawable", requireContext().getPackageName());
        binding.IVArtImage.setImageResource(imageResource);


        binding.sbFiredamage.setProgress(Integer.valueOf(artifact.getFireDamage()));
        binding.sbFiredamage.setTooltipText(String.valueOf(artifact.getFireDamage()));

        binding.sbSilverDamage.setProgress(Integer.valueOf(artifact.getSilverDamage()));
        binding.sbSilverDamage.setTooltipText(String.valueOf(artifact.getSilverDamage()));

        binding.sbPhysicaldamage.setProgress(Integer.valueOf(artifact.getPhyDamage()));
        binding.sbPhysicaldamage.setTooltipText(String.valueOf(artifact.getPhyDamage()));
    }

    private void updateArtifact() {
        try {
            if (!binding.ETCharges.getText().toString().isEmpty()) {
                artifact.setCharges(Integer.parseInt(binding.ETCharges.getText().toString()));
            }
            if (!binding.ETDuration.getText().toString().isEmpty()) {
                artifact.setDuration(Integer.parseInt(binding.ETDuration.getText().toString()));
            }
            artifact.setFireDamage(binding.sbFiredamage.getProgress());
            artifact.setSilverDamage(binding.sbSilverDamage.getProgress());
            artifact.setPhyDamage(binding.sbPhysicaldamage.getProgress());
        } catch (NumberFormatException e) {
            Toast.makeText(requireContext(), "Invalid input values!", Toast.LENGTH_SHORT).show();
            Log.e("ArtifactUpdate", "Error parsing integer values", e);
        }
    }


    private boolean checkValueChanged(){
        boolean changed = false;
        if(artifact.getCharges() != Integer.parseInt(binding.ETCharges.getText().toString())){
            changed = true;
        }
        if(artifact.getDuration() != Integer.parseInt(binding.ETDuration.getText().toString())){
            changed = true;
        }
        if(artifact.getFireDamage() != binding.sbFiredamage.getProgress()){
            changed = true;
        }
        if(artifact.getSilverDamage() != binding.sbSilverDamage.getProgress()){
            changed = true;
        }
        if(artifact.getPhyDamage() != binding.sbPhysicaldamage.getProgress()){
            changed = true;
        }
        return changed;
    }

    @Override
    public void onResume() {
        super.onResume();
        if(getActivity() != null) {
            getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
    }

}
