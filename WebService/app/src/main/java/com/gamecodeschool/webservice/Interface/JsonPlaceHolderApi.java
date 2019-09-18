package com.gamecodeschool.webservice.Interface;

import com.gamecodeschool.webservice.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface JsonPlaceHolderApi {
    @GET("api/usuarios")
    Call<List<User>> getUsers();

    @POST("api/usuarios")
    Call<User> postUser(@Body User user);

    @GET("api/usuarios/{id}")
    Call<User> getUser(@Path("id") int id);

    @PUT("api/usuarios/{id}")
    Call<User> putUser(@Path("id") int id, @Body User user);

    @DELETE("api/usuarios/{id}")
    Call<User> deleteUser(@Path("id") int id);

    @GET("users")
    Call<List<User>> getUser2(@Query("email") String email, @Query("username") String username);
}
