package com.example.m335_poe_ladders;

import android.util.Log;

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
import java.nio.charset.Charset;
import java.util.ArrayList;

public class QueryUtils {

    private static final String LOG_TAG = LadderLoader.class.getSimpleName();
    private ImageService imageService;

    public QueryUtils() {
        this.imageService = new ImageService();
    }

    /**
     * Returns new URL object from the given string URL.
     */
    public URL createUrl(String stringUrl) {
        URL url;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException exception) {
            Log.e(LOG_TAG, "Error with creating URL", exception);
            return null;
        }
        return url;
    }

    /**
     * Make an HTTP request to the given URL and return a String as the response.
     */
    public String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.connect();
            inputStream = urlConnection.getInputStream();
            jsonResponse = readFromStream(inputStream);
        } catch (IOException e) {
            Log.e("MakeHttpRequest", "Problem parsing the poe JSON results", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    /**
     * Generate ladderitem-list from given JSON-String.
     *
     * @param ladderJSON JSON-String.
     * @return Earthquake-list.
     */
    public ArrayList<Ladder> generateLadderList(String ladderJSON) {

        ArrayList<Ladder> ladders = new ArrayList<>();
        try {

            // Build up a list of ladderItem objects with the corresponding data.
            JSONObject response = new JSONObject(ladderJSON);
            JSONArray entries = response.getJSONArray("entries");

            for (int x = 0; x < entries.length(); x++) {
                JSONObject ladderItem = entries.getJSONObject(x);
                JSONObject character = ladderItem.getJSONObject("character");

                Integer rank = ladderItem.getInt("rank");
                String name = character.getString("name");
                Integer level = character.getInt("level");
                String classInfo = character.getString("class");
                Integer imageId = this.imageService.getImageId(classInfo);

                // Create ladderItem and add to ladderItem-list
                Ladder ladderObject = new Ladder(name, rank, level, imageId);
                ladders.add(ladderObject);
            }
        } catch (JSONException e) {
            Log.e("GenerateLadderList", "Problem parsing the ladder JSON results", e);
        }

        // Return earthquake-list.
        return ladders;
    }

    /**
     * Convert inputStream to full string.
     *
     * @param inputStream InputStream to read from.
     * @return Full string.
     * @throws IOException If Failed.
     */
    private String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }
}
