package com.gamecodeschool.webservice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.gamecodeschool.webservice.Interface.JsonPlaceHolderApi;
import com.gamecodeschool.webservice.model.Post;
import com.gamecodeschool.webservice.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView mJsonTxtView, mUsernameTxtView, mIdTxtView, mPasswordTxtView, mFirstNameTxtView, mLastNameTxtView;
    private Retrofit retrofit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mJsonTxtView = findViewById(R.id.jsonText);
        mIdTxtView = findViewById(R.id.txtId);
        mUsernameTxtView = findViewById(R.id.txtUsername);
        mPasswordTxtView = findViewById(R.id.txtPassword);
        mFirstNameTxtView = findViewById(R.id.txtFirstName);
        mLastNameTxtView = findViewById(R.id.txtLastName);

        retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.17:3000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        getUsuarios();
    }

    private void getUsuarios(){
        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        Call<List<User>> call = jsonPlaceHolderApi.getUsers();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (!response.isSuccessful()) {
                    mJsonTxtView.setText("Codigo: " + response.code());
                    return;
                }

                mJsonTxtView.setText("");

                List<User> postList = response.body();
                if (postList!=null){
                    for (User post : postList) {
                        String content = "";
                        content += "id: " + post.getId() + "\n";
                        content += "username: " + post.getUsername() + "\n";
                        content += "password: " + post.getPassword() + "\n";
                        content += "first Name: " + post.getFirstName() + "\n";
                        content += "last Name: " + post.getLastName() + "\n\n";
                        mJsonTxtView.append(content);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error en get Users", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void postUser(View view){
        String username = mUsernameTxtView.getText().toString();
        String password = mPasswordTxtView.getText().toString();
        String firstName = mFirstNameTxtView.getText().toString();
        String lastName = mLastNameTxtView.getText().toString();

        User user = new User(1,username,password,firstName,lastName,null,null);
        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        Call<User> call = jsonPlaceHolderApi.postUser(user);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(MainActivity.this, response.code(), Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this, "Usuario guardado", Toast.LENGTH_SHORT).show();
                    getUsuarios();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error en Post User", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void updateUser(View view){
        int id = Integer.parseInt(mIdTxtView.getText().toString());
        String username = mUsernameTxtView.getText().toString();
        String password = mPasswordTxtView.getText().toString();
        String firstName = mFirstNameTxtView.getText().toString();
        String lastName = mLastNameTxtView.getText().toString();

        User user = new User(1,username,password,firstName,lastName,null,null);
        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        Call<User> call = jsonPlaceHolderApi.putUser(id,user);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(MainActivity.this, response.code(), Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "Usuario actualizado", Toast.LENGTH_SHORT).show();
                    getUsuarios();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error en Update User", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void deleteUser(View view){
        int id = Integer.parseInt(mIdTxtView.getText().toString());
        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        Call<User> call = jsonPlaceHolderApi.deleteUser(id);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(MainActivity.this, response.code(), Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "Usuario eliminado", Toast.LENGTH_SHORT).show();
                    getUsuarios();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error en Delete User", Toast.LENGTH_SHORT).show();
            }
        });

        getUsuarios();
    }

    public void getUser(View view){
        int id = Integer.parseInt(mIdTxtView.getText().toString());
        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        Call<User> call = jsonPlaceHolderApi.getUser(id);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(MainActivity.this, response.code(), Toast.LENGTH_SHORT).show();
                }else{
                    User user = response.body();
                    if (user == null){
                        Toast.makeText(MainActivity.this, "Usuario no encontrado", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    mIdTxtView.setText(String.valueOf(user.getId()));
                    mUsernameTxtView.setText(user.getUsername());
                    mPasswordTxtView.setText(user.getPassword());
                    mFirstNameTxtView.setText(user.getFirstName());
                    mLastNameTxtView.setText(user.getLastName());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error en Update User", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
