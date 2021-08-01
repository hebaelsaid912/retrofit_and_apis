package com.example.android.retrofit_and_apis;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Post post1 = new Post(1,"Heba Elsaid","Hi, My name is Heba Habhoba :) ");
        HashMap<Object , Object> postHashMap = new HashMap<>();
        postHashMap.put("userId",9);
        postHashMap.put("title","Habhobaaaaaaa");
        postHashMap.put("body","popopiidsfkdfgugsduftyua;lsdfkkldjfuyu");
        TextView title = findViewById(R.id.postTitle_tv);
        TextView comment = findViewById(R.id.comment_tv);
        TextView postPost = findViewById(R.id.post_tv);
        TextView postMap = findViewById(R.id.postMap_tv);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiInterface apiInterface = retrofit.create(ApiInterface.class);


        Call<Post> call = apiInterface.getPost(2);
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                title.setText(response.body().getTitle());
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                title.setText(t.getMessage());
            }
        });



        Call<List<Comment>> listCall = apiInterface.getComments("1");
       listCall.enqueue(new Callback<List<Comment>>() {
           @Override
           public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
               comment.setText(response.body().get(1).getBody());
           }

           @Override
           public void onFailure(Call<List<Comment>> call, Throwable t) {
               comment.setText(t.getMessage());
           }
       });


       Call<Post> postCall = apiInterface.storePost(post1);
       postCall.enqueue(new Callback<Post>() {
           @Override
           public void onResponse(Call<Post> call, Response<Post> response) {
               postPost.setText(response.body().getTitle());
           }

           @Override
           public void onFailure(Call<Post> call, Throwable t) {
               postPost.setText(t.getMessage());
           }
       });


       Call<Post> postCallMap = apiInterface.storePost_2(postHashMap);
       postCallMap.enqueue(new Callback<Post>() {
           @Override
           public void onResponse(Call<Post> call, Response<Post> response) {
               postMap.setText(response.body().getBody());
           }

           @Override
           public void onFailure(Call<Post> call, Throwable t) {
               postMap.setText(t.getMessage());
           }
       });

    }
}