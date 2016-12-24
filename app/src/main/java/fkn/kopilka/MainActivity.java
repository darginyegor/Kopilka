package fkn.kopilka;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
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
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public int coins,value;

    public static final String APP_PREFERENCES = "mysettings";
    public static final String APP_PREFERENCES_MONEY = "cena";
    SharedPreferences mSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        TextView textView = (TextView) findViewById(R.id.balance);

        if (mSettings.contains(APP_PREFERENCES_MONEY)) {
            textView.setText(mSettings.getString(APP_PREFERENCES_MONEY," ")+" p.");
        }
        coins = Integer.valueOf(mSettings.getString(APP_PREFERENCES_MONEY, " "));

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


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

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void add (View v)
    {
        Intent intent = new Intent(this,IncreaseSumActivity.class);
        startActivityForResult(intent,1);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        Log.d("myLogs", "requestCode = " + APP_PREFERENCES_MONEY+"        "+coins);//pishet in log

        if (resultCode==1)
        {

            value = Integer.valueOf((mSettings.getString(APP_PREFERENCES_MONEY," ")));

            coins = coins + value;
            TextView textView = (TextView) findViewById(R.id.balance);
            textView.setText(Integer.toString(coins) + " р.");
            SharedPreferences.Editor editor = mSettings.edit();

            String x = Integer.toString(coins);
            editor.putString(APP_PREFERENCES_MONEY, x);

            editor.apply();
        }
    }

}
