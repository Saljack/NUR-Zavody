package cz.cvut.fel.nur.zavody.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.ToggleButton;
import cz.cvut.fel.nur.zavody.R;

/**
 *
 * @author p4nther
 */
public class Settings extends Activity {

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.settings);
    }
    
    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Option checked");
        switch(view.getId()) {
            case R.id.checkbox_option_1:
                if (checked) {
                    alertDialog.setMessage("First option clicked and checked");
                } else {
                    alertDialog.setMessage("First option clicked and not-checked");
                }
                break;
            case R.id.checkbox_option_2:
                if (checked) {
                    alertDialog.setMessage("Second option clicked and checked");
                } else {
                    alertDialog.setMessage("Second option clicked and not-checked");
                }
                break;            
        }
        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
           public void onClick(DialogInterface dialog, int which) {
              // here you can add functions
           }
        });
        alertDialog.show();
    }
    
    public void onToggleClicked(View view) {
        boolean on = ((ToggleButton) view).isChecked();
        
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Toggle changed");
        if (on) {
            alertDialog.setMessage("GPS is on");
        } else {
            alertDialog.setMessage("GPS is off");
        }
        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
           public void onClick(DialogInterface dialog, int which) {
              // here you can add functions
           }
        });
        alertDialog.show();
    }
    
    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Radio changed");
        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_normal:
                if (checked)
                    alertDialog.setMessage("Normal mode is default");
                break;
            case R.id.radio_blind:
                if (checked)
                    alertDialog.setMessage("Blind mode is default");
                break;
        }
        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
           public void onClick(DialogInterface dialog, int which) {
              // here you can add functions
           }
        });
        alertDialog.show();
    }
}
