package tech.mbsoft.simplemvvm.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;

import tech.mbsoft.simplemvvm.repository.CountryRepository;
import tech.mbsoft.simplemvvm.repository.model.CountryListModel;

public class MainActivityViewModel extends AndroidViewModel {

    private CountryRepository countryRepository;

    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    private LiveData<ArrayList<CountryListModel>> countryList;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        countryRepository = new CountryRepository();
        //fetchCountryList();
    }

    public void setIsLoading(Boolean isLoading) {
        this.isLoading.postValue(isLoading);
    }

    public LiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    public void fetchCountryList() {
        countryList = countryRepository.getCountryList();
    }

    public LiveData<ArrayList<CountryListModel>> getCountryList() {
        setIsLoading(true);
        fetchCountryList();
        return countryList;
    }
}
