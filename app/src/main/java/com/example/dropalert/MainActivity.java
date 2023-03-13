package com.example.dropalert;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.graphics.Color;
import android.os.Bundle;
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

        }else {
            Log.i("switch", "not Checked");
            stateTV.setText(STATE_OFF_STRING);
            stateTV.setTextColor(getResources().getColor(R.color.red));
        }

    }
}