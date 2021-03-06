package cz.cvut.fel.nur.zavody;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import com.google.android.maps.GeoPoint;
import cz.cvut.fel.nur.zavody.activity.BlindMode;
import cz.cvut.fel.nur.zavody.activity.Friends;
import cz.cvut.fel.nur.zavody.activity.MapSelectCoordinates;
import cz.cvut.fel.nur.zavody.activity.NormalMode;
import cz.cvut.fel.nur.zavody.activity.Race;
import cz.cvut.fel.nur.zavody.activity.Settings;
import cz.cvut.fel.nur.zavody.dialogs.PickerDialog;

/**
 * Tohle je první volaná aktivita
 *
 * @author saljack
 */
public class MainActivity extends Activity {
    
    private Button _friendsButton;
    private Button _settingsButton;
    private Button _newRaceButton;
    private Button _helpButton;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
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
        
        _newRaceButton = (Button) findViewById(R.id.ma_button00);
        _newRaceButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startNewRaceActivity();
            }
        });
        
        _helpButton = (Button) findViewById(R.id.ma_help);
        _helpButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                MainActivity.this.showHelp();
            }
        });
        if (Zavody.DEBUG) {
            LinearLayout ll = (LinearLayout) findViewById(R.id.ma_layout);
            Button b = new Button(this);
            b.setText("DEBUG BTN");
            b.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    startDebug();
                }
            });
            ll.addView(b);
        }
    }
    
    private void startDebug() {
        PickerDialog dlg = PickerDialog.newInstance(0, 50);
        dlg.show(getFragmentManager(), "DEBUG DLG");
    }
    
    private void startFriendsActivity() {
        Intent intent = new Intent(MainActivity.this, Friends.class);
        startActivity(intent);
    }
    
    private void startSettingsActivity() {
        Intent intent = new Intent(MainActivity.this, Settings.class);
        startActivity(intent);
    }
    
    private void startNewRaceActivity() {
        Intent intent = new Intent(MainActivity.this, Race.class);
        startActivity(intent);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }
    
    /**
     * zobrazi napovedu
     */
    private void showHelp() {
        AlertDialog dlg = new AlertDialog.Builder(this)
                .setView(getLayoutInflater().inflate(R.layout.help, null))
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        })
                .create();
        dlg.show();
    }
}
