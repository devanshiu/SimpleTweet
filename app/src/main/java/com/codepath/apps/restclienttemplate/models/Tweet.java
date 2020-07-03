package com.codepath.apps.restclienttemplate.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

import android.text.format.DateUtils;
import androidx.room.Entity;
import androidx.room.ForeignKey;

@Parcel

public class Tweet {

    public long id;
    public String body;
    public String createdAt;
    public User user;
    public String imageURL;

    // Empty constructor needed by the Parceler library
    public Tweet() {
    }

    public static Tweet fromJson(JSONObject jsonObject) throws JSONException {
        Tweet tweet = new Tweet();
        tweet.id = jsonObject.getLong("id");
        tweet.body = jsonObject.getString("text");
        tweet.createdAt = jsonObject.getString("created_at");
        tweet.user = User.fromJson(jsonObject.getJSONObject("user"));


        try {
            tweet.imageURL = jsonObject.getJSONObject("entities").getJSONArray("media").getJSONObject(0).getString("media_url_https");
            //JSONArray media = entities.getJSONArray("media");
            //JSONObject media_1 = media.getJSONObject(0);
            //tweet.imageURL = media_1.getString("media_url");
        } catch (JSONException e) {
            //tweet.imageURL = "No image";
        }

        return tweet;

    }

    public static List<Tweet> fromJsonArray(JSONArray jsonArray) throws JSONException {
        List<Tweet> tweets = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            tweets.add(fromJson(jsonArray.getJSONObject(i)));
        }
        return tweets;
    }
}
