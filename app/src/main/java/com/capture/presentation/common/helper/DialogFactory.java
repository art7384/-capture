package com.capture.presentation.common.helper;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import com.capture.R;

/**
 * Created by artem on 06.01.16.
 */
public class DialogFactory {


    static public AlertDialog showError(Context context, String message) {
        return showError(context, message, null, null);
    }

    static public AlertDialog showError(Context context, String message, DialogInterface.OnClickListener listener) {
        return showError(context, message, listener, null);
    }

    static public AlertDialog showError(Context context, String message, DialogInterface.OnClickListener listener, String btTxt) {
        AlertDialog.Builder adb = new AlertDialog.Builder(context);
        adb.setTitle(R.string.dialog_title_error);
        adb.setMessage(message);
        if (btTxt != null) {
            adb.setPositiveButton(btTxt, listener);
        } else {
            adb.setPositiveButton(R.string.btn_ok, listener);
        }
        return adb.show();
    }
}
