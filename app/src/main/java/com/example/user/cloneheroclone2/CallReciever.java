package com.example.user.cloneheroclone2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.widget.Toast;

public class CallReciever extends BroadcastReceiver
{


    @Override
    public void onReceive(Context context, Intent intent) {
        String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
        if(state.equals(TelephonyManager.EXTRA_STATE_RINGING))
        {
            while(TelephonyManager.EXTRA_INCOMING_NUMBER.isEmpty())
            {

            }
            String phoneNum = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
            Toast.makeText(context, "Number is: "+phoneNum, Toast.LENGTH_SHORT).show();
        }
    }
}
