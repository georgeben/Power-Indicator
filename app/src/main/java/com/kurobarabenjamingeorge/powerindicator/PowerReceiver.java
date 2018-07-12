package com.kurobarabenjamingeorge.powerindicator;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class PowerReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        //The action that has occured
        String intentAction = intent.getAction();
        Log.i("Intent action", intentAction);

        String message = null;

        switch(intentAction){
            case Intent.ACTION_POWER_CONNECTED:
                message = "Power connected";
                break;
            case Intent.ACTION_POWER_DISCONNECTED:
                message = "Power disconnected";

        }

        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
