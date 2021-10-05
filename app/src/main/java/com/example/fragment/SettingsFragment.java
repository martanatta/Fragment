package com.example.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SettingsFragment extends Fragment {

    public SettingsFragment() {
    }

    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.e("SettingsFragment", "onAttach");
    }

    @Override
    public void onCreate(@NonNull Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("SettingsFragment", "onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.e("SettingsFragment", "onCreateView");
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.e("SettingsFragment", "onViewCreated");
    }

    public void onStart() {
        super.onStart();
        Log.e("SettingsFragment", "onStart");
    }

    public void onResume() {
        super.onResume();
        Log.e("SettingsFragment", "onResume");
    }

    public void onPause() {
        super.onPause();
        Log.e("SettingsFragment", "onPause");
    }

    public void onStop() {
        super.onStop();
        Log.e("SettingsFragment", "onStop");
    }

    public void onDestroy() {
        super.onDestroy();
        Log.e("SettingsFragment", "onDestroy");
    }

    public void onDetach() {
        super.onDetach();
        Log.e("SettingsFragment", "onDetach");
    }
}