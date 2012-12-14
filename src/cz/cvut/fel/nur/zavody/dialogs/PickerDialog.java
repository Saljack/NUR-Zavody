/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.nur.zavody.dialogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.NumberPicker;
import cz.cvut.fel.nur.zavody.R;

/**
 * Dialog s NumberPicker a textovym polem
 * @author Saljack
 */
public class PickerDialog extends DialogFragment {

    public static final String TAG = "PickerDialog";
    private int _min;
    private int _max;
    private View _view;
    private PickerDialogListener _listener;
    private NumberPicker _picker;

    public interface PickerDialogListener {

        public void setValueOfPicked(PickerDialog dialog, int value);
    }

    private PickerDialog(int _min, int _max) {
        this._min = _min;
        this._max = _max;
    }

    public static PickerDialog newInstance(int min, int max) {
        PickerDialog ret = new PickerDialog(min, max);

        return ret;
    }

    public void setMin(int min) {
        _min = min;
    }

    public void setMax(int max) {
        _max = max;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        _view = getActivity().getLayoutInflater().inflate(R.layout.dialog_picker, null);
        _picker = (NumberPicker) _view.findViewById(R.id.picker_dialog_picker);
        _picker.setMinValue(_min);
        _picker.setMaxValue(_max);
        builder.setMessage(R.string.picker_dialog_title).setView(_view)
                .setPositiveButton(R.string.bt_ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                if (_listener != null) {
                    _listener.setValueOfPicked(PickerDialog.this, _picker.getValue());
                }

            }
        })
                .setNegativeButton(R.string.bt_storno, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog
            }
        });
        // Create the AlertDialog object and return it
        return builder.create();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            _listener = (PickerDialogListener) activity;
        } catch (ClassCastException ex) {
            Log.d(TAG, "Class " + activity.getClass().getName() + " not implement PickerDialogListener ");
        }
    }
}
