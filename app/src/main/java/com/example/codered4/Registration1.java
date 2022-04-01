package com.example.codered4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.material.textfield.TextInputEditText;

public class Registration1 extends AppCompatActivity {
    String[] items1;
    ArrayAdapter<String> adapterItems;
    AutoCompleteTextView autoComplete,autoComplete12;
    LinearLayout otpCol,resendL;
    TextInputEditText mob,TIETrName,Age,weight,workLocation,email,pwd,pwd1;
    TextView resend,homeLocation;
    Button GetOtp,submit,zoomIN,zoomOUT;
    GoogleMap gMap;
    EditText inputnumber1;
    EditText inputnumber2;
    EditText inputnumber3;
    EditText inputnumber4;
    EditText inputnumber5;
    EditText inputnumber6;




    private static String otp;

    public static String getValue() {

        return otp;
    }
    private static String name;
    public static String getname(){

        return name;
    }
    private static String age;
    public static String getAge(){

        return age;
    }
    private static String weight1;
    public static String getWeight1(){

        return weight1;
    }
    private static String av;
    public static String getAv(){

        return av;
    }
    private static String emaill;
    public static String getEmaill(){

        return emaill;
    }
    private static String pwdd;
    public static String getPwdd(){

        return pwdd;
    }
    private static String pwddd;
    public static String getPwddd(){

        return pwddd;
    }private static String mobileNum;
    public static String getMobileNum(){

        return mobileNum;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration1);

        autoComplete= findViewById(R.id.autoCompleteTV);
        items1=getResources().getStringArray(R.array.items1);
        adapterItems = new ArrayAdapter<String>(this, R.layout.select_items,items1);
        autoComplete.setAdapter(adapterItems);

        otpCol=findViewById(R.id.otp123);
        otpCol.setVisibility(View.INVISIBLE);

        resendL=findViewById(R.id.resendLL);
        resendL.setVisibility(View.INVISIBLE);

        mob=findViewById(R.id.TIETrMob);

        email=findViewById(R.id.emailID);
        pwd=findViewById(R.id.password);
        pwd1=findViewById(R.id.Cpassword);

        submit=findViewById(R.id.submitBtn);


        inputnumber1=findViewById(R.id.input1);
        inputnumber2=findViewById(R.id.input2);
        inputnumber3=findViewById(R.id.input3);
        inputnumber4=findViewById(R.id.input4);
        inputnumber5 = findViewById(R.id.input5);
        inputnumber6=findViewById(R.id.input6);

        TIETrName=findViewById(R.id.TIETrName);
        Age=findViewById(R.id.Age);
        weight=findViewById(R.id.weight);
        autoComplete12=findViewById(R.id.autoCompleteTV);
        //homeLocation=findViewById(R.id.homeLocation);
        //workLocation=findViewById(R.id.workLocation);
    }
}