package com.example.codered4;

import static android.app.Notification.EXTRA_NOTIFICATION_ID;
import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.app.TaskStackBuilder;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.camera2.params.MandatoryStreamCombination;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.codered4.MainActivity;
import com.example.codered4.R;
import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Objects;

public class requirements extends AppCompatActivity {
    String[] items;
    String[] items1;
    ArrayAdapter<String> adapterItems;
    AutoCompleteTextView bloodGroup1,UnitNeeded1;
    TextInputEditText ddate,reason;
    Button subBtn;
    private TextInputEditText RecipientName1,aadhar1,DOB1,MobileNumber1,Hospitalname1,HAddress1,Need1;
    private ProgressBar loadingPB1;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requirements);
        RecipientName1 = findViewById(R.id.RecipientName);
        aadhar1 = findViewById(R.id.aadhar);
        DOB1 = findViewById(R.id.DOB);
        MobileNumber1=findViewById(R.id.MobileNumber);





        bloodGroup1=findViewById(R.id.bloodGroup);
        Hospitalname1=findViewById(R.id.Hospitalname);
        reason=findViewById(R.id.Need);

        HAddress1=findViewById(R.id.HAddress);



        UnitNeeded1= findViewById(R.id.UnitNeeded);
        items=getResources().getStringArray(R.array.items11);
        adapterItems = new ArrayAdapter<String>(this, R.layout.select_items,items);
        UnitNeeded1.setAdapter(adapterItems);

        items1=getResources().getStringArray(R.array.items1);
        adapterItems = new ArrayAdapter<String>(this, R.layout.select_items,items1);
        bloodGroup1.setAdapter(adapterItems);
        ddate=findViewById(R.id.DOB);



        subBtn=findViewById(R.id.submitBtn1);

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        ddate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePD = new DatePickerDialog(
                        requirements.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        month = month + 1;
                        String date = day + "/" + month + "/" + year;
                        ddate.setText(date);
                    }
                }, year, month, day);
                datePD.show();
            }
        });



        subBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db();

                //addNotification();
                //sendSMS();

            }
        });
    }
    private void db(){
        String NameOfRecipient=RecipientName1.getText().toString();
        String aadharNumber=aadhar1.getText().toString();
        String dateOfBirth=DOB1.getText().toString();
        String mobileNumber=MobileNumber1.getText().toString();
        String bloodGroup=bloodGroup1.getText().toString();
        String unitNeeded=UnitNeeded1.getText().toString();
        String NameofHospital=Hospitalname1.getText().toString();
        String hospitalAddress=HAddress1.getText().toString();
        String Reason_of_the_need=reason.getText().toString();

        CourseRVModal courseRVModal=new CourseRVModal(NameOfRecipient,aadharNumber,dateOfBirth,mobileNumber,bloodGroup,unitNeeded,NameofHospital,hospitalAddress,Reason_of_the_need);

        firebaseDatabase=FirebaseDatabase.getInstance("https://bloodapp-8d1d1-default-rtdb.asia-southeast1.firebasedatabase.app/");
        databaseReference=firebaseDatabase.getReference("Requirements");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                databaseReference.setValue(courseRVModal);
                Toast.makeText(requirements.this, "Requirement of Blood is Broadcasting Now.!", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(requirements.this, "Error:"+error, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void addNotification() {
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("YOUR_CHANNEL_ID",
                    "YOUR_CHANNEL_NAME",
                    NotificationManager.IMPORTANCE_HIGH);
            channel.setDescription("YOUR_NOTIFICATION_CHANNEL_DESCRIPTION");
            mNotificationManager.createNotificationChannel(channel);
        }
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getApplicationContext(), "YOUR_CHANNEL_ID")
                .setSmallIcon(R.drawable.ic_cr_final) // notification icon
                .setContentTitle("hahahaha") // title for notification
                .setContentText("TRyyyy")// message for notification
                .setAutoCancel(true); // clear notification after click
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(pi);
        mNotificationManager.notify(0, mBuilder.build());
    }
    private void sendSMS() {
        String no = "9791269102";
        String msg = "haha test haha";
        SmsManager sms=SmsManager.getDefault();
        sms.sendTextMessage(no.toString(), null, msg, null,null);

        Toast.makeText(getApplicationContext(), "Message Sent successfully!",
                Toast.LENGTH_LONG).show();
    }
}
