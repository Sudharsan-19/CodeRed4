package com.example.codered4;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.auth.api.identity.BeginSignInRequest;
import com.google.android.gms.auth.api.identity.SignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class homeActivity extends AppCompatActivity implements CourseRVAdapter.CourseClickInterface {

    FloatingActionButton  addbtn;
    GoogleSignInClient mGoogleSignInClient;
    private RecyclerView cardRV;
    private ProgressBar loadingPB;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private ArrayList<CourseRVModal> courseRVModalArrayList;
    private RelativeLayout homeRL;
    private SignInClient oneTapClient;
    private BeginSignInRequest signInRequest;
    private static final int REQ_ONE_TAP = 2;  // Can be any integer unique to the Activity.
    private boolean showOneTapUI = true;

    Button signOT;
    private FirebaseAuth mAuth;
    private CourseRVAdapter courseRVAdapter;
    TextView name,bloodGrp,dob,age;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        cardRV = findViewById(R.id.idRVCard);
        loadingPB = findViewById(R.id.loading);
        addbtn = (FloatingActionButton) findViewById(R.id.regBtn);


        signOT=findViewById(R.id.signOut);


        name=findViewById(R.id.name);
        bloodGrp=findViewById(R.id.bloodGroup);
        dob=findViewById(R.id.dob);
        age=findViewById(R.id.age);

        String name1=getIntent().getStringExtra("Google Name");
//        name.setText(name1.toString());

        firebaseDatabase=FirebaseDatabase.getInstance("https://bloodapp-8d1d1-default-rtdb.asia-southeast1.firebasedatabase.app/");
        databaseReference=firebaseDatabase.getReference("Requirements");

        courseRVModalArrayList = new ArrayList<>();
        courseRVAdapter=new CourseRVAdapter(courseRVModalArrayList,this,this);
        cardRV.setLayoutManager(new LinearLayoutManager(this));
        cardRV.setAdapter(courseRVAdapter);


        signOT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signOut();
            }
        });

        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(homeActivity.this,requirements.class);
                startActivity(intent);
            }
        });
        getalldetails();
    }

    private void signOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Intent i=new Intent(homeActivity.this,MainActivity.class);
                    }
                });
    }


    void getalldetails()
    {
        courseRVModalArrayList.clear();
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                loadingPB.setVisibility(View.GONE);
                courseRVModalArrayList.add(snapshot.getValue(CourseRVModal.class));
                courseRVAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                loadingPB.setVisibility(View.GONE);
                courseRVAdapter.notifyDataSetChanged();
            }

            @SuppressLint("NotifyDataSetChanged")@Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                courseRVAdapter.notifyDataSetChanged();
                loadingPB.setVisibility(View.GONE);
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                courseRVAdapter.notifyDataSetChanged();
                loadingPB.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void onCourseClick(int position) {

    }

}