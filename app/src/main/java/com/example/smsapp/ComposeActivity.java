package com.example.smsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Random;

public class ComposeActivity extends AppCompatActivity {

    //Declaration
    View view;
    private Button buttonSend;
    private EditText editTextComposeMessage;

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

    }
}