package com.example.dell.helpinghands.ui;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.helpinghands.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView textViewtitle,textViewdes;
    EditText editTexttitle,editTextdes;
    Button buttonsave;

    FirebaseFirestore firestore;

    void initviews(){

        textViewtitle=findViewById(R.id.textViewtitle);
        textViewdes=findViewById(R.id.textViewdes);
        editTexttitle=findViewById(R.id.edittexttitle);
        editTextdes=findViewById(R.id.edittextdes);
        buttonsave=findViewById(R.id.buttonsave);
        buttonsave.setOnClickListener(this);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firestore = FirebaseFirestore.getInstance();
        initviews();
    }


    @Override
    public void onClick(View view) {

        String title= editTexttitle.getText().toString();
        String message= editTextdes.getText().toString();

        Map<String,String> usermap = new HashMap<>();
        usermap.put("title",title);
        usermap.put("message",message);

        firestore.collection("NeedyDetails").add(usermap).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(MainActivity.this,"Data added successfully",Toast.LENGTH_LONG).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                String error=e.getMessage();
                Toast.makeText(MainActivity.this,"Error:" + error,Toast.LENGTH_LONG).show();
            }
        });

        Intent intent=new Intent();
        intent.setClass(MainActivity.this,MainActivity.class);

        intent.putExtra("title",title);
        intent.putExtra("message",message);
        startActivity(intent);

    }
}
