package tech.mbsoft.simplemvvm.adapter;

import tech.mbsoft.simplemvvm.repository.model.CountryListModel;

@FunctionalInterface
public interface CountryListItemClickListener {
    void onClick(CountryListModel country);
}
