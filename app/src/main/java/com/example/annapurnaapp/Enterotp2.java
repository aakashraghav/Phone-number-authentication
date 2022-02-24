package com.example.annapurnaapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class Enterotp2 extends AppCompatActivity {


    EditText input_otp1, input_otp2, input_otp3, input_otp4, input_otp5, input_otp6;

    String getOTPbackend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_enterotp2);

        final Button submitotp = findViewById(R.id.submit_OTP);

        input_otp1 = findViewById(R.id.input_OTP1);
        input_otp2 = findViewById(R.id.input_OTP2);
        input_otp3 = findViewById(R.id.input_OTP3);
        input_otp4 = findViewById(R.id.input_OTP4);
        input_otp5 = findViewById(R.id.input_OTP5);
        input_otp6 = findViewById(R.id.input_OTP6);


        final ProgressBar progressBarSubmit = findViewById(R.id.progressBarSubmit);

        TextView textView = findViewById(R.id.textMobileShownumber);
        textView.setText(String.format(
                "+91-%s", getIntent().getStringExtra("mobile")
        ));

        getOTPbackend = getIntent().getStringExtra("backendOTP");


        submitotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!input_otp1.getText().toString().trim().isEmpty() && !input_otp2.getText().toString().trim().isEmpty() && !input_otp3.getText().toString().trim().isEmpty() && !input_otp4.getText().toString().trim().isEmpty() && !input_otp5.getText().toString().trim().isEmpty() && !input_otp6.getText().toString().trim().isEmpty()) {

                    String entercodeOTP = input_otp1.getText().toString() +
                            input_otp2.getText().toString() +
                            input_otp3.getText().toString() +
                            input_otp4.getText().toString() +
                            input_otp5.getText().toString() +
                            input_otp6.getText().toString();
                    if (getOTPbackend != null) {
                        progressBarSubmit.setVisibility(View.VISIBLE);
                        submitotp.setVisibility(View.INVISIBLE);

                        PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(
                                getOTPbackend, entercodeOTP
                        );
                        FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential)
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        progressBarSubmit.setVisibility(View.GONE);
                                        submitotp.setVisibility(View.VISIBLE);

                                        if (task.isSuccessful()) {
                                            Intent intent = new Intent(getApplicationContext(), dashboard.class);
                                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                            startActivity(intent);

                                        } else {
                                            Toast.makeText(Enterotp2.this, "Enter the correct otp", Toast.LENGTH_SHORT).show();
                                        }

                                    }
                                });
                    }


                    // Toast.makeText(Enterotp2.this, "OTP Verify", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Enterotp2.this, "Please enter all numbers ", Toast.LENGTH_SHORT).show();
                }
            }
        });

        numberotpmove();

        findViewById(R.id.textSendOTPAgain).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PhoneAuthProvider.getInstance().verifyPhoneNumber(+91+getIntent().getStringExtra("mobile"),
                        60,
                        TimeUnit.SECONDS,
                        Enterotp2.this,
                        new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {


                                Toast.makeText(Enterotp2.this,"Please check your internet connection.", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCodeSent(@NonNull String newbackendOTP, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                               getOTPbackend=newbackendOTP;
                                Toast.makeText(Enterotp2.this, "OTP sent successfully", Toast.LENGTH_SHORT).show();
                            }
                        }
                );
            }
        });

    }

    private void numberotpmove() {
        input_otp1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()) {
                    input_otp2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        input_otp2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()) {
                    input_otp3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        input_otp3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()) {
                    input_otp4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        input_otp4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()) {
                    input_otp5.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        input_otp5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()) {
                    input_otp6.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        input_otp6.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()) {
                    input_otp6.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


}