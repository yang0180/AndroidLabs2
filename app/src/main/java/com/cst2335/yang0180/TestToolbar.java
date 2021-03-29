package com.cst2335.yang0180;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class TestToolbar extends AppCompatActivity {
Toolbar tb;
NavigationView navigationView;
public static final int SUCCESS_FINISH = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_toolbar);
        tb = findViewById(R.id.testtoolbar);
        setSupportActionBar(tb);

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if( item.getItemId() == R.id.nav_chat){
                    Intent toChat = new Intent(TestToolbar.this, ChatRoomActivity.class);
                    startActivity(toChat);
                }
                if( item.getItemId() == R.id.nav_weather){
                    Intent toweather = new Intent(TestToolbar.this, WeatherForecast.class);
                    startActivity(toweather);
                }
                if( item.getItemId() == R.id.nav_go){
//                    Toast.makeText(TestToolbar.this,"test",Toast.LENGTH_SHORT).show();
//                    finishActivity(SUCCESS_FINISH);
                    Intent returnToPrev = new Intent();
                    setResult(SUCCESS_FINISH, returnToPrev);
                    finish();
                }
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
     getMenuInflater().inflate(R.menu.toolbar_items,menu);
     return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
      if (item.getItemId()==R.id.choice1) {
          Toast.makeText(TestToolbar. this, "You click on item 1",Toast.LENGTH_SHORT).show();
      }
        if (item.getItemId()==R.id.choice2){
            Toast.makeText(TestToolbar. this, "You click on item 2",Toast.LENGTH_SHORT).show();
        }
        if (item.getItemId()==R.id.choice3) {
            Toast.makeText(TestToolbar. this, "You click on item 3",Toast.LENGTH_SHORT).show();
        }
        if (item.getItemId()==R.id.choice4) {
            Toast.makeText(TestToolbar. this, "You clicked on the overflow menu",Toast.LENGTH_SHORT).show();
        }
        return true;
    }

}


