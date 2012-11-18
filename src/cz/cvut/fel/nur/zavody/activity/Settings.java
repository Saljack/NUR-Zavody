package cz.cvut.fel.nur.zavody.activity;

import android.app.Activity;
import android.os.Bundle;
import cz.cvut.fel.nur.zavody.R;

/**
 * Na tohle je nejlepsi pouzit aktivitu PreferenceActivity a settings prepsat do XML a jenom ho nacist  
 * @author p4nther
 */
public class Settings extends Activity {

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.settings);
    }    
}
