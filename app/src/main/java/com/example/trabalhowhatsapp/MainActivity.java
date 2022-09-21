package com.example.trabalhowhatsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText phone, message;
    Button sendbtn;
    String messagestr, phonestr = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        phone = findViewById(R.id.editTextNumber);
        message = findViewById(R.id.editTextTextMessage);
        sendbtn = findViewById(R.id.sendbtn);

        sendbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                messagestr = message.getText().toString();
                phonestr = phone.getText().toString();

                if (!messagestr.isEmpty() && !phonestr.isEmpty()){

                    if (IsWhatsaapInstalled()){
                        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://api.whatsapp.com/send?phone=55"+phonestr+
                                "&text="+messagestr));
                        startActivity(i);
                        message.setText("");
                        phone.setText("");
                    }else{
                        Toast.makeText( MainActivity.this,"Falta baixar o zap amigão",Toast.LENGTH_SHORT).show();
                    }

                }else {
                    Toast.makeText(MainActivity.this, "Sem numero ou sem mensagem fica dificil trabalhar aqui amigão", Toast.LENGTH_LONG).show();
                }



            }
        });


    }

    private boolean IsWhatsaapInstalled(){
        PackageManager packageManager = getPackageManager();
        boolean whatsaapInstalled;


        try {
            packageManager.getPackageInfo("com.whatsapp",PackageManager.GET_ACTIVITIES);
            whatsaapInstalled = true;
        }catch (PackageManager.NameNotFoundException e){

            whatsaapInstalled = false;
        }
        return whatsaapInstalled;

    }

}