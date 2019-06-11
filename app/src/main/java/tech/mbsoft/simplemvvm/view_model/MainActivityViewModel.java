package tech.mbsoft.simplemvvm.view_model;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;

import tech.mbsoft.simplemvvm.repository.CountryRepository;
import tech.mbsoft.simplemvvm.repository.model.CountryListModel;

public class MainActivityViewModel extends AndroidViewModel{

    private MutableLiveData<Boolean> isLoading=new MutableLiveData<>();
    private LiveData<ArrayList<CountryListModel>> countryList=new MutableLiveData<>();

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
    }

    public void setIsLoading(Boolean isLoading)
    {
        this.isLoading.postValue(isLoading);
    }
    public LiveData<Boolean> getIsLoading()
    {
        return isLoading;
    }
    public void setCountryList()
    {
        CountryRepository countryRepository= new CountryRepository();
        countryList=countryRepository.getCountryList();
    }
    public LiveData<ArrayList<CountryListModel>> getCountryList()
    {
        setIsLoading(true);
        setCountryList();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(6000);
                    Log.e("___VM___","sleeep");
                    setIsLoading(false);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();

        return countryList;
    }

}
