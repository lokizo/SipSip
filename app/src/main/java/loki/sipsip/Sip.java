package loki.sipsip;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Sip extends Activity implements View.OnClickListener{

    Button btn50;
    Button btn200;
    Button btn330;

    Button btnOk;
    Button btnReset;
    Button btnCancel;

    TextView statusPlus;

    int status = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sip);

        btn50 = (Button) findViewById(R.id.btn50);
        btn200 = (Button) findViewById(R.id.btn200);
        btn330 = (Button) findViewById(R.id.btn330);

        btnOk = (Button) findViewById(R.id.btnOk);
        btnReset = (Button) findViewById(R.id.btnReset);
        btnCancel = (Button) findViewById(R.id.btnCancel);

        statusPlus = (TextView) findViewById(R.id.statusPlus);

        btn50.setOnClickListener(this);
        btn200.setOnClickListener(this);
        btn330.setOnClickListener(this);
        btnOk.setOnClickListener(this);
        btnReset.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn50:
                status = status + 50;
                statusPlus.setText(status + "ml.");
                break;
            case R.id.btn200:
                status = status + 200;
                statusPlus.setText(status + "ml.");
                break;
            case R.id.btn330:
                status = status + 330;
                statusPlus.setText(status + "ml.");
                break;
            case R.id.btnReset:
                status = 0;
                statusPlus.setText(status + "ml.");
                break;

            case R.id.btnOk:
                Intent result = new Intent();
                result.putExtra("plus", status);
                setResult(RESULT_OK, result);
                finish();
                break;
            case R.id.btnCancel:
                finish();
                break;
        }



    }


}
