package com.intellinds.emailer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText edtEmail;
    private EditText edtSubject;
    private EditText edtMessage;
    private Button btnSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] emails = edtEmail.getText().toString().trim().split(",");
                String subject = edtSubject.getText().toString().trim();
                String message = edtMessage.getText().toString().trim();

                // TODO: Validation

                sendEmail(emails, subject, message);
            }
        });
    }

    private void initUI() {
        edtEmail = findViewById(R.id.main_email_editText);
        edtSubject = findViewById(R.id.main_subject_editText);
        edtMessage = findViewById(R.id.main_message_editText);
        btnSend = findViewById(R.id.main_send_button);
    }

    private void sendEmail(String[] emails, String subject, String message) {
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_EMAIL, emails);
        i.putExtra(Intent.EXTRA_SUBJECT, subject);
        i.putExtra(Intent.EXTRA_TEXT, message);
        try {
            startActivity(Intent.createChooser(i, "Send mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(MainActivity.this,
                    "There are no email clients installed.", Toast.LENGTH_SHORT).show();
        }
    }
}
