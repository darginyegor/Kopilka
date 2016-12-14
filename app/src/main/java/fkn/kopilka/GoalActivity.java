package fkn.kopilka;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
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

public class GoalActivity extends AppCompatActivity {
    public static final String APP_PREFERENCES_NAME = "Name";
    public static final String APP_PREFERENCES = "MyGoals";
    public static final Integer APP_PREFERENCES_ID = -1;
    public static final Integer APP_PREFERENCES_GMONEY=-1;
    public static final String APP_PREFERENCES_DESC = "Description";
    public ListView lvGoal;
    public GoalAdapter adapter;
    public List<Goal> mGoallist;
    public String name,description;
    public Integer id=0;
    public Integer money=0;
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
    }

    protected void add_goal(View v){
        Intent intent = new Intent(this,AddGoalActivity.class);
        startActivityForResult(intent,1);
    }
    protected void  onActivityResult(int requestCode,int resultCode,Intent data) {
        if (resultCode==1) {
            name = mGoals.getString(APP_PREFERENCES_NAME, "");
            description = mGoals.getString(APP_PREFERENCES_DESC, "");
            money = mGoals.getInt(String.valueOf(APP_PREFERENCES_GMONEY), -1);
            id = mGoals.getInt(String.valueOf(APP_PREFERENCES_ID), id);
            mGoallist.add(new Goal(id, name, money, description));
            adapter = new GoalAdapter(mGoallist, getApplicationContext());
            lvGoal.setAdapter(adapter);
        }
        else Toast.makeText(this,"Input right data",Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
