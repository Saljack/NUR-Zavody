package cz.cvut.fel.nur.zavody.activity;

import android.app.DialogFragment;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;
import cz.cvut.fel.nur.zavody.R;
import cz.cvut.fel.nur.zavody.dialogs.AddFriendDialogFragment;
import cz.cvut.fel.nur.zavody.model.Friend;
import cz.cvut.fel.nur.zavody.model.FriendsAdapter;
import java.util.ArrayList;

/**
 *
 * Aktivita pro prohlizeni pratel
 * @author p4nther
 */
public class Friends extends ListActivity {

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        ArrayList<Friend> list = new ArrayList<Friend>();
        list.add(new Friend("Jan Novák", Friend.SocialNetwork.FACEBOOK));
        list.add(new Friend("Tomáš Vomáčka", Friend.SocialNetwork.FACEBOOK));
        list.add(new Friend("Kateřina Ruská", Friend.SocialNetwork.TWITTER));
        list.add(new Friend("Pavel Hašek", Friend.SocialNetwork.FACEBOOK));
        list.add(new Friend("Veronika Levá", Friend.SocialNetwork.TWITTER));
//        ArrayAdapter<String> adapterList = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        FriendsAdapter adapterList = new FriendsAdapter(this, list, getLayoutInflater());
        setListAdapter(adapterList);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        String item = ((Friend) getListAdapter().getItem(position)).getName();
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
