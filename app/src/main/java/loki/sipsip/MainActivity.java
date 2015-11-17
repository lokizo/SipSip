package loki.sipsip;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {
    TextView statusMl;
    TextView statusPercent;
    Button btnSip;
    Button btnReset;

    SharedPreferences savedState;
//wooo
    float STATUS;
    final float DAILY_NORM = 1500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        statusMl = (TextView) findViewById(R.id.statusMl);
        statusPercent = (TextView) findViewById(R.id.statusPercent);

        btnSip = (Button) findViewById(R.id.btnSip);
        btnSip.setOnClickListener(this);

        btnReset = (Button) findViewById(R.id.btnReset);
        btnReset.setOnClickListener(this);

        loadState();
        setStatus(0);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnSip:
                Intent sip = new Intent(this, Sip.class);
                startActivityForResult(sip, 0);
                break;

            case R.id.btnReset:
                STATUS = 0;
                setStatus(0);
                break;
        }

    }
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(data == null){return;}
        int plus = data.getIntExtra("plus", 0);
        setStatus(plus);
    }

    protected void onPause(){
        super.onPause();
        saveState();
    }

    public void setStatus(int plus){
        STATUS = STATUS + plus;
        statusMl.setText(Math.round(STATUS) + "ml.");
        statusPercent.setText(Math.round(STATUS / DAILY_NORM * 100) + "%");
    }

    void saveState(){
        savedState = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor ed = savedState.edit();
        ed.putFloat("SAVED_STATUS",STATUS);
        ed.apply();
    }

    void loadState(){
        savedState = getPreferences(MODE_PRIVATE);
        STATUS = savedState.getFloat("SAVED_STATUS", 0);
    }
}
