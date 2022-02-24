package com.example.annapurnaapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class entermobilenumber extends AppCompatActivity {

    EditText enternumber;
    Button getOTPbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       enternumber = findViewById(R.id.input_Mobile_Number);
       getOTPbutton = findViewById(R.id.getOTP);
       final  ProgressBar progressBar= findViewById(R.id.progressBar);
       getOTPbutton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if (!enternumber.getText().toString().trim().isEmpty()) {


                   if ((enternumber.getText().toString().trim()).length() == 10) {
                       progressBar.setVisibility(View.VISIBLE);
                       getOTPbutton.setVisibility(View.INVISIBLE);

                       //otp authentication
                       PhoneAuthProvider.getInstance().verifyPhoneNumber("+91"+ enternumber.getText().toString(),
                               60,
                               TimeUnit.SECONDS,
                               entermobilenumber.this,
                               new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                   @Override
                                   public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                       progressBar.setVisibility(View.GONE);
                                       getOTPbutton.setVisibility(View.VISIBLE);
                                   }

                                   @Override
                                   public void onVerificationFailed(@NonNull FirebaseException e) {

                                       progressBar.setVisibility(View.GONE);
                                       getOTPbutton.setVisibility(View.VISIBLE);
                                       Toast.makeText(entermobilenumber.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                   }

                                   @Override
                                   public void onCodeSent(@NonNull String backendOTP, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                       progressBar.setVisibility(View.GONE);
                                       getOTPbutton.setVisibility(View.VISIBLE);
                                       Intent intent = new Intent(getApplicationContext(), Enterotp2.class);
                                       intent.putExtra("mobile", enternumber.getText().toString());
                                       intent.putExtra("backendOTP", backendOTP);
                                       startActivity(intent);
                                   }
                                   });
                       ;
                       //  )Intent intent = new Intent(getApplicationContext(), Enterotp2.class);
                       //intent.putExtra("mobile", enternumber.getText().toString());
                       //startActivity(intent);
                   } else {
                       Toast.makeText(entermobilenumber.this, "Please enter correct mobile number", Toast.LENGTH_SHORT).show();

                   }
               } else {
                   Toast.makeText(entermobilenumber.this, "Enter Mobile Number", Toast.LENGTH_SHORT).show();
               }
           }
       });

    }
}