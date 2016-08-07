package html5api.jp.ftest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.EditText;

/**
 * Created by kgotoh on 2016/08/06.
 */
public class DetailActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_detail);

    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode != KeyEvent.KEYCODE_BACK) {
            return super.onKeyDown(keyCode, event);
        } else {
            Intent intent = new Intent();
            intent.putExtra("test",  ((EditText) findViewById(R.id.et)).getText().toString());
            setResult(RESULT_OK, intent);
            finish();
            return true;
        }
    }
}
