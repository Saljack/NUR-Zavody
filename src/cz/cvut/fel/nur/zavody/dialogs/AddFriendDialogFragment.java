package cz.cvut.fel.nur.zavody.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import cz.cvut.fel.nur.zavody.R;
import cz.cvut.fel.nur.zavody.model.Friend;
import cz.cvut.fel.nur.zavody.model.FriendsAdapter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * Dialog pro pridani pratel
 * @author p4nther
 */
public class AddFriendDialogFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        String[] persons = new String[]{"Josef Novák", "Karolína Vlachová", "Milan Novák", "Milena Dvořáková", "Karel Nový", "Ivan Klíma", "Ivana Paulů", "Daniel Mašek", "Petr Novotný", "Marie Novotná"};
        List<Friend> list = new ArrayList<Friend>(persons.length);
        for (String per : persons) {
            if (per.charAt(0) % 2 == 0) {
                list.add(new Friend(per, Friend.SocialNetwork.TWITTER));
            } else {
                list.add(new Friend(per, Friend.SocialNetwork.FACEBOOK));
            }
        }
        ListView v = new ListView(getActivity());
        FriendsAdapter adapterList = new FriendsAdapter(getActivity(), list, getActivity().getLayoutInflater());
        v.setAdapter(adapterList);



        builder.setTitle(R.string.fa_choose_friend)
                .setView(v);
        final AlertDialog dlg = builder.create();

        v.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Friend item = (Friend) parent.getAdapter().getItem(position);
                Toast.makeText(getActivity(),
                        "Soupeř: " + item.getName(), Toast.LENGTH_LONG)
                        .show();
                if (dlg != null && dlg.isShowing()) {
                    dlg.dismiss();
                }


            }
        });

        return dlg;
    }
}
