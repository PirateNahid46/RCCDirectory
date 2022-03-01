package com.rcc.rccdirectory;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {
    TextView cadetNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        String cn = getIntent().getStringExtra("cn");
        cadetNo = findViewById(R.id.cadetNo);
        cadetNo.setText(cn);

    }
}