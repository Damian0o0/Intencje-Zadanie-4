package com.example.intencjezadanie4;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText editTextUrl;
    private EditText editTextEmail;
    private Button buttonOpenBrowser;
    private Button buttonSendEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextUrl = findViewById(R.id.editTextUrl);
        editTextEmail = findViewById(R.id.editTextEmail);
        buttonOpenBrowser = findViewById(R.id.buttonOpenBrowser);
        buttonSendEmail = findViewById(R.id.buttonSendEmail);

        buttonOpenBrowser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = editTextUrl.getText().toString();
                if (!url.isEmpty()) {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(browserIntent);
                } else {
                    Toast.makeText(MainActivity.this, "Wprowadź poprawny adres URL", Toast.LENGTH_SHORT).show();
                }
            }
        });

        buttonSendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editTextEmail.getText().toString();
                if (!email.isEmpty()) {
                    Intent emailIntent = new Intent(Intent.ACTION_SEND);
                    emailIntent.setType("text/plain");
                    emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Temat wiadomości");
                    emailIntent.putExtra(Intent.EXTRA_TEXT, "Treść wiadomości");

                    if (emailIntent.resolveActivity(getPackageManager()) != null) {
                        startActivity(emailIntent);
                    } else {
                        Toast.makeText(MainActivity.this, "Brak aplikacji do wysyłania e-maila", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Wprowadź poprawny adres e-mail", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}