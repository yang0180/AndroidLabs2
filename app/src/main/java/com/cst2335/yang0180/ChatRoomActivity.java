package com.cst2335.yang0180;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ChatRoomActivity extends AppCompatActivity {

    ListView messageList;
    Button sendBtn;
    Button receiveBtn;
    EditText inputBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);

        messageList = findViewById(R.id.message_list);
        sendBtn = findViewById( R.id.sendBtn);
        receiveBtn = findViewById(R.id.receiveBtn);
        inputBox = findViewById(R.id.input_content);
        ArrayList<Message> messages = new ArrayList<>();
        MyAdapter adapter = new MyAdapter(messages);
        messageList.setAdapter(adapter);

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = inputBox.getText().toString();
                Message message = new Message(true, content);
                inputBox.setText("");
                adapter.addMessage(message);
                adapter.notifyDataSetChanged();
            }
        });

        receiveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = inputBox.getText().toString();
                Message message = new Message(false, content);
                inputBox.setText("");
                adapter.addMessage(message);
                adapter.notifyDataSetChanged();
            }
        });

        messageList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Message message = adapter.getItem(position);
                AlertDialog.Builder builder = new AlertDialog.Builder(ChatRoomActivity.this);
                builder.setTitle("Do you want to delete this?")
                        .setMessage("The selected row is: "+position+"\nThe database id : "+ message.getId())
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                adapter.removeMessage(position);
                                adapter.notifyDataSetChanged();
                            }
                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).create().show();

                return false;
            }
        });

    }

    private class MyAdapter extends BaseAdapter{

        private ArrayList<Message> messages;

        public MyAdapter(ArrayList<Message> messages) {
            this.messages = messages;
        }

        public void addMessage( Message message){
            this.messages.add(message);
        }

        public void removeMessage(int position){
            this.messages.remove(position);
        }

        @Override
        public int getCount() {
            return this.messages.size();
        }

        @Override
        public Message getItem(int position) {
            return this.messages.get(position);
        }

        @Override
        public long getItemId(int position) {
            return this.messages.get(position).getId();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if( convertView == null){
                LayoutInflater inflater = getLayoutInflater();
                Message message = getItem(position);
                if( message.isSend()){
                    convertView = inflater.inflate(R.layout.row_send_layout,parent,false);
                    TextView messageContent = convertView.findViewById(R.id.send_msg_content);
                    messageContent.setText(message.getContent());
                }else{
                    convertView = inflater.inflate(R.layout.row_receive_layout,parent,false);
                    TextView messageContent = convertView.findViewById(R.id.receive_msg_content);
                    messageContent.setText(message.getContent());
                }
            }

            return convertView;
        }
    }
}