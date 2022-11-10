package com.example.rxpresentation;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.rxpresentation.Adapter.PostListAdapter;
import com.example.rxpresentation.Model.UserModel;
import com.example.rxpresentation.Repository.FirstRepository;
import com.example.rxpresentation.ViewModel.MainActivityViewModel;
import com.example.rxpresentation.ui.main.SectionsPagerAdapter;
import com.example.rxpresentation.databinding.ActivityMainBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity {

    MainActivityViewModel mainActivityViewModel;

    private ActivityMainBinding binding;
    RecyclerView recyclerUserList;
    PostListAdapter adapter;
    LinearLayoutManager linearLayoutManager;
    AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_main);

        dialog = new AlertDialog.Builder(MainActivity.this).create();
        dialog.setCancelable(false);
        dialog.show();

        reSetRecycler();

        mainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        // Setup default
        FloatingActionButton defaultButton = (FloatingActionButton) findViewById(R.id.def);
        defaultButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                displayDefault();
            }
        });

        // Setup Minus
        FloatingActionButton minusButton = (FloatingActionButton) findViewById(R.id.minus);
        minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                displayMinus();
            }
        });

        // Setup Plus
        FloatingActionButton plusButton = (FloatingActionButton) findViewById(R.id.plus);
        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                displayPlus();
            }
        });

        // Setup Clean
        FloatingActionButton cleanButton = (FloatingActionButton) findViewById(R.id.clean);
        cleanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                displayClear();
            }
        });

        displayDefault();

    }

    private void reSetRecycler() {
        recyclerUserList = findViewById(R.id.recyclerUserList);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerUserList.setLayoutManager(linearLayoutManager);
    }

    public void displayDefault() {
        mainActivityViewModel.getUserModel().observe(this, userModels -> {
            adapter = new PostListAdapter(this, userModels);
            adapter.notifyDataSetChanged();
            recyclerUserList.setAdapter(adapter);
            dialog.dismiss();
        });
    }

    public void displayMinus() {
        mainActivityViewModel.getModelByUser().observe(this, userModels -> {
            adapter = new PostListAdapter(this, userModels);
            adapter.notifyDataSetChanged();
            recyclerUserList.setAdapter(adapter);
            dialog.dismiss();
        });
    }

    public void displayPlus() {
        Observable.just(
                new UserModel(1, 1, "totooooo", "titiiiii"),
                new UserModel(1, 2, "totooooo2", "titiiiii2"),
                new UserModel(2, 3, "totooooo3", "titiiiii3"),
                new UserModel(3, 4, "totooooo4", "titiiiii4"),
                new UserModel(4, 5, "totooooo5", "titiiiii5"),
                new UserModel(4, 6, "totooooo6", "titiiiii6")
                )
                .filter(userModel -> 1 < userModel.getId())
                .buffer(3)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(userModels -> {
                    adapter.clear();
                    Log.e("MainActivity", "L'id en cours est : " + userModels.get(0).getId());
                    adapter = new PostListAdapter(this, userModels);
                    adapter.notifyDataSetChanged();
                    recyclerUserList.setAdapter(adapter);
                    dialog.dismiss();
                }, e -> Log.e("MainActivity", "FUCK, woops (?) :D : " + e));
    }

    public void displayClear() {
        adapter.clear();
    }
}