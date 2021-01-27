package com.cst2335.yang0180;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_grid);
        Button btn =findViewById(R.id.btn);
        btn.setOnClickListener(e->{
            Toast.makeText(this,R.string.information,Toast.LENGTH_LONG);});
        Switch sth = findViewById(R.id.sth);
//        sth.setText("The switch is now on");
//        sth.setTextOff("The switch is now off");
        sth.setOnCheckedChangeListener((s, checked)->{
            Snackbar snackbar = null;

            if (checked){
                snackbar = Snackbar.make(s,R.string.on,Snackbar.LENGTH_LONG);
                snackbar.setAction(R.string.undo, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sth.setChecked(!checked);
                    }
                });
            }else{
                snackbar = Snackbar.make(s,R.string.off,Snackbar.LENGTH_LONG);
                snackbar.setAction(R.string.undo, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sth.setChecked(!checked);
                    }
                });
            }

            if(snackbar != null){
                snackbar.show();
            }

        });
    }
}