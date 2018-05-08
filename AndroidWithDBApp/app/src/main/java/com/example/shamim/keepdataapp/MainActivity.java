package com.example.shamim.keepdataapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText txtName,txtAge,txtGender;
    private Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtName=(EditText)findViewById(R.id.txtName);
        txtAge=(EditText)findViewById(R.id.txtAge);
        txtGender=(EditText)findViewById(R.id.txtGender);
        btnAdd =(Button)findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if(v.getId()==R.id.btnAdd){
            String name=txtName.getText().toString();
            String age=txtAge.getText().toString();
            String gender=txtGender.getText().toString();

        }
    }
}
