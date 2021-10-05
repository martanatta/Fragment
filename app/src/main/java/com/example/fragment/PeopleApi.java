package com.example.fragment;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

interface PeopleApi {
    @GET("/people")
    Call<List<People>> getAllPeople();

    @GET("/people/{id}")
    Call<People> getOnePeople(@Path("id") int peopleId);

    @Headers("Content-Type: application/json")
    @POST("/people")
    Call<People> sendPeople(@Body People object);

    @DELETE("people/{id}")
    Call<People> deletePeople(@Path("id") int peopleId);
}
