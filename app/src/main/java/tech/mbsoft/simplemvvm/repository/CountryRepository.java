package tech.mbsoft.simplemvvm.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tech.mbsoft.simplemvvm.network.CountryRestApi;
import tech.mbsoft.simplemvvm.repository.model.CountryListModel;

public class CountryRepository {


    public static final String TAG = "___Repository___";

    public LiveData<ArrayList<CountryListModel>> getCountryList() {

        final MutableLiveData<ArrayList<CountryListModel>> data = new MutableLiveData<>();

        CountryRestApi.getApiService().listCountry().enqueue(new Callback<ArrayList<CountryListModel>>() {
            @Override
            public void onResponse(Call<ArrayList<CountryListModel>> call, Response<ArrayList<CountryListModel>> response) {
              /*  new Thread(()->{
                    try {
                        Thread.sleep(2000);
                        data.postValue(response.body());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }).start();*/
                Log.e(TAG, "Fetching...");
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<CountryListModel>> call, Throwable t) {
                Log.e(TAG, t.getLocalizedMessage());
            }
        });
        return data;
    }
}
