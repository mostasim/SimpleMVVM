package tech.mbsoft.simplemvvm.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CountryRestApi {

    public static Retrofit getRetrofitInstance(){

        return new Retrofit.Builder()
                .baseUrl("http://restcountries.eu/rest/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static CountryService getApiService(){
        return getRetrofitInstance().create(CountryService.class);
    }
}
