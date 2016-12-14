package fkn.kopilka;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AddGoalActivity extends AppCompatActivity {
    public static final String APP_PREFERENCES_NAME = "Name";
    public static final String APP_PREFERENCES = "MyGoals";
    public static final Integer APP_PREFERENCES_ID = -1;
    public static final Integer APP_PREFERENCES_DATE = -1;
    public static final Integer APP_PREFERENCES_GMONEY = -1;
    public static final String APP_PREFERENCES_DESC = "Description";
    private ListView lvGoal;
    private GoalAdapter adapter;
    private List<Goal> mGoallist;
    String name,description;
    Integer money,date=0;
    Integer id=1;
    SharedPreferences mGoals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_goal);
        lvGoal = (ListView)findViewById(R.id.goal_list);
        mGoallist=new ArrayList<>();
    }
    protected void add(View v)
    {
        EditText ename = (EditText) findViewById(R.id.ename);
        name = ename.getText().toString();
        EditText emoney = (EditText) findViewById(R.id.enumbermoney);
        money = Integer.parseInt(emoney.getText().toString());
        EditText edesc = (EditText) findViewById(R.id.edescription);
        description=edesc.getText().toString();
        //EditText edate = (EditText) findViewById(R.id.edata);
        //date = Integer.valueOf(edate.getText().toString());
        mGoals= getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = mGoals.edit();

        if ((description!=null)&&(name!=null)&&(money!=null))
        {
            mGoals.getInt(String.valueOf(APP_PREFERENCES_ID),id);
            ed.putString(APP_PREFERENCES_NAME,name);
            ed.putString(APP_PREFERENCES_DESC,description);
            ed.putInt(String.valueOf(APP_PREFERENCES_GMONEY),money);
            //ed.putInt(String.valueOf(APP_PREFERENCES_DATE),date);
            ed.apply();
            Toast.makeText(this,"Goal Added \n"+id+" " +name +" "+money+" "+description, Toast.LENGTH_SHORT).show();
            id=id+1;
            ed.putInt(String.valueOf(APP_PREFERENCES_ID),id);
            ed.clear();
            setResult(1);
            finish();
        }
    }
    public void onBackPressed()
    {
            super.onBackPressed();
    }

}
