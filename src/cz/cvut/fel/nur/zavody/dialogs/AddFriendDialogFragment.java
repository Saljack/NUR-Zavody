package cz.cvut.fel.nur.zavody.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import cz.cvut.fel.nur.zavody.R;

/**
 *
 * @author p4nther
 */
public class AddFriendDialogFragment extends DialogFragment {
    
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        String[] persons = new String[] {"Josef Novák","Karolína Vlachová","Milan Novák","Milena Dvořáková","Karel Nový","Ivan Klíma","Ivana Paulů","Daniel Mašek","Petr Novotný","Marie Novotná"};
        builder.setTitle(R.string.fa_choose_friend)
               .setItems(persons, new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int which) {
                       // The 'which' argument contains the index position
                       // of the selected item
                   }
               });
        return builder.create();
    }
    
}
