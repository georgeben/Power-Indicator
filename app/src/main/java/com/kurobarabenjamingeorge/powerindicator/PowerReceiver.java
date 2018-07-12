package com.kurobarabenjamingeorge.powerindicator;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class PowerReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        //The action that has occurred
        String intentAction = intent.getAction();

        int value = 0;

        switch(intentAction){
            //Checking which action occurred
            case Intent.ACTION_POWER_CONNECTED:
                value = 1;
                break;
            case Intent.ACTION_POWER_DISCONNECTED:
                value = 0;
                break;

        }

        if(MainActivity.getInstance() != null){
            MainActivity.getInstance().updateIndicator(value);
        }
    }
}
