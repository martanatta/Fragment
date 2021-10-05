package com.example.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PeopleActivity extends AppCompatActivity {
    private TextView mTextId;
    private TextView mTextName;
    private TextView mTextSurname;
    private TextView mTextEmail;
    private TextView mTextPhone;

    private Retrofit mRetrofit;
    private int mPeopleId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people);
        setTitle("Данные человека/удалить человека");
        initView();

        mRetrofit = new Retrofit.Builder()
                .baseUrl("http://95.142.45.128:1337")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        mPeopleId = extras.getInt("people_id");
        getData();
    }

    public void getData() {
        PeopleApi api = mRetrofit.create(PeopleApi.class);
        api.getOnePeople(mPeopleId).enqueue(new Callback<People>() {
            @Override
            public void onResponse(Call<People> call, Response<People> response) {
                Log.e("onResponse", "code: " + response.code());
                Log.e("onResponse", "message: " + response.message());
                initPerson(response.body());
            }

            @Override
            public void onFailure(Call<People> call, Throwable throwable) {
                Log.e("onFailure", throwable.toString());
            }
        });
    }

    public void deletePeople(View view) {
        PeopleApi api = mRetrofit.create(PeopleApi.class);
        api.deletePeople(mPeopleId).enqueue(new Callback<People>() {
            @Override
            public void onResponse(Call<People> call, Response<People> response) {
                Log.e("onResponse", "code: " + response.code());
                Log.e("onResponse", "message: " + response.message());
                if (response.code() == 200) {
                    Toast.makeText(PeopleActivity.this, "Человек удален", Toast.LENGTH_SHORT).show();
                    PeopleActivity.this.finish();
                }
            }

            @Override
            public void onFailure(Call<People> call, Throwable throwable) {
                Log.e("onFailure", throwable.toString());
            }
        });
    }

    private void initPerson(People item) {
        mTextId.setText(String.valueOf(item.getId()));
        mTextName.setText(item.name);
        mTextSurname.setText(item.surname);
        mTextEmail.setText(item.email);
        mTextPhone.setText(item.phone);
    }

    private void initView() {
        mTextId = findViewById(R.id.text_id);
        mTextName = findViewById(R.id.text_name);
        mTextSurname = findViewById(R.id.text_surname);
        mTextEmail = findViewById(R.id.text_email);
        mTextPhone = findViewById(R.id.text_phone);
    }
}