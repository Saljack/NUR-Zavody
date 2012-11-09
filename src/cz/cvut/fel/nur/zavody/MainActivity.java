package cz.cvut.fel.nur.zavody;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import cz.cvut.fel.nur.zavody.activity.NormalMode;

/**
 * Tohle je první volaná aktivita
 *
 * @author saljack
 */
public class MainActivity extends Activity {

    private Button _mapsButton;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        _mapsButton = (Button) findViewById(R.id.ma_button01);
        _mapsButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startMapsActivity();
            }
        });
    }

    private void startMapsActivity() {
//        ((Zavody) getApplication()).startRace();
        ((Zavody) getApplication()).resetAll();
        Intent intent = new Intent(MainActivity.this, NormalMode.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }
}
