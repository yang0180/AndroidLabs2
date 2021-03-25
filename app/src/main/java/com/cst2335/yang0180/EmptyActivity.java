package com.cst2335.yang0180;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.TextView;

public class EmptyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty);
        //show the id:
        Message message = (Message) getIntent().getSerializableExtra(ChatRoomActivity.MESSAGE_TO_PASS);
        TextView idView = (TextView)findViewById(R.id.idTextviewEmpty);
        idView.setText("ID=" + message.getId());
        CheckBox isSendCb = (CheckBox) findViewById(R.id.sendCbEmpty);
        isSendCb.setChecked(message.isSend());
    }

}