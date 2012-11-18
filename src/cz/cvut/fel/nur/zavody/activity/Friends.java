package cz.cvut.fel.nur.zavody.activity;

import android.app.DialogFragment;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import cz.cvut.fel.nur.zavody.R;
import cz.cvut.fel.nur.zavody.dialogs.AddFriendDialogFragment;
import java.util.ArrayList;

/**
 *
 * @author p4nther
 */
public class Friends extends ListActivity {
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        
        ArrayList<String> list = new ArrayList<String>();
        list.add("Jan Novák");
        list.add("Tomáš Vomáčka");
        list.add("Kateřina Ruská");
        list.add("Pavel Hašek");
        list.add("Veronika Levá");
        ArrayAdapter<String> adapterList = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        setListAdapter(adapterList);
    }
    
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        String item = (String) getListAdapter().getItem(position);
        Toast.makeText(this, item + " selected", Toast.LENGTH_LONG).show();
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.friends_menu, menu);        
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_friend:                
                DialogFragment newFragment = new AddFriendDialogFragment();
                newFragment.show(getFragmentManager(), "add_friends");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    
}
