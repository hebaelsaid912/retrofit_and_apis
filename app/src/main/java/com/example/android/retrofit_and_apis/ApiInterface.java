package com.example.android.retrofit_and_apis;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {
    //Resource
    // @GET("posts/2")
    @GET("posts/{id}")
    public Call<Post> getPost(@Path("id") int postId);
    //use Quary
    @GET("comments")
    public Call<List<Comment>> getComments(@Query("postId") String postId);
    @POST("posts")
    public Call<Post> storePost(@Body Post post);
    //post Map
   @POST("posts")
   public Call<Post> storePost_2(@Body HashMap<Object,Object> map);
}
