package com.example.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProfileFragment extends Fragment {
    private TextView mTextId, mTextName, mTextSurname, mTextEmail, mTextPhone;
    private TextInputEditText mEditId;
    private Button mBtnGo;

    Retrofit mRetrofit;

    public ProfileFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);

        mRetrofit = new Retrofit.Builder()
                .baseUrl("http://95.142.45.128:1337/people/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mBtnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
            }
        });
    }

    public void getData() {
        String id = mEditId.getText().toString();
        if (id.isEmpty()) {
            Toast.makeText(getActivity(), "Enter person's ID", Toast.LENGTH_SHORT).show();
            return;
        }

        int peopleId = Integer.parseInt(id);

        PeopleApi api = mRetrofit.create(PeopleApi.class);
        api.getOnePeople(peopleId).enqueue(new Callback<People>() {
            @Override
            public void onResponse(Call<People> call, Response<People> response) {
                initPerson(response.body());
            }

            @Override
            public void onFailure(Call<People> call, Throwable throwable) {
                Toast.makeText(getActivity(), "This ID is wrong", Toast.LENGTH_SHORT).show();
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

    private void initView(View view) {
        mEditId = view.findViewById(R.id.edit_id);
        mBtnGo = view.findViewById(R.id.btn_go);
        mTextId = view.findViewById(R.id.text_id);
        mTextName = view.findViewById(R.id.text_name);
        mTextSurname = view.findViewById(R.id.text_surname);
        mTextEmail = view.findViewById(R.id.text_email);
        mTextPhone = view.findViewById(R.id.text_phone);
    }
}