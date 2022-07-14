package com.example.appsketballandroid;

import android.os.*;
import org.json.*;
import java.util.*;


import okhttp3.*;

public class OkHttpHandler {

    public OkHttpHandler() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    Map<String, String> credentialRetrieval(String url) throws Exception {
        Map<String, String> userCredentials = new HashMap<>();

        //Code from Postman </>
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = RequestBody.create(mediaType, "");
        Request request = new Request.Builder()
                .url(url)
                .method("POST", body)
                .build();
        Response response = client.newCall(request).execute();

        String data = response.body().string();
        // System.out.println(data);
        try {
            JSONObject json = new JSONObject(data);
            Iterator<String> keys = json.keys();

            //Iterating the JSON object(key,value)
            userCredentials.put("id", json.get("id").toString());
            userCredentials.put("username", json.get("username").toString());
            userCredentials.put("email", json.get("email").toString());
            userCredentials.put("password", json.get("password").toString());
            userCredentials.put("isAdmin", json.get("isAdmin").toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return userCredentials;
    }

    public void register(String url) throws Exception {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = RequestBody.create(mediaType, "");
        Request request = new Request.Builder()
                .url(url)
                .method("POST", body)
                .build();
        Response response = client.newCall(request).execute();
    }


    public List<Event> eventsRetrieval(String url) throws Exception{
        List<Event> events = new ArrayList<>();

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = RequestBody.create(mediaType, "");
        Request request = new Request.Builder()
                .url(url)
                .method("POST", body)
                .build();
        Response response = client.newCall(request).execute();

        String data = response.body().string();

        try {
            JSONArray json = new JSONArray(data);

            for(int i=0;i<json.length();i++) {
                JSONObject jsonObject = json.getJSONObject(i);
                Iterator<?> iterator = jsonObject.keys();
                HashMap<String,String> map = new HashMap<>();
                List<String> values = new ArrayList<>();
                while (iterator.hasNext()) {
                    Object key = iterator.next();
                    values.add(jsonObject.get(key.toString()).toString());
                }
                Event event = new Event(Integer.parseInt(values.get(0)), values.get(1), values.get(2), values.get(3), values.get(4), Integer.parseInt(values.get(5)), values.get(6));
                events.add(event);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return events;

    }

    public Team teamDetails(String url) throws Exception{

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = RequestBody.create(mediaType, "");
        Request request = new Request.Builder()
                .url(url)
                .method("POST", body)
                .build();
        Response response = client.newCall(request).execute();

        String data = response.body().string();

        int id;
        String name;
        String badge;
        String town;

        JSONObject json = new JSONObject(data);
        Iterator<String> keys = json.keys();
        //Iterating the JSON object(key,value)
        id = Integer.parseInt(json.get("id").toString());
        name = json.get("name").toString();
        badge = json.get("badge").toString();
        town = json.get("town").toString();
        Team team = new Team(id, name, badge, town);

        return team;

    }





}
