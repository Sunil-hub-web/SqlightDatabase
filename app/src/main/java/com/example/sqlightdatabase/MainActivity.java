package com.example.sqlightdatabase;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.sqlightdatabase.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    DataBaseHelper dataBaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(MainActivity.this,R.layout.activity_main);

        dataBaseHelper=new DataBaseHelper(MainActivity.this);


        //save
        binding.save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean insert=dataBaseHelper.insertData(
                        binding.name.getText().toString().trim(),
                        binding.email.getText().toString().trim(),
                        binding.mobile.getText().toString().trim(),
                        binding.password.getText().toString().trim());
                if(insert==true){
                    Toast.makeText(MainActivity.this, "Sucess", Toast.LENGTH_SHORT).show();

                    binding.name.setText("");
                    binding.email.setText("");
                    binding.mobile.setText("");
                    binding.password.setText("");

                }else {
                    Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show();

                }

            }
        });

        //get
        binding.getall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res=dataBaseHelper.getAlldata();
                if(res.getCount()==0){
                    Toast.makeText(MainActivity.this, "Nodata", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer=new StringBuffer();
                while (res.moveToNext()){
                    buffer.append("Id " +res.getString(0)+"\n");
                    buffer.append("name " +res.getString(1)+"\n");
                    buffer.append("email " +res.getString(2)+"\n");
                    buffer.append("mobile " +res.getString(3)+"\n");
                    buffer.append("pass " +res.getString(4)+"\n");



                }
                ShowMessage("Data",buffer.toString());
            }
        });

        //update
        binding.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean update=dataBaseHelper.updateData(
                        binding.id.getText().toString().trim(),
                        binding.name.getText().toString().trim(),
                        binding.email.getText().toString().trim(),
                        binding.mobile.getText().toString().trim(),
                        binding.password.getText().toString().trim());
                if(update==true){
                    Toast.makeText(MainActivity.this, "Sucess", Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show();

                }

            }
        });


        //delete
        binding.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer delete=dataBaseHelper.deleteData(binding.id.getText().toString());
                if(delete>0){
                    Toast.makeText(MainActivity.this, "Sucess", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        binding.data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    private void ShowMessage(String data, String toString) {
        AlertDialog.Builder alert=new AlertDialog.Builder(MainActivity.this);
        alert.setTitle(data);
        alert.setMessage(toString);
        alert.show();


    }
    }
