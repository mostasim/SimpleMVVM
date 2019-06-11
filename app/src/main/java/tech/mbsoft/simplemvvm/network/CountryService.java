package tech.mbsoft.simplemvvm.network;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import tech.mbsoft.simplemvvm.repository.model.CountryListModel;

public interface CountryService {

    //https://restcountries.eu/rest/v2/all
    @GET("all")
    Call<List<CountryListModel>> listCountry();

}
