package com.example.myapplication;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;

public class ShowResultFragment extends DialogFragment {

    private static String RESULT_MESSAGE = "message";

    static ShowResultFragment newInstance(String m) {
        ShowResultFragment f = new ShowResultFragment();

        Bundle args = new Bundle();
        args.putString(RESULT_MESSAGE, m);
        f.setArguments(args);

        return f;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.result_title)
                .setMessage(getArguments().getString(RESULT_MESSAGE))
                .setPositiveButton(R.string.result_ok, (dialog, id) -> {
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}
