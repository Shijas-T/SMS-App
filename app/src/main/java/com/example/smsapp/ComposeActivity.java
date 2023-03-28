package com.example.smsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.telephony.SmsManager;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.util.Random;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ComposeActivity extends AppCompatActivity {

    //Declaration
    View view;
    private Button buttonSend;
    private EditText editTextComposeMessage;
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 0;
    String contactFirstName, contactLastName, contactPhoneNumber;


    private String otp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose);

        buttonSend = findViewById(R.id.btn_send);
        editTextComposeMessage = findViewById(R.id.edit_text_compose_message);

        // this will generate 6 digit random Number.
        // from 0 to 999999
        Random rnd = new Random();
        int number = rnd.nextInt(999999);
        // this will convert any number sequence into 6 character.
         otp = String.format("%06d", number);

         editTextComposeMessage.setText("Hi. Your OTP is "+otp);

        Intent intent=getIntent();
        contactFirstName = intent.getStringExtra("contact firstName");
        contactLastName = intent.getStringExtra("contact lastName");
        contactPhoneNumber = intent.getStringExtra("contact phoneNumber");

         buttonSend.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
//                 sendSMS(contactPhoneNumber, editTextComposeMessage.getText().toString());
                 sendSMSMessage();
             }
         });

    }

    protected void sendSMSMessage() {

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.SEND_SMS)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.SEND_SMS},
                        MY_PERMISSIONS_REQUEST_SEND_SMS);
            }
        }else {
            if (true) {
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(contactPhoneNumber, null, editTextComposeMessage.getText().toString(), null, null);
                Toast.makeText(getApplicationContext(), "SMS sent.",
                        Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getApplicationContext(),
                        "SMS failed, please try again.", Toast.LENGTH_LONG).show();
            }

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,String permissions[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_SEND_SMS: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(contactPhoneNumber, null, editTextComposeMessage.getText().toString(), null, null);
                    Toast.makeText(getApplicationContext(), "SMS sent.",
                            Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "SMS failed, please try again.", Toast.LENGTH_LONG).show();
                    return;
                }
            }
        }

    }

    // for twilio
    private void sendSMS(String toPhoneNumber,String message) {
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.setThreadPolicy( new StrictMode.ThreadPolicy.Builder().permitAll().build() );
        }

        OkHttpClient client = new OkHttpClient();
        String url = "https://api.twilio.com/2010-04-01/Accounts/"+"ACCOUNT_SID"+"/SMS/Messages";
        String base64EncodedCredentials = "Basic " + Base64.encodeToString(("ACCOUNT_SID" + ":" + "AUTH_TOKEN").getBytes(), Base64.NO_WRAP);

        RequestBody body = new FormBody.Builder()
                .add("From", "fromPhoneNumber")
                .add("To", toPhoneNumber)
                .add("Body", message)
                .build();

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .header("Authorization", base64EncodedCredentials)
                .build();
        try {
            Response response = client.newCall(request).execute();
            Log.d("Inside Compose Activity", "sendSms: "+ response.body().string());
        } catch (IOException e) { e.printStackTrace(); }

    }
}