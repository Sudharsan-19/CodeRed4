package com.example.codered4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.TimeUnit;

public class registeractivity extends AppCompatActivity  implements OnMapReadyCallback{
    String[] items1;
    String[] sitems;
    ArrayAdapter<String> adapterItems;
    AutoCompleteTextView autoComplete,autoComplete12,sex,autoCompleteSex;
    LinearLayout otpCol,resendL;
    TextInputEditText mob,TIETrName,Age,weight,email,pwd,pwd1;
    TextView resend;
    Fragment homeLocation,workLocation;
    Button GetOtp,submit,zoomIN,zoomOUT,zoomIN1,zoomOUT1;
    String homLoc,workLoc1;
    GoogleMap gMap;
    EditText inputnumber1;
    EditText inputnumber2;
    EditText inputnumber3;
    EditText inputnumber4;
    EditText inputnumber5;
    EditText inputnumber6;
    private GoogleMap mMap,mMap1;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    private static String otp;

    public static String getValue() {
        return otp;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeractivity);


        sex=findViewById(R.id.sexList);
        sitems=getResources().getStringArray(R.array.sexItems);
        adapterItems=new ArrayAdapter<String>(this,R.layout.select_items,sitems);
        sex.setAdapter(adapterItems);




        autoComplete12=findViewById(R.id.autoCompleteTV);
        items1=getResources().getStringArray(R.array.items1);
        adapterItems = new ArrayAdapter<String>(this, R.layout.select_items,items1);
        autoComplete12.setAdapter(adapterItems);

        otpCol=findViewById(R.id.otp123);
        otpCol.setVisibility(View.INVISIBLE);

        resendL=findViewById(R.id.resendLL);
        resendL.setVisibility(View.INVISIBLE);



        TIETrName=findViewById(R.id.TIETrName);
        Age=findViewById(R.id.Age);
        //weight=findViewById(R.id.weight);
        mob=findViewById(R.id.TIETrMob);
        email=findViewById(R.id.emailID);
        //pwd=findViewById(R.id.password);
        //pwd1=findViewById(R.id.Cpassword);
        submit=findViewById(R.id.submitBtn);


        inputnumber1=findViewById(R.id.input1);
        inputnumber2=findViewById(R.id.input2);
        inputnumber3=findViewById(R.id.input3);
        inputnumber4=findViewById(R.id.input4);
        inputnumber5 = findViewById(R.id.input5);
        inputnumber6=findViewById(R.id.input6);







        zoomIN=findViewById(R.id.zoomIN);
        zoomOUT=findViewById(R.id.zoomOUT);

        zoomIN1=findViewById(R.id.zoomIN1);
        zoomOUT1=findViewById(R.id.zoomOUT1);

        //SupportMapFragment supportMapFragment = (SupportMapFragment)
        //      getSupportFragmentManager().findFragmentById(R.id.map);
        //supportMapFragment.getMapAsync(this);



        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(onMapReadyCallback1());

        SupportMapFragment mapFragmentTwo = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map1);
        mapFragmentTwo.getMapAsync(onMapReadyCallback2());




        GetOtp=findViewById(R.id.verifyBtn);
        GetOtp.setOnClickListener(view -> {
            if(!mob.getText().toString().trim().isEmpty()) {
                if((mob.getText().toString().trim()).length() == 10) {

                    otpCol.setVisibility(View.VISIBLE);
                    resendL.setVisibility(View.VISIBLE);
                    PhoneAuthProvider.getInstance().verifyPhoneNumber(
                            "+91" + mob.getText().toString(),
                            60,
                            TimeUnit.SECONDS,
                            registeractivity.this,
                            new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                @Override
                                public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                    otpCol.setVisibility(View.VISIBLE);
                                    resendL.setVisibility(View.VISIBLE);
                                }

                                @Override
                                public void onVerificationFailed(@NonNull FirebaseException e) {
                                    Toast.makeText(registeractivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                    otpCol.setVisibility(View.VISIBLE);
                                    resendL.setVisibility(View.VISIBLE);
                                }

                                @Override
                                public void onCodeSent(@NonNull String backOTP, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                    otp=backOTP;
                                    otpCol.setVisibility(View.VISIBLE);
                                    resendL.setVisibility(View.VISIBLE);

                                }
                            }
                    );

                }else{
                    Toast.makeText(registeractivity.this, "Check your number", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(registeractivity.this, "Please verify your number", Toast.LENGTH_SHORT).show();
            }
        });

        setupOTPInputs();
        submit.setOnClickListener(view -> {
            haha();
        });
        setupOTPInputs();

        resend=findViewById(R.id.resendOTP);
        resend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        "+91" + mob.getText().toString(),
                        60,
                        TimeUnit.SECONDS,
                        registeractivity.this,
                        new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                Toast.makeText(registeractivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                            }

                            @Override
                            public void onCodeSent(@NonNull String newbackOTP, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                otp=newbackOTP;
                                Toast.makeText(registeractivity.this, "OTP sent successfully", Toast.LENGTH_SHORT).show();

                            }
                        }
                );
            }
        });

    }

    public OnMapReadyCallback onMapReadyCallback1(){
        return new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                mMap = googleMap;
                //LatLng vannes = new LatLng(47.66, -2.75);
                // mMap.addMarker(new MarkerOptions().position(vannes).title("Vannes"));
                //mMap.moveCamera(CameraUpdateFactory.newLatLng(vannes));
                mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                    @Override
                    public void onMapClick(@NonNull LatLng latLng) {
                        MarkerOptions markerOptions = new MarkerOptions();
                        markerOptions.position(latLng);
                        markerOptions.title(latLng.latitude + ":" + latLng.longitude);
                        homLoc=(latLng.latitude+","+latLng.longitude);
                        Log.i("Home location link","Suspect lat and long "+ String.valueOf(homLoc));
                        mMap.clear();
                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,10));
                        mMap.addMarker(markerOptions);
                    }
                });
            }
        };
    }

    public OnMapReadyCallback onMapReadyCallback2(){
        return new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                mMap1 =googleMap;
                //LatLng vannes = new LatLng(47.66, -2.75);
                // mMap.addMarker(new MarkerOptions().position(vannes).title("Vannes"));
                //mMap.moveCamera(CameraUpdateFactory.newLatLng(vannes));
                mMap1.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                    @Override
                    public void onMapClick(@NonNull LatLng latLng) {
                        MarkerOptions markerOptions = new MarkerOptions();
                        markerOptions.position(latLng);
                        markerOptions.title(latLng.latitude + ":" + latLng.longitude);
                        workLoc1=(latLng.latitude+","+latLng.longitude);
                        Log.i("Work location link","Suspect lat and long "+ String.valueOf(workLoc1));
                        mMap1.clear();
                        mMap1.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,10));
                        mMap1.addMarker(markerOptions);
                    }
                });
            }
        };
    }


    private void haha(){
        if(!mob.getText().toString().trim().isEmpty() && !mob.getText().toString().trim().isEmpty() && !mob.getText().toString().trim().isEmpty() && !mob.getText().toString().trim().isEmpty() && !mob.getText().toString().trim().isEmpty() && !mob.getText().toString().trim().isEmpty()){
            String enterOTP=inputnumber1.getText().toString()+
                    inputnumber2.getText().toString()+
                    inputnumber3.getText().toString()+
                    inputnumber4.getText().toString()+
                    inputnumber5.getText().toString()+
                    inputnumber6.getText().toString();

            if(otp!=null){
                PhoneAuthCredential phoneauthcredential= PhoneAuthProvider.getCredential(
                        otp, enterOTP
                );
                FirebaseAuth.getInstance().signInWithCredential(phoneauthcredential)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if(task.isSuccessful()){
                                    Toast.makeText(registeractivity.this, "OTP Verified",Toast.LENGTH_SHORT).show();
                                    haha1();
                                }else{
                                    Toast.makeText(registeractivity.this, "Enter correct OTP",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });


            }else{
                Toast.makeText(registeractivity.this, "Check your internet connection",Toast.LENGTH_SHORT).show();
            }




            //Toast.makeText(Verify.this, "OTP verified",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(registeractivity.this, "Error",Toast.LENGTH_SHORT).show();
        }
    }



    private void setupOTPInputs(){
        inputnumber1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(! charSequence.toString().trim().isEmpty()){
                    inputnumber2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        inputnumber2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(! charSequence.toString().trim().isEmpty()){
                    inputnumber3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        inputnumber3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(! charSequence.toString().trim().isEmpty()){
                    inputnumber4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        inputnumber4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(! charSequence.toString().trim().isEmpty()){
                    inputnumber5.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        inputnumber5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(! charSequence.toString().trim().isEmpty()){
                    inputnumber6.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void haha1(){
        String UName=TIETrName.getText().toString();
        String UAge=Age.getText().toString();
        String USex=sex.getText().toString();
        String UBlood_Group=autoComplete12.getText().toString();
        String UEmail=email.getText().toString();
        String UmobNumber=mob.getText().toString();
        String UworkLoc=homLoc.toString();
        String UhomeLoc=workLoc1.toString();

        // Log.i( "haha1: ","hehe "+ UName);

        //Log.i( "haha12: ","hehe12 "+ String.valueOf(UhomeLoc));
        //Log.i( "haha234: ","hehe234 "+ String.valueOf(UmobNumber));

        dbHelper dbhelper=new dbHelper(UName,UAge,USex,UBlood_Group,UEmail,UmobNumber,UworkLoc,UhomeLoc);
        firebaseDatabase = FirebaseDatabase.getInstance("https://bloodapp-8d1d1-default-rtdb.asia-southeast1.firebasedatabase.app/");
        databaseReference=firebaseDatabase.getReference("USERS").child(UBlood_Group);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                databaseReference.setValue(dbhelper);
                Log.i("nameee","name"+UName);
                Toast.makeText(registeractivity.this, "Added to db successfully", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getApplicationContext(),homeActivity.class);
                startActivity(intent);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(registeractivity.this,"Error in "+error.toString(),Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        onMapReadyCallback1();
        onMapReadyCallback2();
    }


}