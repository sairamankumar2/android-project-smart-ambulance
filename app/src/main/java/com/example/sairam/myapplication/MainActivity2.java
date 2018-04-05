package com.example.sairam.myapplication;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity implements LocationListener  {
    Button fireButton;
    Button policeButton;
    private String latitude;
    private String longitude;
    private boolean flag;
    protected LocationManager locationManager;
    protected SmsManager smsManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        flag=false;
        smsManager=SmsManager.getDefault();

        if (ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.ACCESS_FINE_LOCATION},0);

        }

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 50, this);
        fireButton = (Button) findViewById(R.id.button_fire);
        policeButton = (Button) findViewById(R.id.button_police);


        //sending sms

        policeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                while (!flag) ;
                Toast.makeText(getBaseContext(), "sending message", Toast.LENGTH_LONG).show();
                smsManager.sendTextMessage("+919498095995", null,"latitude"+latitude+" longitude "+longitude+" police", null, null);//number can be replaced with the number of emergency centre
                Toast.makeText(getBaseContext(), "message sent", Toast.LENGTH_LONG).show();
            }
        });

        fireButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                while (!flag) ;
                Toast.makeText(getBaseContext(), "sending message", Toast.LENGTH_LONG).show();
                smsManager.sendTextMessage("+919498095995", null,"latitude"+latitude+" longitude "+longitude+ " fire", null, null);//number can be replaced with the number of emergency centre
                Toast.makeText(getBaseContext(), "message sent", Toast.LENGTH_LONG).show();
            }
        });


    }
    @Override
    public void onLocationChanged(Location location) {
        latitude=" "+location.getLatitude();
        longitude=" "+location.getLongitude();
        flag=true;
        Toast.makeText(this,"la="+latitude+"lon="+longitude,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
