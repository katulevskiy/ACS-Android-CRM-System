package com.example.androidcrmsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import java.util.List;

public class EmailPasswordActivity extends AppCompatActivity  implements View.OnClickListener{

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference myRef;

    private EditText ETemail;
    private EditText ETpassword;
    private EditText ETadmincode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_password);

        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth){
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null){
                    //user signed in
//                    myRef = FirebaseDatabase.getInstance("https://ultimate-crm-1337-default-rtdb.europe-west1.firebasedatabase.app").getReference();
//                    String uid = user.getUid();
//
//                    myRef.child(uid).child("UserInfo").child("isAdmin").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
//                        @Override
//                        public void onComplete(@NonNull Task<DataSnapshot> task) {
//                            if (!task.isSuccessful()) {
//                                Log.e("firebase", "Error getting data", task.getException());
//                            }
//                            else {
//                                String isAdmin = String.valueOf(task.getResult().getValue());
//                                if (isAdmin.equals("yes")){
//                                    Intent intent = new Intent(EmailPasswordActivity.this, MainActivity.class);
//                                    startActivity(intent);
//                                }
//                                else{Intent intent = new Intent(EmailPasswordActivity.this, MainActivity.class);
//                                    startActivity(intent);}
//                            }
//                        }
//                    });

                    Intent intent = new Intent(EmailPasswordActivity.this, MainActivity.class);
                    startActivity(intent);

                    //Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());

                } else {
                    //user logged out
                    //Log.d(TAG, "onAuthStateChanged:signed_out");

                }

            }
        };


        ETemail = (EditText) findViewById(R.id.et_email);
        ETpassword = (EditText) findViewById(R.id.et_password);
        ETadmincode = (EditText) findViewById(R.id.et_admincode);

        findViewById(R.id.btn_sign_in).setOnClickListener(this);
        findViewById(R.id.btn_registration).setOnClickListener(this);

        FirebaseUser user = mAuth.getCurrentUser();

        if(user != null) {
            Intent intent = new Intent(EmailPasswordActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btn_sign_in)
        {
            signin(ETemail.getText().toString(),ETpassword.getText().toString());
        }
        else if (view.getId() == R.id.btn_registration)
        {
            String isAdmin = "no";
            String admincode = ETadmincode.getText().toString();
            if (admincode.equals("0000")){
                isAdmin="yes";
            }
            registration(ETemail.getText().toString(),ETpassword.getText().toString(), isAdmin);
        }

    }

    public void signin(String email , String password)
    {
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()) {
                    Toast.makeText(EmailPasswordActivity.this, "Aвторизация успешна", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(EmailPasswordActivity.this, EmailPasswordActivity.class);
                    startActivity(intent);

                }else {
                    Toast.makeText(EmailPasswordActivity.this, "Aвторизация провалена", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(EmailPasswordActivity.this, EmailPasswordActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    public void registration (String email , String password, String isAdmin){
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    //Create a new user tree with ID, Tasks, 0:"No active tasks"
                    myRef = FirebaseDatabase.getInstance("https://ultimate-crm-1337-default-rtdb.europe-west1.firebasedatabase.app").getReference();
                    FirebaseUser user = mAuth.getInstance().getCurrentUser();
                    String uid = user.getUid();
                    myRef.child(uid).child("UserInfo").child("email").setValue(email);
                    myRef.child(uid).child("UserInfo").child("isAdmin").setValue(isAdmin);
                    myRef.child(uid).child("Tasks").child("0").child("Title").setValue("No active tasks");
                    myRef.child(uid).child("Tasks").child("0").child("Description").setValue("No available descriptions");
                    myRef.child(uid).child("Tasks").child("0").child("Deadline").setValue("No deadlines set up yet");
                    Toast.makeText(EmailPasswordActivity.this, "Регистрация успешна", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(EmailPasswordActivity.this, EmailPasswordActivity.class);
                    startActivity(intent);
                    //Created a user

                }
                else
                    Toast.makeText(EmailPasswordActivity.this, "Регистрация провалена", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(EmailPasswordActivity.this, EmailPasswordActivity.class);
                    startActivity(intent);
            }
        });
        // Add admin access if admin code is correct
    }
}