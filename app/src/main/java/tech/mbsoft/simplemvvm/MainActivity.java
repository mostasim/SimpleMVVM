package tech.mbsoft.simplemvvm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

import tech.mbsoft.simplemvvm.repository.model.CountryListModel;
import tech.mbsoft.simplemvvm.view_model.MainActivityViewModel;

public class MainActivity extends AppCompatActivity {

    private ProgressBar pbIsLoading;
    private RecyclerView rvCountryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pbIsLoading = findViewById(R.id.pbIsLoading);
        rvCountryList =  findViewById(R.id.rvList);
        MainActivityViewModel mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);

        mainActivityViewModel.setIsLoading(true);
        mainActivityViewModel.getIsLoading().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean)
                {
                    pbIsLoading.setVisibility(View.VISIBLE);
                    rvCountryList.setVisibility(View.INVISIBLE);
                }else
                {
                    pbIsLoading.setVisibility(View.INVISIBLE);
                    rvCountryList.setVisibility(View.VISIBLE);
                }
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
