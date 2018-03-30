package com.example.sih;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class emergency_contact extends AppCompatActivity {
ImageButton back;
LinearLayout ambulance_call, disaster_relief_call;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_contact);
        back = findViewById(R.id.back_emergency_contact);
        ambulance_call = findViewById(R.id.ambulance_dial);
        disaster_relief_call = findViewById(R.id.disaster_relief_call);

        ambulance_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialNum("123456789");
            }
        });

        disaster_relief_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialNum("9833199727");
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
    }

    public void dialNum(String num)
    {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + num));
        startActivity(intent);
    }
}
