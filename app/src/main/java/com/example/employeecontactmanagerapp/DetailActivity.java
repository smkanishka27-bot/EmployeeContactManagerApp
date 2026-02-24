package com.example.employeecontactmanagerapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    private String phone;
    private String email; // optional sample email

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Employee Details");
        setContentView(R.layout.activity_detail);

        TextView tv = findViewById(R.id.tvEmployeeDetails);
        Button btnCall = findViewById(R.id.btnCallEmp);
        Button btnSms = findViewById(R.id.btnSmsEmp);
        Button btnEmail = findViewById(R.id.btnEmailEmp);

        Intent i = getIntent();
        String name = i.getStringExtra("name");
        String empId = i.getStringExtra("empId");
        phone = i.getStringExtra("phone");
        String dept = i.getStringExtra("dept");

        // Sample email (you can change format)
        email = empId.toLowerCase() + "@company.com";

        String details =
                "Name: " + name + "\n" +
                        "Employee ID: " + empId + "\n" +
                        "Department: " + dept + "\n" +
                        "Phone: " + phone + "\n" +
                        "Email: " + email;

        tv.setText(details);

        btnCall.setOnClickListener(v -> showConfirmDialog(
                "Call Employee",
                "Do you want to call this employee?",
                this::callEmployee
        ));

        btnSms.setOnClickListener(v -> showConfirmDialog(
                "Send SMS",
                "Do you want to send SMS to this employee?",
                this::smsEmployee
        ));

        btnEmail.setOnClickListener(v -> emailEmployee());
    }

    private void showConfirmDialog(String title, String message, Runnable onYes) {
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("Yes", (d, w) -> onYes.run())
                .setNegativeButton("No", (d, w) -> d.dismiss())
                .show();
    }

    private void callEmployee() {
        Intent dial = new Intent(Intent.ACTION_DIAL);
        dial.setData(Uri.parse("tel:" + phone));
        startActivity(dial);
    }

    private void smsEmployee() {
        Intent sms = new Intent(Intent.ACTION_SENDTO);
        sms.setData(Uri.parse("smsto:" + phone));
        sms.putExtra("sms_body", "Hello, this is regarding work update.");
        startActivity(sms);
    }

    private void emailEmployee() {
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.parse("mailto:" + email));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Regarding Work");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Hello,\n\nThis is a quick update.\n\nThanks.");
        startActivity(Intent.createChooser(emailIntent, "Choose Email App"));
    }
}