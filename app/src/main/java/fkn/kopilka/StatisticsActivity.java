package fkn.kopilka;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class StatisticsActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    int total_many,complete_goals;
    public static final String APP_PREFERENCES = "mysettings";
    public static final String APP_PREFERENCES_TOTAL_MONEY = "cena";
    public static final String APP_PREFERENCES_COMPLETE_GOALS = "goals";
    SharedPreferences mSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        TextView total = (TextView) findViewById(R.id.total_many);
        TextView complete = (TextView) findViewById(R.id.complete_goals);

        if (mSettings.contains(APP_PREFERENCES_TOTAL_MONEY)) {
            total.setText("Всего вы накопили: "+mSettings.getString(APP_PREFERENCES_TOTAL_MONEY," ")+" p.");
        }
        else
            total.setText("Вы ещё ничего не накопили.");

        if (mSettings.contains(APP_PREFERENCES_COMPLETE_GOALS)) {
            complete.setText("Всего выполнено целей: "+mSettings.getString(APP_PREFERENCES_COMPLETE_GOALS," "));
        }
        else
            complete.setText("Вы ещё ничего не выполнили.");


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

        if (id == R.id.nav_goals) {
            Intent intent = new Intent(this,GoalActivity.class);
            startActivity(intent);
        }
        if (id == R.id.nav_main) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

