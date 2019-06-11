package tech.mbsoft.simplemvvm.view_model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

import tech.mbsoft.simplemvvm.repository.model.CountryListModel;

public class MainActivityViewModel extends ViewModel {

    private MutableLiveData<Boolean> isLoading;
    private MutableLiveData<ArrayList<CountryListModel>> countryList;

    public void setIsLoading(Boolean isLoading)
    {
        this.isLoading.setValue(isLoading);
    }
    public LiveData<Boolean> getIsLoading()
    {
        return isLoading;
    }
    public void setCountryList()
    {

    }
    public LiveData<ArrayList<CountryListModel>> getCountryList()
    {
        return countryList;
    }

}
