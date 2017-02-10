package com.fragment.login.urlexample;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    int userId, id;
    String title, body;
    Exception exception;
    ListView listView;

    CustomAdapter customadapter;

    ArrayList<Post> postArrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }


        listView = (ListView) findViewById(R.id.list_view);


        HttpURLConnection connection = null;

        URL url = null;
        try

        {
            url = new URL("http://jsonplaceholder.typicode.com/posts");
            try {
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                InputStream stream = connection.getInputStream();

                BufferedReader reader = new BufferedReader(new InputStreamReader(stream));


                StringBuffer buffer = new StringBuffer();
                String line = " ";
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }
                String bufferString = buffer.toString();


                try {
                    JSONArray rootarray = new JSONArray(bufferString);

                    Log.d("myapp", "length" + rootarray.length());
                    postArrayList = new ArrayList<>();

                    for (int i = 0; i < rootarray.length(); i++)
                    {
                        JSONObject jsonObject = rootarray.getJSONObject(i);

                        int userId = jsonObject.getInt("userId");
                        int id = jsonObject.getInt("id");
                        String title = jsonObject.getString("title");
                        String body = jsonObject.getString("body");

                        Log.d("myapp", "userId:" + userId);

                        Post p = new Post();
                        p.setId(id);
                        p.setUserid(userId);
                        p.setTitle(title);
                        p.setDescription(body);

                        postArrayList.add(p);
                    }
                    Log.d("myapp","array size"+ postArrayList.size());
                    customadapter = new CustomAdapter(this, postArrayList);
                    listView.setAdapter(customadapter);

                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
        }
    }
}

