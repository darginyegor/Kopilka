package fkn.kopilka;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class IncreaseSumActivity extends Activity {

    public static final String APP_PREFERENCES = "mysettings";
    public static final String APP_PREFERENCES_MONEY = "cena";

    SharedPreferences mSettings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_increase_sum);
    }

    public void enter(View v) {
        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        Integer value;
        EditText addmoney = (EditText) findViewById(R.id.value);
        value = Integer.valueOf(addmoney.getText().toString());
        SharedPreferences.Editor ed = mSettings.edit();
        String x = Integer.toString(value);
        ed.putString(APP_PREFERENCES_MONEY, x);

        ed.apply();
        setResult(1);
        finish();
    }
    public void exit(View v){
        finish();
    }
}
