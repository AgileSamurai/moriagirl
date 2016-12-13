package com.sample.agilesamurai.moriagirl;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;


public class SettingsActivity extends AppCompatActivity {
    RadioButton radioButton1;
    RadioButton radioButton2;
    SampleGLSurfaceView girlView;
    ImageView imageView;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        radioButton1 = (RadioButton)findViewById(R.id.button1);
        radioButton2 = (RadioButton)findViewById(R.id.button2);



    }

    public void  selectCharacter(View view) {
        AlertDialog.Builder alertDialogBuilder=new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("このアバターを選択していただきますか？");
        alertDialogBuilder.setIcon(R.drawable.ribbonkuma);

        alertDialogBuilder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {

                if(radioButton1.isChecked()){
                    Intent intent =new Intent(SettingsActivity.this,MainActivity.class);
                    intent.putExtra("data", "1");
                    startActivity(intent);
                    finish();
                }
                if(radioButton2.isChecked()){
                    Intent intent =new Intent(SettingsActivity.this,MainActivity.class);
                    intent.putExtra("data", "2");
                    startActivity(intent);
                    finish();
                }

            }
        });

        alertDialogBuilder.setNegativeButton(R.string.cancel ,new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                    radioButton1.setChecked(false);
                    radioButton2.setChecked(false);
            }
        });

        alertDialogBuilder.create().show();

    }
}
