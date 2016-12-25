package fkn.kopilka;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class GoalActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static final String APP_PREFERENCES_NAME = "Name";
    public static final String APP_PREFERENCES = "MyGoals";
    public static final Integer APP_PREFERENCES_ID = -1;
    public static final Integer APP_PREFERENCES_GMONEY = -1;
    public static final String APP_PREFERENCES_DESC = "Description";
    public ListView lvGoal;
    public GoalAdapter adapter;
    public List<Goal> mGoallist;
    public String name, description;
    public Integer id = 0;
    public Integer money = 0;
    SharedPreferences mGoals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_goal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mGoals = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        //Goals= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        lvGoal = (ListView) findViewById(R.id.goal_list);
        mGoallist = new ArrayList<>();
        ProgressBar pb = (ProgressBar) findViewById(R.id.progressBar4);
        //pb.setProgress();


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_main) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        if (id == R.id.nav_stats) {
            Intent intent = new Intent(this, StatisticsActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    protected void add_goal(View v) {
        Intent intent = new Intent(this, AddGoalActivity.class);
        startActivityForResult(intent, 1);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == 1) {
            name = mGoals.getString(APP_PREFERENCES_NAME, "");
            description = mGoals.getString(APP_PREFERENCES_DESC, "");
            money = mGoals.getInt(String.valueOf(APP_PREFERENCES_GMONEY), -1);
            id = mGoals.getInt(String.valueOf(APP_PREFERENCES_ID), id);
            mGoallist.add(new Goal(id, name, money, description));
            adapter = new GoalAdapter(mGoallist, getApplicationContext());
            lvGoal.setAdapter(adapter);
        } else Toast.makeText(this, "Input right data", Toast.LENGTH_SHORT).show();
    }

}

