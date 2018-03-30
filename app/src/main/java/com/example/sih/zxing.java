package com.example.sih;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.example.sih.init.introduction;
import com.example.sih.init.slide2;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class zxing extends AppCompatActivity {

    private IntentIntegrator qrScan;
    Bundle bundle;
    public SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         bundle = new Bundle();

        setContentView(R.layout.activity_zxing);
        qrScan = new IntentIntegrator(this);
        qrScan.setPrompt("Scan the QR code on your Aadhar card");
        qrScan.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
        qrScan.initiateScan();

    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        String barcode = result.getContents();
        introduction.z = 1;
        if (result.getContents() == null) {


            Toast.makeText(getApplicationContext(), "Result Not Found", Toast.LENGTH_LONG).show();
        }
        else
        {

            Log.d("ayyyyyyyyyyyyyyyy", barcode);
            Toast.makeText(getApplicationContext(), barcode, Toast.LENGTH_LONG).show();
           useless.qrcodeS = barcode;
            bundle.putString("barcode", barcode);
            slide2 newobj = new slide2();
            newobj.setArguments(bundle);
        }

        finish();
    }
}
