package milen.com.simplelibapp.api;


import java.util.List;

import milen.com.simplelibapp.api.api_models.Post;
import milen.com.simplelibapp.utils.Preferences;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET(Preferences.POSTS_ENDPOINT)
    Call<List<Post>> getAllPosts();
}
