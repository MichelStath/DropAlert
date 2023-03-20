package com.example.dropalert;

import static androidx.core.app.ServiceCompat.stopForeground;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.app.ServiceCompat;
import androidx.core.content.ContextCompat;

import android.app.ActivityManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    final String STATE_ON_STRING = "Enabled";
    final String STATE_OFF_STRING = "Disabled";
    SwitchCompat switchCompat;
    TextView stateTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        switchCompat = findViewById(R.id.switch1);
        stateTV = findViewById(R.id.stateTV);
        checkState();

        switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                checkState();
            }
        });
    }

    private void checkState() {
        if(switchCompat.isChecked()){
            Log.i("switch","checked");
            stateTV.setText(STATE_ON_STRING);
            stateTV.setTextColor(getResources().getColor(R.color.green));

            if(!foregroundServiceRunning()) {
                startService();
            }
        }else {
            Log.i("switch", "not Checked");
            stateTV.setText(STATE_OFF_STRING);
            stateTV.setTextColor(getResources().getColor(R.color.red));
            stopService();
        }

    }

    public boolean foregroundServiceRunning(){
        ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for(ActivityManager.RunningServiceInfo service: activityManager.getRunningServices(Integer.MAX_VALUE)) {
            if(MyForegroundService.class.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    public void startService() {
        Intent serviceIntent = new Intent(this, ForegroundService.class);
        Log.i("Service","started");
        serviceIntent.putExtra("inputExtra", "Foreground Service Example in Android");
        ContextCompat.startForegroundService(this, serviceIntent);
    }
    public void stopService() {
        Intent serviceIntent = new Intent(this, ForegroundService.class);
        stopService(serviceIntent);
        Log.i("Service","stoped");

    }

}