package com.cst2335.yang0180;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class ProfileActivity extends AppCompatActivity {

    ImageButton takePicBtn;
    EditText emailInput;
    Button toChatBtn;
    Button goToWeather;
    Button gotoolbar;
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final String ACTIVITY_NAME = "ProfileActivity";
    public static final int  REQUEST_TOOLBAR = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e(ACTIVITY_NAME, "In function: onCreate()");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        emailInput = findViewById(R.id.emailInput);
        goToWeather = findViewById(R.id.toWeatherForecastBtn);
        gotoolbar = findViewById(R.id.goToolbar);
        gotoolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toolbar = new Intent(ProfileActivity.this,TestToolbar.class);
//                startActivity(toolbar);
                startActivityForResult(toolbar, REQUEST_TOOLBAR);
            }
        });

        takePicBtn = findViewById(R.id.takePicBtn);
        takePicBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }
        });

        String email = getIntent().getStringExtra(MainActivity.EMAIL_KEY);
        emailInput.setText(email);

        toChatBtn = findViewById(R.id.toChatBtn);
        toChatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToChat = new Intent(ProfileActivity.this,ChatRoomActivity.class);
                startActivity(goToChat);
            }
        });

        goToWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToWeather = new Intent(ProfileActivity.this, WeatherForecast.class);
                startActivity(goToWeather);
            }
        });

    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            takePicBtn.setImageBitmap(imageBitmap);
        }

        if( requestCode == REQUEST_TOOLBAR && resultCode == TestToolbar.SUCCESS_FINISH){
            finish();
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(ACTIVITY_NAME, "In function: onPause()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(ACTIVITY_NAME, "In function: onResume()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(ACTIVITY_NAME, "In function:  onStart()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(ACTIVITY_NAME, "In function: onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(ACTIVITY_NAME, "In function:  onDestroy()");
    }
}