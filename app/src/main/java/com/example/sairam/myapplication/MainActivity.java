package com.example.sairam.myapplication;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements LocationListener {

    private String latitude;
    private String longitude;
    private boolean flag;
    protected LocationManager locationManager;
    protected SmsManager smsManager;
    protected Button accidentButton;
    protected Button medicalButton;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.ACCESS_FINE_LOCATION},0);

        }

        locationManager=(LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,50,this);
        smsManager=SmsManager.getDefault();
        accidentButton= (Button) findViewById(R.id.button_accident);
        medicalButton=(Button) findViewById(R.id.button_medical);
        flag=false;

        //setting listeners

        accidentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                while(!flag);
                Toast.makeText(getBaseContext(),"sending message",Toast.LENGTH_LONG).show();
                smsManager.sendTextMessage("+919498095995",null,"latitude"+latitude+" longitude "+longitude+" accident",null,null);//number can be replaced with the number of emergency centre
                Toast.makeText(getBaseContext(),"message sent",Toast.LENGTH_LONG).show();
            }
        });
        medicalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                while(!flag);
                Toast.makeText(getBaseContext(),"sending message",Toast.LENGTH_LONG).show();
                smsManager.sendTextMessage("+919498095995",null,"latitude"+latitude+" longitude "+longitude+" medical",null,null);//number can be replaced with the number of emergency centre
                Toast.makeText(getBaseContext(),"message sent",Toast.LENGTH_LONG).show();
            }
        });

        //end of listeners
    }




    public void onClick(View args0) {
        Intent i=new Intent(this,MainActivity2.class);
        startActivity(i);

    }


    @Override
    public void onLocationChanged(Location location) {
        latitude=" "+location.getLatitude();
        longitude=" "+location.getLongitude();
        Toast.makeText(this,"la="+latitude+"lon="+longitude,Toast.LENGTH_LONG).show();
        flag=true;
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