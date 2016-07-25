package milen.com.simplelibapp.ui.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemSelected;
import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;
import io.realm.Realm;
import milen.com.simplelibapp.R;
import milen.com.simplelibapp.api.ApiService;
import milen.com.simplelibapp.api.api_models.Post;
import milen.com.simplelibapp.realm.database.RealmDB;
import milen.com.simplelibapp.realm.models.PostRealmObject;
import milen.com.simplelibapp.ui.adapters.PostAdapter;
import milen.com.simplelibapp.utils.Preferences;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.main_list)
    ListView main_list;

       @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Preferences.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);
        Call<List<Post>> posts_call = apiService.getAllPosts();



        posts_call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                List<Post> posts = response.body();
                PostAdapter adapter = new PostAdapter(getApplicationContext(), R.layout.post_item, posts);
                main_list.setAdapter(adapter);


                RealmDB realmDB = new RealmDB();
                for (Post post : posts) {
                    PostRealmObject realmPost = new PostRealmObject();
                    realmPost.setUserId(post.getUserId());
                    realmPost.setId(post.getId());
                    realmPost.setTitle(post.getTitle());
                    realmPost.setBody(post.getBody());

                    realmDB.add(realmPost);
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                showCrouton("Failed", Style.ALERT);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_getTitles) {
            RealmDB realmDB = new RealmDB();
            String firstTitle =((PostRealmObject) realmDB.findFirst(PostRealmObject.class)).getTitle();
            //todo make fragments
            showCrouton("Success! First Title is: " + firstTitle, Style.CONFIRM);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Crouton.cancelAllCroutons();
    }

    private void showCrouton(String message, Style style) {
        Crouton.makeText(this, message, style).show();
    }

    public void addFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        String fragmentClassName = fragment.getClass().getCanonicalName();
        transaction.add(android.R.id.content, fragment, fragmentClassName);
        transaction.addToBackStack(fragmentClassName);
        transaction.commit();
    }

    //todo make it work
    @OnItemSelected(R.id.main_list)
    void onItemSelected(int position) {
        showCrouton("You clicked at position " + position, Style.INFO);
    }
}
