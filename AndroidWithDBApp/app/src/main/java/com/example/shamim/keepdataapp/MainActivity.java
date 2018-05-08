package com.example.shamim.keepdataapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText txtName,txtAge,txtGender;
    private Button btnAdd;
    private  Button btnShow;
    MyDataBaseHelper myDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDatabaseHelper=new MyDataBaseHelper(this);
        SQLiteDatabase sqLiteDatabase =myDatabaseHelper.getWritableDatabase();

        txtName=(EditText)findViewById(R.id.txtName);
        txtAge=(EditText)findViewById(R.id.txtAge);
        txtGender=(EditText)findViewById(R.id.txtGender);
        btnAdd =(Button)findViewById(R.id.btnAdd);
        btnShow =(Button)findViewById(R.id.btnShow);

        btnAdd.setOnClickListener(this);
        btnShow.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if(v.getId()==R.id.btnAdd){
            String name=txtName.getText().toString();
            String age=txtAge.getText().toString();
            String gender=txtGender.getText().toString();

          long rowId = myDatabaseHelper.InsertData(name,age,gender);
          if(rowId>1){
              Toast.makeText(getApplicationContext(),"Data Saved Successfully",Toast.LENGTH_LONG).show();
          }else {
              Toast.makeText(getApplicationContext(),"Data Saved UnSuccessfully",Toast.LENGTH_LONG).show();

          }
        }
        if(v.getId()==R.id.btnShow){
            Cursor cursor=myDatabaseHelper.GetAllData();
            if(cursor.getCount()==0){
                ShowData("Result Set","Data Was Not Found");
                return;
            }
            StringBuffer stringBuffer=new StringBuffer();
            while (cursor.moveToNext()){

               stringBuffer.append("ID: "+cursor.getString(0)+"\n\n");
                stringBuffer.append("Name: "+cursor.getString(1)+"\n\n");
                stringBuffer.append("Age: "+cursor.getString(2)+"\n\n");
                stringBuffer.append("Gender: "+cursor.getString(3)+"\n\n");
            }
            ShowData("Result Set",stringBuffer.toString());
        }
    }

    private void ShowData(String title, String resultSet) {
     AlertDialog.Builder builder=new AlertDialog.Builder(this);
     builder.setTitle(title);
     builder.setMessage(resultSet);
     builder.setCancelable(true);
     builder.show();

    }
}
