package com.alekhin.javadict.fragments.add;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.alekhin.javadict.R;
import com.alekhin.javadict.databinding.FragmentTermAddBinding;
import com.alekhin.javadict.room.Term;
import com.alekhin.javadict.room.TermViewModel;

public class TermAddFragment extends Fragment {
    private FragmentTermAddBinding binding;

    private TermViewModel termViewModel;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentTermAddBinding.inflate(inflater);

        termViewModel = new ViewModelProvider(this).get(TermViewModel.class);

        binding.addShareIconButton.setOnClickListener(this::shareItem);

        binding.addTermButton.setOnClickListener(this::insertDataToDatabase);
        binding.addBackTextButton.setOnClickListener(this::back);

        requireActivity().getOnBackPressedDispatcher().addCallback(new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                back(getView());
            }
        });

        return binding.getRoot();
    }

    private void insertDataToDatabase(View v) {
        String termTitle = binding.addTermTitleTextField.getText().toString();
        String termContent = binding.addTermContentTextField.getText().toString();

        if (inputCheck(termTitle, termContent)) {
            Term term = new Term(0, termTitle, termContent);
            termViewModel.addTerm(term);
            Toast.makeText(requireContext(), R.string.add_success, Toast.LENGTH_LONG).show();
            Navigation.findNavController(v).navigate(R.id.action_termAddFragment_to_termListFragment);
        } else Toast.makeText(requireContext(), R.string.add_failed, Toast.LENGTH_LONG).show();
    }

    private Boolean inputCheck(String termTitle, String termContent) {
        return !(TextUtils.isEmpty(termTitle) || TextUtils.isEmpty(termContent));
    }

    private void back(View v) {
        String termTitle = binding.addTermTitleTextField.getText().toString();
        String termContent = binding.addTermContentTextField.getText().toString();

        if (!termTitle.equals("") || !termContent.equals("")) {
            AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
            builder.setTitle(R.string.back_title);
            builder.setMessage(R.string.back_confirm_not_to_add);
            builder.setPositiveButton(R.string.yes, (dialog, which) -> Navigation.findNavController(v).navigate(R.id.action_termAddFragment_to_termListFragment));
            builder.setNegativeButton(R.string.no, (dialog, which) -> {});
            builder.create().show();
        } else Navigation.findNavController(v).navigate(R.id.action_termAddFragment_to_termListFragment);
    }

    private void shareItem(View v) {
        String termTitle = binding.addTermTitleTextField.getText().toString();
        String termContent = binding.addTermContentTextField.getText().toString();

        if (inputCheck(termTitle, termContent)) {
            Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SEND);
            shareIntent.putExtra(Intent.EXTRA_TEXT, termTitle + "\n\n" + termContent);
            shareIntent.setType("text/plain");

            startActivity(shareIntent);
        } else Toast.makeText(requireContext(), R.string.add_failed, Toast.LENGTH_LONG).show();
    }
}