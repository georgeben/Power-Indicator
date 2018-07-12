package com.kurobarabenjamingeorge.powerindicator;

import android.content.ComponentName;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private PackageManager mPackageManager;
    private ComponentName mComponentName;

    //This holds a reference to the main activity
    private static MainActivity instance;

    private ImageView chargingIndicatorImageView;
    private TextView chargingIndicatorTextView;


    public static MainActivity getInstance(){
        return instance;
    }

    public void updateIndicator(final int value){
        MainActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //Value returned from Power receiver => 0 = not charging, 1 = charging
                if(value == 0){
                    chargingIndicatorImageView.setImageResource(R.drawable.ic_battery_60_black_24dp);
                    chargingIndicatorTextView.setText(R.string.not_charging);
                    chargingIndicatorTextView.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.red));

                }else{
                    chargingIndicatorImageView.setImageResource(R.drawable.ic_battery_charging_full_black_24dp);
                    chargingIndicatorTextView.setText(R.string.charging);
                    chargingIndicatorTextView.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.green));

                }
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        //This activates the Power receiver only when the app is running
        mPackageManager.setComponentEnabledSetting(mComponentName,
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);
    }

    @Override
    protected void onStop() {
        super.onStop();
        //This deactivates the power receiver when the app is no longer visible
        mPackageManager.setComponentEnabledSetting(mComponentName,
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPackageManager = getPackageManager();
        mComponentName = new ComponentName(this, PowerReceiver.class);

        chargingIndicatorImageView = (ImageView) findViewById(R.id.chargingIndicator);
        chargingIndicatorTextView  = (TextView) findViewById(R.id.chargingIndicatorTextView);

        instance = this;
    }

}
