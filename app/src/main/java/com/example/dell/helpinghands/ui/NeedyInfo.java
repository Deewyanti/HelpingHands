package com.example.dell.helpinghands.ui;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.helpinghands.R;
import com.example.dell.helpinghands.model.Address;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class NeedyInfo extends AppCompatActivity implements View.OnClickListener{

    TextView textViewname,textViewage,textViewaddress,textViewcontact,textViewintent;
    EditText editTextname,editTextage,editTextadd1,editTextadd2,editTextadd3,editTextcont1,editTextcont2;

    FirebaseFirestore firestore;

    void initviews(){
        textViewname=findViewById(R.id.textViewname);
        textViewage=findViewById(R.id.textViewage);
        textViewaddress=findViewById(R.id.textViewaddress);
        textViewcontact=findViewById(R.id.textViewcontactno_1);
        textViewintent=findViewById(R.id.textviewintent);
        editTextname=findViewById(R.id.editTextname);
        editTextage=findViewById(R.id.edittextage);
        editTextadd1=findViewById(R.id.edittextadd1);
        editTextadd2=findViewById(R.id.edittextadd2);
        editTextadd3=findViewById(R.id.edittextadd3);
        editTextcont1=findViewById(R.id.edittextcont1);
        editTextcont2=findViewById(R.id.edittextcont2);

        textViewintent.setOnClickListener(this);
    }

    Spinner spinnercity,spinnerprob;
    ArrayAdapter<String> adapter;
    ArrayAdapter<String> adapterprob;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_needy_info);

       firestore= FirebaseFirestore.getInstance();

        initviews();

        spinnercity=findViewById(R.id.spinnercity);
        spinnerprob=findViewById(R.id.spinnerproblem);

        adapterprob=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item);
        adapterprob.add("--Problem type--");
        adapterprob.add("Child Abuse");
        adapterprob.add("Accidental Case");

        spinnerprob.setAdapter(adapterprob);
        spinnerprob.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String prob=adapterprob.getItem(i);
                Toast.makeText(NeedyInfo.this,"you selected"+prob,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item);
        adapter.add("---Select City---");
        adapter.add("Ludhiana");
        adapter.add("Chandigarh");
        adapter.add("Delhi");
        adapter.add("Bengaluru");
        adapter.add("California");

        spinnercity.setAdapter(adapter);

        spinnercity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String city=adapter.getItem(i);
                Toast.makeText(NeedyInfo.this,"you selected"+city,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

   /* void details(View view){


        String name= editTextname.getText().toString();
        String age = editTextage.getText().toString();
        String add1 = editTextadd1.getText().toString();
        String add2 = editTextadd2.getText().toString();
        String add3 = editTextadd3.getText().toString();
        String cont1 = editTextcont1.getText().toString();
        int con1= Integer.parseInt(cont1);
        String cont2 = editTextcont2.getText().toString();
        int con2= Integer.parseInt(cont2);

        Intent intent=new Intent();
        intent.setClass(NeedyInfo.this,NeedyInfo.class);

        intent.putExtra("name",name);
        intent.putExtra("age",age);
        intent.putExtra("add1",add1);
        intent.putExtra("add2",add2);
        intent.putExtra("add3",add3);


        intent.putExtra("cont1",con1);
        intent.putExtra("cont2",con2);
        startActivity(intent);
    }*/

    @Override
    public void onClick(View view) {

        String name= editTextname.getText().toString();
        String age = editTextage.getText().toString();
        String add1 = editTextadd1.getText().toString();
        String add2 = editTextadd2.getText().toString();
        String add3 = editTextadd3.getText().toString();
        String cont1 = editTextcont1.getText().toString();

        String cont2 = editTextcont2.getText().toString();


        Map<String,String> usermap = new HashMap<>();
        usermap.put("name",name);
        usermap.put("age",age);
        usermap.put("add1",add1);
        usermap.put("add2",add2);
        usermap.put("add3",add3);
        usermap.put("con1",cont1);
        usermap.put("con2",cont2);
        firestore.collection("NeedyDetails").add(usermap).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(NeedyInfo.this,"Data added successfully",Toast.LENGTH_LONG).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                String error=e.getMessage();
                Toast.makeText(NeedyInfo.this,"Error:" + error,Toast.LENGTH_LONG).show();
            }
        });


        Intent intent=new Intent(NeedyInfo.this,MainActivity.class);
        startActivity(intent);

    }
}
