package com.example.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PeopleListActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private PeopleAdapter mAdapter;
    private List<People> mItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_main);
        setTitle("People");
        initView();

        mAdapter = new PeopleAdapter(this, mItems);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);

        PeopleClickListener listener = new PeopleClickListener() {
            @Override
            public void onClick(int position) {
                People item = mItems.get(position);

                Intent intent = new Intent(PeopleListActivity.this, PeopleActivity.class);
                intent.putExtra("people_id", item.getId());
                startActivity(intent);
            }
        };

        mAdapter.setOnItemClickListener(listener);
        getData();
    }

    public void getData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://95.142.45.128:1337")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PeopleApi api = retrofit.create(PeopleApi.class);
        api.getAllPeople().enqueue(new Callback<List<People>>() {
            @Override
            public void onResponse(Call<List<People>> call, Response<List<People>> response) {
                Log.e("onResponse", "code: " + response.code());
                Log.e("onResponse", "string: " + response.toString());
                initData(response.body());
            }

            @Override
            public void onFailure(Call<List<People>> call, Throwable t) {
                Log.e("onResponse", t.toString());
            }
        });
    }

    private void initData(List<People> items) {
        mItems.clear();
        mItems.addAll(items);

        mAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        getData();
    }

    private void initView() {
        mRecyclerView = findViewById(R.id.recycler_view);
    }
}