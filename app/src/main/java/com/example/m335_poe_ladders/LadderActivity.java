package com.example.m335_poe_ladders;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;

public class LadderActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<Ladder>> {

    private String viewTitle;
    private String jsonUrl;
    private Integer amountOfChars;
    private LadderAdapter ladderAdapter;
    private ConnectivityManager connMgr;
    private LoaderManager loaderManager;
    private Toast toast;

    private SwipeRefreshLayout swipeContainer;
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

        // Get league-info from main-activity then set title and url based on this league-info
        Bundle bundle = getIntent().getExtras();
        String league = bundle.getString("league");
        this.setTitleAndUrl(league);
        setTitle(this.viewTitle);

        // Initialise all properties
        this.amountOfChars = 50;
        this.connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        this.loaderManager = getLoaderManager();
        this.toast = Toast.makeText(this, "", Toast.LENGTH_SHORT);
        this.swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
        this.list = (ListView) findViewById(R.id.list);
        this.emptyList = (TextView) findViewById(R.id.empty_view);
        this.progressBar = (ProgressBar) findViewById(R.id.loading_indicator);

        // Configure swipeContainer -> delay of 200 milliseconds for user-experience
        this.swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeContainer.setRefreshing(false);
                        restartLoader();
                    }
                }, 200);
            }
        });

        // Add scroll-listener. Needed for scroll-down fix.
        this.list.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (list.getChildAt(0) != null) {
                    swipeContainer.setEnabled(list.getFirstVisiblePosition() == 0 && list.getChildAt(0).getTop() == 0);
                }
            }
        });

        // Add empty-view to list-view
        this.list.setEmptyView(this.emptyList);

        // Add adapter
        this.ladderAdapter = new LadderAdapter(this, new ArrayList<Ladder>());
        this.list.setAdapter(this.ladderAdapter);

        // Start loader
        startLoader();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.ladder_options, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.show_50_chars:
                this.amountOfChars = 50;
                restartLoader();
                break;
            case R.id.show_100_chars:
                this.amountOfChars = 100;
                restartLoader();
                break;
            case R.id.show_150_chars:
                this.amountOfChars = 150;
                restartLoader();
                break;
            case R.id.show_200_chars:
                this.amountOfChars = 200;
                restartLoader();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Starting Loader.
     * Get ladder-information and display.
     */
    public void startLoader() {
        this.progressBar.setVisibility(View.VISIBLE);

        NetworkInfo networkInfo = this.connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            this.loaderManager.initLoader(1, null, this);
        } else {
            this.showError("No internet-connection");
        }
    }

    /**
     * Restart Loader.
     * Get ladder-informations and display.
     */
    public void restartLoader() {

        this.emptyList.setText("");
        this.ladderAdapter.clear();

        this.progressBar.setVisibility(View.VISIBLE);

        NetworkInfo networkInfo = this.connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            this.loaderManager.restartLoader(1, null, this);
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
                this.jsonUrl = "https://api.pathofexile.com/ladders/Metamorph";
                this.viewTitle = "Metamorph Standard";
                break;
            case "metamorph_hardcore":
                this.jsonUrl = "https://api.pathofexile.com/ladders/Hardcore Metamorph";
                this.viewTitle = "Metamorph Hardcore";
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
        toast.setText("Loading " + this.amountOfChars + " Exiles.");
        toast.show();
        String url = this.jsonUrl + "?limit=" + this.amountOfChars;
        return new LadderLoader(this, url);
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<Ladder>> loader, ArrayList<Ladder> ladders) {
        if (ladders == null) {
            return;
        }

        this.progressBar.setVisibility(View.GONE);
        this.emptyList.setText(getString(R.string.no_exiles_found));

        updateUi(ladders);

        toast.setText("Exiles loaded.");
        toast.show();
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<Ladder>> loader) {
        this.ladderAdapter.clear();
    }
}
