package com.example.m335_poe_ladders;

import android.content.Context;
import android.util.Log;
import android.content.AsyncTaskLoader;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class LadderLoader extends AsyncTaskLoader<ArrayList<Ladder>> {

    /**
     * URL to earthquake-information.
     */
    private String url;
    private QueryUtils queryUtils;

    public LadderLoader(Context context, String url) {
        super(context);
        this.url = url;
        this.queryUtils = new QueryUtils();
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public ArrayList<Ladder> loadInBackground() {

        // Create URL object
        URL url = this.queryUtils.createUrl(this.url);

        // Perform HTTP request to the URL and receive a JSON response
        String jsonResponse = "";
        try {
            if (url != null) {
                jsonResponse = this.queryUtils.makeHttpRequest(url);
            } else {
                return null;
            }
        } catch (IOException e) {
            Log.e("DoInBackground", "Problem parsing the poe JSON results", e);
        }

        // Return to onPostExecute with generated earthquake-list
        return this.queryUtils.generateLadderList(jsonResponse);
    }
}
