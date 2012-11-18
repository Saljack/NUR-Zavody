package cz.cvut.fel.nur.zavody;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import com.google.android.maps.GeoPoint;
import cz.cvut.fel.nur.zavody.activity.NormalMode;
import cz.cvut.fel.nur.zavody.activity.Friends;
import cz.cvut.fel.nur.zavody.activity.MapSelectCoordinates;
import cz.cvut.fel.nur.zavody.activity.Settings;

/**
 * Tohle je první volaná aktivita
 *
 * @author saljack
 */
public class MainActivity extends Activity {

    private Button _mapsButton;
    private Button _friendsButton;
    private Button _settingsButton;
    private Button _mapSelectCoordinates;

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

        _friendsButton = (Button) findViewById(R.id.ma_button02);
        _friendsButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startFriendsActivity();
            }
        });

        _settingsButton = (Button) findViewById(R.id.ma_button03);
        _settingsButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startSettingsActivity();
            }
        });

        _mapSelectCoordinates = (Button) findViewById(R.id.ma_mapSelectCoordinate);
        _mapSelectCoordinates.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startMapSelectCoordinatesActivity();
            }
        });
    }

    private void startMapsActivity() {
//        ((Zavody) getApplication()).startRace();
        ((Zavody) getApplication()).resetAll();
        Intent intent = new Intent(MainActivity.this, NormalMode.class);
        startActivity(intent);
    }

    private void startFriendsActivity() {
        Intent intent = new Intent(MainActivity.this, Friends.class);
        startActivity(intent);
    }

    private void startSettingsActivity() {
        Intent intent = new Intent(MainActivity.this, Settings.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    private void startMapSelectCoordinatesActivity() {
        Intent intent = new Intent(this, MapSelectCoordinates.class);
        startActivityForResult(intent, 1);

    }

    /**
     * @see android.app.Activity
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                if (data.hasExtra(MapSelectCoordinates.POINT)) {
                    int[] point = data.getIntArrayExtra(MapSelectCoordinates.POINT);
                    ((Zavody) getApplication()).setFinish(new GeoPoint(point[0], point[1]));
                }
            }

            if (resultCode == RESULT_CANCELED) {
                //Write your code on no result return 
            }
        }
    }
}
