package com.alekhin.javadict.fragments.list;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.alekhin.javadict.databinding.CardTermBinding;
import com.alekhin.javadict.room.Term;

import java.util.Collections;
import java.util.List;

public class TermListAdapter extends RecyclerView.Adapter<TermListAdapter.TermListViewHolder> {
    private List<Term> termList = Collections.emptyList();

    public static class TermListViewHolder extends RecyclerView.ViewHolder {
        private final CardTermBinding binding;

        public TermListViewHolder(@NonNull CardTermBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        private void bind(Term term) {
            binding.termTitle.setText(term.termTitle);
            binding.termContent.setText(term.termContent);
            binding.termFavorite.setChecked(term.termFavorite);

            binding.card.setOnClickListener(v -> {
                NavDirections action = TermListFragmentDirections.actionTermListFragmentToTermUpdateFragment(term);
                Navigation.findNavController(v).navigate(action);
            });
        }
    }

    @NonNull
    @Override
    public TermListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TermListViewHolder(CardTermBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TermListViewHolder holder, int position) {
        Term currentTerm = termList.get(position);
        holder.bind(currentTerm);
    }

    @Override
    public int getItemCount() {
        return termList.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setData(List<Term> termList) {
        this.termList = termList;
        notifyDataSetChanged();
    }
}