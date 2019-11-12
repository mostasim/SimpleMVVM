package tech.mbsoft.simplemvvm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.UiThread;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.ArrayList;

import tech.mbsoft.simplemvvm.adapter.CountryDiffCallback;
import tech.mbsoft.simplemvvm.adapter.CountryListAdapter;
import tech.mbsoft.simplemvvm.repository.model.CountryListModel;
import tech.mbsoft.simplemvvm.util.DetectConnection;
import tech.mbsoft.simplemvvm.view_model.MainActivityViewModel;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "__MAIN__";

    private ProgressBar pbIsLoading;
    private RecyclerView rvCountryList;
    private MainActivityViewModel mainActivityViewModel;
    private CountryListAdapter countryListAdapter;
    private FirebaseAnalytics mFirebaseAnalytics;
    //For changing network state
    private BroadcastReceiver networkStateReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetwork = manager.getActiveNetworkInfo();
            if (activeNetwork != null && activeNetwork.isConnected()) {
                observeCountryList();
            }
            Log.e(TAG, "network state change");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        registerReceiver(networkStateReceiver, new IntentFilter(android.net.ConnectivityManager.CONNECTIVITY_ACTION));

        pbIsLoading = findViewById(R.id.pbIsLoading);
        rvCountryList = findViewById(R.id.rvList);
        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);

        // layout manager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvCountryList.setLayoutManager(layoutManager);

        mainActivityViewModel.setIsLoading(true);
        mainActivityViewModel.getIsLoading().observe(this, aBoolean -> {
            if (aBoolean) {
                pbIsLoading.setVisibility(View.VISIBLE);
                rvCountryList.setVisibility(View.INVISIBLE);
            } else {
                pbIsLoading.setVisibility(View.INVISIBLE);
                rvCountryList.setVisibility(View.VISIBLE);
            }
        });
    }

    private void observeCountryList() {
        mainActivityViewModel.getCountryList().observe(this, countryListModels -> {

            if (countryListAdapter == null) {
                countryListAdapter = new CountryListAdapter(new CountryDiffCallback(),
                        //Item Click Listener
                        country -> {
                            updateList(countryListModels);
                            Toast.makeText(MainActivity.this, "" + country.getName(), Toast.LENGTH_SHORT).show();
                            Bundle bundle = new Bundle();
                            bundle.putString(FirebaseAnalytics.Param.ITEM_ID, country.getNumericCode());
                            bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, country.getName());
                            bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "country");
                            mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
                        });
            }
            rvCountryList.setAdapter(countryListAdapter);
            countryListAdapter.submitList(countryListModels);
            mainActivityViewModel.setIsLoading(false);
            Log.e(TAG, countryListModels.get(0).getName());
        });
    }

    private void updateList(ArrayList<CountryListModel> countryListModels) {
        countryListAdapter.submitList(countryListModels);
    }
    @UiThread


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(networkStateReceiver);
    }
}
