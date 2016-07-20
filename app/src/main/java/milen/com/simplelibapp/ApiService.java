package milen.com.simplelibapp;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET(Preferences.POSTS_ENDPOINT)
    Call<List<Post>> getAllPosts();
}
