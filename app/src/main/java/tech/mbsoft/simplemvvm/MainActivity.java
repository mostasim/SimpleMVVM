package tech.mbsoft.simplemvvm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

import tech.mbsoft.simplemvvm.repository.model.CountryListModel;
import tech.mbsoft.simplemvvm.view_model.MainActivityViewModel;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainActivityViewModel mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);

        mainActivityViewModel.setIsLoading(true);
        mainActivityViewModel.getIsLoading().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                Toast.makeText(MainActivity.this, "isLoading.."+aBoolean, Toast.LENGTH_SHORT).show();
            }
        });

        mainActivityViewModel.getCountryList().observe(this, new Observer<ArrayList<CountryListModel>>() {
            @Override
            public void onChanged(ArrayList<CountryListModel> countryListModels) {
                Log.e("__Main__",countryListModels.get(0).getName());
            }
        });

    }
}
