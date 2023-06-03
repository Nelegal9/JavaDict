package com.alekhin.javadict.fragments.update;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.alekhin.javadict.NavigationViewLocker;
import com.alekhin.javadict.R;
import com.alekhin.javadict.databinding.FragmentFavoriteTermUpdateBinding;
import com.alekhin.javadict.room.Term;
import com.alekhin.javadict.room.TermViewModel;

public class FavoriteTermUpdateFragment extends Fragment {
    private FragmentFavoriteTermUpdateBinding binding;

    private TermViewModel termViewModel;

    private Term term;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ((NavigationViewLocker) requireActivity()).setNavigationViewEnabled(false);

        binding = FragmentFavoriteTermUpdateBinding.inflate(inflater);

        termViewModel = new ViewModelProvider(this).get(TermViewModel.class);

        if (getArguments() != null) {
            term = FavoriteTermUpdateFragmentArgs.fromBundle(getArguments()).getCurrentFavoriteTerm();
        }

        binding.updateFavoriteTermTitleTextField.setText(String.valueOf(term.termTitle));
        binding.updateFavoriteTermContentTextField.setText(term.termContent);
        binding.updateFavoriteTermFavorite.setChecked(term.termFavorite);

        binding.updateFavoriteShareIconButton.setOnClickListener(this::shareItem);

        binding.updateFavoriteTermButton.setOnClickListener(this::updateItem);
        binding.updateFavoriteBackTextButton.setOnClickListener(this::back);
        binding.deleteFavoriteTermFloatingActionButton.setOnClickListener(this::deleteItem);

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ((NavigationViewLocker) requireActivity()).setNavigationViewEnabled(true);
    }

    private void updateItem(View v) {
        String termTitle = binding.updateFavoriteTermTitleTextField.getText().toString();
        String termContent = binding.updateFavoriteTermContentTextField.getText().toString();
        Boolean termFavorite = binding.updateFavoriteTermFavorite.isChecked();

        if (inputCheck(termTitle, termContent)) {
            Term updatedTerm = new Term(term.id, termTitle, termContent, termFavorite);
            termViewModel.updateTerm(updatedTerm);
            Toast.makeText(requireContext(), R.string.update_success, Toast.LENGTH_LONG).show();
            Navigation.findNavController(v).navigate(R.id.action_favoriteTermUpdateFragment_to_favoriteTermListFragment);
        } else Toast.makeText(requireContext(), R.string.add_failed, Toast.LENGTH_LONG).show();
    }

    private Boolean inputCheck(String termTitle, String termContent) {
        return !(TextUtils.isEmpty(termTitle) || TextUtils.isEmpty(termContent));
    }

    private void back(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle(R.string.back_title);
        builder.setMessage(R.string.back_confirm_not_to_add);
        builder.setPositiveButton(R.string.yes, (dialog, which) -> Navigation.findNavController(v).navigate(R.id.action_favoriteTermUpdateFragment_to_favoriteTermListFragment));
        builder.setNegativeButton(R.string.no, (dialog, which) -> {
        });

        builder.create().show();
    }

    private void deleteItem(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle(R.string.delete_title);
        builder.setMessage(R.string.back_confirm_to_delete);
        builder.setPositiveButton(R.string.yes, (dialog, which) -> {
            termViewModel.deleteTerm(term);
            Navigation.findNavController(v).navigate(R.id.action_favoriteTermUpdateFragment_to_favoriteTermListFragment);
            Toast.makeText(requireContext(), R.string.delete_success, Toast.LENGTH_LONG).show();
        });

        builder.setNegativeButton(R.string.no, (dialog, which) -> {
        });
        builder.create().show();
    }

    private void shareItem(View v) {
        String termTitle = binding.updateFavoriteTermTitleTextField.getText().toString();
        String termContent = binding.updateFavoriteTermContentTextField.getText().toString();

        if (inputCheck(termTitle, termContent)) {
            Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SEND);
            shareIntent.putExtra(Intent.EXTRA_TEXT, termTitle + "\n\n" + termContent);
            shareIntent.setType("text/plain");

            startActivity(shareIntent);
        } else Toast.makeText(requireContext(), R.string.add_failed, Toast.LENGTH_LONG).show();
    }
}