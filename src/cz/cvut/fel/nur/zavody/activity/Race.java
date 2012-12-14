package cz.cvut.fel.nur.zavody.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.google.android.maps.GeoPoint;
import cz.cvut.fel.nur.zavody.R;
import cz.cvut.fel.nur.zavody.Zavody;
import cz.cvut.fel.nur.zavody.dialogs.PickerDialog;

/**
 *
 * @author p4nther
 */
public class Race extends Activity implements PickerDialog.PickerDialogListener {

    final Context context = this;
    private Button _oponnentsButton;
    private Button _destinationButton;
    private Button _modeButton;
    private Button _betButton;
    private Button _startButton;
    private String oponnent = null;
    private String mode = null;
    private String coordinates = null;
    private int bet = 0;
    private int _mode = 0;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.race);

        _oponnentsButton = (Button) findViewById(R.id.race_button01);
        _oponnentsButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Race.this.startOponnentsActivity();
            }
        });

        _destinationButton = (Button) findViewById(R.id.race_button02);
        _destinationButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Race.this.startDestinationActivity();
            }
        });

        _modeButton = (Button) findViewById(R.id.race_button03);
        _modeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Race.this.startModeActivity();
            }
        });

        _betButton = (Button) findViewById(R.id.race_button04);
        _betButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Race.this.startBetActivity();
            }
        });

        _startButton = (Button) findViewById(R.id.race_button05);
        _startButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Race.this.startStartRaceActivity();
            }
        });

        //DEBUG
        if (Zavody.DEBUG) {
            oponnent = "DEBUG USER";
            coordinates = "50083563 14520741";
            mode = "normální";
            _mode = Zavody.NORMAL_MODE;
            bet = 20;
            checkRaceConditions();
        }
    }

    private void startOponnentsActivity() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

        alertDialogBuilder.setTitle("S kým chcete závodit?");

        final String[] friends = new String[5];
        friends[0] = "Jan Novák";
        friends[1] = "Tomáš Vomáčka";
        friends[2] = "Kateřina Ruská";
        friends[3] = "Pavel Hašek";
        friends[4] = "Veronika Levá";

        alertDialogBuilder
                .setCancelable(false)
                .setItems(friends, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(Race.this,
                        "Soupeř: " + friends[which], Toast.LENGTH_LONG)
                        .show();
                Race.this.oponnent = friends[which];
                dialog.dismiss();
                Race.this.checkRaceConditions();
            }
        })
                .setNegativeButton("Zrušit", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    private void startDestinationActivity() {
        Intent intent = new Intent(this, MapSelectCoordinates.class);
        startActivityForResult(intent, 1);
    }

    private void startModeActivity() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

        alertDialogBuilder.setTitle("Vyberte mód závodu");

        final String[] raceModes = new String[2];
        raceModes[Zavody.NORMAL_MODE] = "normální";
        raceModes[Zavody.BLIND_MODE] = "blind";

        alertDialogBuilder
                .setCancelable(false)
                .setItems(raceModes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(Race.this,
                        "Vybrán mód: " + raceModes[which], Toast.LENGTH_LONG)
                        .show();

                Race.this.mode = raceModes[which];
                dialog.dismiss();
                Race.this.checkRaceConditions();
                _mode = which;
            }
        })
                .setNegativeButton("Zrušit", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    private void startBetActivity() {
        PickerDialog dlg = PickerDialog.newInstance(1, 50);
        dlg.show(getFragmentManager(), "bet");
    }

    private void startStartRaceActivity() {
        Intent intent = null;
        if (_mode == Zavody.NORMAL_MODE) {
            intent = new Intent(this, NormalMode.class);
        } else if (_mode == Zavody.BLIND_MODE) {
            intent = new Intent(this, BlindMode.class);
        }
        ((Zavody) getApplication()).resetAll();
        startActivity(intent);
    }

    private void checkRaceConditions() {
        if (oponnent != null && coordinates != null && mode != null && bet > 0) {
            _startButton.setEnabled(true);
            _startButton.setBackgroundColor(0xff008500);
        }
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
                    coordinates = point[0] + " " + point[1];
                }
            }

            if (resultCode == RESULT_CANCELED) {
                //Write your code on no result return 
            }
        }
    }

    public void setValueOfPicked(PickerDialog dialog, int value) {
        Toast.makeText(Race.this,
                "Výše sázky: " + value, Toast.LENGTH_LONG)
                .show();
        bet = value;
    }
}
