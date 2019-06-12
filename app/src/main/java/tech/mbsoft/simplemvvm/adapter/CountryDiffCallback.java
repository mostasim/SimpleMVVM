package tech.mbsoft.simplemvvm.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import tech.mbsoft.simplemvvm.repository.model.CountryListModel;

public class CountryDiffCallback extends DiffUtil.ItemCallback<CountryListModel> {
    @Override
    public boolean areItemsTheSame(@NonNull CountryListModel oldItem, @NonNull CountryListModel newItem) {
        return oldItem.equals(newItem);
    }

    @Override
    public boolean areContentsTheSame(@NonNull CountryListModel oldItem, @NonNull CountryListModel newItem) {
        return oldItem.equals(newItem);
    }
}
