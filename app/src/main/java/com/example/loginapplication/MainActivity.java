package com.example.loginapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    EditText et_name2,et_pass2;
    Button bt_login2;
    String url = "http://192.168.43.94:130/api/values/";
    TextView tname,tpass,textcolor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tname = findViewById(R.id.textname);
        tpass = findViewById(R.id.textpass);
        textcolor = findViewById(R.id.textcolor);

        et_name2 = findViewById(R.id.name);
        et_pass2 = findViewById(R.id.pass);
        bt_login2 = findViewById(R.id.bt);

        bt_login2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                processlogin();
            }
        });
    }

    private void processlogin() {
        String Name = et_name2.getText().toString();
        String Password = et_pass2.getText().toString();

        Call<responsemodel> call=controller.getInstance().getapi().verifyuser(Name,Password);

        call.enqueue(new Callback<responsemodel>() {
            @Override
            public void onResponse(Call<responsemodel> call, Response<responsemodel> response) {
                responsemodel obj=response.body();
                String output=obj.getMessage();
                if(output!=null && output.equals("failed"))
                {
                    tname.setText("");
                    tpass.setText("");
                    textcolor.setTextColor(Color.RED);
                    textcolor.setText("Invalid username or password");

                }else {
                    Intent intent = new Intent(MainActivity.this, Login_page.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
//                    startActivity(new Intent(MainActivity.this,Login_page.class));
//                    finish();
//                    Toast.makeText(MainActivity.this, "Login", Toast.LENGTH_SHORT).show();
//                    if (output!=null && output.equals("exist")){
//
//                    }
                }

            }

            @Override
            public void onFailure(Call<responsemodel> call, Throwable t) {
                textcolor.setText(t.toString());
                textcolor.setTextColor(Color.RED);
            }
        });

    }
}