package tech.mbsoft.simplemvvm.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CountryRestApi {

    private static Retrofit getRetrofitInstance() {

        return new Retrofit.Builder()
                .baseUrl(RestApiConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static CountryService getApiService() {
        return getRetrofitInstance().create(CountryService.class);
    }
}
