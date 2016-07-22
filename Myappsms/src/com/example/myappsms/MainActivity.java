package com.example.myappsms;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.app.PendingIntent;


public class MainActivity extends Activity {

    Button sendSMSBtn;
    EditText toPhoneNumberET;
    EditText smsMessageET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sendSMSBtn = (Button) findViewById(R.id.smsManager);
        toPhoneNumberET = (EditText) findViewById(R.id.phoneNumber);
        smsMessageET = (EditText) findViewById(R.id.smsBody);
        sendSMSBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                sendSMS();
            }
        });
    }
    public void sendSMS(){
    	String toPhoneNumber = toPhoneNumberET.getText().toString();
        String smsMessage = smsMessageET.getText().toString();
        try {
        	
            SmsManager sms = SmsManager.getDefault();
            PendingIntent sentPI;
            String SENT = "SMS_SENT";

            sentPI = PendingIntent.getBroadcast(this, 0,new Intent(SENT), 0);
            
            sms.sendTextMessage(toPhoneNumber, null, smsMessage, sentPI, null);            
            
            Toast.makeText(getApplicationContext(), "SMS sent.",
                    Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(),
                    "Sending SMS failed.", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }
}