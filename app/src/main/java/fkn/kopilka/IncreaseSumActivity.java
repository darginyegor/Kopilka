package fkn.kopilka;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class IncreaseSumActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_increase_sum);
    }

    public void enter(View v) {
        Integer value;
        EditText addmoney = (EditText) findViewById(R.id.value);
        value = Integer.valueOf(addmoney.getText().toString());
        setResult(value);
        finish();
    }
    public void exit(View v){
        finish();
    }
}
