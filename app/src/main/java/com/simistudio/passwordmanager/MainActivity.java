package com.simistudio.passwordmanager;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText acc,user,pwd;
    Button insert, view;
    password_database g;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        acc=findViewById(R.id.acc);
        user=findViewById(R.id.user);
        pwd=findViewById(R.id.pwd);
        insert=findViewById(R.id.insert);
        view=findViewById(R.id.view);

        g= new password_database(this);
        //SQLiteDatabase db =g.getReadableDatabase();

        insert.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view ) {
                String account1 = acc.getText().toString();
                String username1= user.getText().toString();
                String password1=pwd.getText().toString();

                if (account1.equals("")||username1.equals("")||password1.equals(""))
                {
                    Toast.makeText(MainActivity.this,"Enter all the fields", Toast.LENGTH_SHORT).show();
                }
                else{
                       Boolean i= g.insert_data(account1,username1,password1);
                       if (i==true){
                           Toast.makeText(MainActivity.this,"Successful",Toast.LENGTH_SHORT).show();
                       }
                    else{
                        Toast.makeText(MainActivity.this,"Not Successful",Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor t=g.get_info();
                if (t.getCount()==0){
                    Toast.makeText(MainActivity.this,"No Data found", Toast.LENGTH_SHORT).show();
                }
                StringBuffer buffer=new StringBuffer();
                while(t.moveToNext()){
                    buffer.append("Account::"+ t.getString(1)+"\n");
                    buffer.append("Username::"+ t.getString(2)+"\n");
                    buffer.append("Password::"+ t.getString(3 )+"\n\n");
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("User's Accounts Data");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });
}
}