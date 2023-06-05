package com.alekhin.javadict.fragments.favorite;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.alekhin.javadict.databinding.CardFavoriteTermBinding;
import com.alekhin.javadict.room.Term;

import java.util.Collections;
import java.util.List;

public class FavoriteTermListAdapter extends RecyclerView.Adapter<FavoriteTermListAdapter.FavoriteTermListViewHolder> {
    private List<Term> favoriteTermList = Collections.emptyList();

    public static class FavoriteTermListViewHolder extends RecyclerView.ViewHolder {
        CardFavoriteTermBinding binding;

        public FavoriteTermListViewHolder(@NonNull CardFavoriteTermBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        private void bind(Term favoriteTerm) {
            binding.favoriteTermTitle.setText(favoriteTerm.termTitle);
            binding.favoriteTermContent.setText(favoriteTerm.termContent);

            binding.favoriteCard.setOnClickListener(v -> {
                NavDirections action = FavoriteTermListFragmentDirections.actionFavoriteTermListFragmentToFavoriteTermUpdateFragment(favoriteTerm);
                Navigation.findNavController(v).navigate(action);
            });
        }
    }

    @NonNull
    @Override
    public FavoriteTermListAdapter.FavoriteTermListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FavoriteTermListViewHolder(CardFavoriteTermBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteTermListAdapter.FavoriteTermListViewHolder holder, int position) {
        Term currentFavoriteTerm = favoriteTermList.get(position);
        holder.bind(currentFavoriteTerm);
    }

    @Override
    public int getItemCount() {
        return favoriteTermList.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setFavoriteData(List<Term> termList) {
        this.favoriteTermList = termList;
        notifyDataSetChanged();
    }
}