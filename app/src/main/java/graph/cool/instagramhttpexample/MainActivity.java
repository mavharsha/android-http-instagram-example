package graph.cool.instagramhttpexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class MainActivity extends AppCompatActivity {

    private PostAdapter feedAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView feed = (RecyclerView) findViewById(R.id.feed);
        feedAdapter = new PostAdapter();
        feed.setAdapter(feedAdapter);
        feed.setLayoutManager(new LinearLayoutManager(this));

        getPosts();
    }

    private void getPosts() {
        final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();

        String query = "query {allPosts {id description imageUrl}}";
        JSONObject json = new JSONObject();
        try {
            json.put("query", query);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(JSON, json.toString());
        Request request = new Request.Builder()
                .url("https://api.graph.cool/simple/v1/__PROJECT_ID__")
                .post(body)
                .build();

        client.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                Log.d("MainActivity", "Error when fetching posts");
            }

            @Override
            public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
                final String body = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Gson gson = new Gson();
                        try {
                            JSONArray jsonPosts = new JSONObject(body).getJSONObject("data").getJSONArray("allPosts");
                            for (int i = 0; i < jsonPosts.length(); ++i) {
                                JSONObject postJson = jsonPosts.getJSONObject(i);
                                feedAdapter.addPost(gson.fromJson(postJson.toString(), Post.class));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }
}
