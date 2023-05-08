package com.example.smsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ContactInfoActivity extends AppCompatActivity {

    //Declaration
    private Button buttonSendMessage;
    private TextView textViewFirstName, textViewLastName, textViewPhoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_info);

        getSupportActionBar().setTitle("Contact Info");

        textViewFirstName = findViewById(R.id.text_view_first_name);
        textViewLastName = findViewById(R.id.text_view_last_name);
        textViewPhoneNumber = findViewById(R.id.text_view_phone_no);
        buttonSendMessage = findViewById(R.id.button_send_message);

        Intent intent=getIntent();
        String contactFirstName = intent.getStringExtra("contact firstName");
        String contactLastName = intent.getStringExtra("contact lastName");
        String contactPhoneNumber = intent.getStringExtra("contact phoneNumber");

        textViewFirstName.setText(contactFirstName);
        textViewLastName.setText(contactLastName);
        textViewPhoneNumber.setText(contactPhoneNumber);

        buttonSendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ContactInfoActivity.this, ComposeActivity.class);
                intent.putExtra("contact firstName",contactFirstName);
                intent.putExtra("contact lastName",contactLastName);
                intent.putExtra("contact phoneNumber",contactPhoneNumber);
                startActivity(intent);

            }
        });
    }
}