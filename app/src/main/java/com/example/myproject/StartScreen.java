package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class StartScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);
    }

    public void btn_createTicket(View view) {
        startActivity(new Intent(getApplicationContext(),TicketCreate.class));
    }

    public void btn_ticketInfo(View view) {
        startActivity(new Intent(getApplicationContext(),TicketInformation.class));
    }
}