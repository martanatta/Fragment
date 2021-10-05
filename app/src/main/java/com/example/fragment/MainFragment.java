package com.example.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private PeopleAdapter mAdapter;
    private List<People> mItems = new ArrayList<>();

    public MainFragment() {
    }

    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.e("MainFragment", "onAttach");
    }

    @Override
    public void onCreate(@NonNull Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("MainFragment", "onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.e("MainFragment", "onCreateView");
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.e("MainFragment", "onViewCreated");

        mRecyclerView = view.findViewById(R.id.recycler_view);

        mAdapter = new PeopleAdapter(getActivity(), mItems);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mAdapter);

        PeopleClickListener listener = new PeopleClickListener() {
            @Override
            public void onClick(int position) {
                People item = mItems.get(position);

                Intent intent = new Intent(getActivity(), PeopleActivity.class);
                intent.putExtra("people_id", item.id);
                startActivity(intent);
            }
        };

        mAdapter.setOnItemClickListener(listener);
    }

    public void onStart() {
        super.onStart();
        Log.e("MainFragment", "onStart");
    }

    public void onResume() {
        super.onResume();
        Log.e("MainFragment", "onResume");
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

    public void onPause() {
        super.onPause();
        Log.e("MainFragment", "onPause");
    }

    public void onStop() {
        super.onStop();
        Log.e("MainFragment", "onStop");
    }

    public void onDestroy() {
        super.onDestroy();
        Log.e("MainFragment", "onDestroy");
    }

    public void onDetach() {
        super.onDetach();
        Log.e("MainFragment", "onDetach");
    }
}