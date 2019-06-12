package tech.mbsoft.simplemvvm.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import tech.mbsoft.simplemvvm.R;
import tech.mbsoft.simplemvvm.repository.model.CountryListModel;

public class CountryListAdapter extends ListAdapter<CountryListModel, CountryListAdapter.CountryListViewHolder> {

    CountryListItemClickListener countryListItemClickListener;


    public void setCountryListModels(ArrayList<CountryListModel> countryListModels) {

        submitList(countryListModels);
    }

    public CountryListAdapter(@NonNull DiffUtil.ItemCallback<CountryListModel> diffCallback, CountryListItemClickListener countryListItemClickListener) {
        super(diffCallback);
        this.countryListItemClickListener = countryListItemClickListener;
    }

    @NonNull
    @Override
    public CountryListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.country_item_row, parent, false);
        return new CountryListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryListViewHolder holder, int position) {
        holder.bind(getItem(position));

    }

    public class CountryListViewHolder extends RecyclerView.ViewHolder {

        View itemLayout;
        ImageView ivCountryFlag;
        TextView tvCountryName;

        public CountryListViewHolder(@NonNull View itemView) {
            super(itemView);

            itemLayout = itemView;
            ivCountryFlag = itemView.findViewById(R.id.ivCountryFlag);
            tvCountryName = itemView.findViewById(R.id.tvCountryName);
        }

        public void bind(final CountryListModel item) {
            tvCountryName.setText(item.getName());
            itemLayout.setOnClickListener(v -> countryListItemClickListener.onClick(item));
        }
    }
}
