package com.example.m335_poe_ladders;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class LadderActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<Ladder>> {

    private String viewTitle;
    private String jsonUrl;
    private LadderAdapter ladderAdapter;

    private ListView list;
    private TextView emptyList;
    private ProgressBar progressBar;

    public String getJsonUrl() {
        return jsonUrl;
    }

    public String getViewTitle() {
        return viewTitle;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ladder);

        Bundle bundle = getIntent().getExtras();
        String league = bundle.getString("league");
        this.setTitleAndUrl(league);

        setTitle(this.viewTitle);

        this.list = (ListView) findViewById(R.id.list);
        this.emptyList = (TextView) findViewById(R.id.empty_view);
        this.progressBar = (ProgressBar) findViewById(R.id.loading_indicator);

        this.list.setEmptyView(this.emptyList);

        this.ladderAdapter = new LadderAdapter(this, new ArrayList<Ladder>());
        this.list.setAdapter(this.ladderAdapter);

        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            LoaderManager loaderManager = getLoaderManager();
            loaderManager.initLoader(1, null, this);
        } else {
            this.showError("No internet-connection");
        }
    }

    /**
     * Set viewTitle for activity.
     * Set url to connect.
     *
     * @param league League to connect.
     */
    public void setTitleAndUrl(String league) {
        switch (league) {
            case "metamorph_standard":
                this.jsonUrl = "https://api.pathofexile.com/ladders/Metamorph?limit=50";
                this.viewTitle = "Metamorph Standard";
                break;
            case "metamorph_hardcore":
                this.jsonUrl = "https://api.pathofexile.com/ladders/Hardcore Metamorph?limit=50";
                this.viewTitle = "Metamorph Hardcore";
                break;
            default:
                this.jsonUrl = "https://api.pathofexile.com/ladders/Metamorph?limit=50";
                this.viewTitle = "Metamorph Standard";
                break;
        }
    }

    /**
     * Update ladder-adapter with new ladder-list.
     *
     * @param ladders new ladder-informations.
     */
    private void updateUi(ArrayList<Ladder> ladders) {
        this.ladderAdapter.clear();
        this.ladderAdapter.addAll(ladders);
    }

    /**
     * Display error.
     */
    private void showError(String message) {
        this.progressBar.setVisibility(View.GONE);
        this.emptyList.setText(message);
    }

    @Override
    public Loader<ArrayList<Ladder>> onCreateLoader(int i, Bundle bundle) {
        return new LadderLoader(this, jsonUrl);
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<Ladder>> loader, ArrayList<Ladder> ladders) {
        if (ladders == null) {
            return;
        }

        this.progressBar.setVisibility(View.GONE);
        this.emptyList.setText("No players found");

        updateUi(ladders);
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<Ladder>> loader) {
        this.ladderAdapter.clear();
    }
}
